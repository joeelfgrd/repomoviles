package edu.badpals.examenfinalpdmm.activitys;

import static edu.badpals.examenfinalpdmm.Helpers.cargarToolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.time.LocalDate;

import edu.badpals.examenfinalpdmm.Helpers;
import edu.badpals.examenfinalpdmm.R;
import edu.badpals.examenfinalpdmm.model.Partido;
import edu.badpals.examenfinalpdmm.repository.PartidoRepository;
import edu.badpals.examenfinalpdmm.viewModel.PartidoViewModel;

public class InformacionPartido extends AppCompatActivity {
    private Toolbar tb;
    private Button btnVolver;
    private TextView NombreEQ1, NombreEQ2, tvResultadoEQ1, tvResultadoEQ2,tvSet1Eq1,tvSet1Eq2,tvSet2Eq1,tvSet2Eq2,tvSet3Eq1,tvSet3Eq2,tvArbitro,tvFechaPartido;

    public static final String PARTIDO_ID = "partidoId";
    public int partidoId = 0;

    private PartidoViewModel partidoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Llamamos a la vista
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_partido);

        tb = findViewById(R.id.toolbar);
        NombreEQ1 = findViewById(R.id.NombreEQ1);
        NombreEQ2 = findViewById(R.id.NombreEQ2);
        tvResultadoEQ1 = findViewById(R.id.tvResultadoEQ1);
        tvResultadoEQ2 = findViewById(R.id.tvResultadoEQ2);
        tvSet1Eq1 = findViewById(R.id.tvSet1Eq1);
        tvSet1Eq2 = findViewById(R.id.tvSet1Eq2);
        tvSet2Eq1 = findViewById(R.id.tvSet2Eq1);
        tvSet2Eq2 = findViewById(R.id.tvSet2Eq2);
        tvSet3Eq1 = findViewById(R.id.tvSet3Eq1);
        tvSet3Eq2 = findViewById(R.id.tvSet3Eq2);
        btnVolver = findViewById(R.id.btnVolver);
        tvArbitro = findViewById(R.id.tvArbitro);
        tvFechaPartido = findViewById(R.id.tvFechaPartido);


        partidoId = Helpers.getID_Partido(this);
        cargarInfoPartido(partidoId);
        setOCL();
        cargarToolbar(this, tb);


    }

    private void setOCL() {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(InformacionPartido.this, ListadoPartidos.class);
            startActivity(intent);
        });


    }

    public void cargarInfoPartido(int id){
        Partido partido = PartidoRepository.getPartidoById(id);

        if (partido == null) {
            Toast.makeText(this, "No se encontró el partido con ID: " + id, Toast.LENGTH_SHORT).show();
            return;
        }
        cargarVM(partido);

    }

    private void cargarVM(Partido partido) {

        partidoViewModel = new ViewModelProvider(this).get(PartidoViewModel.class);
        partidoViewModel.getPartido().observe(this, new Observer<Partido>() {
            @Override
            public void onChanged(Partido partido) {
                if (partido != null) {
                    cargarPartido(partido);
                }
            }
        });

        partidoViewModel.setPartido(partido);
    }
    public void comprobarPuntuaciones(Partido partido){
        if (partido.getPuntuacion_eq1()<partido.getPuntuacion_eq2()){
            tvResultadoEQ1.setTextColor(Color.RED);
            NombreEQ1.setTextColor(Color.RED);
            NombreEQ2.setTextColor(Color.GREEN);
            tvResultadoEQ2.setTextColor(Color.GREEN);
        }else{
            tvResultadoEQ1.setTextColor(Color.GREEN);
            NombreEQ1.setTextColor(Color.GREEN);
            NombreEQ2.setTextColor(Color.RED);
            tvResultadoEQ2.setTextColor(Color.RED);
        }
    }

    private void cargarPartido(Partido result) {
        NombreEQ1.setText(result.getEquipo1());
        NombreEQ2.setText(result.getEquipo2());
        tvResultadoEQ1.setText(String.valueOf(result.getPuntuacion_eq1()));
        tvResultadoEQ2.setText(String.valueOf(result.getPuntuacion_eq2()));


        tvSet1Eq1.setText(String.valueOf(result.getSet1_eq1()));
        tvSet1Eq2.setText(String.valueOf(result.getSet1_eq2()));

        tvSet2Eq1.setText(String.valueOf(result.getSet2_eq1()));
        tvSet2Eq2.setText(String.valueOf(result.getSet2_eq2()));

        tvSet3Eq1.setText(String.valueOf(result.getSet3_eq1()));
        tvSet3Eq2.setText(String.valueOf(result.getSet3_eq2()));

        tvArbitro.setText(result.getArbitro());
        tvFechaPartido.setText(LocalDate.now()+"");
        comprobarPuntuaciones(result);
    }

}