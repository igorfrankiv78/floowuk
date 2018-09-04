package flowigor.ie.floowuk.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.model.UserLocations;
import floowuk.floowrx.utils.JSONUtil;
import flowigor.ie.floowuk.BuildConfig;

import static org.junit.Assert.assertEquals;

/*** Created by igorfrankiv on 09/04/2018.*/
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TestJSONUtil {

    @Test
    public void testJSONUtilCreate() {

        List<UserLocation> listOfUserLocations = new ArrayList<>();

        listOfUserLocations.add( new UserLocation (53.3518636,-6.2766698,
                "03/18/2018 22:42:04",0 ) );

        listOfUserLocations.add( new UserLocation (53.3517766,-6.2767846,
                "03/18/2018 22:42:04",0 ) );

        listOfUserLocations.add( new UserLocation (53.3517371,-6.2767983,
                "03/18/2018 22:43:03",0 ) );

        String isRecorded = "YES";

        String createJsonString = JSONUtil.createJsonString( listOfUserLocations, isRecorded );

        System.out.println( " createJsonString "+ createJsonString );

        String expectedValue = "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766,\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517371,\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":0}]}";
        assertEquals(expectedValue, createJsonString );
    }

    @Test
    public void testJSONUtilRead() {

        String givenValue = "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766,\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517371,\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":0}]}";

        UserLocations userLocations = JSONUtil.readJsonString( givenValue );

        assertEquals(userLocations.getListOfUserLocations().get(0).getTime(),"03/18/2018 22:42:04" );
        assertEquals(userLocations.getListOfUserLocations().get(0).getLatitude(),53.3518636, 0 );
        assertEquals(userLocations.getListOfUserLocations().get(0).getLongitude(),-6.2766698, 0 );

        assertEquals(userLocations.getListOfUserLocations().get(1).getTime(),"03/18/2018 22:42:04" );
        assertEquals(userLocations.getListOfUserLocations().get(1).getLatitude(),53.3517766, 0 );
        assertEquals(userLocations.getListOfUserLocations().get(1).getLongitude(),-6.2767846, 0 );

        assertEquals(userLocations.getListOfUserLocations().get(2).getTime(),"03/18/2018 22:43:03" );
        assertEquals(userLocations.getListOfUserLocations().get(2).getLatitude(),53.3517371, 0 );
        assertEquals(userLocations.getListOfUserLocations().get(2).getLongitude(),-6.2767983, 0 );

        assertEquals(userLocations.getIsRecorded(),"YES" );
    }
}

