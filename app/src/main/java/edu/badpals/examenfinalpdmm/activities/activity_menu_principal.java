package edu.badpals.examenfinalpdmm.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.badpals.examenfinalpdmm.Helpers;
import edu.badpals.examenfinalpdmm.R;

public class activity_menu_principal extends AppCompatActivity {

    private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        tb = findViewById(R.id.toolbar);
        Helpers.cargarToolbar(this, tb);

    }
}