Feature: Customer gets token and Merchant scans and request payment

  Scenario: Succeeding payment
    Given a registered customer with the CPR number "111111-1111" with a bank account
    And the customer has at least 1 unused token
    And a registered merchant with the CVR "DK11111111" with a bank account
    When the customer gets a token
    And the merchant scans the token with a request for a payment of 100 kroner
    Then the payment succeeds
    And the money is transferred from the customer's bank account to the merchant's bank account


