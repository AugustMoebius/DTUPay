Feature: Register a merchant in DTUPay

  Scenario: Register a merchant successfully in DTUPay
    Given a merchant with the CVR number "DK11111112", with the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the registration submission succeeds and the merchant is registered

    Scenario: Register a merchant with wrong CVR
      Given a merchant with the CVR number "DK11111112", with the name "Lars" "Larsen"
      And the merchant has an account in the DTU bank with a balance at 200
      When the merchant submits a request to register
      Then the merchant submission fails and he gets an error message

  Scenario: Register a merchant with wrong name
    Given a merchant with the CVR number "DK11111112", with the name "La9rs" "La2rsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message

  Scenario: Register a merchant which already exists in the system
    Given a merchant with the CVR number "DK11111112", with the name "La9rs" "La2rsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message
