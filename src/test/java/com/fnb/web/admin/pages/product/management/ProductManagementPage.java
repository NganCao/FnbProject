package com.fnb.web.admin.pages.product.management;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class ProductManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;

    public ProductManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private CommonComponents commonComponents;
    private By btnAddNew = By.xpath("//*[text()='"+adminLocalization.getButton().getAddNew()+"']");
    public By txtSearch = By.xpath("//div[@class='search-bar']//input");
    private By btnDelete = By.xpath("//button[contains(@class, 'ant-btn-dangerous')]");
    private By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");
    private By btnPrimary = By.xpath("//button[@class='ant-btn ant-btn-primary']");
    private By notificationContentMessage = By.xpath("//div[@class='text-content-notification']");
    private By confirmDeleteContentMessage = By.xpath("//div[contains(@class, 'delete-confirm-modal')]//p");
    private By btnFilter = By.xpath("//button[normalize-space()='Filter']");
    private By btnImport = By.xpath("//a[normalize-space()='Import']");
    private By btnAddNewFoodOrBeverage = By.xpath("//*[text()='"+adminLocalization.getProductManagement().getBtnMenuCreateProduct()+"']");
    private By btnAddNewTopping = By.xpath("//*[text()='"+adminLocalization.getProductManagement().getBtnMenuCreateTopping()+"']");

    public ProductManagementPage clickAddNewButton() {
        helper.clickElement(btnAddNew);
        return this;
    }

    public CreateProductPage clickAddNewFoodOrBeverage() {
        helper.clickElement(btnAddNewFoodOrBeverage);
        return new CreateProductPage(driver);
    }

    public CreateToppingPage clickAddNewTopping() {
        helper.clickElement(btnAddNewTopping);
        return new CreateToppingPage(driver);
    }

    public void clickFilterButton() {
        helper.clickElement(commonComponents.getFilterIcon());
        // Wait until the filter popover shown
        helper.waitForElementVisible(By.xpath("//div[@class='content-filter']"));
    }

    public ProductManagementPage deleteProduct(String productName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, productName);
        commonComponents.selectItem(productName);
        helper.clickElement(commonComponents.getBtnDeleteIcon());
        helper.clickElement(btnDelete);
        helper.waitForElementVisible(By.xpath("(//div[contains(@class, 'ant-message-success')])[1]"));
        helper.clickElement(commonComponents.getClearIcon());
        return this;
    }

    public ProductDetailsPage clickOnProduct(String productName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, productName);
        helper.clickElement(By.xpath(commonComponents.getTableData(productName) + "//td[2]"));
        return new ProductDetailsPage(driver);
    }

    public ProductEditPage clickProductEditIcon(String productName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, productName);
        commonComponents.selectItem(productName);
        helper.clickElement(commonComponents.getBtnEditIcon());
        ProductEditPage productEditPage = new ProductEditPage(driver);
        helper.waitForElementVisible(productEditPage.getBtnCancel());
        return productEditPage;
    }

    public ProductManagementPage clickDeleteButton(String productName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, productName);
        commonComponents.selectItem(productName);
        helper.clickElement(commonComponents.getBtnDeleteIcon());
        return this;
    }

    public ProductManagementPage verifyProductIsDelete(String productName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, productName);
        Assert.assertFalse(helper.isElementVisible(By.xpath(commonComponents.getTableData(productName))), "The product is not be deleted/ The product is still visible");
        helper.clickElement(commonComponents.getClearIcon());
        return this;
    }

    public ProductManagementPage verifyProductIsVisible(String productName) {
        helper.clickElement(commonComponents.getSearchIcon());
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, productName);
        helper.waitForElementVisible(By.xpath(commonComponents.getTableData(productName)), "The " + productName + " is not visible");
        helper.clickElement(commonComponents.getClearIcon());
        return this;
    }
}
