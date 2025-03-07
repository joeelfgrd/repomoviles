package edu.badpals.examenfinalpdmm.model;

public class Animal {
    private int id;
    private String nombre;
    private String raza;
    private String habitat;
    private boolean extinto;
    private String foto;

    public Animal() {
    }

    public Animal(int id, String nombre, String raza, String habitat, boolean extinto, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.habitat = habitat;
        this.extinto = extinto;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public boolean isExtinto() {
        return extinto;
    }

    public void setExtinto(boolean extinto) {
        this.extinto = extinto;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
