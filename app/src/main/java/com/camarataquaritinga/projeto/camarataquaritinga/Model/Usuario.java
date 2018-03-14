package com.camarataquaritinga.projeto.camarataquaritinga.Model;

import com.camarataquaritinga.projeto.camarataquaritinga.Config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

/**
 * Created by harrison on 12-Mar-18.
 */
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha1;
    private String senha2;

    public Usuario() {
    }

    public void salvar(){
        DatabaseReference referenciaFirebase= ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuários").child(getId()).setValue(this);

    }

    @Exclude
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    @Exclude
    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }
}
