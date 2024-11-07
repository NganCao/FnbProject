package com.fnb.web.admin.pages.common;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.inventory.ingredients.DataTest;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Data
public class FilterDialog extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    public CommonComponents commonComponents;
    private By selSearchBranch = By.xpath(getAntRow(DataTest.Branch) + "//input");
    private By selSearchCategory = By.xpath(getAntRow(DataTest.Category) + "//input");
    private By selSearchCUnit = By.xpath(getAntRow(DataTest.Unit) + "//input");
    private By btnResetFilter = By.xpath("//span[text()='"+CommonElementData.Reset_all_filters+"']");

    public FilterDialog(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        siderBar = new SiderBar(driver);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    public String getAntRow(String type) {
        String antRowElement = "//div[@id='table-filter-popover']//div[contains(@class, 'ant-form-item-row') and .//label[text()='"+type+"']]";
        return antRowElement;
    }

    public By getCheckIcon(String optionName, String type) {
        return By.xpath(getAntRow(type) + "//span[contains(@class, 'checked') and ./following-sibling::span[normalize-space()='"+optionName+"']]");
    }

    public By getSelectionItem(String optionName, String type) {
        return By.xpath(getAntRow(type) + "//span[normalize-space()='"+optionName+"']");
    }

    public void selectBranch(String branchName) {
        helper.enterText(selSearchBranch, branchName);
        helper.clickElement(By.xpath("//*[text()='"+branchName+"']"));
    }

    public void selectUnit(String unitName) {
        helper.enterText(selSearchCUnit, unitName);
        helper.clickElement(By.xpath("//*[starts-with(text(),'"+unitName+"')]"));

    }

    public void selectCategory(String categoryName) {
        helper.enterText(selSearchCategory, categoryName);
        helper.clickElement(By.xpath("//*[text()='"+categoryName+"']"));
    }

    // Type: Status
    // Option: Active, Inactive
    public void selectOption(String option, String type) {
        helper.clickElement(getSelectionItem(option, type));
        helper.waitForElementVisible(getCheckIcon(option, type));
    }

    public String getSelectedItem(String field) {
        return helper.getText(By.xpath(""+ getAntRow(field)+"//span[contains(@class, 'selection-item')]"));
    }

    public void clickResetFilter() {
        helper.clickElement(btnResetFilter);
        helper.waitExpectedAttributeToBe(btnResetFilter,"cursor", "not-allowed", "The reset button is not disable");
    }
}
