package com.example.crudlivros; // alterar para o usuario correto

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String inreseDado(String titulo, String autor, String editora) {

        ContentValues valores;
        long resultado;

        //db deve receber o resultado do método getWritableDatabase, que diz ao
        // Android que o banco será utilizado para leitura e escrita de dados.
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.AUTOR,autor);
        valores.put(CriaBanco.EDITORA,editora);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if(resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";

    }

    public Cursor carregaDados(){

        Cursor cursor;
        String[] campos = {banco.ID,banco.TITULO};

        //método getReadleDatabase irá fazer com que os dados sejam acessados como somente para leitura.
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos,null,null,null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.AUTOR,banco.EDITORA};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //Para os dados serem alterados precisamos implementar o método alteraRegisto aqui no controller do banco de dados
    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.AUTOR, autor);
        valores.put(CriaBanco.EDITORA, editora);

        db.update(CriaBanco.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA,where,null);
        db.close();
    }
}