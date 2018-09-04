package floowuk.floowrx.screens.listofjourneys.mvp;

import java.util.List;
import floowuk.floowrx.model.DeleteItemLocationsDB;
import floowuk.floowrx.model.UserLocationsDB;
import rx.Observable;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IListModel {
    Observable<List<UserLocationsDB>> getAllJourneys();
    Integer getPositionToRemove(  DeleteItemLocationsDB deleteItemLocationsDB );
}
