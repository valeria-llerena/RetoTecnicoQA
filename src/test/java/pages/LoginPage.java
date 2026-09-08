package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput =
            By.xpath("//label[contains(.,'Usuario o correo electrónico')]/following::input[1]");

    private By passwordInput =
            By.cssSelector("input[name='password']");

    private By loginButton =
            By.xpath("//button[normalize-space()='INICIAR SESIÓN' and not(@id='login')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeMarketingPopup() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("""
            const dimmer = document.getElementById('optiRealPopupDimmer');

            if (dimmer) {
                dimmer.style.setProperty('display', 'none', 'important');
            }

            const popupImage = document.querySelector('img.bee-popup-center');

            if (popupImage) {

                popupImage.style.setProperty(
                    'display',
                    'none',
                    'important'
                );

                let parent = popupImage.parentElement;

                if (parent) {
                    parent.style.setProperty(
                        'display',
                        'none',
                        'important'
                    );
                }
            }
        """);
    }

    public void enterUsername(String username) {

        closeMarketingPopup();

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameInput)
        );

        input.clear();
        input.sendKeys(username);
    }

    public void enterPassword(String password) {

        closeMarketingPopup();

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        );

        input.clear();
        input.sendKeys(password);
    }

    public void clickLogin() {

        // Importantísimo porque el popup puede aparecer
        // después de llenar los campos.
        closeMarketingPopup();

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        );

        button.click();
    }
}