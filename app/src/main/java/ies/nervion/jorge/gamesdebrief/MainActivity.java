package ies.nervion.jorge.gamesdebrief;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jorge.gamesdebrief.R;

import ies.nervion.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import ies.nervion.jorge.gamesdebrief.clasesDeApoyo.Partida;
import ies.nervion.jorge.gamesdebrief.clasesDeApoyo.Resultados;
import ies.nervion.jorge.gamesdebrief.database.DAL;
import ies.nervion.jorge.gamesdebrief.fragments.Addjuego;
import ies.nervion.jorge.gamesdebrief.fragments.DetallePartida;
import ies.nervion.jorge.gamesdebrief.fragments.DialogAñadir;
import ies.nervion.jorge.gamesdebrief.fragments.Estadisticas;
import ies.nervion.jorge.gamesdebrief.fragments.MenuJuego;
import ies.nervion.jorge.gamesdebrief.fragments.MenuPrincipal;

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
    private MenuPrincipal menuPrincipal;
    private MenuJuego menuJuego;
    private DetallePartida detalle;
    private Addjuego addjuego;
    private Estadisticas estadisticas;
    private boolean editing;

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
        menuJuego = new MenuJuego();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, menuJuego);
        transaction.addToBackStack(null);
        transaction.commit();

        //tostadora("esta opcion no esta disponible todavia");
    }

    @Override
    public void añadeJuego() {
        //tostadora("esta opcion no esta disponible todavia");
        addjuego = new Addjuego();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer,addjuego);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void eligeEstadiscasJuego() {
        //tostadora("esta opcion no esta disponible todavia");
        estadisticas = new Estadisticas();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, estadisticas);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override//Debe hacer una llamada a la base de datos y obtener la lista de DatosSpiner para juegos, por ahora se crea a mano
    public List<DatosSpiner> getJuegos() {
        List<DatosSpiner> juegos = new ArrayList<>();
        juegos.add(new DatosSpiner(SELECCIONE_JUEGO, 0));
        juegos.addAll(new DAL(getApplicationContext()).getJuegos());
        juegos.add(new DatosSpiner(AÑADA_UN_JUEGO, -1));
        return juegos;
    }

    @Override
    public List<DatosSpiner> getModos(long juegoId) {
        List<DatosSpiner> modos = new ArrayList<>();
        modos.add(new DatosSpiner(SELECCIONE_MODO, 0));
        modos.addAll(new DAL(getApplicationContext()).getModos(juegoId));
        modos.add(new DatosSpiner(AÑADA_UN_MODO, -1));
        return modos;
    }

    @Override
    public void lanzaInforme(long juegoId, long modoId, boolean isSinglePlayer) {
        detalle = DetallePartida.newInstance(juegoId, modoId, isSinglePlayer);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.singleContainer, detalle);
        transaction.addToBackStack(null);
        transaction.commit();
        //tostadora("Opcion no disponible");
    }

    @Override
    public void guardarDatos(Partida informe) {
//Se guardan los datos y se vuelve al fragment principal
        boolean correcto = new DAL(getApplicationContext()).addPartida(informe);
        menuPrincipal = new MenuPrincipal();
        String error = "No se salvo bien el informe";
        FragmentManager manager = getFragmentManager();
        limpiaBackStack(manager); // me evito que el usuario pueda volver para atras
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.singleContainer, menuPrincipal);
        transaction.commit();
        if(!correcto){
            tostadora(error);
        }
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
    public List<DatosSpiner> getMapas(long idJuegos) {
        List<DatosSpiner> mapas = new ArrayList<>();
        mapas.add(new DatosSpiner("Selecciona un mapa", 0));
        mapas.addAll(new DAL(getApplicationContext()).getMapas(idJuegos));
        mapas.add(new DatosSpiner("Añada nuevo mapa", -1));
        return mapas;
    }

    @Override
    public void sendNombre(String dato, String nombreDato, long identityJuego) {
        boolean conseguido = false;
        long id;
        switch (nombreDato) {
            case MAPA:
                if(identityJuego >0 ){
                    new DAL(getApplicationContext()).addMapa(identityJuego,dato);
                    detalle.actualizaMapa();
                }else{
                    addjuego.actualizaMapa(dato);
                }
                break;
            case MODO:
                if(identityJuego> 0) {
                    conseguido = new DAL(getApplicationContext()).addModo(identityJuego, dato);
                    if (!conseguido) {
                        tostadora("No se pudo añadir el modo " + dato);
                    } else {
                        menuJuego.actualizaModo();
                    }
                } else {
                    id = new DAL(getApplicationContext()).addModo(dato);
                    if(id==-1){
                        tostadora("No se pudo añadir el modo "+dato);
                    } else {
                        addjuego.actualizaModo();
                    }
                }
                break;
            case GENERO:
                conseguido = new DAL(getApplicationContext()).insertaGenero(dato);
                if(!conseguido){
                tostadora("No se pudo añadir el genero " + dato);
                } else {
                    addjuego.actualizaGenero();
                }
                break;
        }
    }

    @Override
    public List<DatosSpiner> getDatos(String nombreDato) {
        List<DatosSpiner> devolver = new ArrayList<>();
        switch (nombreDato){
            case GENERO:
                devolver = new DAL(getApplicationContext()).getGeneros();
                break;
            case MODO:
                devolver = new DAL(getApplicationContext()).getModos();
                break;
            case MAPA:
                devolver= new DAL(getApplicationContext()).getMapas();
                break;
        }
        return devolver;
    }

    @Override
    public void insertaJuegoCompleto(String nombreJuego, DatosSpiner genero, List<DatosSpiner> mapasAdd, List<DatosSpiner> modosAdd) {
        new DAL(getApplicationContext()).addJuego(nombreJuego,genero,mapasAdd,modosAdd);
        if(editing){
            limpiaBackStack(getFragmentManager());
            eligeJuego();
        }
    }

    @Override
    public void tostar(String frase) {
        tostadora(frase);
    }


    @Override
    public Resultados estadisticaJuegos() {
        Resultados resultados = new DAL(getApplicationContext()).resultadosPorJuegos();
        return resultados;
    }

    @Override
    public Resultados estadisticasGenero() {
        Resultados resultados = new DAL(getApplicationContext()).resultadosPorGenero();
        return resultados;
    }

    @Override
    public Resultados estadisticasModo() {
        Resultados resultados = new DAL(getApplicationContext()).resultadosPorModos();
        return resultados;
    }

    @Override
    public void añadeJuegoMenuJuego(boolean editando) {
        this.editing = editando;
        añadeJuego();
    }
}
