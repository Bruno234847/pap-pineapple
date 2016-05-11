package com.pap.pap_v01.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.pap.pap_v01.R;
import com.pap.pap_v01.modelo.Pet;

import java.util.List;

public class PetAdapter extends BaseAdapter {

    private List<Pet> pets;
    private Activity activityPet;

    public PetAdapter(List<Pet> pets, Activity activityPet){
        this.pets = pets;
        this.activityPet = activityPet;
    }

    @Override
    public int getCount() {
        return pets.size();
    }

    @Override
    public Object getItem(int position) {
        return pets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pets.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Pet pet = pets.get(position);

        LayoutInflater inflaterPet = activityPet.getLayoutInflater();
        View linhaPet =  inflaterPet.inflate(R.layout.item_pet, null);

        TextView nomePet = (TextView) linhaPet.findViewById(R.id.nomePet);
        nomePet.setText(pet.getNome());

        ImageView fotoPet = (ImageView) linhaPet.findViewById(R.id.fotoPet);

        if(pet.getCaminhoFotoPet() != null) {
            Bitmap imagemPet = BitmapFactory.decodeFile(pet.getCaminhoFotoPet());
            Bitmap imagemReduzidaPet = Bitmap.createScaledBitmap(imagemPet, 200, 200, true);
            fotoPet.setImageBitmap(imagemReduzidaPet);
        }else{
            fotoPet.setImageResource(R.drawable.pet);
        }
        return linhaPet;
    }
}

