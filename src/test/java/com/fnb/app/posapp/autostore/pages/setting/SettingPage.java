package com.fnb.app.posapp.autostore.pages.setting;

import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage;
import com.fnb.app.posapp.autostore.pages.dashboard.DataTest;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.api.posapp.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SettingPage extends BaseSetup {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private JsonAPIAdminReader.Product product;
    private JsonAPIAdminReader jsonAPIAdminReader;
    private DataTest dataTest;
    //    private APIAminService apiAminService;
    public static String subTotalStr;
    public String totalStr;
    private DashboardPage.ProductCart addedProductCart;
    public List<DashboardPage.ProductCart> productCartList = new ArrayList<>();
    public static int quantityCartTotal;
    public String actualRS;
    //
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    public WebElement acceptConnect;
    //header
    @FindBy(xpath = "//android.widget.TextView[@text=\"Setting\"]")
    public WebElement settingHeading;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Printer configuration\"]")
    public WebElement printerConfig;
    //sprint screen
    @FindBy(xpath = "//android.widget.TextView[@text=\"Device management\"]")
    public WebElement deviceManagement;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Create new device\"]")
    public WebElement createNewDeviceBtn;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Create new device\"]")
    public WebElement createNewDeviceTitle;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel\"]")
    public WebElement cancelBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Create\"]")
    public WebElement createBtn;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Device name*\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    public WebElement deviceNameTxt;
    @FindBy(xpath = "//android.widget.TextView[@text=\" Device type*\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    public WebElement deviceTypeTxt;
    //management
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[6]/android.view.ViewGroup/android.view.ViewGroup/com.horcrux.svg.SvgView")
    public List<WebElement> deleteIconList;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[6]/android.view.ViewGroup/android.view.ViewGroup/com.horcrux.svg.SvgView")
    public List<WebElement> managementDeviceNameList;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Delete\"]")
    public WebElement deleteDialogBtn;

    public SettingPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptConnect() {
        helper.waitToElementClick(acceptConnect);
    }

    public void clickPrinterConfig() {
        helper.waitToElementClick(printerConfig);
    }

    public void clickCreateNewDeviceBtn() {
        helper.waitToElementClick(createNewDeviceBtn);
    }

    public void clickCreateBtn() {
        helper.waitToElementClick(createBtn);
    }

    public Boolean selectDeviceType(String type) {
        helper.waitToElementClick(deviceTypeTxt);
        helper.sleep(1);
        String xpath = "//android.widget.TextView[@text=\"" + type + "\"]";
        WebElement e = driver.findElement(By.xpath(xpath));
        e.click();
        helper.sleep(1);
        try {
            acceptConnect.click();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.waitForTextToBe(deviceTypeTxt, type);
        return helper.checkText(deviceTypeTxt, type);
    }

    public Boolean selectDeviceName(String name) {
        helper.waitToElementClick(deviceNameTxt);
        helper.sleep(1);
        String xpath = "//android.widget.TextView[contains(@text,\"" + name + "\")]";
        WebElement e = driver.findElement(By.xpath(xpath));
        e.click();
        helper.sleep(1);
        return helper.checkText(deviceNameTxt, name);
    }

    public Boolean settingPrinter(String deviceType, String deviceName) {
        clickPrinterConfig();
        helper.sleep(1);
        clickCreateNewDeviceBtn();
        helper.sleep(1);
        selectDeviceType(deviceType);
        helper.sleep(1);
        selectDeviceName(deviceName);
        helper.sleep(1);
        clickCreateBtn();
        helper.sleep(1);
        String xpath = "//android.widget.TextView[contains(@text,\"" + deviceName + "\")]";
        WebElement e = driver.findElement(By.xpath(xpath));
        return helper.checkContainsText(e, deviceName);
    }

    public Boolean settingPrinterPOS() {
        clickPrinterConfig();
        clickCreateNewDeviceBtn();
        helper.sleep(1);
        clickCreateBtn();
        helper.sleep(1);
        return true;
    }

    public Boolean deleteDeviceWithBillType(String billType) {
        clickPrinterConfig();
        helper.sleepHaftSec();
        String xpath = "//android.widget.TextView[@text=\"" + billType + "\"]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup";
        String xpathDeleteBtn = xpath + "/android.view.ViewGroup/android.view.ViewGroup/com.horcrux.svg.SvgView";
        WebElement deleteBtn = driver.findElement(By.xpath(xpathDeleteBtn));
        helper.waitToElementClick(deleteBtn);
        helper.sleep(1);
        deleteDialogBtn.click();
        helper.sleep(1);
        try {
            WebElement e = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + billType + "\"]"));
            return e.isDisplayed();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return true;
        }
    }
}
