package floowuk.floowrx.model;

import java.util.List;

/*** Created by igorfrankiv on 10/03/2018.*/

public final class LocationRXData {

    private final LocationData locationData;
    private final List<UserLocation> listOfUserLocations;

    public  LocationRXData( LocationData locationData, List<UserLocation> listOfUserLocations ){
        this.locationData = locationData;
        this.listOfUserLocations = listOfUserLocations;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public List<UserLocation> getListOfUserLocations() {
        return listOfUserLocations;
    }
}
