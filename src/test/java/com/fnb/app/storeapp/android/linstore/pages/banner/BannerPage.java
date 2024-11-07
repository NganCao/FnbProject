package com.fnb.app.storeapp.android.linstore.pages.banner;

import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BannerPage extends BaseSetup {
    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;
    static TouchAction touchAction;


    public BannerPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        touchAction = new TouchAction(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.android.chrome:id/url_bar\"]")
    private WebElement url;

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    private WebElement backBtn;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"menu-profile-icon-0\"]")
    private WebElement homeIcon;

    public void clickBanner0() {
        int xCoordinate = 536;
        int yCoordinate = 851;
        try {
            Thread.sleep(3000);
            touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickBanner1() {
        try {
            swipeVertical();
            Thread.sleep(3000);
            swipeHorizontal();
            setClickBanner();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickBanner2() {
        try {
            clickBackBtn();
            Thread.sleep(3000);
            swipeVertical();
            Thread.sleep(1000);
            swipeHorizontal();
            Thread.sleep(1000);
            swipeHorizontal();
            setClickBanner();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickBanner3() {
        try {
            clickHomeIcon();
            Thread.sleep(2000);
            swipeHorizontal2();
            Thread.sleep(2000);
            swipeHorizontal2();
            setClickBanner();
            Thread.sleep(5000);
            System.out.println(getTextURL());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setClickBanner() {
        int xCoordinate = 536;
        int yCoordinate = 951;
        try {
            Thread.sleep(3000);
            touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeHorizontal() {
        int startPointX = 936;
        int startPointY = 950;
        int endPointX = 336;
        int endPointY = 950;
        new TouchAction<>(driver)
                .press(PointOption.point(startPointX, startPointY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endPointX, endPointY))
                .release()
                .perform();
    }

    public void swipeHorizontal2() {
        int startPointX = 936;
        int startPointY = 950;
        int endPointX = 336;
        int endPointY = 950;
        new TouchAction<>(driver)
                .press(PointOption.point(endPointX, endPointY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startPointX, startPointY))
                .release()
                .perform();
    }

    public void swipeVertical() {
        int anchorX = 536;
        int anchorY = 1500;
        int startPointX = 536;
        int startPointY = 936;
        new TouchAction<>(driver)
                .press(PointOption.point(startPointX, startPointY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(anchorX, anchorY))
                .release()
                .perform();
    }

    public boolean checkDisplayBackBtn() {
        return helper.checkElementDisplay(backBtn);
    }

    public void clickBackBtn() {
        try {
            Thread.sleep(3000);
            helper.waitToElementClick(backBtn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDisplayHomeIcon() {
        return helper.checkElementDisplay(homeIcon);
    }

    public void clickHomeIcon() {
        try {
            Thread.sleep(500);
            helper.waitToElementClick(homeIcon);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTextURL() {
        return helper.waitToElementGetText(url);
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}
