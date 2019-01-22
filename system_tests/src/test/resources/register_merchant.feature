Feature: Register a merchant in DTUPay

  Scenario: Register a merchant successfully in DTUPay
    Given a merchant with a CVR number and the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the registration submission succeeds and the merchant is registered

  Scenario: Register a merchant with wrong CVR
    Given a merchant with the invalid CVR number "DK11342134214313413134314" and the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message "Illegal registration: Invalid CVR number. Received DK11342134214313413134314."

  Scenario: Register a merchant with wrong first name
    Given a merchant with a CVR number and the name "La9rs" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message "Illegal registration: Invalid name. Received La9rs."

  Scenario: Register a merchant with wrong first name
    Given a merchant with a CVR number and the name "Lars" "La3rsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    Then the merchant submission fails and he gets an error message "Illegal registration: Invalid name. Received La3rsen."

  Scenario: Register a merchant which already exists in the system
    Given a merchant with a CVR number and the name "Lars" "Larsen"
    And the merchant has an account in the DTU bank with a balance at 200
    When the merchant submits a request to register
    And the merchant submits a request to register
    Then the merchant submission fails and he gets an error message where _cvr_ is merchant CVR: "Illegal registration: Merchant already exists. Received _cvr_."
