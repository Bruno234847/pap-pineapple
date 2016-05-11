package com.pap.pap_v01.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.pap.pap_v01.R;
import com.pap.pap_v01.modelo.Consulta;
import java.util.List;

public class ConsultaAdapter extends BaseAdapter {

    private List<Consulta> consultas;
    private Activity activityConsulta;

    public ConsultaAdapter(List<Consulta> consultas, Activity activityConsulta){
        this.consultas = consultas;
        this.activityConsulta = activityConsulta;
    }

    @Override
    public int getCount() {
        return consultas.size();
    }

    @Override
    public Object getItem(int position) {
        return consultas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return consultas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Consulta consulta = consultas.get(position);

        LayoutInflater inflaterConsulta = activityConsulta.getLayoutInflater();
        View linhaConsulta =  inflaterConsulta.inflate(R.layout.item_consulta, null);

        TextView nomeConsulta = (TextView) linhaConsulta.findViewById(R.id.nomeConsulta);
        nomeConsulta.setText(consulta.getNome_consulta()+ " - " + consulta.getProcedimento_consulta());

        return linhaConsulta;
    }
}


