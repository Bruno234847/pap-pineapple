package com.pap.pap_v01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import com.pap.pap_v01.modelo.Consulta;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends SQLiteOpenHelper {

    public ConsultaDAO(Context context) {
        //Contexto - nome do banco - versão
        super(context, "Consulta", null, 4);
    }

//CRIAR BANCO

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlConsulta = "CREATE TABLE  Consulta (id INTEGER PRIMARY KEY, nome_consulta TEXT NOT NULL, data_consulta TEXT, hora_consulta TEXT, procedimento_consulta TEXT, observacao_consulta TEXT);";
        db.execSQL(sqlConsulta);
    }

//ATUALIZAR O BANCO - DROP NA TABELA

    @Override
    public void onUpgrade(SQLiteDatabase dbConsulta, int oldVersion, int newVersion) {
        String sqlConsulta = "DROP TABLE IF EXISTS Consulta";
        dbConsulta.execSQL(sqlConsulta);
        onCreate(dbConsulta);
    }

//INSERIR DADOS NO BANCO

    public void insereConsulta(Consulta consulta) {
        SQLiteDatabase dbConsulta = getWritableDatabase();
        ContentValues dadosConsulta = pegaDadosDoConsulta(consulta);
        dbConsulta.insert("Consulta", null, dadosConsulta);
    }

//METODO PARA INSERIR DADOS NO BANCO ATRAVÉS DO EXTRACT

    @NonNull
    private ContentValues pegaDadosDoConsulta(Consulta consulta) {
        ContentValues dadosConsulta = new ContentValues();
        dadosConsulta.put("nome_consulta", consulta.getNome_consulta());
        dadosConsulta.put("data_consulta", consulta.getData_consulta());
        dadosConsulta.put("hora_consulta", consulta.getHora_consulta());
        dadosConsulta.put("procedimento_consulta", consulta.getProcedimento_consulta());
        dadosConsulta.put("observacao_consulta", consulta.getObservacao_consulta());
        return dadosConsulta;
    }

//METODOS PARA BUSCAR DADOS NO BANCO

    public List<Consulta> buscaConsulta() {
        String sqlConsulta = "SELECT * FROM Consulta;";
        SQLiteDatabase dbConsulta = getReadableDatabase();
        //Cursor aponta as linhas do banco
        Cursor cConsulta = dbConsulta.rawQuery(sqlConsulta, null);
        List<Consulta> consultas = new ArrayList<Consulta>();

        while(cConsulta.moveToNext()){

            Consulta consulta = new Consulta();

            consulta.setId(cConsulta.getLong(cConsulta.getColumnIndex("id")));
            consulta.setNome_consulta(cConsulta.getString(cConsulta.getColumnIndex("nome_consulta")));
            consulta.setData_consulta(cConsulta.getString(cConsulta.getColumnIndex("data_consulta")));
            consulta.setHora_consulta(cConsulta.getString(cConsulta.getColumnIndex("hora_consulta")));
            consulta.setProcedimento_consulta(cConsulta.getString(cConsulta.getColumnIndex("procedimento_consulta")));
            consulta.setObservacao_consulta(cConsulta.getString(cConsulta.getColumnIndex("observacao_consulta")));

            consultas.add(consulta);
        }

        cConsulta.close();

        return consultas;
    }

//DELETAR

    public void delete(Consulta consulta) {
        SQLiteDatabase dbConsulta = getWritableDatabase();
        String[] params = {consulta.getId().toString()};
        dbConsulta.delete("Consulta", "id = ?", params);

    }

//ALTERAR

    public void altera(Consulta consulta) {
        SQLiteDatabase dbConsulta = getWritableDatabase();
        ContentValues dadosConsulta = pegaDadosDoConsulta(consulta);
        String[] params = {consulta.getId().toString()};
        dbConsulta.update("Consulta", dadosConsulta, "id = ?", params);
    }
}


