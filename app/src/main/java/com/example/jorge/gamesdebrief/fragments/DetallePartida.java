package com.example.jorge.gamesdebrief.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.MultiAdaptador;
import com.example.jorge.gamesdebrief.clasesDeApoyo.Partida;
import com.example.jorge.gamesdebrief.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetallePartida.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetallePartida#newInstance} factory method to
 * create an instance of this fragment.
 * TODO:
 *  Añadir "vista elevada" para añadir un nuevo mapa....
 */
public class DetallePartida extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "juegoId";
    private static final String ARG_PARAM2 = "modoId";
    private static final String ARG_PARAM3 = "isSinglePlayer";


    private static final String MAPA = "mapa";


    // TODO: Rename and change types of parameters
    private long mParam1;
    private long mParam2;
    private boolean mParam3;

    private OnFragmentInteractionListener mListener;
    private Context actualContext;
    private EditText numeroTotalJugadores;
    private EditText numeroAliados;
    private EditText numeroEnemigos;
    private EditText descripcion;
    private Spinner resultado;
    private Spinner mapas;
    private Button salvar;
    private Partida informe;
    private MultiAdaptador adaptadorResultados;
    private MultiAdaptador adaptadorMapa;
    private String nombre = null;

    public DetallePartida() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param juegoId Parameter 1.
     * @param modoId Parameter 2.
     * @param isSinglePlayer Parameter 3.
     * @return A new instance of fragment DetallePartida.
     */
    // TODO: Rename and change types and number of parameters
    public static DetallePartida newInstance(long juegoId, long modoId, boolean isSinglePlayer) {
        DetallePartida fragment = new DetallePartida();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, juegoId);
        args.putLong(ARG_PARAM2, modoId);
        args.putBoolean(ARG_PARAM3,isSinglePlayer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getLong(ARG_PARAM1);
            mParam2 = getArguments().getLong(ARG_PARAM2);
            mParam3 = getArguments().getBoolean(ARG_PARAM3);
            informe = new Partida();
            informe.setIdJuego(mParam1);
            informe.setIdModo(mParam2);
            informe.setSinglePlayer(mParam3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_partida, container, false);
        asociaEditviews(view);
        preparaResultado(inflater, view);
        preparaMapa(inflater, view);
        preparaBoton(view);
        return view;
    }

    private void preparaBoton(View view) {
        salvar = (Button) view.findViewById(R.id.guardaDatos);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (informe.getNumeroJugadoresAliados() > 0 &&
                        informe.getNumeroJugadoresEnemigos() > 0 &&
                        informe.getNumeroJugadoresTotales() > 0 &&
                        !informe.getDescripccion().isEmpty() &&
                        informe.getResultado() > 0 &&
                        informe.getIdMapa() > 0) {
                    mListener.guardarDatos(informe);
                } else {
                    mListener.faltanDatos(informe);
                }
            }
        });
    }

    private void asociaEditviews(View view) {
        numeroTotalJugadores = (EditText) view.findViewById(R.id.jugadoresTotal);
        numeroEnemigos = (EditText) view.findViewById(R.id.jugadoresEnemigos);
        numeroAliados = (EditText) view.findViewById(R.id.jugadoresAliados);
        descripcion = (EditText) view.findViewById(R.id.descripcion);

       // preparaListeners(numeroTotalJugadores); no tiene sentido ponerle listener si los otros dos EditText le van a cambiar el valor
        preparaListeners(numeroAliados);
        preparaListeners(numeroEnemigos);
        preparaListeners(descripcion);
    }

    private void preparaResultado(LayoutInflater inflater, View view) {
        resultado = (Spinner) view.findViewById(R.id.spinnerResultado);
        DatosSpiner itemResultados = null;
        adaptadorResultados = null;
        List<DatosSpiner> resultados = new ArrayList<>();
        itemResultados = new DatosSpiner("Seleccione un resultado", 0);
        resultados.add(itemResultados);
        itemResultados = new DatosSpiner("Ganada", 1);
        resultados.add(itemResultados);
        itemResultados = new DatosSpiner("Empatada", 2);
        resultados.add(itemResultados);
        itemResultados = new DatosSpiner("Perdida", 3);
        resultados.add(itemResultados);
        adaptadorResultados = new MultiAdaptador(resultados,inflater,actualContext);
        resultado.setAdapter(adaptadorResultados);
        resultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adaptadorResultados.getItem(position).getId() != 0) {
                    informe.setResultado(adaptadorResultados.getItem(position).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void preparaMapa(final LayoutInflater inflater, View view) {
        mapas = (Spinner) view.findViewById(R.id.spinnerMapa);
        adaptadorMapa = new MultiAdaptador(mListener.getMapas(mParam1),inflater,actualContext);
        mapas.setAdapter(adaptadorMapa);
        mapas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adaptadorMapa.getItem(position).getId() > 0) {
                    informe.setIdMapa(adaptadorMapa.getItem(position).getId());
                } else if (adaptadorMapa.getItem(position).getId() == -1) {// marca de id para añadir un nuevo mapa...
                    //mostrar una vista parcial en la que se añada el nombre del mapa.
                    DialogFragment añadir = DialogAñadir.newInstance(MAPA,mParam1);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(),"dialog");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void preparaListeners(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    calculaTotales();
                }
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (v.getId() == descripcion.getId()) {
                        informe.setDescripccion(descripcion.getText().toString());
                    } else {
                        calculaTotales();
                    }
                    hideKeyboard(editText);
                }
                return false;//continua con los listeners
            }
        });
    }
    private void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) actualContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.

        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    private void calculaTotales() {
        int numAliados=0, numEnemigos = 0;
        if((!numeroEnemigos.hasFocus()|| !numeroEnemigos.isInEditMode())&&(!numeroTotalJugadores.hasFocus()|| !numeroTotalJugadores.isInEditMode())&&(!numeroAliados.hasFocus()|| !numeroAliados.isInEditMode())){
            if(!numeroAliados.getText().toString().isEmpty()) {
                numAliados = Integer.parseInt(numeroAliados.getText().toString());
                informe.setNumeroJugadoresAliados(Integer.parseInt(numeroAliados.getText().toString()));
            }
            if(!numeroEnemigos.getText().toString().isEmpty()) {
                numEnemigos = Integer.parseInt(numeroEnemigos.getText().toString());
                informe.setNumeroJugadoresEnemigos(Integer.parseInt(numeroEnemigos.getText().toString()));
            }

            numeroTotalJugadores.setText(Integer.toString(numAliados+numEnemigos));
            informe.setNumeroJugadoresTotales(Integer.parseInt(numeroTotalJugadores.getText().toString()));
        }
    }

    public void actualizaMapa() {
        adaptadorMapa = new MultiAdaptador(mListener.getMapas(mParam1),LayoutInflater.from(actualContext),actualContext);
        mapas.setAdapter(adaptadorMapa);
        adaptadorMapa.notifyDataSetChanged();
    }


/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        actualContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void guardarDatos(Partida informe);
        public void faltanDatos(Partida aTostar);
        public List<DatosSpiner> getMapas(long idJuegos);
    }
}
