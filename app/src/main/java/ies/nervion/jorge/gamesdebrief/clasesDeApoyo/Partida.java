package ies.nervion.jorge.gamesdebrief.clasesDeApoyo;

import java.io.Serializable;

/**
 * Created by Jorge on 29/02/2016.
 */
public class Partida implements Serializable{
    private long idJuego;
    private long idModo;
    private long idMapa;
    private int numeroJugadoresTotales;
    private int numeroJugadoresAliados;
    private int numeroJugadoresEnemigos;
    private long resultado;
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

    public Partida(long idJuego, long idModo, long idMapa, int numeroJugadoresTotales, int numeroJugadoresAliados, int numeroJugadoresEnemigos, long resultado, boolean singlePlayer, String descripccion) {
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

    public long getIdJuego() {
        return idJuego;
    }

    public long getIdModo() {
        return idModo;
    }

    public long getIdMapa() {
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

    public long getResultado() {
        return resultado;
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setIdJuego(long idJuego) {
        this.idJuego = idJuego;
    }

    public void setIdModo(long idModo) {
        this.idModo = idModo;
    }

    public void setIdMapa(long idMapa) {
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

    public void setResultado(long resultado) {
        this.resultado = resultado;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }
}
