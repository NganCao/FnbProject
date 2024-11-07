package com.fnb.app.storeapp.android.linstore.scenationtests.loyaltypoint;

import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.loyaltypoint.LoyaltyPointPage;
import com.fnb.app.setup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.app.storeapp.android.linstore.pages.loyaltypoint.LoyaltyPointDataTest.*;
import static com.fnb.app.storeapp.android.linstore.pages.loyaltypoint.LoyaltyPointPage.*;

public class LoyaltyPointScenarioTest extends BaseTest {
    LoyaltyPointPage loyaltyPointPage;
    LoginPageLinStore loginPageLinStore;
    SoftAssert softAssert;
    static String expectedData;
    static String originalData;

    @BeforeClass
    public void navigateToPage() {
        loyaltyPointPage = homePageLinStore.navigateToLoyaltyPointPage();
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void verifyNavigateToLoyaltyPoint() {
        loginPageLinStore.splashScreen();
    }

    @Test(priority = 1)
    public void verifyCheckDisplayMembershipIcon() {
        loginPageLinStore.navigateToLoginSuccess();
        Assert.assertTrue(loyaltyPointPage.CheckDisplayMembershipIcon(), "Testcase fail");
    }

    @Test(priority = 2)
    public void verifyCheckDisplayFullName() {
        originalData = loyaltyPointPage.getTextFullName();
        softAssert.assertTrue(loyaltyPointPage.CheckDisplayFullName(), "Testcase fail");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyFullNameWithAPI() {
        expectedData = loyaltyPointPage.checkApiFullName();
        softAssert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 4)
    public void verifyCheckDisplayPointIcon() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayPointIcon(), "Testcase fail");
    }

    @Test(priority = 5)
    public void verifyCheckDisplayPointValue() {
        originalData = loyaltyPointPage.getTextPointValue();
        Assert.assertTrue(loyaltyPointPage.CheckDisplayPointValue(), "Testcase fail");
    }

    @Test(priority = 6)
    public void verifyPointValueWithAPI() {
        expectedData = loyaltyPointPage.checkPointValue();
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 7)
    public void verifyCheckDisplayMembershipName() {
        originalData = loyaltyPointPage.getTextMembershipName();
        Assert.assertTrue(loyaltyPointPage.CheckDisplayMembershipName(), "Testcase fail");
    }

    @Test(priority = 8)
    public void verifyCheckDisplayBarCode() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayBarCode(), "Testcase fail");
    }

    @Test(priority = 9)
    public void verifyCheckDisplayBarCodeImg() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayBarCodeImg(), "Testcase fail");
    }

    @Test(priority = 10)
    public void verifyCheckDisplayBarCodeValue() {
        originalData = loyaltyPointPage.getTextBarCodeValue();
        Assert.assertTrue(loyaltyPointPage.CheckDisplayBarCodeValue(), "Testcase fail");
    }

    @Test(priority = 11)
    public void verifyBarCodeValueWithAPI() {
        expectedData = loyaltyPointPage.checkBarCodeValue();
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 12)
    public void verifyCTAOnClickLoyaltyPoint() {
        loyaltyPointPage.CTAOnClickLoyaltyPoint();
        Assert.assertTrue(loyaltyPointPage.CheckDisplayBackBtn(), "Testcase fail");
    }

    @Test(priority = 13)
    public void verifyCheckDisplayCheckDisplayTitle() {
        loyaltyPointPage.navigateScreen();
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTitle(), "Testcase fail");
    }


    @Test(priority = 15)
    public void verifyCheckDisplayTabBarOverView() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTabBarOverView(), "Testcase fail");
    }

    @Test(priority = 16)
    public void verifyCheckDisplayCrownImg() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayCrownImg(), "Testcase fail");
    }

    @Test(priority = 17)
    public void verifyCheckDisplayTitleRank() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTitleRank(), "Testcase fail");
    }

    @Test(priority = 18)
    public void verifyCheckDisplayProgressContainer() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayProgressContainer(), "Testcase fail");
    }

    @Test(priority = 19)
    public void verifyCheckDisplayPoint() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayPoint(), "Testcase fail");
    }

    @Test(priority = 20)
    public void verifyCheckDisplayContainer() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayContainer(), "Testcase fail");
    }

    @Test(priority = 21)
    public void verifyCheckDisplayNumberRemainPointText() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayNumberRemainPointText(), "Testcase fail");
    }

    @Test(priority = 22)
    public void verifyCheckDisplayContentAvailablePoint() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayContentAvailablePoint(), "Testcase fail");
    }

    @Test(priority = 23)
    public void verifyCheckDisplayAvailablePoint() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayAvailablePoint(), "Testcase fail");
    }

    @Test(priority = 24)
    public void verifyCheckDisplayContentExpiredPoint() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayContentExpiredPoint(), "Testcase fail");
    }

    @Test(priority = 25)
    public void verifyCheckDisplayTotalOrderBox() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTotalOrderBox(), "Testcase fail");
    }

    @Test(priority = 26)
    public void verifyCheckDisplayTotalOrderTitle() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTotalOrderTitle(), "Testcase fail");
    }

    @Test(priority = 27)
    public void verifyCheckDisplayTotalOrderNumber() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTotalOrderNumber(), "Testcase fail");
    }

    @Test(priority = 28)
    public void verifyCheckDisplayTotalMoneySpentBox() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTotalMoneySpentBox(), "Testcase fail");
    }

    @Test(priority = 29)
    public void verifyCheckDisplayTotalMoneySpentTitle() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTotalMoneySpentTitle(), "Testcase fail");
    }

    @Test(priority = 30)
    public void verifyCheckDisplayTotalMoneySpent() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayTotalMoneySpent(), "Testcase fail");
    }

    @Test(priority = 31)
    public void verifyCheckDisplayMembershipOffersTitle() {
        Assert.assertTrue(loyaltyPointPage.CheckDisplayMembershipOffersTitle(), "Testcase fail");
    }

