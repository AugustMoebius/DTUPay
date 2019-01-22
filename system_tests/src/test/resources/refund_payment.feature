@tagPayment
Feature: Refund payment

  Scenario: Customers want a refund for a token
    Given a registered customer whose name is "Hans" "Hansen" and a bank account with balance 500
    And a registered merchant with the CVR "DK12111115" has the name "Merchant" "Merchantsen" and a bank account with balance 200
    And the customer has a used token with a transaction amount of 40
    And the customer's account has balance 460
    And after the transaction, the merchant's account has balance 240
    When the merchant submits a request for the refund
    Then the submission succeeds
    And the customer's account has balance 500
    And after the transaction, the merchant's account has balance 200
