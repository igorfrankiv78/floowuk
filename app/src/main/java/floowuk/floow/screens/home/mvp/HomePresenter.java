package floowuk.floow.screens.home.mvp;

import java.util.List;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocation;
import android.support.annotation.NonNull;
/*** Created by igorfrankiv on 27/01/2018.*/

public class HomePresenter implements IHomePresenter, IOnCompleteModel {

    private IHomeView mIHomeView;
    private HomeModel mHomeModel;

    public HomePresenter (DBHelper mDBHelper, IHomeView mIHomeView)
    {
        this.mIHomeView = mIHomeView;
        this.mHomeModel = new HomeModel(mDBHelper);
    }

    @Override
    public void getSizeOfDB( ) {
        this.mHomeModel.getSizeOfDB( this );
    }

    @Override
    public void showError( String reason ) {
        this.mIHomeView.showError( reason );
    }

    @Override
    public void showSizeOfDB(@NonNull Integer sizeOfDB ) {
        this.mIHomeView.letUserToSeeHisJourneys(sizeOfDB);
    }

    @Override
    public void writeJoutneyInDB( @NonNull List<UserLocation> mListOfUserLocations, @NonNull String isRecorded){
        this.mHomeModel.writeJourneyInDB( mListOfUserLocations, this, isRecorded );
    }

    @Override
    public void showSuccess(@NonNull String reason) {
        this.mIHomeView.showSuccess(  reason );
    }

}
