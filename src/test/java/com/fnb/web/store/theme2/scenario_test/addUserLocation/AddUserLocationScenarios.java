package com.fnb.web.store.theme2.scenario_test.addUserLocation;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme2.pages.addUserLocation.AddUserLocationDataTest;
import com.fnb.web.store.theme2.pages.addUserLocation.AddUserLocationPage;
import com.fnb.web.store.theme2.pages.home.HomeDataTest;
import com.fnb.web.store.theme2.pages.home.HomePage;
import com.fnb.web.store.theme2.pages.home.TodayMenuPage;
import com.fnb.web.store.theme2.pages.login.DataTest;
import com.fnb.web.store.theme2.pages.product_details.ProductDetailsDataTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddUserLocationScenarios extends BaseTest {
    private HomePage homePage;
    private AddUserLocationPage addUserLocationPage;
    private TodayMenuPage todayMenuPage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private AddUserLocationDataTest addUserLocationDataTest;
    private ProductDetailsDataTest productDetailsDataTest;
    SoftAssert softAssert;
    Helper helper;

    @BeforeClass
    public void navigateToHomePage() {
        homePage = storePage().navigateToHomePageTheme2();
        softAssert = storePage().softAssert;
        helper = storePage().helper;
        addUserLocationPage = new AddUserLocationPage(getDriver());
        todayMenuPage = new TodayMenuPage(getDriver());
    }

    @Test(priority = 1, testName = "Verify display of add location component on Homepage")
    public void FB9378() {
        storePage().navigateToHomePage();
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display");
    }

//    //TODO enhance if design is correct
//    @Test(priority = 2, testName = "Verify the height of location component")
//    public void FB9380() {
//        Assert.assertTrue(addUserLocationPage.checkHeightAddLocationComponent(addUserLocationDataTest.LOCATION_HEIGHT), "the height of location component is incorrect. ACtual: " + addUserLocationPage.actualRS);
//    }
//
//    //TODO compare with customize color
//    @Test(priority = 3, testName = "Verify the color of location component")
//    public void FB9381() {
//        Assert.assertTrue(addUserLocationPage.checkColorAddLocationComponent(addUserLocationDataTest.LOCATION_BACKGROUND), "the color of location component is incorrect. Actual: " + addUserLocationPage.actualRS);
//    }

    @Test(priority = 4, testName = "Verify display of the location icon")
    public void FB9382() {
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationIcon(), "Add location icon did not display");
    }

    @Test(priority = 5, testName = "Verify display of the location label")
    public void FB9384() {
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationLabel(), "Add location label did not display");
    }

    @Test(priority = 6, testName = "Verify the font-size of location label")
    public void FB93861() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeAddLocationLabel(addUserLocationDataTest.LOCATION_LABEL_FONTSIZE), "size of the location icon is incorrect. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.LOCATION_LABEL_FONTSIZE);
    }

    @Test(priority = 6, testName = "Verify the font-size of location label description")
    public void FB93862() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeAddLocationLabelDes(addUserLocationDataTest.LOCATION_LABEL_DES_FONTSIZE), "size of the location label description is incorrect. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.LOCATION_LABEL_DES_FONTSIZE);
    }

