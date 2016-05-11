package com.pap.pap_v01.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.pap.pap_v01.R;
import com.pap.pap_v01.modelo.Proprietario;

import java.util.List;

public class ProprietarioAdapter extends BaseAdapter {

    private List<Proprietario> proprietarios;
    private Activity activity;

    public ProprietarioAdapter(List<Proprietario> proprietarios, Activity activity){
        this.proprietarios = proprietarios;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return proprietarios.size();
    }

    @Override
    public Object getItem(int position) {
        return proprietarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return proprietarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Proprietario proprietario = proprietarios.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha =  inflater.inflate(R.layout.item_proprietario, null);

        TextView nome = (TextView) linha.findViewById(R.id.nome);
        nome.setText(proprietario.getNome());

        ImageView foto = (ImageView) linha.findViewById(R.id.foto);

        if(proprietario.getCaminhoFoto() != null) {
            Bitmap imagem = BitmapFactory.decodeFile(proprietario.getCaminhoFoto());
            Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 200, 200, true);
            foto.setImageBitmap(imagemReduzida);
        }else{
            foto.setImageResource(R.drawable.pessoa);
        }
        return linha;
    }
}
