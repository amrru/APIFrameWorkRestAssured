package resources;

public enum APIResources {

ADDPlaceAPI("/maps/api/place/add/json"),
getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    APIResources(String resource){

    }
}
