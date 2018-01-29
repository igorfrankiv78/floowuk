package floowuk.floow.screens.listofjourneys.mvp;

import android.support.annotation.NonNull;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocationsDB;
/*** Created by igorfrankiv on 28/01/2018.*/

public final class ListModel implements IListModel {

    private final DBHelper mDBHelper;

    public ListModel(DBHelper db) {
        this.mDBHelper = db;
    }

    @Override
    public void getAllJourneys( @NonNull IListOnCompleteModel iListOnCompleteModel) {

        UserLocationsDB userLocationsDB = mDBHelper.getAllJourneys();

        if( userLocationsDB != null)
            iListOnCompleteModel.provideAllJourneys( userLocationsDB );
        else
            iListOnCompleteModel.showError( "No recordings!!! Please, record a journey!");
    }
}
