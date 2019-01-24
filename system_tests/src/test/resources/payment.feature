@tagPayment
Feature: Customer gets token and Merchant scans and request payment
  Scenario: Succeeding payment
    Given a registered customer whose name is "Hans" "Hansen" and a bank account with balance 500
    And the customer has a token
    And a registered merchant has a CVR, the name "Merchant" "Merchantsen", and a bank account with balance 200
    And that the merchant wishes to register a payment of amount 10
    When the merchant submits a request for the payment
    Then the submission succeeds
    And after the transaction, the merchant's account has balance 215
    And the customer's account has balance 490

  Scenario: Failing payment because of already used token
    Given a registered customer whose name is "Hans" "Hansen" and a bank account with balance 500
    And a registered merchant has a CVR, the name "Merchant" "Merchantsen", and a bank account with balance 200
    And the customer has a used token
    And that the merchant wishes to register a payment of amount 20
    When the merchant submits a request for the payment
    Then the submission succeeds
    And the customer's account has balance 500
    And after the transaction, the merchant's account has balance 200

  Scenario: Failing payment because of unknown token
    Given a registered customer whose name is "Hans" "Hansen" and a bank account with balance 500
    And a registered merchant has a CVR, the name "Merchant" "Merchantsen", and a bank account with balance 200
    And the customer has an unknown token
    And that the merchant wishes to register a payment of amount 100
    When the merchant submits a request for the payment
    Then the submission succeeds
    And the customer's account has balance 500
    And after the transaction, the merchant's account has balance 200

  Scenario: Failing payment because of negative payment amount
    Given a registered customer whose name is "Hans" "Hansen" and a bank account with balance 500
    And a registered merchant has a CVR, the name "Merchant" "Merchantsen", and a bank account with balance 200
    And the customer has an unknown token
    And that the merchant wishes to register a payment of negative amount -100
    When the merchant submits a request for the payment
    Then the merchant receives a failure response
    And the customer's account has balance 500
    And after the transaction, the merchant's account has balance 200
    And the customer's account has balance 500

  Scenario: Failing payment because of unregistered merchant
    Given a registered customer whose name is "Hans" "Hansen" and a bank account with balance 500
    And a unregistered merchant has a CVR, the name "Merchant" "Merchantsen", and a bank account with balance 200
    And the customer has a token
    And that the merchant wishes to register a payment of amount 30
    When the merchant submits a request for the payment
    Then the submission succeeds
    And after the transaction, the merchant's account has balance 200
    And the customer's account has balance 500
