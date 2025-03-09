package edu.badpals.examenfinalpdmm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.badpals.examenfinalpdmm.activities.ListadoAnimales;
import edu.badpals.examenfinalpdmm.activities.Listado_Cuidadores;
import edu.badpals.examenfinalpdmm.activities.activity_menu_principal;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, activity_menu_principal.class);
        startActivity(intent);
    }
}