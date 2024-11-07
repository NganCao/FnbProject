package com.fnb.web.store.theme2.pages.addUserLocation;

import com.fnb.utils.api.storeweb.pos.helpers.APIPosService;
import com.fnb.utils.api.storeweb.pos.helpers.JsonAPIPosReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.HelperDataFaker;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.home.HomeDataTest;
import com.fnb.web.store.theme2.pages.login.DataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    public String expectedAddress = "";
    private String deliveryKeyLocale = "delivery";
    private String pickupKeyLocale = "toPickup";
    private HelperDataFaker faker;
    private JsonReader jsonReader;
    private List<Branch> oldBranchList;
    public Branch expectedBranch;
    //------------------ Element locator
    //summary xpath
    private String deliveryPanelXP = "//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-panel-1\")]";
    private String pickupPanelXP = "//div[contains(@id,\"rc-tabs-\") and contains(@id,\"-panel-4\")]";
    private By deliveryAddressSelector = By.xpath("//div[@class=\"delivery-address-selector-theme2\"]");
    //no select pickup/delivery address
    private By locatorIcon = By.xpath("//div[contains(@class,\"delivery-address-selector-theme2\")]/div/div[contains(@class,\"left-box\")]/div[contains(@class,\"img-box\")]//*[name()='svg']");
    private By locatorLabel = By.xpath("//div[contains(@class,\"delivery-address-selector-theme2\")]//div[contains(@class,\"select-branch-dialog-class\")]/span");
    private By locatorLabelDes = By.xpath("//div[contains(@class,\"delivery-address-selector-theme2\")]//div[contains(@class,\"select-branch-dialog-class\")]/div/span");
    private By locatorArrowIcon = By.xpath("//div[contains(@class,\"delivery-address-selector-theme2\")]/div[contains(@class,\"delivery-address-header-box\")]//div[contains(@class,\"right-box\")]/*[name()='svg']");
    private By locatorBackground = By.xpath("//div[contains(@class,\"delivery-address-selector-theme2\")]/div[contains(@class,\"delivery-address-header-box\")]");
    private By locatorDialog = By.xpath("//div[@role=\"dialog\" and contains(@class,\"modal-delivery-address-selector\")]");
    //popover content
    private String locationPopoverXP = "//div[contains(@class,\"popover-delivery-address-detail-theme2\")]";
    private By locationPopover = By.xpath(locationPopoverXP);
    private By deliveryContent = By.xpath(locationPopoverXP + "//div[contains(@class,\"top-content\")]");
    private By pickupContent = By.xpath(locationPopoverXP + "//div[contains(@class,\"bottom-content\")]");
    private String imgBox = ".//div[contains(@class,\"img-box\")]//*[name()='svg']"; //find from content
    private String addressLabel = ".//div[contains(@class,\"text-delivery-address\")]/span"; //find from content
    private String typeLabel = ".//span[contains(@class,\"text-delivery-to\")]"; //find from content
    private String arrowIcon = ".//span[contains(@class,\"icon\")]//*[name()='svg']"; //find from content
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
    private By addressListParent = By.xpath(deliveryPanelXP + "//div[@class=\"delivery-address-popover\"]");
    private By addressListItems = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-popover-item\")]");
    //my address
    private By myAddressContainer = By.xpath(deliveryPanelXP + "//div[contains(@class,\"my-address-container\")]"); //contains "show" when my address displays
    private By myAddressLabel = By.xpath(deliveryPanelXP + "//div[contains(@class,\"my-address-container\")]/div/span");
    private By myAddressItems = By.xpath(deliveryPanelXP + "//div[contains(@class,\"my-address-container\")]//div[contains(@class,\"address-detail-box\")]");
    private By myAddressNameList = By.xpath(deliveryPanelXP + "//div[contains(@class,\"my-address-container\")]//span[contains(@class,\"address-title\")]");
    private By myAddressTxtList = By.xpath(deliveryPanelXP + "//div[contains(@class,\"my-address-container\")]//span[contains(@class,\"address-detail-text\")]");
    private By myAddressIconList = By.xpath(deliveryPanelXP + "//div[contains(@class,\"my-address-container\")]//*[name()=\"svg\"]");
    //selected branch
    private By deliveryFromLabel = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-component\")]//div[contains(@class,\"header-change-branch\")]/span");
    private By moreBranchArrowIcon = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-component\")]//div[contains(@class,\"header-change-branch\")]/*[name()='svg']");
    private By storePicked = By.xpath(deliveryPanelXP + "//div[contains(@class,\"address-store-picked\")]");
    private String storePickedIcon = ".//*[name()=\"svg\"]"; //finds from storePicked
    private String storePickedName = ".//div[@class=\"store-title\"]/span"; //finds from storePicked
    private String storePickedCheckedIcon = ".//div[@class=\"store-infomation\"]//*[name()=\"svg\"]"; //finds from storePicked
    private String storePickedAddress = ".//div[@class=\"store-content\"]/span[1]"; //finds from storePicked
    private String storePickedDistance = ".//span[@class=\"distance\"]"; //finds from storePicked
    //delivery panel
    private By deliveryPanel = By.xpath(deliveryPanelXP);
    //select the other store branch
    private String deliveryOtherBranchXP = deliveryPanelXP + "//div[contains(@class,\"store-branch-component\")]";
    private By deliveryOtherStoreBranch = By.xpath(deliveryOtherBranchXP); //contains "show" when select other branch displays after clicking more branch arrow
    private By deliveryOtherBranchLabel = By.xpath(deliveryOtherBranchXP + "/div/span");
    private By deliveryOtherBranchBackArrow = By.xpath(deliveryOtherBranchXP + "/div/*[name()=\"svg\"]");
    private By deliveryOtherSelectBranchNearestLabel = By.xpath("(" + deliveryPanelXP + "//div[@class=\"text-branch-info\"])[1]");
    private By deliveryOtherStoreBranchItems = By.xpath(deliveryPanelXP + "//div[contains(@class,\"store-branch-address-selector\")]//label");
    private By deliveryOtherDefaultBranch = By.xpath(deliveryPanelXP + "//label[contains(@class,\"ant-radio-wrapper-checked\")]");
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
    private String branchIconNumber = ".//div[@class=\"address-icon\"]//*[name()=\"svg\"]/following-sibling::span/span";
    private String branchName = ".//p[@class=\"address-label\"]";
    private String branchAddress = ".//p[@class=\"address\"]";
    private String branchDistance = ".//p[@class=\"distance\"]";
    private String branchCheckedIconByItem = "./parent::label/span[contains(@class,\"ant-radio-checked\")]"; //finds from pickup item - checked
    private String branchDeliveryCheckedIconByItem = "./span[contains(@class,\"ant-radio-checked\")]"; //finds from delivery item - checked
    private String branchRadioCheckByItem = "./span[contains(@class,\"ant-radio\")]"; //finds from item
    private String branchCheckedIcon = ".//label[contains(@class,\"ant-radio-wrapper-checked\")]//span[contains (@class,\"ant-radio-checked\")]"; //finds from panel
    private By pickupBranchDistanceList = By.xpath(pickupPanelXP + "//label/span[2]//p[@class=\"distance\"]");
    private By pickupBranchNameList = By.xpath(pickupPanelXP + "//label/span[2]//p[@class=\"address-label\"]");

    public AddUserLocationPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        this.driver = driver;
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
    }

    //check display
    public Boolean checkDisplayAddLocationComponent() {
        helper.waitForPresence(deliveryAddressSelector);
        helper.pressPageUpAction();
        helper.visibleOfLocated(deliveryAddressSelector);
        return helper.checkDisplayElement(deliveryAddressSelector);
    }

    public Boolean checkDisplayAddLocationIcon() {
        return helper.checkDisplayElement(locatorIcon);
    }

    public Boolean checkDisplayAddLocationLabel() {
        return helper.checkDisplayElement(locatorLabel);
    }

    public Boolean checkDisplayAddLocationLabelDes() {
        return helper.checkDisplayElement(locatorLabelDes);
    }

    public Boolean checkDisplayAddLocationArrowIcon() {
        return helper.checkDisplayElement(locatorArrowIcon);
    }

    //Popover
    public Boolean checkDisplayOfPopover() {
        return helper.checkDisplayElement(locationPopover);
    }

    private Boolean checkDisplayOfPopoverContent() {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (helper.checkDisplayElement(locationPopover)) {
            //delivery content
            try {
                if (helper.checkDisplayElement(deliveryContent)) {
                    flag.add(true);
                    WebElement delivery = helper.getElement(deliveryContent);
                    try {
                        if (helper.checkDisplayElementByElement(delivery.findElement(By.xpath(imgBox)))) {
                            flag.add(true);
                        } else {
                            actualRS = actualRS + " Delivery icon on popover did not display.\n";
                            flag.add(false);
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        actualRS = actualRS + "Can find delivery icon box\n";
                        flag.add(false);
                    }
                    try {
                        if (helper.checkDisplayElementByElement(delivery.findElement(By.xpath(typeLabel)))) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + " Delivery type label on popover did not display.\n";
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(false);
                        actualRS = actualRS + "Can find delivery type label box\n";
                    }
                    try {
                        if (helper.checkDisplayElementByElement(delivery.findElement(By.xpath(addressLabel)))) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + " Delivery address detail on popover did not display.\n";
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(false);
                        actualRS = actualRS + "Can find delivery address detail box\n";
                    }
                    try {
                        if (helper.checkDisplayElementByElement(delivery.findElement(By.xpath(arrowIcon)))) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + " Delivery arrow icon on popover did not display.\n";
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(false);
                        actualRS = actualRS + "Can find delivery arrow icon box\n";
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + " Delivery content box on popover did not display.\n";
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can find delivery icon box\n";
            }
            //pickup content
            try {
                if (helper.checkDisplayElement(pickupContent)) {
                    flag.add(true);
                    WebElement pickup = helper.getElement(pickupContent);
                    try {
                        if (helper.checkDisplayElementByElement(pickup.findElement(By.xpath(imgBox)))) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + " Pickup icon on popover did not display.\n";
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(false);
                        actualRS = actualRS + "Can find Pickup icon box\n";
                    }
                    try {
                        if (helper.checkDisplayElementByElement(pickup.findElement(By.xpath(typeLabel)))) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + " Delivery type label on popover did not display.\n";
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(false);
                        actualRS = actualRS + "Can find Pickup type label box\n";
                    }
                    try {
                        if (helper.checkDisplayElementByElement(pickup.findElement(By.xpath(addressLabel)))) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + " Pickup address detail on popover did not display.\n";
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(false);
                        actualRS = actualRS + "Can find Pickup address detail box\n";
                    }
                    try {
                        if (helper.checkDisplayElementByElement(pickup.findElement(By.xpath(arrowIcon)))) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + " Pickup arrow icon on popover did not display.\n";
                        }
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(false);
                        actualRS = actualRS + "Can find Pickup arrow icon box\n";
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + " Pickup content box on popover did not display.\n";
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can find Pickup icon box\n";
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfLocationPopoverDetails() {
        List<Boolean> flag = new ArrayList<>();
        helper.refreshPage();
        actualRS = "";
        String actualRSTemp = "0. Click locator box: \n";
        try {
            helper.visibleOfLocated(deliveryAddressSelector);
            helper.clickBtn(driver.findElement(deliveryAddressSelector));
            flag.add(checkDisplayOfPopoverContent());
            actualRSTemp = actualRSTemp + "Popover did not display\n";
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRSTemp = actualRSTemp + "Cannot click on deliveryAddressSelector\n";
        }
        helper.refreshPage();
        actualRSTemp = actualRSTemp + "1. Click locator label: \n";
        try {
            helper.visibleOfLocated(locatorLabel);
            helper.clickBtn(driver.findElement(locatorLabel));
            if (checkDisplayOfPopoverContent()) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRSTemp = actualRSTemp + "Popover did not display\n";
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRSTemp = actualRSTemp + "Cannot click on locator label\n";
        }
        helper.refreshPage();
        actualRSTemp = actualRSTemp + "2. click locator description: \n";
        try {
            helper.visibleOfLocated(locatorLabelDes);
            helper.clickBtn(driver.findElement(locatorLabelDes).findElement(By.xpath("./parent::div")));
            if (checkDisplayOfPopoverContent()) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRSTemp = actualRSTemp + "Popover did not display\n";
            }
            actualRSTemp = actualRSTemp + "Popover did not display\n";
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRSTemp = actualRSTemp + "Cannot click on locator label description\n";
        }
        helper.refreshPage();
        actualRSTemp = actualRSTemp + "3. click locator icon: \n";
        try {
            helper.visibleOfLocated(locatorIcon);
            helper.clickBtn(driver.findElement(locatorIcon).findElement(By.xpath("./parent::div")));
            if (checkDisplayOfPopoverContent()) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRSTemp = actualRSTemp + "Popover did not display\n";
            }
            actualRSTemp = actualRSTemp + "Popover did not display\n";
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRSTemp = actualRSTemp + "Cannot click on locator icon\n";
        }
        actualRSTemp = actualRSTemp + "4. click locator arrow icon: \n";
        helper.refreshPage();
        try {
            helper.visibleOfLocated(locatorArrowIcon);
            helper.clickBtn(driver.findElement(locatorArrowIcon).findElement(By.xpath("./parent::div")));
            if (checkDisplayOfPopoverContent()) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRSTemp = actualRSTemp + "Popover did not display\n";
            }
            actualRSTemp = actualRSTemp + "Popover did not display\n";
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRSTemp = actualRSTemp + "Cannot click on locator arrow icon\n";
        }
        actualRS = actualRSTemp;
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayDialogAfterClickContentPopover(Boolean isDelivery) {
        List<Boolean> flag = new ArrayList<>();
        helper.refreshPage();
        WebElement content;
        By xpath;
        clickLocationArrowIcon();
        String type = "";
        if (isDelivery) {
            actualRS = "========== DELIVERY ==========\n";
            clickDeliveryPopover();
            xpath = deliveryContent;
            type = "delivery";
        } else {
            actualRS = "========== PICKUP ==========\n";
            clickPickupPopover();
            xpath = pickupContent;
            type = "pickup";
        }
        actualRS = actualRS + "1. Click " + type + " content box: \n";
        try {
            if (isDelivery) flag.add(checkDisplayOfDeliveryPanel());
            else flag.add(checkDisplayOfPickupPanel());
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRS = actualRS + "Cannot click " + type + " content box\n";
        }
        helper.refreshPage();
        clickLocationArrowIcon();
        actualRS = actualRS + "2. click locator icon on popover: \n";
        content = driver.findElement(xpath);
        try {
            helper.checkDisplayElementByElement(content.findElement(By.xpath(imgBox)));
            WebElement e = content.findElement(By.xpath(imgBox));
            helper.clickBtn(e);
            if (isDelivery) {
                flag.add(checkDisplayOfDeliveryPanel());
            } else {
                flag.add(checkDisplayOfPickupPanel());
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRS = actualRS + "Cannot click on locator icon\n";
        }
        helper.refreshPage();
        clickLocationArrowIcon();
        content = helper.getElement(xpath);
        actualRS = actualRS + "3. click locator label on popover: \n";
        try {
            helper.checkDisplayElementByElement(content.findElement(By.xpath(typeLabel)));
            WebElement e = content.findElement(By.xpath(typeLabel));
            helper.clickBtn(e);
            if (isDelivery) {
                flag.add(checkDisplayOfDeliveryPanel());
            } else {
                flag.add(checkDisplayOfPickupPanel());
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRS = actualRS + "Cannot click on type label on popover\n";
        }
        helper.refreshPage();
        clickLocationArrowIcon();
        content = helper.getElement(xpath);
        actualRS = actualRS + "4. click address label on popover: \n";
        try {
            helper.checkDisplayElementByElement(content.findElement(By.xpath(addressLabel)));
            WebElement e = content.findElement(By.xpath(addressLabel));
            helper.clickBtn(e);
            if (isDelivery) {
                flag.add(checkDisplayOfDeliveryPanel());
            } else {
                flag.add(checkDisplayOfPickupPanel());
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRS = actualRS + "Cannot click on address label on popover\n";
        }
        helper.refreshPage();
        clickLocationArrowIcon();
        content = helper.getElement(xpath);
        actualRS = actualRS + "5. click locator label on popover: \n";
        try {
            helper.checkDisplayElementByElement(content.findElement(By.xpath(arrowIcon)));
            WebElement e = content.findElement(By.xpath(arrowIcon));
            helper.clickBtn(e);
            if (isDelivery) {
                flag.add(checkDisplayOfDeliveryPanel());
            } else {
                flag.add(checkDisplayOfPickupPanel());
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualRS = actualRS + "Cannot click on arrow icon on popover\n";
        }
        if (flag.contains(false)) return false;
        else return true;
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
        WebElement store = helper.getElement(storePicked);
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
                Log.info(exception.getMessage());
                flag.add(true);
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
                if (isDefault) {
                    flag.add(true);
                } else {
                    flag.add(false);
                    actualRS = actualRS + "Can get/find store picked distance element.\n";
                }
            }
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(storePickedCheckedIcon)));
                if (checked) actualRS = actualRS + "Store picked checked icon is displaying.\n";
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(true);
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            actualRS = "Picked store section did not display";
            return false;
        }
    }

    //Check value

    /**
     * After entering address on delivery or selecting branch, change to address / branch name
     *
     * @param addressIndex
     * @return
     */
    public Boolean checkDisplayOfAddressLabelAfterSelectMyAddress(int addressIndex) {
        expectedAddress = helper.selectOptionDropdown(myAddressTxtList, addressIndex);
        helper.visibleOfLocated(locatorLabelDes);
        return expectedAddress.contains(driver.findElement(locatorLabelDes).getText().trim());
    }

    public Boolean checkValueOfEnterFieldAfterSelected(String addressLabel) {
        helper.refreshPage();
        clickLocationArrowIcon();
        clickSelectAddress();
        checkDisplayOfDeliveryInput();
        String inputTxt = helper.getAttribute(enterAddressField, "value");
        actualRS = "Wrong address. Actual: \"" + inputTxt + "\"\nExpected: \"" + addressLabel + "\"";
        return inputTxt.equalsIgnoreCase(addressLabel);
    }

    public Boolean checkValueOfPopoverAfterChangeAddress(Boolean isDelivery, String address) {
        helper.refreshPage();
        String language = getCurrentLanguage();
        List<Boolean> flag = new ArrayList<>();
        List<String> keyList = new ArrayList<>();
        actualRS = "";
        WebElement content, typeElement, addressElement;
        clickLocationArrowIcon();
        if (isDelivery) {
            keyList.add("deliveryTo");
            Branch selectedBranch = getPickedBranch(address);
            helper.visibleOfLocated(deliveryContent);
            content = helper.getElement(deliveryContent);
            //check delivery
            typeElement = content.findElement(By.xpath(typeLabel));
            String label = typeElement.getText();
            addressElement = content.findElement(By.xpath(addressLabel));
            String detail = addressElement.getText();
            if (helper.checkTextWithLanguage(language, typeElement, "text", addUserLocationDataTest.PAGE, keyList)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + "Wrong type label. Actual: " + label + " Expected: " + helper.expectedLanguage + ".\n";
            }
            if (detail.equals(address)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + "Wrong delivery address label. Actual: " + detail + " Expected: " + address + ".\n";
            }
            //check pickup
            content = helper.getElement(pickupContent);
            typeElement = content.findElement(By.xpath(typeLabel));
            String branchLabel = typeElement.getText();
            addressElement = content.findElement(By.xpath(addressLabel));
            String branchAddress = addressElement.getText();
            keyList.clear();
            keyList.add("branch");
            if (helper.checkTextWithLanguage(language, typeElement, "text", addUserLocationDataTest.PAGE, keyList)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + "Wrong branch label. Actual: " + branchLabel + " Expected: " + helper.expectedLanguage + ".\n";
            }
            if (branchAddress.equals(selectedBranch.getBranchAddress())) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + "Wrong branch address label. Actual: " + branchAddress + " Expected: " + selectedBranch.getBranchAddress() + "\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        } else {
            //pickup
            keyList.clear();
            keyList.add("branch");
            //clear delivery
            address = expectedAddress;
            helper.visibleOfLocated(pickupContent);
            content = helper.getElement(pickupContent);
            //check delivery
            typeElement = content.findElement(By.xpath(typeLabel));
            String label = typeElement.getText();
            addressElement = content.findElement(By.xpath(addressLabel));
            String detail = addressElement.getText();
            if (helper.checkTextWithLanguage(language, typeElement, "text", addUserLocationDataTest.PAGE, keyList)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + "Wrong type label. Actual: " + label + " Expected: " + helper.expectedLanguage + ".\n";
            }
            if (detail.equals(address)) {
                flag.add(true);
            } else {
                flag.add(false);
                actualRS = actualRS + "Wrong delivery address label. Actual: " + detail + " Expected: " + address + ".\n";
            }
            if (flag.contains(false)) return false;
            else return true;
        }
    }

    private Boolean checkBranchPickupWithIndex(By branchItemXP, int index) {
        clickSelectStoreBranch();
        helper.visibleOfLocated(pickupPanel);
        helper.waitForVisibleAllElements(branchItemXP);
        String oldBranch = driver.findElements(branchItemXP).get(index).findElement(By.xpath(branchAddress)).getText();
        selectAnotherStoreByIndex(branchItemXP, index);
        helper.visibleOfLocated(locatorLabelDes);
        expectedAddress = oldBranch;
        return oldBranch.equalsIgnoreCase(expectedAddress);
    }

    private Boolean checkOtherBranchPickupWithIndex(By branchItemXP, int index) {
        clickSelectStoreBranch();
        helper.visibleOfLocated(deliveryOtherStoreBranch);
        helper.waitForVisibleAllElements(deliveryOtherNoActiveItems);
        String oldBranch = driver.findElements(deliveryOtherNoActiveItems).get(index).findElement(By.xpath(branchAddress)).getText();
        selectAnotherStoreByIndex(branchItemXP, index);
        helper.visibleOfLocated(locatorLabelDes);
        return oldBranch.equalsIgnoreCase(expectedAddress);
    }

    /**
     * @param type               (delivery, pickup get from addUserLocationDataTest)
     * @param enterAddressBranch
     * @param address
     * @param index
     * @return
     */
    public Boolean checkDisplayOfDeliveryButtonAfterSelectLocation(String type, Boolean isDefault, Boolean enterAddressBranch, String address, int index) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (type.equalsIgnoreCase("delivery")) {
            if (enterAddressBranch) {
                clickDelivery();
                onlyFillDeliveryAddress(address, index);
            }
            //check label
            flag.add(checkLabelAfterSelectAddress(true, address));
            //check details
            helper.visibleOfLocated(locatorLabelDes);
            helper.waitForTextToBe(driver.findElement(locatorLabelDes), address);
            String actualType = driver.findElement(locatorLabelDes).getText();
            String expectedLabel = address;
            System.out.println(expectedLabel);
            flag.add(actualType.equals(expectedLabel));
            actualRS = actualRS + "\nWrong text on selected type button. Actual: " + actualType + " Expected: " + expectedLabel;
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
                flag.add(checkLabelAfterSelectAddress(false, expectedAddress));
            }
            checkLabelAfterClickingOnPickupTab(isDefault);
        } else {
            //Show default label -> ??
            flag.add(false);
            actualRS = actualRS + "Type button still display";
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * @param index
     * @return
     */
    public Boolean checkDisplayAfterSelectBranch(int index) {
        List<WebElement> list = helper.getElementList(pickupBranchNoActiveItems);
        String address = "";
        try {
            address = list.get(index).findElement(By.xpath(branchAddress)).getText().trim();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPageByActions();
            clickSelectStoreBranch();
            helper.getElementList(pickupBranchNoActiveItems);
            address = list.get(index).findElement(By.xpath(branchAddress)).getText().trim();
        }
        expectedAddress = address;
        expectedBranch = setBranchInformation(list.get(index));
        helper.clickBtn(list.get(index));
        helper.visibleOfLocated(locatorLabelDes);
        actualRS = driver.findElement(locatorLabelDes).getText().trim();
        if (address.equalsIgnoreCase(actualRS)) {
            return true;
        } else {
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

    /**
     * On delivery panel
     *
     * @param isDefault
     * @return
     */
    public Boolean checkDisplayOfOtherBranchDefaultInformation(Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        WebElement store = helper.getElement(deliveryOtherDefaultBranch);
        Boolean checked;
        actualRS = "";
        if (checkDisplayOfDeliveryOtherBranch()) {
            helper.scrollByJS(store);
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
            //todo can find checked icon radio
            try {
                checked = helper.checkDisplayElementByElement(store.findElement(By.xpath(branchDeliveryCheckedIconByItem)));
                if (!checked) actualRS = actualRS + "Delivery other branch checked icon did not display.\n";
                flag.add(checked);
            } catch (Exception exception) {
                Log.error(exception.getMessage());
                flag.add(false);
                actualRS = actualRS + "Can get/find Delivery other branch checked icon element.\n";
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
        List<WebElement> branchList = helper.getElementList(deliveryOtherStoreBranchItems);
        Boolean checked;
        actualRS = "";
        if (checkDisplayOfDeliveryOtherBranch()) {
            for (int i = 1; i < branchList.size(); i++) {
                helper.scrollByJS(branchList.get(i));
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchName)));
                    if (!checked) {
                        actualRS = actualRS + "Position " + i + " Delivery other branch Name did not display.\n";
                    }
                    flag.add(checked);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Position " + i + " Can get/find Delivery other branch name element.\n";
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchAddress)));
                    if (!checked) {
                        actualRS = actualRS + "Position " + i + " Delivery other branch address did not display.\n";
                    }
                    flag.add(checked);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Position " + i + " Can get/find Delivery other branch address element.\n";
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchIcon)));
                    if (!checked)
                        actualRS = actualRS + "Position " + i + " Delivery other branch icon did not display.\n";
                    flag.add(checked);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Position " + i + " Can get/find Delivery other branch icon element.\n";
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchDistance)));
                    if (isDefault) {
                        if (!checked) {
                            flag.add(true); //if the branch is default, all distances are 0,0km, page won't show distance list
                        } else {
                            flag.add(false);
                            actualRS = actualRS + "Position " + i + " Store picked distance is displaying when branch is default.\n";
                        }
                    } else {
                        if (checked) {
                            flag.add(true);
                        } else {
                            flag.add(false);
                            actualRS = actualRS + "Position " + i + " Store picked distance did not display.\n";
                        }
                    }
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Position " + i + " Can get/find Delivery other branch distance element.\n";
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchIconNumber)));
                    if (!checked) {
                        if (i == 0) flag.add(true); //default branch but it wont run, i starts from 1
                        else {
                            flag.add(false);
                            actualRS = actualRS + "Position " + i + " Delivery other branch icon number did not display.\n";
                        }
                    } else {
                        if (i == 0) {
                            actualRS = actualRS + "Position " + i + " Delivery default branch icon has number.\n";
                            flag.add(false);
                        } else {
                            flag.add(true);
                        }
                    }
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    if (i == 0) flag.add(true);
                    else {
                        flag.add(false);
                        actualRS = actualRS + "Position " + i + " Can get/find Delivery other branch number on icon element.\n";
                    }
                }
                try {
                    checked = helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchRadioCheckByItem)));
                    if (!checked) actualRS = actualRS + "Position " + i + " Radio checked icon did not display.\n";
                    flag.add(checked);
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    flag.add(false);
                    actualRS = actualRS + "Position " + i + " Can get/find the radio checked icon element.\n";
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

    private Boolean checkDisplayOfBranchListDetails(By xpath) {
        int size = helper.getElementList(xpath).size();
        if (size == Integer.parseInt(expectedRS)) {
            return true;
        } else {
            actualRS = String.valueOf(size);
            return false;
        }
    }

    //default branch
    public Boolean checkDisplayOfDefaultBranchItem() {
        return helper.checkDisplayElement(pickupDefaultBranch);
    }

    public Boolean checkDisplayOfDeliveryDefaultBranchItem() {
        return helper.checkDisplayElement(deliveryOtherDefaultBranch);
    }

    public Boolean checkDisplayOfDefaultBranchIcon() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getElement(pickupDefaultBranch);
            actualRS = "Default branch icon did not display";
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchIcon)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfDefaultNumberIcon() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getElement(pickupDefaultBranch);
            return !helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchIconNumber)));
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
            WebElement defaultBranch = helper.getElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchName)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfDefaultBranchDistance() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchDistance)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfDefaultBranchAddress() {
        actualRS = "";
        if (checkDisplayOfDefaultBranchItem()) {
            WebElement defaultBranch = helper.getElement(pickupDefaultBranch);
            return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchAddress)));
        } else {
            actualRS = "Can not find default branch icon";
            return false;
        }
    }

    public Boolean checkDisplayOfDefaultBranchChecked(Boolean isDelivery) {
        actualRS = "";
        By xpath;
        if (isDelivery) {
            xpath = deliveryOtherDefaultBranch;
            if (checkDisplayOfDeliveryOtherBranch()) {
                WebElement defaultBranch = helper.getElement(xpath);
                helper.scrollByJS(defaultBranch);
                return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchDeliveryCheckedIconByItem)));
            } else {
                actualRS = "Delivery store branch did not display";
                return false;
            }
        } else {
            xpath = pickupDefaultBranch;
            if (checkDisplayOfDefaultBranchItem()) {
                WebElement defaultBranch = helper.getElement(xpath);
                helper.scrollByJS(defaultBranch);
                return helper.checkDisplayElementByElement(defaultBranch.findElement(By.xpath(branchCheckedIconByItem)));
            } else {
                actualRS = "Pickup store branch did not display";
                return false;
            }
        }
    }

    //all items
    public Boolean checkDisplayOfBranchListItem() {
        helper.presenceOfAllElementsLocatedBy(pickupBranchItems);
        int size = helper.getElementList(pickupBranchItems).size();
        actualRS = String.valueOf(size);
        if (size >= 0) {
            expectedRS = actualRS;
            actualRS = "";
            return true;
        } else {
            actualRS = actualRS + "item(s)\n";
            return false;
        }
    }

    public Boolean checkDisplayOfBranchListIcon() {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        if (checkDisplayOfBranchListItem()) {
            List<WebElement> branches = helper.getElementList(pickupBranchItems);
            for (int i = 1; i < branches.size(); i++) {
                if (i > 2) helper.scrollByJS(branches.get(i));
                if (helper.checkDisplayElementByElement(branches.get(i).findElement(By.xpath(branchIcon))))
                    flag.add(true);
                else {
                    flag.add(false);
                    actualRS = actualRS + i + ". Branch icon did not display\n";
                }
                if (helper.checkDisplayElementByElement(branches.get(i).findElement(By.xpath(branchIconNumber)))) {
                    flag.add(true);
                    if (helper.checkText(branches.get(i).findElement(By.xpath(branchIconNumber)), String.valueOf(i)))
                        flag.add(true);
                    else {
                        actualRS = actualRS + i + ". Branch icon number display wrong. Actual: " + branches.get(i).findElement(By.xpath(branchIconNumber)).getText() + " Expected: " + i + "\n";
                        flag.add(false);
                    }
                } else {
                    flag.add(false);
                    actualRS = actualRS + i + ". Branch icon number did not display\n";
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
                if (i > 2) helper.scrollByJS(branchList.get(i));
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
        List<Boolean> flag = new ArrayList<>();
        if (checkDisplayOfBranchListItem()) {
            List<WebElement> branchList = helper.getElementList(pickupBranchItems);
            for (int i = 1; i < branchList.size(); i++) {
                helper.scrollByJS(branchList.get(i));
                if (helper.checkDisplayElementByElement(branchList.get(i).findElement(By.xpath(branchDistance))))
                    flag.add(true);
                else {
                    actualRS = actualRS + "Branch distance at " + i + 1;
                    flag.add(false);
                }
            }
            if (isDefault) {
                if (flag.contains(true)) {
                    actualRS = actualRS + "\n---- Message: Distances are displaying with default branch list.\n";
                    return false;
                } else return true;
            } else {
                if (flag.contains(false)) {
                    actualRS = actualRS + "---- Message: Distances did not display.\n";
                    return false;
                } else return true;
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
                helper.scrollByJS(branchList.get(i));
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


    //check value
    public Boolean checkValueOfDefaultBranchWithoutAddressOnPickup() {
        Branch nearestBranch = getDefaultBranchWithoutAddress();
        return checkValueOfPickupStore(nearestBranch);
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
        try {
            helper.presenceOfAllElementsLocatedBy(addressListItems);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
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

    public Boolean checkLabelAfterSelectAddress(Boolean isDelivery, String address) {
        List<Boolean> flag = new ArrayList<>();
        helper.visibleOfLocated(locatorLabel);
        String actualStr = helper.getText(locatorLabel);
        if (actualStr.equals(getTypeLabelFollowingLanguage(isDelivery))) {
            flag.add(true);
        } else {
            actualRS = "Wrong label. Actual: " + actualStr + " Expected: " + getTypeLabelFollowingLanguage(isDelivery) + "\n";
            flag.add(false);
        }
        helper.visibleOfLocated(locatorLabelDes);
        actualStr = helper.getText(locatorLabelDes);
        if (actualStr.equals(address)) {
            flag.add(true);
        } else {
            actualRS = "Wrong address label. Actual: " + actualStr + " Expected: " + address + "\n";
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    //distance
    public String getDistanceFormatFollowingDesign(String distanceNumber) {
        return (distanceNumber.replace('.', ',') + " km");
    }

    public Boolean checkValueOfPickedStore(Branch nearestBranch, Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        WebElement store = helper.getElement(storePicked);
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

    public Boolean checkPickedStoreAfterChangeStore(int branchIndex, Boolean isDefault) {
        Branch selectedBranch = new Branch();
        //get branch information at index
        List<WebElement> branchList = helper.getElementList(deliveryOtherStoreBranchItems);
        WebElement branch = branchList.get(branchIndex);
        selectedBranch = setBranchInformation(branch);
        expectedBranch = selectedBranch;
        branch.click();
        clickSelectAddress();
        clickLocationArrowIcon();
        return checkValueOfPickedStore(selectedBranch, isDefault);
    }

    public Boolean checkValueOfPickupAfterChangeBranch() {
        if (expectedBranch != null) {
            //check nearest store
            if (!checkDisplayOfPickupPanel()) {
                clickPickup();
            }
            helper.visibleOfLocated(pickupDefaultBranch);
            if (checkValueOfPickupStore(expectedBranch)) {
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

    public void getOldBranchListPickup(Boolean isDelivery) {
        if (isDelivery) getOldBranchList(deliveryOtherStoreBranchItems);
        else getOldBranchList(pickupBranchItems);
        for (Branch branch : oldBranchList) {
            System.out.println(branch.getBranchName());
        }
    }

    //delivery other branch
    public Boolean checkValueOfDeliveryOtherBranch(String address) {
        if (checkDisplayOfDeliveryOtherBranch()) {
            return checkValueOfDeliveryBranchAllItems(address);
        } else {
            actualRS = "Delivery store section did not display";
            return false;
        }
    }

    public Boolean checkValueOfNearestDeliveryBranchAfterChangeBranch() {
        if (expectedBranch != null) {
            //check nearest store
            if (!checkDisplayOfDeliveryPanel()) {
                clickDelivery();
            }
            clickMoreBranchArrow();
            helper.visibleOfLocated(deliveryOtherDefaultBranch);
            if (checkValueOfDeliveryNearestBranch(expectedBranch)) {
                return true;
            } else return false;
        } else {
            actualRS = "The selected branch is null";
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

    //check value of pickup
    public Boolean checkLabelAfterClickingOnPickupTab(Boolean isDefault) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        String branchStr = "";
        String expectedLabel = getTypeLabelFollowingLanguage(false);
        if (isDefault) branchStr = getDefaultBranchWithoutAddress().getBranchAddress();
        else {
            branchStr = driver.findElement(pickupSelectedBranch).findElement(By.xpath(branchAddress)).getText();
        }
        expectedRS = branchStr;
        checkOutsideDialog();
        helper.visibleOfLocated(locatorLabel);
        helper.waitForTextToBePresent(locatorLabel, expectedLabel);
        String str = "";
        str = helper.getText(locatorLabel);
        Boolean checkedStr = str.equals(expectedLabel);
        if (checkedStr)
            actualRS = actualRS + "\nWrong text on selected type button. Actual: \"" + str + "\" Expected: \"" + expectedLabel + "\"\n";
        flag.add(checkedStr);
        helper.visibleOfLocated(locatorLabelDes);
        helper.waitForTextToBePresent(locatorLabelDes, branchStr);
        str = helper.getText(locatorLabelDes);
        checkedStr = str.equals(branchStr);
        System.out.println(str);
        System.out.println(branchStr);
        if (checkedStr)
            actualRS = actualRS + "\nWrong text on selected type button. Actual: \"" + str + "\" Expected: \"" + branchStr + "\"\n";
        flag.add(checkedStr);
        if (flag.contains(false)) {
            return false;
        } else return true;
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

    private Boolean checkValueOfPickupStore(Branch nearestBranch) {
        System.out.println(nearestBranch.getBranchName());
        List<Boolean> flag = new ArrayList<>();
        helper.visibleOfLocated(pickupDefaultBranch);
        WebElement store = helper.getElement(pickupDefaultBranch);
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
        Boolean check = helper.checkDisplayElement(enterAddressField);
        if (check == false) {
            clickDelivery();
            check = helper.checkDisplayElement(enterAddressField);
        }
        return check;
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
        return helper.checkDisplayElement(myAddressLabel);
    }

    public Boolean checkDisplayAddressListIcon() {
        int addressListSize = helper.getElementList(myAddressItems).size();
        int addressIconListSize = helper.getElementList(myAddressIconList).size();
        if (addressIconListSize == addressListSize) {
            return true;
        } else {
            actualRS = "Actual: " + addressIconListSize + " Expected: " + addressListSize;
            return false;
        }
    }

    public Boolean checkDisplayAddressListName() {
        int addressListSize = helper.getElementList(myAddressItems).size();
        int addressIconListSize = helper.getElementList(myAddressNameList).size();
        if (addressIconListSize == addressListSize) {
            return true;
        } else {
            actualRS = "Actual: " + addressIconListSize + " Expected: " + addressListSize;
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

    public Boolean checkFontSizeAddLocationLabelDes(String fontSize) {
        actualRS = helper.getCSSValue(locatorLabelDes, "font-size");
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
            actualRS = String.valueOf(heightComponent);
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

    public Boolean checkFontSizeOfDefaultBranchDistance(String expected) {
        actualRS = driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchDistance)).getCssValue("font-size");
        return actualRS.equals(expected);
    }

    public Boolean checkFontWeightOfDefaultBranchDistance(String expected) {
        actualRS = driver.findElement(pickupDefaultBranch).findElement(By.xpath(branchDistance)).getCssValue("font-weight");
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
        WebElement defaultBranch = helper.getElement(pickupDefaultBranch);
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

    public Boolean checkAlignOfAddLocationDialog(String align) {
        helper.waitUtilElementVisible(helper.getElement(locatorDialog));
        actualRS = helper.getCSSValue(locatorDialog, "vertical-align");
        if (actualRS.equals(align)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkColorOfDelivery(String color) {
        WebElement element = driver.findElement(deliveryTab).findElement(By.xpath("./parent::div"));
        actualRS = "Wrong delivery color. Actual: ";
        actualRS = Color.fromString(element.getCssValue("background-color")).asHex();
        if (actualRS.equals(color)) {
            return true;
        } else {
            actualRS = actualRS + " Excpected: " + color;
            return false;
        }
    }

    /**
     * @param color
     * @return
     */
    public Boolean checkColorOfPickup(String color) {
        WebElement element = driver.findElement(pickupTab).findElement(By.xpath("./parent::div"));
        String colorStr = Color.fromString(element.getCssValue("background-color")).asHex();
        if (colorStr.equals(color)) {
            return true;
        } else {
            actualRS = "Wrong pickup color. Actual: " + colorStr + " Excpected: " + color;
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
                actualRS = actualRS + " " + i + ": " + c + "\n";
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
            c = list.get(i).findElement(By.xpath(branchAddress)).getCssValue("font-weight");
            if (!c.equals(expected)) {
                countFail++;
                actualRS = actualRS + " " + i + ": " + c + "\n";
            }
        }
        if (countFail > 0) {
            return false;
        } else return true;
    }

    public Boolean checkFontSizeOfOtherBranchListAddress(String expected) {
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String c;
        List<Boolean> flag = new ArrayList<>();
        actualRS = "Wrong at positions:\n";
        for (int i = 1; i < list.size(); i++) {
            helper.scrollByJS(list.get(i));
            c = list.get(i).findElement(By.xpath(branchAddress)).getCssValue("font-size");
            if (!c.equals(expected)) {
                flag.add(false);
                actualRS = actualRS + " " + i + ": " + c + "\n";
            } else flag.add(true);
        }
        if (flag.add(false)) {
            return false;
        } else return true;
    }

    public Boolean checkFontWeightOfOtherBranchListDistance(String expected) {
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String c;
        int countFail = 0;
        actualRS = "Wrong at positions:";
        for (int i = 2; i < list.size(); i++) {
            c = list.get(i).findElement(By.xpath(branchDistance)).getCssValue("font-weight");
            if (!c.equals(expected)) {
                countFail++;
                actualRS = actualRS + " " + i + ": " + c + "\n";
            }
        }
        if (countFail > 0) {
            return false;
        } else return true;
    }

    public Boolean checkFontSizeOfOtherBranchListDistance(String expected) {
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String c;
        int countFail = 0;
        actualRS = "Wrong at positions:";
        for (int i = 2; i < list.size(); i++) {
            c = list.get(i).findElement(By.xpath(branchDistance)).getCssValue("font-size");
            if (!c.equals(expected)) {
                countFail++;
                actualRS = actualRS + " " + i + ": " + c + "\n";
            }
        }
        if (countFail > 0) {
            return false;
        } else return true;
    }

    public Boolean checkFontWeightOfOtherBranchListName(String expected) {
        List<WebElement> list = helper.getElementList(pickupBranchItems);
        String c;
        int countFail = 0;
        actualRS = "Wrong at positions:";
        for (int i = 2; i < list.size(); i++) {
            c = list.get(i).findElement(By.xpath(branchName)).getCssValue("font-weight");
            if (!c.equals(expected)) {
                countFail++;
                actualRS = actualRS + " " + i + ": " + c + "\n";
            }
        }
        if (countFail > 0) {
            return false;
        } else return true;
    }

    //actions
    private String getCurrentLanguage() {
        return commonPagesTheme2().homePage.getCurrentLanguage();
    }

    private String getTypeLabelFollowingLanguage(Boolean isDelivery) {
        String currentLanguage = getCurrentLanguage();
        List<String> keyList = new ArrayList<>();
        String text = "";
        if (isDelivery) {
            keyList.add(deliveryKeyLocale);
            text = jsonReader.localeReader(currentLanguage, addUserLocationDataTest.PAGE, keyList);
            System.out.println(text);
        } else {
            keyList.add(pickupKeyLocale);
            text = jsonReader.localeReader(currentLanguage, addUserLocationDataTest.PAGE, keyList);
            System.out.println(text);
        }
        return text;
    }

    private void clickLocationArrowIcon() {
        helper.pressPageUpAction();
        helper.visibleOfLocated(deliveryAddressSelector);
        helper.visibleOfLocated(locatorArrowIcon);
        int clickLoop = 3;
        while (clickLoop >= 0) {
            if (!helper.checkDisplayElement(locationPopover)) {
                System.out.println("Click locator arrow icon");
                helper.refreshPageByActions();
                try {
                    helper.clickBtn(driver.findElement(locatorArrowIcon));
                } catch (Exception exception) {
                    Log.error(exception.getMessage());
                    try {
                        helper.clickByJS(driver.findElement(locatorArrowIcon));
                    } catch (Exception exception1) {
                        Log.error(exception1.getMessage());
                    }
                }
            } else break;
            clickLoop--;
        }
    }

    public void clickLocatorLabel() {
        helper.visibleOfLocated(locatorLabel);
        helper.clickBtn(driver.findElement(locatorLabel));
    }

    public void clickSelectStoreBranch() {
        checkDisplayAddLocationArrowIcon();
        clickLocationArrowIcon();
        clickPickupPopover();
        clickPickup();
    }

    public void clickSelectAddress() {
        checkDisplayAddLocationArrowIcon();
        clickLocationArrowIcon();
        clickDeliveryPopover();
        clickDelivery();
    }

    public void clickDeliveryPopover() {
        helper.visibleOfLocated(deliveryContent);
        helper.clickBtn(driver.findElement(deliveryContent));
    }

    public void clickDelivery() {
        helper.visibleOfLocated(deliveryTab);
        helper.scrollByJS(driver.findElement(deliveryTab));
        helper.clickBtn(driver.findElement(deliveryTab));
    }

    public void clickPickup() {
        helper.visibleOfLocated(pickupTab);
        helper.scrollByJS(driver.findElement(pickupTab));
        helper.clickBtn(driver.findElement(pickupTab));
    }

    public void clickPickupPopover() {
        helper.visibleOfLocated(pickupContent);
        helper.clickBtn(driver.findElement(pickupContent));
    }

    public void clickMoreBranchArrow() {
        helper.visibleOfLocated(moreBranchArrowIcon);
        helper.clickBtn(driver.findElement(moreBranchArrowIcon));
    }

    public void clickBackDeliveryArrow() {
        helper.visibleOfLocated(deliveryOtherBranchBackArrow);
        helper.scrollByJS(helper.getElement(deliveryOtherBranchBackArrow));
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
        expectedAddress = helper.selectOptionDropdown(addressListItems, addressIndex).replace("\n", ", ");
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
        expectedRS = helper.selectOptionDropdown(addressListParent, addressIndex);
    }

    public String generateRandomStr(int length) {
        return faker.generateRandom(length);
    }

    public void enterAddress(String address, int addressIndex) {
        helper.visibleOfLocated(locatorLabelDes);
        String oldAddress = helper.getText(locatorLabelDes);
        enterDeliveryAddress(address, addressIndex);
        helper.visibleOfLocated(locatorLabelDes);
        String newAddress = helper.getText(locatorLabelDes);
        int count = 3;
        while (count > 0) {
            if (oldAddress.equals(newAddress)) {
                enterDeliveryAddress(address, addressIndex);
            } else {
                break;
            }
            newAddress = helper.getText(locatorLabelDes);
            count--;
        }
    }

    public Boolean selectBranchWithName(String branchName) {
        System.out.println(branchName);
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
        expectedAddress = branchList.get(index).findElement(By.xpath(branchAddress)).getText();
        branchList.get(index).click();
        return expectedAddress;
    }

    public String selectRandomSavedAddress() {
        helper.refreshPage();
        commonPagesTheme2().homePage.clickAccountIcon();
        if (!commonPagesTheme2().homePage.checkDisplayOfLogout()) {
            commonPagesTheme2().homePage.loginSuccessfully(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD);
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
    private Branch getPickedBranch(String address) {
        Map<String, Double> location = helpersAPIPos.getLatLongByAddress(address);
        List<Branch> branchesApi = helpersAPIPos.getBranchesByCustomerAddress(location.get("lat"), location.get("lng"));
        return branchesApi.get(0);
    }

    private void getOldBranchList(By branchListXP) {
        oldBranchList = new ArrayList<>();
        List<WebElement> list = helper.getElementList(branchListXP);
        Branch branch;
        for (WebElement e : list) {
            helper.scrollByJS(e);
            branch = setBranchInformation(e);
            oldBranchList.add(branch);
        }
    }

    //get new branch
    public void getOldBranchAfterChangeBranch(Branch branchName) {
        Branch removedBranch = new Branch();
        if (oldBranchList.size() > 0) {
            for (int i = 0; i < oldBranchList.size(); i++) {
                if (oldBranchList.get(i).getBranchName().equalsIgnoreCase(branchName.getBranchName())) {
                    removedBranch = oldBranchList.get(i);
                    oldBranchList.remove(i);
                    break;
                }
            }
            oldBranchList.add(0, removedBranch);
            for (Branch branch : oldBranchList) {
                System.out.println(branch.getBranchName());
            }
        }
    }

    public Boolean checkBranchListAfterSelectBranch(Boolean isDelivery) {
        getOldBranchAfterChangeBranch(expectedBranch);
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

    private Boolean checkValueOfDeliveryBranchAllItems(String address) {
        Map<String, Double> location = helpersAPIPos.getLatLongByAddress(address);
        try {
            return checkBranchList(deliveryOtherStoreBranchItems, location.get("lat"), location.get("lng"));
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            return false;
        }
    }

    private Boolean checkValueOfDeliveryNearestBranch(Branch nearestBranch) {
        System.out.println(nearestBranch.getBranchName());
        List<Boolean> flag = new ArrayList<>();
        helper.visibleOfLocated(deliveryOtherDefaultBranch);
        WebElement store = helper.getElement(deliveryOtherDefaultBranch);
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
            helper.scrollByJS(elementList.get(i));
            unsort = elementList.get(i).findElement(By.xpath(branchDistance)).getText().trim();
            if (unsort.isEmpty() || unsort.isBlank()) {
                unsort = elementList.get(i).findElement(By.xpath(branchDistance)).getText().trim();
            }
            replaced = helper.subStringFollowIndex(unsort, 0, (unsort.lastIndexOf(" km"))).replace(',', '.');
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

    private Branch getDefaultBranchWithoutAddress() {
        List<Branch> branchesApi = helpersAPIPos.getBranchesByCustomerAddress((double) 0, (double) 0);
        return branchesApi.get(0);
    }

    private String getSelectBranchText() {
        helper.visibleOfLocated(pickupSelectedBranch);
        return helper.getText(pickupSelectedBranch);
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

    public Boolean checkValueAfterSelectSavedAddress() {
        String currentLanguage = getCurrentLanguage();
        String expectedAddress = selectRandomSavedAddress();
        checkDisplayAddLocationLabel();
        helper.waitForTextToBePresent(locatorLabelDes, expectedAddress);
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        String actualAddress = helper.getText(locatorLabelDes).trim();
        System.out.println(expectedAddress);
        //check address
        if (actualAddress.equals(expectedAddress)) flag.add(true);
        else {
            actualRS = actualRS + "Wrong address is displayed. Actual: \"" + actualAddress + "\" Expected: \"" + expectedAddress + "\"\n";
            flag.add(false);
        }
        List<String> keyList = new ArrayList<>();
        //check label
        keyList.add("delivery");
        String str = helper.getText(locatorLabel);
        if (helper.commonLanguageCheckLocaleFile(currentLanguage, "Type button label", "text", locatorLabel, addUserLocationDataTest.PAGE, keyList) == false) {
            flag.add(false);
            actualRS = actualRS + "Actual: " + str + " Expected: " + helper.expectedLanguage + "\n";
        } else flag.add(true);
        //check display of popover
        String actualTemp = actualRS;
        flag.add(checkValueOfPopoverAfterChangeAddress(true, expectedAddress));
        actualTemp = actualTemp + "\n" + actualRS;
        //check display of recommend address dropdown
        clickDeliveryPopover();
        if (checkDisplayAddressList()) {
            flag.add(false);
            actualTemp = actualTemp + "Recommend address box is displaying.\n";
        } else flag.add(true);
        //check value of enter address field
        String inputTxt = helper.getAttribute(enterAddressField, "value");
        Boolean checked = inputTxt.equalsIgnoreCase(expectedAddress);
        if (checked) flag.add(true);
        else {
            flag.add(false);
            actualTemp = actualTemp + "Wrong address on enter field. Actual: \"" + inputTxt + "\" Expected: \"" + expectedAddress + "\"\n";
        }
        flag.add(checked);
        //check picked store
        Branch nearestBranch = getPickedBranch(expectedAddress);
        checked = checkValueOfPickedStore(nearestBranch, false);
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
        Map<String, Double> location = helpersAPIPos.getLatLongByAddress(expectedAddress);
        checked = checkBranchList(pickupBranchItems, location.get("lat"), location.get("lng"));
        if (checked) flag.add(true);
        else {
            flag.add(false);
            actualTemp = actualTemp + "- Pickup branch is displaying incorrect.\n" + actualRS;
        }
        actualRS = actualTemp;
        if (flag.contains(false)) return false;
        else return true;
    }

    //check language

    /**
     * DELIVERY PICKUP/PICKUP PANEL (DIALOG)
     *
     * @param language
     * @param isDefault
     * @return
     */
    public Boolean checkLanguageOfDefaultBranchLabel(String language, Boolean isDefault, Boolean isDelivery) {
        List<String> keyList = new ArrayList<>();
        actualRS = "";
        String actualTemp = "";
        String page = "";
        if (isDefault) {
            keyList.add("defaultBranch");
            actualTemp = "Default branch label display wrong.";
            page = addUserLocationDataTest.STORE_BRANCH_PAGE;
        } else {
            keyList.add("profilePage");
            keyList.add("deliveryFrom"); //deliveryFrom -> storeWebPage ->profilePage
            actualTemp = "Nearest branch label display wrong.";
            page = homeDataTest.STOREWEB_PAGE;
        }
        By xpath;
        if (isDelivery) xpath = deliveryOtherSelectBranchNearestLabel;
        else xpath = pickupDefaultLabel;
        helper.visibleOfLocated(xpath);
        try {
            String labelStr = jsonReader.localeReader(language, page, keyList);
            String actualLabel = helper.getText(xpath);
            if (labelStr.toUpperCase().equals(actualLabel) == false) {
                actualRS = actualRS + actualTemp + " Actual: " + actualLabel + " Expected: " + labelStr.toUpperCase() + "\n";
                return false;
            } else return true;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualRS = actualRS + exception.getMessage() + "\n";
            return false;
        }
    }

    public Boolean checkLanguageOfDefaultLabelPickup() {
        List<Boolean> flag = new ArrayList<>();
        String currentLanguage = getCurrentLanguage();
        System.out.println(currentLanguage);
        String checkedLanguage = currentLanguage;
        int index = 0;
        actualRS = "";
        String actualTemp = "";
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        System.out.println("language " + language);
        List<WebElement> languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
        try {
            helper.waitForPresence(commonPagesTheme2().homePage.dialogContent);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.dialogContent));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
            helper.visibleOfLocated(commonPagesTheme2().homePage.dialogContent);
        }
        if (languageList.get(0).getText().equals(language)) index = 1;
        else index = 0;
        commonPagesTheme2().homePage.clickLanguage();
        //check default language
        clickSelectStoreBranch();
        //check locator label and none-address label
        flag.add(checkLanguageOfDefaultBranchLabel(checkedLanguage, true, false));
        actualTemp = actualTemp + actualRS;
        helper.refreshPage();
        languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            helper.waitForPresence(commonPagesTheme2().homePage.dialogContent);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.dialogContent));
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            String languageTemp = getCurrentLanguage();
            clickSelectStoreBranch();
            flag.add(checkLanguageOfDefaultBranchLabel(languageTemp, true, false));
            actualTemp = actualTemp + actualRS;
            helper.refreshPage();
            helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
            helper.checkDisplayElementByElement(driver.findElement(commonPagesTheme2().homePage.dialogContent));
            System.out.println("checked " + checkedLanguage);
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(commonPagesTheme2().homePage.languageOptions);
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

    /**
     * No enter address/select branch
     *
     * @param language
     * @return
     */
    private Boolean checkLanguageOfAddLocationButton(String language) {
        List<String> keyList = new ArrayList<>();
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        keyList.add("delivery");
        helper.waitUtilElementVisible(driver.findElement(locatorLabel));
        if (helper.commonLanguageCheckLocaleFile(language, "please Select Address", "text", locatorLabel, addUserLocationDataTest.PAGE, keyList) == false) {
            actualRS = "Actual: " + helper.getText(locatorLabel) + "\nExpected: " + helper.expectedLanguage;
            flag.add(false);
        } else {
            flag.add(true);
        }
        keyList.clear();
        keyList.add("chooseAddress");
        helper.waitUtilElementVisible(driver.findElement(locatorLabelDes));
        if (helper.commonLanguageCheckLocaleFile(language, "select address label", "text", locatorLabelDes, addUserLocationDataTest.PAGE, keyList) == false) {
            actualRS = "Actual: " + helper.getText(locatorLabel) + "\nExpected: " + helper.expectedLanguage;
            flag.add(false);
        } else flag.add(true);
        if (flag.contains(false)) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkLanguageOfAddLocationPopover(String language) {
        List<String> keyList = new ArrayList<>();
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        clickLocationArrowIcon();
        if (checkDisplayOfPopover()) {
            keyList.add("deliveryTo");
            helper.waitUtilElementVisible(driver.findElement(deliveryContent));
            WebElement content = driver.findElement(deliveryContent);
            String deliveryTo = content.findElement(By.xpath(typeLabel)).getText();
            if (!jsonReader.localeReader(language, addUserLocationDataTest.PAGE, keyList).equals(deliveryTo)) {
                actualRS = "Actual: " + deliveryTo + "\nExpected: " + jsonReader.localeReader(language, addUserLocationDataTest.PAGE, keyList);
                flag.add(false);
            } else {
                flag.add(true);
            }
            keyList.clear();
            keyList.add("chooseAddress");
            String detail = content.findElement(By.xpath(addressLabel)).getText();
            if (!jsonReader.localeReader(language, addUserLocationDataTest.PAGE, keyList).equals(detail)) {
                actualRS = "Actual: " + detail + "\nExpected: " + jsonReader.localeReader(language, addUserLocationDataTest.PAGE, keyList);
                flag.add(false);
            } else {
                flag.add(true);
            }
            keyList.clear();
            keyList.add("branch");
            helper.waitUtilElementVisible(driver.findElement(pickupContent));
            WebElement content1 = driver.findElement(pickupContent);
            String pickupLabel = content1.findElement(By.xpath(typeLabel)).getText();
            if (!jsonReader.localeReader(language, addUserLocationDataTest.PAGE, keyList).equals(pickupLabel)) {
                actualRS = "Actual: " + pickupLabel + "\nExpected: " + jsonReader.localeReader(language, addUserLocationDataTest.PAGE, keyList);
                flag.add(false);
            } else flag.add(true);
            if (flag.contains(false)) {
                return false;
            } else {
                return true;
            }
        } else {
            actualRS = "Location popover did not display";
            return false;
        }
    }

    //TODO check language
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
        System.out.println(currentLanguage);
        flag.add(checkLanguageOfAddLocationButton(currentLanguage));
        actualCheckLanguage = actualCheckLanguage + actualRS + "\n";
        //CHECK LANGUAGE OF POPOVER
        flag.add(checkLanguageOfAddLocationPopover(currentLanguage));
        helper.refreshPage();
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
            actualCheckLanguage = actualCheckLanguage + keyList + "\n" + exception.getMessage() + "\n";
        }
        keyList.clear();
        keyList.add("toPickup");
        try {
            if (helper.commonLanguageCheckLocaleFile(language, "toPickup", "text", pickupTxt, addUserLocationDataTest.PAGE, keyList) == false) {
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + "Actual: " + helper.getText(pickupTxt) + " Expected: " + helper.expectedLanguage + "\n";
            } else flag.add(true);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualCheckLanguage = actualCheckLanguage + keyList + "\n" + exception.getMessage() + "\n";
            flag.add(false);
        }
        keyList.clear();
        keyList.add("pleaseEnterYourAddress");
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
            flag.add(false);
            actualCheckLanguage = actualCheckLanguage + keyList + "\n" + exception.getMessage() + "\n";
        }
        if (isLogin) {
            keyList.clear();
            keyList.add("profilePage");
            keyList.add("myAddress");
            try {
                String txt = helper.getText(myAddressLabel);
                if (!jsonReader.localeReader(language, homeDataTest.STOREWEB_PAGE, keyList).toUpperCase().equals(txt)) {
                    flag.add(false);
                    actualCheckLanguage = actualCheckLanguage + "My address label wrong. Actual: " + txt + " Expected: " + jsonReader.localeReader(language, homeDataTest.STOREWEB_PAGE, keyList) + "\n";
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + keyList + "\n" + exception.getMessage() + "\n";
            }
        }
        if (hasPickedStore) {
            keyList.clear();
            keyList.add("profilePage");
            keyList.add("deliveryFrom");
            try {
                String txt = helper.getText(deliveryFromLabel);
                if (!jsonReader.localeReader(language, homeDataTest.STOREWEB_PAGE, keyList).toUpperCase().equals(txt)) {
                    flag.add(false);
                    actualCheckLanguage = actualCheckLanguage + "Delivery from label wrong. Actual: " + txt + " Expected: " + jsonReader.localeReader(language, homeDataTest.STOREWEB_PAGE, keyList) + "\n";
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + keyList + "\n" + exception.getMessage() + "\n";
            }
            clickMoreBranchArrow();
            keyList.clear();
            keyList.add("otherBranch");
            try {
                helper.visibleOfLocated(deliveryOtherBranchLabel);
                String deliveryLabel = helper.getText(deliveryOtherBranchLabel);
                if (!jsonReader.localeReader(language, addUserLocationDataTest.STORE_BRANCH_PAGE, keyList).toUpperCase().equals(deliveryLabel)) {
                    flag.add(false);
                    actualCheckLanguage = actualCheckLanguage + "Delivery other branchs label wrong. Actual: " + deliveryLabel + " Expected: " + jsonReader.localeReader(language, addUserLocationDataTest.STORE_BRANCH_PAGE, keyList) + "\n";
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + keyList + "\ndeliveryOtherBranchLabel\n" + exception.getMessage() + "\n";
            }
            flag.add(checkLanguageOfDefaultBranchLabel(language, isDefault, true));
            actualCheckLanguage = actualCheckLanguage + actualRS;
            keyList.clear();
            keyList.add("otherBranch");
            String str = helper.getText(deliveryOtherSelectBranchOtherLabel);
            try {
                if (!jsonReader.localeReader(language, addUserLocationDataTest.STORE_BRANCH_PAGE, keyList).toUpperCase().equals(str)) {
                    actualCheckLanguage = actualCheckLanguage + "deliveryOtherSelectBranchOtherLabel wrong. Actual: " + str + " Expected: " + jsonReader.localeReader(language, addUserLocationDataTest.STORE_BRANCH_PAGE, keyList) + "\n";
                    flag.add(false);
                } else {
                    flag.add(true);
                }
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + keyList + "\ndeliveryOtherSelectBranchOtherLabel\n" + exception.getMessage() + "\n";
            }
            clickBackDeliveryArrow();
        }
        clickPickup();
        flag.add(checkLanguageOfDefaultBranchLabel(language, isDefault, false));
        actualCheckLanguage = actualCheckLanguage + actualRS;
        keyList.clear();
        keyList.add("otherBranch");
        try {
            String txt = helper.getText(pickupOtherLabel);
            if (!jsonReader.localeReader(language, addUserLocationDataTest.STORE_BRANCH_PAGE, keyList).toUpperCase().equals(txt)) {
                flag.add(false);
                actualCheckLanguage = actualCheckLanguage + "Pickup other branchs label wrong. Actual: " + txt + " Expected: " + jsonReader.localeReader(language, addUserLocationDataTest.PAGE, keyList) + "\n";
            } else {
                flag.add(true);
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            flag.add(false);
            actualCheckLanguage = actualCheckLanguage + keyList + "\nPickup other branchs\n" + exception.getMessage() + "\n";
        }
        if (flag.contains(false)) {
            return false;
        } else return true;
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
        List<WebElement> languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
        try {
            helper.waitForPresence(commonPagesTheme2().homePage.dialogContent);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.dialogContent));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
            languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
            helper.visibleOfLocated(commonPagesTheme2().homePage.dialogContent);
        }
        if (languageList.get(0).getText().equalsIgnoreCase(language)) index = 1;
        else index = 0;
        try {
            commonPagesTheme2().homePage.clickLanguage();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.refreshPage();
        }
        //check default language
        clickSelectAddress();
        flag.add(checkLanguageOfLocator(hasPickedStore, isLogin, isDefault));
        helper.refreshPage();
        languageList = helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            helper.waitForPresence(commonPagesTheme2().homePage.dialogContent);
            helper.waitUtilElementVisible(driver.findElement(commonPagesTheme2().homePage.dialogContent));
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            flag.add(checkLanguageOfLocator(hasPickedStore, isLogin, isDefault));
            helper.refreshPage();
            helper.changeLanguage(commonPagesTheme2().homePage.languageSwitch, commonPagesTheme2().homePage.languageOptions);
            helper.checkDisplayElementByElement(driver.findElement(commonPagesTheme2().homePage.dialogContent));
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(commonPagesTheme2().homePage.languageOptions);
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
}