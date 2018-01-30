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

    public static final String ERROR_MESSAGE_SIZE = "No recordings!!! Please, record a journey!";
    public static final String ERROR_MESSAGE_WRITE = "Could not save your current journey!";
    public static final String SUCCESS_MESSAGE  = "Your journey has been successfully safed!";

    public HomeModel(DBHelper db) {
        this.mDBHelper = db;
    }

    @Override
    public void getSizeOfDB( @NonNull IOnCompleteModel iOnCompleteModel) {

               if( mDBHelper.getAllOfTheJourneys().size() > 0 )
                   iOnCompleteModel.showSizeOfDB( mDBHelper.getAllOfTheJourneys().size() );
               else
                   iOnCompleteModel.showError( ERROR_MESSAGE_SIZE );
    }

    @Override
    public void writeJourneyInDB( @NonNull List<UserLocation> mListOfUserLocations,
                                  @NonNull IOnCompleteModel iOnCompleteModel, @NonNull String isRecorded) {

        String timeStamp = DateFormat.getDateTimeInstance().format(new Date());

        if( mListOfUserLocations != null && isRecorded != null  ) {
            String jsonStr = JSONUtil.createJsonString(mListOfUserLocations, isRecorded);

            Boolean wasSuccess = mDBHelper.writeJourneyInDB(jsonStr, timeStamp);

            if (wasSuccess)
                iOnCompleteModel.showSuccess(SUCCESS_MESSAGE );
            else
                iOnCompleteModel.showError(ERROR_MESSAGE_WRITE);
        } else
                iOnCompleteModel.showError(ERROR_MESSAGE_WRITE);
    }

}
