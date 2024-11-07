package com.fnb.web.admin.pages.crm.customer;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateNewCustomerPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public CommonComponents commonComponents;
    public SiderBar siderBar;

    public CreateNewCustomerPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
        siderBar = new SiderBar(driver);
    }

    private By btnAdd = By.xpath("//button[normalize-space()='"+adminLocalization.getButton().getAdd()+"']");
    private By txtName = By.xpath("//input[@id='basic_firstName']");
    private By txtLastName = By.xpath("//input[@id='basic_lastName']");
    private By selSearchInputCountry = By.xpath("//*[text()='" + adminLocalization.getForm().getCountry() + "']/following::input[1]");
    private By txtPhoneNumber = By.xpath("//input[@id='basic_phone']");
    private By txtEmail = By.xpath("//input[@id='basic_email']");
    private By txtBirthDay = By.xpath("//input[@id='basic_birthDay']");
    private By radioBtnFemale = By.xpath("//*[text()='"+adminLocalization.getCustomer().getAddNewForm().getFemale()+"']/preceding::input[1]/ancestor::span");
    private By radioBtnMale = By.xpath("//*[text()='"+adminLocalization.getCustomer().getAddNewForm().getMale()+"']/preceding::input[1]/ancestor::span");
    private By radioBtnOther = By.xpath("//*[text()='"+adminLocalization.getCustomer().getAddNewForm().getOther()+"']/preceding::input[1]/ancestor::span");
    private By txtAddress = By.xpath("//input[@id='basic_address_address1']");
    private By selSearchInputCityProvince = By.xpath("//*[text()='" + adminLocalization.getForm().getProvince() + "']/following::input[1]");
    private By selSearchInputCityDistrict = By.xpath("//*[text()='" + adminLocalization.getForm().getDistrict() + "']/following::input[1]");
    private By selSearchInputCityWard = By.xpath("//*[text()='" + adminLocalization.getForm().getWard() + "']/following::input[1]");

    public void selectCountry(String country) {
        commonComponents.searchAndClickForSelSearchInput(selSearchInputCountry, country);
    }

    public void selectCityProvince(String cityProvince) {
        commonComponents.searchAndClickForSelSearchInput(selSearchInputCityProvince, cityProvince);
    }

    public void selectDistrict(String disStrict) {
        commonComponents.searchAndClickForSelSearchInput(selSearchInputCityDistrict, disStrict);
    }

    public void selectWard(String ward) {
        commonComponents.searchAndClickForSelSearchInput(selSearchInputCityWard, ward);
    }

    public void selectBirthday(String date) {
        helper.enterText(txtBirthDay, date);
        helper.getWebElement(txtBirthDay).sendKeys(Keys.ENTER);
    }

    public void selectGender(String gener) {
        switch (gener) {
            case "Female":
                helper.clickElement(radioBtnFemale);
                break;

            case "Male":
                helper.clickElement(radioBtnMale);
                break;

            case "Other":
                helper.clickElement(radioBtnOther);
                break;
        }
    }

    public void clickAddButton() {
        helper.scrollToElementAtBottom(btnAdd);
        helper.clickElement(btnAdd);
    }
    public void createACustomer(String name, String lastName, String country, String phoneNumber, String email, String birthday, String gender, String address, String cityProvince, String district, String ward) {
        helper.enterText(txtName, name);
        helper.enterText(txtLastName, lastName);
//        selectCountry(country);
        helper.enterText(txtPhoneNumber, phoneNumber);
        helper.enterText(txtEmail, email);
        selectBirthday(birthday);
        selectGender(gender);
        helper.enterText(txtAddress, address);
        helper.scrollToElementAtTop(txtAddress);
        selectCityProvince(cityProvince);
        selectDistrict(district);
        helper.smartWait();
        selectWard(ward);
        clickAddButton();
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }
}
