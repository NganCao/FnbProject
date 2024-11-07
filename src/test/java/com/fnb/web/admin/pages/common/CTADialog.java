package com.fnb.web.admin.pages.common;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class CTADialog extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Header header;
    By btnDiscard = By.xpath("//button[normalize-space()='"+CommonElementData.Discard+"']");
    By btnConfirmLeave = By.xpath("//button[normalize-space()='"+CommonElementData.Confirm_leave+"']");

    public boolean IsVisibleDiscard() {
        return helper.isElementVisible(btnDiscard);
    }

    public String getContentDialog() {
        By dialogBody = By.xpath("//div[@class='ant-modal-body']");
        return helper.getText(dialogBody);
    }

    public String getTitleDialog() {
        By dialogTitle = By.xpath("//div[@class='ant-modal-header']");
        return helper.getText(dialogTitle);
    }

    public void clickBtnDiscard() {
        helper.clickElement(btnDiscard);
    }

    public void clickBtnConfirmLeave() {
        helper.clickElement(btnConfirmLeave);
    }

    public CTADialog(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        header = new Header(driver);
        this.driver = driver;
    }
}
