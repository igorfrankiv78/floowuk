package floowuk.floowrx.screens.home.mvp;

import java.text.DateFormat;
import java.util.Date;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.utils.JSONUtil;
import rx.Observable;

/*** Created by igorfrankiv on 27/01/2018.*/

public class HomeModel implements IHomeModel {

    private final DBHelper mDBHelper;

    public HomeModel(DBHelper db) {
        this.mDBHelper = db;
    }

    @Override
      public Boolean writeJourneyInDB( JourneyDATA journeyDATA ){

        String timeStamp = DateFormat.getDateTimeInstance().format(new Date());

        if (journeyDATA.getmListOfUserLocations() != null &&journeyDATA.getIsRecorded()  != null) {

            String jsonStr = JSONUtil.createJsonString(journeyDATA.getmListOfUserLocations(), journeyDATA.getIsRecorded());

            return mDBHelper.writeJourneyInDB(jsonStr, timeStamp);
        } else
            return false;
      }

    @Override
    public Integer getSizeOfDB() {
        return  mDBHelper.getAllOfTheJourneys() ;
    }

}
