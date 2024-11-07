package com.fnb.web.pos.pages.component;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class MainContentColumn extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;

    public MainContentColumn(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By btnSwiperNext = By.xpath("//div[@class='swiper-button-next']");
    private By btnSwiperPrevious = By.xpath("//div[@class='swiper-button-prev']");
    private By successDialog = By.xpath("(//div[@class='ant-notification-notice-with-icon'])");
    private By textCustomerInput = By.xpath("//span[contains(@class, 'search')]//input");
    private By iconSearch = By.xpath("//div[@class='search-btn']");

    public MainContentColumn clickCategory(String categoryName) {
        By categoryEle = By.xpath("//span[normalize-space()='"+categoryName+"']");
        while (!helper.isElementVisible(categoryEle)) {
            helper.clickElement(btnSwiperNext, "It looks like there are no category. Please check");
        }
        helper.clickElement(categoryEle);
        return this;
    }

    public MainContentColumn clickAddToCard(String productName) {
        By btnAddToCardEle = By.xpath("//h3[normalize-space()='"+productName+"']//ancestor::div[contains(@class, 'product-card-dashboard-wrapper')]//button");
        helper.clickElement(btnAddToCardEle);
        helper.waitForElementVisible(successDialog);
        helper.waitElementIsRemoved(successDialog);
        return this;
    }

    public MainContentColumn selectCustomerByCustomerPhone(String customerName, String customerPhone) {
        helper.enterText(textCustomerInput, customerName);
        helper.sleep(2);
        helper.clickElement(By.xpath("//*[contains(text(),'"+customerPhone+"')]"));
        helper.smartWait();
        return this;
    }

    public ProductCartDetail clickProduct(String productName) {
        // Click product card
        helper.clickElement(By.xpath("//div[@class='product-card-dashboard' and .//*[text()='"+productName+"']]"));
        return new ProductCartDetail(driver);
    }

    public ProductCartDetail searchAndSelectProduct(String productName) {
        helper.clickElement(iconSearch);
        // Enter the product name
        helper.enterText(By.xpath("//*[contains(@class, 'order-content')]//input[contains(@class, 'search-input')]"), productName);
        // Click product search result
        helper.clickElement(By.xpath("//*[contains(@class, 'list-search-product')]//*[text()='"+productName+"']"));
        return new ProductCartDetail(driver);
    }
}
