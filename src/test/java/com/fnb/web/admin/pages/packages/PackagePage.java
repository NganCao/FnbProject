package com.fnb.web.admin.pages.packages;

import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.register.RegisterPage;
import com.fnb.web.setup.Setup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.testng.Assert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class PackagePage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public PackagePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private By itemProcess = By.xpath("//div[@class='fnb-steps-item fnb-steps-item-process']");
    private By btnContinue = By.xpath("//button[@type='button']");
    private By optionBankTranfer = By.xpath("//label[contains(@class, 'ant-radio-wrapper')]");
    private By btnProcessToPayment = By.xpath("//button[@type='button']");
    private By btnBackToDashboard = By.xpath("//button[@type='button']");

    public void verifyStatusProgress(String expectedText) {
        helper.waitTextToBePresent(itemProcess, expectedText);
        Assert.assertEquals(helper.getText(itemProcess), expectedText);
    }

    public void clickBackToDashboard() {
        helper.scrollToElementAtTop(btnBackToDashboard);
        helper.clickJS(btnBackToDashboard);
    }

    public void choosePaymentDuration(PaymentDuration paymentDuration, Platform platform) {
        By year = By.xpath("//p[normalize-space()='" + paymentDuration.getDurationTime() + "']//preceding::span[@class='ant-radio']");
        By option = By.xpath("//strong[normalize-space()='"+platform.getPlatformName()+"']/ancestor::div[@class='package-option']//div[contains(@class, 'hover')]");
        helper.clickElement(year);
        switch (platform.getPlatformName()) {
            case "POS":
                helper.scrollToElementAtTop(btnContinue);
                helper.clickJS(btnContinue);
                break;

            case "WEB":

            case "APP":
                helper.clickElement(option);
                helper.scrollToElementAtTop(btnContinue);
                helper.clickJS(btnContinue);
                break;

            default:
                throw new IllegalArgumentException("Invalid option.");
        }
    }

    public void proceedPayment() {
        helper.scrollToElementAtTop(optionBankTranfer);
        helper.clickJS(optionBankTranfer);
        helper.waitForElementToBeEnabled(btnProcessToPayment);
        helper.clickJS(btnProcessToPayment);
        By sucessfullText = By.xpath("//div[@class='message']");
        helper.waitForElementVisible(sucessfullText);
        helper.verifyContains(helper.getText(sucessfullText), "You have registered", "Not match, please check");
    }

    public String getOrderPackageId() {
        By orderId = By.xpath("//p[normalize-space()='ORDER CODE:']//ancestor::li//p[@class='value']");
        return helper.getText(orderId);
    }

    private static String getToken() {
        String baseUrl = configObject.getApiEnv();
        String apiUrl = baseUrl + "/login/internal-tool";

        String requestBody = "{ \"userName\": \"gofnb_internaltool\", \"password\": \"GoFnBInt3rnalT00l@2022\" }";

        // Send a POST request and capture the response
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(apiUrl);

        // Retrieve and assert the status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        // Extract and print the token from the response body
        String token = response.jsonPath().getString("token");
        return token;
    }

    private static void callSecondApi(String token, String orderPackageId) {
        String baseUrl = configObject.getApiEnv();
        String apiUrl = baseUrl + "/order-packages/action";

        String requestBody = String.format("{ \"orderPackageId\": %s, \"action\": \"approve\", " +
                "\"contractId\": \"2221\", \"note\":\"approve package from internal tool\", " +
                "\"editor\": \"phuong.hoang.nguyen@mediastep.com\" }", orderPackageId);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(apiUrl);

        // Perform assertions or further processing based on the second API response
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public void approveThePackage(String orderPackageId) {
        callSecondApi(getToken(), orderPackageId);
    }

    public RegisterPage.InforAccount endToEndRegisterAccountAndThePackage(PaymentDuration paymentDuration, Platform platform) {
        // Create an account
        helper.navigateToUrl(configObject.getRegister());
        RegisterPage.InforAccount inforAccount = registerPage.registerAnAccount();

        // Login and choose package
        loginPage.clickActiveAccount();
        verifyStatusProgress(DataTest.CHOOSE_PACKAGE_STATUS);
        choosePaymentDuration(paymentDuration, platform);
        verifyStatusProgress(DataTest.PAYMENT_STATUS);
        proceedPayment();
        approveThePackage(getOrderPackageId());

        // need to delete
        System.out.println(inforAccount.getEmail());
        System.out.println(inforAccount.getPassword());
        return inforAccount;
    }

    public String getSubScriptionDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Format the future date
        String currentFormatDate = currentDate.format(formatter);

        return currentFormatDate;
    }

    public String extendPackageSubScriptionDate(String inputDate, int yearsToAdd) {

        // Define the date format of the input string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parse the input string to LocalDate
        LocalDate date = LocalDate.parse(inputDate, formatter);

        // Add the specified number of years
        LocalDate newDate = date.plusYears(yearsToAdd);

        // Format the new date as a string
        String result = newDate.format(formatter);

        return result;
    }
}
