package flowigor.ie.floowuk.screens.home.model;

import java.util.ArrayList;
import java.util.List;

import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.screens.home.FloowHomeActivity;

/**
 * Created by igorfrankiv on 21/04/2018.
 */

public class HomePresenterModelTest1 {
    private final Integer sizeOfDB;
    private final List< UserLocation > listOfUserLocations;
    private final JourneyDATA journeyDATA;
    private final Boolean iSjourneyInWritten;
    private final String timeStamp;

    public HomePresenterModelTest1(){
        sizeOfDB = 1;
        listOfUserLocations = new ArrayList<>();

//        E/getId =: 2
        String isRecorded = FloowHomeActivity.IS_RECORDED_POSITIVE;
        listOfUserLocations.add( new UserLocation (53.3517643,  -6.2767974, "03/18/2018 22:44:17",  0) );

        listOfUserLocations.add( new UserLocation (53.3518313,  -6.2767219, "03/18/2018 22:44:23",  0) );

        listOfUserLocations.add( new UserLocation (53.3518636,  -6.2766698, "03/18/2018 22:44:28",  4.995865821838379) );

        listOfUserLocations.add( new UserLocation (53.351878,  -6.2766789, "03/18/2018 22:44:33",   6.709220051765442) );

        timeStamp = "18 Mar 2018 10:47:06 PM";

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

