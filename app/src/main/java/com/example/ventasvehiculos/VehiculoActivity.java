package com.example.ventasvehiculos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class VehiculoActivity extends AppCompatActivity {

    String placa, marca, modelo, valor;
    EditText jetplaca, jetmarca, jetmodelo, jetvalor;
    CheckBox jcbactivo;
    ClsOpenHelper admin=new ClsOpenHelper(this,"Concesionario.bd",null,1);
    long resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);

        //Ocultar titulo por defecto y asociar objetos Java con xml
        getSupportActionBar().hide();
        jetplaca = findViewById(R.id.etplacaveh);
        jetmarca = findViewById(R.id.etmarca);
        jetmodelo = findViewById(R.id.etmodelo);
        jetvalor = findViewById(R.id.etValor);
        jcbactivo = findViewById(R.id.cbactivoveh);
    }

    public void Guardar(View view) {

        placa = jetplaca.getText().toString();
        marca = jetmarca.getText().toString();
        modelo = jetmodelo.getText().toString();
        valor = jetvalor.getText().toString();
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || valor.isEmpty()) {
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        } else {
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("placa", placa);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            registro.put("valor", Integer.parseInt(valor));
            resp = db.insert("TblVehiculo", null, registro);
            if (resp > 0) {
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            } else {
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }
    }
    public void Consultar (View view){
        placa = jetplaca.getText().toString();
        if (placa.isEmpty()) {
            Toast.makeText(this, "La placa es requerida", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        } else {
            SQLiteDatabase db = admin.getReadableDatabase();
            Cursor fila = db.rawQuery("select * from TblVehiculo where placa='" + placa + "'", null);
            if (fila.moveToNext()) {

            } else
                Toast.makeText(this, "Vehiculo no registrado", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
    private void Limpiar_campos (){
        jetplaca.setText("");
        jetmarca.setText("");
        jetmodelo.setText("");
        jetvalor.setText("");
        jcbactivo.setChecked(false);
        jetplaca.requestFocus();
    }
    public void menu_vehi(View view){
        Intent intmenu_vehi = new Intent(this,MainActivity.class );
        startActivity(intmenu_vehi);
    }
}
