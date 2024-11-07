package com.fnb.drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirefoxDriverManager extends DriverManagerBrowser{
    @Override
    public WebDriver getDriver(){
        System.out.println("Launching firefox browser...");
//        Log.info("Launching firefox browser...");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
