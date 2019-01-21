@tagPayment
Feature: Customer gets token and Merchant scans and request payment

  Scenario: Succeeding payment with token validation
    Given a registered customer with the CPR "270271-1234" has the name is "Hans" "Hansen" and a bank account with balance 500
    And the customer has a token with ID "234"
#    And the token is unused
    And a registered merchant with the CVR "DK11111111" has the name "Merchant" "Merchantsen A/S" and a bank account with balance 200
    And that the merchant wishes to register a payment of amount 100
    When the merchant submits a request for the payment
#    Then the submission succeeds
    And after the transaction, the merchant's account has balance 300
    And the customer's account has balance 400
    And the token has been used
