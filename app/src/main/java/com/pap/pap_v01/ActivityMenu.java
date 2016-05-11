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
import android.widget.ImageButton;

public class ActivityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

// BARRA INFO
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  Menu Principal");
        actionBar.setLogo(R.drawable.logo3);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        configProprietario();
        configPets();
        configConsulta();
        configPets();
    }

//BOTAO - LINK PROPRIETARIO
    private void configProprietario(){
        ImageButton botaoMenu = (ImageButton) findViewById(R.id.Principal_Menu1);
        botaoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent acessarProprietario = new Intent(ActivityMenu.this, ListaProprietarioActivity.class);
                startActivity(acessarProprietario);

            }
        });
    }

//BOTAO - LINK PETS
    private void configPets(){
        ImageButton botaoMenu = (ImageButton) findViewById(R.id.Principal_Menu2);
        botaoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent acessarPets = new Intent(ActivityMenu.this, ListaPetActivity.class);
                startActivity(acessarPets);

            }
        });
    }

//BOTAO - LINK CONSULTA
    private void configConsulta(){
        ImageButton botaoMenu = (ImageButton) findViewById(R.id.Principal_Menu3);
        botaoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent acessarConsulta = new Intent(ActivityMenu.this, ListaConsultaActivity.class);
                startActivity(acessarConsulta);

            }
        });
    }


//INSTANCIANDO MENU XML

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflaterPet = getMenuInflater();
        inflaterPet.inflate(R.menu.menu_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

//MENU - ACTIONBAR

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_logout:

                new AlertDialog.Builder(ActivityMenu.this)
                        .setTitle("SAIR")
                        .setMessage("Gostaria de sair da aplicação?")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialogInterface, int i) {

                                Intent acessarLogin = new Intent(ActivityMenu.this, ActivityLogin.class);
                                startActivity(acessarLogin);

                                finish();
                            }
                        })
                        .show();
                break;

             case R.id.Menu_ajuda:

                 AlertDialog alertDialogAjuda = new AlertDialog.Builder(ActivityMenu.this).create();
                 alertDialogAjuda.setTitle("Ajuda");
                 alertDialogAjuda.setMessage("Contate-nos: " + "\n\ncontato@pineapple.com");
                 alertDialogAjuda.setIcon(R.drawable.ajuda);

                 alertDialogAjuda.setButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 });

                 alertDialogAjuda.show();

                break;

            case R.id.Menu_informacao:

                AlertDialog alertDialogInfo = new AlertDialog.Builder(ActivityMenu.this).create();
                alertDialogInfo.setTitle("Informações");
                alertDialogInfo.setMessage("Aplicativo de uso exclusivo da Clínica PiVET. " +
                        "Todos os direitos estão reservados ao proprietário do contrato de serviço e certificação" +
                        "\n\nVersão: 1.2.0_235154 "  +
                        "\n\nDesenvolvedor Responsável: Bruno Alexandre Alves - 234847");
                alertDialogInfo.setIcon(R.drawable.info);

                alertDialogInfo.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialogInfo.show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

