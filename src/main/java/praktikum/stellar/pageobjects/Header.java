package praktikum.stellar.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private final WebDriver driver;

    // Кнопка "Личный кабинет" в хедере страницы
    private final By headerProfileButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2'][text()='Личный Кабинет']");

    // Кнопка "Конструктор" в хедере страницы
    private final By headerConstructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2'][text()='Конструктор']");

    // Кнопка-логотип STELLAR_BURGER в хедере страницы
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on profile button in header")
    public void clickOnHeaderProfileButton() {
        driver.findElement(headerProfileButton).click();
    }

    @Step("Click on constructor button in header")
    public void clickOnHeaderConstructorButton() {
        driver.findElement(headerConstructorButton).click();
    }

    @Step("Click on logo-button in header")
    public void clickOnHeaderLogoButton() {
        driver.findElement(logoButton).click();
    }
}
