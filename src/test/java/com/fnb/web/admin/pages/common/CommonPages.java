package com.fnb.web.admin.pages.common;

import com.fnb.web.admin.integration.AdminService;
import com.fnb.web.admin.pages.configuration.BillAndTicketTab;
import com.fnb.web.admin.pages.configuration.ConfigurationPage;
import com.fnb.web.admin.pages.configuration.OperationTab;
import com.fnb.web.admin.pages.crm.PointConfigurationPage;
import com.fnb.web.admin.pages.crm.customer.CreateNewCustomerPage;
import com.fnb.web.admin.pages.crm.customer.CustomerManagementPage;
import com.fnb.web.admin.pages.crm.membership.CreateMembershipPage;
import com.fnb.web.admin.pages.crm.membership.MembershipManagementPage;
import com.fnb.web.admin.pages.crm.membership.UpdateMembershipPage;
import com.fnb.web.admin.pages.crm.segment.CreateCustomerSegmentPage;
import com.fnb.web.admin.pages.home.HomePage;
import com.fnb.web.admin.pages.inventory.category.MaterialCategoryManagementPage;
import com.fnb.web.admin.pages.inventory.history.InventoryHistoryPage;
import com.fnb.web.admin.pages.inventory.ingredients.CreateMaterialPage;
import com.fnb.web.admin.pages.inventory.ingredients.MaterialDetailPage;
import com.fnb.web.admin.pages.inventory.ingredients.MaterialManagementPage;
import com.fnb.web.admin.pages.inventory.inventory_control.InventoryControlPage;
import com.fnb.web.admin.pages.inventory.inventory_control.purchase_order.CreatePurchaseOrderPage;
import com.fnb.web.admin.pages.inventory.inventory_control.purchase_order.DetailPurchaseOrderPage;
import com.fnb.web.admin.pages.inventory.inventory_control.purchase_order.PurchaseOrderManagementPage;
import com.fnb.web.admin.pages.inventory.supplier.SupplierManagement;
import com.fnb.web.admin.pages.inventory.inventory_control.transfer_material.CreateTransferMaterialPage;
import com.fnb.web.admin.pages.inventory.inventory_control.transfer_material.DetailTransferMaterialPage;
import com.fnb.web.admin.pages.inventory.inventory_control.transfer_material.TransferMaterialManagementPage;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.marketing.emailCampaign.CreateEmailCampaignPage;
import com.fnb.web.admin.pages.marketing.emailCampaign.EmailCampaignManagementPage;
import com.fnb.web.admin.pages.marketing.notificationCampaign.CreateNotificationCampaignPage;
import com.fnb.web.admin.pages.marketing.notificationCampaign.NotificationManagementPage;
import com.fnb.web.admin.pages.marketing.qrOrder.CreateQrOrderPage;
import com.fnb.web.admin.pages.marketing.qrOrder.QrOrderManagementPage;
import com.fnb.web.admin.pages.packages.PackagePage;
import com.fnb.web.admin.pages.product.category.ProductCategoryManagementPage;
import com.fnb.web.admin.pages.product.combo.ComboManagementPage;
import com.fnb.web.admin.pages.product.combo.CreateComboPage;
import com.fnb.web.admin.pages.product.management.CreateProductPage;
import com.fnb.web.admin.pages.product.management.ProductDetailsPage;
import com.fnb.web.admin.pages.product.management.ProductEditPage;
import com.fnb.web.admin.pages.product.management.ProductManagementPage;
import com.fnb.web.admin.pages.product.options.OptionManagementPage;
import com.fnb.web.admin.pages.register.RegisterPage;
import com.fnb.web.admin.pages.report.RevenuePage;
import com.fnb.web.admin.pages.report.TransactionPage;
import com.fnb.web.admin.pages.store.areaTable.AreaTableManagementPage;
import com.fnb.web.admin.pages.store.feeandtax.FeeManagementPage;
import com.fnb.web.admin.pages.store.feeandtax.TaxManagementPage;
import com.fnb.web.admin.pages.store.promotion.PromotionManagementPage;
import com.fnb.web.admin.pages.store.staff.StaffManagementPage;
import com.fnb.web.setup.BaseTest;

