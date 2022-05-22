package com.bombanya.testinglab3.tests;

import com.bombanya.testinglab3.Utils;
import com.bombanya.testinglab3.pages.MainPage;
import com.bombanya.testinglab3.pages.OrderDetailsPage;
import com.bombanya.testinglab3.pages.ResidencePage;
import com.bombanya.testinglab3.pages.SearchResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

class ResidencePageTest {

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
    void roomsSelectingTest() throws InterruptedException {
        SearchResultsPage searchResultsPage = MainPage.runDefaultSearch(driver);
        ResidencePage residencePage = searchResultsPage.selectResidence(0);
        Thread.sleep(5000);
        residencePage.selectRoom(0, 2);
        Thread.sleep(3000);
        residencePage.selectRoom(2, 1);
        String room1 = residencePage.getRoomsNames().get(0);
        String room2 = residencePage.getRoomsNames().get(2);
        int room1Price = residencePage.getRoomsPrices().get(0);
        int room2Price = residencePage.getRoomsPrices().get(2);
        Thread.sleep(3000);
        OrderDetailsPage orderDetailsPage = residencePage.submitRooms();
        if (room1.equals(room2)) assertTrue(orderDetailsPage.getRooms().get(0).contains("3 x " + room1));
        else {
            assertTrue(orderDetailsPage.getRooms().stream().anyMatch(room -> room.contains("2 x " + room1)));
            assertTrue(orderDetailsPage.getRooms().stream().anyMatch(room -> room.contains("1 x " + room2)));
        }
        assertEquals(room1Price * 2 + room2Price, orderDetailsPage.getPrice());
    }
}