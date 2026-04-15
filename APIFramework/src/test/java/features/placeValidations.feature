 Feature: Validating place API's

   Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
     Given Add Place Payload with "<name>" "<language>" "<address>"
     When user calls "AddPlaceAPI" with Post http request
     Then the API call is success with status code 200
     And "status" in response body is "OK"
     And "scope" in response bode is "APP"


     Examples:
       | name     | language  | address          |
       | AAhouse  | English   | WorldClassCenter |
       | AAhouse2 | English 2 | WorldClassCenter 2|