public class CommonPages extends BaseTest {
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected CreateProductPage createProductPage;
    protected ProductManagementPage productManagementPage;
    protected ProductDetailsPage productDetailsPage;
    protected CommonComponents commonComponents;
    protected ProductEditPage productEditPage;
    protected TaxManagementPage taxManagement;
    protected TaxManagementPage.AddNewTaxDialog addNewTaxDialog;
    protected PackagePage packagePage;
    protected StaffManagementPage staffManagementPage;
    protected MaterialManagementPage materialManagementPage;
    protected CreateMaterialPage createMaterialPage;
    protected CreateMaterialPage.UnitConversionDialog unitConversionDialog;
    protected OptionManagementPage optionManagementPage;
    protected ComboManagementPage comboManagementPage;
    protected CreateComboPage createComboPage;
    protected MaterialDetailPage materialDetailPage;
    protected TransactionPage transactionPage;
    protected RevenuePage revenuePage;
    protected ProductCategoryManagementPage productCategoryManagementPage;
    protected MaterialCategoryManagementPage materialCategoryManagementPage;
    protected SupplierManagement supplierManagementPage;
    protected PurchaseOrderManagementPage purchaseOrderManagementPage;
    protected CreatePurchaseOrderPage createPurchaseOrderPage;
    protected DetailPurchaseOrderPage detailPurchaseOrderPage;
    protected CreateTransferMaterialPage createTransferMaterialPage;
    protected TransferMaterialManagementPage transferMaterialManagementPage;
    protected FilterDialog filterDialog;
    protected CTADialog ctaDialog;
    protected CreateNewCustomerPage createNewCustomerPage;
    protected CustomerManagementPage customerManagementPage;
    protected CreateCustomerSegmentPage createCustomerSegmentPage;
    protected CreateMembershipPage createMembershipPage;
    protected AreaTableManagementPage areaTableManagementPage;
    protected PromotionManagementPage promotionManagementPage;
    protected DetailTransferMaterialPage detailTransferMaterialPage;
    protected FeeManagementPage feeManagementPage;
    protected FeeManagementPage.AddNewFeeDialog addNewFeeDialog;
    protected QrOrderManagementPage qrOrderManagementPage;
    protected CreateQrOrderPage createQrOrderPage;
    protected EmailCampaignManagementPage emailCampaignManagementPage;
    protected CreateEmailCampaignPage createEmailCampaignPage;
    protected NotificationManagementPage notificationManagementPage;
    protected CreateNotificationCampaignPage createNotificationCampaignPage;
    protected InventoryControlPage inventoryControlPage;
    protected ConfigurationPage configurationPage;
    protected OperationTab operationTab;
    protected BillAndTicketTab billAndTicketTab;
    protected PointConfigurationPage pointConfigurationPage;
    protected MembershipManagementPage membershipManagementPage;
    protected UpdateMembershipPage updateMembershipPage;
    protected InventoryHistoryPage inventoryHistoryPage;
    protected AdminService adminService;

    public AdminService adminService() {
        if(adminService == null) {
            adminService = new AdminService(getDriver());
        }
        return adminService;
    }

    public InventoryHistoryPage inventoryHistoryPage() {
        if(inventoryHistoryPage == null) {
            inventoryHistoryPage = new InventoryHistoryPage(getDriver());
        }
        return inventoryHistoryPage;
    }

    public UpdateMembershipPage updateMembershipPage() {
        if(updateMembershipPage == null) {
            updateMembershipPage = new UpdateMembershipPage(getDriver());
        }
        return updateMembershipPage;
    }

    public MembershipManagementPage membershipManagementPage() {
        if(membershipManagementPage == null) {
            membershipManagementPage = new MembershipManagementPage(getDriver());
        }
        return membershipManagementPage;
    }

    public PointConfigurationPage pointConfigurationPage() {
        if(pointConfigurationPage == null) {
            pointConfigurationPage = new PointConfigurationPage(getDriver());
        }
        return pointConfigurationPage;
    }

    public BillAndTicketTab billAndTicketTab() {
        if(billAndTicketTab == null) {
            billAndTicketTab = new BillAndTicketTab(getDriver());
        }
        return billAndTicketTab;
    }

    public OperationTab operationTab() {
        if(operationTab == null) {
            operationTab = new OperationTab(getDriver());
        }
        return operationTab;
    }

    public ConfigurationPage configurationPage() {
        if(configurationPage == null) {
            configurationPage = new ConfigurationPage(getDriver());
        }
        return configurationPage;
    }

    public InventoryControlPage inventoryControlPage() {
        if(inventoryControlPage == null) {
            inventoryControlPage = new InventoryControlPage(getDriver());
        }
        return inventoryControlPage;
    }

