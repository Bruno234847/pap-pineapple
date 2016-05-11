package com.pap.pap_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.pap.pap_v01.adapter.PetAdapter;
import com.pap.pap_v01.dao.PetDAO;
import com.pap.pap_v01.modelo.Pet;

import java.util.List;

public class ListaPetActivity extends AppCompatActivity {

    private ListView listaPet;

    Pet pet = new Pet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pet);

// BARRA INFO

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Pet");
        actionBar.setSubtitle("   Cadastro");
        actionBar.setLogo(R.drawable.logo3);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//LISTA

        listaPet = (ListView) findViewById(R.id.lista_pet);

//SELECIONA ITEM DA LISTA PARA EDICÃO

        listaPet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                pet = (Pet) listaPet.getItemAtPosition(position);

//DIRECIONANDO PARA FORMULARIO PET

                Intent intentVaiProPet = new Intent(ListaPetActivity.this, FormularioPetActivity.class);
                intentVaiProPet.putExtra("pet", pet);
                startActivity(intentVaiProPet);
            }
        });

//LINK PARA FORMULARIO PET

        Button novoPet = (Button) findViewById(R.id.novo_pet);
        novoPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVaiProPet = new Intent(ListaPetActivity.this, FormularioPetActivity.class);
                startActivity(intentVaiProPet);
            }
        });

        registerForContextMenu(listaPet);
    }

//CONSULTAR O BANCO PARA RETORNO DOS DADOS

    private void carregaListaPet() {
        PetDAO daoPet = new PetDAO(this);
        List<Pet> pets = daoPet.buscaPet();

//CARREGAR FOTO NA LISTA

        PetAdapter adapterPet = new PetAdapter(pets, this);
        listaPet.setAdapter(adapterPet);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaPet();
    }

//DELETAR ITEM DA LISTA

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletarPet = menu.add("Deletar");
        deletarPet.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Pet pet = (Pet) listaPet.getItemAtPosition(info.position);

                PetDAO daoPet = new PetDAO(ListaPetActivity.this);
                daoPet.delete(pet);
                daoPet.close();

                Toast.makeText(ListaPetActivity.this, "Pet deletado com sucesso", Toast.LENGTH_SHORT).show();

                carregaListaPet();
                return false;
            }
        });

    }

// BARRA DE MENU SUPERIOR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

                Intent acessarMenu = new Intent(ListaPetActivity.this, ActivityMenu.class);
                startActivity(acessarMenu);

                finish();

        return super.onOptionsItemSelected(item);
    }

}
