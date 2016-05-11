package com.pap.pap_v01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pap.pap_v01.Task.EnviaTaskConsulta;
import com.pap.pap_v01.mascara.Mascara;
import com.pap.pap_v01.dao.ProprietarioDAO;
import com.pap.pap_v01.modelo.Proprietario;

import java.io.File;

public class FormularioProprietarioActivity extends AppCompatActivity {

    private HelperProprietario helper;
    private String caminhoArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity_proprietario);

//MASCARAS

        final EditText campo_cep_proprietario = (EditText) findViewById(R.id.formulario_cep);
        campo_cep_proprietario.addTextChangedListener(Mascara.insert("#####-###", campo_cep_proprietario));

        final EditText campo_telefone_proprietario = (EditText) findViewById(R.id.formulario_telefone);
        campo_telefone_proprietario.addTextChangedListener(Mascara.insert("(##)####-####", campo_telefone_proprietario));

        final EditText campo_celular_proprietario = (EditText) findViewById(R.id.formulario_celular);
        campo_celular_proprietario.addTextChangedListener(Mascara.insert("(##)#####-####", campo_celular_proprietario));

// BARRA INFO
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Proprietário");
        actionBar.setSubtitle("   Formulário");
        actionBar.setLogo(R.drawable.logo3);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//BOTAO - NOVO USUARIO

        helper = new HelperProprietario(this);

        Intent intent = getIntent();
        Proprietario proprietario = (Proprietario) intent.getSerializableExtra("proprietario");
        if (proprietario != null) {
            helper.preencherProprietario(proprietario);
        }
//CAPTURA IMAGEM

        ImageView proprietario_foto = helper.getCampoFoto();

        proprietario_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                caminhoArquivo = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".png";
                File arquivo = new File(caminhoArquivo);

                Uri localFoto = Uri.fromFile(arquivo);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                startActivityForResult(irParaCamera, 123);
            }
        });

        }

//CAPTURA IMAGEM

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123){
            if(resultCode == Activity.RESULT_OK){
                helper.carregaImagem(caminhoArquivo);

            }else{
                caminhoArquivo = null;
            }
        }
    }

//INSTANCIANDO MENU XML

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

//PROCESSO PARA INSERIR OU ALTERAR DADOS NO BANCO

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_salvar:

                Proprietario proprietario = helper.pegaProprietario();

                ProprietarioDAO dao = new ProprietarioDAO(this);

                if (proprietario.getId() != null) {
                    dao.altera(proprietario);
                    Toast.makeText(FormularioProprietarioActivity.this, "Cadastro alterado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    dao.insere(proprietario);
                    Toast.makeText(FormularioProprietarioActivity.this, "Proprietário cadastrados com sucesso", Toast.LENGTH_SHORT).show();
                }
                dao.close();

                finish();
                break;

            case R.id.menu_voltar:

                Intent acessarMenu = new Intent(FormularioProprietarioActivity.this, ListaProprietarioActivity.class);
                startActivity(acessarMenu);

                finish();

                break;

            //Enviando dados para o servidor

            case R.id.enviar:

                AlertDialog alertDialogEnviar = new AlertDialog.Builder(FormularioProprietarioActivity.this).create();
                alertDialogEnviar.setTitle("Enviar Dados");
                alertDialogEnviar.setIcon(R.drawable.enviar);

                new EnviaTaskConsulta(this).execute();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}




