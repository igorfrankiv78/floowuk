package flowigor.ie.floowuk.screens.home.model;

import java.util.List;
import java.util.ArrayList;
import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.screens.home.FloowHomeActivity;

/**
 * Created by igorfrankiv on 21/04/2018.
 */
public class HomePresenterModelTest2 {

    private final Integer sizeOfDB;
    private final List< UserLocation > listOfUserLocations;
    private final JourneyDATA journeyDATA;
    private final Boolean iSjourneyInWritten;
    private final String timeStamp;

    public HomePresenterModelTest2(){
        sizeOfDB = 1;
        listOfUserLocations = new ArrayList<>();

//        E/getId =: 3
        String isRecorded = FloowHomeActivity.IS_RECORDED_POSITIVE;
        listOfUserLocations.add( new UserLocation (53.3518131,  -6.276708, "03/21/2018 22:50:43",  0) );
        listOfUserLocations.add( new UserLocation (53.3517643,  -6.2767363, "03/21/2018 22:50:48",  0) );
        listOfUserLocations.add( new UserLocation (53.3517953,  -6.2767135, "03/21/2018 22:50:58",  2.074333667755127) );
        listOfUserLocations.add( new UserLocation (53.3517943,  -6.2767325, "03/21/2018 22:51:24",  3.3444337844848633) );

        timeStamp = "21 Mar 2018 10:51:30 PM";
        journeyDATA = new JourneyDATA( listOfUserLocations, isRecorded );
        iSjourneyInWritten = true;
    }

    public Integer getSizeOfDB() {
        return sizeOfDB;
    }

    public List<UserLocation> getListOfUserLocations() {
        return listOfUserLocations;
    }

    public JourneyDATA getJourneyDATA() {
        return journeyDATA;
    }

    public Boolean getiSjourneyInWritten() {
        return iSjourneyInWritten;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
