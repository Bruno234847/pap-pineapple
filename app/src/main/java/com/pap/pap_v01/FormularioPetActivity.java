package com.pap.pap_v01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.pap.pap_v01.Task.EnviaTaskConsulta;
import com.pap.pap_v01.dao.PetDAO;
import com.pap.pap_v01.modelo.Pet;

import java.io.File;

public class FormularioPetActivity extends AppCompatActivity {

    private final int capturaImagem = 100;
    private final int selecionaImagem = 200;
    private HelperPet helperPet;
    private String caminhoArquivoPet;
    private ImageView pet_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity_pet);

// BARRA INFO
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Pet");
        actionBar.setSubtitle("   Formulário");
        actionBar.setLogo(R.drawable.logo3);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//BOTAO - NOVO USUARIO

        helperPet = new HelperPet(this);

        Intent intentPet = getIntent();
        Pet pet = (Pet) intentPet.getSerializableExtra("pet");
        if (pet != null) {
            helperPet.preencherPet(pet);
        }

//IMAGEM

        pet_foto = helperPet.getCampoFotoPet();

        pet_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] opcoes = {"Captura foto", "Selecionar da galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(FormularioPetActivity.this);
                builder.setTitle("Selecione uma opção");
                builder.setItems(opcoes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {

                        //Captura imagem
                        if (opcoes[seleccion] == "Captura foto") {

                            Intent irParaCameraPet = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            caminhoArquivoPet = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".png";
                            File arquivoPet = new File(caminhoArquivoPet);

                            Uri localFotoPet = Uri.fromFile(arquivoPet);
                            irParaCameraPet.putExtra(MediaStore.EXTRA_OUTPUT, localFotoPet);

                            startActivityForResult(irParaCameraPet.createChooser(irParaCameraPet, "Imagem capturada"), capturaImagem);

                            //Seleciona imagem
                        } else if (opcoes[seleccion] == "Selecionar da galeria") {

                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Imagem selecionada"), selecionaImagem);

                            //Cancelar
                        } else if (opcoes[seleccion] == "Cancelar") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

    }

//CAPTURA IMAGEM

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            //Captura imagem
            case capturaImagem:
                if (resultCode == Activity.RESULT_OK) {
                    helperPet.carregaImagemPet(caminhoArquivoPet);
                }
                break;
            //Seleciona imagem
            case selecionaImagem:
                if (resultCode == RESULT_OK) {
                    Uri caminhoArquivoPet = data.getData();
                    pet_foto.setImageURI(caminhoArquivoPet);
                }
                break;
        }
    }

//INSTANCIANDO MENU XML

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflaterPet = getMenuInflater();
        inflaterPet.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

//PROCESSO PARA INSERIR OU ALTERAR DADOS NO BANCO

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_salvar:

                Pet pet = helperPet.pegaPet();

                PetDAO daoPet = new PetDAO(this);

                if (pet.getId() != null) {
                    daoPet.altera(pet);
                    Toast.makeText(FormularioPetActivity.this, "Dados alterados com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    daoPet.inserePet(pet);
                    Toast.makeText(FormularioPetActivity.this, "Dados cadastrados com sucesso", Toast.LENGTH_SHORT).show();
                }
                daoPet.close();

                finish();
                break;

            case R.id.menu_voltar:

                Intent acessarMenuPet = new Intent(FormularioPetActivity.this, ListaPetActivity.class);
                startActivity(acessarMenuPet);

                finish();

                break;

            //Enviando dados para o servidor

            case R.id.enviar:

                AlertDialog alertDialogEnviar = new AlertDialog.Builder(FormularioPetActivity.this).create();
                alertDialogEnviar.setTitle("Enviar Dados");
                alertDialogEnviar.setIcon(R.drawable.ajuda);

                new EnviaTaskConsulta(this).execute();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}




