package com.camarataquaritinga.projeto.camarataquaritinga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.camarataquaritinga.projeto.camarataquaritinga.Config.ConfiguracaoFirebase;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button botaoSair;
    private FirebaseAuth autenticacao;
    private Toolbar toolbar;
    private MenuPopupWindow.MenuDropDownListView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();



        toolbar =(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Câmara Taquaritinga");

        setSupportActionBar(toolbar);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.item_sair:
                logout();
                return true;
            case R.id.item_configuracoes:
               return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
      private void logout() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.signOut();
        LoginManager.getInstance().logOut();


       Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
          finish();
    }



}
