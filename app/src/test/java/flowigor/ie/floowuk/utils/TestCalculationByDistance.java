package flowigor.ie.floowuk.utils;

import org.junit.Test;
import java.util.List;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.util.ArrayList;
import floowuk.floowrx.model.DistanceResult;
import floowuk.floowrx.model.LocationData;
import floowuk.floowrx.model.LocationRXData;
import floowuk.floowrx.model.UserLocation;
import android.support.annotation.NonNull;
import static org.junit.Assert.assertEquals;
import floowuk.floowrx.utils.CalculationByDistance;
import flowigor.ie.floowuk.BuildConfig;
/*** Created by igorfrankiv on 09/04/2018.*/

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TestCalculationByDistance {

    @Test
    public void testCalculationByDistance(){

        Double latitude = 53.3517766;
        Double longitude = -6.2767846;
        String time = "03/18/2018 22:42:04";

        LocationData locationData = new LocationData( latitude, longitude, time );
        List<UserLocation> listOfUserLocations = new ArrayList<>();

        listOfUserLocations.add( new UserLocation (53.3518636,-6.2766698,
                "03/18/2018 22:42:04",0 ) );

        listOfUserLocations.add( new UserLocation (53.3517766,-6.2767846,
                "03/18/2018 22:42:04",0 ) );
// the distance between these two points is 4.489731311798096
        listOfUserLocations.add( new UserLocation (53.3517371,-6.2767983,
                "03/18/2018 22:43:03",0 ) );

        LocationRXData locationRXData = new LocationRXData( locationData, listOfUserLocations );

        DistanceResult mDistanceResult = CalculationByDistance.calculateDistanceBetween2points( locationRXData );
        Double expectedValue = 4.489731311798096;

        assertEquals(expectedValue , Double.valueOf(mDistanceResult.getFinalResultInMeters() ), 0.10);

    }

    @Test
    public void testCalculationByDistanceOtherMethods(){
        final double producedValue = CalculationByDistance.calculationByDistance( 53.3517766,-6.2767846, 53.3517371,-6.2767983);
        final double expectedValue = 0.0044853367017050555;
        assertEquals(expectedValue , producedValue, 0.10);
    }

}
