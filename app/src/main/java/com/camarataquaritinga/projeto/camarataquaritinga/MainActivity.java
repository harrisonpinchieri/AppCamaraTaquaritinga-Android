package com.camarataquaritinga.projeto.camarataquaritinga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.camarataquaritinga.projeto.camarataquaritinga.Config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button botaoSair;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botaoSair = (Button)findViewById(R.id.bt_sair);

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

                autenticacao.signOut();

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}
