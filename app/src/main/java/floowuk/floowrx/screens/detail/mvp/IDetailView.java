package floowuk.floowrx.screens.detail.mvp;

import floowuk.floowrx.model.UserLocations;
import rx.Observable;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IDetailView {
    void showError();
    void showSuccefulMessage(String reason);
    void getDetailedJourney(UserLocations userLocations);

    Observable<Integer> btnDeleteUserJourneyObservable();
    Observable<Integer> readIOdataRX();
}
