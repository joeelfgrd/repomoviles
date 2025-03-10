package edu.badpals.examenfinalpdmm.activitys;

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
import edu.badpals.examenfinalpdmm.model.Partido;
import edu.badpals.examenfinalpdmm.repository.PartidoRepository;
import edu.badpals.examenfinalpdmm.viewModel.PartidosViewModel;
import edu.badpals.examenfinalpdmm.Helpers;

public class ListadoPartidos extends AppCompatActivity {

    private RecyclerView recyclerViewPartidos;
    private Toolbar tb;
    private PartidosViewModel partidosViewModel;
    private PartidosApadter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_partidos);




        // Vinculación de vistas
        recyclerViewPartidos = findViewById(R.id.recyclerViewPartidos);
        recyclerViewPartidos.setLayoutManager(new LinearLayoutManager(this));


        tb = findViewById(R.id.toolbar);
        Helpers.cargarToolbar(this, tb);

        partidosViewModel = new ViewModelProvider(this).get(PartidosViewModel.class);
        adapter = new PartidosApadter();
        recyclerViewPartidos.setAdapter(adapter);

        partidosViewModel.getPartidos().observe(this, new Observer<List<Partido>>() {
            @Override
            public void onChanged(List<Partido> partidos) {
                adapter.setPartidos(partidos);
            }
        });

        cargarPartidos();


        partidosViewModel.getPartidos().observe(this, new Observer<List<Partido>>() {
            @Override
            public void onChanged(List<Partido> partidos) {
                adapter.setPartidos(partidos);
            }
        });

        cargarPartidos();

    }

    public void cargarPartidos() {
        List<Partido> partidos = PartidoRepository.getListPartidos();
        if (partidos.isEmpty()) {
            Toast.makeText(ListadoPartidos.this, "No hay partidos", Toast.LENGTH_SHORT).show();
        } else {
            partidosViewModel.setPartidos(partidos);
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
            encryptedEditor.putInt(InformacionPartido.PARTIDO_ID, id);
            encryptedEditor.apply();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class PartidosApadter extends RecyclerView.Adapter<PartidosApadter.MyViewHolder> {
        private List<Partido> partidos = new ArrayList<>();

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtEquipo1, txtEquipo2, txt_puntuacion1, txt_puntuacion2;
            Button btnDetalles;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                txtEquipo1 = itemView.findViewById(R.id.txtEquipo1);
                txtEquipo2 = itemView.findViewById(R.id.txtEquipo2);
                txt_puntuacion1 = itemView.findViewById(R.id.txt_puntuacion1);
                txt_puntuacion2 = itemView.findViewById(R.id.txt_puntuacion2);
                btnDetalles = itemView.findViewById(R.id.btnDetalles);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_listado_partidos, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MyViewHolder myvh = (MyViewHolder) holder;
            Partido partido = partidos.get(position);
            myvh.txtEquipo1.setText(partido.getEquipo1());
            myvh.txtEquipo2.setText(partido.getEquipo2());
            myvh.txt_puntuacion1.setText(String.valueOf(partido.getPuntuacion_eq1()));
            myvh.txt_puntuacion2.setText(String.valueOf(partido.getPuntuacion_eq2()));


            myvh.btnDetalles.setOnClickListener((view) -> {
                Intent intent = new Intent(ListadoPartidos.this, InformacionPartido.class);
                addToEncriptedSharePreferences(partido.getId());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return partidos.size();
        }

        void setPartidos(List<Partido> nuevosPartidos) {
            this.partidos = nuevosPartidos;
            notifyDataSetChanged();
        }
    }
}
