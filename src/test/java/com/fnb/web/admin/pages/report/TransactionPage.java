package com.fnb.web.admin.pages.report;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class TransactionPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    private By tabOrder = By.xpath("//div[text()='"+ElementData.Order_Tab+"']/ancestor::div[contains(@class, 'ant-tabs-tab')]");
    private By tabReservation = By.xpath("//div[text()='"+ElementData.Reservation_Tab+"']/ancestor::div[contains(@class, 'ant-tabs-tab')]");
    private By tabShift = By.xpath("//div[text()='"+adminLocalization.getReport().getShift().getTitle()+"']/ancestor::div[contains(@class, 'ant-tabs-tab')]");
    private By tabProduct = By.xpath("//div[text()='"+ElementData.Product_Tab+"']/ancestor::div[contains(@class, 'ant-tabs-tab')]");
    private By tabCombo = By.xpath("//div[text()='"+ElementData.Combo_Tab+"']/ancestor::div[contains(@class, 'ant-tabs-tab')]");
    private By titleSoldProduct = By.xpath("//*[text()='"+ElementData.SoldProductTitle+"']");
    private By btnExport = By.xpath("//button[contains(@class, 'fnb-add-new-button')]");

    public TransactionPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }
    public TransactionPage clickProductTab() {
        helper.clickElement(tabProduct);
        helper.waitTextToBePresent(By.xpath("//div[@class='title']"), DataTest.Title);
        return this;
    }

    public TransactionPage clickShiftTab() {
        helper.clickElement(tabShift);
        String shiftId = adminLocalization.getReport().getShift().getId();
        helper.waitTextToBePresent(By.xpath("//*[text()='"+shiftId+"']"), shiftId);
        return this;
    }

    public void clickTheFirstShiftNoPermission() {
        String shiftId = adminLocalization.getReport().getShift().getId();
        helper.clickElement(By.xpath("//th[normalize-space()='"+shiftId+"']/ancestor::thead//following::tr[2]"));
    }

    public void click(By by, int expectedNumberOfElements, String message, int retries) {

    }

    public void clickTheFirstShiftHasPermission() {
        String shiftId = adminLocalization.getReport().getShift().getId();
        String shiftTitle = adminLocalization.getReport().getShiftDetail().getTitle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean conditionMet = false;
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                helper.clickElement(By.xpath("//th[normalize-space()='"+shiftId+"']/ancestor::thead//following::tr[2]"));
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[text()='"+shiftTitle+"']"), shiftTitle));
                conditionMet = true;
                break;
            } catch (Exception e) {
                Log.info("Attempt " + (attempt + 1) + " failed, retrying...");
            }
        }
        if (!conditionMet) {
            Assert.fail("Can not view shift detail");
        }
    }

    public TransactionPage scrollTo(By element) {
        helper.scrollToElementAtMiddle(element);
        return this;
    }
}
