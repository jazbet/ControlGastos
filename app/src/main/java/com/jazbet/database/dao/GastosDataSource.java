package com.jazbet.database.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.jazbet.database.DbHelperGastos;
import com.jazbet.database.bean.Gastos;

import android.content.Context;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.database.Cursor;

/**
 * Created by jazbet on 12/14/14.
 */

public class GastosDataSource {
    private SQLiteDatabase database;
    private DbHelperGastos dbHelperGastos;
    private String[] allColumns = {DbHelperGastos.CN_ID, DbHelperGastos.CN_CONCEPTO, DbHelperGastos.CN_CANTIDAD, DbHelperGastos.CN_PLAZOS,
            DbHelperGastos.CN_FECHA};

    public GastosDataSource(Context context) {
        dbHelperGastos = new DbHelperGastos(context);
    }

    public void open() throws SQLException {
        database = dbHelperGastos.getWritableDatabase();
    }

    public void close() {
        dbHelperGastos.close();
    }

    public long insertaGasto(String concepto, double cantidad, long plazos){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        ContentValues valores = new ContentValues();
        valores.put(dbHelperGastos.CN_CONCEPTO, concepto);
        valores.put(dbHelperGastos.CN_CANTIDAD, cantidad);
        valores.put(dbHelperGastos.CN_PLAZOS, plazos);
        valores.put(dbHelperGastos.CN_FECHA, strDate);

        long insertId = database.insert(dbHelperGastos.TABLE_NAME, null, valores);
        return insertId;
    }

    /*TODO
    *Necesita mejora.. no requiere q el cursor se mueva al primer elemento
    * */
    public Gastos consultaGasto(long idGasto){
        Cursor cursor = database.query(dbHelperGastos.TABLE_NAME,
                allColumns, dbHelperGastos.CN_ID + " = " + idGasto, null,
                null, null, null);
        cursor.moveToFirst();
        Gastos newGasto = cursorToGasto(cursor);
        cursor.close();
        return newGasto;
    }

    private Gastos cursorToGasto(Cursor cursor) {
        Gastos gastos = new Gastos();
        gastos.setId(cursor.getLong(0));
        gastos.setConcepto(cursor.getString(1));
        gastos.setCantdad(cursor.getDouble(2));
        gastos.setPlazos(cursor.getLong(3));
        gastos.setFecha(cursor.getString(4));
        return gastos;
    }

    public void deleteGasto(Gastos gasto) {
        long id = gasto.getId();
        System.out.println("Gasto eliminado id: " + id);
        database.delete(dbHelperGastos.TABLE_NAME, dbHelperGastos.CN_ID
                + " = " + id, null);
    }

    /*public List<Gastos> getAllGastos() {
        List<Gastos> gastos = new ArrayList<Gastos>();

        Cursor cursor = database.query(dbHelperGastos.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Gastos gasto = cursorToGasto(cursor);
            gastos.add(gasto);
            cursor.moveToNext();
        }
        // close the cursor
        cursor.close();
        return gastos;
    }*/
}
