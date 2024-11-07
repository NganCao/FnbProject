package com.fnb.app.posapp.autostore.scenariotests.createorder;

import com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage;
import com.fnb.app.posapp.autostore.pages.login.LoginPageAutoStore;
import com.fnb.app.setup.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateOrderScenarioTest extends BaseTest {
    CreateOrderPage createOrderPage;
    String subTotalPrice;
    @BeforeClass
    public void navigateToPage() {
        createOrderPage = homePageAutoStore.navigateToCreateOrderPage();
    }
    @Test(priority = 0)
    public void navigateToDashBoard() {
        createOrderPage.clickElement(createOrderPage.AllowBtn);
        createOrderPage.clickElement(createOrderPage.LaterBtn);
        createOrderPage.clickElement(createOrderPage.LoginBtn);
        createOrderPage.clickElement(createOrderPage.AutomationBranch);
        createOrderPage.waitToLoadData();
        createOrderPage.clickElement(createOrderPage.AddProduct1);
        createOrderPage.clickElement(createOrderPage.AddProduct2);
        createOrderPage.clickElement(createOrderPage.CustomerIcon);
        createOrderPage.clickElement(createOrderPage.CustomerInputField);
        createOrderPage.sendKey(createOrderPage.CustomerInputField, "0981815766");
        createOrderPage.clickElement(createOrderPage.CustomerNameInSearchField);
        //use point available
        createOrderPage.clickElement(createOrderPage.ToggleButton);
        //subtotalPrice: 200,000
//        subTotalPrice = createOrderPage.getText(createOrderPage.SubtotalPrice);

    }

}
