package com.fnb.web.store.theme2.pages.product_list;

import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme2.pages.flashsale.FlashSalePage;
import com.fnb.web.store.theme2.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme2.pages.login.DataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductListPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    private CheckOutLoginPage checkOutLoginPage;
    private FlashSalePage flashSalePage;
    private CheckoutDataTest checkoutDataTest;
    private DataTest dataTest;
    public static String actualRS = "";
    //Notification popup
    public static By notificationOkayBTN = By.xpath("//div[contains(@class,\"notification-container-theme2\")]//button");
    //Message
    public static By addProductSuccessToast = By.xpath("//div[contains(@class,\"toast-message-add-update-to-cart\")]");
    //locator
    private String categoryProductContainerXP = "./following-sibling::div[1]";
    private String categoryProductCartListXP = "./following-sibling::div[1]//div[contains(@class,\"product-item-card\")]";
    //product information - get from productCartList
    private String productNameXP = ".//div[contains(@class,\"product-name\")]";
    private String productPriceXP = ".//div[contains(@class,\"product-price-with-discount\")]";
    private String productThumnailXP = ".//div[contains(@class,\"fnb-display-image\")]//img";
    private String productRatingXP = ".//div[contains(@class,\"product-rating\")]";
    private String productRatingStarsXP = ".//div[contains(@class,\"product-rating\")]//img";
    private String productDescriptionXP = ".//div[contains(@class,\"product-description\")]";
    private String productAddtoCartXP = ".//div[@class=\"price-box\"]/div[2]";
    //flash sale
    private String flashSaleLogoXP = ".//img[contains(@class,\"flash-sale-logo\")]";
    private String flashSaleDiscountPercentXP = ".//div[contains(@class,\"promotion-label\")]/span";
    private String flashSaleOriginalPriceXP = ".//div[@class=\"product-price\"]";
    private String flashSaleBorderXP = ".//div[contains(@class,\"flash-sale-border\")]";

    public ProductListPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        flashSalePage = new FlashSalePage(driver);
    }

    //actions
    private String getCategoryIdByProductName(String productName) {
        return apiAminService.getCategoryIdByProductId(productName);
    }

    private WebElement getCategoryElement(String productName) {
        return driver.findElement(By.id(getCategoryIdByProductName(productName)));
    }

    private List<WebElement> waitCategoryProductCartLoading(String productName) {
        scrollToCategoryByProductName(productName);
        WebElement category = getCategoryElement(productName);
        String categoryName = category.getText();
        List<WebElement> cardList = new ArrayList<>();
        try {
            category.findElements(By.xpath(categoryProductCartListXP));
            cardList = category.findElements(By.xpath(categoryProductCartListXP));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            scrollToCategoryByProductName(productName);
            category = getCategoryElement(productName);
            cardList = category.findElements(By.xpath(categoryProductCartListXP));
        }
        int loopNo = 2;
        String categoryQuantityFormat = "(" + cardList.size();
        while (loopNo > 0) {
            if (categoryName.toLowerCase().contains(categoryQuantityFormat)) break;
            try {
                Thread.sleep(500);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            try {
                cardList = category.findElements(By.xpath(categoryProductCartListXP));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                category = getCategoryElement(productName);
                scrollToCategoryByProductName(productName);
                cardList = category.findElements(By.xpath(categoryProductCartListXP));
            }
            categoryQuantityFormat = "(" + cardList.size();
            loopNo--;
        }
        return cardList;
    }

    public void scrollToCategoryByProductName(String productName) {
        String id = getCategoryIdByProductName(productName);
        helper.waitForPresence(By.id(id));
        helper.visibleOfLocated(By.id(id));
        WebElement e = driver.findElement(By.id(id));
        try {
            helper.scrollByJS(e);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.actionScrollToElement(e);
        }
        helper.waitForPresence(By.id(id));
    }

    //flash sale
    private Boolean checkDisplayFlashSale(Boolean isFlashSale, WebElement productCart, FlashSaleProduct flashSaleProduct) {
        List<Boolean> flag = new ArrayList<>();
        if (isFlashSale) {
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleBorderXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale border did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale border did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleLogoXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale logo did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale logo did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleDiscountPercentXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    String discountPercent = helper.formatPercentDiscount(helper.calculateDiscountPercent(flashSaleProduct.getPrice(), flashSaleProduct.getFlashSalePrice()));
                    if (element.getText().equals(discountPercent)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale discount displayed incorrect! Actual: " + element.getText() + " Expected: " + discountPercent + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale discount tag did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale discount tag did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(productNameXP));
                if (element.getText().equals(flashSaleProduct.getName())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale name displayed incorrect! Actual: " + element.getText() + " Expected: " + flashSaleProduct.getName() + "\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale name did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(productPriceXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    if (element.getText().equals(helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice()))) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale price displayed incorrect! Actual: " + element.getText() + " Expected: " + helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice()) + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale price did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale price did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleOriginalPriceXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    if (element.getText().equals(helper.formatCurrencyToThousand(flashSaleProduct.getPrice()))) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price displayed incorrect! Actual: " + element.getText() + " Expected: " + helper.formatCurrencyToThousand(flashSaleProduct.getPrice()) + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale original price did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale original price did not display\n";
                flag.add(false);
            }
        } else {
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleBorderXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale border did not display\n";
                } else {
                    flag.add(true);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                flag.add(true);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleLogoXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale logo did not display\n";
                } else {
                    flag.add(true);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                flag.add(true);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleDiscountPercentXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale discount tag did not display\n";
                } else {
                    flag.add(true);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                flag.add(true);
            }
            String productName = flashSaleProduct.getName().replaceAll("\\s*\\(.*\\)", "");
            String productPrice = apiAminService.getFirstProductPriceByName(productName);
            try {
                WebElement element = productCart.findElement(By.xpath(productPriceXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    if (element.getText().equals(productPrice)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price displayed incorrect! Actual: " + element.getText() + " Expected: " + productPrice + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale original price did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale original price did not display\n";
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * Not do for full variations
     *
     * @param flashSaleName
     * @param isFlashSale
     * @return
     */
    public Boolean checkProductFlashSaleByProductName(String flashSaleName, Boolean isFlashSale) {
        List<Boolean> flag = new ArrayList<>();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleName);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<WebElement> productCartElementList = new ArrayList<>();
        WebElement category;
        String productNameText = "";
        actualRS = "";
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            productNameText = flashSaleProduct.getName().replaceAll("\\s*\\(.*\\)", "");
            scrollToCategoryByProductName(productNameText);
            category = getCategoryElement(productNameText);
            try {
                productCartElementList = category.findElements(By.xpath(categoryProductCartListXP));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.refreshPage();
                scrollToCategoryByProductName(productNameText);
                category = getCategoryElement(productNameText);
                productCartElementList = category.findElements(By.xpath(categoryProductCartListXP));
            }
            for (WebElement productCart : productCartElementList) {
                if (productCart.getText().toLowerCase().contains(productNameText.toLowerCase())) {
                    flag.add(checkDisplayFlashSale(isFlashSale, productCart, flashSaleProduct));
                    break;
                }
            }
        }
        if (flag.size() != flashSaleProductList.size()) flag.add(false);
        if (flag.contains(false)) return false;
        else return true;
    }

    private Boolean isLogin = false;
    private int cc = 0;

    public Boolean clickFlashSaleProductByName(String flashSaleName, String phone, String password, Boolean isEnterAddress, String address, int addressIndex) {
        String url = helper.getCurrentURL();
        List<Boolean> flag = new ArrayList<>();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleName);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<WebElement> productCartElementList = new ArrayList<>();
        List<String> productNameList = new ArrayList<>();
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            productNameList.add(flashSaleProduct.getName());
        }
        WebElement category;
        String productNameText = "";
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            productNameText = flashSaleProduct.getName().replaceAll("\\s*\\(.*\\)", "");
            productCartElementList = waitCategoryProductCartLoading(productNameText);
            for (int i = 0; i < productCartElementList.size(); i++) {
                String nameStr = productCartElementList.get(i).findElement(By.xpath(productNameXP)).getText().toLowerCase();
                if (nameStr.contains(productNameText.toLowerCase())) {
                    //click add to cart
                    try {
                        helper.scrollByJS(productCartElementList.get(i));
                        helper.actionHover(productCartElementList.get(i));
                        helper.waitUtilElementVisible(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                        helper.clickBtn(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        helper.clickByJS(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                    }
                    try {
                        helper.waitForPresence(addProductSuccessToast);
                        helper.waitUtilElementVisible(helper.getElement(addProductSuccessToast));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        System.out.println("Add successful toast message did not display");
                    }
                    helper.refreshPage(); //display add user location ->
                    //check cart
                    flag.add(flashSalePage.checkCartWhenFlashSale(flashSaleProduct.getName(), true));
                    cc++;
                    actualRS = commonPagesTheme2().homePage.actualRS;
                    //clear cart
                    helper.refreshPage();
                    if (isLogin == false) {
                        flashSalePage.checkoutThenClearCartLogin(phone, password, isEnterAddress, address, addressIndex);
                        isLogin = true;
                    } else flashSalePage.checkoutThenClearCartWithoutLogin();
                    //product list page
                    helper.navigateTo(url);
                    break;
                }
            }
        }
        if (flag.size() != flashSaleProductList.size()) {
            flag.add(false);
            actualRS = actualRS + "Size is incorrect. Actual: " + flag.size() + " Expected: " + flashSaleProductList.size();
        }
        if (flag.contains(false)) return false;
        else return true;
    }
}
