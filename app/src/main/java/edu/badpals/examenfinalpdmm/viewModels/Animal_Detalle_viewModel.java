package edu.badpals.examenfinalpdmm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.badpals.examenfinalpdmm.model.Animal;

public class Animal_Detalle_viewModel extends ViewModel{

    private final MutableLiveData<Animal> animal;

    public Animal_Detalle_viewModel() {
        animal = new MutableLiveData<>();
    }

    public LiveData<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(Animal newAnimal) {
        animal.setValue(newAnimal);
    }


}

