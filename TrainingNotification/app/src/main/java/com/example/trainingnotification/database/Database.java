package com.example.trainingnotification.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    protected static final String TABELA = "noticacao";
    protected final String ID = "id";
    protected final String NOME = "nome";
    protected final String HORARIO = "horario";
    protected final String INTERVALO = "intervarloDose";
    protected final String QUANTIDADE = "quantidade";
    private static final int VERSAO = 1;
    public Database(@Nullable Context context) {
        super(context, TABELA, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS notificacao( " +
                ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME+ " VARCHAR(70) UNIQUE," +
                HORARIO+ " DATETIME UNIQUE DEFAULT CURRENT_TIMESTAMP," +
                INTERVALO+ " DATETIME DEFAULT CURRENT_TIME," +
                QUANTIDADE+ " INTEGER )";

;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notificacao;");
        onCreate(db);
    }
}
