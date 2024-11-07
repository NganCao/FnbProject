package com.fnb.web.admin.pages.store.promotion;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class PromotionManagementPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;

    public PromotionManagementPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    private By tabDiscountCampaign = By.xpath("//*[text()='"+adminLocalization.getPromotion().getDiscount().getTabTitle()+"']");
    private By tabFlashSaleCampaign = By.xpath("//*[text()='"+adminLocalization.getPromotion().getFlashSale().getTabTitle()+"']");
    private By tabDiscountCode = By.xpath("//*[text()='"+adminLocalization.getDiscountCode().getTitle()+"']");
    private By btnSearchIconDiscount = By.xpath("//div[@id='fnb-wrapper-tab-pane-panel-discount']//*[@class='search-bar']");

    public void clickDiscountCampaignTab() {
        helper.clickElement(tabDiscountCampaign);
    }

    public void clickFlashSaleCampaignTab() {
        helper.clickElement(tabFlashSaleCampaign);
    }

    public void clickDiscountCodeTab() {
        helper.clickElement(tabDiscountCode);
    }

    public CreateDiscountCampaignPage clickAddNewAutomaticDiscounts() {
        CreateDiscountCampaignPage createDiscountCampaignPage = new CreateDiscountCampaignPage(driver);
        helper.clickElement(commonComponents.getBtnAddNew());
        return createDiscountCampaignPage;
    }

    public CreateFlashSaleCampaignPage clickAddNewFlashSale() {
        CreateFlashSaleCampaignPage createFlashSaleCampaignPage = new CreateFlashSaleCampaignPage(driver);
        helper.sleep(2);
        helper.getTheVisibleElement(commonComponents.getBtnAddNew()).click();
        helper.waitForElementVisible(commonComponents.getBtnSave());
        return createFlashSaleCampaignPage;
    }

    public CreateDiscountCodePage clickPromoCodes() {
        CreateDiscountCodePage createDiscountCodePage = new CreateDiscountCodePage(driver);
        helper.sleep(2);
        helper.getTheVisibleElement(commonComponents.getBtnAddNew()).click();
        return createDiscountCodePage;
    }

    public PromotionManagementPage searchAPromotion(String promotionName) {
        commonComponents.searchItem(promotionName);
        return this;
    }

    public PromotionManagementPage selectPromotion(String promotionName) {
        commonComponents.selectItem(promotionName);
        return this;
    }

}
