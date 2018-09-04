package flowigor.ie.floowuk.screen.home.reactivex;

import org.junit.Before;
import org.junit.Test;

import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.screens.home.mvp.HomeModel;
import floowuk.floowrx.screens.home.mvp.IHomeView;
import flowigor.ie.floowuk.screen.home.model.HomePresenterModelTest2;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*** Created by igorfrankiv on 15/04/2018.*/

public class FloowHomeActivityObservableSubsTest {

    private HomeModel mHomeModel;
    private IHomeView mIHomeView;

    private HomePresenterModelTest2 mHomePresenterModelTest2;

    @Before
    public void setUp() throws Exception {
        mHomeModel = mock( HomeModel.class );
        mIHomeView = mock( IHomeView.class );
        mHomePresenterModelTest2 = new HomePresenterModelTest2();
    }

    @Test
    public void testBtnShowJourneysObservable() throws Exception {
//        Observable<Void> btnShowJourneysObservable();
        when( mIHomeView.btnShowJourneysObservable() ).thenReturn(Observable.just( null ));

        TestSubscriber<Void> testSubscriber = new TestSubscriber<>();

        mIHomeView.btnShowJourneysObservable().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( null );

        testSubscriber.assertValueCount( 1 );

        testSubscriber.unsubscribe();

    }

    @Test
    public void testBtnStartServiceObservable() throws Exception {
//        Observable<Void> btnStartServiceObservable ();
        when( mIHomeView.btnStartServiceObservable() ).thenReturn(Observable.just( null ));

        TestSubscriber<Void> testSubscriber = new TestSubscriber<>();

        mIHomeView.btnStartServiceObservable().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( null );

        testSubscriber.assertValueCount( 1 );

        testSubscriber.unsubscribe();
    }


    @Test
    public void testBtnStopServiceObservable() throws Exception {
//        Observable<JourneyDATA> btnStopServiceObservable ();
        when( mIHomeView.btnStopServiceObservable() ).thenReturn(Observable.just( mHomePresenterModelTest2.getJourneyDATA() ));

        TestSubscriber<JourneyDATA> testSubscriber = new TestSubscriber<>();

        mIHomeView.btnStopServiceObservable().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( mHomePresenterModelTest2.getJourneyDATA() );

        testSubscriber.assertValueCount( 1 );

        testSubscriber.unsubscribe();
    }
}
