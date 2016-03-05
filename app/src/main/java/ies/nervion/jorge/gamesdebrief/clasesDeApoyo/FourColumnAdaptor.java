package ies.nervion.jorge.gamesdebrief.clasesDeApoyo;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jorge.gamesdebrief.R;

/**
 * Created by Jorge on 04/03/2016.
 */
public class FourColumnAdaptor extends BaseAdapter {
    //CONSTANTES
    private static final int NUMBER_OF_LAYOUT = 1;

    private Resultados lista;
    private LayoutInflater myInflater;
    Context context;

    public FourColumnAdaptor(Context context, Resultados lista, LayoutInflater myInflater) {
        this.context = context;
        this.lista = lista;
        this.myInflater = myInflater;
    }

    public void setLista(Resultados lista) {
        this.lista = lista;
    }

    public void clearLista(){
        lista.getEmpatadas().clear();
        lista.getGanadas().clear();
        lista.getPerdidas().clear();
        lista.getNombre().clear();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        TextView dato;
        TextView partidaGanada;
        TextView partidaPerdida;
        TextView partidaEmpatada;

        if(row == null){
            if(position%2 == 0) {
                row = myInflater.inflate(R.layout.four_column_row, parent , false);
                row.setBackgroundColor(Color.BLUE);
                dato = (TextView) row.findViewById(R.id.columnDato);
                dato.setTextColor(Color.WHITE);
                partidaGanada = (TextView) row.findViewById(R.id.columnPG);
                partidaGanada.setTextColor(Color.WHITE);
                partidaEmpatada = (TextView) row.findViewById(R.id.columnPE);
                partidaEmpatada.setTextColor(Color.WHITE);
                partidaPerdida = (TextView) row.findViewById(R.id.columnPP);
                partidaPerdida.setTextColor(Color.WHITE);
            } else {
                row = myInflater.inflate(R.layout.four_column_row, parent , false);
                row.setBackgroundColor(Color.CYAN);
                dato = (TextView) row.findViewById(R.id.columnDato);
                dato.setTextColor(Color.BLACK);
                partidaGanada = (TextView) row.findViewById(R.id.columnPG);
                partidaGanada.setTextColor(Color.BLACK);
                partidaEmpatada = (TextView) row.findViewById(R.id.columnPE);
                partidaEmpatada.setTextColor(Color.BLACK);
                partidaPerdida = (TextView) row.findViewById(R.id.columnPP);
                partidaPerdida.setTextColor(Color.BLACK);
            }
            holder = new ViewHolder();
            holder.setDataName((TextView) row.findViewById(R.id.columnDato));
            holder.setDataPE((TextView) row.findViewById(R.id.columnPE));
            holder.setDataPP((TextView) row.findViewById(R.id.columnPP));
            holder.setDataPG((TextView) row.findViewById(R.id.columnPG));
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.getDataName().setText(lista.getNombre().get(position).getTexto());
        holder.getDataPE().setText(lista.getEmpatadas().get(position).getTexto());
        holder.getDataPG().setText(lista.getGanadas().get(position).getTexto());
        holder.getDataPP().setText(lista.getPerdidas().get(position).getTexto());

        return row;
    }

    public class ViewHolder {
        private TextView dataName;
        private TextView dataPG;
        private TextView dataPE;
        private TextView dataPP;

        public TextView getDataName() {
            return dataName;
        }

        public TextView getDataPE() {
            return dataPE;
        }

        public TextView getDataPG() {
            return dataPG;
        }

        public TextView getDataPP() {
            return dataPP;
        }

        public void setDataName(TextView dataName) {
            this.dataName = dataName;
        }

        public void setDataPE(TextView dataPE) {
            this.dataPE = dataPE;
        }

        public void setDataPG(TextView dataPG) {
            this.dataPG = dataPG;
        }

        public void setDataPP(TextView dataPP) {
            this.dataPP = dataPP;
        }
    }
}
