package com.fnb.web.admin.scenario_test.product;

import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.CreateProductPage;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.product.management.ProductManagementPage;
import com.fnb.web.setup.Setup;
import dataObject.Product.Options;
import dataObject.Product.Product;
import dataObject.Product.Topping;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.fnb.web.setup.Setup.*;

public class CreateProductTest extends CommonPages {
    private CreateProductPage createProductPage;
    private Product productData;
    private Options optionData;
    private Topping toppingData;
    private List<Product.Information.productInventoryData> onePriceProductInventoryData;
    private Product.Information multiplePricesProduct;

    @BeforeClass
    public void prepareData() throws IOException {
        productData = getSetup().productData;
        optionData = getSetup().optionData;
        toppingData = getSetup().toppingData;
        onePriceProductInventoryData = productData.getProduct().get(0).getProductInventoryData();
        multiplePricesProduct = productData.getProduct().get(1);
        createProductPage = new CreateProductPage(getDriver());
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        createProductPage().prepareData();
    }

    @AfterClass
    public void clearCookie() {
        adminPage().helper.clearCookies();
    }

    @AfterTest
    public void clearData() {
    }

    @BeforeMethod
    public void navigateToCreateProductPage() {
        getDriver().navigate().to(Setup.configObject.getUrlCreateProduct());
        adminPage().helper.waitForUrl(Setup.configObject.getUrlCreateProduct());
    }

    @Test(testName = "FB-8625 : Verify the presence of the placeholder \"Enter product name.\"")
    public void FB8625() {
        createProductPage.verifyPlaceHolder(CreateProductPage.Element.NAME, DataTest.NAME_PLACEHOLDER);
    }

    @Test(testName = "FB-8627 : Verify the required name, price, unit, material field")
    public void FB8627() {
        // Verify the required file by leaving these fields empty, then click 'create.' Afterward, verify the error message for each field
        createProductPage.clickBtnCreate();
        createProductPage.helper.smartWait();
        createProductPage.verifyLblMessage(CreateProductPage.Element.NAME, DataTest.LABEL_MESSAGE_NAME);
        createProductPage.verifyLblMessage(CreateProductPage.Element.PRICE, DataTest.LABEL_MESSAGE_PRICE);
        createProductPage.verifyLblMessage(CreateProductPage.Element.UNIT, DataTest.LABEL_MESSAGE_UNIT);
        createProductPage.verifyLblMessage(CreateProductPage.Element.MATERIAL, DataTest.LABEL_MESSAGE_MATERIAL);
    }

    @Test(testName = "FB-8628 : Verify the maximum of name field length is 100 characters")
    public void FB8628() {
        createProductPage.verifyMaxLength(DataTest.MAX_LENGTH_NAME);
    }

//     Deprecated: User Story 61073: [Admin] [Improvement] Allow to Create/Edit Products with the Same Name
//    @Test(testName = "FB-8630 : Tests for uniqueness validation")
//    public void FB8630() {
//        createProductPage.verifyExistProductName(
//                productData.getProduct().get(0).getName(),
//                DataTest.LABEL_ERROR_MESSAGE_NAME,
//                productData.getProduct().get(0).getPrice().get(0).getPriceValue(),
//                productData.getProduct().get(0).getUnit(),
//                productData.getProduct().get(0).getProductInventoryData()
//        );
//    }

    @Test(testName = "FB-8645 : Verify success behavior when uploading an image")
    public void FB8645() {
        createProductPage.upLoadImage(productData.getProduct().get(0).getImage());
    }

    @Test(testName = "FB-8647 : Verify the maximum file size is 5MB")
    public void FB8647() {
        createProductPage.verifyLargeImageUpload(DataTest.LARGE_IMAGE, DataTest.LARGE_IMAGE_ERROR_MESSAGE);
    }

    @Test(testName = "FB-8658 : Verify error message when value out of range")
    public void FB8658() {
        createProductPage.enterPrice("9,999,999,999");
        createProductPage.helper.sleep(1);
        createProductPage.verifyLblMessage(CreateProductPage.Element.PRICE, "Value allowed from 0 to 999,999,999");
    }

