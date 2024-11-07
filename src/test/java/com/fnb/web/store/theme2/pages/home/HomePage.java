package com.fnb.web.store.theme2.pages.home;

import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme2.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme2.pages.login.DataTest;
import com.fnb.web.store.theme2.pages.myOrder.MyOrderPage;
import com.fnb.web.store.theme2.pages.my_profile.MyAddressListDataTest;
import com.fnb.web.store.theme2.pages.my_profile.MyOrderDataTest;
import com.fnb.web.store.theme2.pages.my_profile.MyProfileDataTest;
import com.fnb.web.store.theme2.pages.product_details.ProductDetailsDataTest;
import com.fnb.web.store.theme2.pages.flashsale.FlashSaleDataTest;
import com.fnb.web.store.theme2.pages.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class HomePage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    private LocalDateTime currentDateTime;
    private FlashSaleDataTest flashSaleDataTest;
    public Map<String, String> timer = new HashMap<>();
    public String flashSaleName;
    private HomeDataTest homeDataTest;
    private JsonReader jsonReader;
    public String actualRS;
    public String selectedSize = "";
    public String expectedRS;
    private CheckOutLoginPage checkOutLoginPage;
    private MyProfileDataTest myProfileDataTest;
    private MyOrderDataTest orderDataTest;
    private MyAddressListDataTest addressListDataTest;
    private DataTest loginDataTest;
    private ProductDetailsDataTest productDetailsDataTest;
    private JsonAPIAdminReader jsonAPIAdminReader;
    private MyOrderPage myOrderPage;
    private CheckoutPage checkoutPage;
    public String size = "";
    //locator
    public By storeLogo = By.xpath("//img[@class=\"ant-image-img\"]");
    //language
    public By languageSwitch = By.xpath("//a[contains(@class,\"link-language\")]");
    public By languageTxt = By.xpath("//a[contains(@class,\"link-language\")]/span");
    public By dialogContent = By.xpath("//div[contains(@class,\"change-language-header-theme2\")]//div[contains(@class,\"ant-popover-inner-content\")]");
    public By languageOptions = By.xpath("//div[contains(@class,\"change-language-header-theme2\")]//div[contains(@class,\"ant-popover-inner-content\")]//a");
    public By vietnameseOption = By.xpath("(//div[contains(@class,\"change-language-header-theme2\")]//div[contains(@class,\"ant-popover-inner-content\")]//a)[1]");
    public By englishOption = By.xpath("(//div[contains(@class,\"change-language-header-theme2\")]//div[contains(@class,\"ant-popover-inner-content\")]//a)[2]");
    public By accountIcon = By.xpath("//a[contains(@class,\"user-icon\")]");
    public By accountInfo = By.xpath("//div[contains(@class,\"user-profile-contain\")]");
    public By accountLogout = By.xpath("//div[@class=\"login_content_theme1\"]");
    public By loginRegisterDiv = By.xpath("//div[@class=\"login_content_theme2\"]//span");
    //banner
    public By bannerTrack = By.id("themeBanner");
    //------------------ address
    public By selectReceiver = By.xpath("//div[@class=\"delivery-address-header-box\"]");
    private By getSelectReceiverTxt = By.xpath("//div[@class=\"delivery-address-header-box\"]//div[@class=\"text-delivery-address\"]/span");
    private By selectAddressButton = By.xpath("//div[contains(@class,\"nav-top-right\")]//div[contains(@class,\"delivery-address-selector-theme2\")]//div[contains(@class,\"select-branch-dialog-class\")]");
    private By selectMethodLocationArrow = By.xpath("//div[contains(@class,\"nav-top-right\")]//div[contains(@class,\"delivery-address-selector-theme2\")]//div[contains(@class,\"right-box\")]");
    private By selectBranchButton = By.xpath("//div[contains(@class,\"nav-top-right\")]//div[contains(@class,\"delivery-address-selector-theme2\")]//div[contains(@class,\"address-detail-popover-content\")]/div[2]");
    public By deliverySpan = By.xpath("//div[contains(@class,\"modal-delivery-address-selector-theme2\")]//div[contains(@class,\"header-container-child-\")][1]"); //find after open address dialog
    public By pickupSpan = By.xpath("//div[contains(@class,\"modal-delivery-address-selector-theme2\")]//div[contains(@class,\"header-container-child-\")][2]");
    private By deliveryBranchBtn = By.xpath("//div[contains(@class,\"store-branch-address-selector\")]//div[contains(@class,\"store-branch-address-selector-child\")][1]"); //find after open pickup dialog
    private By pickupBranchBtn = By.xpath("//div[contains(@class,\"store-branch-address-selector\")]//div[contains(@class,\"store-branch-address-selector-child\")][2]");
    public By enterAddressField = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//input");
    private By clearIcon = By.xpath("//span[contains(@class,\"ant-input-clear-icon\")]/*[name()=\"svg\"]");
    public By addressBoxParent = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[@class=\"delivery-address-popover\"]");
    public By addressBoxItems = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[@class=\"address-box\"]");
    public By selectedAddressToast = By.xpath("//div[@class = \"ant-message-notice-content\"]");
    private By storeBranchParent = By.xpath("//div[contains(@class,\"store-branch-addresses-parent\")]");
    private By branchNameList = By.xpath("//div[contains(@class,\"store-branch-addresses-parent\")]//p[contains(@class,\"address-label\")]");
    private By branchAddressList = By.xpath("//div[contains(@class,\"store-branch-addresses-parent\")]//p[@class=\"address\"]");
    private By selectBbranchText = By.xpath("//div[@id=\"deliveryAddressSelector\"]//div[contains(@class,\"store-branch-address-select-button\")]/span");
    private By locationMethod = By.xpath("//span[contains(@class,\"text-delivery-to\")]");
    private By locationAddress = By.xpath("//div[contains(@class,\"text-delivery-address\")]/span");
    //------------------- cart
    public By cartIcon = By.xpath("//a[@class=\"pointer\"]");
    public By cartContainer = By.xpath("//div[contains(@class,\"cart-header-theme-2\")]");
    public By cartCheckout = By.xpath("//button[contains(@class,\"action-checkout\")]");
    public By cartQuantity = By.xpath("//span[contains(@class,\"ant-scroll-number-only-unit\")]");
    public By cartPrice = By.xpath("//div[contains(@class,\"total-price-checkout\")]");
    public By productCartList = By.xpath("//div[contains(@class,\"shopping-cart-content\")]//li[contains(@class,\"list-items-in-cart\")]");
    public String productCartPriceXP = ".//div[contains(@class,\"total-product-price\")]";
    public By productFlashSaleCartList = By.xpath("//div[contains(@class,\"item-image-border-flash-sale\")]/ancestor::li[contains(@class,\"list-items-in-cart\")]");
    public By productNotFSImageList = By.xpath("//div[contains(@class,\"item-image-border\") and not(contains(@class,\"-flash-sale\"))]/ancestor::li[contains(@class,\"list-items-in-cart\")]");
    public String productCartFlashSaleBorderXP = ".//div[@class=\"flash-sale-border\"]";
    public String productCartFlashSaleTagXP = ".//img[contains(@class,\"flash-sale-logo\")]";
    private String productCartImageXP = ".//img[contains(@class,\"ant-image-img\")]";
    public By productCartImage = By.xpath("//div[contains(@class,\"item-image-border\")]");
    public String productCartNameXP = ".//p[contains(@class,\"cart-item-name\")]";
    public String productCartOptionXP = ".//span[contains(@class,\"cart-item-option\")]";
    public String productCartQuantity = ".//div[contains(@class,\"item-quantity\")]/span";
    //footer
    public By footer = By.id("themeFooter");

    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    //language
    public String getCurrentLanguage() {
        try {
            return helper.getCurrentLanguageHelper(languageTxt);
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            helper.refreshPage();
            return helper.getCurrentLanguageHelper(languageTxt);
        }
    }

    public void clickLanguage() {
        helper.visibleOfLocated(languageSwitch);
        helper.waitUtilElementVisible(driver.findElement(languageSwitch));
        try {
            helper.clickBtn(driver.findElement(languageSwitch));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
        }
    }

    //--------------------- check display of elements after logged in
    public Boolean checkDisplayDefaultLogoutBtn() {
        return driver.findElement(accountLogout).isDisplayed();
    }

    //--------------------- checkUI
    public Boolean checkDisplayOfStoreLogo() {
        return helper.checkDisplayElement(storeLogo);
    }

    public void clickAccountIcon() {
        try {
            helper.waitForPresence(accountIcon);
            helper.visibleOfLocated(accountIcon);
            helper.waitUtilElementVisible(driver.findElement(accountIcon));
            helper.clickBtn(driver.findElement(accountIcon));
        } catch (Exception exception) {
            helper.clickByJS(helper.getElement(accountIcon));
        }
    }

    /**
     * navigate to Login page
     *
     * @return Login page
     */
    public LoginPage clickLoginRegister() {
        clickAccountIcon();
        WebElement loginRegister = driver.findElement(loginRegisterDiv);
        helper.waitUtilElementVisible(loginRegister);
        loginRegister.click();
        return new LoginPage(driver);
    }

    public void clickLogout() {
        clickAccountIcon();
        WebElement logout = driver.findElement(accountLogout);
        helper.waitUtilElementVisible(logout);
        logout.click();
    }

    //cart
    public void clickCartIcon() {
        helper.pressPageUpAction();
        helper.waitForPresence(cartIcon);
        try {
            helper.waitUtilElementVisible(driver.findElement(cartIcon));
            helper.clickBtn(driver.findElement(cartIcon));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.waitUtilElementVisible(driver.findElement(cartIcon));
            helper.clickByJS(driver.findElement(cartIcon));
        }
    }

    public CheckOutLoginPage clickCheckoutOnCart() {
        helper.waitForJStoLoad();
        helper.visibleOfLocated(cartCheckout);
        try {
            helper.waitUtilElementVisible(driver.findElement(cartCheckout));
            helper.clickBtn(driver.findElement(cartCheckout));
        } catch (Exception exception) {
            helper.clickByJS(driver.findElement(cartCheckout));
        }
        if (!helper.visibleOfLocated(commonPagesTheme2().checkoutPage.bannerHeaderCheckout)) {
            try {
                helper.clickBtn(driver.findElement(cartCheckout));
            } catch (Exception exception) {
                helper.refreshPage();
                helper.visibleOfLocated(cartIcon);
                helper.pressPageUpAction();
                try {
                    helper.clickBtn(driver.findElement(cartIcon));
                } catch (Exception exception1) {
                    Log.info(exception1.getMessage());
                    helper.clickJS(cartIcon);
                }
                helper.waitForPresence(cartCheckout);
                helper.visibleOfLocated(cartCheckout);
                helper.clickByJS(driver.findElement(cartCheckout));
            }
        }
        return new CheckOutLoginPage(driver);
    }

    //header
    public Boolean checkDisplayOfLogout() {
        return helper.checkDisplayElement(accountLogout);
    }

    //account
    public void loginSuccessfully(String phone, String password) {
        helper.waitForPresence(accountIcon);
        helper.visibleOfLocated(accountIcon);
        helper.clickBtn(driver.findElement(accountIcon));
        helper.visibleOfLocated(loginRegisterDiv);
        helper.clickBtn(driver.findElement(loginRegisterDiv));
        commonPagesTheme2().loginPage.loginPasswordSuccessfully(phone, password);
    }

    //logout
    public Boolean checkAfterLogout() {
        try {
            clickAccountIcon();
            helper.waitUtilElementVisible(driver.findElement(accountLogout));
            helper.clickBtn(driver.findElement(accountLogout));
            helper.visibleOfLocated(bannerTrack);
            clickAccountIcon();
        } catch (Exception exception) {
            Log.info("Page is not Homepage");
            clickAccountIcon();
        }
        return helper.checkDisplayElement(loginRegisterDiv);
    }

    //Banner
    public Boolean checkDisplayBanner() {
        return helper.visibleOfLocated(bannerTrack);
    }

