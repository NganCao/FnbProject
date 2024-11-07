package com.fnb.app.storeapp.android.linstore.pages;

import com.fnb.app.android.linstore.pages.loginPOS.LoginPOSAppPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addnewaddress.AddNewAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.addressmanagementlist.AddressManagementListPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.delivery.editanaddress.EditAnAddressPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.pickup.PickUpManagementPage;
import com.fnb.app.storeapp.android.linstore.pages.addressmanagement.userlocation.UserLocationPage;
import com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.changepassword.ChangePasswordPage;
import com.fnb.app.storeapp.android.linstore.pages.authenticationusingpassword.updateinforaccount.UpdateInfoAccountPage;
import com.fnb.app.storeapp.android.linstore.pages.banner.BannerPage;
import com.fnb.app.storeapp.android.linstore.pages.blogs.BlogsPage;
import com.fnb.app.storeapp.android.linstore.pages.branch.BranchPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.loyaltypoint.LoyaltyPointPage;
import com.fnb.app.storeapp.android.linstore.pages.promotion.applyproductpromotion.ApplyProductPromotionPage;
import com.fnb.app.storeapp.android.linstore.pages.promotion.searchpage.PromotionInSearchPage;
import com.fnb.app.storeapp.android.linstore.pages.promotion.viewpromotion.PromotionProductListPage;
import com.fnb.app.storeapp.android.linstore.pages.register.RegisterPageLinStore;
import com.fnb.app.storeapp.android.linstore.pages.searchbar.SearchBarPage;
import com.fnb.app.setup.BaseSetup;

public class HomePageLinStore extends BaseSetup {
    public LoginPageLinStore navigateLinStoreToCreateLogin() {
        return new LoginPageLinStore(driver);
    }

    public RegisterPageLinStore navigateLinStoreToCreateRegister() {
        return new RegisterPageLinStore(driver);
    }

    public ChangePasswordPage navigateLinStoreToChangePass() {
        return new ChangePasswordPage(driver);
    }

    public UpdateInfoAccountPage navigateToUpdateInfoAccount() {
        return new UpdateInfoAccountPage(driver);
    }

    public SearchBarPage navigateToSearchBar() {
        return new SearchBarPage(driver);
    }

    public BranchPage navigateToBranch() {
        return new BranchPage(driver);
    }

    public AddNewAddressPage navigateToAddNewPage() {
        return new AddNewAddressPage(driver);
    }

    public AddressManagementListPage navigateToAddressManagementListPage() {
        return new AddressManagementListPage(driver);
    }

    public EditAnAddressPage navigateToEditAnAddressPage() {
        return new EditAnAddressPage(driver);
    }

    public PickUpManagementPage navigateToPickUpManagementPage() {
        return new PickUpManagementPage(driver);
    }

    public UserLocationPage navigateToUserLocationPage() {
        return new UserLocationPage(driver);
    }

    public LoyaltyPointPage navigateToLoyaltyPointPage() {
        return new LoyaltyPointPage(driver);
    }

    public BannerPage navigateToBannerPage() {
        return new BannerPage(driver);
    }

    public BlogsPage navigateToBlogsPage() {
        return new BlogsPage(driver);
    }

    public PromotionProductListPage navigateToPromotionProductListPage() {
        return new PromotionProductListPage(driver);
    }

    public ApplyProductPromotionPage navigateToApplyProductPromotionPage() {
        return new ApplyProductPromotionPage(driver);
    }

    public PromotionInSearchPage navigateToPromotionInSearchPage() {
        return new PromotionInSearchPage(driver);
    }

    public LoginPOSAppPage navigateToPOSLogin() {
        return new LoginPOSAppPage(driver);
    }
}
