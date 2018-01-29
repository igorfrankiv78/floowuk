package floowuk.floow.screens.home.mvp;

import android.support.annotation.NonNull;
/*** Created by igorfrankiv on 27/01/2018.*/

public interface IOnCompleteModel {
    void showError(@NonNull String reason);
    void showSuccess(@NonNull String reason);
    void showSizeOfDB(@NonNull Integer sizeOfDB);
}
