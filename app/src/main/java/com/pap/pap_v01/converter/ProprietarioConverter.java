package com.pap.pap_v01.converter;

import com.pap.pap_v01.modelo.Proprietario;
import org.json.JSONException;
import org.json.JSONStringer;
import java.util.List;

public class ProprietarioConverter {

    //Criando uma lista Json
    public String toJSON(List<Proprietario> proprietarios) {

        //Organizar a lista
        JSONStringer js = new JSONStringer();

        try {

            js.object().key("lista").array();
            js.object().key("consulta").array();
            for (Proprietario proprietario : proprietarios) {
                js.object();
                    js.key("nome").value(proprietario.getNome());
                    js.key("endereco").value(proprietario.getEndereco());
                    js.key("cidade").value(proprietario.getCidade());
                    js.key("cep").value(proprietario.getCep());
                    js.key("telefone").value(proprietario.getTelefone());
                    js.key("celular").value(proprietario.getCelular());
                    js.key("email").value(proprietario.getEmail());
                    js.key("senha").value(proprietario.getSenha());
                    js.key("repetirSenha").value(proprietario.getRepetirSenha());

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