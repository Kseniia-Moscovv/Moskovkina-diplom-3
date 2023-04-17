package praktikum.stellar;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.stellar.client.UserClient;
import praktikum.stellar.model.UserCreate;
import praktikum.stellar.model.UserLogin;
import praktikum.stellar.pageobjects.Header;
import praktikum.stellar.pageobjects.LoginPage;
import praktikum.stellar.pageobjects.RegisterPage;
import praktikum.stellar.utils.Constants;
import praktikum.stellar.utils.UserGenerator;

public class RegistrationTest {
    private WebDriver driver;
    private UserClient userClient = new UserClient();
    private UserCreate newUser = UserGenerator.getRandom();
    private String wrongPassword = "1234";

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

//      System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver");

        driver = new ChromeDriver(options);
        driver.get(Constants.WEB_URL);

        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);


        header.clickOnHeaderProfileButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.clickOnRegisterButton();
        registerPage.waitForRegisterPageToLoad();
    }

    @After
    public void tearDown() {
        String accessToken = userClient.login(new UserLogin(newUser.getEmail(), newUser.getPassword())).extract().path("accessToken");

        if (accessToken == null) {
            accessToken = userClient.login(new UserLogin(newUser.getEmail(), wrongPassword)).extract().path("accessToken");
        }

        if (accessToken != null) {
            userClient.delete(accessToken);
        }

        driver.quit();
    }

    @Test
    @DisplayName("Positive check to register user")
    @Description("Check that user can register with valid parameters")
    public void checkToRegistrationFormWithValidParameters() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registerPage.fillInRegistrationForm(newUser.getName(), newUser.getEmail(), newUser.getPassword());
        loginPage.waitForLoginPageToLoad();
    }

    @Test
    @DisplayName("Negative check to register user")
    @Description("Check that user can't be registered with incorrect password")
    public void checkToRegistrationFormWithIncorrectPassword() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.fillInRegistrationForm(newUser.getName(), newUser.getEmail(), wrongPassword);
        registerPage.waitErrorMessage();
    }
}
