package com.example.dmorenoar.persistencia.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dmorenoar.persistencia.Models.Entrenador;
import com.example.dmorenoar.persistencia.Models.Pokemon;
import com.example.dmorenoar.persistencia.R;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    //Creamos la base de datos
    String sqlCreatePokemon = "CREATE TABLE Pokemon(" +
            "ID_Pokemon INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nombrePokemon TEXT," +
            "imagenPokemon INTEGER," +
            "tipoPokemon TEXT," +
            "fuerzaPokemon INTEGER," +
            "ID_Entrenador INTEGER REFERENCES Entrenador(ID_Entrenador)" +
            "ON DELETE CASCADE" +
    ")";

    String sqlCreateEntrenador = "CREATE TABLE Entrenador(" +
            "ID_Entrenador INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nombreEntrenador TEXT," +
            "edadEntrenador INTEGER," +
            "especialidadEntrenador TEXT)";


    public SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }*/

        db.execSQL(sqlCreateEntrenador);
        db.execSQL(sqlCreatePokemon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Eliminamos la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Entrenador");
        db.execSQL("DROP TABLE IF EXISTS Pokemon");

        //Creamos de nuevo la versión de la bd
        db.execSQL(sqlCreateEntrenador);
        db.execSQL(sqlCreatePokemon);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }



    public List<Pokemon> getAllPokemons(SQLiteDatabase db){
        //Seleccionamos todos los registros de la tabla Pokemon
        Cursor cursor = db.rawQuery("Select * from Pokemon", null);
        List<Pokemon> listPokemons = new ArrayList<>();

        if(cursor.moveToFirst()){
            //Iteramos sobre los resultados del cursor rellenando el array
            while(cursor.isAfterLast() == false){
                String namePokemon = cursor.getString(cursor.getColumnIndex("nombrePokemon"));
                int fuerzaPokemon = cursor.getInt(cursor.getColumnIndex("fuerzaPokemon"));
                int imagenPokemon = cursor.getInt(cursor.getColumnIndex("imagenPokemon"));
                String tipoPokemon = cursor.getString(cursor.getColumnIndex("tipoPokemon"));
                int idEntrenador = cursor.getInt(cursor.getColumnIndex("ID_Entrenador"));

                //Antes de insertas buscamos el entrenador asociado al pokemon
                Entrenador entrenador = getEntrenadorById(db, idEntrenador);

                Pokemon p = new Pokemon(namePokemon,tipoPokemon,fuerzaPokemon,imagenPokemon,entrenador);
                System.out.println("Mostramos el entrenador" + entrenador);
                listPokemons.add(p);
                cursor.moveToNext();
            }

        }

        cursor.close();

        return listPokemons;
    }

    public List<Entrenador> getAllEntrenadors(SQLiteDatabase db){
        //Seleccionamos todos los registros de la tabla Pokemon
        Cursor cursor = db.rawQuery("Select * from Entrenador", null);
        List<Entrenador> listEntrenadores = new ArrayList<>();

        if(cursor.moveToFirst()){
            //Iteramos sobre los resultados del cursor rellenando el array
            while(cursor.isAfterLast() == false){

                String nameEntrenador = cursor.getString(cursor.getColumnIndex("nombreEntrenador"));
                int edadEntrenador = cursor.getInt(cursor.getColumnIndex("edadEntrenador"));
                String especialidadEntrenador = cursor.getString(cursor.getColumnIndex("especialidadEntrenador"));

                Entrenador e = new Entrenador(11,nameEntrenador,edadEntrenador,especialidadEntrenador);
                listEntrenadores.add(e);

                cursor.moveToNext();
            }

        }
        cursor.close();

        return listEntrenadores;
    }

    public void insertEntrenador(SQLiteDatabase db, Entrenador e){
        //Comprobamos que la bd este abierta
        if (db.isOpen()){
            //Creamos el registro para insertar el objeto como un contentValue
            ContentValues insert = new ContentValues();

            //Insertamos el entrenador que recibimos - ID es autoincrement
            insert.put("nombreEntrenador", e.getNombre());
            insert.put("edadEntrenador", e.getEdad());
            insert.put("especialidadEntrenador", e.getEspecialidad());

            System.out.println("El insert:" + insert);

            //Insertamos el entrenador
            db.insert("Entrenador", null, insert);
        }else{
            System.out.println("Base de datos no abierta");
        }
    }


    public void insertarPokemon(SQLiteDatabase db, Pokemon p){
        if(db.isOpen()){
            ContentValues insert = new ContentValues();
            insert.put("nombrePokemon", p.getNombre());
            insert.put("tipoPokemon", p.getTipo());
            insert.put("imagenPokemon", p.getImagen());
            insert.put("fuerzaPokemon", p.getFuerza());
            insert.put("ID_Entrenador", p.getEntrenador().getIdEntrenador());

            db.insert("Pokemon", null, insert);
        }else{
            System.out.println("Base de datos no abierta");
        }

    }

    public Entrenador getEntrenadorById(SQLiteDatabase db , int idEntrenador){

        Cursor cursor = db.rawQuery("Select * from Entrenador WHERE ID_Entrenador = ?", new String[] {String.valueOf(idEntrenador)});
        // Opcion 2 - pasandole los dos campos a consultar con ?
        // Cursor cursor = db.rawQuery("Select * from Entrenador WHERE ? = ?", new String[] {"ID_Entrenador",String.valueOf(idEntrenador)});
        Entrenador e = new Entrenador();

        //Nos aseguramos que almenos hemos encontrado el entrenador con el id
        if(cursor.moveToFirst()){

            //podemos recuperar las columnas por indice o por registro
            //La columa 0 en este caso pertenece al id
            e.setNombre(cursor.getString(1));
            e.setEdad(cursor.getInt(cursor.getColumnIndex("edadEntrenador")));
            e.setEspecialidad(cursor.getString(cursor.getColumnIndex("especialidadEntrenador")));
            e.setIdEntrenador(idEntrenador);
        }else{
            System.out.println("No existe entrenador con ese id");
        }

        cursor.close();
        return e;

    }


    public void removeAllEntrenadores(SQLiteDatabase db){
        db.delete("Entrenador","",null);
    }



}
