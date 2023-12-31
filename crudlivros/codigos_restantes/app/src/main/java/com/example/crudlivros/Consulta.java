package com.example.crudlivros; // alterar para o usuario correto

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Consulta extends Activity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {CriaBanco.ID,CriaBanco.TITULO};
        int[] idViews = new int[] {R.id.idLivro,R.id.nomeLivro};

        //Adaptador - usando o Listview com um layout simples
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),R.layout.livros_layout,cursor,nomeCampos,idViews,0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        //Após concluído o processo, a lista deve ser amarrada com seu componente ListView e o adapter deve ser setado nessa lista.
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(Consulta.this, Alterar.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Voltar(View view){
        Intent i = new Intent(this, TelaInicial.class);
        startActivity(i);
    }

    public void CadastrarLivro(View view){
        Intent i = new Intent(this, Cadastrar.class);
        startActivity(i);
    }

}