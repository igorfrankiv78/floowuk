package flowigor.ie.floowuk.model;

import java.util.List;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.model.UserLocations;
import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 07/04/2018. */

public class TestUserLocations {

   private UserLocations mUserLocations;
   private final List<UserLocation> listOfUserLocations = new ArrayList<>();
   private final String isRecorded = "true";

    @Before
    public void setUp() {
        listOfUserLocations.add( new UserLocation (53.3518636,  -6.2766698, "03/18/2018 22:42:04",  0) );

        listOfUserLocations.add( new UserLocation (53.3517766,  -6.2767846, "03/18/2018 22:42:04",  0) );

        listOfUserLocations.add( new UserLocation (53.3517371,  -6.2767983, "03/18/2018 22:43:03",  4.489731311798096) );

        mUserLocations = new UserLocations( listOfUserLocations, isRecorded );
    }


    @Test
    public void testUserLocationsDB(){
        assertEquals( listOfUserLocations, mUserLocations.getListOfUserLocations() );
        assertEquals( isRecorded, mUserLocations.getIsRecorded() );
    }
}
