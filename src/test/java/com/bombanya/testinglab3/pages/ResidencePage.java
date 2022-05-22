package com.bombanya.testinglab3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// page_url = https://www.booking.com/hotel
public class ResidencePage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='hp_hotel_name']")
    private WebElement title;

    @FindBy(xpath = "//*[@class='hprt-facilities-block']")
    private List<WebElement> roomsAmenities;

    @FindBy(xpath = "//*[contains(@class, ' hprt-table-cell hprt-table-cell-conditions ')]")
    private List<WebElement> roomsOptions;

    @FindBy(xpath = "//*[@class='hprt-block']/select")
    private List<WebElement> roomSelectors;

    @FindBy(xpath = "//*[@class='hprt-price-block ']//*[@class='prco-valign-middle-helper']")
    private List<WebElement> roomsPrices;

    @FindBy(xpath = "//*[@data-room-name]")
    private List<WebElement> roomsTitles;

    @FindBy(xpath = "(//*[@class='hprt-reservation-cta']/button)[last()]")
    private WebElement roomsSubmitter;

    @FindBy(xpath = "//*[contains(@class, 'hprt-table-cell -first')]")
    private List<WebElement> roomsTitlesCells;

    public String getTitle() {
        return title.getText();
    }

    public String getRoomAmenities(int room) {
        return roomsAmenities.get(room).getText();
    }

    public String getRoomOptions(int room) {
        return roomsOptions.get(room).getText();
    }

    public void selectRoom(int room, int number) {
        new Select(roomSelectors.get(room)).selectByValue(String.valueOf(number));
    }

    public List<Integer> getRoomsPrices() {
        return roomsPrices.stream()
                .map(WebElement::getText)
                .map(price -> price.substring(0, price.lastIndexOf(" ")))
                .map(price -> price.replace(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<String> getRoomsNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < roomsTitles.size(); i++) {
            int finalI = i;
            names.addAll(Stream
                    .generate(() -> roomsTitles.get(finalI).getText())
                    .limit(Integer.parseInt(roomsTitlesCells.get(finalI).getAttribute("rowspan")))
                    .toList());
        }
        return names;
    }

    public OrderDetailsPage submitRooms() {
        roomsSubmitter.click();
        return new OrderDetailsPage(driver);
    }

    public ResidencePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}