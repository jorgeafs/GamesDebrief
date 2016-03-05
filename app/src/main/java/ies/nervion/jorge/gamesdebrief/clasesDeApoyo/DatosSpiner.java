package ies.nervion.jorge.gamesdebrief.clasesDeApoyo;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Jorge on 26/02/2016.
 */
public class DatosSpiner implements Serializable, Comparable<DatosSpiner> {
    private String texto;
    private long id;

    public DatosSpiner() {
        this.texto = null;
        this.id = 0;
    }

    public DatosSpiner(String texto, long id) {
        this.texto = texto;
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public long getId() {
        return id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(DatosSpiner another) {
        int devolver = 1;
        if(this.id < another.getId()){
            devolver =-1;
        } else if(this.id == another.getId()){
            devolver = 0;
        }
        return devolver;
    }
}
