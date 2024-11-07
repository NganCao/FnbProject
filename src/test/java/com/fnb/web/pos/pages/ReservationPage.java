package com.fnb.web.pos.pages;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class ReservationPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;
    public ReservationPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By btnCreateReservation = By.xpath("//*[text()='"+posLocalization.getReserve().getCreateReservation()+"']");
    private By btnBackIcon = By.xpath("//button[contains(@class, 'back-icon')]");
    private By btnCloseIcon = By.xpath("//div[@class='close-icon']");
    private By selSearchSelectCustomer = By.xpath("//*[text()='"+posLocalization.getReserTable().getSelectCustomer()+"']/following::input[1]");
    private By selSearchTable = By.xpath("//*[text()='"+posLocalization.getReserTable().getTable()+"']/following::*[contains(@class, 'selection-search')][1]");
    private By txtNumberOfGuest = By.xpath("//*[text()='"+posLocalization.getReserTable().getNumberOfGuest()+"']/following::input[1]");
    private By btnCreate = By.xpath("//button[./ancestor::div[contains(@class, 'reserve-table')]]");
    private By btnFirstCancelIcon = By.xpath("(//div[contains(@class, 'action-icon-close')])[1]");
    private By btnCancel = By.xpath("//div[contains(@class, 'btn-confirm-status')]//*[text()='"+posLocalization.getButton().getCancel()+"']");

    public ReservationPage clickCreateReservation() {
        helper.clickElement(btnCreateReservation);
        // Wait the title element of create new reservation dialog
        helper.waitForElementVisible(By.xpath("//*[contains(@class, 'title-reserve-table-modal')]"), "The reservation dialog is not visible");
        return this;
    }
    public void clickCloseIcon() {
        helper.clickElement(btnCloseIcon);
    }

    public void clickBackIcon() {
        helper.clickElement(btnBackIcon);
    }

    public void clickConfirmLeave() {
        helper.clickElement(commonComponents.btnConfirmLeave);
    }

    public void selectCustomer(String phone) {
        By phoneEle = By.xpath("//*[text()='"+phone+"' and ./ancestor::div[contains(@class, 'ant-select-dropdown')]]");
        helper.enterText(selSearchSelectCustomer, phone);
        helper.clickElement(phoneEle);
    }

    public void selectTable(String area, String table) {
        helper.clickElement(selSearchTable);
        helper.clickElement(By.xpath("//*[text()='"+area+"']/following::*[text()='"+table+"']/preceding-sibling::*[contains(@class, 'checkbox')]"));
        helper.clickElement(selSearchTable);
    }

    public void enterGuest(String numberOfGuest) {
        helper.enterText(txtNumberOfGuest, numberOfGuest);
    }

    public void clickCreate() {
        helper.clickElement(btnCreate);
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }

    public void createNewReservation(String customer, String area, String table, String numberOfGuest) {
        selectCustomer(customer);
        selectTable(area, table);
        enterGuest(numberOfGuest);
        clickCreate();
    }

    public void clickFirstReservationId() {
        helper.clickElement(By.xpath("(//td[1])[2]//span"));
    }

    public void clickArrivalTimeColum() {
        helper.clickElement(By.xpath("//*[text()='"+posLocalization.getReserve().getArrivalTime()+"']"));
    }
}
