package edu.badpals.examenfinalpdmm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.model.Partido;

public class PartidosViewModel extends ViewModel {

    private final MutableLiveData<List<Partido>> partidos;
    public  PartidosViewModel() {
        partidos = new MutableLiveData<>(new ArrayList<>());
    }
    public LiveData<List<Partido>> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> nuevosPartidos) {
        partidos.setValue(nuevosPartidos);
    }
}

