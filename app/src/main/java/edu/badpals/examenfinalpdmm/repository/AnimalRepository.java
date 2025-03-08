package edu.badpals.examenfinalpdmm.repository;

import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.model.Animal;

public class AnimalRepository {

    private static List<Animal> listAnimales = null;

    public static List<Animal> getAnimales() {
        if (listAnimales == null) {
            listAnimales = new ArrayList<>();
            listAnimales.add(new Animal(1, "Tigre", "Panthera tigris", "Selva", false, "tigre"));
            listAnimales.add(new Animal(2, "Dodo", "Raphus cucullatus", "Isla Mauricio", true, "dodo"));
            listAnimales.add(new Animal(3, "Elefante Africano", "Loxodonta africana", "Sabana", false, "elefante"));
            listAnimales.add(new Animal(4, "Mamut", "Mammuthus primigenius", "Tundra", true, "mamut"));
            listAnimales.add(new Animal(5, "Águila Real", "Aquila chrysaetos", "Montañas", false, "aguila"));
        }
        return listAnimales;
    }

    public static Animal getAnimalById(int id) {
        for (Animal animal : getAnimales()) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }
}
