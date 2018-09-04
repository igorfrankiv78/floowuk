package flowigor.ie.floowuk.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import floowuk.floowrx.model.DataForBroadcast;

import static org.junit.Assert.assertEquals;

/**
 * Created by igorfrankiv on 06/04/2018.
 */

public class TestDataForBroadcast {

    private DataForBroadcast mDataForBroadcast;

    private String jsonListOfUserLocationsObj = "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518131,\"longitude\":-6.276708,\"time\":\"03\\/21\\/2018 22:50:43\",\"distance\":0},{\"latitudes\":53.3517643,\"longitude\":-6.2767363,\"time\":\"03\\/21\\/2018 22:50:48\",\"distance\":0},{\"latitudes\":53.3517953,\"longitude\":-6.2767135,\"time\":\"03\\/21\\/2018 22:50:58\",\"distance\":2.074333667755127},{\"latitudes\":53.3517943,\"longitude\":-6.2767325,\"time\":\"03\\/21\\/2018 22:51:24\",\"distance\":3.3444337844848633}]}";
    private String finalResultInMeters = "11";
    private String durrationOfJourney = "11";

    @Before
    public void setUp(){
        mDataForBroadcast = new DataForBroadcast( jsonListOfUserLocationsObj,  finalResultInMeters,  durrationOfJourney );
    }

    @Test
    public void testDataForBroadcast() {
        assertEquals(jsonListOfUserLocationsObj, mDataForBroadcast.getJsonListOfUserLocationsObj());
        assertEquals(finalResultInMeters, mDataForBroadcast.getFinalResultInMeters());
        assertEquals(durrationOfJourney, mDataForBroadcast.getDurrationOfJourney());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestDataForBroadcast.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
