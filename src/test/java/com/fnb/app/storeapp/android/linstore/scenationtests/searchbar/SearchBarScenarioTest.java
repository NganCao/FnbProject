package com.fnb.app.storeapp.android.linstore.scenationtests.searchbar;

import com.fnb.app.storeapp.android.linstore.pages.searchbar.SearchBarPage;
import com.fnb.app.setup.BaseTest;
import com.fnb.utils.helpers.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchBarScenarioTest extends BaseTest {
    SearchBarPage searchBarPage;
    SoftAssert softAssert;
    static final String NOT_FOUND_STORE = "Không có dữ liệu";

    @BeforeClass
    public void navigateToPage() {
        searchBarPage = homePageLinStore.navigateToSearchBar();
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void veifySearchBar() {
        Log.info("Running Verify function search bar in home page");
        searchBarPage.navigateToSearchItemField();
    }

    @Test(priority = 1)
    public void VerifyUI01() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplaySeachIcon(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 2)
    public void VerifyUI02() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplaySeachInputField(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 3)
    public void VerifyUI03() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayHistoryTitle(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 4)
    public void VerifyUI04() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayRecommendTitle(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 5)
    public void VerifyUI05() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag0(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 6)
    public void VerifyUI06() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag1(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 7)
    public void VerifyUI07() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag2(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 8)
    public void VerifyUI08() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag3(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 9)
    public void VerifyUI09() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag4(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 10)
    public void VerifyUI10() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag5(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 11)
    public void VerifyUI11() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag6(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 12)
    public void VerifyUI12() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayTag7(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

    @Test(priority = 13)
    public void VerifyUI13() {
        Log.info("Running Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplaySeachIcon(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
    }

//    @Test(priority = 14)
//    public void VerifyUI14() {
//        Log.info("Running Verify search icon search bar in home page");
//        assertTrue(searchBarPage.checkDisplaySeachIcon(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
//    }

    @Test(priority = 15)
    public void verifySearchNoResult() {
        Log.info("Running Verify function search bar in home page");
        searchBarPage.searchItemNoResult();
        assertTrue(searchBarPage.checkDisplayEmptySearchResultIcon(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
        assertTrue(searchBarPage.checkDisplayNoResult(), "Verify testcase 01 is fail -  Verify search icon search bar in home page");
        assertEquals(searchBarPage.checkTextNoResult(), NOT_FOUND_STORE, "Verify testcase 9779 is fail");
    }

    @Test(priority = 16)
    public void verifySearchResult() {
        Log.info("Running Verify function search bar in home page");
        searchBarPage.searchItemResult();
        softAssert.assertTrue(searchBarPage.checkDisplayItemInfoId0(), "Verify testcase 01 is fail");
        softAssert.assertTrue(searchBarPage.checkDisplayThumbnailId0(), "Verify testcase 01 is fail");
        softAssert.assertTrue(searchBarPage.checkDisplayItemNameId(), "Verify testcase 01 is fail");
        softAssert.assertTrue(searchBarPage.checkDisplaySellingPriceId(), "Verify testcase 01 is fail");
//        softAssert.assertTrue(searchBarPage.checkDisplayOriginalPriceId(), "Verify testcase 01 is fail");
        softAssert.assertAll();
    }
}
