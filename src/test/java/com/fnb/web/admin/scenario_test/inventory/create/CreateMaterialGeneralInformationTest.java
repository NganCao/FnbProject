package com.fnb.web.admin.scenario_test.inventory.create;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.admin.pages.store.staff.GroupPermission;
import com.fnb.web.admin.pages.store.staff.Permission;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import static com.fnb.web.setup.Setup.configObject;
import static com.fnb.web.setup.Setup.materialData;

// Sprint51
public class CreateMaterialGeneralInformationTest extends CommonPages {
    private com.fnb.web.admin.pages.store.staff.DataTest staffDataTest;
    private com.fnb.web.admin.pages.inventory.ingredients.DataTest inventoryDataTest;
    private Helper helper;
    private String materialName;
    private String quantity;
    private String branhName;
    private String unit;

    @BeforeClass
    public void beforeClass() throws IOException, AWTException {
        materialName = materialData.getMaterial().get(0).getName();
        quantity = materialData.getMaterial().get(0).getBranch().get(0).getQuantity();
        branhName = materialData.getMaterial().get(0).getBranch().get(0).getName();
        unit = materialData.getMaterial().get(0).getUnit();
        helper = adminPage().helper;
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
    }

    @Test(testName = "FB-12326 : Ensure that the user has the necessary permissions to create a material")
    public void FB12326() {
        homePage().helper.navigateToUrl(configObject.getUrlStaffManagement());
        staffManagementPage()
                .clickPermissionGroupTab()
                .clickGroupName(staffDataTest.OPTION_PERMISSION1)
                .unCheckAPermission(GroupPermission.Material, Permission.Create_material)
                .clickUpdate();

        homePage().logOut();
        adminPage().navigateToHomePage(DataTest.STAFF_EMAIL, DataTest.STAFF_PASSWORD);
        homePage().siderBar.clickMnuItemInventory();

        Assert.assertFalse(helper.isElementVisible(materialManagementPage().getBtnAddNew()), "The add new button is still visible");

        homePage().logOut();
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
        homePage().helper.navigateToUrl(configObject.getUrlStaffManagement());
        staffManagementPage()
                .clickPermissionGroupTab()
                .checkFullPermission(staffDataTest.OPTION_PERMISSION1)
                .clickUpdate();
    }


    @Test(testName = "FB-12327 : Click on the 'Add new' button on the Material Management list.")
    public void FB12327() {
        homePage().helper.navigateToUrl(configObject.getUrlBase());
        homePage().siderBar.clickMnuItemInventory();
        materialManagementPage().helper.clickElement(commonComponents().getBtnAddNew());
        Assert.assertTrue(helper.isElementVisible(commonComponents().getBtnCancel()), "The cancel button is not visible, please check the check point that check the naviaget to create material page");
    }

    @Test(testName = "FB-12340 : Ensure validation message is displayed when duplicate lowercase name exists")
    public void FB12340() {
        adminPage().helper.navigateToUrl(configObject.getUrlCreateMaterial());

        createMaterialPage().helper.enterText(createMaterialPage().getTxtMaterialName(), materialName);
        createMaterialPage().chooseBaseUnit(unit);
        createMaterialPage().enterQuantityForBranch(branhName, quantity);
        createMaterialPage().helper.scrollToElementAtBottom(commonComponents().getBtnAddNew());
        createMaterialPage().helper.clickElement(commonComponents().getBtnAddNew());

        Assert.assertEquals(helper.getText(createMaterialPage().getToastError()), inventoryDataTest.LABEL_ERROR_MESSAGE_NAME);
    }

    @Test(testName = "FB-12344 : Test uploading an image exceeding the maximum file size (5MB)")
    public void FB12344() {
        adminPage().helper.navigateToUrl(configObject.getUrlCreateMaterial());

        helper.waitForElementVisible(createMaterialPage().btnAddFile);
        helper.getWebElement(createMaterialPage().inputUploadFile).sendKeys(System.getProperty("user.dir") + "/" + inventoryDataTest.LARGE_IMAGE);
        Assert.assertEquals(helper.getText(createMaterialPage().getToastError()), inventoryDataTest.LARGE_IMAGE_ERROR_MESSAGE);
    }

    @Test(testName = "FB-12440 : Test uploading an image exceeding the maximum file size (5.1MB)")
    public void FB12440() {
        adminPage().helper.navigateToUrl(configObject.getUrlCreateMaterial());

        helper.waitForElementVisible(createMaterialPage().btnAddFile);
        helper.getWebElement(createMaterialPage().inputUploadFile).sendKeys(System.getProperty("user.dir") + "/" + inventoryDataTest.FIVE_DOT_TWO_IMAGE);
        Assert.assertEquals(helper.getText(createMaterialPage().getToastError()), inventoryDataTest.LARGE_IMAGE_ERROR_MESSAGE);
    }

    @Test(testName = "FB-12441 : Test uploading an image exceeding the maximum file size (4.9)")
    public void FB12441() {
        adminPage().helper.navigateToUrl(configObject.getUrlCreateMaterial());
        By image = By.xpath("//div[@class='image-item']");

        helper.waitForElementVisible(createMaterialPage().btnAddFile);
        helper.getWebElement(createMaterialPage().inputUploadFile).sendKeys(System.getProperty("user.dir") + "/" + inventoryDataTest.FOURD_DOT_NINE_IMAGE);
        // Increate wait time
        helper.waitForElementVisible(image);
        helper.waitForElementVisible(image);
        Assert.assertTrue(helper.isElementVisible(image), "The image was not uploaded");
    }

    @Test(testName = "FB-12362 : Verify the cancel button functionality")
    public void FB12362() {
        homePage().siderBar.clickMnuItemInventory().clickIngredientsMenu();
        materialManagementPage().helper.clickElement(commonComponents().getBtnAddNew());
        createMaterialPage().helper.enterText(createMaterialPage().getTxtMaterialName(), materialName);
        createMaterialPage().clickCancel();

        // Verify the content dialog after clicking cancel button
        Assert.assertEquals(inventoryDataTest.DIALOG_CONTENT, createMaterialPage().commonComponents.getContentConfirmationDialog(), "The dialog is not visible, PLease double check");

        createMaterialPage().commonComponents.clickIgnore();
        // Verify that after clicking on the discard button, the dialog will disappear
        helper.waitForUrl(configObject.getUrlCreateMaterial());
        helper.waitForElementInVisible(createMaterialPage().commonComponents.btnIgnore);

        createMaterialPage().clickCancel();
        createMaterialPage().commonComponents.clickBtnConfirmLeave();

        // Verify after clicking confirm leave, the page back to material management page
        helper.waitForUrl(configObject.getUrlMaterialManagement());
    }

    @Test(testName = "FB-12363 : Verify add new button functionality")
    public void FB12363() {
        String materialName = "Material" + helper.generateRandomNumber();
        adminPage().helper.navigateToUrl(configObject.getUrlCreateMaterial());
        createMaterialPage().createMaterials(
                materialName,
                "resources/image/materials/suatuoiauto.jpg",
                unit,
                materialData.getMaterial().get(0).getCostPerUnit(),
                "234",
                branhName,
                "43");

        // Verify that the product is created in material management page
        materialManagementPage().helper.clickElement(commonComponents().getSearchIcon());
        materialManagementPage().helper.enterText(materialManagementPage().getTxtSearch(), materialName);
        Assert.assertTrue(helper.isElementVisible(By.xpath(commonComponents().getTableData(materialName))), "The product was not created");
    }
}
