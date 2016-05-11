package com.pap.pap_v01.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.pap.pap_v01.converter.ProprietarioConverter;
import com.pap.pap_v01.dao.ProprietarioDAO;
import com.pap.pap_v01.modelo.Proprietario;
import com.pap.pap_v01.suporte.WebCliente;

import java.util.List;

public class EnviaTaskProprietario extends AsyncTask<Object, Object, String> {

    private Context ctx;
    private ProgressDialog progress;

    public EnviaTaskProprietario(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute(){
        progress = ProgressDialog.show(ctx, "Aguarde...", "Enviando dados para o servidor");
    }

    @Override
    protected String doInBackground(Object...params) {

        try {
            ProprietarioDAO dao = new ProprietarioDAO(ctx);
            List<Proprietario> proprietarios = dao.buscaProprietario();
            dao.close();
            String json = new ProprietarioConverter().toJSON(proprietarios);
            String relatorio = new WebCliente("http://www.caelum.com.br/mobile").post(json);
            return relatorio;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
        protected void onPostExecute (String relatorio){
            progress.dismiss();
            Toast.makeText(ctx, "Dados enviados com sucesso", Toast.LENGTH_LONG).show();
        }

    }

