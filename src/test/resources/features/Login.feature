Feature: Inicio de sesión en Casino Atlantic City

  @smoke @critical @regression
  Scenario Outline: Inicio de sesión exitoso con credenciales válidas
    Given que el usuario ingresa a la página de Casino Atlantic City
    When hace clic en el botón "Iniciar sesión"
    And ingresa el usuario "<usuario>"
    And ingresa la contraseña "<password>"
    And hace clic en el botón para iniciar sesión
    Then el usuario debe iniciar sesión correctamente

    Examples:
      | usuario        | password       |
      | Testcalimaco34 | Testcalimaco34 |