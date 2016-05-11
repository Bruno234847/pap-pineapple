package com.pap.pap_v01.modelo;

import java.io.Serializable;

public class Proprietario implements Serializable    {

        private Long id;
        private String nome;
        private String endereco;
        private String cidade;
        private String cep;
        private String telefone;
        private String celular;
        private String email;
        private String senha;
        private String repetirSenha;
        private String caminhoFoto;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public String getCidade() {return cidade;}

    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getCep() {return cep;}

    public void setCep(String cep) {this.cep = cep;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getCelular() {return celular;}

    public void setCelular(String celular) {this.celular = celular;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public String getRepetirSenha() {return repetirSenha;}

    public void setRepetirSenha(String repetirSenha) {this.repetirSenha = repetirSenha;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getCaminhoFoto() { return caminhoFoto;}

    public void setCaminhoFoto(String caminhoFoto) { this.caminhoFoto = caminhoFoto;}

//INDICA O QUE DEVE SER EXIBIDO
    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}

