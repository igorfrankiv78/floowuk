package floowuk.floow.screens.home.mvp;

import android.support.annotation.NonNull;
/*** Created by igorfrankiv on 27/01/2018.*/

public interface IHomeView {
        void letUserToSeeHisJourneys(@NonNull Integer sizeOfDB);
        void showError(String reason);
        void showSuccess( String reason);
}
