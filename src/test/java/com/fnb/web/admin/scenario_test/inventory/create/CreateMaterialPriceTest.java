package com.fnb.web.admin.scenario_test.inventory.create;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.inventory.ingredients.DataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.fnb.web.setup.Setup.configObject;
import static com.fnb.web.setup.Setup.materialData;

// Sprint51
public class CreateMaterialPriceTest extends CommonPages {
    Helper helper;
    Actions actions;
    WebDriver driver;
    com.fnb.web.admin.pages.product.management.DataTest account;
    String ml;
    By selectionSearchUnit = By.xpath("//span[normalize-space()='" + DataTest.SelectUnit_PlaceHolder + "']/ancestor::div[contains(@class, 'ant-select-show-search')]");

    @BeforeClass
    public void beforeClass() {
        helper = adminPage().helper;
        actions = adminPage().actions;
        driver = getDriver();
        ml = materialData.getMaterial().get(0).getUnit();
        adminPage().navigateToHomePage(account.INPUT_EMAIL, account.INPUT_PASSWORD);
    }

    @BeforeMethod
    public void beforeMethod() {
        homePage().helper.navigateToUrl(configObject.getUrlCreateMaterial());
    }

    @Test(testName = "FB-12680 : Verify that the cost per unit is in decimal format")
    public void FB12680() {
        helper.scrollToElementAtMiddle(createMaterialPage().getTxtCostPerUnit());
        createMaterialPage()
                .enterCostPerUnit("4532.32333")
                .clickAddImportUnit();
        // Check the result of cost per unit
        // Expected cost per unit
        String expectedCostPerUnit = helper.formatDoubleToString(helper.roundToTwoDecimalPlaces("4532.32333"));
        String actualCostPerUnit = helper.getAttribute(createMaterialPage().getTxtCostPerUnit(), "value");
        Assert.assertEquals(actualCostPerUnit, expectedCostPerUnit, "The cost per unit is not correct with decimal format");
    }

    @Test(testName = "FB-12681 : Verify the required field")
    public void FB12681() {
        createMaterialPage().helper.clickElement(commonComponents().getBtnAddNew());

        // Verify the required material name
        helper.smartWait();
        Assert.assertEquals(helper.getText(By.xpath("//*[@id='createMaterialForm_name_help']")), DataTest.MaterialName_ErrorMessage);
        // Verify the required base unit
        Assert.assertEquals(helper.getText(By.xpath("//div[@id='createMaterialForm_unitId_help']")), DataTest.BaseUnit_ErrorMessage);

        // Verify the required please select branch
        Assert.assertEquals(helper.getText(By.xpath("//div[@id='createMaterialForm_branchIds_help']")), DataTest.Branch_ErrorMessage);
    }

