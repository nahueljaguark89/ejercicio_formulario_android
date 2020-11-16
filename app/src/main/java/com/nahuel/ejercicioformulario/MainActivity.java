package com.nahuel.ejercicioformulario;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.app.DatePickerDialog;;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    EditText etFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.picker_fecha);
        //Widget EditText del cual usaremos el evento clic para obtener la fecha (es el mismo)
        //Evento setOnClickListener - clic
        etFecha.setOnClickListener(this);

        EditText etNombreCompleto = (EditText) findViewById(R.id.nombre_completo);
        EditText etTelefono = (EditText) findViewById(R.id.telefono);
        EditText etEmail = (EditText) findViewById(R.id.email);
        EditText etDescripcion = (EditText) findViewById(R.id.descripcion_contacto);

        Bundle datos = getIntent().getExtras();
        if (datos != null ) {
            String nombre = datos.getString("edit_nombre");
            String fecha = datos.getString("edit_fecha");
            String telefono = datos.getString("edit_telefono");
            String email = datos.getString("edit_email");
            String descripcion = datos.getString("edit_descripcion");

            etNombreCompleto.setText(nombre);
            etFecha.setText(fecha);
            etTelefono.setText(telefono);
            etEmail.setText(email);
            etDescripcion.setText(descripcion);
        }

        Button botonSiguiente = (Button) findViewById(R.id.boton_siguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intentDatosContacto = new Intent(MainActivity.this, DatosContacto.class);
                intentDatosContacto.putExtra("var_nombre", etNombreCompleto.getText().toString());
                intentDatosContacto.putExtra("var_fecha", etFecha.getText().toString());
                intentDatosContacto.putExtra("var_telefono", etTelefono.getText().toString());
                intentDatosContacto.putExtra("var_email", etEmail.getText().toString());
                intentDatosContacto.putExtra("var_descripcion", etDescripcion.getText().toString());
                startActivity(intentDatosContacto);
                finish();
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.picker_fecha:
                obtenerFecha();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }


}

