package flowigor.ie.floowuk.services.model;

import java.util.ArrayList;
import java.util.List;

import floowuk.floowrx.model.DataForBroadcast;
import floowuk.floowrx.model.DistanceResult;
import floowuk.floowrx.model.LocationData;
import floowuk.floowrx.model.LocationRXData;
import floowuk.floowrx.model.UserLocation;

/**
 * Created by igorfrankiv on 18/03/2018.
 */

public class ServicePresenterModelTest {

    private Boolean isRecorded = true;
    private LocationData locationData;
    private DataForBroadcast dataForBroadcast;
    private List< UserLocation > listOfUserLocations;
    private DistanceResult distanceResult;
    LocationRXData locationRXData;

    public ServicePresenterModelTest(){

         locationData = new LocationData ( 53.3518131,  -6.276708, "03/21/2018 22:50:43");
         dataForBroadcast = new DataForBroadcast (
                "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766,\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371,\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}",
                "101", "1.45") ;

        listOfUserLocations = new ArrayList<>();
         listOfUserLocations.add( new UserLocation ( 53.3518131,  -6.276708, "\"03/21/2018 22:50:43\"", 121));

         distanceResult = new DistanceResult( 12.1,"101",
                 53.3518131, -6.276708, "03/21/2018 22:50:43");

         locationRXData = new LocationRXData(  locationData, listOfUserLocations );
    }

    public Boolean getRecorded() {
        return isRecorded;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public DataForBroadcast getDataForBroadcast() {
        return dataForBroadcast;
    }

    public List<UserLocation> getListOfUserLocations() {
        return listOfUserLocations;
    }

    public DistanceResult getDistanceResult() {
        return distanceResult;
    }

    public LocationRXData getLocationRXData() {
        return locationRXData;
    }
}
