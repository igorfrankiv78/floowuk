package flowigor.ie.floowuk.services.dagger2;

import android.content.Context;

import com.twistedequations.mvl.rx.AndroidRxSchedulers;

import org.mockito.Mockito;

import floowuk.floowrx.services.dagger.ServiceModule;
import floowuk.floowrx.services.mvp.IServiceView;
import floowuk.floowrx.services.mvp.ServiceModel;
import floowuk.floowrx.services.mvp.ServicePresenter;

/*** Created by igorfrankiv on 28/03/2018. ***/

public class TestServiceModule extends ServiceModule {

    IServiceView mView;

    public TestServiceModule( IServiceView view ) {
        super(view);
        mView = view;
    }

    @Override
    public IServiceView iServiceView() {
        return mView;
    }

    @Override
    public ServicePresenter servicePresenter(IServiceView mIServiceView , ServiceModel serviceModel, AndroidRxSchedulers schedulers){
        return Mockito.mock( ServicePresenter.class );
    }

    @Override
    public ServiceModel serviceModel(Context context) {
        return Mockito.mock( ServiceModel.class );
    }

}
