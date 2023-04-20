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
import praktikum.stellar.utils.Constants;

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
        driver.get(Constants.WEB_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Positive check to choose ingredient section")
    @Description("Check that user can get into Sauce section")
    public void checkToSwitchToSauceSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickOnSauceSection();
        mainPage.waitForSauceSectionToGetActiveClass();
    }

    @Test
    @DisplayName("Positive check to choose ingredient section")
    @Description("Check that user can get into Topping section")
    public void checkToSwitchToToppingSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickOnToppingSection();
        mainPage.waitForToppingSectionToGetActiveClass();
    }

    @Test
    @DisplayName("Positive check to choose ingredient section")
    @Description("Check that user can get into Buns section")
    public void checkToSwitchToBunsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickOnSauceSection();
        mainPage.clickOnBunsSection();
        mainPage.waitForBunsSectionToGetActiveClass();
    }

}
