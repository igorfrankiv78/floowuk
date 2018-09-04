package floowuk.floowrx.screens.detail.mvp;

import rx.Observable;
import android.support.annotation.NonNull;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.model.UserLocations;
/*** Created by igorfrankiv on 28/01/2018.*/

public class DetailModel implements IDetailModel {

    private final DBHelper mDBHelper;

    public DetailModel(DBHelper db) {
        this.mDBHelper = db;
    }

    public static final String SUCCESS_MESSAGE = "The journey was deleted successfully!!!";

    @Override
    public Observable<String> deleteRecordDB(@NonNull Integer id){
        return mDBHelper.deleteUserJourney(id).filter(menuItem -> menuItem == 1).map(menuItem -> SUCCESS_MESSAGE);
    }

    @Override
    public Observable<UserLocations> getDetailedRecord( @NonNull Integer id ){
        return mDBHelper.getDetailedRecord(  id  );
    }
}
