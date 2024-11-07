package com.fnb.posapp.screens;

import com.fnb.posapp.base.BaseSetup;
import com.fnb.posapp.components.Common;
import com.fnb.posapp.components.LeftMenu;
import com.fnb.utils.helpers.Helper;
import constants.PosAppFrameworkConstants;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

@Data
public class DashboardScreen extends BaseSetup {
    public Helper helper;
    public AndroidDriver androidDriver;
    public WebDriverWait wait;
    public Actions actions;
    public Common commonComponent;

    public DashboardScreen(AndroidDriver androidDriver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        actions = new Actions(driver);
        helper = new Helper(androidDriver, wait, actions);
        this.androidDriver = androidDriver;
        commonComponent = new Common(driver);
    }

    By scrollViewCategory = By.xpath("//android.widget.HorizontalScrollView");
    AppiumBy btnCreateOrder = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\""+posAppLocale.getDashboard().getCreateOrder()+"\")");
    AppiumBy textYouDoNotHaveAnyProducts = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\""+posAppLocale.getProduct().getYouDoNotHaveAnyProduct()+"\")");

    public void swipeRightWithinElement(By by) {
            HashMap<String, Object> swipeArgs = new HashMap<>();
            WebElement ele =helper.waitForElementVisible(by);
            int left = ele.getLocation().getX();
            int top = ele.getLocation().getY();
            int width = ele.getSize().width / 4;
            int height = ele.getSize().height;

//            swipeArgs.put("elementId", ((RemoteWebElement) helper.getWebElement(by)).getId());
            swipeArgs.put("direction", "left");
            swipeArgs.put("left", left);
            swipeArgs.put("top", top);
            swipeArgs.put("width", width);
            swipeArgs.put("height", height);
            swipeArgs.put("percent", 1);
            driver.executeScript("mobile: swipeGesture", swipeArgs);
    }

    public void selectCategory(String category) throws IOException {
        By categoryEle = By.xpath("//android.view.ViewGroup[@content-desc=\"" + category + "\"]");
        Color expectedColor = new Color(80, 66, 155);

        while (true) {
            if (helper.isElementVisible(categoryEle)) {
                if (helper.containsInRectangle(scrollViewCategory, categoryEle)) {
                    clickCategory(categoryEle, expectedColor, driver);
                    break;
                } else {
                    swipeRightWithinElement(scrollViewCategory);
                }
            } else {

                swipeRightWithinElement(scrollViewCategory);
            }
        }
    }

    public Color getElementColor(WebElement element) throws IOException {
        // Take screenshot of the element
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage bufferedImage = ImageIO.read(screenshot);

        // Assuming you want the color of the center pixel
        Point elementLocation = element.getLocation();
        int checkColorX = elementLocation.getX() + 10;
        int checkColorY = elementLocation.getY() + 10;
        int rgb = bufferedImage.getRGB(checkColorX, checkColorY);

        return new Color(rgb);
    }

    public void clickCategory(By element, Color expectedColor, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // timeout in 30 seconds
        wait.until((ExpectedCondition<Boolean>) driver1 -> {
            try {
                helper.clickElement(element);
                Color currentColor = getElementColor(helper.getWebElement(element));
                return currentColor.equals(expectedColor);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public CardDetailScreen clickProduct(String productName) {
        String locator = "new UiSelector().resourceId(\"com.gofnb.posapplication:id/txtProductName\").text(\"" + productName + "\")";
        helper.clickElement(helper.getAppiumByUiSelector(locator));
        helper.waitForElementVisible(By.xpath("(//android.widget.TextView[@text=\""+productName+"\"])[1]"));
        return new CardDetailScreen(driver);
    }

    private WebElement plusIconElement = null;
    public DashboardScreen clickPlusIcon_AddToCart(String productName, String priceValue) {
        String formatPriceValue = helper.formatDoubleToString(helper.convertStringToDouble(priceValue));
        By plusBtn = By.xpath("(//*[.//*[@text=\""+productName+"\"] and .//*[@text=\""+formatPriceValue+"\"]])[last()]//*[@resource-id=\"com.gofnb.posapplication:id/cvBtnAddCart\"]");
        if (plusIconElement == null) {
            plusIconElement = helper.waitForElementVisible(plusBtn);
        }
        plusIconElement.click();
        return this;
    }

    public DashboardScreen clickCreateOrder() {
        helper.waitForClickable(helper.waitForElementVisible(btnCreateOrder));
        helper.clickElement(btnCreateOrder);
        helper.waitForElementVisible(textYouDoNotHaveAnyProducts);
        return this;
    }

    public LeftMenu clickHamburgerButton() {
        helper.clickElement(helper.getAppiumByUiSelector("new UiSelector().className(\"com.horcrux.svg.SvgView\").instance(0)"));
        return new LeftMenu(driver);
    }
}