//    @Test(priority = 32)
//    public void verifyCheckDisplayMembershipBox2() {
//        Assert.assertTrue(loyaltyPointPage.CheckDisplayMembershipBox2(), "Testcase fail");
//    }
//
//    @Test(priority = 33)
//    public void verifyCheckDisplayDescriptionMembershipDetail0() {
//        Assert.assertTrue(loyaltyPointPage.CheckDisplayDescriptionMembershipDetail0(), "Testcase fail");
//    }


    @Test(priority = 36)
    public void verifyGetTextTitleRankWithApi() {
        originalData = loyaltyPointPage.getTextTitleRank();
        expectedData = loyaltyPointPage.checkCustomerLoyaltyPointDetailModel(KEY_NAME);
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 38)
    public void verifyNumberRemainPointTextWithApi() {
        originalData = loyaltyPointPage.getTextNumberRemainPointText();
        expectedData = loyaltyPointPage.checkNumbRemainPointNext();
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 39)
    public void verifyGetTextContentAvailablePoint() {
        originalData = loyaltyPointPage.getTextContentAvailablePoint();
        Assert.assertEquals(originalData, CONTENT_AVAILABLE_POINT, "Testcase fail");
    }


    @Test(priority = 41)
    public void verifyAvailablePointWithApi() {
        originalData = loyaltyPointPage.getTextAvailablePoint();
        expectedData = loyaltyPointPage.checkAvailablePoint();
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    //TODO: need to improve
    @Test(priority = 42)
    public void verifyGetTextContentExpiredPoint() {
        originalData = loyaltyPointPage.getTextContentExpiredPoint();
        expectedData = formatDateString();
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 43)
    public void verifyGetTextTotalOrderTitle() {
        originalData = loyaltyPointPage.getTextTotalOrderTitle();
        Assert.assertEquals(originalData, TOTAL_ORDER_TITLE, "Testcase fail");
    }


    @Test(priority = 45)
    public void verifyTotalOrderNumberWithApi() {
        originalData = loyaltyPointPage.getTextTotalOrderNumber();
        expectedData = loyaltyPointPage.checkTotalOrderNumber();
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 46)
    public void verifyGetTextTotalMoneySpentTitle() {
        originalData = loyaltyPointPage.getTextTotalMoneySpentTitle();
        Assert.assertEquals(originalData, TOTAL_MONEY_SPENT_TITLE, "Testcase fail");
    }


    @Test(priority = 48)
    public void verifyTotalMoneySpentWithApi() {
        originalData = loyaltyPointPage.getTextTotalMoneySpent();
        expectedData = loyaltyPointPage.checkTotalMoneySpent();
        Assert.assertEquals(originalData, expectedData, "Testcase fail");
    }

    @Test(priority = 49)
    public void verifyGetTextMembershipOffersTitle() {
        originalData = loyaltyPointPage.getTextMembershipOffersTitle();
        Assert.assertEquals(originalData, MEMBERSHIP_OFFERS_TITLE, "Testcase fail");
        loyaltyPointPage.clickElement(loyaltyPointPage.DescriptionMembershipDetail6);
        loyaltyPointPage.navigateScreen();
        loyaltyPointPage.clickElement(loyaltyPointPage.CloseMembership);
    }

//    @Test(priority = 50)
//    public void verifyGetTextTitleMembershipDetail2() {
//        originalData = loyaltyPointPage.getTextTitleMembershipDetail2();
//        Assert.assertEquals(originalData, TITLE_MEMBERSHIP_DETAIL_2, "Testcase fail");
//    }
}
