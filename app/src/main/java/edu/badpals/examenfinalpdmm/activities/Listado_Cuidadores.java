package edu.badpals.examenfinalpdmm.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.R;
import edu.badpals.examenfinalpdmm.model.Animal;
import edu.badpals.examenfinalpdmm.model.Cuidador;
import edu.badpals.examenfinalpdmm.repository.AnimalRepository;
import edu.badpals.examenfinalpdmm.repository.CuidadoresRepository;
import edu.badpals.examenfinalpdmm.viewModels.AnimalViewModel;
import edu.badpals.examenfinalpdmm.viewModels.CuidadoresViewModel;
import edu.badpals.examenfinalpdmm.Helpers;

public class Listado_Cuidadores extends AppCompatActivity {

    private RecyclerView recyclerViewCuidadores;
    private Button btnCrearNuevoCuidador;
    private Toolbar tb;
    private CuidadoresViewModel cuidadoresViewModel;
    private CuidadorAdapter adapter;


    //Añadimos un array para almacenar el nombre escaneado con la camara
    private String[] nombreAnimalEscaneado = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_cuidadores);





        // Vinculación de vistas
        recyclerViewCuidadores = findViewById(R.id.recyclerViewCuidadores);
        recyclerViewCuidadores.setLayoutManager(new LinearLayoutManager(this));
        btnCrearNuevoCuidador = findViewById(R.id.btnCrearNuevoCuidador);



        tb = findViewById(R.id.toolbar);
        Helpers.cargarToolbar(this, tb);





        // Inicialización del ViewModel
        cuidadoresViewModel = new ViewModelProvider(this).get(CuidadoresViewModel.class);
        adapter = new CuidadorAdapter();
        recyclerViewCuidadores.setAdapter(adapter);

        // Observa cambios en la lista de animales y actualiza el adapter
        cuidadoresViewModel.getCuidadores().observe(this, new Observer<List<Cuidador>>() {
            @Override
            public void onChanged(List<Cuidador> cuidadores) {
                adapter.setCuidadores(cuidadores);
            }
        });


        // Carga inicial de animales
        cargarCuidadores();
        //Carga listeners de botones

        btnCrearNuevoCuidador.setOnClickListener((view) -> {
            CuidadoresRepository.nuevoCuidadorDefault();
            cuidadoresViewModel.setCuidadores(CuidadoresRepository.getCuidadores());
        });




        //LOGICA CAMARA
       /* Helpers.inicializarQRLauncher(this, nombreAnimalEscaneado, result -> {
            // Este callback se llama cuando el escaneo finaliza
            nombreAnimalEscaneado[0] = "Elefante Africano";
            //sin hardcodear sería
            //nombreAnimalEscaneado[0] = result;
            System.out.println("Nombre escaneado desde ListadoAnimales: " + nombreAnimalEscaneado[0]);


            List<Animal> listaAnimales = AnimalRepository.getAnimales();
            boolean encontrado = false;

            for (Animal a : listaAnimales) {
                if (a.getNombre().equals(nombreAnimalEscaneado[0])) {
                    Intent intent = new Intent(ListadoAnimales.this, activity_animal_informacion.class);
                    addToEncriptedSharePreferences(a.getId());
                    startActivity(intent);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                Toast.makeText(ListadoAnimales.this, "No se encontró el Animal con nombre: " + nombreAnimalEscaneado[0], Toast.LENGTH_SHORT).show();
            }

        });*/
    }

    public void cargarCuidadores() {
        List<Cuidador> cuidadores = CuidadoresRepository.getCuidadores();
        if (cuidadores.isEmpty()) {
            Toast.makeText(Listado_Cuidadores.this, "No hay cuidadores disponibles", Toast.LENGTH_SHORT).show();
        } else {
            cuidadoresViewModel.setCuidadores(cuidadores);
        }
    }


    private void addToEncriptedSharePreferences(int id) {
        try {
            MasterKey mk = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            SharedPreferences encryptedSp = EncryptedSharedPreferences.create(this, "ENCRYPTEDSHARE",
                    mk,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

            SharedPreferences.Editor encryptedEditor = encryptedSp.edit();
            encryptedEditor.putInt(activity_cuidador_informacion.CUIDADOR_ID, id);
            encryptedEditor.apply();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class CuidadorAdapter extends RecyclerView.Adapter<CuidadorAdapter.MyViewHolder> {
        private List<Cuidador> cuidadores = new ArrayList<>();

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imgCuidador;
            TextView txtNombre, txtApellidos, txtEdad, txtPais,txtTrabajando;
            Button btnDetalles;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                imgCuidador = itemView.findViewById(R.id.imgPortada);
                txtNombre = itemView.findViewById(R.id.txtNombre);
                txtApellidos = itemView.findViewById(R.id.txtApellidos);
                txtEdad = itemView.findViewById(R.id.txtEdad);
                txtPais = itemView.findViewById(R.id.txtPais);
                txtTrabajando = itemView.findViewById(R.id.txtTrabajando);
                btnDetalles=itemView.findViewById(R.id.btnDetalles);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_cuidador_listado, parent, false);
            return new CuidadorAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MyViewHolder myvh = (MyViewHolder) holder;

            Cuidador cuidador = cuidadores.get(position);


            myvh.txtNombre.setText(cuidador.getNombre());
            myvh.txtApellidos.setText(cuidador.getApellidos());
            myvh.txtEdad.setText(String.valueOf(cuidador.getEdad()));
            myvh.txtPais.setText(cuidador.getPais());

            if (cuidador.isTrabajando()) {
                myvh.txtTrabajando.setText("si");
            } else {
                myvh.txtTrabajando.setText("No");
            }

            if (cuidador.getFoto() != null && !cuidador.getFoto().isEmpty()) {
                myvh.imgCuidador.setImageResource(R.drawable.peruano);
            }
            myvh.btnDetalles.setOnClickListener((view) -> {
                Intent intent = new Intent(Listado_Cuidadores.this, activity_cuidador_informacion.class);
                addToEncriptedSharePreferences(cuidador.getId_cuidador());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return cuidadores.size();
        }

        void setCuidadores(List<Cuidador> nuevosCuidadores) {
            this.cuidadores = nuevosCuidadores;
            notifyDataSetChanged();
        }
    }








}