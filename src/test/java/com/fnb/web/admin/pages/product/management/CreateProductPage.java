package com.fnb.web.admin.pages.product.management;

import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.admin.pages.crm.customer.CreateNewCustomerPage;
import com.fnb.web.admin.pages.inventory.category.MaterialCategoryManagementPage;
import com.fnb.web.admin.pages.inventory.ingredients.CreateMaterialPage;
import com.fnb.web.admin.pages.inventory.ingredients.MaterialManagementPage;
import com.fnb.web.admin.pages.product.category.CreateProductCategoryPage;
import com.fnb.web.admin.pages.product.category.ProductCategoryManagementPage;
import com.fnb.web.admin.pages.store.areaTable.AddNewAreaDialog;
import com.fnb.web.admin.pages.store.areaTable.AddNewTableDialog;
import com.fnb.web.admin.pages.store.areaTable.AreaTableManagementPage;
import com.fnb.web.admin.pages.store.feeandtax.FeeManagementPage;
import com.fnb.web.admin.pages.store.feeandtax.TaxManagementPage;
import dataObject.Product.Product;
import com.fnb.web.admin.pages.product.options.OptionManagementPage;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Data
public class CreateProductPage extends CreateTopping_Product_Common {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    CreateMaterialPage createMaterialPage;
    MaterialManagementPage materialManagementPage;
    ProductManagementPage productManagementPage;
    OptionManagementPage optionManagementPage;
    OptionManagementPage.AddNewOptionDialog addNewOptionDialog;
    CreateProductCategoryPage createProductCategoryPage;
    ProductCategoryManagementPage productCategoryManagementPage;
    FeeManagementPage feeManagementPage;
    TaxManagementPage taxManagementPage;
    TaxManagementPage.AddNewTaxDialog addNewTaxDialog;
    CommonComponents commonComponents;
    CreateNewCustomerPage createNewCustomerPage;
    AreaTableManagementPage areaTableManagementPage;
    CreateToppingPage createToppingPage;
    MaterialCategoryManagementPage materialCategoryManagementPage;
    AddNewTableDialog addNewTableDialog;
    AddNewAreaDialog addNewAreaDialog;

    public CreateProductPage(WebDriver driver) {
        super(driver, CreateProductPage.class);
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        siderBar = new SiderBar(driver);
        this.driver = driver;
        createMaterialPage = new CreateMaterialPage(driver);
        materialManagementPage = new MaterialManagementPage(driver);
        productManagementPage = new ProductManagementPage(driver);
        optionManagementPage = new OptionManagementPage(driver);
        createProductCategoryPage = new CreateProductCategoryPage(driver);
        productCategoryManagementPage = new ProductCategoryManagementPage(driver);
        feeManagementPage = new FeeManagementPage(driver);
        taxManagementPage = new TaxManagementPage(driver);
        addNewTaxDialog = new TaxManagementPage(driver).new AddNewTaxDialog();
        commonComponents = new CommonComponents(driver);
        createNewCustomerPage = new CreateNewCustomerPage(driver);
        areaTableManagementPage = new AreaTableManagementPage(driver);
        addNewTableDialog = new AddNewTableDialog(driver);
        addNewAreaDialog = new AddNewAreaDialog(driver);
        materialCategoryManagementPage = new MaterialCategoryManagementPage(driver);
    }

    private By selSearchIncludedToppings = By.xpath("//*[text()='"+adminLocalization.getProductManagement().getIncludeTopping()+"']/following::input[./ancestor::*[contains(@class, 'search')]][1]");
    private By btnDelete = By.xpath("(//button[contains(@class, 'danger')])[1]");

    public enum Element {
        NAME, PRICE, UNIT, MATERIAL, ICON_DELETE
    }

    public CreateProductPage selectTax(String taxName) {
        helper.scrollToElementAtTop(selSearchTax);
        helper.enterText(selSearchTax, taxName);
        helper.clickElement(By.xpath("//*[contains(text(),'"+taxName+"')]"));
        return this;
    }

    public CreateProductPage enterPrice(String price) {
        helper.scrollToElementAtMiddle(txtPrice);
        helper.enterText(txtPrice, price);
        return this;
    }

