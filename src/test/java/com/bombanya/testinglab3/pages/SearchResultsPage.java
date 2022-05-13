package com.bombanya.testinglab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@role='menubar']/li[@data-id='price']")
    private WebElement byPriceSorter;

    @FindBy(xpath = "//*[@data-testid='price-and-discounted-price']/span[last()]")
    private List<WebElement> prices;

    @FindBy(xpath = "//*[@data-testid='rating-circles']")
    private List<WebElement> stars;

    @FindBy(xpath = "//*[@data-testid='review-score']/div[1]")
    private List<WebElement> reviewRatings;

    @FindBy(xpath = "//*[@data-testid='availability-cta']")
    private List<WebElement> residenceSelectors;

    @FindBy(xpath = "//*[@data-testid='title']")
    private List<WebElement> titles;

    public SearchResultsParameters getSearchParameters() {
        return new SearchResultsParameters(driver);
    }

    public SearchResultsFilters getFilters() {
        return new SearchResultsFilters(driver);
    }

    public void sortByPriceAscending() {
        byPriceSorter.click();
    }

    public List<Integer> getPrices() {
        return prices.stream()
                .map(WebElement::getText)
                .map(price -> price.substring(0, price.lastIndexOf(" "))
                        .replace(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getStars() {
        return stars.stream()
                .map(star -> star.findElements(By.xpath(".//span")))
                .map(List::size)
                .collect(Collectors.toList());
    }

    public List<Double> getReviewRatings() {
        return reviewRatings.stream()
                .map(WebElement::getText)
                .map(review -> review.replace(",", "."))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public List<String> getTitles() {
        return titles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public ResidencePage selectResidence(int residenceN) {
        residenceSelectors.get(residenceN).click();
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
        return new ResidencePage(driver);
    }

    public SearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}