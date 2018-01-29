package floowuk.floow.screens.listofjourneys.mvp;

import android.support.annotation.NonNull;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocationsDB;
/*** Created by igorfrankiv on 28/01/2018.*/

public class ListPresenter implements IListPresenter, IListOnCompleteModel{

    private IListView mIListView;
    private ListModel mListModel;

    public ListPresenter ( DBHelper dBHelper, IListView mIListView )
    {   this.mIListView = mIListView;
        this.mListModel = new  ListModel(dBHelper);
    }

    @Override
    public void getAllIdsTimesStamps() {
        this.mListModel.getAllJourneys( this );
    }

    @Override
    public void showError( String reason ) {
        this.mIListView.showError( reason );
    }

    @Override
    public void provideAllJourneys(@NonNull UserLocationsDB userLocationsDB) {
        this.mIListView.provideAllJourneys(  userLocationsDB );
    }


}
