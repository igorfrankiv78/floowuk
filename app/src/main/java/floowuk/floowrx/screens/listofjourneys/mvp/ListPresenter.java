package floowuk.floowrx.screens.listofjourneys.mvp;

import rx.Observable;
import rx.Subscription;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
/*** Created by igorfrankiv on 28/01/2018.*/

public class ListPresenter {

    private IListView mIListView;
    private ListModel mListModel;
    private AndroidRxSchedulers schedulers;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Inject
    public ListPresenter ( IListView mIListView , ListModel listModel, AndroidRxSchedulers schedulers )
    {   this.mIListView = mIListView;
        this.mListModel = listModel;
        this.schedulers = schedulers;
    }

    public void onCreate() {
        compositeSubscription.add( getAllJourneys() );
        compositeSubscription.add( startDetailActivitySubscription() );
        compositeSubscription.add( getPositionToRemove() );
    }

    public void unsubscribe(){
        compositeSubscription.unsubscribe();
    }

    private Subscription getAllJourneys(){
        return Observable.just(null) //inital load
                .flatMap(aVoid -> this.mListModel.getAllJourneys()) //get the journeys data from Data Base
                .doOnError(throwable -> {
                    throwable.printStackTrace(); //Log errors
                    mIListView.showError(); // show error dialog on error
                })
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIListView::provideAllJourneys); // display data
    }

    private Subscription startDetailActivitySubscription() {
        return mIListView.listItemClicks().subscribe( mIListView::startDetailActivity );
    }

    private Subscription getPositionToRemove() {
        return mIListView.getPositionToRemove() //inital load
                .map( this.mListModel::getPositionToRemove) //get the cell num to be  removed
                .doOnError(throwable -> {
                    throwable.printStackTrace(); //Log errors
                    mIListView.showError(); // show error dialog on error
                })
                .subscribeOn(schedulers.computation())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIListView::provideNumToBeRemoved ); // remove cell from list
    }

}
