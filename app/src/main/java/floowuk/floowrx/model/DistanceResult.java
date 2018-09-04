package floowuk.floowrx.model;

import android.support.annotation.NonNull;

/*** Created by igorfrankiv on 06/03/2018. */

public final class DistanceResult {

    private final Double valueResult;
    private final String finalResultInMeters;
    public final Double latitude;
    public final Double longitude;
    public final String time;

    public DistanceResult(Double valueResult, String finalResultInMeters,
                          @NonNull Double latitude, @NonNull Double longitude, @NonNull String time){
        this.valueResult = valueResult;
        this.finalResultInMeters = finalResultInMeters;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public Double getValueResult() {
        return valueResult;
    }

    public String getFinalResultInMeters() {
        return finalResultInMeters;
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
