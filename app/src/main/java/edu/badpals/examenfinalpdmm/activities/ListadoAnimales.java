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

import edu.badpals.examenfinalpdmm.Helpers;
import edu.badpals.examenfinalpdmm.R;
import edu.badpals.examenfinalpdmm.model.Animal;
import edu.badpals.examenfinalpdmm.repository.AnimalRepository;
import edu.badpals.examenfinalpdmm.viewModels.AnimalViewModel;

public class ListadoAnimales extends AppCompatActivity {

    private RecyclerView recyclerViewAnimales;
    private Button btnCrearNuevoAnimal;
    private Toolbar tb;
    private AnimalViewModel animalViewModel;
    private AnimalAdapter adapter;


    //Añadimos un array para almacenar el nombre escaneado con la camara
    private String[] nombreAnimalEscaneado = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_animales);




        // Vinculación de vistas
        recyclerViewAnimales = findViewById(R.id.recyclerViewAnimales);
        recyclerViewAnimales.setLayoutManager(new LinearLayoutManager(this));
        btnCrearNuevoAnimal = findViewById(R.id.btnCrearNuevoAnimal);




        tb = findViewById(R.id.toolbar);
        Helpers.cargarToolbar(this, tb);

        // Inicialización del ViewModel
        animalViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);
        adapter = new AnimalAdapter();
        recyclerViewAnimales.setAdapter(adapter);

        // Observa cambios en la lista de animales y actualiza el adapter
        animalViewModel.getAnimales().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animales) {
                adapter.setAnimales(animales);
            }
        });

        // Carga inicial de animales
        cargarAnimales();

        btnCrearNuevoAnimal.setOnClickListener((view) -> {
            AnimalRepository.nuevoAnimalDefault();
            animalViewModel.setAnimales(AnimalRepository.getAnimales());
        });


        //LOGICA CAMARA
        Helpers.inicializarQRLauncher(this, nombreAnimalEscaneado, result -> {
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

        });
    }

    public void cargarAnimales() {
        List<Animal> animales = AnimalRepository.getAnimales();
        if (animales.isEmpty()) {
            Toast.makeText(ListadoAnimales.this, "No hay animales disponibles", Toast.LENGTH_SHORT).show();
        } else {
            animalViewModel.setAnimales(animales);
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
            encryptedEditor.putInt(activity_animal_informacion.ANIMAL_ID, id);
            encryptedEditor.apply();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {
        private List<Animal> animales = new ArrayList<>();

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imgAnimal;
            TextView txtNombre, txtRaza, txt_habitat, txt_extinto;
            Button btnDetalles;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                imgAnimal = itemView.findViewById(R.id.imgAnimal);
                txtNombre = itemView.findViewById(R.id.txtNombre);
                txtRaza = itemView.findViewById(R.id.txtRaza);
                txt_habitat = itemView.findViewById(R.id.txt_habitat);
                txt_extinto = itemView.findViewById(R.id.txt_extinto);
                btnDetalles = itemView.findViewById(R.id.btnDetalles);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_animal_listado, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MyViewHolder myvh = (MyViewHolder) holder;
            Animal animal = animales.get(position);
            myvh.txtNombre.setText(animal.getNombre());
            myvh.txtRaza.setText(animal.getRaza());
            myvh.txt_habitat.setText(animal.getHabitat());
            if (animal.isExtinto()) {
                myvh.txt_extinto.setText("si");
            } else {
                myvh.txt_extinto.setText("No");
            }

            if (animal.getFoto() != null && !animal.getFoto().isEmpty()) {
                myvh.imgAnimal.setImageResource(R.drawable.gato);
            }

            myvh.btnDetalles.setOnClickListener((view) -> {
                Intent intent = new Intent(ListadoAnimales.this, activity_animal_informacion.class);
                addToEncriptedSharePreferences(animal.getId());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return animales.size();
        }

        void setAnimales(List<Animal> nuevosAnimales) {
            this.animales = nuevosAnimales;
            notifyDataSetChanged();
        }
    }
}
