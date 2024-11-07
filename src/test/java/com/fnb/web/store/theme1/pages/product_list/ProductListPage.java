package com.fnb.web.store.theme1.pages.product_list;

import com.fnb.utils.api.storeweb.admin.helpers.APIAminService;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.web.store.theme1.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme1.pages.flashSale.FlashSalePage;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
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
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public APIAminService apiAminService;
    private WebDriver driver;
    private CheckOutLoginPage checkOutLoginPage;
    private FlashSalePage flashSalePage;
    private CheckoutDataTest checkoutDataTest;
    private DataTest loginDataTest;
    public String actualRS = "";
    private By banner = By.xpath("//div[@class=\"banner-top-product-list\"]");
    //Message
    public By addProductSuccessToast = By.xpath("//div[@class=\"ant-notification-notice-message\"]");
    private String categoryId = "list-products-section-id-";
    private String categoryNameXP = ".//span[contains(@class,\"title-name\")]"; //concat with ID
    private String categoryQuantityXP = ".//span[contains(@class,\"quantity-products\")]"; //concat with ID
    private String categoryProductContainerXP = "./div[contains(@class,\"product-list__container\")]";
    private String categoryProductCartListXP = "./div[contains(@class,\"product-list__container\")]/div";
    //product information - get from productCartList
    private String productNameXP = ".//div[contains(@class,\"product-card__title\")]";
    private String productPriceXP = ".//span[contains(@class,\"product-card__price-sell\")]";
    private String productThumnailXP = ".//img[contains(@class,\"product-card__img\")]";
    private String productAddtoCartXP = ".//div[contains(@class,\"product-card__btn-add-to-cart\")]";
    //flash sale
    private String flashSaleLogoXP = ".//img[contains(@class,\"product-card__flashsale-bottom\")]";
    private String flashSaleDiscountPercentXP = ".//div[contains(@class,\"product-card__percent-discount\")]";
    private String flashSaleOriginalPriceXP = ".//span[contains(@class,\"product-card__price-discount\")]";
    private String flashSaleBorderXP = ".//img[contains(@class,\"product-card__flashsale-img\")]";

    public ProductListPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        flashSalePage = new FlashSalePage(driver);
    }

    //check display
    public Boolean checkDisplayOfCategoryBanner() {
        return helper.checkDisplayElement(banner);
    }

    //get values
    private String getCategoryIdByProductName(String productName) {
        return categoryId + apiAminService.getCategoryIdByProductId(productName);
    }

    private WebElement getCategoryElement(String productName) {
        return driver.findElement(By.id(getCategoryIdByProductName(productName)));
    }

    public void scrollToCategoryByProductName(String productName) {
        String element = getCategoryIdByProductName(productName);
        helper.waitForPresence(By.id(element));
        helper.visibleOfLocated(By.id(element));
        try {
            helper.scrollByJS(driver.findElement(By.id(element)));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.actionScrollToElement(driver.findElement(By.id(element)));
        }
        helper.waitForPresence(By.id(element));
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
            String nameStr = "";
            for (WebElement productCart : productCartElementList) {
                helper.scrollByJS(productCart);
                nameStr = productCart.getText().toLowerCase();
                if (nameStr.contains(productNameText.toLowerCase())) {
                    flag.add(checkDisplayFlashSale(isFlashSale, productCart, flashSaleProduct));
                    break;
                }
            }
        }
        if (flag.size() != flashSaleProductList.size()) flag.add(false);
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean clickFlashSaleProductByName(String flashSaleName) {
        actualRS = "";
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
            scrollToCategoryByProductName(productNameText);
            category = getCategoryElement(productNameText);
            try {
                category.findElements(By.xpath(categoryProductCartListXP));
                productCartElementList = category.findElements(By.xpath(categoryProductCartListXP));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.refreshPage();
                scrollToCategoryByProductName(productNameText);
                category = getCategoryElement(productNameText);
                productCartElementList = category.findElements(By.xpath(categoryProductCartListXP));
            }
            for (int i = 0; i < productCartElementList.size(); i++) {
                try {
                    helper.waitUtilElementVisible(productCartElementList.get(i));
                    helper.scrollByJS(productCartElementList.get(i));
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                }
                String nameStr = "";
                try {
                    nameStr = productCartElementList.get(i).getText().toLowerCase();
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    System.out.println("catch " + i);
                    helper.refreshPage();
                    scrollToCategoryByProductName(productNameText);
                    category = getCategoryElement(productNameText);
                    productCartElementList = category.findElements(By.xpath(categoryProductCartListXP));
                    helper.scrollByJS(productCartElementList.get(i));
                    nameStr = productCartElementList.get(i).getText().toLowerCase();
                }
                System.out.println(i + nameStr);
                if (nameStr.contains(productNameText.toLowerCase())) {
                    //click add to cart
                    try {
                        helper.actionHover(productCartElementList.get(i));
                        helper.waitUtilElementVisible(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                        helper.clickBtn(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        helper.clickByJS(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                    }
                    helper.waitForPresence(addProductSuccessToast);
                    try {
                        helper.waitUtilElementVisible(helper.getElement(addProductSuccessToast));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        System.out.println("Add successful toast message did not display");
                    }
                    //check cart
                    flag.add(flashSalePage.checkCartWhenFlashSale(flashSaleProduct.getName(), true)); //todo
                    actualRS = actualRS + flashSalePage.actualRS;
                    //clear cart
                    flashSalePage.checkoutThenClearCartWithoutLogin();
                    //product list page
                    helper.navigateTo(url);
                    break;
                }
            }
        }
        if (flag.size() != flashSaleProductList.size()) {
            actualRS = actualRS + "Check not full: " + flag.size() + " Expected: " + flashSaleProductList.size();
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }
}
