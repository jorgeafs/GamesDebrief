package com.example.jorge.gamesdebrief;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements MenuPrincipal.OnFragmentInteractionListener {
    //Constants
    private static final String MENU_JUEGO = "menu_juego";
    private static final String MENU_PRINCIPAL = "menu_principal";
    private static final String DETALLE_PARTIDA = "detalle_partida";

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
        tostar("esta opcion no esta disponible todavia");
    }

    @Override
    public void añadeJuego() {
        tostar("esta opcion no esta disponible todavia");
    }

    @Override
    public void eligeEstadiscasJuego() {
        tostar("esta opcion no esta disponible todavia");
    }
}
