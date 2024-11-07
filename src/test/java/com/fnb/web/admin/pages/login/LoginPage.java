package com.fnb.web.admin.pages.login;

import com.fnb.utils.helpers.Log;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;
import static java.lang.Integer.parseInt;

public class LoginPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public LoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By txtUserName = By.id("basic_userName");
    private By txtPassword = By.id("basic_password");
    private By btnLogin = By.xpath("//button[contains(@class, 'button')]");
    private By lblErrorUserName = By.xpath("//*[@id=\"basic_userName_help\"]/div");
    private By lblErrorPassword = By.xpath("//*[@id=\"basic_password_help\"]/div");
    private By notificationContent = By.xpath("//p[@class='your-text']");
    private By lnkRegister = By.xpath("//a[normalize-space()='Register']");
    private By storeLocator = By.xpath("//div[@class='store-detail-form']");
    private By storeFormLocator = By.xpath("//div[@class='store-form']");
    private By btnActiveAccount = By.xpath("//button[@class='ant-btn ant-btn-default activate-account-btn']");

    public void enterEmail(String username) {
        helper.enterText(txtUserName, username);
    }

    public void enterPassword(String password) {
        helper.enterText(txtPassword, password);
    }

    public void clickLoginButton() {
        helper.clickElement(btnLogin);
    }

    public void clickRegisterLink() {
        helper.clickElement(lnkRegister);
    }

    public String getErrorMessageUserName() {
        return helper.getText(lblErrorUserName);
    }

    public String getErrorMessagePassword() {
        return helper.getText(lblErrorPassword);
    }

    public String getNotificationContent() {
        return helper.getText(notificationContent);
    }

    public String getEmailPlaceHolderValue() {
        return helper.waitForElementVisible(txtUserName).getAttribute("placeholder");
    }

    public String getPasswordPlaceHolderValue() {
        return helper.waitForElementVisible(txtPassword).getAttribute("placeholder");
    }

    public void verifyActiveButton() {
        Assert.assertEquals(helper.getText(btnActiveAccount), "Activate account");;
    }

    public void verifyNoPasswordAlert() {
        try {
            helper.waitForElementInVisible(lblErrorPassword);
        }
        catch (Throwable error) {
            Assert.fail("There is a password error message");
        }
    }

    public void verifyNoUserAlert() {
        try {
            helper.waitForElementInVisible(lblErrorUserName);
        }
        catch (Throwable error) {
            Assert.fail("There is a user error message");
        }
    }

    public void selectStoreByIndex(int index) throws InterruptedException {
        By storeLocator  = By.xpath("//div[@class='store-detail-form']["+ index +"]");
        helper.waitForElementVisible(storeLocator).click();
    }

    public void detectScrollbar() {
        helper.waitForElementVisible(storeLocator);
        List<WebElement> elements = driver.findElements(storeLocator);
        int items = elements.size();
        if (items >= 4) {
            WebElement container = driver.findElement(storeFormLocator);
            int scrollHeight = parseInt(container.getAttribute("scrollHeight"));
            int offsetHeight = parseInt(container.getAttribute("offsetHeight"));
            Boolean isScrollable = scrollHeight> offsetHeight;
            Log.info("Have scrollbar");
            Assert.assertTrue(isScrollable, "The frame does not have scroll bar");

            // Scroll to the last element
            String lastItemXpath = "//div[@class='store-detail-form']" + "[" + items +"]";
            By lastLocator = By.xpath(lastItemXpath);
            WebElement lastElement = helper.waitForElementVisible(lastLocator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", lastElement);
        }
        else {
            System.out.println("No scrollbar");
        }
    }

    public HomePage loginWithEmailAndPassword(String email, String password) {
        driver.get(configObject.getUrlLogin());
        helper.enterText(txtUserName, email);
        helper.enterText(txtPassword, password);
        helper.clickElement(btnLogin);
        helper.waitForUrl(Setup.configObject.getUrlHome());
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
        return new HomePage(driver);
    }

    public void clickActiveAccount() {
        helper.clickElement(btnActiveAccount);
    }
}
