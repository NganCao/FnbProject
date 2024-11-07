package com.fnb.web.pos.pages;

import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import static java.lang.Integer.parseInt;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class LoginPosPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public LoginPosPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        inStorePage = new InStorePage(driver);
    }
    private By txtUserName = By.id("basic_userName");
    private By txtPassword = By.id("basic_password");
    private By btnLogin = By.xpath("//button[@type='submit']");
    private By lblErrorUserName = By.xpath("//*[@id=\"basic_userName_help\"]/div");
    private By lblErrorPassword = By.xpath("//*[@id=\"basic_password_help\"]/div");
    private By lblErrorWrongUserNameAndPassword = By.xpath("//*[@id=\"basic\"]/div/div[2]/p");
    private By lblTitle = By.cssSelector(".label-store");
    private By btnContinue = By.xpath("//span[normalize-space()='Continue']");
    private By btnStart = By.xpath("//span[normalize-space()='Start']");
    private By btnCheckInventory = By.xpath("//div[@class='box-inventory-checking']");
    private By itemLocator = By.xpath("//div[@class='store-detail-form item-option pointer']");
    private By listItemsLocator = By.xpath("//div[@class='store-form list-item']");
    private By lblTitleTest = By.cssSelector(".label-store");
    private InStorePage inStorePage;

    public String getErrorMessageEmail() {
        return helper.getText(lblErrorUserName);
    }

    public String getErrorMessagePassword() {
        return helper.getText(lblErrorPassword);
    }

    public String getErrorMessageWrongUserNameAndPassword() {
        return helper.waitForElementVisible(lblErrorWrongUserNameAndPassword).getText();
    }

    public String getHeadingTitle() {
        return helper.getText(lblTitle);
    }

    public void selectBranchByIndex(int branchIndex) throws InterruptedException {
        By branchLocator  = By.xpath("//div[@class='store-detail-form item-option pointer']["+ branchIndex +"]");
        helper.clickElement(branchLocator);
    }

    public void verifyStartShift() {
        boolean check = helper.isElementVisible(btnCheckInventory);
        if (check) {
            helper.clickElement(btnContinue);
            helper.clickElement(btnStart);
            inStorePage.verifyHeader();
        }
        else {
            inStorePage.verifyHeader();
        }
    }

    public void detectScrollbar() {
        helper.waitForElementVisible(itemLocator);
        List<WebElement> elements = driver.findElements(itemLocator);
        int items = elements.size();
        if (items >= 4) {
            WebElement container = driver.findElement(listItemsLocator);
            int scrollHeight = parseInt(container.getAttribute("scrollHeight"));
            int offsetHeight = parseInt(container.getAttribute("offsetHeight"));
            Boolean isScrollable = scrollHeight> offsetHeight;
            Log.info("Have scrollbar");
            Assert.assertTrue(isScrollable, "The frame does not have scroll bar");

            // Scroll to the last element
            String lastItemXpath = "//div[@class='store-detail-form item-option pointer']" + "[" + items +"]";
            By lastLocator = By.xpath(lastItemXpath);
            WebElement lastElement = helper.waitForElementVisible(lastLocator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", lastElement);

        }
        else {
            System.out.println("No scrollbar");
        }
    }

    public void verifyLabel(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBe(lblTitleTest, expectedText));
        Assert.assertEquals(helper.getText(lblTitleTest), expectedText);
    }

    public void selectStoreOrBranchByIndex(int storeIndex) throws InterruptedException {
        By item  = By.xpath("//div[@class='store-detail-form item-option pointer']["+ storeIndex +"]");
        helper.clickElement(item);
    }

    public void selectBranchByName(String branchName) {
        helper.clickElement(By.xpath("//div[@class='store-detail-form item-option pointer' and normalize-space()='"+branchName+"']"));
    }

    public LoginPosPage enterUserName(String userName) {
        helper.enterText(txtUserName, userName);
        return this;
    }

    public LoginPosPage enterPassword(String password) {
        helper.enterText(txtPassword, password);
        return this;
    }

    public LoginPosPage clickLoginButton() {
        helper.clickElement(btnLogin);
        return this;
    }

    public void login(String username, String password, String branchName) {
        driver.get(configObjectPos.getUrlLogin());
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
        selectBranchByName(branchName);
        verifyStartShift();
        helper.waitForElementVisible(inStorePage.orderTypeBar.getImageLogo());
    }
}
