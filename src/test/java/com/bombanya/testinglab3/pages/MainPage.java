package com.bombanya.testinglab3.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

// page_url = https://www.booking.com/
public class MainPage {

    public static final int DEFAULT_PERIOD = 15;

    private final WebDriver driver;
    @FindBy(xpath = "//*[@id='ss']")
    private WebElement cityInput;

    @FindBy(xpath = "//*[@id='ss']/../../ul[1]/*[@role='option'][1]")
    private WebElement firstCityInList;

    @FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    private WebElement cookies;

    @FindBy(xpath = "//*[@class='xp__dates-inner'][1]/div[2]")
    private WebElement datesInput;

    @FindBy(xpath = "//*[@class='xp__guests__count']")
    private WebElement guestsInput;

    @FindBy(xpath = "//*[@aria-describedby='group_children_desc'][2]")
    private WebElement kidsIncrementer;

    @FindBy(xpath = "//*[@name='age'][last()]")
    private WebElement ageSelector;

    @FindBy(xpath = "//*[@data-sb-id='main'][@type='submit']")
    private WebElement submitter;

    @FindBy(xpath = "//nav[@class='bui-header__bar']/div[2]/div[last()]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id='profile-menu-trigger--title']")
    private WebElement profileName;

    @FindBy(xpath = "//nav[@class='bui-header__bar']/div[2]/div[1]")
    private WebElement currencyChooser;
    @SneakyThrows
    public static SearchResultsPage runDefaultSearch(WebDriver driver) {
        driver.get("https://www.booking.com/");
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        Thread.sleep(2000);
        mainPage.selectCurrency("RUB");
        mainPage.chooseCity("Пекин");
        mainPage.chooseDates("15 Июнь 2022", "30 Июнь 2022");
        mainPage.addKid(14);
        return mainPage.submit();
    }

    public void acceptCookies() {
        cookies.click();
    }

    public void chooseCity(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
        cityInput.click();
        firstCityInList.click();
    }

    public void chooseDates(String checkIn, String checkOut) {
        datesInput.click();
        datesInput.click();
        driver.findElement(By.xpath("//*[@aria-label='" + checkIn + "']")).click();
        driver.findElement(By.xpath("//*[@aria-label='" + checkOut + "']")).click();
        datesInput.click();
    }

    public void addKid(int age) {
        guestsInput.click();
        kidsIncrementer.click();
        new Select(ageSelector).selectByValue(String.valueOf(age));
        guestsInput.click();
        guestsInput.click();
    }

    public SearchResultsPage submit() {
        submitter.click();
        return new SearchResultsPage(driver);
    }

    public SignInPage signIn() {
        signInButton.click();
        return new SignInPage(driver);
    }

    public String getProfileName() {
        return profileName.getText();
    }

    @SneakyThrows
    public void selectCurrency(String currency){
        currencyChooser.click();
        Thread.sleep(2000);
        driver.findElement(By.
                xpath("//div[contains(text(), '" + currency + "')]"))
                .click();
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}