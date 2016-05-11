package com.pap.pap_v01;

import android.content.Intent;
import android.net.Uri;
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

import com.pap.pap_v01.adapter.ProprietarioAdapter;
import com.pap.pap_v01.dao.ProprietarioDAO;
import com.pap.pap_v01.modelo.Proprietario;

import java.util.List;

public class ListaProprietarioActivity extends AppCompatActivity {

    private ListView listaProprietario;

    Proprietario proprietario = new Proprietario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proprietario);

// BARRA INFO

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Proprietário");
        actionBar.setSubtitle("   Cadastro");
        actionBar.setLogo(R.drawable.logo3);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//LISTA

        listaProprietario = (ListView) findViewById(R.id.lista_proprietario);

//SELECIONA ITEM DA LISTA PARA EDIÇÃO

        listaProprietario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                proprietario = (Proprietario) listaProprietario.getItemAtPosition(position);

//DIRECIONANDO PARA FORMULARIO PROPRIETARIO
                Intent intentVaiProProprietario = new Intent(ListaProprietarioActivity.this, FormularioProprietarioActivity.class);
                intentVaiProProprietario.putExtra("proprietario", proprietario);
                startActivity(intentVaiProProprietario);
            }
        });

//LINK PARA FORMULARIO PROPRIETARIO

        Button novoProprietario = (Button) findViewById(R.id.novo_proprietario);
        novoProprietario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVaiProProprietario = new Intent(ListaProprietarioActivity.this, FormularioProprietarioActivity.class);
                startActivity(intentVaiProProprietario);
            }
        });

        registerForContextMenu(listaProprietario);
    }

    private void carregaLista() {

//CONSULTAR O BANCO PARA RETORNO DOS DADOS

        ProprietarioDAO dao = new ProprietarioDAO(this);
        List<Proprietario> proprietarios = dao.buscaProprietario();

//CARREGAR FOTO NA LISTA

        ProprietarioAdapter adapter = new ProprietarioAdapter(proprietarios, this);
        listaProprietario.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

//DELETAR ITEM DA LISTA

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Proprietario proprietario = (Proprietario) listaProprietario.getItemAtPosition(info.position);

                ProprietarioDAO dao = new ProprietarioDAO(ListaProprietarioActivity.this);
                dao.delete(proprietario);
                dao.close();

                Toast.makeText(ListaProprietarioActivity.this, "Proprietário deletado com sucesso", Toast.LENGTH_SHORT).show();

                carregaLista();
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

                Intent acessarMenu = new Intent(ListaProprietarioActivity.this, ActivityMenu.class);
                startActivity(acessarMenu);

                finish();

        return super.onOptionsItemSelected(item);
    }

}
