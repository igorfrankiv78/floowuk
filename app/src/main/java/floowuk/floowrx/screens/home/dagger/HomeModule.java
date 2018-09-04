package floowuk.floowrx.screens.home.dagger;

import dagger.Module;
import dagger.Provides;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.screens.home.mvp.HomeModel;
import floowuk.floowrx.screens.home.mvp.IHomeView;
import floowuk.floowrx.screens.home.mvp.HomePresenter;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;

@Module
public class HomeModule {

    IHomeView mView;

    public HomeModule( IHomeView view ) {
        mView = view;
    }

    @Provides
    public IHomeView iHomeView() {
        return mView;
    }

    @Provides
    @IHomeScope
    public HomePresenter homePresenter( IHomeView mIHomeView , HomeModel homeModel, AndroidRxSchedulers schedulers){
        return new HomePresenter(  mIHomeView, homeModel, schedulers );
    }

    @Provides
    @IHomeScope
    public HomeModel homeModel(DBHelper mDBHelper) {
        return new HomeModel( mDBHelper );
    }
}
