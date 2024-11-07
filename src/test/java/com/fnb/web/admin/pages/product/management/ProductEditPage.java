package com.fnb.web.admin.pages.product.management;

import com.fnb.web.admin.pages.common.CommonComponents;
import dataObject.Product.Product;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;



@Data
public class ProductEditPage extends CreateProductPage {
    public ProductEditPage(WebDriver driver) {
        super(driver);
        commonComponents = new CommonComponents(driver);
    }

    private CommonComponents commonComponents;
    private By clearIconOfUnit = By.xpath("//h4[normalize-space()='Unit']//following-sibling::div//span[@class='ant-select-clear']");
    private By btnSave = By.xpath("//button[normalize-space()='Save Changes']");
    private By clearIconOfTax = By.xpath("//h4[normalize-space()='Tax']//following-sibling::div//span[@class='ant-select-clear']");
    private By checkBoxIsTopping = By.xpath("//span[@class='ant-checkbox']//input");

    public ProductEditPage clearAllFields() {
        // Clear product name
        helper.smartWait();
        while (!helper.getWebElement(getTxtProductName()).getAttribute("value").equals("")) {
            helper.getWebElement(getTxtProductName()).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            helper.getWebElement(getTxtProductName()).sendKeys(Keys.BACK_SPACE);
        }

        // Clear image
        while (helper.isElementVisible(By.xpath("//div[@class='image-item']"))) {
            actions.moveToElement(helper.getWebElement(By.xpath("//div[@class='image-item']"))).perform();
            helper.clickElement(By.xpath("//*[local-name()='svg' and contains(@class, 'trash-fill-icon')]//ancestor::div[contains(@class, 'btn-upload-image-item')]"));
        }

        // Clear price
        helper.getWebElement(getTxtPrice()).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        helper.getWebElement(getTxtPrice()).sendKeys(Keys.BACK_SPACE);
        helper.waitExpectedAttributeToBe(getTxtPrice(), "value", "", "The price is not clear");

        // Clear tax
        helper.scrollToElementAtMiddle(getSelSearchTax());
        helper.hover(clearIconOfTax);
        helper.clickElement(clearIconOfTax);

        // Clear unit
        helper.scrollToElementAtBottom(getSelSearchUnit());
        helper.hover(clearIconOfUnit);
        helper.clickElement(clearIconOfUnit);

        // Clear material
        helper.scrollToElementAtMiddle(getSelSearchMaterial());
        List<WebElement> elements = getDriver().findElements(By.xpath("//tr[contains(@class, 'ant-table-row')]"));
        for (int i=0 ; i < elements.size(); i++) {
            helper.clickElement(By.xpath("//tr[contains(@class, 'ant-table-row')][1]//a"));
        }
        return this;
    }

    public void updateProduct(String productName, String image, List<Product.Information.ToppingName> toppingNames, List<Product.Information.Option> option, List<Product.Information.Price> price, List<Product.Information.Platform> platforms, String unit, List<Product.Information.productInventoryData> productInventoryData, String taxName) {
        helper.enterText(getTxtProductName(), productName);
        upLoadImage(image);
        chooseTopping(toppingNames);
        selectOption(option);
        enterPrice(price);
        choosePlatform(platforms);
        selectTax(taxName);
        chooseBaseUnit(unit);
        chooseMaterialRecipe(productInventoryData);
        helper.scrollToElementAtBottom(btnSave);
        clickSaveButton();
        helper.waitForElementVisible(getToastSuccessMessage());
    }

    public ProductEditPage clickSaveButton() {
        helper.clickElement(btnSave);
        return this;
    }
}
