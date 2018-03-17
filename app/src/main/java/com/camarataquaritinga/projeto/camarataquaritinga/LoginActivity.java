package com.camarataquaritinga.projeto.camarataquaritinga;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.camarataquaritinga.projeto.camarataquaritinga.Config.ConfiguracaoFirebase;
import com.camarataquaritinga.projeto.camarataquaritinga.Model.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {



    private EditText email;
    private EditText senha;
    private Button   botaoLogar;

    private FirebaseAuth mFirebaseAuth;
    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;

    private TextView recuperasenha;

    private Usuario usuario;
    private FirebaseAuth autenticacao;

    private DatabaseReference referenciaFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        referenciaFirebase = ConfiguracaoFirebase.getFirebase();

        verificarUsuarioLogado();


        email =     (EditText) findViewById(R.id.edit_login_email);
        senha =     (EditText) findViewById(R.id.edit_login_senha);
        botaoLogar= (Button)   findViewById(R.id.bt_login);
        recuperasenha = (TextView) findViewById(R.id.text_recupera_senha);

        recuperasenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder mBuilder= new AlertDialog.Builder(LoginActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_recuperar_senha,null);
                final EditText mEmail = (EditText) mView.findViewById(R.id.edit_recupera_email);
                Button   mEnviar= (Button)   mView.findViewById(R.id.btnEnviar);

                mEnviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        if(!mEmail.getText().toString().isEmpty()){

                            String email = mEmail.getText().toString();
                            mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        alert("Enviamos um e-mail  para a redefinição de senha.");
                                        finish();
                                    }else{

                                        alert("Erro ao enviar e-mail");
                                        finish();
                                    }

                                }
                            });



                        }else{

                            alert("Digite o seu e-mail");
                        }


                    }

                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();

                dialog.show();

            }
        });



        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario =new Usuario();

                if(email.getText().toString().isEmpty() )  {

                    alert("Digite seu e-mail.");

                }else if(senha.getText().toString().isEmpty() ){

                    alert("Digite sua senha.");

                }else{

                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha1(senha.getText().toString());
                    validarLogin();

                }





            }
        });


        inicializarComponente();
        inicializarFirebaseCallback();
        clickButton();




    }

    private void clickButton() {

        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                firebaseLogin(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

                alert("Operação cancelada");

            }

            @Override
            public void onError(FacebookException error) {

                alert("Erro no login com Facebook");
            }
        });

    }

    private void firebaseLogin(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());

        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{

                            alert("Erro de autenticação com o Firebase");
                        }
                    }
                });

    }

    private void alert(String s) {
        Toast.makeText(LoginActivity.this,s, Toast.LENGTH_SHORT).show();
    }

    private void inicializarFirebaseCallback() {

        mFirebaseAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void inicializarComponente() {
        mLoginButton = (LoginButton) findViewById(R.id.btnLoginF);
        mLoginButton.setReadPermissions("email","public_profile");
    }

    private void verificarUsuarioLogado(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //se o usuário atual é diferente de nulo, quer dizer que o usuário já está logado e vai abrir a tela principal
        if(autenticacao.getCurrentUser() != null){

            abrirTelaPrincipal();

        }
    }



    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();



            autenticacao.signInWithEmailAndPassword(
                    usuario.getEmail(),
                    usuario.getSenha1()

            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //se deu certo a autenticação de login do usuário
                    if (task.isSuccessful()) {

                        checarEmailVerificado();

                    } else {

                        String erroExcecao = "";

                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidCredentialsException e) {

                            erroExcecao = "Senha ou e-mail incorreto!";

                        } catch (Exception e) {

                            erroExcecao = "Senha ou e-mail incorreto!";
                            e.printStackTrace();
                        }

                        Toast.makeText(LoginActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();

                    }

                }
            });



        }

    private void checarEmailVerificado() {
        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();
        if(user.isEmailVerified()){
            Toast.makeText(LoginActivity.this, "Sucesso ao fazer login", Toast.LENGTH_SHORT).show();
            abrirTelaPrincipal();

        }else{

            FirebaseAuth.getInstance().signOut();
            alert("Você ainda não verificou o seu e-mail");

        }
    }


    private void abrirTelaPrincipal(){

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
        startActivity(intent);
    }

}
