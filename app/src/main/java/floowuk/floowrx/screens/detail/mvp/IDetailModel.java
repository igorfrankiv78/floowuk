package floowuk.floowrx.screens.detail.mvp;

import android.support.annotation.NonNull;
import floowuk.floowrx.model.UserLocations;
import rx.Observable;

/*** Created by igorfrankiv on 28/01/2018.*/

public interface IDetailModel {
    Observable<String> deleteRecordDB(@NonNull Integer id );
    Observable<UserLocations> getDetailedRecord(@NonNull Integer id );
}
