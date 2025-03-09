package edu.badpals.examenfinalpdmm.activities;

import static edu.badpals.examenfinalpdmm.Helpers.cargarToolbar;

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
import edu.badpals.examenfinalpdmm.model.Cuidador;
import edu.badpals.examenfinalpdmm.repository.CuidadoresRepository;
import edu.badpals.examenfinalpdmm.viewModels.CuidadorViewModel;

public class activity_cuidador_informacion extends AppCompatActivity {

    private ImageView ivFotoCuidador;
    private TextView tvNombre, tvApellidos, tvEdad, tvPais, tvTrabajando;
    private Button btnDespedir, btnAnhos, btnVolver;

    private CuidadorViewModel cuidadorViewModel;


    public static final String CUIDADOR_ID = "cuidadorId";
    public int cuidadorId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuidador_informacion);

        tvNombre = findViewById(R.id.tvNombre);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvEdad = findViewById(R.id.tvEdad);
        tvPais = findViewById(R.id.tvPais);
        tvTrabajando = findViewById(R.id.tvTrabajando);
        btnDespedir = findViewById(R.id.btnDespedir);
        ivFotoCuidador = findViewById(R.id.ivFotoCuidador);
        btnAnhos = findViewById(R.id.btnAnhos);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtenemos el ID del cuidador  mediante los shared preferences y el intent para pasar información entre clases
        cuidadorId = Helpers.getID_Cuidador(this);

        // Cargamos la información del cuidador a partir de la id de este
        cargarInfoCuidador(cuidadorId);
/*
        // Inicializamos el QR Launcher para poder escanear:
        Helpers.inicializarQRLauncher(this, isbnEscaneado, result -> {

            // Como mencioné en la clase Helpers este método es como un listener el cual puedes añadirle acciones cuando lea el QR

            isbnEscaneado[0] = result;
            System.out.println("ISBN escaneado desde LibroInformacion: " + isbnEscaneado[0]);

            // Una vez tenemos el ISBN, buscamos el libro en la lista general:
            BookRepository bookRepository = new BookRepository();
            bookRepository.getBooks(new BookRepository.ApiCallback<List<Book>>() {
                @Override
                public void onSuccess(List<Book> books) {
                    for (Book book : books) {
                        if (book.getIsbn().equals(isbnEscaneado[0])) {
                            bookId = book.getId();
                            System.out.println("Libro hallado con ISBN: " + book.getIsbn() + " => ID: " + book.getId());

                            cargarInfoCuidador(bookId);
                            return;
                        }
                    }
                    Toast.makeText(LibroInformacion.this, "No se encontró el libro con ISBN: " + isbnEscaneado[0], Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(LibroInformacion.this,
                            "Error al buscar libros: " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
*/
        setOCL();


    }

    private void setOCL() {


        btnDespedir.setOnClickListener(v -> {
            Cuidador cuidador = CuidadoresRepository.getCuidadorById(cuidadorId);
            if(cuidador.isTrabajando()){
                cuidador.setTrabajando(false);
            }else{
                cuidador.setTrabajando(true);
            }
            cargarCuidador(cuidador);
        });


        btnAnhos.setOnClickListener(v -> {
           Cuidador cuidador = CuidadoresRepository.getCuidadorById(cuidadorId);
           int edad = cuidador.getEdad();
           cuidador.setEdad(edad+1);
           cargarCuidador(cuidador);
        });

        // Listener del botón volver que nos lleva a la pantalla de listado de libros
        btnVolver.setOnClickListener(v -> {
            Intent i = new Intent(activity_cuidador_informacion.this, Listado_Cuidadores.class);
            startActivity(i);
        });
    }

    private void cargarVM(Cuidador cuidador) {

        cuidadorViewModel = new ViewModelProvider(this).get(CuidadorViewModel.class);
        cuidadorViewModel.getCuidador().observe(this, new Observer<Cuidador>() {
            @Override
            public void onChanged(Cuidador cuidador) {
                if (cuidador != null) {
                    cargarCuidador(cuidador);
                }
            }
        });


        cuidadorViewModel.setCuidador(cuidador);
    }

    public void cargarInfoCuidador(Integer id) {
        Cuidador cuidador = CuidadoresRepository.getCuidadorById(id);
        if (cuidador == null) {
            Toast.makeText(activity_cuidador_informacion.this, "El repositorio devolvió null para el ID: " + id, Toast.LENGTH_LONG).show();
            return;
        }
        cargarVM(cuidador);
    }


    private void cargarCuidador(Cuidador result) {
        tvNombre.setText(result.getNombre());
        tvApellidos.setText(result.getApellidos());
        tvEdad.setText(String.valueOf(result.getEdad()));
        tvPais.setText(result.getPais());
        if (result.isTrabajando()) {
            tvTrabajando.setText("Trabajando");
        } else {
            tvTrabajando.setText("Despedido");
        }

        if (result.getFoto() == null || !result.getFoto().isEmpty()) {
            ivFotoCuidador.setImageResource(R.drawable.peruano);
        }
    }
}

