package com.example.exemplosplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        //Timer do splash screen
        final int SPLASH_TIME_OUT = 4000;
        new Handler().postDelayed(new Runnable(){
            /*
             * Exibindo splash com um timer
             * */
            @Override
            public void run(){
                /*
                * Esse método será executado sempre que o timer acabar
                * E inicia a activity principal
                * */
                Intent i = new Intent(TelaInicial.this, MainActivity.class);
                startActivity(i);

                //fecha esta activity
                finish();//Sem encerrar a tela continua rodando no background
            }
        }, SPLASH_TIME_OUT);
    }
}