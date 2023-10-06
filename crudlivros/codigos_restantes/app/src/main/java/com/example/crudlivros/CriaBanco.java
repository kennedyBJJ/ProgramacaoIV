package com.example.crudlivros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    protected static final String TABELA = "Livros";
    protected static final String ID = "_id";
    protected static final String TITULO = "titulo";
    protected static final String AUTOR = "autor";
    protected static final String EDITORA = "editora";
    private static final int VERSAO = 1;

    //O construtor que passara para a super classe as informações do local e versão do banco
    public CriaBanco(Context context){ super(context, NOME_BANCO, null, VERSAO);}

    //cHAMADO QUANDO A APLICAÇÃO CRIA O BANCO DE DADOS PELA PRIMEIRA VEZ
    @Override
    public void onCreate(SQLiteDatabase db){

        String sql = "CREATE TABLE "+TABELA +"("
                + ID + " integer primary key autoincrement,"
                + TITULO + " text,"
                + AUTOR + " text,"
                + EDITORA + " text"
                + ");";

        db.execSQL(sql);
    }

    //Método responsável por atualizar o banco de dados com alguma informação estrutural que tenha sido alterada
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DEOP TABLE IF EXISTS '" + TABELA +"'");
        onCreate(db);
    }
}
