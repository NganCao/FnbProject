package com.fnb.web.admin.pages.register;

import com.fnb.utils.helpers.Log;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.mail.Mailinator;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegisterPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public RegisterPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        loginPage = new LoginPage(driver);
        mailinator = new Mailinator(driver);
        adminPages = new PagesAdminSetup(driver);
    }

    LoginPage loginPage;
    Mailinator mailinator;
    public PagesAdminSetup adminPages;
    private By header = By.xpath("//span[contains(@class, 'bold')]");
    // Element for Store Information
    private By txtStoreName = By.xpath("//input[@id='storeName']");
    private By lblStoreName = By.xpath("//input[@id='storeName']//ancestor::div[contains(@class,'ant-form-item-has-error')]//div[@class='ant-form-item-explain-error']");
    private By lblBusinessArea = By.xpath("//div[@class='ant-form-item ant-form-item-with-help ant-form-item-has-error']//div[@id='businessAreaId_help']");
    private By btnNext = By.xpath("(//button)[2]");
    private By cboBusinessArea = By.xpath("//*[text()='Business area']/following::input[1]");
    private By cboCountry = By.xpath("//input[@id='countryId']");
    private By cboCityProvince = By.xpath("//*[text()='City/ Province']/following::input[1]");
    private By cboCurrency = By.xpath("(//div[contains(@class, 'ant-col') and .//*[text()='Currency'] and not(contains(@class, 'label'))])[last()]//span[@class='ant-select-selection-item']");
    private By optionCboRestaurant = By.xpath("//div[@title='Restaurant']");
    private By optionCboBoth = By.xpath("//*[text()='Both']");
    private By optionCboCoffeeShop = By.xpath("//div[@title='Coffee shop']");
    // Element for "Your Information"
    private By txtFullName = By.xpath("//input[@id='fullName']");
    private By lblFullName = By.xpath("//div[contains(@class, 'has-error') and not(contains(@class, 'hidden'))]//div[@id='fullName_help']");
    private By txtPhoneNumber = By.xpath("//input[@id='phoneNumber']");
    private By lblPhoneNumber = By.xpath("//div[contains(@class, 'has-error') and not(contains(@class, 'hidden'))]//div[@id='phoneNumber_help']");
    private By txtEmail = By.xpath("//input[@id='email']");
    private By lblEmail = By.xpath("//div[contains(@class, 'has-error') and not(contains(@class, 'hidden'))]//div[@id='email_help']");
    // Elements for "Your Address"
    private By btnComplete = By.xpath("//button[@type='submit']");
    private By lblAddress = By.xpath("//div[contains(@class, 'has-error') and not(contains(@class, 'hidden'))]//div[@id='address1_help']");
    private By lblCityProvince = By.xpath("//div[contains(@class, 'has-error') and not(contains(@class, 'hidden'))]//div[@id='cityId_help']");
    private By lblDistrict = By.xpath("//div[contains(@class, 'has-error') and not(contains(@class, 'hidden'))]//div[@id='districtId_help']");
    private By lblWard = By.xpath("//div[contains(@class, 'has-error') and not(contains(@class, 'hidden'))]//div[@id='wardId_help']");
    private By cboDistrict = By.xpath("//input[@id='districtId']");
    private By cboWard = By.xpath("//input[@id='wardId']");
    private By txtAddress = By.xpath("//input[@id='address1']");
    private By btnStart = By.xpath("//button[@type='button']");

    public void navigateToYourInformation() {
        enterStoreName(generateStoreName());
        chooseCountry(Country.VIETNAM);
        chooseBusinessArea(BusinessArea.BOTH);
        clickNextButton();
        verifyHeaderDisplay(DataTest.YOUR_INFORMATION_HEADER);
    }

    public void navigateToYourAddress() {
        enterStoreName(generateStoreName());
        chooseCountry(Country.VIETNAM);
        chooseBusinessArea(BusinessArea.BOTH);
        clickNextButton();
        verifyHeaderDisplay(DataTest.YOUR_INFORMATION_HEADER);
        enterFullName(generateFullName());
        enterPhoneNumber(generatePhoneNumber());
        enterEmail(generateEmail());
        clickNextButton();
        verifyHeaderDisplay(DataTest.YOUR_ADDRESS_HEADER);
    }
    public void verifyHeaderDisplay(String expectedText) {
        helper.waitTextToBePresent(header, expectedText);
        helper.getText(header);
    }

    public void clickNextButton() {
        helper.clickElement(btnNext);
    }

    public void clickComplete() {
        helper.clickElement(btnComplete);
    }

    public void clickStart() {
        helper.clickElement(btnStart);
    }

    private By textNodata = By.xpath("//div[normalize-space()='No data' and @class='ant-empty-description']");

    public void verifyNoDistrictData() {
        helper.clickElement(cboDistrict);
        helper.waitForElementVisible(textNodata);
    }

    public void verifyNoWardData() {
        helper.clickElement(cboWard);
        helper.waitForElementVisible(textNodata);
    }

    public void verifyFullNameErrorMessage(String expectedText) {
        helper.waitTextToBePresent(lblFullName, expectedText);
        Assert.assertEquals(helper.getWebElement(lblFullName).getText(), expectedText);
    }

    public void verifyPhoneErrorMessage(String expectedText) {
        helper.waitTextToBePresent(lblPhoneNumber, expectedText);
        Assert.assertEquals(helper.getWebElement(lblPhoneNumber).getText(), expectedText);
    }

    public void verifyEmailErrorMessage(String expectedText) {
        helper.waitTextToBePresent(lblEmail, expectedText);
        Assert.assertEquals(helper.getWebElement(lblEmail).getText(), expectedText);
    }

    public void verifyWardErrorMessage(String expectedText) {
        helper.waitTextToBePresent(lblWard, expectedText);
        Assert.assertEquals(helper.getWebElement(lblWard).getText(), expectedText);
    }

    public void verifyAddressErrorMessage(String expectedText) {
        helper.waitTextToBePresent(lblAddress, expectedText);
        Assert.assertEquals(helper.getWebElement(lblAddress).getText(), expectedText);
    }

    public void verifyCityProvincErrorMessage(String expectedText) {
        helper.waitTextToBePresent(lblCityProvince, expectedText);
        Assert.assertEquals(helper.getWebElement(lblCityProvince).getText(), expectedText);
    }

    public void verifyDistrictErrorMessage(String expectedText) {
        helper.waitTextToBePresent(lblDistrict, expectedText);
        Assert.assertEquals(helper.getWebElement(lblDistrict).getText(), expectedText);
    }

    public String getStoreNameErrorMessage() {
        return helper.waitForElementVisible(lblStoreName).getText();
    }

    public String getBusinessAreaErrorMessage() {
        return helper.waitForElementVisible(lblBusinessArea).getText();
    }

    public void enterStoreName(String name) {
        helper.enterText(txtStoreName, name);
    }

    public void enterPhoneNumber(String phone) {
        helper.enterText(txtPhoneNumber, phone);
    }

    public void enterFullName(String name) {
        helper.enterText(txtFullName, name);
    }

    public void enterEmail(String email) {
        helper.enterText(txtEmail, email);
    }

    public void enterAddress(String address) {
        helper.enterText(txtAddress, address);
    }

    public void chooseBusinessArea(BusinessArea option) {
        switch (option) {

            case RESTAURANT:
                helper.clickElement(cboBusinessArea);
                helper.clickElement(optionCboRestaurant);
                break;

            case COFFEE_SHOP:
                helper.clickElement(cboBusinessArea);
                helper.clickElement(optionCboCoffeeShop);
                break;

            case BOTH:
                helper.clickElement(cboBusinessArea);
                helper.clickElement(optionCboBoth);
                break;

            default:
                throw new IllegalArgumentException("Invalid option. Please choose 'Restaurant', 'Coffee Shop', or 'Both'.");
        }
    }

    public String generatePhoneNumber() {
        Random random = new Random();

        // Generate a random number between 0 and 9 for the second digit
        int secondDigit = random.nextInt(10);
        String secondStringDigit = Integer.toString(secondDigit);

        // Concatenate 0 with the random second digit
        int randomNumber = Integer.parseInt("01" + secondDigit);
        String theFirstTwoDigit = "01" + secondStringDigit;

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("mmssMs");
        String datetime = ft.format(dNow);
        return theFirstTwoDigit + datetime;
    }

    public String generateStoreName() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("ddhhmmssMs");
        String datetime = ft.format(dNow);
        return "StoreName" + datetime;
    }

    public String generateFullName() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("ddhhmmssMs");
        String datetime = ft.format(dNow);
        return "FullName" + datetime;
    }

    public String generateEmail() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("ddhhmmssMs");
        String datetime = ft.format(dNow);
        return "Email" + datetime + "@mailinator.com";
    }

    public String getCountryValue() {
        return helper.waitForElementVisible(By.xpath("//input[@id='countryId']/following::div[contains(@class, 'content')][1]")).getText();
    }

    public String getCityProvincePlaceholder() {
        By selectionCityProvince = By.xpath("//input[@id='cityId']/following::span[contains(@class, 'placeholder')][1]");
        return helper.waitForElementVisible(selectionCityProvince).getText();
    }

    public String getDistrictPlaceholder() {
        By selectionDistrict = By.xpath("//input[@id='districtId']/following::span[contains(@class, 'placeholder')][1]");
        return helper.waitForElementVisible(selectionDistrict).getText();
    }

    public String getWardValue() {
        By selectionWard = By.xpath("//input[@id='wardId']/following::span[contains(@class, 'placeholder')][1]");
        return helper.waitForElementVisible(selectionWard).getText();
    }

    public String getCurrencyValue() {
        return helper.waitForElementVisible(cboCurrency).getText();
    }

    public void chooseCountry(Country country) {
        String countryName = country.getDisplayName();
        helper.enterText(cboCountry, countryName);
        helper.getTheVisibleElement("//*[text()='"+country.getDisplayName()+"']").click();
        Assert.assertEquals(getCountryValue(), countryName);
    }

    public void chooseCityProvince(CityProvince cityProvince) {
        String cityProvinceName = cityProvince.getCityProvinceName();
        helper.enterText(cboCityProvince, cityProvinceName);
        helper.getTheVisibleElement("//*[text()='"+cityProvinceName+"']").click();
        helper.waitForElementVisible(By.xpath("//input[@id='cityId']/following::*[text()='"+cityProvince.getCityProvinceName()+"'][1]"));
    }

    public void chooseDistrict(Integer index) {
        helper.clickElement(By.xpath("//input[@id='districtId']/ancestor::div[1]"));
        By cityProvinceNameLocator = By.xpath("(((//div[contains(@class, 'ant-col') and .//*[text()='District'] and not(contains(@class, 'label'))])[last()]//div[contains(@class, 'rc-virtual-list')])[1]//div[@name])["+index+"]");
        helper.waitForElementVisible(cityProvinceNameLocator).click();
    }

    public void chooseWard(Integer index) {
        helper.clickElement(By.xpath("//input[@id='wardId']/ancestor::div[1]"));
        By wardNameLocator = By.xpath("(((//div[contains(@class, 'ant-col') and .//*[text()='Ward'] and not(contains(@class, 'label'))])[last()]//div[contains(@class, 'rc-virtual-list')])[1]//div[@name])["+index+"]");
        helper.waitForElementVisible(wardNameLocator).click();
    }

    public void verifyCountryCurrencyMatcher(Country country) {
        switch (country.getDisplayName()) {
            case "Viet Nam":
                Assert.assertEquals(getCurrencyValue(), "VND");
                break;

            case "United States":
                Assert.assertEquals(getCurrencyValue(), "USD");
                break;

            case "Cuba":
                Assert.assertEquals(getCurrencyValue(), "CUP");
                break;

            case "Japan":
                Assert.assertEquals(getCurrencyValue(), "JPY");
                break;

            default:
                throw new IllegalArgumentException("Invalid option. Please choose valid country name.");
        }
    }

    public void verifyIfThereAreDistrictData() {
        By data = By.xpath("((//div[contains(@class, 'ant-col') and .//*[text()='District'] and not(contains(@class, 'label'))])[last()]//div[contains(@class, 'rc-virtual-list')])[1]//div[@name]");
        helper.clickElement(cboDistrict);
        helper.waitForElementVisible(data);
        List<WebElement> dataElement = driver.findElements(data);
        int totalCount = dataElement.size();
        if(totalCount >= 1) {
            Log.info("There are data in District");
        }
        else {
            Assert.fail("There are no district data");
        }
    }

    public void verifyIfThereAreWardData() {
        By data = By.xpath("((//div[contains(@class, 'ant-col') and .//*[text()='Ward'] and not(contains(@class, 'label'))])[last()]//div[contains(@class, 'rc-virtual-list')])[1]//div[@name]");
        helper.clickElement(cboWard);
        helper.waitForElementVisible(data);
        List<WebElement> dataElement = driver.findElements(data);
        int totalCount = dataElement.size();
        if(totalCount >= 1) {
            Log.info("There are data in Ward");
        }
        else {
            Assert.fail("There are no Ward data");
        }
    }

    public static class InforAccount {
        private String email;
        private String password;

        private String expiryDate;

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public InforAccount registerAnAccount() {
        String storeName = generateStoreName();
        String fullName = generateFullName();
        String phoneNumber = generatePhoneNumber();
        String email = generateEmail();
        InforAccount inforAccount = new InforAccount();

        enterStoreName(storeName);
        chooseCountry(Country.VIETNAM);
        chooseBusinessArea(BusinessArea.BOTH);
        clickNextButton();
        verifyHeaderDisplay(DataTest.YOUR_INFORMATION_HEADER);
        enterFullName(fullName);
        enterPhoneNumber(phoneNumber);
        enterEmail(email);
        clickNextButton();
        verifyHeaderDisplay(DataTest.YOUR_ADDRESS_HEADER);
        enterAddress(DataTest.ADDRESS_NAME);
        chooseCityProvince(CityProvince.BINHDUONG);
        chooseDistrict(2);
        chooseWard(2);
        clickComplete();
        clickStart();

        mailinator.gotoMailPlatform();
        mailinator.enterMail(email);
        mailinator.clickMail(DataTest.MAIL_TITLE);
        String password = mailinator.getRegisterPassword();

        Map<String, String> result = new HashMap<>();
        inforAccount.setEmail(email);
        inforAccount.setPassword(password);

        adminPages.navigateToLoginPage();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.verifyActiveButton();

        return inforAccount;
    }
}
