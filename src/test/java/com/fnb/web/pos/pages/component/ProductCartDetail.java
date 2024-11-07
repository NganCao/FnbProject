package com.fnb.web.pos.pages.component;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ProductCartDetail extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public ProductCartDetail(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private By btnPlusIcon = By.xpath("(//div[@class='btn-plus'])[1]");
    private By btnMinusIcon = By.xpath("(//div[@class='btn-minus'])[1]");
    private By btnPlusIconTopping = By.xpath("//button[@class='btn-topping plus plus-custom']//*[name()='svg']");
    private By btnCloseIcon = By.xpath("(//*[name()='svg' and @class='close-icon'])[1]");

    public ProductCartDetail clickPlusBtn() {
        helper.clickElement(btnPlusIcon);
        return this;
    }

    public ProductCartDetail increaseQuantityProduct(Integer quantity) {
        quantity = quantity - 1;
        for (int i=0; i < quantity; i++) {
            clickPlusBtn();
            helper.smartWait();
        }
        return this;
    }

    public ProductCartDetail clickMinusBtn() {
        helper.clickElement(btnMinusIcon);
        return this;
    }

    public ProductCartDetail selectOption(String optionName, String optionLevel) {
        // Scroll the OPTION header to top
        helper.scrollToElementAtTop(By.xpath("(//*[contains(@class, 'header') and .//*[text()='"+posLocalization.getProductDetail().getOption()+"']])[1]"));

        // Click the level option
        helper.clickElement(By.xpath("//div[@class='option-item' and .//*[text()='"+optionName+"']]//*[text()='"+optionLevel+"']"));
        return this;
    }

    public ProductCartDetail clickPlusBtnIconTopping() {
        // Scroll the TOPPING header to top
        helper.scrollToElementAtTop(By.xpath("(//*[contains(@class, 'header') and .//*[text()='"+posLocalization.getProductDetail().getTopping()+"']])[1]"));

        helper.clickElement(btnPlusIconTopping);
        return this;
    }

    public void clickBtnCloseIcon() {
        helper.clickElement(btnCloseIcon);
    }

    public ProductCartDetail selectPrice(String priceName) {
        // Click price
        helper.clickElement(By.xpath("//div[contains(@class, 'product-price') and .//*[text()='"+priceName+"']]"));
        return this;
    }

    private By btnAdd = By.xpath("//button[@class='btn-add-product']");
    public void clickAddToCart() {
        helper.clickElement(btnAdd);
        By dialogSuccess = By.xpath("//*[text()='"+posLocalization.getToastMessage().getProductAddToCartSuccess()+"']");
        // Wait the success dialog, informing that the product is added
        helper.waitForElementVisible(dialogSuccess);
        // Wait the success dialog to disappear
        helper.waitElementIsRemoved(dialogSuccess);
        helper.smartWait();
    }
}
