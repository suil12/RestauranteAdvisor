package com.example.prueba2.model;

public class Foto {
    String nombre, precio, descripcion , coordenaday, coordenadax, photo;

    public Foto(){}

    public Foto(String nombre, String precio, String descripcion, String coordenaday, String coordenadax) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.coordenaday = coordenaday;
        this.coordenadax = coordenadax;
        this.photo = photo;

    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCoordenaday() {
        return coordenaday;
    }

    public String getCoordenadax() {
        return coordenadax;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCoordenaday(String coordenaday) {
        this.coordenaday = coordenaday;
    }

    public void setCoordenadax(String coordenadax) {
        this.coordenadax = coordenadax;
    }
}
