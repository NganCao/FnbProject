package com.fnb.app.posapp.autostore.pages.dashboard;

import com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage;
import com.fnb.app.posapp.autostore.pages.setting.SettingPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.utils.api.posapp.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.api.posapp.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BaseSetup {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private Product product;
    private JsonAPIAdminReader jsonAPIAdminReader;
    private DataTest dataTest;
    //    private APIAminService apiAminService;
    public static String subTotalStr;
    public String totalStr;
    private ProductCart addedProductCart;
//    public List<ProductCart> productCartList = new ArrayList<>();
    public static List<ProductCart> productCartList = new ArrayList<>();
    public static int quantityCartTotal;
    private AddressManagementListPage addressManagementListPage;
    public String actualRS;
    //header
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup//com.horcrux.svg.SvgView")
    public WebElement hambergerMenu;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Order\"]")
    public WebElement orderMenu;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Home\"]")
    public WebElement homeMenu;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Settings\"]")
    public WebElement settingMenu;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
    private WebElement serviceType;
    @FindBy(xpath = "//android.widget.TextView[@text=\"In-Store\"]")
    private WebElement instoreType;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Takeaway\"]")
    private WebElement takeawayType;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Delivery\"]")
    private WebElement deliveryType;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    private WebElement searchBtn;
    @FindBy(xpath = "//android.widget.EditText[@text=\"Search by product name\"]")
    private WebElement searchField;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup")
    private WebElement clearSearchIcon;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]")
    private WebElement searchResultBox;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    private List<WebElement> searchItems;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")
    private List<WebElement> searchThumbnailItems;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView[1]")
    private List<WebElement> searchNameItems;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView[2]")
    private List<WebElement> searchPriceItems;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Refresh\"]")
    private WebElement refreshBtn;
    //product
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtProductName\"]")
    private List<WebElement> productNameList;
    @FindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"com.gofnb.posapplication:id/layoutProductName\"]")
    private List<WebElement> productNameLayoutList;
    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.gofnb.posapplication:id/imgProduct\"]")
    private List<WebElement> productThumbnailList;
    @FindBy(xpath = "(//android.widget.FrameLayout[@resource-id=\"com.gofnb.posapplication:id/cvBtnAddCart\"])")
    private List<WebElement> addToCartBtnList;
    //cart
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    private List<WebElement> productList;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")
    private List<WebElement> productNameCartList;
    private String productCartInformationTop = "(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1])";
    private String productCartInformationBottom = "(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2])";
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView")
    private List<WebElement> productSizeCartList;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.TextView")
    private List<WebElement> productPriceCartList;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
    private List<WebElement> productIncreaseCartBtnList;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
    private List<WebElement> productReduceCartBtnList;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
    private List<WebElement> productQuantityCartList;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Customer\"]/android.view.ViewGroup[2]")
    private WebElement customerButton;
    @FindBy(xpath = "(//android.widget.TextView[contains(@text,\"Subtotal\")]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup[3]//android.widget.TextView)[1]")
    private WebElement discountTotal;
    @FindBy(xpath = "(//android.widget.TextView[contains(@text,\"Subtotal\")]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup[3]//android.widget.TextView)[2]")
    private WebElement feeTaxTotal;
    @FindBy(xpath = "(//android.widget.TextView[contains(@text,\"Subtotal\")]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup[3]//android.widget.TextView)[3]")
    private WebElement noteBtn;
    @FindBy(xpath = "//android.widget.TextView[contains(@text,\"Subtotal\")]")
    private WebElement subTotalTxt;
    @FindBy(xpath = "//android.widget.TextView[contains(@text,\"Subtotal\")]/following-sibling::android.widget.TextView")
    private WebElement subTotalPrice;
    @FindBy(xpath = "//android.widget.TextView[contains(@text,\"Subtotal\")]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private WebElement totalPrice;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Create order\"]")
    private WebElement createOrderBtn;
    //productDetail
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private WebElement productDetailName;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private WebElement productDetailPrice;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]")
    private WebElement sizeSection;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<WebElement> sizeList;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]")
    private WebElement toppingSection;
    @FindBy(xpath = "(//android.widget.TextView[@text=\"Topping\"])[1]")
    private WebElement toppingTitle;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<WebElement> toppingList;
    private String toppingFirst = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[";
    private String toppingNameLast = "]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView";
    private String toppingPriceLast = "]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView";
    private String toppingAddBtn = "]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup";
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]")
    private WebElement optionSection;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Option\"]")
    private WebElement optionTitle;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup")
    private List<WebElement> optionList;
    private String optionFirst = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[";
    private String optionName = "]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView";
    private String optionValue = "]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView";
    //    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.TextView")
    @FindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"Add to cart\")]/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup[2]/android.widget.TextView")
    private WebElement quantityDetail;
    @FindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"Add to cart\")]/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup[2]/android.view.ViewGroup[2]")
    private WebElement upQuantityDetail;
    @FindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,\"Add to cart\")]/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup[2]/android.view.ViewGroup[1]")
    private WebElement reduceQuantityDetail;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Add to cart\"]/parent::android.view.ViewGroup")
    private WebElement addToCartDetailBtn;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Add to cart\"]/following-sibling::android.view.ViewGroup/android.widget.TextView")
    private WebElement totalPriceDetail;

    public DashboardPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //header
    public void gotoDashBoard() {
        helper.waitToElementClick(hambergerMenu);
        helper.sleep(2);
        try {
            homeMenu.click();
        } catch (Exception exception) {
            helper.waitToElementClick(hambergerMenu);
            helper.sleep(1);
            helper.waitToElementClick(homeMenu);
        }
    }

    public void clickOutsidePopOver() {
        helper.clickOutsideElementByActions(subTotalTxt);
    }

    public void clickHamburgerMenuMenu() {
        helper.waitToElementClick(hambergerMenu);
    }

    public void clickOrderMenu() {
        helper.waitToElementClick(orderMenu);
    }

    public SettingPage clickSettingMenu() {
        helper.waitToElementClick(settingMenu);
        return new SettingPage(driver);
    }

    private WebElement getSearchNameElement(String productName) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + productName + "\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup"));
    }

    public void clickRefreshButton() {
        try {
            helper.waitToElementClick(refreshBtn);
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    //service type
    public Boolean selectServiceType(String type) {
        helper.waitToElementClick(serviceType);
        helper.sleepHaftSec();
        String typeStr = "";
        switch (type.toLowerCase()) {
            case "takeaway":
                helper.waitForClickable(takeawayType);
                typeStr = takeawayType.getText();
                takeawayType.click();
                break;
            case "delivery":
                helper.waitForClickable(deliveryType);
                typeStr = deliveryType.getText();
                deliveryType.click();
                break;
            default:
                helper.waitForClickable(instoreType);
                typeStr = instoreType.getText();
                instoreType.click();
        }
        helper.waitForTextToBe(serviceType, typeStr);
        actualRS = "Actual: " + serviceType.getText() + " Expected: " + typeStr;
        return helper.checkText(serviceType, typeStr);
    }

    //customer
    public void clickCustomer() {
        try {
            helper.waitToElementClick(customerButton);
        } catch (Exception e) {
            Log.error(e.getMessage());
        }
    }

    //product
    //category
    //actions
    public void selectCategoryByText(String categoryName) {
        scrollVerticalAndClick(categoryName);
    }

    //combo/products
    //actions
    public void clickAddToCart() {
        helper.visibilityOfAllElements(addToCartBtnList);
        int index = 1; //helper.generateRandomNumberWithBound(addToCartBtnList.size());
        String name = productNameList.get(index).getText();
        getProductInformationOnDashboard(name);
        helper.sleep(2);
        addToCartBtnList.get(index).click();
    }

    public void clickAddToCartOnDetail(Boolean getProductInfo, String productName, Boolean selectSize, Boolean selectTopping, Boolean selectOption) {
        if (getProductInfo) getProductInformation(productName, selectSize, selectTopping, selectOption);
        helper.sleepHaftSec();
        helper.waitToElementClick(addToCartDetailBtn);
    }

    public void clickProductByProductName(String productName) {
        helper.sleepHaftSec();
        for (WebElement element : productNameList) {
            String name = element.getText();
            if (name.equalsIgnoreCase(productName)) {
                element.click();
                break;
            }
        }
    }

    /**
     * get information on detail
     *
     * @param produdctName
     * @param selectSize
     * @return
     */
    public List<ProductCart> getProductInformation(String produdctName, Boolean selectSize, Boolean selectTopping, Boolean selectOption) {
        ProductCart productCartItem = selectProductByNameOnDetail(produdctName, selectSize, selectTopping, selectOption);
        addProductToProductCart(productCartItem);
        return productCartList;
    }

    private List<String> selectedSizeOnDetail(int size, Boolean selectSize) {
        List<String> list = new ArrayList<>();
        String productPriceName = "";
        float priceSelectedSizePrice = 0;
        String priceFormatted = "";
        int count = 0;
        if (size > 1) {
            for (ProductPrices price : product.getProductPrices()) {
                productPriceName = price.getPriceName();
                if (selectSize) {
                    if (count == 1) {
                        priceFormatted = helper.formatCurrencyToThousand(price.getPrice());
                        priceSelectedSizePrice = price.getPrice();
                        System.out.println("//android.view.ViewGroup[@content-desc=\"" + price.getPriceName() + ", " + priceFormatted + "\"]");
                        WebElement selectedSize = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"" + price.getPriceName() + ", " + priceFormatted + "\"]"));
                        selectedSize.click();
                        helper.sleep(1);
                        break;
                    }
                    count++;
                } else {
                    priceSelectedSizePrice = product.getProductPrices().get(0).getPrice();
                    break;
                }
            }
        } else {
            priceSelectedSizePrice = product.getProductPrices().get(0).getPrice();
        }
        list.add(productPriceName);
        list.add(String.valueOf(priceSelectedSizePrice));
        return list;
    }

    public Topping selectToppingOnDetail(String productName) {
        //getTopping price following product
        List<Topping> toppingList = apiAminService.getToppingListByProductId(productName);
        //add the first topping
        String toppingStr = "";
        int toppingQuantity = 0;
        float toppingPriceValue = 0;
        Topping selectedTopping = new Topping();
        if (toppingList.size() > 0) {
            //android.widget.TextView[contains(@text,"Thạch topping")]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup
//            WebElement toppingName = driver.findElement(By.xpath(toppingFirst + "1" + toppingNameLast));
//            WebElement toppingAdd = driver.findElement(By.xpath(toppingFirst + "1" + toppingAddBtn));
//            toppingStr = toppingName.getText().trim();
            WebElement toppingAdd = null;
            try {
                for (Topping tp : toppingList) {
                    String nameXP = "//android.widget.TextView[contains(@text,\"" + tp.getName() + "\")]";
                    WebElement toppingName = driver.findElement(By.xpath(nameXP));
                    String addBtnXP = "/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup";
                    toppingAdd = driver.findElement(By.xpath(nameXP + addBtnXP));
                    selectedTopping = tp;
                    toppingQuantity++;
                    break;
                }
                toppingAdd.click();
                helper.sleep(1);
            } catch (Exception exception) {
                Log.error("Can not click topping " + exception.getMessage());
                selectedTopping.setQuantity(0);
                selectedTopping.setName("");
                selectedTopping.setPrice(toppingPriceValue);
            }
        } else {
            toppingPriceValue = 0;
            selectedTopping.setQuantity(0);
            selectedTopping.setName("");
            selectedTopping.setPrice(toppingPriceValue);
        }
        selectedTopping.setQuantity(toppingQuantity);
        System.out.println("================="+String.valueOf(selectedTopping));
        return selectedTopping;
    }

    public String selectOptionOnDetail() {
        //change the option and get the list
        String optionStr = "";
        try {
            //android.widget.TextView[contains(@text,"Đá viên")]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup[2]
            //android.widget.TextView[@text="Option"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView
            //android.widget.TextView[@text="Option"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[index option]/android.view.ViewGroup/android.view.ViewGroup[index option value]/android.view.ViewGroup/android.widget.TextView
            //android.widget.TextView[@text="Option"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView
            //android.widget.TextView[@text="Option"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[indexoption]/android.view.ViewGroup/android.view.ViewGroup[index name]/android.widget.TextView
//            String optionNameXP = "//android.widget.TextView[@text=\"Option\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView";
//            String optionValueXP = "//android.widget.TextView[@text=\"Option\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView";
            String optionNameXP = "//android.widget.TextView[@text=\"Option\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView";
            String optionValueXP = "//android.widget.TextView[@text=\"Option\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView";
            WebElement optionNameElement = driver.findElement(By.xpath(optionNameXP));
            List<WebElement> optionValueElement = driver.findElements(By.xpath(optionValueXP));
            optionValueElement.get(1).click();
            optionStr = optionNameElement.getText() + " (" + optionValueElement.get(1).getText() + ")";
        } catch (Exception exception) {
            Log.info("No option");
        }
        return optionStr;
    }

    public ProductCart getAddedProductCart(String name, String size, float totalValue, int quantity, String toppingName, int toppingQuantity, String optionStr) {
        addedProductCart = new ProductCart();
        addedProductCart.setName(name);
        addedProductCart.setSize(size);
        addedProductCart.setPrice(totalValue);
        addedProductCart.setQuantity(quantity);
        addedProductCart.setTopping(toppingName);
        addedProductCart.setToppingQuantity(toppingQuantity);
        addedProductCart.setOption(optionStr);
        return addedProductCart;
    }

    public ProductCart selectProductByNameOnDetail(String productName, Boolean selectSize, Boolean selectTopping, Boolean selectOption) {
        //get product price following variations
        product = apiAminService.getProductBySearchName(productName);
        List<String> sizeList = selectedSizeOnDetail(product.getProductPrices().size(), selectSize);
        String productSize = sizeList.get(0);
        float priceSelectedSizePrice = Float.parseFloat(sizeList.get(1));
        String toppingName = "";
        float toppingPriceValue = 0;
        int toppingQuantity = 0;
        if (selectTopping) {
            Topping selectedTopping = selectToppingOnDetail(productName);
            toppingName = selectedTopping.getName();
            toppingPriceValue = selectedTopping.getPrice();
            toppingQuantity = selectedTopping.getQuantity();
        }
        float totalValue = 0;
        totalValue = priceSelectedSizePrice + toppingPriceValue;
        String optionStr = "";
        if (selectOption) {
            optionStr = selectOptionOnDetail();
        }
        int quantityProduct = Integer.parseInt(quantityDetail.getText());
        System.out.println("quantityProduct " + quantityProduct);
        totalValue = totalValue * quantityProduct;
        //copy value to instance variable - addedProductCart
        return getAddedProductCart(product.getName(), productSize, totalValue, quantityProduct, toppingName, toppingQuantity, optionStr);
    }

    /**
     * get information on dashboard
     *
     * @param produdctName
     * @return
     */
    public List<ProductCart> getProductInformationOnDashboard(String produdctName) {
        ProductCart productCartItem = selectProductByName(produdctName);
        addProductToProductCart(productCartItem);
        return productCartList;
    }

    /**
     * get information on product section
     *
     * @param productName
     * @return
     */
    public ProductCart selectProductByName(String productName) {
        //get product price following variations
        product = apiAminService.getProductBySearchName(productName);
        float priceSelectedSizePrice = 0;
        String productPriceName = "";
        if (product.getProductPrices().size() > 1) {
            productPriceName = product.getProductPrices().get(0).getPriceName();
            if (productPriceName == null) {
                productPriceName = "";
            }
        } else {
            productPriceName = "";
        }
        priceSelectedSizePrice = product.getProductPrices().get(0).getPrice();
        float totalValue = priceSelectedSizePrice;
        //copy value to instance variable - addedProductCart
        return getAddedProductCart(product.getName(), productPriceName, totalValue, 1, "", 0, "");
    }

    //cart
    private void getTotalStr() {
        totalStr = totalPrice.getText();
    }

    public String getSubTotalStr() {
        try {
            return subTotalStr = helper.waitToElementGetText(subTotalPrice);
        } catch (Exception e) {
            Log.info("Element can't get text" + CreateOrderPage.class.getName());
            e.printStackTrace();
            return null;
        }
    }

    private Topping checkToppingInProductCart(int index) {
        List<WebElement> element = driver.findElements(By.xpath(productCartInformationTop + "[" + index + "]/android.view.ViewGroup"));
        Topping topping = new Topping();
        if (element.size() > 2) {
            try {
                WebElement nameTopping = driver.findElement(By.xpath(productCartInformationTop + "[" + index + "]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView"));
                topping.setName(nameTopping.getText());
                WebElement quantityTopping = driver.findElement(By.xpath(productCartInformationTop + "[" + index + "]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView"));
                String quantityStr = quantityTopping.getText().substring(1);
                topping.setQuantity(Integer.parseInt(quantityStr));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                System.out.println("No topping");
            }
        }
        return topping;
    }

    private String getSizeInProductCart(int index) {
        String str = "";
        try {
            WebElement sizeElement = driver.findElement(By.xpath(productCartInformationTop + "[" + index + "]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView"));
            str = sizeElement.getText();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("No size");
        }
        return str.replace("(", "").replace(")", "").trim();
    }

    private String getNameInProductCart(int index) {
        String str = "";
        try {
            WebElement sizeElement = driver.findElement(By.xpath(productCartInformationTop + "[" + index + "]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView"));
            str = sizeElement.getText();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("No name");
        }
        return str;
    }

    private float getPriceInProductCart(int index) {
        String str = "";
        try {
            WebElement sizeElement = driver.findElement(By.xpath(productCartInformationBottom + "[" + index + "]/android.view.ViewGroup[2]/android.widget.TextView"));
            str = sizeElement.getText();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("No price");
        }
        return Float.parseFloat(str.replace(",", ""));
    }

    private int getQuantityInProductCart(int index) {
        String str = "";
        try {
            WebElement sizeElement = driver.findElement(By.xpath(productCartInformationBottom + "[" + index + "]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView"));
            str = sizeElement.getText();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("No quantity");
        }
        return Integer.parseInt(str);
    }

    private String checkOptionInProductCart(int index) {
        List<WebElement> element = driver.findElements(By.xpath(productCartInformationTop + "[" + index + "]/android.view.ViewGroup"));
        String optionStr = "";
        if (element.size() > 2) {
            try {
                optionStr = driver.findElement(By.xpath(productCartInformationTop + "[" + index + "]/android.view.ViewGroup[2]/android.widget.TextView")).getText();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                System.out.println("No option");
            }
        }
        return optionStr;
    }

    /**
     * add new product to productCartItemList
     *
     * @param addedProduct
     * @return
     */
    public List<ProductCart> addProductToProductCart(ProductCart addedProduct) {
        if (addedProduct != null) {
            int quantity = 0;
            float price = 0;
            Boolean added = false; //check if the additional product exists in the list
            for (ProductCart pc : productCartList) {
                if (pc.getName().equalsIgnoreCase(addedProduct.getName())) {
                    if (pc.getSize().equalsIgnoreCase(addedProduct.getSize())) {
                        if (pc.getTopping().equalsIgnoreCase(addedProduct.getTopping())) {
                            if (pc.getToppingQuantity() == addedProduct.getToppingQuantity()) {
                                if (pc.getOption() == addedProduct.getOption()) {
                                    quantity = pc.getQuantity() + addedProduct.getQuantity();
                                    pc.setQuantity(quantity);
                                    price = pc.getPrice() + addedProduct.getPrice();
                                    pc.setPrice(price);
                                    added = true; //existed
                                    System.out.println("existed product");
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(added);
            if (!added) {
                productCartList.add(addedProduct);
                System.out.println("add product to product expected list");
            }
        }
        return productCartList;
    }

    /**
     * cart item = 3
     *
     * @return
     */
    public List<ProductCart> getProductCartUiList() {
        List<ProductCart> productCartUIList = new ArrayList<>();
        ProductCart productCart;
        //get Cart item on UI
        int cartSize = productNameCartList.size();
        for (int i = 0; i < cartSize; i++) {
            productCart = new ProductCart();
            productCart.setName(productNameCartList.get(i).getText()); //no get text
            String formatPrice = productPriceCartList.get(i).getText();
            productCart.setPrice(Float.parseFloat(formatPrice.replace(",", "")));
            productCart.setQuantity(Integer.parseInt(productQuantityCartList.get(i).getText()));
            try {
                productCart.setSize(productSizeCartList.get(i).getText().replace("(", "").replace(")", "").trim());
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                productCart.setSize("");
            }
            //get topping, option
            Topping toppingUI = checkToppingInProductCart(i + 1);
            if (toppingUI.getName() != null) {
                productCart.setTopping(toppingUI.getName());
                productCart.setToppingQuantity(toppingUI.getQuantity());
            } else {
                productCart.setTopping("");
                productCart.setToppingQuantity(0);
            }
            String optionStr = checkOptionInProductCart(i + 1);
            if (!optionStr.equalsIgnoreCase("")) {
                productCart.setOption(optionStr);
            } else {
                productCart.setOption("");
            }
            //set option and quantity
            productCartUIList.add(productCart);
        }
        return productCartUIList;
    }

    public List<ProductCart> getProductCartUiListMoreThan3() {
        List<ProductCart> productCartUIList = new ArrayList<>();
        List<WebElement> list = driver.findElements(By.xpath(productCartInformationTop));
        ProductCart productCart;
        //get Cart item on UI
        int cartSize = list.size();
        System.out.println("cartSize " + cartSize);
        if (cartSize > 3) {
            //scroll to top - support to take a screenshot and get data - must
            try {
                scrollInCart(true);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            list = driver.findElements(By.xpath(productCartInformationTop));
            cartSize = list.size();
            System.out.println("cartSize 2 " + cartSize);
            int index = 0;
            for (int i = 1; i <= cartSize; i++) {
                if (i > 3) {
                    try {
                        swipeVertical(list.get(i - 3), list.get(i - 2));
                        list = driver.findElements(By.xpath(productCartInformationTop));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        System.out.println("catch scroll");
                    }
                }
                productCart = new ProductCart();
                if (i > 4) index = 4;
                else index = i;
                productCart.setName(getNameInProductCart(index));
                System.out.println(productCart.getName());
                productCart.setPrice(getPriceInProductCart(index));
                productCart.setQuantity(getQuantityInProductCart(index));
                productCart.setSize(getSizeInProductCart(index));
                //get topping, option
                Topping toppingUI = checkToppingInProductCart(index);
                if (toppingUI.getName() != null) {
                    productCart.setTopping(toppingUI.getName());
                    productCart.setToppingQuantity(toppingUI.getQuantity());
                } else {
                    productCart.setTopping("");
                    productCart.setToppingQuantity(0);
                }
                String optionStr = checkOptionInProductCart(index);
                if (!optionStr.equalsIgnoreCase("")) {
                    productCart.setOption(optionStr);
                } else {
                    productCart.setOption("");
                }
                System.out.println(productCart.toString());
                //set option and quantity
                productCartUIList.add(productCart);
            }
        } else productCartUIList = getProductCartUiList();
        return productCartUIList;
    }

    public Boolean compare2ProductCartList(List<ProductCart> actualList, List<ProductCart> expectedList, int totalQuantityItemsActual) {
        //get Cart item on UI
        List<Boolean> flag = new ArrayList<>();
        float subtotalUI = 0, subtotalCart = 0;
        //productCartList is added productlist
        int totalQuantityItems = 0;
        System.out.println("ui " + actualList.toString());
        System.out.println("pc cart " + expectedList.toString());
        for (ProductCart pcUI : actualList) {
            for (ProductCart pc : expectedList) {
                if (pcUI.getName().equalsIgnoreCase(pc.getName())) {
                    if (pcUI.getSize().equalsIgnoreCase(pc.getSize())) {
                        if (pcUI.getTopping().equalsIgnoreCase(pc.getTopping())) {
                            if (pcUI.getToppingQuantity() == pc.getToppingQuantity()) {
                                if (pcUI.getOption().equalsIgnoreCase(pc.getOption())) {
                                    totalQuantityItems = totalQuantityItems + pc.getQuantity();
                                    subtotalUI = subtotalUI + pcUI.getPrice();
                                    subtotalCart = subtotalCart + pc.getPrice();
                                    flag.add(helper.checkValueEquals(pc.getName() + " price", pcUI.getPrice(), pc.getPrice()));
                                    actualRS = actualRS + helper.actualRS;
                                    flag.add(helper.checkValueEquals(pc.getName() + " quantity", pcUI.getQuantity(), pc.getQuantity()));
                                    actualRS = actualRS + helper.actualRS;
                                    flag.add(helper.checkValueEquals(pc.getName() + " Subtotal", subtotalUI, subtotalCart));
                                    actualRS = actualRS + helper.actualRS;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        quantityCartTotal = totalQuantityItems;
        subTotalStr = helper.formatCurrencyToThousand(subtotalCart);
        System.out.println("subTotalStr 1 " + subTotalStr);
        //check subtotal
        flag.add(subtotalUI == subtotalCart);
        flag.add(totalQuantityItemsActual == totalQuantityItems);
        return !flag.contains(false);
    }

    /**
     * check after change items
     *
     * @return
     */
    public Boolean compareCartListWithNewCart(List<ProductCart> expectedList) {
        String str = subTotalTxt.getText();
        String quantityStr = str.substring(str.indexOf("(") + 1, str.indexOf(")")).replace("items", "").trim();
        int totalQuantityItemsUI = Integer.parseInt(quantityStr);
//        List<ProductCart> productCartUIList = getProductCartUiList();
        List<ProductCart> productCartUIList = getProductCartUiListMoreThan3();
        List<Boolean> flag = new ArrayList<>();
        if (productCartUIList.size() == expectedList.size()) flag.add(true);
        else {
            flag.add(false);
            actualRS = actualRS + "Wrong cart list. Actual number items: " + productCartUIList.size() + " Expected: " + expectedList.size();
        }
        flag.add(compare2ProductCartList(productCartUIList, expectedList, totalQuantityItemsUI));
        return !flag.contains(false);
    }

    public Boolean checkProductOnCart() {
        helper.sleep(1);
        actualRS = "";
        String str = subTotalTxt.getText();
        String quantityStr = str.substring(str.indexOf("(") + 1, str.indexOf(")")).replace("items", "").trim();
        int totalQuantityItemsUI = Integer.parseInt(quantityStr);
        //get Cart item on UI
//        List<ProductCart> productCartUIList = getProductCartUiList();
        List<ProductCart> productCartUIList = getProductCartUiListMoreThan3();
        List<Boolean> flag = new ArrayList<>();
        float subtotalUI = 0, subtotalCart = 0;
        //productCartList is added product list
        //check 2 list size
        flag.add(helper.checkValueEquals("size", productCartUIList.size(), productCartList.size()));
        actualRS = actualRS + helper.actualRS;
        //check list
        flag.add(compare2ProductCartList(productCartUIList, productCartList, totalQuantityItemsUI));
        //check subtotal
        flag.add(helper.checkValueEquals("subtotal", subtotalUI, subtotalCart));
        actualRS = actualRS + helper.actualRS;
        //total quantity
        flag.add(helper.checkValueEquals("total quantity", totalQuantityItemsUI, quantityCartTotal));
        actualRS = actualRS + helper.actualRS;
        return !flag.contains(false);
    }

    public void updateProductCatList(ProductCart productCart, Boolean removed) {
        float subTotal = 0;
        List<ProductCart> oldList = productCartList;
        for (ProductCart pc : oldList) {
            if (pc.getName().equalsIgnoreCase(productCart.getName())) {
                scrollVertical(pc.getName());
                if (pc.getSize().equalsIgnoreCase(productCart.getSize())) {
                    if (pc.getTopping().equalsIgnoreCase(productCart.getTopping())) {
                        if (pc.getToppingQuantity() == productCart.getToppingQuantity()) {
                            if (pc.getOption().equalsIgnoreCase(productCart.getOption())) {
                                if (removed) {
                                    subTotal = helper.convertToFloat(subTotalStr) - pc.getPrice();
                                    oldList.remove(pc);
                                    break;
                                } else {
                                    pc.setQuantity(productCart.getQuantity());
                                    pc.setPrice(productCart.getPrice());
                                    subTotal = helper.convertToFloat(subTotalStr) - productCart.getPrice();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        subTotalStr = helper.formatCurrencyToThousand(subTotal);
        productCartList = oldList;
    }

    public List<ProductCart> reduceProductOnCart(int reduceNumber) {
//        List<ProductCart> productCartUiList = getProductCartUiList();
        List<ProductCart> productCartUiList = getProductCartUiListMoreThan3();
        scrollInCart(true);
        int cartItems = productCartUiList.size();
        List<Boolean> flag = new ArrayList<>();
        ProductCart productCart;
        Boolean removed = false;
        if (cartItems > 0) {
            int index = 1; //helper.generateRandomNumberWithBound(cartItems);
            productCart = productCartUiList.get(index);
            int quantity = productCartUiList.get(index).getQuantity();
            if (reduceNumber >= quantity) {
                reduceNumber = quantity;
                removed = true;
            }
            WebElement reduceBtn = productReduceCartBtnList.get(index);
            WebElement quantityElement;
            int newQuantity = quantity;
            while (reduceNumber > 0) {
                reduceBtn.click();
                reduceNumber--;
                newQuantity--;
                if (removed && reduceNumber > 0) {
                    quantityElement = productQuantityCartList.get(index);
                    helper.waitForTextToBe(quantityElement, String.valueOf(newQuantity));
                } else helper.sleepHaftSec();
            }
            if (removed) {
                productCartUiList.remove(index);
            } else {
                productCartUiList.get(index).setQuantity(newQuantity);
                float oldPrice = productCartUiList.get(index).getPrice();
                float price = (oldPrice / quantity) * newQuantity;
                productCartUiList.get(index).setPrice(price);
            }
            helper.sleep(1);
            updateProductCatList(productCart, removed);
        }
        return productCartUiList;
    }

    //search product
    public void clickSearchButton() {
        searchBtn.click();
        helper.sleep(1);
    }

    public void clickClearSearchProduct() {
        try {
            clickSearchButton();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        try {
            helper.waitToElementClick(clearSearchIcon);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
    }

    public String searchProductByName(String name) {
        clickSearchButton();
        helper.waitUtilElementVisible(searchField);
        helper.enterData(searchField, name);
        driver.hideKeyboard();
        helper.waitUtilElementVisible(searchResultBox);
        int size = searchItems.size();
        if (size == 0) {
            clickClearSearchProduct();
            helper.enterData(searchField, dataTest.SEARCH_PRODUCT_DEFAULT);
            driver.hideKeyboard();
        }
        helper.sleep(2);
        String nameProd = searchNameItems.get(0).getText();
        WebElement element = getSearchNameElement(nameProd);
        String selectedName = nameProd;
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        System.out.println("x: " + x + " y: " + y);
        clickByCoordinates(x, y);
        helper.sleep(1);
        //todo update xpath
//        helper.waitUtilElementVisible(productDetailName);
        System.out.println("selectedName " + selectedName);
        return selectedName;
    }

    public int upQuantityOnDetail(int addMore) {
        String quantityStr = quantityDetail.getText();
        int quantity = Integer.parseInt(quantityStr);
        int expectedQuantity = quantity + addMore;
        while (addMore > 0) {
            upQuantityDetail.click();
            helper.sleepHaftSec();
            quantityStr = quantityDetail.getText();
            quantity = Integer.parseInt(quantityStr);
            addMore--;
        }
        System.out.println(expectedQuantity == quantity);
        return expectedQuantity;
    }

    public int reduceQuantityOnDetail(int addMore) {
        String quantityStr = quantityDetail.getText();
        int quantity = Integer.parseInt(quantityStr);
        int expectedQuantity = 0;
        if (addMore >= quantity) {
            addMore = quantity;
        }
        while (addMore > 0) {
            reduceQuantityDetail.click();
            helper.sleepHaftSec();
            quantityStr = quantityDetail.getText();
            quantity = Integer.parseInt(quantityStr);
            addMore--;
        }
        return quantity;
    }

    public void clickCreateOrder() {
        try {
            helper.clickBtn(createOrderBtn);
        } catch (Exception exception) {
            Log.error("Can not click create order btn. \n" + exception.getMessage());
        }
    }

    public void scrollInCart(Boolean scrollTop) {
        int size = productNameCartList.size();
        int count = 5;
        String nameY = productNameCartList.get(size - 2).getText();
        String nameX = productNameCartList.get(size - 1).getText();
        String oldNameX = "";
        List<WebElement> nameList;
        //namex = namey
        while (!nameX.equals(oldNameX) && count > 0) {
            oldNameX = nameX;
            count--;
            swipeVertical(productNameCartList.get(size - 1), productNameCartList.get(size - 2));
            nameList = driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView"));
            try {
                nameX = nameList.get(size - 2).getText();
            } catch (Exception e) {
                Log.info(e.getMessage());
            }
        }
    }

    public static class ProductCart {
        private String name;
        private String size;
        private float price;
        private int quantity;
        private String note;

        public int getToppingQuantity() {
            return toppingQuantity;
        }

        public void setToppingQuantity(int toppingQuantity) {
            this.toppingQuantity = toppingQuantity;
        }

        private int toppingQuantity;

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getTopping() {
            return topping;
        }

        public void setTopping(String topping) {
            this.topping = topping;
        }

        private String option;
        private String topping;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        @Override
        public String toString() {
            return "ProductCart{" + "name='" + name + '\'' + ", size='" + size + '\'' + ", price=" + price + ", option='" + option + '\'' + ", topping='" + topping + '\'' + ", quantity='" + quantity + '\'' + '}';
        }

        public ProductCart() {
        }

        public ProductCart(String name, String size, float price, int quantity, String note, String option, String topping) {
            this.name = name;
            this.size = size;
            this.price = price;
            this.quantity = quantity;
            this.note = note;
            this.option = option;
            this.topping = topping;
        }

    }
}