package com.example.dmorenoar.persistencia.MyAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmorenoar.persistencia.Models.Pokemon;
import com.example.dmorenoar.persistencia.R;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    //Creamos el contexto donde cargaremos el adaptador
    private Context context;
    private int layout;
    private List<Pokemon> listPokemons;
    private ViewHolder viewHolder;

    public MyAdapter(Context context, int layout, List<Pokemon> listPokemons) {
        this.context = context;
        this.layout = layout;
        this.listPokemons = listPokemons;
    }


    @Override
    public int getCount() {
        return listPokemons.size();
    }

    //Le indicamos que en lugar de un object el adapter devuelve un pokemon
    @Override
    public Pokemon getItem(int position) {
        return this.listPokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creamos la vista del adaptador

        //Asignamos el viewHolder al convertView

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(layout, null);

            //Creamos el viewHolder con el constructor pasandole todas las instancias del adaptar asociado a la layout
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {
            //Si ya esta referenciado, obtenemos la que hemos creado con el convertiView y asi la reciclamos para no volver a usar el findById
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //Asignamos los valores al viewHolder usando el método bind y pasandole el pokemon en la posición
        viewHolder.bind(getItem(position));

        return convertView;
    }

    public class ViewHolder {

        private TextView namePokemon;
        private TextView tipoPokemon;
        private TextView fuerzaPokemon;
        private ImageView imagenPokemon;
        private TextView nameEntrenador;

        public ViewHolder(View view) {
            namePokemon = (TextView) view.findViewById(R.id.namePokemon);
            tipoPokemon = (TextView) view.findViewById(R.id.tipoPokemon);
            fuerzaPokemon = (TextView) view.findViewById(R.id.fuerzaPokemon);
            imagenPokemon = (ImageView) view.findViewById(R.id.imagenPokemon);
            nameEntrenador = (TextView) view.findViewById(R.id.nameEntrenador);
        }


        public void bind(Pokemon p) {
            this.namePokemon.setText(p.getNombre());
            this.fuerzaPokemon.setText(String.valueOf(p.getFuerza()));
            this.imagenPokemon.setImageResource(p.getImagen());
            this.tipoPokemon.setText(p.getTipo());
            Log.d("MyApp", p.getEntrenador().getNombre());
            System.out.println(p.getEntrenador().getNombre());
            this.nameEntrenador.setText(p.getEntrenador().getNombre());
        }

    }
}
