package floowuk.floow.screens.detail.mvp;

import android.support.annotation.NonNull;

import java.util.List;

import floowuk.floow.model.UserLocation;

/*** Created by igorfrankiv on 28/01/2018.*/

public interface IDetailPresenter  {
    void deleteRecordDB(@NonNull Integer recordId);
    void getDetailedRecord( @NonNull Integer recordId);
}
