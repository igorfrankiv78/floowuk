package floowuk.floow.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import floowuk.floow.model.LastRecord;
import floowuk.floow.model.UserLocationsDB;
/*** Created by igorfrankiv on 21/01/2018.*/

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DataBaseOfUserLocations.db";
    public static final String LIST_TABLE_NAME = "tablelistLocations";
    public static final String  LIST_US_LC_COLUMN_ID= "id";
    public static final String LIST_US_LC_COLUMN_LIST_US_LOC = "listOfUserLocations";
    public static final String LIST_US_LC_COLUMN_TIME = "time";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table tablelistLocations " +
                          "(id integer primary key, listOfUserLocations text,time text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS tablelistLocations");
        onCreate(db);
    }

    public boolean writeJourneyInDB (String listOfUserLocations, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("listOfUserLocations", listOfUserLocations);
        contentValues.put("time", time);
        db.insert("tablelistLocations", null, contentValues);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, LIST_TABLE_NAME);
        return numRows;
    }

    public Integer deleteUserJourney(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tablelistLocations",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllOfTheJourneys() {
           ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tablelistLocations", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(LIST_US_LC_COLUMN_LIST_US_LOC)));
            res.moveToNext();
        }
        return array_list;
    }

    public UserLocationsDB getAllJourneys() {
        List<String> listOfIds = new ArrayList<String>();
        List<String> listOflocations = new ArrayList<String>();
        List<String> listOfTimeStamps = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tablelistLocations", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            listOfIds.add( String.valueOf( res.getInt(res.getColumnIndex(LIST_US_LC_COLUMN_ID ))));
            listOflocations.add(res.getString(res.getColumnIndex(LIST_US_LC_COLUMN_LIST_US_LOC)));
            listOfTimeStamps.add(res.getString(res.getColumnIndex(LIST_US_LC_COLUMN_TIME)));
            res.moveToNext();
        }
        return new UserLocationsDB( listOfIds,  listOflocations, listOfTimeStamps );
    }

    public String getDetailedRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tablelistLocations where id="+id, null );
        if (res != null)
        res.moveToFirst();
        String str = res.getString(res.getColumnIndex(LIST_US_LC_COLUMN_LIST_US_LOC));
        return str;
    }

    public LastRecord getLastRecordOfListLocations( ) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tablelistLocations WHERE ID = (SELECT MAX(ID) FROM tablelistLocations)", null);
        String listOfLocations = "";
        int id = 0;
        if (cursor.moveToFirst()) {
            listOfLocations = cursor.getString(cursor.getColumnIndex(LIST_US_LC_COLUMN_LIST_US_LOC));
            id = cursor.getInt(cursor.getColumnIndex(LIST_US_LC_COLUMN_ID));
        }
        cursor.close();

        return new LastRecord( id, listOfLocations);
    }

    public boolean updateRecord (Integer id, String listOfUserLocations, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("listOfUserLocations", listOfUserLocations);
        contentValues.put("time", time);
        db.update("tablelistLocations", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

}