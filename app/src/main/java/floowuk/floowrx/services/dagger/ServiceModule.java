package floowuk.floowrx.services.dagger;

import dagger.Module;
import dagger.Provides;
import android.content.Context;
import floowuk.floowrx.services.mvp.IServiceView;
import floowuk.floowrx.services.mvp.ServiceModel;
import floowuk.floowrx.services.mvp.ServicePresenter;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
/*** Created by igorfrankiv on 01/03/2018.*/

@Module
public class ServiceModule {

    IServiceView mView;

    public ServiceModule( IServiceView view ) {
        mView = view;
    }

    @Provides
    public IServiceView iServiceView() {
        return mView;
    }

    @Provides
    @IServiceScope
    public ServicePresenter servicePresenter(IServiceView mIServiceView , ServiceModel serviceModel, AndroidRxSchedulers schedulers){
        return new ServicePresenter(  mIServiceView, serviceModel, schedulers );
    }

    @Provides
    @IServiceScope
    public ServiceModel serviceModel(Context context) {
        return new ServiceModel(  context );
    }
}