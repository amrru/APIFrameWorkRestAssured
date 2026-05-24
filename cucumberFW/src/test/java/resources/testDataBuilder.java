package resources;

import pojo.Location;
import pojo.googleMaps;

import java.util.ArrayList;
import java.util.List;

public class testDataBuilder {
public static googleMaps addPlacePayLoad(String name, String language, String address){
    googleMaps GmapsObj = new googleMaps();
    GmapsObj.setAccuracy(50);
    GmapsObj.setAddress(address);
    GmapsObj.setLanguage(language);
    GmapsObj.setPhoneNumber("(+91) 983 893 3937");
    GmapsObj.setWebsite("https://rahulshettyacademy.com");
    GmapsObj.setName(name);
    Location l = new Location();
    l.setLat(-38.383494);
    l.setLng(33.427362);
    GmapsObj.setLocation(l);
    List<String> myList = new ArrayList<String>();
    myList.add("shoe park");
    myList.add("shop");
    GmapsObj.setTypes(myList);

    return GmapsObj;
}
}
