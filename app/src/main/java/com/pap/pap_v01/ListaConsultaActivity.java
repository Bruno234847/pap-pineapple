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
import com.pap.pap_v01.adapter.ConsultaAdapter;
import com.pap.pap_v01.dao.ConsultaDAO;
import com.pap.pap_v01.modelo.Consulta;

import java.util.List;

public class ListaConsultaActivity extends AppCompatActivity {

    private ListView listaConsulta;

    Consulta consulta = new Consulta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_consulta);

// BARRA INFO

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Consulta");
        actionBar.setSubtitle("  Cadastro");
        actionBar.setLogo(R.drawable.logo3);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//LISTA

        listaConsulta = (ListView) findViewById(R.id.lista_consulta);

//SELECIONA ITEM DA LISTA PARA EDICÃO

        listaConsulta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                consulta = (Consulta) listaConsulta.getItemAtPosition(position);

//DIRECIONANDO PARA FORMULARIO CONSULTA

                Intent intentVaiProConsulta = new Intent(ListaConsultaActivity.this, FormularioConsultaActivity.class);
                intentVaiProConsulta.putExtra("consulta", consulta);
                startActivity(intentVaiProConsulta);
            }
        });

//LINK PARA FORMULARIO CONSULTA

        Button novoConsulta = (Button) findViewById(R.id.nova_consulta);
        novoConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVaiProConsulta = new Intent(ListaConsultaActivity.this, FormularioConsultaActivity.class);
                startActivity(intentVaiProConsulta);
            }
        });

        registerForContextMenu(listaConsulta);
    }

//CONSULTAR O BANCO PARA RETORNO DOS DADOS

    private void carregaListaConsulta() {
        ConsultaDAO daoConsulta = new ConsultaDAO(this);
        List<Consulta> consultas = daoConsulta.buscaConsulta();

//CARREGAR FOTO NA LISTA

        ConsultaAdapter adapterConsulta = new ConsultaAdapter(consultas, this);
        listaConsulta.setAdapter(adapterConsulta);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaConsulta();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

//DELETAR ITEM DA LISTA

        MenuItem deletarConsulta = menu.add("Deletar");
        deletarConsulta.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Consulta consulta = (Consulta) listaConsulta.getItemAtPosition(info.position);

                ConsultaDAO daoConsulta = new ConsultaDAO(ListaConsultaActivity.this);
                daoConsulta.delete(consulta);
                daoConsulta.close();

                Toast.makeText(ListaConsultaActivity.this, "Consulta deletada com sucesso", Toast.LENGTH_SHORT).show();

                carregaListaConsulta();
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

                Intent acessarMenu = new Intent(ListaConsultaActivity.this, ActivityMenu.class);
                startActivity(acessarMenu);

                finish();

        return super.onOptionsItemSelected(item);
    }

}
