package edu.badpals.examenfinalpdmm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.badpals.examenfinalpdmm.R;
import edu.badpals.examenfinalpdmm.model.Animal;
import edu.badpals.examenfinalpdmm.repository.AnimalRepository;

public class activity_animal_informacion extends AppCompatActivity {
    //Siempre al iniciar un activity metemos las variables para los objetos de los xml
    private Button btnVolver, btnAdoptar, btnDevolver;
    private ImageView ivFotoAnimal;
    private TextView tvNombre, tvRaza, tvHabitat, tvAnimalesDisponibles,tvAnimalesExtintos;

    public static final String ANIMAL_ID = "animalId";
    public int animalId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Llamamos a la vista
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_informacion);
        //Vinculamos las variables con su correspondencia en el xml
        ivFotoAnimal= findViewById(R.id.ivFotoAnimal);
        btnVolver= findViewById(R.id.btnVolver);
        btnAdoptar= findViewById(R.id.btnAdoptar);
        btnDevolver= findViewById(R.id.btnDevolver);
        tvNombre= findViewById(R.id.tvNombre);
        tvRaza= findViewById(R.id.tvRaza);
        tvHabitat= findViewById(R.id.tvHabitat);
        tvAnimalesDisponibles= findViewById(R.id.tvAnimalesDisponibles);
        tvAnimalesExtintos= findViewById(R.id.tvAnimalesExtintos);

        //Obtenemos el id del item seleccionado en la otra ventana en el recyclerview a traves del intent y pasamos el dato
        animalId = getIntent().getIntExtra("ANIMAL_ID", 0);
        //El id que pasamos al metodo de cargar es el que acabamos de obtener con el intent
        cargarInfoAnimal(animalId);


        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(activity_animal_informacion.this, ListadoAnimales.class);
            startActivity(intent);
        });



    }
    public void cargarInfoAnimal(int id){
        Animal animal = AnimalRepository.getAnimalById(id);

        if (animal == null) {
            Toast.makeText(this, "No se encontró el animal con ID: " + id, Toast.LENGTH_SHORT).show();
            return;
        }
        tvNombre.setText(animal.getNombre());
        tvRaza.setText(animal.getRaza());
        tvHabitat.setText(animal.getHabitat());
        if(animal.isExtinto()){
            tvAnimalesExtintos.setText("Si");
        }else{
            tvAnimalesExtintos.setText("No");
        }
        tvAnimalesDisponibles.setText("Disponible");
        ivFotoAnimal.setImageResource(R.drawable.gato);




    }

}