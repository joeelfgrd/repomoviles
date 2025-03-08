package edu.badpals.examenfinalpdmm;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;

import edu.badpals.examenfinalpdmm.activities.ListadoAnimales;

public class Helpers {

    public static void cargarToolbar(AppCompatActivity context, Toolbar tb) {
        // Configura la barra de herramientas (Toolbar) en la actividad proporcionada
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
                if(id == R.id.btnMenuMenuPrincipal){
                    Intent intent = new Intent(context, ListadoAnimales.class);
                    context.startActivity(intent);
                }
                if(id == R.id.btnMenuListadoAnimales){
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }

                // Devuelve false si no se selecciona ningún elemento conocido
                return false;
            }
        });
    }
}
