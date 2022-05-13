package com.bombanya.testinglab3.tests;

import com.bombanya.testinglab3.Utils;
import com.bombanya.testinglab3.pages.MainPage;
import com.bombanya.testinglab3.pages.SignInPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class AccountTest {

    String browser = Utils.getProperty("browser");
    WebDriver driver;
    String login = Utils.getProperty("login");
    String password = Utils.getProperty("password");

    @BeforeEach
    void setUp() {
        driver = Utils.createDriver(Utils.Browser.valueOf(browser));
    }

    @AfterEach
    void tearDown() {
        //driver.quit();
    }

    @Test
    void signInTest() throws InterruptedException {
        driver.get("https://www.booking.com/");
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        SignInPage signInPage = mainPage.signIn();
        signInPage.setEmail(login);
        signInPage.submit();
        Thread.sleep(1000);
        signInPage.setPassword(password);
        signInPage.submit();
        System.out.println(mainPage.getProfileName());
    }
}
