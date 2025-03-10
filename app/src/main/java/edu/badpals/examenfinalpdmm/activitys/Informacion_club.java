package edu.badpals.examenfinalpdmm.activitys;

import static edu.badpals.examenfinalpdmm.Helpers.cargarToolbar;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.badpals.examenfinalpdmm.R;

public class Informacion_club extends AppCompatActivity {
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_club);
        tb = findViewById(R.id.toolbar);
        cargarToolbar(this, tb);
    }
}