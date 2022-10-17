package com.example.apipokemon;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonApi {
    ArrayList<Pokemon> getPokemons() {
        String url = "https://pokeapi.co/api/v2/pokemon";
        String urlBase= "https://pokeapi.co/api/v2";

        Uri.Builder pokemonUri =Uri.parse(urlBase).buildUpon().appendPath("pokemon");
        Uri.Builder urlhabilidades =Uri.parse(urlBase).buildUpon().appendPath("ability");

        try {
            String result = HttpUtils.get(url);


            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");

            ArrayList<Pokemon> listaPokemon = new ArrayList<>();

            for (int i = 0; i < result.length(); i++) {
                try {

                JSONObject pokemonJson = results.getJSONObject(i);

                Pokemon pokemon1 = new Pokemon();
                pokemon1.setName(pokemonJson.getString("name"));
                pokemon1.setDetailsUrl(pokemonJson.getString("url"));

                 String resultDetails = HttpUtils.get(pokemon1.getDetailsUrl());

                 JSONObject jsonDetails = new JSONObject(resultDetails);
                 JSONObject sprites = jsonDetails.getJSONObject("sprites");


                pokemon1.setHeight(jsonDetails.getInt("height"));
                pokemon1.setImage(sprites.getString("front_default"));
                pokemon1.setWeight(jsonDetails.getInt("weight"));
                listaPokemon.add(pokemon1);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return listaPokemon;
           // System.out.println(listaPokemon);.0


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
