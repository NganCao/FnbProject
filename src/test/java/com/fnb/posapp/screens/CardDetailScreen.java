package com.fnb.posapp.screens;

import com.fnb.posapp.base.BaseSetup;
import com.fnb.posapp.components.Common;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import constants.PosAppFrameworkConstants;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;

public class CardDetailScreen extends BaseSetup {
    public Helper helper;
    public AndroidDriver androidDriver;
    public WebDriverWait wait;
    public Actions actions;
    public Common commonComponent;

    public CardDetailScreen(AndroidDriver androidDriver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(PosAppFrameworkConstants.TIMEOUT));
        actions = new Actions(driver);
        helper = new Helper(androidDriver, wait, actions);
        this.androidDriver = androidDriver;
        commonComponent = new Common(driver);
    }
    By btnAddToCard = By.xpath("//android.widget.TextView[@text=\""+posAppLocale.getProduct().getAddToCart()+"\"]");
    String plus_Minus_AddToCard_Button = "//*[.//*[@text and normalize-space(@text) != '' and normalize-space(@text) != '"+posAppLocale.getDashboard().getSaveDraft()+"' and normalize-space(@text) != '"+posAppLocale.getDashboard().getCreateOrder()+"' and normalize-space(@text) != '"+posAppLocale.getDashboard().getTable()+"'  and normalize-space(@text) != '"+posAppLocale.getCustomer().getCustomerName()+"'] and (.//com.horcrux.svg.SvgView)[2] and  (.//com.horcrux.svg.SvgView)[1] and not((.//com.horcrux.svg.SvgView)[3])]//android.view.ViewGroup";
    By plusBtn = By.xpath(plus_Minus_AddToCard_Button + "[2]");
    By minusBtn = By.xpath(plus_Minus_AddToCard_Button + "[1]");

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

    public CardDetailScreen selectPriceName(String priceName, String priceValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By element = helper.getAppiumByUiSelector("new UiSelector().description(\"" +priceName+", "+priceValue+ "\")");
        Color expectedColor = new Color(80, 66, 155);

        wait.until((ExpectedCondition<Boolean>) driver1 -> {
            helper.clickElement(element);
            return compareColor(expectedColor, element);
        });
        return this;
    }

    public boolean compareColor(Color expectedColor, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean flag;
        flag = wait.until((ExpectedCondition<Boolean>) driver1 -> {
            Color currentColor;
            try {
                currentColor = getElementColor(helper.getWebElement(element));
                return currentColor.equals(expectedColor);
            } catch (IOException e) {
                Log.error("The color is not the same with expected");
                return false;
            }
        });
        return flag;
    }

    public CardDetailScreen selectOption(String optionName, String optionValue) {
        By element = By.xpath("(//*[.//*[@text=\""+optionName+"\"] and .//*[@text=\""+optionValue+"\"]])[last()]//*[@text='"+optionValue+"']");
        helper.clickElement(element);
        return this;
    }

    public void clickAddToCart() {
        helper.clickElement(btnAddToCard);
        helper.waitForElementInVisible(btnAddToCard);
    }

    private WebElement plusIconElement = null;
    public CardDetailScreen clickPlusIcon() {
        if (plusIconElement == null) {
            plusIconElement = helper.waitForElementVisible(plusBtn);
        }
        plusIconElement.click();
//        tap(plusBtn);
        return this;
    }

    public CardDetailScreen clickMinusIcon() {
        helper.clickElement(minusBtn);
        return this;
    }

    public void tap(By by) {
        WebElement element = helper.getWebElement(by);
        Point location = element.getLocation();
        Dimension dimension = element.getSize();
        Point centerOfElement = getCenterOfElement(location, dimension);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth()/2, location.getY() + size.getHeight()/2);
    }
}
