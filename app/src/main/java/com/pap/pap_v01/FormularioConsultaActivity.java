package com.pap.pap_v01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.pap.pap_v01.Task.EnviaTaskConsulta;
import com.pap.pap_v01.mascara.Mascara;
import com.pap.pap_v01.dao.ConsultaDAO;
import com.pap.pap_v01.modelo.Consulta;

import java.util.ArrayList;
import java.util.List;

public class FormularioConsultaActivity extends AppCompatActivity {

    private HelperConsulta helperConsulta;
    final List<CharSequence> lista = new ArrayList<CharSequence>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity_consulta);


//MASCARAS

        final EditText campo_data_consulta = (EditText) findViewById(R.id.formulario_data__consulta);
        campo_data_consulta.addTextChangedListener(Mascara.insert("##/##/####", campo_data_consulta));

        final EditText campo_hora_consulta = (EditText) findViewById(R.id.formulario_hora_consulta);
        campo_hora_consulta.addTextChangedListener(Mascara.insert("##:##", campo_hora_consulta));

// BARRA INFO

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Consulta");
        actionBar.setSubtitle("   Formulário");
        actionBar.setLogo(R.drawable.logo3);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//BOTAO - NOVO USUARIO

        helperConsulta = new HelperConsulta(this);

        Intent intentConsulta = getIntent();
        Consulta consulta = (Consulta) intentConsulta.getSerializableExtra("consulta");
        if (consulta != null) {
            helperConsulta.preencherConsulta(consulta);
        }

//ACIONAR O SERVIÇO DE TAXI

        Switch switch1 = (Switch) findViewById(R.id.servicoTaxi);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Adicional de R$12,50 para o raio de 20 km", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//INSTANCIANDO MENU XML

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflaterConsulta = getMenuInflater();
        inflaterConsulta.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

//PROCESSO PARA INSERIR OU ALTERAR DADOS NO BANCO

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_salvar:

                Consulta consulta = helperConsulta.pegaConsulta();

                ConsultaDAO daoConsulta = new ConsultaDAO(this);

                if (consulta.getId() != null) {
                    daoConsulta.altera(consulta);
                    Toast.makeText(FormularioConsultaActivity.this, "Consulta altarada com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    daoConsulta.insereConsulta(consulta);
                    Toast.makeText(FormularioConsultaActivity.this, "Consulta agendada com sucesso", Toast.LENGTH_SHORT).show();
                }
                daoConsulta.close();

                finish();
                break;

            case R.id.menu_voltar:

                Intent acessarMenuConsulta = new Intent(FormularioConsultaActivity.this, ListaConsultaActivity.class);
                startActivity(acessarMenuConsulta);

                finish();

                break;

            //Enviando dados para o servidor

            case R.id.enviar:

                AlertDialog alertDialogEnviar = new AlertDialog.Builder(FormularioConsultaActivity.this).create();
                alertDialogEnviar.setTitle("Enviar Dados");
                alertDialogEnviar.setIcon(R.drawable.ajuda);

                new EnviaTaskConsulta(this).execute();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}




