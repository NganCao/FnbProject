package com.fnb.web.admin.pages.product.category;

import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class ProductCategoryManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public ProductCategoryManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By txtSearch = By.xpath("//input[contains(@class, 'ant-input-lg')]");
    private By btnDelete = By.xpath("(//button[contains(@class, 'ant-btn-dangerous')])[1]");
    private By iconClear = By.xpath("//span[@class='ant-input-clear-icon']");

    public ProductCategoryManagementPage deleteProductCategory(String categoryName) {
        By tableEle = By.xpath("//div[@class='ant-table-content']//table");
        By eptCategoryRow = By.xpath("(//span[contains(text(), '"+categoryName+"')]/ancestor::tr//div[@class='fnb-table-action-icon'])[2]");

        int initialHeigh = helper.getWebElement(tableEle).getSize().height;
        
        helper.clearText(txtSearch);
        helper.enterText(txtSearch, categoryName);
        // this code block will wait the result
        helper.waitForElementHeightToChange(tableEle, initialHeigh);

        helper.clickElement(eptCategoryRow);
        helper.clickElement(btnDelete);
        helper.clickElement(iconClear);
        helper.waitForElementVisible(By.xpath("(//div[contains(@class, 'ant-message-success')])[1]"));
        return this;
    }
}