    public CreateNotificationCampaignPage createNotificationCampaignPage() {
        if(createNotificationCampaignPage == null) {
            createNotificationCampaignPage = new CreateNotificationCampaignPage(getDriver());
        }
        return createNotificationCampaignPage;
    }

    public NotificationManagementPage notificationManagementPage() {
        if(notificationManagementPage == null) {
            notificationManagementPage = new NotificationManagementPage(getDriver());
        }
        return notificationManagementPage;
    }

    public CreateEmailCampaignPage createEmailCampaignPage() {
        if(createEmailCampaignPage == null) {
            createEmailCampaignPage = new CreateEmailCampaignPage(getDriver());
        }
        return createEmailCampaignPage;
    }

    public EmailCampaignManagementPage emailCampaignManagementPage() {
        if(emailCampaignManagementPage == null) {
            emailCampaignManagementPage = new EmailCampaignManagementPage(getDriver());
        }
        return emailCampaignManagementPage;
    }

    public CreateQrOrderPage createQrOrderPage() {
        if(createQrOrderPage == null) {
            createQrOrderPage = new CreateQrOrderPage(getDriver());
        }
        return createQrOrderPage;
    }

    public QrOrderManagementPage qrOrderManagementPage() {
        if(qrOrderManagementPage == null) {
            qrOrderManagementPage = new QrOrderManagementPage(getDriver());
        }
        return qrOrderManagementPage;
    }

    public FeeManagementPage feeManagementPage() {
        if(feeManagementPage == null) {
            feeManagementPage = new FeeManagementPage(getDriver());
        }
        return feeManagementPage;
    }

    public PromotionManagementPage promotionManagementPage() {
        if(promotionManagementPage == null) {
            promotionManagementPage = new PromotionManagementPage(getDriver());
        }
        return promotionManagementPage;
    }

    public AreaTableManagementPage areaTableManagementPage() {
        if(areaTableManagementPage == null) {
            areaTableManagementPage = new AreaTableManagementPage(getDriver());
        }
        return areaTableManagementPage;
    }

    public CreateMembershipPage createMembershipPage() {
        if(createMembershipPage == null) {
            createMembershipPage = new CreateMembershipPage(getDriver());
        }
        return createMembershipPage;
    }

    public CreateCustomerSegmentPage createCustomerSegmentPage() {
        if(createCustomerSegmentPage == null) {
            createCustomerSegmentPage = new CreateCustomerSegmentPage(getDriver());
        }
        return createCustomerSegmentPage;
    }

    public CustomerManagementPage customerManagementPage() {
        if(customerManagementPage == null) {
            customerManagementPage = new CustomerManagementPage(getDriver());
        }
        return customerManagementPage;
    }

    public CreateNewCustomerPage createNewCustomerPage() {
        if(createNewCustomerPage == null) {
            createNewCustomerPage = new CreateNewCustomerPage(getDriver());
        }
        return createNewCustomerPage;
    }

    public CTADialog ctaDialog() {
        if(ctaDialog == null) {
            ctaDialog = new CTADialog(getDriver());
        }
        return ctaDialog;
    }

    public FilterDialog filterDialog() {
        if(filterDialog == null) {
            filterDialog = new FilterDialog(getDriver());
        }
        return filterDialog;
    }

    public TransferMaterialManagementPage transferMaterialManagementPage() {
        if(transferMaterialManagementPage == null) {
            transferMaterialManagementPage = new TransferMaterialManagementPage(getDriver());
        }
        return transferMaterialManagementPage;
    }

    public CreateTransferMaterialPage createTransferMaterialPage() {
        if(createTransferMaterialPage == null) {
            createTransferMaterialPage = new CreateTransferMaterialPage(getDriver());
        }
        return createTransferMaterialPage;
    }

    public DetailPurchaseOrderPage detailPurchaseOrderPage() {
        if(detailPurchaseOrderPage == null) {
            detailPurchaseOrderPage = new DetailPurchaseOrderPage(getDriver());
        }
        return detailPurchaseOrderPage;
    }

    public PurchaseOrderManagementPage purchaseOrderManagementPage() {
        if(purchaseOrderManagementPage == null) {
            purchaseOrderManagementPage = new PurchaseOrderManagementPage(getDriver());
        }
        return purchaseOrderManagementPage;
    }

    public CreatePurchaseOrderPage createPurchaseOrderPage() {
        if(createPurchaseOrderPage == null) {
            createPurchaseOrderPage = new CreatePurchaseOrderPage(getDriver());
        }
        return createPurchaseOrderPage;
    }

