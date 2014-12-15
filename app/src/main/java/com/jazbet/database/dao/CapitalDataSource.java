package com.jazbet.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jazbet.database.DbHelperCapital;
import com.jazbet.database.bean.Capital;
import java.sql.SQLException;

/**
 * Created by jazbet on 12/14/14.
 */
public class CapitalDataSource {
    private SQLiteDatabase database;
    private DbHelperCapital dbHelperCapital;
    private String[] allColumns = {DbHelperCapital.CN_ID, DbHelperCapital.CN_MES, DbHelperCapital.CN_ANIO,
            DbHelperCapital.CN_CANTIDAD};
    private String[] oneColumn = {DbHelperCapital.CN_CANTIDAD};

    public CapitalDataSource(Context context){ dbHelperCapital = new DbHelperCapital(context); }

    public void open() throws SQLException { database = dbHelperCapital.getWritableDatabase(); }

    public void close() { dbHelperCapital.close();}

    public long insertaIngreso(long mes, long anio, double cantidad){
        ContentValues valores = new ContentValues();
        valores.put(dbHelperCapital.CN_MES, mes);
        valores.put(dbHelperCapital.CN_ANIO, anio);
        valores.put(dbHelperCapital.CN_CANTIDAD, cantidad);

        long insertId = database.insert(dbHelperCapital.TABLE_CAPITAL, null, valores);
        return insertId;
    }

    //Leemos un registro
    public Capital consultaCapital(long mes, long anio){
        Capital newCapital = new Capital();
                newCapital.setCantidad(Double.parseDouble("-1"));
        if(database.isOpen()) {
            Cursor cursor = database.query(dbHelperCapital.TABLE_CAPITAL, // The table to query
                    oneColumn, // The columns to return
                    dbHelperCapital.CN_MES + " = " + mes + " AND " + dbHelperCapital.CN_ANIO + " = " + anio, // The columns for the WHERE clause
                    null,// The values for the WHERE clause
                    null, // don't group the rows
                    null, // don't filter by row groups
                    null); // The sort order
            if (cursor == null || cursor.getCount() == 0) {
                newCapital.setCantidad(Double.parseDouble("-1"));
            } else {
                cursor.moveToFirst();
                newCapital = cursorToCapitalOne(cursor);
            }
            cursor.close();
        }
        return newCapital;
    }

    private Capital cursorToCapitalAll(Cursor cursor) {
        Capital capital = new Capital();
        capital.setId(cursor.getLong(0));
        capital.setMes(cursor.getLong(1));
        capital.setAnio(cursor.getLong(2));
        capital.setCantidad(cursor.getDouble(3));
        return capital;
    }

    private Capital cursorToCapitalOne(Cursor cursor) {
        Capital capital = new Capital();
        capital.setCantidad(cursor.getDouble(0));
        return capital;
    }

    public void deleteGasto(Capital capital) {
        long id = capital.getId();
        System.out.println("capital eliminado id: " + id);
        database.delete(dbHelperCapital.TABLE_CAPITAL, dbHelperCapital.CN_ID
                + " = " + id, null);
    }
}
