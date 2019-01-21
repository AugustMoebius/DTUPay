Feature: Request token
  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "120245-2525"
    When the customer submits a request for 1 token/s
    Then customer receives 1 token/s containing an ID and a barcode URL