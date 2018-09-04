package flowigor.ie.floowuk.zendesk.dagger2;

import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import org.mockito.Mockito;

import floowuk.floowrx.model.MyObservable;
import floowuk.zendeskigorlibrary.helpers.ZendeskServiceHelper;
import floowuk.zendeskigorlibrary.screens.dagger.ListOfTicketsModule;
import floowuk.zendeskigorlibrary.screens.mvp.IListView;
import floowuk.zendeskigorlibrary.screens.mvp.ListModel;
import floowuk.zendeskigorlibrary.screens.mvp.ListPresenter;

/*** Created by igorfrankiv on 03/05/2018. */

public class TestListOfTicketsModule extends ListOfTicketsModule {

    IListView mView;

    public TestListOfTicketsModule( IListView view ) {
        super(view);
        mView = view;
    }

    @Override
    public IListView iListView() {
        return mView;
    }

    @Override
    public ListPresenter homePresenter(IListView iListView, ListModel listModel, AndroidRxSchedulers schedulers){
        return Mockito.mock( ListPresenter.class );
    }

    @Override
    public ListModel listModel(ZendeskServiceHelper zendeskServiceHelper, MyObservable myObservable) {
        return Mockito.mock( ListModel.class  );
    }

}
