package com.camarataquaritinga.projeto.camarataquaritinga.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.camarataquaritinga.projeto.camarataquaritinga.R;
import com.camarataquaritinga.projeto.camarataquaritinga.StreamingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvcamaraFragment extends Fragment {


    private ImageView botaoTv;

    public TvcamaraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvcamara, container, false);


        botaoTv = (ImageView) view.findViewById(R.id.videoViewStreamingID);


        botaoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(),StreamingActivity.class);
                startActivity(intent);
            }
        });




        // Inflate the layout for this fragment

        return view;
    }

}
