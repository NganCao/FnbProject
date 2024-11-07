package com.fnb.web.admin.scenario_test.register;

import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.mail.Mailinator;
import com.fnb.web.admin.pages.register.*;
import com.fnb.web.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegisterScenarioTest extends BaseTest {
    RegisterPage registerPage;
    Mailinator mailinator;
    LoginPage loginPage;

    @BeforeMethod
    public void navigateToPage() {
        registerPage = adminPage().navigateToRegisterPage();
        mailinator = new Mailinator(getDriver());
        loginPage = new LoginPage(getDriver());
    }

    @Test(testName = "Verify error when empty message of StoreName and BusinessName")
    public void FB7785() {
        registerPage.clickNextButton();
        Assert.assertEquals(registerPage.getStoreNameErrorMessage(), DataTest.ERROR_MESSAGE_STORENAME);
        Assert.assertEquals(registerPage.getBusinessAreaErrorMessage(), DataTest.ERROR_MESSAGE_BUSINESS_AREA);
    }

    @Test(testName = "Verify default value of Country field")
    public void FB7861() {
        loginPage.helper.sleep(2);
        Assert.assertEquals(registerPage.getCountryValue(), DataTest.NAME_COUNTRY_VIETNAM);
    }

    @Test(testName = "Verify currency will change to corresponded item while change country")
    public void FB7862() {
        registerPage.chooseCountry(Country.CUBA);
        registerPage.verifyCountryCurrencyMatcher(Country.CUBA);
    }

    @Test(testName = "Verify full name, phone number, email required fields")
    public void FB7863() throws InterruptedException {
        registerPage.navigateToYourInformation();
        registerPage.clickNextButton();
        registerPage.verifyFullNameErrorMessage(DataTest.EMPTY_MESSAGE_FULLNAME);
        registerPage.verifyPhoneErrorMessage(DataTest.EMPTY_MESSAGE_PHONENUMBER);
        registerPage.verifyEmailErrorMessage(DataTest.EMPTY_MESSAGE_EMAIL);
    }

    @Test(testName = "Verify when entering the invalid phone number")
    public void FB7951(){
        registerPage.navigateToYourInformation();
        registerPage.enterPhoneNumber(DataTest.INVALID_PHONE_NUMBER);
        registerPage.verifyPhoneErrorMessage(DataTest.ERROR_MESSAGE_PHONENUMBER);
    }

    @Test(testName = "Verify format email when entering the invalid email")
    public void FB8040(){
        registerPage.navigateToYourInformation();
        registerPage.enterEmail(DataTest.INVALID_EMAIL);
        registerPage.verifyEmailErrorMessage(DataTest.ERROR_MESSAGE_EMAIL);
    }

    @Test(testName = "Verify error when empty address, city/province, district and ward fields")
    public void FB8047(){
        registerPage.navigateToYourAddress();
        registerPage.clickComplete();
        registerPage.verifyAddressErrorMessage(DataTest.EMPTY_MESSAGE_ADDRESS);
        registerPage.verifyCityProvincErrorMessage(DataTest.EMPTY_MESSAGE_CITY_PROVINCE);
        registerPage.verifyDistrictErrorMessage(DataTest.EMPTY_MESSAGE_DISTRICT);
        registerPage.verifyWardErrorMessage(DataTest.EMPTY_MESSAGE_WARD);
    }

    @Test(testName = "Verify the default value of city/province, district and ward")
    public void FB8105() {
        registerPage.navigateToYourAddress();
        // In DOM structure, when the text box has no value, it will take the placeholder
        Assert.assertEquals(registerPage.getCityProvincePlaceholder(), DataTest.CITY_PROVINCE_PLACEHOLDER);
        Assert.assertEquals(registerPage.getDistrictPlaceholder(), DataTest.DISTRICT_PLACEHOLDER);
        Assert.assertEquals(registerPage.getWardValue(), DataTest.WARD_PLACEHOLDER);
    }

    @Test(testName = "Verify no district data when the province is not selected")
    public void FB8107() {
        registerPage.navigateToYourAddress();
        registerPage.verifyNoDistrictData();
    }

    @Test(testName = "Verify no ward data when the district is not selected")
    public void FB8109() {
        registerPage.navigateToYourAddress();
        registerPage.verifyNoWardData();
    }

    @Test(testName = "Verify district load all district value of selected province")
    public void FB8141() {
        registerPage.navigateToYourAddress();
        registerPage.chooseCityProvince(CityProvince.BINHDUONG);
        registerPage.verifyIfThereAreDistrictData();
    }

    @Test(testName = "Verify ward load all ward value of selected district")
    public void FB8142() {
        registerPage.navigateToYourAddress();
        registerPage.chooseCityProvince(CityProvince.BINHDUONG);
        registerPage.chooseDistrict(3);
        registerPage.verifyIfThereAreWardData();
    }

    @Test(testName = "Verify to see reloading district lists when city/province is changed")
    public void FB8143() {
        registerPage.navigateToYourAddress();
        registerPage.chooseCityProvince(CityProvince.BINHDUONG);
        registerPage.chooseDistrict(3);
        registerPage.chooseCityProvince(CityProvince.HOCHIMINH);
        Assert.assertEquals(registerPage.getDistrictPlaceholder(), DataTest.DISTRICT_PLACEHOLDER);
    }

    @Test(testName = "Verify to see reloading ward lists when district is changed")
    public void FB8144() {
        registerPage.navigateToYourAddress();
        registerPage.chooseCityProvince(CityProvince.BINHDUONG);
        registerPage.chooseDistrict(3);
        registerPage.chooseWard(2);
        registerPage.chooseDistrict(2);
        Assert.assertEquals(registerPage.getWardValue(), DataTest.WARD_PLACEHOLDER);
    }

    @Test(testName = "Verify register successfully when we complete all the step")
    public void FB8145() {
        String storeName = registerPage.generateStoreName();
        String fullName = registerPage.generateFullName();
        String phoneNumber = registerPage.generatePhoneNumber();
        String email = registerPage.generateEmail();

        registerPage.enterStoreName(storeName);
        registerPage.chooseCountry(Country.VIETNAM);
        registerPage.chooseBusinessArea(BusinessArea.BOTH);
        registerPage.clickNextButton();
        registerPage.verifyHeaderDisplay(DataTest.YOUR_INFORMATION_HEADER);
        registerPage.enterFullName(fullName);
        registerPage.enterPhoneNumber(phoneNumber);
        registerPage.enterEmail(email);
        registerPage.clickNextButton();
        registerPage.verifyHeaderDisplay(DataTest.YOUR_ADDRESS_HEADER);
        registerPage.enterAddress(DataTest.ADDRESS_NAME);
        registerPage.chooseCityProvince(CityProvince.BINHDUONG);
        registerPage.chooseDistrict(3);
        registerPage.chooseWard(2);
        registerPage.clickComplete();
        registerPage.clickStart();

        mailinator.gotoMailPlatform();
        mailinator.enterMail(email);
        mailinator.clickMail(DataTest.MAIL_TITLE);
        String password = mailinator.getRegisterPassword();

        adminPage().navigateToLoginPage();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.verifyActiveButton();
    }
}