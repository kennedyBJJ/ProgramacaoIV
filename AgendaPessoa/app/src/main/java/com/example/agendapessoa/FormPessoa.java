package com.example.agendapessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agendapessoa.dao.PessoaDao;
import com.example.agendapessoa.modelo.Pessoa;

public class FormPessoa extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pessoa);

        EditText editNome, editIdade, editEndereco, editTelefone;
        Button btnVariavel;
        Pessoa pessoa, altPessoa;
        PessoaDao pessoaDao;


        Intent i = getIntent();
        altPessoa = (Pessoa) i.getSerializableExtra("pessoa-enviada");
        pessoa = new Pessoa();
        pessoaDao = new PessoaDao(FormPessoa.this);

        editNome = (EditText) findViewById(R.id.editNome);
        editIdade = (EditText) findViewById(R.id.editIdade);
        editEndereco = (EditText) findViewById(R.id.editEndereco);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        btnVariavel = (Button) findViewById(R.id.btnVariavel);

        if(altPessoa != null){
            btnVariavel.setText("Alterar");
            editNome.setText(altPessoa.getNome());
            editIdade.setText(altPessoa.getIdade());
            editEndereco.setText(altPessoa.getEndereco());
            editTelefone.setText(altPessoa.getTelefone());

            pessoa.setId(altPessoa.getId());
        }else{
            btnVariavel.setText("Salvar");
        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pessoa.setNome(editNome.getText().toString());
                pessoa.setIdade(Integer.parseInt(editIdade.getText().toString()));
                pessoa.setEndereco(editEndereco.getText().toString());
                pessoa.setTelefone(editTelefone.getText().toString());

                long retornoDB;

                if(btnVariavel.getText().toString().equals("Salvar")){
                    retornoDB = pessoaDao.salvarPessoa(pessoa);
                    pessoaDao.close();
                    if(retornoDB == -1){
                        alert("Erro no Banco!");
                    }else{
                        alert("Cadastro realizado com sucesso!");
                    }
                }else{
                    retornoDB = pessoaDao.alterarPessoa(pessoa);
                    pessoaDao.close();
                    if(retornoDB == -1){
                        alert("Erro ao alterar!");
                    }else{
                        alert("Atualização realizada com sucesso!");
                    }
                }

                finish();
            }
        });
    }
    private void alert(String s){ Toast.makeText(this, s, Toast.LENGTH_SHORT).show(); }
    public void Voltar(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}