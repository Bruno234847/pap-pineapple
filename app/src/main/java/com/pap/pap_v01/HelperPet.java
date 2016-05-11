package com.pap.pap_v01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.pap.pap_v01.modelo.Pet;

public class HelperPet {

    private Pet campoPelagem2 = new Pet();

    private final EditText campoNome;
    private final EditText campoEspecie;
    private final EditText campoRaca;
    private final EditText campoPeso;
    private final EditText campoIdade;
    private final RatingBar campoTemperamento;
    private final EditText campoObservacao;
    private final ImageView campoFotoPet;

    private Pet pet;

    public HelperPet(FormularioPetActivity activity) {

//PEGANDO OS DADOS PARA SALVAR

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEspecie = (EditText) activity.findViewById(R.id.formulario_especie);
        campoRaca = (EditText) activity.findViewById(R.id.formulario_raca);
        campoPeso = (EditText) activity.findViewById(R.id.formulario_peso);
        campoIdade = (EditText) activity.findViewById(R.id.formulario_idade);
        campoTemperamento = (RatingBar) activity.findViewById(R.id.formulario_temperamento);
        campoObservacao = (EditText) activity.findViewById(R.id.formulario_observacao);
        campoFotoPet = (ImageView) activity.findViewById(R.id.formulario_foto);

        pet = new Pet();
    }

    public Pet pegaPet() {
        pet.setNome(campoNome.getText().toString());
        pet.setEspecie(campoEspecie.getText().toString());
        pet.setRaca(campoRaca.getText().toString());
        pet.setPeso(campoPeso.getText().toString());
        pet.setIdade(campoIdade.getText().toString());
        pet.setTemperamento(Double.valueOf(campoTemperamento.getProgress()));
        pet.setObservacao(campoObservacao.getText().toString());

        return pet;
    }

//RETORNA DADOS AO FORMULARIO PARA EDIÇÃO

    public void preencherPet(Pet pet) {

        this.pet = pet;

        campoNome.setText(pet.getNome());
        campoEspecie.setText(pet.getEspecie());
        campoRaca.setText(pet.getRaca());
        campoPeso.setText(pet.getPeso());
        campoIdade.setText(pet.getIdade());
        campoTemperamento.setProgress(pet.getTemperamento().intValue());
        campoObservacao.setText(pet.getObservacao());

        if(pet.getCaminhoFotoPet() != null){
            carregaImagemPet(pet.getCaminhoFotoPet());
        }
    }

    public ImageView getCampoFotoPet(){

        return campoFotoPet;
    }

    //Metodo para carregar imagem no imageView
    public void carregaImagemPet(String caminhoArquivoPet) {

        pet.setCaminhoFotoPet(caminhoArquivoPet);

        Bitmap imagemPet = BitmapFactory.decodeFile(caminhoArquivoPet);
        //regular a dimensao da imagem
        Bitmap imagemReduzidaPet =  Bitmap.createScaledBitmap(imagemPet, 200, 200, true);

        campoFotoPet.setImageBitmap(imagemReduzidaPet);
    }

    //Metodo para selecionar imagem no imageView
    public void selecionarImagemPet(String caminhoArquivoPet) {

        pet.setCaminhoFotoPet(caminhoArquivoPet);

        Bitmap imagemPet = BitmapFactory.decodeFile(caminhoArquivoPet);
        Bitmap imagemReduzidaPet =  Bitmap.createScaledBitmap(imagemPet, 400, 400, true);

        campoFotoPet.setImageBitmap(imagemReduzidaPet);
    }
}

