package com.fnb.drivermanager;

import com.fnb.utils.helpers.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManagerBrowser {
    //init driver
    @Override
    public WebDriver getDriver(){
//        System.out.println("Launching chrome browser...");
        Log.info("Launching chrome browser...");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
