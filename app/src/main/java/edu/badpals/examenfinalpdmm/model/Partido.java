package edu.badpals.examenfinalpdmm.model;

public class Partido {
    private int id;
    private String Equipo1;
    private String Equipo2;
    private int set1_eq1;
    private int set2_eq1;
    private int set3_eq1;
    private int Set1_eq2;
    private int Set2_eq2;
    private int Set3_eq2;
    private String fecha;
    private String arbitro;
    private int puntuacion_eq1;
    private int puntuacion_eq2;


    public Partido(int id, String equipo1, String equipo2, int set1_eq1, int set2_eq1, int set3_eq1, int set1_eq2, int set2_eq2, int set3_eq2, String fecha, String arbitro, int puntuacion_eq1, int puntuacion_eq2) {
        this.id = id;
        Equipo1 = equipo1;
        Equipo2 = equipo2;
        this.set1_eq1 = set1_eq1;
        this.set2_eq1 = set2_eq1;
        this.set3_eq1 = set3_eq1;
        Set1_eq2 = set1_eq2;
        Set2_eq2 = set2_eq2;
        Set3_eq2 = set3_eq2;
        this.fecha = fecha;
        this.arbitro = arbitro;
        this.puntuacion_eq1 = puntuacion_eq1;
        this.puntuacion_eq2 = puntuacion_eq2;
    }

    public Partido() {
    }



    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public int getSet1_eq1() {
        return set1_eq1;
    }

    public void setSet1_eq1(int set1_eq1) {
        this.set1_eq1 = set1_eq1;
    }

    public int getSet2_eq1() {
        return set2_eq1;
    }

    public void setSet2_eq1(int set2_eq1) {
        this.set2_eq1 = set2_eq1;
    }

    public int getSet1_eq2() {
        return Set1_eq2;
    }

    public void setSet1_eq2(int set1_eq2) {
        Set1_eq2 = set1_eq2;
    }

    public int getSet3_eq1() {
        return set3_eq1;
    }

    public void setSet3_eq1(int set3_eq1) {
        this.set3_eq1 = set3_eq1;
    }

    public int getSet2_eq2() {
        return Set2_eq2;
    }

    public void setSet2_eq2(int set2_eq2) {
        Set2_eq2 = set2_eq2;
    }

    public int getSet3_eq2() {
        return Set3_eq2;
    }

    public void setSet3_eq2(int set3_eq2) {
        Set3_eq2 = set3_eq2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipo1() {
        return Equipo1;
    }

    public void setEquipo1(String equipo1) {
        Equipo1 = equipo1;
    }

    public String getEquipo2() {
        return Equipo2;
    }

    public void setEquipo2(String equipo2) {
        Equipo2 = equipo2;
    }

    public int getPuntuacion_eq1() {
        return puntuacion_eq1;
    }

    public void setPuntuacion_eq1(int puntuacion_eq1) {
        this.puntuacion_eq1 = puntuacion_eq1;
    }

    public int getPuntuacion_eq2() {
        return puntuacion_eq2;
    }

    public void setPuntuacion_eq2(int puntuacion_eq2) {
        this.puntuacion_eq2 = puntuacion_eq2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
