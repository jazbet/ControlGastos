package com.jazbet.controlgastos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jazbet.database.bean.Capital;
import com.jazbet.database.dao.CapitalDataSource;


public class MainMoneyActivity extends Activity {
    private static final String TAG = "Menu";
    private CapitalDataSource capitalDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_money);


        //Creación de los menus
        ImageButton imgBtnGasto = (ImageButton) findViewById(R.id.imgBtnConcepto);
        imgBtnGasto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gasto = new Intent(MainMoneyActivity.this, AgregarGastos.class);
                startActivity(gasto);
            }

        });

        ImageButton imgBtnIngresos = (ImageButton) findViewById(R.id.imgBtnIngresos);
        imgBtnIngresos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ingreso = new Intent(MainMoneyActivity.this, Ingresos.class);
                startActivity(ingreso);
            }

        });

        TextView txtTotal = (TextView) findViewById(R.id.txtTotal);
        try {
                capitalDS = new CapitalDataSource(this);
                capitalDS.open();

                //Verificar que ese ingreso mensual no haya sido insertado antes
                Capital verificaIngreso = capitalDS.consultaTotal();
                Log.w(Ingresos.class.getName(), "Cantidad = " + verificaIngreso.getCantidad());
            String tot = String.valueOf(verificaIngreso.getCantidad());
                txtTotal.setText(tot);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();

            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Ocurrió un fallo en la aplicación!", Toast.LENGTH_LONG);
            toast.show();
        } finally {
            capitalDS.close();
        }

        /*ImageButton imgBtnAhorro = (ImageButton) findViewById(R.id.imgBtnAhorro);
        imgBtnAhorro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ahorro = new Intent(MainMoneyActivity.this, Ahorro.class);
                startActivity(ahorro);
            }

        });

        ImageButton imgBtnComprasAPlazos = (ImageButton) findViewById(R.id.imgBtnComprasAPlazos);
        imgBtnComprasAPlazos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent compras = new Intent(MainMoneyActivity.this, ComprasAPlazos.class);
                startActivity(compras);
            }

        });

        ImageButton imgBtnGastoAnual = (ImageButton) findViewById(R.id.imgBtnGastoAnual);
        imgBtnGastoAnual.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent estadistica = new Intent(MainMoneyActivity.this, EstadisticaAnual.class);
                startActivity(estadistica);
            }

        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_money, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
