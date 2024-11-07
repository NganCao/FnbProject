package com.fnb.app.storeapp.android.linstore.scenationtests.blogs;

import com.fnb.app.storeapp.android.linstore.pages.blogs.BlogsPage;
import com.fnb.app.storeapp.android.linstore.pages.login.LoginPageLinStore;
import com.fnb.app.setup.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BlogsScenarioTest extends BaseTest {
    LoginPageLinStore loginPageLinStore;
    BlogsPage blogsPage;

    @BeforeClass
    public void navigateToPage() {
        loginPageLinStore = homePageLinStore.navigateLinStoreToCreateLogin();
        blogsPage = homePageLinStore.navigateToBlogsPage();
    }
    @Test
    public void testcase1(){
        loginPageLinStore.splashScreen();
        blogsPage.swipeVertical();
        System.out.println(blogsPage.getText11());
        blogsPage.clickBlog();
    }
}
