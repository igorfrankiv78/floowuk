package flowigor.ie.floowuk.zendesk.util;

import android.support.annotation.NonNull;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import floowuk.zendeskigorlibrary.model.Tickets;

/*** Created by igor on 20/08/2017.*/

public final class NetworkUtil {

    private static final String MAIN_JSON_OBJ = "tickets";
    private static final String MAIN_JSON_ARRAY_OBJ = "tickets";
    private static final String ID = "id";
    private static final String SUBJECT = "subject";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";

    private static String getJSONStr(@NonNull JSONObject result, @NonNull String whichJSONObj) throws JSONException {
        String strToBeReturn = "";
        if (result.has(whichJSONObj))
            strToBeReturn = result.getString(whichJSONObj);
        return  strToBeReturn;
    }

    private static Integer getJSONInt(@NonNull JSONObject result, @NonNull String whichJSONObj) throws JSONException {
        Integer intToBeReturn = 0;
        if (result.has(whichJSONObj))
            intToBeReturn = result.getInt(whichJSONObj);
        return  intToBeReturn;
    }

    public static String getAuthStr(@NonNull String userName, @NonNull String password){
        String authStr = null;
        if( userName != null && password != null) {
            String authString = userName + ":" + password;
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            authStr = new String(authEncBytes);
        }
        return authStr;
    }

    public static URLConnection sendGetRequest(@NonNull String urlStr, @NonNull String authStr) {
        URLConnection urlConnection = null;
        if( urlStr != null && authStr != null) {
            try {
                URL url = new URL(urlStr);
                urlConnection = url.openConnection();
                urlConnection.setRequestProperty("Authorization", "Basic " + authStr);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return urlConnection;
    }

    public static String readMultipleLinesRespone(@NonNull URLConnection urlConnection )
    {
        String multipleLinesStr = "";
        if( urlConnection != null ) {
            try {
                InputStream is = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int numCharsRead;
                char[] charArray = new char[1024];
                StringBuffer sb = new StringBuffer();
                while ((numCharsRead = isr.read(charArray)) > 0) {
                    sb.append(charArray, 0, numCharsRead);

                }
                multipleLinesStr = sb.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return multipleLinesStr;
    }

    public static List<Tickets> readJSONFile(@NonNull String jSONStr){
        List<Tickets> tickets = new ArrayList<>();

        if( jSONStr != null ) {
            try {
                JSONObject rv = new JSONObject(jSONStr);

                if (!rv.isNull(MAIN_JSON_OBJ)) {
                    JSONArray results;
                    if ((results = rv.getJSONArray(MAIN_JSON_ARRAY_OBJ)) != null && results.length() > 0) {
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject result = results.getJSONObject(i);
                            tickets.add(new Tickets(getJSONInt(result, ID), getJSONStr(result, SUBJECT ), getJSONStr(result, DESCRIPTION), getJSONStr(result, STATUS)));
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return tickets;
    }
}