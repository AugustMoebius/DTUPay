Feature: Request token
  Scenario: Customer requests tokens error scenario
    Given a registered customer
    When the customer submits a request for -1 token/s
    Then the customer receives an error response
    And the response contains the error message "Must request between 1 and 5 tokens"

  Scenario: Customer requests tokens error scenario
    Given a registered customer
    When the customer submits a request for 0 token/s
    Then the customer receives an error response
    And the response contains the error message "Must request between 1 and 5 tokens"

  Scenario: Customer requests tokens error scenario
    Given a registered customer
    When the customer submits a request for 6 token/s
    Then the customer receives an error response
    And the response contains the error message "Must request between 1 and 5 tokens"

  Scenario: Customer requests tokens error scenario
    Given a registered customer
    And that customer has already been assigned 2 unused token/s
    When the customer submits a request for 5 token/s
    Then the customer receives an error response
    And the response contains the error message "Must have 1 or less tokens to request new tokens"
