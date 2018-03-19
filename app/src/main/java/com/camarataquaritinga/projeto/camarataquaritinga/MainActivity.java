package com.camarataquaritinga.projeto.camarataquaritinga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.camarataquaritinga.projeto.camarataquaritinga.Config.ConfiguracaoFirebase;
import com.camarataquaritinga.projeto.camarataquaritinga.adapter.TabsAdapter;
import com.camarataquaritinga.projeto.camarataquaritinga.util.SlidingTabLayout;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private Button botaoSair;
    private FirebaseAuth autenticacao;
    private Toolbar toolbar;
  //  private MenuPopupWindow.MenuDropDownListView menu;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();



        toolbar =(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Câmara Taquaritinga");

        setSupportActionBar(toolbar);

        //Configura abas
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab_main);
        viewPager =(ViewPager) findViewById(R.id.view_pager_main);



        //configurar adapter
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(tabsAdapter);
        slidingTabLayout.setCustomTabView(R.layout.tab_view,R.id.text_item_tab);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.branco));
        slidingTabLayout.setViewPager(viewPager);








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
            case R.id.item_Sobre:


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
