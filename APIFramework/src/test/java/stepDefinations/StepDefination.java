package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;
import resources.utils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDefination extends utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        res = given().spec(requestSpecification())
                .body(TestDataBuild.addPlacePayload(name, language, address));
        // Remove the PendingException line!
    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        response =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();

    }




    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(response.getStatusCode(),200);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(string).toString(),string2);
    }


    @Then("{string} in response bode is {string}")
    public void in_response_bode_is(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(string).toString(),string2);
    }



}
