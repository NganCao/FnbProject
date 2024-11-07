package com.fnb.web.store.theme1.scenario_test.home.signatureProduct;

import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignatureProductScenarios extends BaseTest {
    //Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private HomeDataTest dataTest;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
    }

    @Test(priority = 1, testName = "Verify display of Signature component")
    public void FB9566() {
        Assert.assertTrue(homePage.checkDisplayOfSignature(), "Signature did not display");
    }

    @Test(priority = 2, testName = "Verify display of Signature slider")
    public void FB11215() {
        Assert.assertTrue(homePage.checkDisplayOfSignatureSlider(), "Title did not display");
    }

    @Test(priority = 3, testName = "Verify behavior of Signature after 5s")
    public void FB11214() {
        Assert.assertTrue(homePage.checkDisplaySignatureAfter5s(), "Signature did not automationally");
    }

    @Test(priority = 4, testName = "Verify display of Signature pagination")
    public void FB11217() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkDisplayOfPagination(), "Pagination did not display");
    }

    @Test(priority = 5, testName = "Verify display of Signature after clicking dot to next slide")
    public void FB11218() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkDisplaySignatureAfterClicksDot(dataTest.MAXIMUM_NUMBER_BANNER));
    }

    @Test(priority = 6, testName = "Verify display of signature details of all signature slides")
    public void FB11216() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkDisplaySignatureDetails(), homePage.actualRS);
    }
}
