package flowigor.ie.floowuk.screen.detail.mvp;

import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import com.twistedequations.mvl.rx.TestAndroidRxSchedulers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import flowigor.ie.floowuk.screen.detail.model.DetailPresenterModelTest;
import rx.Observable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import floowuk.floowrx.screens.detail.mvp.DetailModel;
import floowuk.floowrx.screens.detail.mvp.DetailPresenter;
import floowuk.floowrx.screens.detail.mvp.IDetailView;

/*** Created by igorfrankiv on 15/03/2018. */

public class DetailPresenterTest {

    DetailPresenter detailPresenter;
    IDetailView iDetailView;
    DetailModel detailModel;
    private AndroidRxSchedulers schedulers = new TestAndroidRxSchedulers();
    DetailPresenterModelTest mDetailPresenterModelTest;

    @Before
    public void setUp() throws Exception {
        detailModel = mock(DetailModel.class);
        iDetailView = mock(IDetailView.class);
        detailPresenter = new DetailPresenter(iDetailView, detailModel, schedulers);
        mDetailPresenterModelTest = new DetailPresenterModelTest();
                //Mock the iDetailView and detailModel with blank observables by default
                when(iDetailView.readIOdataRX()).thenReturn(Observable.empty());
                when(detailModel.getDetailedRecord(mDetailPresenterModelTest.getNum())).thenReturn(Observable.empty());

                // Mock the view with never observables to simulate no clicks,
                // empty causes an on complete event which can cause problems
                when(iDetailView.btnDeleteUserJourneyObservable()).thenReturn(Observable.never());
                when(detailModel. deleteRecordDB( mDetailPresenterModelTest.getNum() ) ).thenReturn(Observable.empty());
    }

    @Test
    public void getDataSucessTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------   getJourney()              ---------------
        // 1 ---------------
        when(   iDetailView. readIOdataRX() ).thenReturn(Observable.just( mDetailPresenterModelTest.getNum() ));
        // 2 ---------------
        when(   detailModel. getDetailedRecord( mDetailPresenterModelTest.getNum() ) ).thenReturn(Observable.just( mDetailPresenterModelTest.getUserLocations() ));
        // ---------------   deleteJourneys()  ---------------
        when(   iDetailView. btnDeleteUserJourneyObservable() ).thenReturn(Observable.just( mDetailPresenterModelTest.getNum() ));
        when(   detailModel. deleteRecordDB( mDetailPresenterModelTest.getNum() ) ).thenReturn(Observable.just( DetailModel.SUCCESS_MESSAGE ));
        // ---------------------------------------------
            detailPresenter.onCreate();
        // ---------------------------------------------
        // ---------------  verify --------------- getJourney()  ---------------
        verify( this.iDetailView, times(1)).getDetailedJourney( mDetailPresenterModelTest.getUserLocations());
        // ---------------  verify --------------- deleteJourneys()  ---------------
        verify( this.iDetailView, times(1)).showSuccefulMessage( DetailModel.SUCCESS_MESSAGE );
        // ---------------------------------------------
        detailPresenter.unsubscribe();
        // ---------------------------------------------
    }

    // Test calling all the methods in the chain in the Presenter
    @Test
    public void getDataSucessFullTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------   getJourney()              ---------------
        // 1 ---------------
        when(   iDetailView. readIOdataRX() ).thenReturn(Observable.just( mDetailPresenterModelTest.getNum() ));
        // 2 ---------------
        when(   detailModel. getDetailedRecord( mDetailPresenterModelTest.getNum() ) ).thenReturn(Observable.just( mDetailPresenterModelTest.getUserLocations() ));
        // 3 ---------------
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {

                System.out.print("getDetailedJourney test \n");
                System.out.println( mDetailPresenterModelTest.getUserLocations().getIsRecorded() );
                return null;
            }
        }).when(iDetailView).getDetailedJourney( mDetailPresenterModelTest.getUserLocations());
        // ---------------   ---------------   ---------------
        // ---------------   deleteJourneys()  ---------------
        when(   iDetailView. btnDeleteUserJourneyObservable() ).thenReturn(Observable.just( mDetailPresenterModelTest.getNum() ));
        when(   detailModel. deleteRecordDB( mDetailPresenterModelTest.getNum() ) ).thenReturn(Observable.just( DetailModel.SUCCESS_MESSAGE ));

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("showSuccefulMessage test \n"); return null; }).
                when(iDetailView).showSuccefulMessage(DetailModel.SUCCESS_MESSAGE);

        detailPresenter.onCreate();

        // ---------------  verify --------------- getJourney()  ---------------
        verify( iDetailView).readIOdataRX();
        verify( this.detailModel).getDetailedRecord( mDetailPresenterModelTest.getNum() );
        verify( this.iDetailView, times(1)).getDetailedJourney( mDetailPresenterModelTest.getUserLocations());
        // ---------------  verify --------------- deleteJourneys()  ---------------
        verify( iDetailView). btnDeleteUserJourneyObservable() ;
        verify( detailModel). deleteRecordDB( mDetailPresenterModelTest.getNum() ) ;
        verify( this.iDetailView, times(1)).showSuccefulMessage( DetailModel.SUCCESS_MESSAGE );

        detailPresenter.unsubscribe();
    }

    @Test
    public void deletDataSucessFullTest() throws Exception {

    }


}