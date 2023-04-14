package praktikum.stellar.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private final WebDriver driver;

    // Поле ввода "Имя" в форме регистрации
    private final By nameInput = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default'][text()='Имя']/following-sibling::input");

    // Поле ввода "Email" в форме регистрации
    private final By emailInput = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default'][text()='Email']/following-sibling::input");

    // Поле ввода "Пароль" в форме регистрации
    private final By passwordInput = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default'][text()='Пароль']/following-sibling::input");

    // Кнопка Зарегистрироваться под формой регистрации
    private final By registerButtonUnderForm = By.className("button_button__33qZ0");

    // Кнопка Войти внизу страницы
    private final By loginButtonOnRegisterPage = By.className("Auth_link__1fOlj");

    // Сообщение об ошибке при вводе некорректного пароля
    private final By passwordErrorMessage = By.className("input__error");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait for register page to load")
    public void waitForRegisterPageToLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(nameInput));
    }

    @Step("Wait for error message for incorrect password")
    public void waitErrorMessage() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(passwordErrorMessage));
    }

    @Step("Type in user name in to mane input field")
    public void inputName(String userName) {
        driver.findElement(nameInput).sendKeys(userName);
    }

    @Step("Type in email in to email input field")
    public void inputEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Type in password in to password input field")
    public void inputPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Click on registration button")
    public void clickOnRegLoginButton() { driver.findElement(loginButtonOnRegisterPage).click(); }

    @Step("Click on registration button under registration form")
    public void clickOnRegisterButtonUnderForm() {
        driver.findElement(registerButtonUnderForm).click();
    }

    @Step("Fill in login form")
    public void fillInRegistrationForm(String userName, String email, String password) {
        inputName(userName);
        inputEmail(email);
        inputPassword(password);
        clickOnRegisterButtonUnderForm();
    }
}
