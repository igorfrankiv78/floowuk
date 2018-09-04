package floowuk.floowrx.utils;

import java.util.List;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONException;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.model.UserLocations;
/*** Created by igorfrankiv on 23/01/2018.*/

public final class JSONUtil {

    public final static String createJsonString(List<UserLocation> listOfUserLocations, String isRecorded){
        String stringJson ="";
        try {

            JSONArray arr = new JSONArray();
            for (UserLocation s : listOfUserLocations) {
                JSONObject key = new JSONObject();
                key.put("latitudes", s.getLatitude());
                key.put("longitude", s.getLongitude());
                key.put("time", s.getTime());
                key.put("distance", s.getDistance());
                arr.put(key);
            }
            JSONObject json = new JSONObject();
            json.put("isRecorded", isRecorded);
            json.put("listofuserlocations", arr);

             stringJson = json.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
      return stringJson;
    }

    // make it an Observable method later
    public final static UserLocations readJsonString(String jsonObj){
        List<UserLocation> listOfUserLocations = new ArrayList<>();
        String isRecorded = "NO";
        try {
            if ( jsonObj != null) {
                JSONObject jsonObject = new JSONObject(jsonObj);

                if((jsonObject.has("isRecorded")) && (jsonObject.getString("isRecorded"))!=null)
                    isRecorded = jsonObject.getString("isRecorded");
                Log.e("isRecorded ", String.valueOf( isRecorded));
                JSONArray listofuserlocations;
                if ((jsonObject.has("listofuserlocations")) &&
                        (listofuserlocations = jsonObject.getJSONArray("listofuserlocations")) != null &&
                        listofuserlocations.length() > 0)
                    for (int i = 0; i < listofuserlocations.length(); i++) {
                        JSONObject listofuserlocationsObj = listofuserlocations.getJSONObject(i);
                        listOfUserLocations.add(new UserLocation(
                                listofuserlocationsObj.getDouble("latitudes"),
                                listofuserlocationsObj.getDouble("longitude"),
                                listofuserlocationsObj.getString("time"),
                                listofuserlocationsObj.getDouble("distance")
                        ));
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       return new UserLocations( listOfUserLocations,  isRecorded);
    }

}
