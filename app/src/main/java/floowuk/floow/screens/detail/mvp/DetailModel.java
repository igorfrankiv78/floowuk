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

    @Override
    public void deleteRecordDB( @NonNull Integer id,  @NonNull IDetailOnCompleteModel iDetailOnCompleteModel){

        Integer isDeleted = mDBHelper.deleteUserJourney(id);
        Log.e( "isDeleted", String.valueOf( isDeleted ));
            iDetailOnCompleteModel.showSuccessfullDeletion( String.valueOf( isDeleted ) );
    }

    @Override
    public void getDetailedRecord( @NonNull Integer id, @NonNull IDetailOnCompleteModel iDetailOnCompleteModel){

        String jsonStr = mDBHelper.getDetailedRecord(  id  );

        UserLocations userLocations = JSONUtil.readJsonString( jsonStr  );

        if(userLocations != null)
            iDetailOnCompleteModel.getDetailedRecord(userLocations);
        else
            iDetailOnCompleteModel.showError( "No such a recording in DB!!!");
    }
}