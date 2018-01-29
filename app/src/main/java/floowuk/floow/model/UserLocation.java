package floowuk.floow.model;
/*** Created by igorfrankiv on 20/01/2018.*/
public final class UserLocation {

  private final double latitude;
  private final double longitude;
  private final String time;

    public UserLocation (double latitude, double longitude, String time){
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTime() {  return time; }
}
