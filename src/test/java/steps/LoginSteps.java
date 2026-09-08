package steps;

import org.junit.Assert;

import hooks.Hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {

    private HomePage homePage;
    private LoginPage loginPage;

    @Given("que el usuario ingresa a la página de Casino Atlantic City")
    public void ingresarPaginaCasino() {

        homePage = new HomePage(Hooks.driver);
        loginPage = new LoginPage(Hooks.driver);

        homePage.open();
    }

    @When("hace clic en el botón {string}")
    public void clickIniciarSesion(String button) {

        homePage.clickLoginButton();
    }

    @When("ingresa el usuario {string}")
    public void ingresarUsuario(String username) {

        loginPage.enterUsername(username);
    }

    @When("ingresa la contraseña {string}")
    public void ingresarPassword(String password) {

        loginPage.enterPassword(password);
    }

    @When("hace clic en el botón para iniciar sesión")
    public void enviarLogin() {

        loginPage.clickLogin();
    }

    @Then("el usuario debe iniciar sesión correctamente")
    public void validarLoginExitoso() {

        Assert.assertTrue(
                "No se visualizó el botón DEPOSITAR después del login",
                homePage.isDepositButtonVisible()
        );
    }
}