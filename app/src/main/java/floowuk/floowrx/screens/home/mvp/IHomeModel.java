package floowuk.floowrx.screens.home.mvp;

import android.support.annotation.NonNull;
import floowuk.floowrx.model.JourneyDATA;
import rx.Observable;

/*** Created by igorfrankiv on 27/01/2018.*/
public interface IHomeModel {
    Integer getSizeOfDB( );
    Boolean writeJourneyInDB( JourneyDATA journeyDATA );
}
