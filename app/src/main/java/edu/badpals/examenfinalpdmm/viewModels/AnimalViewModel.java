package edu.badpals.examenfinalpdmm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.model.Animal;

public class AnimalViewModel extends ViewModel {

    private final MutableLiveData<List<Animal>> animales;

    public  AnimalViewModel() {
        animales = new MutableLiveData<>(new ArrayList<>());
    }
    public LiveData<List<Animal>> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> nuevosAnimales) {
        animales.setValue(nuevosAnimales);
    }
}
