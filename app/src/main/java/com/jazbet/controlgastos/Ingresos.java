package com.jazbet.controlgastos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Ingresos extends Activity {

    private EditText txtMonto;
    private EditText txtMes;
    private EditText txtAnio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

         txtMonto = (EditText)findViewById(R.id.txtMontoMens);
         txtMes = (EditText)findViewById(R.id.txtMes);
         txtAnio = (EditText)findViewById(R.id.txtAnio);
        Button btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarIngresos();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ingresos, menu);
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

    private void agregarIngresos(){
        DataBaseManager dbMngr = new DataBaseManager(this);
        dbMngr.insertarCapital(Integer.getInteger(txtMes.toString()), Integer.getInteger(txtAnio.toString()),Float.parseFloat(txtMonto.toString()));
    }
}
