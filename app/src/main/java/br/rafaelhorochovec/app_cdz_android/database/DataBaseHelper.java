package br.rafaelhorochovec.app_cdz_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "cavaleiros";

    // Table columns
    public static final String _ID = "_id";
    public static final String NOME = "nome";
    public static final String ARMADURA = "armadura";

    // Database Information
    static final String DB_NAME = "CDZ.DB";

    // DataBase version
    static final int DB_VERSION = 2;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " TEXT NOT NULL, " + ARMADURA + " TEXT);";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
