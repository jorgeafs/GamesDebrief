package com.example.jorge.gamesdebrief;

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
        db.execSQL("CREATE TABLE "+PartidasDB.Tipo.TIPO_TABLE_NAME
                +" ( "+
                    PartidasDB.Tipo.TIPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                    +", "+PartidasDB.Tipo.TIPO_NOMBRE+" TEXT "
                +");");
        db.execSQL("CREATE TABLE "+ PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME
                +" ( "+
                    PartidasDB.ModoPartida.MODO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                    +", "+PartidasDB.ModoPartida.MODO_PARTIDA_NOMBRE+" TEXT "
                    +", "+PartidasDB.ModoPartida.MODO_JUEGO_ID+" TEXT "
                    +", FOREING KEY ( "+PartidasDB.ModoPartida.MODO_JUEGO_ID+" ) REFERENCES "+ PartidasDB.Juego.JUEGO_TABLE_NAME+" ( "+ PartidasDB.Juego.JUEGO_ID+" ) "
                +" );");
        db.execSQL("CREATE TABLE "+PartidasDB.Mapa.MAPA_TABLE_NAME
                +" ( "+
                    PartidasDB.Mapa.MAPA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                    +", "+PartidasDB.Mapa.MAPA_NOMBRE+" TETX "
                +" );");
        db.execSQL("CREATE TABLE "+PartidasDB.Juego.JUEGO_TABLE_NAME
                +" ( "+
                    PartidasDB.Juego.JUEGO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "
                    +", "+PartidasDB.Juego.JUEGO_NOMBRE+" TETX "
                    +", "+PartidasDB.Juego.JUEGO_IS_SINGLE_PLAYER+" NUMERIC "
                    +", "+PartidasDB.Juego.JUEGO_ID_TIPO+" INTEGER "
                    +", FOREIGN KEY ( "+ PartidasDB.Juego.JUEGO_ID_TIPO+" ) REFERENCES "+ PartidasDB.Tipo.TIPO_TABLE_NAME+" ( "+ PartidasDB.Tipo.TIPO_ID+" ) "
                    +", CHECK ( "+ PartidasDB.Juego.JUEGO_IS_SINGLE_PLAYER +" IN (0,1))"
                +" );");

        db.execSQL("CREATE TABLE " + PartidasDB.Partida.PARTIDA_TABLE_NAME
                + " ( " +
                PartidasDB.Partida.PARTIDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + ", " + PartidasDB.Partida.PARTIDA_ID_JUEGO + " INTEGER "
                + ", " + PartidasDB.Partida.PARTIDA_ID_MAPA + " INTEGER "
                + ", " + PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA + " INTEGER "
                + ", " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES + " INTEGER "
                + ", " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ALIADOS + " INTEGER "
                + ", " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ENEMIGOS + " INTEGER "
                + ", " + PartidasDB.Partida.PARTIDA_ES_GANADA + " NUMERIC "
                + ", " + PartidasDB.Partida.PARTIDA_DESCRIPCION + " TEXT "
                + ", FOREIGN KEY ( " + PartidasDB.Partida.PARTIDA_ID_JUEGO + " ) REFERENCES " + PartidasDB.Juego.JUEGO_TABLE_NAME + " ( " + PartidasDB.Juego.JUEGO_ID + " ) "
                + ", FOREIGN KEY ( " + PartidasDB.Partida.PARTIDA_ID_MAPA + " ) REFERENCES " + PartidasDB.Mapa.MAPA_TABLE_NAME + " ( " + PartidasDB.Mapa.MAPA_ID + " ) "
                + ", FOREIGN KEY ( " + PartidasDB.Partida.PARTIDA_ID_MODO_PARTIDA + " ) REFERENCES " + PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME + " ( " + PartidasDB.ModoPartida.MODO_ID + " ) "
                + ", CHECK ( " + PartidasDB.Partida.PARTIDA_ES_GANADA + " IN (0,1))"
                + ", CHECK ( " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES + " == ( " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ALIADOS + " + " + PartidasDB.Partida.PARTIDA_NUMERO_JUGADORES_ENEMIGOS + " ) "
                + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        android.util.Log.w("Constants", "Upgrading database, which will destroy allold data");
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Mapa.MAPA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Tipo.TIPO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.ModoPartida.MODO_PARTIDA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Juego.JUEGO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PartidasDB.Partida.PARTIDA_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
