package flowigor.ie.floowuk.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.model.UserLocation;
import android.support.annotation.NonNull;
import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 07/04/2018. */

public class TestJourneyDATA {

    private JourneyDATA mJourneyDATA;
    private List<UserLocation> mListOfUserLocations = new ArrayList<>();
    private String isRecorded = "true";

    @Before
    public void setUp(){
        mListOfUserLocations.add( new UserLocation (53.3518636,  -6.2766698, "03/18/2018 22:42:04",  0) );

        mListOfUserLocations.add( new UserLocation (53.3517766,  -6.2767846, "03/18/2018 22:42:04",  0) );

        mListOfUserLocations.add( new UserLocation (53.3517371,  -6.2767983, "03/18/2018 22:43:03",  4.489731311798096 ) );

        mJourneyDATA = new JourneyDATA( mListOfUserLocations, isRecorded );
    }

    @Test
    public void testJourneyDATA() {
        assertEquals( mListOfUserLocations, mJourneyDATA.getmListOfUserLocations() );
        assertEquals( isRecorded, mJourneyDATA.getIsRecorded() );
    }
}
