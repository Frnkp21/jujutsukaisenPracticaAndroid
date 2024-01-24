package com.example.jujutsuukaisenfinal;

public class Elemento2 {
    String nombre2;
    String descripcion2;
    String tipo;
    int imagenResId; // Nueva propiedad para el ID del recurso de la imagen

    public Elemento2(String nombre2, String descripcion2, String tipo, int imagenResId) {
        this.nombre2 = nombre2;
        this.descripcion2 = descripcion2;
        this.tipo = tipo;
        this.imagenResId = imagenResId;
    }

    public String getTipo() {
        return tipo;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
