package com.fnb.web.admin.pages.inventory.ingredients;

import com.fnb.web.admin.pages.common.CommonComponents;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Data
public class MaterialDetailPage extends CreateMaterialPage{
    private By btnDropDown = By.xpath("//a[contains(@class, 'ant-dropdown-trigger')]");
    private By btnDeactivate = By.xpath("//button[normalize-space()='Deactivate']");
    private By btnActive = By.xpath("//button[normalize-space()='Activate']");
    private CommonComponents commonComponents;

    public MaterialDetailPage(WebDriver driver) {
        super(driver);
        commonComponents = new CommonComponents(driver);
    }

//    public MaterialDetailPage clickDropDownButton() {
//        helper.clickElement(btnDropDown);
//        return this;
//    }

    public MaterialDetailPage clickDeactivateButton() {
        helper.clickElement(btnDeactivate);
        return this;
    }

    public MaterialDetailPage clickActiveButton() {
        helper.clickElement(btnActive);
        return this;
    }

    public Double getTheCurrentQuantityStockOfIngredient(String branch) {
        By element = By.xpath("(//*[.//*[text()='"+adminLocalization.getMaterial().getBranchAndWarehouse()+"'] and .//*[text()='"+adminLocalization.getMaterial().getIngredientQuantityOfBranch()+"']])[last()]//tr[.//*[text()='"+branch+"']]//*[@class='table-quantity-in-stock']");
        String quantity = helper.getText(element);
        Double quantityDouble = helper.convertStringToDouble(quantity);
        return quantityDouble;
    }

    public void clickEdit() {
        helper.clickElement(By.xpath("//button[.//*[text()='"+adminLocalization.getButton().getEdit()+"']]"));
    }
}