    @Test(testName = "FB-8668 : Verify there is remove button icon when adding more price")
    public void FB8668() {
        createProductPage
                .clickBtnAddPrice()
                .checkElementAppearance(CreateProductPage.Element.ICON_DELETE);
    }

    @Test(testName = "FB-8671 : Verify new group will be added at the end of prices list")
    public void FB8671() {
        createProductPage.clickBtnAddPrice();
        Assert.assertEquals(createProductPage.countTheNumberOfPriceItem(), 2);
        createProductPage.clickBtnAddPrice();
        Assert.assertEquals(createProductPage.countTheNumberOfPriceItem(), 3);
    }

    @Test(testName = "FB-8672 : Verify price behavior system after clicking on delete icon")
    public void FB8672() {
        createProductPage
                .clickBtnAddPrice()
                .clickBtnAddPrice()
                .clickBtnAddPrice();
        createProductPage.clickPriceDeleteIcon(4);
        Assert.assertEquals(createProductPage.countTheNumberOfPriceItem(), 3);
        createProductPage.clickPriceDeleteIcon(3);
        Assert.assertEquals(createProductPage.countTheNumberOfPriceItem(), 2);
    }

    @Test(testName = "FB-8682 : Verify price after clicking on delete button of the last group")
    public void FB8682() {
        createProductPage
                .clickBtnAddPrice()
                .clickBtnAddPrice()
                .clickBtnAddPrice();
        createProductPage.clickPriceDeleteIcon(4);
        Assert.assertEquals(createProductPage.countTheNumberOfPriceItem(), 3);
        createProductPage.clickPriceDeleteIcon(3);
        Assert.assertEquals(createProductPage.countTheNumberOfPriceItem(), 2);
        createProductPage.clickPriceDeleteIcon(2);
        Assert.assertEquals(createProductPage.countTheNumberOfPriceItem(), 0);
    }

    @Test(testName = "FB-8701 : Verify text display of dialog after clicking on cancel button")
    public void FB8701() {
        createProductPage.enterPrice("2000");
        createProductPage.clickBtnCancel();
        adminPage().helper.verifyContains(DataTest.DIALOG_CONTENT, ctaDialog().getContentDialog(), "NOT MATCH");
        adminPage().helper.verifyContains(DataTest.DIALOG_TITLE, ctaDialog().getTitleDialog(), "NOT MATCH");
    }

    @Test(testName = "FB-8702 : Verify behavior after clicking DISCARD button")
    public void FB8702() {
        createProductPage.enterPrice("2000");
        createProductPage.clickBtnCancel();
        ctaDialog().clickBtnDiscard();
        // Verify that the page is still on the 'create product' page
        adminPage().helper.verifyContains(Setup.configObject.getUrlCreateProduct(), adminPage().helper.getCurrentUrl(), "The URL not match");
        // Verify that after clicking on the discard button, the dialog will disappear
        Assert.assertFalse(ctaDialog().IsVisibleDiscard(), "The Discard button is still visible");
    }

    @Test(testName = "FB-8703 : Verify behavior after clicking CONFIRM LEAVE button")
    public void FB8703() {
        String productName = "testing";
        createProductPage.enterPrice("2000");
        createProductPage.clickBtnCancel();
        ctaDialog().clickBtnConfirmLeave();
        // Verify that after clicking the 'Confirm leave' button, the page will be redirected to the product management page
        adminPage().helper.verifyContains(Setup.configObject.getUrlProductManagement(), adminPage().helper.getCurrentUrl(), "The URL not match");

        // Verify that after clicking on the discard button, the dialog will disappear
        Assert.assertFalse(ctaDialog().IsVisibleDiscard(), "The Discard button is still visible");

        // Verify that this product is not created
        ProductManagementPage productManagementPage = new ProductManagementPage(getDriver());
        homePage().helper.clickElement(commonComponents().getSearchIcon());
        adminPage().helper.enterText(productManagementPage.getTxtSearch(), productName);
        Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//*[text()='No Data Found']")), "The product being created is being displayed");
    }

