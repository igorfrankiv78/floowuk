package floowuk.floowrx.screens.listofjourneys.mvp;

import java.util.List;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.model.DeleteItemLocationsDB;
import floowuk.floowrx.model.UserLocationsDB;
import floowuk.floowrx.utils.FloowUtil;
import rx.Observable;
/*** Created by igorfrankiv on 28/01/2018.*/

public class ListModel implements IListModel {

    private final DBHelper mDBHelper;

    public ListModel(DBHelper db) {
        this.mDBHelper = db;
    }

    @Override
    public Observable<List<UserLocationsDB>> getAllJourneys() {
        return  mDBHelper.getAllJourneys() ;
    }

    @Override
    public Integer getPositionToRemove( DeleteItemLocationsDB deleteItemLocationsDB ){
        return  FloowUtil.getPositionToBeRemoveD( deleteItemLocationsDB ) ;
    }
}
