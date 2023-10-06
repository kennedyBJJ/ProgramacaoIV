package com.example.exemplosplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spn;
    private List<String> sd = new ArrayList<>();
    private String saude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sd.add("Selecione a informação desejada");
        sd.add("Dados da Covid no Brasil");
        sd.add("Site Ministério da Saúde");
        sd.add("Ouvidoria do Ministério da Saúde");
        sd.add("Ministério da Saúde - Responde");

        //Identifica o Spinner no Layout
        spn = (Spinner) findViewById(R.id.spinInformacao);

        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList sd
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sd);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(spinnerArrayAdapter);

        //método do Spinner para capturar cada item selecionado
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {

                //pega o nome pela posição
                saude = parent.getItemAtPosition(posicao).toString();
                //condicional para comparativo add spn
                if(saude.equals(("Dados da Covid no Brasil"))){
                    Uri url = Uri.parse("https://covid.saude.gov.br");
                    Intent it = new Intent(Intent.ACTION_VIEW, url);
                    startActivity(it);
                    Toast.makeText(MainActivity.this, "Dados da Covid no Brasil", Toast.LENGTH_SHORT).show();
                }else if(saude.equals(("Site Ministério da Saúde"))){
                    Uri url = Uri.parse("https://gov.br/saude/pt-br");
                    Intent it = new Intent(Intent.ACTION_VIEW, url);
                    startActivity(it);
                    Toast.makeText(MainActivity.this, "Site Ministério da Saúde", Toast.LENGTH_SHORT).show();
                }else if(saude.equals(("Ouvidoria do Ministério da Saúde"))){
                    Uri url = Uri.parse("tel:136");
                    Intent it = new Intent(Intent.ACTION_DIAL, url);
                    startActivity(it);
                    Toast.makeText(MainActivity.this, "Dados da Covid no Brasil", Toast.LENGTH_SHORT).show();
                }else if(saude.equals(("Ministério da Saúde - Responde"))){
                    String url = "https://api.whatsapp.com/send?phone=" + "556199380031";
                    try {
                        PackageManager pm = v.getContext().getPackageManager();
                        pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        v.getContext().startActivity(i);
                    } catch (PackageManager.NameNotFoundException e){
                        v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }
                    Toast.makeText(MainActivity.this, "Ministério da Saúde - Responde", Toast.LENGTH_SHORT).show();
                }

            };


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
