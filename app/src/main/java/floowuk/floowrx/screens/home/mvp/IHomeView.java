package floowuk.floowrx.screens.home.mvp;

import android.support.annotation.NonNull;
import floowuk.floowrx.model.JourneyDATA;
import rx.Observable;
/*** Created by igorfrankiv on 27/01/2018. ***/

public interface IHomeView {
        void letUserToSeeHisJourneys(@NonNull Integer sizeOfDB);
        void showError( );
        void onCompleteResult(Boolean isSaved);

        Observable<Void> btnShowJourneysObservable();
        Observable<Void> btnStartServiceObservable();
        Observable<JourneyDATA> btnStopServiceObservable();

        void  startService();

}
