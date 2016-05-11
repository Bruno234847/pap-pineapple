package com.pap.pap_v01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import com.pap.pap_v01.modelo.Proprietario;

public class HelperProprietario {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoCidade;
    private final EditText campoCep;
    private final EditText campoTelefone;
    private final EditText campoCelular;
    private final EditText campoSenha;
    private final EditText campoRepetirSenha;
    private final EditText campoEmail;
    private final ImageView campoFoto;

    private Proprietario proprietario;

    public HelperProprietario(FormularioProprietarioActivity activity){

//PEGANDO OS DADOS PARA SALVAR

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoCidade = (EditText) activity.findViewById(R.id.formulario_cidade);
        campoCep = (EditText) activity.findViewById(R.id.formulario_cep);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoCelular = (EditText) activity.findViewById(R.id.formulario_celular);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoSenha = (EditText) activity.findViewById(R.id.formulario_senha);
        campoRepetirSenha = (EditText) activity.findViewById(R.id.formulario_repetirsenha);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);

        proprietario = new Proprietario();
    }

    public Proprietario pegaProprietario() {
        proprietario.setNome(campoNome.getText().toString());
        proprietario.setEndereco(campoEndereco.getText().toString());
        proprietario.setCidade(campoCidade.getText().toString());
        proprietario.setCep(campoCep.getText().toString());
        proprietario.setTelefone(campoTelefone.getText().toString());
        proprietario.setCelular(campoCelular.getText().toString());
        proprietario.setEmail(campoEmail.getText().toString());
        proprietario.setSenha(campoSenha.getText().toString());
        proprietario.setRepetirSenha(campoRepetirSenha.getText().toString());


        return proprietario;
    }

    public void preencherProprietario(Proprietario proprietario) {

//RETORNA DADOS AO FORMULARIO PARA EDIÇÃO

        campoNome.setText(proprietario.getNome());
        campoEndereco.setText(proprietario.getEndereco());
        campoCidade.setText(proprietario.getCidade());
        campoCep.setText(proprietario.getCep());
        campoTelefone.setText(proprietario.getTelefone());
        campoCelular.setText(proprietario.getCelular());
        campoEmail.setText(proprietario.getEmail());
        campoSenha.setText(proprietario.getEmail());
        campoRepetirSenha.setText(proprietario.getEmail());

        this.proprietario = proprietario;

        if(proprietario.getCaminhoFoto() != null){
            carregaImagem(proprietario.getCaminhoFoto());
        }
    }

    public ImageView getCampoFoto(){

        return campoFoto;
    }

    //Metodo para carregar imagem no imageView
    public void carregaImagem(String caminhoArquivo) {

        proprietario.setCaminhoFoto(caminhoArquivo);

        Bitmap imagemProprietario = BitmapFactory.decodeFile(caminhoArquivo);
        Bitmap imagemReduzida =  Bitmap.createScaledBitmap(imagemProprietario, 200, 200, true);
        campoFoto.setImageBitmap(imagemReduzida);
        campoFoto.setScaleType(ImageView.ScaleType.FIT_END);
    }
}


