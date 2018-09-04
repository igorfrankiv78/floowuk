package floowuk.floow.services.mvp;

/*** Created by igorfrankiv on 28/01/2018.*/

public interface IServiceView {
    void showError( String reason );
    void readyDataForBroadcast( String  jsonListOfUserLocationsObj, String finalResultInMeters, String durrationOfJourney);
}
