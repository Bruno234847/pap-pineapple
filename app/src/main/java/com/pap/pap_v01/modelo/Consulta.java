package com.pap.pap_v01.modelo;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable {

    private Long id;
    private String nome_consulta;
    private String data_consulta;
    private String hora_consulta;
    private String procedimento_consulta;
    private String observacao_consulta;
    private boolean taxi;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getProcedimento_consulta() { return procedimento_consulta;}

    public void setProcedimento_consulta(String procedimento_consulta) {this.procedimento_consulta = procedimento_consulta;}

    public String getObservacao_consulta() {return observacao_consulta;}

    public void setObservacao_consulta(String observacao_consulta) {this.observacao_consulta = observacao_consulta;}

    public String getHora_consulta() {return hora_consulta;}

    public void setHora_consulta(String hora_consulta) {this.hora_consulta = hora_consulta;}

    public String getData_consulta() {return data_consulta;}

    public void setData_consulta(String data_consulta) {this.data_consulta = data_consulta;}

    public String getNome_consulta() {return nome_consulta;}

    public void setNome_consulta(String nome_consulta) {this.nome_consulta = nome_consulta;}

    public boolean isTaxi() {return taxi;}

    public void setTaxi(boolean taxi) {this.taxi = taxi;}

//INDICA O QUE DEVE SER EXIBIDO
    @Override
    public String toString() {return getId() + " - " + getProcedimento_consulta();}

}