//    //flash sale - Create flash sale
//    private String getFlashSaleName() {
//        Random random = new Random();
//        return flashSaleDataTest.FLASH_SALE_NAME.concat(String.valueOf(random.nextInt(100001)));
//    }
//
//    private List<String> getProductNameList() {
//        List<String> productNameFlashSale = new ArrayList<>();
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_1);
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_2);
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_3);
//        return productNameFlashSale;
//    }
//
//    private List<String> getProductNameListFull() {
//        List<String> productNameFlashSale = new ArrayList<>();
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_2);
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_3);
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_7);
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_9);
//
//        System.out.println(productNameFlashSale);
//        return productNameFlashSale;
//    }
//
//    private List<String> getProductNameListCheckSpecialBranch() {
//        List<String> productNameFlashSale = new ArrayList<>();
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_1);
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_2);
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_4);
//        return productNameFlashSale;
//    }
//
//    public void createFlashSale(int addStartMinute, int addEndMinute, Boolean checkBranch) {
//        List<String> productNameFlashSale = new ArrayList<>();
//        if (!checkBranch) productNameFlashSale = getProductNameList();
//        else productNameFlashSale = getProductNameListCheckSpecialBranch();
//        flashSaleName = getFlashSaleName();
//        System.out.println(flashSaleName);
//        currentDateTime = apiAminService.createFlashSaleList(productNameFlashSale, flashSaleName, addStartMinute, addEndMinute);
//        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        System.out.println("start: " + timer.get("start"));
//        System.out.println("end: " + timer.get("end"));
//    }
//
//    public void createFlashSaleWithMinimumPurchaseOrder(int addStartMinute, int addEndMinute, int maximumQuantity, int flashSaleQuantity, int minimumPurchaseOrder) {
//        List<String> productNameFlashSale = new ArrayList<>();
//        productNameFlashSale = getProductNameList();
//        flashSaleName = getFlashSaleName();
//        System.out.println(flashSaleName);
//        currentDateTime = apiAminService.createFlashSaleWithMinimumPurchaseOrder(productNameFlashSale, flashSaleName, addStartMinute, addEndMinute, maximumQuantity, flashSaleQuantity, minimumPurchaseOrder);
//        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        System.out.println("start: " + timer.get("start"));
//        System.out.println("end: " + timer.get("end"));
//    }
//
//    private List<String> getBranchNameListStrings() {
//        List<String> branchName = new ArrayList<>();
//        branchName.add(homeDataTest.BRANCH_NAME_STAG);
//        return branchName;
//    }
//
//    public void createFlashSaleSpecialBranch(int addStartMinute, int addEndMinute) {
//        int lastAddEndMinutes = 0;
//        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
//        List<String> productNameFlashSale = getProductNameListCheckSpecialBranch();
//        flashSaleName = getFlashSaleName();
//        System.out.println(flashSaleName);
//        List<String> branchNameList = getBranchNameListStrings();
//        currentDateTime = apiAminService.createFlashSaleWithSpecialBranch(productNameFlashSale, flashSaleName, addStartMinute, lastAddEndMinutes, branchNameList);
//        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        System.out.println("start: " + timer.get("start"));
//        System.out.println("end: " + timer.get("end"));
//    }
//
//    public void createFlashSaleNotFullVariations(int addStartMinute, int addEndMinute) {
//        int lastAddEndMinutes = 0;
//        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
//        List<String> productNameFlashSale = getProductNameList();
//        flashSaleName = getFlashSaleName();
//        System.out.println(flashSaleName);
//        currentDateTime = apiAminService.createFlashSaleListNotFullVariations(productNameFlashSale, flashSaleName, addStartMinute, lastAddEndMinutes);
//        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        System.out.println("start: " + timer.get("start"));
//        System.out.println("end: " + timer.get("end"));
//    }
//
//    public void createFlashSaleWithQuantity(int addStartMinute, int addEndMinute, int maximumLimit, int flashSaleQuantity) {
//        int lastAddEndMinutes = 0;
//        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
//        List<String> productNameFlashSale = new ArrayList<>();
//        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_1);
//        flashSaleName = getFlashSaleName();
//        System.out.println(flashSaleName);
//        currentDateTime = apiAminService.createFlashSaleWithQuantity(productNameFlashSale, flashSaleName, maximumLimit, flashSaleQuantity, addStartMinute, lastAddEndMinutes);
//        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        System.out.println("start: " + timer.get("start"));
//        System.out.println("end: " + timer.get("end"));
//    }
//
//    /**
//     * Create flash sale with special variation and included topping
//     *
//     * @param addStartMinute
//     * @param addEndMinute
//     * @param variation
//     * @param maximumLimit
//     * @param flashSaleQuantity
//     */
//    public void createFlashSaleVariationTopping(List<String> productNameFlashSale, int addStartMinute, int addEndMinute, String variation, Boolean isTopping, int maximumLimit, int flashSaleQuantity) {
//        int lastAddEndMinutes = 0;
//        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
//        flashSaleName = getFlashSaleName();
//        System.out.println(flashSaleName);
//        currentDateTime = apiAminService.createFlashSaleWithVariation(productNameFlashSale, flashSaleName, variation, isTopping, maximumLimit, flashSaleQuantity, addStartMinute, lastAddEndMinutes);
//        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        System.out.println("start: " + timer.get("start"));
//        System.out.println("end: " + timer.get("end"));
//    }
//
//    public void createFlashSaleWithVariation(int addStartMinute, int addEndMinute, String variation, int maximumLimit, int flashSaleQuantity) {
//        List<String> productNameFlashSale = getProductNameListFull();
//        createFlashSaleVariationTopping(productNameFlashSale, addStartMinute, addEndMinute, variation, true, maximumLimit, flashSaleQuantity);
//    }
//
//    public void createFlashSaleWithVariationWithoutTopping(int addStartMinute, int addEndMinute, String variation, int maximumLimit, int flashSaleQuantity) {
//        List<String> productNameFlashSale = getProductNameListFull();
//        createFlashSaleVariationTopping(productNameFlashSale, addStartMinute, addEndMinute, variation, false, maximumLimit, flashSaleQuantity);
//    }
//
//    public void deleteFlashSale(String flashSaleKey) {
//        apiAminService.deleteFlashSaleById(flashSaleKey);
//    }
//
//    public void stopFlashSale(String flashSaleKey) {
//        apiAminService.stopFlashSaleById(flashSaleKey);
//    }
//
//    public void updateTimeFlashSale(String nameFlashSale, int addStartMinute, int addEndMinute) {
//        currentDateTime = apiAminService.editTimeOfFlashSale(nameFlashSale, addStartMinute, addEndMinute);
//        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
//        System.out.println(timer.get("start"));
//        System.out.println(timer.get("end"));
//    }
//
//    public String getCurrentCreateFlashSaleTime() {
//        return "Create time: " + timer.get("start") + " End time: " + timer.get("end");
//    }
//
//    //check flash sale
//    public Boolean checkDisplayOfFlashSaleComponent() {
//        helper.waitForJStoLoad();
//        boolean isDisplay = helper.checkDisplayElement(flashSaleComponent);
//        if (isDisplay) {
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//            return true;
//        } else return false;
//    }
//
//    /**
//     * Get status of flash sale following language
//     *
//     * @param statusTab 0: ended
//     *                  1: end after
//     *                  2: coming
//     * @return
//     */
//    private List<String> getKeyListByStatus(int statusTab) {
//        List<String> keyList = new ArrayList<>();
//        keyList.clear();
//        keyList.add("flashSale");
//        if (statusTab == 0) keyList.add("ended");
//        else if (statusTab == 1) keyList.add("endAfter");
//        else keyList.add("beginAfter");
//        return keyList;
//    }
//
//    private String getLocaleForTabTitle(String currentLanguage, int status) {
//        List<String> keyList = new ArrayList<>();
//        keyList.clear();
//        keyList.add("flashSale");
//        if (status == 1) {
//            keyList.add("isHappening");
//        } else if (status == 0) {
//            keyList.add("ended");
//        } else {
//            keyList.add("coming");
//        }
//        return jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList);
//    }
//
//    /**
//     * Get status of flash sale following language
//     *
//     * @param status 0: ended
//     *               1: end after
//     *               2: coming
//     * @return
//     */
//    private String getFlashSaleLocale(int status) {
//        String currentLanguage = getCurrentLanguage();
//        String statusText = "";
//        List<String> keyList = getKeyListByStatus(status);
//        statusText = jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList);
//        return statusText;
//    }
//
//    public String getFlashSaleTabTime() {
//        checkDisplayOfFlashSaleComponent();
//        try {
//            helper.checkDisplayElement(flashSaleTabActive);
//            helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime)));
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//        }
//        try {
//            return helper.getElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime)).getText();
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//            return helper.getElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime)).getText();
//        }
//    }
//
//    public List<String> getFlashsSaleProductInformation(String flashSaleKey, String productName) {
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
//        List<String> productFlastSale = new ArrayList<>();
//        productFlastSale.add(productName);
//        System.out.println(productName);
//        System.out.println(flashSaleProductList);
//        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
//            if (flashSaleProduct.getName().equals(productName)) {
//                productFlastSale.add(helper.formatCurrencyToThousand(flashSaleProduct.getPrice()));
//                productFlastSale.add(helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice()));
//            }
//        }
//        return productFlastSale;
//    }
//
//
//    public Boolean checkActiveStatusTab(Boolean hasEnded, Boolean hasEndAfter, Boolean hasComing) {
//        String currentLanguage = getCurrentLanguage();
//        System.out.println(currentLanguage);
//        if (checkDisplayOfFlashSaleComponent() == true) {
//            helper.waitForPresence(flashSaleTabActive);
//            helper.visibleOfLocated(flashSaleTabActive);
//            String actualStr = driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText();
//            String statusText = "";
//            if (hasEndAfter) {
//                statusText = getLocaleForTabTitle(currentLanguage, 1).toUpperCase();
//                actualRS = "Actual: \"" + actualStr + "\" Expected: \"" + statusText + "\"";
//                return actualStr.equals(statusText);
//            } else if (!hasEndAfter && hasComing) {
//                statusText = getLocaleForTabTitle(currentLanguage, 2).toUpperCase();
//                actualRS = "Actual: \"" + actualStr + "\" Expected: \"" + statusText + "\"";
//                return actualStr.equals(statusText);
//            } else if (hasEnded && !hasEndAfter && !hasComing) {
//                statusText = getLocaleForTabTitle(currentLanguage, 0).toUpperCase();
//                actualRS = "Actual: \"" + actualStr + "\" Expected: " + statusText + "\"";
//                return actualStr.equals(statusText);
//            } else {
//                actualRS = "Not have active tab";
//                return false;
//            }
//        } else {
//            if (hasComing && hasEndAfter) {
//                actualRS = "Flash sale did not display";
//                return false;
//            } else return true;
//        }
//    }
//
//    public void clickFlashSaleTab(int statusTab) {
//        if (checkDisplayOfFlashSaleComponent() == true) {
//            helper.visibilityOfAllElementsLocatedBy(flashSaleTabList);
//            List<WebElement> tabListElement = helper.getElementList(flashSaleTabList);
//            if (tabListElement.size() == 3) {
//                helper.clickBtn(tabListElement.get(statusTab));
//            } else if (tabListElement.size() == 2) {
//                if (statusTab != 2) {
//                    helper.checkDisplayElementByElement(tabListElement.get(statusTab));
//                    helper.clickBtn(tabListElement.get(statusTab));
//                } else {
//                    helper.checkDisplayElementByElement(tabListElement.get(1));
//                    helper.clickBtn(tabListElement.get(1));
//                }
//            } else {
//                helper.checkDisplayElementByElement(tabListElement.get(0));
//                helper.clickBtn(tabListElement.get(0));
//            }
//        }
//    }
//
//    public Boolean checkTabFlashSaleAfterCreatedFlashSale(Boolean create, int addStartMinute, int addEndMinute, int statusBar, Boolean isSpecialBranch) {
//        String currentLanguage = getCurrentLanguage();
//        actualRS = "";
//        List<Boolean> flag = new ArrayList<>();
//        if (create) {
//            //create flash sale from now
//            createFlashSale(addStartMinute, addEndMinute, isSpecialBranch);
//        }
//        helper.refreshPage();
//        helper.waitForJStoLoad();
//        //check flash sale component
//        if (checkDisplayOfFlashSaleComponent()) {
//            helper.visibleOfLocated(flashSaleComponent);
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//            //check header
//            if (helper.checkDisplayElement(flashSaleBanner)) {
//                flag.add(true);
//                try {
//                    flag.add(driver.findElement(flashSaleBanner).getCssValue("background-image").contains("background-flash-sale"));
//                } catch (Exception exception) {
//                    Log.info(exception.getMessage());
//                    flag.add(false);
//                    actualRS = actualRS + "Actual background-image is " + helper.getCSSValue(flashSaleBanner, "background-image") + "\n";
//                }
//            } else {
//                actualRS = actualRS + "Banner did not display\n";
//                flag.add(false);
//            }
//            //check tab
//            List<String> keyList = new ArrayList<>();
//            helper.presenceOfAllElementsLocatedBy(flashSaleTabActive);
//            helper.visibilityOfAllElementsLocatedBy(flashSaleTabActive);
//            List<WebElement> tabList = helper.getElementList(flashSaleTabActive);
//            if (tabList.size() == 0) tabList = helper.getElementList(flashSaleTabActive);
//            if (tabList.size() == 1) {
//                keyList = getKeyListByStatus(statusBar);
//                int index = 0;
//                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag);
//            } else if (tabList.size() == 2) {
//                keyList = getKeyListByStatus(statusBar);
//                int index = tabList.size() - 1;
//                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag);
//            } else if (tabList.size() == 3) {
//                keyList = getKeyListByStatus(statusBar);
//                int index = statusBar;
//                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag);
//            } else {
//                actualRS = actualRS + tabList.size() + " is more/less than 3";
//                flag.add(false);
//            }
//            //check main session
//            if (helper.checkDisplayElement(flashSaleMainSession)) flag.add(true);
//            else {
//                actualRS = actualRS + "Flash sale main session did not display\n";
//                flag.add(false);
//            }
//            if (flag.contains(false)) return false;
//            else return true;
//        } else {
//            actualRS = actualRS + "Flash sale component did not display\n";
//            return false;
//        }
//    }
//
//    /**
//     * auto change status from Coming to End After
//     *
//     * @param addMinute
//     * @return
//     */
//    public Boolean waitTimeToChangeStatus(int addMinute, int status) {
//        actualRS = "";
//        List<Boolean> flag = new ArrayList<>();
//        LocalDateTime nowTime = LocalDateTime.now(ZoneId.systemDefault());
//        try {
//            helper.waitForPresence(flashSaleComponent);
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            helper.refreshPage();
//            helper.waitForPresence(flashSaleComponent);
//        }
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        String expectedRS = getFlashSaleLocale(status).toUpperCase();
//        LocalDateTime targetTime = currentDateTime.plusMinutes(addMinute).withSecond(0).withNano(0);
//        while (nowTime.isBefore(targetTime)) {
//            nowTime = LocalDateTime.now(ZoneId.systemDefault());
//            if (nowTime.isAfter(targetTime) || nowTime.isEqual(targetTime)) {
//                System.out.println(nowTime);
//                break;
//            }
//            helper.waitForJStoLoad();
//            actualRS = helper.getText(flashSaleCountdownTitle);
//            if (!actualRS.equals(expectedRS)) {
//                flag.add(true);
//            } else {
//                actualRS = "At " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " The selected status is display\nCurrent status is " + actualRS + "\n";
//                flag.add(false);
//            }
//        }
//        helper.waitForJStoLoad();
//        LocalDateTime no = LocalDateTime.now(ZoneId.systemDefault());
//        try {
//            helper.waitForTextToBePresent(flashSaleCountdownTitle, expectedRS);
//            flag.add(true);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            actualRS = actualRS + "After " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " The selected status did not display\n";
//            flag.add(false);
//        }
//        actualRS = actualRS + helper.getText(flashSaleCountdownTitle) + " still display\n";
//        Duration duration = Duration.between(nowTime, no);
//        long seconds = duration.getSeconds();
//        if (seconds > 3) {
//            flag.add(false);
//            actualRS = actualRS + "After 3s from " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " EndAfter display\n";
//        } else flag.add(true);
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    public String waitTimeChangeStatus(int addTime) {
//        actualRS = "";
//        LocalDateTime nowTime = LocalDateTime.now(ZoneId.systemDefault());
//        checkDisplayOfFlashSaleComponent();
//        LocalDateTime targetTime = currentDateTime.plusMinutes(addTime).withSecond(0).withNano(10000);
//        while (nowTime.isBefore(targetTime)) {
//            nowTime = LocalDateTime.now(ZoneId.systemDefault());
//        }
//        System.out.println("Changes status at time: " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm")));
//        return nowTime.format(DateTimeFormatter.ofPattern("HH:mm"));
//    }
//
//    private void checkFlashSaleActive(int statusBar, List<WebElement> tabList, int index, String currentLanguage, List<Boolean> flag) {
//        //check label
//        String expectedRS = "";
//        helper.waitForJStoLoad();
//        helper.sleep(0.5);
//        String expectedTabRS = getLocaleForTabTitle(currentLanguage, statusBar);
//        expectedRS = getFlashSaleLocale(statusBar);
//        if (tabList.get(index).findElement(By.xpath(flashSaleTabName)).getText().equals(expectedTabRS.toUpperCase()))
//            flag.add(true);
//        else {
//            actualRS = actualRS + "Tab title: " + tabList.size() + " has wrong text " + tabList.get(0).findElement(By.xpath(flashSaleTabName)).getText() + " Expected: " + expectedTabRS + "\n";
//            System.out.println(actualRS);
//            flag.add(false);
//        }
//        //check time coming and ended
//        if (statusBar != 1) {
//            if (!checkTimeByStatus(statusBar, index, tabList)) {
//                Boolean rs = checkTimeByStatus(statusBar, index, tabList);
//                if (rs == false)
//                    actualRS = actualRS + "Time wrong. Expected: " + timer.get("start") + " Actual: " + tabList.get(index).findElement(By.xpath(flashSaleTabTime)).getText();
//                flag.add(rs);
//            }
//        }
//        //check countdown label
//        if (driver.findElement(flashSaleCountdownTitle).getText().equals(expectedRS.toUpperCase())) flag.add(true);
//        else {
//            actualRS = actualRS + "Count down title: " + tabList.size() + " has wrong text " + driver.findElement(flashSaleCountdownTitle).getText() + " Expected: " + expectedRS + "\n";
//            System.out.println(actualRS);
//            flag.add(false);
//        }
//        WebElement countDownTime;
//        if (statusBar == 0) {
//            countDownTime = tabList.get(index).findElement(By.xpath(flashSaleCountdownZeroXP));
//        } else {
//            countDownTime = tabList.get(index).findElement(By.xpath(flashSaleCountdownXP));
//        }
//        if (helper.checkDisplayElementByElement(countDownTime)) {
//            List<WebElement> timeList = countDownTime.findElements(By.xpath(flashSaleCountdownNumberXP));
//            if (timeList.size() == 6) {
//                flag.add(true);
//                if (statusBar == 0) {
//                    int countZeroNumber = 0;
//                    for (int i = 0; i < timeList.size(); i++) {
//                        if (timeList.get(i).getText().equals("0")) {
//                            countZeroNumber++;
//                        }
//                    }
//                    if (countZeroNumber == timeList.size()) flag.add(true);
//                    else {
//                        flag.add(false);
//                        actualRS = actualRS + "Time is not zero " + timeList;
//                    }
//                }
//            } else {
//                actualRS = actualRS + "Count down display more than 6 numbers\n";
//                flag.add(false);
//            }
//        } else {
//            actualRS = actualRS + "Count down flip timer did not display\n";
//            flag.add(false);
//        }
//    }
//
//    private Boolean checkTimeByStatus(int statusBar, int index, List<WebElement> tabList) {
//        String timeCheck = "";
//        if (statusBar == 0) {
//            timeCheck = timer.get("end"); //Ended
//        } else if (statusBar == 2) {
//            timeCheck = timer.get("start"); //Coming
//        }
//        String cleanTime = tabList.get(index).findElement(By.xpath(flashSaleTabTime)).getText().replace("\n", "");
//        return cleanTime.equals(timeCheck);
//    }
//
//    public String clickAddToCartFromFlashSale(String flashSaleKey) {
//        actualRS = "";
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        String homeURL = driver.getCurrentUrl();
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        helper.presenceOfAllElementsLocatedBy(flashSaleItems);
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        Random random = new Random();
//        int productIndex = 0;
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        WebElement selectedItem;
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)));
//        selectedItem = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP));
//        int quantity = random.nextInt(flashSale.getFlashSaleProduct().get(productIndex).getMaximumLimit()) + 1;
//        String productFlashSaleName = selectedItem.getText().trim();
//        System.out.println(productFlashSaleName);
//        try {
//            helper.clickBtn(selectedItem);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(selectedItem);
//        }
//        System.out.println("quantity " + quantity);
//        commonPagesTheme2().productDetailsPage.addProductToCartWithQuantity(quantity);
//        driver.navigate().to(homeURL);
//        return productFlashSaleName;
//    }
//
//    /**
//     * Add to cart with quantity
//     *
//     * @param quantity
//     * @return
//     */
//    public String clickAddToCartFromFlashSaleWithQuantity(int productIndex, int quantity) {
//        actualRS = "";
//        helper.waitForJStoLoad();
//        try {
//            helper.waitForPresence(flashSaleComponent);
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//        }
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        try {
//            helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)));
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            helper.sleep(5);
//        }
//        WebElement selectedItem = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP));
//        String productFlashSaleName = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim();
//        System.out.println(productFlashSaleName);
//        try {
//            helper.clickBtn(selectedItem);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(selectedItem);
//        }
//        commonPagesTheme2().productDetailsPage.addProductToCartWithQuantity(quantity);
//        return productFlashSaleName;
//    }
//
//    public List<String> clickProductFlashSale(int productIndex) {
//        actualRS = "";
//        List<String> productAdded = new ArrayList<>();
//        helper.waitForJStoLoad();
//        helper.waitForPresence(flashSaleComponent);
//        helper.visibleOfLocated(flashSaleComponent);
//        try {
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            helper.refreshPage();
//            helper.visibleOfLocated(flashSaleComponent);
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        }
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        WebElement selectedItem;
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)));
//        selectedItem = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP));
//        productAdded.add(selectedItem.getText().trim());
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSalePriceXP)).getText().trim());
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim());
//        try {
//            helper.clickBtn(selectedItem);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(selectedItem);
//        }
//        return productAdded;
//    }
//
//    public List<String> clickAddToCartFlashSaleWithQuantityList(int productIndex, int quantity) {
//        List<String> productAdded = clickProductFlashSale(productIndex);
//        commonPagesTheme2().productDetailsPage.addProductToCartWithQuantity(quantity);
//        return productAdded;
//    }
//
//    public List<String> clickAddToCartFlashSaleSelectSize(String size, int productIndex, int quantity) {
//        actualRS = "";
//        List<String> productAdded = new ArrayList<>();
//        helper.waitForJStoLoad();
//        helper.waitForPresence(flashSaleComponent);
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        WebElement selectedItem;
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)));
//        selectedItem = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP));
//        String flashsalePrice = cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim();
//        try {
//            helper.clickBtn(selectedItem);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(selectedItem);
//        }
//        List<String> product = commonPagesTheme2().productDetailsPage.addProductToCartWithSize(size, quantity);
//        selectedSize = size;
//        System.out.println(product);
//        productAdded.add(product.get(0)); //getName
//        productAdded.add(product.get(1)); //getPrice
//        productAdded.add(flashsalePrice);
//        return productAdded;
//    }
//
//    public List<String> clickAddToCartFlashSaleSelectSizeAndTopping(String size, int productIndex, int quantity) {
//        actualRS = "";
//        List<String> productAdded = new ArrayList<>();
//        helper.waitForJStoLoad();
//        try {
//            helper.waitForPresence(flashSaleComponent);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//        }
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)));
//        WebElement selectedItem = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP));
//        String flashsalePrice = cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim();
//        try {
//            helper.clickBtn(selectedItem);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(selectedItem);
//        }
//        List<String> product = commonPagesTheme2().productDetailsPage.addProductToCartWithSizeAndTopping(size, quantity);
//        productAdded.add(product.get(0)); //getName
//        productAdded.add(product.get(1)); //getPrice
//        productAdded.add(flashsalePrice);
//        return productAdded;
//    }
//
//    public List<String> clickAddToCartFlashSaleSelectTopping(int productIndex, int quantity) {
//        actualRS = "";
//        List<String> productAdded = new ArrayList<>();
//        helper.waitForJStoLoad();
//        helper.visibleOfLocated(flashSaleComponent);
//        try {
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        } catch (Exception exception) {
//            helper.refreshPage();
//            helper.visibleOfLocated(flashSaleComponent);
//            helper.waitUtilElementVisible(driver.findElement(flashSaleComponent));
//            helper.scrollByJS(driver.findElement(flashSaleComponent));
//        }
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        //check clickable
//        try {
//            helper.scrollByJS(cardList.get(productIndex));
//        } catch (Exception exception) {
//            helper.refreshPage();
//            helper.visibleOfLocated(flashSaleComponent);
//            helper.scrollByJS(driver.findElement(flashSaleComponent));
//            cardList = helper.getElementList(flashSaleItems);
//            helper.scrollByJS(cardList.get(productIndex));
//        }
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)));
//        WebElement selectedItem = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP));
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim());
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim());
//        try {
//            helper.clickBtn(selectedItem);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(selectedItem);
//        }
//        productAdded.add(commonPagesTheme2().productDetailsPage.addProductToCartWithTopping(quantity));
//        return productAdded;
//    }
//
//    public Boolean checkCartWhenFlashSale(String productName, Boolean isClickCart) {
//        List<Boolean> flag = new ArrayList<>();
//        actualRS = "";
//        if (isClickCart) {
//            helper.pressPageUpAction();
//            clickCartIcon();
//        }
//        helper.waitForPresence(cartContainer);
//        helper.visibleOfLocated(cartContainer);
//        List<WebElement> productCartList = helper.getElementList(productFlashSaleCartList);
//        String productNameStr = "";
//        if (productCartList.size() > 0) {
//            try {
//                for (WebElement element : productCartList) {
//                    try {
//                        helper.waitUtilElementVisible(element.findElement(By.xpath(productCartNameXP)));
//                    } catch (Exception exception) {
//                        Log.error(exception.getMessage());
//                    }
//                    try {
//                        helper.scrollByJS(element.findElement(By.xpath(productCartNameXP)));
//                    } catch (Exception exception) {
//                        Log.error(exception.getMessage());
//                    }
//                    productNameStr = element.findElement(By.xpath(productCartNameXP)).getText().trim();
//                    if (productName.toLowerCase().contains(productNameStr.toLowerCase())) {
//                        flag.add(true);
//                        actualRS = actualRS + "Flash sale still display with product: " + productName + "\n";
//                    } else {
//                        actualRS = actualRS + "Flash sale did not display with product product Name: " + productName + " Acaual text: " + productNameStr + "\n";
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                }
//            } catch (NoSuchElementException exception) {
//                Log.error(exception.getMessage());
//                flag.add(false);
//                actualRS = actualRS + "Flash sale did not display with product: " + productName + "\n";
//            }
//        } else {
//            flag.add(false);
//            actualRS = actualRS + "Flash sale did not display with product: " + productName + "\n";
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    /**
//     * Use for check price when flash sale
//     *
//     * @param flashSaleProduct
//     * @param isFlashSale
//     * @param isClickCart
//     * @return
//     */
//    public Boolean checkCartPriceWhenFlashSale(FlashSaleProduct flashSaleProduct, Boolean isFlashSale, Boolean isClickCart) {
//        helper.waitForJStoLoad();
//        String productName = flashSaleProduct.getName();
//        String onlyName = removeSizeFromProductNameSize(productName);
//        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
//        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
//        List<Boolean> flag = new ArrayList<>();
//        actualRS = "";
//        if (isClickCart) {
//            helper.pressPageUpAction();
//            clickCartIcon();
//        }
//        helper.waitForPresence(cartContainer);
//        helper.visibleOfLocated(cartContainer);
//        if (isFlashSale) {
//            helper.visibleOfLocated(cartContainer);
//            List<WebElement> productCartFSList = helper.getElementList(productFlashSaleCartList);
//            if (productCartFSList.size() > 0) {
//                for (WebElement element : productCartFSList) {
//                    if (onlyName.equals(element.findElement(By.xpath(productCartNameXP)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Flash sale name displays incorrect. Expected: " + onlyName + " Actual: " + element.findElement(By.xpath(productCartNameXP)).getText() + "\n";
//                        flag.add(false);
//                    }
//                    if (productName.contains("(" + selectedSize + ")")) {
//                        if (element.findElement(By.xpath(productCartOptionXP)).getText().contains(selectedSize)) {
//                            flag.add(true);
//                        } else {
//                            actualRS = actualRS + "Flash sale size displays incorrect. Expected: " + selectedSize + " Actual: " + element.findElement(By.xpath(productCartOptionXP)).getText() + "\n";
//                            flag.add(false);
//                        }
//                    }
//                    if (flashSalePrice.equals(element.findElement(By.xpath(productCartPriceXP)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productCartPriceXP)).getText().trim() + " Expected: " + flashSalePrice + "\n";
//                        flag.add(false);
//                    }
//                    //check flash sale tag
//                    try {
//                        element.findElement(By.xpath(productCartFlashSaleTagXP)).isDisplayed();
//                        flag.add(true);
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        actualRS = actualRS + "Flash sale tag did not display\n";
//                        flag.add(false);
//                    }
//                    //check flash sale border
//                    try {
//                        element.findElement(By.xpath(productCartFlashSaleBorderXP)).isDisplayed();
//                        flag.add(true);
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        actualRS = actualRS + "Flash sale border did not display\n";
//                        flag.add(false);
//                    }
//                    //check total price on button
//                    if (flashSalePrice.equals(driver.findElement(cartPrice).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Flash sale price is incorrect. Actual: " + driver.findElement(cartPrice).getText() + " Expected: " + flashSalePrice + "\n";
//                        flag.add(false);
//                    }
//                }
//            } else {
//                flag.add(false);
//                actualRS = actualRS + "Flash sale did not display with product: " + productName;
//            }
//        } else {
//            helper.visibleOfLocated(cartContainer);
//            List<WebElement> productCartNotFSList = helper.getElementList(productNotFSImageList);
//            if (productCartNotFSList.size() > 0) {
//                for (WebElement element : productCartNotFSList) {
//                    try {
//                        helper.waitUtilElementVisible(element.findElement(By.xpath(productCartNameXP)));
//                    } catch (Exception ex) {
//                        Log.info(ex.getMessage());
//                    }
//                    if (onlyName.equals(element.findElement(By.xpath(productCartNameXP)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "None flash sale - product name is incorrect. Actual: " + element.findElement(By.xpath(productCartNameXP)).getText().trim() + " Expected: " + onlyName + "\n";
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                    if (productName.contains("(" + selectedSize + ")")) {
//                        if (element.findElement(By.xpath(productCartOptionXP)).getText().contains(selectedSize)) {
//                            flag.add(true);
//                        } else {
//                            actualRS = actualRS + "None flash sale - size displays incorrect. Expected: " + selectedSize + " Actual: " + element.findElement(By.xpath(productCartOptionXP)).getText() + "\n";
//                            flag.add(false);
//                        }
//                    }
//                    if (originalPrice.equals(element.findElement(By.xpath(productCartPriceXP)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(productCartPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                    //check flash sale tag
//                    try {
//                        element.findElement(By.xpath(productCartFlashSaleTagXP)).isDisplayed();
//                        flag.add(false);
//                        actualRS = actualRS + "Flash sale tag displays\n";
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        flag.add(true);
//                    }
//                    //check flash sale border
//                    try {
//                        element.findElement(By.xpath(productCartFlashSaleBorderXP)).isDisplayed();
//                        flag.add(false);
//                        actualRS = actualRS + "Flash sale border displays\n";
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        flag.add(true);
//                    }
//                    //check total price on button
//                    if (originalPrice.equals(driver.findElement(cartPrice).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Flash sale price is incorrect. Actual: " + driver.findElement(cartPrice).getText() + " Expected: " + originalPrice + "\n";
//                        flag.add(false);
//                    }
//                }
//            } else {
//                flag.add(false);
//                actualRS = actualRS + "Normal product did not display with product: " + productName;
//            }
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    /**
//     * use for check quantity after add flash sale product to cart
//     *
//     * @param flashSaleProduct
//     * @param cartQuantity
//     * @param maximumLimit
//     * @param isClickCart
//     * @return
//     */
//    public Boolean checkCartQuantityWhenFlashSale(FlashSaleProduct flashSaleProduct, int cartQuantity, int maximumLimit, Boolean isClickCart) {
//        helper.waitForJStoLoad();
//        String productName = flashSaleProduct.getName();
//        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
//        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
//        List<Boolean> flag = new ArrayList<>();
//        actualRS = "";
//        if (isClickCart) {
//            helper.pressPageUpAction();
//            clickCartIcon();
//            helper.waitForJStoLoad();
//        }
//        helper.waitForPresence(cartContainer);
//        helper.visibleOfLocated(cartContainer);
//        int quantity = 0, remainingQuantity = 0, cartList = 0;
//        if (maximumLimit < cartQuantity) {
//            quantity = maximumLimit;
//            remainingQuantity = cartQuantity - maximumLimit;
//            cartList = 2;
//        } else if (maximumLimit > cartQuantity) {
//            quantity = cartQuantity;
//            cartList = 2;
//        } else {
//            quantity = cartQuantity;
//            cartList = 1;
//        }
//        helper.sleep(2); //waiting for product cart api loading
//        int load = 0;
//        while (load < 5) {
//            List<WebElement> cartElementList = helper.getElementList(productCartList);
//            if (cartElementList.size() != cartList) {
//                cartElementList = helper.getElementList(productCartList);
//                load++;
//            } else break;
//
//        }
//        List<WebElement> productCartFSList = helper.getElementList(productFlashSaleCartList);
//        if (productCartFSList.size() == 1) {
//            for (WebElement element : productCartFSList) {
//                if (flashSalePrice.equals(element.findElement(By.xpath(productCartPriceXP)).getText().trim())) {
//                    flag.add(true);
//                } else {
//                    actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productCartPriceXP)).getText().trim() + " Expected: " + flashSalePrice + "\n";
//                    flag.add(false);
//                }
//                //check quantity of fs product
//                if (String.valueOf(quantity).equals(element.findElement(By.xpath(productCartQuantity)).getText().trim())) {
//                    flag.add(true);
//                } else {
//                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(productCartQuantity)).getText().trim() + " Expected: " + quantity + "\n";
//                    flag.add(false);
//                }
//            }
//        } else {
//            flag.add(false);
//            actualRS = actualRS + "Flash sale did not display with product: " + productName;
//        }
//        List<WebElement> productCartNotFSList = helper.getElementList(productNotFSImageList);
//        if (productCartNotFSList.size() == 1) {
//            for (WebElement element : productCartNotFSList) {
//                if (String.valueOf(remainingQuantity).equals(element.findElement(By.xpath(productCartQuantity)).getText().trim())) {
//                    flag.add(true);
//                } else {
//                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(productCartQuantity)).getText().trim() + " Expected: " + remainingQuantity + "\n";
//                    flag.add(false);
//                }
//            }
//        } else {
//            flag.add(false);
//            actualRS = actualRS + "Normal product did not display with product: " + productName;
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    public List<String> getFlashSaleProductInformation(String flashSaleKey, String productName) {
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
//        List<String> productFlastSale = new ArrayList<>();
//        productFlastSale.add(productName);
//        System.out.println(productName);
//        System.out.println(flashSaleProductList);
//        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
//            if (flashSaleProduct.getName().equals(productName)) {
//                productFlastSale.add(helper.formatCurrencyToThousand(flashSaleProduct.getPrice()));
//                productFlastSale.add(helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice()));
//            }
//        }
//        return productFlastSale;
//    }
//
//    public FlashSaleProduct getFlashSaleProductObject(String flashSaleKey, String productName) {
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
//        FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
//        for (FlashSaleProduct product : flashSaleProductList) {
//            if (product.getName().equalsIgnoreCase(productName)) {
//                flashSaleProduct.setName(product.getName());
//                flashSaleProduct.setPrice(product.getPrice());
//                flashSaleProduct.setFlashSalePrice(product.getFlashSalePrice());
//                break;
//            }
//        }
//        return flashSaleProduct;
//    }
//
//    public String checkoutWhenFlashSale(String phone, String password, Boolean isEnterAddress, String address, int addressIndex, boolean checkout) {
//        helper.pressPageUpAction();
//        String currentLanguage = getCurrentLanguage();
//        System.out.println(currentLanguage);
//        clickCartIcon();
//        checkOutLoginPage = clickCheckoutOnCart();
//        checkOutLoginPage.checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, checkout);
//        String checkoutURL = driver.getCurrentUrl();
//        return checkoutURL;
//    }
//
//    public String checkoutThenClearCartWithoutLogin() {
//        helper.pressPageUpAction();
//        clickCartIcon();
//        try {
//            checkOutLoginPage = clickCheckoutOnCart();
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            helper.refreshPage();
//            clickCartIcon();
//            checkOutLoginPage = clickCheckoutOnCart();
//        }
//        checkoutPage = new CheckoutPage(driver);
//        commonPagesTheme2().checkoutPage.clearAllCart();
//        String checkoutURL = driver.getCurrentUrl();
//        return checkoutURL;
//    }
//
//    public String checkoutThenClearCartLogin(String phone, String password, Boolean isEnterAddress, String address, int addressIndex) {
//        helper.pressPageUpAction();
//        String currentLanguage = getCurrentLanguage();
//        clickCartIcon();
//        try {
//            checkOutLoginPage = clickCheckoutOnCart();
//        } catch (Exception exception) {
//            helper.refreshPage();
//            checkOutLoginPage = clickCheckoutOnCart();
//        }
//        checkOutLoginPage.checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, false);
//        checkoutPage = new CheckoutPage(driver);
//        commonPagesTheme2().checkoutPage.clearAllCart();
//        String checkoutURL = driver.getCurrentUrl();
//        return checkoutURL;
//    }
//
//    public String checkoutWhenFlashSaleWithoutLogin(Boolean isEnterAddress, String address, int addressIndex, Boolean isCheckout) {
//        helper.pressPageUpAction();
//        String currentLanguage = getCurrentLanguage();
//        clickCartIcon();
//        checkOutLoginPage = clickCheckoutOnCart();
//        String checkoutURL = driver.getCurrentUrl();
//        if (isCheckout) checkOutLoginPage.checkoutWithoutLogin(currentLanguage, isEnterAddress, address, addressIndex);
//        return checkoutURL;
//    }
//
//    public void openEditOrderWithoutLoginCheckoutWhenFlashSale(Boolean isEnterAddress, String address, int addressIndex, Boolean isCheckout) {
//        checkoutPage = new CheckoutPage(driver);
//        helper.refreshPage();
//        checkoutWhenFlashSaleWithoutLogin(isEnterAddress, address, addressIndex, isCheckout);
//        helper.refreshPage();
//        commonPagesTheme2().checkoutPage.openEditOrderFromCheckout();
//    }
//
//    public void openEditOrderLoginCheckoutWhenFlashSale(String phoneNumber, String password, Boolean isEnterAddress, String address, int addressIndex) {
//        checkoutPage = new CheckoutPage(driver);
//        helper.refreshPage();
//        checkoutWhenFlashSale(phoneNumber, password, isEnterAddress, address, addressIndex, false);
//        helper.refreshPage();
//        commonPagesTheme2().checkoutPage.openEditOrderFromCheckout();
//    }
//
//    /**
//     * @param flashSaleKey
//     * @param isEndAfter
//     * @param statusTab    0: Ended
//     *                     1: End after
//     *                     2: Coming
//     * @return
//     */
//    public Boolean checkDisplayOfMainSession(String flashSaleKey, Boolean isEndAfter, int statusTab) {
//        String homeURL = helper.getCurrentURL();
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        actualRS = "";
//        List<Boolean> flag = new ArrayList<>();
//        List<String> keyList = new ArrayList<>();
//        keyList.add("flashSale");
//        if (statusTab == 0) keyList.add("ended");
//        else if (statusTab == 1) keyList.add("remaining");
//        else keyList.add("coming");
//        helper.refreshPage();
//        helper.waitForJStoLoad();
//        String currentLanguage = getCurrentLanguage();
//        //check flash sale component
//        if (helper.checkDisplayElement(flashSaleSwiper)) {
//            helper.visibleOfLocated(flashSaleSwiper);
//            helper.scrollToElementByJS(driver.findElement(flashSaleSwiper));
//            List<WebElement> cardList = helper.getElementList(flashSaleItems);
//            List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
//            if (flashSaleProductList.size() == cardList.size()) {
//                for (int i = 0; i < flashSaleProductList.size(); i++) {
//                    FlashSaleProduct flashSaleProduct = flashSaleProductList.get(i);
//                    helper.scrollByJS(cardList.get(i));
//                    helper.waitUtilElementVisible(cardList.get(i));
//                    //Check image displays
//                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleImageXP))))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display the image\n";
//                        flag.add(false);
//                    }
//                    //Check image thumbnail
//                    if (cardList.get(i).findElement(By.xpath(flashSaleImageXP)).getAttribute("src").equals(flashSaleProduct.getThumbnail()))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Thumbnail is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleImageXP)).getAttribute("src") + " Expected: " + flashSaleProduct.getThumbnail() + "\n";
//                        flag.add(false);
//                    }
//                    //Check name
//                    if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().equals(flashSaleProduct.getName()))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Name is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText() + " Expected: " + flashSaleProduct.getName() + "\n";
//                        flag.add(false);
//                    }
//                    //check star rating
//                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleRatingXP)))) {
//                        flag.add(true);
//                        if (cardList.get(i).findElement(By.xpath(flashSaleRatingXP)).findElements(By.xpath(flashSaleStarImgXP)).size() == 5)
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Card in position " + (i + 1) + " the number star is not enough 5 stars \n";
//                            flag.add(false);
//                        }
//                    } else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display the rating\n";
//                        flag.add(false);
//                    }
//                    helper.scrollByJS(cardList.get(i));
//                    //check flash sale price
//                    String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
//                    String sellingPrice = "";
//                    try {
//                        sellingPrice = cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getText();
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        helper.refreshPage();
//                        helper.presenceOfAllElementsLocatedBy(flashSaleItems);
//                        cardList = helper.getElementList(flashSaleItems);
//                        helper.scrollByJS(cardList.get(i));
//                        sellingPrice = cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getText();
//                    }
//                    if (sellingPrice.equals(flashSalePrice)) flag.add(true);
//                    else {
//                        actualRS = actualRS + "Flash sale price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getText() + " Expected: " + flashSalePrice + "\n";
//                        flag.add(false);
//                    }
//                    //check original price
//                    String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
//                    if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getText().equals(originalPrice))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Original price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getText() + " Expected: " + originalPrice + "\n";
//                        flag.add(false);
//                    }
//                    if (isEndAfter) {
//                        //check display of status bar
//                        try {
//                            helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)));
//                            flag.add(true);
//                            //check quantity bar text
//                            if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList))) {
//                                flag.add(true);
//                            } else {
//                                actualRS = actualRS + "Card in position " + (i + 1) + " wrong text\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList) + "\n";
//                                System.out.println(actualRS);
//                                flag.add(false);
//                            }
//                            if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleFireIconXP))))
//                                flag.add(true);
//                            else {
//                                actualRS = actualRS + "Card in position " + (i + 1) + " did not display fire icon on quantity bar\n";
//                                flag.add(false);
//                            }
//                            if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText().equals(String.valueOf(flashSaleProduct.getFlashSaleQuantity())))
//                                flag.add(true);
//                            else {
//                                actualRS = actualRS + "Card in position " + (i + 1) + " wrong quantity\nActual: \"" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText() + "\" Expected: \"" + flashSaleProduct.getFlashSaleQuantity() + "\"\n";
//                                System.out.println(actualRS);
//                                flag.add(false);
//                            }
//                        } catch (Exception exception) {
//                            Log.info(exception.getMessage());
//                            actualRS = actualRS + "Card in position " + (i + 1) + " did not display quantity bar\n";
//                            flag.add(false);
//                        }
//                    } else {
//                        try {
//                            helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)));
//                            flag.add(false);
//                            actualRS = actualRS + "Card in position " + (i + 1) + " displays quantity bar\n";
//                        } catch (Exception exception) {
//                            Log.info(exception.getMessage());
//                            flag.add(true);
//                        }
//                    }
//                    try {
//                        helper.scrollByJS(cardList.get(i));
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                    }
//                    //check display of percent discount tag
//                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)))) {
//                        flag.add(true);
//                        //check value of percent discount tag
//                        int newPrice = flashSaleProduct.getFlashSalePrice();
//                        int oldPrice = helper.convertToInteger(cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getText());
//                        String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(oldPrice, newPrice));
//                        if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getText().equals(discountPercentFormatted))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Percent is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getText() + " Expected: " + discountPercentFormatted + "\n";
//                            flag.add(false);
//                        }
//                    } else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display or broken UI: the discount percent label\n";
//                        flag.add(false);
//                    }
//                    //check clickable
//                    Boolean clicked = false;
//                    try {
//                        helper.clickBtn(cardList.get(i));
//                        clicked = true;
//                    } catch (Exception e) {
//                        Log.info(e.getMessage());
//                        try {
//                            helper.clickByJS(cardList.get(i));
//                        } catch (Exception ex) {
//                            Log.info(ex.getMessage());
//                            helper.refreshPage();
//                            helper.presenceOfAllElementsLocatedBy(flashSaleItems);
//                            cardList = helper.getElementList(flashSaleItems);
//                            helper.scrollByJS(cardList.get(i));
//                            helper.clickBtn(cardList.get(i));
//                        }
//                        clicked = true;
//                    }
//                    helper.waitForJStoLoad();
//                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
//                    if (clicked == true) {
//                        driver.navigate().to(homeURL);
//                        //get cardList element list again
//                        try {
//                            helper.waitForPresence(flashSaleComponent);
//                            helper.visibleOfLocated(flashSaleComponent);
//                        } catch (Exception exception) {
//                            Log.info(exception.getMessage());
//                        }
//                        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " can not click\n";
//                        flag.add(false);
//                    }
//                    cardList = helper.getElementList(flashSaleItems);
//                }
//                if (flag.contains(false)) return false;
//                else return true;
//            } else {
//                actualRS = actualRS + "Showing wrong flash sale quantity\nActual: " + cardList.size() + " Expected: " + flashSaleProductList.size() + "\n";
//                return false;
//            }
//        } else {
//            actualRS = actualRS + "Flash sale component did not display\n";
//            return false;
//        }
//    }
//
//    public Boolean checkCSSOfMainSession(Boolean isAfter) {
//        actualRS = "";
//        List<Boolean> flag = new ArrayList<>();
//        List<WebElement> cardList = new ArrayList<>();
//        helper.refreshPage();
//        helper.waitForJStoLoad();
//        //check flash sale component
//        if (helper.checkDisplayElement(flashSaleComponent)) {
//            cardList = helper.getElementList(flashSaleItems);
//            for (int i = 0; i < cardList.size(); i++) {
//                helper.scrollByJS(cardList.get(i));
//                try {
//                    helper.waitUtilElementVisible(cardList.get(i));
//                } catch (Exception e) {
//                    Log.info(e.getMessage());
//                    helper.refreshPage();
//                    helper.waitForJStoLoad();
//                    helper.visibleOfLocated(flashSaleComponent);
//                    helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//                    cardList = helper.getElementList(flashSaleItems);
//                    helper.scrollByJS(cardList.get(i));
//                }
//                //Check image size
//                if (cardList.get(i).findElement(By.xpath(flashSaleImageXP)).getCssValue("height").equals(flashSaleDataTest.PRODUCT_IMAGE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Image in position " + (i + 1) + " has wrong height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleImageXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_IMAGE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleImageXP)).getCssValue("width").equals(flashSaleDataTest.PRODUCT_IMAGE_WIDTH))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Image in position " + (i + 1) + " has wrong width.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleImageXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_IMAGE_WIDTH + "\n";
//                    flag.add(false);
//                }
//                //Check name
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_MARGIN_TOP))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_MARGIN_TOP + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_NAME_FONT_SIZE))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_NAME_FONT_SIZE + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_NAME_FONT_WEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_NAME_FONT_WEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_LINE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_LINE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                //check price
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_MARGIN_TOP))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_MARGIN_TOP + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_SIZE))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_SIZE + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_WEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_WEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("text-decoration-line").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_DECORATION))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong text-decoration-line.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("text-decoration-line") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_DECORATION + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-right").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_MARGIN_RIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong margin-right.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-right") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_MARGIN_RIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_LINE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_LINE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                //CHECK SELLING PRICE
//                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_SIZE))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_SIZE + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_WEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_WEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (isAfter) {
//                    //check display of status bar
//                    try {
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_TOP))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_TOP + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-right").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_RIGHT))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-right.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-right") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_RIGHT + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-left").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_LEFT))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-left.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-left") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_LEFT + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height").equals(flashSaleDataTest.PRODUCT_BAR_HEIGHT))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_HEIGHT + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("width").equals(flashSaleDataTest.PRODUCT_BAR_WIDTH))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong width.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_WIDTH + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("border-radius").equals(flashSaleDataTest.PRODUCT_BAR_BORDER_RADIUS))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong border-radius.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("border-radius") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_BORDER_RADIUS + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_BAR_FONT_SIZE))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_FONT_SIZE + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_BAR_FONT_WEIGHT))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_FONT_WEIGHT + "\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
//                            flag.add(false);
//                        }
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        flag.add(false);
//                    }
//                }
//                //check display of percent discount tag
//                String color = Color.fromString(cardList.get(i).findElement(By.xpath(flashSalePercentDiscountXP)).getCssValue("background-color")).asHex();
//                if (color.equals(flashSaleDataTest.PRODUCT_TAG_BG_COLOR)) flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong font-size.\nActual:" + Color.fromString(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("background-color")).asHex().toString() + " Expected: " + flashSaleDataTest.PRODUCT_TAG_BG_COLOR + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentDiscountXP)).getCssValue("padding").equals(flashSaleDataTest.PRODUCT_TAG_PADDING))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong padding.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("padding") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_PADDING + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentDiscountXP)).getCssValue("left").equals(flashSaleDataTest.PRODUCT_TAG_LEFT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong right.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("left") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_LEFT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentDiscountXP)).getCssValue("border-radius").equals(flashSaleDataTest.PRODUCT_TAG_BORDER_RADIUS))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong border-radius.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("border-radius") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_BORDER_RADIUS + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_TAG_FONT_SIZE))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_FONT_SIZE + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_TAG_FONT_WEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_FONT_WEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_TAG_LINE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_LINE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//            }
//            if (flag.contains(false)) return false;
//            else return true;
//        } else {
//            actualRS = actualRS + "Flash sale component did not display\n";
//            return false;
//        }
//    }
//
//    public Boolean checkDisplayOfMainSessionAfterChangeBranch(String flashSaleKey) {
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        actualRS = "";
//        List<Boolean> flag = new ArrayList<>();
//        helper.waitForJStoLoad();
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
//        if (flashSaleProductList.size() == cardList.size()) {
//            actualRS = actualRS + "List size - Actual: " + cardList.size() + " Expected: " + flashSaleProductList.size() + "\n";
//            for (int i = 0; i < cardList.size(); i++) {
//                helper.scrollByJS(cardList.get(i));
//                helper.waitUtilElementVisible(cardList.get(i));
//                //Check name
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().toLowerCase().contains(flashSaleDataTest.PRODUCT_FLASH_SALE_4.toLowerCase())) {
//                    flag.add(false);
//                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " did not display after changed branch to " + getBranchNameMissingProductByEnv() + "\n";
//                } else {
//                    flag.add(true);
//                }
//            }
//            if (flag.contains(false)) return false;
//            else return true;
//        } else {
//            for (int i = 0; i < cardList.size(); i++) {
//                helper.scrollByJS(cardList.get(i));
//                helper.waitUtilElementVisible(cardList.get(i));
//                //Check name
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().toLowerCase().contains(flashSaleDataTest.PRODUCT_FLASH_SALE_4.toLowerCase())) {
//                    flag.add(false);
//                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " dont have belong to " + getBranchNameMissingProductByEnv() + "\n";
//                } else {
//                    System.out.println(cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText());
//                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " did not display after changed branch to " + getBranchNameMissingProductByEnv() + "\n";
//                    flag.add(true);
//                }
//            }
//            if (flag.contains(false)) return false;
//            else return true;
//        }
//    }
//
//    public Boolean checkClickableOfFlashSaleTab() {
//        helper.waitForJStoLoad();
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> tabList = helper.getElementList(flashSaleTabList);
//        List<Boolean> flag = new ArrayList<>();
//        actualRS = "";
//        if (tabList.size() == 3) {
//            flag.add(true);
//            for (int i = 0; i < tabList.size(); i++) {
//                try {
//                    tabList.get(i).click();
//                } catch (ElementClickInterceptedException elementClickInterceptedException) {
//                    Log.info(elementClickInterceptedException.getMessage());
//                    helper.clickByJS(tabList.get(i));
//                }
//                helper.waitForJStoLoad();
//                if (i == 0) {
//                    flag.add(checkActiveStatusTab(true, false, false));
//                } else if (i == 1) {
//                    flag.add(checkActiveStatusTab(false, true, false));
//                } else {
//                    flag.add(checkActiveStatusTab(false, false, true));
//                }
//            }
//        } else {
//            actualRS = actualRS + "tabList displayed wrong. Actual: " + tabList.size();
//            flag.add(false);
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    public Boolean checkRemainingQuantityFlashSale(int quantity, int limit, int cart) {
//        int remainingQuantity = 0;
//        if (quantity > limit) {
//            if (cart < limit) remainingQuantity = quantity - cart;
//            else remainingQuantity = quantity - limit;
//        } else if (quantity < limit) {
//            if (quantity > cart) remainingQuantity = quantity - cart;
//            else remainingQuantity = 0;
//        } else {
//            if (limit > cart) remainingQuantity = quantity - cart;
//            else remainingQuantity = 0;
//        }
//        System.out.println("quantity: " + quantity + " limit: " + limit + " cart: " + cart + " remainingQuantity: " + remainingQuantity);
//        List<String> keyList = new ArrayList<>();
//        keyList.add("flashSale");
//        keyList.add("soldOut");
//        actualRS = "";
//        List<Boolean> flag = new ArrayList<>();
//        helper.waitForJStoLoad();
//        try {
//            helper.waitForPresence(flashSaleComponent);
//            helper.visibleOfLocated(flashSaleComponent);
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//        }
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        int index = 0;
//        helper.scrollByJS(cardList.get(index));
//        helper.waitUtilElementVisible(cardList.get(index));
//        //Check quantity API
//        int actualQuantity = 0;
//        String soldOutText = "";
//        if (remainingQuantity > 0) {
//            try {
//                WebElement element = cardList.get(index).findElement(By.xpath(flashSaleQuantityBarNumberXP));
//                actualQuantity = Integer.parseInt(element.getText());
//            } catch (NoSuchElementException noSuchElementException) {
//                Log.info(noSuchElementException.getMessage());
//                helper.refreshPage();
//                helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//                cardList = helper.getElementList(flashSaleItems);
//                helper.scrollByJS(cardList.get(index));
//                helper.waitUtilElementVisible(cardList.get(index));
//                actualQuantity = Integer.parseInt(cardList.get(index).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText());
//            }
//            if (actualQuantity == remainingQuantity) flag.add(true);
//            else {
//                actualRS = actualRS + "Remaining quantity displayed wrong. Actual: " + actualQuantity + " Expected: " + remainingQuantity + "\n";
//                flag.add(false);
//            }
//            try {
//                cardList.get(index).findElement(By.xpath(flashSaleFireIconXP)).isDisplayed();
//                flag.add(true);
//            } catch (NoSuchElementException noSuchElementException) {
//                Log.info(noSuchElementException.getMessage());
//                actualRS = actualRS + "Fire icon did not display";
//                flag.add(false);
//            }
//        } else {
//            soldOutText = cardList.get(index).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText();
//            String currentLanguage = getCurrentLanguage();
//            if (soldOutText.equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList)))
//                flag.add(true);
//            else {
//                actualRS = actualRS + "Sold out displayed wrong. Actual: " + soldOutText + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList);
//                flag.add(false);
//            }
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    public Boolean checkRemainingQuantityFlashSaleIndex(int productIndex, int quantity, int limit, int cart) {
//        int remainingQuantity = 0;
//        if (quantity > limit) {
//            if (cart < limit) remainingQuantity = quantity - cart;
//            else remainingQuantity = quantity - limit;
//        } else if (quantity < limit) {
//            if (quantity > cart) remainingQuantity = quantity - cart;
//            else remainingQuantity = 0;
//        } else {
//            if (limit > cart) remainingQuantity = quantity - cart;
//            else remainingQuantity = 0;
//        }
//        List<String> keyList = new ArrayList<>();
//        keyList.add("flashSale");
//        keyList.add("soldOut");
//        actualRS = "";
//        List<Boolean> flag = new ArrayList<>();
//        helper.waitForJStoLoad();
//        helper.waitForPresence(flashSaleComponent);
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        int index = productIndex;
//        helper.scrollByJS(cardList.get(index));
//        helper.waitUtilElementVisible(cardList.get(index));
//        //Check quantity API
//        int actualQuantity = 0;
//        String soldOutText = "";
//        if (remainingQuantity > 0) {
//            try {
//                actualQuantity = Integer.parseInt(cardList.get(index).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText());
//                if (actualQuantity == remainingQuantity) flag.add(true);
//                else {
//                    actualRS = actualRS + "Remaining quantity displayed wrong. Actual: " + actualQuantity + " Expected: " + remainingQuantity + "\n";
//                    flag.add(false);
//                }
//                try {
//                    cardList.get(index).findElement(By.xpath(flashSaleFireIconXP)).isDisplayed();
//                    flag.add(true);
//                } catch (NoSuchElementException noSuchElementException) {
//                    Log.info(noSuchElementException.getMessage());
//                    actualRS = actualRS + "Fire icon did not display";
//                    flag.add(false);
//                }
//            } catch (Exception exception) {
//                Log.info(exception.getMessage());
//                System.out.println(exception.getMessage());
//                flag.add(false);
//            }
//        } else {
//            soldOutText = cardList.get(index).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText();
//            String currentLanguage = getCurrentLanguage();
//            if (soldOutText.equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList)))
//                flag.add(true);
//            else {
//                actualRS = actualRS + "Sold out displayed wrong. Actual: " + soldOutText + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList);
//                flag.add(false);
//            }
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    private MyOrderPage gotoOrderDetailByURL(String url) {
//        System.out.println(url);
//        driver.navigate().to(url);
//        return new MyOrderPage(driver);
//    }
//
//    private MyOrderPage backPreviousOrder() {
//        driver.navigate().back();
//        return new MyOrderPage(driver);
//    }
//
//    public String checkRemainingFlashSaleAfterReorder(String url, int cartQuantity, Boolean isEnterAddress, String address, int addressIndex) {
//        Map<String, String> flashSaleProductMap = new HashMap<>();
//        myOrderPage = gotoOrderDetailByURL(url);
//        myOrderPage.clickFirstOrder();
//        String currentLanguage = getCurrentLanguage();
//        checkoutPage = myOrderPage.clickReOrderBtn(currentLanguage);
//        //get Flash sale product list from cart
//        clickCartIcon();
//        helper.waitForPresence(cartContainer);
//        helper.visibleOfLocated(cartContainer);
//        List<WebElement> flashSaleProductCartList = helper.getElementList(productFlashSaleCartList);
//        if (flashSaleProductCartList.size() > 0) {
//            for (WebElement element : flashSaleProductCartList) {
//                flashSaleProductMap.put(element.findElement(By.xpath(productCartNameXP)).getText(), String.valueOf(cartQuantity));
//            }
//        }
//        helper.refreshPage();
//        //check flash sale product on checkout
//        commonPagesTheme2().checkoutPage.checkQuantityFlashSale(flashSaleProductMap);
//        commonPagesTheme2().checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
//        return commonPagesTheme2().checkoutPage.viewOrderAfterCheckout();
//    }
//
//    public String formatProductNameSize(String productName, String sizeName) {
//        return productName + " (" + sizeName + ")";
//    }
//
//    public String removeSizeFromProductNameSize(String productName) {
//        return productName.replaceAll("\\s*\\(.*\\)", "");
//    }
//
//    public FlashSaleProduct getProductByName(Boolean isFlashsale, String typeFlashSaleObjectJson, List<String> productInformation) {
//        return jsonAPIAdminReader.getFlashSaleProductFromJson(isFlashsale, typeFlashSaleObjectJson, productInformation, size);
//    }
//
//    public Boolean checkFlashSaleLanguage(Boolean hasEnded, Boolean hasEndAfter, Boolean hasComing) {
//        String currentLanguage = getCurrentLanguage();
//        actualRS = "";
//        System.out.println(currentLanguage);
//        int statusTab = 0;
//        List<String> keyListStatus = null;
//        keyListStatus = new ArrayList<>();
//        keyListStatus.add("flashSale");
//        if (hasEnded) {
//            keyListStatus.add("ended");
//            statusTab = 0;
//        }
//        if (hasComing) {
//            keyListStatus.remove(1);
//            keyListStatus.add("coming");
//            statusTab = 2;
//        }
//        if (hasEndAfter) {
//            keyListStatus.remove(1);
//            statusTab = 1;
//            keyListStatus.add("remaining");
//        }
//        List<String> keyListCountdown = getKeyListByStatus(statusTab);
//        List<Boolean> flag = new ArrayList<>();
//        if (helper.checkDisplayElement(flashSaleComponent)) {
//            helper.visibleOfLocated(flashSaleComponent);
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//            try {
//                clickFlashSaleTab(statusTab);
//            } catch (Exception e) {
//                Log.info(e.getMessage());
//                clickFlashSaleTab(statusTab);
//            }
//            helper.waitForJStoLoad();
//            if (driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText().equals(getLocaleForTabTitle(currentLanguage, statusTab).toUpperCase())) {
//                flag.add(true);
//            } else {
//                actualRS = actualRS + "Tab wrong text\nActual: " + driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText() + " Expected: " + getLocaleForTabTitle(currentLanguage, statusTab).toUpperCase() + "\n";
//                System.out.println(actualRS);
//                flag.add(false);
//            }
//            if (driver.findElement(flashSaleCountdownTitle).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListCountdown).toUpperCase())) {
//                flag.add(true);
//            } else {
//                actualRS = actualRS + "Tab wrong text\nActual: " + driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListCountdown).toUpperCase() + "\n";
//                System.out.println(actualRS);
//                flag.add(false);
//            }
//            List<WebElement> cardList = helper.getElementList(flashSaleItems);
//            for (int i = 0; i < cardList.size(); i++) {
//                try {
//                    helper.scrollByJS(cardList.get(i));
//                    helper.waitUtilElementVisible(cardList.get(i));
//                } catch (Exception e) {
//                    Log.info(e.getMessage());
//                    helper.refreshPage();
//                    helper.waitForJStoLoad();
//                    helper.visibleOfLocated(flashSaleComponent);
//                    helper.scrollToElementByJS(driver.findElement(flashSaleMainSession));
//                    cardList = helper.getElementList(flashSaleItems);
//                    helper.scrollByJS(cardList.get(i));
//                }
//                if (hasEndAfter) {
//                    if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListStatus))) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " wrong text\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListStatus) + "\n";
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                }
//            }
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    public Boolean checkLanguageOfFlashSale(Boolean hasEnded, Boolean hasEndAfter, Boolean hasComing) {
//        List<Boolean> flag = new ArrayList<>();
//        String currentLanguage = getCurrentLanguage();
//        String checkedLanguage = currentLanguage;
//        int index = 0;
//        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
//        List<WebElement> languageList = helper.changeLanguage(languageSwitch, languageOptions);
//        helper.waitUtilElementVisible(driver.findElement(dialogContent));
//        if (languageList.get(0).getText().equals(language)) index = 1;
//        else index = 0;
//        clickLanguage();
//        //check default language
//        flag.add(checkFlashSaleLanguage(hasEnded, hasEndAfter, hasComing));
//        languageList = helper.changeLanguage(languageSwitch, languageOptions);
//        for (int i = index; i < languageList.size(); i++) {
//            try {
//                helper.waitForPresence(dialogContent);
//                helper.waitUtilElementVisible(driver.findElement(dialogContent));
//                helper.waitUtilElementVisible(languageList.get(i));
//            } catch (Exception exception) {
//                Log.info(exception.getMessage());
//            }
//            helper.clickBtn(languageList.get(i));
//            helper.waitForJStoLoad();
//            flag.add(checkFlashSaleLanguage(hasEnded, hasEndAfter, hasComing));
//            helper.refreshPage();
//            helper.pressPageUpAction();
//            helper.changeLanguage(languageSwitch, languageOptions);
//            helper.waitForPresence(dialogContent);
//            helper.waitUtilElementVisible(driver.findElement(dialogContent));
//            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
//            languageList = helper.getElementList(languageOptions);
//            if (!languageList.get(i).getText().equals(language)) {
//                helper.waitUtilElementVisible(languageList.get(i));
//                helper.clickBtn(languageList.get(i));
//                helper.waitForJStoLoad();
//                i++;
//            }
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
}
