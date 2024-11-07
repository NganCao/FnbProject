package com.fnb.web.pos.pages.component;

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
public class OrderTypeBar extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public OrderTypeBar(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By expandControl = By.xpath("//div[@class='order-type-container']//div[@class='expand-control']");
    private By imageLogo = By.xpath("//a[@class='navbar-logo']//img[@class='ant-image-img']");
    private By btnExpand = By.xpath("//div[@class='order-type-container']//div[@class='expand-control']");

    public void logOut() {
        OpenLeftMenu openLeftMenu = clickBtnExpand();
        openLeftMenu.clickUserName();
        openLeftMenu.clickLogout();
        helper.waitForUrl(Setup.configObjectPos.getUrlLogin());
    }

    public OpenLeftMenu clickBtnExpand() {
        helper.clickElement(btnExpand);
        return new OpenLeftMenu(driver);
    }

    public PosOrderDialog navigateToOrderList() {
        OpenLeftMenu openLeftMenu = clickBtnExpand();
        openLeftMenu.clickOrder();
        return new PosOrderDialog(driver);
    }

    public OrderTypeBar selectInStore_OrderType() {
        helper.clickElement(By.xpath("//*[text()='"+posLocalization.getPosOrder().getInStore()+"' and @class='order-type-name']"));
        return this;
    }

    public OrderTypeBar selectTakeAway_OrderType() {
        helper.clickElement(By.xpath("//*[text()='"+posLocalization.getPosOrder().getTakeAway()+"' and @class='order-type-name']"));
        return this;
    }

    public OrderTypeBar selectDelivery_OrderType() {
        helper.clickElement(By.xpath("//*[text()='"+posLocalization.getPosOrder().getDelivery()+"' and @class='order-type-name']"));
        return this;
    }
}
