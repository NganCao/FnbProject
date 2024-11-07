package com.fnb.app.storeapp.android.kemstore.pages;

import com.fnb.app.storeapp.android.kemstore.pages.login.LoginPageKemStore;
import com.fnb.app.setup.BaseSetup;

public class HomePageKemStore extends BaseSetup {
    public LoginPageKemStore navigateKemStoreToCreate(){
        return new LoginPageKemStore();
    }
}
