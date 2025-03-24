package org.desafio.steps;

import com.itextpdf.layout.Document;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.desafio.logic.LoginLogic;
import org.desafio.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Log4j2
public class StepDefinitions {

    private LoginLogic loginLogic;
    private Utilities utilities;
    private String scenarioName;
    private Document documentEvidence;

    @Before
    public void setUp(Scenario scenario) throws IOException {
        utilities = new Utilities();
        loginLogic = new LoginLogic();
        String edgeDriverPath = "C:\\edgedriver_win64\\msedgedriver.exe"; // Caminho correto para o executável
        System.setProperty("webdriver.edge.driver", edgeDriverPath);
        scenarioName = scenario.getName();
        documentEvidence = utilities.createDocumentPDF(scenarioName);
    }

    @Given("I open the home page")
    public void i_open_the_home_page() throws InterruptedException {
        loginLogic.navigateTo("http://localhost:3000");
    }
    @Then("I should see the title {string}")
    public void i_should_see_the_title(String expectedTitle) throws InterruptedException {
        loginLogic.validateTitle(expectedTitle, documentEvidence);
    }

    @Then("I should see the text {string}")
    public void i_should_see_the_text(String string) throws InterruptedException {
        loginLogic.validateText(string, documentEvidence);
    }

    @Given("fill e-mail input {string}")
    public void fill_e_mail_input(String email) throws InterruptedException {
        loginLogic.enterEmail(email, documentEvidence);
    }
    @Given("fill password input {string}")
    public void fill_password_input(String password) throws InterruptedException {
        loginLogic.enterPassword(password, documentEvidence);
    }
    @Then("click on btn login")
    public void click_on_btn_login() throws InterruptedException {
        loginLogic.clickLoginButton(documentEvidence);
    }

    @After
    public void tearDown() {
        utilities.generateDocumentPDF(documentEvidence,scenarioName);
        Utilities.quitAllDrivers();
    }
}
