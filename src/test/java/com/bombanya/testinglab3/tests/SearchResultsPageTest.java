package com.bombanya.testinglab3.tests;

import com.bombanya.testinglab3.Utils;
import com.bombanya.testinglab3.pages.MainPage;
import com.bombanya.testinglab3.pages.ResidencePage;
import com.bombanya.testinglab3.pages.SearchResultsFilters;
import com.bombanya.testinglab3.pages.SearchResultsFilters.Amenity;
import com.bombanya.testinglab3.pages.SearchResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchResultsPageTest {

    String browser = Utils.getProperty("browser");
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = Utils.createDriver(Utils.Browser.valueOf(browser));
    }

    @AfterEach
    void tearDown() {
        //driver.quit();
    }

    @Test
    void budgetTest() throws InterruptedException {
        SearchResultsPage searchResultsPage = MainPage.runDefaultSearch(driver);
        SearchResultsFilters filters = searchResultsPage.getFilters();
        Thread.sleep(3000);
        filters.setMinimalPriceForNight("4 000");
        Thread.sleep(5000);
        searchResultsPage.sortByPriceAscending();
        Thread.sleep(3000);
        List<Integer> prices = searchResultsPage.getPrices();
        assertEquals(prices.get(0), Collections.min(prices));
        assertTrue(prices.get(0) >= MainPage.DEFAULT_PERIOD * 4000);
    }

    @Test
    void starsTest() throws InterruptedException {
        SearchResultsPage searchResultsPage = MainPage.runDefaultSearch(driver);
        SearchResultsFilters filters = searchResultsPage.getFilters();
        Thread.sleep(3000);
        filters.setStars(3);
        Thread.sleep(3000);
        List<Integer> stars = searchResultsPage.getStars();
        assertEquals(3, Collections.min(stars));
        assertEquals(3, Collections.max(stars));
    }

    @Test
    void ratingTest() throws InterruptedException {
        SearchResultsPage searchResultsPage = MainPage.runDefaultSearch(driver);
        SearchResultsFilters filters = searchResultsPage.getFilters();
        Thread.sleep(3000);
        filters.setReviewRating(80);
        Thread.sleep(3000);
        List<Double> ratings = searchResultsPage.getReviewRatings();
        assertEquals(ratings.size(), ratings.stream()
                .filter(rating -> rating >= 8.0)
                .toList()
                .size());
    }

    @Test
    void residenceSelectingTest() {
        SearchResultsPage searchResultsPage = MainPage.runDefaultSearch(driver);
        String referenceTitle = searchResultsPage.getTitles().get(0);
        assertTrue(searchResultsPage.selectResidence(0).getTitle().contains(referenceTitle));
    }

    @Test
    void amenitiesTest() throws InterruptedException {
        SearchResultsPage searchResultsPage = MainPage.runDefaultSearch(driver);
        SearchResultsFilters filters = searchResultsPage.getFilters();
        Thread.sleep(3000);
        filters.includeBreakfast();
        Thread.sleep(3000);
        filters.expandAmenitiesList();
        filters.includeAmenity(Amenity.BATH);
        Thread.sleep(3000);
        filters.includeAmenity(Amenity.CONDITIONER);
        Thread.sleep(3000);
        ResidencePage residence = searchResultsPage.selectResidence(0);
        assertTrue(residence.getRoomAmenities(0).contains(Amenity.BATH.getDescription()));
        assertTrue(residence.getRoomAmenities(0).contains(Amenity.CONDITIONER.getDescription()));
        assertTrue(residence.getRoomOptions(0).contains("завтрак"));
    }
}