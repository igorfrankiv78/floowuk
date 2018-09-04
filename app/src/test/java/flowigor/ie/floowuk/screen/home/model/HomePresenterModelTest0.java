package flowigor.ie.floowuk.screen.home.model;

import java.util.ArrayList;
import java.util.List;

import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.screens.home.FloowHomeActivity;

/**
 * Created by igorfrankiv on 04/04/2018.
 */

public class HomePresenterModelTest0 {
        private final Integer sizeOfDB;
        private final List< UserLocation > listOfUserLocations;
        private final JourneyDATA journeyDATA;
        private final Boolean iSjourneyInWritten;
        private final String timeStamp;

        public HomePresenterModelTest0(){
            sizeOfDB = 1;
            listOfUserLocations = new ArrayList<>();

//        E/getId =: 1
                listOfUserLocations.add( new UserLocation (53.3518636,  -6.2766698, "03/18/2018 22:42:04",  0) );

                listOfUserLocations.add( new UserLocation (53.3517766,  -6.2767846, "03/18/2018 22:42:04",  0) );

                listOfUserLocations.add( new UserLocation (53.3517371,  -6.2767983, "03/18/2018 22:43:03",  4.489731311798096) );

        String isRecorded = FloowHomeActivity.IS_RECORDED_POSITIVE;
         timeStamp = "18 Mar 2018 10:43:05 PM";

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
