package com.jazbet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jazbet on 12/14/14.
 */
public class DbHelperCapital extends SQLiteOpenHelper {

    private static final String DB_NAME = "control_gastos.db";
    private static final int DB_SCHEME_VERSION = 1;

    public static final String TABLE_CAPITAL = "capital";
    public static final String CN_ID = "_id";
    public static final String CN_MES = "mes";
    public static final String CN_ANIO = "anio";
    public static final String CN_CANTIDAD = "cantidad";

    public static final String CREATE_TABLE_CAPITAL = "create table " + TABLE_CAPITAL + " ("
            + CN_ID + " integer primary key autoincrement, "
            + CN_MES + " integer, "
            + CN_ANIO + " integer, "
            + CN_CANTIDAD + " real); ";


    public DbHelperCapital(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CAPITAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelperCapital.class.getName(),
                "La actualización de la Base de Datos de la versión " + oldVersion + " a la versión "
                        + newVersion + ", eliminará todos los datos");
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }
}
