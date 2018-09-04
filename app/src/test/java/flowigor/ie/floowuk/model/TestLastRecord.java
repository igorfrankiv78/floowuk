package flowigor.ie.floowuk.model;

import org.junit.Test;
import org.junit.Before;
import floowuk.floowrx.model.LastRecord;
import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 07/04/2018. */

public class TestLastRecord {

   private LastRecord mLastRecord;
    private final int id = 1;
    private final String listOfLocations = "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766,\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371,\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}";

    @Before
    public void setUp(){
        mLastRecord = new LastRecord( id, listOfLocations);
    }

    @Test
    public void testLastRecord() {
        assertEquals( id, mLastRecord.getId() );
        assertEquals( listOfLocations, mLastRecord.getListOfLocations() );
    }
}
