package flowigor.ie.floowuk.model;

import org.junit.Before;
import org.junit.Test;

import floowuk.floowrx.model.LastKnownResults;

import static org.junit.Assert.assertEquals;

/*** Created by igorfrankiv on 07/04/2018. */

public class TestLastKnownResults {

    private LastKnownResults mLastKnownResults;
    private String time = "03/18/2018 22:42:04";
    private String distance = "4.489731311798096";

    @Before
    public void setUp(){
        mLastKnownResults = new LastKnownResults( time, distance );
    }

    @Test
    public void testLastKnownResults() {
        assertEquals( time , mLastKnownResults.getTime() );
        assertEquals( distance , mLastKnownResults.getDistance() );
    }
}
