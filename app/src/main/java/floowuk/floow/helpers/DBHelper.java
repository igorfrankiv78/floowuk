package floowuk.floow.helpers;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import floowuk.floow.model.LastRecord;
import floowuk.floow.model.UserLocationsDB;

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
      // TODO Auto-generated method stub
      db.execSQL(
         "create table tablelistLocations " +
          "(id integer primary key, "+COLUMN_LIST_OF_USER_LOCATIONS+" text,"+COLUMN_TIME+" text)"
      );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LIST_OF_USER_JOURNEYS);
      onCreate(db);
   }
   // ---------------------------------------------------------------------------------
   public boolean writeJourneyInDB  (String listOfUserLocations, String time) {
       SQLiteDatabase db = this.getWritableDatabase();
               ContentValues contentValues = new ContentValues();
               contentValues.put(COLUMN_LIST_OF_USER_LOCATIONS, listOfUserLocations);
               contentValues.put(COLUMN_TIME, time);
               db.insert(TABLE_NAME_LIST_OF_USER_JOURNEYS, null, contentValues);
               return true;
   }

  public ArrayList<String> getAllOfTheJourneys() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME_LIST_OF_USER_JOURNEYS, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_LIST_OF_USER_LOCATIONS)));
            res.moveToNext();
        }
        return array_list;
  }

  public String getDetailedRecord(int id) {
           SQLiteDatabase db = this.getReadableDatabase();
           Cursor res =  db.rawQuery( "select * from "+TABLE_NAME_LIST_OF_USER_JOURNEYS+" where id="+id, null );
           if (res != null)
               res.moveToFirst();
           String str = res.getString(res.getColumnIndex(COLUMN_LIST_OF_USER_LOCATIONS));
           return str;
   }

  public Integer deleteUserJourney(Integer id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME_LIST_OF_USER_JOURNEYS,
                    "id = ? ",
                    new String[] { Integer.toString(id) });
        }

   public UserLocationsDB getAllJourneys() {
           List<String> listOfIds = new ArrayList<String>();
           List<String> listOflocations = new ArrayList<String>();
           List<String> listOfTimeStamps = new ArrayList<String>();
   
           SQLiteDatabase db = this.getReadableDatabase();
           Cursor res =  db.rawQuery( "select * from "+TABLE_NAME_LIST_OF_USER_JOURNEYS, null );
           res.moveToFirst();
   
           while(res.isAfterLast() == false){
               listOfIds.add( String.valueOf( res.getInt(res.getColumnIndex(LIST_US_LC_COLUMN_ID ))));
               listOflocations.add(res.getString(res.getColumnIndex(COLUMN_LIST_OF_USER_LOCATIONS)));
               listOfTimeStamps.add(res.getString(res.getColumnIndex(COLUMN_TIME)));
               res.moveToNext();
           }
           return new UserLocationsDB( listOfIds,  listOflocations, listOfTimeStamps );
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



























