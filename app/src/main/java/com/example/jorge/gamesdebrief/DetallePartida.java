package com.example.jorge.gamesdebrief;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetallePartida.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetallePartida#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallePartida extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "juegoId";
    private static final String ARG_PARAM2 = "modoId";
    private static final String ARG_PARAM3 = "isSinglePlayer";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private boolean mParam3;

    private OnFragmentInteractionListener mListener;
    private Context actualContext;
    private EditText numeroTotalJugadores;
    private EditText numeroAliados;
    private EditText numeroEnemigos;
    private EditText descripcion;
    private Spinner resultado;
    private Button salvar;

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
    public static DetallePartida newInstance(int juegoId, int modoId, boolean isSinglePlayer) {
        DetallePartida fragment = new DetallePartida();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, juegoId);
        args.putInt(ARG_PARAM2, modoId);
        args.putBoolean(ARG_PARAM3,isSinglePlayer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getBoolean(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_partida, container, false);
        asociaEditviews(view);
        resultado = (Spinner) view.findViewById(R.id.spinnerResultado);
        preparaSpinner(inflater);
        preparaBoton(view);
        return view;
    }

    private void preparaBoton(View view) {
        salvar = (Button) view.findViewById(R.id.guardaDatos);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!numeroAliados.getText().toString().isEmpty()&&!numeroEnemigos.getText().toString().isEmpty()&&!numeroTotalJugadores.getText().toString().isEmpty()&&!descripcion.getText().toString().isEmpty())
                    mListener.guardarDatos();
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
    }

    private void preparaSpinner(LayoutInflater inflater) {
        DatosSpiner itemResultados = null;
        MultiAdaptador adaptador = null;
        List<DatosSpiner> resultados = new ArrayList<>();
        itemResultados = new DatosSpiner("Seleccione un resultado", 0);
        resultados.add(itemResultados);
        itemResultados = new DatosSpiner("Ganada", 1);
        resultados.add(itemResultados);
        itemResultados = new DatosSpiner("Empatada", 2);
        resultados.add(itemResultados);
        itemResultados = new DatosSpiner("Perdida", 3);
        resultados.add(itemResultados);
        adaptador = new MultiAdaptador(resultados,inflater,actualContext);
        resultado.setAdapter(adaptador);
    }

    private void preparaListeners(EditText editText) {
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
                    calculaTotales();
                }
                return false;//continua con los listeners
            }
        });
    }

    private void calculaTotales() {
        int numTotal=0;
        if((!numeroEnemigos.hasFocus()|| !numeroEnemigos.isInEditMode())&&(!numeroTotalJugadores.hasFocus()|| !numeroTotalJugadores.isInEditMode())&&(!numeroAliados.hasFocus()|| !numeroAliados.isInEditMode())){
            if(!numeroAliados.getText().toString().isEmpty()) {
                numTotal = Integer.parseInt(numeroAliados.getText().toString());

            }
            if(!numeroEnemigos.getText().toString().isEmpty()) {
                numTotal += Integer.parseInt(numeroEnemigos.getText().toString());
            }
            numeroTotalJugadores.setText(Integer.toString(numTotal));
        }
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
        public void guardarDatos();
    }
}
