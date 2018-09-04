package floowuk.zendeskigorlibrary.screens.dagger;

import dagger.Module;
import dagger.Provides;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;

import floowuk.floowrx.model.MyObservable;
import floowuk.zendeskigorlibrary.helpers.ZendeskServiceHelper;
import floowuk.zendeskigorlibrary.screens.mvp.IListView;
import floowuk.zendeskigorlibrary.screens.mvp.ListModel;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;
import floowuk.zendeskigorlibrary.screens.mvp.ListPresenter;

/***  Created by igorfrankiv on 26/04/2018. ***/
@Module
public class ListOfTicketsModule {

    IListView mView;

    public ListOfTicketsModule( IListView view ) {
        mView = view;
    }

    @Provides
    public IListView iListView() {
        return mView;
    }

    @Provides
    @IListOfTicketsScope
    public ListPresenter homePresenter(IListView iListView, ListModel listModel, AndroidRxSchedulers schedulers){
        return new ListPresenter( iListView, listModel, schedulers);
    }

//    @Provides
//    @IListOfTicketsScope
//    public ListModel listModel(ZendeskService zendeskService) {
//        return new ListModel( zendeskService );
//    }

    @Provides
    @IListOfTicketsScope
    public ListModel listModel( ZendeskServiceHelper zendeskServiceHelper, MyObservable myObservable) {
        return new ListModel( zendeskServiceHelper, myObservable );
    }
}
