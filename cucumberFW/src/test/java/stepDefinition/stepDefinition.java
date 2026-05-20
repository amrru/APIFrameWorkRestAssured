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
import pojo.googleMaps;
import resources.Utils;
import resources.testDataBuilder;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinition {

    Response res;
    RequestSpecification reqSpec;

    @Given("Add_Place Payload")
    public void add_place_payload() {


        googleMaps GmapsObj = testDataBuilder.addPlacePayLoad();

         reqSpec = Utils.requestSpecification(GmapsObj);
    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
         res = given()
                .spec(reqSpec)
                .when()
                .post("maps/api/place/add/json").then().extract().response();

    }
    @Then("API call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer int1) {
            assertEquals( res.getStatusCode(),200);
    }
    @Then("{string} is {string}")
    public void is(String string, String string2) {
        String resp = res.asString();
        JsonPath js = new JsonPath(resp);
        js.get(string);
        assertEquals( js.get(string).toString(),string2);

    }


}
