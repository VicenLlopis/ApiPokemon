package com.example.apipokemon;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonViewModel extends AndroidViewModel {

    private final Application app;
    private final PokemonDb db;
    private MutableLiveData<List<Pokemon>> pokemones;
    private Pokemon pokemonDao;


    public PokemonViewModel(@NonNull Application application) {
        super(application);
        this.app =application;
        this.db =PokemonDb.getDatabase(this.app);
        this.pokemonDao = this.db.getPokemonDao();
    }

    public MutableLiveData<List<Pokemon>> getPokemones(){

        if(pokemones==null){
            pokemones = new MutableLiveData<>();
            refresh();
        }

       return pokemones;
    }


    private void refresh() {
  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());

        ExecutorService executor = Executors.newSingleThreadExecutor();


        executor.execute(() -> {
            PokemonApi api = new PokemonApi();
            ArrayList<Pokemon> pokemons = api.getPokemons();

            this.pokemones.postValue(pokemons);



        });

    }
}
