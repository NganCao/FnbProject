package com.fnb.web.admin.pages.product.combo;

import com.fnb.web.admin.pages.common.CommonComponents;
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
public class ComboManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    private By txtSearchComboName = By.xpath("//div[@class='search-bar']//div[@class='search-bar']//input");
    private By btnStop = By.xpath("//button[contains(@class, 'danger')]");
    private By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");
    private By contentMessage = By.xpath("//div[contains(@class, 'text-content-notification')]");
    private By stopComboIcon = By.xpath("//button[contains(@class, 'stop-button') and not(ancestor::div[contains(@class, 'sibling')])]");

    public ComboManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        commonComponents = new CommonComponents(driver);
        this.driver = driver;
    }
    public ComboManagementPage enterSearch(String comboName) {
        helper.clearText(commonComponents.getTxtSearch());
        helper.enterText(commonComponents.getTxtSearch(), comboName);
        return this;
    }
    public ComboManagementPage deActiveCombo(String comboName) {
        helper.clickElement(commonComponents.getSearchIcon());
        enterSearch(comboName);
        // Click stop button
        commonComponents.selectItem(comboName);
        helper.clickElement(stopComboIcon);
        helper.clickElement(btnStop);
        helper.waitForElementVisible(toastSuccessMessage);
        return this;
    }
}
