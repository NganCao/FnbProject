package com.fnb.web.store.theme1.pages.addUserLocation;

import com.fnb.utils.api.storeweb.pos.helpers.APIPosService;
import com.fnb.utils.api.storeweb.pos.helpers.JsonAPIPosReader.*;
import com.fnb.utils.api.storeweb.pos.helpers.APIPosService.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.HelperDataFaker;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.login.DataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class AddUserLocationPage extends Setup {
    private WebDriver driver;
    private Helper helper;
    private APIPosService helpersAPIPos;
    private WebDriverWait wait;
    private Actions actions;
    private HomeDataTest homeDataTest;
    private AddUserLocationDataTest addUserLocationDataTest;
    private DataTest loginDataTest;
    public String actualRS = "";
    private String actualCheckLanguage = "";
    public String expectedRS = "";
    private HelperDataFaker faker;
    private JsonReader jsonReader;
    private List<Branch> oldBranchList;
    public Branch expectedBranch;
    //------------------ Element locator
    //summary xpath
    private String deliveryPanelXP = "//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-panel-1\")]";
    private String pickupPanelXP = "//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-panel-4\")]";
    private By deliveryAddressSelector = By.id("deliveryAddressSelector");
    private By locatorIcon = By.xpath("//div[@id=\"deliveryAddressSelector\"]//*[name()='svg']");
    private By locatorLabel = By.xpath("//div[@id=\"deliveryAddressSelector\"]//div[contains(@class,\"receiver-address-select\")]/span");
    private By locatorBackground = By.xpath("//div[@id=\"deliveryAddressSelector\"]/div");
    private By locatorDialog = By.xpath("//div[@role=\"dialog\" and contains(@class,\"modal-delivery-address-selector\")]");
    private By selectTypeButton = By.xpath("//div[contains(@class,\"store-branch-address-select-button\")]");
    private By selectedTypeLabel = By.xpath("//div[contains(@class,\"store-branch-address-select-button\")]/span");
    private By selectedTypeIcon = By.xpath("//div[contains(@class,\"store-branch-address-select-button\")]/*[name()='svg']");
    //dialog
    private By deliveryTab = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-tab-1\")]");
    private By deliveryTxt = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-tab-1\")]//span");
    private By deliveryIcon = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-tab-4\")]//*[name()='svg']");
    private By pickupTab = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-tab-4\")]");
    private By pickupTxt = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-tab-4\")]//span");
    private By pickupIcon = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-tab-4\")]//*[name()='svg']");
    public By enterAddressField = By.xpath("//span[contains(@class,\"delivery-select-address-input\")]/input");
    private By deliveryInputIcon = By.xpath("//span[contains(@class,\"delivery-select-address-input\")]/input/preceding-sibling::span/*[name()=\"svg\"]");
    private By clearIcon = By.xpath("//span[contains(@class,\"delivery-select-address-input\")]//span[contains(@class,\"ant-input-clear-icon\")]//*[name()=\"svg\"]");
    //entered address
    private By addressListParent = By.xpath(deliveryPanelXP + "//div[contains(@class,\"delivery-address-popover\")]");
    private By addressListItems = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-popover-item\")]");
    //my address
    private By myAddressContainer = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-saved\")]"); //contains "show" when my address displays
    private By myAddressLabel = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-saved\")]/span");
    private By myAddressList = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-list-container\")]");
    private By myAddressItems = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-list-container\")]/div");
    private By myAddressTxtList = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-list-container\")]//span");
    private By myAddressIconList = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-list-container\")]//*[name()=\"svg\"]");
    //selected branch
    private By deliveryFromLabel = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-saved\")]/div/span");
    private By moreBranchArrowIcon = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-saved\")]/div[@class=\"header-row\"]/*[name()=\"svg\"]");
    private By storePicked = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-saved\")]/div[@class=\"address-store-picked\"]");
    private String storePickedIcon = "./*[name()=\"svg\"]"; //finds from storePicked
    private String storePickedName = ".//div[@class=\"store-title\"]/span"; //finds from storePicked
    private String storePickedCheckedIcon = ".//div[@class=\"store-infomation\"]//*[name()=\"svg\"]"; //finds from storePicked
    private String storePickedAddress = ".//div[@class=\"store-content\"]/span[1]"; //finds from storePicked
    private String storePickedDistance = ".//span[@class=\"distance\"]"; //finds from storePicked
    //delivery panel
    private By deliveryPanel = By.xpath(deliveryPanelXP);
    //select the other store branch
    private String deliveryOtherBranchXP = deliveryPanelXP + "//div[contains(@class,\"select-store-branch\")]";
    private By deliveryOtherStoreBranch = By.xpath(deliveryOtherBranchXP); //contains "show" when select other branch displays after clicking more branch arrow
    private By deliveryOtherBranchLabel = By.xpath(deliveryOtherBranchXP + "/div/span");
    private By deliveryOtherBranchBackArrow = By.xpath(deliveryOtherBranchXP + "/div/*[name()=\"svg\"]");
    private By deliveryOtherSelectBranchNearestLabel = By.xpath("(" + deliveryPanelXP + "//div[@class=\"text-branch-info\"])[1]");
    private By deliveryOtherStoreBranchItems = By.xpath(deliveryPanelXP + "//div[contains(@class,\"store-branch-address-selector\")]//label");
    //xpath of selected branch
    private By deliveryOtherSelectedBranch = By.xpath(deliveryPanelXP + "//label[contains(@class,\"ant-radio-wrapper-checked\")]");
    private By deliveryOtherSelectBranchOtherLabel = By.xpath(deliveryPanelXP + "//div[contains(@class,\"store-branch-address-selector\")]//label[not(contains(@class,\"ant-radio-wrapper-checked\"))][1]//div[@class=\"text-branch-info\"]");
    private By deliveryOtherNoActiveItems = By.xpath(deliveryPanelXP + "//div[contains(@class,\"store-branch-address-selector\")]//label[not(contains(@class,\"ant-radio-wrapper-checked\"))]");
    //pickup panel
    private By pickupPanel = By.xpath(pickupPanelXP);
    private By pickupDefaultLabel = By.xpath("(" + pickupPanelXP + "//label/span/div[@class=\"text-branch-info\"])[1]");
    private By pickupOtherLabel = By.xpath("(" + pickupPanelXP + "//label/span/div[@class=\"text-branch-info\"])[2]");
    private By pickupDefaultBranch = By.xpath("(" + pickupPanelXP + "//label/span[2])[1]");
    private By pickupSelectedBranch = By.xpath(pickupPanelXP + "//label[contains(@class,\"ant-radio-wrapper-checked\")]");
    private By pickupBranchItems = By.xpath(pickupPanelXP + "//label/span[2]");
    private By pickupBranchNoActiveItems = By.xpath(pickupPanelXP + "//label[not(contains(@class,\"ant-radio-wrapper-checked\"))]/span[2]");
    //store branch information - delivery and pickup
    private String branchIcon = ".//div[@class=\"address-icon\"]//*[name()=\"svg\"]";
    private String branchName = ".//p[@class=\"address-label\"]";
    private String branchAddress = ".//p[@class=\"address\"]/span[@class=\"location\"]";
    private String branchDistance = ".//p[@class=\"address\"]/span[@class=\"distance\"]";
    private String branchCheckedIconByItem = ".//div[@class=\"selected\"]//*[name()=\"svg\"]"; //finds from item
    private String branchCheckedIcon = ".//label[contains(@class,\"ant-radio-wrapper-checked\")]//div[@class=\"selected\"]//*[name()=\"svg\"]"; //finds from panel
    private By pickupBranchDistanceList = By.xpath(pickupPanelXP + "//label/span[2]//p[@class=\"address\"]/span[@class=\"distance\"]");
    private By pickupBranchNameList = By.xpath(pickupPanelXP + "//label/span[2]//p[@class=\"address-label\"]");

    public AddUserLocationPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        this.driver = driver;
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
    }

    //check display component
    public Boolean checkDisplayAddLocationComponent() {
        return helper.checkDisplayElement(deliveryAddressSelector);
    }

    public Boolean checkDisplayAddLocationIcon() {
        return helper.checkDisplayElement(locatorIcon);
    }

    public Boolean checkDisplayAddLocationLabel() {
        return helper.checkDisplayElement(locatorLabel);
    }

    public Boolean checkDisplayAddLocationTypeButton() {
        return helper.checkDisplayElement(selectTypeButton);
    }

    public Boolean checkDisplayAddLocationTypeLabel() {
        return helper.checkDisplayElement(selectedTypeLabel);
    }

    public Boolean checkDisplayAddLocationTypeIcon() {
        return helper.checkDisplayElement(selectedTypeIcon);
    }

    //Check value

    /**
     * After entering address on delivery or selecting branch, change to address / branch name
     *
     * @param addressIndex
     * @return
     */
    public Boolean checkDisplayOfAddressLabelAfterSelectMyAddress(int addressIndex) {
        expectedRS = helper.selectOptionDropdown(myAddressTxtList, addressIndex);
        helper.visibleOfLocated(locatorLabel);
        return expectedRS.contains(driver.findElement(locatorLabel).getText().trim());
    }

    public Boolean checkValueOfEnterFieldAfterSelected() {
        String label = helper.getText(locatorLabel);
        Boolean rs = clickSelectedTypeButton();
        if (rs == false) clickLocationLabel();
        checkDisplayOfDeliveryInput();
        String inputTxt = helper.getAttribute(enterAddressField, "value");
        actualRS = "Wrong address. Actual: \"" + inputTxt + "\"\nExpected: \"" + label + "\"";
        return inputTxt.equalsIgnoreCase(label);
    }

    private Boolean checkBranchPickupWithIndex(By branchItemXP, int index) {
        clickSelectStoreBranch();
        helper.visibleOfLocated(pickupPanel);
        helper.waitForVisibleAllElements(branchItemXP);
        String oldBranch = driver.findElements(branchItemXP).get(index).findElement(By.xpath(branchName)).getText();
        selectAnotherStoreByIndex(branchItemXP, index);
        helper.visibleOfLocated(locatorLabel);
        return oldBranch.equalsIgnoreCase(expectedRS);
    }

    private Boolean checkOtherBranchPickupWithIndex(By branchItemXP, int index) {
        clickSelectStoreBranch();
        helper.visibleOfLocated(deliveryOtherStoreBranch);
        helper.waitForVisibleAllElements(deliveryOtherNoActiveItems);
        String oldBranch = driver.findElements(deliveryOtherNoActiveItems).get(index).findElement(By.xpath(branchName)).getText();
        selectAnotherStoreByIndex(branchItemXP, index);
        helper.visibleOfLocated(locatorLabel);
        return oldBranch.equalsIgnoreCase(expectedRS);
    }

    /**
     * @param type               (delivery, pickup get from addUserLocationDataTest)
     * @param enterAddressBranch
     * @param address
     * @param index
     * @return
     */
    public Boolean checkDisplayOfTypeButtonAfterSelectLocation(String type, Boolean enterAddressBranch, String address, int index) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (type.equalsIgnoreCase("delivery")) {
            if (enterAddressBranch) {
                clickDelivery();
                onlyFillDeliveryAddress(address, index);
            }
            flag.add(checkLabelAfterSelectAddress());
            helper.waitForTextToBe(driver.findElement(selectedTypeLabel), getTypeLabelFollowingLanguage(true));
            String actualType = driver.findElement(selectedTypeLabel).getText();
            String expectedType = getTypeLabelFollowingLanguage(true);
            flag.add(actualType.equals(expectedType));
            actualRS = actualRS + "\nWrong text on selected type button. Actual: " + actualType + " Expected: " + expectedType;
        } else if (type.equalsIgnoreCase("pickup")) {
            try {
                clickPickup();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                clickSelectStoreBranch();
            }
            if (enterAddressBranch) {
                //SELECT other BRANCH
                checkBranchPickupWithIndex(deliveryOtherNoActiveItems, index);
                //get name to verify
                flag.add(checkLabelAfterSelectAddress());
            }
            helper.visibleOfLocated(selectedTypeLabel);
            String actualLabel = helper.getText(selectedTypeLabel);
            String expectedLabel = getTypeLabelFollowingLanguage(false);
            flag.add(actualLabel.equals(expectedLabel));
            actualRS = actualRS + "\nWrong text on selected type button. Actual: \"" + actualLabel + "\" Expected: \"" + expectedLabel + "\"\n";
        } else {
            //hide type button
            flag.add(!checkDisplayAddLocationTypeButton());
            actualRS = actualRS + "Type button still display";
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayAfterSelectBranch(int index) {
        List<WebElement> list = helper.getElementList(pickupBranchNoActiveItems);
        String name = list.get(index).findElement(By.xpath(branchName)).getText().trim();
        expectedRS = name;
        WebElement branch = list.get(index);
        Branch selectedBranch = new Branch();
        selectedBranch = setBranchInformation(branch);
        expectedBranch = selectedBranch;
        helper.clickBtn(list.get(index));
        helper.visibleOfLocated(locatorLabel);
        actualRS = driver.findElement(locatorLabel).getText().trim();
        if (name.equalsIgnoreCase(actualRS)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkValueOfAddressAfterClear() {
        clickClearIcon();
        helper.waitInvisibleByLocated(clearIcon);
        actualRS = helper.getAttribute(enterAddressField, "value");
        if (actualRS.equals("")) {
            return true;
        } else return false;
    }

    public Boolean checkNumberOfAddressItem(int expectedSize) {
        helper.waitForPresence(addressListParent);
        int listSize = helper.getElementList(addressListItems).size();
        if (listSize == expectedSize) {
            return true;
        } else {
            actualRS = "Actual: " + String.valueOf(listSize) + " Expected: " + expectedSize;
            return false;
        }
    }

    public Boolean checkMatchKeySearchAddress(String address) {
        helper.waitForJStoLoad();
        List<WebElement> addressBoxList = helper.getElementList(addressListParent);
        List<Boolean> flag = new ArrayList<>();
        address = helper.removeAccent(address.toLowerCase());
        List<String> keySearch = helper.stringSplit(address, " ");
        String addressTemp;
        for (int i = 0; i < addressBoxList.size(); i++) {
            addressTemp = helper.removeAccent(addressBoxList.get(i).getText().toLowerCase());
            for (int y = 0; y < keySearch.size(); y++) {
                if (addressTemp.contains(keySearch.get(y))) {
                    flag.add(true);
                } else {
                    System.out.println("Expected: " + keySearch.get(y) + "\nActual: " + addressTemp);
                    Log.info("Expected: " + keySearch.get(y) + "\nActual: " + addressTemp);
                    flag.add(false);
                }
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkLabelAfterSelectAddress() {
        helper.visibleOfLocated(locatorLabel);
        actualRS = helper.getText(locatorLabel);
        String formatExpected = helper.replaceString(expectedRS, ", ", "\n");
        return formatExpected.equals(actualRS);
    }

    //distance
    public String getDistanceFormatFollowingDesign(String distanceNumber) {
        return ("(" + distanceNumber.replace('.', ',') + " km)");
    }

    public Boolean checkValueOfPickedStore(Branch nearestBranch, Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        WebElement store = helper.getWebElement(storePicked);
        helper.scrollByJS(store);
        String checked;
        actualRS = "";
        if (checkDisplayOfStorePicked()) {
            try {
                checked = store.findElement(By.xpath(storePickedName)).getText();
                if (!checked.equalsIgnoreCase(nearestBranch.getBranchName())) {
                    actualRS = actualRS + "Store picked Name is wrong. Actual:" + checked + "Expected: " + nearestBranch.getBranchName() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked name element.\n";
            }
            try {
                checked = store.findElement(By.xpath(storePickedAddress)).getText();
                if (!checked.equalsIgnoreCase(nearestBranch.getBranchAddress())) {
                    actualRS = actualRS + "Store picked address is wrong. Actual:" + checked + "Expected: " + nearestBranch.getBranchAddress() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked address element.\n";
            }
            if (!isDefault) {
                try {
                    checked = store.findElement(By.xpath(storePickedDistance)).getText();
                    String distanceStr = "";
                    if (nearestBranch.getBranchDistance().contains("(") || nearestBranch.getBranchDistance().contains("km"))
                        distanceStr = nearestBranch.getBranchDistance();
                    else distanceStr = getDistanceFormatFollowingDesign(nearestBranch.getBranchDistance());
                    if (!checked.equalsIgnoreCase(distanceStr)) {
                        actualRS = actualRS + "Store picked address is wrong. Actual: " + checked + " Expected: " + nearestBranch.getBranchDistance() + ".\n";
                        flag.add(false);
                    } else flag.add(true);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Can get/find store picked distance element.\n";
                }
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Store picked section did not display";
            return false;
        }
    }

    public Boolean checkValueOfPickedBranchAfterEnterAddress(String address, Boolean isDefault) {
        Branch nearestBranch = getPickedBranch(address);
        return checkValueOfPickedStore(nearestBranch, isDefault);
    }

    public Boolean checkPickedStoreAfterChangeStoreFromDelivery(int branchIndex, Boolean isDefault) {
        Branch selectedBranch = new Branch();
        //get branch information at index
        List<WebElement> branchList = helper.getElementList(deliveryOtherStoreBranchItems);
        WebElement branch = branchList.get(branchIndex);
        selectedBranch = setBranchInformation(branch);
        expectedBranch = selectedBranch;
        branch.click();
        return checkValueOfPickedStore(selectedBranch, isDefault);
    }

    public Boolean checkValueOfPickupAfterChangeBranch() {
        if (expectedBranch != null) {
            // check nearest store
            if (!checkDisplayOfPickupPanel()) {
                clickPickup();
            }
            helper.visibleOfLocated(pickupDefaultBranch);
            if (checkValueOfPickupStore(expectedBranch, false)) {
                return true;
            } else return false;
        } else {
            actualRS = "The selected branch is null";
            return false;
        }
    }

    private Branch setBranchInformation(WebElement e) {
        Branch branch = new Branch();
        String name = e.findElement(By.xpath(branchName)).getText();
        String address = e.findElement(By.xpath(branchAddress)).getText();
        String distance = e.findElement(By.xpath(branchDistance)).getText();
        branch.setBranchName(name);
        branch.setBranchAddress(address);
        branch.setBranchDistance(distance);
        return branch;
    }

    //delivery other branch
    //TODO check branch list
    public Boolean checkValueOfDeliveryOtherBranch(String address) {
        if (checkDisplayOfDeliveryOtherBranch()) {
            return checkValueOfDeliveryBranchAllItems(address);
        } else {
            actualRS = "Delivery store section did not display";
            return false;
        }
    }

    //check value of pickup
    public Boolean checkLabelAfterClickingOnPickupTab(Boolean isDefault) {
        String branchStr = "";
        if (isDefault) branchStr = getDefaultBranchWithoutAddress().getBranchName();
        else branchStr = driver.findElement(pickupSelectedBranch).findElement(By.xpath(branchName)).getText();
        checkOutsideDialog();
        helper.visibleOfLocated(locatorLabel);
        actualRS = helper.getText(locatorLabel);
        return branchStr.equals(actualRS);
    }

    public Boolean checkLabelWithDefaultBranch() {
        Branch nearestBranch = getDefaultBranchWithoutAddress();
        helper.visibleOfLocated(locatorLabel);
        WebElement label = driver.findElement(locatorLabel);
        actualRS = "Wrong branch name - Actual: \"" + label.getText() + "\" Expected: \"" + nearestBranch.getBranchName() + "\"\n";
        return label.getText().equals(nearestBranch.getBranchName());
    }

    public Boolean checkLabelAfterSelectOtherBranch(int branchIndex) {
        String branchStr = "";
        clickSelectStoreBranch();
        String oldBranch = getSelectBranchText();
        branchStr = selectAnotherStoreByIndex(pickupBranchNoActiveItems, branchIndex);
        helper.visibleOfLocated(locatorLabel);
        actualRS = helper.getText(locatorLabel);
        return branchStr.equalsIgnoreCase(actualRS);
    }

    private Boolean checkValueOfPickupStore(Branch nearestBranch, Boolean isDefault) {
        System.out.println(nearestBranch.getBranchName());
        List<Boolean> flag = new ArrayList<>();
        WebElement store;
        if (isDefault) store = helper.getWebElement(pickupDefaultBranch);
        else store = helper.getWebElement(pickupSelectedBranch);
        String checked;
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            try {
                checked = store.findElement(By.xpath(branchName)).getText();
                if (!checked.equals(nearestBranch.getBranchName())) {
                    actualRS = actualRS + "Store picked Name is wrong. Actual:" + checked + "Expected: " + nearestBranch.getBranchName() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked name element.\n";
            }
            try {
                checked = store.findElement(By.xpath(branchAddress)).getText();
                if (!checked.equals(nearestBranch.getBranchAddress())) {
                    actualRS = actualRS + "Store picked address is wrong. Actual:" + checked + "Expected: " + nearestBranch.getBranchAddress() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked address element.\n";
            }
            try {
                checked = store.findElement(By.xpath(branchDistance)).getText();
                String distanceStr = "";
                if (nearestBranch.getBranchDistance().contains("(") || nearestBranch.getBranchDistance().contains("km"))
                    distanceStr = nearestBranch.getBranchDistance();
                else distanceStr = getDistanceFormatFollowingDesign(nearestBranch.getBranchDistance());
                if (!checked.equals(distanceStr)) {
                    actualRS = actualRS + "Store picked address is wrong. Actual: " + checked + " Expected: " + nearestBranch.getBranchDistance() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked distance element.\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Store picked section did not display";
            return false;
        }
    }

    public Boolean checkValueOfPickedBranchWithoutAddress(Boolean isDefault) {
        Branch nearestBranch = getDefaultBranchWithoutAddress();
        return checkValueOfPickedStore(nearestBranch, isDefault);
    }

    //check display on dialog
    //header
    public Boolean checkDisplayOfDeliveryTab() {
        return helper.checkDisplayElement(deliveryTab);
    }

    public Boolean checkDisplayOfPickupTab() {
        return helper.checkDisplayElement(pickupTab);
    }

    public Boolean checkDisplayOfPickupLabel() {
        return helper.checkDisplayElement(pickupTxt);
    }

    public Boolean checkDisplayOfDeliveryLabel() {
        return helper.checkDisplayElement(deliveryTxt);
    }

    public Boolean checkDisplayOfPickupIcon() {
        return helper.checkDisplayElement(pickupIcon);
    }

    public Boolean checkDisplayOfDeliveryIcon() {
        return helper.checkDisplayElement(deliveryIcon);
    }

    //body
    public Boolean checkDisplayOfPickupPanel() {
        return helper.checkDisplayElement(pickupPanel);
    }

    public Boolean checkDisplayOfDeliveryPanel() {
        return helper.checkDisplayElement(deliveryPanel);
    }

    //delivery address
    public Boolean checkDisplayOfDeliveryInput() {
        Boolean check = checkDisplayOfDeliveryInputField();
        if (check == false) {
            clickDelivery();
            check = checkDisplayOfDeliveryInputField();
        }
        return check;
    }

    public Boolean checkDisplayOfDeliveryInputField() {
        return helper.checkDisplayElement(enterAddressField);
    }

    public Boolean checkDisplayOfDeliveryInputIcon() {
        return helper.checkDisplayElement(deliveryInputIcon);
    }

    public Boolean checkDisplayOfEnterAddressPlaceHolder() {
        if (driver.findElement(enterAddressField).getAttribute("placeholder").isEmpty() || driver.findElement(enterAddressField).getAttribute("placeholder").isBlank())
            return false;
        else return true;
    }

    /**
     * only display after entering address
     *
     * @return
     */
    public Boolean checkDisplayOfClearIcon() {
        return helper.checkDisplayElement(clearIcon);
    }

    /**
     * only display after entering address
     *
     * @return
     */
    public Boolean checkDisplayAddressList() {
        return helper.checkDisplayElement(addressListParent);
    }

    /**
     * My address list - saved address
     *
     * @return
     */
    public Boolean checkDisplayOfMyAddressList() {
        clickSelectAddress();
        return helper.checkDisplayElement(myAddressContainer);
    }

    public Boolean checkDisplayOfMyAddressLabel() {
        clickSelectAddress();
        return helper.checkDisplayElement(myAddressLabel);
    }

    public Boolean checkDisplayAddressListIcon() {
        int addressListSize = helper.getElementList(myAddressItems).size();
        int addressIconListSize = helper.getElementList(myAddressIconList).size();
        if (addressIconListSize == addressListSize) {
            return true;
        } else {
            actualRS = "Actual: " + String.valueOf(addressIconListSize) + " Expected: " + String.valueOf(addressListSize);
            return false;
        }
    }

    public Boolean checkDisplayAddressListAddress() {
        int addressListSize = helper.getElementList(myAddressItems).size();
        int addressIconListSize = helper.getElementList(myAddressTxtList).size();
        if (addressIconListSize == addressListSize) {
            return true;
        } else {
            actualRS = "Actual: " + String.valueOf(addressIconListSize) + " Expected: " + String.valueOf(addressListSize);
            return false;
        }
    }

    //store picked section

    /**
     * only display when user login or entered address
     *
     * @return
     */
    public Boolean checkDisplayOfDeliveryFromLabel() {
        return helper.checkDisplayElement(deliveryFromLabel);
    }

    /**
     * only display when user login or entered address
     *
     * @return
     */
    public Boolean checkDisplayOfMoreBranchIcon() {
        return helper.checkDisplayElement(moreBranchArrowIcon);
    }

    /**
     * only display when user login or entered address
     *
     * @return
     */
    public Boolean checkDisplayOfStorePicked() {
        return helper.checkDisplayElement(storePicked);
    }

    /**
     * only display when user login or entered address
     *
     * @return
     */
    public Boolean checkDisplayOfStorePickedInformation(Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        WebElement store = helper.getWebElement(storePicked);
        Boolean checked;
        actualRS = "";
        if (checkDisplayOfStorePicked()) {
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(storePickedName)));
                if (!checked) {
                    actualRS = actualRS + "Store picked Name did not display.\n";
                }
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked name element.\n";
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(storePickedAddress)));
                if (!checked) {
                    actualRS = actualRS + "Store picked address did not display.\n";
                }
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked address element.\n";
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(storePickedIcon)));
                if (!checked) actualRS = actualRS + "Store picked icon did not display.\n";
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked icon element.\n";
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(storePickedDistance)));
                if (isDefault) {
                    if (!checked) {
                        flag.add(true); //if the branch is default, all distances are 0,0km, page won't show distance list
                    } else {
                        flag.add(false);
                        actualRS = actualRS + "Store picked distance is displaying when branch is default.\n";
                    }
                } else {
                    if (checked) {
                        flag.add(true);
                    } else {
                        flag.add(false);
                        actualRS = actualRS + "Store picked distance did not display.\n";
                    }
                }
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked distance element.\n";
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(storePickedCheckedIcon)));
                if (!checked) actualRS = actualRS + "Store picked checked icon did not display.\n";
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked checked icon element.\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Picked store section did not display";
            return false;
        }
    }

    //more branch section
    public Boolean checkDisplayOfBackDeliveryArrow() {
        return helper.checkDisplayElement(deliveryOtherBranchBackArrow);
    }

    public Boolean checkDisplayOfDeliveryOtherBranch() {
        return helper.checkDisplayElement(deliveryOtherStoreBranch);
    }

    public Boolean checkDisplayOfDeliveryOtherBranchLabel() {
        return helper.checkDisplayElement(deliveryOtherBranchLabel);
    }

    public Boolean checkDisplayOfBackDeliveryNearestBranchLabel() {
        return helper.checkDisplayElement(deliveryOtherSelectBranchNearestLabel);
    }

    public Boolean checkDisplayOfBackDeliveryOtherBranchLabel() {
        return helper.checkDisplayElement(deliveryOtherSelectBranchOtherLabel);
    }

    public Boolean checkDisplayOfDeliveryDefaultBranchItem() {
        return helper.checkDisplayElement(deliveryOtherSelectedBranch);
    }

    /**
     * Check other branch items except checkedIcon
     *
     * @param isDefault
     * @return
     */
    public Boolean checkDisplayOfOtherSelectedBranchInformation(Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        WebElement store = helper.getWebElement(deliveryOtherSelectedBranch);
        Boolean checked;
        actualRS = "";
        if (checkDisplayOfDeliveryOtherBranch()) {
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(branchName)));
                if (!checked) {
                    actualRS = actualRS + "Delivery other branch Name did not display.\n";
                }
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find Delivery other branch name element.\n";
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(branchAddress)));
                if (!checked) {
                    actualRS = actualRS + "Delivery other branch address did not display.\n";
                }
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find Delivery other branch address element.\n";
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(branchIcon)));
                if (!checked) actualRS = actualRS + "Delivery other branch icon did not display.\n";
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find Delivery other branch icon element.\n";
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(branchDistance)));
                if (isDefault) {
                    if (!checked) {
                        flag.add(true); //if the branch is default, all distances are 0,0km, page won't show distance list
                    } else {
                        flag.add(false);
                        actualRS = actualRS + "Store picked distance is displaying when branch is default.\n";
                    }
                } else {
                    if (checked) {
                        flag.add(true);
                    } else {
                        flag.add(false);
                        actualRS = actualRS + "Store picked distance did not display.\n";
                    }

                }
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find Delivery other branch distance element.\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Delivery other branch section did not display";
            return false;
        }
    }

    public Boolean checkDisplayOfDeliveryOtherBranchInformation(Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        List<WebElement> branchList = helper.getElementList(deliveryOtherNoActiveItems);
        Boolean checked;
        int countChecked = 0;
        actualRS = "";
        if (checkDisplayOfDeliveryOtherBranch()) {
            for (int i = 0; i < branchList.size(); i++) {
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchName)));
                    if (!checked) {
                        actualRS = actualRS + "Delivery other branch Name did not display.\n";
                    }
                    flag.add(checked);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Can get/find Delivery other branch name element.\n";
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchAddress)));
                    if (!checked) {
                        actualRS = actualRS + "Delivery other branch address did not display.\n";
                    }
                    flag.add(checked);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Can get/find Delivery other branch address element.\n";
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchIcon)));
                    if (!checked) actualRS = actualRS + "Delivery other branch icon did not display.\n";
                    flag.add(checked);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Can get/find Delivery other branch icon element.\n";
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchDistance)));
                    if (isDefault) {
                        if (!checked) {
                            flag.add(true); //if the branch is default, all distances are 0,0km, page won't show distance list
                        } else {
                            flag.add(false);
                            actualRS = actualRS + "Store picked distance is displaying when branch is default.\n";
                        }
                    } else {
                        if (checked) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + "Store picked distance did not display.\n";
                        }
                    }
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Can get/find Delivery other branch distance element.\n";
                }
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Delivery other branch section did not display";
            return false;
        }
    }

    //pickup
    public Boolean checkDisplayOfElementOnPickup() {
        Boolean nearest = helper.checkDisplayElement(pickupDefaultLabel);
        Boolean other = helper.checkDisplayElement(pickupOtherLabel);
        if (nearest == true && other == true) {
            return true;
        } else if (nearest == false) {
            actualRS = "Nearest branch label did not display";
            return false;
        } else if (other == false) {
            actualRS = "Other branch label did not display";
            return false;
        } else {
            actualRS = "Nearest and Other branch label did not display";
            return false;
        }
    }

    public Boolean checkDisplayOfPickupBranchList() {
        int expectedSize = helpersAPIPos.getBranchesByCustomerAddress((double) 0, (double) 0).size();
        int size = helper.getElementList(pickupBranchItems).size();
        if (size == expectedSize) {
            return true;
        } else {
            actualRS = "Branch did not display full list. Actual: " + size + " Expected: " + expectedSize;
            return false;
        }
    }

    //default branch
    public Boolean checkDisplayOfDefaultBranchItem() {
        return helper.checkDisplayElement(pickupDefaultBranch);
    }

    public Boolean checkDisplayOfDefaultBranchIcon() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getWebElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchIcon)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfSelectedDefaultBranchChecked() {
        WebElement element = helper.getElement(pickupDefaultBranch);
        actualRS = element.findElement(By.xpath("./parent::label")).getAttribute("class");
        return actualRS.contains(addUserLocationDataTest.CHECK_CHECKED_BRANCH);
    }

    public Boolean checkDisplayOfDefaultBranchName() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getWebElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchName)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfDefaultBranchDistance() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getWebElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchDistance)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfDefaultBranchAddress() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getWebElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchAddress)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfDefaultBranchChecked() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getWebElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchCheckedIconByItem)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    //all items
    public Boolean checkDisplayOfBranchListItem() {
        helper.presenceOfAllElementsLocatedBy(pickupBranchItems);
        int size = helper.getElementList(pickupBranchItems).size();
        actualRS = String.valueOf(size);
        if (size >= 0) {
            expectedRS = actualRS;
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkDisplayOfBranchListIcon() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        if (checkDisplayOfBranchListItem()) {
            List<WebElement> branches = helper.getElementList(pickupBranchItems);
            int i = 0;
            for (WebElement branch : branches) {
                i++;
                if (helper.checkDisplayElementByElement(branch.findElement(By.xpath(branchIcon)))) flag.add(true);
                else {
                    flag.add(false);
                    actualRS = actualRS + "Position: " + i + "Branch icon did not display\n";
                }
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Can not find branch icon list";
            return false;
        }
    }

    /**
     * check other branch
     *
     * @return
     */
    public Boolean checkDisplayOfBranchListName() {
        actualRS = "";
        int x = 0;
        List<Boolean> flag = new ArrayList<>();
        if (checkDisplayOfBranchListItem()) {
            List<WebElement> branchList = helper.getElementList(pickupBranchItems);
            for (int i = 1; i < branchList.size(); i++) {
                x++;
                flag.add(helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchName))));
                actualRS = actualRS + "Branch name at " + x + " did not display.\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Can not find default branch";
            return false;
        }
    }

    public Boolean checkDisplayOfBranchListDistance(Boolean isDefault) {
        actualRS = "";
        int x = 0;
        List<Boolean> flag = new ArrayList<>();
        if (checkDisplayOfBranchListItem()) {
            List<WebElement> branchList = helper.getElementList(pickupBranchItems);
            for (int i = 1; i < branchList.size(); i++) {
                x++;
                flag.add(helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchDistance))));
                actualRS = actualRS + "Branch distance at " + x + " did not display.\n";
            }
            if (isDefault) {
                if (flag.contains(true)) return false;
                else return true;
            } else {
                if (flag.contains(false)) return false;
                else return true;
            }
        } else {
            actualRS = "Can not find other branch";
            return false;
        }
    }

    public Boolean checkDisplayOfBranchListAddress() {
        actualRS = "";
        int x = 0;
        List<Boolean> flag = new ArrayList<>();
        if (checkDisplayOfBranchListItem()) {
            List<WebElement> branchList = helper.getElementList(pickupBranchItems);
            for (int i = 1; i < branchList.size(); i++) {
                x++;
                flag.add(helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchAddress))));
                actualRS = actualRS + "Branch address at " + x + " did not display.\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Can not find default branch";
            return false;
        }
    }

    public Boolean checkOutsideDialog() {
        helper.pressESC();
        return checkInvisibleOfDialog();
    }

    public Boolean checkInvisibleOfDialog() {
        return helper.waitInvisibleByLocated(locatorDialog);
    }

    //check css
    public Boolean checkColorOfSelectedDefaultBranchChecked(String color) {
        WebElement element = helper.getElement(pickupDefaultBranch).findElement(By.xpath(branchCheckedIconByItem));
        actualRS = Color.fromString(element.getCssValue("fill")).asHex();
        return actualRS.equals(color);
    }

    public Boolean checkColorOfSelectedDefaultBranchIcon(String color) {
        WebElement element = helper.getElement(pickupDefaultBranch).findElement(By.xpath(branchIcon));
        ;
        actualRS = Color.fromString(element.getCssValue("fill")).asHex();
        return actualRS.equals(color);
    }

    public Boolean checkSizeAddLocationIcon(int height, int width) {
        int iconH = driver.findElement(locatorIcon).getSize().getHeight();
        int iconW = driver.findElement(locatorIcon).getSize().getWidth();
        if (iconW == width && iconH == height) {
            return true;
        } else if (iconW != width) {
            actualRS = "Wrong WIDTH: " + iconW;
            return false;
        } else if (iconH != height) {
            actualRS = "Wrong HEIGHT: " + iconH;
            return false;
        } else {
            actualRS = "Wrong ALl - H: " + iconH + " W: " + iconW;
            return false;
        }
    }

    public Boolean checkFontSizeAddLocationLabel(String fontSize) {
        actualRS = helper.getCSSValue(locatorLabel, "font-size");
        if (actualRS.equals(fontSize)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkColorAddLocationLabel(String color) {
        actualRS = Color.fromString(helper.getCSSValue(locatorLabel, "color")).asHex();
        if (actualRS.equals(color)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkHeightAddLocationComponent(int height) {
        int heightComponent = driver.findElement(deliveryAddressSelector).getSize().getHeight();
        if (heightComponent == height) {
            return true;
        } else {
            actualRS = String.valueOf(height);
            return false;
        }
    }

    public Boolean checkColorAddLocationComponent(String color) {
        actualRS = Color.fromString(helper.getCSSValue(locatorBackground, "background-color")).asHex();
        return actualRS.equals(color);
    }

    public Boolean checkSizeOfDefaultBranchAddress(String size) {
        helper.waitForPresence(pickupDefaultBranch);
        helper.visibleOfLocated(pickupDefaultBranch);
        actualRS = driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchAddress)).getCssValue("font-size");
        return actualRS.equals(size);
    }

    public Boolean checkFontWeightOfDefaultBranchAddress(String expected) {
        actualRS = driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchAddress)).getCssValue("font-weight");
        return actualRS.equals(expected);
    }

    public Boolean checkSizeOfDefaultBranchName(String size) {
        actualRS = driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchName)).getCssValue("font-size");
        return actualRS.equals(size);
    }

    public Boolean checkFontWeightOfDefaultBranchName(String expected) {
        actualRS = driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchName)).getCssValue("font-weight");
        return actualRS.equals(expected);
    }

    public Boolean checkSizeDefaultBranchIcon(int height, int width) {
        WebElement defaultBranch = helper.getWebElement(pickupDefaultBranch);
        int iconH = defaultBranch.findElement(By.xpath(branchIcon)).getSize().getHeight();
        int iconW = defaultBranch.findElement(By.xpath(branchIcon)).getSize().getWidth();
        if (iconW == width && iconH == height) {
            return true;
        } else if (iconW != width) {
            actualRS = "Wrong WIDTH: " + iconW;
            return false;
        } else if (iconH != height) {
            actualRS = "Wrong HEIGHT: " + iconH;
            return false;
        } else {
            actualRS = "Wrong ALl - H: " + iconH + " W: " + iconW;
            return false;
        }
    }

    public Boolean checkColorCheckedIconAfterSelectBranch(Boolean isDelivery, String color) {
        if (isDelivery) {
            actualRS = Color.fromString(driver.findElement(deliveryPanel).findElement(By.xpath(branchCheckedIcon)).getCssValue("fill")).asHex();
        } else
            actualRS = Color.fromString(driver.findElement(pickupPanel).findElement(By.xpath(branchCheckedIcon)).getCssValue("fill")).asHex();
        if (actualRS.equals(color)) {
            return true;
        } else {
            return false;
        }
    }

    private String getSelectBranchText() {
        helper.visibleOfLocated(pickupSelectedBranch);
        return helper.getText(pickupSelectedBranch);
    }

    public Boolean checkAlignOfAddLocationDialog(String align) {
        helper.waitUtilElementVisible(helper.getElement(locatorDialog));
        actualRS = helper.getCSSValue(locatorDialog, "vertical-align");
        if (actualRS.equals(align)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkColorOfDelivery(String color, Boolean isTrue) {
        actualRS = Color.fromString(helper.getCSSValue(deliveryTab, "background-color")).asHex();
        if (actualRS.equals(color)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param color
     * @param isTrue check xpath is active
     * @return
     */
    public Boolean checkColorOfPickup(String color, Boolean isTrue) {
        actualRS = Color.fromString(helper.getCSSValue(pickupTab, "background-color")).asHex();
        if (actualRS.equals(color)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkMaxLengthOfAddressInput(int length, int expectedLength) {
        String str = generateRandomStr(length);
        enterAddressLocation(str);
        int actualLength = helper.getAttribute(enterAddressField, "value").trim().length();
        actualRS = String.valueOf(actualLength);
        if (actualLength <= expectedLength) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkStoreAfterSelectAddress(String branchName) {
        actualRS = helper.getText(pickupSelectedBranch);
        return actualRS.equals(branchName);
    }

    public Boolean checkColorAllBranchIcon(String color) {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str = Color.fromString(list.get(i).findElement(By.xpath(branchIcon)).getCssValue("fill")).asHex();
            actualRS = actualRS + str + "\n";
            flag.add(str.equalsIgnoreCase(color));
        }
        if (flag.contains(false)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * check from other - from 2nd item
     *
     * @param height
     * @param width
     * @return
     */
    public Boolean checkSizeOfOtherBranchIconList(String height, String width) {
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String iconH, iconW;
        String c;
        int countFailH = 0, countFailW = 0;
        actualRS = "Wrong at positions:\n";
        for (int i = 1; i < list.size(); i++) {
            WebElement branchIconElement = list.get(i).findElement(By.xpath(branchIcon));
            iconH = branchIconElement.getCssValue("height");
            iconW = branchIconElement.getCssValue("width");
            if (!iconW.equalsIgnoreCase(width)) {
                actualRS = actualRS + ("Wrong WIDTH: " + iconW + "\n");
                countFailW++;
            } else if (!iconH.equalsIgnoreCase(height)) {
                actualRS = actualRS + ("Wrong HEIGHT: " + iconH + "\n");
                countFailH++;
            } else if (!iconW.equalsIgnoreCase(width) && !iconH.equalsIgnoreCase(height)) {
                actualRS = actualRS + ("Wrong ALl - H: " + iconH + " W: " + iconW + "\n");
                countFailH++;
                countFailW++;
            }
        }
        if (countFailH > 0 || countFailW > 0) {
            return false;
        } else return true;
    }

    /**
     * check from other - from 2nd item
     *
     * @param size
     * @return
     */
    public Boolean checkFontSizeOfOtherBranchListName(String size) {
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String c;
        actualRS = "";
        int countFail = 0;
        actualRS = "Wrong at positions:";
        for (int i = 1; i < list.size(); i++) {
            c = list.get(i).findElement(By.xpath(branchName)).getCssValue("font-size");
            if (!c.equals(size)) {
                countFail++;
                actualRS = actualRS + " " + i + 1;
            }
        }
        if (countFail > 0) {
            return false;
        } else return true;
    }

    /**
     * check from other - from 2nd item
     *
     * @param expected
     * @return
     */
    public Boolean checkFontWeightOfOtherBranchListAddress(String expected) {
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String c;
        int countFail = 0;
        actualRS = "Wrong at positions:";
        for (int i = 2; i < list.size(); i++) {
            c = list.get(i).findElement(By.xpath(branchName)).getCssValue("font-weight");
            if (!c.equals(expected)) {
                countFail++;
                actualRS = actualRS + (" " + i + 1);
            }
        }
        if (countFail > 0) {
            return false;
        } else return true;
    }

    //check value
    public Boolean checkValueOfDefaultBranchWithoutAddressOnPickup() {
        Branch nearestBranch = getDefaultBranchWithoutAddress();
        return checkValueOfPickupStore(nearestBranch, true);
    }

    /**
     * Entering address to check distance
     *
     * @return
     */
    public Boolean checkBranchAfterAddAddress() {
        actualRS = getSelectBranchText();
        List<WebElement> distanceList = helper.getElementList(pickupBranchDistanceList);
        Map<Integer, Double> distance = new HashMap();
        Double dis;
        int index;
        String unsort, replaced;
        for (int i = 0; i < distanceList.size(); i++) {
            unsort = distanceList.get(i).getText();
            replaced = helper.subStringFollowIndex(unsort, 1, (unsort.lastIndexOf(" km"))).replace(',', '.');
            dis = Double.parseDouble(replaced);
            distance.put(i, dis);
        }
        //Sorted by values
        Map<Integer, Double> sortedByValueMap = distance.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), LinkedHashMap::putAll);
        //get first key in Map
        index = distance.keySet().stream().findFirst().get();
        expectedRS = helper.getElementList(pickupBranchNameList).get(index).getText().trim();
        //Check the display of branch name on Select Branch button and nearest branch on Pickup
        Boolean checkSelectBranchText = expectedRS.equals(actualRS);
        Boolean checkNearestBranch;
        if (index == 0) {
            checkNearestBranch = true;
        } else checkNearestBranch = false;
        if (checkSelectBranchText == true && checkNearestBranch == true) {
            return true;
        } else if (checkSelectBranchText == false) {
            actualRS = actualRS + (" is wrong. Expected: " + expectedRS);
            return false;
        } else if (checkNearestBranch == false) {
            actualRS = "This branch is not nearest branch. Nearest is " + expectedRS + " with distance " + distance.get(index);
            return false;
        } else {
            actualRS = "Actual distance " + driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchDistance)).getText() + "\nExpected distance: " + distanceList.get(index).getText() + "\nActual branch name: " + driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchName)).getText() + "\nExpected branch name: " + helper.getElementList(pickupBranchNameList).get(index);
            return false;
        }
    }

    public Boolean checkBranchWithoutAddress() {
        try {
            return checkBranchList(pickupBranchItems, 0, 0);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            actualRS = exception.getMessage();
            return false;
        }
    }

    public Boolean checkBranchAfterEnterAddress(Boolean isEnterAddress, String address, int addressIndex) {
        Map<String, Double> location = helpersAPIPos.getLatLongByAddress(address);
        if (isEnterAddress) {
            clickSelectAddress();
            enterDeliveryAddress(address, addressIndex);
        }
        clickSelectStoreBranch();
        try {
            return checkBranchList(pickupBranchItems, location.get("lat"), location.get("lng"));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            return false;
        }
    }

    private Boolean checkValueOfDeliveryBranchAllItems(String address) {
        Map<String, Double> location = helpersAPIPos.getLatLongByAddress(address);
        try {
            return checkBranchList(deliveryOtherStoreBranchItems, location.get("lat"), location.get("lng"));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            return false;
        }
    }

    private Branch getPickedBranch(String address) {
        Map<String, Double> location = helpersAPIPos.getLatLongByAddress(address);
        List<Branch> branchesApi = helpersAPIPos.getBranchesByCustomerAddress(location.get("lat"), location.get("lng"));
        return branchesApi.get(0);
    }

    private Branch getDefaultBranchWithoutAddress() {
        List<Branch> branchesApi = helpersAPIPos.getBranchesByCustomerAddress((double) 0, (double) 0);
        return branchesApi.get(0);
    }

    /**
     * check branch list with address
     *
     * @param branchListXP using xpath of branch list. Ex: pickupBranchItems, deliveryOtherStoreBranchItems
     * @param lat
     * @param lng
     * @return
     */
    public Boolean checkBranchList(By branchListXP, double lat, double lng) {
        actualRS = "";
        //get all branches with api
        List<Branch> branchesApi = helpersAPIPos.getBranchesByCustomerAddress(lat, lng);
        List<Branch> branches = new ArrayList<>();
        Branch branch;
        String unsort, replaced, address;
        helper.waitForJStoLoad();
        List<WebElement> elementList = helper.getElementList(branchListXP);
        for (int i = 0; i < elementList.size(); i++) {
            branch = new Branch();
            //get distance
            unsort = elementList.get(i).findElement(By.xpath(branchDistance)).getText().trim();
            if (unsort.isEmpty() || unsort.isBlank()) {
                unsort = elementList.get(i).findElement(By.xpath(branchDistance)).getText().trim();
            }
            replaced = helper.subStringFollowIndex(unsort, 1, (unsort.lastIndexOf(" km"))).replace(',', '.');
            address = elementList.get(i).findElement(By.xpath(branchAddress)).getText().trim();
            //clean the duplicate space
            if (address.contains("  ")) address = address.replace("  ", " ");
            branch.setBranchAddress(address);
            branch.setBranchDistance(replaced);
            branch.setBranchName(elementList.get(i).findElement(By.xpath(branchName)).getText().trim());
            branches.add(branch);
            unsort = "";
        }
        //compare 2 list
        List<Boolean> flag = new ArrayList<>();
        for (int x = 0; x < branches.size(); x++) {
            if (branches.get(x).getBranchName().equals(branchesApi.get(x).getBranchName())) {
                flag.add(true);
            } else {
                actualRS = actualRS + (branches.get(x).getBranchName() + " - Branch name failed!\nActual: " + branches.get(x).getBranchName() + "\nExpected: " + branchesApi.get(x).getBranchName());
                flag.add(false);
            }
            if (branches.get(x).getBranchAddress().equals(branchesApi.get(x).getBranchAddress())) {
                flag.add(true);
            } else {
                actualRS = actualRS + (branches.get(x).getBranchName() + " - Address failed!\nActual: " + branches.get(x).getBranchAddress() + "\nExpected: " + branchesApi.get(x).getBranchAddress());
                flag.add(false);
            }
            if (branches.get(x).getBranchDistance().equals(branchesApi.get(x).getBranchDistance())) {
                flag.add(true);
            } else {
                actualRS = actualRS + (branches.get(x).getBranchName() + " - Distance failed!\nActual: " + branches.get(x).getBranchDistance() + "\nExpected: " + branchesApi.get(x).getBranchDistance());
                flag.add(false);
            }
        }
        if (flag.contains(false)) {
            return false;
        } else return true;
    }

    private void getOldBranchList(By branchListXP) {
        oldBranchList = new ArrayList<>();
        helper.presenceOfAllElementsLocatedBy(branchListXP);
        List<WebElement> list = helper.getElementList(branchListXP);
        Branch branch;
        for (WebElement e : list) {
            branch = setBranchInformation(e);
            oldBranchList.add(branch);
        }
    }

    public void getOldBranchListPickup(Boolean isDelivery) {
        if (isDelivery) getOldBranchList(deliveryOtherStoreBranchItems);
        else getOldBranchList(pickupBranchItems);
    }

    /**
     * check branch list same as old list after selecting another branch
     *
     * @param isDelivery false: PICKUP
     * @return
     */
    public Boolean checkBranchListAfterSelectBranch(Boolean isDelivery) {
        actualRS = "";
        helper.refreshPage();
        clickSelectStoreBranch();
        List<Branch> branches = new ArrayList<>();
        Branch branch;
        String distance, address;
        helper.waitForJStoLoad();
        List<WebElement> elementList;
        if (isDelivery) elementList = helper.getElementList(deliveryOtherStoreBranchItems);
        else elementList = helper.getElementList(pickupBranchItems);
        for (int i = 0; i < elementList.size(); i++) {
            branch = new Branch();
            //get distance
            distance = elementList.get(i).findElement(By.xpath(branchDistance)).getText().trim();
            address = elementList.get(i).findElement(By.xpath(branchAddress)).getText().trim();
            //clean the duplicate space
            if (address.contains("  ")) address = address.replace("  ", " ");
            branch.setBranchAddress(address);
            branch.setBranchDistance(distance);
            branch.setBranchName(elementList.get(i).findElement(By.xpath(branchName)).getText().trim());
            branches.add(branch);
        }
        //compare 2 list
        List<Boolean> flag = new ArrayList<>();
        for (int x = 0; x < branches.size(); x++) {
            if (branches.get(x).getBranchName().equals(oldBranchList.get(x).getBranchName())) {
                flag.add(true);
            } else {
                actualRS = actualRS + (branches.get(x).getBranchName() + " - Branch name failed!\nActual: " + branches.get(x).getBranchName() + "\nExpected: " + oldBranchList.get(x).getBranchName());
                flag.add(false);
            }
            if (branches.get(x).getBranchAddress().equals(oldBranchList.get(x).getBranchAddress())) {
                flag.add(true);
            } else {
                actualRS = actualRS + (branches.get(x).getBranchName() + " - Address failed!\nActual: " + branches.get(x).getBranchAddress() + "\nExpected: " + oldBranchList.get(x).getBranchAddress());
                flag.add(false);
            }
            if (branches.get(x).getBranchDistance().equals(oldBranchList.get(x).getBranchDistance())) {
                flag.add(true);
            } else {
                actualRS = actualRS + (branches.get(x).getBranchName() + " - Distance failed!\nActual: " + branches.get(x).getBranchDistance() + "\nExpected: " + oldBranchList.get(x).getBranchDistance());
                flag.add(false);
            }
        }
        if (flag.contains(false)) {
            return false;
        } else return true;
    }

    public Boolean checkValueOfNearestDeliveryBranchAfterChangeBranch() {
        if (expectedBranch != null) {
            // check nearest store
            if (!checkDisplayOfDeliveryPanel()) {
                clickDelivery();
            }
            clickMoreBranchArrow();
            helper.visibleOfLocated(deliveryOtherSelectedBranch);
            if (checkValueOfDeliveryNearestBranch(expectedBranch)) {
                return true;
            } else return false;
        } else {
            actualRS = "The selected branch is null";
            return false;
        }
    }

    //todo check default branch
    public Boolean checkValueOfDeliveryDefaultBranch() {
        Branch defaultBranch = getDefaultBranchWithoutAddress();
        System.out.println(defaultBranch.getBranchName());
        List<Boolean> flag = new ArrayList<>();
        List<WebElement> branchList = helper.getElementList(deliveryOtherStoreBranchItems);
        WebElement store = branchList.get(0);
        String checked;
        actualRS = "";
        if (checkDisplayOfDeliveryDefaultBranchItem()) {
            try {
                checked = store.findElement(By.xpath(branchName)).getText();
                if (!checked.equals(defaultBranch.getBranchName())) {
                    actualRS = actualRS + "Store picked Name is wrong. Actual:" + checked + "Expected: " + defaultBranch.getBranchName() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked name element.\n";
            }
            try {
                checked = store.findElement(By.xpath(branchAddress)).getText();
                if (!checked.equals(defaultBranch.getBranchAddress())) {
                    actualRS = actualRS + "Store picked address is wrong. Actual:" + checked + "Expected: " + defaultBranch.getBranchAddress() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked address element.\n";
            }
            try {
                checked = store.findElement(By.xpath(branchDistance)).getText();
                String distanceStr = "";
                if (defaultBranch.getBranchDistance().contains("km")) distanceStr = defaultBranch.getBranchDistance();
                else distanceStr = getDistanceFormatFollowingDesign(defaultBranch.getBranchDistance());
                if (!checked.equals(distanceStr)) {
                    actualRS = actualRS + "Store picked address is wrong. Actual: " + checked + " Expected: " + defaultBranch.getBranchDistance() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked distance element.\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Store picked section did not display";
            return false;
        }
    }

    private Boolean checkValueOfDeliveryNearestBranch(Branch nearestBranch) {
        System.out.println(nearestBranch.getBranchName());
        List<Boolean> flag = new ArrayList<>();
        helper.visibleOfLocated(deliveryOtherSelectedBranch);
        WebElement store = helper.getWebElement(deliveryOtherSelectedBranch);
        String checked;
        actualRS = "";
        if (checkDisplayOfDeliveryDefaultBranchItem()) {
            try {
                checked = store.findElement(By.xpath(branchName)).getText();
                if (!checked.equals(nearestBranch.getBranchName())) {
                    actualRS = actualRS + "Store picked Name is wrong. Actual:" + checked + "Expected: " + nearestBranch.getBranchName() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked name element.\n";
            }
            try {
                checked = store.findElement(By.xpath(branchAddress)).getText();
                if (!checked.equals(nearestBranch.getBranchAddress())) {
                    actualRS = actualRS + "Store picked address is wrong. Actual:" + checked + "Expected: " + nearestBranch.getBranchAddress() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked address element.\n";
            }
            try {
                checked = store.findElement(By.xpath(branchDistance)).getText();
                String distanceStr = "";
                if (nearestBranch.getBranchDistance().contains("km")) distanceStr = nearestBranch.getBranchDistance();
                else distanceStr = getDistanceFormatFollowingDesign(nearestBranch.getBranchDistance());
                if (!checked.equals(distanceStr)) {
                    actualRS = actualRS + "Store picked address is wrong. Actual: " + checked + " Expected: " + nearestBranch.getBranchDistance() + ".\n";
                    flag.add(false);
                } else flag.add(true);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find store picked distance element.\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Store picked section did not display";
            return false;
        }
    }

    private Boolean checkCheckedIconWhenSelectingBranch(By branchXP) {
        String name = expectedBranch.getBranchName();
        WebElement selectedBranch = driver.findElement(branchXP);
        actualRS = "";
        if (selectedBranch != null) {
            List<Boolean> flag = new ArrayList<>();
            String branchStr = selectedBranch.findElement(By.xpath(branchName)).getText();
            if (branchStr.trim().equalsIgnoreCase(name.trim())) {
                flag.add(true);
                if (helper.checkDisplayElementByElement(selectedBranch.findElement(By.xpath(branchCheckedIconByItem)))) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Checked icon did not display with name: " + name + "\n";
                    flag.add(false);
                }
            } else {
                actualRS = actualRS + "Branch name is wrong. Actual: " + branchStr + " Expected: " + name + "\n";
                flag.add(false);
            }
            branchStr = selectedBranch.findElement(By.xpath(branchAddress)).getText();
            if (branchStr.equalsIgnoreCase(expectedBranch.getBranchAddress())) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Branch address is wrong. Actual: " + branchStr + " Expected: " + branchStr + "\n";
                flag.add(false);
            }
            branchStr = selectedBranch.findElement(By.xpath(branchDistance)).getText();
            if (branchStr.equalsIgnoreCase(expectedBranch.getBranchDistance())) {
                flag.add(true);
            } else {
                actualRS = actualRS + "Branch distance is wrong. Actual: " + branchStr + " Expected: " + branchStr + "\n";
                flag.add(false);
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "This selected branch did not display on Pickup/Delivery pickup";
            return false;
        }
    }

    public Boolean checkCheckedIconAfterSelectBranchPickup(String activeClassCss, Boolean isDelivery) {
        if (isDelivery) return checkCheckedIconWhenSelectingBranch(deliveryOtherSelectedBranch);
        else return checkCheckedIconWhenSelectingBranch(pickupSelectedBranch);
    }

    //actions
    private String getCurrentLanguage() {
        return commonPagesTheme1().homePage.getCurrentLanguage();
    }

    private String getTypeLabelFollowingLanguage(Boolean isDelivery) {
        String currentLanguage = getCurrentLanguage();
        List<String> keyList = new ArrayList<>();
        String text = "";
        if (isDelivery) {
            keyList.add("deliveryTo");
            text = jsonReader.localeReader(currentLanguage, addUserLocationDataTest.PAGE, keyList);
            System.out.println(text);
        } else {
            keyList.add("pickUp");
            text = jsonReader.localeReader(currentLanguage, addUserLocationDataTest.PAGE, keyList);
            System.out.println(text);
        }
        return text;
    }

    private void clickLocationLabel() {
        try {
            helper.clickBtn(driver.findElement(locatorLabel));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(driver.findElement(locatorLabel));
        }
    }

    public void clickSelectStoreBranch() {
        clickLocationLabel();
        clickPickup();
    }

    public void clickSelectAddress() {
        checkDisplayAddLocationLabel();
        clickLocationLabel();
        clickDelivery();
    }

    public Boolean clickSelectedTypeButton() {
        if (checkDisplayAddLocationTypeButton()) {
            helper.clickBtn(driver.findElement(selectTypeButton));
            return true;
        } else return false;
    }

    public void clickDelivery() {
        helper.visibleOfLocated(deliveryTab);
        helper.clickBtn(driver.findElement(deliveryTab));
    }

    public void clickPickup() {
        helper.visibleOfLocated(pickupTab);
        helper.clickBtn(driver.findElement(pickupTab));
    }

    public void clickMoreBranchArrow() {
        helper.visibleOfLocated(moreBranchArrowIcon);
        helper.scrollByJS(driver.findElement(moreBranchArrowIcon));
        helper.clickBtn(driver.findElement(moreBranchArrowIcon));
    }

    public void clickBackDeliveryArrow() {
        helper.visibleOfLocated(deliveryOtherBranchBackArrow);
        helper.clickBtn(driver.findElement(deliveryOtherBranchBackArrow));
    }

    /**
     * Type and select address
     *
     * @param address
     * @param addressIndex get from a list so index from 0
     */
    public void enterDeliveryAddress(String address, int addressIndex) {
        clickSelectAddress();
        clickDelivery();
        onlyFillDeliveryAddress(address, addressIndex);
    }

    public void enterAddressLocation(String address) {
        helper.visibleOfLocated(enterAddressField);
        if (checkDisplayOfClearIcon()) {
            System.out.println("Clear address");
            clickClearIcon();
        }
        helper.enterData(driver.findElement(enterAddressField), address);
    }

    public void clickClearIcon() {
        helper.visibleOfLocated(enterAddressField);
        try {
            helper.clickBtn(driver.findElement(clearIcon));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        if (helper.getCSSValue(enterAddressField, "value").isEmpty()) {
            System.out.println(helper.getCSSValue(enterAddressField, "value").isBlank());
        }
    }

    public void onlyFillDeliveryAddress(String address, int addressIndex) {
        helper.waitForPresence(enterAddressField);
        helper.visibleOfLocated(enterAddressField);
        if (checkDisplayOfClearIcon()) {
            System.out.println("Clear address");
            clickClearIcon();
            helper.waitForJStoLoad();
        }
        helper.enterData(driver.findElement(enterAddressField), address);
        try {
            helper.waitUtilElementVisible(driver.findElement(addressListParent));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitForPresence(addressListParent);
        }
        expectedRS = helper.selectOptionDropdown(addressListItems, addressIndex);
    }

    public void onlyFillDeliveryAddressNoClear(String address, int addressIndex) {
        helper.waitForPresence(enterAddressField);
        helper.visibleOfLocated(enterAddressField);
        helper.enterData(driver.findElement(enterAddressField), address);
        try {
            helper.waitUtilElementVisible(driver.findElement(addressListParent));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitForPresence(addressListParent);
        }
        expectedRS = helper.selectOptionDropdown(addressListItems, addressIndex);
    }

    public String generateRandomStr(int length) {
        return faker.generateRandom(length);
    }

    public void enterAddress(String address, int addressIndex) {
        helper.visibleOfLocated(locatorLabel);
        String oldAddress = helper.getText(locatorLabel);
        enterDeliveryAddress(address, addressIndex);
        String newAddress = helper.getText(locatorLabel);
        int count = 3;
        while (count > 0) {
            System.out.println(count);
            if (oldAddress.equals(newAddress)) {
                enterDeliveryAddress(address, addressIndex);
            } else {
                break;
            }
            newAddress = helper.getText(locatorLabel);
            count--;
        }
    }

    public Boolean selectBranchWithName(String branchName) {
        System.out.println(branchName);
        helper.pressHomeAction();
        clickSelectStoreBranch();
        helper.visibleOfLocated(pickupPanel);
        helper.visibleOfLocated(pickupSelectedBranch);
        String oldBranch = helper.getText(pickupSelectedBranch);
        List<WebElement> nameList = helper.getElementList(pickupBranchNameList);
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).getText().equalsIgnoreCase(branchName)) {
                helper.scrollToElementByJS(nameList.get(i));
                nameList.get(i).click();
                break;
            }
        }
        helper.visibleOfLocated(locatorLabel);
        return oldBranch.equalsIgnoreCase(branchName);
    }

    private String selectAnotherStoreByIndex(By otherBranchList, int index) {
        List<WebElement> branchList = helper.getElementList(otherBranchList);
        expectedRS = branchList.get(index).findElement(By.xpath(branchName)).getText();
        branchList.get(index).click();
        return expectedRS;
    }

    public String selectRandomSavedAddress() {
        helper.refreshPage();
        commonPagesTheme1().homePage.clickAccountIcon();
        if (!commonPagesTheme1().homePage.checkDisplayOfLogout()) {
            commonPagesTheme1().homePage.loginSuccessfully(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD);
        }
        helper.refreshPage();
        clickSelectAddress();
        helper.visibleOfLocated(myAddressContainer);
        List<WebElement> list = helper.getElementList(myAddressItems);
        int size = list.size();
        Random random = new Random();
        int index = random.nextInt(size);
        String address = helper.getElementList(myAddressTxtList).get(index).getText();
        list.get(index).click();
        return address;
    }

    //check value
    public Boolean checkValueAfterSelectSavedAddress() {
        String currentLanguage = getCurrentLanguage();
        String expectedAddress = selectRandomSavedAddress();
        checkDisplayAddLocationLabel();
        helper.waitForTextToBePresent(locatorLabel, expectedAddress);
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        String actualAddress = helper.getText(locatorLabel).trim();
        System.out.println(expectedAddress);
        if (actualAddress.equals(expectedAddress)) flag.add(true);
        else {
            actualRS = actualRS + "Wrong address is displayed. Actual: \"" + actualAddress + "\" Expected: \"" + expectedAddress + "\"\n";
            flag.add(false);
        }
        List<String> keyList = new ArrayList<>();
        keyList.add("deliveryTo");
        if (helper.commonLanguageCheckLocaleFile(currentLanguage, "Type button label", "text", selectedTypeLabel, addUserLocationDataTest.PAGE, keyList) == false) {
            flag.add(false);
            actualRS = actualRS + "Actual: " + helper.getText(selectedTypeLabel) + " Expected: " + helper.expectedLanguage + "\n";
        } else flag.add(true);
        clickSelectAddress();
        //check display of delivery panel
        if (!checkDisplayOfDeliveryPanel()) {
            flag.add(false);
            actualRS = actualRS + "Delivery panel did not display.\n";
            clickDelivery();
        } else flag.add(true);
        //check display of recommend address dropdown
        if (checkDisplayAddressList()) {
            flag.add(false);
            actualRS = actualRS + "Recommend address box is displaying.\n";
        } else flag.add(true);
        String enterAddressValue = helper.getAttribute(enterAddressField, "value");
        if (enterAddressValue.equals(expectedAddress)) flag.add(true);
        else {
            actualRS = "Wrong address. Actual: \"" + enterAddressValue + "\"\nExpected: \"" + expectedAddress + "\"";
            flag.add(false);
        }
        //check picked store
        Branch nearestBranch = getPickedBranch(expectedAddress);
        Boolean checked = checkValueOfPickedStore(nearestBranch, false);
        String actualTemp = actualRS;
        if (checked) flag.add(true);
        else {
            flag.add(false);
            actualTemp = actualTemp + "- Nearest branch is displaying incorrect.\n" + actualRS;
        }
        //check delivery branch
        clickMoreBranchArrow();
        checked = checkValueOfDeliveryOtherBranch(expectedAddress);
        if (checked) flag.add(true);
        else {
            flag.add(false);
            actualTemp = actualTemp + "- Delivery branch is displaying incorrect.\n" + actualRS;
        }
        //check pickup branch
        clickPickup();
        checked = checkBranchAfterEnterAddress(false, expectedAddress, 0);
        if (checked) flag.add(true);
        else {
            flag.add(false);
            actualTemp = actualTemp + "- Pickup branch is displaying incorrect.\n" + actualRS;
        }
        actualRS = actualTemp;
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * No enter address/select branch
     *
     * @param language
     * @return
     */
    private Boolean checkLanguageOfAddLocation(String language) {
        List<String> keyList = new ArrayList<>();
        keyList.add("pleaseSelectAddress");
        helper.waitUtilElementVisible(driver.findElement(locatorLabel));
        if (helper.commonLanguageCheckLocaleFile(language, "please Select Address", "text", locatorLabel, addUserLocationDataTest.PAGE, keyList) == false) {
            actualRS = "Actual: " + helper.getText(locatorLabel) + "\nExpected: " + helper.expectedLanguage;
            return false;
        } else {
            return true;
        }
    }

    /**
     * PICKUP PANEL
     *
     * @param language
     * @param isDefault
     * @return
     */
    public Boolean checkLanguageOfDefaultBranchLabel(String language, Boolean isDefault, Boolean isDelivery) {
        List<String> keyList = new ArrayList<>();
        String text = "";
        actualRS = "";
        String actualTemp = "";
        if (isDefault) {
            text = "defaultBranch";
            keyList.add("defaultBranch");
            actualTemp = "Default branch label display wrong.";
        } else {
            text = "nearestBranch";
            keyList.add("nearestBranch");
            actualTemp = "Nearest branch label display wrong.";
        }
        By xpath;
        if (isDelivery) xpath = deliveryOtherSelectBranchNearestLabel;
        else xpath = pickupDefaultLabel;
        helper.visibleOfLocated(xpath);
        try {
            String actualLabel = helper.getText(xpath);
            if (helper.commonLanguageCheckLocaleFile(language, text, "text", xpath, addUserLocationDataTest.PAGE, keyList) == false) {
                actualRS = actualRS + actualTemp + " Actual: " + actualLabel + " Expected: " + helper.expectedLanguage + "\n";
                return false;
            } else return true;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualRS = actualRS + exception.getMessage() + "\n";
            return false;
        }
    }

    private Boolean checkLanguageOfAddLocationDialog(String language, Boolean hasPickedStore, Boolean isLogin, Boolean isDefault) {
        actualRS = "";
        List<String> keyList = new ArrayList<>();
        List<Boolean> flag = new ArrayList<>();
        keyList.add("deliveryTo");
        clickSelectAddress();
        helper.waitUtilElementVisible(driver.findElement(deliveryTxt));
        try {
            if (helper.commonLanguageCheckLocaleFile(language, "deliveryTxt", "text", deliveryTxt, addUserLocationDataTest.PAGE, keyList) == false) {
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + "Actual: " + helper.getText(deliveryTxt) + " Expected: " + helper.expectedLanguage + "\n";
            } else flag.add(true);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
        }
        keyList.clear();
        keyList.add("pickUp");
        try {
            if (helper.commonLanguageCheckLocaleFile(language, "pickUp", "text", pickupTxt, addUserLocationDataTest.PAGE, keyList) == false) {
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + "Actual: " + helper.getText(pickupTxt) + " Expected: " + helper.expectedLanguage + "\n";
            } else flag.add(true);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
        }
        keyList.clear();
        keyList.add("autoCompletePlaceholder");
        clickDelivery();
        helper.waitUtilElementVisible(driver.findElement(enterAddressField));
        try {
            if (helper.commonLanguageCheckLocaleFile(language, "Enter address placeholder", "placeholder", enterAddressField, addUserLocationDataTest.PAGE, keyList) == false) {
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + "Enter address placeholder wrong. Actual: " + helper.getAttribute(enterAddressField, "placeholder") + " Expected: " + helper.expectedLanguage + "\n";
            } else {
                flag.add(true);
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
        }
        if (isLogin) {
            keyList.clear();
            keyList.add("addressSaved");
            try {
                if (helper.commonLanguageCheckLocaleFile(language, "Saved address label", "text", myAddressLabel, addUserLocationDataTest.PAGE, keyList) == false) {
                    flag.add(false);
                    actualCheckLanguage = actualCheckLanguage + "Saved address label wrong. Actual: " + helper.getText(myAddressLabel) + " Expected: " + helper.expectedLanguage + "\n";
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
            }
        }
        if (hasPickedStore) {
            keyList.clear();
            keyList.add("deliveredFrom");
            try {
                if (helper.commonLanguageCheckLocaleFile(language, "Delivered From label", "text", deliveryFromLabel, addUserLocationDataTest.PAGE, keyList) == false) {
                    flag.add(false);
                    actualCheckLanguage = actualCheckLanguage + "Delivered From label wrong. Actual: " + helper.getText(deliveryFromLabel) + " Expected: " + helper.expectedLanguage + "\n";
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
            }
            clickMoreBranchArrow();
            keyList.clear();
            keyList.add("selectOtherBranch");
            try {
                helper.visibleOfLocated(deliveryOtherBranchLabel);
                if (helper.commonLanguageCheckLocaleFile(language, "Other branchs label", "text", deliveryOtherBranchLabel, addUserLocationDataTest.PAGE, keyList) == false) {
                    flag.add(false);
                    actualCheckLanguage = actualCheckLanguage + "Other branchs label wrong. Actual: " + helper.getText(deliveryOtherBranchLabel) + " Expected: " + helper.expectedLanguage + "\n";
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
            }
            flag.add(checkLanguageOfDefaultBranchLabel(language, isDefault, true));
            actualCheckLanguage = actualCheckLanguage + actualRS;
            keyList.clear();
            keyList.add("otherBranch");
            try {
                if (!helper.commonLanguageCheckLocaleFile(language, "deliveryOtherSelectBranchOtherLabel", "text", deliveryOtherSelectBranchOtherLabel, addUserLocationDataTest.PAGE, keyList)) {
                    actualCheckLanguage = actualCheckLanguage + "deliveryOtherSelectBranchOtherLabel wrong. Actual: " + helper.getText(deliveryOtherSelectBranchOtherLabel) + " Expected: " + helper.expectedLanguage + "\n";
                    flag.add(false);
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
            }
            clickBackDeliveryArrow();
        }
        clickPickup();
        flag.add(checkLanguageOfDefaultBranchLabel(language, isDefault, false));
        actualCheckLanguage = actualCheckLanguage + actualRS;
        keyList.clear();
        keyList.add("otherBranch");
        try {
            if (helper.commonLanguageCheckLocaleFile(language, "otherBranch", "text", pickupOtherLabel, addUserLocationDataTest.PAGE, keyList) == false) {
                actualCheckLanguage = actualCheckLanguage + "Actual: " + helper.getText(pickupOtherLabel) + " Expected: " + helper.expectedLanguage + "\n";
                flag.add(false);
            } else flag.add(true);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualCheckLanguage = actualCheckLanguage + exception.getMessage() + "\n";
        }
        if (flag.contains(false)) {
            return false;
        } else return true;
    }

    private List<Boolean> checkAllAddUserLocation(Boolean hasPickedStore, Boolean isLogin, Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "1. Locator component\n";
        String currentLanguage = getCurrentLanguage();
        //clear branch name on locator component
        try {
            clickSelectAddress();
            checkValueOfAddressAfterClear();
        } catch (Exception exception) {
            Log.info("No clear address");
        }
        checkOutsideDialog();
        flag.add(checkLanguageOfAddLocation(currentLanguage));
        actualCheckLanguage = actualCheckLanguage + actualRS + "\n";
        try {
            enterDeliveryAddress(addUserLocationDataTest.ADDRESS_DELIVERY, addUserLocationDataTest.SELECT_INDEX_ADDRESS_LIST);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.refreshPage();
        actualRS = "2. Locator dialog\n";
        flag.add(checkLanguageOfAddLocationDialog(currentLanguage, hasPickedStore, isLogin, isDefault));
        actualCheckLanguage = actualCheckLanguage + actualRS + "\n";
        checkOutsideDialog();
        return flag;
    }

    private Boolean checkLanguageOfLocator(Boolean hasPickedStore, Boolean isLogin, Boolean isDefault) {
        helper.refreshPage();
        List<Boolean> flag = checkAllAddUserLocation(hasPickedStore, isLogin, isDefault);
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkLanguageOfAddUserLocation(Boolean hasPickedStore, Boolean isLogin, Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        String currentLanguage = getCurrentLanguage();
        String checkedLanguage = currentLanguage;
        actualRS = "";
        actualCheckLanguage = "";
        int index = 0;
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        List<WebElement> languageList = helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
        helper.waitForPresence(commonPagesTheme1().homePage.dialogContent);
        helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.dialogContent));
        if (languageList.get(0).getText().equalsIgnoreCase(language)) index = 1;
        else index = 0;
        commonPagesTheme1().homePage.clickLanguage();
        //check default language
        clickSelectAddress();
        flag.add(checkLanguageOfLocator(hasPickedStore, isLogin, isDefault));
        helper.refreshPage();
        languageList = helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            helper.waitForPresence(commonPagesTheme1().homePage.dialogContent);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.dialogContent));
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            flag.add(checkLanguageOfLocator(hasPickedStore, isLogin, isDefault));
            helper.refreshPage();
            helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
            helper.checkDisplayElementByElement(driver.findElement(commonPagesTheme1().homePage.dialogContent));
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(commonPagesTheme1().homePage.languageOptions);
            if (!languageList.get(i).getText().equals(language)) {
                helper.waitUtilElementVisible(languageList.get(i));
                helper.clickBtn(languageList.get(i));
                helper.waitForJStoLoad();
                i++;
            }
        }
        actualRS = actualCheckLanguage;
        System.out.println(flag);
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkLanguageOfDefaultLabelPickup() {
        List<Boolean> flag = new ArrayList<>();
        String currentLanguage = getCurrentLanguage();
        System.out.println(currentLanguage);
        String checkedLanguage = currentLanguage;
        int index = 0;
        actualRS = "";
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        System.out.println("language " + language);
        List<WebElement> languageList = helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
        helper.waitForPresence(commonPagesTheme1().homePage.dialogContent);
        helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.dialogContent));
        if (languageList.get(0).getText().equals(language)) index = 1;
        else index = 0;
        commonPagesTheme1().homePage.clickLanguage();
        //check default language
        clickSelectStoreBranch();
        flag.add(checkLanguageOfDefaultBranchLabel(checkedLanguage, true, false));
        helper.refreshPage();
        languageList = helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            helper.waitForPresence(commonPagesTheme1().homePage.dialogContent);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme1().homePage.dialogContent));
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            String languageTemp = getCurrentLanguage();
            clickSelectStoreBranch();
            flag.add(checkLanguageOfDefaultBranchLabel(languageTemp, true, false));
            helper.refreshPage();
            helper.changeLanguage(commonPagesTheme1().homePage.languageSwitch, commonPagesTheme1().homePage.languageOptions);
            helper.checkDisplayElementByElement(driver.findElement(commonPagesTheme1().homePage.dialogContent));
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
}
