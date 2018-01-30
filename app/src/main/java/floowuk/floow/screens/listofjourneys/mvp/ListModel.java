package floowuk.floow.screens.listofjourneys.mvp;

import android.support.annotation.NonNull;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocationsDB;
import floowuk.floow.screens.home.mvp.HomeModel;

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
            iListOnCompleteModel.showError(  HomeModel.ERROR_MESSAGE_SIZE );
    }
}
