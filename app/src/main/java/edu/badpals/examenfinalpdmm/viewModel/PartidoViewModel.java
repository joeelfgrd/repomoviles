package edu.badpals.examenfinalpdmm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.badpals.examenfinalpdmm.model.Partido;

public class PartidoViewModel extends ViewModel {
    private final MutableLiveData<Partido> partido;

    public PartidoViewModel() {
        partido = new MutableLiveData<>();
    }

    public LiveData<Partido> getPartido() {
        return partido;
    }

    public void setPartido(Partido newPartido) {
        partido.setValue(newPartido);
    }


}

