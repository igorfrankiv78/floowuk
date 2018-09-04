package flowigor.ie.floowuk.model;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import floowuk.floowrx.model.LocationData;
import floowuk.floowrx.model.LocationRXData;
import floowuk.floowrx.model.UserLocation;

import static org.junit.Assert.assertEquals;

/*** Created by igorfrankiv on 07/04/2018. ***/

public class TestLocationRXData {

    private LocationRXData mLocationRXData;
    private LocationData locationData;
    private List<UserLocation> listOfUserLocations;

    @Before
    public void setUp(){

        locationData = new LocationData ( 53.3518636, -6.2766698, "03/18/2018 22:42:04" );

        listOfUserLocations = new ArrayList<>();

        listOfUserLocations.add( new UserLocation (53.3518636,-6.2766698, "03/18/2018 22:42:04",  0) );

        listOfUserLocations.add( new UserLocation (53.3517766,-6.2767846, "03/18/2018 22:42:04",  0) );

        listOfUserLocations.add( new UserLocation (53.3517371,-6.2767983, "03/18/2018 22:43:03",  4.489731311798096 ) );

        mLocationRXData = new LocationRXData( locationData, listOfUserLocations);
    }

    @Test
    public void testLocationRXData() {
        assertEquals( locationData, mLocationRXData.getLocationData() );
        assertEquals( listOfUserLocations, mLocationRXData.getListOfUserLocations() );
    }

}