//    //TODO compare with customize color
//    @Test(priority = 7, testName = "Verify the color of location label")
//    public void FB9387() {
//        Assert.assertTrue(addUserLocationPage.checkColorAddLocationLabel(addUserLocationDataTest.WHITE_COLOR), "The color of the location icon is incorrect. Actual: " + addUserLocationPage.actualRS);
//    }

    @Test(priority = 8, testName = "Verify display of add user location on Product list page")
    public void FB9395() {
        storePage().navigateToProductListTheme2();
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display on Product list");
    }

    @Test(priority = 9, testName = "Verify display of add user location on Product details page")
    public void FB9396() {
        storePage().navigateToHomePage();
        try {
            todayMenuPage.clickTodayMenuCardWithIndex(0);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            todayMenuPage.clickTodayMenuCardWithIndex(0);
        }
        helper.checkURL(productDetailsDataTest.URL);
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display on Product details");
    }

    @Test(priority = 10, testName = "Verify display of the Add location popover")
    public void FB93981() {
        storePage().navigateToHomePage();
        helper.sleep(2);
        Assert.assertTrue(addUserLocationPage.checkDisplayOfLocationPopoverDetails(), addUserLocationPage.actualRS);
    }

    @Test(priority = 10, testName = "Verify display of the Add location popover - delivery content")
    public void FB93982() {
        helper.refreshPage();
        try {
            Assert.assertTrue(addUserLocationPage.checkDisplayDialogAfterClickContentPopover(true), addUserLocationPage.actualRS);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            Assert.assertTrue(addUserLocationPage.checkDisplayDialogAfterClickContentPopover(true), addUserLocationPage.actualRS);
        }
    }

    @Test(priority = 10, testName = "Verify display of the Add location popover - pickup content")
    public void FB93983() {
        helper.refreshPage();
        try {
            Assert.assertTrue(addUserLocationPage.checkDisplayDialogAfterClickContentPopover(false), addUserLocationPage.actualRS);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            Assert.assertTrue(addUserLocationPage.checkDisplayDialogAfterClickContentPopover(false), addUserLocationPage.actualRS);
        }
    }

    @Test(priority = 11, testName = "Verify align of Add location dialog")
    public void FB9402() {
        helper.refreshPage();
        addUserLocationPage.clickSelectAddress();
        Assert.assertTrue(addUserLocationPage.checkAlignOfAddLocationDialog(addUserLocationDataTest.LOCATION_DIALOG_ALIGN), "Add location dialog aligns wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of Delivery label")
    public void FB9403() {
        Assert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryLabel(), "Delivery label did not display");
    }

    @Test(priority = 12, testName = "Verify display of Pickup label")
    public void FB9404() {
        Assert.assertTrue(addUserLocationPage.checkDisplayOfPickupLabel(), "Pickup label did not display wrong");
    }

