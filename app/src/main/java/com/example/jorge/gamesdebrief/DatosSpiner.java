package com.example.jorge.gamesdebrief;

import java.io.Serializable;

/**
 * Created by Jorge on 26/02/2016.
 */
public class DatosSpiner implements Serializable {
    private String texto;
    private int id;

    public DatosSpiner() {
        this.texto = null;
        this.id = 0;
    }

    public DatosSpiner(String texto, int id) {
        this.texto = texto;
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setId(int id) {
        this.id = id;
    }
}
