package com.jazbet.controlgastos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.jazbet.database.bean.Capital;
import com.jazbet.database.dao.CapitalDataSource;


public class Ingresos extends Activity {

    private static final String TAG = "AgregarIngresos";
    private EditText txtMonto;
    private EditText txtMes;
    private EditText txtAnio;
    private CapitalDataSource capitalDS;

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
                try {
                    Context context = getApplicationContext();
                    long totReg = 0;
                    capitalDS = new CapitalDataSource(context);
                    capitalDS.open();

                    //Verificar que ese ingreso mensual no haya sido insertado antes
                    Capital verificaIngreso = capitalDS.consultaCapital(Long.parseLong(txtMes.getText().toString()),
                            Long.parseLong(txtAnio.getText().toString()));
                    if(verificaIngreso!= null && verificaIngreso.getCantidad()>0)
                    {
                        totReg = capitalDS.insertaIngreso(Long.parseLong(txtMes.getText().toString()),
                                Long.parseLong(txtAnio.getText().toString()),
                                Double.parseDouble(txtMonto.getText().toString()));

                        if(totReg < 1){
                            Toast toast = Toast.makeText(context, "no fue posible insertar el registro", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(context, "registro insertado exitosamente", Toast.LENGTH_LONG);
                            toast.show();
                            cleanFields();
                        }

                    }
                    else{
                        Toast toast = Toast.makeText(context, "ya ha sido registrado el ingreso de ese mes", Toast.LENGTH_LONG);
                        toast.show();
                    }



                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();

                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Ocurrió un fallo en la aplicación!", Toast.LENGTH_LONG);
                    toast.show();
                } finally {
                    capitalDS.close();
                }
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

    //internal functions
    private void cleanFields(){
        txtMonto.setText("");
        txtMes.setText("");
        txtAnio.setText("");
    }
}
