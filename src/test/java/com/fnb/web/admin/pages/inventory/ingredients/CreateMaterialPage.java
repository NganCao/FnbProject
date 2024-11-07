package com.fnb.web.admin.pages.inventory.ingredients;

import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;
import com.fnb.utils.helpers.Helper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Data
public class CreateMaterialPage extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CommonComponents commonComponents;
    private By txtMaterialName = By.xpath("//input[@id='createMaterialForm_name']");
    private By txtCostPerUnit = By.xpath("//input[@id='createMaterialForm_costPerUnit']");
    private By selSearchBaseUnit = By.xpath("//input[@id='createMaterialForm_unitId']");
    private By txtMinQuantity = By.xpath("//input[@id='createMaterialForm_minQuantity']");
    private By selSearchBranch = By.xpath("//div[contains(@class, 'fnb-card')][3]//input[(@class='ant-select-selection-search-input') and not(@disabled)]//ancestor::div[contains(@class, 'ant-select-selector')]");
    private By selSearchInputBranch = By.xpath("//div[contains(@class, 'fnb-card')][3]//input[(@class='ant-select-selection-search-input') and not(@disabled)]");
    private By emptyDescription = By.xpath("//div[@class='empty-text']");
    private By toastSuccessMessage = By.xpath("//div[contains(@class, 'ant-message-success')]");
    private By toastError = By.xpath("//div[contains(@class, 'ant-message-error')]");
    public By btnAddFile = By.xpath("//input[@type='file']/..");
    public By inputUploadFile = By.xpath("//input[@type='file']");
    public By txtSKU = By.xpath("//input[@id='createMaterialForm_sku']");
    private By btnAddImportUnit = By.xpath("//button[@id='btn-add-new' and contains(@class, 'import')]");
    private By newUnitElement = By.xpath("//div[contains(@class, 'add-new-select')]");
    private By switchBtn_setupQuantity = By.xpath("//button[@role='switch' and ./following::*[text()='"+adminLocalization.getMaterial().getSetupQuantity()+"']]");

    public CreateMaterialPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        commonComponents = new CommonComponents(driver);
    }

    public CreateMaterialPage click_SwitchBtn_SetupQuantity() {
        helper.clickElement(switchBtn_setupQuantity);
        return this;
    }

    public CreateMaterialPage chooseBaseUnit(String unit) {
        helper.scrollToElementAtTop(selSearchBaseUnit);
        helper.clickElement(selSearchBaseUnit);
        helper.enterText(selSearchBaseUnit, unit);

        By unitElement = By.xpath("//div[contains(@class, 'ant-select-item-option')]//div[normalize-space()='"+ unit +"']");

        if(helper.isElementVisible(emptyDescription)) {
            helper.clickElement(newUnitElement);
            helper.clickElement(unitElement);
        }
        else {
            helper.clickElement(unitElement);
        }
        return this;
    }

    public CreateMaterialPage clickAddImportUnit() {
        helper.clickElement(btnAddImportUnit);
        return this;
    }

    public CreateMaterialPage enterCostPerUnit(String cost) {
        helper.enterText(txtCostPerUnit, cost);
        return this;
    }

    public CreateMaterialPage enterBaseUnit(String baseUnit) {
        helper.scrollToElementAtMiddle(selSearchBaseUnit);
        helper.enterText(selSearchBaseUnit, baseUnit);
        return this;
    }

    public CreateMaterialPage enterSKU(String skuName) {
        helper.scrollToElementAtMiddle(txtSKU);
        helper.enterText(txtSKU, skuName);
        return this;
    }

    public CreateMaterialPage clickAddNew() {
        helper.scrollToElementAtBottom(commonComponents.getBtnAddNew());
        helper.clickElement(commonComponents.getBtnAddNew());
        helper.waitForElementVisible(toastSuccessMessage);
        return this;
    }

    public CreateMaterialPage enterQuantityForBranch(String branchName, String quantity) {
        helper.scrollToElementAtTop(selSearchBranch);
        helper.clickElement(selSearchBranch);
        helper.enterText(selSearchInputBranch, branchName);
        By branchElement = By.xpath("//div[contains(@class, 'ant-select-item-option')]//div[normalize-space()='"+branchName+"'][1]");
        helper.clickElement(branchElement);
        actions.sendKeys(Keys.ESCAPE).build().perform();

        By txtBranchQuantity = By.xpath("//*[text()='"+adminLocalization.getMaterial().getBranchAndWarehouse()+"']/following::*[text()='"+branchName+"']/following::input[1]");
        helper.scrollToElementAtTop(txtBranchQuantity);
        helper.getWebElement(txtBranchQuantity).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        helper.getWebElement(txtBranchQuantity).sendKeys(Keys.BACK_SPACE);
        helper.enterText(txtBranchQuantity, quantity);
        return this;
    }

    public void createMaterials(String materialName, String image, String unit, String costPerUnit, String minQuantity, String branhName, String quantity) {
        helper.enterText(txtMaterialName, materialName);
        upLoadImage(image);
        chooseBaseUnit(unit);
        helper.enterText(txtCostPerUnit, costPerUnit);
        helper.enterText(txtMinQuantity, minQuantity);
        click_SwitchBtn_SetupQuantity();
        enterQuantityForBranch(branhName, quantity);
        helper.scrollToElementAtBottom(commonComponents.getBtnAddNew());
        helper.clickElement(commonComponents.getBtnAddNew());
        helper.waitForElementVisible(toastSuccessMessage);
    }

    public void upLoadImage(String imagePath) {
        helper.waitForElementVisible(btnAddFile);
        helper.getWebElement(inputUploadFile).sendKeys(System.getProperty("user.dir") + "/" + imagePath);
        helper.waitForElementPresent(By.xpath("//button[contains(@class,'btn-hidden')]"));
    }

    public void clickCancel() {
        helper.clickElement(commonComponents.getBtnCancel());
    }

    @Data
    public class UnitConversionDialog {
        private String xpathDialog = "//div[@role='dialog']";
        private By dialog = By.xpath(xpathDialog);
        private By btnAddNewImportUnit = By.xpath("//button[normalize-space()='Add New Import Unit']");
        private By btnAdd = By.xpath(xpathDialog + "//button[@type='submit']");
        private By btnCancel = By.xpath(xpathDialog + "//button[contains(@class, 'cancel-button')]");
        private By selSearchUnit = By.xpath(xpathDialog + "//span[normalize-space()='"+ DataTest.SelectUnit_PlaceHolder+"']/ancestor::div[contains(@class, 'ant-select-show-search')]//input");

        public String getTitle() {
            return helper.getText(By.xpath(helper.getTheXpathStringFromBy(dialog) + "//div[@class='ant-modal-title']"));
        }

        public UnitConversionDialog clickAddButton() {
            helper.clickElement(btnAdd);
            return this;
        }

        public UnitConversionDialog clickCancelButton() {
            helper.clickElement(btnCancel);
            return this;
        }

        public UnitConversionDialog clickAddNewImportUnit() {
            helper.clickElement(btnAddNewImportUnit);
            return this;
        }
    }

    public String createAMaterialWithAnyTypeData() {
        helper.navigateToUrl(configObject.getUrlCreateMaterial());
        String materialName = "Material" + helper.generateRandomNumber();
        createMaterials(
                materialName,
                "resources/image/materials/suatuoiauto.jpg",
                "ml",
                "20000",
                "1000",
                branchData.getBranch().get(0).getName(),
                "1000"
        );
        return materialName;
    }
}
