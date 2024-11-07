package com.fnb.web.store.theme1.pages.product_details;

import com.fnb.utils.api.storeweb.admin.helpers.APIAminService;
import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.flashSale.FlashSalePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends Setup {
    private WebDriver driver;
    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public String actualRS;
    private FlashSalePage flashSalePage;
    private ProductDetailsDataTest productDetailsDataTest;
    private JsonReader jsonReader;
    private APIAminService apiAminService;
    //locators
    public By productInformation = By.id("popup-choose-options");
    private By productModifyQuantity = By.xpath("//div[@class=\"modify-quantity\"]");
    private By productIncreaseBtn = By.xpath("//div[@class=\"modify-quantity\"]/button[contains(@class,\"btn-increase\")]");
    private By productReduceBtn = By.xpath("//div[@class=\"modify-quantity\"]/button[contains(@class,\"btn-reduce\")]");
    private By productQuantity = By.xpath("//div[@class=\"modify-quantity\"]/span");
    public By addToCartBTN = By.xpath("//div[@class=\"button-background\"]/button");
    public By sizeSelected = By.xpath("//div[contains(@class,\"selection\")]/span[contains(text(),\"size\")]/following-sibling::div//label[contains(@class,\"ant-radio-button-wrapper-checked\")]//div[contains(@class,\"group-price\")]/span");
    public By sizeOptionList = By.xpath("//div[contains(@class,\"selection\")]/span[contains(text(),\"size\")]/following-sibling::div/label");
    private By productName = By.xpath("//div[@id=\"popup-choose-options\"]//div[contains(@class,\"product-detail-title\")]");
    //size
    private By sizeSection = By.xpath("//div[contains(@class,\"selection\")]/span[contains(text(),\"size\")]");
    private By sizeText = By.xpath("//div[contains(@class,\"selection\")]/span[contains(text(),\"size\")]/following-sibling::div/label//div[contains(@class,\"group-price\")]/span");
    private By sizePrice = By.xpath("//div[contains(@class,\"selection\")]/span[contains(text(),\"size\")]/following-sibling::div/label//div[contains(@class,\"group-price\")]/p");
    //topping
    private By toppingTitle = By.xpath("//div[contains(@class,\"selection\")]/span[contains(text(),\"topping\")]");
    private By toppingNameList = By.xpath("//div[contains(@class,\"topping-item\")]//p[contains(@class,\"topping-original-price\")]");
    private By toppingPriceList = By.xpath("//div[contains(@class,\"topping-item\")]//p[contains(@class,\"topping-price\")]");
    private By toppingOriginalPriceList = By.xpath("//div[contains(@class,\"topping-item\")]//p[contains(@class,\"topping-original-price\")]");
    private By increaseBtnList = By.xpath("//div[contains(@class,\"group-topping\")]/button[contains(@class,\"btn-increase\")]");
    //add to cart button
    private By buttonAddPrice = By.xpath("//div[@id=\"popup-choose-options\"]//div[contains(@class,\"btn-add-to-cart-price\")]");
    private By buttonAddOriginalPrice = By.xpath("//div[@id=\"popup-choose-options\"]//div[contains(@class,\"btn-add-to-cart-original-price\")]");
    private By productPrice = By.xpath("//div[@id=\"popup-choose-options\"]//span[contains(@class,\"product-price\")]");
    //flash sale
    private String productDetailFlashSaleLocator = "//div[@id=\"popup-choose-options\"]/div[contains(@class,\"product-detail-flash-sale\")]";
    private By productFlashSale = By.xpath(productDetailFlashSaleLocator);
    private By flashSaleLogo = By.xpath(productDetailFlashSaleLocator + "//div[contains(@class,\"flash-sale-banner-logo\")]//*[name()=\"svg\"]");
    private By flashSaleTitle = By.xpath(productDetailFlashSaleLocator + "//div[contains(@class,\"flash-sale-banner-title\")]");
    private By flashSaleFlipCountdown = By.xpath(productDetailFlashSaleLocator + "//div[contains(@class,\"flip-countdown\")]");
    private By flashSaleFlipCountdownZero = By.xpath(productDetailFlashSaleLocator + "//div[contains(@class,\"flash-sale-zero\")]");
    private By flashSaleFlipCountdownNumber = By.xpath(productDetailFlashSaleLocator + "//div[contains(@class,\"flip-countdown\")]/div/div"); //6 numbers
    private By flashSaleDiscountPercent = By.xpath("//div[contains(@class,\"product-detail-image\")]//div[contains(@class,\"promotional-product\")]/span");
    private By originalPrice = By.xpath("//div[@id=\"popup-choose-options\"]//span[contains(@class,\"product-original-price\")]");
    //Maximum limit notification
    private By maximumLimitNotify = By.xpath(productDetailFlashSaleLocator + "/following-sibling::div[@class=\"maximum-limit-flash-sale-notify\"]");
    private By maximumLimitIcon = By.xpath(productDetailFlashSaleLocator + "/following-sibling::div[@class=\"maximum-limit-flash-sale-notify\"]/*[name()=\"svg\"]");
    private By maximumLimitText = By.xpath(productDetailFlashSaleLocator + "/following-sibling::div[@class=\"maximum-limit-flash-sale-notify\"]/span");
    //Related product
    private By relatedProductSection = By.xpath("//div[contains(@class,\"similar-product \")]");
    private By relatedProductCart = By.xpath("//div[contains(@class,\"similar-product \")]//div[contains(@class,\"swiper-slide\")]");
    private By relatedProductName = By.xpath("//div[contains(@class,\"similar-product \")]//div[contains(@class,\"swiper-slide\")]//div[contains(@class,\"product-name\")]");
    private String relatedProductNameXP = ".//div[contains(@class,\"product-name\")]";
    private String relatedProductSellingPriceXP = ".//div[contains(@class,\"selling-price\")]";
    private String relatedProductAddToCartXP = ".//button[contains(@class,\"add-to-cart\")]";
    //related flash sale
    private String relatedFlashSaleDiscountPercentXP = ".//div[contains(@class,\"promotion-label\")]/span";
    private String relatedFlashSaleLogoXP = ".//img[contains(@class,\"flash-sale-logo\")]";
    private String relatedFlashSaleBorderXP = ".//div[contains(@class,\"flash-sale-border\")]";
    private String relatedFlashSaleOriginalPriceXP = ".//div[contains(@class,\"original-price\")]";

    public ProductDetailsPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        flashSalePage = new FlashSalePage(driver);
    }

    public void navigateToProductDetailsWithURL(String url) {
        String urlFull = configObject.getUrlBase() + url;
        helper.navigateTo(urlFull);
    }

    //product information
    public Boolean checkFlipCounterWhenFlashSaleEnded() {
        return helper.checkDisplayElement(flashSaleFlipCountdownZero);
    }

    //maximum Limit
    public Boolean checkDisplayOfMaximumLimitNotify() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        if (helper.checkDisplayElement(maximumLimitNotify)) {
            if (checkDisplayOfMaximumLimitIcon()) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Maximum Limit Icon did not display";
                flag.add(false);
            }
            if (checkDisplayOfMaximumLimitText()) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Maximum Limit Icon did not display";
                flag.add(false);
            }
        } else {
            actualRS = actualRS + "Maximum Limit notify did not display";
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

    public Boolean checkContentOfMaximumLimitNotify(int maximumLimit) {
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        String expected = jsonReader.localeReader(currentLanguage, productDetailsDataTest.MAXIMUM_LIMIT_NOTIFY_PAGE, null);
        String replaced = expected.replace("{{maximumLimit}}", String.valueOf(maximumLimit));
        if (checkDisplayOfMaximumLimitText()) {
            if (helper.getAttribute(maximumLimitText, "innerHTML").equals(replaced)) return true;
            else {
                actualRS = "Notify is incorrect. Actual: " + helper.getText(maximumLimitText) + " Expected: " + replaced;
                return false;
            }
        } else {
            actualRS = "Notify is incorrect. Actual: " + helper.getText(maximumLimitText) + " Expected: " + replaced;
            return false;
        }
    }

    //size
    public List<String> selectSizeOption(String size) {
        List<String> product = new ArrayList<>();
        List<WebElement> sizePriceList = new ArrayList<>();
        String price = "";
        try {
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            helper.visibleOfLocated(productInformation);
        }
        helper.waitForPresence(productName);
        helper.visibleOfLocated(productName);
        String text = driver.findElement(productName).getText().trim();
        if (text.equals("") || text.isEmpty() || text.isBlank()) {
            helper.refreshPage();
            helper.waitForPresence(productName);
            helper.visibleOfLocated(productName);
            text = driver.findElement(productName).getText().trim();
        }
        System.out.println(text);
        String productNameSize = text + " (" + size + ")";
        if (driver.findElement(productName).getText().trim().equals("")) {
            productNameSize = text + " (" + size + ")";
            System.out.println("if name is empty");
        }
        System.out.println(productNameSize);
        helper.waitForJStoLoad();
        helper.waitForPresence(sizeSection);
        helper.scrollToElementByJS(driver.findElement(sizeSection));
        List<WebElement> sizeElementList = helper.getElementList(sizeOptionList);
        List<WebElement> sizeTextList = helper.getElementList(sizeText);
        if (sizeTextList.size() == 0) sizeTextList = helper.getElementList(sizeText);
        for (int i = 0; i < sizeElementList.size(); i++) {
            if (sizeTextList.get(i).getText().equals(size)) {
                try {
                    helper.waitUtilElementVisible(sizeElementList.get(i));
                    helper.scrollByJS(sizeElementList.get(i));
                    helper.clickBtn(sizeElementList.get(i));
                } catch (ElementClickInterceptedException elementClickInterceptedException) {
                    helper.clickByJS(sizeElementList.get(i));
                }
                sizePriceList = helper.getElementList(sizePrice);
                try {
                    helper.scrollByJS(sizePriceList.get(i));
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                }
                try {
                    price = sizePriceList.get(i).getText();
                } catch (Exception exception) {
                    sizePriceList = helper.getElementList(sizePrice);
                    price = sizePriceList.get(i).getText();
                }
                break;
            }
        }
        product.add(productNameSize);
        product.add(price);
        System.out.println(price);
        return product;
    }

    //add product to cart
    public void clickAddToCart() {
        helper.waitForPresence(addToCartBTN);
        helper.visibleOfLocated(addToCartBTN);
        try {
            helper.scrollByJS(driver.findElement(addToCartBTN));
            helper.clickBtn(driver.findElement(addToCartBTN));
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            Log.error(elementClickInterceptedException.getMessage());
            helper.clickByJS(driver.findElement(addToCartBTN));
        }
    }

    public Boolean addToCart() {
        Boolean check = true;
        int quantity = 0, quantityExpected = 0;
        try {
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        helper.waitForPresence(addToCartBTN);
        WebElement e = driver.findElement(addToCartBTN);
        helper.waitForClickable(e);
        try {
            helper.actionHover(e);
            helper.clickBtn(e);
            quantityExpected++;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(e);
            quantityExpected++;
        }
        helper.waitForPresence(commonPagesTheme1().productListPage.addProductSuccessToast);
        try {
            helper.waitUtilElementVisible(helper.getElement(commonPagesTheme1().productListPage.addProductSuccessToast));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("Add successful toast message did not display");
            helper.pressPageUpAction();
            helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
            helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
            quantity = Integer.parseInt(helper.getText(commonPagesTheme1().homePage.cartQuantity));
            if (quantity < quantityExpected) {
                check = false;
                helper.clickByJS(e);
            }
        }
        helper.waitForJStoLoad();
        helper.pressPageUpAction();
        helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
        String cartQuantityStr = helper.getText(commonPagesTheme1().homePage.cartQuantity);
        if (cartQuantityStr.length() == 0) cartQuantityStr = helper.getText(commonPagesTheme1().homePage.cartQuantity);
        quantity = Integer.parseInt(cartQuantityStr);
        if (quantity == quantityExpected && check) return true;
        else return false;
    }

    public Boolean addProductToCartWithQuantity(int quantityExpected) {
        int quantity = 0;
        try {
            helper.waitForPresence(productInformation);
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        WebElement e = driver.findElement(addToCartBTN);
        int count = quantityExpected;
        while (count > 0) {
            helper.waitForClickable(e);
            try {
                helper.actionHover(e);
                helper.clickBtn(e);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(e);
            }
            try {
                helper.waitForPresence(commonPagesTheme1().productListPage.addProductSuccessToast);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                System.out.println("Add successful toast message did not display");
                helper.pressPageUpAction();
                try {
                    helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
                    helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
                    WebElement element = driver.findElement(commonPagesTheme1().homePage.cartQuantity);
                    quantity = Integer.parseInt(element.getText());
                    if (quantity < (quantityExpected - count)) {
                        helper.clickByJS(e);
                    }
                } catch (Exception exception1) {
                    Log.info(exception1.getMessage());
                    helper.refreshPage();
                    try {
                        helper.waitForPresence(addToCartBTN);
                        helper.waitUtilElementVisible(driver.findElement(addToCartBTN));
                        e = driver.findElement(addToCartBTN);
                        helper.clickBtn(e);
                    } catch (Exception ex) {
                        Log.info(ex.getMessage());
                        helper.clickByJS(e);
                    }
                    try {
                        helper.waitForPresence(commonPagesTheme1().productListPage.addProductSuccessToast);
                        helper.waitUtilElementVisible(helper.getElement(commonPagesTheme1().productListPage.addProductSuccessToast));
                    } catch (Exception exception2) {
                        Log.info(exception2.getMessage());
                        System.out.println("Add successful toast message did not display");
                        helper.pressPageUpAction();
                        helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
                        helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
                        quantity = Integer.parseInt(helper.getText(commonPagesTheme1().homePage.cartQuantity));
                        if (quantity < (quantityExpected - count)) {
                            helper.clickByJS(e);
                        }
                    }
                }
            }
            count--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException interruptedException) {
                Log.info(interruptedException.getMessage());
            }
        }
        helper.waitForJStoLoad();
        helper.pressPageUpAction();
        helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
        quantity = Integer.parseInt(helper.getText(commonPagesTheme1().homePage.cartQuantity));
        return (quantity == quantityExpected);
    }

    public List<String> addProductToCartWithSize(String size, int quantityExpected) {
        int quantity = 0;
        List<String> product = new ArrayList<>();
        try {
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        WebElement e = driver.findElement(addToCartBTN);
        int count = quantityExpected;
        while (count > 0) {
            product = selectSizeOption(size);
            helper.waitForClickable(e);
            try {
                helper.actionHover(e);
                helper.clickBtn(e);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(e);
            }
            try {
                helper.waitForPresence(commonPagesTheme1().productListPage.addProductSuccessToast);
                helper.waitUtilElementVisible(helper.getElement(commonPagesTheme1().productListPage.addProductSuccessToast));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                System.out.println("Add successful toast message did not display");
                helper.pressPageUpAction();
                helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
                helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
                quantity = Integer.parseInt(helper.getText(commonPagesTheme1().homePage.cartQuantity));
                if (quantity < (quantityExpected - count)) {
                    helper.clickByJS(e);
                }
            }
            count--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException interruptedException) {
                Log.info(interruptedException.getMessage());
            }
        }
        helper.waitForJStoLoad();
        helper.pressPageUpAction();
        helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
        return product;
    }

    public String addProductToCartWithTopping(int quantityExpected) {
        int quantity = 0;
        String price = "";
        try {
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        WebElement e = driver.findElement(addToCartBTN);
        int count = quantityExpected;
        while (count > 0) {
            price = getTotalPriceSelectToppingOption();
            helper.waitForClickable(e);
            try {
                helper.actionHover(e);
                helper.clickBtn(e);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(e);
            }
            try {
                helper.waitForPresence(commonPagesTheme1().productListPage.addProductSuccessToast);
                helper.waitUtilElementVisible(helper.getElement(commonPagesTheme1().productListPage.addProductSuccessToast));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                System.out.println("Add successful toast message did not display");
                helper.pressPageUpAction();
                helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
                helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
                quantity = Integer.parseInt(helper.getText(commonPagesTheme1().homePage.cartQuantity));
                if (quantity < (quantityExpected - count)) {
                    helper.clickByJS(e);
                }
            }
            count--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException interruptedException) {
                Log.info(interruptedException.getMessage());
            }
        }
        helper.waitForJStoLoad();
        helper.pressPageUpAction();
        helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
        return price;
    }

    public List<String> addProductToCartWithSizeAndTopping(String size, int quantityExpected) {
        int quantity = 0;
        int price = 0;
        List<String> product = new ArrayList<>();
        try {
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        WebElement e = driver.findElement(addToCartBTN);
        int count = quantityExpected;
        while (count > 0) {
            product = selectSizeOption(size);
            price = getToppingPriceSelectToppingOption();
            int lastPrice = price + Integer.parseInt(product.get(1).substring(0, product.get(1).length() - 1).replace(",", ""));
            product.add(1, helper.formatCurrencyToThousand(lastPrice));
            helper.waitForClickable(e);
            try {
                helper.actionHover(e);
                helper.clickBtn(e);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(e);
            }
            try {
                helper.waitForPresence(commonPagesTheme1().productListPage.addProductSuccessToast);
                helper.waitUtilElementVisible(helper.getElement(commonPagesTheme1().productListPage.addProductSuccessToast));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                System.out.println("Add successful toast message did not display");
                helper.pressPageUpAction();
                helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
                helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
                quantity = Integer.parseInt(helper.getText(commonPagesTheme1().homePage.cartQuantity));
                if (quantity < (quantityExpected - count)) {
                    helper.clickByJS(e);
                }
            }
            count--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException interruptedException) {
                Log.info(interruptedException.getMessage());
            }
        }
        helper.waitForJStoLoad();
        helper.pressPageUpAction();
        helper.waitForPresence(commonPagesTheme1().homePage.cartQuantity);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartQuantity);
        return product;
    }

    //topping-option
    public String getTotalPriceSelectToppingOption() {
        String price = "";
        try {
            helper.waitForPresence(productInformation);
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        helper.waitForJStoLoad();
        helper.waitForJStoLoad();
        helper.waitForPresence(toppingTitle);
        helper.visibleOfLocated(toppingTitle);
        try {
            helper.scrollByJS(driver.findElement(toppingTitle));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        List<WebElement> increaseBtnElementList = helper.getElementList(increaseBtnList);
        if (increaseBtnElementList.size() == 0) increaseBtnElementList = helper.getElementList(increaseBtnList);
        for (int i = 0; i < increaseBtnElementList.size(); i++) {
            try {
                increaseBtnElementList.get(i).isDisplayed();
                helper.clickBtn(increaseBtnElementList.get(i));
            } catch (ElementClickInterceptedException elementClickInterceptedException) {
                Log.info(elementClickInterceptedException.getMessage());
                try {
                    helper.clickByJS(increaseBtnElementList.get(i));
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    increaseBtnElementList = helper.getElementList(increaseBtnList);
                    helper.clickByJS(increaseBtnElementList.get(i));
                }
            }
        }
        price = driver.findElement(buttonAddPrice).getText();
        return price;
    }

    public int getToppingPriceSelectToppingOption() {
        String price = "";
        int topping = 0;
        try {
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        helper.waitForJStoLoad();
        helper.waitForPresence(toppingTitle);
        helper.visibleOfLocated(toppingTitle);
        try {
            helper.scrollByJS(driver.findElement(toppingTitle));
            System.out.println("scroll");
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.sleep(1);
        List<WebElement> increaseBtnElementList = helper.getElementList(increaseBtnList);
        System.out.println(increaseBtnElementList.size());
        if (increaseBtnElementList.size() == 0) increaseBtnElementList = helper.getElementList(increaseBtnList);
        for (int i = 0; i < increaseBtnElementList.size(); i++) {
            try {
                helper.waitUtilElementVisible(increaseBtnElementList.get(i));
                WebElement increase = increaseBtnElementList.get(i);
                helper.clickBtn(increase);
            } catch (ElementClickInterceptedException elementClickInterceptedException) {
                Log.info(elementClickInterceptedException.getMessage());
                WebElement increase = increaseBtnElementList.get(i);
                helper.clickByJS(increase);
            }
            price = driver.findElement(toppingPriceList).getText();
            topping = topping + helper.convertCurrencyToInteger(price);
        }
        return topping;
    }

    public List<Integer> getToppingPriceFlashSaleCondition() {
        int toppingPrice = 0;
        int toppingOriginalPrice = 0;
        List<WebElement> toppingPriceElementList = new ArrayList<>();
        List<WebElement> toppingOriginalElementList = new ArrayList<>();
        List<Integer> toppingIntList = new ArrayList<>();
        Topping topping;
        try {
            helper.waitUtilElementVisible(driver.findElement(productInformation));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.visibleOfLocated(productInformation);
        }
        helper.waitForJStoLoad();
        helper.waitForPresence(toppingTitle);
        helper.visibleOfLocated(toppingTitle);
        try {
            helper.scrollByJS(driver.findElement(toppingTitle));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        try {
            helper.scrollByJS(driver.findElement(toppingTitle));
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            Log.info(elementClickInterceptedException.getMessage());
        }
        helper.sleep(1);
        List<WebElement> increaseBtnElementList = helper.getElementList(increaseBtnList);
        if (increaseBtnElementList.size() == 0) increaseBtnElementList = helper.getElementList(increaseBtnList);
        for (int i = 0; i < increaseBtnElementList.size(); i++) {
            try {
                helper.waitUtilElementVisible(increaseBtnElementList.get(i));
                helper.scrollByJS(increaseBtnElementList.get(i));
                helper.clickBtn(increaseBtnElementList.get(i));
            } catch (ElementClickInterceptedException elementClickInterceptedException) {
                Log.info(elementClickInterceptedException.getMessage());
                helper.clickByJS(increaseBtnElementList.get(i));
            }
            toppingPriceElementList = helper.getElementList(toppingPriceList);
            toppingOriginalElementList = helper.getElementList(toppingOriginalPriceList);
            topping = new Topping();
            topping.setPrice(toppingPriceElementList.get(i).getText());
            toppingPrice = toppingPrice + helper.convertCurrencyToInteger(topping.getPrice());
            if (toppingOriginalElementList.size() > 0) {
                topping.setOriginalPrice(toppingOriginalElementList.get(i).getText());
                toppingOriginalPrice = toppingOriginalPrice + helper.convertCurrencyToInteger(topping.getOriginalPrice());
            } else {
                topping.setOriginalPrice(toppingPriceElementList.get(i).getText());
                toppingOriginalPrice = toppingOriginalPrice + helper.convertCurrencyToInteger(topping.getOriginalPrice());
            }
        }
        toppingIntList.add(toppingPrice);
        toppingIntList.add(toppingOriginalPrice);
        return toppingIntList;
    }

    //open edit order
    public Boolean openEditCartFromCart() {
        commonPagesTheme1().homePage.clickCartIcon();
        helper.waitForPresence(commonPagesTheme1().homePage.productCartImage);
        helper.visibleOfLocated(commonPagesTheme1().homePage.productCartImage);
        try {
            helper.clickBtn(driver.findElement(commonPagesTheme1().homePage.productCartImage));
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            Log.info(elementClickInterceptedException.getMessage());
            helper.clickByJS(driver.findElement(commonPagesTheme1().homePage.productCartImage));
        }
        return commonPagesTheme1().editOrder.checkDisplayOfEditCart();
    }

    //flash sale
    public Boolean checkDisplayOfFlashSaleSection() {
        return helper.checkDisplayElement(productFlashSale);
    }

    private void scrollToFlashSaleProductInformation() {
        try {
            helper.waitForPresence(productFlashSale);
            helper.scrollByJS(driver.findElement(productFlashSale));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.pressHomeAction();
            helper.waitForPresence(productFlashSale);
            helper.visibleOfLocated(productFlashSale);
        }
    }

    public Boolean checkDisplayWhenFlashSale() {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        scrollToFlashSaleProductInformation();
        if (checkDisplayOfFlashSaleSection()) {
            flag.add(true);
            if (helper.checkDisplayElement(flashSaleLogo)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + " Flash sale logo did not display.\n";
            }
            if (helper.checkDisplayElement(flashSaleTitle)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + " Flash sale title did not display.\n";
            }
            if (helper.checkDisplayElement(flashSaleFlipCountdown)) {
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
            if (flag.size() > 1)
                actualRS = "Flash sale infor displays with order: flash sale title, flash sale flip count down: " + flag;
            else actualRS = "Flash sale section displays";
            return true;
        }
    }

    public Boolean checkPriceFlashSale(String flashSaleKey, String productName, Boolean isFlashSale, List<Integer> toppingTotalPrice, String sizeName) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.waitForPresence(productInformation);
        if (isFlashSale) {
            FlashSaleProduct flashSaleProduct = flashSalePage.getFlashSaleProductObject(flashSaleKey, productName);
            String price = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
            String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
            if (checkDisplayOfFlashSaleSection()) {
                if (helper.checkDisplayElement(flashSaleDiscountPercent)) {
                    flag.add(true);
                    int newPrice = flashSaleProduct.getFlashSalePrice();
                    int oldPrice = flashSaleProduct.getPrice();
                    String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(oldPrice, newPrice));
                    if (driver.findElement(flashSaleDiscountPercent).getText().equals(discountPercentFormatted))
                        flag.add(true);
                    else {
                        actualRS = actualRS + "Percent is wrong!\nActual: " + driver.findElement(flashSaleDiscountPercent).getText() + " Expected: " + discountPercentFormatted + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + " Flash sale discount percent tag did not display.\n";
                }
                if (helper.checkDisplayElement(productPrice)) {
                    flag.add(true);
                    if (driver.findElement(productPrice).getText().equals(flashSalePrice)) flag.add(true);
                    else {
                        actualRS = actualRS + "Flash sale selling price is wrong!\nActual: " + driver.findElement(productPrice).getText() + " Expected: " + flashSalePrice + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale Selling price did not display.\n";
                }
                if (helper.checkDisplayElement(originalPrice)) {
                    flag.add(true);
                    if (driver.findElement(originalPrice).getText().equals(price)) flag.add(true);
                    else {
                        actualRS = actualRS + "Flash sale Original price is wrong!\nActual: " + driver.findElement(originalPrice).getText() + " Expected: " + price + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale price did not display.\n";
                }
                price = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice() + toppingTotalPrice.get(0)); //get selling price
                if (helper.checkDisplayElement(buttonAddPrice)) {
                    flag.add(true);
                    if (driver.findElement(buttonAddPrice).getText().equals(price)) flag.add(true);
                    else {
                        actualRS = actualRS + "Flash sale price is wrong!\nActual: " + driver.findElement(buttonAddPrice).getText() + " Expected: " + flashSalePrice + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale Original price on button did not display.\n";
                }
                price = helper.formatCurrencyToThousand(flashSaleProduct.getPrice() + toppingTotalPrice.get(1)); //get original price
                if (helper.checkDisplayElement(buttonAddOriginalPrice)) {
                    flag.add(true);
                    if (driver.findElement(buttonAddOriginalPrice).getText().equals(price)) flag.add(true);
                    else {
                        actualRS = actualRS + "Flash sale Original price is wrong!\nActual: " + driver.findElement(buttonAddOriginalPrice).getText() + " Expected: " + price + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale Original price on button did not display.\n";
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Flash sale section did not display.\n";
            }
        } else {
            String price = apiAminService.getPriceProductByName(productName.replaceAll("\\s*\\(.*\\)", ""), sizeName);
            if (helper.checkDisplayElement(productPrice)) {
                flag.add(true);
                if (driver.findElement(productPrice).getText().equals(price)) flag.add(true);
                else {
                    actualRS = actualRS + "Selling price is wrong!\nActual: " + driver.findElement(productPrice).getText() + " Expected: " + price + "\n";
                    flag.add(false);
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Selling price did not display.\n";
            }
            try {
                helper.scrollByJS(driver.findElement(buttonAddPrice));
            } catch (Exception exception) {
                Log.error(exception.getMessage());
            }
            String lastPrice = helper.formatCurrencyToThousand(helper.convertCurrencyToInteger(price) + toppingTotalPrice.get(0)); //get selling price
            if (helper.checkDisplayElement(buttonAddPrice)) {
                flag.add(true);
                if (driver.findElement(buttonAddPrice).getText().equals(lastPrice)) flag.add(true);
                else {
                    actualRS = actualRS + "Original price on button is wrong!\nActual: " + driver.findElement(buttonAddPrice).getText() + " Expected: " + lastPrice + "\n";
                    flag.add(false);
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Original price on button did not display.\n";
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    private List<Integer> getToppingEqual0() {
        List<Integer> toppingTotalPrice = new ArrayList<>();
        toppingTotalPrice.add(0);
        toppingTotalPrice.add(0);
        return toppingTotalPrice;
    }

    public Boolean checkPriceFlashSaleCondition(String flashSaleKey, String productName, Boolean isFlashSale) {
        return checkPriceFlashSale(flashSaleKey, productName, isFlashSale, getToppingEqual0(), "");
    }

    public Boolean checkPriceFlashSaleSizeCondition(String flashSaleKey, String productName, Boolean isFlashSale, String sizeName) {
        return checkPriceFlashSale(flashSaleKey, productName, isFlashSale, getToppingEqual0(), sizeName);
    }

    public Boolean checkPriceFlashSaleToppingCondition(String flashSaleKey, String productName, Boolean isFlashSale, List<Integer> toppingTotalPrice, String sizeName) {
        return checkPriceFlashSale(flashSaleKey, productName, isFlashSale, toppingTotalPrice, sizeName);
    }

    //maximum limit
    public void clickUpQuantityWithElement(int addProduct) {
        helper.waitForPresence(productModifyQuantity);
        helper.visibleOfLocated(productModifyQuantity);
        int oldQuantity = Integer.parseInt(driver.findElement(productQuantity).getText());
        int clickedNumber = addProduct - oldQuantity;
        while (clickedNumber > 0 || oldQuantity < addProduct) {
            helper.clickBtn(driver.findElement(productIncreaseBtn));
            oldQuantity = Integer.parseInt(driver.findElement(productQuantity).getText());
            clickedNumber--;
        }
    }

    public void clickDownQuantityWithElement(int expectedQuantity) {
        helper.waitForPresence(productModifyQuantity);
        helper.visibleOfLocated(productModifyQuantity);
        int oldQuantity = Integer.parseInt(driver.findElement(productQuantity).getText());
        if (oldQuantity > 1) {
            int clickedNumber = oldQuantity - expectedQuantity;
            while (clickedNumber > 0 || oldQuantity > expectedQuantity) {
                helper.clickBtn(driver.findElement(productReduceBtn));
                oldQuantity = Integer.parseInt(driver.findElement(productQuantity).getText());
                clickedNumber--;
            }
        }
    }

    //related product
    public Boolean checkDisplayOfRelatedProduct() {
        return helper.checkDisplayElement(relatedProductSection);
    }

    private void scrollToFlashSaleRelatedProduct() {
        try {
            helper.waitForPresence(relatedProductSection);
            helper.scrollByJS(driver.findElement(relatedProductSection));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.pressEndAction();
            helper.waitForPresence(relatedProductSection);
            helper.visibleOfLocated(relatedProductSection);
        }
    }

    public Boolean checkDisplayFlashSaleRelatedProduct(String flashSaleKey, String productClicked, Boolean isFlashSale) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        scrollToFlashSaleRelatedProduct();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        List<String> productNameFlashSaleList = new ArrayList<>();
        for (FlashSaleProduct flashSaleProduct : flashSale.getFlashSaleProduct()) {
            productNameFlashSaleList.add(flashSaleProduct.getName());
        }
        String productName = "";
        List<WebElement> productCartList = helper.getElementList(relatedProductCart);
        List<WebElement> productCartNameList = helper.getElementList(relatedProductName);
        for (int i = 0; i < productCartList.size(); i++) {
            productName = productCartNameList.get(i).getText();
            if (productNameFlashSaleList.contains(productName) && !productName.equals(productClicked)) {
                FlashSaleProduct flashSaleProduct = flashSalePage.getFlashSaleProductObject(flashSaleKey, productName);
                String price = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
                String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
                if (isFlashSale) {
                    if (checkDisplayOfRelatedProduct()) {
                        try {
                            WebElement productNameElement = productCartList.get(i).findElement(By.xpath(relatedProductNameXP));
                            if (productNameElement.getText().equals(flashSaleProduct.getName())) {
                                try {
                                    WebElement element = productCartList.get(i).findElement(By.xpath(relatedFlashSaleBorderXP));
                                    if (helper.checkDisplayElementByElement(element)) {
                                        flag.add(true);
                                    } else {
                                        flag.add(false);
                                        actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                                    }
                                } catch (NoSuchElementException noSuchElementException) {
                                    Log.error(noSuchElementException.getMessage());
                                    actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                                    flag.add(false);
                                }
                                try {
                                    WebElement element = productCartList.get(i).findElement(By.xpath(relatedFlashSaleLogoXP));
                                    if (helper.checkDisplayElementByElement(element)) {
                                        flag.add(true);
                                    } else {
                                        flag.add(false);
                                        actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                                    }
                                } catch (NoSuchElementException noSuchElementException) {
                                    Log.error(noSuchElementException.getMessage());
                                    actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                                    flag.add(false);
                                }
                                try {
                                    WebElement element = productCartList.get(i).findElement(By.xpath(relatedFlashSaleDiscountPercentXP));
                                    if (helper.checkDisplayElementByElement(element)) {
                                        flag.add(true);
                                        int newPrice = flashSaleProduct.getFlashSalePrice();
                                        int oldPrice = flashSaleProduct.getPrice();
                                        String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(oldPrice, newPrice));
                                        if (element.getText().equals(discountPercentFormatted)) flag.add(true);
                                        else {
                                            actualRS = actualRS + "Related product - Percent is wrong!\nActual: " + element.getText() + " Expected: " + discountPercentFormatted + "\n";
                                            flag.add(false);
                                        }
                                    } else {
                                        flag.add(false);
                                        actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                                    }
                                } catch (NoSuchElementException noSuchElementException) {
                                    Log.error(noSuchElementException.getMessage());
                                    actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                                    flag.add(false);
                                }
                                try {
                                    WebElement element = productCartList.get(i).findElement(By.xpath(relatedProductSellingPriceXP));
                                    if (helper.checkDisplayElementByElement(element)) {
                                        flag.add(true);
                                        if (element.getText().equals(flashSalePrice)) {
                                            flag.add(true);
                                        } else {
                                            actualRS = actualRS + "Related product - Flash sale price displayed incorrect! Actual: " + element.getText() + " Expected: " + flashSalePrice + "\n";
                                            flag.add(false);
                                        }
                                    } else {
                                        actualRS = actualRS + "Related product - Flash sale price did not display\n";
                                        flag.add(false);
                                    }
                                } catch (NoSuchElementException noSuchElementException) {
                                    Log.error(noSuchElementException.getMessage());
                                    actualRS = actualRS + "Related product - Flash sale price did not display\n";
                                    flag.add(false);
                                }
                                try {
                                    WebElement element = productCartList.get(i).findElement(By.xpath(relatedFlashSaleOriginalPriceXP));
                                    if (helper.checkDisplayElementByElement(element)) {
                                        flag.add(true);
                                        if (element.getText().equals(price)) {
                                            flag.add(true);
                                        } else {
                                            actualRS = actualRS + "Related product - Flash sale original price displayed incorrect! Actual: " + element.getText() + " Expected: " + price + "\n";
                                            flag.add(false);
                                        }
                                    } else {
                                        actualRS = actualRS + "Related product - Flash sale original price did not display\n";
                                        flag.add(false);
                                    }
                                } catch (NoSuchElementException noSuchElementException) {
                                    Log.error(noSuchElementException.getMessage());
                                    actualRS = actualRS + "Related product - Flash sale original price did not display\n";
                                    flag.add(false);
                                }
                            }
                        } catch (NoSuchElementException noSuchElementException) {
                            Log.error(noSuchElementException.getMessage());
                            actualRS = actualRS + "Related product - Flash sale name did not display\n";
                            flag.add(false);
                        }
                    } else {
                        flag.add(false);
                        actualRS = actualRS + "Related product - Flash sale section did not display.\n";
                    }
                } else {
                    try {
                        WebElement element = productCartList.get(i).findElement(By.xpath(relatedProductSellingPriceXP));
                        if (helper.checkDisplayElementByElement(element)) {
                            flag.add(true);
                            if (element.getText().equals(price)) {
                                flag.add(true);
                            } else {
                                actualRS = actualRS + "Related product - Price displayed incorrect! Actual: " + element.getText() + " Expected: " + price + "\n";
                                flag.add(false);
                            }
                        } else {
                            actualRS = actualRS + "Related product - Price did not display\n";
                            flag.add(false);
                        }
                    } catch (NoSuchElementException noSuchElementException) {
                        Log.error(noSuchElementException.getMessage());
                        actualRS = actualRS + "Related product - Flash sale price did not display\n";
                        flag.add(false);
                    }
                    try {
                        WebElement element = productCartList.get(i).findElement(By.xpath(relatedFlashSaleBorderXP));
                        if (!helper.checkDisplayElementByElement(element)) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + "Related product - Flash sale discount percent tag still display.\n";
                        }
                    } catch (NoSuchElementException noSuchElementException) {
                        Log.error(noSuchElementException.getMessage());
                        flag.add(true);
                    }
                    try {
                        WebElement element = productCartList.get(i).findElement(By.xpath(relatedFlashSaleLogoXP));
                        if (!helper.checkDisplayElementByElement(element)) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                        }
                    } catch (NoSuchElementException noSuchElementException) {
                        Log.error(noSuchElementException.getMessage());
                        flag.add(true);
                    }
                    try {
                        WebElement element = productCartList.get(i).findElement(By.xpath(relatedFlashSaleDiscountPercentXP));
                        if (!helper.checkDisplayElementByElement(element)) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + "Related product - Flash sale discount percent tag did not display.\n";
                        }
                    } catch (NoSuchElementException noSuchElementException) {
                        Log.error(noSuchElementException.getMessage());
                        flag.add(true);
                    }
                }
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }
}