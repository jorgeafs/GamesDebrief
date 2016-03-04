package com.example.jorge.gamesdebrief.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.MultiAdaptador;
import com.example.jorge.gamesdebrief.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuJuego.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuJuego#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuJuego extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Constantes
    private static final String SELECCIONE_MODO = "Seleccione un modo";
    private static final String SELECCIONE_JUEGO = "Seleccione un juego";
    private static final String AÑADA_UN_JUEGO = "Añada un juego nuevo";
    private static final String AÑADA_UN_MODO = "Añada un modo nuevo";
    private static final String JUEGO = "juego";
    private static final String MODO = "modo";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Spinner juegos;
    private Switch esUnSoloJugador;
    private Spinner modos;
    private Button rellenarPartida;
    private Boolean esSinglePlayer;
    private Context actualContext;
    private MultiAdaptador modosAdapter;
    private MultiAdaptador juegosAdapter;

    public MenuJuego() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuJuego.
     */
    public static MenuJuego newInstance(String param1, String param2) {
        MenuJuego fragment = new MenuJuego();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menuJuegos = inflater.inflate(R.layout.fragment_menu_juego, container, false);
        //preparamos los widgets
        preparaBoton(menuJuegos);
        preparaSwitch(menuJuegos);
        preparaModos(menuJuegos, inflater);
        preparaJuegos(menuJuegos, inflater);

        //devuelve la vista
        return menuJuegos;
    }

    private void preparaModos(View menuJuegos, LayoutInflater inflater) {
        modos = (Spinner) menuJuegos.findViewById(R.id.spinnerModos);
        List<DatosSpiner> empty = new ArrayList<>();
        empty.add(new DatosSpiner(SELECCIONE_JUEGO, 0));
        modosAdapter = new MultiAdaptador(empty, inflater, actualContext);
        modos.setAdapter(modosAdapter);
        modos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (modosAdapter.getItem(position).getId() == 0) {
                    rellenarPartida.setEnabled(false);
                } else if (modosAdapter.getItem(position).getId() == -1) {
                    rellenarPartida.setEnabled(false);
                    DialogFragment añadir = DialogAñadir.newInstance(MODO,((DatosSpiner)juegos.getSelectedItem()).getId());
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");
                } else {
                    rellenarPartida.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rellenarPartida.setEnabled(false);
            }
        });
        modos.setEnabled(false);
    }

    private void preparaJuegos(View menuJuegos, final LayoutInflater inflater) {
        juegos = (Spinner) menuJuegos.findViewById(R.id.spinnerJuegos);
        juegosAdapter = new MultiAdaptador(mListener.getJuegos(), inflater, actualContext);
        juegos.setAdapter(juegosAdapter);
        juegos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (juegosAdapter.getItem(position).getId() == 0) {
                    List<DatosSpiner> empty = new ArrayList<>(0);
                    empty.add(new DatosSpiner(SELECCIONE_JUEGO, 0));
                    modosAdapter = new MultiAdaptador(empty, inflater, actualContext);
                    modos.setAdapter(modosAdapter);
                    modosAdapter.notifyDataSetChanged();
                    modos.setEnabled(false);
                    esUnSoloJugador.setEnabled(false);
                } /*else if (juegosAdapter.getItem(position).getId() == -1) {
                    modos.setEnabled(false);
                    esUnSoloJugador.setEnabled(false);
                    DialogFragment añadir = DialogAñadir.newInstance(JUEGO);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");

                }*/ else if(juegosAdapter.getItem(position).getId()>0) {
                    modosAdapter = new MultiAdaptador(mListener.getModos(((DatosSpiner) juegos.getSelectedItem()).getId()), inflater, actualContext);
                    modos.setAdapter(modosAdapter);
                    modosAdapter.notifyDataSetChanged();
                    modos.setEnabled(true);
                    esUnSoloJugador.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                modos.setEnabled(false);
            }
        });
    }

    private void preparaSwitch(View menuJuegos) {
        esUnSoloJugador = (Switch) menuJuegos.findViewById(R.id.switchJugador);
        esUnSoloJugador.setChecked(true);
        esSinglePlayer = true;
        esUnSoloJugador.setEnabled(false);
        esUnSoloJugador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                esSinglePlayer = isChecked;
            }
        });
    }

    private void preparaBoton(View view){
        rellenarPartida = (Button) view.findViewById(R.id.crearPartida);
        rellenarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.lanzaInforme(((DatosSpiner) juegos.getSelectedItem()).getId(), ((DatosSpiner) modos.getSelectedItem()).getId(), esSinglePlayer);
            }
        });
        rellenarPartida.setEnabled(false);
    }

    public void actualizaJuego(){
        juegosAdapter = new MultiAdaptador(mListener.getJuegos(),LayoutInflater.from(actualContext),actualContext);
        juegos.setAdapter(juegosAdapter);
        juegosAdapter.notifyDataSetChanged();
    }

    public void actualizaModo(){
        modosAdapter = new MultiAdaptador(mListener.getModos(((DatosSpiner) juegos.getSelectedItem()).getId()),LayoutInflater.from(actualContext),actualContext);
        modos.setAdapter(modosAdapter);
        modosAdapter.notifyDataSetChanged();
    }
/*    private void botonActivo() {
        if(modos.getSelectedItem() != null){
            DatosSpiner modo = (DatosSpiner)modos.getSelectedItem();
            if(modo.getTexto() != SELECCIONE_MODO){
                rellenarPartida.setEnabled(true);
            }
        }else {
            rellenarPartida.setEnabled(false);
        }
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.actualContext = context;
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
        public List<DatosSpiner> getJuegos();
        public List<DatosSpiner> getModos(long juegoId);
        public void lanzaInforme(long juegoId, long modoId, boolean isSinglePlayer);
    }
}
