package floowuk.floowrx.screens.detail.dagger;

import dagger.Module;
import dagger.Provides;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.screens.detail.mvp.DetailModel;
import floowuk.floowrx.screens.detail.mvp.DetailPresenter;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import floowuk.floowrx.screens.detail.mvp.IDetailView;
/*** Created by igorfrankiv on 28/02/2018.*/

@Module
public class DetailedJourneyModule {

    IDetailView mView;

    public DetailedJourneyModule( IDetailView view ) {
        mView = view;
    }

    @Provides
    public IDetailView iDetailView() {
        return mView;
    }

    @Provides
    @IDetailedJourneyScope
    public DetailPresenter detailPresenter(IDetailView iDetailView , DetailModel detailModel, AndroidRxSchedulers schedulers){
        return new DetailPresenter( iDetailView, detailModel, schedulers );
    }

    @Provides
    @IDetailedJourneyScope
    public DetailModel detailModel(DBHelper mDBHelper) {
        return new DetailModel( mDBHelper );
    }
}
