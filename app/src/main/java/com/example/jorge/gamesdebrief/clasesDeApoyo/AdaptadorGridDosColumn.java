package com.example.jorge.gamesdebrief.clasesDeApoyo;

import android.content.Context;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jorge.gamesdebrief.R;

import java.util.List;

/**
 * Created by jclozano on 02/03/2016.
 */
public class AdaptadorGridDosColumn extends BaseAdapter {
    private List<String> modos;
    private List<String> mapas;
    private LayoutInflater myInflater;
    private Context actualContext;

    public AdaptadorGridDosColumn(List<String> modos, List<String> mapas, LayoutInflater myInflater, Context actualContext) {
        this.modos = modos;
        this.mapas = mapas;
        this.myInflater = myInflater;
        this.actualContext = actualContext;
    }

    @Override
    public int getCount() {
        return modos.size()+mapas.size();
    }

    public String getModos(int position){
        return  modos.get(position);
    }

    public String getMapa(int position){
        return mapas.get(position);
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
        TextView text;

        if(row == null){
            if(position%2 == 0){
                row = myInflater.inflate(R.layout.multi_adaptor_row, parent , false);
                text = (TextView) row.findViewById(R.id.spinnerText);
            }
        }

        return null;
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
