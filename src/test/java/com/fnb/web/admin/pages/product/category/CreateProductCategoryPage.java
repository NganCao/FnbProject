package com.fnb.web.admin.pages.product.category;

import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import dataObject.Product.ProductCategory;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.List;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class CreateProductCategoryPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public CreateProductCategoryPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By txtCategoryName = By.xpath("//input[@id='name']");
    private By selSearchBranch = By.xpath("//input[@id='storeBranchIds']");
    private By txtPriority = By.xpath("//input[@id='priority']");
    private By selSearchSelectProduct = By.xpath("//*[text()='Select product']/following::input[1]");
    private By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");
    private By chboxAllBranches = By.xpath("//input[@type='checkbox']");

    public CreateProductCategoryPage selectBranch(String branchName) {
        helper.getWebElement(selSearchBranch).sendKeys(branchName);
        helper.clickElement(By.xpath("//div[normalize-space()='"+branchName+"' and contains(@class, 'active')]"));
        return this;
    }

    public CreateProductCategoryPage selectProduct(String productName) {
        helper.getWebElement(selSearchSelectProduct).sendKeys(productName);
        helper.clickElement(By.xpath("//div[normalize-space()='"+productName+"' and @class='product-option-box']"));
        return this;
    }

    public CreateProductCategoryPage enterPriority(String priority) {
        helper.enterText(txtPriority, priority);
        return this;
    }

    public void createProductCategory(String categoryName, Boolean isDisplayAllBranches, List<ProductCategory.Information.BranchName> branchName, String priority, List<ProductCategory.Information.ProductName> productName) {
        helper.enterText(txtCategoryName, categoryName);

        // select the branch
        if (isDisplayAllBranches == false) {
            for (int i=0; i < branchName.size(); i++) {
                selectBranch(branchName.get(i).getName());
            }
        }
        else {
            helper.getWebElement(chboxAllBranches).click();
        }

        // enter priority
        enterPriority(priority);

        // Select products
        for (int i=0 ; i < productName.size(); i++) {
            selectProduct(productName.get(i).getName());
        }
        helper.getWebElement(commonComponents.getBtnAddNew()).sendKeys(Keys.ESCAPE);
        helper.clickElement(commonComponents.getBtnAddNew());
        helper.waitForElementVisible(toastSuccessMessage);
    }
}
