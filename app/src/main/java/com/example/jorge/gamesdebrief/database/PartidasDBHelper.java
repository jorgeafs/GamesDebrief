package com.example.jorge.gamesdebrief.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jclozano on 24/02/2016.
 */
public class PartidasDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "partidasDB.db";
    private static final int DATABASE_VERSION = 1;

    public PartidasDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabla tipo
        db.execSQL("CREATE TABLE "+ PartidasDB.Genero.GENERO_TABLE_NAME
                +" ( "+
                    PartidasDB.Genero.GENERO_ID +" INTEGER PRIMARY KEY AUTOINCREMENT "
                    +" , "+ PartidasDB.Genero.GENERO_NOMBRE +" TEXT "
                +");");
        //tabla modo partida
        db.execSQL("CREATE TABLE "+ PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME
                +" ( "+
                    PartidasDB.ModoPartida.MODO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                    +" , "+PartidasDB.ModoPartida.MODO_PARTIDA_NOMBRE+" TEXT "
                +" );");
        //tabla Juego
        db.execSQL("CREATE TABLE "+PartidasDB.Juego.JUEGO_TABLE_NAME
                +" ( "+
                    PartidasDB.Juego.JUEGO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                    +" , "+PartidasDB.Juego.JUEGO_NOMBRE+" TETX "
                    +" , "+PartidasDB.Juego.JUEGO_ID_GENERO +" INTEGER "
                    +" , FOREIGN KEY ( "+ PartidasDB.Juego.JUEGO_ID_GENERO +" ) REFERENCES "+ PartidasDB.Genero.GENERO_TABLE_NAME +" ( "+ PartidasDB.Genero.GENERO_ID +" ) "
                +" );");
        //tabla mapa
        db.execSQL("CREATE TABLE "+PartidasDB.Mapa.MAPA_TABLE_NAME
                +" ( "+
                PartidasDB.Mapa.MAPA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                +" , "+PartidasDB.Mapa.MAPA_NOMBRE+" TETX "
                +" , "+PartidasDB.Mapa.MAPA_JUEGO_ID+" INTEGER"
                +" , FOREIGN KEY ( "+ PartidasDB.Mapa.MAPA_JUEGO_ID+" ) REFERENCES "+ PartidasDB.Juego.JUEGO_TABLE_NAME+" ( "+ PartidasDB.Juego.JUEGO_ID+" ) "
                +" );");
        //tabla partida
        db.execSQL("CREATE TABLE " + PartidasDB.Partida.PARTIDA_TABLE_NAME
                + " ( " +
                PartidasDB.Partida.PARTIDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + " , " + PartidasDB.Partida.PARTIDA_ID_JUEGO + " INTEGER "
                + " , " + PartidasDB.Partida.PARTIDA_ID_MAPA + " INTEGER "
                + " , " + PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA + " INTEGER "
                + " , " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES + " INTEGER "
                + " , " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ALIADOS + " INTEGER "
                + " , " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ENEMIGOS + " INTEGER "
                + " , " + PartidasDB.Partida.PARTIDA_RESULTADO + " NUMERIC "
                + " , " + PartidasDB.Partida.JUEGO_IS_SINGLE_PLAYER+" NUMERIC "
                + " , " + PartidasDB.Partida.PARTIDA_DESCRIPCION + " TEXT "
                + " , FOREIGN KEY ( " + PartidasDB.Partida.PARTIDA_ID_JUEGO + " ) REFERENCES " + PartidasDB.Juego.JUEGO_TABLE_NAME + " ( " + PartidasDB.Juego.JUEGO_ID + " ) "
                + " , FOREIGN KEY ( " + PartidasDB.Partida.PARTIDA_ID_MAPA + " ) REFERENCES " + PartidasDB.Mapa.MAPA_TABLE_NAME + " ( " + PartidasDB.Mapa.MAPA_ID + " ) "
                + " , FOREIGN KEY ( " + PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA + " ) REFERENCES " + PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME + " ( " + PartidasDB.ModoPartida.MODO_ID + " ) "
               + " );");
/* Codigo que creo que esta dandome el error, va de detras de las foreign keys
        + " , CHECK ( " + PartidasDB.Partida.JUEGO_IS_SINGLE_PLAYER +" IN (0 , 1))"
                + " , CHECK ( " + PartidasDB.Partida.PARTIDA_RESULTADO + " IN (1 , 2 , 3)"//1=GANADA,2=EMPATADA,3=PERDIDA
                + " , CHECK ( " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES + " == ( " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ALIADOS + " + " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ENEMIGOS + " ) "
*/


        //tabla juego modo partida
        db.execSQL("CREATE TABLE " + PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_TABLE_NAME
                + " ( " +
                PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
                +", "+PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_JUEGO+ " INTEGER"
                + ", " + PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_MODO + " INTEGER "
                + ", FOREIGN KEY ( " + PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_JUEGO + " ) REFERENCES " + PartidasDB.Juego.JUEGO_TABLE_NAME + " ( " + PartidasDB.Juego.JUEGO_ID + " ) "
                + ", FOREIGN KEY ( " + PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_ID_MODO + " ) REFERENCES " + PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME + " ( " + PartidasDB.ModoPartida.MODO_ID + " ) "
                + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        android.util.Log.w("Constants", "Upgrading database, which will destroy allold data");
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Mapa.MAPA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Genero.GENERO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Juego.JUEGO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.JuegoModoPartida.JUEGO_MODO_PARTIDA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Partida.PARTIDA_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
