Feature: Request report

  Scenario: User request a report of all transactions
    Given a registered user with id ""
    When the user requests a report of all transactions
    Then the user gets a report of the transactions

  Scenario: User request a report of all transactions previous to a date
    Given a registered user with id ""
    When the user requests a report of all transactions previous to "20-01-2019"
    Then the user gets a report of the transactions

  Scenario: User requests a report of all transactions after a date
    Given a registered user with id ""
    When the user requests a report of all transactions after "19-01-2019"
    Then the user gets a report of the transactions

  Scenario: User requests a report of all transactions between dates
    Given a registered user with id ""
    When the user requests a report of all transactions between "19-01-2019" and "20-01-2019"
    Then the user gets a report of the transactions