    public void verifyPlaceHolder(Element element, String expectedText) {
        switch (element) {
            case NAME -> {
                Assert.assertEquals(helper.getWebElement(txtProductName).getAttribute("placeholder"), expectedText);
            }
        }
    }

    public void clickBtnCreate() {
        helper.waitForElementVisible(commonComponents.getBtnCreate()).click();
    }

    public CreateProductPage clickBtnCancel() {
        helper.scrollToElementAtBottom(btnCancel);
        helper.clickElement(btnCancel);
        return this;
    }

    public void clickPriceDeleteIcon(int index) {
        By iconDeleteIndex = By.xpath("(//div[contains(@class, 'icon-delete-price')])[" + index + "]");
        helper.clickElement(iconDeleteIndex);
    }

    public CreateProductPage clickBtnAddPrice() {
        helper.scrollToElementAtMiddle(btnAddPrice);
        helper.waitForElementVisible(btnAddPrice).click();
        return this;
    }

    public CreateProductPage checkElementAppearance(Element element) {
        switch (element) {
            case ICON_DELETE -> {
                helper.waitForElementVisible(By.xpath("(//div[contains(@class, 'icon-delete-price')])[1]"));
            }
        }
        return this;
    }

    public CreateProductPage verifyLblMessage(Element element, String expectedText) {
        switch (element) {
            case PRICE -> {
                helper.waitForElementVisible(lblMessagePrice);
                Assert.assertEquals(helper.getText(lblMessagePrice), expectedText);
            }
            case UNIT -> {
                helper.waitForElementVisible(lblMessageUnit);
                Assert.assertEquals(helper.getText(lblMessageUnit), expectedText);
            }
            case MATERIAL -> {
                helper.waitForElementVisible(lblMessageMaterial);
                Assert.assertEquals(helper.getText(lblMessageMaterial), expectedText);
            }
        }
        return this;
    }

    public void verifyMaxLength(String maxLength) {
        Assert.assertEquals(helper.waitForElementVisible(txtProductName).getAttribute("maxlength"), maxLength);
    }

    public void verifyLargeImageUpload(String imagePath, String errorMessage) {
        helper.waitForElementVisible(btnAddFile);
        helper.getWebElement(inputUploadFile).sendKeys(System.getProperty("user.dir") + "/" + imagePath);
        Assert.assertEquals(helper.getText(toastFailureMessage), errorMessage);
    }

