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
import praktikum.stellar.pageobjects.MainPage;
import praktikum.stellar.pageobjects.ProfilePage;
import praktikum.stellar.utils.UserGenerator;

public class GoToConstructorTest {
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

        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        header.clickOnHeaderProfileButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.fillInLoginForm(newUser.getEmail(), newUser.getPassword());
        header.clickOnHeaderProfileButton();
        profilePage.waitForProfilePageToLoad();

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
    @DisplayName("Positive check to go to main page to constructor")
    @Description("Check to go to main page to constructor from profile with button 'Конструктор' in header")
    public void checkGoToUserProfile() {
        Header header = new Header(driver);
        MainPage mainPage = new MainPage(driver);

        header.clickOnHeaderConstructorButton();
        mainPage.waitForCreateOrderButton();
    }

    @Test
    @DisplayName("Positive check to go to main page to constructor")
    @Description("Check to go to main page to constructor from profile with logo button in header")
    public void checkToGoToConstructor() {
        Header header = new Header(driver);
        MainPage mainPage = new MainPage(driver);

        header.clickOnHeaderLogoButton();
        mainPage.waitForCreateOrderButton();
    }
}
