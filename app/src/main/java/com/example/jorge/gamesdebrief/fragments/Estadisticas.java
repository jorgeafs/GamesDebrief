package com.example.jorge.gamesdebrief.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jorge.gamesdebrief.R;
import com.example.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.MultiAdaptador;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Estadisticas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Estadisticas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Estadisticas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button juego;
    private Button genero;
    private Button modo;
    private TextView tipoDato;
    private ListView listaDato;
    private ListView listaGanada;
    private ListView listaEmpatada;
    private ListView listaPerdida;
    private MultiAdaptador adaptadorDatos;
    private MultiAdaptador adaptadorGanada;
    private MultiAdaptador adaptadorEmpatada;
    private MultiAdaptador adaptadorPerdida;
    private Context actualContext;
    private OnFragmentInteractionListener mListener;

    public Estadisticas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Estadisticas.
     */
    // TODO: Rename and change types and number of parameters
    public static Estadisticas newInstance(String param1, String param2) {
        Estadisticas fragment = new Estadisticas();
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
        View view = inflater.inflate(R.layout.fragment_estadisticas, container, false);
        preparaBotonJuego(view, inflater);
        preparaBotonGenero(view, inflater);
        preparaBotonModo(view, inflater);
        preparaListaDato(view, inflater);
        preparaListaGanada(view, inflater);
        preparaListaEmpatada(view, inflater);
        preparaListaPerdida(view, inflater);
        return view;
    }

    private void preparaBotonJuego(View view, LayoutInflater inflater){
        juego = (Button) view.findViewById(R.id.botonJuego);
        juego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void preparaBotonGenero(View view, LayoutInflater inflater){
        genero = (Button) view.findViewById(R.id.botonGenero);
        genero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void preparaBotonModo(View view, LayoutInflater inflater){
        modo = (Button) view.findViewById(R.id.botonModo);
        modo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void preparaListaDato(View view, LayoutInflater inflater){
        listaDato = (ListView) view.findViewById(R.id.listaDatos);
        adaptadorDatos = new MultiAdaptador(new ArrayList<DatosSpiner>(), inflater, actualContext);
        listaDato.setAdapter(adaptadorDatos);
    }

    private void preparaListaGanada(View view, LayoutInflater inflater){
        listaGanada = (ListView) view.findViewById(R.id.listaPartidasGanadas);
        adaptadorGanada = new MultiAdaptador(new ArrayList<DatosSpiner>(),inflater,actualContext);
        listaGanada.setAdapter(adaptadorGanada);
    }

    private void preparaListaEmpatada(View view, LayoutInflater inflater){
        listaEmpatada = (ListView) view.findViewById(R.id.listaPartidasEmpatadas);
        adaptadorEmpatada = new MultiAdaptador(new ArrayList<DatosSpiner>(),inflater,actualContext);
        listaEmpatada.setAdapter(adaptadorEmpatada);
    }

    private void preparaListaPerdida(View view, LayoutInflater inflater){
        listaPerdida = (ListView) view.findViewById(R.id.listaPartidasPerdidas);
        adaptadorPerdida = new MultiAdaptador(new ArrayList<DatosSpiner>(),inflater,actualContext);
        listaPerdida.setAdapter(adaptadorPerdida);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
