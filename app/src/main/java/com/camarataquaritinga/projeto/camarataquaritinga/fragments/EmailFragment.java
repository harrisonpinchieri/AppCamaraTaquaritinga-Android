package com.camarataquaritinga.projeto.camarataquaritinga.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.camarataquaritinga.projeto.camarataquaritinga.R;
import com.camarataquaritinga.projeto.camarataquaritinga.SendMail;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment   implements View.OnClickListener{

    private TextView texto;
    EditText editTextEmail;
    EditText editTextSubject;
    EditText editTextMessage;
    Button buttonSend;


    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_email,container,false);











            //Initializing the views
            editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
            editTextSubject = (EditText) view.findViewById(R.id.editTextSubject);
            editTextMessage = (EditText) view.findViewById(R.id.editTextMessage);

            Bundle bundle = getArguments();
            String Email = bundle.getString("email");
            editTextEmail.setText(Email);

            buttonSend = (Button) view.findViewById(R.id.buttonSend);

            //Adding click listener
            buttonSend.setOnClickListener(this);




        // Inflate the layout for this fragment
        return view;
    }

    private void sendEmail() {
        //Getting content for email


        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(getContext(), email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View view) {

        sendEmail();
    }



}




