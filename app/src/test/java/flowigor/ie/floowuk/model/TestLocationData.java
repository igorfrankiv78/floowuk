package flowigor.ie.floowuk.model;

import org.junit.Test;
import org.junit.Before;
import floowuk.floowrx.model.LastRecord;
import floowuk.floowrx.model.LocationData;
import static org.junit.Assert.assertEquals;

/*** Created by igorfrankiv on 07/04/2018.*/

public class TestLocationData {

    private LocationData mLocationData;
    private Double latitude = 53.3518636;
    private Double longitude = -6.276708;
    public final String time = "18 Mar 2018 10:43:05 PM";

    @Before
    public void setUp(){
        mLocationData = new LocationData( latitude, longitude, time );
    }
    @Test
    public void testLocationData() {
        assertEquals( latitude, mLocationData.getLatitude() );
        assertEquals( longitude, mLocationData.getLongitude() );
        assertEquals( time, mLocationData.getTime() );
    }
}
