package floowuk.zendeskigorlibrary.tickets.util;

import android.support.annotation.NonNull;

/*** Created by igor on 26/08/2017.*/

public final class UtilReduceStr {

    public static String reduceStr(@NonNull String str, @NonNull Integer numBiggerThan, @NonNull Integer sizeOfStr ) {
        String strToBeReturned;

        if (str.length() > numBiggerThan)
            strToBeReturned = str.substring(0, sizeOfStr) + "...";
        else
            strToBeReturned = str;

        return strToBeReturned;
    }

}
