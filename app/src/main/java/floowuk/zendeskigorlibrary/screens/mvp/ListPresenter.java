package floowuk.zendeskigorlibrary.screens.mvp;

import javax.inject.Inject;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/*** Created by igor on 03/06/2017. ***/

public class ListPresenter
{
    private IListView mIListView;
    private ListModel mListModel;
    private AndroidRxSchedulers schedulers;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Inject
    public ListPresenter(IListView iListView, ListModel listModel, AndroidRxSchedulers schedulers )
    {
        this.mIListView = iListView;
        this.mListModel = listModel;
        this.schedulers = schedulers;
    }

    public void onCreate() {
        compositeSubscription.add(getAllTickets());
    }

    public void unsubscribe(){
        compositeSubscription.unsubscribe();
    }

    private Subscription getAllTickets(){
        return Observable.just(null) //inital load
                .flatMap(aVoid -> this.mListModel.getAllTheTickets()) //get the journeys data from Data Base
                .doOnError(throwable -> {
                    throwable.printStackTrace(); //Log errors
                    mIListView.errorInGettingTickets(ListModel.ERROR_MESSAGE); // show error dialog on error
                })
                .subscribeOn(schedulers.network())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIListView::successInGettingTickets); // display data
    }

}