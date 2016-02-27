package com.example.jorge.gamesdebrief;

import android.provider.BaseColumns;

/**
 * Created by jclozano on 24/02/2016.
 */
public class PartidasDB  {
    private  PartidasDB () {}

    public static final class Tipo implements BaseColumns {
        private Tipo () {}
        public static final String TIPO_TABLE_NAME = "table_tipo";
        public static final String TIPO_ID = "tipo_id";
        public static final String TIPO_NOMBRE = "tipo_nombre";
    }

    public static final class Juego implements BaseColumns {
        private Juego () {}
        public static final String JUEGO_TABLE_NAME = "table_juego";
        public static final String JUEGO_ID = "juego_id";
        public static final String JUEGO_NOMBRE = "juego_nombre";
        public static final String JUEGO_IS_SINGLE_PLAYER = "juego_is_single_player";
        public static final String JUEGO_ID_TIPO = "juego_id_tipo";
    }

    public static final class ModoPartida implements BaseColumns {
        private ModoPartida () {}
        public static final String MODO_PARTIDA_TABLE_NAME = "table_modo_partida";
        public static final String MODO_ID = "modo_id";
        public static final String MODO_PARTIDA_NOMBRE = "modo_partida_nombre";
    }

    public static final class JuegoModoPartida implements BaseColumns {
        private JuegoModoPartida () {}
        public static final String JUEGO_MODO_PARTIDA_TABLE_NAME = "table_juego_modo_partida";
        public static final String JUEGO_MODO_PARTIDA_ID = "juego_modo_partida_id";
        public static final String JUEGO_MODO_PARTIDA_ID_JUEGO = "juego_modo_partida_id_juego";
        public static final String JUEGO_MODO_PARTIDA_ID_MODO_PARTIDA = "juego_modo_partida_id_modo_partida";
    }

    public static final class Mapa implements BaseColumns {
        private Mapa () {}
        public static final String MAPA_TABLE_NAME = "table_mapa";
        public static final String MAPA_ID = "mapa_id";
        public static final String MAPA_NOMBRE = "mapa_nombre";
    }

    public static final class Partida implements BaseColumns {
        private Partida (){}
        public static final String PARTIDA_TABLE_NAME = "table_partida";
        public static final String PARTIDA_ID = "partida_id";
        public static final String PARTIDA_ID_JUEGO = "partida_id_juego";
        public static final String PARTIDA_ID_MODO_PARTIDA = "partida_id_modo_partida";
        public static final String PARTIDA_ID_MAPA = "partida_id_mapa";
        public static final String PARTIDA_NUMERO_JUGADORES = "partida_numero_jugadores";
        public static final String PARTIDA_NUMERO_JUGADORES_ALIADOS = "partida_numero_jugadores_aliados";
        public static final String PARTIDA_NUMERO_JUGADORES_ENEMIGOS = "partida_numero_jugadores_enemigos";
        public static final String PARTIDA_ES_GANADA = "partida_es_ganada";
        public static final String PARTIDA_DESCRIPCION = "descripcion";
    }
}
