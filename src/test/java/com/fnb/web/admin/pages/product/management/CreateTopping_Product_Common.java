package com.fnb.web.admin.pages.product.management;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import dataObject.Product.Topping;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Data
public class CreateTopping_Product_Common<T extends CreateTopping_Product_Common<?>> extends Setup {
    //reference to self as the subclass type
    protected final T self;

    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SiderBar siderBar;
    private CommonComponents commonComponents;

    public CreateTopping_Product_Common(WebDriver driver, final Class<T> selfClass) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        siderBar = new SiderBar(driver);
        this.driver = driver;
        this.self = selfClass.cast(this);
        commonComponents = new CommonComponents(driver);
    }

    private By inputSearchMaterial = By.xpath("//*[text()='"+adminLocalization.getMaterial().getIngredientSearch()+"']//following::input[1]");
    protected By txtProductName = By.xpath("//input[@id='product-name']");
    protected By txtPrice = By.xpath("//input[@id='product-price']");
    protected By selSearchUnit = By.xpath("//*[text()='"+adminLocalization.getProductManagement().getUnit().getTitle()+"']/following::input[1]");
    protected By selSearchMaterial = By.xpath("//*[text()='"+adminLocalization.getProductManagement().getUnit().getRecipe()+"']/following::*[contains(@class, 'search')][1]");
    protected By lblMessagePrice = By.xpath("//div[@id='basic_product_price_help' and normalize-space()]");
    protected By lblMessageUnit = By.xpath("//div[@id='basic_product_unitId_help' and normalize-space()]");
    protected By lblMessageMaterial = By.xpath("//span[@id='receipe-message' and normalize-space()]");
    protected By emptyDescription = By.xpath("//div[@class='ant-empty-description']");
    protected By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");
    protected By toastErrorMessage = By.xpath("//div[contains(@class, 'ant-message-error')]");
    protected By toastFailureMessage = By.xpath("//div[contains(@class, 'ant-message-error')]");
    protected By inputUploadFile = By.xpath("//input[@type='file']");
    protected By btnAddFile = By.xpath("//input[@type='file']/..");
    protected By btnAddPrice = By.xpath("//span[text()='"+adminLocalization.getProductManagement().getPricing().getAddPrice()+"']");
    protected By btnCancel = By.xpath("//button[normalize-space()='Cancel']");
    protected By newUnitElement = By.xpath("//div[contains(@class, 'add-new-select')]");
    protected By selSearchOption = By.xpath("//*[text()='"+adminLocalization.getProductManagement().getOption().getTitle()+"']/following::input[1]");
    protected By selSearchCategory = By.xpath("//input[@id='basic_product_productCategoryId']");
    protected By checkBoxIsTopping = By.xpath("//input[@id='basic_product_topping']");
    protected By titlePlatform = By.xpath("//h4[normalize-space()='"+adminLocalization.getProductManagement().getPlatform().getTitle()+"']");
    protected By selSearchTopping = By.xpath("//div[contains(@class,'product-topping-search')]//input");
    protected By selSearchTax = By.xpath("//h4[normalize-space()='Tax']//following-sibling::div//input");
    protected By placeHolderTax = By.xpath("//h4[normalize-space()='Tax']//following-sibling::div//span[contains(@class, 'place')]");
    protected By btnTop_CreateProductOrTopping = By.xpath("(//button[@id='btn-add-new'])[1]");
    protected By btnBottom_CreateProductOrTopping = By.xpath("(//button[@id='btn-add-new'])[2]");

    public T upLoadImage(String imagePath) {
        helper.waitForElementVisible(btnAddFile);
        helper.getWebElement(inputUploadFile).sendKeys(System.getProperty("user.dir") + "/" + imagePath);
        helper.waitForElementPresent(By.xpath("//button[contains(@class,'btn-hidden')]"));
        return self;
    }

    public T enterPrice(String price) {
        helper.scrollToElementAtMiddle(txtPrice);
        helper.enterText(txtPrice, price);
        return self;
    }

    public T choosePlatformForTopping(List<Topping.Information.Platform> platforms) {
        helper.scrollToElementAtBottom(titlePlatform);

        helper.getWebElement(getPlatformCheckBoxEle("POS devices")).click();
        helper.getWebElement(getPlatformCheckBoxEle("Store Web")).click();
        helper.getWebElement(getPlatformCheckBoxEle("Store App")).click();

        for (int i = 0; i < platforms.size(); i++) {
            helper.getWebElement(getPlatformCheckBoxEle(platforms.get(i).getName())).click();
        }
        return self;
    }

    public By getPlatformCheckBoxEle(String platformName) {
        return By.xpath("//span[normalize-space()='" + platformName + "']//preceding-sibling::span//input");
    }

    public T chooseBaseUnit(String unit) {
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
        return self;
    }

    public By getMaterialSelectionEle(String materialName) {
        return By.xpath("//div[@name='" + materialName + "']");
    }

    public By getSelectedMaterialQuantityEle(String materialName) {
        return By.xpath(""+commonComponents.getTableData(materialName)+"//input");
    }

    public void enterMaterialName(String material) {
        helper.enterText(getInputSearchMaterial(), material);
    }

    public void addSelectedIngredient(String ingredientName) {
        enterMaterialName(ingredientName);
        helper.smartWait();
        // Wait the ingredient is visible
        helper.waitForElementVisible(By.xpath("//div[contains(@class, 'material-search')]//*[text()='"+ingredientName+"']"), "The ingredient is not visible");

        // Click checkbox of ingredient
        driver.findElement(By.xpath("//div[contains(@class, 'material-search')]//*[text()='"+ingredientName+"']/ancestor::tr//input")).click();

        // Click close circle icon to clear
        helper.clickElement(By.xpath("//*[text()='"+adminLocalization.getMaterial().getIngredientSearch()+"']//following::span[@aria-label='close-circle'][1]"));
    }
}
