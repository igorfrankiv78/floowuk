package floowuk.floow.screens.detail.mvp;

import android.support.annotation.NonNull;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocations;
/*** Created by igorfrankiv on 28/01/2018.*/

public class DetailPresenter implements IDetailPresenter, IDetailOnCompleteModel {

    private IDetailView mIDetailView;
    private DetailModel mDetailModel;

    public DetailPresenter (DBHelper mDBHelper, IDetailView mIDetailView)
    {
        this.mIDetailView = mIDetailView;
        this.mDetailModel = new DetailModel( mDBHelper );
    }

    @Override
    public void deleteRecordDB(@NonNull Integer recordId){
        this.mDetailModel.deleteRecordDB( recordId, this);
    }

    @Override
    public void showSuccessfullDeletion(@NonNull String reason) {
        this.mIDetailView.showSuccefulMessage(reason);
    }

    @Override
    public void showError( String reason ) {
        this.mIDetailView.showError( reason );
    }

    // This is call form View
    @Override
    public void getDetailedRecord( @NonNull Integer recordId ){
        this.mDetailModel.getDetailedRecord(  recordId,   this);
    }
    // This is callback form Model
    @Override
    public void getDetailedRecord(@NonNull UserLocations userLocations) {

        this.mIDetailView.getDetailedJourney(userLocations);
    }

}
