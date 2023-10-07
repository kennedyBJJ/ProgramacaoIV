package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jokempo.pessoa.Pessoa;
import com.example.jokempo.pessoaDao.PessoaDao;

public class FormUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_usuario);

        Intent intent = getIntent();
        Pessoa pessoaAlterada = (Pessoa) intent.getSerializableExtra("pessoa-alterada");
        Button buttonAcao = findViewById(R.id.buttonAcao);

        //Colocar o texto nos botoes e views e chamar as funções quando eles são clicados
        buttonAcao.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                //se existe uma pessoaAlterada, somente deve alterar
                if(pessoaAlterada != null){
                    //Significa que existe um jogador e ele deve ser alterado
                    alterarJogador(pessoaAlterada);
                    return;
                }
                //Significa que não existe jogador e está sendo feito um cadastro
                cadastrarJogador();

            }
        });

    }

    public void cadastrarJogador(){

        EditText editNome = findViewById(R.id.editNome);

        String nome = editNome.getText().toString();

        Pessoa novaPessoa = new Pessoa();

        novaPessoa.setNome(nome);
        novaPessoa.setHorasJogadas(0);
        novaPessoa.setQtdVitorias(0);
        novaPessoa.setQtdPartidas(0);

        PessoaDao pessoaDao = new PessoaDao(FormUsuario.this);

        pessoaDao.salvarPessoa(novaPessoa);
        pessoaDao.close();

    }

    public void alterarJogador(Pessoa pessoaAlterada){
        EditText editNome = findViewById(R.id.editNome);

        String nome = editNome.getText().toString();

        pessoaAlterada.setNome(nome);

        PessoaDao pessoaDao = new PessoaDao(FormUsuario.this);

        pessoaDao.alterarPessoa(pessoaAlterada);
    }
}
