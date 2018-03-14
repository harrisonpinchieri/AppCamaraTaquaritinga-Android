package com.camarataquaritinga.projeto.camarataquaritinga;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.camarataquaritinga.projeto.camarataquaritinga.Config.ConfiguracaoFirebase;
import com.camarataquaritinga.projeto.camarataquaritinga.Model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha1;
    private EditText senha2;
    private Button   botaoCadastrar;
    private Usuario  usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

      nome = (EditText) findViewById(R.id.edit_cadastro_nome);
      email = (EditText) findViewById(R.id.edit_cadastro_email);
      senha1 = (EditText) findViewById(R.id.edit_cadastro_senha1);
      senha2 = (EditText) findViewById(R.id.edit_cadastro_senha2);
      botaoCadastrar = (Button) findViewById(R.id.bt_cadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha1(senha1.getText().toString());
                usuario.setSenha2(senha2.getText().toString());
                cadastrarUsuario();
            }
        });

    }

    private void cadastrarUsuario(){

       if(usuario.getSenha1().toString().equals(usuario.getSenha2().toString())){

           autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
           autenticacao.createUserWithEmailAndPassword(
                   usuario.getEmail(),
                   usuario.getSenha1()
           ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()) {
                       Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();

                       FirebaseUser usuarioFireBase = task.getResult().getUser();
                       usuario.setId(usuarioFireBase.getUid() );
                       usuario.salvar();

                       autenticacao.signOut();
                       finish();


                   } else {

                       String erroExcecao = "";

                       try{
                           throw  task.getException();
                       } catch (FirebaseAuthWeakPasswordException e) {
                           erroExcecao = "Digite uma senha mais forte, contendo mais caracteres e com letras e números!";
                       } catch (FirebaseAuthInvalidCredentialsException e) {
                           erroExcecao = "O e-mail digitado é invalido,digite novamente seu e-mail!";

                       } catch (FirebaseAuthUserCollisionException e) {
                           erroExcecao = "O e-mail já está em uso no App!";

                       } catch (Exception e) {
                           erroExcecao = "Erro ao efetuar o cadastro!";

                           e.printStackTrace();
                       }

                       Toast.makeText(CadastroUsuarioActivity.this, "Erro "+erroExcecao, Toast.LENGTH_SHORT).show();





                   }
               }
           });
        }else{

            Toast.makeText(CadastroUsuarioActivity.this, "As senhas não condizem", Toast.LENGTH_SHORT).show();
        }


    }

}
