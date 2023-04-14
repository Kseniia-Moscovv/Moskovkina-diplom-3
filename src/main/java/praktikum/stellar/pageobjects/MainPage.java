package praktikum.stellar.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    // Кнопка "Войти в аккаунт" на странице
    private final By profileButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg'][text()='Войти в аккаунт']");

    // Кнопка Оформить заказ на странице (появляется после корректного логина пользователя в системе)
    private final By createOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg'][text()='Оформить заказ']");

    // Разделы ингредиентов для оформления заказа
    private final By ingredientSections = By.className("tab_tab__1SPyG");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForProfileButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(profileButton));
    }

    @Step("Wait for create-order-button to load")
    public void waitForCreateOrderButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(createOrderButton));
    }

    @Step("Click on profile button")
    public void clickOnProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Click on sauce section")
    public void clickOnSauceSection() { driver.findElements(ingredientSections).get(1).click(); }

    @Step("Click on topping section")
    public void clickOnToppingSection() { driver.findElements(ingredientSections).get(2).click(); }

    @Step("Click on buns section")
    public void clickOnBunsSection() { driver.findElements(ingredientSections).get(0).click(); }
}

