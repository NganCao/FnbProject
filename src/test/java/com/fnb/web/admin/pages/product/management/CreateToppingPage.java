package com.fnb.web.admin.pages.product.management;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.admin.pages.common.SiderBar;
import dataObject.Product.Topping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CreateToppingPage extends CreateTopping_Product_Common {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SiderBar siderBar;

    public CreateToppingPage(WebDriver driver) {
        super(driver, CreateToppingPage.class);
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        helper = new Helper(driver, wait, actions);
        siderBar = new SiderBar(driver);
        this.driver = driver;
    }

    public void enterToppingName(String toppingName) {
        helper.enterText(txtProductName, toppingName);
    }

    public CreateToppingPage click_selSearchMaterial() {
        helper.clickElement(selSearchMaterial);
        return this;
    }

    public void chooseMaterialNameForTopping(List<Topping.Information.ProductInventoryData> productInventoryData) {
        click_selSearchMaterial();
        for (int i = 0; i < productInventoryData.size(); i++) {
            addSelectedIngredient(productInventoryData.get(i).getMaterial());
        }
        // Click Add Ingredient
        helper.clickElement(By.xpath("//*[contains(text(), 'Selected Ingredient(s)')]"));
    }

    public void chooseMaterialRecipeForTopping(List<Topping.Information.ProductInventoryData> productInventoryData) {
        for (int i = 0; i < productInventoryData.size(); i++) {
            helper.scrollToElementAtTop(selSearchMaterial);
            By quantityElement = getSelectedMaterialQuantityEle(productInventoryData.get(i).getMaterial());
            helper.scrollToElementAtTop(quantityElement);
            helper.enterText(quantityElement, productInventoryData.get(i).getQuantity());
        }
    }

    public CreateToppingPage clickCreateToppingBottom() {
        helper.clickElement(btnBottom_CreateProductOrTopping);
        getCommonComponents().waitSuccessToast();
        return this;
    }
    public void createATopping(String toppingName, String image, String price, List<Topping.Information.Platform> platforms, String unit, List<Topping.Information.ProductInventoryData> productInventoryData) {
        enterToppingName(toppingName);
        upLoadImage(image);
        enterPrice(price);
        choosePlatformForTopping(platforms);
        chooseBaseUnit(unit);
        chooseMaterialNameForTopping(productInventoryData);
        chooseMaterialRecipeForTopping(productInventoryData);
        clickCreateToppingBottom();
    }
}
