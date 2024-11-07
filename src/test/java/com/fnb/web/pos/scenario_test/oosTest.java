package com.fnb.web.pos.scenario_test;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.CommonPages;
import com.fnb.web.admin.pages.product.management.DataTest;
import dataObject.Crm.Customer;
import org.junit.rules.TestName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fnb.web.setup.Setup.*;
import static com.fnb.web.setup.Setup.customerData;

public class oosTest extends CommonPages {
    private Helper action;
    private String posTab; private String adminTab;
    private String binhthanh_branch;
    private String category;
    private String customerName;
    private String customerPhone;

    // Product information 1
    String cafeDen_product;
    String cafeDen_product_M_priceName;
    String cafeDen_product_S_priceName;
    String cafeDen_product_M_priceValue;
    String cafeDen_product_S_priceValue;
    String cafeDen_product_caPhePhaSan_Ingredient;
    String cafeDen_product_caPhePhaSan_Ingredient_M_recipe;
    String cafeDen_product_caPhePhaSan_Ingredient_S_recipe;
    String cafeDen_product_cafe_optionName;
    String cafeDen_product_cafe_optionNam_vuaLevel;
    String cafeDen_product_cafe_optionNam_itLevel;
    String cafeDen_product_cafe_optionName_vuaLevel_quota;
    String cafeDen_product_cafe_optionName_itLevel_quota;

    @BeforeClass
    public void precondition() {
        // Prepare data
        binhthanh_branch = branchData.getBranch().get(0).getName();
        category = productCategoryData.getProductCategory().get(0).getName();
        customerName = customerData.getCustomers().get(0).getFirstName();
        customerPhone = customerData.getCustomers().get(0).getPhone();

        // Product information 1
        cafeDen_product = productData.getProduct().get(1).getName();
        cafeDen_product_caPhePhaSan_Ingredient = productData.getProduct().get(1).getProductInventoryData().get(1).getListProductPriceMaterials().get(0).getMaterial();
        cafeDen_product_caPhePhaSan_Ingredient_M_recipe = productData.getProduct().get(1).getProductInventoryData().get(1).getListProductPriceMaterials().get(0).getQuantity();
        cafeDen_product_caPhePhaSan_Ingredient_S_recipe = productData.getProduct().get(1).getProductInventoryData().get(0).getListProductPriceMaterials().get(0).getQuantity();

        cafeDen_product_M_priceName = productData.getProduct().get(1).getPrice().get(1).getPriceName();
        cafeDen_product_S_priceName = productData.getProduct().get(1).getPrice().get(0).getPriceName();

        cafeDen_product_M_priceValue = productData.getProduct().get(1).getPrice().get(1).getPriceValue();
        cafeDen_product_S_priceValue = productData.getProduct().get(1).getPrice().get(0).getPriceValue();

        cafeDen_product_cafe_optionName = productData.getProduct().get(1).getOption().get(0).getName();
        cafeDen_product_cafe_optionNam_vuaLevel = optionData.getOptions().get(1).getOptionLevels().get(1).getName();
        cafeDen_product_cafe_optionNam_itLevel = optionData.getOptions().get(1).getOptionLevels().get(0).getName();

        cafeDen_product_cafe_optionName_vuaLevel_quota = optionData.getOptions().get(1).getOptionLevels().get(1).getQuota();
        cafeDen_product_cafe_optionName_itLevel_quota = optionData.getOptions().get(1).getOptionLevels().get(0).getQuota();

        action  = adminPage().helper;
        // Get admin tab
        adminTab = getDriver().getWindowHandle();
        adminPage().navigateToHomePage(DataTest.INPUT_EMAIL, DataTest.INPUT_PASSWORD);
    }

    /**
     1.When add product to order
     1.1 If store allow to sell products when out of materials (value = true).
     * */
    @Test
    public void test1() {
        Customer.Information customer = adminService().getCustomer("0365771592");

        // Turn off OOS
        homePage().siderBar.clickButtonConfiguration();
        configurationPage().clickOperationTab();
        operationTab().turnOffOOS();

        // Reduce the material to zero
        adminService().changeTheQuantityOfIngredient(cafeDen_product_caPhePhaSan_Ingredient, binhthanh_branch, "0");
    }
}
