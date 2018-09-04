package flowigor.ie.floowuk.screen.home.dagger2;

import org.mockito.Mockito;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.screens.home.dagger.HomeModule;
import floowuk.floowrx.screens.home.mvp.HomeModel;
import floowuk.floowrx.screens.home.mvp.HomePresenter;
import floowuk.floowrx.screens.home.mvp.IHomeView;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
/*** Created by igorfrankiv on 28/03/2018. ***/

public class TestHomeModule extends HomeModule {

    IHomeView mView;

    public TestHomeModule( IHomeView view ) {
        super(view);
        mView = view;
    }

    @Override
    public IHomeView iHomeView() {
        return mView;
    }

    @Override
    public HomePresenter homePresenter(IHomeView mIHomeView , HomeModel homeModel, AndroidRxSchedulers schedulers){
        return Mockito.mock( HomePresenter.class );
    }

    @Override
    public HomeModel homeModel(DBHelper mDBHelper) {
        return Mockito.mock( HomeModel.class );
    }
}
