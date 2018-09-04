package flowigor.ie.floowuk.services.dagger2;

import android.app.Service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import javax.inject.Inject;
import floowuk.floowrx.app.RxMvpApp;
import floowuk.floowrx.services.FloowServiceLocator;
import floowuk.floowrx.services.mvp.IServiceView;
import floowuk.floowrx.services.mvp.ServicePresenter;
import flowigor.ie.floowuk.BuildConfig;

/*** Created by igorfrankiv on 28/03/2018.*/
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FloowServiceLocatorTest {

    @Inject
    ServicePresenter mServicePresenter;
    IServiceView mIServiceView = Mockito.mock( IServiceView.class );
    // The bellow mock does not work. So we use the Robolectric Builder
    FloowServiceLocator myServiceMock = Mockito.mock(FloowServiceLocator.class);

    @Before
    public void setUp() {

        FloowServiceLocator svc = Robolectric.buildService(FloowServiceLocator.class).get();
        DaggerITestServiceComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(svc).component())
                .serviceModule(new TestServiceModule(mIServiceView))
                .build().inject(this);

    }

    @Test
    public void testDoSomethingTestSubscriptionSucessTest() throws Exception {
        mServicePresenter.onCreate();
        mServicePresenter.unsubscribe();
    }
}
