package flowigor.ie.floowuk.screen.home.mvp;

import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import com.twistedequations.mvl.rx.TestAndroidRxSchedulers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import floowuk.floowrx.screens.home.mvp.HomeModel;
import floowuk.floowrx.screens.home.mvp.HomePresenter;
import floowuk.floowrx.screens.home.mvp.IHomeView;
import flowigor.ie.floowuk.screen.home.model.HomePresenterModelTest2;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/*** Created by igorfrankiv on 17/03/2018. */

public class HomePresenterTest {

    //Class to test
    HomePresenter mHomePresenter;

    //To be mocked
    private HomeModel mHomeModel;
    private IHomeView mIHomeView;
    private HomePresenterModelTest2 mHomePresenterModelTest2;

    //TestAndroidSchedulers that force everything onto the current thread
    AndroidRxSchedulers schedulers = new TestAndroidRxSchedulers();

    @Before
    public void setUp() throws Exception {
        mHomeModel = mock(HomeModel.class);
        mIHomeView = mock(IHomeView.class);
        mHomePresenter = new HomePresenter ( mIHomeView,  mHomeModel,  schedulers);
        mHomePresenterModelTest2 = new HomePresenterModelTest2();
    }

    @Test
    public void listPresenterObservablesSubscriptionSucessTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------  showJourneys()  ------------
        Mockito.doAnswer((Answer<Observable<Void>>) invocation ->
        { System.out.print("btnShowJourneysObservable clicked \n"); return Observable.just(null); }).
                when(this.mIHomeView).btnShowJourneysObservable( );

        when( this.mHomeModel. getSizeOfDB() ).thenReturn(  mHomePresenterModelTest2.getSizeOfDB()  );
        // ---------------  startServices() ------------
        Mockito.doAnswer((Answer<Observable<Void>>) invocation ->
        { System.out.print("btnStartServiceObservable clicked \n"); return  Observable.just(null); }).
                when(this.mIHomeView).btnStartServiceObservable( );
//        // ---------------  stopServices()  ------------
        when( this.mIHomeView. btnStopServiceObservable() ).thenReturn(Observable.just( mHomePresenterModelTest2.getJourneyDATA() ));
        when( this.mHomeModel. writeJourneyInDB( mHomePresenterModelTest2.getJourneyDATA() ) ).
                thenReturn( mHomePresenterModelTest2.getiSjourneyInWritten() );
        // ---------------------------------------------
        mHomePresenter.onCreate();
        // ---------------------------------------------
        // ---------------  verify ---------------------
        // ---------------  showJourneys()  ------------
        verify( this.mIHomeView ).letUserToSeeHisJourneys( mHomePresenterModelTest2.getSizeOfDB());
//        // ---------------  startServices() ------------
        verify( mIHomeView ).startService();
//        // ---------------  stopServices()  ------------
        verify( this.mIHomeView ).onCompleteResult( mHomePresenterModelTest2.getiSjourneyInWritten() );
        // ---------------------------------------------
        mHomePresenter.unsubscribe();
        // ---------------------------------------------
    }

    @Test
    public void listPresenterObservablesSubscriptionSucessFullTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------  showJourneys()  ------------
        Mockito.doAnswer((Answer<Observable<Void>>) invocation ->
        { System.out.print("btnShowJourneysObservable clicked \n"); return Observable.just(null); }).
                when(this.mIHomeView).btnShowJourneysObservable( );

        when( this.mHomeModel. getSizeOfDB() ).thenReturn(  mHomePresenterModelTest2.getSizeOfDB() );

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("letUserToSeeHisJourneys clicked \n"); return null; }).
                when(this.mIHomeView).letUserToSeeHisJourneys( mHomePresenterModelTest2.getSizeOfDB());
        // ---------------  startServices() ------------
        Mockito.doAnswer((Answer<Observable<Void>>) invocation ->
        { System.out.print("btnStartServiceObservable clicked \n"); return  Observable.just(null); }).
                when(this.mIHomeView).btnStartServiceObservable( );

        Mockito.doAnswer((Answer<Observable<Void>>) invocation ->
        { System.out.print("startService started \n"); return  Observable.just(null); }).
                when(this.mIHomeView).btnStartServiceObservable( );
//        // ---------------  stopServices()  ------------
        when( this.mIHomeView. btnStopServiceObservable() ).thenReturn(Observable.just( mHomePresenterModelTest2.getJourneyDATA() ));
        when( this.mHomeModel. writeJourneyInDB( mHomePresenterModelTest2.getJourneyDATA() ) ).
                thenReturn(  mHomePresenterModelTest2.getiSjourneyInWritten());

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("onCompleteResult \n"); return null; }).
                when(this.mIHomeView).onCompleteResult( mHomePresenterModelTest2.getiSjourneyInWritten() );
        // ---------------------------------------------
        mHomePresenter.onCreate();
        // ---------------------------------------------
        // ---------------  verify ---------------------
        // ---------------  showJourneys()  ------------
        verify( this.mIHomeView ).btnShowJourneysObservable();
        verify( this.mHomeModel ).getSizeOfDB();
        verify( this.mIHomeView ).letUserToSeeHisJourneys( mHomePresenterModelTest2.getSizeOfDB());
//        // ---------------  startServices() ------------
        verify( mIHomeView ).btnStartServiceObservable();
        verify( mIHomeView ).startService();
//        // ---------------  stopServices()  ------------
        verify( this.mIHomeView ).btnStopServiceObservable();
        verify( this.mHomeModel ).writeJourneyInDB( mHomePresenterModelTest2.getJourneyDATA() );
        verify( this.mIHomeView ).onCompleteResult( mHomePresenterModelTest2.getiSjourneyInWritten() );
        // ---------------------------------------------
        mHomePresenter.unsubscribe();
        // ---------------------------------------------
    }
}
