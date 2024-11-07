package com.fnb.web.admin.scenario_test.product;

import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.CreateProductPage;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.product.management.ProductManagementPage;
import com.fnb.web.setup.Setup;
import dataObject.Product.Product;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.fnb.web.setup.Setup.*;

// Testlink: http://103.191.146.224/testlink/linkto.php?tprojectPrefix=FB&item=testsuite&id=45674
public class EditProductTest extends CommonPages {
    String productName;

    @BeforeClass
    public void createANewProduct() throws IOException {
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
    }

    @AfterClass
    public void clearCookie() {
        adminPage().helper.clearCookies();
    }

    @BeforeMethod
    public void clickEditProductAndClearFields() {
        // Click edit product
        adminPage().helper.navigateToUrl(configObject.getUrlProductManagement());
        productManagementPage().clickProductEditIcon(productName);
        // Clear all field of the product
        productEditPage().clearAllFields();
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());
    }

    // Sprint49
    @Test(testName = "FB-11373 : Check if the placeholder text displays “Enter product name”")
    public void FB11373() {
        productEditPage().verifyPlaceHolder(CreateProductPage.Element.NAME, DataTest.NAME_PLACEHOLDER);
    }

    @Test(testName = "FF-11374 : Verify the required name, price, unit, material field”")
    public void FB11374() {
        productEditPage().clickSaveButton();
        productEditPage().verifyLblMessage(CreateProductPage.Element.NAME, DataTest.LABEL_MESSAGE_NAME);
        productEditPage().verifyLblMessage(CreateProductPage.Element.PRICE, DataTest.LABEL_MESSAGE_PRICE);
        productEditPage().verifyLblMessage(CreateProductPage.Element.UNIT, DataTest.LABEL_MESSAGE_UNIT);
        productEditPage().verifyLblMessage(CreateProductPage.Element.MATERIAL, DataTest.LABEL_MESSAGE_MATERIAL);
    }

    @Test(testName = "FB-11435 : Verify success behavior when uploading an image")
    public void FB11435() {
        productEditPage().upLoadImage(productData.getProduct().get(0).getImage());
    }

    @Test(testName = "FB-11439 : Verify that images up to 5MB can be uploaded")
    public void FB11439() {
        productEditPage().verifyLargeImageUpload(DataTest.LARGE_IMAGE, DataTest.LARGE_IMAGE_ERROR_MESSAGE);
    }

    @Test(testName = "FB-11457 : Show validation if values are out of range")
    public void FB11457() {
        productEditPage()
                .enterPrice("9,999,999,999")
                .verifyLblMessage(CreateProductPage.Element.PRICE, "Value allowed from 0 to 999,999,999");
    }

    @Test(testName = "FB-11461 : Verify there is remove button icon when adding more price")
    public void FB11461() {
        productEditPage()
                .clickBtnAddPrice()
                .checkElementAppearance(CreateProductPage.Element.ICON_DELETE);
    }

    @Test(testName = "FB-11462 : Verify new group will be added at the end of prices list")
    public void FB11462() {
        productEditPage().clickBtnAddPrice();
        Assert.assertEquals(productEditPage().countTheNumberOfPriceItem(), 2);
        productEditPage().clickBtnAddPrice();
        Assert.assertEquals(productEditPage().countTheNumberOfPriceItem(), 3);
    }

    @Test(testName = "FB-11464 : Verify price behavior system after clicking on delete icon")
    public void FB11464() {
        productEditPage()
                .clickBtnAddPrice()
                .clickBtnAddPrice()
                .clickBtnAddPrice();
        productEditPage().clickPriceDeleteIcon(4);
        Assert.assertEquals(productEditPage().countTheNumberOfPriceItem(), 3);
        productEditPage().clickPriceDeleteIcon(3);
        Assert.assertEquals(productEditPage().countTheNumberOfPriceItem(), 2);
    }

    @Test(testName = "FB-11465 : Verify price after clicking on delete button of the last group")
    public void FB11465() {
        productEditPage()
                .clickBtnAddPrice()
                .clickBtnAddPrice()
                .clickBtnAddPrice();
        productEditPage().clickPriceDeleteIcon(4);
        Assert.assertEquals(productEditPage().countTheNumberOfPriceItem(), 3);
        productEditPage().clickPriceDeleteIcon(3);
        Assert.assertEquals(productEditPage().countTheNumberOfPriceItem(), 2);
        productEditPage().clickPriceDeleteIcon(2);
        Assert.assertEquals(productEditPage().countTheNumberOfPriceItem(), 0);
    }

    @Test(testName = "FB-11466 : Verify text display of ctaDialog() after clicking on cancel button")
    public void FB11466() {
        productEditPage().enterPrice("2000");
        productEditPage().clickBtnCancel();
        adminPage().helper.verifyContains(DataTest.DIALOG_CONTENT, ctaDialog().getContentDialog(), "NOT MATCH");
        adminPage().helper.verifyContains(DataTest.DIALOG_TITLE, ctaDialog().getTitleDialog(), "NOT MATCH");
    }

    @Test(testName = " FB-11468 : Verify behavior after clicking DISCARD button")
    public void FB11468() {
        productEditPage().enterPrice("2000");
        productEditPage().clickBtnCancel();
        ctaDialog().clickBtnDiscard();
        // Verify that the page is still on the 'create product' page
        adminPage().helper.waitForElementVisible(productEditPage().getBtnSave());
        // Verify that after clicking on the discard button, the ctaDialog() will disappear
        adminPage().helper.waitForElementInVisible(ctaDialog().getBtnDiscard());
        Assert.assertFalse(ctaDialog().IsVisibleDiscard(), "The Discard button is still visible");
    }

    @Test(testName = "FB-11469 : Verify behavior after clicking CONFIRM LEAVE button")
    public void FB11469() {
        String productName = "testing";
        productEditPage().enterPrice("2000");
        productEditPage().clickBtnCancel();
        ctaDialog().clickBtnConfirmLeave();
        // Verify that after clicking the 'Confirm leave' button, the page will be redirected to the product management page
        adminPage().helper.verifyContains(Setup.configObject.getUrlProductManagement(), adminPage().helper.getCurrentUrl(), "The URL not match");

        // Verify that after clicking on the discard button, the ctaDialog() will disappear
        Assert.assertFalse(ctaDialog().IsVisibleDiscard(), "The Discard button is still visible");

        // Verify that this product is not updated
        ProductManagementPage productManagementPage = new ProductManagementPage(getDriver());
        adminPage().helper.clickElement(commonComponents().getSearchIcon());
        adminPage().helper.enterText(productManagementPage.getTxtSearch(), productName);
        Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//p[normalize-space()='No Data Found']")), "The product being created is being displayed");
    }

    @Test(testName = "FB-11481 : Verify that the successful state after update product")
    public void FB11481() {
        // ************************* Create another product to avoid affecting other tests *************************
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
        productManagementPage().clickProductEditIcon(productName);
        productEditPage().clearAllFields();

        // *************************  Update product *************************
        Product.Information.Price price1 = new Product.Information.Price("S", "7000", "1");
        Product.Information.Platform platform1 = new Product.Information.Platform("POS devices");
        List<Product.Information.Price> prices = Arrays.asList(price1);
        List<Product.Information.Platform> platforms = Arrays.asList(platform1);
        productName = "product" + adminPage().helper.generateRandomNumber();
        productEditPage().updateProduct(
                productName,
                productData.getProduct().get(2).getImage(),
                productData.getProduct().get(2).getTopping(),
                productData.getProduct().get(2).getOption(),
                prices,
                platforms,
                "ml",
                productData.getProduct().get(2).getProductInventoryData(),
                taxData.getTax().get(0).getName());

        //**Checkpoint: Navigate to product management page
        adminPage().helper.verifyContains(Setup.configObject.getUrlProductManagement(), adminPage().helper.getCurrentUrl(), "The current URL is not the product management page");


        //**Checkpoint: Check the updated data is correct or not
        productManagementPage().clickOnProduct(productName);
        // ************************* Verify product name *************************
        adminPage().softAssert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//span[normalize-space()='" + productName + "']")), "The product name is not updated");

        // ************************* Verify price *************************
        String expectedPrice = adminPage().helper.formatDoubleToString(adminPage().helper.convertStringToDouble(prices.get(0).getPriceValue())) + " VND";
        adminPage().softAssert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//span[normalize-space()='" + expectedPrice + "']")), "The product price is not updated");

        // ************************* Verify material *************************
        List<Product.Information.productInventoryData.ListProductPriceMaterial> listProductPriceMaterial = productData.getProduct().get(2).getProductInventoryData().get(0).getListProductPriceMaterials();
        for (int i = 0; i < listProductPriceMaterial.size(); i++) {
            adminPage().softAssert.assertTrue(adminPage().helper.isElementVisible(By.xpath("(//*[normalize-space()='" + listProductPriceMaterial.get(i).getMaterial() + "'])[last()]")), "The material is not updated\nExpected material should be " + listProductPriceMaterial.get(i).getMaterial() + "");
        }

        adminPage().softAssert.assertAll();
    }

    @Test(testName = "FB-11482 : Verify that duplicate values in lowercase are not allowed")
    public void FB11482() {
        adminPage().helper.scrollToElementAtMiddle(productEditPage().getSelSearchUnit());
        adminPage().helper.clickElement(productEditPage().getSelSearchUnit());
        adminPage().helper.enterText(productEditPage().getSelSearchUnit(), productData.getProduct().get(0).getUnit());
        Assert.assertFalse(adminPage().helper.isElementVisible(productEditPage().getNewUnitElement()), "Enter the existing unit, but the 'Add New Unit' button is displayed");
    }

    @Test(testName = "FB-11548 : Verify behavior of unit field after clicking 'Add new' button")
    public void FB11548() {
        String unit = materialData.getMaterial().get(0).getUnit();
        productEditPage().chooseBaseUnit(unit);

        // This is the expected unit after selecting
        By expectedUnit = By.xpath("//span[@title='" + unit + "']");
        Assert.assertTrue(adminPage().helper.isElementVisible(expectedUnit), "The material unit should be displayed after choosing the unit");
    }

    @Test(testName = " FB-11549 : Verify that the option can be selected multiple times after updated")
    public void FB11549() {
        // ************************* Create another product to avoid affecting other tests *************************
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
        productManagementPage().clickProductEditIcon(productName);

        String option1 = optionData.getOptions().get(0).getName();
        String option2 = optionData.getOptions().get(1).getName();
        productEditPage().selectOption(option1).selectOption(option2);

        // Verify that two options are selected
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//*[contains(text(),'" + option1 + "')]")), option1);
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//*[contains(text(),'" + option2 + "')]")), option2);

        //**Checkpoint: Check the updated option data
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());
        productEditPage().clickSaveButton();
        commonComponents().waitSuccessToast();

        productManagementPage().clickProductEditIcon(productName);
        // *************************  Check the option data *************************
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchOption());
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//*[contains(text(),'" + option1 + "')]")), option1);
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//*[contains(text(),'" + option2 + "')]")), option2);
    }

    @Test(testName = "FB-11550 : Verify that all levels of an option are shown after updating")
    public void FB11550() {
        // ************************* Create another product to avoid affecting other tests *************************
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
        productManagementPage().clickProductEditIcon(productName);

        String option1 = optionData.getOptions().get(0).getName();
        String option2 = optionData.getOptions().get(1).getName();

        productEditPage().selectOption(option1).selectOption(option2);
        // All level of an option should be shown
        for (int i = 0; i < optionData.getOptions().size(); i++) {
            for (int j = 0; j < optionData.getOptions().get(i).getOptionLevels().size(); j++) {
                adminPage().softAssert.assertTrue(
                        adminPage().helper.isElementVisible(By.xpath("//*[contains(text(),'"+optionData.getOptions().get(i).getName()+"')]/following::*[text()='"+optionData.getOptions().get(i).getOptionLevels().get(j).getName()+"'][1]")),
                        "The " + optionData.getOptions().get(i).getOptionLevels().get(j).getName() + " level of " + optionData.getOptions().get(i).getName() + " option is not shown");
            }
        }

        // ************************* Check the updated option data *************************
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());
        productEditPage().clickSaveButton();
        commonComponents().waitSuccessToast();

        // *************************  Check the level option data after updated *************************
        productManagementPage().clickProductEditIcon(productName);
        adminPage().helper.scrollToElementAtMiddle(productEditPage().getSelSearchOption());
        // All level of an option should be shown
        for (int i = 0; i < optionData.getOptions().size(); i++) {
            for (int j = 0; j < optionData.getOptions().get(i).getOptionLevels().size(); j++) {
                adminPage().softAssert.assertTrue(
                        adminPage().helper.isElementVisible(By.xpath("//*[contains(text(),'"+optionData.getOptions().get(i).getName()+"')]/following::*[text()='"+optionData.getOptions().get(i).getOptionLevels().get(j).getName()+"'][1]")),
                        "The " + optionData.getOptions().get(i).getOptionLevels().get(j).getName() + " level of " + optionData.getOptions().get(i).getName() + " option is not shown");
            }
        }

        // ************************* FB-11551 : Verify the behavior when clicking on the trash icon to delete an option after updating *************************
        productEditPage().deleteOption(option1).deleteOption(option2);
        // Update the product
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());
        productEditPage().clickSaveButton();
        commonComponents().waitSuccessToast();

        // Check the product again to verify if the option is deleted or not
        productManagementPage().clickProductEditIcon(productName);
        adminPage().helper.scrollToElementAtMiddle(productEditPage().getSelSearchOption());
        Assert.assertFalse(adminPage().helper.isElementVisible(By.xpath("//strong[normalize-space()='" + option1 + "']")), "This option has not been moved");
        Assert.assertFalse(adminPage().helper.isElementVisible(By.xpath("//strong[normalize-space()='" + option2 + "']")), "This option has not been moved");

        adminPage().softAssert.assertAll();
    }

    @Test(testName = "FB-11553 : Verify that the material is removed after being selected")
    public void FB11553() {
        List<Product.Information.productInventoryData> onePriceProductInventoryData;
        onePriceProductInventoryData = productData.getProduct().get(0).getProductInventoryData();

        productEditPage().chooseMaterialRecipe(onePriceProductInventoryData);

        // Verify that this material is not in the selection list
        adminPage().helper.clickElement(productEditPage().getSelSearchMaterial());
        adminPage().helper.enterText(productEditPage().getSelSearchMaterial(), onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial());
        Assert.assertEquals(adminPage().helper.getText(By.xpath("//div[@class='ant-empty-description']")), "No data", "It looks like there is still selected material data");
    }

    @Test(testName = "FB-11554 : Verify the quantity field is required")
    public void FB11554() {
        List<Product.Information.productInventoryData> onePriceProductInventoryData;
        onePriceProductInventoryData = productData.getProduct().get(0).getProductInventoryData();

        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();

        // Click material selection
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        adminPage().helper.clickElement(productEditPage().getSelSearchMaterial());
        adminPage().helper.enterText(productEditPage().getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(productEditPage().getMaterialSelectionEle(materialName));

        // Click on create button
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());
        productEditPage().clickSaveButton();

        // Verify the error message label should be displayed
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        Assert.assertTrue(adminPage().helper.isElementVisible(By.xpath("//*[normalize-space()='" + materialName + "']/ancestor::*//*[contains(text(),'" + DataTest.QUANTITY_ERROR_MESSAGE2 + "')]")), "The error label message is not be displayed");
    }

    @Test(testName = "FB-11555 : Verify that entering a negative number is not allowed")
    public void FB11555_1() {
        // Precondition
        List<Product.Information.productInventoryData> onePriceProductInventoryData;
        onePriceProductInventoryData = productData.getProduct().get(0).getProductInventoryData();
        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();

        // Click a certain material
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        adminPage().helper.clickElement(productEditPage().getSelSearchMaterial());
        adminPage().helper.enterText(productEditPage().getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(productEditPage().getMaterialSelectionEle(materialName));

        // Enter the nagative number -40
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        adminPage().helper.enterText(productEditPage().getSelectedMaterialQuantityEle(materialName), "-40");

        // Verify whether the quantity field allows input of negative numbers or no
        Assert.assertEquals(adminPage().helper.getWebElement(productEditPage().getSelectedMaterialQuantityEle(materialName)).getAttribute("value"), "40", "The quantity looks like it has a negative number");
    }

    @Test(testName = "FB-11556 : Verify that error message when entering a zero number in quantity field")
    public void FB11556() {
        // Precondition
        List<Product.Information.productInventoryData> onePriceProductInventoryData;
        onePriceProductInventoryData = productData.getProduct().get(0).getProductInventoryData();
        String materialName = onePriceProductInventoryData.get(0).getListProductPriceMaterials().get(0).getMaterial();

        // Click a certain material
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        adminPage().helper.clickElement(productEditPage().getSelSearchMaterial());
        adminPage().helper.enterText(productEditPage().getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(productEditPage().getMaterialSelectionEle(materialName));

        // Enter 0
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());
        productEditPage().clickSaveButton();
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());

        // Verify whether the quantity field allows input of negative numbers or no
        Assert.assertEquals(productEditPage().getQuantityError(materialName), DataTest.LABEL_MESSAGE_PRICE, "The error message is displayed incorrectly");
    }

    @Test(testName = "FB-11558 : Verify that the total cost is calculated correctly for a single material")
    public void FB11558() {
        // ************************* Create another product to avoid affecting other tests *************************
        String productName = createProductPage().createAProductWithAnyTypeData(productCategoryData.getProductCategory().get(0).getName());
        productManagementPage().clickProductEditIcon(productName);
        productEditPage().clearAllFields();
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());

        // Enter name, price, and unit
        productName = "product" + adminPage().helper.generateRandomNumber();
        adminPage().helper.enterText(productEditPage().getTxtProductName(), productName);
        productEditPage().enterPrice("1000");
        productEditPage().chooseBaseUnit(productData.getProduct().get(0).getUnit());

        // Click a certain material
        String materialName = materialData.getMaterial().get(0).getName();
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        adminPage().helper.clickElement(productEditPage().getSelSearchMaterial());
        adminPage().helper.enterText(productEditPage().getSelSearchMaterial(), materialName);
        adminPage().helper.clickJS(productEditPage().getMaterialSelectionEle(materialName));

        // Enter quantity
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        adminPage().helper.enterText(productEditPage().getSelectedMaterialQuantityEle(materialName), "20.1");

        // Verify that (Total cost = quantity * unit cost)
        String actualTotalCost = productEditPage().getRecipeMaterialData(materialName, DataTest.TOTAL_COST_OPTION_NAME);

        // Get the quantity and cost
        double quantity = adminPage().helper.roundToTwoDecimalPlaces(adminPage().helper.convertStringToDouble("20.1"));
        double unitCost = adminPage().helper.convertStringToDouble(materialData.getMaterial().get(0).getCostPerUnit());

        // Calculate the expected value using the formula (quantity * unit cost)
        double totalCost = adminPage().helper.roundToTwoDecimalPlaces(quantity * unitCost);
        String expectedTotalCost = adminPage().helper.formatDoubleToString(totalCost);
        String expectedQuantity = productEditPage().getSelectedMaterialQuantity(materialName);
        // Verify that the total cost is calculated correctly
        Assert.assertEquals(actualTotalCost, expectedTotalCost, "The actual cost looks incorrect. Please double-check");

        // Save the the product
        adminPage().helper.scrollToElementAtBottom(productEditPage().getBtnSave());
        productEditPage().clickSaveButton();
        commonComponents().waitSuccessToast();

        productManagementPage().clickProductEditIcon(productName);

        // Verify that the total cost is calculated correctly
        adminPage().helper.scrollToElementAtTop(productEditPage().getSelSearchMaterial());
        Assert.assertEquals(productEditPage().getSelectedMaterialQuantity(materialName), expectedQuantity, "The quantity is not match");
        Assert.assertEquals(actualTotalCost, expectedTotalCost, "The actual cost looks incorrect. Please double-check");
    }

    @Test(testName = "FB-11559 : Verify that the price name appears as a tab in the recipe section when the product has multiple price")
    public void FB11559() {
        Product.Information multiplePricesProduct = productData.getProduct().get(1);
        String productName = "product" + adminPage().helper.generateRandomNumber();
        adminPage().helper.enterText(productEditPage().getTxtProductName(), productName);

        productEditPage()
                .enterPrice(multiplePricesProduct.getPrice())
                .chooseMaterialRecipe(multiplePricesProduct.getProductInventoryData());

        // Verify that there are inventory price tabs
        for (int i = 0; i < multiplePricesProduct.getPrice().size(); i++) {
            adminPage().softAssert.assertTrue(adminPage().helper.isElementVisible(productEditPage().getInventoryTabEle(multiplePricesProduct.getPrice().get(i).getPriceName())), "The " + multiplePricesProduct.getPrice().get(i).getPriceName() + " price tab in inventory is not visible - ");
        }
        adminPage().softAssert.assertAll();
    }

    @Test(testName = "FB-11560 : Verify that the price name tab does not appear when there is only one price")
    public void FB11560() {
        List<Product.Information.productInventoryData> onePriceProductInventoryData = productData.getProduct().get(0).getProductInventoryData();
        String productName = "product" + adminPage().helper.generateRandomNumber();
        adminPage().helper.enterText(productEditPage().getTxtProductName(), productName);
        productEditPage()
                .enterPrice("40000")
                .chooseMaterialRecipe(onePriceProductInventoryData);

        // Verify that there is no price tab in Recipe
        Assert.assertFalse(adminPage().helper.isElementVisible(By.xpath("//div[contains(@id, 'tab-inventory-tab')]")), "It looks like there is a price tab showing up");
    }
}