    @Test(testName = "FB-12682 : Verify the autocomplete select option field of base unit")
    public void FB12682() {
        // Get the unit and split it and take the first word
        String unit = materialData.getMaterial().get(2).getUnit();
        String[] parts = unit.split("(?!^)");
        String firstPart = parts[0];

        createMaterialPage().helper.scrollToElementAtMiddle(createMaterialPage().getSelSearchBaseUnit());
        createMaterialPage().enterBaseUnit(firstPart);
        // Verify if the option is visble
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(text(),'" + unit + "') and contains(@class, 'option')]")), "The expected unit is not visible");
    }

    @Test(testName = "FB-12705 : Verify that load all existed units on the system")
    public void FB12705() {
        createMaterialPage().helper.scrollToElementAtMiddle(createMaterialPage().getSelSearchBaseUnit());
        createMaterialPage().helper.clickElement(selectionSearchUnit);
        WebElement iframe = driver.findElement(By.xpath("//div[@class='rc-virtual-list-holder-inner']"));
        int deltaY = iframe.getRect().y;
        int deltaX = iframe.getRect().x;
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);

        for (int i = 0; i < materialData.getMaterial().size(); i++) {
            By xpath = By.xpath("//div[contains(text(),'" + materialData.getMaterial().get(i).getUnit() + "') and contains(@class, 'option')]");
            int stopOffSet = 0;
            int stopOffSet2 = 0;
            // Scroll down to find the unit
            while (!helper.isElementVisible(xpath) && stopOffSet < deltaY) {
                new Actions(driver)
                        .scrollFromOrigin(scrollOrigin, 0, 200)
                        .perform();
                stopOffSet = stopOffSet + 200;
            }
            Assert.assertTrue(helper.isElementVisible(xpath), "The " + materialData.getMaterial().get(i).getUnit() + " unit does not exist");

            // Scroll up to find the unit
            new Actions(driver)
                    .scrollFromOrigin(scrollOrigin, 0, -deltaY)
                    .perform();
        }
    }

    @Test(testName = "FB-12706 : Verify that duplicate values are not allowed as lowercase")
    public void FB12706() {
        String existBaseUnit = materialData.getMaterial().get(0).getUnit();
        String uppercaseExistBaseUnit = existBaseUnit.toUpperCase();
        String lowercaseExistBaseUnit = existBaseUnit.toLowerCase();

        // Choose uppercase base unit and verify if the add new button (unit) is visible
        createMaterialPage().enterBaseUnit(uppercaseExistBaseUnit);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(text(),'" + existBaseUnit + "') and contains(@class, 'option')]")), "The unit does not exist");
        Assert.assertFalse(helper.isElementVisible(createMaterialPage().getNewUnitElement()), "This add " + existBaseUnit + " is still visible while the material is exist in system");

        createMaterialPage().helper.refreshPage();

        // Choose lowercase base unit and verify if the add new button (unit) is visible
        createMaterialPage().enterBaseUnit(lowercaseExistBaseUnit);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(text(),'" + existBaseUnit + "') and contains(@class, 'option')]")), "The unit does not exist");
        Assert.assertFalse(helper.isElementVisible(createMaterialPage().getNewUnitElement()), "This add " + existBaseUnit + " is still visible while the material is exist in system");
    }

    @Test(testName = "FB-12707 : Verify that creating a new unit adds it to the top of the unit options")
    public void FB12707() {
        String newBaseUnit = "Unit" + helper.generateRandomNumber();
        createMaterialPage().chooseBaseUnit(newBaseUnit);
        createMaterialPage().helper.refreshPage();
        createMaterialPage().helper.scrollToElementAtMiddle(selectionSearchUnit);
        createMaterialPage().helper.clickElement(selectionSearchUnit);
        // The first unit
        String firstUnit = helper.getText(By.xpath("(//div[contains(@class, 'option')])[1]"));
        Assert.assertEquals(firstUnit, newBaseUnit, "The created unit created does not appear at the top");
    }

    @Test(testName = "FB-12708 : Verify that setting the new unit as the selected item")
    public void FB12708() {
        String newBaseUnit = "Unit" + helper.generateRandomNumber();
        createMaterialPage().chooseBaseUnit(newBaseUnit);
        // Click import button to remove blinking in unit text box
        createMaterialPage().clickAddImportUnit();

        // Verify the value selected unit
        helper.waitForElementVisible(By.xpath("//span[text()='" + newBaseUnit + "']"), "The unit " + newBaseUnit + " is not set to the field");
    }

    @Test(testName = "FB-12709 : Verify the error message when a unit is not chosen before selecting 'import unit' ")
    public void FB12709() {
        createMaterialPage().helper.scrollToElementAtMiddle(createMaterialPage().getBtnAddImportUnit());
        createMaterialPage().clickAddImportUnit();
        System.out.println(commonComponents().getContentErrorDialog());
        Assert.assertEquals(commonComponents().getContentErrorDialog(), DataTest.ImportUnit_Error_Message);
    }

    @Test(testName = "FB-12710 : Verify that the 'Open' dialog for setting unit conversion opens")
    public void FB12710() {
        createMaterialPage()
                .chooseBaseUnit(ml)
                .clickAddImportUnit();
        createMaterialPage().helper.waitForElementVisible(unitConversionDialog().getDialog());
        Assert.assertEquals(unitConversionDialog().getTitle(), DataTest.UnitConversion_Title);
    }

    @Test(testName = "FB-12711 : Verify the validation error message of unit conversion")
    public void FB12711() {
        // Navigate to unit conversion dialog
        createMaterialPage().chooseBaseUnit(ml).clickAddImportUnit();

        unitConversionDialog()
                .clickAddNewImportUnit()
                .clickAddButton();

        // Verify the alert message of 'Import unit' and 'Capacity'
        helper.waitTextToBePresent(By.xpath("//div[contains(@id, 'unitId_help')]"), DataTest.ImportUnit_ErrorMessage);
        helper.waitTextToBePresent(By.xpath("//div[contains(@id, 'capacity_help')]"),DataTest.Capacity_ErrorMessage);
    }

    @Test(testName = "FB-12712 : Verify that all existing units in the system are loaded, excluding the base unit")
    public void FB12712() {
        // Navigate to unit conversion dialog
        createMaterialPage().chooseBaseUnit(ml).clickAddImportUnit();
        unitConversionDialog().clickAddNewImportUnit();

        for (int i=0; i < materialData.getMaterial().size(); i++) {
            helper.enterText(unitConversionDialog().getSelSearchUnit(), materialData.getMaterial().get(i).getUnit());
            helper.clearText(unitConversionDialog().getSelSearchUnit());
        }
    }
}
