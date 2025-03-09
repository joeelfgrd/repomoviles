package edu.badpals.examenfinalpdmm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.badpals.examenfinalpdmm.activities.ListadoAnimales;
import edu.badpals.examenfinalpdmm.activities.Listado_Cuidadores;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, Listado_Cuidadores.class);
        startActivity(intent);
    }
}