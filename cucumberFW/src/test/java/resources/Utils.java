package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.googleMaps;

public class Utils {
    public static RequestSpecification requestSpecification(googleMaps GmapsObj){

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")   // base URI
                .addQueryParam("key", "qaclick123")             // query param
                .addHeader("Content-Type", "application/json")  // header
                .setBody(GmapsObj)                              // body object
                .setContentType(ContentType.JSON)
                .build();
        return reqSpec;
    }
}
