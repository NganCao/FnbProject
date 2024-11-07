package com.fnb.web.pos.pages;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.pos.pages.component.MainContentColumn;
import com.fnb.web.pos.pages.component.OpenLeftMenu;
import com.fnb.web.pos.pages.component.OrderTypeBar;
import com.fnb.web.pos.pages.component.RightCheckOutColumn;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class KitchenPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public MainContentColumn mainContentColumn;
    public RightCheckOutColumn rightCheckOutColumn;

    public KitchenPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        mainContentColumn = new MainContentColumn(driver);
        rightCheckOutColumn = new RightCheckOutColumn(driver);
    }

    private By iconExpand = By.xpath("//*[@class='expand-icon-container']");
    private By tabOrder = By.xpath("//button[normalize-space()='"+posLocalization.getKitchenManagement().getOrder()+"']");
    private By tabDish = By.xpath("//button[normalize-space()='"+posLocalization.getKitchenManagement().getDish()+"']");

    public OpenLeftMenu clickIconExpand() {
        helper.clickElement(iconExpand);
        return new OpenLeftMenu(driver);
    }
}
