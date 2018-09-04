package floowuk.floowrx.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import floowuk.floowrx.model.UserLocations;
import floowuk.floowrx.services.mvp.ServiceModel;
import floowuk.floowrx.services.mvp.ServicePresenter;
import floowuk.floowrx.utils.JSONUtil;

/*** Created by igorfrankiv on 27/01/2018.*/

// Check from JSON OBJECT which is saved locally if it was reading recording during SHUTDOWN OR RESTARTED
// If yes, it is going to restart the service
public class FloowServiceLocatorWakeUp  extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        onBootCompleteWakeUp(intent, context);
    }

    public void onBootCompleteWakeUp(Intent intent, Context context) {

        String getAction = String.valueOf(intent.getAction());
        if (getAction.equals("android.intent.action.BOOT_COMPLETED")) {
            try {

                if( new File( context.getFilesDir(),  ServiceModel.JSON_LOCATION_FILE).exists()) {

                    final String jsonStr = readJSONFileFromLocalDIR( context );

                    final UserLocations userLocations =   JSONUtil.readJsonString(jsonStr);
                    String isRecorded  = userLocations.getIsRecorded();

                    if (isRecorded.contains( ServicePresenter.STATE_OF_RECORDDING_YES )) {
                        Intent intent23 = new Intent(context.getApplicationContext(), FloowServiceLocator.class);
                        context.startService(intent23);
                    }
                }
            }
            catch (Exception e)
            {
            }
        }
    }

    private String readJSONFileFromLocalDIR(Context context)
    {
        StringBuilder jsonData = new StringBuilder();
        BufferedReader in = null;
        try
        {
            File file = new File(context.getFilesDir(), ServiceModel.JSON_LOCATION_FILE);
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), ServiceModel.DEFAULT_FILE_ENCODING));
            String line;
            while ((line = in.readLine()) != null)
            {jsonData.append(line);}
        }catch (Exception e)     { e.printStackTrace(); }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                return ServiceModel.READD_ERROR_MESSAGE;
            }
            finally {

            }
        }
        return jsonData.toString();
    }
}