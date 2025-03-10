package edu.badpals.examenfinalpdmm.activitys;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

import edu.badpals.examenfinalpdmm.Helpers;
import edu.badpals.examenfinalpdmm.R;

public class Login extends AppCompatActivity {
    private EditText etBuscar;
    private Button btnLogin;
    private String nombre;
    public static final String LOGIN_NOMBRE = "nombre";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nombre = String.valueOf(Helpers.getNombreLogin(this));
        if(!nombre.isEmpty()){
            etBuscar.setText(nombre);
        }
        etBuscar = findViewById(R.id.etBuscar);
        btnLogin = findViewById(R.id.btnLogin);

        nombre = String.valueOf(etBuscar.getText());
        btnLogin.setOnClickListener((view) -> {
            Intent intent = new Intent(Login.this, ListadoPartidos.class);
            addToEncriptedSharePreferences(nombre);
            startActivity(intent);
        });
    }


    private void addToEncriptedSharePreferences(String nombre) {
        try {
            MasterKey mk = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences encryptedSp = EncryptedSharedPreferences.create(this, "ENCRYPTEDSHARE",
                    mk,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

            SharedPreferences.Editor encryptedEditor = encryptedSp.edit();
            encryptedEditor.putString(Login.LOGIN_NOMBRE, nombre);
            encryptedEditor.apply();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}