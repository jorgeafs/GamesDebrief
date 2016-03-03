package com.example.jorge.gamesdebrief.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.Partida;
import com.example.jorge.gamesdebrief.clasesDeApoyo.Resultados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 03/03/2016.
 */
public class DAL implements Serializable {
    private static final int GANADA = 1;
    private static final int EMPATADA = 2;
    private static final int PERDIDA = 3;

    private Context actualContext;
    private PartidasDBHelper helper;

    public DAL (Context context){
        this.actualContext = context;
        this.helper = new PartidasDBHelper(this.actualContext);
    }

    public List<DatosSpiner> getJuegos(){
        Cursor juegos = null;
        List<DatosSpiner> datos;
        String [] columnas = {PartidasDB.Juego.JUEGO_NOMBRE,PartidasDB.Juego.JUEGO_ID};
        juegos = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Juego.JUEGO_TABLE_NAME
                //columnas
                ,columnas
                //where
                ,null
                //argumentos del where
                ,null
                //group by
                ,null
                //Having
                ,null
                //OrderBy
                ,PartidasDB.Juego.JUEGO_ID+" ASC "
                );
        datos = new ArrayList<>();
        if(juegos.moveToFirst()){
            do {
                datos.add(new DatosSpiner(juegos.getString(juegos.getColumnIndex(PartidasDB.Juego.JUEGO_NOMBRE)),
                        juegos.getInt(juegos.getColumnIndex(PartidasDB.Juego.JUEGO_ID))));
            } while (juegos.moveToNext());
        }
        juegos.close();
        return datos;
    }

    public List<DatosSpiner> getModos(){
        Cursor modos = null;
        List<DatosSpiner> datos;
        String [] columnas = {PartidasDB.ModoPartida.MODO_PARTIDA_NOMBRE,PartidasDB.ModoPartida.MODO_ID};
        modos = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME
                //columnas
                ,columnas
                //where
                ,null
                //argumentos del where
                ,null
                //group by
                ,null
                //Having
                ,null
                //OrderBy
                ,PartidasDB.ModoPartida.MODO_ID+" ASC "
        );
        datos = new ArrayList<>();
        if(modos.moveToFirst()){
            do {
                datos.add(new DatosSpiner(modos.getString(modos.getColumnIndex(PartidasDB.ModoPartida.MODO_PARTIDA_NOMBRE)),
                        modos.getInt(modos.getColumnIndex(PartidasDB.ModoPartida.MODO_ID))));
            } while (modos.moveToNext());
        }
        modos.close();
        return datos;
    }

    public List<DatosSpiner> getMapas(){
        Cursor mapas = null;
        List<DatosSpiner> datos;
        String [] columnas = {PartidasDB.Mapa.MAPA_NOMBRE,PartidasDB.Mapa.MAPA_ID};
        mapas = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Mapa.MAPA_TABLE_NAME
                //columnas
                ,columnas
                //where
                ,null
                //argumentos del where
                ,null
                //group by
                ,null
                //Having
                ,null
                //OrderBy
                ,PartidasDB.Mapa.MAPA_ID+" ASC "
        );
        datos = new ArrayList<>();
        if(mapas.moveToFirst()){
            do {
                datos.add(new DatosSpiner(mapas.getString(mapas.getColumnIndex(PartidasDB.Mapa.MAPA_NOMBRE)),
                        mapas.getInt(mapas.getColumnIndex(PartidasDB.Mapa.MAPA_ID))));
            } while (mapas.moveToNext());
        }
        mapas.close();
        return datos;
    }

    public List<DatosSpiner> getGeneros () {
        Cursor cursor = null;
        List<DatosSpiner> generos;
        String [] columnas = {PartidasDB.Genero.GENERO_NOMBRE,PartidasDB.Genero.GENERO_ID};
        cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Genero.GENERO_TABLE_NAME
                //columnas
                ,columnas
                //where
                ,null
                //argumentos del where
                ,null
                //group by
                ,null
                //Having
                ,null
                //OrderBy
                ,PartidasDB.Genero.GENERO_ID+" ASC "
        );
        generos = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                generos.add(new DatosSpiner(cursor.getString(cursor.getColumnIndex(PartidasDB.Mapa.MAPA_NOMBRE)),
                        cursor.getInt(cursor.getColumnIndex(PartidasDB.Mapa.MAPA_ID))));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return generos;
    }

    public List<DatosSpiner> getModos(long juegoId){
        Cursor cursor;
        List<DatosSpiner> modos = new ArrayList<>();
        String idModos = getModosJuego(juegoId);
        String[] columnas = {PartidasDB.ModoPartida.MODO_PARTIDA_NOMBRE,PartidasDB.ModoPartida.MODO_ID};
        if (idModos!=null) {
            cursor = this.helper.getReadableDatabase().query(
                    //Nombre de la tabla
                    PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME
                    //columnas
                    , columnas
                    //where
                    , idModos
                    //argumentos del where
                    , null
                    //group by
                    , null
                    //Having
                    , null
                    //OrderBy
                    , PartidasDB.ModoPartida.MODO_ID + " ASC "
            );
            if (cursor.moveToFirst()) {
                do {
                    modos.add(new DatosSpiner(cursor.getString(cursor.getColumnIndex(PartidasDB.ModoPartida.MODO_PARTIDA_NOMBRE)),
                            cursor.getInt(cursor.getColumnIndex(PartidasDB.ModoPartida.MODO_ID))));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return  modos;
    }



    private String getModosJuego(long juegoId) {
        Cursor cursor;
        String idModos = null;
        List<String> lectura;
        String[] columnas = {PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_MODO};
        cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_TABLE_NAME
                //columnas
                ,columnas
                //where
                ,PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_JUEGO+" = "+juegoId
                //argumentos del where
                ,null
                //group by
                ,null
                //Having
                ,null
                //OrderBy
                ,PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_MODO+" ASC "
        );
        if(cursor.moveToFirst()){
            lectura = new ArrayList<>();
            idModos = " ";
            do{
                lectura.add(Integer.toString(cursor.getInt(cursor.getColumnIndex(PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_MODO))));
            }while(cursor.moveToNext());
            for(int i = 0; i<lectura.size();i++){
                if(i+1 == lectura.size()){
                    idModos +=" "+ PartidasDB.ModoPartida.MODO_ID+" = "+lectura.get(i);
                } else {
                    idModos +=" "+ PartidasDB.ModoPartida.MODO_ID+" = "+lectura.get(i)+" AND ";
                }
            }
            cursor.close();
        }
        return  idModos;
    }

    public List<DatosSpiner> getMapas(long juegoId){
        Cursor cursor;
        List<DatosSpiner> mapas = new ArrayList<>();
        String idMapas = " "+PartidasDB.Mapa.MAPA_JUEGO_ID+" = "+ juegoId;
        String[] columnas = {PartidasDB.Mapa.MAPA_NOMBRE,PartidasDB.Mapa.MAPA_ID};
            cursor = this.helper.getReadableDatabase().query(
                    //Nombre de la tabla
                    PartidasDB.Mapa.MAPA_TABLE_NAME
                    //columnas
                    , columnas
                    //where
                    , idMapas
                    //argumentos del where
                    , null
                    //group by
                    , null
                    //Having
                    , null
                    //OrderBy
                    , PartidasDB.ModoPartida.MODO_ID + " ASC "
            );
            if (cursor.moveToFirst()) {
                do {
                    mapas.add(new DatosSpiner(cursor.getString(cursor.getColumnIndex(PartidasDB.Mapa.MAPA_NOMBRE)),
                            cursor.getInt(cursor.getColumnIndex(PartidasDB.Mapa.MAPA_ID))));
                } while (cursor.moveToNext());
            }
        cursor.close();
        return  mapas;
    }

    public boolean addMapa(long idJuego, String nombre){
        long correcto = -1;
        if(mapaNoExiste(idJuego, nombre)) {
            ContentValues mapa = new ContentValues();
            mapa.put(PartidasDB.Mapa.MAPA_NOMBRE, nombre);
            mapa.put(PartidasDB.Mapa.MAPA_JUEGO_ID, idJuego);
            correcto = this.helper.getWritableDatabase().insert(PartidasDB.Mapa.MAPA_TABLE_NAME, null, mapa);
        }
        return correcto != -1;
    }

    private boolean mapaNoExiste(long idJuego, String nombre) {
        long correcto = -1;
        List<DatosSpiner> mapas = getMapas(idJuego);
        for (DatosSpiner mapa:mapas     ) {
            if(mapa.getTexto().equals(nombre)){
                correcto = mapa.getId();
            }
        }
        return correcto == -1;
    }

    public boolean addModo(long idJuego, String nombre){
        long correcto;
        long idModo = addModo(nombre);
        if(idModo != -1) {
            ContentValues modo = new ContentValues();
            modo.put(PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_JUEGO, idJuego);
            modo.put(PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_MODO, idModo);
            correcto = this.helper.getWritableDatabase().insert(PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_TABLE_NAME, null, modo);
        } else {
            correcto= idModo;
        }
        return correcto != -1;
    }

    private long addModo(String nombre){
        long idModo = -1;
        boolean existe = false;
        List<DatosSpiner> modos = getModos();
        for (int i = 0; i<modos.size() && !existe ;i++) {
            if(modos.get(i).getTexto().equals(nombre)){
                existe = true;
                idModo = modos.get(i).getId();
            }
        }
        if(!existe) {
            idModo = addModoaTabla(nombre);
        }
        return idModo;
    }

    private long addModoaTabla(String nombre) {
        long idModo;
        ContentValues modo = new ContentValues();
        modo.put(PartidasDB.ModoPartida.MODO_PARTIDA_NOMBRE,nombre);
        idModo = this.helper.getWritableDatabase().insert(PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME,null,modo);
        return idModo;
    }

    public boolean addJuego(String nombreJuego, DatosSpiner genero, List<DatosSpiner> mapasAdd, List<DatosSpiner> modosAdd){
        boolean correcto = false;
        boolean bandera = true;
        long idJuego;
        if(juegoNoExiste(nombreJuego)){
            idJuego = insertaJuego(nombreJuego,genero.getId());
            if(idJuego!=-1){
                for (int i = 0; i<mapasAdd.size() && bandera;i++ ) {
                    bandera = addMapa(idJuego,mapasAdd.get(i).getTexto());
                }
                if(bandera){
                    for (int i = 0; i<modosAdd.size() && bandera;i++ ){
                        bandera = addModo(idJuego,modosAdd.get(i).getTexto());
                    }
                    correcto = bandera;
                }
            }
        }
        return correcto;
    }

    private long insertaJuego(String nombreJuego, long generoId) {
        long correcto = -1;
        ContentValues juego = new ContentValues();
        juego.put(PartidasDB.Juego.JUEGO_NOMBRE, nombreJuego);
        juego.put(PartidasDB.Juego.JUEGO_ID_GENERO, generoId);
        correcto = this.helper.getWritableDatabase().insert(PartidasDB.Juego.JUEGO_TABLE_NAME,null,juego);
        return correcto;
    }

    private boolean juegoNoExiste(String nombreJuego) {
        boolean correcto = true;
        List<DatosSpiner> juegos = getJuegos();
        for (DatosSpiner juego: juegos) {
            if(juego.getTexto().equals(nombreJuego)){
                correcto = false;
            }
        }
        return correcto;
    }

    public boolean insertaGenero(String nombre){
        long correcto = -1;
        if(generoNoExiste(nombre)){
            ContentValues genero = new ContentValues();
            genero.put(PartidasDB.Genero.GENERO_NOMBRE, nombre);
            correcto = this.helper.getWritableDatabase().insert(PartidasDB.Genero.GENERO_TABLE_NAME,null,genero);
        }
        return correcto != -1 ;
    }

    private boolean generoNoExiste(String nombre) {
        boolean correcto = true;
        List<DatosSpiner> generos = getGeneros();
        for (DatosSpiner genero: generos) {
            if(genero.getTexto().equals(nombre)){
                correcto = false;
            }
        }
        return correcto;
    }

    public boolean addPartida(Partida informe){
        long correcto;
        ContentValues partida = new ContentValues();
        partida.put(PartidasDB.Partida.PARTIDA_ID_JUEGO,informe.getIdJuego());
        partida.put(PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA,informe.getIdModo());
        partida.put(PartidasDB.Partida.PARTIDA_ID_MAPA,informe.getIdMapa());
        partida.put(PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES,informe.getNumeroJugadoresTotales());
        partida.put(PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ALIADOS, informe.getNumeroJugadoresAliados());
        partida.put(PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ENEMIGOS, informe.getNumeroJugadoresEnemigos());
        partida.put(PartidasDB.Partida.PARTIDA_RESULTADO, informe.getResultado());
        partida.put(PartidasDB.Partida.JUEGO_IS_SINGLE_PLAYER, informe.isSinglePlayer());
        partida.put(PartidasDB.Partida.PARTIDA_DESCRIPCION, informe.getDescripccion());
        correcto = this.helper.getWritableDatabase().insert(PartidasDB.Partida.PARTIDA_TABLE_NAME,null,partida);
        return correcto != -1;
    }

    public Resultados resultadosPorJuegos(){
        Resultados devolver;
        int partidasGanadas = getPartidasGanadasPorJuego();
        int partidaEmpatadas = getPartidasEmpatadasPorJuego();
        int partidaPerdidas = getPartidasPerdidasPorJuego();
        devolver = new Resultados(getJuegos(),partidasGanadas,partidaEmpatadas,partidaPerdidas);
        return devolver;
    }

    public Resultados resultadosPorModos(){
        Resultados devolver;
        int partidasGanadas = getPartidasGanadasPorModo();
        int partidaEmpatadas = getPartidasEmpatadasPorModo();
        int partidaPerdidas = getPartidasPerdidasPorModo();
        devolver = new Resultados(getModos(),partidasGanadas,partidaEmpatadas,partidaPerdidas);
        return devolver;
    }
    public Resultados resultadosPorGenero(){
        Resultados devolver;
        int partidasGanadas = getPartidasGanadasPorGenero();
        int partidaEmpatadas = getPartidasEmpatadasPorGenero();
        int partidaPerdidas = getPartidasPerdidasPorGenero();
        devolver = new Resultados(getGeneros(),partidasGanadas,partidaEmpatadas,partidaPerdidas);
        return devolver;
    }

    private int getPartidasGanadasPorJuego() {
        int devolver = 0;
        String[] columnas = {PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO + " = " + GANADA
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_JUEGO + " ASC "
        );
        if(cursor.moveToFirst()){
          do {
              devolver++;
          }while(cursor.moveToNext());
        }
        return devolver;
    }

    private  int getPartidasEmpatadasPorJuego(){
        int devolver = 0;
        String[] columnas = {PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = "+EMPATADA
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_JUEGO+ " ASC "
        );
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;
    }

    private int getPartidasPerdidasPorJuego(){
        int devolver = 0;
        String[] columnas = {PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = "+PERDIDA
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_JUEGO+ " ASC "
        );
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;
    }

    private int getPartidasGanadasPorModo (){
        int devolver = 0;
        String[] columnas = {PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = "+GANADA
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA+ " ASC "
        );
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;
    }

    private int getPartidasEmpatadasPorModo(){
        int devolver = 0;
        String[] columnas = {PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = "+EMPATADA
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA+ " ASC "
        );
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;
    }

    private int getPartidasPerdidasPorModo() {
        int devolver = 0;
        String[] columnas = {PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = "+PERDIDA
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA+ " ASC "
        );
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;
    }

    private int getPartidasGanadasPorGenero() {

        int devolver = 0;
        String sentenciaSql = "Select "+PartidasDB.Partida.PARTIDA_RESULTADO+
                " FROM "+PartidasDB.Partida.PARTIDA_TABLE_NAME+" JOIN "+
                PartidasDB.Juego.JUEGO_TABLE_NAME+" ON "+ PartidasDB.Partida.PARTIDA_ID_JUEGO+" = "+PartidasDB.Juego.JUEGO_ID+
                " JOIN "+ PartidasDB.Genero.GENERO_TABLE_NAME+" ON "+PartidasDB.Juego.JUEGO_ID_GENERO +" = "+
                PartidasDB.Genero.GENERO_ID+ " WHERE "+PartidasDB.Partida.PARTIDA_RESULTADO+ " = "+GANADA+
                " ORDER BY "+PartidasDB.Genero.GENERO_ID;

        Cursor cursor = this.helper.getReadableDatabase().rawQuery(sentenciaSql,null);
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;

    }

    private int getPartidasEmpatadasPorGenero() {

        int devolver = 0;
        String sentenciaSql = "Select "+PartidasDB.Partida.PARTIDA_RESULTADO+
                " FROM "+PartidasDB.Partida.PARTIDA_TABLE_NAME+" JOIN "+
                PartidasDB.Juego.JUEGO_TABLE_NAME+" ON "+ PartidasDB.Partida.PARTIDA_ID_JUEGO+" = "+PartidasDB.Juego.JUEGO_ID+
                " JOIN "+ PartidasDB.Genero.GENERO_TABLE_NAME+" ON "+PartidasDB.Juego.JUEGO_ID_GENERO +" = "+
                PartidasDB.Genero.GENERO_ID+ " WHERE "+PartidasDB.Partida.PARTIDA_RESULTADO+ " = "+EMPATADA+
                " ORDER BY "+PartidasDB.Genero.GENERO_ID;

        Cursor cursor = this.helper.getReadableDatabase().rawQuery(sentenciaSql, null);
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;

    }

    private int getPartidasPerdidasPorGenero(){
        int devolver = 0;
        String sentenciaSql = "Select "+PartidasDB.Partida.PARTIDA_RESULTADO+
                " FROM "+PartidasDB.Partida.PARTIDA_TABLE_NAME+" JOIN "+
                PartidasDB.Juego.JUEGO_TABLE_NAME+" ON "+ PartidasDB.Partida.PARTIDA_ID_JUEGO+" = "+PartidasDB.Juego.JUEGO_ID+
                " JOIN "+ PartidasDB.Genero.GENERO_TABLE_NAME+" ON "+PartidasDB.Juego.JUEGO_ID_GENERO +" = "+
                PartidasDB.Genero.GENERO_ID+ " WHERE "+PartidasDB.Partida.PARTIDA_RESULTADO+ " = "+PERDIDA+
                " ORDER BY "+PartidasDB.Genero.GENERO_ID;

        Cursor cursor = this.helper.getReadableDatabase().rawQuery(sentenciaSql, null);
        if(cursor.moveToFirst()){
            do {
                devolver++;
            }while(cursor.moveToNext());
        }
        return devolver;
    }
}
