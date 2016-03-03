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
    private int ganadas;
    private int empatadas;
    private int perdidas;

    public Resultados() {
        this.nombre = new ArrayList<>();
        this.ganadas = 0;
        this.empatadas = 0;
        this.perdidas = 0;
    }

    public Resultados(List<DatosSpiner> nombre, int ganadas, int empatadas, int perdidas) {
        this.empatadas = empatadas;
        this.ganadas = ganadas;
        this.nombre = nombre;
        this.perdidas = perdidas;
    }

    public int getEmpatadas() {
        return empatadas;
    }

    public int getGanadas() {
        return ganadas;
    }

    public List<DatosSpiner> getNombre() {
        return nombre;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void setEmpatadas(int empatadas) {
        this.empatadas = empatadas;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public void setNombre(List<DatosSpiner> nombre) {
        this.nombre = nombre;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }
}
