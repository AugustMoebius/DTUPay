@tagToken
Feature: Request token
  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for 1 token/s
    Then the request succeeds
    And customer receives 1 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for 2 token/s
    Then the request succeeds
    And customer receives 2 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for 3 token/s
    Then the request succeeds
    And customer receives 3 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for 4 token/s
    Then the request succeeds
    And customer receives 4 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for 5 token/s
    Then the request succeeds
    And customer receives 5 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

