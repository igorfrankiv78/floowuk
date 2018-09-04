package floowuk.floowrx.model;
/*** Created by igorfrankiv on 20/01/2018.*/
public final class UserLocation {

  private final double latitude;
  private final double longitude;
  private final double distance;

  private final String time;

    public UserLocation (double latitude, double longitude, String time, double distance){
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTime() {  return time; }

    public double getDistance() {
        return distance;
    }
}