//    //Todo with mapping color
//    @Test(priority = 12, testName = "Verify color of Delivery tab when ACTIVE")
//    public void FB9405() {
//        addUserLocationPage.clickDelivery();
//        Assert.assertTrue(addUserLocationPage.checkColorOfDelivery(addUserLocationDataTest.ACTIVE_BG_COLOR, true), "Background color of Delivery display wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.ACTIVE_BG_COLOR);
//    }

    @Test(priority = 12, testName = "Verify color of Pickup tab when INACTIVE")
    public void FB9406() {
        Assert.assertTrue(addUserLocationPage.checkColorOfPickup(addUserLocationDataTest.INACTIVE_BG_COLOR), addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of elements on Delivery tab after clicking Delivery without login")
    public void FB9407() {
        helper.refreshPage();
        addUserLocationPage.clickSelectAddress();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryInput(), "Delivery address input did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryInputIcon(), "Delivery address icon did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfEnterAddressPlaceHolder(), "Delivery address placeholder did not display");
        softAssert.assertAll();
    }

    @Test(priority = 12, testName = "Verify display of elements on Delivery tab after clicking Delivery")
    public void FB9409() {
        addUserLocationPage.enterAddressLocation(addUserLocationDataTest.ADDRESS_DELIVERY);
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfClearIcon(), "Clear(x) icon did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddressList(), "Recommend address list did not display");
        softAssert.assertAll();
    }

    @Test(priority = 12, testName = "Verify the max-length of address input with over maximum limit character")
    public void FB94101() {
        Assert.assertTrue(addUserLocationPage.checkMaxLengthOfAddressInput(addUserLocationDataTest.LENGTH_ADDRESS_INPUT, addUserLocationDataTest.MAXLENGTH_ADDRESS_INPUT), "The max-lenght of address input. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify the max-length of address input")
    public void FB94102() {
        Assert.assertTrue(addUserLocationPage.checkMaxLengthOfAddressInput(addUserLocationDataTest.LENGTH_ADDRESS_INPUT_AGAIN, addUserLocationDataTest.MAXLENGTH_ADDRESS_INPUT), "The max-lenght of address input after entering again. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify when user clicks clear (x) icon")
    public void FB9411() {
        addUserLocationPage.enterAddressLocation(addUserLocationDataTest.ADDRESS_DELIVERY);
        Assert.assertTrue(addUserLocationPage.checkValueOfAddressAfterClear(), "Background color of Puckup display wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of recommend address item")
    public void FB9413() {
        addUserLocationPage.enterAddressLocation(addUserLocationDataTest.ADDRESS_DELIVERY_CHECK_NUMBER);
        Assert.assertTrue(addUserLocationPage.checkMatchKeySearchAddress(addUserLocationDataTest.ADDRESS_DELIVERY_CHECK_NUMBER), "the recommend item(s) are incorrect.\n" + addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify the number item will be displayed on recommend address list")
    public void FB9414() {
        Assert.assertTrue(addUserLocationPage.checkNumberOfAddressItem(addUserLocationDataTest.MAXNUMBER_ADDRESS_ITEMS), "the number item display wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.MAXNUMBER_ADDRESS_ITEMS);
    }

    @Test(priority = 12, testName = "Verify when user clicks out side of location dialog")
    public void FB9415() {
        Assert.assertTrue(addUserLocationPage.checkOutsideDialog(), "Location dialog still display");
    }

    @Test(priority = 13, testName = "Check locator label after changing to pickup dialog without address - default branch")
    public void FB94211() {
        addUserLocationPage.clickSelectAddress();
        addUserLocationPage.clickClearIcon();
        addUserLocationPage.clickPickup();
        Assert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryButtonAfterSelectLocation(addUserLocationDataTest.PICKUP_TYPE, true, false, "", 0), "2 " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Check pickup dialog without address")
    public void FB94212() {
        addUserLocationPage.clickSelectStoreBranch();
        Assert.assertTrue(addUserLocationPage.checkBranchWithoutAddress(), addUserLocationPage.actualRS);
    }

//    //TODO enhance code with customize theme
//    @Test(priority = 13, testName = "Verify color of Pickup tab when ACTIVE")
//    public void FB9422() {
//        helper.refreshPage();
//        addUserLocationPage.clickSelectStoreBranch();
//        try {
//            Assert.assertTrue(addUserLocationPage.checkColorOfPickup(addUserLocationDataTest.ACTIVE_BG_COLOR), "Color of Pickup is wrong. Actual: " + addUserLocationPage.actualRS);
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            helper.refreshPage();
//            addUserLocationPage.clickSelectStoreBranch();
//            Assert.assertTrue(addUserLocationPage.checkColorOfPickup(addUserLocationDataTest.ACTIVE_BG_COLOR), "Color of Pickup is wrong. Actual: " + addUserLocationPage.actualRS);
//        }
//    }

    @Test(priority = 13, testName = "Verify color of Delivery tab when INACTIVE")
    public void FB9423() {
        helper.refreshPage();
        addUserLocationPage.clickSelectStoreBranch();
        Assert.assertTrue(addUserLocationPage.checkColorOfDelivery(addUserLocationDataTest.INACTIVE_BG_COLOR), "Color of Delivery is wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of labels on Pickup tab")
    public void FB9424() {
        Assert.assertTrue(addUserLocationPage.checkDisplayOfElementOnPickup(), addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of branch list on pickup tab")
    public void FB9425() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfPickupBranchList(), "Branch list on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchItem(), "default Branch item on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchName(), "default Branch on pickup name list did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchDistance(), "Branch distance list on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchAddress(), "Branch address list on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchChecked(false), "Branch checked icon on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfSelectedDefaultBranchChecked(), "default branch checked was not automatically selected.\nActual: " + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

//    //TODO enhance with customized theme
//    @Test(priority = 13, testName = "Verify color of default branch checked icon on pickup")
//    public void FB94277() {
//        Assert.assertTrue(addUserLocationPage.checkColorOfSelectedDefaultBranchChecked(addUserLocationDataTest.CHECKED_BRANCH_ICON), "default branch border checked displayed wrong.\nActual: " + addUserLocationPage.actualRS);
//    }
//
//    //TODO enhance with customized theme
//    @Test(priority = 13, testName = "Verify color of default branch icon on pickup")
//    public void FB9429() {
//        Assert.assertTrue(addUserLocationPage.checkColorOfSelectedDefaultBranchIcon(addUserLocationDataTest.CHECKED_BRANCH_ICON), "default branch border checked displayed wrong.\nActual: " + addUserLocationPage.actualRS);
//    }

    @Test(priority = 13, testName = "Verify display of default branch icon on pickup")
    public void FB94281() {
        helper.refreshPage();
        addUserLocationPage.clickSelectStoreBranch();
        Assert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchIcon(), addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of the number in the default Branch icon on pickup")
    public void FB94282() {
        Assert.assertTrue(addUserLocationPage.checkDisplayOfDefaultNumberIcon(), "the number in the default Branch icon is displaying");
    }

    @Test(priority = 13, testName = "Verify font-size of default branch name on pickup")
    public void FB94311() {
        Assert.assertTrue(addUserLocationPage.checkSizeOfDefaultBranchName(addUserLocationDataTest.BRANCH_NAME_SIZE), "font-size of the default Branch name list displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_NAME_SIZE);
    }

    @Test(priority = 13, testName = "Verify font-weight of default branch name on pickup")
    public void FB94312() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfDefaultBranchName(addUserLocationDataTest.BRANCH_NAME_WEIGHT), "font-weight of the default Branch name list displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_NAME_WEIGHT);
    }

    @Test(priority = 13, testName = "Verify font-size of default branch address on Pickup")
    public void FB94321() {
        Assert.assertTrue(addUserLocationPage.checkSizeOfDefaultBranchAddress(addUserLocationDataTest.BRANCH_ADDRESS_SIZE), "font-size of the default Branch address list displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_ADDRESS_SIZE);
    }

    @Test(priority = 13, testName = "Verify font-weight of default branch address on Pickup")
    public void FB94322() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfDefaultBranchAddress(addUserLocationDataTest.BRANCH_ADDRESS_WEIGHT), "font-weight of the default Branch address list displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_ADDRESS_WEIGHT);
    }

    @Test(priority = 13, testName = "Verify font-size of default branch distance on Pickup")
    public void FB94323() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeOfDefaultBranchDistance(addUserLocationDataTest.BRANCH_DISTANCE_SIZE), "font-size of the default Branch distance list displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_DISTANCE_SIZE);
    }

    @Test(priority = 13, testName = "Verify font-weight of default branch distance on Pickup")
    public void FB94324() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfDefaultBranchDistance(addUserLocationDataTest.BRANCH_DISTANCE_WEIGHT), "font-weight of the default Branch distance list displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_DISTANCE_WEIGHT);
    }

    @Test(priority = 13, testName = "Verify display of other branch item on pickup")
    public void FB9434() {
        helper.refreshPage();
        addUserLocationPage.clickSelectStoreBranch();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListItem(), "-------- TC1 Check display of branch list\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListName(), "-------- TC2: Check display of branch name list\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListIcon(), "-------- TC3: Check display of branch icon list\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListDistance(true), "-------- TC4: Check display of branch distance list\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListAddress(), "-------- TC5: Check display of branch address list\n" + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

//   //todo enhance with customized theme
//    @Test(priority = 13, testName = "Verify color of branch icon list on pickup")
//    public void FB9435() {
//        Assert.assertTrue(addUserLocationPage.checkColorAllBranchIcon(addUserLocationDataTest.CHECKED_BRANCH_ICON), "other branch border checked displayed wrong.\nActual: " + addUserLocationPage.actualRS);
//    }

    @Test(priority = 13, testName = "Verify font-size of branch name on Other branch on pickup")
    public void FB94451() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeOfOtherBranchListName(addUserLocationDataTest.BRANCH_NAME_SIZE), "font-size of branch name displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_NAME_SIZE);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch name on Other branch on pickup")
    public void FB94452() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfOtherBranchListName(addUserLocationDataTest.BRANCH_NAME_WEIGHT), "font-size of branch name displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_NAME_WEIGHT);
    }

    @Test(priority = 13, testName = "Verify font-size of branch address on Other branch on pickup")
    public void FB94453() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeOfOtherBranchListAddress(addUserLocationDataTest.BRANCH_ADDRESS_SIZE), "font-size of branch address displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_ADDRESS_SIZE);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch address on Other branch on pickup")
    public void FB94454() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfOtherBranchListAddress(addUserLocationDataTest.BRANCH_ADDRESS_WEIGHT), "font-weight of branch address displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_ADDRESS_WEIGHT);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch distance on Other branch on pickup")
    public void FB94455() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfOtherBranchListDistance(addUserLocationDataTest.BRANCH_DISTANCE_WEIGHT), "font-weight of branch distance displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_DISTANCE_WEIGHT);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch distance on Other branch on pickup")
    public void FB94456() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeOfOtherBranchListDistance(addUserLocationDataTest.BRANCH_DISTANCE_SIZE), "font-size of branch distance displayed wrong. Actual: " + addUserLocationPage.actualRS + " Expected: " + addUserLocationDataTest.BRANCH_DISTANCE_SIZE);
    }

    @Test(priority = 13, testName = "Verify value of the default branch on Pickup")
    public void FB94457() {
        Assert.assertTrue(addUserLocationPage.checkValueOfDefaultBranchWithoutAddressOnPickup(), addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify language of the default label on Pickup when user don't change address yet")
    public void FB94458() {
        helper.refreshPage();
        try {
            Assert.assertTrue(addUserLocationPage.checkLanguageOfDefaultLabelPickup(), addUserLocationPage.actualRS);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            Assert.assertTrue(addUserLocationPage.checkLanguageOfDefaultLabelPickup(), addUserLocationPage.actualRS);
        }
    }

    //change to Delivery tab
    @Test(priority = 14, testName = "Verify display of my saved address list without login on Delivery")
    public void FB94459() {
        helper.refreshPage();
        Assert.assertFalse(addUserLocationPage.checkDisplayOfMyAddressList(), "My saved address is displaying without login");
    }

    @Test(priority = 14, testName = "Verify display of picked branch without login or address")
    public void FB94460() {
        softAssert = new SoftAssert();
        softAssert.assertFalse(addUserLocationPage.checkDisplayOfDeliveryFromLabel(), "picked branch is displaying without login or address");
        softAssert.assertFalse(addUserLocationPage.checkDisplayOfMoreBranchIcon(), "More branch arrow is displaying without login or address");
        softAssert.assertFalse(addUserLocationPage.checkDisplayOfStorePicked(), "Picked store section is displaying without login or address");
    }

    @Test(priority = 14, testName = "Verify default picked branch section after login successfully")
    public void FB94461() {
        helper.refreshPage();
        try {
            homePage.loginSuccessfully(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            homePage.loginSuccessfully(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD);
        }
        helper.refreshPage();
        addUserLocationPage.clickSelectAddress();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryFromLabel(), "Delivery from label displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMoreBranchIcon(), "More branch arrow did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfStorePicked(), "Picked store section did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfStorePickedInformation(true), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickedBranchWithoutAddress(true), addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    /**
     * Check values after entering address
     */
    @Test(priority = 15, testName = "Verify locator label and type button when user select an recommend address", description = "No login")
    public void FB94161() {
        helper.refreshPage();
        homePage.checkAfterLogout();
        helper.refreshPage();
        try {
            addUserLocationPage.enterAddress(addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception e) {
            Log.error(e.getMessage());
            helper.refreshPage();
            addUserLocationPage.enterAddress(addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkInvisibleOfDialog(), "------------- TC1: Dialog still displays");
        //check popover
        softAssert.assertTrue(addUserLocationPage.checkLabelAfterSelectAddress(true, addUserLocationPage.expectedAddress), "--------- TC2:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfPopoverAfterChangeAddress(true, addUserLocationPage.expectedAddress), "--------- TC3:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfEnterFieldAfterSelected(addUserLocationPage.expectedAddress), "--------- TC4:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfClearIcon(), "--------- TC5: Clear address icon did not display");
        softAssert.assertAll();
    }

    @Test(priority = 16, testName = "Verify picked branch section after selecting an recommend address")
    public void FB94162() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryFromLabel(), "Delivery from label displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMoreBranchIcon(), "More branch arrow did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfStorePicked(), "Picked store section did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfStorePickedInformation(false), addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 17, testName = "Verify picked branch section after selecting an other address")
    public void FB94163() {
        helper.refreshPage();
        String address = addUserLocationDataTest.ADDRESS_DELIVERY1;
        addUserLocationPage.enterAddress(address, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkInvisibleOfDialog(), "--------- TC1: Dialog still displays");
        softAssert.assertTrue(addUserLocationPage.checkLabelAfterSelectAddress(true, addUserLocationPage.expectedAddress), "--------- TC2:\n" + "Address label displayed wrong." + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfPopoverAfterChangeAddress(true, addUserLocationPage.expectedAddress), "--------- TC3:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfEnterFieldAfterSelected(addUserLocationPage.expectedAddress), "--------- TC4:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryFromLabel(), "--------- TC5: Delivery from label displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMoreBranchIcon(), "--------- TC6: More branch arrow did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfStorePicked(), "--------- TC7: Picked store section did not display");
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickedBranchAfterEnterAddress(address, false), "--------- TC8:\n" + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 18, testName = "Verify when user logged in and has saved address list")
    public void FB94172() {
        helper.refreshPage();
        addUserLocationPage.clickSelectAddress();
        addUserLocationPage.clickClearIcon();
        addUserLocationPage.checkOutsideDialog();
        homePage.loginSuccessfully(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD);
        storePage().navigateToHomePage();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMyAddressList(), "--------- TC1: Address list did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMyAddressLabel(), "--------- TC2:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddressListIcon(), "--------- TC3:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddressListName(), "--------- TC4:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddressListAddress(), "--------- TC5:\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfAddressLabelAfterSelectMyAddress(addUserLocationDataTest.SELECT_INDEX_SAVED_ADDRESS_LIST), "--------- TC6: Address label displayed wrong. Actual: " + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    /**
     * check branch list on more branch delivery
     */
    @Test(priority = 19, testName = "Verify more branch section after clicking on more branch arrow")
    public void FB94203() {
        helper.refreshPage();
        String address = addUserLocationDataTest.ADDRESS_DELIVERY;
        try {
            addUserLocationPage.enterAddress(address, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            addUserLocationPage.enterAddress(address, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        }
        addUserLocationPage.clickSelectAddress();
        addUserLocationPage.clickMoreBranchArrow();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranch(), "-------- TC1\n" + "Delivery store section displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBackDeliveryArrow(), "-------- TC2\n" + "Back arrow did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranchLabel(), "-------- TC3\n" + "Select other store label did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBackDeliveryNearestBranchLabel(), "-------- TC4\n" + "Delivery default store label not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBackDeliveryOtherBranchLabel(), "-------- TC5\n" + "Delivery other store label did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfOtherBranchDefaultInformation(false), "-------- TC6: Check information of default branch\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranchInformation(false), "-------- TC7: Check information of other branches\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfDeliveryOtherBranch(address), "-------- TC8: Check value of all branches\n" + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 20, testName = "Verify more branch section after clicking on back delivery arrow")
    public void FB94204() {
        addUserLocationPage.clickBackDeliveryArrow();
        softAssert = new SoftAssert();
        softAssert.assertFalse(addUserLocationPage.checkDisplayOfDeliveryOtherBranch(), "Other on delivery still display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryFromLabel(), "\"Delivery from label displayed wrong.");
        softAssert.assertAll();
    }

    @Test(priority = 20, testName = "Verify branch list and distance list on Pickup after entering address on delivery")
    public void FB94206() {
        helper.refreshPage();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkBranchAfterEnterAddress(false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListDistance(false), addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    /**
     * Check branch information after changing to another branch on Delivery
     */
    @Test(priority = 21, testName = "Verify the picked branch section after selecting another branch on delivery")
    public void FB94205() {
        helper.refreshPage();
        addUserLocationPage.clickSelectAddress();
        addUserLocationPage.clickMoreBranchArrow();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranch(), "--------- TC1:\n" + "Delivery store section displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkPickedStoreAfterChangeStore(addUserLocationDataTest.SELECT_INDEX_BRANCH_LIST, false), "--------- TC2:\n" + "Check branch information on picked stored" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfNearestDeliveryBranchAfterChangeBranch(), "--------- TC3:\nCheck branch information after change branch on delivery\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchChecked(true), "--------- TC4:\nBranch checked icon on delivery\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickupAfterChangeBranch(), "--------- TC5:\nCheck branch information after change branch on pickup\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchChecked(false), "--------- TC6:\nBranch checked icon on pickup\n" + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    /**
     * Check the nearest branch after changing another branch on Pickup panel
     */
    @Test(priority = 22, testName = "Verify when user select another branch on Pickup")
    public void FB94391() {
        helper.refreshPage();
        addUserLocationPage.clickSelectStoreBranch();
        addUserLocationPage.getOldBranchListPickup(false);
        int addressIndex = addUserLocationDataTest.SELECT_INDEX_BRANCH_LIST;
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayAfterSelectBranch(addressIndex), "TC 1\nSelect branch button displayed wrong.\nActual: " + addUserLocationPage.actualRS + "\nExpected: " + addUserLocationPage.expectedAddress);
        softAssert.assertTrue(addUserLocationPage.checkLabelAfterSelectAddress(false, addUserLocationPage.expectedAddress), "--------- TC2:\n" + "Address label displayed wrong. Actual: " + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfPopoverAfterChangeAddress(false, ""), "--------- TC3:\nCheck popover information after changing address" + addUserLocationPage.actualRS);
        addUserLocationPage.clickDeliveryPopover();
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickedStore(addUserLocationPage.expectedBranch, false), "--------- TC4:\n" + "Check branch information on picked stored" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfNearestDeliveryBranchAfterChangeBranch(), "--------- TC5:\nCheck branch information after change branch on delivery\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchChecked(true), "--------- TC6:\nBranch checked icon on delivery\n" + addUserLocationPage.actualRS);
        addUserLocationPage.checkBranchListAfterSelectBranch(true);
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickupAfterChangeBranch(), "--------- TC7:\nCheck branch information after change branch on pickup\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchChecked(false), "--------- TC8:\nBranch checked icon on pickup\n" + addUserLocationPage.actualRS);
        addUserLocationPage.checkBranchListAfterSelectBranch(false);
        softAssert.assertAll();
    }

//    //TODO enhance with customized theme
//    @Test(priority = 23, testName = "Verify color of checked branch icon when user select an branch")
//    public void FB94394() {
//        Assert.assertTrue(addUserLocationPage.checkColorCheckedIconAfterSelectBranch(false, addUserLocationDataTest.CHECKED_BRANCH_ICON), "Color of checked icon displayed wrong.\nActual: " + addUserLocationPage.actualRS);
//    }

    @Test(priority = 24, testName = "Verify display of add user location on Checkout page")
    public void FB9397() {
        helper.refreshPage();
        storePage().navigateToCheckoutTheme2();
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display on Checkout");
    }

    @Test(priority = 25, testName = "Verify language of add user location on when logged in")
    public void FB1() {
        storePage().navigateToHomePage();
        Assert.assertTrue(addUserLocationPage.checkLanguageOfAddUserLocation(true, true, false), addUserLocationPage.actualRS);
    }

    @Test(priority = 26, testName = "Verify value of add user location after clicking saved address")
    public void clickSavedAddress() {
        storePage().navigateToHomePage();
        Assert.assertTrue(addUserLocationPage.checkValueAfterSelectSavedAddress(), addUserLocationPage.actualRS);
    }

    @Test(priority = 27, testName = "Logout")
    public void logOut() {
        helper.refreshPage();
        homePage.checkAfterLogout();
    }

    @Test(priority = 28, testName = "Verify language of add user location on without login")
    public void FB2() {
        storePage().navigateToHomePage();
        addUserLocationPage.enterAddress(addUserLocationDataTest.ADDRESS_DELIVERY1, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        helper.refreshPage();
        Assert.assertTrue(addUserLocationPage.checkLanguageOfAddUserLocation(true, false, false), addUserLocationPage.actualRS);
    }
}
