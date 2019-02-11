package com.example.dmorenoar.persistencia.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.dmorenoar.persistencia.DBHelpers.SQLHelper;
import com.example.dmorenoar.persistencia.Models.Entrenador;
import com.example.dmorenoar.persistencia.Models.Pokemon;
import com.example.dmorenoar.persistencia.MyAdapter.MyAdapter;
import com.example.dmorenoar.persistencia.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPokemon;
    private MyAdapter myAdapter;
    private List<Pokemon> listPokemons = new ArrayList<>();

    //Creamos la instancia al dbHelper
    private SQLHelper sqlHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_pokemons);



        //Vinculamos el listview al layout
        listViewPokemon = (ListView) findViewById(R.id.listViewPokemons);

        //Creamos el objeto sqlHelper y la base de datos
        sqlHelper = new SQLHelper(this,"Liga.db", null, 1);
        db = sqlHelper.getWritableDatabase();


        insertEntrenador(db,new Entrenador(1,"Ash", 14, "Eléctrico"));

        insertPokemon(db, new Pokemon("Bulbasaur", "Planta", 33, R.drawable.ic_bulbasaur_web, new Entrenador(1,"Ash", 14, "Eléctrico")));
        insertPokemon(db, new Pokemon("Charmander", "Fuego", 14, R.drawable.ic_charmander_web, new Entrenador(1,"Ash", 14, "Eléctrico")));


        //Rellenamos el ListView con los pokemons recogidos de la BD
        this.listPokemons = getAllPokemons(db);


        //Finalmente creamos el adaptador pasandole la lista de pokemons
        myAdapter = new MyAdapter(this,R.layout.list_view_pokemons_item,listPokemons);
        listViewPokemon.setAdapter(myAdapter);



        for (Pokemon p: sqlHelper.getAllPokemons(db)){
            Log.d("MyApp",p.getNombre());
            System.out.println(p);
        }

        /*
        for (Entrenador e: sqlHelper.getAllEntrenadors(db)){
            Log.d("MyApp",e.getNombre());
            System.out.println(e);
        }*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private List<Pokemon> getAllPokemons(SQLiteDatabase db) {
        return sqlHelper.getAllPokemons(db);
    }

    private List<Entrenador> getAllEntrenadores(SQLiteDatabase db){
        return sqlHelper.getAllEntrenadors(db);
    }

    private void insertEntrenador(SQLiteDatabase db, Entrenador e){
        sqlHelper.insertEntrenador(db, e);
    }

    private void insertPokemon(SQLiteDatabase db, Pokemon p){
        sqlHelper.insertarPokemon(db, p);
    }

}
