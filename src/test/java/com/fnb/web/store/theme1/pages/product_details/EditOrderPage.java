package com.fnb.web.store.theme1.pages.product_details;

import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.flashSale.FlashSalePage;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EditOrderPage extends Setup {

    private static WebDriver driver;
    public Helper helper;
    public WebDriverWait wait;
    private JsonReader jsonReader;
    private HomeDataTest homeDataTest;
    private ProductDetailsDataTest productDetailsDatatest;
    private FlashSalePage flashSalePage;
    public Actions actions;
    public String actualRS = "";
    //locators
    private String editCartDetailLocator = "//div[contains(@class,\"edit-product-cart-detail-container\")]//div[@id=\"popup-choose-options\"]";
    private By editCartPrice = By.xpath(editCartDetailLocator + "//span[contains(@class,\"product-price\")]");
    private By editCartModifyQuantity = By.xpath(editCartDetailLocator + "//div[@class=\"modify-quantity\"]");
    private By editCartIncreaseBtn = By.xpath(editCartDetailLocator + "//div[@class=\"modify-quantity\"]/button[contains(@class,\"btn-increase\")]");
    private By editCartReduceBtn = By.xpath(editCartDetailLocator + "//div[@class=\"modify-quantity\"]/button[contains(@class,\"btn-reduce\")]");
    private By editCartQuantity = By.xpath(editCartDetailLocator + "//div[@class=\"modify-quantity\"]/span");
    private By editCartContainer = By.xpath("//div[contains(@class,\"edit-product-cart-detail-container\")]");
    private By editCartProductFlashSale = By.xpath(editCartDetailLocator + "//div[contains(@class,\"flash-sale-banner \")]");
    private By editCartFlashSaleLogo = By.xpath(editCartDetailLocator + "//div[contains(@class,\"flash-sale-banner-logo\")]//*[name()=\"svg\"]");
    private By editCartFlashSaleTitle = By.xpath(editCartDetailLocator + "//div[contains(@class,\"flash-sale-banner-title\")]");
    private By editCartFlashSaleFlipCountdown = By.xpath(editCartDetailLocator + "//div[contains(@class,\"flash-sale-banner\")]//div[contains(@class,\"flip-countdown\")]"); //6 numbers
    private By editCartFlashSaleFlipCountdownZero = By.xpath(editCartDetailLocator + "//div[contains(@class,\"flash-sale-zero\")]");
    private By editCartFlashSaleFlipCountdownNumbers = By.xpath(editCartDetailLocator + "//div[contains(@class,\"flash-sale-banner\")]//div[contains(@class,\"flip-countdown\")]/div/div"); //6 numbers
    private By editCartFlashSaleDiscountPercent = By.xpath("//div[contains(@class,\"edit-product-cart-detail-container\")]//div[contains(@class,\"promotional-product\")]/span");
    private By editCartOriginalPrice = By.xpath(editCartDetailLocator + "//span[contains(@class,\"product-original-price\")]");
    private By editCartButtonPrice = By.xpath(editCartDetailLocator + "//div[contains(@class,\"btn-add-to-cart-price\")]");
    private By editCartButtonOriginalPrice = By.xpath(editCartDetailLocator + "//div[contains(@class,\"btn-add-to-cart-original-price\")]");
    private By updateButton = By.xpath(editCartDetailLocator + "//button[contains(@class,\"btn-submit\")]");
    //Maximum limit notification
    private By maximumLimitNotify = By.xpath(editCartDetailLocator + "//div[@class=\"maximum-limit-flash-sale-notify\"]");
    private By maximumLimitIcon = By.xpath(editCartDetailLocator + "//div[@class=\"maximum-limit-flash-sale-notify\"]/*[name()=\"svg\"]");
    private By maximumLimitText = By.xpath(editCartDetailLocator + "//div[@class=\"maximum-limit-flash-sale-notify\"]/span");

    public EditOrderPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        flashSalePage = new FlashSalePage(driver);
    }

    public Boolean checkDisplayOfEditCart() {
        return helper.checkDisplayElement(editCartContainer);
    }

    public Boolean checkDisplayOfMaximumLimitNotify() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        if (helper.checkDisplayElement(maximumLimitNotify)) {
            if (checkDisplayOfMaximumLimitIcon()) flag.add(true);
            else {
                actualRS = actualRS + "Maximum Limit Icon did not display\n";
                flag.add(false);
            }
            if (checkDisplayOfMaximumLimitText()) flag.add(true);
            else {
                actualRS = actualRS + "Maximum Limit Icon did not display\n";
                flag.add(false);
            }
        } else {
            actualRS = actualRS + "Maximum Limit notify did not display\n";
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    private Boolean checkDisplayOfMaximumLimitText() {
        return helper.checkDisplayElement(maximumLimitText);
    }

    private Boolean checkDisplayOfMaximumLimitIcon() {
        return helper.checkDisplayElement(maximumLimitIcon);
    }

    public Boolean checkContentOfMaximumLimitNotify(String currentLanguage, int maximumLimit) {
        String expected = jsonReader.localeReader(currentLanguage, productDetailsDatatest.MAXIMUM_LIMIT_NOTIFY_PAGE, null);
        String replaced = expected.replace("{{maximumLimit}}", String.valueOf(maximumLimit));
        if (checkDisplayOfMaximumLimitText()) {
            try {
                if (helper.getAttribute(maximumLimitText, "innerHTML").equals(replaced)) return true;
                else {
                    actualRS = "Notify is incorrect. Actual: " + helper.getText(maximumLimitText) + " Expected: " + replaced;
                    return false;
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                actualRS = "Edit order dialog was blink";
                return false;
            }
        } else {
            actualRS = "Notify is incorrect. Actual: " + helper.getText(maximumLimitText) + " Expected: " + replaced;
            return false;
        }
    }

    //flash sale
    public Boolean checkDisplayOfFlashSaleSection() {
        return helper.checkDisplayElement(editCartProductFlashSale);
    }

    public Boolean checkDisplayWhenFlashSale() {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (checkDisplayOfFlashSaleSection()) {
            flag.add(true);
            if (helper.checkDisplayElement(editCartFlashSaleLogo)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + " Flash sale logo did not display.\n";
            }
            if (helper.checkDisplayElement(editCartFlashSaleTitle)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + " Flash sale title did not display.\n";
            }
            if (helper.checkDisplayElement(editCartFlashSaleFlipCountdown)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + " Flash sale count down did not display.\n";
            }
        } else {
            flag.add(false);
            actualRS = actualRS + " Flash sale section did not display.\n";
        }
        if (flag.contains(false)) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkFlipCounterWhenFlashSale() {
        return helper.checkDisplayElement(editCartFlashSaleFlipCountdown);
    }

    public Boolean checkFlipCounterWhenFlashSaleEnded() {
        return helper.checkDisplayElement(editCartFlashSaleFlipCountdownZero);
    }

    public Boolean checkPriceFlashSale(String flashSaleKey, String productName, Boolean isFlashSale, int cartQuantity, List<Integer> toppingTotalPrice, String sizeName) {
        List<Boolean> flag = new ArrayList<>();
        int scroll = 0;
        JsonAPIAdminReader.FlashSaleProduct flashSaleProduct = flashSalePage.getFlashSaleProductObject(flashSaleKey, productName);
        actualRS = "";
        helper.waitForPresence(editCartProductFlashSale);
        helper.visibleOfLocated(editCartProductFlashSale);
        if (isFlashSale) {
            String price = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
            String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
            String priceBtn = helper.formatCurrencyToThousand(Math.round(flashSaleProduct.getPrice()) * cartQuantity + toppingTotalPrice.get(1)); //price on button
            String sellingPrice = helper.formatCurrencyToThousand(Math.round(flashSaleProduct.getFlashSalePrice()) * cartQuantity + toppingTotalPrice.get(0)); //price on button
            if (checkDisplayOfFlashSaleSection()) {
                if (helper.checkDisplayElement(editCartFlashSaleDiscountPercent)) {
                    flag.add(true);
                    int newPrice = flashSaleProduct.getFlashSalePrice();
                    int oldPrice = flashSaleProduct.getPrice();
                    String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(oldPrice, newPrice));
                    if (driver.findElement(editCartFlashSaleDiscountPercent).getText().equals(discountPercentFormatted)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Percent is wrong!\nActual: " + driver.findElement(editCartFlashSaleDiscountPercent).getText() + " Expected: " + discountPercentFormatted + "\n";
                        flag.add(false);
                        scroll = 1;
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + " Flash sale discount percent tag did not display.\n";
                }
                if (helper.checkDisplayElement(editCartPrice)) {
                    flag.add(true);
                    if (driver.findElement(editCartPrice).getText().equals(flashSalePrice)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale price is wrong!\nActual: " + driver.findElement(editCartPrice).getText() + " Expected: " + flashSalePrice + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Original price did not display.\n";
                }
                if (helper.checkDisplayElement(editCartOriginalPrice)) {
                    flag.add(true);
                    if (driver.findElement(editCartOriginalPrice).getText().equals(price)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price is wrong!\nActual: " + driver.findElement(editCartOriginalPrice).getText() + " Expected: " + price + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale price did not display.\n";
                }
                try {
                    helper.scrollByJS(driver.findElement(editCartButtonPrice));
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                }
                if (helper.checkDisplayElement(editCartButtonPrice)) {
                    flag.add(true);
                    if (driver.findElement(editCartButtonPrice).getText().equals(sellingPrice)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale price on button is wrong!\nActual: " + driver.findElement(editCartButtonPrice).getText() + " Expected: " + sellingPrice + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale price on button did not display.\n";
                }
                if (helper.checkDisplayElement(editCartButtonOriginalPrice)) {
                    flag.add(true);
                    if (driver.findElement(editCartButtonOriginalPrice).getText().equals(priceBtn)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price on button is wrong!\nActual: " + driver.findElement(editCartButtonOriginalPrice).getText() + " Expected: " + priceBtn + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Original price on button did not display.\n";
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Flash sale section did not display.\n";
            }
        } else {
            String price = apiAminService.getPriceProductByName(productName.replaceAll("\\s*\\(.*\\)", ""), sizeName);
            if (helper.checkDisplayElement(editCartPrice)) {
                flag.add(true);
                if (driver.findElement(editCartPrice).getText().equals(price)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Price is wrong!\nActual: " + driver.findElement(editCartPrice).getText() + " Expected: " + price + "\n";
                    flag.add(false);
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Price did not display.\n";
            }
            try {
                helper.scrollByJS(driver.findElement(editCartButtonPrice));
            } catch (Exception exception) {
                Log.error(exception.getMessage());
            }
            String lastPrice = helper.formatCurrencyToThousand(helper.convertCurrencyToInteger(price) + toppingTotalPrice.get(0)); //get selling price
            String sellingPrice = helper.formatCurrencyToThousand(helper.convertCurrencyToInteger(lastPrice) * cartQuantity); //price on button
            if (helper.checkDisplayElement(editCartButtonPrice)) {
                flag.add(true);
                if (driver.findElement(editCartButtonPrice).getText().equals(sellingPrice)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Selling price on button is wrong!\nActual: " + driver.findElement(editCartButtonPrice).getText() + " Expected: " + sellingPrice + "\n";
                    flag.add(false);
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Selling price on button did not display.\n";
            }
        }
        if (flag.contains(false)) {
            return false;
        } else {
            return true;
        }
    }

    private List<Integer> getToppingEqual0() {
        List<Integer> toppingTotalPrice = new ArrayList<>();
        toppingTotalPrice.add(0);
        toppingTotalPrice.add(0);
        return toppingTotalPrice;
    }

    public Boolean checkPriceFlashSaleCondition(String flashSaleKey, String productName, Boolean isFlashSale, int cartQuantity) {
        return checkPriceFlashSale(flashSaleKey, productName, isFlashSale, cartQuantity, getToppingEqual0(), "");
    }

    public Boolean checkPriceFlashSaleSizeCondition(String flashSaleKey, String productName, Boolean isFlashSale, int cartQuantity, String sizeName) {
        return checkPriceFlashSale(flashSaleKey, productName, isFlashSale, cartQuantity, getToppingEqual0(), sizeName);
    }

    public Boolean checkPriceFlashSaleToppingCondition(String flashSaleKey, String productName, Boolean isFlashSale, int cartQuantity, List<Integer> toppingTotalPrice, String sizeName) {
        return checkPriceFlashSale(flashSaleKey, productName, isFlashSale, cartQuantity, toppingTotalPrice, sizeName);
    }

    public Boolean checkPriceFlashSaleConditionOneProduct(String flashSaleKey, String productName, Boolean isFlashSale) {
        return checkPriceFlashSale(flashSaleKey, productName, isFlashSale, 1, getToppingEqual0(), "");
    }

    //actions
    public void clickUpdateBtn() {
        helper.waitForPresence(updateButton);
        helper.visibleOfLocated(updateButton);
        try {
            helper.scrollByJS(driver.findElement(updateButton));
            helper.clickBtn(driver.findElement(updateButton));
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            Log.error(elementClickInterceptedException.getMessage());
            helper.clickByJS(driver.findElement(updateButton));
        }
    }

    public void clickUpQuantityWithElement(int expectedQuantity) {
        helper.waitForPresence(editCartModifyQuantity);
        helper.visibleOfLocated(editCartModifyQuantity);
        int oldQuantity = Integer.parseInt(driver.findElement(editCartQuantity).getText());
        int clickedNumber = expectedQuantity - oldQuantity;
        while (clickedNumber > 0) {
            try {
                helper.clickBtn(driver.findElement(editCartIncreaseBtn));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(driver.findElement(editCartIncreaseBtn));
            }
            clickedNumber--;
        }
    }

    public void clickDownQuantityWithElement(int expectedQuantity) {
        helper.waitForPresence(editCartModifyQuantity);
        helper.visibleOfLocated(editCartModifyQuantity);
        int oldQuantity = Integer.parseInt(driver.findElement(editCartQuantity).getText());
        if (oldQuantity > 1) {
            int clickedNumber = oldQuantity - expectedQuantity;
            while (clickedNumber > 0) {
                try {
                    helper.clickBtn(driver.findElement(editCartReduceBtn));
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    helper.clickByJS(driver.findElement(editCartReduceBtn));
                }
                clickedNumber--;
            }
        }
    }

    private Boolean updateQuantityToCheckNotifyLanguage(String currentLanguage, int maximumLimit) {
        commonPagesTheme1().productDetailsPage.openEditCartFromCart();
        clickUpQuantityWithElement(maximumLimit + 1);
        int loop = 3;
        while (!checkDisplayOfMaximumLimitText() || loop > 0) {
            clickUpQuantityWithElement(maximumLimit + 1);
            loop--;
        }
        return checkContentOfMaximumLimitNotify(currentLanguage, maximumLimit);
    }

    //language
    public Boolean checkLanguageOfMaximumLimitNotify(int maximumLimit) {
        List<Boolean> flag = new ArrayList<>();
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        String checkedLanguage = currentLanguage;
        int index = 0;
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        List<WebElement> languageList = helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
        helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.dialogContent));
        if (languageList.get(0).getText().equals(language)) index = 1;
        else index = 0;
        commonPagesTheme1().homePage.clickLanguage();
        //check default language
        flag.add(updateQuantityToCheckNotifyLanguage(currentLanguage, maximumLimit));
        helper.refreshPage();
        languageList = helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            try {
                helper.waitForPresence(commonPagesTheme1().homePage.dialogContent);
                helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.dialogContent));
                helper.waitUtilElementVisible(languageList.get(i));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            flag.add(updateQuantityToCheckNotifyLanguage(currentLanguage, maximumLimit));
            helper.refreshPage();
            helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.dialogContent));
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(commonPagesTheme1().homePage.languageOptions);
            if (!languageList.get(i).getText().equals(language)) {
                helper.waitUtilElementVisible(languageList.get(i));
                helper.clickBtn(languageList.get(i));
                helper.waitForJStoLoad();
                i++;
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }
}
