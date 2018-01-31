package floowuk.floow.utils;

import android.content.Context;
import android.content.SharedPreferences;
import floowuk.floow.model.LastKnownResults;
/*** Created by igorfrankiv on 29/01/2018.*/

public class SharedPreferencesUtil {

    public final static String NAME_OF_SHARE_PREF =  "FloowShere";
    public final static String NAME_OF_BUTTON =  "startbutton";
    public final static String VALUE_ON = "ON";
    public final static String VALUE_OFF = "OFF";
    public final static String TIME = "time";
    public final static String DISTANCE = "distance";

    public static Boolean isStartButtonTurnOn(Context context){
      Boolean isStarted = false;
        SharedPreferences sharedpreferences = context.getSharedPreferences(NAME_OF_SHARE_PREF, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(NAME_OF_BUTTON)) {
            if( sharedpreferences.getString(NAME_OF_BUTTON, "").contains(VALUE_ON) )
                isStarted = true;
        }
      return isStarted;
    }

    public static void setStartButtonOn( Context context ){
        SharedPreferences pref = context .getSharedPreferences(NAME_OF_SHARE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(NAME_OF_BUTTON, VALUE_ON);
        editor.commit();
    }

    public static void setStartButtonOff( Context context ){
        SharedPreferences pref = context .getSharedPreferences(NAME_OF_SHARE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(NAME_OF_BUTTON, VALUE_OFF);
        editor.commit();
    }

    public static void setLastKnownResults( Context context, String timeStr, String distanceStr ){
        SharedPreferences pref = context .getSharedPreferences(NAME_OF_SHARE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TIME, timeStr);
        editor.putString(DISTANCE, distanceStr);
        editor.commit();
    }

    public static LastKnownResults getLastKnownResults(Context context ){
        SharedPreferences sharedpreferences = context.getSharedPreferences(NAME_OF_SHARE_PREF, Context.MODE_PRIVATE);

        String time = "0";
        String distance = "0";

        if (sharedpreferences.contains(TIME))
            time =  sharedpreferences.getString(TIME, "");

        if (sharedpreferences.contains(DISTANCE))
            distance =  sharedpreferences.getString(DISTANCE, "");

        return new LastKnownResults( time, distance);
    }
}
