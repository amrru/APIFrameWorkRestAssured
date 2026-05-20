package resources;

import pojo.Location;
import pojo.googleMaps;

import java.util.ArrayList;
import java.util.List;

public class testDataBuilder {
public static googleMaps addPlacePayLoad(){
    googleMaps GmapsObj = new googleMaps();
    GmapsObj.setAccuracy(50);
    GmapsObj.setAddress("29, side layout, cohen 09");
    GmapsObj.setLanguage("French-IN");
    GmapsObj.setPhoneNumber("(+91) 983 893 3937");
    GmapsObj.setWebsite("https://rahulshettyacademy.com");
    GmapsObj.setName("Frontline house");
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
