package com.bombanya.testinglab3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = https://www.booking.com/searchresults
public class SearchResultsParameters {

    @FindBy(xpath = "//*[@name='ss']")
    private WebElement city;

    @FindBy(xpath = "//*[@data-testid='date-display-field-start']")
    private WebElement checkIn;

    @FindBy(xpath =  "//*[@data-testid='date-display-field-end']")
    private WebElement checkOut;

    @FindBy(xpath =  "//*[@data-testid='occupancy-config']")
    private WebElement occupancy;

    public String getCityName() {
        return city.getAttribute("value");
    }

    public String getCheckInDate() {
        return checkIn.getText();
    }

    public String getCheckOutDate() {
        return checkOut.getText();
    }

    public String getOccupancyData() {
        return occupancy.getText();
    }

    public SearchResultsParameters(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}