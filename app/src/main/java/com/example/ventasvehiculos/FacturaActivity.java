package com.example.ventasvehiculos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FacturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
    }
    public void menu_fac(View view){
        Intent intmenu_fac = new Intent(this,MainActivity.class );
        startActivity(intmenu_fac);
    }
}