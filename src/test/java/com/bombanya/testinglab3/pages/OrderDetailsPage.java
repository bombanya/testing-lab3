package com.bombanya.testinglab3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

// page_url = about:blank
public class OrderDetailsPage {

    @FindBy(xpath = "//*[contains(@class, 'bp-card--booking-summary')]//li/div")
    private List<WebElement> rooms;

    @FindBy(xpath = "//*[contains(@class, 'bp-price-details__total')]//*[@data-currency-code='RUB']")
    private WebElement price;

    public List<String> getRooms() {
        return rooms.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getPrice() {
        return Integer.parseInt(price.getText()
                .substring(0, price.getText().lastIndexOf(" руб"))
                .replace(" ", ""));
    }
    public OrderDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}