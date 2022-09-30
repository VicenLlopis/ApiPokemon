package com.example.apipokemon;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonApi {


    ArrayList<Pokemon> getPokemons() {
        String url = "https://pokeapi.co/api/v2/pokemon/";

        try {
            String result = HttpUtils.get(url);

            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");

            ArrayList <Pokemon> listapokemon = new ArrayList <Pokemon> ();
            for (int i = 0; i < result.length(); i++) {
                JSONObject pokemonJson = results.getJSONObject(i);

                Pokemon pokemon1 = new Pokemon();

                pokemon1.setName(pokemonJson.getString("name"));
                pokemon1.setDetailsUrl(pokemonJson.getString("url"));

                String resultDetails = HttpUtils.get(pokemon1.getDetailsUrl());
                JSONObject jsonDetails = new JSONObject(resultDetails);

                pokemon1.setHeight(jsonDetails.getInt("height"));
                pokemon1.setWeight(jsonDetails.getInt("weight"));
                //pokemon1.setImage(jsonDetails.getString("image"));


                listapokemon.add(pokemon1);
            }


            Log.e("XXX Pokemons XXX", result);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