    @Test(testName = "FB-8705 : Verify that the successful state after add new product")
    public void FB8705() {
        Product.Information.Price price1 = new Product.Information.Price("S", "2000", "1");
        Product.Information.Platform platform1 = new Product.Information.Platform("POS devices");

        List<Product.Information.Price> prices = Arrays.asList(price1);
        List<Product.Information.Platform> platforms = Arrays.asList(platform1);

        String productName = "product" + adminPage().helper.generateRandomNumber();
        createProductPage.createProducts(
                productName,
                productData.getProduct().get(1).getImage(),
                productData.getProduct().get(0).getTopping(),
                productData.getProduct().get(1).getOption(),
                prices,
                platforms,
                "ml",
                productData.getProduct().get(0).getProductInventoryData(),
                taxData.getTax().get(0).getName());
        adminPage().helper.verifyContains(Setup.configObject.getUrlProductManagement(), adminPage().helper.getCurrentUrl(), "The current URL is not the product management page");
    }

    @Test(testName = "FB-8782 : Verify that duplicate values in lowercase are not allowed")
    public void FB8782() {
        By selSearchUnit = createProductPage.getSelSearchUnit();
        adminPage().helper.scrollToElementAtTop(selSearchUnit);
        adminPage().helper.clickElement(selSearchUnit);
        adminPage().helper.enterText(selSearchUnit, productData.getProduct().get(0).getUnit());
        Assert.assertFalse(adminPage().helper.isElementVisible(createProductPage.getNewUnitElement()), "Enter the existing unit, but the 'Add New Unit' button is displayed");
    }

    @Test(testName = "FB-8789 : Verify behavior of unit field after clicking 'Add new' button")
    public void FB8789() {
        String unit = materialData.getMaterial().get(0).getUnit();
        createProductPage.chooseBaseUnit(unit);

        // This is the expected unit after selecting
        By expectedUnit = By.xpath("//span[@title='" + unit + "']");
        Assert.assertTrue(adminPage().helper.isElementVisible(expectedUnit), "The material unit should be displayed after choosing the unit");
    }

    @Test(testName = "FB-10036 : Verify that creating a unit that already exists")
    public void FB10036() {
        By selSearchUnit = createProductPage.getSelSearchUnit();
        By btnAddNewUnit = createProductPage.getNewUnitElement();

        adminPage().helper.scrollToElementAtTop(selSearchUnit);
        adminPage().helper.clickElement(selSearchUnit);
        adminPage().helper.enterText(selSearchUnit, "LY");
        adminPage().helper.clickElement(btnAddNewUnit);
        Assert.assertEquals(adminPage().helper.getText(createProductPage.getToastErrorMessage()), DataTest.UNIT_ERROR_MESSAGE);
    }

    @Test(testName = "FB-10542 : Verify that the option can be selected multiple times")
    public void FB10542() {
        String option1 = optionData.getOptions().get(0).getName();
        String option2 = optionData.getOptions().get(1).getName();
        createProductPage.selectOption(option1).selectOption(option2);

        // Verify that two options are selected
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//*[text()='"+option1+"' and contains(@class, 'option-name-text')]")), option1);
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//*[text()='"+option2+"' and contains(@class, 'option-name-text')]")), option2);
    }

    @Test(testName = "FB-10585 : Verify that all levels of an option are shown")
    public void FB10585() {

        String option1 = optionData.getOptions().get(0).getName();
        String option2 = optionData.getOptions().get(1).getName();

        createProductPage.selectOption(option1).selectOption(option2);
        // All level of an option should be shown
        for (int i = 0; i < optionData.getOptions().size(); i++) {
            for (int j = 0; j < optionData.getOptions().get(i).getOptionLevels().size(); j++) {
                Assert.assertTrue(
                        adminPage().helper.isElementVisible(By.xpath("//*[contains(@class, 'option-name-text') and text()='"+optionData.getOptions().get(i).getName()+"']/following::*[text()='"+optionData.getOptions().get(i).getOptionLevels().get(j).getName()+"'][1]")),
                        "The " + optionData.getOptions().get(i).getOptionLevels().get(j).getName() + " level of " + optionData.getOptions().get(i).getName() + " option is not shown");
            }
        }
    }

