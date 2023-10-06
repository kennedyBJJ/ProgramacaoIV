package com.example.jokempo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //declarando os objetos
        Button buttonTesoura = findViewById(R.id.buttonTesoura);
        Button buttonPapel = findViewById(R.id.buttonPapel);
        Button buttonPedra = findViewById(R.id.buttonPedra);

        buttonTesoura.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                realizarTurno("tesoura");
            }
        });

        buttonPapel.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View v){
                        realizarTurno("papel");
                    }
                });

        buttonPedra.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View v){
                        realizarTurno("pedra");
                    }
                });


    }

    public void realizarTurno(String player){

        String[] escolhas = {"papel", "tesoura", "pedra"};

        //gerando escolha da máquina
        int index = new Random().nextInt(3);
        String maquina = escolhas[index];
        String vencedor = "";

        if(player.equals("tesoura")){
            vencedor = compararTesoura(maquina);
        }else if(player.equals("pedra")){
            vencedor = compararPedra(maquina);
        }else{
            vencedor = compararPapel(maquina);
        }

        exibirResultado(player, maquina, vencedor);



    }

    public void exibirResultado(String player, String maquina, String vencedor){
        TextView textViewMaquina = findViewById(R.id.textViewMaquina);
        TextView textViewPlayer = findViewById(R.id.textViewPlayer);
        TextView textViewResult = findViewById(R.id.textViewResult);

        textViewPlayer.setText(player);
        textViewMaquina.setText(maquina);
        textViewResult.setText(vencedor);
    }
    public String compararTesoura(@NonNull String maquina){
        if(maquina.equals("tesoura")){
            return "empate";
        } else if (maquina.equals("pedra")) {
            return "você perdeu";
        }
        return "você ganhou";
    }

    public String compararPapel(String maquina){
        if(maquina.equals("papel")){
            return "empate";
        } else if (maquina.equals("tesoura")) {
            return "você perdeu";
        }
        return "você ganhou";
    }

    public String compararPedra(String maquina){
        if(maquina.equals("pedra")){
            return "empate";
        } else if (maquina.equals("papel")) {
            return "você perdeu";
        }
        return "você ganhou";
    }
}