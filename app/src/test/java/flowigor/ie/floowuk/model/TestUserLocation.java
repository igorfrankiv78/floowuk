package flowigor.ie.floowuk.model;

import org.junit.Test;
import org.junit.Before;
import floowuk.floow.model.UserLocation;
import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 07/04/2018. ***/

public class TestUserLocation {

    private UserLocation mUserLocation;
    private final double latitude = 53.3517371;
    private final double longitude = -6.2767983;
    private final double distance = 4.489731311798096;
    private final String time = "03/18/2018 22:43:03";

    @Before
    public void setUp(){
        mUserLocation = new UserLocation( latitude, longitude,
                                          time, distance);
    }

    @Test
    public void testUserLocationsDB(){
        assertEquals( latitude, mUserLocation.getLatitude(), 0);
        assertEquals( longitude, mUserLocation.getLongitude(), 0 );
        assertEquals( distance, mUserLocation.getDistance(), 0 );
        assertEquals( time, mUserLocation.getTime() );
    }
}
