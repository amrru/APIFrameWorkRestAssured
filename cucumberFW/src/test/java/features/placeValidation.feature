Feature: Validating place API's

  Scenario: Verify if place is being successfully added using AddPlaceApI
    Given Add_Place Payload
    When user calls "AddPlaceAPI" with Post http request
    Then API call is success with status code 200
    And "status" is "OK"
    And "scope" is "APP"