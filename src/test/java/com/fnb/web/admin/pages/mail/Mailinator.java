package com.fnb.web.admin.pages.mail;


import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class Mailinator extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public Mailinator(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }
    
    private By txtMail = By.xpath("//input[@id='inbox_field']");
    private By btnGo = By.xpath("//button[normalize-space()='GO']");

    public void gotoMailPlatform() {
        driver.navigate().to("https://mailinator.com/v4/public/inboxes.jsp");
        helper.waitForPageLoaded();
    }

    public void enterMail(String email) {
        helper.enterText(txtMail, email);
        helper.clickElement(btnGo);
    }

    public void clickMail(String mailTitle) {
        By mail = By.xpath("//tr[td[contains(text(),'"+mailTitle+"')] and td[contains(text(), 'just now')]]");
        helper.clickElement(mail);
    }

    public String getRegisterPassword() {
        By registerPasswordLocator = By.xpath("//div[normalize-space()='Please using this Password to login:']//following-sibling::div[1]");
        WebElement iframeElement = driver.findElement(By.id("html_msg_body"));
        driver.switchTo().frame(iframeElement);
        return helper.getText(registerPasswordLocator);
    }
}
