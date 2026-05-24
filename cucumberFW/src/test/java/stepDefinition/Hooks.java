package stepDefinition;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {
    @Before("@deletePlace")
    public void beforeScenario(){


        stepDefinition s = new stepDefinition();
       if(s.place_id ==null) {
           s.add_place_payload_with("Amr", "English", "Egy");
           s.user_calls_with_http_request("AddPlaceAPI", "POST");
           try {
               s.verify_place_id_created_maps_to_using("Amr", "getPlaceAPI");
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
       }

    }
}
