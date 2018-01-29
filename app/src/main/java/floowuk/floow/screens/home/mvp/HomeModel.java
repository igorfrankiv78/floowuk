package floowuk.floow.screens.home.mvp;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocation;
import android.support.annotation.NonNull;
import floowuk.floow.utils.JSONUtil;
/*** Created by igorfrankiv on 27/01/2018.*/

public final class HomeModel implements IHomeModel {

    private final DBHelper mDBHelper;

    public HomeModel(DBHelper db) {
        this.mDBHelper = db;
    }

    @Override
    public void getSizeOfDB( @NonNull IOnCompleteModel iOnCompleteModel) {

               if(  mDBHelper.getAllOfTheJourneys().size() > 0)
                   iOnCompleteModel.showSizeOfDB( mDBHelper.getAllOfTheJourneys().size() );
               else
                   iOnCompleteModel.showError( "No recordings!!! Please, record a journey!");
    }

    @Override
    public void writeJourneyInDB( @NonNull List<UserLocation> mListOfUserLocations,
                                  @NonNull IOnCompleteModel iOnCompleteModel, @NonNull String isRecorded) {

        String timeStamp = DateFormat.getDateTimeInstance().format(new Date());
        String jsonStr = JSONUtil.createJsonString(mListOfUserLocations, isRecorded);

        Boolean  wasSuccess = mDBHelper.writeJourneyInDB(jsonStr, timeStamp);

            if (wasSuccess)
                iOnCompleteModel.showSuccess("Your journey has been successfully safed!");
            else
                iOnCompleteModel.showError("Could not save your current journey!");

    }

}
