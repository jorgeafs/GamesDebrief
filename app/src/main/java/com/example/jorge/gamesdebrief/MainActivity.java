package com.example.jorge.gamesdebrief;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MenuPrincipal.OnFragmentInteractionListener, MenuJuego.OnFragmentInteractionListener, DetallePartida.OnFragmentInteractionListener {
    //Constants
    private static final String MENU_JUEGO = "menu_juego";
    private static final String MENU_PRINCIPAL = "menu_principal";
    private static final String DETALLE_PARTIDA = "detalle_partida";

    private static final String SELECCIONE_MODO = "Seleccione un modo";
    private static final String SELECCIONE_JUEGO = "Seleccione un juego";


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

    private void tostar(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eligeJuego() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, new MenuJuego());
        transaction.addToBackStack(null);
        transaction.commit();

        //tostar("esta opcion no esta disponible todavia");
    }

    @Override
    public void añadeJuego() {
        tostar("esta opcion no esta disponible todavia");
    }

    @Override
    public void eligeEstadiscasJuego() {
        tostar("esta opcion no esta disponible todavia");
    }

    @Override//Debe hacer una llamada a la base de datos y obtener la lista de DatosSpiner para juegos, por ahora se crea a mano
    public List<DatosSpiner> getJuegos() {
        List<DatosSpiner> juegos = new ArrayList<>();
        DatosSpiner datos = new DatosSpiner(SELECCIONE_JUEGO,0);
        juegos.add(datos);
        datos = new DatosSpiner("Doom",1);
        juegos.add(datos);
        return juegos;
    }

    @Override
    public List<DatosSpiner> getModos(int juegoId) {
        List<DatosSpiner> modos = new ArrayList<>();
        DatosSpiner datos = new DatosSpiner(SELECCIONE_MODO,0);
        modos.add(datos);
        datos = new DatosSpiner("Historia",1);
        modos.add(datos);
        datos = new DatosSpiner("Todos contra todos",2);
        modos.add(datos);
        return modos;
    }

    @Override
    public void lanzaInforme(int juegoId, int modoId, boolean isSinglePlayer) {
        DetallePartida partida = DetallePartida.newInstance(juegoId, modoId, isSinglePlayer);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, partida);
        transaction.addToBackStack(null);
        transaction.commit();
        //tostar("Opcion no disponible");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        tostar("nada.. no hay nada que tostar");
    }
}
