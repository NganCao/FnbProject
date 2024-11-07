package com.fnb.web.store.theme1.pages.flashSale;

import com.fnb.utils.api.storeweb.admin.helpers.APIAminService;
import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.api.storeweb.pos.helpers.APIPosService;
import com.fnb.utils.api.storeweb.pos.helpers.JsonAPIPosReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.HelperDataFaker;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.myOrder.MyOrderPage;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsDataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlashSalePage extends Setup {
    private WebDriver driver;
    private Helper helper;
    private APIPosService helpersAPIPos;
    private WebDriverWait wait;
    private Actions actions;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private ProductDetailsDataTest productDetailsDataTest;
    private CheckOutLoginPage checkOutLoginPage;
    private MyOrderPage myOrderPage;
    public String actualRS = "";
    private String actualCheckLanguage = "";
    public String expectedRS = "";
    private HelperDataFaker faker;
    private JsonReader jsonReader;
    private JsonAPIAdminReader jsonAPIAdminReader;
    private JsonAPIPosReader jsonAPIPosReader;
    private FlashSaleDataTest flashSaleDataTest;
    private APIAminService apiAminService;
    public String currentWindow;
    public String size = "";
    public Map<String, String> timer = new HashMap<>();
    public String flashSaleName;
    private LocalDateTime currentDateTime;
    private By flashSaleComponent = By.id("themeFlashSale");
    private By flashSaleMainSession = By.xpath("//div[@id=\"themeFlashSale\"]/div");
    private By flashSaleLogo = By.xpath("//div[contains(@class,\"flash-sale-logo\")]/img");
    private By flashSaleTabActive = By.xpath("//div[@id=\"themeFlashSale\"]//div[contains(@class,\"ant-tabs-tab-btn\")]/parent::div[contains(@class,\"ant-tabs-tab-active\")]");
    private By flashSaleTabList = By.xpath("//div[@id=\"themeFlashSale\"]//div[contains(@class,\"ant-tabs-tab-btn\")]/parent::div");
    private String flashSaleTabName = ".//div[contains(@class,\"tab-item-name\")]";
    private String flashSaleTabTime = ".//div[contains(@class,\"tab-item-time\")]";
    private By flashSaleCountdown = By.xpath("//div[contains(@class,\"flip-countdown\")]");
    private String flashSaleCountdownXP = ".//div[starts-with(@class, '_SKh-V')]";
    private String flashSaleEndedSwiperId = ".//div[@id=\"rc-tabs-1-tab-0\"]";
    private String flashSaleComingSwiperId = ".//div[@id=\"rc-tabs-1-tab-2\"]";
    private String flashSaleEndAfterSwiperId = ".//div[@id=\"rc-tabs-1-tab-1\"]";
    private By flashSaleComingSwiper = By.xpath("//div[@id=\"themeFlashSale\"]//div[contains(@class,\"ant-tabs-tab-active\")]/div[@id=\"rc-tabs-1-tab-2\"]");
    private By flashSaleEndAfterSwiper = By.xpath("//div[@id=\"themeFlashSale\"]//div[contains(@class,\"ant-tabs-tab-active\")]/div[@id=\"rc-tabs-1-tab-1\"]");
    private By flashSaleEndedSwiper = By.xpath("//div[@id=\"themeFlashSale\"]//div[contains(@class,\"ant-tabs-tab-active\")]/div[@id=\"rc-tabs-1-tab-0\"]");
    private By flashSaleItems = By.xpath("//div[@id=\"themeFlashSale\"]//div[contains(@class,\"ant-tabs-tabpane-active\")]//a");
    private String flashSaleImage = ".//img";
    private String flashSaleNameXP = ".//div[@class=\"name\"]";
    private String flashSalePriceXP = ".//div[@class=\"price\"]";
    private String flashSaleSellingPriceXP = ".//div[@class=\"selling-price\"]";
    private String flashSaleOriginalPriceXP = ".//div[@class=\"original-price\"]";
    private String flashSaleQuantityBarTextXP = ".//div[@class=\"quantity-bar\"]//div[contains(@class,\"quantity-bar-text\")]";
    private String flashSaleQuantityBarNumberXP = ".//div[@class=\"quantity-bar\"]//div[contains(@class,\"quantity-bar-number\")]";
    private String flashSaleQuantityBarXP = ".//div[@class=\"quantity-bar\"]";
    private String flashSalePercentLabelXP = ".//div[@class=\"image\"]//div[@class=\"percent-label\"]";
    private String flashSaleFireIconXP = ".//div[contains(@class,\"fire\")]";

    public FlashSalePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        this.driver = driver;
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
    }

    //flash sale
    public Boolean checkDisplayOfFlashSaleComponent() {
        helper.waitForJStoLoad();
        boolean isDisplay = helper.checkDisplayElement(flashSaleComponent);
        if (isDisplay) {
            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
            return true;
        } else return false;
    }

    public Boolean checkActiveStatusTab(Boolean hasEnded, Boolean hasEndAfter, Boolean hasComing) {
        if (checkDisplayOfFlashSaleComponent() == true) {
            if (hasEndAfter) {
                return helper.checkDisplayElement(flashSaleEndAfterSwiper);
            } else if (!hasEndAfter && hasComing) {
                return helper.checkDisplayElement(flashSaleComingSwiper);
            } else if (hasEnded && !hasEndAfter && !hasComing) {
                return helper.checkDisplayElement(flashSaleEndedSwiper);
            } else return false; //not have active tab
        } else {
            if (hasComing && hasEndAfter) {
                actualRS = "Flash sale did not display";
                return false;
            } else return true;
        }
    }

    public void clickFlashSaleTab(int statusTab) {
        if (checkDisplayOfFlashSaleComponent() == true) {
            if (statusTab == 0) {
                try {
                    helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                    try {
                        helper.clickBtn(driver.findElement(flashSaleTabList).findElement(By.xpath(flashSaleEndedSwiperId)));
                        helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                    }
                }
            } else if (statusTab == 1) {
                try {
                    helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndAfterSwiperId)));
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                    try {
                        helper.clickBtn(driver.findElement(flashSaleTabList).findElement(By.xpath(flashSaleEndAfterSwiperId)));
                        helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                    }
                }
            } else {
                try {
                    helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleComingSwiperId)));
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                    try {
                        helper.clickBtn(driver.findElement(flashSaleTabList).findElement(By.xpath(flashSaleComingSwiperId)));
                        helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId)));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                    }
                }
            }
        }
    }

    public String getFlashSaleTabTime() {
        checkDisplayOfFlashSaleComponent();
        try {
            return helper.getElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime)).getText();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            return helper.getElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime)).getText();
        }
    }

    private String getFlashSaleName() {
        Random random = new Random();
        return flashSaleDataTest.FLASH_SALE_NAME.concat(String.valueOf(random.nextInt(100001)));
    }

    private List<String> getProductNameList() {
        List<String> productNameFlashSale = new ArrayList<>();
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_1);
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_2);
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_3);
        return productNameFlashSale;
    }

    private List<String> getProductNameListFull() {
        List<String> productNameFlashSale = new ArrayList<>();
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_2);
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_3);
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_7);
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_9);

        System.out.println(productNameFlashSale);
        return productNameFlashSale;
    }

    private List<String> getProductNameListCheckSpecialBranch() {
        List<String> productNameFlashSale = new ArrayList<>();
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_1);
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_2);
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_4);
        return productNameFlashSale;
    }

    private List<String> getProductNameSameCategory() {
        List<String> productNameFlashSale = new ArrayList<>();
        String productName = flashSaleDataTest.PRODUCT_FLASH_SALE_1;
        List<Product> productList = jsonAPIAdminReader.getProductListWithCategoryIdFromJson(apiAminService.getCategoryIdByProductId(productName));
        for (Product product : productList) {
            if (!product.getName().equals(productName)) {
                productNameFlashSale.add(product.getName());
            }
        }
        System.out.println(productNameFlashSale);
        return productNameFlashSale;
    }

    public void createFlashSale(int addStartMinute, int addEndMinute, Boolean checkBranch) {
        List<String> productNameFlashSale = new ArrayList<>();
        if (!checkBranch) productNameFlashSale = getProductNameList();
        else productNameFlashSale = getProductNameListCheckSpecialBranch();
        flashSaleName = getFlashSaleName();
        System.out.println(flashSaleName);
        currentDateTime = apiAminService.createFlashSaleList(productNameFlashSale, flashSaleName, addStartMinute, addEndMinute);
        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("start: " + timer.get("start"));
        System.out.println("end: " + timer.get("end"));
    }

    public void createFlashSaleWithMinimumPurchaseOrder(int addStartMinute, int addEndMinute, int maximumQuantity, int flashSaleQuantity, int minimumPurchaseOrder) {
        List<String> productNameFlashSale = new ArrayList<>();
        productNameFlashSale = getProductNameList();
        flashSaleName = getFlashSaleName();
        System.out.println(flashSaleName);
        currentDateTime = apiAminService.createFlashSaleWithMinimumPurchaseOrder(productNameFlashSale, flashSaleName, addStartMinute, addEndMinute, maximumQuantity, flashSaleQuantity, minimumPurchaseOrder);
        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("start: " + timer.get("start"));
        System.out.println("end: " + timer.get("end"));
    }

    private List<String> getBranchNameListStrings() {
        List<String> branchName = new ArrayList<>();
        if (jsonReader.getEnviroment.equals("stag")) {
            branchName.add(homeDataTest.BRANCH_NAME_STAG);
        } else {
            branchName.add(homeDataTest.BRANCH_NAME);
        }
        return branchName;
    }

    public void createFlashSaleSpecialBranch(int addStartMinute, int addEndMinute) {
        int lastAddEndMinutes = 0;
        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
        List<String> productNameFlashSale = getProductNameListCheckSpecialBranch();
        flashSaleName = getFlashSaleName();
        System.out.println(flashSaleName);
        List<String> branchNameList = getBranchNameListStrings();
        currentDateTime = apiAminService.createFlashSaleWithSpecialBranch(productNameFlashSale, flashSaleName, addStartMinute, lastAddEndMinutes, branchNameList);
        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("start: " + timer.get("start"));
        System.out.println("end: " + timer.get("end"));
    }

    public void createFlashSaleNotFullVariations(int addStartMinute, int addEndMinute) {
        int lastAddEndMinutes = 0;
        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
        List<String> productNameFlashSale = getProductNameList();
        flashSaleName = getFlashSaleName();
        System.out.println(flashSaleName);
        currentDateTime = apiAminService.createFlashSaleListNotFullVariations(productNameFlashSale, flashSaleName, addStartMinute, lastAddEndMinutes);
        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("start: " + timer.get("start"));
        System.out.println("end: " + timer.get("end"));
    }

    public void createFlashSaleWithQuantity(int addStartMinute, int addEndMinute, int maximumLimit, int flashSaleQuantity) {
        int lastAddEndMinutes = 0;
        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
        List<String> productNameFlashSale = new ArrayList<>();
        productNameFlashSale.add(flashSaleDataTest.PRODUCT_FLASH_SALE_1);
        flashSaleName = getFlashSaleName();
        System.out.println(flashSaleName);
        currentDateTime = apiAminService.createFlashSaleWithQuantity(productNameFlashSale, flashSaleName, maximumLimit, flashSaleQuantity, addStartMinute, lastAddEndMinutes);
        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("start: " + timer.get("start"));
        System.out.println("end: " + timer.get("end"));
    }

    /**
     * Create flash sale with special variation and included topping
     *
     * @param addStartMinute
     * @param addEndMinute
     * @param variation
     * @param maximumLimit
     * @param flashSaleQuantity
     */
    public void createFlashSaleVariationTopping(List<String> productNameFlashSale, int addStartMinute, int addEndMinute, String variation, Boolean isTopping, int maximumLimit, int flashSaleQuantity) {
        int lastAddEndMinutes = 0;
        lastAddEndMinutes = lastAddEndMinutes + addEndMinute;
        flashSaleName = getFlashSaleName();
        System.out.println(flashSaleName);
        currentDateTime = apiAminService.createFlashSaleWithVariation(productNameFlashSale, flashSaleName, variation, isTopping, maximumLimit, flashSaleQuantity, addStartMinute, lastAddEndMinutes);
        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("start: " + timer.get("start"));
        System.out.println("end: " + timer.get("end"));
    }

    public void createFlashSaleWithVariation(int addStartMinute, int addEndMinute, String variation, int maximumLimit, int flashSaleQuantity) {
        List<String> productNameFlashSale = getProductNameListFull();
        createFlashSaleVariationTopping(productNameFlashSale, addStartMinute, addEndMinute, variation, true, maximumLimit, flashSaleQuantity);
    }

    public void createFlashSaleWithVariationWithoutTopping(int addStartMinute, int addEndMinute, String variation, int maximumLimit, int flashSaleQuantity) {
        List<String> productNameFlashSale = getProductNameListFull();
        createFlashSaleVariationTopping(productNameFlashSale, addStartMinute, addEndMinute, variation, false, maximumLimit, flashSaleQuantity);
    }

    public void createFlashSaleBelongsSameCategorySpecialVariation(int addStartMinute, int addEndMinute, String variation, int maximumLimit, int flashSaleQuantity) {
        List<String> productNameFlashSale = getProductNameListFull();
        createFlashSaleVariationTopping(productNameFlashSale, addStartMinute, addEndMinute, variation, false, maximumLimit, flashSaleQuantity);
    }

    /**
     * @param create         create Flash sale
     * @param addStartMinute from
     * @param addEndMinute   to
     * @param statusBar      0: Ended
     *                       1: End after
     *                       2: Coming
     * @return
     */
    public Boolean checkTabFlashSaleAfterCreatedFlashSale(Boolean create, int addStartMinute, int addEndMinute, int statusBar, Boolean isSpecialBranch) {
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        if (create) {
            //create flash sale from now
            createFlashSale(addStartMinute, addEndMinute, isSpecialBranch);
        }
        helper.refreshPage();
        helper.waitForJStoLoad();
        //check flash sale component
        if (checkDisplayOfFlashSaleComponent()) {
            helper.visibleOfLocated(flashSaleComponent);
            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
            //check header
            if (helper.checkDisplayElement(flashSaleLogo)) flag.add(true);
            else {
                actualRS = actualRS + "Logo did not display\n";
                flag.add(false);
            }
            //check tab
            List<String> keyList = new ArrayList<>();
            List<WebElement> tabList = helper.getElementList(flashSaleTabActive);
            if (tabList.size() == 1) {
                keyList = getKeyListByStatus(statusBar);
                int index = 0;
                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag, keyList);
            } else if (tabList.size() == 2) {
                keyList = getKeyListByStatus(statusBar);
                int index = tabList.size() - 1;
                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag, keyList);
            } else if (tabList.size() == 3) {
                keyList = getKeyListByStatus(statusBar);
                int index = statusBar;
                checkFlashSaleActive(statusBar, tabList, index, currentLanguage, flag, keyList);
            } else {
                actualRS = actualRS + tabList.size() + " is more than 3";
                flag.add(false);
            }
            // check main session
            if (helper.checkDisplayElement(flashSaleMainSession)) flag.add(true);
            else {
                actualRS = actualRS + "Flash sale main session did not display\n";
                flag.add(false);
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = actualRS + "Flash sale component did not display\n";
            return false;
        }
    }

    /**
     * auto change status from Coming to End After
     *
     * @param addMinute
     * @return
     */
    public Boolean waitTimeToChangeStatus(int addMinute) {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        LocalDateTime nowTime = LocalDateTime.now(ZoneId.systemDefault());
        try {
            helper.waitForPresence(flashSaleComponent);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            helper.waitForPresence(flashSaleComponent);
        }
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        LocalDateTime targetTime = currentDateTime.plusMinutes(addMinute).withSecond(0).withNano(0);
        while (nowTime.isBefore(targetTime)) {
            nowTime = LocalDateTime.now(ZoneId.systemDefault());
            if (nowTime.isAfter(targetTime) || nowTime.isEqual(targetTime)) {
                System.out.println(nowTime);
                break;
            }
            helper.waitForJStoLoad();
            try {
                driver.findElement(flashSaleEndAfterSwiper).isDisplayed();
                actualRS = actualRS + "At " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " EndAfter is display\n";
                flag.add(false);
                break;
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                flag.add(true);
            }
            try {
                driver.findElement(flashSaleComingSwiper).isDisplayed();
                flag.add(true);
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                actualRS = actualRS + "At " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " Coming is hide\n";
                flag.add(false);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.info(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        helper.waitForJStoLoad();
        LocalDateTime no = LocalDateTime.now(ZoneId.systemDefault());
        try {
            helper.waitForPresence(flashSaleEndAfterSwiper);
            flag.add(true);
        } catch (Exception e) {
            Log.info(e.getMessage());
            actualRS = actualRS + "After " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " EndAfter did not display\n";
            flag.add(false);
        }
        if (!helper.checkDisplayElement(flashSaleComingSwiper)) flag.add(true);
        else {
            actualRS = actualRS + "After " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " Coming still display\n";
            flag.add(false);
        }
        Duration duration = Duration.between(nowTime, no);
        long seconds = duration.getSeconds();
        if (seconds > 3) {
            flag.add(false);
            actualRS = actualRS + "After 3s from " + nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " EndAfter display\n";
        } else flag.add(true);
        if (flag.contains(false)) return false;
        else return true;
    }

    public String waitTimeChangeStatus(int addTime) {
        actualRS = "";
        LocalDateTime nowTime = LocalDateTime.now(ZoneId.systemDefault());
        checkDisplayOfFlashSaleComponent();
        LocalDateTime targetTime = currentDateTime.plusMinutes(addTime).withSecond(0).withNano(100);
        while (nowTime.isBefore(targetTime)) {
            nowTime = LocalDateTime.now(ZoneId.systemDefault());
        }
        return nowTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private void checkFlashSaleActive(int statusBar, List<WebElement> tabList, int index, String currentLanguage, List<Boolean> flag, List<String> keyList) {
        //check label
        if (tabList.get(index).findElement(By.xpath(flashSaleTabName)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList)))
            flag.add(true);
        else {
            actualRS = actualRS + tabList.size() + " wrong text " + tabList.get(0).findElement(By.xpath(flashSaleTabName)).getText() + "\n";
            System.out.println(actualRS);
            flag.add(false);
        }
        //check time coming and ended
        if (statusBar != 1) {
            if (!checkTimeByStatus(statusBar, index, tabList)) {
                actualRS = actualRS + "Time wrong";
                flag.add(checkTimeByStatus(statusBar, index, tabList));
            }
        }
        //check end after time
        if (statusBar == 1) {
            //check display count down time
            if (helper.checkDisplayElementByElement(tabList.get(index).findElement(flashSaleCountdown))) {
                if (tabList.get(index).findElement(flashSaleCountdown).findElements(By.xpath(flashSaleCountdownXP)).size() == 6) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Count down display more than 6 numbers\n";
                    flag.add(false);
                }
            } else {
                actualRS = actualRS + "Count down flip timer did not display\n";
                flag.add(false);
            }
        }
    }

    private Boolean checkTimeByStatus(int statusBar, int index, List<WebElement> tabList) {
        String timeCheck = "";
        if (statusBar == 0) {
            timeCheck = timer.get("end"); //Ended
        } else if (statusBar == 2) {
            timeCheck = timer.get("start"); //Coming
        }
        return tabList.get(index).findElement(By.xpath(flashSaleTabTime)).getText().equals(timeCheck);
    }

    //Todo check flip countdown time
    private void isFlipCountdownRunning(List<Boolean> flag) {
        //check 5 times
        int count = 5;
        LocalDateTime currentCountdownValue;
        LocalDateTime newCountdownValue;
        while (count > 0) {
            currentCountdownValue = getPresentCountDownTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.info(e.getMessage());
                e.printStackTrace();
            }
            newCountdownValue = getPresentCountDownTime();
            Duration duration = Duration.between(newCountdownValue, currentCountdownValue);
            long seconds = duration.getSeconds();
            if (seconds == 1) {
                flag.add(true);
            } else {
                System.out.println(count);
                System.out.println(currentCountdownValue);
                System.out.println(newCountdownValue);
                System.out.println(seconds);
                flag.add(false);
                actualRS = actualRS + "count down time wrong";
            }
            count--;
        }
    }

    private LocalDateTime getPresentCountDownTime() {
        WebElement countdownContainer = driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabTime));
        List<WebElement> hourDigits = countdownContainer.findElements(By.xpath(".//div[starts-with(@class, '_SKh-V')]"));
        String countDown = "";
        for (int i = 0; i < hourDigits.size(); i++) {
            if (i < 2) {
                countDown = countDown + (hourDigits.get(i).getText());
            } else if (i < 4) {
                countDown = countDown + hourDigits.get(i).getText();
            } else {
                countDown = countDown + hourDigits.get(i).getText();
            }
        }
        int hour = Integer.parseInt(countDown.substring(0, 2));
        int minute = Integer.parseInt(countDown.substring(2, 4));
        int second = Integer.parseInt(countDown.substring(4, 6));
        LocalTime time = LocalTime.of(hour, minute, second);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now(ZoneId.systemDefault()).toLocalDate(), time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime;
    }

    public String clickAddToCartFromFlashSale(String flashSaleKey) {
        actualRS = "";
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        String homeURL = driver.getCurrentUrl();
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        Random random = new Random();
        int productIndex = 0;
        //check clickable
        helper.scrollByJS(cardList.get(productIndex));
        try {
            helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarNumberXP)));
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        int quantity = random.nextInt(flashSale.getFlashSaleProduct().get(productIndex).getMaximumLimit()) + 1;
        String productFlashSaleName = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim();
        System.out.println(productFlashSaleName);
        try {
            helper.clickBtn(cardList.get(productIndex));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.clickByJS(cardList.get(productIndex));
        }
        System.out.println("quantity " + quantity);
        commonPagesTheme1().productDetailsPage.addProductToCartWithQuantity(quantity);
        driver.navigate().to(homeURL);
        return productFlashSaleName;
    }

    /**
     * Add to cart with quantity
     *
     * @param quantity
     * @return
     */
    public String clickAddToCartFromFlashSaleWithQuantity(int productIndex, int quantity) {
        actualRS = "";
        helper.waitForJStoLoad();
        try {
            helper.waitForPresence(flashSaleComponent);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        //check clickable
        helper.scrollByJS(cardList.get(productIndex));
        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
        String productFlashSaleName = cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim();
        System.out.println(productFlashSaleName);
        try {
            helper.clickBtn(cardList.get(productIndex));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.clickByJS(cardList.get(productIndex));
        }
        commonPagesTheme1().productDetailsPage.addProductToCartWithQuantity(quantity);
        return productFlashSaleName;
    }

    public List<String> clickProductFlashSale(int productIndex) {
        actualRS = "";
        List<String> productAdded = new ArrayList<>();
        helper.waitForJStoLoad();
        helper.waitForPresence(flashSaleComponent);
        helper.visibleOfLocated(flashSaleComponent);
        try {
            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            helper.visibleOfLocated(flashSaleComponent);
            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        }
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        //check clickable
        helper.scrollByJS(cardList.get(productIndex));
        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim());
        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleOriginalPriceXP)).getText().trim());
        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim());
        try {
            helper.clickBtn(cardList.get(productIndex));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.clickByJS(cardList.get(productIndex));
        }
        return productAdded;
    }

    public List<String> clickAddToCartFlashSaleWithQuantityList(int productIndex, int quantity) {
        List<String> productAdded = clickProductFlashSale(productIndex);
        commonPagesTheme1().productDetailsPage.addProductToCartWithQuantity(quantity);
        return productAdded;
    }

    public List<String> clickAddToCartFlashSaleSelectSize(String size, int productIndex, int quantity) {
        actualRS = "";
        List<String> productAdded = new ArrayList<>();
        helper.waitForJStoLoad();
        helper.waitForPresence(flashSaleComponent);
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        //check clickable
        helper.scrollByJS(cardList.get(productIndex));
        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
        String flashsalePrice = cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim();
        try {
            helper.clickBtn(cardList.get(productIndex));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.clickByJS(cardList.get(productIndex));
        }
        List<String> product = commonPagesTheme1().productDetailsPage.addProductToCartWithSize(size, quantity);
        System.out.println(product);
        productAdded.add(product.get(0)); //getName
        productAdded.add(product.get(1)); //getPrice
        productAdded.add(flashsalePrice);
        return productAdded;
    }

    public List<String> clickAddToCartFlashSaleSelectSizeAndTopping(String size, int productIndex, int quantity) {
        actualRS = "";
        List<String> productAdded = new ArrayList<>();
        helper.waitForJStoLoad();
        try {
            helper.waitForPresence(flashSaleComponent);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        //check clickable
        helper.scrollByJS(cardList.get(productIndex));
        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
        String flashsalePrice = cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim();
        try {
            helper.clickBtn(cardList.get(productIndex));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.clickByJS(cardList.get(productIndex));
        }
        List<String> product = commonPagesTheme1().productDetailsPage.addProductToCartWithSizeAndTopping(size, quantity);
        productAdded.add(product.get(0)); //getName
        productAdded.add(product.get(1)); //getPrice
        productAdded.add(flashsalePrice);
        return productAdded;
    }

    public List<String> clickAddToCartFlashSaleSelectTopping(int productIndex, int quantity) {
        actualRS = "";
        List<String> productAdded = new ArrayList<>();
        helper.waitForJStoLoad();
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        //check clickable
        helper.scrollByJS(cardList.get(productIndex));
        helper.waitUtilElementVisible(cardList.get(productIndex).findElement(By.xpath(flashSaleQuantityBarXP)));
        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleNameXP)).getText().trim());
        productAdded.add(cardList.get(productIndex).findElement(By.xpath(flashSaleSellingPriceXP)).getText().trim());
        try {
            helper.clickBtn(cardList.get(productIndex));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.clickByJS(cardList.get(productIndex));
        }
        productAdded.add(commonPagesTheme1().productDetailsPage.addProductToCartWithTopping(quantity));
        return productAdded;
    }

    public Boolean checkCartWhenFlashSale(String productName, Boolean isClickCart) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isClickCart) {
            helper.pressPageUpAction();
            commonPagesTheme1().homePage.clickCartIcon();
        }
        helper.waitForPresence(commonPagesTheme1().homePage.cartContainer);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartContainer);
        List<WebElement> productCartList = helper.getElementList(commonPagesTheme1().homePage.productFlashSaleCartList);
        String productNameStr = "";
        if (productCartList.size() > 0) {
            try {
                for (WebElement element : productCartList) {
                    productNameStr = element.findElement(By.xpath(commonPagesTheme1().homePage.productCartNameXP)).getText().trim();
                    if (productName.equalsIgnoreCase(productNameStr)) {
                        flag.add(true);
                        actualRS = actualRS + "Flash sale still display with product: " + productName;
                    } else {
                        actualRS = actualRS + "Flash sale did not display with product: " + productName;
                        System.out.println(actualRS);
                        flag.add(false);
                    }
                }
            } catch (NoSuchElementException exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Flash sale did not display with product: " + productName;
            }
        } else {
            flag.add(false);
            actualRS = actualRS + "Flash sale did not display with product: " + productName;
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkCartPriceWhenFlashSale(FlashSaleProduct flashSaleProduct, Boolean isFlashSale, Boolean isClickCart) {
        helper.waitForJStoLoad();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isClickCart) {
            helper.pressPageUpAction();
            commonPagesTheme1().homePage.clickCartIcon();
        }
        helper.waitForPresence(commonPagesTheme1().homePage.cartContainer);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartContainer);
        if (isFlashSale) {
            helper.visibleOfLocated(commonPagesTheme1().homePage.cartContainer);
            List<WebElement> productCartFSList = helper.getElementList(commonPagesTheme1().homePage.productFlashSaleCartList);
            if (productCartFSList.size() > 0) {
                for (WebElement element : productCartFSList) {
                    if (productName.equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartFlashSaleName)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale did not display with product: " + productName + "\n";
                        flag.add(false);
                    }
                    if (flashSalePrice.equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartFlashSalePrice)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(commonPagesTheme1().homePage.productCartFlashSalePrice)).getText().trim() + " Expected: " + flashSalePrice + "\n";
                        flag.add(false);
                    }
                    //check flash sale tag
                    try {
                        element.findElement(By.xpath(commonPagesTheme1().homePage.productFlashSaleTag)).isDisplayed();
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
            helper.visibleOfLocated(commonPagesTheme1().homePage.cartContainer);
            List<WebElement> productCartNotFSList = helper.getElementList(commonPagesTheme1().homePage.productNotFSImageList);
            if (productCartNotFSList.size() > 0) {
                for (WebElement element : productCartNotFSList) {
                    try {
                        helper.waitUtilElementVisible(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartNameXP)));
                    } catch (Exception ex) {
                        Log.info(ex.getMessage());
                    }
                    if (productName.equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartNameXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale Product name is incorrect. Actual: " + element.findElement(By.xpath(commonPagesTheme1().homePage.productCartNameXP)).getText().trim() + " Expected: " + productName + "\n";
                        System.out.println(actualRS);
                        flag.add(false);
                    }
                    if (originalPrice.equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartPriceXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(commonPagesTheme1().homePage.productCartPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
                        System.out.println(actualRS);
                        flag.add(false);
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

    public Boolean checkCartQuantityWhenFlashSale(FlashSaleProduct flashSaleProduct, int cartQuantity, int maximumLimit, Boolean isClickCart) {
        helper.waitForJStoLoad();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isClickCart) {
            helper.pressPageUpAction();
            commonPagesTheme1().homePage.clickCartIcon();
            helper.waitForJStoLoad();
        }
        helper.waitForPresence(commonPagesTheme1().homePage.cartContainer);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartContainer);
        int quantity = 0, remainingQuantity = 0, cartList = 0;
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
        helper.sleep(2); //waiting for product cart api loading
        int load = 0;
        while (load < 5) {
            List<WebElement> cartElementList = helper.getElementList(commonPagesTheme1().homePage.productCartList);
            if (cartElementList.size() != cartList) {
                cartElementList = helper.getElementList(commonPagesTheme1().homePage.productCartList);
                load++;
            } else break;

        }
        List<WebElement> productCartFSList = helper.getElementList(commonPagesTheme1().homePage.productFlashSaleCartList);
        if (productCartFSList.size() == 1) {
            for (WebElement element : productCartFSList) {
                if (flashSalePrice.equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartFlashSalePrice)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(commonPagesTheme1().homePage.productCartFlashSalePrice)).getText().trim() + " Expected: " + flashSalePrice + "\n";
                    flag.add(false);
                }
                //check flash sale tag
                try {
                    element.findElement(By.xpath(commonPagesTheme1().homePage.productFlashSaleTag)).isDisplayed();
                    flag.add(true);
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    actualRS = actualRS + "Flash sale tag did not display\n";
                    flag.add(false);
                }
                //check quantity of fs product
                if (String.valueOf(quantity).equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartQuantity)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(commonPagesTheme1().homePage.productCartQuantity)).getText().trim() + " Expected: " + quantity + "\n";
                    flag.add(false);
                }
            }
        } else {
            flag.add(false);
            actualRS = actualRS + "Flash sale did not display with product: " + productName;
        }
        List<WebElement> productCartNotFSList = helper.getElementList(commonPagesTheme1().homePage.productNotFSImageList);
        if (productCartNotFSList.size() == 1) {
            for (WebElement element : productCartNotFSList) {
                try {
                    helper.waitUtilElementVisible(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartNameXP)));
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                }
                if (originalPrice.equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartPriceXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(commonPagesTheme1().homePage.productCartPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
                if (String.valueOf(remainingQuantity).equals(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartQuantity)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(commonPagesTheme1().homePage.productCartQuantity)).getText().trim() + " Expected: " + remainingQuantity + "\n";
                    flag.add(false);
                }
            }
        } else {
            flag.add(false);
            actualRS = actualRS + "Normal product did not display with product: " + productName;
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public List<String> getFlashsSaleProductInformation(String flashSaleKey, String productName) {
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<String> productFlastSale = new ArrayList<>();
        productFlastSale.add(productName);
        System.out.println(productName);
        System.out.println(flashSaleProductList);
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            if (flashSaleProduct.getName().equals(productName)) {
                productFlastSale.add(helper.formatCurrencyToThousand(flashSaleProduct.getPrice()));
                productFlastSale.add(helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice()));
            }
        }
        return productFlastSale;
    }

    public FlashSaleProduct getFlashSaleProductObject(String flashSaleKey, String productName) {
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
        for (FlashSaleProduct product : flashSaleProductList) {
            if (product.getName().equalsIgnoreCase(productName)) {
                flashSaleProduct.setName(product.getName());
                flashSaleProduct.setPrice(product.getPrice());
                flashSaleProduct.setFlashSalePrice(product.getFlashSalePrice());
                break;
            }
        }
        return flashSaleProduct;
    }

    /**
     * Get best selling product list, flash sale list then check it
     *
     * @param flashSaleKey
     * @return
     */
    public Boolean checkBestSellingAllWhenFlashSale(String flashSaleKey) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<String> flashSaleProductName = new ArrayList<>();
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            flashSaleProductName.add(flashSaleProduct.getName());
        }
        helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.productComponent));
        helper.scrollToElementByJS(driver.findElement(commonPagesTheme1().homePage.productComponent));
        helper.waitForJStoLoad();
        helper.visibleOfLocated(commonPagesTheme1().homePage.productSlider);
        List<String> productNameList = new ArrayList<>();
        List<WebElement> productCardList = helper.getElementList(commonPagesTheme1().homePage.productItemList);
        int size = productCardList.size();
        String name = "", nameChecked = "";
        Actions actions = helper.getActions();
        actions.moveToElement(driver.findElement(commonPagesTheme1().homePage.productSlider));
        helper.visibleOfLocated(commonPagesTheme1().homePage.productActiveItem);
        int count = 0;
        int productFSSize = 0;
        int checked = 0;
        while (productNameList.size() != size && checked < 2) {
            if (count > 0) {
                productCardList.clear();
                helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.productComponent));
                helper.scrollToElementByJS(driver.findElement(commonPagesTheme1().homePage.productComponent));
                helper.waitForJStoLoad();
                helper.visibleOfLocated(commonPagesTheme1().homePage.productSlider);
                actions.moveToElement(driver.findElement(commonPagesTheme1().homePage.productSlider));
                helper.visibleOfLocated(commonPagesTheme1().homePage.productActiveItem);
            }
            for (int i = 0; i < size; i++) {
                try {
                    helper.waitUtilElementVisible(productCardList.get(i));
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                }
                if (i > 3) {
                    try {
                        actions.dragAndDropBy(productCardList.get(i), -10, 0).release().perform();
                    } catch (Exception exception) {
                        Log.error(exception.getMessage());
                        helper.waitUtilElementVisible(productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productNameXpath)));
                    }
                }
                name = productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productNameXpath)).getText();
                System.out.println(name);
                nameChecked = name;
                if (flashSaleProductName.contains(nameChecked)) {
                    productFSSize++;
                    int originalPrice = 0, flashSalePrice = 0;
                    for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
                        if (flashSaleProduct.getName().equals(name)) {
                            originalPrice = flashSaleProduct.getPrice();
                            flashSalePrice = flashSaleProduct.getFlashSalePrice();
                        }
                    }
                    //Check display of discount percent
                    try {
                        productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashsaleDiscount)).isDisplayed();
                        flag.add(true);
                        String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(originalPrice, flashSalePrice));
                        if (productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashsaleDiscount)).getText().equals(discountPercentFormatted))
                            flag.add(true);
                        else {
                            actualRS = actualRS + "Percent is wrong!\nActual: " + productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashsaleDiscount)).getText() + " Expected: " + discountPercentFormatted + "\n";
                            flag.add(false);
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        actualRS = actualRS + "Discount percent did not display - " + name + "\n";
                        flag.add(false);
                    }
                    //check original price
                    try {
                        productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashSaleOriginalXP)).isDisplayed();
                        flag.add(true);
                        if (productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashSaleOriginalXP)).getText().equals(helper.formatCurrencyToThousand(originalPrice)))
                            flag.add(true);
                        else {
                            actualRS = actualRS + "original is wrong!\nActual: " + productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashSaleOriginalXP)).getText() + " Expected: " + helper.formatCurrencyToThousand(originalPrice) + "\n";
                            flag.add(false);
                        }
                    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
                        Log.info(noSuchElementException.getMessage());
                        actualRS = actualRS + "productFlashSaleOriginal did not display - " + name + "\n";
                        flag.add(false);
                    }
                    //Check flash sale border
                    try {
                        productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashSaleBorder)).isDisplayed();
                        flag.add(true);
                    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
                        Log.info(noSuchElementException.getMessage());
                        actualRS = actualRS + "Border did not display - " + name + "\n";
                        flag.add(false);
                    }
                    //Check display of flash sale tag
                    try {
                        productCardList.get(i).findElement(By.xpath(commonPagesTheme1().homePage.productFlashSaleTag)).isDisplayed();
                        flag.add(true);
                    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
                        Log.info(noSuchElementException.getMessage());
                        actualRS = actualRS + "Flash sale tag did not display - " + name + "\n";
                        flag.add(false);
                    }
                }
                productNameList.add(name);
            }
            count++;
            checked++;
        }
        if (flag.contains(false)) {
            return false;
        } else return true;
    }

    public String checkoutWhenFlashSale(String phone, String password, Boolean isEnterAddress, String address, int addressIndex, boolean checkout) {
        helper.pressPageUpAction();
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        commonPagesTheme1().homePage.clickCartIcon();
        checkOutLoginPage = commonPagesTheme1().homePage.clickCheckoutOnCart();
        checkOutLoginPage.checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, checkout);
        String checkoutURL = driver.getCurrentUrl();
        return checkoutURL;
    }

    public String checkoutThenClearCartWithoutLogin() {
        helper.pressPageUpAction();
        commonPagesTheme1().homePage.clickCartIcon();
        try {
            checkOutLoginPage = commonPagesTheme1().homePage.clickCheckoutOnCart();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            commonPagesTheme1().homePage.clickCartIcon();
            checkOutLoginPage = commonPagesTheme1().homePage.clickCheckoutOnCart();
        }
        helper.refreshPage();
        commonPagesTheme1().checkoutPage.clearAllCart();
        String checkoutURL = driver.getCurrentUrl();
        return checkoutURL;
    }

    public String checkoutThenClearCartLogin(String phone, String password, Boolean isEnterAddress, String address, int addressIndex) {
        helper.pressPageUpAction();
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        commonPagesTheme1().homePage.clickCartIcon();
        checkOutLoginPage = commonPagesTheme1().homePage.clickCheckoutOnCart();
        checkOutLoginPage.checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, false);
        commonPagesTheme1().checkoutPage.clearAllCart();
        String checkoutURL = driver.getCurrentUrl();
        return checkoutURL;
    }

    public String checkoutWhenFlashSaleWithoutLogin(Boolean isEnterAddress, String address, int addressIndex) {
        helper.pressPageUpAction();
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        commonPagesTheme1().homePage.clickCartIcon();
        checkOutLoginPage = commonPagesTheme1().homePage.clickCheckoutOnCart();
        try {
            checkOutLoginPage.checkoutWithoutLogin(currentLanguage, isEnterAddress, address, addressIndex);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            commonPagesTheme1().homePage.clickCartIcon();
            checkOutLoginPage = commonPagesTheme1().homePage.clickCheckoutOnCart();
            checkOutLoginPage.checkoutWithoutLogin(currentLanguage, isEnterAddress, address, addressIndex);
        }
        String checkoutURL = driver.getCurrentUrl();
        return checkoutURL;
    }

    /**
     * @param flashSaleKey
     * @param isEndAfter
     * @param statusTab    0: Ended
     *                     1: End after
     *                     2: Coming
     * @return
     */
    public Boolean checkDisplayOfMainSession(String flashSaleKey, Boolean isEndAfter, int statusTab) {
        String homeURL = helper.getCurrentURL();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        actualRS = "";
        String id = "";
        String href = "";
        List<Boolean> flag = new ArrayList<>();
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        if (statusTab == 0) keyList.add("ended");
        else if (statusTab == 1) keyList.add("remaining");
        else keyList.add("coming");
        helper.refreshPage();
        helper.waitForJStoLoad();
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        //check flash sale component
        if (helper.checkDisplayElement(flashSaleComponent)) {
            helper.visibleOfLocated(flashSaleComponent);
            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
            List<WebElement> cardList = helper.getElementList(flashSaleItems);
            List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
            if (flashSaleProductList.size() == cardList.size()) {
                for (int i = 0; i < flashSaleProductList.size(); i++) {
                    FlashSaleProduct flashSaleProduct = flashSaleProductList.get(i);
                    href = cardList.get(i).getAttribute("href");
                    id = href.substring(href.lastIndexOf("/") + 1);
                    helper.scrollByJS(cardList.get(i));
                    helper.waitUtilElementVisible(cardList.get(i));
                    //check href of <a>
                    if (id.contains(flashSaleProduct.getProductId())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Href - id is wrong!\nActual: " + id + " Expected: " + flashSaleProduct.getProductId() + "\n";
                        flag.add(false);
                    }
                    //Check image displays
                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleImage))))
                        flag.add(true);
                    else {
                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display the image\n";
                        flag.add(false);
                    }
                    //Check image thumbnail
                    if (cardList.get(i).findElement(By.xpath(flashSaleImage)).getAttribute("src").equals(flashSaleProduct.getThumbnail()))
                        flag.add(true);
                    else {
                        actualRS = actualRS + "Thumbnail is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleImage)).getAttribute("src") + " Expected: " + flashSaleProduct.getThumbnail() + "\n";
                        flag.add(false);
                    }
                    //Check name
                    if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().equals(flashSaleProduct.getName()))
                        flag.add(true);
                    else {
                        actualRS = actualRS + "Name is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText() + " Expected: " + flashSaleProduct.getName() + "\n";
                        flag.add(false);
                    }
                    //check flash sale price
                    String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
                    if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getText().equals(flashSalePrice))
                        flag.add(true);
                    else {
                        actualRS = actualRS + "Flash sale price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getText() + " Expected: " + flashSalePrice + "\n";
                        flag.add(false);
                    }
                    //check original price
                    String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
                    if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getText().equals(originalPrice))
                        flag.add(true);
                    else {
                        actualRS = actualRS + "Original price is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getText() + " Expected: " + originalPrice + "\n";
                        flag.add(false);
                    }
                    //check display of status bar
                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP))))
                        flag.add(true);
                    else {
                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display quantity bar\n";
                        flag.add(false);
                    }
                    //check quantity bar text
                    if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList))) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Card in position " + (i + 1) + " wrong text\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList) + "\n";
                        System.out.println(actualRS);
                        flag.add(false);
                    }
                    if (isEndAfter) {
                        if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSaleFireIconXP))))
                            flag.add(true);
                        else {
                            actualRS = actualRS + "Card in position " + (i + 1) + " did not display fire icon on quantity bar\n";
                            flag.add(false);
                        }
                        if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText().equals(String.valueOf(flashSaleProduct.getFlashSaleQuantity())))
                            flag.add(true);
                        else {
                            actualRS = actualRS + "Card in position " + (i + 1) + " wrong quantity\nActual: \"" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText() + "\" Expected: \"" + flashSaleProduct.getFlashSaleQuantity() + "\"\n";
                            System.out.println(actualRS);
                            flag.add(false);
                        }
                    }
                    //check display of percent discount tag
                    if (helper.checkDisplayElementByElement(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)))) {
                        flag.add(true);
                        //check value of percent discount tag
                        int newPrice = flashSaleProduct.getFlashSalePrice();
                        int oldPrice = helper.convertToInteger(cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getText());
                        String discountPercentFormatted = helper.formatPercentDiscount(helper.calculateDiscountPercent(oldPrice, newPrice));
                        if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getText().equals(discountPercentFormatted))
                            flag.add(true);
                        else {
                            actualRS = actualRS + "Percent is wrong!\nActual: " + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getText() + " Expected: " + discountPercentFormatted + "\n";
                            flag.add(false);
                        }
                    } else {
                        actualRS = actualRS + "Card in position " + (i + 1) + " did not display or broken UI: the discount percent label\n";
                        flag.add(false);
                    }
                    //check clickable
                    Boolean clicked = false;
                    helper.scrollByJS(cardList.get(i));
                    try {
                        helper.clickBtn(cardList.get(i));
                        clicked = true;
                    } catch (Exception e) {
                        Log.info(e.getMessage());
                        helper.clickByJS(cardList.get(i));
                        clicked = true;
                    }
                    helper.waitForJStoLoad();
                    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
                    if (clicked == true) {
                        if (helper.waitForURLContains(href)) flag.add(true);
                        else {
                            actualRS = actualRS + "URL is wrong!\nActual: " + driver.getCurrentUrl() + " Expected: " + href + "\n";
                            flag.add(false);
                        }
                        driver.navigate().to(homeURL);
                        //get cardList element list again
                        try {
                            helper.waitForPresence(flashSaleComponent);
                            helper.visibleOfLocated(flashSaleComponent);
                        } catch (Exception exception) {
                            Log.info(exception.getMessage());
                        }
                        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
                        cardList = helper.getElementList(flashSaleItems);
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Card in position " + (i + 1) + " can not click\n";
                        flag.add(false);
                    }
                }
                if (flag.contains(false)) return false;
                else return true;
            } else {
                actualRS = actualRS + "Showing wrong flash sale quantity\nActual: " + cardList.size() + " Expected: " + flashSaleProductList.size() + "\n";
                return false;
            }
        } else {
            actualRS = actualRS + "Flash sale component did not display\n";
            return false;
        }
    }

    public Boolean checkCSSOfMainSession() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        List<WebElement> cardList = new ArrayList<>();
        helper.refreshPage();
        helper.waitForJStoLoad();
        //check flash sale component
        if (helper.checkDisplayElement(flashSaleComponent)) {
            cardList = helper.getElementList(flashSaleItems);
            for (int i = 0; i < cardList.size(); i++) {
                helper.scrollByJS(cardList.get(i));
                try {
                    helper.waitUtilElementVisible(cardList.get(i));
                } catch (Exception e) {
                    Log.info(e.getMessage());
                    helper.refreshPage();
                    helper.waitForJStoLoad();
                    helper.visibleOfLocated(flashSaleComponent);
                    helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
                    cardList = helper.getElementList(flashSaleItems);
                    helper.scrollByJS(cardList.get(i));
                }
                //Check image size
                if (cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("height").equals(flashSaleDataTest.PRODUCT_IMAGE_HEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Image in position " + (i + 1) + " has wrong height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_IMAGE_HEIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("width").equals(flashSaleDataTest.PRODUCT_IMAGE_WIDTH))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Image in position " + (i + 1) + " has wrong width.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleImage)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_IMAGE_WIDTH + "\n";
                    flag.add(false);
                }
                //Check name
                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_MARGIN_TOP))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_MARGIN_TOP + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_NAME_FONT_SIZE))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_NAME_FONT_SIZE + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_NAME_FONT_WEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_NAME_FONT_WEIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_LINE_HEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Name in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_LINE_HEIGHT + "\n";
                    flag.add(false);
                }
                //check flash sale price
                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_MARGIN_TOP))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_MARGIN_TOP + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("align-items").equals(flashSaleDataTest.PRODUCT_PRICE_ALIGN_ITEMS))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong align-items.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePriceXP)).getCssValue("align-items") + " Expected: " + flashSaleDataTest.PRODUCT_PRICE_ALIGN_ITEMS + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_SIZE))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_SIZE + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_WEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_SELLING_PRICE_FONT_WEIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_LINE_HEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Flash sale price in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleSellingPriceXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_LINE_HEIGHT + "\n";
                    flag.add(false);
                }
                //check original price
                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_SIZE))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_SIZE + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_WEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_FONT_WEIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-decoration-line").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_DECORATION))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong text-decoration-line.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-decoration-line") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_DECORATION + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("padding-left").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_PADDING_LEFT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong padding-left.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("padding-left") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_PADDING_LEFT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("line-height").equals(flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_LINE_HEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Original price in position " + (i + 1) + " has wrong line-height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleOriginalPriceXP)).getCssValue("line-height") + " Expected: " + flashSaleDataTest.PRODUCT_ORIGINAL_PRICE_LINE_HEIGHT + "\n";
                    flag.add(false);
                }
                //check display of status bar
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-top").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_TOP))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_TOP + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-right").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_RIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-right.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-right") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_RIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-left").equals(flashSaleDataTest.PRODUCT_BAR_MARGIN_LEFT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong margin-left.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("margin-left") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_MARGIN_LEFT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height").equals(flashSaleDataTest.PRODUCT_BAR_HEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong height.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_HEIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("width").equals(flashSaleDataTest.PRODUCT_BAR_WIDTH))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong width.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("height") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_WIDTH + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("border-radius").equals(flashSaleDataTest.PRODUCT_BAR_BORDER_RADIUS))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar in position " + (i + 1) + " has wrong border-radius.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarXP)).getCssValue("border-radius") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_BORDER_RADIUS + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_BAR_FONT_SIZE))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_FONT_SIZE + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_BAR_FONT_WEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_BAR_FONT_WEIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("text-align").equals(flashSaleDataTest.PRODUCT_ALIGN_CENTER))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Bar text in position " + (i + 1) + " has wrong text-align.\nActual:" + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getCssValue("text-align") + " Expected: " + flashSaleDataTest.PRODUCT_ALIGN_CENTER + "\n";
                    flag.add(false);
                }
                //check display of percent discount tag
                String color = Color.fromString(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("background-color")).asHex();
                if (color.equals(flashSaleDataTest.PRODUCT_TAG_BG_COLOR)) flag.add(true);
                else {
                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong font-size.\nActual:" + Color.fromString(cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("background-color")).asHex().toString() + " Expected: " + flashSaleDataTest.PRODUCT_TAG_BG_COLOR + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("padding").equals(flashSaleDataTest.PRODUCT_TAG_PADDING))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong padding.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("margin-top") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_PADDING + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("right").equals(flashSaleDataTest.PRODUCT_TAG_RIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong right.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("right") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_RIGHT + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("top").equals(flashSaleDataTest.PRODUCT_TAG_TOP))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong top.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("top") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_TOP + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("border-radius").equals(flashSaleDataTest.PRODUCT_TAG_BORDER_RADIUS))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong border-radius.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("border-radius") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_BORDER_RADIUS + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-size").equals(flashSaleDataTest.PRODUCT_TAG_FONT_SIZE))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong font-size.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-size") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_FONT_SIZE + "\n";
                    flag.add(false);
                }
                if (cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-weight").equals(flashSaleDataTest.PRODUCT_TAG_FONT_WEIGHT))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Percent discount tag in position " + (i + 1) + " has wrong font-weight.\nActual:" + cardList.get(i).findElement(By.xpath(flashSalePercentLabelXP)).getCssValue("font-weight") + " Expected: " + flashSaleDataTest.PRODUCT_TAG_FONT_WEIGHT + "\n";
                    flag.add(false);
                }
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = actualRS + "Flash sale component did not display\n";
            return false;
        }
    }

    public Boolean checkDisplayOfMainSessionAfterChangeBranch(String flashSaleKey) {
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleKey);
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.waitForJStoLoad();
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        if (flashSaleProductList.size() == cardList.size()) {
            actualRS = actualRS + "List size - Actual: " + cardList.size() + " Expected: " + flashSaleProductList.size() + "\n";
            for (int i = 0; i < cardList.size(); i++) {
                helper.scrollByJS(cardList.get(i));
                helper.waitUtilElementVisible(cardList.get(i));
                //Check name
                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().contains(flashSaleDataTest.PRODUCT_FLASH_SALE_4)) {
                    flag.add(false);
                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " did not display after changed branch to " + getBranchNameMissingProductByEnv() + "\n";
                } else {
                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " did not display after changed branch to " + getBranchNameMissingProductByEnv() + "\n";
                    flag.add(true);
                }
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            for (int i = 0; i < cardList.size(); i++) {
                helper.scrollByJS(cardList.get(i));
                helper.waitUtilElementVisible(cardList.get(i));
                //Check name
                if (cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText().contains(flashSaleDataTest.PRODUCT_FLASH_SALE_4)) {
                    flag.add(false);
                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " dont have belong to " + getBranchNameMissingProductByEnv() + "\n";
                } else {
                    System.out.println(cardList.get(i).findElement(By.xpath(flashSaleNameXP)).getText());
                    actualRS = actualRS + flashSaleDataTest.PRODUCT_FLASH_SALE_4 + " did not display after changed branch to " + getBranchNameMissingProductByEnv() + "\n";
                    flag.add(true);
                }
            }
            if (flag.contains(false)) return false;
            else return true;
        }
    }

    private List<String> getKeyListByStatus(int statusTab) {
        List<String> keyList = new ArrayList<>();
        keyList.clear();
        keyList.add("flashSale");
        if (statusTab == 0) keyList.add("ended");
        else if (statusTab == 1) keyList.add("endAfter");
        else keyList.add("coming");
        return keyList;
    }

    public void deleteFlashSale(String flashSaleKey) {
        apiAminService.deleteFlashSaleById(flashSaleKey);
    }

    public void stopFlashSale(String flashSaleKey) {
        apiAminService.stopFlashSaleById(flashSaleKey);
    }

    public void updateTimeFlashSale(String nameFlashSale, int addStartMinute, int addEndMinute) {
        currentDateTime = apiAminService.editTimeOfFlashSale(nameFlashSale, addStartMinute, addEndMinute);
        timer.put("start", currentDateTime.plusMinutes(addStartMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        timer.put("end", currentDateTime.plusMinutes(addStartMinute + addEndMinute).withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println(timer.get("start"));
        System.out.println(timer.get("end"));
    }

    public Boolean checkFlashSaleLanguage(Boolean hasEnded, Boolean hasEndAfter, Boolean hasComing) {
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        actualRS = "";
        System.out.println(currentLanguage);
        int statusTab = 0;
        List<String> keyListStatus = null;
        keyListStatus = new ArrayList<>();
        keyListStatus.add("flashSale");
        if (hasEnded) {
            keyListStatus.add("ended");
            statusTab = 0;
        }
        if (hasComing) {
            keyListStatus.remove(1);
            keyListStatus.add("coming");
            statusTab = 2;
        }
        if (hasEndAfter) {
            keyListStatus.remove(1);
            statusTab = 1;
            keyListStatus.add("remaining");
        }
        List<String> keyListTab = getKeyListByStatus(statusTab);
        List<Boolean> flag = new ArrayList<>();
        if (helper.checkDisplayElement(flashSaleComponent)) {
            helper.visibleOfLocated(flashSaleComponent);
            helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
            clickFlashSaleTab(statusTab);
            helper.waitForJStoLoad();
            if (driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListTab))) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Tab wrong text\nActual: " + driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleTabName)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListTab) + "\n";
                System.out.println(actualRS);
                flag.add(false);
            }
            List<WebElement> cardList = helper.getElementList(flashSaleItems);
            for (int i = 0; i < cardList.size(); i++) {
                try {
                    helper.scrollByJS(cardList.get(i));
                    helper.waitUtilElementVisible(cardList.get(i));
                } catch (Exception e) {
                    Log.info(e.getMessage());
                    helper.refreshPage();
                    helper.waitForJStoLoad();
                    helper.visibleOfLocated(flashSaleComponent);
                    helper.scrollToElementByJS(driver.findElement(flashSaleMainSession));
                    cardList = helper.getElementList(flashSaleItems);
                    helper.scrollByJS(cardList.get(i));
                }
                if (cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText().equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListStatus))) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Card in position " + (i + 1) + " wrong text\nActual: " + cardList.get(i).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyListStatus) + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkLanguageOfFlashSale(Boolean hasEnded, Boolean hasEndAfter, Boolean hasComing) {
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
        flag.add(checkFlashSaleLanguage(hasEnded, hasEndAfter, hasComing));
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
            flag.add(checkFlashSaleLanguage(hasEnded, hasEndAfter, hasComing));
            helper.refreshPage();
            helper.pressPageUpAction();
            helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
            helper.waitForPresence(commonPagesTheme1().homePage.dialogContent);
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

    public Boolean checkClickableOfFlashSaleTab() {
        helper.waitForJStoLoad();
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> tabList = helper.getElementList(flashSaleTabList);
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (tabList.size() == 3) {
            flag.add(true);
            for (int i = 0; i < tabList.size(); i++) {
                try {
                    tabList.get(i).click();
                } catch (ElementClickInterceptedException elementClickInterceptedException) {
                    Log.info(elementClickInterceptedException.getMessage());
                    helper.clickByJS(tabList.get(i));
                }
                helper.waitForJStoLoad();
                if (i == 0) {
                    flag.add(helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndedSwiperId))));
                } else if (i == 1) {
                    flag.add(helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleEndAfterSwiperId))));
                } else {
                    flag.add(helper.checkDisplayElementByElement(driver.findElement(flashSaleTabActive).findElement(By.xpath(flashSaleComingSwiperId))));
                }
            }
        } else {
            actualRS = actualRS + "tabList displayed wrong. Actual: " + tabList.size();
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkRemainingQuantityFlashSale(int quantity, int limit, int cart) {
        int remainingQuantity = 0;
        if (quantity > limit) {
            if (cart < limit) remainingQuantity = quantity - cart;
            else remainingQuantity = quantity - limit;
        } else if (quantity < limit) {
            if (quantity > cart) remainingQuantity = quantity - cart;
            else remainingQuantity = 0;
        } else {
            if (limit > cart) remainingQuantity = quantity - cart;
            else remainingQuantity = 0;
        }
        System.out.println(quantity + " " + limit + " " + cart + " " + remainingQuantity);
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("soldOut");
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.waitForJStoLoad();
        try {
            helper.waitForPresence(flashSaleComponent);
            helper.visibleOfLocated(flashSaleComponent);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        int index = 0;
        helper.scrollByJS(cardList.get(index));
        helper.waitUtilElementVisible(cardList.get(index));
        //Check quantity API
        int actualQuantity = 0;
        String soldOutText = "";
        if (remainingQuantity > 0) {
            try {
                WebElement element = cardList.get(index).findElement(By.xpath(flashSaleQuantityBarNumberXP));
                actualQuantity = Integer.parseInt(element.getText());
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                helper.refreshPage();
                helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
                cardList = helper.getElementList(flashSaleItems);
                helper.scrollByJS(cardList.get(index));
                helper.waitUtilElementVisible(cardList.get(index));
                actualQuantity = Integer.parseInt(cardList.get(index).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText());
            }
            if (actualQuantity == remainingQuantity) flag.add(true);
            else {
                actualRS = actualRS + "Remaining quantity displayed wrong. Actual: " + actualQuantity + " Expected: " + remainingQuantity + "\n";
                flag.add(false);
            }
            try {
                cardList.get(index).findElement(By.xpath(flashSaleFireIconXP)).isDisplayed();
                flag.add(true);
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                actualRS = actualRS + "Fire icon did not display";
                flag.add(false);
            }
        } else {
            soldOutText = cardList.get(index).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText();
            String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
            if (soldOutText.equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList)))
                flag.add(true);
            else {
                actualRS = actualRS + "Sold out displayed wrong. Actual: " + soldOutText + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList);
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkRemainingQuantityFlashSaleIndex(int productIndex, int quantity, int limit, int cart) {
        int remainingQuantity = 0;
        if (quantity > limit) {
            if (cart < limit) remainingQuantity = quantity - cart;
            else remainingQuantity = quantity - limit;
        } else if (quantity < limit) {
            if (quantity > cart) remainingQuantity = quantity - cart;
            else remainingQuantity = 0;
        } else {
            if (limit > cart) remainingQuantity = quantity - cart;
            else remainingQuantity = 0;
        }
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("soldOut");
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.waitForJStoLoad();
        helper.waitForPresence(flashSaleComponent);
        helper.visibleOfLocated(flashSaleComponent);
        helper.scrollToElementByJS(driver.findElement(flashSaleComponent));
        List<WebElement> cardList = helper.getElementList(flashSaleItems);
        int index = productIndex;
        helper.scrollByJS(cardList.get(index));
        helper.waitUtilElementVisible(cardList.get(index));
        //Check quantity API
        int actualQuantity = 0;
        String soldOutText = "";
        if (remainingQuantity > 0) {
            try {
                actualQuantity = Integer.parseInt(cardList.get(index).findElement(By.xpath(flashSaleQuantityBarNumberXP)).getText());
                if (actualQuantity == remainingQuantity) flag.add(true);
                else {
                    actualRS = actualRS + "Remaining quantity displayed wrong. Actual: " + actualQuantity + " Expected: " + remainingQuantity + "\n";
                    flag.add(false);
                }
                try {
                    cardList.get(index).findElement(By.xpath(flashSaleFireIconXP)).isDisplayed();
                    flag.add(true);
                } catch (NoSuchElementException noSuchElementException) {
                    Log.info(noSuchElementException.getMessage());
                    actualRS = actualRS + "Fire icon did not display";
                    flag.add(false);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                System.out.println(exception.getMessage());
                flag.add(false);
            }
        } else {
            soldOutText = cardList.get(index).findElement(By.xpath(flashSaleQuantityBarTextXP)).getText();
            String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
            if (soldOutText.equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList)))
                flag.add(true);
            else {
                actualRS = actualRS + "Sold out displayed wrong. Actual: " + soldOutText + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList);
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    private MyOrderPage gotoOrderDetailByURL(String url) {
        System.out.println(url);
        driver.navigate().to(url);
        return new MyOrderPage(driver);
    }

    public String checkRemainingFlashSaleAfterReorder(String url, int cartQuantity, Boolean isEnterAddress, String address, int addressIndex) {
        Map<String, String> flashSaleProductMap = new HashMap<>();
        myOrderPage = gotoOrderDetailByURL(url);
        String currentLanguage = commonPagesTheme1().homePage.getCurrentLanguage();
        myOrderPage.clickReOrderBtn(currentLanguage);
        //get Flash sale product list from cart
        commonPagesTheme1().homePage.clickCartIcon();
        helper.waitForPresence(commonPagesTheme1().homePage.cartContainer);
        helper.visibleOfLocated(commonPagesTheme1().homePage.cartContainer);
        List<WebElement> flashSaleProductCartList = helper.getElementList(commonPagesTheme1().homePage.productFlashSaleCartList);
        if (flashSaleProductCartList.size() > 0) {
            for (WebElement element : flashSaleProductCartList) {
                flashSaleProductMap.put(element.findElement(By.xpath(commonPagesTheme1().homePage.productCartFlashSaleName)).getText(), String.valueOf(cartQuantity));
            }
        }
        helper.refreshPage();
        //check flash sale product on checkout
        commonPagesTheme1().checkoutPage.checkQuantityFlashSale(flashSaleProductMap);
        commonPagesTheme1().checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
        return commonPagesTheme1().checkoutPage.viewOrderAfterCheckout();
    }

    public String formatProductNameSize(String productName, String sizeName) {
        return productName + " (" + sizeName + ")";
    }

    public String removeSizeFromProductNameSize(String productName) {
        return productName.replaceAll("\\s*\\(.*\\)", "");
    }

    public FlashSaleProduct getProductByName(Boolean isFlashsale, String typeFlashSaleObjectJson, List<String> productInformation) {
        return jsonAPIAdminReader.getFlashSaleProductFromJson(isFlashsale, typeFlashSaleObjectJson, productInformation, size);
    }

    public String getBranchNameByEnv() {
        if (jsonReader.getEnviroment.equals("stag")) {
            return homeDataTest.BRANCH_NAME_STAG;
        } else return homeDataTest.BRANCH_NAME;
    }

    public String getBranchNameMissingProductByEnv() {
        if (jsonReader.getEnviroment.equals("stag")) {
            return homeDataTest.BRANCH_NAME_MISSING_PRODUCT_STAG;
        } else return homeDataTest.BRANCH_NAME_MISSING_PRODUCT;
    }
}
