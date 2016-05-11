package com.pap.pap_v01.modelo;

import java.io.Serializable;

public class Pet implements Serializable {

    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private String peso;
    private String idade;
    private Double temperamento;
    private String observacao;
    private String caminhoFotoPet;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(Double temperamento) {
        this.temperamento = temperamento;
    }

    public String getCaminhoFotoPet() {
        return caminhoFotoPet;
    }

    public void setCaminhoFotoPet(String caminhoFotoPet) {
        this.caminhoFotoPet = caminhoFotoPet;
    }

}
