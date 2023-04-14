package praktikum.stellar;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.stellar.model.UserLogin;
import praktikum.stellar.pageobjects.MainPage;

import java.io.File;

public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

//        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver");

        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Positive check to choose ingredient section")
    @Description("Check that user can switch between ingredient sections")

    public void checkToSwitchIngredientSections() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickOnSauceSection();
        mainPage.clickOnToppingSection();
        mainPage.clickOnBunsSection();
    }

}
