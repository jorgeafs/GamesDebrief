package com.example.jorge.gamesdebrief;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jorge on 27/02/2016.
 */
public class MultiAdaptador extends BaseAdapter {
    //CONSTANTES
    private static final int NUMBER_OF_LAYOUT = 1;

    //VARIABLES
    private List<DatosSpiner> myDataSpiner;
    private LayoutInflater myInflater;
    Context context;

    public MultiAdaptador(List<DatosSpiner> myDataSpiner, LayoutInflater myInflater, Context context) {
        this.myDataSpiner = myDataSpiner;
        this.myInflater = myInflater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return myDataSpiner.size();
    }

    @Override
    public DatosSpiner getItem(int position) {
        return myDataSpiner.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount(){
        return NUMBER_OF_LAYOUT;
    }

    @Override
    public int getItemViewType(int position) {
        return (position%2);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        TextView texto;

        if(row == null){
            if(position%2 == 0) {
                row = myInflater.inflate(R.layout.multi_adaptor_row, parent , false);
                row.setBackgroundColor(Color.BLUE);
                texto = (TextView) row.findViewById(R.id.spinnerText);
                texto.setTextColor(Color.WHITE);
            } else {
                row = myInflater.inflate(R.layout.multi_adaptor_row, parent , false);
                row.setBackgroundColor(Color.CYAN);
                texto = (TextView) row.findViewById(R.id.spinnerText);
                texto.setTextColor(Color.BLACK);
            }
            holder = new ViewHolder();
            holder.setDataName((TextView) row.findViewById(R.id.spinnerText));
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        DatosSpiner misDatos = myDataSpiner.get(position);
        holder.getDataName().setText(misDatos.getTexto());
        return row;
    }

    public class ViewHolder {
        private TextView dataName;

        public TextView getDataName() {
            return dataName;
        }

        public void setDataName(TextView dataName) {
            this.dataName = dataName;
        }
    }
}