package com.fnb.utils.helpers;

import com.fnb.utils.api.storeweb.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.api.storeweb.pos.helpers.JsonAPIPosReader;
import com.github.javafaker.Faker;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Helper {
    private WebDriver driver;
    private WebDriverWait wait;
    public Faker faker = new Faker();
    public String lastName = faker.name().lastName();
    String randum = faker.number().digits(6);
    public String expectedLanguage = "";
    public String phoneNumber = "0981" + randum;
    public String actualRS = "";
    private AndroidDriver androidDriver;
    private Actions actions;
    private JsonReader jsonReader;
    private JsonAPIPosReader jsonAPIPosReader;
    private JsonAPIAdminReader jsonAPIAdminReader;
    private final AtomicLong counter = new AtomicLong(0);

    public Helper(AndroidDriver driver, WebDriverWait wait) {
        this.androidDriver = driver;
        this.wait = wait;
    }

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public Helper(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }

    public String phoneNumberData() {
        String randumNumber = faker.number().digits(6);
        String phone = "0981" + randumNumber;
        return phone;
    }

    public String getLoginSuccessToast() {
        By toast = By.xpath("//android.view.ViewGroup[@resource-id=\"loginSuccessToastMessage\"]");
        String toastMess = wait.until(ExpectedConditions.presenceOfElementLocated(toast)).getText();
        System.out.println(toastMess);
        return toastMess;
    }

    public void waitToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToElementClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitToElementLocatedClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void waitElementLocated(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click();
    }

    public void waitElementLocated1(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitToElementSendKey(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    public String waitToElementGetText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public Boolean checkElementDisplay(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            Log.fatal("Element is not Display");
            return false;
        }
    }


    public void waitToElementClear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
    }

    public void navigateBack(int index) {
        for (int i = 0; i < index; i++) {
            driver.navigate().back();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkDialogExits(WebElement element) {
        boolean checkDialog = checkElementDisplay(element);
        if (checkDialog) {
            waitToElementClick(driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"acceptBtn\"]")));
        }
    }

    public void splashScreen() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createFile(String pathName) {
        try {
            File file = new File(pathName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            Log.info(e.getMessage());
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void waitForPageLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            Log.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                Log.info(error.getMessage());
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load.");
            }
        }
    }

    public void navigateToUrl(String URL) {
        driver.navigate().to(URL);
        waitForPageLoaded();
        waitForUrl(URL);
        Log.info("Navigate to " + URL + "");
    }

    public void waitForUrl(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
        Log.info("Wait for url: " + url + "");
    }

    public void writeFile(String pathName, String str) {
        try {
            FileWriter myWriter = new FileWriter(pathName);
            myWriter.write(str);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    //wait
    public void waitUtilElementVisible(WebElement e) {
        try {
            wait.until(ExpectedConditions.visibilityOf(e));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            System.out.println("Timeout exception: Element is not visible.");
        }
    }

    public Boolean visibleOfLocated(By xpath) {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(xpath)));
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public void waitForTextToBe(WebElement e, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(e, text));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
        }
    }

    public Boolean waitForTextToBePresent(By locator, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            return checkDisplayElement(locator);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            return false;
        }
    }

    public Boolean waitInvisibleByLocated(By xpath) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            return true;
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            return false;
        }
    }

    public void presenceOfAllElementsLocatedBy(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void visibilityOfAllElementsLocatedBy(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void visibilityOfAllElements(List<WebElement> elementList) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
    }

    public Boolean waitAttributeContains(WebElement element, String attribute, String value) {
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
            return true;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    public Boolean waitForURLContains(String str) {
        try {
            wait.until(ExpectedConditions.urlContains(str));
            return true;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    // ngan
    public void waitForURL(String str) {
        wait.until(ExpectedConditions.urlContains(str));
    }


    public Boolean waitForClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            return false;
        }
    }

    public Boolean waitForJStoLoad() {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    Log.info(e.getMessage());
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public Boolean waitForPresence(By xpath) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
            return true;
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            return false;
        }
    }

    public void waitForPresenceAllements(By xpath) {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
        }
    }

    public void waitForVisibleAllElements(By xpath) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
        }
    }

    //Handle popup, alert, windows
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(String str) {
        driver.switchTo().alert().sendKeys(str);
    }

    public void sendKeysAlert() {
        driver.switchTo().alert().accept();
    }

    public String getTextOfAlert() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }

    public void waitNumberHandleToBe(int seconds, int number) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.numberOfWindowsToBe(number));
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void switchToWindowHandle(String window) {
        driver.switchTo().window(window);
    }

    public void clearAllCookie() {
        driver.manage().deleteAllCookies();
    }

    public void backPrevPage() {
        driver.navigate().back();
    }

    public void openNewTab(String url) {
        String mainWindow = getCurrentWindow();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to(url);
    }

    public void switchBackCurrentTab(String currentTab) {
        driver.close();
        driver.switchTo().window(currentTab);
    }

    public void closeAllOpenTabExceptMainTab(String currentTab) {
        // get all new open tab
        Set<String> openTabs = driver.getWindowHandles();
        for (String tab : openTabs) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
                driver.close();
            }
        }
        driver.switchTo().window(currentTab);
    }

    //String
    public String subStringFollowIndex(String str, int beginIndex, int endIndex) {
        return str.substring(beginIndex, endIndex);
    }

    public String replaceString(String str, CharSequence newC, CharSequence oldC) {
        return str.replace(oldC, newC);
    }

    public String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public List<String> stringSplit(String string, String regex) {
        String[] arrStr = string.split(regex);
        return Arrays.asList(arrStr);
    }

    /**
     * If 2 lists have common data -> return true and vice versa
     *
     * @param list1
     * @param list2
     * @return
     */
    public Boolean checkCommonDataListStringStream(List<String> list1, List<String> list2) {
        return list1.stream().anyMatch(list2::contains);
    }

    //action
    public Actions getActions() {
        return actions;
    }

    public void ctrlADeleteAction(WebElement e) {
        actions.moveToElement(e).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).keyDown(Keys.DELETE).perform();
    }

    public void pressEnterAction(WebElement e) {
        actions.moveToElement(e).click().keyDown(Keys.ENTER).perform();
    }

    public void pressPageUpAction() {
        try {
            actions.sendKeys(Keys.PAGE_UP).build().perform();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actions.sendKeys(Keys.PAGE_UP).build().perform();
        }
    }

    public void pressPageDownAction() {
        actions.moveToElement(driver.findElement(By.tagName("body"))).sendKeys(Keys.END).perform();
    }

    public void pressEndAction() {
        actions.sendKeys(Keys.END).build().perform();
    }

    public void pressHomeAction() {
        actions.sendKeys(Keys.HOME).build().perform();
    }

    public void refreshPageByActions() {
        actions.keyDown(Keys.SHIFT).sendKeys(Keys.F5).keyUp(Keys.SHIFT).perform();
    }

    public void pressESC() {
        actions.sendKeys(Keys.ESCAPE).build().perform();
    }

    public void actionDragAndDropByOffset(WebElement elementStart, WebElement elementEnd) {
        Rectangle start = elementStart.getRect();
        Rectangle finish = elementEnd.getRect();
        new Actions(driver).dragAndDropBy(elementStart, finish.getX() - start.getX(), finish.getY() - start.getY()).perform();
    }

    public void actionScrollSwiper(WebElement slider, int numberOfImages) {
        Actions actions = new Actions(driver);
        actions.moveToElement(slider);
        for (int i = 0; i < numberOfImages; i++) {
            actions.clickAndHold().moveByOffset(44, 0).pause(500).release().perform();
        }
    }

    public void addingInnerJs(WebElement e, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + text + "'", e);
    }

    public void clickByJS(WebElement e) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", e);
    }

    public void scrollByJS(WebElement e) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", e);
    }

    public void actionScrollToElement(WebElement element) {
        waitUtilElementVisible(element);
        actions.moveToElement(element).perform();
    }

    public void actionScrollAndClickToElement(WebElement element) {
        waitUtilElementVisible(element);
        actions.moveToElement(element).click().build().perform();
    }

    public void actionHover(WebElement welementHover) {
        actions.moveToElement(welementHover).perform();
    }

    //common
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getAttribute(By xpath, String attribute) {
        waitUtilElementVisible(driver.findElement(xpath));
        return driver.findElement(xpath).getAttribute(attribute);
    }

    public String getCSSValue(By xpath, String value) {
        waitUtilElementVisible(driver.findElement(xpath));
        return driver.findElement(xpath).getCssValue(value);
    }

    public Boolean checkDisplayElement(By xpath) {
        try {
            waitForPresence(xpath);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(xpath)));
            return true;
        } catch (Exception e) {
            Log.error(e.getMessage());
            return false;
        }
    }

    public Boolean checkDisplayElementByElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            Log.error(e.getMessage());
            return false;
        }
    }

    public void enterData(WebElement e, String data) {
        waitUtilElementVisible(e);
        e.sendKeys(data);
    }

    public void clickBtn(WebElement e) {
        waitUtilElementVisible(e);
        e.click();
    }

    public void clickOutsideElementByActions(WebElement element) {
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        actions.moveByOffset(x, y).perform();
    }

    public WebElement getElement(By xpath) {
        return driver.findElement(xpath);
    }

    public List<WebElement> getElementList(By testObject) {
        List<WebElement> e_list = driver.findElements(testObject);
        return e_list;
    }

    public String selectOptionDropdown(By testObject, int index) {
        List<WebElement> e_list = getElementList(testObject);
        WebElement e = e_list.get(index);
        waitUtilElementVisible(e);
        String text = e.getText();
        e.click();
        return text;
    }

    public void selectOptionByText(By testList, String text) {
        String xpath = "/*[text(),\"" + text + "\"";
        WebElement element = driver.findElement(testList).findElement(By.xpath(xpath));
        element.click();
    }

    public void scrollToElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public boolean checkURL(String url) {
        if (!driver.getCurrentUrl().contains(url)) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkText(WebElement e, String expected) {
        if (e.getText().trim().equals(expected)) {
            return true;
        } else {
            actualRS = "Actual: " + e.getText() + " Expected: " + expected;
            return false;
        }
    }

    public Boolean checkContainsText(WebElement e, String expected) {
        if (e.getText().toLowerCase().trim().contains(expected.toLowerCase())) {
            return true;
        } else {
            actualRS = "Actual: " + e.getText() + " Expected: " + expected;
            return false;
        }
    }

    public Boolean checkPlaceHolder(WebElement e, String expected) {
        return e.getAttribute("placeholder").equals(expected);
    }

    // Header menu
    public String getCurrentLanguageHelper(By xpath) {
        pressPageUpAction();
        WebElement element;
        visibleOfLocated(xpath);
        String currentLanguage = "";
        try {
            checkDisplayElement(xpath);
            element = driver.findElement(xpath);
            currentLanguage = element.getText().trim();
        } catch (Exception e) {
            Log.info(e.getMessage());
            refreshPage();
            visibleOfLocated(xpath);
            checkDisplayElement(xpath);
            element = driver.findElement(xpath);
            currentLanguage = element.getText().trim();
        }
        return currentLanguage;
    }

    /**
     * support for changeMethodLanguage
     *
     * @param option
     */
    public void selectLanguage(WebElement option) {
        try {
            waitUtilElementVisible(option);
            option.click();
        } catch (Exception e) {
            Log.info(e.getMessage());
            option.click();
        }
    }

    /**
     * change another language base on currentLanguage variable
     *
     * @param languageSwitch   //language dropdown xpath
     * @param languageTxt      //language value xpath
     * @param vietnameseOption //Vietnamese xpath
     * @param englishOption    //English xpath
     */
    public void changeMethodLanguage(By languageSwitch, By languageTxt, By vietnameseOption, By englishOption) {
        pressPageUpAction();
        waitUtilElementVisible(driver.findElement(languageSwitch));
        WebElement changeMethod = driver.findElement(languageSwitch);
        clickByJS(changeMethod);
        waitUtilElementVisible(driver.findElement(languageTxt));
        if (driver.findElement(languageTxt).getText().toLowerCase().equals("en")) {
            //change to En
            selectLanguage(driver.findElement(vietnameseOption));
        } else {
            //change to Vi
            selectLanguage(driver.findElement(englishOption));
        }
    }

    public List<WebElement> changeLanguage(By languageSwitch, By languageOptionsList) {
        List<WebElement> languageList = new ArrayList<>();
        pressPageUpAction();
        visibleOfLocated(languageSwitch);
        try {
            clickBtn(driver.findElement(languageSwitch));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            clickByJS(driver.findElement(languageSwitch));
        }
        waitForJStoLoad();
        languageList = getElementList(languageOptionsList);
        return languageList;
    }

    /**
     * Check text/place-holder with language switch
     *
     * @param language  get current language on language switch
     * @param element   Element want to check text
     * @param typeCheck text / placeholder
     * @param page      page want to check (check page keyword from vi/en.json) -- will enhance when adding json file for another platform/page
     * @param key       keyword of text on json file (as expected text)
     * @return
     */
    public Boolean checkTextWithLanguage(String language, WebElement element, String typeCheck, String page, List<String> key) {
        expectedLanguage = jsonReader.localeReader(language, page, key);
        if (typeCheck.equals("text")) {
            return element.getText().trim().contains(expectedLanguage);
        } else {
            return element.getAttribute("placeholder").trim().contains(expectedLanguage);
        }
    }

    /**
     * Create a general method to check UI when user changes language
     *
     * @param language
     * @param element   name of element
     * @param typeCheck text / placeholder : define type to get property
     * @param xpath
     * @param page
     * @param key
     * @return
     */
    public Boolean commonLanguageCheckLocaleFile(String language, String element, String typeCheck, By xpath, String page, List<String> key) {
        expectedLanguage = "";
        Boolean result;
        WebElement e = driver.findElement(xpath);
        result = checkTextWithLanguage(language, e, typeCheck, page, key);
        if (result == false) {
            System.out.println("FAILED! " + element + " Actual: \"" + e.getText() + "\" Expected: \"" + expectedLanguage + "\"");
        }
        return result;
    }

    public Boolean checkValueEquals(String errormsg, int actual, int expected) {
        actualRS = "";
        Boolean flag = false;
        if (actual == expected) flag = true;
        else actualRS = actualRS + errormsg + " wrong. Actual: " + actual + " Expected: " + expected + "\n";
        return flag;
    }

    public Boolean checkValueEquals(String errormsg, float actual, float expected) {
        actualRS = "";
        Boolean flag = false;
        if (actual == expected) flag = true;
        else actualRS = actualRS + errormsg + " wrong. Actual: " + actual + " Expected: " + expected + "\n";
        return flag;
    }

    public Boolean checkValueEquals(String errormsg, String actual, String expected) {
        actualRS = "";
        Boolean flag = false;
        if (actual.equals(expected)) flag = true;
        else actualRS = actualRS + errormsg + " wrong. Actual: " + actual + " Expected: " + expected + "\n";
        return flag;
    }

    public WebElement waitForElementPresent(By by) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
        return null;
    }

    public Object waitTextToBePresent(By by, String expectedText) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(by, expectedText));
        } catch (Throwable error) {
            Log.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
        return null;
    }

    public boolean isElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Log.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            Log.error("The " + by + " is not visible");
            return false;
        }
    }

    public void waitElementIsRemoved(By by) {
        wait.until(ExpectedConditions.stalenessOf(getWebElement(by)));
    }

    public void waitElementIsRemoved(By by, Integer timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.stalenessOf(getWebElement(by)));
    }

    public WebElement waitForElementVisible(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error(error.getMessage());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    public Boolean waitForElementToBeIntercepted() {
        boolean isIntercepted = wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                // Check if the original element is intercepted by another element
                try {
                    clickElement(By.xpath("//div[@id='logo-img']"));
                    return false;
                } catch (Exception e) {
                    Log.info(e.getMessage());
                    return true;
                }
            }
        });
        return isIntercepted;
    }

    public WebElement waitForElementVisible(By by, String message) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element Visible. (Message: " + message + ")" + by.toString());
            Assert.fail(message);
        }
        return null;
    }

    public void waitForElementInVisible(By by) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element to be invisible. " + by.toString());
            Assert.fail("Timeout waiting for the element Invisible. " + by.toString());
        }
    }

    public void waitForElementInVisible(By by, String message) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element to be invisible. " + by.toString());
            Assert.fail(message);
        }
    }

    public void clickElement(By by) {
        try {
            waitForElementVisible(by).click();
            Log.info("Clicked on the element " + by.toString());
        } catch (StaleElementReferenceException e) {
            Log.error("The element goes stale, try to click again " + by.toString());
            waitForElementVisible(by).click();
        }
    }

    public void clickElement(By by, String message) {
        try {
            waitForElementVisible(by, message).click();
            Log.info("Clicked on the element " + by.toString());
        } catch (StaleElementReferenceException e) {
            Log.error(message);
            waitForElementVisible(by).click();
        }
    }

    public void enterText(By by, String value) {
        waitForElementVisible(by).sendKeys(value);
        Log.info("Set text " + value + " on " + by.toString());
    }

    public void clearText(By by) {
        waitForElementVisible(by).clear();
    }

    public String getText(By by) {
        Log.info("Get text: " + by + "");
        return waitForElementVisible(by).getText();
    }

    public void scrollToElementAtTop(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        smartWait();
        Log.info("Scroll " + by + " to top");
    }

    public void scrollToElementAtBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        smartWait();
        Log.info("Scroll " + by + " to bottom");
    }

    public void scrollToElementAtMiddle(By by) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" + "var elementTop = arguments[0].getBoundingClientRect().top;" + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, getWebElement(by));
        smartWait();
    }

    public WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    public void clickJS(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getWebElement(by));
        Log.info("Click " + by + "");
    }

    public void waitForElementToBeEnabled(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            Log.error("Timeout waiting for the element to be enable. " + by.toString());
            Assert.fail("Timeout waiting for the element enable. " + by.toString());
        }
    }

    public void smartWait() {
        waitForPageLoaded();
        sleep(1);
        Log.info("Wait for 1s");
    }

    public void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleepHaftSec() {
        try {
            Thread.sleep((500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentUrl() {
        smartWait();
        return driver.getCurrentUrl();
    }

    public boolean verifyContains(String value1, String value2, String message) {
        boolean result = value1.contains(value2);
        if (result == true) {
            Log.info("Verify Equals: " + value1 + " CONTAINS " + value2);
        } else {
            Log.info("Verify Contains: " + value1 + " NOT CONTAINS " + value2);
            Assert.assertEquals(value1, value2, message);
        }
        return result;
    }

    public Boolean checkDisplayDefaultLanguage(By languageTxt, String defaultLanguage) {
        pressPageUpAction();
        waitUtilElementVisible(driver.findElement(languageTxt));
        return driver.findElement(languageTxt).getText().toString().trim().equals(defaultLanguage);
    }

    //time
    public LocalDateTime getDateTime(int year, int month, int day, int hour, int minute, int second, int nanoseconds) {
        return LocalDateTime.of(year, month, day, hour, minute, second, nanoseconds * 1000000);
    }

    public String getUTCTimeFormatISO(LocalDateTime dateTime) {
        ZoneId localZoneId = ZoneId.systemDefault(); //Local time zone
        ZonedDateTime timeInLocalZone = dateTime.atZone(localZoneId);
        ZonedDateTime timeInUtc = timeInLocalZone.withZoneSameInstant(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return (timeInUtc.format(formatter) + "Z");
    }

    public String getUTCTimeFormatISOVN(LocalDateTime dateTime) {
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime timeInVietnam = ZonedDateTime.of(dateTime, vietnamZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        ZoneId utcZone = ZoneId.of("UTC");
        ZonedDateTime timeInUtc = timeInVietnam.withZoneSameInstant(utcZone);
        return (timeInUtc.format(formatter) + "Z");
    }

    public String getUTCTime(LocalDateTime dateTime) {
        ZoneId localZoneId = ZoneId.systemDefault(); //Local time zone
        ZonedDateTime timeInLocalZone = dateTime.atZone(localZoneId);
        ZonedDateTime timeInUtc = timeInLocalZone.withZoneSameInstant(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeInUtc.format(formatter);
    }

    public String getUTCTimeVN(LocalDateTime dateTime) {
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime timeInVietnam = ZonedDateTime.of(dateTime, vietnamZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId utcZone = ZoneId.of("UTC");
        ZonedDateTime timeInUtc = timeInVietnam.withZoneSameInstant(utcZone);
        return timeInUtc.format(formatter);
    }

    public String getTimeFromUTC(String utcTime, String zone) {
        Instant instant = Instant.parse(utcTime);
        ZoneId vietnamZone = ZoneId.of(zone);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, vietnamZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }

    //price and discount
    public String formatCurrencyToThousand(int number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return (formatter.format(number) + jsonAPIPosReader.getCurrencySymbol());
    }

    public String formatCurrencyToThousand(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return (formatter.format(number));
    }

    public String formatCurrencyToThousand(float number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return (formatter.format(number));
    }

    public String formatCurrencyToThousandWithCurrencyCode(int number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return (formatter.format(number) + jsonAPIPosReader.getCurrencyCode());
    }

    public String formatCurrencyToThousandWithoutSymbol(int number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return (formatter.format(number));
    }

    public String formatCurrencyToThousandWithoutSymbol(String numberStr) {
        String replaceStr = numberStr.substring(0, numberStr.lastIndexOf(",") + 4);
        return replaceStr;
    }

    public String formatPercentDiscount(double discountPercent) {
        DecimalFormat formatter;
        String percentStr = String.valueOf(discountPercent);
        if (percentStr.substring(percentStr.indexOf(".") + 1).length() < 2) {
            if (discountPercent % 1 == 0) {
                formatter = new DecimalFormat("-##0%");
            } else {
                formatter = new DecimalFormat("-##0.0%");
            }
        } else {
            formatter = new DecimalFormat("-##0.00%");
        }
        return formatter.format(discountPercent / 100);
    }

    public double calculateDiscountPercent(int oldPrice, int newPrice) {
        double discountPercent = ((double) (oldPrice - newPrice) / oldPrice) * 100.0;
        return Math.round(discountPercent * 100.0) / 100.0; //Round to 2 decimal places
    }

    public double calculateDiscountPercentFromFloat(float oldPrice, float newPrice) {
        double discountPercent = ((double) (oldPrice - newPrice) / oldPrice) * 100.0;
        return Math.round(discountPercent * 100.0) / 100.0; //Round to 2 decimal places
    }

    /**
     * convert from #,###đ to ####
     *
     * @param numberString
     * @return
     */
    public int convertToInteger(String numberString) {
        // check and delete "đ"
        if (numberString.endsWith("đ")) {
            numberString = numberString.substring(0, numberString.length() - 1);
        }
        //Delete ","
        String cleanedString = numberString.replaceAll(",", "");
        return Integer.parseInt(cleanedString);
    }

    public float convertToFloat(String numberString) {
        // check and delete "đ"
        if (numberString.endsWith("đ")) {
            numberString = numberString.substring(0, numberString.length() - 1);
        }
        //Delete ","
        String cleanedString = numberString.replaceAll(",", "");
        return Float.parseFloat(cleanedString);
    }

    public double convertToDouble(String numberString) {
        // check and delete "đ"
        if (numberString.endsWith("đ")) {
            numberString = numberString.substring(0, numberString.length() - 1);
        }
        //Delete ","
        String cleanedString = numberString.replaceAll(",", "");
        return Double.parseDouble(cleanedString);
    }

    public int convertCurrencyToInteger(String numberString) {
        String cleanedString = numberString.replaceAll("[+,đ]", "");
        return Integer.parseInt(cleanedString);
    }

    public int convertToIntegerWithEnd(String numberString, String endWith, int length) {
        // check and delete "đ"
        if (numberString.toLowerCase().endsWith(endWith)) {
            numberString = numberString.substring(0, numberString.length() - length);
        }
        //Delete ","
        String cleanedString = numberString.replaceAll(",", "").replaceAll(" ", "");
        return Integer.parseInt(cleanedString.trim());
    }

    public float convertToFloatWithEnd(String numberString, String endWith, int length) {
        // check and delete "đ"
        if (numberString.toLowerCase().endsWith(endWith)) {
            numberString = numberString.substring(0, numberString.length() - length);
        }
        //Delete ","
        String cleanedString = numberString.replaceAll(",", "").replaceAll(" ", "");
        return Float.parseFloat(cleanedString.trim());
    }

    public String formatPriceToCurrencyWithCurrencyCode(String currentLanguage, int number) {
        Locale locale = new Locale(currentLanguage);
        ResourceBundle bundle = ResourceBundle.getBundle("CurrencySymbols", locale);
        String currencySymbol = bundle.getString("currencySymbol");
        System.out.println("Currency symbol for location \"vi\": " + currencySymbol);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return (formatter.format(number) + currencySymbol);
    }

    public void refreshPage() {
        String url = getCurrentUrl();
        try {
            driver.navigate().refresh();
            waitForPageLoaded();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            refreshPageByActions();
            waitForPageLoaded();
        }
        waitForURL(url);
    }

    public synchronized String generateRandomNumber() {
        long uniqueId = counter.incrementAndGet();

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("ddhhmmssMs");
        String datetime = ft.format(dNow);
        return datetime + uniqueId;
    }

    public int generateRandomNumberWithBound(int bound) {
        Random r = new Random();
        return r.nextInt(bound - 1);
    }

    public void waitForElementHeightToChange(By element, int targetHeight) {
        WebElement el = getWebElement(element);
        // Wait until the height of the element changes to the desired number
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            Dimension currentSize = el.getSize();
            int currentHeight = currentSize.getHeight();
            return currentHeight != targetHeight;
        });
    }

    public double convertStringToDouble(String text) {
        // Remove commas and convert the string to a double
        try {
            return NumberFormat.getNumberInstance().parse(text.replaceAll(",", "")).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException("Error converting string to double: " + text);
        }
    }

    public String formatDoubleToString(double value) {
        // Format the double value as a string with commas
        return NumberFormat.getNumberInstance().format(value);
    }

    public double roundToTwoDecimalPlaces(double value) {
        // Round the double value to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }

    public double roundToTwoDecimalPlaces(String valueString) {
        Double valueDouble = convertStringToDouble(valueString);
        // Round the double valueString to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(valueDouble));
    }

    public String getCurrentDate() {
        // Get the current date
        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Format the current date as a string
        String formattedDate = dateFormat.format(currentDate);

        return formattedDate;
    }

    public String getNextDay() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Add 1 day to the current date
        LocalDate nextDay = currentDate.plusDays(1);

        // Define the desired date format
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Format the next day as a string
        String formattedNextDay = nextDay.format(dateFormat);

        return formattedNextDay;
    }

    public String getANumberofNextDay(int days) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Add 1 day to the current date
        LocalDate nextDay = currentDate.plusDays(days);

        // Define the desired date format
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Format the next day as a string
        String formattedNextDay = nextDay.format(dateFormat);

        return formattedNextDay;
    }

    public void switchTab(String tab) {
        driver.switchTo().window(tab);
    }

    public void waitExpectedAttributeToBe(By by, String attribute, String expectedValue, String message) {
        try {
            wait.until(ExpectedConditions.attributeToBe(by, attribute, expectedValue));
        } catch (Exception e) {
            throw new AssertionError(message);
        }
    }

    public void hover(By element) {
        actions.moveToElement(getWebElement(element)).perform();
        Log.info("Hover to: " + element + "");
    }

    public void clearCookies() {
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        Log.info("Clear cache");
    }

    public String removeDiacritics(String text) {
        // Normalize the text to decomposed form
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        // Remove diacritics using regex
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedText).replaceAll("").toLowerCase();
    }

    public void waitForNumberOfElements(By by, int expectedNumberOfElements, String message) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(by, expectedNumberOfElements));
        } catch (Exception e) {
            Assert.fail(message);
        }
    }

    public void waitTheElementChecked(By by, String message) {
        try {
            wait.until(ExpectedConditions.elementToBeSelected(by));
        } catch (Throwable error) {
            Assert.fail(message);
        }
    }

    public void waitTheElementChecked(WebElement element, String message) {
        try {
            wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (Throwable error) {
            Assert.fail(message);
        }
    }

    public void waitForNumberOfElements(By by, int expectedNumberOfElements) {
        wait.until(ExpectedConditions.numberOfElementsToBe(by, expectedNumberOfElements));
    }

    public void clickElementWithRetries(By by, By expectedBy, int retries, String message) {
        boolean conditionMet = false;
        for (int attempt = 0; attempt < retries; attempt++) {
            try {
                getTheVisibleElement(by).click();
                getTheVisibleElement(expectedBy);
                conditionMet = true;
                break; // Condition met, exit the loop
            } catch (Exception e) {
                // Log the exception or retry message if necessary
                System.out.println("Attempt " + (attempt + 1) + " failed, retrying...");
            }
        }
        if (!conditionMet) {
            Assert.fail(message);
        }
    }

    public WebElement getTheVisibleElement(String xpath) {
        try {
            // Wait until any element specified by the XPath is visible
            WebElement visibleElement = wait.until(driver -> {
                List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        return element;
                    }
                }
                return null;
            });
            return visibleElement;

        } catch (TimeoutException e) {
            Assert.fail("Can not locate a visible element with xpath: " + xpath);
            return null; // Or throw an exception indicating no element was visible
        }
    }

    public WebElement getTheVisibleElement(By by) {
        try {
            // Wait until any element specified by the XPath is visible
            WebElement visibleElement = wait.until(driver -> {
                List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(getTheXpathStringFromBy(by))));
                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        return element;
                    }
                }
                return null;
            });
            return visibleElement;

        } catch (TimeoutException e) {
            Assert.fail("Can not locate a visible element with xpath: " + getTheXpathStringFromBy(by));
            return null; // Or throw an exception indicating no element was visible
        }
    }

    public String getTheXpathStringFromBy(By by) {
        String check = by.toString();
        String[] xpath = check.split(" ", 2);
        return xpath[1];
    }

    public void clickText(String text) {
        clickElement(By.xpath("//*[starts-with(text(),'" + text + "')]"));
    }

    public void verifIfTheTextVisible(String text) {
        Assert.assertTrue(isElementVisible(By.xpath("//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '" + text.toLowerCase() + "']")), "The " + text + " is not visible");
    }

    public String getHourAndMinuteNow() {
        LocalTime now = LocalTime.now();           // Current time
        LocalTime updatedTime = now.plusMinutes(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Formatter for hour and minute
        return updatedTime.format(formatter);
    }

    public String getHourAndMinuteCustom(Integer plusHours, Integer plusMinutes) {
        LocalTime now = LocalTime.now();           // Current time
        LocalTime updatedTime = now.plusHours(plusHours).plusMinutes(plusMinutes);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Formatter for hour and minute
        return updatedTime.format(formatter);
    }

    public boolean isElementVisible(By by, Integer customWait) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(customWait));
            Log.info("Verify element visible " + by);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public void wait(int timeout) {
        sleep(timeout);
    }

    public boolean containsInRectangle(By parentEle, By childEle) {
        WebElement parent = getTheVisibleElement(parentEle);
        WebElement child = getTheVisibleElement(childEle);

        java.awt.Rectangle rect_parent = new java.awt.Rectangle(parent.getLocation().getX(), parent.getLocation().getY(), parent.getSize().width, parent.getSize().height);
        java.awt.Rectangle rect_child = new java.awt.Rectangle(child.getLocation().getX(), child.getLocation().getY(), child.getSize().width, child.getSize().height);

        return rect_parent.contains(rect_child);
    }

    public void clearCache() {
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
    }

    public AppiumBy getAppiumByUiSelector(String locator) {
        return new AppiumBy.ByAndroidUIAutomator(locator);
    }
}
