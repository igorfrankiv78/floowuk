package flowigor.ie.floowuk.model;

import org.junit.Test;
import org.junit.Before;
import floowuk.floowrx.model.UserLocationsDB;
import static org.junit.Assert.assertEquals;

/*** Created by igorfrankiv on 07/04/2018.*/

public class TestUserLocationsDB {

  private String id = "1";
  private String locations =
          "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":" +
                  "\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766," +
          "\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371," +
                  "\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}";
  private String timeStamp = "18 Mar 2018 10:47:06 PM";
  private UserLocationsDB mUserLocationsDB;

  @Before
  public void setUp(){
      mUserLocationsDB = new UserLocationsDB ( id,  locations,  timeStamp );
  }

  @Test
  public void testUserLocationsDB(){
      assertEquals( id, mUserLocationsDB.getId() );
      assertEquals( locations, mUserLocationsDB.getLocations() );
      assertEquals( timeStamp, mUserLocationsDB.getTimeStamp() );
  }

}
