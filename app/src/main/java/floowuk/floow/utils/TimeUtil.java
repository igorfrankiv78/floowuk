package floowuk.floow.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/*** Created by igorfrankiv on 27/01/2018. ***/

public final class TimeUtil {

    public static final String PATTERN = "MM/dd/yyyy HH:mm:ss";

    public static final String durationOfJourney (String dateStart, String dateStop) {

        SimpleDateFormat format = new SimpleDateFormat(PATTERN);
            Date d1 = null;
            Date d2 = null;

        StringBuilder durrationOfJourney = new StringBuilder();

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffDays = diff / (24 * 60 * 60 * 1000);
            if(diffDays >0)
                durrationOfJourney. append(String.valueOf(diffDays+"d "));

            long diffHours = diff / (60 * 60 * 1000) % 24;
            if(diffHours >0)
                durrationOfJourney. append(String.valueOf(diffHours+"h "));

            long diffMinutes = diff / (60 * 1000) % 60;
            if(diffMinutes >0)
                durrationOfJourney. append(String.valueOf(diffMinutes+"m "));

            long diffSeconds = diff / 1000 % 60;
            if(diffSeconds >0)
            durrationOfJourney. append(String.valueOf(diffSeconds+"s "));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return durrationOfJourney.toString();
    }
}
