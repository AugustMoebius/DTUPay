Feature: Register a customer in DTUPay

  Scenario: Register a correct customer in DTUPay
    Given a name "Peter" "Hansen" and CPR "120245-2525"
    When registering a customer
    Then a new customer is added to DTUPay