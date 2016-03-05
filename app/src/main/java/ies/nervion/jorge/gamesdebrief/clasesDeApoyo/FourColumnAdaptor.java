package ies.nervion.jorge.gamesdebrief.clasesDeApoyo;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jorge.gamesdebrief.R;

import java.util.List;

/**
 * Created by Jorge on 04/03/2016.
 */
public class FourColumnAdaptor extends BaseAdapter {
    //CONSTANTES
    private static final int NUMBER_OF_LAYOUT = 1;

    private List<DatosSpiner> datos;
    private List<DatosSpiner> ganadas;
    private List<DatosSpiner> empatadas;
    private List<DatosSpiner> perdidas;
    private LayoutInflater myInflater;
    Context context;

    public FourColumnAdaptor(Context context, List<DatosSpiner> datos, List<DatosSpiner> empatadas, List<DatosSpiner> ganadas, LayoutInflater myInflater, List<DatosSpiner> perdidas) {
        this.context = context;
        this.datos = datos;
        this.empatadas = empatadas;
        this.ganadas = ganadas;
        this.myInflater = myInflater;
        this.perdidas = perdidas;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        holder.getDataName().setText(datos.get(position).getTexto());
        holder.getDataPE().setText(empatadas.get(position).getTexto());
        holder.getDataPG().setText(ganadas.get(position).getTexto());
        holder.getDataPP().setText(perdidas.get(position).getTexto());

        return row;
    }

    public void setListas(List<DatosSpiner> datos, List<DatosSpiner> ganadas, List<DatosSpiner> empatadas, List<DatosSpiner> perdidas) {
        this.datos = datos;
        this.ganadas = ganadas;
        this.empatadas = empatadas;
        this.perdidas = perdidas;
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
