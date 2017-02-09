package co.alexwilkinson.jotter.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

/**
 * Created by Spreetrip on 2/9/2017.
 */

public class DBManager {
    private SQLiteDatabase sqlDB;
    public static final String dbName = "jotter";
    public static final int dbVersion = 1;

    //USER TABLE
    public static final String tableUsers = "jotterUsers";
    //USER COLUMNS
    public static final String userColUsername = "username";
    public static final String userColPages = "totalPages";

    //MAIN TABLE
    public static final String tableJotter = "jotterMain";
    //MAIN COLUMNS
    public static final String jotterColTitle = "jotterColTitle";
    public static final String jotterColBody = "jotterColBody";
    public static final String jotterColPage = "jotterColPage";
    public static final String jotterColDate = "jotterColDate";
    public static final String jotterColUsername = "username";

    //BUILD USER TABLE
    protected static final String buildUserTable =
            "CREATE TABLE IF NOT EXISTS " + tableUsers
                    +"("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + userColUsername + " TEXT ,"
                    + userColPages + " INT"
                    + ");";

    protected static final String buildJotterTable =
            "CREATE TABLE IF NOT EXISTS " + tableJotter
                    +"("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + jotterColTitle + " TEXT ,"
                    + jotterColBody + " TEXT ,"
                    + jotterColPage + " INT ,"
                    + jotterColDate + " TEXT "
                    + jotterColUsername + " TEXT "
                    + ");";

    public DBManager(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        sqlDB = db.getWritableDatabase();
    }

    public Cursor queryUserTable(String[]columns, String where, String[]whereArgs, String sortOrder){

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(tableUsers);

        Cursor cursor = qb.query(sqlDB, columns, where, whereArgs, null, null, sortOrder);

        return cursor;
    }

    public Cursor queryJotterTable(String[]columns, String where, String[]whereArgs, String sortOrder){

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(tableJotter);

        Cursor cursor = qb.query(sqlDB, columns, where, whereArgs, null, null, sortOrder);

        return cursor;
    }

    public boolean doesUserExist(String username){
        Cursor cursor = queryUserTable(null,userColUsername,new String[]{username},null);
        String user = "";
        user = cursor.getString(cursor.getColumnIndex(DBManager.userColUsername));

        if(user.equalsIgnoreCase(username)){
            return true;
        }

        return false;
    }

    public ArrayList userData(String username){
        ArrayList<UserDataObject> userData = new ArrayList<>();

        Cursor cursor = queryJotterTable(null,jotterColUsername,new String[]{username},null);

        if(cursor.moveToFirst()){
            do{
                userData.add(new UserDataObject(
                        cursor.getString(cursor.getColumnIndex(DBManager.jotterColUsername)),
                        cursor.getString(cursor.getColumnIndex(DBManager.jotterColTitle)),
                        cursor.getString(cursor.getColumnIndex(DBManager.jotterColBody)),
                        cursor.getString(cursor.getColumnIndex(DBManager.jotterColDate)),
                        cursor.getString(cursor.getColumnIndex(DBManager.jotterColPage))
                ));
            }while (cursor.moveToNext());
        }

        return userData;
    }



    public static class DatabaseHelper extends SQLiteOpenHelper{
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, dbName, null, dbVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(buildUserTable);
            db.execSQL(buildJotterTable);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }
    }


}
