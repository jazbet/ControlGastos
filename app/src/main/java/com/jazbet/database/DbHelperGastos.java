package com.jazbet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jazbet on 12/8/14.
 */
public class DbHelperGastos extends SQLiteOpenHelper {

    private static final String DB_NAME = "control_gastos2.db";
    private static final int DB_SCHEME_VERSION = 1;

    public static final String TABLE_NAME = "gastos";
    public static final String CN_ID = "_id";
    public static final String CN_CONCEPTO = "concepto";
    public static final String CN_CANTIDAD = "cantidad";
    public static final String CN_PLAZOS = "plazos";
    public static final String CN_FECHA = "fecha";

    public static final String CREATE_TABLE_GASTOS = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement, "
            + CN_CONCEPTO + " text not null, "
            + CN_CANTIDAD + " real not null, "
            + CN_PLAZOS + " integer, "
            + CN_FECHA + " text not null); ";

    public static final String TABLE_CAPITAL = "capital";
    public static final String CN_MES = "mes";
    public static final String CN_ANIO = "anio";
    //public static final String CN_CANTIDAD2 = "cantidad";

    public static final String CREATE_TABLE_CAPITAL = "create table " + TABLE_CAPITAL + " ("
            + CN_MES + " integer not null, "
            + CN_ANIO + " integer not null, "
            + CN_CANTIDAD + " real," +
            " PRIMARY KEY (" + CN_MES + ", " + CN_ANIO + ")); ";


    public DbHelperGastos(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(DbHelperGastos.class.getName(),"Capital table = " + CREATE_TABLE_CAPITAL);
        db.execSQL(CREATE_TABLE_CAPITAL);
        Log.w(DbHelperGastos.class.getName(),"Capital table creada!!");
        db.execSQL(CREATE_TABLE_GASTOS);
        //db.execSQL(DataBaseManager.CREATE_TABLE_PLAZOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelperGastos.class.getName(),
                "La actualización de la Base de Datos de la versión " + oldVersion + " a la versión "
                        + newVersion + ", eliminará todos los datos");
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

}
