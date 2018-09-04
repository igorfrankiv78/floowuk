package flowigor.ie.floowuk.model;

import org.junit.Test;
import org.junit.Before;
import floowuk.floowrx.model.DistanceResult;
import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 07/04/2018. */

public class TestDistanceResult {

    private DistanceResult mDistanceResult;
    private Double valueResult = 10.2;
    private String finalResultInMeters = "110";
    private Double latitude = 53.3518636;
    private Double longitude = -6.276708;
    private String time = "18 Mar 2018 10:43:05 PM";

    @Before
    public void setUp(){
        mDistanceResult = new DistanceResult( valueResult, finalResultInMeters, latitude, longitude, time);
    }

    @Test
    public void testDistanceResult() {
        assertEquals(valueResult, mDistanceResult.getValueResult() );
        assertEquals(finalResultInMeters, mDistanceResult.getFinalResultInMeters() );
        assertEquals(latitude, mDistanceResult.getLatitude() );
        assertEquals(longitude , mDistanceResult.getLongitude() );
        assertEquals(time , mDistanceResult.getTime() );
    }
}
