package com.example.crudlivros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TelaInicial extends AppCompatActivity {

    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }

    public void CadastrarLivro(View view){
        Intent i = new Intent(this, Cadastrar.class);
        startActivity(i);
    }
    public void verLivro(View view){
        Intent i = new Intent(this, Consulta.class);
        startActivity(i);
    }
    public void sair(View view){
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Saindo do APP");
        //define a mensagem
        builder.setMessage("Deseja realmente sair do App?");
        //define um botao de SIM
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(TelaInicial.this, "Saindo do APP...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        //define um botao de NAO
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(TelaInicial.this, "Continuando", Toast.LENGTH_SHORT).show();

            }
        });

        //Cria o alert dialog
        alert = builder.create();
        //exibe
        alert.show();
    }
}