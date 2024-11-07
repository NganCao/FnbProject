package com.fnb.web.pos.pages;

import com.fnb.web.pos.pages.component.MainContentColumn;
import com.fnb.web.pos.pages.component.OrderTypeBar;
import com.fnb.web.pos.pages.component.RightCheckOutColumn;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class InStorePage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public InStorePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        orderTypeBar = new OrderTypeBar(driver);
        mainContentColumn = new MainContentColumn(driver);
        rightCheckOutColumn = new RightCheckOutColumn(driver);
    }
    public OrderTypeBar orderTypeBar;
    public MainContentColumn mainContentColumn;
    public RightCheckOutColumn rightCheckOutColumn;

    private By headerTitle = By.xpath("//span[@class='header-date-info-title']");

    public void verifyHeader() {
        Assert.assertEquals(helper.getText(headerTitle), "IN-STORE");
    }

    public By getProductEle(String productName) {
        return By.xpath("//h3[normalize-space()='"+productName+"']//ancestor::div[@class='product-card-dashboard']");
    }
}
