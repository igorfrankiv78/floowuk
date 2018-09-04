package flowigor.ie.floowuk.screen.home.dagger2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import javax.inject.Inject;
import floowuk.floowrx.app.RxMvpApp;
import floowuk.floowrx.screens.home.FloowHomeActivity;
import floowuk.floowrx.screens.home.mvp.HomePresenter;
import floowuk.floowrx.screens.home.mvp.IHomeView;
import flowigor.ie.floowuk.BuildConfig;
/*** Created by igorfrankiv on 28/03/2018. ***/

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FloowHomeActivityTest {

@Inject HomePresenter mHomePresenter;
        IHomeView mIHomeView = Mockito.mock(IHomeView.class);
        FloowHomeActivity floowHomeActivity;

    @Before
    public void setUp() {
        floowHomeActivity = Robolectric.setupActivity( FloowHomeActivity.class );
    }

    @Test
    public void testDoSomethingTestSubscriptionSucessTest() throws Exception {
        DaggerITestHomeActivityComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get( floowHomeActivity ).component())
                .homeModule(new TestHomeModule(mIHomeView))
                .build().inject(this);
    }

}
