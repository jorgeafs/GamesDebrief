package ies.nervion.jorge.gamesdebrief.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ies.nervion.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import ies.nervion.jorge.gamesdebrief.clasesDeApoyo.Partida;
import ies.nervion.jorge.gamesdebrief.clasesDeApoyo.Resultados;

import java.io.Serializable;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jorge on 03/03/2016.
 */
public class DAL implements Serializable {
    private static final String[] GANADA = {"1"};
    private static final String[] EMPATADA = {"2"};
    private static final String[] PERDIDA = {"3"};

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
                        juegos.getLong(juegos.getColumnIndex(PartidasDB.Juego.JUEGO_ID))));
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
                        modos.getLong(modos.getColumnIndex(PartidasDB.ModoPartida.MODO_ID))));
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
                        mapas.getLong(mapas.getColumnIndex(PartidasDB.Mapa.MAPA_ID))));
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
                generos.add(new DatosSpiner(cursor.getString(cursor.getColumnIndex(PartidasDB.Genero.GENERO_NOMBRE)),
                        cursor.getLong(cursor.getColumnIndex(PartidasDB.Genero.GENERO_ID))));
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
                            cursor.getLong(cursor.getColumnIndex(PartidasDB.ModoPartida.MODO_ID))));
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
                lectura.add(Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_MODO))));
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
                    , PartidasDB.Mapa.MAPA_ID + " ASC "
            );
            if (cursor.moveToFirst()) {
                do {
                    mapas.add(new DatosSpiner(cursor.getString(cursor.getColumnIndex(PartidasDB.Mapa.MAPA_NOMBRE)),
                            cursor.getLong(cursor.getColumnIndex(PartidasDB.Mapa.MAPA_ID))));
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

    public long addModo(String nombre){
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
        Resultados devolver = new Resultados();
        devolver.setNombre(getJuegos());
        devolver.setGanadas(getPartidasGanadasPorJuego(devolver.getNombre()));
        devolver.setEmpatadas(getPartidasEmpatadasPorJuego(devolver.getNombre()));
        devolver.setPerdidas(getPartidasPerdidasPorJuego(devolver.getNombre()));
        return devolver;
    }


    public Resultados resultadosPorGenero() {
        Resultados devolver = new Resultados();
        devolver.setNombre(getGeneros());
        devolver.setGanadas(getPartidasGanadasPorGenero());
        devolver.setEmpatadas(getPartidasEmpatadasPorGenero());
        devolver.setPerdidas(getPartidasPerdidasPorGenero());
        return devolver;
    }

    public Resultados resultadosPorModos() {
        Resultados devolver = new Resultados();
        devolver.setNombre(getModos());
        devolver.setGanadas(getPartidasGanadasPorModos());
        devolver.setEmpatadas(getPartidasEmpatadasPorModos());
        devolver.setPerdidas(getPartidasPerdidasPorModos());
        return devolver;
    }

    private List<DatosSpiner> getPartidasPerdidasPorModos() {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[] columnas = {PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA , PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = ? "
                //argumentos del where
                , EMPATADA
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_RESULTADO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA))));
            }while(cursor.moveToNext());
        }
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver, getGeneros());
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> getPartidasGanadasPorModos() {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[] columnas = {PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA , PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = ? "
                //argumentos del where
                , GANADA
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_RESULTADO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA))));
            }while(cursor.moveToNext());
        }
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver, getGeneros());
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> getPartidasEmpatadasPorModos() {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[] columnas = {PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA , PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO+" = ? "
                //argumentos del where
                , EMPATADA
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_RESULTADO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA))));
            }while(cursor.moveToNext());
        }
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver, getGeneros());
        cursor.close();
        return devolver;
    }


    private List<DatosSpiner> getPartidasPerdidasPorGenero() {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[] columnas = {PartidasDB.Juego.JUEGO_ID , PartidasDB.Juego.JUEGO_ID_GENERO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Juego.JUEGO_TABLE_NAME
                //columnas
                , columnas
                //where
                , null
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Juego.JUEGO_ID_GENERO + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Juego.JUEGO_ID_GENERO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Juego.JUEGO_ID))));
            }while(cursor.moveToNext());
        }
        devolver = sustituyeJuegoId(devolver,PERDIDA[0]);
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver,getGeneros());
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> getPartidasEmpatadasPorGenero() {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[] columnas = {PartidasDB.Juego.JUEGO_ID , PartidasDB.Juego.JUEGO_ID_GENERO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Juego.JUEGO_TABLE_NAME
                //columnas
                , columnas
                //where
                , null
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Juego.JUEGO_ID_GENERO + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Juego.JUEGO_ID_GENERO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Juego.JUEGO_ID))));
            }while(cursor.moveToNext());
        }
        devolver = sustituyeJuegoId(devolver,EMPATADA[0]);
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver,getGeneros());
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> getPartidasGanadasPorGenero() {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[] columnas = {PartidasDB.Juego.JUEGO_ID , PartidasDB.Juego.JUEGO_ID_GENERO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Juego.JUEGO_TABLE_NAME
                //columnas
                , columnas
                //where
                , null
                //argumentos del where
                , null
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Juego.JUEGO_ID_GENERO + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Juego.JUEGO_ID_GENERO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Juego.JUEGO_ID))));
            }while(cursor.moveToNext());
        }
        devolver = sustituyeJuegoId(devolver, GANADA[0]);
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver,getGeneros());
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> sustituyeJuegoId(List<DatosSpiner> original, String resultado) {
        List<DatosSpiner> devolver = new ArrayList<>();
        List<DatosSpiner> comparador;
        if (resultado.equals(GANADA[0])) {
            comparador = getPartidasGanadasPorJuego(getJuegos());
        } else if (resultado.equals(EMPATADA[0])) {
            comparador = getPartidasEmpatadasPorJuego(getJuegos());
        } else {
            comparador = getPartidasPerdidasPorJuego(getJuegos());
        }
        if (original.size() > 0)
            for (DatosSpiner comparando : comparador) {
                for (DatosSpiner genero : original) {
                    if (comparando.getId() == genero.getId()) {
                        devolver.add(new DatosSpiner(comparando.getTexto(), Long.parseLong(genero.getTexto())));
                    }
                }
            }

        return devolver;
    }

    private List<DatosSpiner> getPartidasPerdidasPorJuego(List<DatosSpiner> nombre) {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[]columnas = {PartidasDB.Partida.PARTIDA_ID_JUEGO, PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO +" = ?"
                //argumentos del where
                , PERDIDA
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_JUEGO + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_RESULTADO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_ID_JUEGO))));
            }while(cursor.moveToNext());
        }
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver, nombre);
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> getPartidasEmpatadasPorJuego(List<DatosSpiner> nombre) {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[]columnas = {PartidasDB.Partida.PARTIDA_ID_JUEGO, PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO +" = ?"
                //argumentos del where
                , EMPATADA
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_JUEGO + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_RESULTADO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_ID_JUEGO))));
            }while(cursor.moveToNext());
        }
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver, nombre);
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> getPartidasGanadasPorJuego(List<DatosSpiner> nombre) {
        List<DatosSpiner>devolver = new ArrayList<>();
        String[]columnas = {PartidasDB.Partida.PARTIDA_ID_JUEGO, PartidasDB.Partida.PARTIDA_RESULTADO};
        Cursor cursor = this.helper.getReadableDatabase().query(
                //Nombre de la tabla
                PartidasDB.Partida.PARTIDA_TABLE_NAME
                //columnas
                , columnas
                //where
                , PartidasDB.Partida.PARTIDA_RESULTADO +" = ?"
                //argumentos del where
                , GANADA
                //group by
                , null
                //Having
                , null
                //OrderBy
                , PartidasDB.Partida.PARTIDA_ID_JUEGO + " ASC "
        );
        if(cursor.moveToFirst()){
            do{
                devolver.add(new DatosSpiner((Long.toString(cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_RESULTADO))))
                        ,cursor.getLong(cursor.getColumnIndex(PartidasDB.Partida.PARTIDA_ID_JUEGO))));
            }while(cursor.moveToNext());
        }
        devolver = sumaResultadoListaOrdenada(devolver);
        devolver = insertaCeros(devolver, nombre);
        cursor.close();
        return devolver;
    }

    private List<DatosSpiner> insertaCeros(List<DatosSpiner> modificar, List<DatosSpiner> dato) {
        List<DatosSpiner> devolver = new ArrayList<>();
        if(modificar.size()>0) {
            for (int i = 0, j = 0; i < dato.size() ; i++) {
                if(j>=modificar.size()) {
                    devolver.add(new DatosSpiner(Long.toString(0), dato.get(i).getId()));
                } else if (dato.get(i).getId() == modificar.get(j).getId()) {
                    devolver.add(modificar.get(j));
                    j++;
                } else {
                    devolver.add(new DatosSpiner(Long.toString(0), dato.get(i).getId()));
                }
            }
        }
        return devolver;
    }


    private List<DatosSpiner> sumaResultadoListaOrdenada(List<DatosSpiner> aSumar) {
        List<DatosSpiner> devolver = new ArrayList<>();
        int i = 0;
        for(DatosSpiner sumando: aSumar){
            if(devolver.size()==0){
                devolver.add(sumando);
            } else if(devolver.get(i).getId() == sumando.getId()){
                long incremento = Long.getLong(devolver.get(i).getTexto())+1;
                devolver.get(i).setTexto(Long.toString(incremento));
            }else {
                devolver.add(sumando);
                i++;
            }
        }
        return devolver;
    }

}
