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
import praktikum.stellar.pageobjects.*;
import praktikum.stellar.utils.UserGenerator;

public class LoginTest {
    private WebDriver driver;
    private UserClient userClient = new UserClient();
    private UserCreate newUser = UserGenerator.getRandom();

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        //System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver");

        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");

        userClient.create(newUser);
    }


        @After
    public void tearDown() {
        String accessToken = userClient.login(new UserLogin(newUser.getEmail(), newUser.getPassword())).extract().path("accessToken");

        if (accessToken != null) {
            userClient.delete(accessToken);
        }

        driver.quit();

    }

    @Test
    @DisplayName("Positive check to login user")
    @Description("Check that user can login on button 'Войти в аккаунт'")
    public void checkLoginOnProfileButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickOnProfileButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.inputUserEmail(newUser.getEmail());
        loginPage.inputUserPassword(newUser.getPassword());
        loginPage.clickLoginButtonUnderLoginForm();
        mainPage.waitForCreateOrderButton();
    }

    @Test
    @DisplayName("Positive check to login user")
    @Description("Check that user can login on button 'Личный кабинет' in header")
    public void checkLoginOnHeaderProfileButton() {
        Header header = new Header(driver);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        header.clickOnHeaderProfileButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.inputUserEmail(newUser.getEmail());
        loginPage.inputUserPassword(newUser.getPassword());
        loginPage.clickLoginButtonUnderLoginForm();
        mainPage.waitForCreateOrderButton();
    }

    @Test
    @DisplayName("Positive check to login user")
    @Description("Check that user can login on button 'Войти' on register page")
    public void checkLoginOnRegisterPage() {
        Header header = new Header(driver);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        header.clickOnHeaderProfileButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.clickOnRegisterButton();
        registerPage.waitForRegisterPageToLoad();
        registerPage.clickOnRegLoginButton();
        loginPage.inputUserEmail(newUser.getEmail());
        loginPage.inputUserPassword(newUser.getPassword());
        loginPage.clickLoginButtonUnderLoginForm();
        mainPage.waitForCreateOrderButton();
    }

    @Test
    @DisplayName("Positive check to login user")
    @Description("Check that user can login on button 'Войти' on forgot_password page")
    public void checkLoginOnForgotPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);


        mainPage.clickOnProfileButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.clickOnResetPasswordButton();
        forgotPasswordPage.waitForgotPasswordPageToLoad();
        forgotPasswordPage.clickOnPassLoginButton();
        loginPage.inputUserEmail(newUser.getEmail());
        loginPage.inputUserPassword(newUser.getPassword());
        loginPage.clickLoginButtonUnderLoginForm();
        mainPage.waitForCreateOrderButton();
    }
}
