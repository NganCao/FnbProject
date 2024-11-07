package com.fnb.web.pos.pages.component;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.pos.pages.ReservationPage;
import com.fnb.web.pos.pages.KitchenPage;
import com.fnb.web.pos.pages.SettingDialog;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class OpenLeftMenu extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    private String leftMenuDiv = "//div[contains(@class, 'open-left-menu')]";
    private By btnCollapse = By.xpath("//div[contains(@class, 'open-left-menu')]//div[contains(@class, 'expand')]");
    private By btnOrder = By.xpath("" + leftMenuDiv + "//span[normalize-space()='" + posLocalization.getLeftMenu().getOrder() + "']");
    private By lblUserName = By.xpath("" + leftMenuDiv + "//div[@class='user-info']//a");
    private By btnLogOut = By.xpath("" + leftMenuDiv + "//a[@class='user-logout']");
    private By btnReservation = By.xpath("" + leftMenuDiv + "//*[text()='" + posLocalization.getReserve().getTitle() + "']");
    private By btnKitchen = By.xpath("" + leftMenuDiv + "//*[text()='" + posLocalization.getLeftMenu().getKitchen() + "']");
    private By btnSetting = By.xpath("" + leftMenuDiv + "//*[text()='" + posLocalization.getLeftMenu().getSetting() + "']");

    public OpenLeftMenu(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    public void clickOrder() {
        helper.clickElement(btnOrder);
    }

    public void clickUserName() {
        helper.clickElement(lblUserName);
    }

    public void clickLogout() {
        helper.clickElement(btnLogOut);
    }

    public ReservationPage clickReservation() {
        helper.clickElement(btnReservation);
        return new ReservationPage(driver);
    }

    public void logOut() {
        clickUserName();
        clickLogout();
    }

    public KitchenPage clickKitchen() {
        helper.clickElement(btnKitchen);
        return new KitchenPage(driver);
    }

    public SettingDialog clickSetting() {
        helper.clickElement(btnSetting);
        return new SettingDialog(driver);
    }

    public void clickCollapseIcon() {
        helper.clickElement(btnCollapse);
    }
}
