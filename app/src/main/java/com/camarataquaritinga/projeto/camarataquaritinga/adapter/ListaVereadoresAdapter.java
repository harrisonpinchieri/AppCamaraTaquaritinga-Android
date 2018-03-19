package com.camarataquaritinga.projeto.camarataquaritinga.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.camarataquaritinga.projeto.camarataquaritinga.R;

import java.util.ArrayList;

/**
 * Created by harrison on 19-Mar-18.
 */
public class ListaVereadoresAdapter extends BaseAdapter {

    ArrayList<SingleRow> list;

    Context context;
    public ListaVereadoresAdapter(Context c){

        context =c;
        list= new ArrayList<SingleRow>();

        Resources res = c.getResources();
        String[] titulo = res.getStringArray(R.array.Titulo);
        String[] nome = res.getStringArray(R.array.Nome);
        String[] partido = res.getStringArray(R.array.Partido);
        String[] email = res.getStringArray(R.array.Email);
        int[] img ={R.drawable.item_presidente_1,R.drawable.item_vicepresidente_2,R.drawable.item_1secretario_3,R.drawable.item_2secretario_4,R.drawable.item_vereador_5,
                R.drawable.item_vereador_6,R.drawable.item_vereador_7,R.drawable.item_vereador_8,R.drawable.item_vereador_9,R.drawable.item_vereador_10,
                R.drawable.item_vereador_11,R.drawable.item_vereador_12,R.drawable.item_vereador_13,R.drawable.item_vereador_14,R.drawable.item_vereador_15};

        for(int i=0;i<15;i++){

            list.add(new SingleRow(titulo[i],nome[i],partido[i],email[i],img[i]));
        }

    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    // reservado p/ bd
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {



        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_row,viewGroup,false);
        TextView titulo = (TextView) row.findViewById(R.id.txt_Titulo);
        TextView nome = (TextView) row.findViewById(R.id.txt_Nome);
        TextView email = (TextView) row.findViewById(R.id.txt_Email);
        TextView partido = (TextView) row.findViewById(R.id.txt_Partido);
        ImageView img = (ImageView)row.findViewById(R.id.img_FotoVereador);

        SingleRow temp= list.get(i);

        titulo.setText(temp.titulo);
        nome.setText(temp.nome);
        email.setText(temp.email);
        partido.setText(temp.partido);
        img.setImageResource(temp.img);

        return row;
    }


}

class SingleRow{

    int    img;
    String titulo;
    String nome;
    String partido;
    String email;

    SingleRow(String titulo,String nome,String partido,String email,int img){

        this.titulo = titulo;
        this.nome = nome;
        this.partido = partido;
        this.email=email;
        this.img = img;

    }

}
