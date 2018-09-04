package floowuk.floowrx.utils;

import floowuk.floowrx.model.DeleteItemLocationsDB;
/*** Created by igorfrankiv on 13/03/2018.*/

public final class FloowUtil {

    public static final int getPositionToBeRemoveD(DeleteItemLocationsDB deleteItemLocationsDB ){

        int positionToBeRemoved = Integer.valueOf(deleteItemLocationsDB.getIdRemoved());
        int positionToBeRemovedFromList = 0;

        for (int i = 0; i < deleteItemLocationsDB.getUserLocationsDB().size(); i++) {
            positionToBeRemovedFromList = i;

            if (positionToBeRemoved == Integer.valueOf(deleteItemLocationsDB.getUserLocationsDB().get(i).getId()))
                break;
        }

        return positionToBeRemovedFromList;
    }
}
