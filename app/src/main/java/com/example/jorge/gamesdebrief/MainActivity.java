package com.example.jorge.gamesdebrief;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.Partida;
import com.example.jorge.gamesdebrief.fragments.Addjuego;
import com.example.jorge.gamesdebrief.fragments.DetallePartida;
import com.example.jorge.gamesdebrief.fragments.DialogAñadir;
import com.example.jorge.gamesdebrief.fragments.Estadisticas;
import com.example.jorge.gamesdebrief.fragments.MenuJuego;
import com.example.jorge.gamesdebrief.fragments.MenuPrincipal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MenuPrincipal.OnFragmentInteractionListener, MenuJuego.OnFragmentInteractionListener, DetallePartida.OnFragmentInteractionListener, DialogAñadir.OnDialogInteractionListener, Addjuego.OnFragmentInteractionListener, Estadisticas.OnFragmentInteractionListener {
    //Constants
    private static final String MENU_JUEGO = "menu_juego";
    private static final String MENU_PRINCIPAL = "menu_principal";
    private static final String DETALLE_PARTIDA = "detalle_partida";

    private static final String MAPA = "mapa";
    private static final String JUEGO = "juego";
    private static final String MODO = "modo";
    private static final String GENERO = "genero";

    private static final String SELECCIONE_MODO = "Seleccione un modo";
    private static final String SELECCIONE_JUEGO = "Seleccione un juego";
    private static final String AÑADA_UN_JUEGO = "Añada un juego nuevo";
    private static final String AÑADA_UN_MODO = "Añada un modo nuevo";


    //Variables
    MenuPrincipal menuPrincipal;
    MenuJuego menuJuego;
    DetallePartida detalles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.singleContainer)!= null && savedInstanceState == null){
            //primera carga de la app con el movil en posicion vertical y sin ningun dato salvado
            menuPrincipal = new MenuPrincipal();
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.singleContainer, menuPrincipal,null)
                    .commit();
        } else if (findViewById(R.id.singleContainer)!=null && savedInstanceState != null){
            //movil en posicion vertical con datos en el saveInstaceState, debemos ver que fragment lanzamos
        }else if(findViewById(R.id.singleContainer)== null && savedInstanceState != null){
            //movil en posicion horizontal y sin ningun dato salvado
        } else {
            //movil en posicion horizontal y con datos salvados
        }
    }

    private void tostadora(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


    private void limpiaBackStack(FragmentManager manager) {
        while (manager.getBackStackEntryCount() > 0){
            manager.popBackStackImmediate();
        }
    }

    @Override
    public void eligeJuego() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, new MenuJuego());
        transaction.addToBackStack(null);
        transaction.commit();

        //tostadora("esta opcion no esta disponible todavia");
    }

    @Override
    public void añadeJuego() {
        //tostadora("esta opcion no esta disponible todavia");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer,new Addjuego());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void eligeEstadiscasJuego() {
        //tostadora("esta opcion no esta disponible todavia");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, new Estadisticas());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override//Debe hacer una llamada a la base de datos y obtener la lista de DatosSpiner para juegos, por ahora se crea a mano
    public List<DatosSpiner> getJuegos() {
        List<DatosSpiner> juegos = new ArrayList<>();
        juegos.add(new DatosSpiner(SELECCIONE_JUEGO, 0));
        juegos.add(new DatosSpiner("Doom", 1));
        juegos.add(new DatosSpiner(AÑADA_UN_JUEGO, -1));
        return juegos;
    }

    @Override
    public List<DatosSpiner> getModos(int juegoId) {
        List<DatosSpiner> modos = new ArrayList<>();
        modos.add(new DatosSpiner(SELECCIONE_MODO,0));
        modos.add(new DatosSpiner("Historia", 1));
        modos.add(new DatosSpiner("Todos contra todos", 2));
        modos.add(new DatosSpiner(AÑADA_UN_MODO, -1));
        return modos;
    }

    @Override
    public void lanzaInforme(int juegoId, int modoId, boolean isSinglePlayer) {
        DetallePartida partida = DetallePartida.newInstance(juegoId, modoId, isSinglePlayer);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, partida);
        transaction.addToBackStack(null);
        transaction.commit();
        //tostadora("Opcion no disponible");
    }

    @Override
    public void guardarDatos(Partida informe) {
//Se guardan los datos y se vuelve al fragment principal
        FragmentManager manager = getFragmentManager();
        limpiaBackStack(manager); // me evito que el usuario pueda volver para atras
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.singleContainer, new MenuPrincipal());
        transaction.commit();
    }

    @Override
    public void faltanDatos(Partida aTostar) {
        String falta = getResources().getString(R.string.falta)+"\n";
        if(aTostar.getNumeroJugadoresAliados()==0){
            falta += "Numero de jugadores aliados \n";
        }
        if(aTostar.getNumeroJugadoresEnemigos() == 0) {
            falta += "Numero de jugadores enemigos \n";
        }
        if(aTostar.getNumeroJugadoresTotales()==0) {
            falta += "Numero de jugadores totales \n";
        }
        if(aTostar.getDescripccion() == null) {
            falta += "Descripcion de la partida\n";
        }
        if(aTostar.getResultado() == 0) {
            falta += "Resultado de la partida \n";
        }
        if (aTostar.getIdMapa() == 0) {
            falta += "Mapa de la partida";
        }
        tostadora(falta);
    }

    @Override
    public List<DatosSpiner> getMapas(int idJuegos) {
        List<DatosSpiner> mapas = new ArrayList<>();
        mapas.add(new DatosSpiner("Selecciona un mapa", 0));
        mapas.add(new DatosSpiner("Inferno", 1));
        mapas.add(new DatosSpiner("Hell's door", 2));
        mapas.add(new DatosSpiner("Añada nuevo mapa", -1));
        return mapas;
    }

    @Override
    public void sendNombre(String dato, String nombreDato) {
        switch (nombreDato) {
            case MAPA:
                tostadora("Falta añadir a db y recargar el spinner " + nombreDato);
                break;
            case JUEGO:
                tostadora("Falta añadir a db y recargar el spinner " + nombreDato);
                break;
            case MODO:
                tostadora("Falta añadir a db y recargar el spinner " + nombreDato);
                break;
            case GENERO:
                tostadora("Falta añadir a db y recargar el spinner " + nombreDato);
                break;
        }
    }

    @Override
    public List<DatosSpiner> getDatos(String nombreDato) {
        tostadora("Esto no esta disponible aun" + nombreDato);
        List<DatosSpiner> devolver = new ArrayList<>();
        devolver.add(new DatosSpiner("Hola", 0));
        return devolver;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void insertaDatos(String nombreJuego, DatosSpiner nombreGenero, List<DatosSpiner>... datos) {

    }

    @Override
    public void tostar(String frase) {
        tostadora(frase);
    }
}
