@tagRefund
Feature: Refund payment

  Scenario: Customers want a refund for a token
    Given a registered customer with the CPR "270271-1234" has the name is "Hans" "Hansen" and a bank account with balance 500
    And a registered merchant with the CVR "DK11111111" has the name "Merchant" "Merchantsen A/S" and a bank account with balance 200
    And the customer has a used token with ID "234" and an amount of 100
    When the merchant submits a request for the refund
    Then the submission succeeds
    And after the transaction, the merchant's account has balance 200
    And the customer's account has balance 600




