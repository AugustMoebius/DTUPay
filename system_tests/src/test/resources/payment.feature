@tagPayment
Feature: Customer gets token and Merchant scans and request payment

  Scenario: Succeeding payment
    Given a registered customer with the CPR "111111-1111" has the name is "Hans" "Hansen" and a bank account with balance 100
    And the customer has a token with ID "123"
    And a registered merchant with the CVR "DK11111111" has the name "Merchant" "Merchantsen A/S" and a bank account with balance 200
    And that the merchant wishes to register a payment of amount 100
    When the merchant submits a request for the payment
    Then the payment request succeeds
    And after the transaction, the merchant's account has balance 300
    And the customer's account has balance 0

  Scenario: Succeeding request for payment
    Given that the merchant has a barcode containing token ID "123"
    And that the merchant ID is "DK11111111"
    And that the merchant wishes to register a payment of amount 100
    When the merchant submits a request for the payment
    Then the payment request succeeds