package flowigor.ie.floowuk.screen.detail.dagger2;

import org.mockito.Mockito;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.screens.detail.mvp.DetailModel;
import floowuk.floowrx.screens.detail.mvp.IDetailView;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import floowuk.floowrx.screens.detail.mvp.DetailPresenter;
import floowuk.floowrx.screens.detail.dagger.DetailedJourneyModule;
/*** Created by igorfrankiv on 28/03/2018. ***/

public class TestDetailedJourneyModule extends DetailedJourneyModule {

    IDetailView mView;

    public TestDetailedJourneyModule( IDetailView view ) {
        super(view);
        mView = view;
    }

    @Override
    public IDetailView iDetailView() {
        return mView;
    }

    @Override
    public DetailPresenter detailPresenter(IDetailView iDetailView , DetailModel detailModel, AndroidRxSchedulers schedulers){
        return Mockito.mock( DetailPresenter.class );
    }

    @Override
    public DetailModel detailModel(DBHelper mDBHelper) {
        return Mockito.mock( DetailModel.class );
    }

}
