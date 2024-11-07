package com.fnb.app.storeapp.android.linstore.pages.blogs;

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

public class BlogsPage extends BaseSetup {
    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;
    static TouchAction touchAction;

    public BlogsPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        touchAction = new TouchAction(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.TextView[@text=\"11\"]")
    public WebElement element11;
    @FindBy(xpath = "//android.view.View[@content-desc=\"11 Tháng 9 Dat Blog Tuyên bố chung về nâng cấp quan hệ Việt Nam - Hoa Kỳ lên Đối tác Chiến lược Toàn diện 604 bởi Lại là Lin đây Lại là Lin đây Lại là Lin đây Lại là Lin đây nè Lại là Lin đây Lại là Lin đây Lại là Nhận lời mời của Tổng Bí thư Nguyễn Phú Trọng, từ ngày 10 -11/9, Tổng thống Hợp chúng quốc Hoa Kỳ Joe Biden thực hiện chuyến thăm cấp Nhà nước.Báo điện tử Dân trí trân trọng giới thiệu Tuyên bố chung Image\"]")
    public WebElement blog;

    public void swipeVerticalDown() {
        int anchorX = 536;
        int anchorY = 1500;
        int startPointX = 536;
        int startPointY = 936;
        new TouchAction<>(driver)
                .press(PointOption.point(anchorX, anchorY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startPointX, startPointY))
                .release()
                .perform();
    }

    public void swipeVertical() {

        try {
            swipeVerticalDown();
            Thread.sleep(2000);
            swipeVerticalDown();
            Thread.sleep(2000);
            swipeVerticalDown();
            Thread.sleep(2000);
            swipeVerticalDown();
            Thread.sleep(2000);
            swipeVerticalDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText11() {
        return helper.waitToElementGetText(element11);
    }

    public void clickBlog() {
        helper.waitToElementClick(blog);
    }
}
