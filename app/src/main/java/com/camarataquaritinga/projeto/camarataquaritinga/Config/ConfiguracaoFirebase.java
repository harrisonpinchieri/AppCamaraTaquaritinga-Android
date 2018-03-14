package com.camarataquaritinga.projeto.camarataquaritinga.Config;

import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by harrison on 12-Mar-18.
 */
public final  class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;
    private static CallbackManager mCallbackManager;


    public static DatabaseReference getFirebase() {

        if(referenciaFirebase == null) {

            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){

        if( autenticacao == null){

            autenticacao = FirebaseAuth.getInstance();
            mCallbackManager = CallbackManager.Factory.create();

        }
        return autenticacao;
    }



}
