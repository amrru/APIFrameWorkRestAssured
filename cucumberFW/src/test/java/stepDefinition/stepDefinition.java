package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Location;
import pojo.deletePlacePojo;
import pojo.googleMaps;
import resources.APIResources_enum;
import resources.Utils;
import resources.testDataBuilder;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static resources.APIResources_enum.deletePlaceAPI;
import static resources.Utils.getJsonPath;
import static resources.Utils.requestSpecification;

public class stepDefinition {

    Response res;
    RequestSpecification reqSpec;
    JsonPath js;
    static String placeID;
    static String resource;
    @Given("Add_Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) {

        googleMaps GmapsObj = testDataBuilder.addPlacePayLoad(name,language,address);

        try {
            reqSpec = Utils.requestSpecification(GmapsObj,null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    String place_id;
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String string, String string2) {
        APIResources_enum resourceAPI = APIResources_enum.valueOf(string);
         resource = resourceAPI.getResource();


        if (string2.equalsIgnoreCase("POST")) {
            res = given()
                    .spec(reqSpec)
                    .when()
                    .post(resource).then().extract().response();
        }
        else if(string2.equalsIgnoreCase("GET")){
            res = given()
                    .spec(reqSpec)
                    .when()
                    .get(resource).then().extract().response();

        }
    }


    @Then("API call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer int1) {
            assertEquals( res.getStatusCode(),200);
    }
    @Then("{string} is {string}")
    public void is(String string, String string2) {
        String resp = res.asString();
         js = new JsonPath(resp);
        js.get(string);
        place_id = js.get("place_id");
        assertEquals( js.get(string).toString(),string2);

    }


    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String APIresource) throws FileNotFoundException {
        placeID= getJsonPath(res,"place_id");

        reqSpec = given()
                .spec(requestSpecification(null,null))
                .queryParam("place_id", placeID)
                ;

        user_calls_with_http_request(APIresource, "GET");
        String name= getJsonPath(res,"name");
        assertEquals(name,(expectedName));
    }


    @Given("DeletePlace Payload")
    public void delete_place_payload() throws FileNotFoundException {
        deletePlacePojo deletePlaceBody = new deletePlacePojo();
        deletePlaceBody.setPlace_id(placeID);
        try {
            reqSpec = Utils.requestSpecification(null,deletePlaceBody);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(deletePlaceBody.getPlace_id());

    //   res= given().spec(reqSpec)
    //           .when().post("/maps/api/place/delete/")
    //           .then().extract().response();

    }


}
