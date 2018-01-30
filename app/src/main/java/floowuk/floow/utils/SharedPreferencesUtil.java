package floowuk.floow.utils;

import android.content.Context;
import android.content.SharedPreferences;
/*** Created by igorfrankiv on 29/01/2018.*/

public class SharedPreferencesUtil {

    public final static String NAME_OF_SHARE_PREF =  "FloowShere";
    public final static String NAME_OF_BUTTON =  "startbutton";
    public final static String VALUE_ON = "ON";
    public final static String VALUE_OFF = "OFF";

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

}
