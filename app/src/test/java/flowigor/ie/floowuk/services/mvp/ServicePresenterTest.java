package flowigor.ie.floowuk.services.mvp;

/*** Created by igorfrankiv on 18/03/2018. */

import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import com.twistedequations.mvl.rx.TestAndroidRxSchedulers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import floowuk.floowrx.model.LocationData;
import floowuk.floowrx.services.mvp.IServiceView;
import floowuk.floowrx.services.mvp.ServiceModel;
import floowuk.floowrx.services.mvp.ServicePresenter;
import flowigor.ie.floowuk.services.model.ServicePresenterModelTest;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.subjects.PublishSubject;

public class ServicePresenterTest {

    ServicePresenter mServicePresenter;
    IServiceView mIServiceView;
    ServiceModel mServiceModel;
    private AndroidRxSchedulers schedulers = new TestAndroidRxSchedulers();
    ServicePresenterModelTest servicePresenterModelTest;

    @Before
    public void setUp() throws Exception {
        mServiceModel = mock( ServiceModel.class );
        mIServiceView = mock( IServiceView.class );
        mServicePresenter = new ServicePresenter ( mIServiceView ,  mServiceModel, schedulers );

        servicePresenterModelTest = new ServicePresenterModelTest();
    }

    @Test
    public void servicePresenterObservablesSubscriptionSucessTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------   writeIOdataRX()          ----------------
        when( this.mIServiceView. finalWriteIODataRX() ).thenReturn(Observable.just( ServicePresenter.STATE_OF_RECORDDING_NO ));
        when( this.mServiceModel. finalWriteIODataRX( ServicePresenter.STATE_OF_RECORDDING_NO ) ).
                thenReturn( servicePresenterModelTest.getRecorded() );
        // ---------------   startService()  ----------------
        when( this.mIServiceView. getLocationDataObservable() ).thenReturn(Observable.just( servicePresenterModelTest.getLocationData() ));
                   //--------------- this::startChainOffuncs ---------------
        when( this.mServiceModel. readIOdataRX(servicePresenterModelTest.getLocationData()) ).thenReturn(Observable.just( servicePresenterModelTest.getLocationRXData() ));
        when( this.mServiceModel. calculateDistanceBetween2pointsRX(servicePresenterModelTest.getLocationRXData()) ).thenReturn( servicePresenterModelTest.getDistanceResult() );
        when( this.mServiceModel. writeIODataRX2( servicePresenterModelTest.getDistanceResult())).
                thenReturn( servicePresenterModelTest.getDataForBroadcast() );
        // ---------------------------------------------
        mServicePresenter.onCreate();
        // ---------------  verify ---------------------
        // ------------------------------ writeIOdataRX()  ---------------
        verify( this.mIServiceView).onResultMessage( servicePresenterModelTest.getRecorded() );
        // ---------------   startService()  ----------------
        verify(this.mIServiceView).readyDataForBroadcastRX( servicePresenterModelTest.getDataForBroadcast()  );
        // ---------------------------------------------
        mServicePresenter.unsubscribe();
    }

    @Test
    public void servicePresenterObservablesSubscriptionSucessFullTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------   writeIOdataRX()          ----------------
        when( this.mIServiceView. finalWriteIODataRX() ).thenReturn(Observable.just( ServicePresenter.STATE_OF_RECORDDING_NO ));

        when( this.mServiceModel. finalWriteIODataRX( ServicePresenter.STATE_OF_RECORDDING_NO ) ).
                thenReturn( servicePresenterModelTest.getRecorded() );

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("onResultMessage test \n"); return null; }).
                when(mIServiceView).onResultMessage( servicePresenterModelTest.getRecorded() );
        // ---------------   startService()  ----------------
        when( this.mIServiceView. getLocationDataObservable() ).thenReturn(Observable.just( servicePresenterModelTest.getLocationData() ));
        //        this::startChainOffuncs
        when( this.mServiceModel. readIOdataRX(servicePresenterModelTest.getLocationData()) ).
                thenReturn(Observable.just(servicePresenterModelTest.getLocationRXData() ));
        when( this.mServiceModel. calculateDistanceBetween2pointsRX( servicePresenterModelTest.getLocationRXData() ) ).
                thenReturn( servicePresenterModelTest.getDistanceResult() );
        when( this.mServiceModel. writeIODataRX2( servicePresenterModelTest.getDistanceResult())).
                thenReturn(  servicePresenterModelTest.getDataForBroadcast() );

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("readyDataForBroadcastRX test \n"); return null; }).
                when(mIServiceView).readyDataForBroadcastRX( servicePresenterModelTest.getDataForBroadcast() );
        // ---------------------------------------------
        mServicePresenter.onCreate();
        // ---------------  verify ---------------------
        // ------------------------------ writeIOdataRX()  ---------------
        verify( this.mIServiceView).finalWriteIODataRX();
        verify( this.mServiceModel).finalWriteIODataRX( ServicePresenter.STATE_OF_RECORDDING_NO );
        verify( this.mIServiceView).onResultMessage( servicePresenterModelTest.getRecorded() );
        // ---------------   startService()  ------------
        verify(this.mIServiceView). getLocationDataObservable();
        verify( this.mServiceModel). readIOdataRX( servicePresenterModelTest.getLocationData() );
        verify( this.mServiceModel). calculateDistanceBetween2pointsRX( servicePresenterModelTest.getLocationRXData() );
        verify( this.mServiceModel). writeIODataRX2( servicePresenterModelTest.getDistanceResult());
        verify(this.mIServiceView).readyDataForBroadcastRX( servicePresenterModelTest.getDataForBroadcast() );
        // ---------------------------------------------
        mServicePresenter.unsubscribe();
    }
}
