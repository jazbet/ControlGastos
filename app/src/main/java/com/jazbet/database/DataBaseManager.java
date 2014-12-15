package com.jazbet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jazbet on 12/8/14.
 */
public class DataBaseManager {

    public static final String TABLE_NAME = "money_data";

    public static final String CN_MES = "mes";
    public static final String CN_ANIO = "anio";
    public static final String CN_CANTIDAD2 = "cantidad";

    public static final String CN_PLAZOS2 = "plazos";
    public static final String CN_ID_GASTO = "id_gasto";



    public static final String CREATE_TABLE_CAPITAL = "create table " + TABLE_NAME + " ("
            + CN_MES + " integer, "
            + CN_ANIO + " integer, "
            + CN_CANTIDAD2 + " real); ";

    public static final String CREATE_TABLE_PLAZOS = "create table " + TABLE_NAME + " ("
            + CN_ID_GASTO + " integer, "
            + CN_PLAZOS2 + " integer); ";


    private SQLiteDatabase db;

    //Constructor
    public DataBaseManager(Context context) throws SQLiteException {
        DbHelperGastos helper = new DbHelperGastos(context);
            db = helper.getWritableDatabase();
    }

    //funciones utileria
    private ContentValues generarValoresCapital(long mes, long anio, double cantidad){
        ContentValues valores = new ContentValues();
        valores.put(CN_MES, mes);
        valores.put(CN_ANIO, anio);
        valores.put(CN_CANTIDAD2, cantidad);
        return valores;
    }
    private ContentValues generarValoresPlazos(long id_gasto, long plazos){
        ContentValues valores = new ContentValues();
        valores.put(CN_ID_GASTO, id_gasto);
        valores.put(CN_PLAZOS2, plazos);
        return valores;
    }

    //Insercion
    public void insertarCapital(long mes, long anio, double cantidad)throws SQLiteException
    {
        db.insert(TABLE_NAME, null, generarValoresCapital(mes, anio, cantidad));
    }
    public void insertarPlazos(long id_gasto, long plazos)throws SQLiteException
    {
        db.insert(TABLE_NAME, null, generarValoresPlazos(id_gasto, plazos));
    }

}
