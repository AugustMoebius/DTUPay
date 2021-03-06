@tagGetMerchant
Feature: Get merchant

  Scenario: Get merchant, who is in the system, successfully
    Given a registered merchant with a CVR, the name "Lars" "Larsen" and a bank account with balance 400
    When the merchant submits a request to get the user information
    Then the get submission succeeds and the merchant gets the information

#  Scenario: Get merchant, who is not in the system
#    Given a unregistered merchant with the CVR "DK22223333" with the name "Hans" "Hansen"
#    When the merchant submits a request to get the user information
#    Then the merchant submission fails and he gets an error message "Illegal get request: Not found. Received DK22223333."

