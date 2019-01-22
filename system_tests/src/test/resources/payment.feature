@tagPayment
Feature: Customer gets token and Merchant scans and request payment

    Scenario: Succeeding payment
      Given a registered customer with the CPR "270271-1234" has the name is "Hans" "Hansen" and a bank account with balance 500
      And the customer has a token
      And a registered merchant with the CVR "DK11111112" has the name "Merchant" "Merchantsen" and a bank account with balance 200
      And that the merchant wishes to register a payment of amount 10
      When the merchant submits a request for the payment
      Then the submission succeeds
      And after the transaction, the merchant's account has balance 210
      And the customer's account has balance 490

    Scenario: Failing payment because of already used token
      Given a registered customer with the CPR "270271-1234" has the name is "Hans" "Hansen" and a bank account with balance 500
      And a registered merchant with the CVR "DK11111112" has the name "Merchant" "Merchantsen" and a bank account with balance 200
      And the customer has a used token
      And that the merchant wishes to register a payment of amount 20
      When the merchant submits a request for the payment
      And after the transaction, the merchant's account has balance 200
      And the customer's account has balance 500

    Scenario: Failing payment because of unregistered merchant
      Given a registered customer with the CPR "270271-1234" has the name is "Hans" "Hansen" and a bank account with balance 500
      And a unregistered merchant with the CVR "DK99999999" has the name "Merchant" "Merchantsen" and a bank account with balance 200
      And the customer has a token
      And that the merchant wishes to register a payment of amount 30
      When the merchant submits a request for the payment
      Then the submission succeeds
      And after the transaction, the merchant's account has balance 200
      And the customer's account has balance 500