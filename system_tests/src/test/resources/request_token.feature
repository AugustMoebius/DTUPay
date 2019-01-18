Feature: Request token
  Scenario: Customer requests 1 token
    Given that a user is registered as a customer
    When the user requests 1 token
    Then the user receives 1 token containing an ID and a barcode URL
