package floowuk.floow.services.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import floowuk.floow.model.UserLocation;
import floowuk.floow.model.UserLocations;
import floowuk.floow.utils.JSONUtil;
/*** Created by igorfrankiv on 28/01/2018.*/

public class ServiceModel implements IServiceModel  {
//    The Oracle Declaration is
//    Implementation Notes
//    This lock supports a maximum of 65535 recursive write locks and 65535 read locks.
//    Attempts to exceed these limits result in Error throws from locking methods.
//    https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html
    private ReadWriteLock rwlock = new ReentrantReadWriteLock();

    public static final String JSON_LOCATION_FILE = "jsonlocation.json";
    public static final String DEFAULT_FILE_ENCODING = "UTF-8";
    public static final String READD_ERROR_MESSAGE ="Could not read the file!";
    public static final String WRITE_ERROR_MESSAGE ="Could not write the file!";

    private final Context mContext;

    public ServiceModel(Context context ) {
        this.mContext = context;
    }

    @Override
    public void readIOdata (  @NonNull IServiceOnCompleteModel iServiceOnCompleteModel ){

        List<UserLocation> listOfUserLocations = null;

         if( new File( mContext.getFilesDir(),  JSON_LOCATION_FILE).exists()) {

             final String jsonStr = readJSONFileFromLocalDIR( mContext );

             final UserLocations userLocations =   JSONUtil.readJsonString(jsonStr);
             String isRecorded  = userLocations.getIsRecorded();

             if (isRecorded.contains( ServicePresenter.STATE_OF_RECORDDING_YES )) {
                 listOfUserLocations = userLocations.getListOfUserLocations();
             }
             else
                 listOfUserLocations = new ArrayList<>();
         }
        iServiceOnCompleteModel.readIODataReceived( listOfUserLocations );
    }

    @Override
    public void writeIOData( @NonNull List<UserLocation> listOfUserLocations,  @NonNull IServiceOnCompleteModel iServiceOnCompleteModel, String isRecorded){

        String jsonListOfUserLocationsObj = JSONUtil.createJsonString( listOfUserLocations, isRecorded );
        boolean isWrittenIO = writingJSONFileToLocalDIR( mContext, jsonListOfUserLocationsObj);

      if(isRecorded.contains( ServicePresenter.STATE_OF_RECORDDING_YES ))
        iServiceOnCompleteModel.readyDataForBroadcast ( jsonListOfUserLocationsObj );

      if(!isWrittenIO)
          iServiceOnCompleteModel.showError(WRITE_ERROR_MESSAGE);
    }

    private String readJSONFileFromLocalDIR(Context context)
    {
        rwlock.readLock().lock();

        StringBuilder jsonData = new StringBuilder();
        BufferedReader in = null;
        try
        {
            File file = new File(context.getFilesDir(), JSON_LOCATION_FILE);
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), DEFAULT_FILE_ENCODING));
            String line;
            while ((line = in.readLine()) != null)
            {jsonData.append(line);}
        }catch (Exception e)     { e.printStackTrace(); }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                return READD_ERROR_MESSAGE;
            }
            finally {

             rwlock.readLock().unlock();

            }
        }
        return jsonData.toString();
    }

    private  boolean writingJSONFileToLocalDIR( Context context, String jsonObj) {

        rwlock.writeLock().lock();

        try{ FileOutputStream output = context.openFileOutput(JSON_LOCATION_FILE, Context.MODE_PRIVATE);//
            OutputStreamWriter writer = new OutputStreamWriter(output);
            writer.write(jsonObj);
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            return false;
        }
        finally {

        rwlock.writeLock().unlock();

        }
        return true;
    }
}