    public int countTheNumberOfPriceItem() {
        try {
            By priceItemsLocator = By.xpath("//div[contains(@class, 'price-item')]");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(priceItemsLocator));
            List<WebElement> priceItem = driver.findElements(priceItemsLocator);
            return priceItem.size();
        } catch (TimeoutException exception) {
            return 0;
        }
    }

    public CreateProductPage chooseBaseUnit(String unit) {
        helper.scrollToElementAtMiddle(selSearchUnit);
        helper.clickElement(selSearchUnit);
        helper.enterText(selSearchUnit, unit);
        By unitElement = By.xpath("//div[contains(@class, 'ant-select-item-option')]//div[normalize-space()='" + unit + "']");
        if (helper.isElementVisible(emptyDescription)) {
            helper.clickElement(newUnitElement);
            helper.clickElement(unitElement);
        } else {
            helper.clickElement(unitElement);
        }
        return this;
    }

    public By getSelectedMaterialQuantityEle(String materialName, String index) {
        return By.xpath("("+commonComponents.getTableData(materialName)+"//input[contains(@id, 'quantity')])[" + index + "]");
    }

    public String getSelectedMaterialQuantity(String materialName) {
        return helper.getWebElement(getSelectedMaterialQuantityEle(materialName)).getAttribute("value");
    }

    public String getQuantityError(String materialName) {
        return helper.getText(By.xpath(""+commonComponents.getTableData(materialName)+"//div[@class='ant-form-item-explain-error']"));
    }

    public By getInventoryTabEle(String priceName) {
        return By.xpath("//div[contains(@id, 'tab-inventory-tab') and normalize-space()='" + priceName + "']");
    }

    public void clickOnInventoryTab(String priceName) {
        helper.clickElement(getInventoryTabEle(priceName));
    }

    public void chooseIngredientName(List<Product.Information.productInventoryData> productInventoryData) {
        List<Product.Information.productInventoryData.ListProductPriceMaterial> listProductPriceMaterials;

        clickSelSearchIngredient();

        if (productInventoryData.size() == 1) {
            listProductPriceMaterials = productInventoryData.get(0).getListProductPriceMaterials();
            for (int i = 0; i < listProductPriceMaterials.size(); i++) {
                addSelectedIngredient(listProductPriceMaterials.get(i).getMaterial());
            }
        }
        else {
            for (int i = 0; i < productInventoryData.get(0).getListProductPriceMaterials().size(); i++) {
                addSelectedIngredient(productInventoryData.get(i).getListProductPriceMaterials().get(i).getMaterial());
            }
        }

        // Click Add Ingredient
        helper.clickElement(By.xpath("//*[contains(text(), 'Selected Ingredient(s)')]"));
    }

    public void chooseMaterialRecipe(List<Product.Information.productInventoryData> productInventoryData) {
        List<Product.Information.productInventoryData.ListProductPriceMaterial> listProductPriceMaterials;

        if (productInventoryData.size() == 1) {
            listProductPriceMaterials = productInventoryData.get(0).getListProductPriceMaterials();
            chooseIngredientName(productInventoryData);

            for (int i = 0; i < listProductPriceMaterials.size(); i++) {
                helper.scrollToElementAtTop(selSearchMaterial);
                By quantityElement = getSelectedMaterialQuantityEle(listProductPriceMaterials.get(i).getMaterial());
                helper.scrollToElementAtTop(quantityElement);
                helper.enterText(quantityElement, listProductPriceMaterials.get(i).getQuantity());
            }
        } else {
            for (int i = 0; i < productInventoryData.size(); i++) {
                listProductPriceMaterials = productInventoryData.get(i).getListProductPriceMaterials();
                clickOnInventoryTab(listProductPriceMaterials.get(i).getPriceName());

                clickSelSearchIngredient();
                // Choose ingredient name
                for (int k = 0; k < productInventoryData.get(i).getListProductPriceMaterials().size(); k++) {
                    addSelectedIngredient(productInventoryData.get(i).getListProductPriceMaterials().get(k).getMaterial());
                }
                // Click Add Ingredient
                helper.clickElement(By.xpath("//*[contains(text(), 'Selected Ingredient(s)')]"));

                for (int j = 0; j < listProductPriceMaterials.size(); j++) {
                    By quantityElement = getSelectedMaterialQuantityEle(listProductPriceMaterials.get(j).getMaterial(), listProductPriceMaterials.get(j).getPosition());
                    helper.scrollToElementAtBottom(quantityElement);
                    helper.enterText(quantityElement, listProductPriceMaterials.get(j).getQuantity());
                }
            }
        }
    }

    public By getPriceNameInputEle(String position) {
        return By.xpath("//*[text()='"+adminLocalization.getProductManagement().getPricing().getPricesAndVariations()+"']/following::input[contains(@id, 'product-prices') and contains(@id, 'name')]["+position+"]");
    }

    public By getPriceValueInputEle(String position) {
        return By.xpath("//*[text()='"+adminLocalization.getProductManagement().getPricing().getPricesAndVariations()+"']/following::input[contains(@id, 'product-prices') and not(contains(@id, 'name'))]["+position+"]");
    }

    public CreateProductPage enterPrice(List<Product.Information.Price> prices) {
        if (prices.size() == 1) {
            // The product only have one price
            helper.enterText(txtPrice, prices.get(0).getPriceValue());
        } else {
            // The product have multiple price
            for (int i = 1; i < prices.size(); i++) {
                clickBtnAddPrice();
            }
            for (int i = 0; i < prices.size(); i++) {
                helper.scrollToElementAtBottom(getPriceNameInputEle(prices.get(i).getPosition()));
                helper.enterText(getPriceNameInputEle(prices.get(i).getPosition()), prices.get(i).getPriceName());
                helper.enterText(getPriceValueInputEle(prices.get(i).getPosition()), prices.get(i).getPriceValue());
            }
        }
        return this;
    }

    public CreateProductPage clickSelSearchIngredient() {
        helper.scrollToElementAtTop(selSearchMaterial);
        helper.clickElement(selSearchMaterial);
        return this;
    }
    public void createProducts(String productName, String image, List<Product.Information.ToppingName> toppingNames, List<Product.Information.Option> option, List<Product.Information.Price> price, List<Product.Information.Platform> platforms, String unit, List<Product.Information.productInventoryData> productInventoryData, String taxName) {
        helper.enterText(txtProductName, productName);
        upLoadImage(image);
        chooseTopping(toppingNames);
        selectOption(option);
        enterPrice(price);
        choosePlatform(platforms);
        selectTax(taxName);
        chooseBaseUnit(unit);
        chooseMaterialRecipe(productInventoryData);
        helper.scrollToElementAtBottom(commonComponents.getBtnCreate());
        clickBtnCreate();
        helper.waitForElementVisible(toastSuccessMessage);
    }

    public CreateProductPage prepareData() throws IOException {
        // Create materials
        for (int i = 0; i < materialData.getMaterial().size(); i++) {
            helper.navigateToUrl(Setup.configObject.getUrlCreateMaterial());
            createMaterialPage.createMaterials(
                    materialData.getMaterial().get(i).getName(),
                    materialData.getMaterial().get(i).getImage(),
                    materialData.getMaterial().get(i).getUnit(),
                    materialData.getMaterial().get(i).getCostPerUnit(),
                    materialData.getMaterial().get(i).getMinQuantity(),
                    materialData.getMaterial().get(i).getBranch().get(0).getName(),
                    materialData.getMaterial().get(i).getBranch().get(0).getQuantity()
            );
        }

        // Create options
        for (int i = 0; i < optionData.getOptions().size(); i++) {
            helper.navigateToUrl(Setup.configObject.getUrlOptionManagement());
            //addNewOptionDialog = optionManagementPage.openAddNewOption();
            addNewOptionDialog = new OptionManagementPage(driver) .new AddNewOptionDialog();
            helper.clickElement(commonComponents.getBtnAddNew());
            addNewOptionDialog.addNewOption(
                    optionData.getOptions().get(i).getName(),
                    optionData.getOptions().get(i).getMaterialName(),
                    optionData.getOptions().get(i).getOptionLevels());
        }

        // Create tax
        for (int i=0 ; i < taxData.getTax().size(); i++) {
            helper.navigateToUrl(configObject.getUrlFeeTaxManagement());
            feeManagementPage.clickTaxTab();
            taxManagementPage.helper.clickElement(commonComponents.getBtnAddNew());
            addNewTaxDialog.addTax(taxData.getTax().get(i).getName(), taxData.getTax().get(i).getPercentage(), taxData.getTax().get(i).getTaxType());
        }

        // Create topping
        for (int  i=0; i < toppingData.getTopping().size(); i++) {
            helper.navigateToUrl(Setup.configObject.getUrlProductManagement());
            createToppingPage = productManagementPage.clickAddNewButton().clickAddNewTopping();
            createToppingPage.createATopping(
                    toppingData.getTopping().get(i).getName(),
                    toppingData.getTopping().get(i).getImage(),
                    toppingData.getTopping().get(i).getPrice(),
                    toppingData.getTopping().get(i).getPlatforms(),
                    toppingData.getTopping().get(i).getUnit(),
                    toppingData.getTopping().get(i).getProductInventoryData());
        }

        // Create products
        for (int i = 0; i < productData.getProduct().size(); i++) {
            helper.navigateToUrl(Setup.configObject.getUrlCreateProduct());
            createProducts(
                    productData.getProduct().get(i).getName(),
                    productData.getProduct().get(i).getImage(),
                    productData.getProduct().get(i).getTopping(),
                    productData.getProduct().get(i).getOption(),
                    productData.getProduct().get(i).getPrice(),
                    productData.getProduct().get(i).getPlatforms(),
                    productData.getProduct().get(i).getUnit(),
                    productData.getProduct().get(i).getProductInventoryData(),
                    productData.getProduct().get(i).getTax()
            );
        }

        // Create product categories
        for (int i = 0; i < productCategoryData.getProductCategory().size(); i++) {
            helper.navigateToUrl(Setup.configObject.getUrlProductCategoryManagement());
            helper.clickElement(commonComponents.getBtnAddNew());
            createProductCategoryPage.createProductCategory(
                    productCategoryData.getProductCategory().get(i).getName(),
                    productCategoryData.getProductCategory().get(i).isDisplayAllBranches(),
                    productCategoryData.getProductCategory().get(i).getBranchs(),
                    productCategoryData.getProductCategory().get(i).getPriority(),
                    productCategoryData.getProductCategory().get(i).getProducts()
            );
        }

        // Create customers
        for (int i=0; i < customerData.getCustomers().size(); i++) {
            helper.navigateToUrl(configObject.getUrlCreateCustomer());
            createNewCustomerPage.createACustomer(
                    customerData.getCustomers().get(i).getFirstName(),
                    customerData.getCustomers().get(i).getLastName(),
                    customerData.getCustomers().get(i).getAddress().getCountry(),
                    customerData.getCustomers().get(i).getPhone(),
                    customerData.getCustomers().get(i).getEmail(),
                    customerData.getCustomers().get(i).getBirthDay(),
                    customerData.getCustomers().get(i).getGender(),
                    customerData.getCustomers().get(i).getAddress().getAddress(),
                    customerData.getCustomers().get(i).getAddress().getCity(),
                    customerData.getCustomers().get(i).getAddress().getDistrict(),
                    customerData.getCustomers().get(i).getAddress().getWard()
            );
        }

        // Create areas and tables
        for (int i=0; i < areaTableData.getAreas().size(); i++) {
            helper.navigateToUrl(configObject.getUrlAreaTable());
            addNewAreaDialog.createArea(
                    areaTableData.getAreas().get(i).getBranch(),
                    areaTableData.getAreas().get(i).getAreaName()
            );
            helper.smartWait();
            for (int j=0; j < areaTableData.getAreas().get(i).getTables().size(); j++) {
                areaTableManagementPage.clickAddNewTable();
                addNewTableDialog.createTable(
                        areaTableData.getAreas().get(i).getTables().get(j).getTableName(),
                        areaTableData.getAreas().get(i).getAreaName(),
                        areaTableData.getAreas().get(i).getTables().get(j).getSeat()
                );
            }
        }

        // Create material categories
        for (int i=0; i < materialCategoryData.getMaterialCategories().size(); i++) {
            helper.navigateToUrl(configObject.getUrlMaterialCategoryManagement());
            materialCategoryManagementPage.clickAddNew();
            materialCategoryManagementPage.createMaterialCategory(
                    materialCategoryData.getMaterialCategories().get(i).getName(),
                    materialCategoryData.getMaterialCategories().get(i).getMaterials());
        }
        return this;
    }

    public CreateProductPage clearData() {
        // Delete options
        helper.navigateToUrl(Setup.configObject.getUrlOptionManagement());
        for (int i = 0; i < optionData.getOptions().size(); i++) {
            optionManagementPage.deleteOption(optionData.getOptions().get(i).getName());
        }

        // Delete materials
        helper.navigateToUrl(Setup.configObject.getUrlMaterialManagement());
        for (int i = 0; i < materialData.getMaterial().size(); i++) {
            materialManagementPage.deleteMaterial(materialData.getMaterial().get(i).getName());
        }

        // Delete products
        helper.navigateToUrl(Setup.configObject.getUrlProductManagement());
        for (int i = 0; i < productData.getProduct().size(); i++) {
            productManagementPage.deleteProduct(productData.getProduct().get(i).getName());
        }

        // Delete toppings
        helper.navigateToUrl(Setup.configObject.getUrlProductManagement());
        for (int i = 0; i < toppingData.getTopping().size(); i++) {
            productManagementPage.deleteProduct(toppingData.getTopping().get(i).getName());
        }

        // Delete product categories
        helper.navigateToUrl(Setup.configObject.getUrlProductCategoryManagement());
        for (int i = 0; i < productCategoryData.getProductCategory().size(); i++) {
            productCategoryManagementPage.deleteProductCategory(productCategoryData.getProductCategory().get(i).getName());
        }
        return this;
    }

    public CreateProductPage selectOption(List<Product.Information.Option> optionName) {
        helper.scrollToElementAtTop(selSearchOption);
        for (int i = 0; i < optionName.size(); i++) {
            commonComponents.searchAndClickForSelSearchInput(selSearchOption, optionName.get(i).getName());
        }
        return this;
    }

    public CreateProductPage selectOption(String optionName) {
        helper.scrollToElementAtMiddle(selSearchOption);
        helper.enterText(selSearchOption, optionName);
        helper.clickElement(By.xpath(""+helper.getTheXpathStringFromBy(selSearchOption)+"//following::*[text()='"+optionName+"'][1]"));
        return this;
    }

    public CreateProductPage selectCategory(String categoryName) {
        helper.scrollToElementAtBottom(selSearchCategory);
        commonComponents.searchAndClickForSelSearchInput(selSearchCategory, categoryName);
        return this;
    }

    public CreateProductPage deleteOption(String optionName) {
        helper.clickElement(By.xpath("//*[contains(text(),'" + optionName + "')]//ancestor::div[2]//following-sibling::div[1]"));
        return this;
    }

    // attribute like Quantity, Unit, Cost, TotalCost
    public String getRecipeMaterialData(String materialName, String attribute) {
        return helper.getText(By.xpath(""+commonComponents.getTableData(materialName)+"//td["+commonComponents.theOrderOfColumn(attribute)+"]"));
    }

    public void clickDelete_Recipe_Material(String materialName) {
        helper.clickElement(By.xpath(""+commonComponents.getTableData(materialName)+"//a"));
    }

    public CreateProductPage choosePlatform(List<Product.Information.Platform> platforms) {
        helper.scrollToElementAtTop(selSearchOption);

        helper.getWebElement(getPlatformCheckBoxEle("POS devices")).click();
        helper.getWebElement(getPlatformCheckBoxEle("Store Web")).click();
        helper.getWebElement(getPlatformCheckBoxEle("Store App")).click();

        for (int i = 0; i < platforms.size(); i++) {
            helper.getWebElement(getPlatformCheckBoxEle(platforms.get(i).getName())).click();
        }
        return this;
    }

    public CreateProductPage chooseTopping(List<Product.Information.ToppingName> toppingNames) {
        for(int i=0; i < toppingNames.size(); i++) {
            commonComponents.searchAndClickForSelSearchInput(selSearchIncludedToppings, toppingNames.get(i).getName());
        }
        return this;
    }

    public String createAProductWithAnyTypeData(String category) {
        helper.navigateToUrl(configObject.getUrlCreateProduct());
        Product.Information.Price price1 = new Product.Information.Price("S", "2000", "1");
        Product.Information.Platform POSplatform = new Product.Information.Platform("POS devices");
        Product.Information.Platform StoreWeblatform = new Product.Information.Platform("Store App");
        Product.Information.Platform StoreApplatform = new Product.Information.Platform("Store Web");

        List<Product.Information.Price> prices = Arrays.asList(price1);
        List<Product.Information.Platform> platforms = Arrays.asList(POSplatform, StoreWeblatform, StoreApplatform);

        String productName = "product" + helper.generateRandomNumber();
        helper.enterText(txtProductName, productName);
        upLoadImage("resources/image/productIcon.png");
        enterPrice(prices);
        choosePlatform(platforms);
        chooseBaseUnit("ml");
        selectTax(taxData.getTax().get(0).getName());
        chooseMaterialRecipe(productData.getProduct().get(0).getProductInventoryData());
        selectCategory(category);
        helper.scrollToElementAtBottom(commonComponents.getBtnCreate());
        clickBtnCreate();
        helper.waitForElementVisible(toastSuccessMessage);
        return productName;
    }
}
