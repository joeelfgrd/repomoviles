package edu.badpals.examenfinalpdmm;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.badpals.examenfinalpdmm.activitys.InformacionPartido;
import edu.badpals.examenfinalpdmm.activitys.ListadoPartidos;
import edu.badpals.examenfinalpdmm.activitys.Login;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);


    }
}