package com.fnb.app.storeapp.android.linstore.pages.loyaltypoint;

import com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage.UTC_ZONE;
import static com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage.VN_ZONE;
import static com.fnb.utils.api.storeapp.pos.DataTest.*;
import static com.fnb.utils.api.storeapp.pos.Get_LoyaltyPointRequest.*;
import static com.fnb.utils.api.storeapp.pos.Post_Login_Request.checkApiLoginSuccess;


public class LoyaltyPointPage extends BaseSetup {

    Helper helper;
    WebDriverWait wait;
    AndroidDriver driver;


    public LoyaltyPointPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"backBtn\"]")
    public WebElement backBtn;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"Title\"]")
    public WebElement Title;
    @FindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"OnClickLoyaltyPoint\"])[2]")
    public WebElement OnClickLoyaltyPoint;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"MembershipIcon\"]")
    public WebElement MembershipIcon;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"FullName\"]")
    public WebElement FullName;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"PointIcon\"]")
    public WebElement PointIcon;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"PointValue\"]")
    public WebElement PointValue;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"MembershipName\"]")
    public WebElement MembershipName;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"BarCode\"]")
    public WebElement BarCode;
    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"BarCodeImg\"]")
    public WebElement BarCodeImg;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"BarCodeValue\"]")
    public WebElement BarCodeValue;
    @FindBy(xpath = "(//android.view.View[@resource-id=\"TabBarOverView\"])[1]")
    public WebElement TabBarOverView;
    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"CrownImg\"]")
    public WebElement CrownImg;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TitleRank\"]")
    public WebElement TitleRank;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ProgressContainer\"]")
    public WebElement ProgressContainer;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"Point\"]")
    public WebElement Point;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"Container\"]")
    public WebElement Container;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"NumberRemainPointText\"]")
    public WebElement NumberRemainPointText;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ContentAvailablePoint\"]")
    public WebElement ContentAvailablePoint;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"AvailablePoint\"]")
    public WebElement AvailablePoint;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"ContentExpiredPoint\"]")
    public WebElement ContentExpiredPoint;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"TotalOrderBox\"]")
    public WebElement TotalOrderBox;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalOrderTitle\"]")
    public WebElement TotalOrderTitle;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalOrderNumber\"]")
    public WebElement TotalOrderNumber;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"TotalMoneySpentBox\"]")
    public WebElement TotalMoneySpentBox;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalMoneySpentTitle\"]")
    public WebElement TotalMoneySpentTitle;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TotalMoneySpent\"]")
    public WebElement TotalMoneySpent;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"MembershipOffersTitle\"]")
    public WebElement MembershipOffersTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MembershipBox0\"]")
    public WebElement MembershipBox0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"DescriptionMembershipDetail0\"]")
    public WebElement DescriptionMembershipDetail0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ContentCrownIcon0\"]")
    public WebElement ContentCrownIcon0;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MembershipBox1\"]")
    public WebElement MembershipBox1;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TitleMembershipDetail1\"]")
    public WebElement TitleMembershipDetail1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"DescriptionMembershipDetail1\"]")
    public WebElement DescriptionMembershipDetail1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ContentCrownIcon1\"]")
    public WebElement ContentCrownIcon1;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MembershipBox2\"]")
    public WebElement MembershipBox2;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"TitleMembershipDetail2\"]")
    public WebElement TitleMembershipDetail2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"DescriptionMembershipDetail2\"]")
    public WebElement DescriptionMembershipDetail2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"ContentCrownIcon2\"]")
    public WebElement ContentCrownIcon2;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"acceptBtn\"]")
    public WebElement acceptBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"MembershipBox3\"]")
    public WebElement MembershipBox3;
    @FindBy(xpath = "//com.horcrux.svg.SvgView[@resource-id=\"CloseMembership\"]")
    public WebElement CloseMembership;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"DescriptionMembershipDetail3\"]")
    public WebElement DescriptionMembershipDetail3;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"DescriptionMembershipDetail6\"]")
    public WebElement DescriptionMembershipDetail6;

    public void clickAccept() {
        helper.waitToElementClick(acceptBtn);
    }


    public boolean CheckDisplayMembershipIcon() {
        return helper.checkElementDisplay(MembershipIcon);
    }

    public boolean CheckDisplayFullName() {
        return helper.checkElementDisplay(FullName);
    }

    public boolean CheckDisplayPointIcon() {
        return helper.checkElementDisplay(PointIcon);
    }

    public boolean CheckDisplayPointValue() {
        return helper.checkElementDisplay(PointValue);
    }

    public boolean CheckDisplayMembershipName() {
        return helper.checkElementDisplay(MembershipName);
    }

    public boolean CheckDisplayBarCode() {
        return helper.checkElementDisplay(BarCode);
    }

    public boolean CheckDisplayBarCodeImg() {
        return helper.checkElementDisplay(BarCodeImg);
    }

    public boolean CheckDisplayBarCodeValue() {
        return helper.checkElementDisplay(BarCodeValue);
    }

    public boolean CheckDisplayBackBtn() {
        try {
            Thread.sleep(2000);
            return helper.checkElementDisplay(backBtn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean CheckDisplayTitle() {
        return helper.checkElementDisplay(Title);
    }

    public boolean CheckDisplayTabBarOverView() {
        return helper.checkElementDisplay(TabBarOverView);
    }

    public boolean CheckDisplayCrownImg() {
        return helper.checkElementDisplay(CrownImg);
    }

    public boolean CheckDisplayTitleRank() {
        return helper.checkElementDisplay(TitleRank);
    }

    public boolean CheckDisplayProgressContainer() {
        return helper.checkElementDisplay(ProgressContainer);
    }

    public boolean CheckDisplayPoint() {
        return helper.checkElementDisplay(Point);
    }

    public boolean CheckDisplayContainer() {
        return helper.checkElementDisplay(Container);
    }

    public boolean CheckDisplayNumberRemainPointText() {
        return helper.checkElementDisplay(NumberRemainPointText);
    }

    public boolean CheckDisplayContentAvailablePoint() {
        return helper.checkElementDisplay(ContentAvailablePoint);
    }

    public boolean CheckDisplayAvailablePoint() {
        return helper.checkElementDisplay(AvailablePoint);
    }

    public boolean CheckDisplayContentExpiredPoint() {
        return helper.checkElementDisplay(ContentExpiredPoint);
    }

    public boolean CheckDisplayTotalOrderBox() {
        return helper.checkElementDisplay(TotalOrderBox);
    }

    public boolean CheckDisplayTotalOrderTitle() {
        return helper.checkElementDisplay(TotalOrderTitle);
    }

    public boolean CheckDisplayTotalOrderNumber() {
        return helper.checkElementDisplay(TotalOrderNumber);
    }

    public boolean CheckDisplayTotalMoneySpentBox() {
        return helper.checkElementDisplay(TotalMoneySpentBox);
    }

    public boolean CheckDisplayTotalMoneySpentTitle() {
        return helper.checkElementDisplay(TotalMoneySpentTitle);
    }

    public boolean CheckDisplayTotalMoneySpent() {
        return helper.checkElementDisplay(TotalMoneySpent);
    }

    public boolean CheckDisplayMembershipOffersTitle() {
        return helper.checkElementDisplay(MembershipOffersTitle);
    }

    public boolean CheckDisplayMembershipBox0() {
        return helper.checkElementDisplay(MembershipBox0);
    }

    public boolean CheckDisplayMembershipBox2() {
        return helper.checkElementDisplay(MembershipBox2);
    }

    public boolean CheckDisplayDescriptionMembershipDetail0() {
        return helper.checkElementDisplay(DescriptionMembershipDetail2);
    }

    public boolean CheckDisplayContentCrownIcon0() {
        return helper.checkElementDisplay(ContentCrownIcon0);
    }

    public boolean CheckDisplayMembershipBox1() {
        return helper.checkElementDisplay(MembershipBox1);
    }

    public boolean CheckDisplayTitleMembershipDetail1() {
        return helper.checkElementDisplay(TitleMembershipDetail1);
    }

    public boolean CheckDisplayDescriptionMembershipDetail1() {
        return helper.checkElementDisplay(DescriptionMembershipDetail1);
    }

    public boolean CheckDisplayContentCrownIcon() {
        return helper.checkElementDisplay(ContentCrownIcon1);
    }

    public String getTextFullName() {
        return helper.waitToElementGetText(FullName);
    }

    public String getTextPointValue() {
        return helper.waitToElementGetText(PointValue);
    }

    public String getTextMembershipName() {
        return helper.waitToElementGetText(MembershipName);
    }

    public String getTextBarCodeValue() {
        return helper.waitToElementGetText(BarCodeValue);
    }

    public String getTextTitle() {
        return helper.waitToElementGetText(Title);
    }

    public String getTextTabBarOverView() {
        return helper.waitToElementGetText(TabBarOverView);
    }

    public String getTextTitleRank() {
        return helper.waitToElementGetText(TitleRank);
    }

    public String getTextNumberRemainPointText() {
        return helper.waitToElementGetText(NumberRemainPointText);
    }

    public String getTextContentAvailablePoint() {
        return helper.waitToElementGetText(ContentAvailablePoint);
    }

    public String getTextAvailablePoint() {
        return helper.waitToElementGetText(AvailablePoint);
    }

    public String getTextContentExpiredPoint() {
        return helper.waitToElementGetText(ContentExpiredPoint);
    }

    public String getTextTotalOrderTitle() {
        return helper.waitToElementGetText(TotalOrderTitle);
    }

    public String getTextTotalOrderNumber() {
        return helper.waitToElementGetText(TotalOrderNumber);
    }

    public String getTextTotalMoneySpentTitle() {
        return helper.waitToElementGetText(TotalMoneySpentTitle);
    }

    public String getTextTotalMoneySpent() {
        return helper.waitToElementGetText(TotalMoneySpent);
    }

    public String getTextMembershipOffersTitle() {
        return helper.waitToElementGetText(MembershipOffersTitle);
    }

    public String getTextTitleMembershipDetail2() {
        return helper.waitToElementGetText(TitleMembershipDetail2);
    }

    public void CTAOnClickLoyaltyPoint() {
        helper.waitToElementClick(MembershipIcon);
    }

    public void CTAOnDescriptionMembershipDetail2() {
        helper.waitToElementClick(DescriptionMembershipDetail2);
    }

    public void navigateScreen(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.info("Fail to load screen");
            e.printStackTrace();
        }
    }

    public void clickElement(WebElement element) {
        try {
            helper.waitToElementClick(element);
        } catch (Exception e) {
            Log.info("Element can't click" + PromotionProductListPage.class.getName());
            e.printStackTrace();
        }
    }

    public String checkApiFullName() {
        response = checkApiLoginSuccess(PHONE_NUMBER, PHONE_CODE, PASSWORD, STORE_ID, BRANCH_ID);
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("data.customerInfo.fullName");
    }

    public String checkCustomerLoyaltyPoint(String key) {
        return checkApiGetCustomerLoyaltyPoint(key);
    }

    public String checkCustomerLoyaltyPointBarCodeValue() {
        return getCustomerInfo("code");
    }

    public String checkCustomerLoyaltyPointDetailModel(String key) {
        return checkApiGetCustomerLoyaltyPointDetailModel(key);
    }

    public String checkCustomerLoyaltyPointDetail(String key) {
        return checkApiGetCustomerLoyaltyPointDetail(key);
    }

    public String checkCustomerMemberships(String key) {
        return checkApiGetCustomerMemberships(key);
    }

    public String checkNumbRemainPointNext() {
        String customMembershipFuturePoint = checkApiGetCustomerLoyaltyPointDetail("customMembershipFuturePoint");
        String availablePoint = checkApiGetCustomerLoyaltyPoint("availablePoint");
        int NumbRemainPointNext = 0;
        if (customMembershipFuturePoint != null && availablePoint != null) {
            NumbRemainPointNext = Integer.parseInt(customMembershipFuturePoint) - Integer.parseInt(availablePoint);
        }

        String futureRank = checkApiGetCustomerLoyaltyPointDetail("customerMembershipFutureName");
        String formatNumber = formatNumber(String.valueOf(NumbRemainPointNext));
        String formatNumberRemainPointText = "Tích thêm " + formatNumber + " điểm để đạt hạng " + futureRank;
        return formatNumberRemainPointText;
    }

    public String formatNumber(String number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedNumber;
        try {
            double numericValue = Double.parseDouble(number);
            formattedNumber = decimalFormat.format(numericValue);
            return String.valueOf(formattedNumber);
        } catch (NumberFormatException e) {
            Log.info("String incorrect Format");
        }
        return null;
    }

    public String formatDecimal(String StringNumber) {
        try {
            double number = Double.parseDouble(StringNumber);
            int intValue = (int) number;
            String formattedNumber = String.valueOf(intValue);
            return formattedNumber;
        } catch (NumberFormatException e) {
            Log.info("String incorrect Format");
        }
        return null;
    }

    public String formatMoney(String money) {
        try {
            double number = Double.parseDouble(money.replace(",", "."));
            int formatNumber = (int) Math.round(number);
            return String.valueOf(formatNumber);
        } catch (NumberFormatException e) {
            System.out.println("String incorrect Format");
        }
        return null;
    }

    public static String formatBarCodeValue(String Id) {
        return String.format("%09d", Integer.parseInt(Id));
    }

    public String checkAvailablePoint() {
        String availablePoint = checkApiGetCustomerLoyaltyPointDetailModel("availablePoint");
        return formatNumber(availablePoint);
    }

    public String checkTotalOrderNumber() {
        String orderNumber = checkApiGetCustomerLoyaltyPointDetail("totalOrder");
        return formatDecimal(orderNumber);
    }

    public String checkTotalMoneySpent() {
        String s = formatMoney(checkApiGetCustomerLoyaltyPointDetail("totalMoney"));
        String format = formatNumber(s) + "đ";
        return format;
    }

    public String checkPointValue() {
        String s = checkApiGetCustomerLoyaltyPoint("availablePoint");
        String format = formatNumber(s) + " điểm";
        return format;
    }

    public String checkBarCodeValue() {
        return formatBarCodeValue(getCustomerInfo("code"));
    }
//TODO: improvement
    private static String formatDateString(String inputDate, String pattern) {
        System.out.println(inputDate);
        LocalDateTime utcDateTime = LocalDateTime.parse(inputDate, DateTimeFormatter.ISO_DATE_TIME);
        ZoneId utcZoneId = ZoneId.of(UTC_ZONE);
        ZoneId vnZoneId = ZoneId.of(VN_ZONE);
        ZonedDateTime utcZonedDateTime = ZonedDateTime.of(utcDateTime, utcZoneId);
        ZonedDateTime vnZonedDateTime = utcZonedDateTime.withZoneSameInstant(vnZoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return vnZonedDateTime.format(formatter);
    }
    public static String formatDateString() {
        String inputDate = checkApiGetCustomerLoyaltyPointDetail("expiryDate");
        return formatDateString(inputDate, "'Điểm sẽ hết hạn vào ngày 'dd/MM/yyyy");
    }
}
