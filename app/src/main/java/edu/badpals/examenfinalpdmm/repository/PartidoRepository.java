package edu.badpals.examenfinalpdmm.repository;

import java.util.ArrayList;
import java.util.List;

import edu.badpals.examenfinalpdmm.model.Partido;

public class PartidoRepository {

    private static List<Partido> listPartidos = null;

    public static List<Partido> getListPartidos() {
        if (listPartidos == null) {
            listPartidos = new ArrayList<>();
            listPartidos.add(new Partido(1,"Equipo1","Equipo2",4,5,10,13,13,13,"10-12-2024","Arbitro1",0,3));
            listPartidos.add(new Partido(2,"Equipo3","Equipo4",12,11,13,5,2,1,"12-01-2020","Arbitro2",3,0));
            listPartidos.add(new Partido(3,"Equipo5","Equipo6",13,5,10,2,13,13,"10-12-2024","Arbitro1",1,2));
        }
        return listPartidos;
    }

    public static Partido getPartidoById(int id) {
        for (Partido partido : getListPartidos()) {
            if (partido.getId() == id) {
                return partido;
            }
        }
        return null;
    }
}
