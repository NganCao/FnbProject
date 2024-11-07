package com.fnb.web.store.theme1.scenario_test.addUserLocation;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.addUserLocation.AddUserLocationDataTest;
import com.fnb.web.store.theme1.pages.addUserLocation.AddUserLocationPage;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsDataTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AddUserLocationScenarios extends BaseTest {
    private HomePage homePage;
    private AddUserLocationPage addUserLocationPage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private AddUserLocationDataTest addUserLocationDataTest;
    private ProductDetailsDataTest productDetailsDataTest;
    SoftAssert softAssert;
    Helper helper;

    @BeforeClass
    public void navigateToHomePage() {
        homePage = storePage().navigateToHomePage();
        softAssert = storePage().softAssert;
        helper = storePage().helper;
        addUserLocationPage = new AddUserLocationPage(getDriver());
    }

    @Test(priority = 1, testName = "Verify display of add location component on Homepage")
    public void FB9378() {
        storePage().navigateToHomePage();
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display");
    }

    @Test(priority = 2, testName = "Verify the height of location component")
    public void FB9380() {
        Assert.assertTrue(addUserLocationPage.checkHeightAddLocationComponent(addUserLocationDataTest.LOCATION_HEIGHT), "the height of location component is incorrect");
    }

    @Test(priority = 3, testName = "Verify the color of location component")
    public void FB9381() {
        Assert.assertTrue(addUserLocationPage.checkColorAddLocationComponent(addUserLocationDataTest.LOCATION_BACKGROUND), "the color of location component is incorrect. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 4, testName = "Verify display of the location icon")
    public void FB9382() {
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationIcon(), "Add location icon did not display");
    }

    @Test(priority = 5, testName = "Verify display of the location label")
    public void FB9384() {
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationLabel(), "Add location label did not display");
    }

    @Test(priority = 6, testName = "Verify the font-size of location label")
    public void FB9386() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeAddLocationLabel(addUserLocationDataTest.LOCATION_LABEL_FONTSIZE), "size of the location icon is incorrect. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 7, testName = "Verify the color of location label")
    public void FB9387() {
        Assert.assertTrue(addUserLocationPage.checkColorAddLocationLabel(addUserLocationDataTest.WHITE_COLOR), "The color of the location icon is incorrect. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 8, testName = "Verify display of add user location on Product list page")
    public void FB9395() {
        storePage().navigateToProductListTheme1();
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display on Product list");
    }

    @Test(priority = 9, testName = "Verify display of add user location on Product details page")
    public void FB9396() {
        storePage().navigateToHomePage();
        homePage.clickProductOnBestselling();
        helper.checkURL(productDetailsDataTest.URL);
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display on Product details");
    }

    @Test(priority = 10, testName = "Verify display of the Add location dialog after user click on Location label")
    public void FB9398() {
        storePage().navigateToHomePage();
        addUserLocationPage.clickSelectAddress();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryTab(), "Delivery tab did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfPickupTab(), "Pickup tab did not display");
        softAssert.assertAll();
    }

    @Test(priority = 11, testName = "Verify align of Add location dialog")
    public void FB9402() {
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

    @Test(priority = 12, testName = "Verify color of Delivery tab when ACTIVE")
    public void FB9405() {
        addUserLocationPage.clickDelivery();
        Assert.assertTrue(addUserLocationPage.checkColorOfDelivery(addUserLocationDataTest.ACTIVE_BG_COLOR, true), "Background color of Delivery display wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify color of Pickup tab when INACTIVE")
    public void FB9406() {
        Assert.assertTrue(addUserLocationPage.checkColorOfPickup(addUserLocationDataTest.INACTIVE_BG_COLOR, true), "Background color of Pickup display wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of elements on Delivery tab after clicking Delivery without login")
    public void FB9407() {
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
        Assert.assertTrue(addUserLocationPage.checkMatchKeySearchAddress(addUserLocationDataTest.ADDRESS_DELIVERY_CHECK_NUMBER), "the recommend item(s) are incorrect.");
    }

    @Test(priority = 12, testName = "Verify the number item will be displayed on recommend address list")
    public void FB9414() {
        Assert.assertTrue(addUserLocationPage.checkNumberOfAddressItem(addUserLocationDataTest.MAXNUMBER_ADDRESS_ITEMS), "the number item display wrong. Actual: " + addUserLocationPage.actualRS);
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
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkLabelAfterClickingOnPickupTab(true), "----- TC1: Check label after clicking pickup tab \n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfTypeButtonAfterSelectLocation(addUserLocationDataTest.PICKUP_TYPE, false, "", 0), "----- TC2: Check type button after clicking pickup tab \n" + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 13, testName = "Check pickup dialog without address")
    public void FB94212() {
        addUserLocationPage.clickSelectStoreBranch();
        Assert.assertTrue(addUserLocationPage.checkBranchWithoutAddress(), addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify color of Pickup tab when ACTIVE")
    public void FB9422() {
        helper.refreshPage();
        addUserLocationPage.clickSelectStoreBranch();
        try {
            Assert.assertTrue(addUserLocationPage.checkColorOfPickup(addUserLocationDataTest.ACTIVE_BG_COLOR, false), "Color of Pickup is wrong. Actual: " + addUserLocationPage.actualRS);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            addUserLocationPage.clickSelectStoreBranch();
            Assert.assertTrue(addUserLocationPage.checkColorOfPickup(addUserLocationDataTest.ACTIVE_BG_COLOR, false), "Color of Pickup is wrong. Actual: " + addUserLocationPage.actualRS);
        }
    }

    @Test(priority = 13, testName = "Verify color of Delivery tab when INACTIVE")
    public void FB9423() {
        Assert.assertTrue(addUserLocationPage.checkColorOfDelivery(addUserLocationDataTest.INACTIVE_BG_COLOR, false), "Color of Delivery is wrong. Actual: " + addUserLocationPage.actualRS);
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
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchChecked(), "Branch checked icon on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfSelectedDefaultBranchChecked(), "default branch checked was not automatically selected.\nActual: " + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 13, testName = "Verify color of default branch checked icon on pickup")
    public void FB94277() {
        Assert.assertTrue(addUserLocationPage.checkColorOfSelectedDefaultBranchChecked(addUserLocationDataTest.CHECKED_BRANCH_ICON), "default branch border checked displayed wrong.\nActual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify color of default branch icon on pickup")
    public void FB9429() {
        Assert.assertTrue(addUserLocationPage.checkColorOfSelectedDefaultBranchIcon(addUserLocationDataTest.CHECKED_BRANCH_ICON), "default branch border checked displayed wrong.\nActual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of default branch icon on pickup")
    public void FB9428() {
        Assert.assertTrue(addUserLocationPage.checkDisplayOfDefaultBranchIcon(), "default Branch icon list did not display");
    }

    @Test(priority = 13, testName = "Verify font-size of default branch icon on pickup")
    public void FB9430() {
        Assert.assertTrue(addUserLocationPage.checkSizeDefaultBranchIcon(addUserLocationDataTest.BRANCH_ICON_H, addUserLocationDataTest.BRANCH_ICON_W), "font-size of the default Branch icon list displayed wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-size of default branch address on pickup")
    public void FB94311() {
        Assert.assertTrue(addUserLocationPage.checkSizeOfDefaultBranchName(addUserLocationDataTest.BRANCH_NAME_SIZE), "font-size of the default Branch name list displayed wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-weight of default branch address on pickup")
    public void FB94312() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfDefaultBranchName(addUserLocationDataTest.BRANCH_NAME_WEIGHT), "font-weight of the default Branch name list displayed wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-size of default branch name on Pickup")
    public void FB94321() {
        Assert.assertTrue(addUserLocationPage.checkSizeOfDefaultBranchAddress(addUserLocationDataTest.BRANCH_ADDRESS_SIZE), "font-size of the default Branch name list displayed wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-weight of default branch address on Pickup")
    public void FB94322() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfDefaultBranchAddress(addUserLocationDataTest.BRANCH_ADDRESS_WEIGHT), "font-weight of the default Branch name list displayed wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of other branch item on pickup when isDefault is true")
    public void FB9434() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListItem(), "other Branch item on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListName(), "other Branch name list on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListIcon(), "other Branch icon list on pickup did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListDistance(true), "Other distance list on pickup is displaying");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBranchListAddress(), "other address list on pickup did not display");
        softAssert.assertAll();
    }

    @Test(priority = 13, testName = "Verify color of branch icon list on pickup")
    public void FB9435() {
        Assert.assertTrue(addUserLocationPage.checkColorAllBranchIcon(addUserLocationDataTest.CHECKED_BRANCH_ICON), "other branch border checked displayed wrong.\nActual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-size of icon on Other branch on pickup")
    public void FB9444() {
        Assert.assertTrue(addUserLocationPage.checkSizeOfOtherBranchIconList(addUserLocationDataTest.BRANCH_ICON_HEIGHT, addUserLocationDataTest.BRANCH_ICON_WIDTH), "font-size of icon on Other branch displayed wrong.\nActual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-size of branch address on Other branch on pickup")
    public void FB94451() {
        Assert.assertTrue(addUserLocationPage.checkFontSizeOfOtherBranchListName(addUserLocationDataTest.BRANCH_NAME_SIZE), "font-size of branch address displayed wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch address on Other branch on pickup")
    public void FB94452() {
        Assert.assertTrue(addUserLocationPage.checkFontWeightOfOtherBranchListAddress(addUserLocationDataTest.BRANCH_NAME_WEIGHT), "font-size of branch address displayed wrong. Actual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of the default branch on Pickup")
    public void FB94453() {
        Assert.assertTrue(addUserLocationPage.checkValueOfDefaultBranchWithoutAddressOnPickup(), addUserLocationPage.actualRS);
    }

    @Test(priority = 13, testName = "Verify language of the default label on Pickup when user don't change address yet")
    public void FB94457() {
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
    public void FB94454() {
        helper.refreshPage();
        Assert.assertFalse(addUserLocationPage.checkDisplayOfMyAddressList(), "My saved address is displaying without login");
    }

    @Test(priority = 14, testName = "Verify display of picked branch without login or address")
    public void FB94455() {
        softAssert = new SoftAssert();
        softAssert.assertFalse(addUserLocationPage.checkDisplayOfDeliveryFromLabel(), "picked branch is displaying without login or address");
        softAssert.assertFalse(addUserLocationPage.checkDisplayOfMoreBranchIcon(), "More branch arrow is displaying without login or address");
        softAssert.assertFalse(addUserLocationPage.checkDisplayOfStorePicked(), "Picked store section is displaying without login or address");
    }

    @Test(priority = 14, testName = "Verify default picked branch section after login successfully when isDefault is true")
    public void FB94456() {
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
    @Test(priority = 15, testName = "Verify locator label and type button when user select an recommend address")
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
        softAssert.assertTrue(addUserLocationPage.checkInvisibleOfDialog(), "Dialog still displays");
        softAssert.assertTrue(addUserLocationPage.checkLabelAfterSelectAddress(), "Address label displayed wrong. Actual: " + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddLocationTypeButton(), "Type button did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddLocationTypeLabel(), "Type label did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddLocationTypeIcon(), "Type icon did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfTypeButtonAfterSelectLocation(addUserLocationDataTest.DELIVERY_TYPE, false, addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfEnterFieldAfterSelected(), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfClearIcon(), "Clear address icon did not display");
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
        softAssert.assertTrue(addUserLocationPage.checkInvisibleOfDialog(), "Dialog still displays");
        softAssert.assertTrue(addUserLocationPage.checkLabelAfterSelectAddress(), "Address label displayed wrong. Actual: " + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddLocationTypeButton(), "Type button did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddLocationTypeLabel(), "Type label did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddLocationTypeIcon(), "Type icon did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfTypeButtonAfterSelectLocation(addUserLocationDataTest.DELIVERY_TYPE, false, address, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfEnterFieldAfterSelected(), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryFromLabel(), "Delivery from label displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMoreBranchIcon(), "More branch arrow did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfStorePicked(), "Picked store section did not display");
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickedBranchAfterEnterAddress(address, false), addUserLocationPage.actualRS);
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
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMyAddressList(), "Address list did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfMyAddressLabel(), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddressListIcon(), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayAddressListAddress(), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfAddressLabelAfterSelectMyAddress(addUserLocationDataTest.SELECT_INDEX_SAVED_ADDRESS_LIST), "Address label displayed wrong. Actual: " + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    //todo adding check enter address field, icon

    /**
     * check branch list on more branch delivery
     */
    @Test(priority = 19, testName = "Verify more branch section after clicking on more branch arrow")
    public void FB94203() {
        helper.refreshPage();
        String address = addUserLocationDataTest.ADDRESS_DELIVERY;
        addUserLocationPage.enterAddress(address, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        addUserLocationPage.clickSelectAddress();
        addUserLocationPage.clickMoreBranchArrow();
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranch(), "Delivery store section displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBackDeliveryArrow(), "Back arrow did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranchLabel(), "Select other store label did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBackDeliveryNearestBranchLabel(), "Delivery default store label not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfBackDeliveryOtherBranchLabel(), "Delivery other store label did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryInputField(), "Delivery input did not display");
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfOtherSelectedBranchInformation(false), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranchInformation(false), addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkValueOfDeliveryOtherBranch(address), addUserLocationPage.actualRS);
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

    @Test(priority = 21, testName = "Verify the picked branch section after selecting another branch on delivery")
    public void FB94205() {
        helper.refreshPage();
        addUserLocationPage.clickSelectAddress();
        addUserLocationPage.clickMoreBranchArrow();
        addUserLocationPage.getOldBranchListPickup(true);
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfDeliveryOtherBranch(), "--------- TC1:\nDelivery store section displayed wrong.");
        softAssert.assertTrue(addUserLocationPage.checkPickedStoreAfterChangeStoreFromDelivery(addUserLocationDataTest.SELECT_INDEX_BRANCH_LIST, false), "--------- TC2:\nCheck display of picked store after selecting another branch\n" + addUserLocationPage.actualRS);
        //check branch list on delivery
        softAssert.assertTrue(addUserLocationPage.checkValueOfNearestDeliveryBranchAfterChangeBranch(), "--------- TC3:\nCheck branch information after change branch on delivery\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkCheckedIconAfterSelectBranchPickup(addUserLocationDataTest.CHECK_CHECKED_BRANCH, true), "TC 4\n" + addUserLocationPage.actualRS);
        //check selected on pickup
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickupAfterChangeBranch(), "TC 5\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkCheckedIconAfterSelectBranchPickup(addUserLocationDataTest.CHECK_CHECKED_BRANCH, false), "TC 6\n" + addUserLocationPage.actualRS);
        //branch list won't change - for theme 1
        softAssert.assertTrue(addUserLocationPage.checkBranchListAfterSelectBranch(false), "--------- TC 7\nCheck order of branch list after changing another branch" + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 22, testName = "Verify when user select an branch on Pickup")
    public void FB94391() {
        helper.refreshPage();
        addUserLocationPage.clickSelectStoreBranch();
        addUserLocationPage.getOldBranchListPickup(false);
        int addressIndex = addUserLocationDataTest.SELECT_INDEX_BRANCH_LIST;
        softAssert = new SoftAssert();
        softAssert.assertTrue(addUserLocationPage.checkDisplayAfterSelectBranch(addressIndex), "--------- TC 1\nSelect branch button displayed wrong.\nActual: " + addUserLocationPage.actualRS + "\nExpected: " + addUserLocationPage.expectedRS);
        softAssert.assertTrue(addUserLocationPage.checkDisplayOfTypeButtonAfterSelectLocation(addUserLocationDataTest.PICKUP_TYPE, false, "", addressIndex), "--------- TC 2\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkBranchListAfterSelectBranch(false), "--------- TC 3\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkCheckedIconAfterSelectBranchPickup(addUserLocationDataTest.CHECK_CHECKED_BRANCH, false), "--------- TC 4\n" + addUserLocationPage.actualRS);
        //check branch list on DELIVERY
        softAssert.assertTrue(addUserLocationPage.checkValueOfNearestDeliveryBranchAfterChangeBranch(), "--------- TC5:\nCheck branch information after change branch on delivery\n" + addUserLocationPage.actualRS);
        softAssert.assertTrue(addUserLocationPage.checkCheckedIconAfterSelectBranchPickup(addUserLocationDataTest.CHECK_CHECKED_BRANCH, true), "--------- TC 6\n" + addUserLocationPage.actualRS);
        addUserLocationPage.clickBackDeliveryArrow();
        softAssert.assertTrue(addUserLocationPage.checkValueOfPickedStore(addUserLocationPage.expectedBranch, false), "--------- TC7\n" + addUserLocationPage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 23, testName = "Verify color of checked branch icon when user select an branch")
    public void FB94394() {
        Assert.assertTrue(addUserLocationPage.checkColorCheckedIconAfterSelectBranch(false, addUserLocationDataTest.CHECKED_BRANCH_ICON), "Color of checked icon displayed wrong.\nActual: " + addUserLocationPage.actualRS);
    }

    @Test(priority = 24, testName = "Verify display of add user location on Checkout page")
    public void FB9397() {
        helper.refreshPage();
        storePage().navigateToCheckoutTheme1();
        Assert.assertTrue(addUserLocationPage.checkDisplayAddLocationComponent(), "Add location component did not display on Checkout");
    }

    @Test(priority = 25, testName = "Verify language of add user location on when logged in")
    public void FB9999() {
        storePage().navigateToHomePage();
        try {
            addUserLocationPage.clickSelectAddress();
            addUserLocationPage.clickClearIcon();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.refreshPage();
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

    //Check language no login
    @Test(priority = 28, testName = "Verify language of add user location on without login")
    public void FB2() {
        storePage().navigateToHomePage();
        addUserLocationPage.enterAddress(addUserLocationDataTest.ADDRESS_DELIVERY1, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        helper.refreshPage();
        Assert.assertTrue(addUserLocationPage.checkLanguageOfAddUserLocation(true, false, false), addUserLocationPage.actualRS);
    }
}
