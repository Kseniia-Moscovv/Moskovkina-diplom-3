package praktikum.stellar.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private final WebDriver driver;

    // Кнопка Выход в Личном кабинете пользователя
    private final By logoutButton = By.className("Account_button__14Yp3");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on exit-button")
    public void clickOnExitButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Wait for profile page to load")
    public void waitForProfilePageToLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(logoutButton));
    }
}
