package com.bombanya.testinglab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.booking.com/searchresults

public class SearchResultsFilters {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@for='filter-style-switch-pri']/span")
    private WebElement budgetToggle;

    @FindBy(xpath = "//*[@data-filters-item='mealplan:mealplan=1']/label/span")
    private WebElement breakfastIncluder;

    @FindBy(xpath = "//*[@data-filters-group='roomfacility']/button")
    private WebElement amenitiesListExpander;

    public void setMinimalPriceForNight(String price) {
        //budgetToggle.click();
        driver.findElement(By.
                        xpath("//*[@data-testid='filters-group-label-content'][starts-with(text(), " +
                                "'" + price + "')]/../../../../span"))
                .click();
    }

    public void setStars(int stars) {
        driver.findElement(By
                        .xpath("//*[@data-filters-item='class:class=" + stars + "']/label/span"))
                .click();
    }

    public void setReviewRating(int rating) {
        driver.findElement(By
                        .xpath("//*[@data-filters-item='review_score:review_score=" + rating + "']/label/span"))
                .click();
    }

    public void includeBreakfast() {
        breakfastIncluder.click();
    }

    public void includeAmenity(Amenity amenity) {
        driver.findElement(By.
                xpath("//*[@data-filters-item='roomfacility:roomfacility=" + amenity.getId()
                        + "']/label/span"))
                .click();
    }

    public void expandAmenitiesList() {
        amenitiesListExpander.click();
    }

    public SearchResultsFilters(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public enum Amenity {
        KITCHEN(999, "Кухня"), CONDITIONER(11, "Кондиционер"),
        BATH(5, "ванна");

        Amenity(int id, String description) {
            this.id = id;
            this.description = description;
        }

        private final int id;
        private final String description;

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }
}