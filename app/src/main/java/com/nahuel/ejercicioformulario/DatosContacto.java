package com.nahuel.ejercicioformulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DatosContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);


        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("var_nombre");
        String fecha = datos.getString("var_fecha");
        String telefono = datos.getString("var_telefono");
        String email = datos.getString("var_email");
        String descripcion = datos.getString("var_descripcion");

        TextView muestro_dato_nombre = (TextView) findViewById(R.id.muestro_dato_nombre);
        TextView muestro_dato_fecha = (TextView) findViewById(R.id.muestro_dato_fecha);
        TextView muestro_dato_telefono = (TextView) findViewById(R.id.muestro_dato_telefono);
        TextView muestro_dato_email = (TextView) findViewById(R.id.muestro_dato_email);
        TextView muestro_dato_descripcion = (TextView) findViewById(R.id.muestro_dato_descripcion);

        muestro_dato_nombre.setText(nombre);
        muestro_dato_fecha.setText(fecha);
        muestro_dato_telefono.setText(telefono);
        muestro_dato_email.setText(email);
        muestro_dato_descripcion.setText(descripcion);


        Button botonEditar = (Button) findViewById(R.id.boton_editar);
        botonEditar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intentEditarDatos = new Intent(DatosContacto.this, MainActivity.class);
                intentEditarDatos.putExtra("edit_nombre", nombre);
                intentEditarDatos.putExtra("edit_fecha", fecha);
                intentEditarDatos.putExtra("edit_telefono", telefono);
                intentEditarDatos.putExtra("edit_email", email);
                intentEditarDatos.putExtra("edit_descripcion", descripcion);
                startActivity(intentEditarDatos);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intentBack = new Intent(DatosContacto.this, MainActivity.class);
            startActivity(intentBack);
        }
        return super.onKeyDown(keyCode,event);
    }
}