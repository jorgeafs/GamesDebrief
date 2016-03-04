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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.DatosSpiner;
import com.example.jorge.gamesdebrief.clasesDeApoyo.MultiAdaptador;
import com.example.jorge.gamesdebrief.R;

import java.util.ArrayList;
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
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String GENERO = "genero";
    private static final String MODO = "modo";
    private static final String MAPA = "mapa";

    private String mParam1;
    private String mParam2;

    private EditText nombreJuego;
    private Spinner genero;
    private Spinner mapa;
    private Spinner modo;
    private ListView añadeModo;
    private ListView añadeMapa;
    private Button añadeJuego;
    private Context actualContex;
    private MultiAdaptador adaptadorGenero;
    private MultiAdaptador adaptadorModo;
    private MultiAdaptador adaptadorMapa;
    private MultiAdaptador adaptadorListaModo;
    private MultiAdaptador adaptadorListaMapa;
    private List<DatosSpiner> vacio = new ArrayList<>();


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
        preparaModo(view, inflater);
        preparaMapa(view, inflater);
        preparaListaMapa(view, inflater);
        preparaListaModo(view, inflater);
        preparaButton(view);

        return view;
    }

    private void preparaListaMapa(View view, LayoutInflater inflater) {
        añadeMapa = (ListView) view.findViewById(R.id.listMapa);
        adaptadorListaMapa = new MultiAdaptador(vacio,inflater,actualContex);
        añadeMapa.setAdapter(adaptadorListaMapa);
    }

    private void preparaListaModo(View view, LayoutInflater inflater) {
        añadeModo = (ListView) view.findViewById(R.id.listModo);
        adaptadorListaModo = new MultiAdaptador(vacio,inflater,actualContex);
        añadeModo.setAdapter(adaptadorListaModo);
    }

    private void preparaGenero(View view, LayoutInflater inflater) {
        genero = (Spinner) view.findViewById(R.id.genero);
        adaptadorGenero = new MultiAdaptador(mListener.getDatos(GENERO),inflater,actualContex);
        genero.setAdapter(adaptadorGenero);
        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adaptadorGenero.getItem(position).getId() == -1) {
                    DialogFragment añadir = DialogAñadir.newInstance(GENERO,1);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");
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
                    DialogFragment añadir = DialogAñadir.newInstance(MODO,-2);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");
                } else if (adaptadorModo.getItem(position).getId() > 0) {
                    if(!adaptadorListaModo.getList().contains(adaptadorModo.getItem(position))) {
                        adaptadorListaModo.getList().add(adaptadorModo.getItem(position));
                    } else {
                        adaptadorListaModo.getList().remove(adaptadorModo.getItem(position));
                    }
                    adaptadorListaModo.notifyDataSetChanged();
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
                    DialogFragment añadir = DialogAñadir.newInstance(MAPA, -2);
                    añadir.setShowsDialog(true);
                    añadir.show(getFragmentManager(), "dialog");
                } else if (adaptadorMapa.getItem(position).getId() > 0) {
                    if(!adaptadorListaMapa.getList().contains(adaptadorMapa.getItem(position))) {
                        adaptadorListaMapa.getList().add(adaptadorMapa.getItem(position));
                    } else {
                        adaptadorListaMapa.getList().remove(adaptadorMapa.getItem(position));
                    }
                    adaptadorListaMapa.notifyDataSetChanged();
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
                String juego = nombreJuego.getText().toString();
                if (genero.getSelectedItemPosition() > 0 && adaptadorListaMapa.getList().size() > 0 && adaptadorListaModo.getList().size() > 0) {
                    DatosSpiner generoJuego = (DatosSpiner) genero.getSelectedItem();
                    mListener.insertaJuegoCompleto(juego, generoJuego, adaptadorListaMapa.getList(), adaptadorListaModo.getList());
                } else {
                    String tostada ="";
                    if(genero.getSelectedItemPosition()==0)
                        tostada+="\nSe necesita un genero";
                    if(adaptadorListaMapa.getList().size() == 0)
                        tostada+="\nSe necesita al menos un mapa";
                    if(adaptadorListaModo.getList().size() == 0)
                        tostada+="\nSe necesita al menos un modo";
                    mListener.tostar(tostada);
                }
            }
        });
    }

    public void actualizaGenero(){
        adaptadorGenero = new MultiAdaptador(mListener.getDatos(GENERO), LayoutInflater.from(actualContex),actualContex);
        genero.setAdapter(adaptadorGenero);
        adaptadorGenero.notifyDataSetChanged();
    }

    public void actualizaMapa(String nombre){
        DatosSpiner nuevoMapa = new DatosSpiner(nombre,-2);
        List<DatosSpiner> old = adaptadorMapa.getList();
        old.add(nuevoMapa);
        adaptadorMapa = new MultiAdaptador(old,LayoutInflater.from(actualContex),actualContex);
        mapa.setAdapter(adaptadorMapa);
        adaptadorMapa.notifyDataSetChanged();
    }

    public void actualizaModo() {
        adaptadorModo = new MultiAdaptador(mListener.getDatos(MODO),LayoutInflater.from(actualContex),actualContex);
        modo.setAdapter(adaptadorModo);
        adaptadorModo.notifyDataSetChanged();
    }


/*
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
        public void insertaJuegoCompleto(String nombreJuego, DatosSpiner genero, List<DatosSpiner> mapasAdd, List<DatosSpiner> modosAdd);
        public void tostar(String frase);
    }
}
