package com.example.crudlivros; // alterar para o usuario correto

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastrar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        Button cadastrar = (Button) findViewById(R.id.btnCadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());
                EditText titulo = (EditText) findViewById(R.id.etTitulo);
                EditText autor = (EditText) findViewById(R.id.etAutor);
                EditText editora = (EditText) findViewById(R.id.etEditora);
                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraString = editora.getText().toString();
                String resultado;

                resultado = crud.inreseDado(tituloString,autorString,editoraString);
                Toast.makeText(getApplicationContext(), resultado,Toast.LENGTH_LONG).show();

                Intent i = new Intent(Cadastrar.this,Consulta.class);
                startActivity(i);
            }
        });

    }

    public void Voltar(View view){
        Intent i = new Intent(this, TelaInicial.class);
        startActivity(i);
    }

}