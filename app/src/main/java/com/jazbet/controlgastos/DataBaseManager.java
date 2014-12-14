package com.jazbet.controlgastos;

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

    public static final String CN_ID = "_id";
    public static final String CN_CONCEPTO = "concepto";
    public static final String CN_CANTIDAD = "cantidad";
    public static final String CN_PLAZOS = "plazos";
    public static final String CN_FECHA = "fecha";

    public static final String CN_MES = "mes";
    public static final String CN_ANIO = "anio";
    public static final String CN_CANTIDAD2 = "cantidad";

    public static final String CN_PLAZOS2 = "plazos";
    public static final String CN_ID_GASTO = "id_gasto";


    public static final String CREATE_TABLE_GASTOS = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement, "
            + CN_CONCEPTO + " text not null, "
            + CN_CANTIDAD + " real not null, "
            + CN_PLAZOS + " integer, "
            + CN_FECHA + " text not null); ";

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
        DbHelper helper = new DbHelper(context);
            db = helper.getWritableDatabase();
    }

    //funciones utileria
    private ContentValues generarValoresGastos(String concepto, float cantidad, int plazos) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        ContentValues values = new ContentValues();
        values.put("ColumnName", strDate);
        ContentValues valores = new ContentValues();
        valores.put(CN_CONCEPTO, concepto);
        valores.put(CN_CANTIDAD, cantidad);
        valores.put(CN_PLAZOS, plazos);
        valores.put(CN_FECHA, strDate);
        return valores;
    }
    private ContentValues generarValoresCapital(int mes, int anio, float cantidad){
        ContentValues valores = new ContentValues();
        valores.put(CN_MES, mes);
        valores.put(CN_ANIO, anio);
        valores.put(CN_CANTIDAD2, cantidad);
        return valores;
    }
    private ContentValues generarValoresPlazos(int id_gasto, int plazos){
        ContentValues valores = new ContentValues();
        valores.put(CN_ID_GASTO, id_gasto);
        valores.put(CN_PLAZOS2, plazos);
        return valores;
    }

    //Insercion
    public void insertarGastos(String concepto, float cantidad, int plazos) throws Exception
    {
            db.insert(TABLE_NAME, null, generarValoresGastos(concepto, cantidad, plazos));
    }
    public void insertarCapital(int mes, int anio, float cantidad)throws SQLiteException
    {
        db.insert(TABLE_NAME, null, generarValoresCapital(mes, anio, cantidad));
    }
    public void insertarPlazos(int id_gasto, int plazos)throws SQLiteException
    {
        db.insert(TABLE_NAME, null, generarValoresPlazos(id_gasto, plazos));
    }

}
