@tagToken
Feature: Request token while having existing tokens
  Scenario: Customer requests tokens while already having tokens
    Given a registered customer with the CPR "120245-2525"
    And that customer has already been assigned 1 token
    When the customer submits a request for 1 token/s
    Then customer receives 1 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens while already having tokens
    Given a registered customer with the CPR "240371-4565"
    And that customer has already been assigned 1 token
    When the customer submits a request for 5 token/s
    Then customer receives 5 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL