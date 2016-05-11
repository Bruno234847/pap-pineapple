package com.pap.pap_v01.converter;

import com.pap.pap_v01.modelo.Consulta;
import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class ConsultaConverter {

    //Criando uma lista Json
    public String toJSON(List<Consulta> consultas) {

        //organizar a lista
        JSONStringer js = new JSONStringer();

        try {

            js.object().key("lista").array();
            js.object().key("consulta").array();
            for (Consulta consulta : consultas) {
                js.object();
                    js.key("nome").value(consulta.getNome_consulta());
                    js.key("data").value(consulta.getData_consulta());
                    js.key("hora").value(consulta.getHora_consulta());
                    js.key("procedimento").value(consulta.getProcedimento_consulta());
                    js.key("observacao").value(consulta.getObservacao_consulta());
                    //js.key("taxi").value(consulta.getTaxi());
                js.endObject();
            }
            //Finaliza todas as chaves
            js.endObject().endArray();
            js.endObject().endArray();

        }catch(JSONException e){
            e.printStackTrace();
        }

        return js.toString();
    }
}