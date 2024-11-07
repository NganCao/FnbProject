package com.fnb.web.admin.pages.store.feeandtax;

import com.fnb.web.setup.Setup;
import lombok.Data;
import org.openqa.selenium.By;

@Data
public class FeeAndTaxCommon extends Setup {
    By btnTabFee = By.xpath("//div[contains(@class, 'tab-btn') and normalize-space()='Fee']");
    By btnTabTax = By.xpath("//div[contains(@class, 'tab-btn') and normalize-space()='Tax']");
}
