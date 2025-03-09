package edu.badpals.examenfinalpdmm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.model.Cuidador;

public class CuidadoresViewModel extends ViewModel {
    private final MutableLiveData<List<Cuidador>> cuidadores;

    public CuidadoresViewModel() {
        cuidadores = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Cuidador>> getCuidadores() {
        return cuidadores;
    }

    public void setCuidadores(List<Cuidador> newCuidadores) {
        cuidadores.setValue(newCuidadores);
    }

    public void addCuidador(Cuidador cuidador) {
        List<Cuidador> currentList = cuidadores.getValue();
        if (currentList != null) {
            currentList.add(cuidador);
            cuidadores.setValue(currentList);
        }
    }
}
