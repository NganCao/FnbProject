package com.fnb.web.admin.scenario_test.product;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.combo.ComboManagementPage;
import com.fnb.web.admin.pages.product.combo.CreateComboPage;
import com.fnb.web.admin.pages.product.management.DataTest;
import com.fnb.web.setup.Setup;
import dataObject.Product.SpecificCombo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.fnb.web.setup.Setup.configObject;

import java.io.IOException;

public class CreateComboTest extends CommonPages {
    CreateComboPage createComboPage;
    ComboManagementPage comboManagementPage;

    @BeforeClass
    public void prepareData() throws IOException {
        createComboPage = new CreateComboPage(getDriver());
        comboManagementPage = new ComboManagementPage(getDriver());
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
    }

    @BeforeMethod
    public void navigateToCreateComboPage() {
        adminPage().helper.navigateToUrl(Setup.configObject.getUrlComboManagement());
        adminPage().helper.waitForUrl(Setup.configObject.getUrlComboManagement());
    }

    @Test
    public void Testing() {
        homePage().helper.navigateTo(configObject.getUrlCreateCombo());
        SpecificCombo.Information specificCombo = getSetup().specificComboData.getSpecificCombo().get(0);
        createComboPage.createSpecificCombo(
                specificCombo.getName(),
                specificCombo.getImage(),
                createComboPage.helper.getNextDay(),
                createComboPage.helper.getNextDay(),
                specificCombo.getBranchNames(),
                specificCombo.getComboProductNames(),
                specificCombo.isShowAllBranches(),
                specificCombo.getSellingPrice());
        comboManagementPage.deActiveCombo("2411PTEST COMBO1");
    }
}
