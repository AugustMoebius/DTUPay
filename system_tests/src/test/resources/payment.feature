@tagPayment
Feature: Customer gets token and Merchant scans and request payment


  Scenario: Succeeding payment
    Given a registered customer with the CPR number "111111-1111"
    And the customer's name is "Hans" "Hansen"
    And the customer has a bank account with balance 100
    And the customer has a token with ID "123"
    And a registered merchant with the CVR "DK11111111" has the name "Merchant" "Merchantsen A/S" and a bank account with balance 200
    When the merchant scans the token with a request for a payment of 100 kroner
    Then the payment request succeeds
    And after the transaction, the merchant's account has balance 300
    And the customer's account has balance 0