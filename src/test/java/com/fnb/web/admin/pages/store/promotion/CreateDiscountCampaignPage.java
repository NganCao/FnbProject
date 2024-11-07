package com.fnb.web.admin.pages.store.promotion;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CreateDiscountCampaignPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public CreateDiscountCampaignPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private By txtName = By.xpath("//input[@id='promotion_name']");
    private By selSearchApplicableType = By.xpath("//input[contains(@class, 'selection-search')]");
    private By txtDiscountValue = By.xpath("//input[@id='discountPercent']");
    private By txtMaxDiscount = By.xpath("//input[@id='maximumDiscountAmount']");
    private By txtStartDate = By.xpath("//input[@id='promotion_startDate']");
    private By txtEndDate = By.xpath("//input[@id='promotion_endDate']");

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

    public void selectEndDate(String endDate) {
        helper.enterText(txtEndDate, endDate);
        helper.getWebElement(txtEndDate).sendKeys(Keys.ENTER);
    }

    public void enterTermsAndConditions(String termsAndConditions) {

    }
    public void selectCouponConditions() {

    }

    public void clickSave() {
        helper.scrollToElementAtBottom(commonComponents.getBtnSave());
        helper.clickElement(commonComponents.getBtnSave());
        commonComponents.waitSuccessToast();
        commonComponents.waitSuccessToastHidden();
    }

    public String createDiscountCampaign(String name, ApplicableType applicableType, String discountValue, String maxDiscount, String startDate, String endDate) {
        enterName(name);
        selectApplicableType(applicableType);
        enterDiscountValue(discountValue);
        enterMaxDiscount(maxDiscount);
        selectStartDate(startDate);
        selectEndDate(endDate);
        clickSave();
        return name;
    }
}
