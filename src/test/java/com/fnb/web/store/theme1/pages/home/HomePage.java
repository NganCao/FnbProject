package com.fnb.web.store.theme1.pages.home;

import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.api.storeweb.pos.helpers.APIPosService;
import com.fnb.utils.api.storeweb.pos.helpers.JsonAPIPosReader;
import com.fnb.utils.api.storeweb.pos.helpers.JsonAPIPosReader.*;
import com.fnb.utils.helpers.HelperDataFaker;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.login.LoginPage;
import com.fnb.web.store.theme1.pages.myOrder.MyOrderPage;
import com.fnb.web.store.theme1.pages.my_profile.MyAddressListDataTest;
import com.fnb.web.store.theme1.pages.my_profile.MyOrderDataTest;
import com.fnb.web.store.theme1.pages.my_profile.MyProfileDataTest;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsDataTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.*;
import java.util.List;

import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class HomePage extends Setup {
    private WebDriver driver;
    private HelperDataFaker faker;
    private HomeDataTest homeDataTest;
    private MyProfileDataTest myProfileDataTest;
    private MyOrderDataTest orderDataTest;
    private MyAddressListDataTest addressListDataTest;
    private DataTest loginDataTest;
    private ProductDetailsDataTest productDetailsDataTest;
    private CheckOutLoginPage checkOutLoginPage;
    private CheckoutPage checkoutPage;
    private MyOrderPage myOrderPage;
    private APIPosService helpersAPIPos;
    private JsonAPIPosReader jsonAPIPosReader;
    private JsonAPIAdminReader jsonAPIAdminReader;
    private JsonReader jsonReader;
    public String currentWindow;
    public String size = "";
    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public String actualRS;
    public String expectedRS;
    public By boxHeader = By.id("box-content");
    public By storeLogo = By.xpath("//img[@class=\"logo-original-theme\"]");
    //-------------------switch language
    public By languageSwitch = By.xpath("//p[contains(@class,\"link-language\")]");
    public By languageOptions = By.xpath("//div[contains(@class,\"change-language-popover\")]//p");
    public By languageTxt = By.xpath("//span[contains(@class,\"Flag-Default\")]");
    public By vietnameseOption = By.xpath("(//div[contains(@class,\"change-language-popover\")]//p)[1]");
    public By englishOption = By.xpath("(//div[contains(@class,\"change-language-popover\")]//p)[2]");
    //-------------------account
    public By accountIcon = By.xpath("//span[contains(@class,\"account\")]");
    public By accountInfo = By.xpath("//div[@class=\"account-info\"]");
    public By accountLogout = By.xpath("//div[@class=\"account-list\"]/a[4]");
    public By loginRegisterDiv = By.xpath("//div[@class=\"ant-popover-inner\"]//a[@href=\"/login\"]");
    //cart
    public By cartContainer = By.xpath("//div[contains(@class,\"cart-theme1-container\")]");
    public By cartCheckout = By.xpath("//div[contains(@class,\"cart-checkout\")]");
    public By cartQuantity = By.id("cart-quantity");
    //------------------- cart -empty
    public By cartIcon = By.xpath("//span[contains(@class,\"cart\")]");
    public By productCartImage = By.xpath("//div[contains(@class,\"cart-item\")]//img[not(contains(@class,\"flash-sale\"))]");
    //------------------ Address
    public By selectReceiver = By.xpath("//div[contains(@class,\"receiver-address-select-button\")]");
    public By deliverySpan = By.xpath("//div[contains(@class,\"header-container\")]/div[contains(@class,\"header-left\")]/span");
    public By enterAddressField = By.xpath("//span[contains(@class,\"delivery-select-address-input\")]/input");
    public By addressBoxParent = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[@class=\"delivery-address-popover\"]");
    public By addressBoxItems = By.xpath("//div[@class=\"delivery-address-popover\"]/div");
    //banner
    public By bannerTrack = By.id("themeBanner");
    //footer
    public By footer = By.id("themeFooter");
    private String defaultSelectBranch = "ant-radio-wrapper-checked";
    //-------------------Header menu
    private By header = By.id("header");
    private By menuHeader = By.id("nav-menu");
    private String menuHeaderStr = "//li[contains(@class,\"nav-menu-main\")]";
    private String menuHeaderOn3DotsStr = "//li[contains(@class,\"nav-menu-li-dropdown\")]//li[contains(@class,\"nav-menu-li-dropdown-children\")]";
    private By moreMenuDots = By.xpath("//li[@class=\"nav-menu-li-dropdown\"]");
    private By moreSubmenuDots = By.xpath("//ul[contains(@class,\"nav-dropdown\")]");
    private By homeMainMenu = By.xpath("//span[contains(text(),\"Home\")]/parent::a");
    private By menuHasSubmenu = By.xpath("(//li[contains(@class,\"nav-menu-main\")]//*[name()=\"svg\"])[1]");
    private By submenuFirst = By.xpath("(//li[contains(@class,\"nav-menu-main\")]/ul)[1]");
    public By dialogContent = By.xpath("//div[contains(@class,\"ant-popover-inner-content\")]");
    private By languageDialog = By.xpath("//div[contains(@class,\"language-top-bar\")]");
    private By headerRectangle = By.xpath("//span[contains(@class,\"header-rectangle\")]");
    private By languageFlagIcon = By.xpath("//div[contains(@class,\"Flag-Default\")]/*[name()=\"svg\"]");
    private By languageDownArrown = By.xpath("//p[@class=\"link-language\"]/*[name()=\"svg\"]");
    private By avatar = By.xpath("//div[@class=\"account-info\"]/img");
    private By accountName = By.xpath("//div[@class=\"account-name\"]/span");
    private By editAccount = By.xpath("//div[@class=\"account-name\"]/a");
    private By myOrder = By.xpath("//div[@class=\"account-list\"]/a[1]");
    private By myReservations = By.xpath("//div[@class=\"account-list\"]/a[2]");
    private By addressList = By.xpath("//div[@class=\"account-list\"]/a[3]");
    private By myOrderIcon = By.xpath("//div[@class=\"account-list\"]/a[1]//*[name()=\"svg\"]");
    private By addressListIcon = By.xpath("//div[@class=\"account-list\"]/a[2]//*[name()=\"svg\"]");
    private By logoutIcon = By.xpath("//div[@class=\"account-list\"]/a[3]//*[name()=\"svg\"]");
    private By myReservationsNoneLogin = By.xpath("//div[@class=\"ant-popover-inner\"]//a[contains(@class,\"reservation\")]/div");
    private By loginRegisterIcon = By.xpath("//div[@class=\"ant-popover-inner\"]//*[name()=\"svg\"]");
    private By emptyCart = By.xpath("//div[contains(@class,\"no-product-in-cart\")]");
    private By emptyPackageImg = By.xpath("//div[@id=\"box-content\"]//div[contains(@class,\"no-cart-theme1-container\")]//img");
    private By emptyPackageMsg = By.xpath("//div[@id=\"box-content\"]//div[contains(@class,\"no-cart-theme1-container\")]//span");
    //Cart - has product
    private By checkIcon = By.xpath("//div[contains(@class,\"title-cart\")]/*[name()=\"svg\"]");
    private By yourCartLabel = By.xpath("//div[contains(@class,\"title-cart\")]/span");
    public By productCartList = By.xpath("//div[contains(@class,\"cart-item-list\")]/div[contains(@class,\"cart-item\")]");
    private String productCartName = ".//p[contains(@class,\"cart-product-name\")]";
    private By productCartDetails = By.xpath("//div[(@class=\"cart-item-detail\")]");
    private By productComboName = By.xpath("//div[(@class=\"cart-item-detail\")]//p[contains(@class,\"cart-product-name \")]");
    private By productOfComboName = By.xpath("//p[contains(@class,\"cart-product-name-combo\")]");
    private By productOptionCart = By.xpath("//p[contains(@class,\"cart-option\")]");
    private By priceCartList = By.xpath("//span[contains(@class,\"price-after-discount\")]");
    private By minusCartButtonList = By.xpath("//div[contains(@class,\"btn-minus\")]");
    private By quantityCartList = By.xpath("//div[contains(@class,\"item-quantity\")]/span");
    private By plusCartButtonList = By.xpath("//div[contains(@class,\"btn-plus\")]");
    public String productCartNameXP = "./following-sibling::div[contains(@class,\"cart-item-detail\")]//p[contains(@class,\"cart-product-name\")]";
    public String productCartPriceXP = "./following-sibling::div//div[contains(@class,\"cart-price\")]/span";
    public By productFlashSaleCartList = By.xpath("//div[contains(@class,\"cart-item-list\")]//div[contains(@class,\"flash-sale-border\")]/parent::div");
    public By productNotFSImageList = By.xpath("//div[contains(@class,\"cart-item-list\")]/div[contains(@class,\"cart-item\")]/div[contains(@class,\"image\") and not(contains(@class,\"flash-sale-border\"))]");
    private String productCartFlashSaleThumbnail = ".//img[contains(@class,\"item-thumbnail\")]";
    public String productCartFlashSaleName = "./following-sibling::div[contains(@class,\"cart-item-detail\")]//p[contains(@class,\"cart-product-name\")]";
    public String productCartFlashSalePrice = "./following-sibling::div//div[contains(@class,\"cart-price\")]/span";
    public String productCartQuantity = "./following-sibling::div[contains(@class,\"cart-item-detail\")]//div[contains(@class,\"item-quantity\")]/span";
    //banner
    private By bannerDiv = By.xpath("//div[contains(@class,\"slick-list\")]//div[contains(@class,\"slick-slide\") and not(contains(@class,\"slick-cloned\"))]");
    private By bannerImg = By.xpath("//div[contains(@class,\"slick-list\")]//div[contains(@class,\"slick-slide\") and not(contains(@class,\"slick-cloned\"))]//img");
    private By bannerActive = By.xpath("//div[contains(@class,\"slick-list\")]//div[contains(@class,\"slick-current\") and (contains(@class,\"slick-active\"))]");
    private By dotList = By.xpath("//ul[contains(@class,\"slick-dots\")]");
    private By bannerDotsUl = By.xpath("//ul[@class=\"slick-dots\"]");
    private By bannerDots = By.xpath("//ul[@class=\"slick-dots\"]/li");
    //Best selling product
    private By productTitle = By.xpath("//div[@id=\"themeBestSellingProduct\"]//h2");
    public By productItemList = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[(contains(@class,\"swiper-slide\") and not(contains(@class,\" swiper-slide-duplicate \")) and contains(@class,\"swiper-slide-duplicate-\")) or (contains(@class,\"swiper-slide\") and not(contains(@class,\"swiper-slide-duplicate\")))]");
    private By productItemNonDuplicateList = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[contains(@class,\"swiper-slide\") and not(contains(@class,\"swiper-slide-duplicate\"))]");
    private By productItemSpecialList = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[contains(@class,\"swiper-slide\") and not(contains(@class,\" swiper-slide-duplicate \")) and contains(@class,\"swiper-slide-duplicate-\")]");
    private String productImageXpath = ".//img[contains(@class,\"display-image\")]";
    public String productNameXpath = ".//div[contains(@class,\"product-name\")]";
    private String productPriceXpath = ".//div[contains(@class,\"product-price\")]";
    private String orderNowButtonXpath = ".//div[contains(@class,\"btn-order-now\")]";
    public By productActiveItem = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[contains(@class,\"swiper-slide-active\")]");
    private By productSpecialActiveItem = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[contains(@class,\"swiper-slide-active\")]");
    private By productNextItem = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[contains(@class,\"swiper-slide-next\")]");
    public By productComponent = By.xpath("//div[@id=\"themeBestSellingProduct\"]");
    public By productSlider = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[contains(@class,\"swiper-wrapper\")]");
    private By productOrderBtn = By.xpath("//div[@id=\"themeBestSellingProduct\"]//div[contains(@class,\"swiper-slide-active\")]//div[contains(@class,\"btn-order-now\")]/parent::a");
    public String productFlashSaleTag = ".//img[contains(@class,\"flash-sale-logo\")]";
    public String productFlashSaleBorder = ".//div[contains(@class,\"flash-sale-border\")]";
    public String productFlashsaleDiscount = ".//div[contains(@class,\"flash-sale-discount\")]/span";
    public String productFlashSaleOriginalXP = ".//span[contains(@class,\"discount-price\")]";
    //category
    private By categoryComponent = By.id("themeCategory");
    private By leftArrowBtn = By.xpath("//div[contains(@class,\"button-left-arrow\")]");
    private By rightArrowBtn = By.xpath("//div[contains(@class,\"button-right-arrow\")]");
    //Signature product
    private By signatureComponent = By.id("signature-product");
    private By signatureSlider = By.xpath("//div[@id=\"signature-product\"]//div[contains(@class,\"swiper-wrapper\")]");
    private By signatureItemsList = By.xpath("//div[@id=\"signature-product\"]//div[contains(@class,\"swiper-wrapper\")]/div[contains(@class,\"swiper-slide\") and not(contains(@class,\" swiper-slide-duplicate \"))]");
    private By signatureActiveItem = By.xpath("//div[@id=\"signature-product\"]//div[contains(@class,\"swiper-wrapper\")]/div[not(contains(@class,\" swiper-slide-duplicate \")) and contains(@class,\"swiper-slide-active\")]");
    private String activeListXpath = ".//div[(contains(@class,\"swiper-slide\") and not(contains(@class,\" swiper-slide-duplicate \")) and contains(@class,\"swiper-slide-duplicate-\")) or (contains(@class,\"swiper-slide\") and not(contains(@class,\"swiper-slide-duplicate\")))]";
    private String signatureImageXpath = ".//img";
    private String signatureLogoXpath = ".//*[name()=\"svg\"]";
    private String signatureProductTitleXpath = ".//div[contains(@class,\"signature-product-title\")]";
    private String signatureDescriptionXpath = ".//div[starts-with(@id,\"signature-product-description-\")]";
    private String tryNowBtnXpath = ".//button[contains(@class,\"signature-product-btn\")]";
    private By signaturePagination = By.xpath("//div[@id=\"signature-product\"]//div[contains(@class,\"swiper-pagination\")]");
    private By signaturePaginationList = By.xpath("//div[@id=\"signature-product\"]//div[contains(@class,\"swiper-pagination\")]/span");
    private By footerHeadOfficeTitle = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"store-name\")]//span[1]");
    private By footerHeadOffice = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"store-name\")]//span[2]");
    private By footerAddressTitle = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"address\")]//span[1]");
    private By footerAddress = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"address\")]//span[2]");
    private By footerPhoneNumberTitle = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"phone\")]//span[1]");
    private By footerPhoneNumber = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"phone\")]//span[2]");
    private By footerEmailTitle = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"email\")]//span[1]");
    private By footerEmail = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"email\")]//span[2]");
    private By footerMenuTitle = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"store-contact-title\")]/div/div");
    private By footerMenuItem = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"store-contact-flex\")]");
    private String footerMenuListXpath = ".//a";
    private By footerDownloadTitle = By.xpath("//*[@id=\"footer-original-theme\"]//span[contains(@class,\"download-app-title\")]");
    private By footerQRImg = By.xpath("//*[@id=\"footer-original-theme\"]//img[contains(@class,\"image-qr-code\")]");
    private By footerAppleDL = By.xpath("(//*[@id=\"footer-original-theme\"]//img[contains(@class,\"download-app-image\")])[1]");
    private By footerGGPlayDL = By.xpath("(//*[@id=\"footer-original-theme\"]//img[contains(@class,\"download-app-image\")])[2]");
    private By footerBusinessLicense = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"-footer-business\")]//a");
    private By footerSocialMedia = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"social-footer\")]");
    private By footerSocialMediaItems = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"social-footer\")]//a");
    private By footerCopyright = By.xpath("//*[@id=\"footer-original-theme\"]//div[contains(@class,\"footer-copyright\")]/div/div");

    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    public String getCurrentLanguage() {
        try {
            helper.waitForPresence(languageTxt);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.pressPageUpAction();
            helper.waitForPresence(languageTxt);
        }
        helper.visibleOfLocated(languageTxt);
        return helper.getCurrentLanguageHelper(languageTxt);
    }

    public String getCurrentWindow() {
        currentWindow = helper.getCurrentWindow();
        return currentWindow;
    }

    //--------------------- check display of elements after logged in
    public Boolean checkDisplayDefaultLogoutBtn() {
        return driver.findElement(accountLogout).isDisplayed();
    }

    //--------------------- checkUI
    public Boolean checkDisplayOfStoreLogo() {
        return helper.checkDisplayElement(storeLogo);
    }

    public Boolean checkDisplayOfMenu() {
        return helper.checkDisplayElement(menuHeader);
    }

    public Boolean checkInformationOfMenu() {


        return helper.checkDisplayElement(menuHeader);
    }

    public Boolean checkMenuGap(String expected) {
        return helper.getCSSValue(menuHeader, "gap").equals(expected);
    }

    public Boolean checkLevelMenuHover() {
        try {
            helper.visibleOfLocated(menuHasSubmenu);
            helper.actionScrollToElement(driver.findElement(menuHasSubmenu));
            return helper.checkDisplayElement(submenuFirst);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualRS = exception.getMessage();
            return false;
        }

    }

    public Boolean checkDisplayOfDotsMenu() {
        return helper.checkDisplayElement(moreMenuDots);
    }

    public Boolean checkDotsMenuHover() {
        helper.visibleOfLocated(moreMenuDots);
        helper.actionScrollToElement(driver.findElement(moreMenuDots));
        return helper.checkDisplayElement(moreSubmenuDots);
    }

    public Boolean clickHomeMainMenu(String URL) {
        helper.visibleOfLocated(homeMainMenu);
        helper.clickBtn(driver.findElement(homeMainMenu));
        return helper.checkURL(URL);
    }

    public Boolean checkDisplayOfAccountIcon() {
        return helper.checkDisplayElement(accountIcon);
    }

    public Boolean checkDisplayOfLoginRegisterWithoutLogin() {
        return helper.checkDisplayElement(loginRegisterDiv);
    }

    public Boolean checkDisplayOfLoginRegisterIcon() {
        return helper.checkDisplayElement(loginRegisterIcon);
    }

    public Boolean checkDisplayOfAvatar() {
        return helper.checkDisplayElement(avatar);
    }

    public Boolean checkDisplayOfName() {
        return helper.checkDisplayElement(accountName);
    }

    public Boolean checkDisplayOfEditProfile() {
        return helper.checkDisplayElement(editAccount);
    }

    public Boolean checkDisplayOfOrderIcon() {
        return helper.checkDisplayElement(myOrderIcon);
    }

    public Boolean checkDisplayOfOrder() {
        return helper.checkDisplayElement(myOrder);
    }

    public Boolean checkDisplayOfAddressListIcon() {
        return helper.checkDisplayElement(addressListIcon);
    }

    public Boolean checkDisplayOfAddressList() {
        return helper.checkDisplayElement(addressList);
    }

    public Boolean checkDisplayOfLogoutIcon() {
        return helper.checkDisplayElement(logoutIcon);
    }

    public Boolean checkDisplayOfLogout() {
        return helper.checkDisplayElement(accountLogout);
    }

    public Boolean checkDisplayOfHeader() {
        helper.waitForJStoLoad();
        return helper.checkDisplayElement(header);
    }

    public Boolean checkHeightHeader(int height) {
        helper.waitUtilElementVisible(driver.findElement(header));
        int heightHeader = driver.findElement(header).getSize().getHeight();
        if (heightHeader == height) {
            return true;
        } else {
            actualRS = String.valueOf(heightHeader);
            return false;
        }
    }

    public Boolean checkWidthOfHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println(driver.manage().window().getSize().getWidth());
        System.out.println(helper.getCSSValue(header, "width"));
        System.out.println(((Number) js.executeScript("return window.innerWidth")).intValue());
        System.out.println(driver.findElement(By.xpath(".body")).getAttribute("innerHTML"));
        int windowWidth = driver.manage().window().getSize().getWidth();
        int widthHeader = driver.findElement(header).getSize().getWidth();
        if (widthHeader == windowWidth) {
            return true;
        } else {
            actualRS = String.valueOf(widthHeader);
            return false;
        }
    }

    public Boolean checkDisplayOfHeaderRectangle() {
        return helper.checkDisplayElement(headerRectangle);
    }

    //shopping cart
    public Boolean checkDisplayOfCartIcon() {
        return helper.checkDisplayElement(cartIcon);
    }

    public Boolean checkSizeCartIcon(int height, int width) {
        int iconH = driver.findElement(cartIcon).getSize().getHeight();
        int iconW = driver.findElement(cartIcon).getSize().getWidth();
        if (iconW == width && iconH == height) {
            return true;
        } else if (iconW != width) {
            actualRS = "Wrong WIDTH: " + iconW;
            return false;
        } else {
            actualRS = "Wrong heigth: " + iconH;
            return false;
        }
    }

    public Boolean checkDisplayEmptyCartDialog() {
        return helper.checkDisplayElement(emptyCart);
    }

    public Boolean checkDisplayEmptyPackageImage() {
        return helper.checkDisplayElement(emptyPackageImg);
    }

    public Boolean checkDisplayEmptyLabel() {
        return helper.checkDisplayElement(emptyPackageMsg);
    }

    public Boolean addToCartFromBestsellingProduct() {
        boolean quantity = false;
        clickProductOnBestselling();
        quantity = commonPagesTheme1().productDetailsPage.addToCart();
        helper.pressPageUpAction();
        clickCartIcon();
        return quantity;
    }

    public Boolean checkDisplayOfQuantityCart() {
        return helper.checkDisplayElement(cartQuantity);
    }

    public Boolean checkCartCheckIcon() {
        helper.visibleOfLocated(cartContainer);
        return helper.checkDisplayElement(checkIcon);
    }

    public Boolean checkYourCartLabel() {
        return helper.checkDisplayElement(yourCartLabel);
    }

    public Boolean checkProductCartList() {
        if (helper.getElementList(productCartList).size() > 0) return true;
        else return false;
    }

    public Boolean checkCheckoutButton() {
        return helper.checkDisplayElement(cartCheckout);
    }

    public Boolean checkDisplayOfLanguageFlagIcon() {
        return helper.checkDisplayElement(languageFlagIcon);
    }

    public Boolean checkDisplayOfLanguageLabel() {
        return helper.checkDisplayElement(languageSwitch);
    }

    public Boolean checkDisplayOfLanguageArrow() {
        return helper.checkDisplayElement(languageDownArrown);
    }

    public Boolean checkDefaultLanguage(String expected) {
        actualRS = driver.findElement(languageTxt).getText().trim();
        return actualRS.equals(expected);
    }

    public Boolean checkDisplayLanguageDialog() {
        return helper.checkDisplayElement(languageDialog);
    }

    //TODO - size will get from ADMIN
    public Boolean checkDisplayOfNumberOptions(int size) {
        int listSize = helper.getElementList(languageOptions).size();
        if (listSize == size) {
            return true;
        } else {
            actualRS = String.valueOf(listSize);
            return false;
        }
    }

    public Boolean checkDisplayOfVietnamOption() {
        return helper.checkDisplayElement(vietnameseOption);
    }

    public Boolean checkDisplayOfEnglishOption() {
        return helper.checkDisplayElement(englishOption);
    }


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
        try {
            helper.waitForJStoLoad();
            helper.waitUtilElementVisible(driver.findElement(cartCheckout));
            helper.clickBtn(driver.findElement(cartCheckout));
            helper.visibleOfLocated(commonPagesTheme1().checkoutPage.bannerHeaderCheckout);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitUtilElementVisible(driver.findElement(cartCheckout));
            helper.clickByJS(driver.findElement(cartCheckout));
            helper.visibleOfLocated(commonPagesTheme1().checkoutPage.bannerHeaderCheckout);
        }
        return new CheckOutLoginPage(driver);
    }

    public void clickAccountIcon() {
        try {
            helper.waitForPresence(accountIcon);
            helper.visibleOfLocated(accountIcon);
            helper.waitUtilElementVisible(driver.findElement(accountIcon));
            helper.clickBtn(driver.findElement(accountIcon));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(helper.getElement(accountIcon));
        }
    }

    public Boolean clickEditAccount() {
        clickAccountIcon();
        helper.waitUtilElementVisible(driver.findElement(editAccount));
        helper.clickBtn(driver.findElement(editAccount));
        return helper.checkURL(myProfileDataTest.URL);
    }

    public Boolean clickOrder() {
        clickAccountIcon();
        helper.waitUtilElementVisible(driver.findElement(myOrder));
        helper.clickBtn(driver.findElement(myOrder));
        return helper.checkURL(orderDataTest.URL);
    }

    public Boolean clickAddressList() {
        clickAccountIcon();
        helper.waitForPresence(addressList);
        helper.visibleOfLocated(addressList);
        helper.waitUtilElementVisible(driver.findElement(addressList));
        helper.clickBtn(driver.findElement(addressList));
        return helper.checkURL(addressListDataTest.URL);
    }

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

    public void loginSuccessfully(String phone, String password) {
        helper.waitForPresence(accountIcon);
        helper.visibleOfLocated(accountIcon);
        helper.clickBtn(driver.findElement(accountIcon));
        helper.visibleOfLocated(loginRegisterDiv);
        helper.clickBtn(driver.findElement(loginRegisterDiv));
        commonPagesTheme1().loginPage.loginPasswordSuccessfully(phone, password);
    }

    public void clickLanguage() {
        helper.visibleOfLocated(languageSwitch);
        helper.waitUtilElementVisible(driver.findElement(languageSwitch));
        try {
            helper.clickBtn(driver.findElement(languageSwitch));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            helper.clickBtn(driver.findElement(languageSwitch));
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
        helper.waitUtilElementVisible(driver.findElement(accountLogout));
        WebElement logout = driver.findElement(accountLogout);
        logout.click();
    }

    public String generateRandomStr(int length) {
        return faker.generateRandom(length);
    }

    private Boolean checkLanguageOfAccountIconWithoutLogin(String language) {
        int failed = 0;
        List<String> keyList = new ArrayList<>();
        keyList.add("loginOrRegister");
        helper.waitUtilElementVisible(driver.findElement(loginRegisterDiv));
        if (helper.commonLanguageCheckLocaleFile(language, "login / register", "text", loginRegisterDiv, loginDataTest.PAGE, keyList) == false) {
            failed++;
            actualRS = "Actual: " + helper.getText(loginRegisterDiv) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "login / register", "text", loginRegisterDiv, loginDataTest.PAGE, keyList);
        }
        keyList.clear();
        keyList.add("myReservations");
        if (helper.commonLanguageCheckLocaleFile(language, "My reservations", "text", myReservationsNoneLogin, homeDataTest.MY_RESERVATIONS, keyList) == false) {
            failed++;
            actualRS = "Actual: " + helper.getText(myReservationsNoneLogin) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "My reservations", "text", myReservationsNoneLogin, homeDataTest.MY_RESERVATIONS, keyList);
        }
        if (failed > 0) {
            return false;
        } else return true;
    }

    private Boolean checkLanguageOfAccountIconAfterLogin(String language) {
        int failed = 0;
        actualRS = "Actual: \n";
        List<String> keyList = new ArrayList<>();
        keyList.add("accountInfo");
        keyList.add("editProfile");
        helper.waitUtilElementVisible(driver.findElement(editAccount));
        if (helper.commonLanguageCheckLocaleFile(language, "edit profile", "text", editAccount, homeDataTest.MY_PROFILE, keyList) == false) {
            failed++;
            actualRS = "Actual: " + helper.getText(editAccount) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "edit profile", "text", editAccount, homeDataTest.MY_PROFILE, keyList);
        }
        keyList.remove("editProfile");
        keyList.add("order");
        if (helper.commonLanguageCheckLocaleFile(language, "order", "text", myOrder, homeDataTest.MY_PROFILE, keyList) == false) {
            failed++;
            actualRS = "Actual: " + helper.getText(myOrder) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "My order", "text", myOrder, homeDataTest.MY_PROFILE, keyList);
        }
        keyList.remove("order");
        keyList.add("addressList");
        if (helper.commonLanguageCheckLocaleFile(language, "address List", "text", addressList, homeDataTest.MY_PROFILE, keyList) == false) {
            failed++;
            actualRS = "Actual: " + helper.getText(addressList) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "address List", "text", addressList, homeDataTest.MY_PROFILE, keyList);
        }
        keyList.remove("addressList");
        keyList.add("logout");
        if (helper.commonLanguageCheckLocaleFile(language, "logout", "text", accountLogout, homeDataTest.MY_PROFILE, keyList) == false) {
            failed++;
            actualRS = "Actual: " + helper.getText(accountLogout) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "logout", "text", accountLogout, homeDataTest.MY_PROFILE, keyList);
        }
        keyList.clear();
        keyList.add("myReservations");
        if (helper.commonLanguageCheckLocaleFile(language, "My reservations", "text", myReservations, homeDataTest.MY_RESERVATIONS, keyList) == false) {
            failed++;
            actualRS = "Actual: " + helper.getText(myReservations) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "My reservations", "text", myReservations, homeDataTest.MY_RESERVATIONS, keyList);
        }
        if (failed > 0) {
            return false;
        } else return true;
    }

    private Boolean checkLanguageOfCartEmpty(String language) {
        checkDisplayEmptyCartDialog();
        List<String> keyList = new ArrayList<>();
        keyList.add("youDontHaveAnyItemsInYourCart");
        if (helper.commonLanguageCheckLocaleFile(language, "Empty Cart", "text", emptyPackageMsg, homeDataTest.SHOPPING_CART, keyList) == false) {
            actualRS = "Actual: " + helper.getText(emptyPackageMsg) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "Empty Cart", "text", emptyPackageMsg, homeDataTest.SHOPPING_CART, keyList);
            return false;
        } else return true;
    }

    private Boolean checkLanguageOfCartHasProduct(String language) {
        int failed = 0;
        List<String> keyList = new ArrayList<>();
        keyList.add("yourCart");
        try {
            helper.waitUtilElementVisible(driver.findElement(yourCartLabel));
            if (helper.commonLanguageCheckLocaleFile(language, "yourCart", "text", yourCartLabel, homeDataTest.SHOPPING_CART, keyList) == false) {
                failed++;
                actualRS = "Actual: " + helper.getText(yourCartLabel) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "yourCart", "text", yourCartLabel, homeDataTest.SHOPPING_CART, keyList);
            }
            keyList.clear();
            keyList.add("checkout");
            if (helper.commonLanguageCheckLocaleFile(language, "checkout", "text", cartCheckout, homeDataTest.SHOPPING_CART, keyList) == false) {
                failed++;
                actualRS = "Actual: " + helper.getText(cartCheckout) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(language, "checkout", "text", cartCheckout, homeDataTest.SHOPPING_CART, keyList);
            }
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        if (failed > 0) {
            return false;
        } else return true;
    }

    private void checkLanguageHeaderIf(List<Boolean> flag, Boolean login, Boolean productCart) {
        helper.refreshPage();
        if (productCart == true) {
            checkLanguageWithoutLogin(flag, productCart);
        } else {
            if (login == true) {
                checkLanguageHeaderAfterLogin(flag);
            } else {
                checkLanguageWithoutLogin(flag, productCart);
            }
        }
        helper.refreshPage();
    }

    public Boolean checkLanguageOfAddUserLocation(Boolean login, Boolean productCart) {
        List<Boolean> flag = new ArrayList<>();
        String currentLanguage = getCurrentLanguage();
        String checkedLanguage = currentLanguage;
        int index = 0;
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        List<WebElement> languageList = helper.changeLanguage(languageSwitch, languageOptions);
        helper.waitForPresence(dialogContent);
        helper.waitUtilElementVisible(driver.findElement(dialogContent));
        if (languageList.get(0).getText().equals(language)) index = 1;
        else index = 0;
        clickLanguage();
        //check default language
        checkLanguageHeaderIf(flag, login, productCart);
        languageList = helper.changeLanguage(languageSwitch, languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            helper.waitForPresence(dialogContent);
            helper.waitUtilElementVisible(driver.findElement(dialogContent));
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            checkLanguageHeaderIf(flag, login, productCart);
            helper.refreshPage();
            helper.changeLanguage(languageSwitch, languageOptions);
            helper.checkDisplayElementByElement(driver.findElement(dialogContent));
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(languageOptions);
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

    public void checkLanguageWithoutLogin(List<Boolean> flag, Boolean productCart) {
        String currentLanguage = getCurrentLanguage();
        if (productCart == true) {
            helper.waitUtilElementVisible(driver.findElement(cartIcon));
            clickCartIcon();
            if (checkLanguageOfCartHasProduct(currentLanguage) == false) flag.add(false);
            else flag.add(true);
            clickCartIcon();
        } else {
            helper.waitUtilElementVisible(driver.findElement(accountIcon));
            clickAccountIcon();
            if (checkLanguageOfAccountIconWithoutLogin(currentLanguage) == false) flag.add(false);
            else flag.add(true);
            clickAccountIcon();
            clickCartIcon();
            if (checkLanguageOfCartEmpty(currentLanguage) == false) flag.add(false);
            else flag.add(true);
            clickCartIcon();
        }
    }

    public void checkLanguageHeaderAfterLogin(List<Boolean> flag) {
        String currentLanguage = getCurrentLanguage();
        clickAccountIcon();
        if (checkLanguageOfAccountIconAfterLogin(currentLanguage) == false) flag.add(false);
        else flag.add(true);
        clickAccountIcon();
    }

    //best selling products
    public void clickProductOnBestselling() {
        clickProductItem();
    }

    public void clickProductItem() {
        Actions actions = helper.getActions();
        helper.waitForJStoLoad();
        helper.waitForPresence(productComponent);
        helper.visibleOfLocated(productComponent);
        try {
            helper.scrollToElementByJS(driver.findElement(productComponent));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.scrollByJS(driver.findElement(productComponent));
        }
        helper.waitForPresence(productActiveItem);
        try {
            actions.clickAndHold(driver.findElement(productActiveItem)).pause(200).perform();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitForPresence(productActiveItem);
            actions.clickAndHold(driver.findElement(productActiveItem)).pause(200).perform();
        }
        actions.click().build().perform();
    }

    public Boolean checkHeaderMenuItem() {
        actualRS = "";
        //call api to get configured menu
        List<MenuItem> menuApi = helpersAPIPos.getMenuHeaderList();
        //get menu header element list - Main menu
        List<WebElement> menuHeaderElementList = helper.getElementList(By.xpath(menuHeaderStr));
        List<MenuItem> menuItemsUI = new ArrayList<>();
        //get menu header element list - Menu on eclipses
        List<WebElement> menuHeaderOn3Dots = helper.getElementList(By.xpath(menuHeaderOn3DotsStr));
        //get Main Menu header object list
        menuItemsUI.addAll(getListMenuItemFromWebElementList(menuHeaderElementList));
        //get Menu header object list after hover on eclipses
        helper.actionHover(driver.findElement(moreMenuDots));
        menuItemsUI.addAll(getListMenuItemFromWebElementList(menuHeaderOn3Dots));
        List<Boolean> flag = new ArrayList<>(); //use to check result
        List<String> menuNameList = new ArrayList<>();
        List<String> menuAPINameList = new ArrayList<>();
        for (MenuItem item : menuItemsUI) menuNameList.add(item.getName());
        for (MenuItem item : menuApi) menuAPINameList.add(item.getName());
        if (menuItemsUI.size() == menuApi.size()) {
            for (int i = 0; i < menuItemsUI.size(); i++) {
                if (menuItemsUI.get(i).getName().equals(menuApi.get(i).getName())) {
                    flag.add(true);
                } else {
                    flag.add(false);
                    Log.info("Actual: " + menuItemsUI.get(i).getName() + "\nExpected: " + menuApi.get(i).getName());
                    actualRS = actualRS + "Actual: " + menuItemsUI.get(i).getName() + "\nExpected: " + menuApi.get(i).getName() + "\n";
                    System.out.println(menuItemsUI.get(i).getName() + " - name failed");
                }
                if (menuItemsUI.get(i).getHasChildren().equals(menuApi.get(i).getHasChildren())) {
                    if (menuItemsUI.get(i).getChildrenList().size() == menuApi.get(i).getChildrenList().size()) {
                        for (int y = 0; y < menuItemsUI.get(i).getChildrenList().size(); y++) {
                            if (menuItemsUI.get(i).getChildrenList().get(y).getName().equals(menuApi.get(i).getChildrenList().get(y).getName())) {
                                flag.add(true);
                            } else {
                                flag.add(false);
                                Log.info("Actual: " + menuItemsUI.get(i).getChildrenList().get(y).getName() + "\nExpected: " + menuApi.get(i).getChildrenList().get(y).getName());
                                actualRS = actualRS + (menuItemsUI.get(i).getName() + " - submenu name failed\n" + "Actual: " + menuItemsUI.get(i).getChildrenList().get(y).getName() + "\nExpected: " + menuApi.get(i).getChildrenList().get(y).getName()) + "\n";
                            }
                        }
                    } else {
                        flag.add(false);
                        Log.info("Actual: " + menuItemsUI.get(i).getChildrenList().size() + "\nExpected: " + menuApi.get(i).getChildrenList().size());
                        actualRS = actualRS + "Actual: " + menuItemsUI.get(i).getChildrenList().size() + "\nExpected: " + menuApi.get(i).getChildrenList().size() + "\n";
                        System.out.println(menuItemsUI.get(i).getName() + " - submenu size is wrong");
                    }
                } else {
                    flag.add(false);
                    Log.info("Actual: " + menuItemsUI.get(i).getHasChildren() + "\nExpected: " + menuApi.get(i).getHasChildren());
                    actualRS = actualRS + "Actual: " + menuItemsUI.get(i).getHasChildren() + "\nExpected: " + menuApi.get(i).getHasChildren() + "\n";
                    System.out.println(menuItemsUI.get(i).getName() + " - Not has submenu");
                }
            }

            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = actualRS + "Actual: " + menuItemsUI.size() + " " + menuNameList + "/nAPI: " + menuApi.size() + " " + menuAPINameList + "\n";
            return false;
        }
    }

    private List<MenuItem> getListMenuItemFromWebElementList(List<WebElement> menuHeaderList) {
        List<MenuItem> menuItemList = new ArrayList<>();
        List<WebElement> submenuElement = new ArrayList<>();
        MenuItem menuItem;
        ChildMenuItem submenuItem;
        for (int i = 0; i < menuHeaderList.size(); i++) {
            menuItem = new MenuItem();
            helper.actionHover(menuHeaderList.get(i));
            menuItem.setName(menuHeaderList.get(i).findElement(By.xpath("./a")).getText());
            try {
                if (helper.checkDisplayElementByElement(menuHeaderList.get(i).findElement(By.xpath("./ul"))) == true) {
                    submenuElement = menuHeaderList.get(i).findElements(By.xpath(".//li"));
                    for (int y = 0; y < submenuElement.size(); y++) {
                        submenuItem = new ChildMenuItem();
                        submenuItem.setName(submenuElement.get(y).findElement(By.xpath("./a")).getText());
                        submenuItem.setUrl(submenuElement.get(y).findElement(By.xpath("./a")).getAttribute("href"));
                        submenuItem.setHasChildren(false);
                        menuItem.addMenuChild(submenuItem);
                        //hover submenu
                        helper.actionScrollToElement(submenuElement.get(y));
                    }
                    try {
                        menuItem.setUrl(menuHeaderList.get(i).findElement(By.xpath("./a")).getAttribute("href"));
                    } catch (Exception ex) {
                        Log.info(ex.getMessage());
                        menuItem.setUrl("");
                    }
                    menuItem.setHasChildren(true);
                } else {
                    menuItem.setHasChildren(false);
                    System.out.println("No has submenu");
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                menuItem.setHasChildren(false);
            }
            menuItemList.add(menuItem);
        }
        return menuItemList;
    }

    //Banner - slide
    public Boolean checkDisplayOfBanner() {
        return helper.checkDisplayElement(bannerTrack);
    }

    public Boolean checkDisplayOfBannerDots() {
        return helper.checkDisplayElement(bannerDotsUl);
    }

    public Boolean checkDisplayBannerSliderAfter5s() {
        int secondStart, secondEnd;
        helper.visibleOfLocated(bannerActive);
        int tabIndex = Integer.parseInt(helper.getAttribute(bannerActive, "data-index"));
        secondStart = (int) System.currentTimeMillis(); //LocalTime.now().getSecond();
        secondEnd = (int) System.currentTimeMillis();//LocalTime.now().getSecond();
        int countTime = secondEnd - secondStart;
        int checkTimer = 0;
        while (countTime < 5000) {
            secondEnd = (int) System.currentTimeMillis();
            countTime = secondEnd - secondStart;
            checkTimer = countTime;
        }
        helper.waitUtilElementVisible(driver.findElement(bannerActive));
        if (checkTimer >= 5000) {
            int tabIndexAfter = Integer.parseInt(helper.getAttribute(bannerActive, "data-index"));
            if (tabIndexAfter != tabIndex) return true;
            else {
                actualRS = "After " + checkTimer + " banner did not change to next slide. From " + tabIndex + " To " + tabIndexAfter;
                return false;
            }
        } else {
            actualRS = "After " + checkTimer + " banner did not changed/next slide. From " + secondStart + " To " + secondEnd;
            return false;
        }
    }

    public Boolean checkBannerClickable() {
        actualRS = "";
        String baseURL = driver.getCurrentUrl();
        List<Boolean> flag = new ArrayList<>();
        List<WebElement> divList;
        helper.waitForClickable(driver.findElement(bannerActive));
        int presentIndex = Integer.parseInt(helper.getAttribute(bannerActive, "data-index"));
        int changeIndex = presentIndex;
        String classCheck = "";
        try {
            helper.clickBtn(driver.findElement(bannerActive));
            Log.info(driver.getCurrentUrl());
            if (!driver.getCurrentUrl().equals(baseURL)) {
                driver.navigate().back();
                helper.visibleOfLocated(bannerTrack);
            }
            flag.add(true);
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            actualRS = actualRS + "Can not click on banner at index " + presentIndex;
            flag.add(false);
        }
        divList = helper.getElementList(bannerDiv);
        if (divList.size() != 1) {
            for (int i = 0; i < divList.size(); i++) {
                changeIndex = Integer.parseInt(divList.get(i).getAttribute("data-index"));
                if (changeIndex == presentIndex) continue;
                presentIndex = changeIndex;
                classCheck = divList.get(i).getClass().toString();
                while (classCheck.contains("slick-active") && classCheck.contains("slick-current")) {
                    classCheck = divList.get(i).getClass().toString();
                }
                try {
                    helper.waitForClickable(divList.get(i));
                    helper.clickBtn(divList.get(i));
                    Log.info(driver.getCurrentUrl());
                    if (!driver.getCurrentUrl().equals(baseURL)) {
                        driver.navigate().back();
                        helper.visibleOfLocated(bannerTrack);
                    }
                    flag.add(true);
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                    actualRS = actualRS + "Can not click on banner at index " + presentIndex;
                    flag.add(false);
                }
                divList = helper.getElementList(bannerDiv);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * 100%, width equals width of body -> minus 20px - define on dataTest
     *
     * @return
     */
    public Boolean checkWidthOfBanner() {
        int screenSize = driver.manage().window().getSize().getWidth() - homeDataTest.BODY_SCROLLBAR_WIDTH;
        helper.waitForPresence(bannerTrack);
        helper.visibleOfLocated(bannerTrack);
        int elementWidth = driver.findElement(bannerTrack).getSize().getWidth();
        actualRS = "banner: " + elementWidth + " - screen size: " + screenSize;
        return (elementWidth == screenSize);
    }

    public Boolean checkDisplayBannerAfterClicksDot(int maxNumberOfBanner) {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        String classBanner = "";
        helper.waitForPresence(bannerActive);
        helper.visibleOfLocated(bannerActive);
        List<WebElement> divList = helper.getElementList(bannerDiv);
        List<WebElement> dotListElement = helper.getElementList(bannerDots);
        helper.waitForPresence(dotList);
        helper.visibleOfLocated(dotList);
        try {
            helper.scrollToElementByJS(driver.findElement(dotList));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        if (dotListElement.size() == divList.size()) {
            for (int i = 0; i < dotListElement.size(); i++) {
                helper.waitForClickable(dotListElement.get(i));
                helper.clickByJS(dotListElement.get(i));
                helper.waitAttributeContains(dotListElement.get(i), "class", "slick-active");
                classBanner = divList.get(i).getAttribute("class").trim();
                if (classBanner.contains("slick-active") && classBanner.contains("slick-current")) {
                    flag.add(true);
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Actual: Class did not contains active value" + classBanner + "\n";
                }
            }
        } else {
            actualRS = actualRS + "banner: " + divList.size() + " - dot: " + dotListElement.size() + "\n";
            flag.add(false);
        }
        if (dotListElement.size() > maxNumberOfBanner) {
            actualRS = actualRS + "Maximum number of dot is wrong: " + dotListElement.size() + "\n";
            flag.add(false);
        } else flag.add(true);
        if (divList.size() > maxNumberOfBanner) {
            actualRS = actualRS + "Maximum number of banner image is wrong: " + divList.size() + "\n";
            flag.add(false);
        } else flag.add(true);
        if (flag.contains(false)) return false;
        else return true;
    }

    private Boolean scrollProductSlider(WebElement slider, int numberOfImages, Boolean isRight) {
        List<Boolean> flag = new ArrayList<>();
        int index1 = 0, index2 = 0;
        Actions actions = helper.getActions();
        actions.moveToElement(slider);
        helper.waitForPresence(productActiveItem);
        helper.visibleOfLocated(productActiveItem);
        int offsetEnd = driver.findElement(productActiveItem).getSize().getWidth();
        for (int i = 0; i < numberOfImages; i++) {
            helper.visibleOfLocated(productActiveItem);
            String indexStr = driver.findElement(productActiveItem).getAttribute("data-swiper-slide-index");
            index1 = Integer.parseInt(indexStr);
            if (isRight == true) {
                actions.clickAndHold().moveByOffset(offsetEnd, 0).pause(500).release().perform();
            } else {
                actions.dragAndDropBy(driver.findElement(productActiveItem), -20, 0).pause(1000).release().perform();
            }
            helper.visibleOfLocated(productActiveItem);
            index2 = Integer.parseInt(driver.findElement(productActiveItem).getAttribute("data-swiper-slide-index"));
            if (index1 == index2) flag.add(false);
            else flag.add(true);
        }
        long count = flag.stream().filter(element -> element.equals(false)).count();
        if (count >= (numberOfImages - 1)) return false;
        else return true;
    }

    private List<String> getProductCardList(WebElement slider) {
        List<String> productNameList = new ArrayList<>();
        List<WebElement> productCardList = helper.getElementList(productItemList);
        int size = productCardList.size();
        String name = "";
        Actions actions = helper.getActions();
        actions.moveToElement(slider);
        helper.visibleOfLocated(productActiveItem);
        int count = 0, checked = 0;
        while (productNameList.size() != size) {
            if (count > 0) {
                productCardList.clear();
                helper.refreshPage();
                helper.waitUtilElementVisible(driver.findElement(productComponent));
                helper.scrollToElementByJS(driver.findElement(productComponent));
                helper.waitForJStoLoad();
                helper.waitForPresence(productSlider);
                helper.visibleOfLocated(productSlider);
                actions.moveToElement(slider);
                helper.visibleOfLocated(productActiveItem);
            }
            for (int i = 0; i < size; i++) {
                helper.waitUtilElementVisible(productCardList.get(i));
                if (i > 3) {
                    actions.dragAndDropBy(productCardList.get(i), -10, 0).pause(500).release().perform();
                }
                name = productCardList.get(i).findElement(By.xpath(productNameXpath)).getText();
                System.out.println(name);
                productNameList.add(name);
            }
            count++;
            checked++;
        }
        System.out.println(productNameList);
        return productNameList;
    }

    public Boolean checkDisplayOfProductScroll(Boolean isRight, int number) {
        helper.waitForPresence(productComponent);
        helper.waitUtilElementVisible(driver.findElement(productComponent));
        helper.scrollToElementByJS(driver.findElement(productComponent));
        helper.waitForJStoLoad();
        helper.waitForPresence(productSlider);
        helper.visibleOfLocated(productSlider);
        if (isRight == true) return scrollProductSlider(driver.findElement(productSlider), number, true);
        else return scrollProductSlider(driver.findElement(productSlider), number, false);
    }

    //category
    public Boolean checkDisplayOfCategory() {
        return helper.checkDisplayElement(categoryComponent);
    }

    public Boolean checkDisplayOfLeftArrow() {
        return helper.checkDisplayElement(leftArrowBtn);
    }

    public Boolean checkDisplayOfRightArrow() {
        return helper.checkDisplayElement(rightArrowBtn);
    }

    public Boolean checkWidthOfCategory() {
        int screenSize = driver.manage().window().getSize().getWidth() - homeDataTest.BODY_SCROLLBAR_WIDTH;
        int elementWidth = driver.findElement(categoryComponent).getSize().getWidth();
        actualRS = "category: " + elementWidth + " - screen size: " + screenSize;
        return (elementWidth == screenSize);
    }

    public Boolean checkPaddingOfCategory() {
        actualRS = driver.findElement(categoryComponent).findElement(By.xpath("./div")).getCssValue("padding");
        return true;
    }

    //Best selling product
    public Boolean checkDisplayOfBestSelling() {
        helper.waitForPresence(productComponent);
        helper.visibleOfLocated(productComponent);
        try {
            helper.actionScrollToElement(driver.findElement(productComponent));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            helper.scrollToElementByJS(driver.findElement(productComponent));
        }
        return helper.checkDisplayElement(productComponent);
    }

    public Boolean checkDisplayOfBestSellingTitle() {
        return helper.checkDisplayElement(productTitle);
    }

    public Boolean checkAutoScrollOfBestSelling(int timeScroll) {
        int secondStart, secondEnd;
        helper.waitForPresence(productComponent);
        helper.visibleOfLocated(productComponent);
        helper.scrollToElementByJS(driver.findElement(productComponent));
        helper.waitForPresence(productActiveItem);
        helper.visibleOfLocated(productActiveItem);
        String indexStr = driver.findElement(productActiveItem).getAttribute("data-swiper-slide-index");
        int tabIndex = Integer.parseInt(indexStr);
        secondStart = (int) System.currentTimeMillis(); //LocalTime.now().getSecond();
        secondEnd = (int) System.currentTimeMillis();//LocalTime.now().getSecond();
        System.out.println(secondEnd + " - " + secondStart);
        int countTime = secondEnd - secondStart;
        int checkTimer = 0;
        while (countTime < timeScroll) {
            secondEnd = (int) System.currentTimeMillis();
            countTime = secondEnd - secondStart;
            checkTimer = countTime;
        }
        System.out.println(checkTimer);
        helper.waitUtilElementVisible(driver.findElement(productActiveItem));
        int tabIndexAfter = Integer.parseInt(driver.findElement(productActiveItem).getAttribute("data-swiper-slide-index"));
        if (checkTimer >= timeScroll) {
            if (tabIndexAfter != tabIndex) return true;
            else {
                actualRS = "After " + checkTimer + " banner did not chang to next slide. From " + tabIndex + " To " + tabIndexAfter;
                return false;
            }
        } else {
            actualRS = "After " + checkTimer + " banner did not changed/next slide. From " + secondStart + " To " + secondEnd;
            return false;
        }
    }

    public Boolean checkBestSellingProductAnimation() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        Actions actions = helper.getActions();
        String href = "";
        helper.waitForPresence(productComponent);
        helper.visibleOfLocated(productComponent);
        helper.scrollToElementByJS(driver.findElement(productComponent));
        helper.visibleOfLocated(productActiveItem);
        helper.waitForPresence(productActiveItem);
        String scalePass = driver.findElement(productActiveItem).getCssValue("scale");
        actions.clickAndHold(driver.findElement(productActiveItem)).pause(500).perform();
        String scaleNew = driver.findElement(productActiveItem).getCssValue("scale");
        href = driver.findElement(productActiveItem).findElement(By.xpath(orderNowButtonXpath)).findElement(By.xpath("./parent::a")).getAttribute("href");
        String opacity = driver.findElement(productActiveItem).findElement(By.xpath(orderNowButtonXpath)).getCssValue("opacity");
        actions.click().release().perform();
        if (href.contains(productDetailsDataTest.URL) == true) flag.add(true);
        else {
            actualRS = actualRS + "\nUrl did not contain " + productDetailsDataTest.URL + "\nCurrent url: " + driver.getCurrentUrl();
            System.out.println("\nUrl did not contain " + productDetailsDataTest.URL + "\nCurrent url: " + driver.getCurrentUrl());
            flag.add(false);
        }
        if (helper.waitForURLContains(href) == true) flag.add(true);
        else {
            actualRS = actualRS + "\nUrl wrong " + driver.getCurrentUrl();
            System.out.println("\nUrl wrong " + driver.getCurrentUrl());
            flag.add(false);
        }
        if (!scaleNew.equals(scalePass)) flag.add(true);
        else {
            actualRS = actualRS + "\ncard did not zoom. Scale- new: " + scaleNew + " pass: " + scalePass;
            System.out.println("\ncard did not zoom. Scale- new: " + scaleNew + " pass: " + scalePass);
            flag.add(false);
        }
        if (opacity.equals("1")) flag.add(true);
        else {
            actualRS = actualRS + "\nButton did not display. Opacity: " + opacity;
            System.out.println("\nButton did not display. Opacity: " + opacity);
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfBestSellingProductInformation() {
        List<Boolean> flag = new ArrayList<>();
        helper.waitForPresence(productComponent);
        helper.visibleOfLocated(productComponent);
        helper.scrollToElementByJS(driver.findElement(productComponent));
        List<WebElement> productCardList = helper.getElementList(productItemNonDuplicateList);
        List<WebElement> productCardSpecialList = helper.getElementList(productItemSpecialList); //remaining card which has changed xpath when slider is scrolling. Example: index 0 will change
        for (int i = 0; i < productCardList.size(); i++) {
            helper.scrollToElementByJS(productCardList.get(i));
            flag.add(helper.checkDisplayElementByElement(productCardList.get(i).findElement(By.xpath(productImageXpath))));
            flag.add(productCardList.get(i).findElement(By.xpath(productImageXpath)).getCssValue("border-radius").equals(homeDataTest.BORDER_RADIUS));
            flag.add(helper.checkDisplayElementByElement(productCardList.get(i).findElement(By.xpath(productNameXpath))));
            flag.add(helper.checkDisplayElementByElement(productCardList.get(i).findElement(By.xpath(productPriceXpath))));
            flag.add(productCardList.get(i).findElement(By.xpath(orderNowButtonXpath)).getCssValue("opacity").toString().equals("0"));
        }
        for (int i = 0; i < productCardSpecialList.size(); i++) {
            helper.scrollToElementByJS(productCardSpecialList.get(i));
            flag.add(helper.checkDisplayElementByElement(productCardSpecialList.get(i).findElement(By.xpath(productImageXpath))));
            flag.add(productCardSpecialList.get(i).findElement(By.xpath(productImageXpath)).getCssValue("border-radius").equals(homeDataTest.BORDER_RADIUS));
            flag.add(helper.checkDisplayElementByElement(productCardSpecialList.get(i).findElement(By.xpath(productNameXpath))));
            flag.add(helper.checkDisplayElementByElement(productCardSpecialList.get(i).findElement(By.xpath(productPriceXpath))));
            flag.add(productCardSpecialList.get(i).findElement(By.xpath(orderNowButtonXpath)).getCssValue("opacity").toString().equals("0"));
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * @param categoryName
     * @param isDisplay    true - category belongs to the selected branch
     *                     false - else
     * @return
     */
    public Boolean checkDisplayproductWithName(String categoryName, Boolean isDisplay) {
        //will enhance if category with branch exists -> dynamic branch and category
        List<String> productNameList = jsonReader.getProductInformationByName(categoryName);
        helper.waitUtilElementVisible(driver.findElement(productComponent));
        helper.scrollToElementByJS(driver.findElement(productComponent));
        helper.waitForJStoLoad();
        helper.visibleOfLocated(productSlider);
        List<String> productElementNameList = getProductCardList(driver.findElement(productSlider));
        if (checkCommonDataListString(productElementNameList, productNameList) == isDisplay) return true;
        else return false;
    }

    private Boolean checkCommonDataListString(List<String> list1, List<String> list2) {
        int matchec = 0;
        String s1 = "";
        String s2 = "";
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).contains("(") && list1.get(i).contains(")")) {
                s1 = list1.get(i).substring(0, list1.get(i).indexOf("(") - 1);
            } else {
                s1 = list1.get(i);
            }
            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(j).contains("(") && list2.get(j).contains(")")) {
                    s2 = list2.get(j).substring(0, list2.get(j).indexOf("(") - 1);
                } else s2 = list2.get(j);
                if (s1.equals(s2)) matchec++;
            }
        }
        if (matchec > 0) return true;
        else return false;
    }

    private Boolean checkSizeOfBestSellingCardItems(WebElement slider, int width, int height, String align) {
        List<Boolean> flag = new ArrayList<>();
        List<WebElement> productCardList = helper.getElementList(productItemList);
        int size = productCardList.size();
        String name = "";
        Actions actions = helper.getActions();
        actions.moveToElement(slider);
        helper.waitForPresence(productActiveItem);
        helper.visibleOfLocated(productActiveItem);
        int count = 0;
        for (int i = 0; i < size; i++) {
            helper.waitUtilElementVisible(productCardList.get(i));
            if (i > 3) {
                actions.dragAndDropBy(productCardList.get(i), -10, 0).pause(500).release().perform();
            }
            if (productCardList.get(i).findElement(By.xpath("./div")).getSize().width == width) flag.add(true);
            else {
                System.out.println(i + "-width");
                flag.add(false);
            }
            if (productCardList.get(i).findElement(By.xpath("./div")).getSize().height == height) flag.add(true);
            else {
                System.out.println(i + "-height");
                flag.add(false);
            }
            if (productCardList.get(i).findElement(By.xpath("./div")).getCssValue("align-items").equals(align))
                flag.add(true);
            else {
                System.out.println(i + "-height");
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkSizeOfBestSellingCard() {
        helper.waitForPresence(productComponent);
        helper.visibleOfLocated(productComponent);
        helper.waitUtilElementVisible(driver.findElement(productComponent));
        helper.scrollToElementByJS(driver.findElement(productComponent));
        helper.waitForJStoLoad();
        helper.visibleOfLocated(productSlider);
        return checkSizeOfBestSellingCardItems(driver.findElement(productSlider), homeDataTest.PRODUCT_CARD_WIDTH, homeDataTest.PRODUCT_CARD_HEIGHT, homeDataTest.PRODUCT_CARD_ALIGN);
    }

    public Boolean checkAlignOfProductLabel() {
        helper.waitForPresence(productComponent);
        helper.waitUtilElementVisible(driver.findElement(productComponent));
        helper.scrollToElementByJS(driver.findElement(productComponent));
        helper.waitForJStoLoad();
        helper.visibleOfLocated(productTitle);
        return driver.findElement(productTitle).getCssValue("text-align").equals(homeDataTest.PRODUCT_LABEL_ALIGN);

    }

    //Signature product
    public Boolean checkDisplayOfSignature() {
        helper.waitForPresence(signatureComponent);
        helper.visibleOfLocated(signatureComponent);
        try {
            helper.actionScrollToElement(driver.findElement(signatureComponent));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            helper.scrollToElementByJS(driver.findElement(signatureComponent));
        }
        return helper.checkDisplayElement(signatureComponent);
    }

    public Boolean checkDisplayOfSignatureSlider() {
        return helper.checkDisplayElement(signatureSlider);
    }

    public Boolean checkDisplaySignatureAfter5s() {
        int secondStart, secondEnd;
        helper.visibleOfLocated(signatureActiveItem);
        int tabIndex = Integer.parseInt(helper.getAttribute(signatureActiveItem, "data-swiper-slide-index"));
        secondStart = (int) System.currentTimeMillis(); //LocalTime.now().getSecond();
        secondEnd = (int) System.currentTimeMillis();//LocalTime.now().getSecond();
        int countTime = secondEnd - secondStart;
        int checkTimer = 0;
        while (countTime < 5000) {
            secondEnd = (int) System.currentTimeMillis();
            countTime = secondEnd - secondStart;
            checkTimer = countTime;
        }
        helper.waitUtilElementVisible(driver.findElement(signatureActiveItem));
        int tabIndexAfter = Integer.parseInt(helper.getAttribute(signatureActiveItem, "data-swiper-slide-index"));
        if (checkTimer >= 5000) {
            if (tabIndexAfter != tabIndex) return true;
            else {
                actualRS = "After " + checkTimer + " banner did not chang to next slide. From " + tabIndex + " To " + tabIndexAfter;
                return false;
            }
        } else {
            actualRS = "After " + checkTimer + " banner did not changed/next slide. From " + secondStart + " To " + secondEnd;
            return false;
        }
    }

    public Boolean checkDisplaySignatureAfterClicksDot(int maxNumberOfBanner) {
        List<Boolean> flag = new ArrayList<>();
        String classSignature = "";
        helper.waitForPresence(signatureComponent);
        helper.visibleOfLocated(signatureComponent);
        helper.scrollToElementByJS(driver.findElement(signatureSlider));
        helper.waitForJStoLoad();
        List<WebElement> dotListElement = helper.getElementList(signaturePaginationList);
        List<WebElement> itemList = helper.getElementList(signatureItemsList);
        if (dotListElement.size() == itemList.size()) {
            for (int i = 0; i < dotListElement.size(); i++) {
                helper.waitForClickable(dotListElement.get(i));
                helper.clickByJS(dotListElement.get(i));
                helper.waitAttributeContains(dotListElement.get(i), "class", "swiper-pagination-bullet-active");
                classSignature = itemList.get(i).getAttribute("class").trim();
                if (classSignature.contains("swiper-slide-active")) {
                    flag.add(true);
                } else {
                    flag.add(false);
                    System.out.println(classSignature);
                    actualRS = classSignature;
                }
            }
        } else {
            actualRS = "signature: " + itemList.size() + " - dot: " + dotListElement.size();
            System.out.println(actualRS);
            flag.add(false);
        }
        if (dotListElement.size() > maxNumberOfBanner) {
            System.out.println(dotListElement.size());
            flag.add(false);
        } else flag.add(true);
        if (itemList.size() > maxNumberOfBanner) {
            System.out.println(itemList.size());
            flag.add(false);
        } else flag.add(true);
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * Check size tẽt, text/element align, border radius
     * Need update color (mapping color)
     *
     * @return
     */
    public Boolean checkDisplaySignatureDetails() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.waitForPresence(signatureComponent);
        helper.visibleOfLocated(signatureComponent);
        helper.scrollToElementByJS(driver.findElement(signatureSlider));
        helper.waitForJStoLoad();
        List<WebElement> dotListElement = helper.getElementList(signaturePaginationList);
        List<WebElement> itemList = helper.getElementList(signatureItemsList);
        for (int i = 0; i < dotListElement.size(); i++) {
            helper.waitForClickable(dotListElement.get(i));
            helper.clickByJS(dotListElement.get(i));
            helper.waitAttributeContains(dotListElement.get(i), "class", "swiper-pagination-bullet-active");
            if (helper.checkDisplayElementByElement(itemList.get(i).findElement(By.xpath(signatureLogoXpath))) == true) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature logo did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (helper.checkDisplayElementByElement(itemList.get(i).findElement(By.xpath(signatureImageXpath))) == true) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature image did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (itemList.get(i).findElement(By.xpath(signatureImageXpath)).getSize().width == homeDataTest.PRODUCT_IMG_WIDTH) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature image width is wrong. Actual: " + itemList.get(i).findElement(By.xpath(signatureImageXpath)).getSize().width + "- Expected: " + homeDataTest.PRODUCT_IMG_WIDTH + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (itemList.get(i).findElement(By.xpath(signatureImageXpath)).getSize().height == homeDataTest.PRODUCT_IMG_HEIGHT) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature image height is wrong. Actual: " + itemList.get(i).findElement(By.xpath(signatureImageXpath)).getSize().height + "- Expected: " + homeDataTest.PRODUCT_IMG_HEIGHT + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            //make sure we keep this slider checked if the connection is slow
            helper.clickByJS(dotListElement.get(i));
            if (itemList.get(i).findElement(By.xpath(signatureImageXpath)).getCssValue("border-radius").equals(homeDataTest.SIGNATURE_BORDER_RADIUS)) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature image border-radius is wrong. Actual: " + itemList.get(i).findElement(By.xpath(signatureImageXpath)).getCssValue("border-radius") + "- Expected: " + homeDataTest.SIGNATURE_BORDER_RADIUS + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (helper.checkDisplayElementByElement(itemList.get(i).findElement(By.xpath(signatureProductTitleXpath))) == true) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature title did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (itemList.get(i).findElement(By.xpath(signatureProductTitleXpath)).getCssValue("font-size").equals(homeDataTest.SIGNATURE_TITLE_FONT_SIZE)) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature title font-size is wrong. Actual: " + itemList.get(i).findElement(By.xpath(signatureProductTitleXpath)).getCssValue("font-size") + "- Expected: " + homeDataTest.SIGNATURE_TITLE_FONT_SIZE + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (itemList.get(i).findElement(By.xpath(signatureProductTitleXpath)).getCssValue("text-align").equals(homeDataTest.ALIGN_CENTER)) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature title text-align is wrong. Actual: " + itemList.get(i).findElement(By.xpath(signatureProductTitleXpath)).getCssValue("text-align") + "- Expected: " + homeDataTest.ALIGN_CENTER + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            //make sure we keep this slider checked if the connection is slow
            helper.clickByJS(dotListElement.get(i));
            if (helper.checkDisplayElementByElement(itemList.get(i).findElement(By.xpath(signatureDescriptionXpath))) == true) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature description did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (itemList.get(i).findElement(By.xpath(signatureDescriptionXpath)).getCssValue("font-size").equals(homeDataTest.SIGNATURE_DESCRIPTION_FONT_SIZE)) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature description font-size is wrong. Actual: " + itemList.get(i).findElement(By.xpath(signatureDescriptionXpath)).getCssValue("font-size") + "- Expected: " + homeDataTest.SIGNATURE_DESCRIPTION_FONT_SIZE + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (itemList.get(i).findElement(By.xpath(signatureDescriptionXpath)).getCssValue("text-align").equals(homeDataTest.ALIGN_CENTER)) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Signature description text-align is wrong. Actual: " + itemList.get(i).findElement(By.xpath(signatureDescriptionXpath)).getCssValue("text-align") + "- Expected: " + homeDataTest.ALIGN_CENTER + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            if (helper.checkDisplayElementByElement(itemList.get(i).findElement(By.xpath(tryNowBtnXpath))) == true) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Try now button did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            //make sure we keep this slider checked if the connection is slow
            helper.clickByJS(dotListElement.get(i));
            if (itemList.get(i).findElement(By.xpath(tryNowBtnXpath)).getCssValue("border-radius").equals(homeDataTest.SIGNATURE_BORDER_BUTTON_RADIUS)) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Try now border-radius is wrong. Actual: " + itemList.get(i).findElement(By.xpath(tryNowBtnXpath)).getCssValue("border-radius") + "- Expected: " + homeDataTest.SIGNATURE_BORDER_BUTTON_RADIUS + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            try {
                if (helper.checkDisplayElementByElement(itemList.get(i).findElement(By.xpath(tryNowBtnXpath)).findElement(By.xpath("./span"))) == true) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Try now text did not display\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
                if (itemList.get(i).findElement(By.xpath(tryNowBtnXpath)).findElement(By.xpath("./span")).getCssValue("font-size").equals(homeDataTest.SIGNATURE_BUTTON_FONT_SIZE)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Try now text font-size is wrong. Actual: " + itemList.get(i).findElement(By.xpath(tryNowBtnXpath)).findElement(By.xpath("./span")).getCssValue("font-size") + "- Expected: " + homeDataTest.SIGNATURE_BUTTON_FONT_SIZE + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
                if (itemList.get(i).findElement(By.xpath(tryNowBtnXpath)).findElement(By.xpath("./span")).getCssValue("text-align").equals(homeDataTest.ALIGN_CENTER)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Try now text font-size is wrong. Actual: " + itemList.get(i).findElement(By.xpath(tryNowBtnXpath)).findElement(By.xpath("./span")).getCssValue("text-align") + "- Expected: " + homeDataTest.ALIGN_CENTER + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                actualRS = actualRS + "Try now text did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    //TODO will do after execute customize scripts FB9576
    public Boolean checkClickableOfTryNowBtn() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.visibleOfLocated(signatureComponent);
        helper.scrollToElementByJS(driver.findElement(signatureSlider));
        helper.waitForJStoLoad();
        List<WebElement> dotListElement = helper.getElementList(signaturePaginationList);
        List<WebElement> itemList = helper.getElementList(signatureItemsList);
        for (int i = 0; i < dotListElement.size(); i++) {
            helper.waitForClickable(dotListElement.get(i));
            helper.clickByJS(dotListElement.get(i));
            helper.waitAttributeContains(dotListElement.get(i), "class", "swiper-pagination-bullet-active");
            helper.waitUtilElementVisible(itemList.get(i).findElement(By.xpath(tryNowBtnXpath)));
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfPagination() {
        return helper.checkDisplayElement(signaturePagination);
    }

    //footer
    public Boolean checkDisplayOfFooter() {
        helper.pressEndAction();
        return helper.checkDisplayElement(footer);
    }

    public Boolean checkDisplayOfStoreInformation() {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        if (helper.checkDisplayElement(footerHeadOfficeTitle) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office title did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerHeadOffice) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerAddressTitle) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerAddress) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerPhoneNumberTitle) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerPhoneNumber) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerEmailTitle) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerEmail) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfMenuFooter() {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        if (helper.checkDisplayElement(footerMenuTitle) == true) flag.add(true);
        else {
            actualRS = actualRS + "Menu title did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerMenuItem) == true) flag.add(true);
        else {
            actualRS = actualRS + "Menu did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        List<WebElement> menuList = driver.findElement(footerMenuItem).findElements(By.xpath(footerMenuListXpath));
        for (int i = 0; i < menuList.size(); i++) {
            if (helper.checkDisplayElementByElement(menuList.get(i)) == true) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Item " + (i + 1) + "did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkMenuFooterClickable() {
        String currentURL = driver.getCurrentUrl();
        String href = "";
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.pressEndAction();
        helper.waitForPresence(footer);
        helper.visibleOfLocated(footer);
        List<WebElement> menuList = driver.findElement(footerMenuItem).findElements(By.xpath(footerMenuListXpath));
        for (int i = 0; i < menuList.size(); i++) {
            if (helper.checkDisplayElementByElement(menuList.get(i)) == true) {
                href = menuList.get(i).getAttribute("href");
                helper.scrollToElementByJS(menuList.get(i));
                helper.clickByJS(menuList.get(i));
                if (helper.waitForURLContains(href) == true) flag.add(true);
                else {
                    actualRS = actualRS + "After clicking on menu " + (i + 1) + ", url is incorrect. Actual: " + driver.getCurrentUrl() + ". Expected: should contain " + href + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
                driver.navigate().to(currentURL);
                helper.scrollToElementByJS(driver.findElement(footer));
                menuList = driver.findElement(footerMenuItem).findElements(By.xpath(footerMenuListXpath));
            } else {
                actualRS = actualRS + "Item " + (i + 1) + "did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfDownloadInformation() {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        if (helper.checkDisplayElement(footerDownloadTitle) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office title did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerQRImg) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerAppleDL) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (helper.checkDisplayElement(footerGGPlayDL) == true) flag.add(true);
        else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * Cannot check apple because it is undefined and cannot take further steps as url will stop loading and reset
     *
     * @param appleURL
     * @param ggplayURL
     * @return
     */
    public Boolean checkDisplayOfDownloadInformationClickable(String appleURL, String ggplayURL) {
        actualRS = "";
        String newTabURL = "";
        String currentWindow = helper.getCurrentWindow();
        int initialWindowCount = driver.getWindowHandles().size();
        int finalWindowCount = 0;
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        if (helper.checkDisplayElement(footerGGPlayDL) == true) {
            helper.clickByJS(driver.findElement(footerGGPlayDL));
            finalWindowCount = driver.getWindowHandles().size(); //get the number of window
            if (finalWindowCount > initialWindowCount) {
                Set<String> windowHandles = driver.getWindowHandles();
                for (String windowHandle : windowHandles) {
                    if (!windowHandle.equals(currentWindow)) {
                        driver.switchTo().window(windowHandle);
                        newTabURL = driver.getCurrentUrl();
                        break;
                    }
                }
                if (newTabURL.contains(ggplayURL)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "GooglePlay URL is wrong. Actual: " + newTabURL + " Expected: " + ggplayURL + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
            } else {
                actualRS = actualRS + "Browser did not open google play download link on new tab\n";
                System.out.println(actualRS);
                flag.add(false);
            }
        } else {
            actualRS = actualRS + "Header office did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfBusinessLicense() {
        String currentWindow = helper.getCurrentWindow();
        int initialWindowCount = driver.getWindowHandles().size();
        int finalWindowCount = 0;
        List<Boolean> flag = new ArrayList<>();
        String href = "";
        actualRS = "";
        String newTabURL = "";
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        if (helper.checkDisplayElement(footerBusinessLicense) == true) {
            href = helper.getAttribute(footerBusinessLicense, "href");
            System.out.println(href);
            driver.findElement(footerBusinessLicense).click();
            helper.waitNumberHandleToBe(10, 2);
            finalWindowCount = driver.getWindowHandles().size(); //get the number of window
            if (finalWindowCount > initialWindowCount) {
                Set<String> windowHandles = driver.getWindowHandles();

                for (String windowHandle : windowHandles) {
                    if (!windowHandle.equals(currentWindow)) {
                        driver.switchTo().window(windowHandle);
                        if (helper.waitForURLContains(href) == true) {
                            flag.add(true);
                        } else {
                            actualRS = actualRS + "Business License URL is wrong. Actual: " + driver.getCurrentUrl() + ". Expected: " + href + "\n";
                            System.out.println(actualRS);
                            flag.add(false);
                        }
                        break;
                    }
                }
            } else {
                actualRS = actualRS + "Business License did not open new tab\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            helper.closeAllOpenTabExceptMainTab(currentWindow);
        } else {
            actualRS = actualRS + "Business License logo did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfSocialMedia() {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        if (helper.checkDisplayElement(footerSocialMedia) == true) flag.add(true);
        else {
            actualRS = actualRS + "Menu title did not display\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkSocialMediaClickable() {
        String currentWindow = helper.getCurrentWindow();
        int initialWindowCount = driver.getWindowHandles().size();
        int finalWindowCount = 0;
        String href = "";
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        List<WebElement> socialList = driver.findElements(footerSocialMediaItems);
        for (int i = 0; i < socialList.size(); i++) {
            if (helper.checkDisplayElementByElement(socialList.get(i)) == true) {
                href = socialList.get(i).getAttribute("href");
                socialList.get(i).click();
                finalWindowCount = driver.getWindowHandles().size(); //get the number of window
                if (finalWindowCount > initialWindowCount) {
                    Set<String> windowHandles = driver.getWindowHandles();
                    for (String windowHandle : windowHandles) {
                        if (!windowHandle.equals(currentWindow)) {
                            driver.switchTo().window(windowHandle);
                            if (helper.waitForURLContains(href) == true) {
                                flag.add(true);
                            } else {
                                actualRS = actualRS + "Social media URL is wrong. Actual: " + driver.getCurrentUrl() + ". Expected: " + href + "\n";
                                System.out.println(actualRS);
                                flag.add(false);
                            }
                            break;
                        }
                    }
                } else {
                    actualRS = actualRS + "Item " + (i + 1) + "did not display\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
                helper.closeAllOpenTabExceptMainTab(currentWindow);
            } else {
                actualRS = actualRS + "Item " + (i + 1) + "did not display\n";
                System.out.println(actualRS);
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfCopyright() {
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        if (helper.checkDisplayElement(footerCopyright) == true) return true;
        else {
            actualRS = "Menu title did not display\n";
            System.out.println(actualRS);
            return false;
        }
    }

    public Boolean checkLanguageOfFooter() {
        String currentLanguage = getCurrentLanguage();
        helper.waitUtilElementVisible(driver.findElement(languageSwitch));
        List<Boolean> flag = new ArrayList<>();
        String checkedLanguage = currentLanguage;
        int index = 0;
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        List<WebElement> languageList = helper.changeLanguage(languageSwitch, languageOptions);
        helper.waitUtilElementVisible(driver.findElement(dialogContent));
        if (languageList.get(0).getText().equals(language)) index = 1;
        else index = 0;
        helper.refreshPage();
        helper.pressEndAction();
        helper.visibleOfLocated(footer);
        //check default language
        if (checkLanguageFooterTitle() == true) flag.add(true);
        else flag.add(false);
        languageList = helper.changeLanguage(languageSwitch, languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            helper.waitUtilElementVisible(driver.findElement(dialogContent));
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            if (checkLanguageFooterTitle() == true) flag.add(true);
            else flag.add(false);
            helper.refreshPage();
            helper.changeLanguage(languageSwitch, languageOptions);
            helper.waitUtilElementVisible(driver.findElement(dialogContent));
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(languageOptions);
            if (!languageList.get(i).getText().equals(language)) {
                helper.waitUtilElementVisible(languageList.get(i));
                helper.clickBtn(languageList.get(i));
                helper.waitForJStoLoad();
                i++;
            }
            helper.refreshPage();
            helper.pressEndAction();
            helper.visibleOfLocated(footer);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    private Boolean checkLanguageFooterTitle() {
        helper.pressPageUpAction();
        String currentLanguage = getCurrentLanguage();
        int failed = 0;
        actualRS = "";
        List<String> keyList = new ArrayList<>();
        keyList.add("footerThemeConfiguration");
        keyList.add("storeInformation");
        keyList.add("headquarters");
        try {
            helper.waitUtilElementVisible(driver.findElement(yourCartLabel));
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        if (helper.commonLanguageCheckLocaleFile(currentLanguage, "head office", "text", footerHeadOfficeTitle, homeDataTest.STOREWEB_PAGE, keyList) == false) {
            failed++;
            actualRS = actualRS + "Actual: " + driver.findElement(footerHeadOfficeTitle).getText() + " Expected: " + helper.commonLanguageCheckLocaleFile(currentLanguage, "head office", "text", footerHeadOfficeTitle, homeDataTest.STOREWEB_PAGE, keyList) + "\n";
        }
        keyList.remove("headquarters");
        keyList.add("address");
        if (helper.commonLanguageCheckLocaleFile(currentLanguage, "address", "text", footerAddressTitle, homeDataTest.STOREWEB_PAGE, keyList) == false) {
            failed++;
            actualRS = actualRS + "Actual: " + helper.getText(footerAddressTitle) + " Expected: " + helper.commonLanguageCheckLocaleFile(currentLanguage, "address", "text", footerAddressTitle, homeDataTest.STOREWEB_PAGE, keyList) + "\n";
        }
        keyList.remove("address");
        keyList.add("phoneNumber");
        if (helper.commonLanguageCheckLocaleFile(currentLanguage, "phoneNumber", "text", footerPhoneNumberTitle, homeDataTest.STOREWEB_PAGE, keyList) == false) {
            failed++;
            actualRS = actualRS + "Actual: " + helper.getText(footerPhoneNumberTitle) + " Expected: " + helper.commonLanguageCheckLocaleFile(currentLanguage, "phoneNumber", "text", footerPhoneNumberTitle, homeDataTest.STOREWEB_PAGE, keyList) + "\n";
        }
        keyList.remove("phoneNumber");
        keyList.add("email");
        if (helper.commonLanguageCheckLocaleFile(currentLanguage, "email", "text", footerEmailTitle, homeDataTest.STOREWEB_PAGE, keyList) == false) {
            failed++;
            actualRS = actualRS + "Actual: " + helper.getText(footerEmailTitle) + "\nExpected: " + helper.commonLanguageCheckLocaleFile(currentLanguage, "email", "text", footerEmailTitle, homeDataTest.STOREWEB_PAGE, keyList) + "\n";
        }
        if (failed > 0) {
            return false;
        } else return true;
    }

//    //flash sale
//    public Boolean checkDisplayOfFlashSaleComponent() {
//        helper.waitForJStoLoad();
//        boolean isDisplay = helper.checkDisplayElement(flashSaleComponent);
//        if (isDisplay) {
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//            return true;
//        } else return false;
//    }
//
//    public Boolean checkActiveStatusTab(Boolean hasEnded, Boolean hasEndAfter, Boolean hasComing) {
//        if (checkDisplayOfFlashSaleComponent() == true) {
//            if (hasEndAfter) {
//                return helper.checkDisplayElement(flashSaleEndAfterSwiper);
//            } else if (!hasEndAfter && hasComing) {
//                return helper.checkDisplayElement(flashSaleComingSwiper);
//            } else if (hasEnded && !hasEndAfter && !hasComing) {
//                return helper.checkDisplayElement(flashSaleEndedSwiper);
//            } else return false; //not have active tab
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
//            if (statusTab == 0) {
//                try {
//                    helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
//                } catch (Exception ex) {
//                    Log.info(ex.getMessage());
//                    try {
//                        helper.clickBtn(driver.findElement(flashSaleTabList).findElement(By.xpath(flashSaleEndedSwiperId)));
//                        helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                    }
//                }
//            } else if (statusTab == 1) {
//                try {
//                    helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndAfterSwiperId)));
//                } catch (Exception ex) {
//                    Log.info(ex.getMessage());
//                    try {
//                        helper.clickBtn(driver.findElement(flashSaleTabList).findElement(By.xpath(flashSaleEndAfterSwiperId)));
//                        helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                    }
//                }
//            } else {
//                try {
//                    helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleComingSwiperId)));
//                } catch (Exception ex) {
//                    Log.info(ex.getMessage());
//                    try {
//                        helper.clickBtn(driver.findElement(flashSaleTabList).findElement(By.xpath(flashSaleComingSwiperId)));
//                        helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                    }
//                }
//            }
//        }
//    }
//
//    public String getFlashSaleTabTime() {
//        checkDisplayOfFlashSaleComponent();
//        try {
//            return helper.getElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime)).getText();
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//            return helper.getElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime)).getText();
//        }
//    }
//
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
//    private List<String> getProductNameSameCategory() {
//        List<String> productNameFlashSale = new ArrayList<>();
//        String productName = flashSaleDataTest.PRODUCT_FLASH_SALE_1;
//        List<Product> productList = jsonAPIAdminReader.getProductListWithCategoryIdFromJson(apiAminService.getCategoryIdByProductId(productName));
//        for (Product product : productList) {
//            if (!product.getName().equals(productName)) {
//                productNameFlashSale.add(product.getName());
//            }
//        }
//        System.out.println(productNameFlashSale);
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
//        if (jsonReader.getEnviroment.equals("stag")) {
//            branchName.add(homeDataTest.BRANCH_NAME_STAG);
//        } else {
//            branchName.add(homeDataTest.BRANCH_NAME);
//        }
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
//    public void createFlashSaleBelongsSameCategorySpecialVariation(int addStartMinute, int addEndMinute, String variation, int maximumLimit, int flashSaleQuantity) {
//        List<String> productNameFlashSale = getProductNameListFull();
//        createFlashSaleVariationTopping(productNameFlashSale, addStartMinute, addEndMinute, variation, false, maximumLimit, flashSaleQuantity);
//    }
//
//    /**
//     * @param create         create Flash sale
//     * @param addStartMinute from
//     * @param addEndMinute   to
//     * @param statusBar      0: Ended
//     *                       1: End after
//     *                       2: Coming
//     * @return
//     */
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
//            if (helper.checkDisplayElement(flashSaleLogo)) flag.add(true);
//            else {
//                actualRS = actualRS + "Logo did not display\n";
//                flag.add(false);
//            }
//            //check tab
//            List<String> keyList = new ArrayList<>();
//            List<WebElement> tabList = helper.getElementList(flashSaleTabActive);
//            if (tabList.size() == 1) {
//                keyList = getKeyListByStatus(statusBar);
//                int index = 0;
//                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag, keyList);
//            } else if (tabList.size() == 2) {
//                keyList = getKeyListByStatus(statusBar);
//                int index = tabList.size() - 1;
//                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag, keyList);
//            } else if (tabList.size() == 3) {
//                keyList = getKeyListByStatus(statusBar);
//                int index = statusBar;
//                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag, keyList);
//            } else {
//                actualRS = actualRS + tabList.size() + " is more than 3";
//                flag.add(false);
//            }
//            // check main session
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
//    public Boolean waitTimeToChangeStatus(int addMinute) {
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
//        LocalDateTime targetTime = currentDateTime.plusMinutes(addMinute).withSecond(0).withNano(0);
//        while (nowTime.isBefore(targetTime)) {
//            nowTime = LocalDateTime.now(ZoneId.systemDefault());
//            if (nowTime.isAfter(targetTime) || nowTime.isEqual(targetTime)) {
//                System.out.println(nowTime);
//                break;
//            }
//            helper.waitForJStoLoad();
//            try {
//                driver.findElement(flashSaleEndAfterSwiper).isDisplayed();
//                actualRS = actualRS + "At " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " EndAfter is display\n";
//                flag.add(false);
//                break;
//            } catch (Exception exception) {
//                Log.info(exception.getMessage());
//                flag.add(true);
//            }
//            try {
//                driver.findElement(flashSaleComingSwiper).isDisplayed();
//                flag.add(true);
//            } catch (Exception exception) {
//                Log.info(exception.getMessage());
//                actualRS = actualRS + "At " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " Coming is hide\n";
//                flag.add(false);
//                break;
//            }
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                Log.info(e.getMessage());
//                throw new RuntimeException(e);
//            }
//        }
//        helper.waitForJStoLoad();
//        LocalDateTime no = LocalDateTime.now(ZoneId.systemDefault());
//        try {
//            helper.waitForPresence(flashSaleEndAfterSwiper);
//            flag.add(true);
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            actualRS = actualRS + "After " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " EndAfter did not display\n";
//            flag.add(false);
//        }
//        if (!helper.checkDisplayElement(flashSaleComingSwiper)) flag.add(true);
//        else {
//            actualRS = actualRS + "After " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " Coming still display\n";
//            flag.add(false);
//        }
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
//        LocalDateTime targetTime = currentDateTime.plusMinutes(addTime).withSecond(0).withNano(100);
//        while (nowTime.isBefore(targetTime)) {
//            nowTime = LocalDateTime.now(ZoneId.systemDefault());
//        }
//        return nowTime.format(DateTimeFormatter.ofPattern("HH:mm"));
//    }
//
//    private void checkFlashSaleActive(int statusBar, List<WebElement> tabList, int index, String currentLanguage, List<Boolean> flag, List<String> keyList) {
//        //check label
//        if (tabList.get(index).findElement(By.xpath(flashSaleTabName)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList)))
//            flag.add(true);
//        else {
//            actualRS = actualRS + tabList.size() + " wrong text " + tabList.get(0).findElement(By.xpath(flashSaleTabName)).getText() + "\n";
//            System.out.println(actualRS);
//            flag.add(false);
//        }
//        //check time coming and ended
//        if (statusBar != 1) {
//            if (!checkTimeByStatus(statusBar, index, tabList)) {
//                actualRS = actualRS + "Time wrong";
//                flag.add(checkTimeByStatus(statusBar, index, tabList));
//            }
//        }
//        //check end after time
//        if (statusBar == 1) {
//            //check display count down time
//            if (helper.checkDisplayElementByElement(tabList.get(index).findElement(flashSaleCountdown))) {
//                if (tabList.get(index).findElement(flashSaleCountdown).findElements(By.xpath(flashSaleCountdownXP)).size() == 6) {
//                    flag.add(true);
//                } else {
//                    actualRS = actualRS + "Count down display more than 6 numbers\n";
//                    flag.add(false);
//                }
//            } else {
//                actualRS = actualRS + "Count down flip timer did not display\n";
//                flag.add(false);
//            }
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
//        return tabList.get(index).findElement(By.xpath(flashSaleTabTime)).getText().equals(timeCheck);
//    }
//
//    //Todo check flip countdown time
//    private void isFlipCountdownRunning(List<Boolean> flag) {
//        //check 5 times
//        int count = 5;
//        LocalDateTime currentCountdownValue;
//        LocalDateTime newCountdownValue;
//        while (count > 0) {
//            currentCountdownValue = getPresentCountDownTime();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                Log.info(e.getMessage());
//                e.printStackTrace();
//            }
//            newCountdownValue = getPresentCountDownTime();
//            Duration duration = Duration.between(newCountdownValue, currentCountdownValue);
//            long seconds = duration.getSeconds();
//            if (seconds == 1) {
//                flag.add(true);
//            } else {
//                System.out.println(count);
//                System.out.println(currentCountdownValue);
//                System.out.println(newCountdownValue);
//                System.out.println(seconds);
//                flag.add(false);
//                actualRS = actualRS + "count down time wrong";
//            }
//            count--;
//        }
//    }
//
//    private LocalDateTime getPresentCountDownTime() {
//        WebElement countdownContainer = driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime));
//        List<WebElement> hourDigits = countdownContainer.findElements(By.xpath(".//div[starts-with(@class, '_SKh-V')]"));
//        String countDown = "";
//        for (int i = 0; i < hourDigits.size(); i++) {
//            if (i < 2) {
//                countDown = countDown + (hourDigits.get(i).getText());
//            } else if (i < 4) {
//                countDown = countDown + hourDigits.get(i).getText();
//            } else {
//                countDown = countDown + hourDigits.get(i).getText();
//            }
//        }
//        int hour = Integer.parseInt(countDown.substring(0, 2));
//        int minute = Integer.parseInt(countDown.substring(2, 4));
//        int second = Integer.parseInt(countDown.substring(4, 6));
//        LocalTime time = LocalTime.of(hour, minute, second);
//        LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now(ZoneId.systemDefault()).toLocalDate(), time);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        return localDateTime;
//    }
//
//    public String clickAddToCartFromFlashSale(String flashSaleKey) {
//        actualRS = "";
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        String homeURL = driver.getCurrentUrl();
//        helper.visibleOfLocated(flashSaleComponent);
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        Random random = new Random();
//        int productIndex = 0;
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        try {
//            helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarNumberXP)));
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//        }
//        int quantity = random.nextInt(flashSale.getFlashSaleProduct().get(productIndex).getMaximumLimit()) + 1;
//        String productFlashSaleName = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim();
//        System.out.println(productFlashSaleName);
//        try {
//            helper.clickBtn(cardList.get(productIndex));
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(cardList.get(productIndex));
//        }
//        System.out.println("quantity " + quantity);
//        commonPagesTheme1().productDetailsPage.addProductToCartWithQuantity(quantity);
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
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
//        String productFlashSaleName = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim();
//        System.out.println(productFlashSaleName);
//        try {
//            helper.clickBtn(cardList.get(productIndex));
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(cardList.get(productIndex));
//        }
//        commonPagesTheme1().productDetailsPage.addProductToCartWithQuantity(quantity);
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
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim());
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleOriginalPriceXP)).getText().trim());
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim());
//        try {
//            helper.clickBtn(cardList.get(productIndex));
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(cardList.get(productIndex));
//        }
//        return productAdded;
//    }
//
//    public List<String> clickAddToCartFlashSaleWithQuantityList(int productIndex, int quantity) {
//        List<String> productAdded = clickProductFlashSale(productIndex);
//        commonPagesTheme1().productDetailsPage.addProductToCartWithQuantity(quantity);
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
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
//        String flashsalePrice = cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim();
//        try {
//            helper.clickBtn(cardList.get(productIndex));
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(cardList.get(productIndex));
//        }
//        List<String> product = commonPagesTheme1().productDetailsPage.addProductToCartWithSize(size, quantity);
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
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
//        String flashsalePrice = cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim();
//        try {
//            helper.clickBtn(cardList.get(productIndex));
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(cardList.get(productIndex));
//        }
//        List<String> product = commonPagesTheme1().productDetailsPage.addProductToCartWithSizeAndTopping(size, quantity);
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
//        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//        List<WebElement> cardList = helper.getElementList(flashSaleItems);
//        //check clickable
//        helper.scrollByJS(cardList.get(productIndex));
//        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim());
//        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim());
//        try {
//            helper.clickBtn(cardList.get(productIndex));
//        } catch (Exception e) {
//            Log.info(e.getMessage());
//            helper.clickByJS(cardList.get(productIndex));
//        }
//        productAdded.add(commonPagesTheme1().productDetailsPage.addProductToCartWithTopping(quantity));
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
//                    productNameStr = element.findElement(By.xpath(productCartNameXP)).getText().trim();
//                    if (productName.equalsIgnoreCase(productNameStr)) {
//                        flag.add(true);
//                        actualRS = actualRS + "Flash sale still display with product: " + productName;
//                    } else {
//                        actualRS = actualRS + "Flash sale did not display with product: " + productName;
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                }
//            } catch (NoSuchElementException exception) {
//                Log.error(exception.getMessage());
//                flag.add(false);
//                actualRS = actualRS + "Flash sale did not display with product: " + productName;
//            }
//        } else {
//            flag.add(false);
//            actualRS = actualRS + "Flash sale did not display with product: " + productName;
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
//    public Boolean checkCartPriceWhenFlashSale(FlashSaleProduct flashSaleProduct, Boolean isFlashSale, Boolean isClickCart) {
//        helper.waitForJStoLoad();
//        String productName = flashSaleProduct.getName();
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
//                    if (productName.equals(element.findElement(By.xpath(productCartFlashSaleName)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Flash sale did not display with product: " + productName + "\n";
//                        flag.add(false);
//                    }
//                    if (flashSalePrice.equals(element.findElement(By.xpath(productCartFlashSalePrice)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productCartFlashSalePrice)).getText().trim() + " Expected: " + flashSalePrice + "\n";
//                        flag.add(false);
//                    }
//                    //check flash sale tag
//                    try {
//                        element.findElement(By.xpath(productFlashSaleTag)).isDisplayed();
//                        flag.add(true);
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        actualRS = actualRS + "Flash sale tag did not display\n";
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
//                    if (productName.equals(element.findElement(By.xpath(productCartNameXP)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Flash sale Product name is incorrect. Actual: " + element.findElement(By.xpath(productCartNameXP)).getText().trim() + " Expected: " + productName + "\n";
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                    if (originalPrice.equals(element.findElement(By.xpath(productCartPriceXP)).getText().trim())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(productCartPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                }
//            } else {
//                flag.add(false);
//                actualRS = actualRS + "Nornal product did not display with product: " + productName;
//            }
//        }
//        if (flag.contains(false)) return false;
//        else return true;
//    }
//
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
//                if (flashSalePrice.equals(element.findElement(By.xpath(productCartFlashSalePrice)).getText().trim())) {
//                    flag.add(true);
//                } else {
//                    actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productCartFlashSalePrice)).getText().trim() + " Expected: " + flashSalePrice + "\n";
//                    flag.add(false);
//                }
//                //check flash sale tag
//                try {
//                    element.findElement(By.xpath(productFlashSaleTag)).isDisplayed();
//                    flag.add(true);
//                } catch (Exception exception) {
//                    Log.info(exception.getMessage());
//                    actualRS = actualRS + "Flash sale tag did not display\n";
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
//                try {
//                    helper.waitUtilElementVisible(element.findElement(By.xpath(productCartNameXP)));
//                } catch (Exception ex) {
//                    Log.info(ex.getMessage());
//                }
//                if (originalPrice.equals(element.findElement(By.xpath(productCartPriceXP)).getText().trim())) {
//                    flag.add(true);
//                } else {
//                    actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(productCartPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
//                    System.out.println(actualRS);
//                    flag.add(false);
//                }
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
//    /**
//     * Get best selling product list, flash sale list then check it
//     *
//     * @param flashSaleKey
//     * @return
//     */
//    public Boolean checkBestSellingAllWhenFlashSale(String flashSaleKey) {
//        List<Boolean> flag = new ArrayList<>();
//        actualRS = "";
//        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
//        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
//        List<String> flashSaleProductName = new ArrayList<>();
//        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
//            flashSaleProductName.add(flashSaleProduct.getName());
//        }
//        helper.waitUtilElementVisible(driver.findElement(productComponent));
//        helper.scrollToElementByJS(driver.findElement(productComponent));
//        helper.waitForJStoLoad();
//        helper.visibleOfLocated(productSlider);
//        List<String> productNameList = new ArrayList<>();
//        List<WebElement> productCardList = helper.getElementList(productItemList);
//        int size = productCardList.size();
//        String name = "", nameChecked = "";
//        Actions actions = helper.getActions();
//        actions.moveToElement(driver.findElement(productSlider));
//        helper.visibleOfLocated(productActiveItem);
//        int count = 0;
//        int productFSSize = 0;
//        int checked = 0;
//        while (productNameList.size() != size && checked < 2) {
//            if (count > 0) {
//                productCardList.clear();
//                helper.waitUtilElementVisible(driver.findElement(productComponent));
//                helper.scrollToElementByJS(driver.findElement(productComponent));
//                helper.waitForJStoLoad();
//                helper.visibleOfLocated(productSlider);
//                actions.moveToElement(driver.findElement(productSlider));
//                helper.visibleOfLocated(productActiveItem);
//            }
//            for (int i = 0; i < size; i++) {
//                try {
//                    helper.waitUtilElementVisible(productCardList.get(i));
//                } catch (Exception exception) {
//                    Log.info(exception.getMessage());
//                }
//                if (i > 3) {
//                    try {
//                        actions.dragAndDropBy(productCardList.get(i), -10, 0).release().perform();
//                    } catch (Exception exception) {
//                        Log.error(exception.getMessage());
//                        helper.waitUtilElementVisible(productCardList.get(i).findElement(By.xpath(productNameXpath)));
//                    }
//                }
//                name = productCardList.get(i).findElement(By.xpath(productNameXpath)).getText();
//                System.out.println(name);
//                nameChecked = name;
//                if (flashSaleProductName.contains(nameChecked)) {
//                    productFSSize++;
//                    int originalPrice = 0, flashSalePrice = 0;
//                    for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
//                        if (flashSaleProduct.getName().equals(name)) {
//                            originalPrice = flashSaleProduct.getPrice();
//                            flashSalePrice = flashSaleProduct.getFlashSalePrice();
//                        }
//                    }
//                    //Check display of discount percent
//                    try {
//                        productCardList.get(i).findElement(By.xpath(productFlashsaleDiscount)).isDisplayed();
//                        flag.add(true);
//                        String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(originalPrice, flashSalePrice));
//                        if (productCardList.get(i).findElement(By.xpath(productFlashsaleDiscount)).getText().equals(discountPercentFormatted))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Percent is wrong!\nActual: " + productCardList.get(i).findElement(By.xpath(productFlashsaleDiscount)).getText() + " Expected: " + discountPercentFormatted + "\n";
//                            flag.add(false);
//                        }
//                    } catch (Exception exception) {
//                        Log.info(exception.getMessage());
//                        actualRS = actualRS + "Discount percent did not display - " + name + "\n";
//                        flag.add(false);
//                    }
//                    //check original price
//                    try {
//                        productCardList.get(i).findElement(By.xpath(productFlashSaleOriginalXP)).isDisplayed();
//                        flag.add(true);
//                        if (productCardList.get(i).findElement(By.xpath(productFlashSaleOriginalXP)).getText().equals(helper.formatCurrencyToThousand(originalPrice)))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "original is wrong!\nActual: " + productCardList.get(i).findElement(By.xpath(productFlashSaleOriginalXP)).getText() + " Expected: " + helper.formatCurrencyToThousand(originalPrice) + "\n";
//                            flag.add(false);
//                        }
//                    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
//                        Log.info(noSuchElementException.getMessage());
//                        actualRS = actualRS + "productFlashSaleOriginal did not display - " + name + "\n";
//                        flag.add(false);
//                    }
//                    //Check flash sale border
//                    try {
//                        productCardList.get(i).findElement(By.xpath(productFlashSaleBorder)).isDisplayed();
//                        flag.add(true);
//                    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
//                        Log.info(noSuchElementException.getMessage());
//                        actualRS = actualRS + "Border did not display - " + name + "\n";
//                        flag.add(false);
//                    }
//                    //Check display of flash sale tag
//                    try {
//                        productCardList.get(i).findElement(By.xpath(productFlashSaleTag)).isDisplayed();
//                        flag.add(true);
//                    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
//                        Log.info(noSuchElementException.getMessage());
//                        actualRS = actualRS + "Flash sale tag did not display - " + name + "\n";
//                        flag.add(false);
//                    }
//                }
//                productNameList.add(name);
//            }
//            count++;
//            checked++;
//        }
//        if (flag.contains(false)) {
//            return false;
//        } else return true;
//    }
//
//    public String checkoutWhenFlashSale(String phone, String password, Boolean isEnterAddress, String address, int addressIndex, boolean checkout) {
//        helper.pressPageUpAction();
//        String currentLanguage = getCurrentLanguage();
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
//        helper.refreshPage();
//        checkoutPage.clearAllCart();
//        String checkoutURL = driver.getCurrentUrl();
//        return checkoutURL;
//    }
//
//    public String checkoutThenClearCartLogin(String phone, String password, Boolean isEnterAddress, String address, int addressIndex) {
//        helper.pressPageUpAction();
//        String currentLanguage = getCurrentLanguage();
//        clickCartIcon();
//        checkOutLoginPage = clickCheckoutOnCart();
//        checkOutLoginPage.checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, false);
//        checkoutPage = new CheckoutPage(driver);
//        checkoutPage.clearAllCart();
//        String checkoutURL = driver.getCurrentUrl();
//        return checkoutURL;
//    }
//
//    public String checkoutWhenFlashSaleWithoutLogin(Boolean isEnterAddress, String address, int addressIndex) {
//        helper.pressPageUpAction();
//        String currentLanguage = getCurrentLanguage();
//        clickCartIcon();
//        checkOutLoginPage = clickCheckoutOnCart();
//        try {
//            checkOutLoginPage.checkoutWithoutLogin(currentLanguage, isEnterAddress, address, addressIndex);
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            helper.refreshPage();
//            clickCartIcon();
//            checkOutLoginPage = clickCheckoutOnCart();
//            checkOutLoginPage.checkoutWithoutLogin(currentLanguage, isEnterAddress, address, addressIndex);
//        }
//        String checkoutURL = driver.getCurrentUrl();
//        return checkoutURL;
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
//        String id = "";
//        String href = "";
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
//        if (helper.checkDisplayElement(flashSaleComponent)) {
//            helper.visibleOfLocated(flashSaleComponent);
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//            List<WebElement> cardList = helper.getElementList(flashSaleItems);
//            List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
//            if (flashSaleProductList.size() == cardList.size()) {
//                for (int i = 0; i < flashSaleProductList.size(); i++) {
//                    FlashSaleProduct flashSaleProduct = flashSaleProductList.get(i);
//                    href = cardList.get(i).getAttribute("href");
//                    id = href.substring(href.lastIndexOf("/") + 1);
//                    helper.scrollByJS(cardList.get(i));
//                    helper.waitUtilElementVisible(cardList.get(i));
//                    //check href of <a>
//                    if (id.contains(flashSaleProduct.getProductId())) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Href - id is wrong!\nActual: " + id + " Expected: " + flashSaleProduct.getProductId() + "\n";
//                        flag.add(false);
//                    }
//                    //Check image displays
//                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleImage))))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display the image\n";
//                        flag.add(false);
//                    }
//                    //Check image thumbnail
//                    if (cardList.get(i).findElement(By.xpath(flashSaleImage)).getAttribute("src").equals(flashSaleProduct.getThumbnail()))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Thumbnail is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleImage)).getAttribute("src") + " Expected: " + flashSaleProduct.getThumbnail() + "\n";
//                        flag.add(false);
//                    }
//                    //Check name
//                    if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().equals(flashSaleProduct.getName()))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Name is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText() + " Expected: " + flashSaleProduct.getName() + "\n";
//                        flag.add(false);
//                    }
//                    //check flash sale price
//                    String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
//                    if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getText().equals(flashSalePrice))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Flash sale price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getText() + " Expected: " + flashSalePrice + "\n";
//                        flag.add(false);
//                    }
//                    //check original price
//                    String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
//                    if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getText().equals(originalPrice))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Original price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getText() + " Expected: " + originalPrice + "\n";
//                        flag.add(false);
//                    }
//                    //check display of status bar
//                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP))))
//                        flag.add(true);
//                    else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display quantity bar\n";
//                        flag.add(false);
//                    }
//                    //check quantity bar text
//                    if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList))) {
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " wrong text\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList) + "\n";
//                        System.out.println(actualRS);
//                        flag.add(false);
//                    }
//                    if (isEndAfter) {
//                        if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleFireIconXP))))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Card in position " + (i + 1) + " did not display fire icon on quantity bar\n";
//                            flag.add(false);
//                        }
//                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText().equals(String.valueOf(flashSaleProduct.getFlashSaleQuantity())))
//                            flag.add(true);
//                        else {
//                            actualRS = actualRS + "Card in position " + (i + 1) + " wrong quantity\nActual: \"" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText() + "\" Expected: \"" + flashSaleProduct.getFlashSaleQuantity() + "\"\n";
//                            System.out.println(actualRS);
//                            flag.add(false);
//                        }
//                    }
//                    //check display of percent discount tag
//                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)))) {
//                        flag.add(true);
//                        //check value of percent discount tag
//                        int newPrice = flashSaleProduct.getFlashSalePrice();
//                        int oldPrice = helper.convertToInteger(cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getText());
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
//                    helper.scrollByJS(cardList.get(i));
//                    try {
//                        helper.clickBtn(cardList.get(i));
//                        clicked = true;
//                    } catch (Exception e) {
//                        Log.info(e.getMessage());
//                        helper.clickByJS(cardList.get(i));
//                        clicked = true;
//                    }
//                    helper.waitForJStoLoad();
//                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
//                    if (clicked == true) {
//                        if (helper.waitForURLContains(href)) flag.add(true);
//                        else {
//                            actualRS = actualRS + "URL is wrong!\nActual: " + driver.getCurrentUrl() + " Expected: " + href + "\n";
//                            flag.add(false);
//                        }
//                        driver.navigate().to(homeURL);
//                        //get cardList element list again
//                        try {
//                            helper.waitForPresence(flashSaleComponent);
//                            helper.visibleOfLocated(flashSaleComponent);
//                        } catch (Exception exception) {
//                            Log.info(exception.getMessage());
//                        }
//                        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//                        cardList = helper.getElementList(flashSaleItems);
//                        flag.add(true);
//                    } else {
//                        actualRS = actualRS + "Card in position " + (i + 1) + " can not click\n";
//                        flag.add(false);
//                    }
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
//    public Boolean checkCSSOfMainSession() {
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
//                if (cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("height").equals(flashSaleDataTest.PRODUCT_IMAGE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Image in position " + (i + 1) + " has wrong height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_IMAGE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("width").equals(flashSaleDataTest.PRODUCT_IMAGE_WIDTH))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Image in position " + (i + 1) + " has wrong width.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_IMAGE_WIDTH + "\n";
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
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_LINE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_LINE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                //check flash sale price
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_MARGIN_TOP))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_MARGIN_TOP + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("align-items").equals(flashSaleDataTest.PRODUCT_PRICE_ALIGN_ITEMS))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong align-items.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("align-items") + " Expected: " + flashSaleDataTest.PRODUCT_PRICE_ALIGN_ITEMS + "\n";
//                    flag.add(false);
//                }
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
//                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_LINE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_LINE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                //check original price
//                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_SIZE))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_SIZE + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_WEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_WEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-decoration-line").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_DECORATION))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong text-decoration-line.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-decoration-line") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_DECORATION + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("padding-left").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_PADDING_LEFT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong padding-left.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("padding-left") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_PADDING_LEFT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_LINE_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_LINE_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                //check display of status bar
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_TOP))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_TOP + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-right").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_RIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-right.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-right") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_RIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-left").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_LEFT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-left.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-left") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_LEFT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height").equals(flashSaleDataTest.PRODUCT_BAR_HEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_HEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("width").equals(flashSaleDataTest.PRODUCT_BAR_WIDTH))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong width.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_WIDTH + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("border-radius").equals(flashSaleDataTest.PRODUCT_BAR_BORDER_RADIUS))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong border-radius.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("border-radius") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_BORDER_RADIUS + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_BAR_FONT_SIZE))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_FONT_SIZE + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_BAR_FONT_WEIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_FONT_WEIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
//                    flag.add(false);
//                }
//                //check display of percent discount tag
//                String color = Color.fromString(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("background-color")).asHex();
//                if (color.equals(flashSaleDataTest.PRODUCT_TAG_BG_COLOR)) flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong font-size.\nActual:" + Color.fromString(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("background-color")).asHex().toString() + " Expected: " + flashSaleDataTest.PRODUCT_TAG_BG_COLOR + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("padding").equals(flashSaleDataTest.PRODUCT_TAG_PADDING))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong padding.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_PADDING + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("right").equals(flashSaleDataTest.PRODUCT_TAG_RIGHT))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong right.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("right") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_RIGHT + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("top").equals(flashSaleDataTest.PRODUCT_TAG_TOP))
//                    flag.add(true);
//                else {
//                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("top") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_TOP + "\n";
//                    flag.add(false);
//                }
//                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("border-radius").equals(flashSaleDataTest.PRODUCT_TAG_BORDER_RADIUS))
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
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().contains(flashSaleDataTest.PRODUCT_FLASH_SALE_4)) {
//                    flag.add(false);
//                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " did not display after changed branch to " + getBranchNameMissingProductByEnv() + "\n";
//                } else {
//                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " did not display after changed branch to " + getBranchNameMissingProductByEnv() + "\n";
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
//                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().contains(flashSaleDataTest.PRODUCT_FLASH_SALE_4)) {
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
//    private List<String> getKeyListByStatus(int statusTab) {
//        List<String> keyList = new ArrayList<>();
//        keyList.clear();
//        keyList.add("flashSale");
//        if (statusTab == 0) keyList.add("ended");
//        else if (statusTab == 1) keyList.add("endAfter");
//        else keyList.add("coming");
//        return keyList;
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
//        List<String> keyListTab = getKeyListByStatus(statusTab);
//        List<Boolean> flag = new ArrayList<>();
//        if (helper.checkDisplayElement(flashSaleComponent)) {
//            helper.visibleOfLocated(flashSaleComponent);
//            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
//            clickFlashSaleTab(statusTab);
//            helper.waitForJStoLoad();
//            if (driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListTab))) {
//                flag.add(true);
//            } else {
//                actualRS = actualRS + "Tab wrong text\nActual: " + driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListTab) + "\n";
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
//                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListStatus))) {
//                    flag.add(true);
//                } else {
//                    actualRS = actualRS + "Card in position " + (i + 1) + " wrong text\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListStatus) + "\n";
//                    System.out.println(actualRS);
//                    flag.add(false);
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
//                    flag.add(helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId))));
//                } else if (i == 1) {
//                    flag.add(helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndAfterSwiperId))));
//                } else {
//                    flag.add(helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleComingSwiperId))));
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
//        System.out.println(quantity + " " + limit + " " + cart + " " + remainingQuantity);
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
//    public String checkRemainingFlashSaleAfterReorder(String url, int cartQuantity, Boolean isEnterAddress, String address, int addressIndex) {
//        Map<String, String> flashSaleProductMap = new HashMap<>();
//        myOrderPage = gotoOrderDetailByURL(url);
//        String currentLanguage = getCurrentLanguage();
//        checkoutPage = myOrderPage.clickReOrderBtn(currentLanguage);
//        //get Flash sale product list from cart
//        clickCartIcon();
//        helper.waitForPresence(cartContainer);
//        helper.visibleOfLocated(cartContainer);
//        List<WebElement> flashSaleProductCartList = helper.getElementList(productFlashSaleCartList);
//        if (flashSaleProductCartList.size() > 0) {
//            for (WebElement element : flashSaleProductCartList) {
//                flashSaleProductMap.put(element.findElement(By.xpath(productCartFlashSaleName)).getText(), String.valueOf(cartQuantity));
//            }
//        }
//        helper.refreshPage();
//        //check flash sale product on checkout
//        checkoutPage.checkQuantityFlashSale(flashSaleProductMap);
//        checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
//        return checkoutPage.viewOrderAfterCheckout();
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
}