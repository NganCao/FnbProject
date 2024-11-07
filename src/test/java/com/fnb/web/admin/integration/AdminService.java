package com.fnb.web.admin.integration;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonComponents;
import com.fnb.web.admin.pages.common.Header;
import com.fnb.web.admin.pages.common.SiderBar;
import com.fnb.web.admin.pages.crm.customer.CustomerDetailPage;
import com.fnb.web.admin.pages.crm.customer.CustomerManagementPage;
import com.fnb.web.admin.pages.crm.membership.MembershipManagementPage;
import com.fnb.web.admin.pages.crm.membership.UpdateMembershipPage;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.inventory.history.InventoryHistoryPage;
import com.fnb.web.admin.pages.inventory.ingredients.CreateMaterialPage;
import com.fnb.web.admin.pages.inventory.ingredients.MaterialDetailPage;
import com.fnb.web.admin.pages.inventory.ingredients.MaterialManagementPage;
import com.fnb.web.setup.Setup;
import dataObject.Crm.Customer;
import dataObject.Integration.CustomerMembership;
import dataObject.Integration.MaterialInventoryHistory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AdminService extends Setup {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public SiderBar siderBar;
    private Header header;
    private CommonComponents commonComponents;
    private HomePage homePage;
    private InventoryHistoryPage inventoryHistoryPage;
    private MaterialManagementPage materialManagementPage;
    private MaterialDetailPage materialDetailPage;
    private MembershipManagementPage membershipManagementPage;
    private UpdateMembershipPage updateMembershipPage;
    private CustomerManagementPage customerManagementPage;
    private CustomerDetailPage customerDetailPage;
    private CreateMaterialPage createMaterialPage;

    public AdminService(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        siderBar = new SiderBar(driver);
        header = new Header(driver);
        commonComponents = new CommonComponents(driver);
        homePage = new HomePage(driver);
        inventoryHistoryPage = new InventoryHistoryPage(driver);
        materialManagementPage = new MaterialManagementPage(driver);
        materialDetailPage = new MaterialDetailPage(driver);
        membershipManagementPage = new MembershipManagementPage(driver);
        updateMembershipPage = new UpdateMembershipPage(driver);
        customerManagementPage = new CustomerManagementPage(driver);
        customerDetailPage = new CustomerDetailPage(driver);
        createMaterialPage = new CreateMaterialPage(driver);
    }

    public MaterialInventoryHistory getAMaterialInventoryHistory(String ingredient, String orderID) {
        MaterialInventoryHistory materialInventoryHistory = new MaterialInventoryHistory();
        homePage.siderBar.clickMnuItemInventory().clickInventoryHistory();
        materialInventoryHistory = inventoryHistoryPage.getAMaterialHistory(ingredient, orderID);
        return materialInventoryHistory;
    }

    public Double getQuantityIngredient(String ingredientName, String branchName) {
        homePage.siderBar.clickMnuItemInventory().clickIngredientsMenu();
        materialManagementPage.clickOnMaterial(ingredientName);
        Double quantity = materialDetailPage.getTheCurrentQuantityStockOfIngredient(branchName);
        return quantity;
    }

    public CustomerMembership getCustomerMemberShip(String customerRank) {
        CustomerMembership customerMembership = new CustomerMembership();
        homePage.siderBar.clickMnuCRM().clickMembership();
        membershipManagementPage.editCustomerRank(customerRank);
        customerMembership.setDiscount(updateMembershipPage.getDiscountValue());
        customerMembership.setMaximumDiscount(updateMembershipPage.getMaximumDiscount());
        return customerMembership;
    }

    public Double getRewardPoint(String phoneNumber) {
        homePage.siderBar.clickMnuCRM().clickCustomer();
        CustomerDetailPage customerDetailPage = customerManagementPage.clickCustomerByPhone(phoneNumber);
        String beforePoint = customerDetailPage.getRewardPoint();
        return helper.convertStringToDouble(beforePoint);
    }

    public Customer.Information getCustomer(String phoneNumber) {
        Customer.Information customer = new Customer.Information();
        homePage.siderBar.clickMnuCRM().clickCustomer();
        CustomerDetailPage customerDetailPage = customerManagementPage.clickCustomerByPhone(phoneNumber);
        customer.setFirstName(customerDetailPage.getName());
        customer.setLastName(customerDetailPage.getLastName());

        Customer.Information.Address address = new Customer.Information.Address();

        address.setWard(customerDetailPage.getWard());
        address.setCountry(customerDetailPage.getCountry());
        address.setCity(customerDetailPage.getCityProvince());
        address.setDistrict(customerDetailPage.getDistrict());
        customer.setAddress(address);

        customer.setBirthDay(customer.getBirthDay());
        customer.setGender(customerDetailPage.getGender());
        customer.setEmail(customerDetailPage.getEmail());
        customer.setRank(customerDetailPage.getRank());
        customer.setRewardPoint(customerDetailPage.getRewardPoint());
        customer.setPhone(customerDetailPage.getPhoneNumber());
        customer.setTotalOrder(customerDetailPage.getTotalOrder());
        customer.setTotalMoney(customerDetailPage.getTotalMoney());
        return customer;
    }

    /**
     This method will check if points are added after the customer completes the order.
     @Params: expectedRewardPoint: This point is calculated by yourself
     */
    public void AssertionRewardPoint(String customerName, int expectedRewardPoint) {
        homePage.siderBar.clickMnuCRM().clickCustomer();
        customerManagementPage.clickCustomer(customerName);
        int afterPoint = (int) (helper.convertStringToDouble(customerDetailPage.getRewardPoint()));
        Assert.assertEquals(afterPoint, expectedRewardPoint, "The point earned is not correct");
    }

    /**
     This method will check if ingredient are deducted after the customer completes the order.
     */
    public void AssertionOOSIngredient(String ingredientName, String branchName, Double expectedQuantityOfIngredient) {
        homePage.siderBar.clickMnuItemInventory().clickIngredientsMenu();
        materialManagementPage.clickOnMaterial(ingredientName);
        Double after_Actual_Quantity = materialDetailPage.getTheCurrentQuantityStockOfIngredient(branchName);
        Assert.assertEquals(after_Actual_Quantity, expectedQuantityOfIngredient, "The OOS have some not the same, Please check");
    }

    /**
     This method will check the inventory history after customer complete order
     */
    public void AssertionInventoryHistory(String ingredientName, String orderID, Double expected_quantity_BeforeCompleteOrder, Double expected_quantity_AfterCompleteOrder, Double expected_totalIngredientWhichWillBeDeducted) {
        homePage.siderBar.clickMnuItemInventory().clickIngredientsMenu();
        MaterialInventoryHistory materialInventoryHistory = getAMaterialInventoryHistory(ingredientName, orderID);
        Double previous = helper.convertStringToDouble(materialInventoryHistory.getPreviousQuantity());
        Double remain = helper.convertStringToDouble(materialInventoryHistory.getRemain());
        Double change = helper.convertStringToDouble(materialInventoryHistory.getChange());

        Assert.assertEquals(previous, expected_quantity_BeforeCompleteOrder,"The previous quantity is not consistent, Please check!");
        Assert.assertEquals(remain, expected_quantity_AfterCompleteOrder,"The remain quantity is not consistent, Please check!");
        Assert.assertEquals(change, expected_totalIngredientWhichWillBeDeducted * (-1), "The change quantity is not consistent, Please check");
    }

    public void changeTheQuantityOfIngredient(String ingredientName, String branchName, String quantity) {
        homePage.siderBar.clickMnuItemInventory().clickIngredientsMenu();
        materialManagementPage.clickOnMaterial(ingredientName);
        materialDetailPage.clickEdit();
        createMaterialPage.enterQuantityForBranch(branchName, quantity);
        helper.scrollToElementAtBottom(commonComponents.getBtnUpdate());
        helper.clickElement(commonComponents.getBtnUpdate());
        helper.waitForElementVisible(createMaterialPage.getToastSuccessMessage());
        helper.smartWait();
    }
}
