package com.fnb.web.store.theme2.pages.home;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.flashsale.FlashSalePage;
import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TodayMenuPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public String actualRS;
    private FlashSalePage flashSalePage;
    //element selector
    public By todayMenuComponent = By.id("themeTodayMenu");
    private String todayMenuIdStr = "//div[@id=\"themeTodayMenu\"]";
    private By todayIntro = By.xpath(todayMenuIdStr + "//div[contains(@class,\"menu-special-intro\")]");
    private By todayTitle = By.xpath(todayMenuIdStr + "//div[contains(@class,\"menu-special-title\")]/div");
    private By todayListUl = By.xpath(todayMenuIdStr + "//div[contains(@class,\"menu-special-nav\")]/ul");
    private By todayCategoryItems = By.xpath(todayMenuIdStr + "//div[contains(@class,\"menu-special-nav\")]/ul/li[not(contains(@class,\"-li-arrow-\"))]");
    private By todayCategoryAll = By.xpath(todayMenuIdStr + "//div[contains(@class,\"menu-special-nav\")]/ul/li[not(contains(@class,\"-li-arrow-\"))][1]");
    private By downArrow = By.xpath(todayMenuIdStr + "//div[contains(@class,\"menu-special-nav\")]/ul/li[contains(@class,\"-li-arrow-\")]");
    private By productLeftArrow = By.xpath(todayMenuIdStr + "//img[contains(@class,\"button-left-arrow\")]");
    private By productRightArrow = By.xpath(todayMenuIdStr + "//img[contains(@class,\"button-right-arrow\")]");
    private By swipperPagination = By.xpath(todayMenuIdStr + "//div[contains(@class,\"swiper-pagination-bullet\")]");
    private String dots3Swipper = "."; //work badly -> no check
    private String swipperActive = "swiper-pagination-bullet-active-main";
    //product
    private By productSwipper = By.xpath("//div[@id=\"today-special-menu-swiper\"]//div[contains(@class,\"swiper-wrapper\")]");
    private By productCardItems = By.xpath("//div[@id=\"today-special-menu-swiper\"]//div[contains(@class,\"swiper-wrapper\")]//div[contains(@class,\"product-item-card\")]");
    private String productImg = ".//div[contains(@class,\"product-img\")]";
    private String productRating = ".//div[contains(@class,\"product-rating\")]/img";
    private String productName = ".//div[contains(@class,\"product-name\")]";
    private String productDescription = ".//div[contains(@class,\"product-description\")]";
    private String productSellingPrice = ".//div[contains(@class,\"product-price-with-discount\")]";
    private String productOriginalPrice = ".//div[@class=\"product-price\"]";
    private String productAddtoCart = ".//div[contains(@class,\"price-box-left\")]/following-sibling::div";
    //flash sale on today menu
    private String todayProductFlashSaleBorderXP = ".//div[contains(@class,\"flash-sale-border\")]";
    private String todayProductFlashSaleLogoXP = ".//div[contains(@class,\"flash-sale\")]/img";
    private String todayProductFlashSaleDiscountXP = ".//div[contains(@class,\"promotion-label\")]/span";

    public TodayMenuPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        flashSalePage = new FlashSalePage(driver);
    }

    //check display
    public Boolean checkDisplayOfTodayMenu() {
        return helper.checkDisplayElement(todayMenuComponent);
    }

    public Boolean checkDisplayOfTodayMenuIntro() {
        return helper.checkDisplayElement(todayIntro);
    }

    public Boolean checkDisplayOfTodayMenuTitle() {
        return helper.checkDisplayElement(todayTitle);
    }

    public Boolean checkDisplayOfTodayMenuCategoryMenu() {
        return helper.checkDisplayElement(todayListUl);
    }

    public Boolean checkDisplayOfTodayMenuSwiper() {
        return helper.checkDisplayElement(productSwipper);
    }

    public Boolean checkDisplayOfTodayMenuRightArrow() {
        return helper.checkDisplayElement(productRightArrow);
    }

    public Boolean checkDisplayOfTodayMenuLeftArrow() {
        return helper.checkDisplayElement(productLeftArrow);
    }

    public Boolean checkDisplayOfTodayMenuPagination() {
        return helper.checkDisplayElement(swipperPagination);
    }

    public Boolean checkDisplayOfTodayMenuAllCategorySlide() {
        try {
            helper.scrollByJS(driver.findElement(todayMenuComponent));
            return helper.checkDisplayElement(todayCategoryAll);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            return false;
        }
    }

    //actions
    public String clickTodayMenuCardRandom() {
        if (checkDisplayOfTodayMenu()) {
            helper.scrollByJS(driver.findElement(todayMenuComponent));
            List<WebElement> list = helper.getElementList(productCardItems);
            int size = list.size();
            Random r = new Random();
            int index = r.nextInt(size);
            return clickTodayMenuCardWithIndex(index);
        } else {
            return null;
        }
    }

    public String clickTodayMenuCardWithIndex(int index) {
        if (checkDisplayOfTodayMenu()) {
            try {
                helper.presenceOfAllElementsLocatedBy(productCardItems);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            helper.scrollByJS(driver.findElement(todayMenuComponent));
            List<WebElement> list = helper.getElementList(productCardItems);
            helper.scrollByJS(list.get(index));
            helper.waitUtilElementVisible(list.get(index));
            String productNameStr = list.get(index).findElement(By.xpath(productName)).getText();
            System.out.println(productNameStr);
            try {
                list.get(index).findElement(By.xpath(productName)).click();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(list.get(index).findElement(By.xpath(productName)));
            }
            return productNameStr;
        } else {
            return null;
        }
    }

    public void clickRightArrowSwiper() {
        if (checkDisplayOfTodayMenuRightArrow()) {
            helper.scrollByJS(driver.findElement(productRightArrow));
            try {
                helper.clickBtn(driver.findElement(productRightArrow));
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                helper.clickByJS(driver.findElement(productRightArrow));
            }
        }
    }

    public void clickLeftArrowSwiper() {
        if (checkDisplayOfTodayMenuRightArrow()) {
            helper.scrollByJS(driver.findElement(productLeftArrow));
            try {
                helper.clickBtn(driver.findElement(productLeftArrow));
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                helper.clickByJS(driver.findElement(productLeftArrow));
            }
        }
    }

    private void clickAddToCartIcon(int index) {
        helper.visibilityOfAllElementsLocatedBy(productCardItems);
        List<WebElement> cardList = helper.getElementList(productCardItems);
        try {
            WebElement e = cardList.get(index).findElement(By.xpath(productAddtoCart));
            helper.clickBtn(e);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
        }
    }

    //flash sale todo check flash sale
    public Boolean checkDisplayOfFlashSale(String flashSaleName, Boolean isFlashSale) {
        List<String> productNameCheckedList = new ArrayList<>();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleName);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<Boolean> flag = new ArrayList<>();
        String productCardName = "";
        WebElement checkElement;
        Boolean checked;
        actualRS = "";
        int checkedNumItems = 0;
        if (!checkDisplayOfTodayMenuAllCategorySlide()) {
            flag.add(false);
            actualRS = "All category on today menu did not display.";
        } else {
            helper.visibleOfLocated(todayCategoryAll);
            helper.scrollByJS(driver.findElement(todayCategoryAll));
            helper.waitForPresenceAllements(productCardItems);
            List<WebElement> productCardList = helper.getElementList(productCardItems);
            for (int i = 0; i < productCardList.size(); i++) {
                if (i % 3 == 0 && i != 0) clickRightArrowSwiper();
                checkElement = productCardList.get(i);
                productCardName = checkElement.findElement(By.xpath(productName)).getText();
                for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
                    if (productCardName.equalsIgnoreCase(flashSaleProduct.getName())) {
                        try {
                            checked = helper.checkDisplayElementByElement(checkElement.findElement(By.xpath(todayProductFlashSaleBorderXP)));
                            if (!checked) actualRS = actualRS + "Flash sale border did not display. \n";
                            flag.add(checked);
                        } catch (Exception exception) {
                            Log.error("Can find element \n" + exception.getMessage());
                            flag.add(false);
                            actualRS = actualRS + "Flash sale border did not display. \n";
                        }
                        try {
                            checked = helper.checkDisplayElementByElement(checkElement.findElement(By.xpath(todayProductFlashSaleLogoXP)));
                            if (!checked) actualRS = actualRS + "Flash sale border did not display. \n";
                            flag.add(checked);
                        } catch (Exception exception) {
                            Log.error("Can find element \n" + exception.getMessage());
                            flag.add(false);
                            actualRS = actualRS + "Flash sale border did not display. \n";
                        }
                        try {
                            WebElement element = checkElement.findElement(By.xpath(todayProductFlashSaleDiscountXP));
                            checked = helper.checkDisplayElementByElement(element);
                            if (!checked) actualRS = actualRS + "Flash sale discount did not display. \n";
                            else {
                                String discountPercent = helper.formatPercentDiscount(helper.calculateDiscountPercent(flashSaleProduct.getPrice(), flashSaleProduct.getFlashSalePrice()));
                                String discountStr = element.getText();
                                if (discountStr.equals(discountPercent)) {
                                    flag.add(true);
                                } else {
                                    actualRS = actualRS + "Flash sale discount displayed incorrect! Actual: " + discountStr + " Expected: " + discountPercent + "\n";
                                    flag.add(false);
                                }
                            }
                            flag.add(checked);
                        } catch (Exception exception) {
                            Log.error("Can find element \n" + exception.getMessage());
                            flag.add(false);
                            actualRS = actualRS + "Flash sale discount did not display. \n";
                        }
                        int price = 0;
                        if (isFlashSale) price = flashSaleProduct.getFlashSalePrice();
                        else price = flashSaleProduct.getPrice();
                        try {
                            WebElement element = checkElement.findElement(By.xpath(productSellingPrice));
                            checked = helper.checkDisplayElementByElement(element);
                            if (!checked) actualRS = actualRS + "Flash sale selling price did not display. \n";
                            else {
                                String sellingPriceStr = element.getText();
                                if (sellingPriceStr.equals(helper.formatCurrencyToThousand(price))) {
                                    flag.add(true);
                                } else {
                                    actualRS = actualRS + "Flash sale selling price displayed incorrect! Actual: " + sellingPriceStr + " Expected: " + price + "\n";
                                    flag.add(false);
                                }
                            }
                            flag.add(checked);
                        } catch (Exception exception) {
                            Log.error("Can find element \n" + exception.getMessage());
                            flag.add(false);
                            actualRS = actualRS + "Flash sale selling price did not display. \n";
                        }
                        if (isFlashSale) {
                            try {
                                WebElement element = checkElement.findElement(By.xpath(productOriginalPrice));
                                checked = helper.checkDisplayElementByElement(element);
                                if (!checked) actualRS = actualRS + "Flash sale original price did not display. \n";
                                else {
                                    String originalPriceStr = element.getText();
                                    if (originalPriceStr.equals(helper.formatCurrencyToThousand(flashSaleProduct.getPrice()))) {
                                        flag.add(true);
                                    } else {
                                        actualRS = actualRS + "Flash sale selling price displayed incorrect! Actual: " + originalPriceStr + " Expected: " + helper.formatCurrencyToThousand(flashSaleProduct.getPrice()) + "\n";
                                        flag.add(false);
                                    }
                                }
                                flag.add(checked);
                            } catch (Exception exception) {
                                Log.error("Can find element \n" + exception.getMessage());
                                flag.add(false);
                                actualRS = actualRS + "Flash sale original price did not display. \n";
                            }
                        } else {
                            try {
                                WebElement element = checkElement.findElement(By.xpath(productOriginalPrice));
                                checked = helper.checkDisplayElementByElement(element);
                                if (checked) actualRS = actualRS + "Flash sale original price is displaying. \n";
                                flag.add(checked);
                            } catch (Exception exception) {
                                Log.error("Can find element \n" + exception.getMessage());
                                flag.add(true);
                            }
                        }
                        checkedNumItems++;
                        productNameCheckedList.add(productCardName);
                        break;
                    }
                }
            }
        }
        if (checkedNumItems != flashSaleProductList.size()) {
            flag.add(false);
            String productName = "";
            for (FlashSaleProduct product : flashSaleProductList) {
                if (!productNameCheckedList.contains(product.getName())) {
                    productName = productName + "\n" + product.getName();
                }
            }
            actualRS = actualRS + "Product flash sale did not display enough number. But it cannot belong to the selected branch. Please check again. \n" + productName;
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    //todo add to cart then check cart
    public Boolean checkCartAfterClickAddToCart(String flashSaleName, Boolean isFlashSale) {
        List<String> productNameCheckedList = new ArrayList<>();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleName);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<Boolean> flag = new ArrayList<>();
        String productCardName = "";
        WebElement checkElement;
        Boolean checked;
        actualRS = "";
        int checkedNumItems = 0;
        if (!checkDisplayOfTodayMenuAllCategorySlide()) {
            flag.add(false);
            actualRS = "All category on today menu did not display.";
        } else {
            helper.visibleOfLocated(todayCategoryAll);
            helper.scrollByJS(driver.findElement(todayCategoryAll));
            helper.waitForPresenceAllements(productCardItems);
            List<WebElement> productCardList = helper.getElementList(productCardItems);
            for (int i = 0; i < productCardList.size(); i++) {
                if (i % 3 == 0 && i != 0) clickRightArrowSwiper();
                checkElement = productCardList.get(i);
                productCardName = checkElement.findElement(By.xpath(productName)).getText();
                for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
                    if (productCardName.equalsIgnoreCase(flashSaleProduct.getName())) {
                        if (productCardName.contains(flashSaleProduct.getProductPriceName())) {
                            try {
                                WebElement e = productCardList.get(i).findElement(By.xpath(productAddtoCart));
                                try {
                                    helper.clickBtn(e);
                                } catch (Exception exception) {
                                    Log.info(exception.getMessage());
                                    helper.clickByJS(e);
                                }
                            } catch (Exception exception) {
                                Log.info(exception.getMessage());
                            }
                            helper.refreshPage();
                            flag.add(flashSalePage.checkCartPriceWhenFlashSale(flashSaleProduct, isFlashSale, true));
                        }
                    }
                    break;
                }
            }
        }
        if (flag.contains(false)) {
            return false;
        } else return true;
    }
}
