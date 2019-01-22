Feature: Register a merchant in DTUPay

  Scenario: Register a merchant successfully in DTUPay
    Given a merchant with the CVR number "DK11111112", with the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the registration submission succeeds and the merchant is registered

  Scenario: Register a merchant with wrong CVR
    Given a merchant with the CVR number "DK111111182", with the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message "Illegal registration: Invalid CVR number. Received DK111111182."

  Scenario: Register a merchant with wrong first name
    Given a merchant with the CVR number "DK11111112", with the name "La9rs" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message "Illegal registration: Invalid name. Received La9rs."

  Scenario: Register a merchant with wrong first name
    Given a merchant with the CVR number "DK11111112", with the name "Lars" "La3rsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message "Illegal registration: Invalid name. Received La3rsen."

  Scenario: Register a merchant which already exists in the system
    Given a merchant with the CVR number "DK11111112", with the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    And the merchant submits a request to register
    Then the merchant submission fails and he gets an error message "Illegal registration: Merchant already exists. Received DK11111112."
