package flowigor.ie.floowuk.screen.detail.model;

import java.util.List;
import java.util.ArrayList;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.model.UserLocations;
import floowuk.floowrx.screens.home.FloowHomeActivity;
/*** Created by igorfrankiv on 18/03/2018. ***/

public class DetailPresenterModelTest {

    private final int num;
    private final List<UserLocation> listOfUserLocations;
    private final UserLocations userLocations;

    public DetailPresenterModelTest (){
        num = 1;

        listOfUserLocations = new ArrayList();

        String isRecorded = FloowHomeActivity.IS_RECORDED_POSITIVE;
//        E/getId =: 1
//                listOfUserLocations.add( new UserLocation (53.3518636,  -6.2766698, "03/18/2018 22:42:04",  0) );
//
//                listOfUserLocations.add( new UserLocation (53.3517766,  -6.2767846, "03/18/2018 22:42:04",  0) );
//
//                listOfUserLocations.add( new UserLocation (53.3517371,  -6.2767983, "03/18/2018 22:43:03",  4.489731311798096) );
//
//        String isRecorded = FloowHomeActivity.IS_RECORDED_POSITIVE;
//        String timeStamp = "18 Mar 2018 10:43:05 PM";
//
//        E/getId =: 2
//        String isRecorded = FloowHomeActivity.IS_RECORDED_POSITIVE;
//        listOfUserLocations.add( new UserLocation (53.3517643,  -6.2767974, "03/18/2018 22:44:17",  0) );
//
//        listOfUserLocations.add( new UserLocation (53.3518313,  -6.2767219, "03/18/2018 22:44:23",  0) );
//
//        listOfUserLocations.add( new UserLocation (53.3518636,  -6.2766698, "03/18/2018 22:44:28",  4.995865821838379) );
//
//        listOfUserLocations.add( new UserLocation (53.351878,  -6.2766789, "03/18/2018 22:44:33",   6.709220051765442) );
//
//        String timeStamp = "18 Mar 2018 10:47:06 PM";
//
//        E/getId =: 3
        listOfUserLocations.add( new UserLocation (53.3518131,  -6.276708, "03/21/2018 22:50:43",  0) );
        listOfUserLocations.add( new UserLocation (53.3517643,  -6.2767363, "03/21/2018 22:50:48",  0) );
        listOfUserLocations.add( new UserLocation (53.3517953,  -6.2767135, "03/21/2018 22:50:58",  2.074333667755127) );
        listOfUserLocations.add( new UserLocation (53.3517943,  -6.2767325, "03/21/2018 22:51:24",  3.3444337844848633) );

        userLocations = new UserLocations(listOfUserLocations, isRecorded);
    }

    public int getNum() {
        return num;
    }

    public List<UserLocation> getListOfUserLocations() {
        return listOfUserLocations;
    }

    public UserLocations getUserLocations() {
        return userLocations;
    }
}
