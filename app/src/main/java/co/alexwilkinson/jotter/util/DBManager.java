package co.alexwilkinson.jotter.util;

import android.database.sqlite.SQLiteDatabase;

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
    public static final String jotterTitle = "jotterTitle";
    public static final String jotterBody = "jotterBody";
    public static final String jotterDate = "jotterDate";


}
