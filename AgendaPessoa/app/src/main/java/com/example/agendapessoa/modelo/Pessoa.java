package com.example.agendapessoa.modelo;

import java.io.Serializable;
/*
* A serialização significa salvar o estado atual dos objetos em arquivos em formato binário para
* o seu computador, sendo assim ess estado poderá ser recuperado posteriormente recriando o
* o objeto em memória assim como ele estava no momento da sua serialização
* */
public class Pessoa implements Serializable {

    private int id;
    private String nome;
    private int idade;
    private String endereco;
    private String telefone;

    public Pessoa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString(){ return nome;}
}
