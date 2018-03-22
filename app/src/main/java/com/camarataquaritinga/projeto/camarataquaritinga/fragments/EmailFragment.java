package com.camarataquaritinga.projeto.camarataquaritinga.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.camarataquaritinga.projeto.camarataquaritinga.R;
import com.camarataquaritinga.projeto.camarataquaritinga.SendMail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment   implements View.OnClickListener{

    private TextView texto;
    EditText editTextEmail;
    EditText editTextSubject;
    EditText editTextMessage;
    Button buttonSend;
    TextView txt_contador;





    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_email,container,false);





            //Initializing the views
            txt_contador  = (TextView) view.findViewById(R.id.txt_contador);
            editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
            editTextSubject = (EditText) view.findViewById(R.id.editTextSubject);
            editTextMessage = (EditText) view.findViewById(R.id.editTextMessage);

            Bundle bundle = getArguments();
            String Email = bundle.getString("email");
            editTextEmail.setText(Email);

            buttonSend = (Button) view.findViewById(R.id.buttonSend);

            //Adding click listener
            buttonSend.setOnClickListener(this);


            editTextMessage.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String texto = editTextMessage.getText().toString();
                    int caracter = texto.length()+1;
                    txt_contador.setText("" + caracter);
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        // Inflate the layout for this fragment
        return view;
    }

    private void sendEmail() {
        //Getting content for email

        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();



        String nome = user.getDisplayName();
        String Email= user.getEmail();



        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();
        String Mensagem = "Nome: "+nome+"\n"+"Email do Remetente: "+ Email +"\n" +"Mensagem: "+ message;

        if(!email.isEmpty() && !subject.isEmpty() && !message.isEmpty()){

          if(message.length() < 100){

              alert("O mínimo de caracteres é 100");
          }else{
        //Creating SendMail object
        SendMail sm = new SendMail(getContext(), email, subject, Mensagem);

        //Executing sendmail to send email
        sm.execute();}
        }else{

            alert("Por favor, preencha todos os campos.");
        }




    }

    private void alert(String s) {

        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        sendEmail();
    }





}




