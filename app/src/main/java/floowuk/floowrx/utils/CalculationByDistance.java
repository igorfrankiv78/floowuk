package floowuk.floowrx.utils;

import android.util.Log;
import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import java.text.DecimalFormat;
import floowuk.floowrx.model.DistanceResult;
import floowuk.floowrx.model.LocationRXData;
/*** Created by igorfrankiv on 10/02/2018.*/

public final class CalculationByDistance {

    public static final double calculationByDistance
             (double startPlatitude, double startPlongitude,
              double endPlatitude, double endPlongitude) {
        int Radius = 6371;// radius of earth in Km

        double lat1 = startPlatitude;
        double lat2 = endPlatitude;
        double lon1 = startPlongitude;
        double lon2 = endPlongitude;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(Math.toRadians(lat1))
                 * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                 * Math.sin(dLon / 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        return Radius * c;
    }

    public static final int calculateDistanceInMeters
            (double valueResult){

        double meter = valueResult % 1000;
        Log.e("meter = : " , String.valueOf( meter ));
        DecimalFormat newFormat = new DecimalFormat("####");
        int meterInDec = Integer.valueOf(newFormat.format(meter));

         return meterInDec;
    }

    public static final int calculateDistanceInKM
            (double valueResult){

        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));

        return kmInDec;
    }


    public static final DistanceResult calculateDistanceBetween2points( LocationRXData locationRXData ){

        double mValueResult = 0;
        String mFinalResultInMeters = "0"; // locationRXData.getLocationData() ;

        if(locationRXData.getListOfUserLocations() != null && locationRXData.getListOfUserLocations().size() > 1 ) {
            double startPlatitude = locationRXData.getListOfUserLocations().
                    get(locationRXData.getListOfUserLocations().size() - 1).getLatitude();

            System.out.println( "startPlatitude = : "+ startPlatitude );

            double startPlongitude = locationRXData.getListOfUserLocations().
                    get(locationRXData.getListOfUserLocations().size() - 1).getLongitude();

            System.out.println( "startPlongitude = : "+ startPlongitude );

            double endPlatitude = locationRXData.getLocationData().getLatitude();

            System.out.println( "endPlatitude = : "+ endPlatitude );

            double endPlongitude = locationRXData.getLocationData().getLongitude();

            System.out.println( "endPlongitude = : "+ endPlongitude );

            LatLng latLngA = new LatLng(startPlatitude, startPlongitude);
            LatLng latLngB = new LatLng(endPlatitude, endPlongitude);

            Location locationA = new Location("point A");
            locationA.setLatitude(latLngA.latitude);
            locationA.setLongitude(latLngA.longitude);
            Location locationB = new Location("point B");
            locationB.setLatitude(latLngB.latitude);
            locationB.setLongitude(latLngB.longitude);

            double valueResult = locationA.distanceTo(locationB);
            mValueResult = valueResult;

            mValueResult = locationRXData.getListOfUserLocations().get(locationRXData.getListOfUserLocations().size() - 1).getDistance() + mValueResult;

            System.out.println( "mValueResult = : "+ endPlongitude );

            mFinalResultInMeters = String.valueOf( mValueResult );
            String[] strTemp =  mFinalResultInMeters.split(".") ;
            if(strTemp.length > 0)
                mFinalResultInMeters =  strTemp[0];
        }

        return new floowuk.floowrx.model.DistanceResult( mValueResult, mFinalResultInMeters,
                locationRXData.getLocationData().getLatitude(), locationRXData.getLocationData().getLongitude(),
                locationRXData.getLocationData().getTime());
    }


}
