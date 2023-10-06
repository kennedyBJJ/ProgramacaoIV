package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.jokempo.pessoa.Pessoa;

public class FormUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_usuario);

        Intent intent = getIntent();
        Pessoa pessoaAlterada = (Pessoa) intent.getSerializableExtra("pessoa-alterada");

        //Colocar o texto nos botoes e views e chamar as funções quando eles são clicados
        if(pessoaAlterada != null){
            //Significa que existe um jogador e ele deve ser alterado
            alterarJogador(pessoaAlterada);//fazer a função
        }else{
            //Significa que não existe jogador e está sendo feito um cadastro
            cadastrarJogador();
        }

    }

    public void cadastrarJogador(){

        EditText editNome = findViewById(R.id.editNome);

        String nome = editNome.getText().toString();

        Pessoa novaPessoa = new Pessoa();

        novaPessoa.setNome(nome);

    }
}