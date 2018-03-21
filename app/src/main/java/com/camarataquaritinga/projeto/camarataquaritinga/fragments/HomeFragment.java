package com.camarataquaritinga.projeto.camarataquaritinga.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.camarataquaritinga.projeto.camarataquaritinga.R;
import com.camarataquaritinga.projeto.camarataquaritinga.adapter.ListaVereadoresAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment   {


    ListView list;
    private Boolean status;



    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);





        list = (ListView) view.findViewById(R.id.listViewVereadoresId);



        list.setAdapter(new ListaVereadoresAdapter(getActivity()));


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                TextView txt_Email = (TextView) view.findViewById(R.id.txt_Email);

                String email= txt_Email.getText().toString();




                FragmentManager manager = getFragmentManager();



                    EmailFragment emailFragment = new EmailFragment();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.layout_home,emailFragment);
                    transaction.addToBackStack("1");
                    Bundle budle = new Bundle();
                    budle.putString("email",email);
                    emailFragment.setArguments(budle);
                    transaction.commit();
















            }
        });




      /*  View view = inflater.inflate(R.layout.fragment_home,container,false);

        String[] cargo = {
                "Presidente","Vice Presidente","1° Secretário","2° Secretário", "Vereador","Vereador",
                "Vereador","Vereador","Vereador","Vereador","Vereador","Vereador","Vereador",
                "Vereador","Vereador"
        };
        String[] desc ={"José Rodrigo de Pietro","Marcos Rui Gomes Marona"};
        Integer[] imgid ={R.drawable.item_presidente_1,R.drawable.item_vicepresidente_2};


        ListView listView = (ListView) view.findViewById(R.id.listViewVereadoresId);


        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
            getActivity(),
            android.R.layout.simple_list_item_1,
                desc
        );
*/




      //  listView.setAdapter(listViewAdapter);

     //  return inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }



}

