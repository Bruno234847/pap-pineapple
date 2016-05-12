package com.pap.pap_v01;

import android.widget.EditText;
import com.pap.pap_v01.modelo.Consulta;

public class HelperConsulta {

    private final EditText campoNome_consulta;
    private final EditText campoData_consulta;
    private final EditText campoHora_consulta;
    private final EditText campoProcedimento_consulta;
    private final EditText campoObservacao_consulta;

    private Consulta consulta;

    public HelperConsulta(FormularioConsultaActivity activity){

//PEGANDO OS DADOS PARA SALVAR

        campoNome_consulta = (EditText) activity.findViewById(R.id.formulario_nome_consulta);
        campoData_consulta = (EditText) activity.findViewById(R.id.formulario_data__consulta);
        campoHora_consulta = (EditText) activity.findViewById(R.id.formulario_hora_consulta);
        campoProcedimento_consulta = (EditText) activity.findViewById(R.id.formulario_procedimento_consulta);
        campoObservacao_consulta = (EditText) activity.findViewById(R.id.formulario_observacao_consulta);

        consulta = new Consulta();
    }

    public Consulta pegaConsulta() {
        consulta.setNome_consulta(campoNome_consulta.getText().toString());
        consulta.setData_consulta(campoData_consulta.getText().toString());
        consulta.setHora_consulta(campoHora_consulta.getText().toString());
        consulta.setProcedimento_consulta(campoProcedimento_consulta.getText().toString());
        consulta.setObservacao_consulta(campoObservacao_consulta.getText().toString());

        return consulta;
    }

    public void preencherConsulta(Consulta consulta) {

//RETORNA DADOS AO FORMULARIO PARA EDIÇÃO

        campoNome_consulta.setText(consulta.getNome_consulta());
        campoData_consulta.setText(consulta.getData_consulta());
        campoHora_consulta.setText(consulta.getHora_consulta());
        campoProcedimento_consulta.setText(consulta.getProcedimento_consulta());
        campoObservacao_consulta.setText(consulta.getObservacao_consulta());

        this.consulta = consulta;
    }
}
