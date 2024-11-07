package com.fnb.web.store.theme2.pages.checkout;

import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.addUserLocation.AddUserLocationDataTest;
import com.fnb.web.store.theme2.pages.addUserLocation.AddUserLocationPage;
import com.fnb.web.store.theme2.pages.flashsale.FlashSaleDataTest;
import com.fnb.web.store.theme2.pages.flashsale.FlashSalePage;
import com.fnb.web.store.theme2.pages.home.HomeDataTest;
import com.fnb.web.store.theme2.pages.home.HomePage;
import com.fnb.web.store.theme2.pages.home.PromotionDataTest;
import com.fnb.web.store.theme2.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme2.pages.login.DataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutPage extends Setup {
    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    private CheckoutDataTest dataTest;
    private WebDriver driver;
    public String actualRS;
    private String expectedRS;
    private HomePage homePage;
    private FlashSalePage flashSalePage;
    private AddUserLocationPage addUserLocationPage;
    private JsonReader jsonReader;
    private DataTest loginDataTest;
    private HomeDataTest homeDataTest;
    private AddUserLocationDataTest addUserLocationDataTest;
    private FlashSaleDataTest flashSaleDataTest;
    private CheckOutLoginPage checkOutLoginPage;
    private PromotionDataTest promotionDataTest;
    private int cartQuantityNew = 0, totalOriginalPrice = 0;
    //banner
    public By bannerHeaderCheckout = By.id("themeHeaderCheckout");
    //product info
    public By totalQuantity = By.xpath("//div[@class=\"total\"]/div[@class=\"quantity\"]");
    public By totalQuantityNumber = By.xpath("//div[@class=\"total\"]/div[@class=\"quantity\"]/span[1]");
    //user info
    public By userName = By.xpath("(//div[@class=\"detail-receiver\"])[1]");
    public By phoneNumber = By.xpath("(//div[@class=\"detail-receiver\"])[2]");
    public By productDetailsSection = By.xpath("//div[contains(@class, \"product_detail\")]");
    public By deleteList = By.xpath("//*[name()=\"svg\" and contains(@class,\"delete-icon\")]");
    //Complete
    public By completeBtn = By.xpath("//button[contains(@class,\"pay-button\")]");
    private By changeAddressBtn = By.xpath("//div[@class=\"title-delivery-method\"]/div");
    private By boxProductList = By.xpath("//div[contains(@class, \"product_detail\")]/div[contains(@class,\"order-item\")]");
    private By productImage = By.xpath("//div[contains(@class, \"product_detail\")]//img[contains(@class,\"ant-image-img\")]");
    //total
    private By subTotalAmount = By.xpath("//div[contains(@class,\"sub-total\")]/span[2]");
    //flash sale
    private By flashSaleImageBorder = By.xpath("//div[contains(@class,\"checkout-order-item-theme-2\")]//div[contains(@class,\"flash-sale\")]/parent::div");
    private By notFlashSaleImage = By.xpath("//div[contains(@class,\"checkout-order-item-theme-2\")]//div[contains(@class, \"fnb-display-image\") and not(.//div[contains(@class, \"flash-sale\")])]");
    private By flashSaleImageTag = By.xpath("//div[contains(@class,\"checkout-order-item-theme-2\")]//div[@class=\"flash-sale\"]/img");
    private By flashSaleBorder = By.xpath("//div[contains(@class,\"checkout-order-item-theme-2\")]//div[@class=\"flash-sale-border\"]");
    private By promotionLabelCheckout = By.xpath("//div[contains(@class, \"product_cart_theme1\")]//div[contains(@class, \"promotion-label-checkout\")]/span");
    private By flashSaleProductName = By.xpath("//div[contains(@class,\"checkout-order-item-theme-2\")]//div[contains(@class,\"flash-sale\")]/ancestor::div[contains(@class,\"order-item-img\")]/following-sibling::div//div[contains(@class,\"name-info\")]/div[contains(@class,\"name\")]");
    private By flashSaleProductQuantity = By.xpath("//div[contains(@class,\"checkout-order-item-theme-2\")]//div[contains(@class,\"flash-sale\")]/ancestor::div[contains(@class,\"order-item-img\")]/following-sibling::div//div[@class=\"quantity\"]");
    private String flashSaleTagXP = ".//div[@class=\"flash-sale\"]/img";
    //Xpath can user for normal product and flash sale product
    private String productPriceXP = "./ancestor::div[contains(@class,\"order-item-img\")]/following-sibling::div//div[contains(@class,\"price-after-discount\")]";
    private String productQuantityXP = "./ancestor::div[contains(@class,\"order-item-img\")]/following-sibling::div//div[@class=\"quantity\"]";
    private String productNameXP = "./ancestor::div[contains(@class,\"order-item-img\")]/following-sibling::div//div[contains(@class,\"name-info\")]/div[contains(@class,\"name\")]";
    private String upQuantityBtnXP = "./ancestor::div[contains(@class,\"order-item-img\")]/following-sibling::div//*[@class=\"quantity-icon\"][2]";
    private String downQuantityBtnXP = "./ancestor::div[contains(@class,\"order-item-img\")]/following-sibling::div//*[@class=\"quantity-icon\"][1]";
    private By okayBtn = By.xpath("//div[contains(@class,\"-modal-theme2\")]//div[contains(@class,\"ant-modal-footer\")]/button");
    //dialog
    private By confirmDialog = By.xpath("//div[contains(@class,\"-modal-theme2\")]");
    private String dialogContent = ".//div[contains(@class,\"-dialog-content\")]";
    private String okayBtnDialog = ".//div[contains(@class,\"ant-modal-footer\")]//button";
    //checkout successfully
    public By checkOutPopup = By.xpath("//div[contains(@class,\"check-out-cash-theme1-vn\")]");
    public By congratulationText = By.xpath("//div[contains(@class,\"check-out-cash-title1\")]/span");
    public By viewOrder = By.xpath("//div[contains(@class,\"create-order-status-dialog\")]//a");
    public By newOrder = By.xpath("//div[contains(@class,\"create-order-status-dialog\")]//div[@class=\"center\"]/button");
    public By toastMsg = By.xpath("//div[contains(@class,\"ant-notification-notice-message\")]");
    //related product
    private By relatedProduct = By.id("themeRelatedProductsCheckout");
    private By productCardSlide = By.xpath("//div[@id=\"themeRelatedProductsCheckout\"]//div[contains(@class,\"swiper-wrapper\")]");
    private By productCardList = By.xpath("//div[@id=\"themeRelatedProductsCheckout\"]//div[contains(@class,\"ant-card-body\")]");
    private String productCardNameXP = ".//div[contains(@class,\"product-name\")]";
    private String productCardSellingPriceXP = ".//div[contains(@class,\"price-box-left\")]/div[@class=\"product-price-with-discount\"]";
    //flash sale
    private String productCardFlashSaleBorderXP = ".//div[contains(@class,\"flash-sale-border\")]";
    private String productCardFlashSaleLogoXP = ".//img[contains(@class,\"flash-sale\")]";
    private String productCardOriginalPriceXP = ".//div[contains(@class,\"price-box-left\")]/div[@class=\"product-price\"]";
    private String productCardPercentDiscountTagXP = ".//div[contains(@class,\"promotion-label\")]/span";

    public CheckoutPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        addUserLocationPage = new AddUserLocationPage(driver);
        flashSalePage = new FlashSalePage(driver);
    }

    public Boolean checkDisplayOfUserName() {
        try {
            helper.waitUtilElementVisible(driver.findElement(userName));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean checkDisplayOfPhoneNumber() {
        try {
            helper.waitUtilElementVisible(driver.findElement(userName));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void checkDisplayTotalQuantity() {
        helper.checkDisplayElement(totalQuantity);
    }

    public Boolean checkValueTotalQuantity(String expected) {
        helper.waitUtilElementVisible(driver.findElement(totalQuantity));
        actualRS = driver.findElement(totalQuantity).getText().trim();
        return actualRS.contains(expected);
    }

    public Boolean checkValueOfUsername() {
        return driver.findElement(userName).getAttribute("value").trim().equals(dataTest.USERNAME);
    }

    public Boolean checkValueOfSubTotalQuantity(String expected) {
        helper.waitForPresence(subTotalAmount);
        String subtotalAmountStr = helper.formatCurrencyToThousandWithoutSymbol(driver.findElement(subTotalAmount).getText());
        actualRS = "Actual: " + subtotalAmountStr + " Expected: " + expected;
        return subtotalAmountStr.equals(expected);
    }

    //flash sale

    /**
     * Apply for one product flash sale
     *
     * @return
     */
    public Boolean checkDisplayOfFlashSaleBorder() {
        return helper.checkDisplayElement(flashSaleImageBorder);
    }

    /**
     * Apply for one product flash sale
     *
     * @return
     */
    public Boolean checkDisplayOfFlashSaleTag() {
        return helper.checkDisplayElement(flashSaleImageTag);
    }

    /**
     * Apply for one product flash sale
     *
     * @return
     */
    public Boolean checkDisplayOfFlashSaleLabel() {
        return helper.checkDisplayElement(promotionLabelCheckout);
    }

    /**
     * Check flash sale / normal product following isFlashSale
     *
     * @param flashSaleProduct got when add to cart. Include: name, original price, flash sale price
     * @param isFlashSale
     * @param iscLickCart
     * @param isLogin
     * @return
     */
    public Boolean checkCheckoutPriceWhenFlashSale(JsonAPIAdminReader.FlashSaleProduct flashSaleProduct, Boolean isFlashSale, Boolean iscLickCart, Boolean isLogin, Boolean isEnterAddress) {
        helper.waitForJStoLoad();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isLogin) {
            flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, isEnterAddress, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        } else {
            if (iscLickCart) {
                helper.pressPageUpAction();
                commonPagesTheme2().homePage.clickCartIcon();
                checkOutLoginPage = commonPagesTheme2().homePage.clickCheckoutOnCart();
            }
        }
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        if (isFlashSale) {
            helper.visibleOfLocated(productDetailsSection);
            List<WebElement> productCartFSList = helper.getElementList(flashSaleImageBorder);
            if (productCartFSList.size() > 0) {
                for (WebElement element : productCartFSList) {
                    //check name
                    if (productName.equals(element.findElement(By.xpath(productNameXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale did not display with product: " + productName + "\n";
                        flag.add(false);
                    }
                    if (flashSalePrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + flashSalePrice + "\n";
                        flag.add(false);
                    }
                    //check flash sale tag
                    try {
                        element.findElement(By.xpath(flashSaleTagXP)).isDisplayed();
                        flag.add(true);
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        actualRS = actualRS + "Flash sale tag did not display\n";
                        flag.add(false);
                    }
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Flash sale did not display with product: " + productName;
            }
        } else {
            helper.visibleOfLocated(productDetailsSection);
            List<WebElement> productCartNotFSList = helper.getElementList(notFlashSaleImage);
            if (productCartNotFSList.size() > 0) {
                for (WebElement element : productCartNotFSList) {
                    try {
                        helper.waitUtilElementVisible(element.findElement(By.xpath(productNameXP)));
                    } catch (Exception ex) {
                        Log.info(ex.getMessage());
                    }
                    if (productName.equals(element.findElement(By.xpath(productNameXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale Product name is incorrect. Actual: " + element.findElement(By.xpath(productNameXP)).getText().trim() + " Expected: " + productName + "\n";
                        System.out.println(actualRS);
                        flag.add(false);
                    }
                    if (originalPrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
                        System.out.println(actualRS);
                        flag.add(false);
                    }
                    //check flash sale tag
                    try {
                        element.findElement(By.xpath(flashSaleTagXP)).isDisplayed();
                        actualRS = actualRS + "Flash sale tag displayed on normal product\n";
                        flag.add(false);
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(true);
                    }
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Nornal product did not display with product: " + productName;
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * @param flashSaleProduct got when add to cart. Include: name, original price, flash sale price
     * @param cartQuantity
     * @param maximumLimit
     * @param isLogin
     * @param isClickCart
     * @return
     */
    public Boolean checkCheckoutQuantityWhenFlashSale(JsonAPIAdminReader.FlashSaleProduct flashSaleProduct, int cartQuantity, int maximumLimit, int actualQuantity, Boolean isLogin, Boolean isClickCart, Boolean isEnterAddress) {
        helper.waitForJStoLoad();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isLogin) {
            flashSalePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, isEnterAddress, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        } else {
            if (isClickCart) {
                helper.pressPageUpAction();
                commonPagesTheme2().homePage.clickCartIcon();
                checkOutLoginPage = commonPagesTheme2().homePage.clickCheckoutOnCart();
            }
        }
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        int quantity = 0, remainingQuantity = 0, cartList = 0;
        if (actualQuantity >= maximumLimit) {
            if (maximumLimit < cartQuantity) {
                quantity = maximumLimit;
                remainingQuantity = cartQuantity - maximumLimit;
                cartList = 2;
            } else if (maximumLimit > cartQuantity) {
                quantity = cartQuantity;
                cartList = 2;
            } else {
                quantity = cartQuantity;
                cartList = 1;
            }
        } else {
            if (actualQuantity < cartQuantity) {
                quantity = actualQuantity;
                remainingQuantity = cartQuantity - actualQuantity;
                cartList = 2;
            } else if (actualQuantity > cartQuantity) {
                quantity = cartQuantity;
                cartList = 2;
            } else {
                quantity = cartQuantity;
                cartList = 1;
            }
        }
        int load = 0;
        if (cartList > 1) helper.sleep(2);
        while (load < 5) {
            List<WebElement> boxProductElementList = helper.getElementList(boxProductList);
            if (boxProductElementList.size() != cartList) {
                boxProductElementList = helper.getElementList(boxProductList);
                load++;
            } else break;
        }
        List<WebElement> productCartFSList = helper.getElementList(flashSaleImageBorder);
        if (productCartFSList.size() == 1) {
            for (WebElement element : productCartFSList) {
                if (flashSalePrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + flashSalePrice + "\n";
                    flag.add(false);
                }
                //check flash sale tag
                try {
                    element.findElement(By.xpath(flashSaleTagXP)).isDisplayed();
                    flag.add(true);
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    actualRS = actualRS + "Flash sale tag did not display\n";
                    flag.add(false);
                }
                //check quantity of fs product
                if (String.valueOf(quantity).equals(element.findElement(By.xpath(productQuantityXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(productQuantityXP)).getText().trim() + " Expected: " + quantity + "\n";
                    flag.add(false);
                }
            }
        } else {
            flag.add(false);
            actualRS = actualRS + "Flash sale did not display with product: " + productName;
        }
        List<WebElement> productCartNotFSList = helper.getElementList(notFlashSaleImage);
        if (productCartNotFSList.size() == 1) {
            for (WebElement element : productCartNotFSList) {
                try {
                    helper.waitUtilElementVisible(element.findElement(By.xpath(productNameXP)));
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                }
                if (originalPrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
                if (String.valueOf(remainingQuantity).equals(element.findElement(By.xpath(productQuantityXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(productQuantityXP)).getText().trim() + " Expected: " + remainingQuantity + "\n";
                    flag.add(false);
                }
            }
        } else {
            flag.add(false);
            actualRS = actualRS + "Nornal product did not display with product: " + productName;
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfFlashSaleEndedDialog() {
        return helper.checkDisplayElement(confirmDialog);
    }

    public Boolean checkContentConfirmEndedFlashSaleDialog() {
        List<String> keyList = new ArrayList<>();
        actualRS = "";
        String currentLanguage = commonPagesTheme2().homePage.getCurrentLanguage();
        keyList.add("flashSale");
        keyList.add("flashSaleEndNotification");
        if (checkDisplayOfFlashSaleEndedDialog()) {
            String actualText = driver.findElement(confirmDialog).findElement(By.xpath(dialogContent)).getText();
            return actualText.equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList));
        } else {
            actualRS = "End flash sale notification dialog did not display";
            return false;
        }
    }

    public List<String> getProductFlashSaleInformation(String productName) {
        List<String> productList = new ArrayList<>();
        helper.waitForPresence(flashSaleImageBorder);
        String name = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productNameXP)).getText();
        productList.add(productName);
        if (name.equals(productName)) {
            productList.add(driver.findElement(flashSaleImageBorder).findElement(By.xpath(productPriceXP)).getText());
        }
        return productList;
    }

    public Boolean checkQuantityFlashSale(Map<String, String> productFlashSale) {
        List<Boolean> flag = new ArrayList<>();
        String quantity = "";
        helper.waitForJStoLoad();
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        List<WebElement> flashSaleProductNameElementList = helper.getElementList(flashSaleProductName);
        List<WebElement> flashSaleProductQuantityElementList = helper.getElementList(flashSaleProductQuantity);
        for (int i = 0; i < flashSaleProductNameElementList.size(); i++) {
            //check flash sale product name
            if (productFlashSale.containsKey(flashSaleProductNameElementList.get(i).getText())) {
                quantity = productFlashSale.get(flashSaleProductNameElementList.get(i).getText());
                System.out.println("Name: " + flashSaleProductNameElementList.get(i).getText() + ", Quantity: " + flashSaleProductQuantityElementList.get(i).getText());
                //check flash sale product quantity
                if (quantity.equals(flashSaleProductQuantityElementList.get(i).getText())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Quantity of " + flashSaleProductNameElementList.get(i).getText() + "is incorrect. Actual: " + flashSaleProductQuantityElementList.get(i).getText() + " Expected: " + quantity + "\n";
                    flag.add(false);
                }
            } else {
                actualRS = actualRS + "Checkout not included " + flashSaleProductQuantityElementList.get(i).getText() + "\n";
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    //checkout action
    //TODO debug for issue api loaded slowly
    public void checkoutAction(String currentLanguage, Boolean isEnterAddress, String address, int addressIndex) {
        int count = 5;
        int loopNo = count;
        helper.visibleOfLocated(bannerHeaderCheckout);
        helper.pressPageDownAction();
        try {
            helper.waitForPresence(subTotalAmount);
            helper.visibleOfLocated(subTotalAmount);
            helper.scrollByJS(driver.findElement(subTotalAmount));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.pressEndAction();
        }
        helper.waitForJStoLoad();
        waitTotalLoaded();
        try {
            helper.waitForPresence(completeBtn);
            helper.clickBtn(driver.findElement(completeBtn));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitForJStoLoad();
            waitTotalLoaded();
            helper.clickByJS(driver.findElement(completeBtn));
        }
        System.out.println("address");
        if (isEnterAddress) {
            try {
                if (addUserLocationPage.checkDisplayOfDeliveryInput()) {
                    addUserLocationPage.onlyFillDeliveryAddressNoClear(address, addressIndex);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
        }
        try {
            try {
                helper.waitForPresence(viewOrder);
                helper.getElement(viewOrder).isDisplayed();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                if (isEnterAddress) {
                    try {
                        addUserLocationPage.onlyFillDeliveryAddressNoClear(address, addressIndex);
                    } catch (Exception ex1) {
                        Log.info(ex1.getMessage());
                    }
                }
                helper.waitForPresence(completeBtn);
                helper.waitForJStoLoad();
                waitTotalLoaded();
                helper.clickByJS(driver.findElement(completeBtn));
            }
            helper.sleep(2);
            helper.checkDisplayElement(viewOrder);
            while (!helper.checkDisplayElement(viewOrder) && loopNo > 0) {
                if (helper.checkDisplayElement(viewOrder)) break;
                helper.refreshPage();
                try {
                    helper.waitForPresence(commonPagesTheme2().homePage.footer);
                    helper.scrollByJS(driver.findElement(commonPagesTheme2().homePage.footer));
                } catch (Exception ex2) {
                    Log.info(ex2.getMessage());
                }
                helper.waitForJStoLoad();
                helper.waitForPresence(subTotalAmount);
                waitTotalLoaded();
                try {
                    helper.waitForPresence(completeBtn);
                    helper.clickByJS(driver.findElement(completeBtn));
                } catch (Exception ex1) {
                    Log.info(ex1.getMessage());
                    helper.clickByJS(driver.findElement(completeBtn));
                }
                try {
                    if (addUserLocationPage.checkDisplayOfDeliveryInput()) {
                        addUserLocationPage.onlyFillDeliveryAddressNoClear(address, addressIndex);
                    }
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                }
                loopNo--;
                clickOkayBtn();
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        List<String> keyList = new ArrayList<>();
        keyList.add("congratulation");
        loopNo = count;
        try {
            helper.checkDisplayElement(viewOrder);
            helper.waitForTextToBe(driver.findElement(congratulationText), jsonReader.localeReader(currentLanguage, "order", keyList));
            helper.waitForPresence(checkOutPopup);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println(125);
            helper.checkDisplayElement(viewOrder);
            while (!helper.checkDisplayElement(viewOrder) && loopNo > 0) {
                helper.refreshPage();
                try {
                    helper.waitForPresence(commonPagesTheme2().homePage.footer);
                    helper.scrollByJS(driver.findElement(commonPagesTheme2().homePage.footer));
                } catch (Exception ex2) {
                    Log.info(ex2.getMessage());
                }
                helper.waitForJStoLoad();
                waitTotalLoaded();
                helper.sleep(1);
                try {
                    helper.clickByJS(driver.findElement(completeBtn));
                    System.out.println("9");
                } catch (Exception ex1) {
                    Log.info(ex1.getMessage());
                    System.out.println(124);
                    int loopNo2 = count;
                    helper.checkDisplayElement(viewOrder);
                    while (!helper.checkDisplayElement(viewOrder) && loopNo2 > 0) {
                        helper.refreshPage();
                        try {
                            helper.waitForPresence(commonPagesTheme2().homePage.footer);
                            helper.scrollByJS(driver.findElement(commonPagesTheme2().homePage.footer));
                        } catch (Exception ex2) {
                            Log.info(ex2.getMessage());
                            System.out.println("catch 1");
                        }
                        helper.waitForJStoLoad();
                        waitTotalLoaded();
                        helper.sleep(1);
                        try {
                            try {
                                helper.clickByJS(driver.findElement(completeBtn));
                                System.out.println("10");
                            } catch (Exception ex2) {
                                Log.info(ex2.getMessage());
                                helper.clickByJS(driver.findElement(completeBtn));
                                System.out.println("11");
                            }
                        } catch (Exception ex2) {
                            Log.info(ex2.getMessage());
                        }
                        loopNo2--;
                    }
                }
                loopNo--;
                System.out.println(loopNo);
            }
        }
    }

    public Boolean clickOkayBtn() {
        List<WebElement> okayList = helper.getElementList(okayBtn);
        if (okayList.size() > 0) {
            for (WebElement element : okayList) {
                try {
                    helper.waitUtilElementVisible(element);
                    helper.clickBtn(element);
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    helper.clickByJS(element);
                }
            }
            actualRS = "Dialog display more than 1";
        } else {
            actualRS = "Dialog did not display";
        }
        System.out.println("size " + okayList.size());
        return (okayList.size() == 1);
    }

    //view order
    public Boolean checkDisplayOfViewOrder() {
        return helper.checkDisplayElement(viewOrder);
    }

    public String viewOrderAfterCheckout() {
        helper.visibleOfLocated(checkOutPopup);
        helper.visibleOfLocated(viewOrder);
        try {
            helper.clickBtn(driver.findElement(viewOrder));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            helper.clickByJS(driver.findElement(viewOrder));
        }
        System.out.println(driver.getCurrentUrl());
        helper.waitForURLContains("my-profile/3");
        return helper.getCurrentURL();
    }

    public void clearAllCart() {
        helper.waitForJStoLoad();
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        helper.waitForVisibleAllElements(deleteList);
        List<WebElement> deleteBTNList = helper.getElementList(deleteList);
        if (deleteBTNList.size() == 0) deleteBTNList = helper.getElementList(deleteList);
        int size = deleteBTNList.size();
        while (helper.checkDisplayElement(commonPagesTheme2().homePage.cartQuantity)) {
            if (deleteBTNList.size() < 0) break;
            for (int i = 0; i < size; i++) {
                try {
                    helper.waitUtilElementVisible(deleteBTNList.get(0));
                    helper.clickBtn(deleteBTNList.get(0));
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    try {
                        helper.clickByJS(deleteBTNList.get(0));
                    } catch (Exception exception1) {
                        Log.info(exception1.getMessage());
                        helper.refreshPage();
                        if (addUserLocationPage.checkDisplayOfDeliveryPanel())
                            addUserLocationPage.onlyFillDeliveryAddress(addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
                        deleteBTNList = helper.getElementList(deleteList);
                        if (deleteBTNList.size() > 0) try {
                            helper.clickByJS(deleteBTNList.get(0));
                        } catch (Exception exception2) {
                            Log.info(exception2.getMessage());
                            helper.clickBtn(deleteBTNList.get(0));
                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Log.info(e.getMessage());
                }
                deleteBTNList = helper.getElementList(deleteList);
            }
        }
    }

    private void waitTotalLoaded() {
        helper.waitForJStoLoad();
        helper.waitForPresence(subTotalAmount);
        helper.waitForTextToBePresent(subTotalAmount, "1");
    }

    public void clickCompleteBtn(String address, int addressIndex) {
        helper.visibleOfLocated(bannerHeaderCheckout);
        helper.pressPageDownAction();
        try {
            helper.waitForPresence(subTotalAmount);
            helper.scrollByJS(driver.findElement(subTotalAmount));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.pressEndAction();
        }
        helper.waitForJStoLoad();
        helper.waitForPresence(subTotalAmount);
        helper.visibleOfLocated(subTotalAmount);
        helper.waitForJStoLoad();
        int total = 0;
        int loopNo = 3;
        while (total == 0 && loopNo > 0) {
            total = 0;
            try {
                total = helper.convertToIntegerWithEnd(driver.findElement(subTotalAmount).getText(), "đ", 4);
            } catch (Exception ex4) {
                Log.info(ex4.getMessage());
            }
            loopNo--;
        }
        try {
            helper.clickBtn(driver.findElement(completeBtn));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(driver.findElement(completeBtn));
        }
    }

    public Boolean checkDisplayOfNotificationDialog() {
        return helper.checkDisplayElement(confirmDialog);
    }

    public Boolean checkDisplayOfNotificationContent() {
        return helper.checkDisplayElementByElement(driver.findElement(confirmDialog).findElement(By.xpath(dialogContent)));
    }

    public Boolean checkDisplayOfNotificationOkayBtn() {
        return helper.checkDisplayElement(okayBtn);
    }

    public void changeAddress(String address, int addressIndex) {
        helper.waitForPresence(changeAddressBtn);
        helper.visibleOfLocated(changeAddressBtn);
        helper.clickElement(changeAddressBtn);
        addUserLocationPage.onlyFillDeliveryAddress(address, addressIndex);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.info(e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            helper.waitUtilElementVisible(driver.findElement(toastMsg));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("Toast msg did not display");
        }
    }

    public List<String> getFlashSaleKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("flashSaleEndNotification");
        return keyList;
    }

    public List<String> getOverLimitedKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("description");
        keyList.add("overLimited");
        return keyList;
    }

    public List<String> getMinimumPurchaseKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("description");
        keyList.add("minimumPurchaseValue");
        return keyList;
    }

    public List<String> getHasBeenChangedKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("description");
        keyList.add("hasBeenChanged");
        return keyList;
    }

    public Boolean checkContentDialog(String currentLanguage) {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        List<String> keyList = getFlashSaleKeyList();
        helper.waitForPresence(confirmDialog);
        List<WebElement> dialogList = helper.getElementList(confirmDialog);
        System.out.println("size dialog " + dialogList.size());
        WebElement dialog;
        if (dialogList.size() > 0) {
            if (dialogList.size() > 1) {
                dialog = dialogList.get(1);
                flag.add(false);
                actualRS = actualRS + "Dialog displayed more than 1.\n";
            } else {
                dialog = dialogList.get(0);
                flag.add(true);
            }
            if (helper.checkDisplayElementByElement(dialog.findElement(By.xpath(dialogContent)))) flag.add(true);
            else {
                actualRS = actualRS + "Dialog content did not display" + "\n";
                flag.add(false);
            }
            if (helper.checkDisplayElementByElement(dialog.findElement(By.xpath(okayBtnDialog)))) flag.add(true);
            else {
                actualRS = actualRS + "Dialog okay btn did not display" + "\n";
                flag.add(false);
            }
            if (helper.checkTextWithLanguage(currentLanguage, dialog.findElement(By.xpath(dialogContent)), "text", flashSaleDataTest.FLASH_SALE_PAGE, keyList))
                flag.add(true);
            else {
                actualRS = actualRS + "Dialog content is incorrect. Actual: " + dialog.findElement(By.xpath(dialogContent)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList) + "\n";
                flag.add(false);
            }
        } else {
            flag.add(false);
            actualRS = "Dialog did not display";
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkContentDialog(String currentLanguage, String page, List<String> keyList) {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.waitForPresence(confirmDialog);
        List<WebElement> dialogList = helper.getElementList(confirmDialog);
        WebElement dialog;
        if (dialogList.size() > 0) {
            if (dialogList.size() > 1) {
                dialog = dialogList.get(1);
                flag.add(false);
                actualRS = actualRS + "Dialog displayed more than 1.\n";
            } else {
                dialog = dialogList.get(0);
                flag.add(true);
            }
            helper.waitUtilElementVisible(dialog);
            if (checkDisplayOfNotificationDialog()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog did not display" + "\n";
                flag.add(false);
            }
            if (checkDisplayOfNotificationContent()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog content did not display" + "\n";
                flag.add(false);
            }
            if (checkDisplayOfNotificationOkayBtn()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog button did not display" + "\n";
                flag.add(false);
            }
            if (helper.checkTextWithLanguage(currentLanguage, dialog.findElement(By.xpath(dialogContent)), "text", page, keyList))
                flag.add(true);
            else {
                actualRS = actualRS + "Dialog content is incorrect. Actual: " + dialog.findElement(By.xpath(dialogContent)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, page, keyList) + "\n";
                flag.add(false);
            }
            keyList.clear();
            keyList.add("generalUse");
            keyList.add("okay");
            if (helper.checkTextWithLanguage(currentLanguage, dialog.findElement(By.xpath(okayBtnDialog)), "text", flashSaleDataTest.FLASH_SALE_PAGE, keyList))
                flag.add(true);
            else {
                actualRS = actualRS + "Dialog button is incorrect. Actual: " + dialog.findElement(By.xpath(okayBtnDialog)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, dataTest.PAGE, keyList) + "\n";
                flag.add(false);
            }
        } else {
            actualRS = actualRS + "Dialog did not display.\n";
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkPriceOfProduct(boolean isFlashSale, FlashSaleProduct flashSaleProduct) {
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        helper.waitForPresence(productDetailsSection);
        helper.scrollByJS(driver.findElement(productDetailsSection));
        actualRS = "";
        expectedRS = "";
        List<WebElement> productList = new ArrayList<>();
        String price = "";
        if (isFlashSale) {
            productList = helper.getElementList(flashSaleImageBorder);
            expectedRS = flashSalePrice;
        } else {
            productList = helper.getElementList(notFlashSaleImage);
            expectedRS = originalPrice;
        }
        for (WebElement element : productList) {
            helper.scrollByJS(element);
            if (productName.equalsIgnoreCase(element.findElement(By.xpath(productNameXP)).getText()))
                price = element.findElement(By.xpath(productPriceXP)).getText().trim();
        }
        actualRS = "Price is wrong. Actual: \"" + price + "\" Expected: \"" + expectedRS + "\"";
        return price.equals(expectedRS);
    }

    //todo updated

    /**
     * for one product on cart
     *
     * @param product
     * @param addProduct
     */
    private void clickUpQuantityWithElement(WebElement product, int addProduct) {
        helper.waitUtilElementVisible(product);
        Log.info("Before: " + product.findElement(By.xpath(productQuantityXP)).getText());
        System.out.println("Before: " + product.findElement(By.xpath(productQuantityXP)).getText());
        String totalQuantityStr = driver.findElement(totalQuantityNumber).getText();
        int oldTotalQuantity = Integer.parseInt(totalQuantityStr.substring(totalQuantityStr.lastIndexOf("(") + 1));
        int total = oldTotalQuantity + addProduct;
        int loopNo = 50;
        while (oldTotalQuantity < total && loopNo > 0) {
            try {
                helper.clickBtn(product.findElement(By.xpath(upQuantityBtnXP)));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            helper.waitForTextToBePresent(totalQuantityNumber, String.valueOf(oldTotalQuantity + 1));
            totalQuantityStr = driver.findElement(totalQuantityNumber).getText();
            oldTotalQuantity = Integer.parseInt(totalQuantityStr.substring(totalQuantityStr.lastIndexOf("(") + 1));
            System.out.println(oldTotalQuantity + " Total: " + total + "\n");
        }
        System.out.println("After: " + product.findElement(By.xpath(productQuantityXP)).getText());
        Log.info("After: " + product.findElement(By.xpath(productQuantityXP)).getText());
        loopNo--;
    }

    private void clickDownQuantityWithElement(WebElement product, int minusProduct) {
        helper.waitUtilElementVisible(product);
        String totalQuantityStr = driver.findElement(totalQuantityNumber).getText();
        int oldTotalQuantity = Integer.parseInt(totalQuantityStr.substring(totalQuantityStr.lastIndexOf("(") + 1));
        int total = oldTotalQuantity - minusProduct;
        while (oldTotalQuantity > total) {
            helper.clickBtn(product.findElement(By.xpath(downQuantityBtnXP)));
            helper.waitForTextToBePresent(totalQuantityNumber, String.valueOf(oldTotalQuantity - 1));
            totalQuantityStr = driver.findElement(totalQuantityNumber).getText();
            oldTotalQuantity = Integer.parseInt(totalQuantityStr.substring(totalQuantityStr.lastIndexOf("(") + 1));
        }
    }

    /**
     * pply for one product
     *
     * @param addProduct    // todo
     * @param maxinmumLimit
     * @param cartQuantity
     */
    public void upQuantityWithElementProductFlashSale(int addProduct, int maxinmumLimit, int cartQuantity) {
        helper.waitForPresence(bannerHeaderCheckout);
        WebElement product = helper.getElement(flashSaleImageBorder);
        product = helper.getElement(flashSaleImageBorder);
        int flashSaleQuantity = Integer.parseInt(product.findElement(By.xpath(productQuantityXP)).getText());
        int loopNo = 20;
        while (maxinmumLimit >= flashSaleQuantity && loopNo > 0) {
            try {
                clickUpQuantityWithElement(product, addProduct);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            cartQuantity++;
            loopNo--;
        }
    }

    public void upQuantityWithElementProduct(int addProduct) {
        helper.waitForPresence(bannerHeaderCheckout);
        WebElement product;
        try {
            product = helper.getElement(notFlashSaleImage);
        } catch (Exception exc) {
            Log.info(exc.getMessage());
            product = helper.getElement(flashSaleImageBorder);
        }
        clickUpQuantityWithElement(product, addProduct);
    }

    public void downQuantityWithElementProduct(int minusProduct) {
        helper.waitForPresence(bannerHeaderCheckout);
        WebElement product;
        try {
            try {
                product = helper.getElement(notFlashSaleImage);
            } catch (Exception exc) {
                Log.info(exc.getMessage());
                product = helper.getElement(flashSaleImageBorder);
            }
            List<WebElement> productList = new ArrayList<>();
            clickDownQuantityWithElement(product, minusProduct);
        } catch (Exception exc) {
            Log.info(exc.getMessage());
            try {
                product = helper.getElement(notFlashSaleImage);
            } catch (Exception exc1) {
                Log.info(exc1.getMessage());
                product = helper.getElement(flashSaleImageBorder);
            }
            List<WebElement> productList = new ArrayList<>();
            clickDownQuantityWithElement(product, minusProduct);
        }
    }

    public void downQuantityWithElementProductNotFlashSale(int minusProduct) {
        helper.waitForPresence(bannerHeaderCheckout);
        WebElement product = helper.getElement(notFlashSaleImage);
        clickDownQuantityWithElement(product, minusProduct);
    }

    /**
     * @param minimumPurchase
     * @param cartQuantity
     * @param originalPrice   integer because price cannot be Decimal
     * @param flashSalePrice  integer because flash sale price cannot be Decimal
     * @return
     */
    public Boolean checkMaximumPurchase(int minimumPurchase, int cartQuantity, int originalPrice, int flashSalePrice) {
        helper.waitForPresence(subTotalAmount);
        helper.visibleOfLocated(subTotalAmount);
        helper.waitUtilElementVisible(driver.findElement(subTotalAmount));
        totalOriginalPrice = cartQuantity * originalPrice;
        int validPrice = 0;
        if (totalOriginalPrice < minimumPurchase) {
            validPrice = minimumPurchase - totalOriginalPrice;
            while (validPrice < minimumPurchase) {
                upQuantityWithElementProduct(1);
                helper.waitForJStoLoad();
                cartQuantity++;
                totalOriginalPrice = cartQuantity * originalPrice;
                if (totalOriginalPrice > minimumPurchase) break;
                validPrice = minimumPurchase - totalOriginalPrice;
            }
        }
        totalOriginalPrice = cartQuantity * originalPrice;
        int totalLastPrice = totalOriginalPrice - originalPrice + flashSalePrice; //check price has enough condition to apply flash sale price
        int numberFSProduct = (totalOriginalPrice - minimumPurchase) / (originalPrice - flashSalePrice);
        if (totalLastPrice < minimumPurchase) {
            upQuantityWithElementProduct(1);
            cartQuantity++;
            totalOriginalPrice += originalPrice;
            numberFSProduct = 1;
        }
        if (numberFSProduct == 0) {
            numberFSProduct = cartQuantity;
        } else {
            helper.waitForJStoLoad();
            int priceDiscount = originalPrice - flashSalePrice;
            validPrice = totalOriginalPrice - minimumPurchase;
            numberFSProduct = (validPrice / priceDiscount);//validPrice / priceDiscount;
        }
        totalOriginalPrice = (cartQuantity - numberFSProduct) * originalPrice + numberFSProduct * flashSalePrice;
        cartQuantityNew = cartQuantity;
        helper.waitForJStoLoad();
        helper.waitInvisibleByLocated(flashSaleImageBorder);
        return checkValueOfSubTotalQuantity(helper.formatCurrencyToThousandWithoutSymbol(totalOriginalPrice));
    }

    /**
     * @param cartQuantity
     * @param originalPrice integer because price cannot be Decimal
     * @return
     */
    public Boolean checkMaximumPurchaseNoFlashSale(int cartQuantity, int originalPrice) {
        helper.waitForPresence(subTotalAmount);
        helper.visibleOfLocated(subTotalAmount);
        helper.waitUtilElementVisible(driver.findElement(subTotalAmount));
        String totalQuantityStr = driver.findElement(totalQuantityNumber).getText();
        int totalQuantity = Integer.parseInt(totalQuantityStr.substring(totalQuantityStr.lastIndexOf("(") + 1));
        List<Boolean> flag = new ArrayList<>();
        if (totalQuantity != cartQuantity) {
            flag.add(false);
            actualRS = actualRS + "Quantity is incorrect. Actual: " + totalQuantity + " Expected: " + cartQuantity;
        }
        totalOriginalPrice = originalPrice * cartQuantity;
        helper.waitForJStoLoad();
        return checkValueOfSubTotalQuantity(helper.formatCurrencyToThousandWithoutSymbol(totalOriginalPrice));
    }

    public int getCartQuantity() {
        return cartQuantityNew;
    }

    public int gettotalOriginalPrice() {
        return totalOriginalPrice;
    }

    /**
     * apply for one flash sale product
     *
     * @return if flash sale border is not display -> true
     */
    public Boolean decreaseFlashSaleProductQuantity() {
        helper.waitForPresence(flashSaleImageBorder);
        helper.visibleOfLocated(flashSaleImageBorder);
        String quantityStr = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP)).getText();
        int quantity = Integer.parseInt(quantityStr);
        while (quantity > 0) {
            downQuantityWithElementProduct(1);
            helper.waitForJStoLoad();
            try {
                quantityStr = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP)).getText();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                break;
            }
            quantity = Integer.parseInt(quantityStr);
        }
        helper.waitForJStoLoad();
        actualRS = "flash sale product still display";
        return !checkDisplayOfFlashSaleBorder();
    }

    public Boolean checkQuantity(Boolean isFlashSale, int quantity) {
        helper.waitForJStoLoad();
        WebElement element;
        if (isFlashSale) {
            helper.waitForPresence(flashSaleImageBorder);
            helper.waitForPresence(flashSaleImageBorder);
            helper.visibleOfLocated(flashSaleImageBorder);
            element = driver.findElement(flashSaleImageBorder);
        } else {
            helper.waitForPresence(notFlashSaleImage);
            helper.waitForPresence(notFlashSaleImage);
            helper.visibleOfLocated(notFlashSaleImage);
            element = driver.findElement(notFlashSaleImage);
        }
        try {
            actualRS = "Flash sale quantity is incorrect. Actual:" + element.findElement(By.xpath(productQuantityXP)).getText();
            return (element.findElement(By.xpath(productQuantityXP)).getText().equals(String.valueOf(quantity)));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualRS = exception.getMessage() + "\n\nFlash sale product did not display.";
            return false;
        }
    }

    //language
    public Boolean checkLanguageOfDialog(String page, List<String> keyList) {
        List<Boolean> flag = new ArrayList<>();
        String currentLanguage = commonPagesTheme2().homePage.getCurrentLanguage();
        String checkedLanguage = currentLanguage;
        int index = 0;
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        List<WebElement> languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
        helper.waitUtilElementVisible(driver.findElement(confirmDialog));
        if (languageList.get(0).getText().equals(language)) index = 1;
        else index = 0;
        commonPagesTheme2().homePage.clickLanguage();
        //check default language
        flag.add(checkContentDialog(currentLanguage, page, keyList));
        languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            try {
                helper.waitForPresence(confirmDialog);
                helper.waitUtilElementVisible(driver.findElement(confirmDialog));
                helper.waitUtilElementVisible(languageList.get(i));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            flag.add(checkContentDialog(currentLanguage, page, keyList));
            helper.refreshPage();
            helper.pressPageUpAction();
            helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
            helper.waitUtilElementVisible(driver.findElement(confirmDialog));
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(commonPagesTheme2().homePage.languageOptions);
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

    //edit order
    public Boolean openEditOrderFromCheckout() {
        helper.waitForPresence(productDetailsSection);
        helper.visibleOfLocated(productDetailsSection);
        helper.visibleOfLocated(productImage);
        try {
            helper.clickBtn(driver.findElement(productImage));
        } catch (ElementClickInterceptedException e) {
            Log.info(e.getMessage());
            helper.clickByJS(driver.findElement(productImage));
        }
        return commonPagesTheme2().editOrderPage.checkDisplayOfEditCart();
    }

    //related product
    public Boolean checkDisplayOfRelated() {
        return helper.checkDisplayElement(relatedProduct);
    }

    //flash sale
    public Boolean checkRelatedProductWhenFlashSale(String flashSaleKey, Boolean isFlashSale) {
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.waitForJStoLoad();
        //check flash sale component
        if (helper.checkDisplayElement(relatedProduct)) {
            helper.visibleOfLocated(relatedProduct);
            helper.scrollToElementByJS(driver.findElement(relatedProduct));
            helper.presenceOfAllElementsLocatedBy(productCardList);
            List<WebElement> cardList = helper.getElementList(productCardList);
            List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
            int checked = 0;
            if (isFlashSale) {
                for (int y = 0; y < flashSaleProductList.size(); y++) {
                    FlashSaleProduct flashSaleProduct = flashSaleProductList.get(y);
                    for (int i = 0; i < cardList.size(); i++) {
                        helper.scrollByJS(cardList.get(i));
                        helper.waitUtilElementVisible(cardList.get(i));
                        String productName = cardList.get(i).findElement(By.xpath(productCardNameXP)).getText();
                        if (flashSaleProduct.getName().toLowerCase().contains(flashSalePage.removeSizeFromProductNameSize(productName).toLowerCase())) {
                            checked++;
                            //Check flash sale border
                            try {
                                if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(productCardFlashSaleBorderXP)))) {
                                    flag.add(true);
                                } else {
                                    actualRS = actualRS + "Flash sale border at " + (i + 1) + " did not display\n";
                                    flag.add(false);
                                }
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                actualRS = actualRS + "Flash sale border at " + (i + 1) + " did not display\n";
                                flag.add(false);
                            }
                            //Check flash sale logo
                            try {
                                if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(productCardFlashSaleLogoXP)))) {
                                    flag.add(true);
                                } else {
                                    actualRS = actualRS + "Flash sale logo at " + (i + 1) + " did not display\n";
                                    flag.add(false);
                                }
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                actualRS = actualRS + "Flash sale logo at " + (i + 1) + " did not display\n";
                                flag.add(false);
                            }
                            //Check name
                            if (cardList.get(i).findElement(By.xpath(productCardNameXP)).getText().equals(flashSaleProduct.getName()))
                                flag.add(true);
                            else {
                                actualRS = actualRS + "Name is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(productCardNameXP)).getText() + " Expected: " + flashSaleProduct.getName() + "\n";
                                flag.add(false);
                            }
                            //check flash sale price
                            String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
                            String sellingPrice = "";
                            try {
                                sellingPrice = cardList.get(i).findElement(By.xpath(productCardSellingPriceXP)).getText();
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                helper.refreshPage();
                                helper.presenceOfAllElementsLocatedBy(productCardList);
                                cardList = helper.getElementList(productCardList);
                                helper.scrollByJS(cardList.get(i));
                                sellingPrice = cardList.get(i).findElement(By.xpath(productCardSellingPriceXP)).getText();
                            }
                            if (sellingPrice.equals(flashSalePrice)) flag.add(true);
                            else {
                                actualRS = actualRS + "Flash sale price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(productCardSellingPriceXP)).getText() + " Expected: " + flashSalePrice + "\n";
                                flag.add(false);
                            }
                            //check original price
                            String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
                            if (cardList.get(i).findElement(By.xpath(productCardOriginalPriceXP)).getText().equals(originalPrice))
                                flag.add(true);
                            else {
                                actualRS = actualRS + "Original price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(productCardOriginalPriceXP)).getText() + " Expected: " + originalPrice + "\n";
                                flag.add(false);
                            }
                            try {
                                helper.scrollByJS(cardList.get(i));
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                            }
                            //check display of percent discount tag
                            if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(productCardPercentDiscountTagXP)))) {
                                flag.add(true);
                                //check value of percent discount tag
                                int newPrice = flashSaleProduct.getFlashSalePrice();
                                int oldPrice = flashSaleProduct.getPrice();
                                String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(oldPrice, newPrice));
                                if (cardList.get(i).findElement(By.xpath(productCardPercentDiscountTagXP)).getText().equals(discountPercentFormatted))
                                    flag.add(true);
                                else {
                                    actualRS = actualRS + "Percent is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(productCardPercentDiscountTagXP)).getText() + " Expected: " + discountPercentFormatted + "\n";
                                    flag.add(false);
                                }
                            } else {
                                actualRS = actualRS + "Card in position " + (i + 1) + " did not display or broken UI: the discount percent label\n";
                                flag.add(false);
                            }
                        }
                    }
                }
                if (checked == flashSaleProductList.size()) flag.add(true);
                else {
                    flag.add(false);
                    actualRS = actualRS + "The number of Flash sale display incorrect. Actual: " + checked + " Expected: " + flashSaleProductList.size() + "\n";
                }
                if (flag.contains(false)) return false;
                else return true;
            } else {
                for (int y = 0; y < flashSaleProductList.size(); y++) {
                    FlashSaleProduct flashSaleProduct = flashSaleProductList.get(y);
                    for (int i = 0; i < cardList.size(); i++) {
                        helper.scrollByJS(cardList.get(i));
                        helper.waitUtilElementVisible(cardList.get(i));
                        String productName = cardList.get(i).findElement(By.xpath(productCardNameXP)).getText();
                        if (flashSaleProduct.getName().equalsIgnoreCase(productName)) {
                            //Check flash sale border
                            try {
                                if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(productCardFlashSaleBorderXP)))) {
                                    actualRS = actualRS + "Flash sale border at " + (i + 1) + " did not display\n";
                                    flag.add(false);
                                } else {
                                    flag.add(true);
                                }
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                flag.add(true);
                            }
                            //Check flash sale logo
                            try {
                                if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(productCardFlashSaleLogoXP)))) {
                                    actualRS = actualRS + "Flash sale logo at " + (i + 1) + " did not display\n";
                                    flag.add(false);
                                } else {
                                    flag.add(true);
                                }
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                flag.add(true);
                            }
                            //check flash sale price
                            String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
                            String sellingPrice = "";
                            try {
                                sellingPrice = cardList.get(i).findElement(By.xpath(productCardSellingPriceXP)).getText();
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                helper.refreshPage();
                                helper.presenceOfAllElementsLocatedBy(productCardList);
                                cardList = helper.getElementList(productCardList);
                                helper.scrollByJS(cardList.get(i));
                                sellingPrice = cardList.get(i).findElement(By.xpath(productCardSellingPriceXP)).getText();
                            }
                            if (sellingPrice.equals(originalPrice)) {
                                flag.add(true);
                            } else {
                                flag.add(false);
                                actualRS = actualRS + "Flash sale price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(productCardSellingPriceXP)).getText() + " Expected: " + originalPrice + "\n";
                            }
                            //check original price
                            try {
                                if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(productCardOriginalPriceXP)))) {
                                    actualRS = actualRS + "Flash sale original price at " + (i + 1) + " is displaying\n";
                                    flag.add(false);
                                } else {
                                    flag.add(true);
                                }
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                flag.add(true);
                            }
                            try {
                                helper.scrollByJS(cardList.get(i));
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                            }
                            //check display of percent discount tag
                            try {
                                if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(productCardPercentDiscountTagXP)))) {
                                    actualRS = actualRS + "Card in position " + (i + 1) + " did not display or broken UI: the discount percent label\n";
                                    flag.add(false);
                                } else {
                                    flag.add(true);
                                }
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                                flag.add(true);
                            }
                        }
                    }
                }
                if (flag.contains(false)) return false;
                else return true;
            }
        } else {
            actualRS = actualRS + "Flash sale component did not display\n";
            return false;
        }
    }

    //TODO auto split flash sale product
    public Boolean checkAutoSplitProductQuantityWhenFlashSale(FlashSaleProduct flashSaleProduct, int cartQuantity, int maximumLimit, int actualQuantity) {
        helper.waitForJStoLoad();
        actualRS = "";
        String currentLanguage = commonPagesTheme2().homePage.getCurrentLanguage();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        int quantity = 0, remainingQuantity = 0, boxList = 0;
        if (maximumLimit < cartQuantity) {
            quantity = maximumLimit;
            remainingQuantity = cartQuantity - maximumLimit;
            boxList = 2;
        } else {
            quantity = cartQuantity;
            remainingQuantity = maximumLimit - cartQuantity;
            boxList = 1;
        }
        int fsOldQuantity = quantity;
        int fsNewQuantity = 0;
        int nfsOldQuantity = 0;
        int addProduct = 0;
        List<WebElement> productBoxList = helper.getElementList(boxProductList);
        upQuantityWithElementProductFlashSale(1, maximumLimit, cartQuantity);
        int newCartQuantity = cartQuantity + 1;
        System.out.println(checkCheckoutQuantityWhenFlashSale(flashSaleProduct, newCartQuantity, maximumLimit, actualQuantity, true, false, true));
//        if (productBoxList.size() == boxList) {
//            WebElement productFSItem = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP));
//            if (productFSItem.getText().equals(String.valueOf(fsOldQuantity))) {
//                flag.add(true);
//            } else {
//                actualRS = actualRS + "flash sale quantity is wrong. Actual: " + productFSItem.getText() + " Expected: " + fsOldQuantity + "\n";
//            }
//            //Up quantity of product flash sale
//            addProduct = remainingQuantity + addNoFlashSaleItem;
//            System.out.println(addNoFlashSaleItem);
//            while (addProduct > 0) {
//                System.out.println(addProduct);
//                upQuantityWithElementProductFlashSale(1, maximumLimit, cartQuantity);
//                if (addProduct <= addNoFlashSaleItem) {
//                    flag.add(checkContentDialog(currentLanguage, promotionDataTest.PAGE, getOverLimitedKeyList()));
//                    clickOkayBtn();
//                }
//                addProduct--;
//                cartQuantity++;
//            }
//            boxList = 2; //auto split product to flash sale and normal product
//            productBoxList = helper.getElementList(boxProductList); //get new product box list
//            if (productBoxList.size() == boxList) {
//                flag.add(true);
//            } else {
//                actualRS = actualRS + "Box list size is incorrect after up quantity. Actual: " + productBoxList.size() + " Expected: " + boxList + "\n";
//                flag.add(false);
//            }
//            WebElement productNoFSItem = driver.findElement(notFlashSaleImage).findElement(By.xpath(productQuantityXP));
//            productFSItem = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP)); //get flash sale product list again
//            //check new quantity of flash sale
//            try {
//                nfsOldQuantity = Integer.parseInt(productNoFSItem.getText());
//                fsNewQuantity = Integer.parseInt(productFSItem.getText());
//            } catch (Exception exception) {
//                Log.info(exception.getMessage());
//                productNoFSItem = driver.findElement(notFlashSaleImage).findElement(By.xpath(productQuantityXP));
//                productFSItem = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP));
//                fsNewQuantity = Integer.parseInt(productFSItem.getText());
//                nfsOldQuantity = Integer.parseInt(productNoFSItem.getText());
//            }
//            System.out.println(fsNewQuantity);
//            System.out.println(fsOldQuantity);
//            System.out.println(nfsOldQuantity);
//            if (fsOldQuantity == fsNewQuantity) {
//                flag.add(false);
//                actualRS = actualRS + "Flash sale quantity is incorrect after up quantity. Actual: " + fsNewQuantity + " Expected: " + (addNoFlashSaleItem - fsOldQuantity) + "\n";
//            } else flag.add(true);
//            //check quantity of no flash sale item
//            System.out.println(addNoFlashSaleItem);
//            if (nfsOldQuantity == addNoFlashSaleItem) {
//                flag.add(true);
//            } else {
//                flag.add(false);
//                actualRS = actualRS + "Normal product quantity is incorrect after upquantity. Actual: " + nfsOldQuantity + " Expected: " + addNoFlashSaleItem + "\n";
//            }
//        } else {
//            flag.add(false);
//            actualRS = actualRS + "Flash sale did not display with product: " + productName;
//        }
        if (flag.contains(false)) return false;
        else return true;
    }

    //TODO auto split flash sale product
    public Boolean checkReduceFlashSaleProductQuantityWhenFlashSale(FlashSaleProduct flashSaleProduct, int cartQuantity, int maximumLimit) {
        helper.waitForJStoLoad();
        actualRS = "";
        String currentLanguage = commonPagesTheme2().homePage.getCurrentLanguage();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        int quantity = 0, remainingQuantity = 0, boxList = 0;
        if (maximumLimit < cartQuantity) {
            quantity = maximumLimit;
            remainingQuantity = cartQuantity - maximumLimit;
            boxList = 2;
        } else {
            quantity = cartQuantity;
            remainingQuantity = maximumLimit - cartQuantity;
            boxList = 1;
        }
        int fsOldQuantity = quantity;
        int fsNewQuantity = 0;
        int nfsOldQuantity = 0;
        int reduceProduct = 0;
        List<WebElement> productBoxList = helper.getElementList(boxProductList);
        if (productBoxList.size() == boxList) {
            WebElement productFSItem = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP));
            if (productFSItem.getText().equals(String.valueOf(fsOldQuantity))) {
                flag.add(true);
            } else {
                actualRS = actualRS + "flash sale quantity is wrong. Actual: " + productFSItem.getText() + " Expected: " + fsOldQuantity + "\n";
            }
            WebElement productNoFSItem = driver.findElement(notFlashSaleImage).findElement(By.xpath(productQuantityXP));
            //Up quantity of product flash sale
            reduceProduct = remainingQuantity;
            while (reduceProduct >= 0) {
                productNoFSItem = driver.findElement(notFlashSaleImage);
                System.out.println("1 " + reduceProduct);
                downQuantityWithElementProductNotFlashSale(1);
                if (reduceProduct == maximumLimit) {
                    boxList = 1; //auto remove normal product to flash sale and normal product
                }
                reduceProduct--;
                System.out.println(reduceProduct);
            }
            productBoxList = helper.getElementList(boxProductList); //get new product box list
            if (productBoxList.size() == boxList) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Box list size is incorrect after up quantity. Actual: " + productBoxList.size() + " Expected: " + boxList + "\n";
                flag.add(false);
                try {
                    productNoFSItem = driver.findElement(notFlashSaleImage).findElement(By.xpath(productQuantityXP));
                    flag.add(false);
                    actualRS = actualRS + "Normal product still display with quantity: " + productNoFSItem.getText() + "\n";
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    flag.add(true);
                }
            }
            productFSItem = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP)); //get flash sale product list again
            //check new quantity of flash sale
            try {
                fsNewQuantity = Integer.parseInt(productFSItem.getText());
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                productFSItem = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP));
                fsNewQuantity = Integer.parseInt(productFSItem.getText());
            }
            if (maximumLimit == fsNewQuantity) {
                flag.add(false);
                actualRS = actualRS + "Flash sale quantity is incorrect after up quantity. Actual: " + fsNewQuantity + " Expected: " + maximumLimit + "\n";
            } else flag.add(true);
        } else {
            flag.add(false);
            actualRS = actualRS + "Flash sale did not display with product: " + productName;
        }
        if (flag.contains(false)) return false;
        else return true;
    }
}
