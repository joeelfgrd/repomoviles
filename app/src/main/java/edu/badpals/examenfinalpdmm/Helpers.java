package edu.badpals.examenfinalpdmm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;
import edu.badpals.examenfinalpdmm.activitys.InformacionPartido;
import edu.badpals.examenfinalpdmm.activitys.Informacion_club;
import edu.badpals.examenfinalpdmm.activitys.ListadoPartidos;

public class Helpers {
    public static void cargarToolbar(AppCompatActivity context, Toolbar tb) {

        context.setSupportActionBar(tb);
        context.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.btnMenuMenuPrincipal){
                    Intent intent = new Intent(context, Informacion_club.class);
                    context.startActivity(intent);
                }
                if(id == R.id.btnMenuListadoPartidos){
                    Intent intent = new Intent(context, ListadoPartidos.class);
                    context.startActivity(intent);
                }

                return false;
            }
        });
    }


    public static int getID_Partido(AppCompatActivity context){
        try {
            MasterKey mk = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences encryptedSp = EncryptedSharedPreferences.create(context, "ENCRYPTEDSHARE",
                    mk,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

            return encryptedSp.getInt(InformacionPartido.PARTIDO_ID, 0);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
