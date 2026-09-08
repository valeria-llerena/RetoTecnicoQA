package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By loginButton = By.id("login");

    /*
     * Botón visible después de iniciar sesión:
     *
     * <button class="... clmc-btn-primary ... aliasLink ...">
     *     ... DEPOSITAR
     * </button>
     */
    private By depositButton =
            By.xpath(
                "//button[" +
                "contains(@class,'aliasLink') " +
                "and contains(normalize-space(.),'DEPOSITAR')" +
                "]"
            );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://www.casinoatlanticcity.com/");
    }

    private void removeInitialOverlay() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("""
            const dimmer =
                document.getElementById('optiRealPopupDimmer');

            if (dimmer) {
                dimmer.style.setProperty(
                    'display',
                    'none',
                    'important'
                );
            }

            const popupImage =
                document.querySelector('img.bee-popup-center');

            if (popupImage) {
                popupImage.style.setProperty(
                    'display',
                    'none',
                    'important'
                );

                if (popupImage.parentElement) {
                    popupImage.parentElement.style.setProperty(
                        'display',
                        'none',
                        'important'
                    );
                }
            }
        """);
    }

    public void clickLoginButton() {

        removeInitialOverlay();

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        );

        try {

            button.click();

        } catch (ElementClickInterceptedException e) {

            removeInitialOverlay();

            button = wait.until(
                    ExpectedConditions.elementToBeClickable(loginButton)
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", button);
        }
    }

    public boolean isDepositButtonVisible() {

        WebElement deposit = wait.until(
                ExpectedConditions.visibilityOfElementLocated(depositButton)
        );

        return deposit.isDisplayed();
    }
}