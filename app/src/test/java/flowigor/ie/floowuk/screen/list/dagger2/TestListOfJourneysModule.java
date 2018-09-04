package flowigor.ie.floowuk.screen.list.dagger2;

import org.mockito.Mockito;
import floowuk.floowrx.helpers.DBHelper;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import floowuk.floowrx.screens.listofjourneys.mvp.IListView;
import floowuk.floowrx.screens.listofjourneys.mvp.ListModel;
import floowuk.floowrx.screens.listofjourneys.mvp.ListPresenter;
import floowuk.floowrx.screens.listofjourneys.dagger.ListOfJourneysModule;
/*** Created by igorfrankiv on 25/03/2018. */

public class TestListOfJourneysModule extends ListOfJourneysModule
{    IListView mView;

    public TestListOfJourneysModule(IListView view) {
        super(view);
        mView = view;
    }

    @Override
    public IListView iListView() {
        return mView;
    }

    @Override
    public ListPresenter homePresenter(IListView iListView, ListModel listModel, AndroidRxSchedulers schedulers){
        return Mockito.mock( ListPresenter.class);
    }

    @Override
    public ListModel listModel(DBHelper mDBHelper) {
        return Mockito.mock( ListModel.class );
    }
}