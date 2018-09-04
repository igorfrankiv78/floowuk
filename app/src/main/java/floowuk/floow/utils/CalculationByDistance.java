package floowuk.floow.utils;

import android.util.Log;
import java.text.DecimalFormat;
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

}
