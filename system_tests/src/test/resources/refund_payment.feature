@tagRefund
Feature: Refund payment

  Scenario: Customers want a refund for a token
    Given a registered customer with the CPR "270271-2467" has the name is "Hans" "Hansen" and a bank account with balance 500
    And a registered merchant with the CVR "DK11111111" has the name "Merchant" "Merchantsen A/S" and a bank account with balance 200
<<<<<<< HEAD
    And the customer has a used token with ID "123" and an amount of 100
    When the merchant submits a request for the refund
   #Then the submission succeeds
   # And the customer's account has balance 600
   # And after the transaction, the merchant's account has balance 100
=======
    And the customer has a used token with ID "234" and an amount of 100
    And the customer's account has balance 400
    And after the transaction, the merchant's account has balance 300
    When the merchant submits a request for the refund
    Then the submission succeeds
    And the customer's account has balance 500
    And after the transaction, the merchant's account has balance 200

>>>>>>> 5dafe64f1faa66b750601e7f2616f7c540980a3c




