package com.pap.pap_v01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity  {

    ImageButton Login_Botao;
    EditText campo_Login;
    EditText campo_Senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configProprietario();

//BOTAO - LOGIN

        campo_Login = (EditText) findViewById(R.id.campo_Login);
        campo_Senha = (EditText) findViewById(R.id.campo_Senha);
        Login_Botao = (ImageButton) findViewById(R.id.Login_Botao);

        ImageButton botaoLogin = (ImageButton) findViewById(R.id.Login_Botao);
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (campo_Login.getText().toString().length() <= 0) {
                    campo_Login.setError("Preencha o campo E-mail!");
                    campo_Login.requestFocus();

                } else if (campo_Senha.getText().toString().length() <= 0) {
                    campo_Senha.setError("Preencha o campo Senha!");
                    campo_Senha.requestFocus();

                }else{ //if(campo_Login.equals("nome") || campo_Senha.equals("")) {
                        Intent acessarMenu = new Intent(ActivityLogin.this, ActivityMenu.class);
                        startActivity(acessarMenu);

                        Toast.makeText(ActivityLogin.this, "Seja Bem Vindo " + campo_Login.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

//BOTAO - LINK NOVO CADASTRO

    private void configProprietario(){
        Button novo = (Button) findViewById(R.id.novo_Registro);
        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent novoProprietario = new Intent(ActivityLogin.this, FormularioProprietarioActivity.class);
                startActivity(novoProprietario);

            }
        });
    }

}


