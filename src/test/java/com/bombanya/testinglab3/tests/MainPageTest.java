package com.bombanya.testinglab3.tests;

import com.bombanya.testinglab3.Utils;
import com.bombanya.testinglab3.pages.MainPage;
import com.bombanya.testinglab3.pages.SearchResultsParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainPageTest {
    
    String browser = Utils.getProperty("browser");
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = Utils.createDriver(Utils.Browser.valueOf(browser));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void residenceSearchingTest() {
        driver.get("https://www.booking.com/");
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.selectCurrency("RUB");
        mainPage.chooseCity("Пекин");
        mainPage.chooseDates("15 Июнь 2022", "30 Июнь 2022");
        mainPage.addKid(14);
        SearchResultsParameters searchResultsParameters = mainPage.submit().getSearchParameters();
        assertEquals("Пекин",searchResultsParameters.getCityName());
        assertTrue(searchResultsParameters.getCheckInDate().contains("15 июня 2022"));
        assertTrue(searchResultsParameters.getCheckOutDate().contains("30 июня 2022"));
        assertTrue(searchResultsParameters.getOccupancyData().contains("1 ребенок"));
    }
}