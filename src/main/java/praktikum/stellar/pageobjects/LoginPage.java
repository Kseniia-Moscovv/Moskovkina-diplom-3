package praktikum.stellar.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;

    // Кнопка "Зарегистрироваться" на странице
    private final By registerButton = By.xpath(".//a[@class='Auth_link__1fOlj'][text()='Зарегистрироваться']");

    // Кнопка Восстановить пароль под формой логина
    private final By resetPasswordButton = By.xpath(".//a[@class='Auth_link__1fOlj'][text()='Восстановить пароль']");

    // Полле ввода Email
    private final By emailInputForLogin = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default'][text()='Email']/following-sibling::input");

    // Поле ввода Пароль
    private final By passwordInputForLogin = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default'][text()='Пароль']/following-sibling::input");

    // Кнопка Войти под формой логина
    private final By loginButtonUnderLoginForm = By.className("button_button__33qZ0");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait for login page to load")
    public void waitForLoginPageToLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(registerButton));
    }

    @Step("Type in email in to email input field")
    public void inputUserEmail(String userEmail) {
        driver.findElement(emailInputForLogin).sendKeys(userEmail);
    }

    @Step("Type in password in to password input field")
    public void inputUserPassword(String userPassword) { driver.findElement(passwordInputForLogin).sendKeys(userPassword); }

    @Step("Click on login button under login for")
    public void clickLoginButtonUnderLoginForm() {
        driver.findElement(loginButtonUnderLoginForm).click();
    }

    @Step("Click on register button")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Click on reset-password button")
    public void clickOnResetPasswordButton() { driver.findElement(resetPasswordButton).click(); }

    @Step("Fill in authorization form")
    public void fillInLoginForm(String email, String password) {
        inputUserEmail(email);
        inputUserPassword(password);
        clickLoginButtonUnderLoginForm();
    }
}
