package com.example.jorge.gamesdebrief.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jorge.gamesdebrief.R;

/**
 * Created by Jorge on 29/02/2016.
 */
public class DialogAñadir extends DialogFragment {
    private static final String TITULO = "Titulo";


    private OnDialogInteractionListener mListener;
    private String titulo;
    private Button añadir;
    private Button continuar;
    private Button noHacerNada;
    private TextView tituloMostrar;
    private EditText nombre;

    public DialogAñadir() {

    }

    public static DialogAñadir newInstance(String titulito){
        DialogAñadir dialog = new DialogAñadir();
        Bundle args = new Bundle();
        args.putString(TITULO, titulito);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            titulo = getArguments().getString(TITULO);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog,container,false);
        tituloMostrar = (TextView) view.findViewById(R.id.titulo);
        tituloMostrar.setText("Añadir nuevo nombre de "+titulo);
        nombre = (EditText) view.findViewById(R.id.nombre);
        logicaBotonAñadir(view);
        logicaBotonContinuar(view);
        logicaBotonNoHaceNada(view);
        return view;
    }

    /*private void logicaEditText(View view) {
        nombre = (EditText) view.findViewById(R.id.nombre);
        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    nombreDevolver = nombre.getText().toString();
                }
            }
        });
        nombre.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    nombreDevolver = nombre.getText().toString();
                }
                return false;
            }
        });
    }*/

    private void logicaBotonNoHaceNada(View view) {
        noHacerNada = (Button) view.findViewById(R.id.noHacerNada);
        noHacerNada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void logicaBotonContinuar(View view) {
        continuar = (Button) view.findViewById(R.id.añadirNuevoNombre);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString() != null && !nombre.getText().toString().isEmpty()) {
                    mListener.sendNombre(nombre.getText().toString(), titulo);
                    tituloMostrar.setText("Deme nuevo nombre de " + titulo);
                    nombre.setText("");
                }
            }
        });
    }

    private void logicaBotonAñadir(View view) {
        añadir = (Button) view.findViewById(R.id.añadirNombre);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString() != null && !nombre.getText().toString().isEmpty()) {
                    mListener.sendNombre(nombre.getText().toString(), titulo);
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDialogInteractionListener) {
            mListener = (OnDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnDialogInteractionListener {
        public void sendNombre(String dato, String nombreDato);
    }
}
