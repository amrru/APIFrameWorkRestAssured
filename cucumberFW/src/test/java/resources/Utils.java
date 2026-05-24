package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.deletePlacePojo;
import pojo.googleMaps;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification reqSpec;

    public static RequestSpecification requestSpecification(googleMaps GmapsObj, deletePlacePojo deleteBody) throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("logging.txt", true)); // append mode

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(getGlobalValue("baseUrl"))
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON);

        if (GmapsObj != null) {
            builder.setBody(GmapsObj);
        } else if (deleteBody != null) {
            builder.setBody(deleteBody);
        }

        return builder.build();
    }
    public static String getGlobalValue(String key){
        Properties prop = new Properties();
        FileInputStream fis;
        try {
             fis = new FileInputStream("C:\\Users\\Amr\\IdeaProjects\\cucumberFW\\src\\test\\java\\resources\\global.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return prop.getProperty(key);
    }


    public static String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();

    }
}
