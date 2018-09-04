package floowuk.floowrx.model;

/*** Created by igorfrankiv on 06/03/2018.*/

public class DataForBroadcast {

   private final String jsonListOfUserLocationsObj;
   private final String finalResultInMeters;
   private final String durrationOfJourney;

    public  DataForBroadcast ( String jsonListOfUserLocationsObj, String finalResultInMeters, String durrationOfJourney){
        this.jsonListOfUserLocationsObj = jsonListOfUserLocationsObj;
        this.finalResultInMeters = finalResultInMeters;
        this.durrationOfJourney = durrationOfJourney;
    }

    public String getJsonListOfUserLocationsObj() {
        return jsonListOfUserLocationsObj;
    }

    public String getFinalResultInMeters() {
        return finalResultInMeters;
    }

    public String getDurrationOfJourney() {
        return durrationOfJourney;
    }
}
