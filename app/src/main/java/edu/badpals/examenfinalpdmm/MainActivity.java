package edu.badpals.examenfinalpdmm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.badpals.examenfinalpdmm.activities.ListadoAnimales;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, ListadoAnimales.class);
        startActivity(intent);
    }
}