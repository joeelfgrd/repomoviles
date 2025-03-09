package edu.badpals.examenfinalpdmm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.badpals.examenfinalpdmm.model.Animal;
import edu.badpals.examenfinalpdmm.model.Cuidador;

public class CuidadorViewModel extends ViewModel {
    private final MutableLiveData<Cuidador> cuidador;

    public CuidadorViewModel() {
        cuidador = new MutableLiveData<>();
    }

    public LiveData<Cuidador> getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador newCuidador) {
        cuidador.setValue(newCuidador);
    }
}
