package floowuk.floowrx.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import floowuk.floowrx.utils.JSONUtil;
import floowuk.floowrx.model.LastRecord;
import floowuk.floowrx.model.UserLocations;
import floowuk.floowrx.model.UserLocationsDB;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import rx.Observable;
import rx.Subscriber;

public class DBHelper extends SQLiteOpenHelper {

      public static final String DATABASE_NAME = "DataBaseOfUserLocations.db";
      public static final String TABLE_NAME_LIST_OF_USER_JOURNEYS = "tablelistLocations";
      public static final String LIST_US_LC_COLUMN_ID= "id";
      public static final String COLUMN_LIST_OF_USER_LOCATIONS = "listOfUserLocations";
      public static final String COLUMN_TIME = "time";

   public DBHelper(Context context) {
      super(context, DATABASE_NAME , null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL(
         "create table tablelistLocations " +
          "(id integer primary key, "+COLUMN_LIST_OF_USER_LOCATIONS+" text,"+COLUMN_TIME+" text)"
      );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LIST_OF_USER_JOURNEYS);
      onCreate(db);
   }
// ---------------------------------------------------------------------------------
   public Boolean writeJourneyInDB  (String listOfUserLocations, String time) {
       SQLiteDatabase db = this.getWritableDatabase();
               ContentValues contentValues = new ContentValues();
               contentValues.put(COLUMN_LIST_OF_USER_LOCATIONS, listOfUserLocations);
               contentValues.put(COLUMN_TIME, time);
              db.insert(TABLE_NAME_LIST_OF_USER_JOURNEYS, null, contentValues);
               return true;
   }

  public Integer getAllOfTheJourneys() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME_LIST_OF_USER_JOURNEYS, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_LIST_OF_USER_LOCATIONS)));
            res.moveToNext();
        }

        return  array_list.size();
  }

// OBS
  public Observable<UserLocations> getDetailedRecord(int id) {
           SQLiteDatabase db = this.getReadableDatabase();
           Cursor res =  db.rawQuery( "select * from "+TABLE_NAME_LIST_OF_USER_JOURNEYS+" where id="+id, null );
           if (res != null)
               res.moveToFirst();
           String str = res.getString(res.getColumnIndex(COLUMN_LIST_OF_USER_LOCATIONS));

      return Observable.just( JSONUtil.readJsonString( str  ) );
   }

// OBS
  public Observable<Integer> deleteUserJourney(Integer id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return Observable.just(db.delete(TABLE_NAME_LIST_OF_USER_JOURNEYS,
                    "id = ? ",
                    new String[] { Integer.toString(id) }) );
        }

// OBS
    public Observable<List <UserLocationsDB>> getAllJourneys( ) {
        return Observable.create(new Observable.OnSubscribe<List <UserLocationsDB>>() {
            @Override
            public void call(Subscriber<? super List <UserLocationsDB>> lineSubscriber) {

                List <UserLocationsDB> allJourneys =  new ArrayList<>();

                SQLiteDatabase db = getReadableDatabase();
                Cursor res =  db.rawQuery( "select * from "+TABLE_NAME_LIST_OF_USER_JOURNEYS, null );
                    if (res == null) {
                        lineSubscriber.onError(new IllegalArgumentException( "Data not found in the database: " ));
                    return;
                }
                res.moveToFirst();

                while(res.isAfterLast() == false){
                    allJourneys.add(new UserLocationsDB( String.valueOf( res.getInt(res.getColumnIndex(LIST_US_LC_COLUMN_ID ))),
                            res.getString(res.getColumnIndex(COLUMN_LIST_OF_USER_LOCATIONS)),
                            res.getString(res.getColumnIndex(COLUMN_TIME)) ));
                    lineSubscriber.onNext(allJourneys);
                    res.moveToNext();
                }
                    // no more lines to read, we can complete our subscriber.
                    lineSubscriber.onCompleted();

            }
        });
    }
  // -----------------------------------------------------------------------------------
  // -------------- This methods bellow are not used -----------------------------------
       public boolean updateRecord (Integer id, String listOfUserLocations, String time) {
           SQLiteDatabase db = this.getWritableDatabase();
           ContentValues contentValues = new ContentValues();
           contentValues.put(COLUMN_LIST_OF_USER_LOCATIONS, listOfUserLocations);
           contentValues.put(COLUMN_TIME, time);
           db.update(TABLE_NAME_LIST_OF_USER_JOURNEYS, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
           return true;
        }

       public LastRecord getLastRecordOfListLocations( ) {
           SQLiteDatabase db = this.getWritableDatabase();

           Cursor cursor = db.rawQuery("SELECT * FROM tablelistLocations WHERE ID = (SELECT MAX(ID) FROM tablelistLocations)", null);
           String listOfLocations = "";
           int id = 0;
           if (cursor.moveToFirst()) {
               listOfLocations = cursor.getString(cursor.getColumnIndex(COLUMN_LIST_OF_USER_LOCATIONS));
               id = cursor.getInt(cursor.getColumnIndex(LIST_US_LC_COLUMN_ID));
           }
           cursor.close();

           return new LastRecord( id, listOfLocations);
       }

}



























