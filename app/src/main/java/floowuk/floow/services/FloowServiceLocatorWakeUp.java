package floowuk.floow.services;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;
/*** Created by igorfrankiv on 27/01/2018.*/

// I DON'T USE IT YET!!!
// Check from JSON OBJECT which is saved locally if it was reading recording during SHUTDOWN OR RESTARTED
public class FloowServiceLocatorWakeUp  extends WakefulBroadcastReceiver {

    public static final int MINUTES = 60000 * 240;// 60000 * 1 = 1 minute

    @Override
    public void onReceive(Context context, Intent intent) {
        onBootCompleteWakeUp(intent, context);
    }

    public void onBootCompleteWakeUp(Intent intent, Context context) {
        String getAction = String.valueOf(intent.getAction());

        if (getAction.equals("android.intent.action.BOOT_COMPLETED")) {
            try {
                Intent intent2 = new Intent(context, FloowServiceLocatorWakeUp.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                        12345, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager) context.getSystemService(Activity.ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(),
                        MINUTES, pendingIntent);
// Check from JSON OBJECT which is saved locally if it was reading recording during SHUTDOWN OR RESTARTED
                Intent serviceIntent = new Intent(context, FloowServiceLocatorWakeUp.class);
                startWakefulService(context, serviceIntent);
            } catch (Exception e) {
            }
        }
    }
}