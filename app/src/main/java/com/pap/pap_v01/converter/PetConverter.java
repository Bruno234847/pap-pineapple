package com.pap.pap_v01.converter;

import com.pap.pap_v01.modelo.Pet;
import org.json.JSONException;
import org.json.JSONStringer;
import java.util.List;

public class PetConverter {

    //Criando uma lista Json
    public String toJSON(List<Pet> pets) {

        //organizar a lista
        JSONStringer js = new JSONStringer();

        try {

            js.object().key("lista").array();
            js.object().key("pet").array();
            for (Pet pet : pets) {
                js.object();
                    js.key("nome").value(pet.getNome());
                    js.key("especie").value(pet.getEspecie());
                    js.key("reca").value(pet.getRaca());
                    js.key("peso").value(pet.getPeso());
                    js.key("idade").value(pet.getIdade());
                    js.key("temperamento").value(pet.getTemperamento());
                    js.key("obeservacao").value(pet.getObservacao());
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