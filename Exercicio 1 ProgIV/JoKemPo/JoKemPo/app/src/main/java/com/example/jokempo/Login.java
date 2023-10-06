package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.jokempo.pessoa.Pessoa;
import com.example.jokempo.pessoaDao.PessoaDao;

public class Login extends AppCompatActivity {

    Pessoa jogador;
    PessoaDao pessoaDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Botão que vai para a tela de cadastro
        Button buttonCadastro = findViewById(R.id.buttonCadastro);

        //Setando evento que vai para a tela de cadastro


    }

    private void irTelaCadastro(){

        Intent intent = new Intent(this, FormUsuario.class);
        startActivity(intent);
    }
}