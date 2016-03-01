package com.example.jorge.gamesdebrief.clasesDeApoyo;

/**
 * Created by Jorge on 29/02/2016.
 */
public class Partida {
    private int idJuego;
    private int idModo;
    private int idMapa;
    private int numeroJugadoresTotales;
    private int numeroJugadoresAliados;
    private int numeroJugadoresEnemigos;
    private int resultado;
    private boolean singlePlayer;
    private String descripccion;

    public Partida() {
        this.idJuego = 0;
        this.idModo = 0;
        this.idMapa = 0;
        this.numeroJugadoresTotales = 0;
        this.numeroJugadoresAliados = 0;
        this.numeroJugadoresEnemigos = 0;
        this.resultado = 0;
        this.singlePlayer = false;
        this.descripccion = null;
    }

    public Partida(int idJuego, int idModo, int idMapa, int numeroJugadoresTotales, int numeroJugadoresAliados, int numeroJugadoresEnemigos, int resultado, boolean singlePlayer, String descripccion) {
        this.idJuego = idJuego;
        this.idModo = idModo;
        this.idMapa = idMapa;
        this.numeroJugadoresTotales = numeroJugadoresTotales;
        this.numeroJugadoresAliados = numeroJugadoresAliados;
        this.numeroJugadoresEnemigos = numeroJugadoresEnemigos;
        this.resultado = resultado;
        this.singlePlayer = singlePlayer;
        this.descripccion = descripccion;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public int getIdModo() {
        return idModo;
    }

    public int getIdMapa() {
        return idMapa;
    }

    public int getNumeroJugadoresTotales() {
        return numeroJugadoresTotales;
    }

    public int getNumeroJugadoresAliados() {
        return numeroJugadoresAliados;
    }

    public int getNumeroJugadoresEnemigos() {
        return numeroJugadoresEnemigos;
    }

    public int getResultado() {
        return resultado;
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public void setIdModo(int idModo) {
        this.idModo = idModo;
    }

    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }

    public void setNumeroJugadoresTotales(int numeroJugadoresTotales) {
        this.numeroJugadoresTotales = numeroJugadoresTotales;
    }

    public void setNumeroJugadoresAliados(int numeroJugadoresAliados) {
        this.numeroJugadoresAliados = numeroJugadoresAliados;
    }

    public void setNumeroJugadoresEnemigos(int numeroJugadoresEnemigos) {
        this.numeroJugadoresEnemigos = numeroJugadoresEnemigos;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }
}
