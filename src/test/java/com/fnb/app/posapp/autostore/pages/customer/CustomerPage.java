package com.fnb.app.posapp.autostore.pages.customer;

import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage;
import com.fnb.app.setup.BaseSetup;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage extends BaseSetup {
    private AndroidDriver driver;
    private WebDriverWait wait;
    public String customerName;
    public String actualRS;
    @FindBy(xpath = "//android.widget.EditText[@text=\"Search by customer name, phone\"]/parent::android.view.ViewGroup/android.widget.EditText")
    private WebElement searchField;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]")
    private WebElement firstResult;
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[1]")
    private WebElement firstNameResult;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]" + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[3]")
    private WebElement clearIcon;

    public CustomerPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean selectCustomerWithPhone(String phone) {
        helper.waitUtilElementVisible(searchField);
        helper.enterData(searchField, phone);
        try {
            helper.checkElementDisplay(firstNameResult);
            customerName = firstNameResult.getText();
            System.out.println(customerName);
            try {
                firstNameResult.click();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(firstNameResult);
            }
            helper.sleep(2);
            //android.widget.TextView[@text="La POP Chi Chi"]
            System.out.println("//android.widget.TextView[@text=\"" + customerName + "\"]");
            WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + customerName + "\"]"));
            actualRS = "Customer did not display";
            return helper.checkElementDisplay(element);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println(exception.getMessage());
            actualRS = "Catch : customer did not display";
            return false;
        }
    }
}
