package com.example.agendapessoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agendapessoa.dao.PessoaDao;
import com.example.agendapessoa.modelo.Pessoa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listVisivel;
    Button btnNovoCadastro;
    Pessoa pessoa;
    PessoaDao pessoaDao;
    ArrayList<Pessoa> arrayListPessoa;
    ArrayAdapter<Pessoa> arrayAdapterPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        listVisivel = (ListView) findViewById(R.id.listPessoas);
        btnNovoCadastro = (Button) findViewById(R.id.btnNovoCadastro);
        registerForContextMenu(listVisivel);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FormPessoa.class);
                startActivity(i);
            }
        });

        listVisivel.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Pessoa pessoaEnviada = (Pessoa) arrayAdapterPessoa.getItem(position);
                Intent i = new Intent(MainActivity.this, FormPessoa.class);
                i.putExtra("pessoa-enviada", pessoaEnviada);
                startActivity(i);

            }
        });

        listVisivel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                pessoa = arrayAdapterPessoa.getItem(position);
                return false;
            }
        });
    }

    public void populaLista(){

        pessoaDao = new PessoaDao(MainActivity.this);
        arrayListPessoa = pessoaDao.selectAllPessoa();
        pessoaDao.close();

        if(listVisivel != null){
            arrayAdapterPessoa = new ArrayAdapter<Pessoa>(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    arrayListPessoa);
            listVisivel.setAdapter(arrayAdapterPessoa);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        populaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        MenuItem mDelete = menu.add("Excluir Registro");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                long retornoDB;
                pessoaDao = new PessoaDao(MainActivity.this);
                retornoDB = pessoaDao.excluirPessoa(pessoa);
                pessoaDao.close();

                if(retornoDB == -1){
                    alert("Erro ao excluir");
                }else{
                    alert("Registro excluido com sucesso");
                }

                populaLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    private void alert(String s){ Toast.makeText(this, s, Toast.LENGTH_SHORT).show(); }
}