    public SupplierManagement supplierManagementPage() {
        if(supplierManagementPage == null) {
            supplierManagementPage = new SupplierManagement(getDriver());
        }
        return supplierManagementPage;
    }

    public MaterialCategoryManagementPage materialCategoryManagementPage() {
        if(materialCategoryManagementPage == null) {
            materialCategoryManagementPage = new MaterialCategoryManagementPage(getDriver());
        }
        return materialCategoryManagementPage;
    }

    public ProductCategoryManagementPage productCategoryManagementPage() {
        if(productCategoryManagementPage == null) {
            productCategoryManagementPage = new ProductCategoryManagementPage(getDriver());
        }
        return productCategoryManagementPage;
    }

    public RevenuePage revenuePage() {
        if(revenuePage == null) {
            revenuePage = new RevenuePage(getDriver());
        }
        return revenuePage;
    }

    public TransactionPage transactionPage() {
        if(transactionPage == null) {
            transactionPage = new TransactionPage(getDriver());
        }
        return transactionPage;
    }

    public CreateMaterialPage createMaterialPage() {
        if(createMaterialPage == null) {
            createMaterialPage = new CreateMaterialPage(getDriver());
        }
        return createMaterialPage;
    }

    public MaterialManagementPage materialManagementPage() {
        if(materialManagementPage == null) {
            materialManagementPage = new MaterialManagementPage(getDriver());
        }
        return materialManagementPage;
    }

    public PackagePage packagePage() {
        if(packagePage == null) {
            packagePage = new PackagePage(getDriver());
        }
        return packagePage;
    }

    public RegisterPage registerPage() {
        if(registerPage == null) {
            registerPage = new RegisterPage(getDriver());
        }
        return registerPage;
    }
    public TaxManagementPage taxManagement() {
        if (taxManagement == null) {
            taxManagement = new TaxManagementPage(getDriver());
        }
        return taxManagement;
    }

    public TaxManagementPage.AddNewTaxDialog addNewTaxDialog() {
        if (addNewTaxDialog == null) {
            addNewTaxDialog = new TaxManagementPage(getDriver()).new AddNewTaxDialog();
        }
        return addNewTaxDialog;
    }

    public ProductEditPage productEditPage() {
        if (productEditPage == null) {
            productEditPage = new ProductEditPage(getDriver());
        }
        return productEditPage;
    }

    public CommonComponents commonComponents() {
        if (commonComponents == null) {
            commonComponents = new CommonComponents(getDriver());
        }
        return commonComponents;
    }
    public ProductDetailsPage productDetailsPage() {
        if (productDetailsPage == null) {
            productDetailsPage = new ProductDetailsPage(getDriver());
        }
        return productDetailsPage;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(getDriver());
        }
        return loginPage;
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage(getDriver());
        }
        return homePage;
    }

    public CreateProductPage createProductPage() {
        if (createProductPage == null) {
            createProductPage = new CreateProductPage(getDriver());
        }
        return createProductPage;
    }

    public ProductManagementPage productManagementPage() {
        if(productManagementPage == null) {
            productManagementPage = new ProductManagementPage(getDriver());
        }
        return productManagementPage;
    }

    public StaffManagementPage staffManagementPage() {
        if(staffManagementPage == null) {
            staffManagementPage = new StaffManagementPage(getDriver());
        }
        return staffManagementPage;
    }

    public CreateMaterialPage.UnitConversionDialog unitConversionDialog() {
        if(unitConversionDialog == null) {
            unitConversionDialog = new CreateMaterialPage(getDriver()). new UnitConversionDialog();
        }
        return unitConversionDialog;
    }

    public OptionManagementPage optionManagementPage() {
        if(optionManagementPage == null) {
            optionManagementPage = new OptionManagementPage(getDriver());
        }
        return optionManagementPage;
    }

    public ComboManagementPage comboManagementPage() {
        if(comboManagementPage == null) {
            comboManagementPage = new ComboManagementPage(getDriver());
        }
        return comboManagementPage;
    }

    public CreateComboPage createComboPage() {
        if(createComboPage == null) {
            createComboPage = new CreateComboPage(getDriver());
        }
        return createComboPage;
    }

    public MaterialDetailPage materialDetailPage() {
        if(materialDetailPage == null) {
            materialDetailPage = new MaterialDetailPage(getDriver());
        }
        return materialDetailPage;
    }
}
