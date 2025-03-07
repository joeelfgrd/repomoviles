package edu.badpals.examenfinalpdmm.repository;

import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.model.Animal;

public class AnimalRepository {

    public static List<Animal> getAnimales() {
        List<Animal> listAnimales = new ArrayList<>();

        listAnimales.add(new Animal(1, "Tigre", "Panthera tigris", "Selva", false, "https://example.com/tigre.jpg"));
        listAnimales.add(new Animal(2, "Dodo", "Raphus cucullatus", "Isla Mauricio", true, "https://example.com/dodo.jpg"));
        listAnimales.add(new Animal(3, "Elefante Africano", "Loxodonta africana", "Sabana", false, "https://example.com/elefante.jpg"));
        listAnimales.add(new Animal(4, "Mamut", "Mammuthus primigenius", "Tundra", true, "https://example.com/mamut.jpg"));
        listAnimales.add(new Animal(5, "Águila Real", "Aquila chrysaetos", "Montañas", false, "https://example.com/aguila.jpg"));

        return listAnimales;
    }
}
