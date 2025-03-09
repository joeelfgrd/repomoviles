package edu.badpals.examenfinalpdmm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;

import java.io.IOException;
import java.security.GeneralSecurityException;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import edu.badpals.examenfinalpdmm.activities.ListadoAnimales;
import edu.badpals.examenfinalpdmm.activities.activity_animal_informacion;
import edu.badpals.examenfinalpdmm.activities.activity_menu_principal;

public class Helpers {
    //Variable estática para el qr
    private static ActivityResultLauncher<ScanOptions> qrLauncher;



    public static void cargarToolbar(AppCompatActivity context, Toolbar tb) {
        // Configura el toolbar
        context.setSupportActionBar(tb);

        // Añade un proveedor de menú a la actividad
        context.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                // Infla el menú desde el recurso XML 'main_menu'
                menuInflater.inflate(R.menu.main_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                // Obtiene el ID del elemento del menú seleccionado
                int id = menuItem.getItemId();
                // Maneja la selección de los elementos del menú
                if(id == R.id.btnMenuListadoAnimales){
                    Intent intent = new Intent(context, ListadoAnimales.class);
                    context.startActivity(intent);
                }
                if(id == R.id.btnMenuMenuPrincipal){
                    Intent intent = new Intent(context, activity_menu_principal.class);
                    context.startActivity(intent);
                }
                if(id == R.id.btnMenuCamara){
                    scanearQR();
                }


                // Devuelve false si no se selecciona ningún elemento conocido
                return false;
            }
        });
    }




    public static int getID_Animal(AppCompatActivity context){
        try {
            MasterKey mk = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences encryptedSp = EncryptedSharedPreferences.create(context, "ENCRYPTEDSHARE",
                    mk,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

            return encryptedSp.getInt(activity_animal_informacion.ANIMAL_ID, 0);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    //LOGICA CAMARA PARA ESCANEAR --------------------------------------------------------------------------------------------------
    public static void scanearQR() {
        if (qrLauncher != null) {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Escanea el código de barras");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CaptureActivity.class);

            qrLauncher.launch(options);
        } else {
            System.out.println("QR Launcher no inicializado");
        }
    }

    public interface QRCallback {
        void onResult(String scannedData);
    }

    public static void inicializarQRLauncher(AppCompatActivity context, String[] scannedResult, QRCallback callback) {

        // La lógica de este método nos permite hacer acciones específicas para cuando devuelva el resultado como un listener (mirar en los métodos que se usa)

        qrLauncher = context.registerForActivityResult(new ScanContract(), result -> {
            if (result.getContents() != null) {
                scannedResult[0] = result.getContents();
                callback.onResult(scannedResult[0]);
                System.out.println("Animal escaneado desde Helpers: " + scannedResult[0]);
            }
        });
    }
    //FIN LOGICA CAMARA --------------------------------------------------------------------------------------------------
}
