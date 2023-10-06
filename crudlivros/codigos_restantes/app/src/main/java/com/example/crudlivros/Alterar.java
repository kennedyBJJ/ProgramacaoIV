package com.example.crudlivros; // alterar para o usuario correto

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alterar extends Activity {

    private AlertDialog alerta;
    private EditText livro;
    private EditText autor;
    private EditText editora;
    private Button alterar;
    private Button deletar;
    private Cursor cursor;
    private BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        livro = (EditText) findViewById(R.id.etTitulo);
        autor = (EditText) findViewById(R.id.etAutor);
        editora = (EditText) findViewById(R.id.etEditora);

        alterar = (Button) findViewById(R.id.btnAlterar);

        cursor = crud.carregaDadosById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo), livro.getText().toString(),autor.getText().toString(), editora.getText().toString());
                Toast.makeText(getApplicationContext(), "Registro alterado com sucesso!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Alterar.this,Consulta.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.btnDeletar);

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cria o gerador do AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Alterar.this);
                //define o titulo
                builder.setTitle("Excluindo registro...");
                //define a mensagem
                builder.setMessage("Deseja realmente excluir registro?");
                //define um botão como SIM
                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //excluindo o registro
                        crud.deletaRegistro(Integer.parseInt(codigo));
                        Toast.makeText(getApplicationContext(), "Registro excluido com sucesso!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Alterar.this,Consulta.class);
                        startActivity(intent);
                        finish();
                        //fim do processo
                    }
                });
                //define um botão como NÃO.
                builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(Alterar.this, "Registro não excluido.", Toast.LENGTH_SHORT).show();
                    }
                });
                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();
            }
        });

    }

    public void Voltar(View view){
        Intent i = new Intent(this, TelaInicial.class);
        startActivity(i);
    }

}