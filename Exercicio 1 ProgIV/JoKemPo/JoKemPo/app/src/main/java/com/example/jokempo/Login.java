package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jokempo.pessoa.Pessoa;
import com.example.jokempo.pessoaDao.PessoaDao;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Pessoa jogador;
    PessoaDao pessoaDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Botão que vai para a tela de cadastro
        Button buttonCadastro = findViewById(R.id.buttonCadastro);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irTelaCadastro();
            }
        });
        //Setando evento que vai para a tela de cadastro
        //TO DO
        /*
        * [] EXIBIR JOGADORES CADASTRADOS NA LISTA
        *   [] EXIBIR NOME
        *   [] HORAS JOGADAS
        *   [] TAXA DE VITORIAS
        * [] FAZER O LOGIN CLICANDO EM ITENS DA LISTA
        * [] FAZER A OPÇÃO DE DELETAR UM JOGADOR AO CLICAR E SEGURAR
        *
        * */

        //Exibindo jogadores cadastrados na lista


    }

    private void irTelaCadastro(){

        Intent intent = new Intent(this, FormUsuario.class);
        startActivity(intent);
    }

    //Função que popula a lista
    public void populaLista() {
        ArrayList<Pessoa> listaPessoa = new ArrayList<>();

        pessoaDao = new PessoaDao(Login.this);
        listaPessoa = pessoaDao.selectAllPessoa();
        pessoaDao.close();

        ListView listaVisivel = findViewById(R.id.listJogadores);

        if (listaPessoa != null) {
            // Crie um adaptador personalizado para exibir os dados da Pessoa
            ArrayAdapter<Pessoa> arrayAdapterPessoa = new ArrayAdapter<Pessoa>(Login.this, R.layout.list_item_layout, R.id.nomeTextView, listaPessoa) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    // Configure os elementos da lista com os dados da Pessoa
                    Pessoa pessoa = getItem(position);
                    TextView nomeTextView = view.findViewById(R.id.nomeTextView);
                    TextView horasJogadasTextView = view.findViewById(R.id.horasJogadasTextView);
                    TextView taxaVitoriasTextView = view.findViewById(R.id.taxaVitoriasTextView);

                    if (pessoa != null) {

                        double qtdPartidas = pessoa.getQtdPartidas();
                        double qtdVitorias = pessoa.getQtdVitorias();

                        double taxaVitorias = qtdVitorias/qtdPartidas;

                        nomeTextView.setText("Nome: " + pessoa.getNome());
                        horasJogadasTextView.setText("Horas Jogadas: " + pessoa.getHorasJogadas());
                        taxaVitoriasTextView.setText("Taxa de Vitórias: " + taxaVitorias + "%");
                    }

                    return view;
                }
            };

            listaVisivel.setAdapter(arrayAdapterPessoa);
        }
    }


    @Override
    protected void onResume(){
        super.onResume();
        populaLista();
    }
}