package com.example.apipokemon;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apipokemon.databinding.LvPokemonRowBinding;

import java.time.Instant;
import java.util.ArrayList;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {


        public PokemonAdapter(Context context, int resource, ArrayList<Pokemon> objects) {
        super(context, resource, objects);
        }
    @Override
  public View getView(int position, View convertView, ViewGroup parent) {
       /// Obtenim l'objecte en la possició corresponent
        Pokemon pokemon = getItem(position);
        Log.w("XXXX", pokemon.toString());

        LvPokemonRowBinding binding =null;

        // Mirem a veure si la View s'està reutilitzant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_pokemon_row, parent, false);
        } else {
            binding = (LvPokemonRowBinding) convertView.getTag();
        }

        // Unim el codi en les Views del Layout
             TextView txtListName = convertView.findViewById(R.id.txtListName);
            ImageView imagenPokemon = convertView.findViewById(R.id.imagenPokemon);


        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        txtListName.setText(pokemon.getName());
        Glide.with(getContext()).load(pokemon.getImage()).into(imagenPokemon);


        // Retornem la View replena per a mostrarla
        return binding.getRoot();
    }
}
