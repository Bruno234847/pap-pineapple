package com.pap.pap_v01.suporte;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WebCliente {

    private String url;

    public WebCliente(String url) {

        this.url = url;
    }

    public String post(String json) throws IOException {

        URL url = new URL("https://www.caelum.com.br/mobile");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        connection.setDoOutput(true);

        PrintStream saida = new PrintStream(connection.getOutputStream());
        saida.println(json);

        connection.connect();
        String resposta = new Scanner(connection.getInputStream()).next();


        return resposta;
    }

}