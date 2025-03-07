package edu.badpals.examenfinalpdmm.activities;

import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import edu.badpals.examenfinalpdmm.Auxiliar;
import edu.badpals.examenfinalpdmm.R;
import edu.badpals.examenfinalpdmm.model.Animal;
import edu.badpals.examenfinalpdmm.repository.AnimalRepository;

public class ListadoAnimales extends AppCompatActivity {

    //Primero se inicializan las variables de xml para trabajar aqui
    private RecyclerView recyclerViewAnimales;
    private Button btnFiltrarAnimal,btnFiltrarExtinto,btnEliminarFiltro;
    private List<Animal> listAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_animales);

        //Aqui obtenemos las variables con el id
        recyclerViewAnimales = findViewById(R.id.recyclerViewAnimales);
        recyclerViewAnimales.setLayoutManager(new LinearLayoutManager(this));
        btnFiltrarAnimal= findViewById(R.id.btnFiltrarAnimal);
        btnFiltrarExtinto= findViewById(R.id.btnFiltrarExtinto);
        btnEliminarFiltro= findViewById(R.id.btnEliminarFiltro);

        cargarBooks();
        //Cargamos la lista de los animales hardcodeados
        listAnimales = AnimalRepository.getAnimales();
        //Cargamos la lista de animales al adapter para cargarla en las tarjetas individuales
        cargarAdapter(listAnimales);


        //Aquí irán los listeners para los botones
    }

    private void cargarAdapter(List<Animal>animalesLista) {
        // Se settea el adapter con el fragment
        List<Animal> AnimalesListado = animalesLista;
        recyclerViewAnimales.setAdapter(new RecyclerView.Adapter() {
            class MyViewHolder extends RecyclerView.ViewHolder {
                //Se inicializan las variables del cuadrito que se ve en la lista,el fragment
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

            // Función que crea el fragment
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_animal_listado, parent, false);
                return new MyViewHolder(view);
            }

            // Función que se encarga de llenar el fragment con los datos de los animales
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                MyViewHolder myvh = (MyViewHolder) holder;
                Animal animal = AnimalesListado.get(position);

                myvh.txtNombre.setText(animal.getNombre());
                myvh.txtRaza.setText(animal.getRaza());

                myvh.txt_habitat.setText(animal.getHabitat());
                if (animal.isExtinto()) {
                    myvh.txt_extinto.setText("si");
                } else {
                    myvh.txt_extinto.setText("No");
                }
                String urlImagen = animal.getFoto();
                if (urlImagen != null && !urlImagen.isEmpty()) {
                    myvh.imgAnimal.setImageResource(R.drawable.gato);
                }
                //Asignamos el intent para abrir la informacion detallada
                /*myvh.btn1.setOnClickListener((view) -> {
                    Intent intent = new Intent(ListadoLibros.this, LibroInformacion.class);
                    intent.putExtra(LibroInformacion.BOOK_ID_EXTRA, book.getId());
                    startActivity(intent);
                });*/

            }

            @Override
            public int getItemCount() {
                return AnimalesListado.size();
            }
        });
    }

    public void cargarBooks() {
        List<Animal> animales = AnimalRepository.getAnimales();

        if (animales.isEmpty()) {
            Toast.makeText(ListadoAnimales.this, "No hay animales disponibles", Toast.LENGTH_SHORT).show();
        } else {
            cargarAdapter(animales);
        }
    }


}