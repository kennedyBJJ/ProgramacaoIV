package com.example.trainingnotification.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.trainingnotification.model.NotificationModel;

public class DatabaseController {

    private static SQLiteDatabase db;
    private static Database model;

    public DatabaseController(Context context){ model = new Database(context);}

    public static int insertNotification(NotificationModel notification){

        ContentValues content = new ContentValues();
        long queryResult;

        db = model.getWritableDatabase();


        content.put(model.NOME, notification.getNome());
        content.put(model.HORARIO, notification.getHorario());
        content.put(model.INTERVALO, notification.getIntervaloDose());
        content.put(model.QUANTIDADE, notification.getQuantidade());

        queryResult = db.insert(model.TABELA, null, content);

        db.close();

        return (int) queryResult;
    }


    public static Cursor searchNotification(NotificationModel notification){
        String where =
                model.NOME +"=?s" + "AND " + model.HORARIO + "=?s";
        String[] whereArgs = {notification.getNome(),  notification.getHorario()};
        db = model.getWritableDatabase();
        Cursor retorno = db.query(model.TABELA, null, where, whereArgs, null, null, null);

        if(retorno != null) {retorno.moveToFirst();}

        return retorno;
    }

    public static Cursor getAllNotifications(){

        db = model.getWritableDatabase();
        Cursor retorno = db.query(model.TABELA, null, null, null, null, null, null);

        if(retorno != null) retorno.moveToFirst();

        return retorno;

    }

    public static boolean deleteNotification(NotificationModel notification){

        db = model.getWritableDatabase();

        String[]whereArgs = {String.valueOf(notification.getId())};
        int retorno = db.delete(model.TABELA, model.ID + "=?s", whereArgs);

        return retorno != 0;
    }
}
