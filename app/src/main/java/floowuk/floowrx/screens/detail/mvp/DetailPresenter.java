package floowuk.floowrx.screens.detail.mvp;

import rx.Subscription;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
/*** Created by igorfrankiv on 28/01/2018.*/

public class DetailPresenter {

    private IDetailView mIDetailView;
    private DetailModel mDetailModel;
    private AndroidRxSchedulers schedulers;
    private final CompositeSubscription compositeSubscription;

    @Inject
    public DetailPresenter (IDetailView iDetailView , DetailModel detailModel, AndroidRxSchedulers schedulers)
    {
        this.mIDetailView = iDetailView;
        this.mDetailModel = detailModel;
        this.schedulers = schedulers;
        this.compositeSubscription = new CompositeSubscription();
    }

    public void onCreate( ) {
        compositeSubscription.add( getJourney( ) );
        compositeSubscription.add( deleteJourneys() );
    }

    public void unsubscribe(){
        compositeSubscription.unsubscribe();
    }
    //   handle the subscription
    //   check if view is not null
    //   handle the onError
        private Subscription getJourney(){
        return this.mIDetailView.readIOdataRX()//Observable.just(id) //inital load

                .flatMap( this.mDetailModel::getDetailedRecord ) //get the journeys data from Data Base
                .doOnError(throwable -> {
                    throwable.printStackTrace(); //Log errors
                    mIDetailView.showError( ); // show error dialog on error
                })
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIDetailView::getDetailedJourney , e -> mIDetailView.showError( )); // display data
    }

    private Subscription deleteJourneys(){
        return this.mIDetailView.btnDeleteUserJourneyObservable() //   click
                .flatMap(  this.mDetailModel::deleteRecordDB ) // delete the entry of db from data base
                .doOnError(throwable -> {
                    throwable.printStackTrace(); //Log errors
                    this.mIDetailView.showError( ); // show error dialog on error
                })
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIDetailView::showSuccefulMessage, e -> mIDetailView.showError( ));// show succeful message
    }

}
