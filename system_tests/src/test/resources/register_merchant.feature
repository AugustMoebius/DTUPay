Feature: Register a merchant in DTUPay

  Scenario: Register a merchant successfully in DTUPay
    Given a merchant with the CVR number "DK11111111", with the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the registration submission succeeds and the merchant is registered