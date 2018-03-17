package com.camarataquaritinga.projeto.camarataquaritinga.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.camarataquaritinga.projeto.camarataquaritinga.R;
import com.camarataquaritinga.projeto.camarataquaritinga.fragments.HomeFragment;
import com.camarataquaritinga.projeto.camarataquaritinga.fragments.TvcamaraFragment;

/**
 * Created by harrison on 17-Mar-18.
 */
public class TabsAdapter  extends FragmentStatePagerAdapter{

    private Context context;
   // private String[] abas = new String[]{"HOME","TVCAMARA"} ;
    private int[] icones = new int[]{R.drawable.ic_people, R.drawable.ic_videocam};
    private int tamanhoIcone;

    //objeto responsável por gerenciar os fragmentos que serão retornados pelo método getItem
    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int) (36 * escala);
    }

    @Override
    //ao pressionar o HOME, vai chamar o método getitem e esse método baseado na posição, iremos retornar o conteúdo da viewPager
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch  (position){

            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment  = new TvcamaraFragment();
                break;


        }
        return fragment;

    }


    @Override
    public CharSequence getPageTitle(int position) {

        //recuperar icone de acordo com a posição
        Drawable drawable = ContextCompat.getDrawable(this.context,icones[position]);
        drawable.setBounds(0,0,tamanhoIcone,tamanhoIcone);

        //Permite colocar uma imagem dentro de um texto
        ImageSpan imageSpan = new ImageSpan(drawable);

        //classe utilizada para retornar Charsequence
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public int getCount() {

        return icones.length;
    }
}