    @Test(testName = "FB-10586 : Verify the behavior when clicking on the trash icon to delete an option")
    public void FB10586() {
        String option1 = optionData.getOptions().get(0).getName();
        String option2 = optionData.getOptions().get(1).getName();

        createProductPage
                .selectOption(option1)
                .deleteOption(option1);
        // Verify that when the option is deleted, the option1 will be moved out of OPTION SELECTION
        Assert.assertFalse(adminPage().helper.isElementVisible(By.xpath("//strong[normalize-space()='" + option1 + "']")), "This option has not been moved");

        createProductPage
                .selectOption(option2)
                .deleteOption(option2);
        // Verify that when the option is deleted, the option2 will be moved out of OPTION SELECTION
        Assert.assertFalse(adminPage().helper.isElementVisible(By.xpath("//strong[normalize-space()='" + option2 + "']")), "This option has not been moved");
    }

    @Test(testName = "FB-8975 : Verify that the material is removed after being selected")
    public void FB8975() {
        createProductPage.chooseMaterialRecipe(onePriceProductInventoryData);

        // Verify that this material is not in the selection list
        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial());
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//div[@class='ant-empty-description']")), "No data", "It looks like there is still selected material data");
    }

    @Test(testName = "FB-9050 : Verify the quantity field is required")
    public void FB9050() {
        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();

        // Click material selection
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(createProductPage.getMaterialSelectionEle(materialName));

        // Click on create button
        adminPage().helper.scrollToElementAtBottom(commonComponents().getBtnCreate());
        createProductPage.clickBtnCreate();

        // Verify the error message label should be displayed
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("" + commonComponents().getTableData(materialName) + "//*[contains(text(),'" + DataTest.QUANTITY_ERROR_MESSAGE + "')]")), "The error label message is not be displayed");
    }

    @Test(testName = "FB-9059 : Verify that entering a negative number is not allowed")
    public void FB9059_1() {
        // Precondition
        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();

        // Click a certain material
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(createProductPage.getMaterialSelectionEle(materialName));

        // Enter the nagative number -40
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelectedMaterialQuantityEle(materialName), "-40");

        // Verify whether the quantity field allows input of negative numbers or no
        Assert.assertEquals(adminPage().helper.getWebElement(createProductPage.getSelectedMaterialQuantityEle(materialName)).getAttribute("value"), "40", "The quantity looks like it has a negative number");
    }

//    Deprecated
//    @Test(testName = "FB-9059 : Verify that entering a negative number is not allowed")
//    public void FB9059_2() {
//        // Precondition
//        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();
//
//        // Click a certain material
//        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
//        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
//        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), materialName);
//        adminPage().helper.clickJS(createProductPage.getMaterialSelectionEle(materialName));
//        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
//
//        // Decrease selected material
//        createProductPage.clickMaterialIconDown(materialName);
//
//        // Verify the error message
//        Assert.assertEquals(createProductPage.getQuantityError(materialName), DataTest.LABEL_MESSAGE_PRICE, "The error message is displayed incorrectly");
//    }

    @Test(testName = "FB-9090 : Verify that error message when entering a limit number in quantity field")
    public void FB9090() {
        // Precondition
        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();

        // Click a certain material
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(createProductPage.getMaterialSelectionEle(materialName));

        // Enter the maximum number 999,999,999,999
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelectedMaterialQuantityEle(materialName), "999,999,999,999");

        // Verify whether the quantity field allows input of negative numbers or no
        Assert.assertEquals(createProductPage.getQuantityError(materialName), DataTest.LABEL_MESSAGE_PRICE, "The error message is displayed incorrectly");
    }

    @Test(testName = "FB-9091 : Verify that error message when entering a zero number in quantity field")
    public void FB9091() {
        // Precondition
        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();

        // Click a certain material
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(createProductPage.getMaterialSelectionEle(materialName));

        // Enter 0
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelectedMaterialQuantityEle(materialName), "0");

        // Verify whether the quantity field allows input of negative numbers or no
        Assert.assertEquals(createProductPage.getQuantityError(materialName), DataTest.LABEL_MESSAGE_PRICE, "The error message is displayed incorrectly");
    }

