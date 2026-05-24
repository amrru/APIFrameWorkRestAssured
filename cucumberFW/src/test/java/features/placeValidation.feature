Feature: Validating place API's

  @AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceApI
    Given Add_Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then API call is success with status code 200
    And "status" is "OK"
    And "scope" is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

  Examples:
    | name    | language | address            |
    | AAhouse | English  | world cross center |
   # | BBhouse | Spanish  | Sea cross center |

  @deletePlace
  Scenario: Verify if delete place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then API call is success with status code 200
    And "status" is "OK"