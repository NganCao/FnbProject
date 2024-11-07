package com.fnb.web.admin.pages.product.management;

import com.fnb.web.admin.pages.common.CommonComponents;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Data
public class ProductDetailsPage extends CreateProductPage{
    private CommonComponents commonComponents;
    private By btnDeactivate = By.xpath("//button[normalize-space()='Deactivate']");
    private By btnActive = By.xpath("//button[normalize-space()='Activate']");
    private By badgeStatus = By.xpath("//span[contains(@class, 'badge')]");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        commonComponents = new CommonComponents(super.driver);
    }
    public String StatusText() {
        helper.smartWait();
        String text = helper.getText(badgeStatus);
        return text;
    }

    public ProductDetailsPage clickDeactivateButton() {
        helper.clickElement(btnDeactivate);
        return this;
    }

    public ProductDetailsPage clickActiveButton() {
        helper.clickElement(btnActive);
        return this;
    }
}
