package com.jazbet.controlgastos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class AgregarGastos extends Activity {

    private EditText concepto;
    private EditText cantidad;
    private CheckBox chkYes;
    private CheckBox chkNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gastos);
        concepto = (EditText)findViewById(R.id.txtConcepto);
        cantidad = (EditText)findViewById(R.id.txtCantidad);
        chkYes = (CheckBox)findViewById(R.id.chkYes);
        chkNo = (CheckBox)findViewById(R.id.chkNo);

        Button btnAceptar = (Button)findViewById(R.id.btnAgregar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    agregarGasto();
                } catch (Exception e) {
                    e.printStackTrace();
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "application Failed!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar_gastos, menu);
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

    private void agregarGasto() throws Exception {
            int chkAnswer = 0;
            if (chkYes.isChecked()) {
                chkAnswer = 1;
            } else if (chkNo.isChecked()) {
                chkAnswer = 0;
            }

            DataBaseManager dbMngr = new DataBaseManager(this);
            dbMngr.insertarGastos((String) concepto.toString(), Float.parseFloat((String) cantidad.toString()), chkAnswer);
    }

}
