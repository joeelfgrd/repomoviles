package edu.badpals.examenfinalpdmm.editPreference;

import static edu.badpals.examenfinalpdmm.Helpers.cargarToolbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import edu.badpals.examenfinalpdmm.R;
import edu.badpals.examenfinalpdmm.fragments.PreferenceFragment;

public class EditPreferences extends AppCompatActivity {
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_preferences);

        tb = findViewById(R.id.toolbar);

        cargarToolbar(this,tb);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.preferences_container, new PreferenceFragment())
                .commit();




    }
}