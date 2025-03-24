package org.desafio.logic;

import com.itextpdf.layout.Document;
import lombok.extern.log4j.Log4j2;
import org.desafio.utils.Utilities;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

@Log4j2
public class LoginLogic {
    private String step = "";
    private WebDriver driver;
    private Utilities utilities;

    public LoginLogic() {
        utilities = new Utilities();
        driver = new EdgeDriver();
        Utilities.addDriver(driver);
    }

    // Web elements
    private By emailInput = By.name("email");
    private By passwordInput = By.name("senha");
    private By loginButton = By.name("loginbtn");
    public void navigateTo(String url) throws InterruptedException {
        String step = "Navigate to URL "+ url;
        log.info(step);
        driver.get(url);
        driver.manage().window().maximize();
    }
    public void validateTitle(String expectedTitle, Document documentEvidence) throws InterruptedException {
        String step = "Validate title";
        log.info(step);
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            log.info("Title is correct");
            utilities.takeScreenshot(driver, step + ".png", documentEvidence);
        } else {
            log.error("Title is incorrect");
            utilities.takeScreenshot(driver, step + ".png", documentEvidence);
            Assert.fail("Title is incorrect");
        }
    }

    public void enterEmail(String text, Document documentEvidence) throws InterruptedException {
        String step = "Fill input email";
        log.info(step);
        WebElement emailElement = driver.findElement(emailInput);
        emailElement.clear();
        emailElement.sendKeys(text);
        emailElement.isDisplayed();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }

    public void enterPassword(String text, Document documentEvidence) throws InterruptedException {
        String step = "Fill input password";
        log.info(step);
        WebElement passwordElement = driver.findElement(passwordInput);
        passwordElement.clear();
        passwordElement.sendKeys(text);
        passwordElement.isDisplayed();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }

    public void clickLoginButton(Document documentEvidence) throws InterruptedException {
        String step = "Click login button";
        log.info(step);
        WebElement loginBtn = driver.findElement(loginButton);
        loginBtn.click();
        utilities.takeScreenshot(driver, step + ".png", documentEvidence);
    }

    public void validateText(String expectedText, Document documentEvidence) throws InterruptedException {
        String step = "Validate text";
        log.info(step);
        boolean isTextPresent = driver.getPageSource().contains(expectedText);
        if (isTextPresent) {
            log.info("Text is present");
            utilities.takeScreenshot(driver, step + ".png", documentEvidence);
        } else {
            log.error("Text is not present");
            utilities.takeScreenshot(driver, step + ".png", documentEvidence);
            Assert.fail("Text is not present");
        }
    }
}
