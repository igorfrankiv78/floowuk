package floowuk.floow.screens.detail.mvp;

import android.support.annotation.NonNull;
import floowuk.floow.model.UserLocations;
/**
 * Created by igorfrankiv on 28/01/2018.
 */

public interface IDetailOnCompleteModel {
    void showError(@NonNull String reason);
    void getDetailedRecord(@NonNull UserLocations userLocations);
    void showSuccessfullDeletion(@NonNull String reasonB);
}
