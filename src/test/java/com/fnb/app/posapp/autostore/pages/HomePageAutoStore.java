package com.fnb.app.posapp.autostore.pages;

import com.fnb.app.posapp.autostore.pages.createorder.CreateOrderPage;
import com.fnb.app.posapp.autostore.pages.customer.CustomerPage;
import com.fnb.app.posapp.autostore.pages.dashboard.DashboardPage;
import com.fnb.app.posapp.autostore.pages.inventorycheck.InventoryCheckPage;
import com.fnb.app.posapp.autostore.pages.login.LoginPageAutoStore;
import com.fnb.app.posapp.autostore.pages.order.OrderDetailPage;
import com.fnb.app.posapp.autostore.pages.order.OrderManagementPage;
import com.fnb.app.posapp.autostore.pages.payment.PaymentDialogPage;
import com.fnb.app.setup.BaseSetup;

public class HomePageAutoStore extends BaseSetup {
    public LoginPageAutoStore navigateToLoginPageAutoStore() {
        return new LoginPageAutoStore(driver);
    }

    public DashboardPage navigateToDashboard() {
        return new DashboardPage(driver);
    }

    public CustomerPage navigateToCustomerPage() {
        return new CustomerPage(driver);
    }

    public OrderManagementPage navigateToOrderManagementPage() {
        return new OrderManagementPage(driver);
    }

    public OrderDetailPage navigateToOrderDetailPage() {
        return new OrderDetailPage(driver);
    }

    public PaymentDialogPage navigateToPaymentDialogPage() {
        return new PaymentDialogPage(driver);
    }

    public CreateOrderPage navigateToCreateOrderPage(){return new CreateOrderPage(driver);}

    public InventoryCheckPage navigateToInventoryCheckPage(){ return new InventoryCheckPage(driver);}
}
