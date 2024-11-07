package com.fnb.app.posapp.autostore.pages.order;

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
import java.util.ArrayList;
import java.util.List;

public class OrderManagementPage extends BaseSetup {
    private AndroidDriver driver;
    private WebDriverWait wait;
    public String actualRS;
    @FindBy(xpath = "//android.widget.TextView[@text=\"POS Order\"]")
    public WebElement title;
    @FindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"Refresh\"])[1]")
    public WebElement refreshBtn;
    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Completed\"]")
    public WebElement completeTab;
    @FindBy(xpath = "//android.widget.TextView[@text=\"In store\"]/preceding-sibling::android.view.ViewGroup")
    public WebElement instoreCheckbox;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtCode\"")
    public List<WebElement> orderId;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    public WebElement orderTotalBillTxtFirst;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/android.widget.TextView[3]")
    public WebElement orderCreatedTimeTxtFirst;
    @FindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtTotalBill\"])[1]")
    public WebElement orderTotalAmountFirst;
    @FindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtDish\"])[1]")
    public WebElement orderTotalItemsFirst;
    @FindBy(xpath = "(//android.widget.Button[@resource-id=\"com.gofnb.posapplication:id/btnPrintBill\"])[1]")
    public WebElement printBillBtnFirst;

    public OrderManagementPage(AndroidDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObjectAndroid.getTimeOut()));
        this.helper = new Helper(driver, wait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCompletedTab() {
        completeTab.click();
    }

    public void clickInstoreFilter() {
        instoreCheckbox.click();
    }

    public void clickOrderById(String id) {
        WebElement e = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtCode\" and @text=\"" + id + "\"]"));
        helper.waitToElementClick(e);
        helper.sleepHaftSec();
    }

    public void clickPrintButton() {
        printBillBtnFirst.click();
    }

    public Boolean checkOrderDetail(String id, String status, String totalAmount, int quantity) {
        actualRS = "checkOrderDetail \n";
        System.out.println("checkOrderDetail");
        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.gofnb.posapplication:id/txtCode\" and @text=\"" + id + "\"]"));
        try {
            scrollVertical(id);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        helper.waitUtilElementVisible(element);
        String totalAmountStr = orderTotalAmountFirst.getText().trim();
        String totalItemsStr = orderTotalItemsFirst.getText().trim();
        String totalItem = totalItemsStr.substring(0, totalItemsStr.indexOf("/")).trim();
        String totalCompleteItems = totalItemsStr.substring(totalItemsStr.indexOf("/") + 1).trim();
        List<Boolean> flag = new ArrayList<>();
        String expectedQuantity = String.valueOf(quantity).trim();
        flag.add(helper.checkValueEquals("totalItem", totalItem, expectedQuantity));
        actualRS = actualRS + helper.actualRS;
        if (status.toLowerCase().contains("complete")) {
            flag.add(helper.checkValueEquals("totalCompleteItems", totalCompleteItems, expectedQuantity));
            actualRS = actualRS + helper.actualRS;
        }
        //check total amount
        flag.add(helper.checkValueEquals("total Amount", totalAmountStr, totalAmount));
        actualRS = actualRS + helper.actualRS;
        System.out.println(flag);
        return !flag.contains(false);
    }
}
