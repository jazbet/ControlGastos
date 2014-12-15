package com.jazbet.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jazbet.database.DbHelperGastos;
import com.jazbet.database.bean.Capital;
import java.sql.SQLException;

/**
 * Created by jazbet on 12/14/14.
 */
public class CapitalDataSource {
    private SQLiteDatabase database;
    private DbHelperGastos DbHelperGastos;
    private String[] allColumns = {DbHelperGastos.CN_ID, DbHelperGastos.CN_MES, DbHelperGastos.CN_ANIO,
            DbHelperGastos.CN_CANTIDAD};
    private String[] oneColumn = {DbHelperGastos.CN_CANTIDAD};


    public CapitalDataSource(Context context){ DbHelperGastos = new DbHelperGastos(context); }

    public void open() throws SQLException { database = DbHelperGastos.getWritableDatabase(); }

    public void close() { DbHelperGastos.close();}

    public long insertaIngreso(long mes, long anio, double cantidad){
        ContentValues valores = new ContentValues();
        valores.put(DbHelperGastos.CN_MES, mes);
        valores.put(DbHelperGastos.CN_ANIO, anio);
        valores.put(DbHelperGastos.CN_CANTIDAD, cantidad);

        long insertId = database.insert(DbHelperGastos.TABLE_CAPITAL, null, valores);
        return insertId;
    }

    //Leemos un registro
    public Capital consultaCapital(long mes, long anio){
        Capital newCapital = new Capital();
                newCapital.setCantidad(Double.parseDouble("-1"));
        if(database.isOpen()) {
            Cursor cursor = database.query(DbHelperGastos.TABLE_CAPITAL, // The table to query
                    oneColumn, // The columns to return
                    DbHelperGastos.CN_MES + " = " + mes + " AND " + DbHelperGastos.CN_ANIO + " = " + anio, // The columns for the WHERE clause
                    null,// The values for the WHERE clause
                    null, // don't group the rows
                    null, // don't filter by row groups
                    null); // The sort order
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                newCapital = cursorToCapitalOne(cursor);
            }
            cursor.close();
        }
        return newCapital;
    }

    private Capital cursorToCapitalAll(Cursor cursor) {
        Capital capital = new Capital();
        capital.setMes(cursor.getLong(0));
        capital.setAnio(cursor.getLong(1));
        capital.setCantidad(cursor.getDouble(2));
        return capital;
    }

    private Capital cursorToCapitalOne(Cursor cursor) {
        Capital capital = new Capital();
        capital.setCantidad(cursor.getDouble(0));
        return capital;
    }

    public void deleteGasto(Capital capital) {
        long mes = capital.getMes();
        long anio = capital.getAnio();
        System.out.println("capital eliminado mes: " + mes + " año:" + anio);
        database.delete(DbHelperGastos.TABLE_CAPITAL, DbHelperGastos.CN_MES
                + " = " + mes + " AND " + DbHelperGastos.CN_ANIO + " = " + anio, null);
    }
}
