package edu.badpals.examenfinalpdmm.repository;

import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.model.Cuidador;

public class CuidadoresRepository {

    private static List<Cuidador> listCuidadores;

    public static List<Cuidador> getCuidadores() {
        if (listCuidadores == null) {
            listCuidadores = new ArrayList<>();
            listCuidadores.add(new Cuidador(1, "Juan", "Pérez García", 45, "España", true, "cuidador1"));
            listCuidadores.add(new Cuidador(2, "María", "López Fernández", 38, "México", true, "cuidador2"));
            listCuidadores.add(new Cuidador(3, "Carlos", "Rodríguez Díaz", 50, "Argentina", false, "cuidador3"));
            listCuidadores.add(new Cuidador(4, "Elena", "Gómez Martínez", 42, "Chile", true, "cuidador4"));
            listCuidadores.add(new Cuidador(5, "Fernando", "Ruiz Sánchez", 35, "Colombia", false, "cuidador5"));
        }
        return listCuidadores;
    }



    public static Cuidador getCuidadorById(int id) {
        for (Cuidador cuidador : getCuidadores()) {
            if (cuidador.getId_cuidador() == id) {
                return cuidador;
            }
        }
        return null;
    }

    public static void nuevoCuidadorDefault() {
        if (listCuidadores == null) {
            listCuidadores = new ArrayList<>();
        }

        int nuevoId = listCuidadores.size() + 1;
        listCuidadores.add(new Cuidador(nuevoId, "JP", "Rojas Vasquez", 35, "Colombia", false, "cuidador5"));
    }
    }



