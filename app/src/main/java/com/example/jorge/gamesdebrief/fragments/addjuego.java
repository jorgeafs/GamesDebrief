package com.example.jorge.gamesdebrief.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.jorge.gamesdebrief.R;
import com.example.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.MultiAdaptador;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Addjuego.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Addjuego#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Addjuego extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String GENERO = "genero";
    private static final String MODO = "modo";
    private static final String MAPA = "mapa";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText nombreJuego;
    private Spinner genero;
    private Spinner mapa;
    private Spinner modo;
    private GridView grid;
    private Button añadeJuego;
    private Context actualContex;
    private MultiAdaptador adaptadorGenero;
    private MultiAdaptador adaptadorModo;
    private MultiAdaptador adaptadorMapa;

    private OnFragmentInteractionListener mListener;

    public Addjuego() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Addjuego.
     */
    // TODO: Rename and change types and number of parameters
    public static Addjuego newInstance(String param1, String param2) {
        Addjuego fragment = new Addjuego();
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
        View view = inflater.inflate(R.layout.fragment_addjuego, container, false);
        nombreJuego = (EditText) view.findViewById(R.id.nombreJuego);
        preparaGenero(view,inflater);
        preparaModo(view,inflater);
        preparaMapa(view, inflater);
        preparaButton(view);

        return view;
    }

    private void preparaGenero(View view, LayoutInflater inflater) {
        genero = (Spinner) view.findViewById(R.id.genero);
        adaptadorGenero = new MultiAdaptador(mListener.getDatos(GENERO),inflater,actualContex);
        genero.setAdapter(adaptadorGenero);
        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adaptadorGenero.getItem(position).getId() == -1) {
                    DialogFragment añadir = DialogAñadir.newInstance(GENERO);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");
                } else if (adaptadorGenero.getItem(position).getId() == 0) {

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void preparaModo(View view, LayoutInflater inflater){
        modo = (Spinner) view.findViewById(R.id.nuevoModo);
        adaptadorModo = new MultiAdaptador(mListener.getDatos(MODO),inflater,actualContex);
        modo.setAdapter(adaptadorModo);
        modo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adaptadorModo.getItem(position).getId() == -1) {
                    DialogFragment añadir = DialogAñadir.newInstance(MODO);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");
                } else if (adaptadorModo.getItem(position).getId() == 0) {

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void preparaMapa(View view, LayoutInflater inflater){
        mapa = (Spinner) view.findViewById(R.id.nuevoMapa);
        adaptadorMapa = new MultiAdaptador(mListener.getDatos(MAPA),inflater,actualContex);
        mapa.setAdapter(adaptadorMapa);
        mapa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adaptadorMapa.getItem(position).getId() == -1) {
                    DialogFragment añadir = DialogAñadir.newInstance(MAPA);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");
                } else if (adaptadorMapa.getItem(position).getId() == 0) {

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void preparaButton(View view){
        añadeJuego = (Button) view.findViewById(R.id.añadirJuegoNuevo);
        añadeJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.insertaDatos(nombreJuego.getText().toString(),);
            }
        });
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
        actualContex = context;
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
        public List<DatosSpiner> getDatos(String nombreDato);
    }
}
