package floowuk.floowrx.model;

import android.support.annotation.NonNull;

/*** Created by igorfrankiv on 05/03/2018.*/

public class LocationData {

    public final Double latitude;
    public final Double longitude;
    public final String time;

    public LocationData(@NonNull Double latitude, @NonNull Double longitude, @NonNull String time){

        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getTime() {
        return time;
    }
}


