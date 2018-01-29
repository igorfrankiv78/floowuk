package floowuk.floow.screens.detail.mvp;

import floowuk.floow.model.UserLocations;

/*** Created by igorfrankiv on 28/01/2018.*/

public interface IDetailView {
    void showError( String reason );
    void showSuccefulMessage( String reason );
    void getDetailedJourney ( UserLocations userLocations);
}
