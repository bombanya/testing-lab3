package com.bombanya.testinglab3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = https://booking.com/sign-in

public class SignInPage {

    @FindBy(xpath = "//*[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement submitter;

    public void setEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void submit() {
        submitter.click();
    }
    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}