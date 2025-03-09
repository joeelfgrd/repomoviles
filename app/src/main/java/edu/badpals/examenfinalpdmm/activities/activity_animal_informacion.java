package edu.badpals.examenfinalpdmm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import edu.badpals.examenfinalpdmm.Helpers;
import edu.badpals.examenfinalpdmm.R;
import edu.badpals.examenfinalpdmm.model.Animal;
import edu.badpals.examenfinalpdmm.repository.AnimalRepository;
import edu.badpals.examenfinalpdmm.viewModels.Animal_Detalle_viewModel;

public class activity_animal_informacion extends AppCompatActivity {


    //Siempre al iniciar un activity metemos las variables para los objetos de los xml
    private Button btnVolver, btnExtinguir,btnSumar;
    private ImageView ivFotoAnimal;
    private TextView tvNombre, tvRaza, tvHabitat, tvAnimalesDisponibles,tvAnimalesExtintos;




    //Creamos una variable estática en la que almacenamos nuestro shared preference,este nombre en mayusculas es el que se pone en
    // listado animales al añadirlos al shared preference
    public static final String ANIMAL_ID = "animalId";
    //inicializamos una variable que es la que cargamos con el resultado de lo de arriba mediante el método de helpers
    public int animalId = 0;

    private Animal_Detalle_viewModel animal_Detalle_viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Llamamos a la vista
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_informacion);


        //Vinculamos las variables con su correspondencia en el xml
        ivFotoAnimal = findViewById(R.id.ivFotoAnimal);
        btnVolver = findViewById(R.id.btnVolver);
        btnExtinguir = findViewById(R.id.btnExtinguir);
        btnSumar = findViewById(R.id.btnSumar);
        tvNombre = findViewById(R.id.tvNombre);
        tvRaza = findViewById(R.id.tvRaza);
        tvHabitat = findViewById(R.id.tvHabitat);
        tvAnimalesDisponibles = findViewById(R.id.tvAnimalesDisponibles);
        tvAnimalesExtintos = findViewById(R.id.tvAnimalesExtintos);


        //Obtenemos el id del item seleccionado en la otra ventana en el recyclerview a traves del sharedpreference y pasamos el dato
        animalId = Helpers.getID_Animal(this);
        //El id que pasamos al metodo de cargar es el que acabamos de obtener con el intent
        cargarInfoAnimal(animalId);
        setOCL();


    }

    private void setOCL() {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(activity_animal_informacion.this, ListadoAnimales.class);
            startActivity(intent);
        });

        btnExtinguir.setOnClickListener(v -> {
            Animal animal = AnimalRepository.getAnimalById(animalId);
            if (animal.isExtinto()) {
                animal.setExtinto(false);
                cargarInfoAnimal(animal.getId());
            } else {
                animal.setExtinto(true);
                cargarInfoAnimal(animal.getId());
            }
        });
    }

    private void cargarVM(Animal animal) {

        animal_Detalle_viewModel = new ViewModelProvider(this).get(Animal_Detalle_viewModel.class);
        animal_Detalle_viewModel.getAnimal().observe(this, new Observer<Animal>() {
            @Override
            public void onChanged(Animal animal) {
                if (animal != null) {
                    cargarAnimal(animal);
                }
            }
        });

        animal_Detalle_viewModel.setAnimal(animal);
    }



    public void cargarInfoAnimal(int id){
        Animal animal = AnimalRepository.getAnimalById(id);

        if (animal == null) {
            Toast.makeText(this, "No se encontró el animal con ID: " + id, Toast.LENGTH_SHORT).show();
            return;
        }
        cargarVM(animal);


    }

    private void cargarAnimal(Animal result) {
        tvNombre.setText(result.getNombre());
        tvRaza.setText(result.getRaza());
        tvHabitat.setText(result.getHabitat());
        if(result.isExtinto()){
            tvAnimalesExtintos.setText("Si");
        }else{
            tvAnimalesExtintos.setText("No");
        }
        tvAnimalesDisponibles.setText("Disponible");
        ivFotoAnimal.setImageResource(R.drawable.gato);
    }

}