Feature: Request token
  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "120245-2525"
    When the customer submits a request for 1 token/s
    Then customer receives 1 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "240371-4565"
    When the customer submits a request for 2 token/s
    Then customer receives 2 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "260192-3267"
    When the customer submits a request for 3 token/s
    Then customer receives 3 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "110388-2164"
    When the customer submits a request for 4 token/s
    Then customer receives 4 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "031294-2754"
    When the customer submits a request for 5 token/s
    Then customer receives 5 token/s containing an ID and a barcode URL
    And customer can access the barcode via the URL

  Scenario: Customer requests tokens success scenario
    Given a registered customer with the CPR "130291-1253"
    When the customer submits a request for -1 token/s
    Then customer receives and error message "Must request between 1-5 tokens"