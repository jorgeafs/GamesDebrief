package com.example.jorge.gamesdebrief.clasesDeApoyo;

import org.w3c.dom.ls.LSInput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 03/03/2016.
 */
public class Resultados implements Serializable{
    private List<DatosSpiner> nombre;
    private List<DatosSpiner> ganadas;
    private List<DatosSpiner> empatadas;
    private List<DatosSpiner> perdidas;

    public Resultados() {
        this.nombre = new ArrayList<>();
        this.ganadas = new ArrayList<>();
        this.empatadas = new ArrayList<>();
        this.perdidas = new ArrayList<>();
    }

    public Resultados(List<DatosSpiner> nombre, List<DatosSpiner> ganadas, List<DatosSpiner> empatadas, List<DatosSpiner> perdidas) {
        this.empatadas = empatadas;
        this.ganadas = ganadas;
        this.nombre = nombre;
        this.perdidas = perdidas;
    }

    public List<DatosSpiner> getEmpatadas() {
        return empatadas;
    }

    public List<DatosSpiner> getGanadas() {
        return ganadas;
    }

    public List<DatosSpiner> getNombre() {
        return nombre;
    }

    public List<DatosSpiner> getPerdidas() {
        return perdidas;
    }

    public void setEmpatadas(List<DatosSpiner> empatadas) {
        this.empatadas = empatadas;
    }

    public void setGanadas(List<DatosSpiner> ganadas) {
        this.ganadas = ganadas;
    }

    public void setNombre(List<DatosSpiner> nombre) {
        this.nombre = nombre;
    }

    public void setPerdidas(List<DatosSpiner> perdidas) {
        this.perdidas = perdidas;
    }
}
