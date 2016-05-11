package com.pap.pap_v01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import com.pap.pap_v01.modelo.Pet;
import java.util.ArrayList;
import java.util.List;

public class PetDAO extends SQLiteOpenHelper {

    public PetDAO(Context context) {
        //Contexto - nome do banco - versão
        super(context, "Pet", null, 9);
    }

//CRIAR BANCO

    @Override
    public void onCreate(SQLiteDatabase dbPet) {
        String sqlPet = "CREATE TABLE  Pet (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, especie TEXT, raca TEXT, peso TEXT, idade TEXT, temperamento REAL, observacao TEXT, caminhoFotoPet TEXT);";
        dbPet.execSQL(sqlPet);
    }

//ATUALIZAR O BANCO - DROP NA TABELA

    @Override
    public void onUpgrade(SQLiteDatabase dbPet, int oldVersion, int newVersion) {
        String sqlPet = "DROP TABLE IF EXISTS Pet";
        dbPet.execSQL(sqlPet);
        onCreate(dbPet);
    }

//INSERIR DADOS NO BANCO

    public void inserePet(Pet pet) {
        SQLiteDatabase dbPet = getWritableDatabase();
        ContentValues dadosPet = pegaDadosDoPet(pet);
        dbPet.insert("Pet", null, dadosPet);
    }

//METODO PARA INSERIR DADOS NO BANCO ATRAVÉS DO EXTRACT

    @NonNull
    private ContentValues pegaDadosDoPet(Pet pet) {
        ContentValues dadosPet = new ContentValues();
        dadosPet.put("nome", pet.getNome());
        dadosPet.put("especie", pet.getEspecie());
        dadosPet.put("raca", pet.getRaca());
        dadosPet.put("peso", pet.getPeso());
        dadosPet.put("idade", pet.getIdade());
        dadosPet.put("temperamento", pet.getTemperamento());
        dadosPet.put("observacao", pet.getObservacao());
        dadosPet.put("caminhoFotoPet", pet.getCaminhoFotoPet());

//Teste
       // dadosPet.put("pelagem2", pet.getPelagem2());

        return dadosPet;
    }

//METODOS PARA BUSCAR DADOS NO BANCO
    public List<Pet> buscaPet() {
        String sqlPet = "SELECT * FROM Pet;";
        SQLiteDatabase dbPet = getReadableDatabase();
        //Cursor aponta as linhas do banco
        Cursor cPet = dbPet.rawQuery(sqlPet, null);
        List<Pet> pets = new ArrayList<Pet>();

        while(cPet.moveToNext()){

            Pet pet = new Pet();

            pet.setId(cPet.getLong(cPet.getColumnIndex("id")));
            pet.setNome(cPet.getString(cPet.getColumnIndex("nome")));
            pet.setEspecie(cPet.getString(cPet.getColumnIndex("especie")));
            pet.setRaca(cPet.getString(cPet.getColumnIndex("raca")));
            pet.setPeso(cPet.getString(cPet.getColumnIndex("peso")));
            pet.setIdade(cPet.getString(cPet.getColumnIndex("idade")));
            pet.setTemperamento(cPet.getDouble(cPet.getColumnIndex("temperamento")));
            pet.setObservacao(cPet.getString(cPet.getColumnIndex("observacao")));
            pet.setCaminhoFotoPet(cPet.getString(cPet.getColumnIndex("caminhoFotoPet")));

            pets.add(pet);
        }

        cPet.close();

        return pets;
    }

//DELETAR

    public void delete(Pet pet) {
        SQLiteDatabase dbPet = getWritableDatabase();
        String[] params = {pet.getId().toString()};
        dbPet.delete("Pet", "id = ?", params);

    }

//ALTERAR

    public void altera(Pet pet) {
        SQLiteDatabase dbPet = getWritableDatabase();
        ContentValues dadosPet = pegaDadosDoPet(pet);
        String[] params = {pet.getId().toString()};
        dbPet.update("Pet", dadosPet, "id = ?", params);
    }
}

