package praktikum.stellar.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private final WebDriver driver;

    // Кнопка Войти внизу страницы
    private final By loginButtonOnForgotPasswordPage = By.className("Auth_link__1fOlj");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on login button")
    public void clickOnPassLoginButton() {
        driver.findElement(loginButtonOnForgotPasswordPage).click();
    }

    @Step("Wait for forgot-password page to load")
    public void waitForgotPasswordPageToLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(loginButtonOnForgotPasswordPage));
    }



}
