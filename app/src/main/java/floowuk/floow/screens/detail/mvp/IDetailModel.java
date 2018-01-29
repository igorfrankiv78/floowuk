package floowuk.floow.screens.detail.mvp;

import java.util.List;
import android.content.Context;
import android.support.annotation.NonNull;
import floowuk.floow.model.UserLocation;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IDetailModel {
    void deleteRecordDB( @NonNull Integer id, @NonNull IDetailOnCompleteModel iDetailOnCompleteModel);
    void getDetailedRecord( @NonNull Integer id, @NonNull IDetailOnCompleteModel iDetailOnCompleteModel);
}
