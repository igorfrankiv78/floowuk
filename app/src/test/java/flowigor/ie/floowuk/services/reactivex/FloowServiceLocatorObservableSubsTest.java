package flowigor.ie.floowuk.services.reactivex;

import org.junit.Before;
import org.junit.Test;

import floowuk.floowrx.model.LocationData;
import floowuk.floowrx.model.LocationRXData;
import floowuk.floowrx.services.mvp.IServiceView;
import floowuk.floowrx.services.mvp.ServiceModel;
import floowuk.floowrx.services.mvp.ServicePresenter;
import flowigor.ie.floowuk.services.model.ServicePresenterModelTest;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by igorfrankiv on 19/04/2018.
 */

public class FloowServiceLocatorObservableSubsTest {

   private ServicePresenterModelTest servicePresenterModelTest;
   private IServiceView mIServiceView;
   private ServiceModel mServiceModel;

    @Before
    public void setUp() throws Exception {
        mServiceModel = mock( ServiceModel.class );
        mIServiceView = mock( IServiceView.class );
        servicePresenterModelTest = new ServicePresenterModelTest();
    }

        @Test
    public void testGetLocationDataObservable() throws Exception {
//            Observable<LocationData> getLocationDataObservable ();
            when( mIServiceView.getLocationDataObservable() ).thenReturn(Observable.just( servicePresenterModelTest.getLocationData()  ));

            TestSubscriber<LocationData> testSubscriber = new TestSubscriber<>();

            mIServiceView.getLocationDataObservable().subscribe( testSubscriber );

            testSubscriber.assertNoErrors();

            testSubscriber.assertValue( servicePresenterModelTest.getLocationData() );

            testSubscriber.assertValueCount( 1 );

            testSubscriber.unsubscribe();
        }

        @Test
    public void testFinalWriteIODataRX() throws Exception {
//            Observable<String> finalWriteIODataRX ();
            when( mIServiceView.finalWriteIODataRX() ).thenReturn(Observable.just( ServicePresenter.STATE_OF_RECORDDING_NO  ));

            TestSubscriber<String> testSubscriber = new TestSubscriber<>();

            mIServiceView.finalWriteIODataRX().subscribe( testSubscriber );

            testSubscriber.assertNoErrors();

            testSubscriber.assertValue( ServicePresenter.STATE_OF_RECORDDING_NO );

            testSubscriber.assertValueCount( 1 );

            testSubscriber.unsubscribe();
        }

        @Test
    public void testReadIOdataRX() throws Exception {
//            Observable<LocationRXData> readIOdataRX (LocationData locationData );
            when( mServiceModel.readIOdataRX( servicePresenterModelTest.getLocationData()) ).thenReturn(Observable.just( servicePresenterModelTest.getLocationRXData() ));

            TestSubscriber<LocationRXData> testSubscriber = new TestSubscriber<>();

            mServiceModel.readIOdataRX(servicePresenterModelTest.getLocationData()).subscribe( testSubscriber );

            testSubscriber.assertNoErrors();

            testSubscriber.assertValue( servicePresenterModelTest.getLocationRXData() );

            testSubscriber.assertValueCount( 1 );

            testSubscriber.unsubscribe();
        }
//    @Test
//    public void servicePresenterTestSubscriptionSucessTest() throws Exception {
//
//        ArrangeBuilder builder = new ArrangeBuilder();
//
//        TestSubscriber<LocationData> testSubscriber = new TestSubscriber<>();
//
//        mIServiceView. getLocationDataObservable().subscribe(testSubscriber);
//
//        builder.withUserLocationsDB(servicePresenterModelTest.getLocationData() );
//
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertValue(servicePresenterModelTest.getLocationData() );
//        testSubscriber.assertValueCount(1);
//        testSubscriber.unsubscribe();
//        // testSubscriber.assertNoValues();
//    }
//
//    private class ArrangeBuilder {
////        AsyncSubject: only emits the last value of the source Observable
////
////        BehaviorSubject: emits the most recently emitted item and all the subsequent items of the source Observable when a observer subscribe to it
//
//        //        PublishSubject: emits all the subsequent items of the source Observable at the time of the subscription
////        ReplaySubject: emits all the items of the source Observable, regardless of when the subscriber subscribes.
////        http://www.juanmendez.info/2015/11/when-to-use-publishsubject-or.html
//        private PublishSubject<LocationData> mUserLocationsDBSubject = PublishSubject.create();
//
//        public ArrangeBuilder() {
//            when( mIServiceView. getLocationDataObservable()).thenReturn(mUserLocationsDBSubject);
//        }
//
//        ArrangeBuilder withUserLocationsDB(LocationData locationData) {
//            mUserLocationsDBSubject.onNext(locationData);
//            return this;
//        }
//
//    }
}
