Feature: Request token
  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for -1 token/s
    Then the customer receives an error response
    And the response contains the error message "Must request between 1 and 5 tokens"

  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for 0 token/s
    Then the customer receives an error response
    And the response contains the error message "Must request between 1 and 5 tokens"

  Scenario: Customer requests tokens success scenario
    Given a registered customer
    When the customer submits a request for 6 token/s
    Then the customer receives an error response
    And the response contains the error message "Must request between 1 and 5 tokens"
