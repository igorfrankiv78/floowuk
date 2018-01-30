package floowuk.floow.screens.detail.mvp;

import android.support.annotation.NonNull;
import android.util.Log;

import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocations;
import floowuk.floow.utils.JSONUtil;
/*** Created by igorfrankiv on 28/01/2018.*/

public final class DetailModel implements IDetailModel {

    private final DBHelper mDBHelper;

    public DetailModel(DBHelper db) {
        this.mDBHelper = db;
    }

    public static final String ERROR_MESSAGE_DETAIL = "No such a recording in DB!!!";
    public static final String SUCCESS_MESSAGE = "The journey  was deleted successfully!!!";
    public static final String ERROR_MESSAGE_DELETE = "No such a recording in DB!!!";

    @Override
    public void deleteRecordDB( @NonNull Integer id,  @NonNull IDetailOnCompleteModel iDetailOnCompleteModel){

        Integer isDeleted = mDBHelper.deleteUserJourney(id);

            if(isDeleted == 1)
                iDetailOnCompleteModel.showSuccessfullDeletion( SUCCESS_MESSAGE  );
            else
                iDetailOnCompleteModel.showSuccessfullDeletion( ERROR_MESSAGE_DELETE );
    }

    @Override
    public void getDetailedRecord( @NonNull Integer id, @NonNull IDetailOnCompleteModel iDetailOnCompleteModel){

        String jsonStr = mDBHelper.getDetailedRecord(  id  );

        UserLocations userLocations = JSONUtil.readJsonString( jsonStr  );

        if(userLocations != null)
            iDetailOnCompleteModel.getDetailedRecord(userLocations);
        else
            iDetailOnCompleteModel.showError( ERROR_MESSAGE_DETAIL );
    }
}