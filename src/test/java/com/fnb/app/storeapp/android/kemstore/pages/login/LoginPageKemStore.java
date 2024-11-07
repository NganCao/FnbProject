package com.fnb.app.storeapp.android.kemstore.pages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.fnb.app.setup.BaseSetup;

public class LoginPageKemStore extends BaseSetup {
    @FindBy(id = "android:id/content")
    public WebElement profileBtn;


    public LoginPageKemStore() {
        PageFactory.initElements(driver, this);
    }

    public void sendInfo() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(profileBtn));
        String s = profileBtn.getText();
        System.out.println(profileBtn);
        System.out.println(profileBtn.getText());
    }

    public String verifyToastMessDisplay() {
        return helper.getLoginSuccessToast();
    }

}

