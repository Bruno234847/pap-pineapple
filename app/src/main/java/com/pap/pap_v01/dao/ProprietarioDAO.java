package com.pap.pap_v01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import com.pap.pap_v01.modelo.Proprietario;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO extends SQLiteOpenHelper {

    //Criando o banco
    public ProprietarioDAO(Context context) {
        //Contexto - nome do banco - versão
        super(context, "Proprietario", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Metodo para criar o banco
        String sql = "CREATE TABLE  Proprietario (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, cidade TEXT, cep TEXT, telefone TEXT, celular TEXT, email TEXT, senha TEXT, repetirSenha TEXT, caminhoFoto TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Metodo para atualizar o banco, apagando a tabela.... Não esquecer de mudar a versão
        String sql = "DROP TABLE IF EXISTS Proprietario";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Proprietario proprietario) {

        //Metodo para inserir dados no banco
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoProprietario(proprietario);

        db.insert("Proprietario", null, dados);
    }

    @NonNull
    //Metodo para inserir dados no banco / Extract
    private ContentValues pegaDadosDoProprietario(Proprietario proprietario) {
        ContentValues dados = new ContentValues();
        dados.put("nome", proprietario.getNome());
        dados.put("endereco", proprietario.getEndereco());
        dados.put("cidade", proprietario.getCidade());
        dados.put("cep", proprietario.getCep());
        dados.put("telefone", proprietario.getTelefone());
        dados.put("celular", proprietario.getCelular());
        dados.put("email", proprietario.getEmail());
        dados.put("senha", proprietario.getSenha());
        dados.put("repetirSenha", proprietario.getRepetirSenha());
        dados.put("caminhoFoto", proprietario.getCaminhoFoto());
        return dados;
    }

    public List<Proprietario> buscaProprietario() {

        //Metodo para busca
        String sql = "SELECT * FROM Proprietario;";
        SQLiteDatabase db = getReadableDatabase();
        //Cursor aponta as linhas do banco
        Cursor c = db.rawQuery(sql, null);

        List<Proprietario> proprietarios = new ArrayList<Proprietario>();

        while(c.moveToNext()){

            Proprietario proprietario = new Proprietario();

            proprietario.setId(c.getLong(c.getColumnIndex("id")));
            proprietario.setNome(c.getString(c.getColumnIndex("nome")));
            proprietario.setEndereco(c.getString(c.getColumnIndex("endereco")));
            proprietario.setCidade(c.getString(c.getColumnIndex("cidade")));
            proprietario.setCep(c.getString(c.getColumnIndex("cep")));
            proprietario.setTelefone(c.getString(c.getColumnIndex("telefone")));
            proprietario.setCelular(c.getString(c.getColumnIndex("celular")));
            proprietario.setEmail(c.getString(c.getColumnIndex("email")));
            proprietario.setSenha(c.getString(c.getColumnIndex("senha")));
            proprietario.setRepetirSenha(c.getString(c.getColumnIndex("repetirSenha")));
            proprietario.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            proprietarios.add(proprietario);
        }
        c.close();

        return proprietarios;
    }

    //Metodo deletar
    public void delete(Proprietario proprietario) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {proprietario.getId().toString()};
        db.delete("Proprietario", "id = ?", params);

    }

    //Metodo para alterar
    public void altera(Proprietario proprietario) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoProprietario(proprietario);

        String[] params = {proprietario.getId().toString()};
        db.update("Proprietario", dados, "id = ?", params);

    }
}

