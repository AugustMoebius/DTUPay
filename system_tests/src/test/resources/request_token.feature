Feature: Request token
  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "270271-2467"
    When the customer submits a request for 1 token
    Then 1 token is generated and stored
    And the customer receives 1 token containing an ID and a barcode URL