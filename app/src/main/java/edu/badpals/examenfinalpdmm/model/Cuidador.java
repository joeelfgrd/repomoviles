package edu.badpals.examenfinalpdmm.model;

public class Cuidador {
    private int id_cuidador;
    private String nombre;
    private String apellidos;
    private int edad;
    private String pais;
    private Boolean trabajando;
    private String foto;

    public Cuidador() {
    }

    public Cuidador(int id_cuidador, String nombre, String apellidos, int edad, String pais, Boolean trabajando, String foto) {
        this.id_cuidador = id_cuidador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.pais = pais;
        this.trabajando = trabajando;
        this.foto = foto;
    }

    public int getId_cuidador() {
        return id_cuidador;
    }

    public void setId_cuidador(int id_cuidador) {
        this.id_cuidador = id_cuidador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Boolean isTrabajando() {
        return trabajando;
    }

    public void setTrabajando(Boolean trabajando) {
        this.trabajando = trabajando;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
