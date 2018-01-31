package floowuk.floow.model;

/*** Created by igorfrankiv on 31/01/2018.*/

public class LastKnownResults {

    private String time;
    private String distance;

    public LastKnownResults( String time, String distance){
        this.time = time;
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public String getDistance() {
        return distance;
    }
}