//    Deprecated
//    @Test(testName = "FB-10729 : Verify that the correct base unit of the material is being displayed")
//    public void FB10729() {
//        String materialName = materialData.getMaterial().get(0).getName();
//
//        // Click a certain material
//        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
//        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
//        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), materialName);
//        adminPage().helper.clickJS(createProductPage.getMaterialSelectionEle(materialName));
//
//        // Verify if the base unit of selected material is correct or not
//        Assert.assertEquals(createProductPage.getRecipeMaterialData(materialName, DataTest.UNIT_OPTION_NAME), materialData.getMaterial().get(0).getUnit(), "The base unit does not match the original material");
//    }

    @Test(testName = "FB-9138 : Verify that the total cost is calculated correctly for a single material")
    public void FB9138() {
        // Choose a recipe material
        String materialName = materialData.getMaterial().get(0).getName();

        // Click a certain material
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.clickElement(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(createProductPage.getMaterialSelectionEle(materialName));

        // Enter quantity
        adminPage().helper.scrollToElementAtTop(createProductPage.getSelSearchMaterial());
        adminPage().helper.enterText(createProductPage.getSelectedMaterialQuantityEle(materialName), "20.1");

        // Verify that (Total cost = quantity * unit cost)
        String actualTotalCost = createProductPage.getRecipeMaterialData(materialName, DataTest.TOTAL_COST_OPTION_NAME);

        // Get the quantity and cost
        double quantity = adminPage().helper.roundToTwoDecimalPlaces(adminPage().helper.convertStringToDouble("20.1"));
        double unitCost = adminPage().helper.convertStringToDouble(materialData.getMaterial().get(0).getCostPerUnit());

        // Calculate the expected value using the formula (quantity * unit cost)
        double totalCost = adminPage().helper.roundToTwoDecimalPlaces(quantity * unitCost);
        String expectedTotalCost = adminPage().helper.formatDoubleToString(totalCost);

        // Verify that the total cost is calculated correctly
        Assert.assertEquals(actualTotalCost, expectedTotalCost, "The actual cost looks incorrect. Please double-check");
    }

    // Sprint48
    @Test(testName = "FB-9169 : Verify that the price name appears as a tab in the recipe section when the product has multiple price")
    public void FB9169() {
        String productName = "product" + adminPage().helper.generateRandomNumber();
        adminPage().helper.enterText(createProductPage.getTxtProductName(), productName);

        createProductPage
                .enterPrice(multiplePricesProduct.getPrice())
                .chooseMaterialRecipe(multiplePricesProduct.getProductInventoryData());

        // Verify that there are inventory price tabs
        for (int i = 0; i < multiplePricesProduct.getPrice().size(); i++) {
            adminPage().softAssert.assertTrue(adminPage().helper.isElementVisible(createProductPage.getInventoryTabEle(multiplePricesProduct.getPrice().get(i).getPriceName())), "The " + multiplePricesProduct.getPrice().get(i).getPriceName() + " price tab in inventory is not visible - ");
        }
        adminPage().softAssert.assertAll();
    }

    @Test(testName = "FB-9172 : Verify that the price name tab does not appear when there is only one price")
    public void FB9172() {
        String productName = "product" + adminPage().helper.generateRandomNumber();
        adminPage().helper.enterText(createProductPage.getTxtProductName(), productName);
        createProductPage
                .enterPrice("40000")
                .chooseMaterialRecipe(onePriceProductInventoryData);

        // Verify that there is no price tab in Recipe
        Assert.assertFalse(adminPage().helper.isElementVisible(By.xpath("//div[contains(@id, 'tab-inventory-tab')]")), "It looks like there is a price tab showing up");
    }

    @Test(testName = "FB-9174 : Verify that the table of materials is hidden when all materials are removed")
    public void FB9174() {
        String materialName = productData.getProduct().get(0).getProductInventoryData().get(0).getListProductPriceMaterials().get(0).getMaterial();
        createProductPage
                .enterPrice("40000")
                .chooseMaterialRecipe(productData.getProduct().get(0).getProductInventoryData());

        // Delete the material recipe
        createProductPage.clickDelete_Recipe_Material(materialName);

        // Verify that hide table of material after deleting all material in Recipe
        Assert.assertFalse(adminPage().helper.isElementVisible(By.xpath("//div[contains(@class, 'table-product-receipt')]")), "The table of material is not be hidden");
    }
}
