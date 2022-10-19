package com.example.apipokemon;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Pokemon.class}, version = 1)
public abstract class  PokemonDb extends RoomDatabase {

        private static PokemonDb INSTANCE;

        public static PokemonDb getDatabase(Context context) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(
                                context.getApplicationContext(),
                                PokemonDb.class, "db"
                        ).build();
            }
            return INSTANCE;
        }

        public abstract Pokemon getPokemonDao();
    }


