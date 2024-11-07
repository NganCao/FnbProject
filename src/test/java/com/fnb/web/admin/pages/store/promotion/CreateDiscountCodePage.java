package com.fnb.web.admin.pages.store.promotion;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CreateDiscountCodePage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public CreateDiscountCodePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By txtName = By.xpath("//input[@id='discountCode_name']");
    private By selSearchApplicableType = By.xpath("//*[text()='"+adminLocalization.getPromotion().getApplicableType()+"']/following::input[1]");
    private By txtDiscountValue = By.xpath("//input[@id='discountPercent']");
    private By txtMaxDiscount = By.xpath("//input[@id='maximumDiscountAmount']");
    private By txtStartDate = By.xpath("//input[@id='discountCode_startDate']");
    private By txtStartTime = By.xpath("//input[@id='discountCode_startTime']");
    private By txtEndDate = By.xpath("//input[@id='discountCode_endDate']");
    private By txtEndTime = By.xpath("//input[@id='discountCode_endTime']");
    private By btnGenerateCode = By.xpath("//span[normalize-space()='"+adminLocalization.getDiscountCode().getFormCreate().getGenerateCodes()+"']");

    public void enterName(String name) {
        helper.enterText(txtName, name);
    }

    public enum ApplicableType {
        Discount_on_total_bill(adminLocalization.getPromotion().getDiscount().getTotal()),
        Discount_on_specific_product(adminLocalization.getPromotion().getDiscount().getProduct()),
        Discount_on_specific_product_category(adminLocalization.getPromotion().getDiscount().getProductCategory());

        private final String type;

        ApplicableType(String type) {
            this.type = type;
        }

        public String getApplicableType() {
            return type;
        }
    }

    public void selectApplicableType(ApplicableType applicableType) {
        helper.enterText(selSearchApplicableType, applicableType.getApplicableType());
        helper.clickElement(By.xpath("//div[@name='" + applicableType.getApplicableType() + "']"));
    }

    public void enterDiscountValue(String discountValue) {
        helper.enterText(txtDiscountValue, discountValue);
    }

    public void enterMaxDiscount(String maxDiscount) {
        helper.enterText(txtMaxDiscount, maxDiscount);
    }

    public void selectStartDate(String startDate) {
        helper.enterText(txtStartDate, startDate);
        helper.getWebElement(txtStartDate).sendKeys(Keys.ENTER);
    }

    public void selectStartTime(String startTime) {
        helper.enterText(txtStartTime, startTime);
        helper.getWebElement(txtStartTime).sendKeys(Keys.ENTER);
    }

    public void selectEndDate(String endDate) {
        helper.enterText(txtEndDate, endDate);
        helper.getWebElement(txtEndDate).sendKeys(Keys.ENTER);
    }

    public void selectEndTime(String endTime) {
        helper.enterText(txtEndTime, endTime);
        helper.getWebElement(txtEndTime).sendKeys(Keys.ENTER);
    }

    public void selectCouponConditions() {

    }

    public void clickGenerateCode() {
        helper.scrollToElementAtTop(btnGenerateCode);
        helper.clickElement(btnGenerateCode);
        wait.until(driver -> {
            WebElement element = driver.findElement(By.id("discountCode_code"));
            String value = element.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                return element;
            }
            return null;
        });
    }

    public void clickSave(){
        helper.clickElement(commonComponents.getBtnSave());
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }

    public String createDiscountCode(String name, ApplicableType applicableType, String discountValue, String maxDiscount, String startDate, String startTime, String endDate, String endTime) {
        enterName(name);
        selectApplicableType(applicableType);
        enterDiscountValue(discountValue);
        enterMaxDiscount(maxDiscount);
        selectStartDate(startDate);
        selectStartTime(startTime);
        selectEndDate(endDate);
        selectEndTime(endTime);
        helper.scrollToElementAtTop(btnGenerateCode);
        clickGenerateCode();
        helper.scrollToElementAtBottom(commonComponents.getBtnSave());
        clickSave();
        return name;
    }
}
