package com.fnb.web.admin.pages.product.combo;

import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class CreateComboPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    public CreateComboPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }
    private By txtComboName = By.xpath("//input[@id='name']");
    private By txtStartDate = By.xpath("//input[@id='startDate']");
    private By txtEndDate = By.xpath("//input[@id='endDate']");
    private By selSearchBranch = By.xpath("//h4[normalize-space()='Branch']/following-sibling::div[contains(@class, 'ant-form-item') and not(contains(@class, 'hidden'))]//input");
    private By selSearchProduct = By.xpath("//input[@id='productPriceIds']");
    private By checkBoxAllBranches = By.xpath("//div[contains(@class, 'select-all-branch')]//input");
    private By sectionComboType = By.xpath("//div[@id='comboTypeId']");
    private By radioButtonFlexibleCombo = By.xpath("//div[@id='comboTypeId']//label[contains(normalize-space(), 'Flexible')]//input");
    private By radioButtonSpecificCombo = By.xpath("//div[@id='comboTypeId']//label[contains(normalize-space(), 'Specific')]//input");
    private By txtSellingPrice = By.xpath("//input[@id='sellingPrice']");
    private By btnAddFile = By.xpath("//input[@type='file']/..");
    private By inputUploadFile = By.xpath("//input[@type='file']");
    private By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");

    public CreateComboPage selectBranch(String branchName) {
        helper.getWebElement(selSearchBranch).sendKeys(branchName);
        helper.clickElement(By.xpath("//div[normalize-space()='" + branchName + "' and contains(@class, 'active')]"));
        return this;
    }

    public CreateComboPage selectProduct(String productName) {
        helper.scrollToElementAtTop(sectionComboType);
        helper.getWebElement(selSearchProduct).sendKeys(productName);
        commonComponents.selectOptionInDropDownOfSearchSelect(productName);
        return this;
    }

    public void upLoadImage(String imagePath) {
        helper.waitForElementVisible(btnAddFile);
        helper.getWebElement(inputUploadFile).sendKeys(System.getProperty("user.dir") + "/" + imagePath);
        helper.waitForElementPresent(By.xpath("//button[contains(@class,'btn-hidden')]"));
    }

    public void createSpecificCombo(String comboName, String image, String startDate, String endDate, String[] branches, String[] products, Boolean isShowAllBranches, String sellingPrice) {
        // Enter combo name
        helper.enterText(txtComboName, comboName);

        // Upload image
        upLoadImage(image);

        // Select start date
        helper.enterText(txtStartDate, startDate);
        helper.getWebElement(txtStartDate).sendKeys(Keys.ENTER);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(@class, 'ant-picker-status-success')]//input[@id='startDate']")), "Select startdate unsuccessful, the date may be invalid");

        // Select end date
        helper.enterText(txtEndDate, endDate);
        helper.getWebElement(txtEndDate).sendKeys(Keys.ENTER);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(@class, 'ant-picker-status-success')]//input[@id='endDate']")), "Select enddate unsuccessful, the end date may be invalid");

        // Select branch
        if (!isShowAllBranches) {
            for (int i = 0; i < branches.length; i++) {
                String branhch = branches[i];
                selectBranch(branches[i]);
            }
        } else {
            if (!helper.getWebElement(checkBoxAllBranches).isSelected()) {
                helper.getWebElement(checkBoxAllBranches).click();
            }
        }

        // Choose Combo Type
        helper.scrollToElementAtBottom(sectionComboType);
        if (!helper.getWebElement(radioButtonSpecificCombo).isSelected()) {
            helper.getWebElement(radioButtonSpecificCombo).click();
        }
        // Select product
        for (int i = 0; i < products.length; i++) {
            selectProduct(products[i]);
        }

        // Enter selling price
        helper.enterText(txtSellingPrice, sellingPrice);

        // Click Create button
        helper.scrollToElementAtBottom(commonComponents.getBtnCreate());
        helper.clickElement(commonComponents.getBtnCreate());
        helper.waitForElementVisible(toastSuccessMessage);
    }

    public String createAActiveComboWithAnyTypeData(String[] producName) {
        helper.navigateToUrl(configObject.getUrlCreateCombo());
        String comboName = "Combo" + helper.generateRandomNumber();
        helper.enterText(txtComboName, comboName);
        upLoadImage(specificComboData.getSpecificCombo().get(0).getImage());
        helper.enterText(txtStartDate, helper.getCurrentDate());
        helper.getWebElement(txtStartDate).sendKeys(Keys.ENTER);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(@class, 'ant-picker-status-success')]//input[@id='startDate']")), "Select startdate unsuccessful, the date may be invalid");
        helper.enterText(txtEndDate, helper.getNextDay());
        helper.getWebElement(txtEndDate).sendKeys(Keys.ENTER);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(@class, 'ant-picker-status-success')]//input[@id='endDate']")), "Select enddate unsuccessful, the end date may be invalid");
        if (!helper.getWebElement(checkBoxAllBranches).isSelected()) {
            helper.getWebElement(checkBoxAllBranches).click();
        }
        helper.scrollToElementAtBottom(sectionComboType);
        if (!helper.getWebElement(radioButtonSpecificCombo).isSelected()) {
            helper.getWebElement(radioButtonSpecificCombo).click();
        }
        // Select product
        for (int i = 0; i < producName.length; i++) {
            selectProduct(producName[i]);
        }
        // Enter selling price
        helper.enterText(txtSellingPrice, "10000");
        // Click Create button
        helper.scrollToElementAtBottom(commonComponents.getBtnCreate());
        helper.clickElement(commonComponents.getBtnCreate());
        helper.waitForElementVisible(toastSuccessMessage);

        return comboName;
    }

    public String createAScheduleComboWithAnyTypeData(String[] producName) {
        helper.navigateToUrl(configObject.getUrlCreateCombo());
        String comboName = "Combo" + helper.generateRandomNumber();
        helper.enterText(txtComboName, comboName);
        upLoadImage(specificComboData.getSpecificCombo().get(0).getImage());
        helper.enterText(txtStartDate, helper.getANumberofNextDay(2));
        helper.getWebElement(txtStartDate).sendKeys(Keys.ENTER);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(@class, 'ant-picker-status-success')]//input[@id='startDate']")), "Select startdate unsuccessful, the date may be invalid");
        helper.enterText(txtEndDate, helper.getANumberofNextDay(3));
        helper.getWebElement(txtEndDate).sendKeys(Keys.ENTER);
        Assert.assertTrue(helper.isElementVisible(By.xpath("//div[contains(@class, 'ant-picker-status-success')]//input[@id='endDate']")), "Select enddate unsuccessful, the end date may be invalid");
        if (!helper.getWebElement(checkBoxAllBranches).isSelected()) {
            helper.getWebElement(checkBoxAllBranches).click();
        }
        helper.scrollToElementAtBottom(sectionComboType);
        if (!helper.getWebElement(radioButtonSpecificCombo).isSelected()) {
            helper.getWebElement(radioButtonSpecificCombo).click();
        }
        // Select product
        for (int i = 0; i < producName.length; i++) {
            selectProduct(producName[i]);
        }
        // Enter selling price
        helper.enterText(txtSellingPrice, "10000");
        // Click Create button
        helper.scrollToElementAtBottom(commonComponents.getBtnCreate());
        helper.clickElement(commonComponents.getBtnCreate());
        helper.waitForElementVisible(toastSuccessMessage);

        return comboName;
    }
}
