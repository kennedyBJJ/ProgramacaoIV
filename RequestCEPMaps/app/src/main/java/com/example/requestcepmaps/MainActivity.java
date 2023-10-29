package com.example.requestcepmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public String BID_ENDPOINT = "https://cep.awesomeapi.com.br/json/";
    private OkHttpClient okHttpClient = new OkHttpClient();//é preciso injetar a dependencia dentro do build.gradle
    private ProgressDialog progressDialog;
    private EditText edtCep;
    private TextView tvEndereco;
    private TextView tvBairro;
    private TextView tvCidade;
    private TextView tvEstado;
    private TextView tvLat;
    private TextView tvLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCep = findViewById(R.id.edtCEP);
        tvEndereco = findViewById(R.id.tvEndereco);
        tvBairro = findViewById(R.id.tvBairro);
        tvCidade = findViewById(R.id.tvCidade);
        tvEstado = findViewById(R.id.tvEstado);
        tvLat = findViewById(R.id.tvLat);
        tvLon = findViewById(R.id.tvLon);

        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Carregando");
        progressDialog.setMessage("Aguarde...");

    }

    private void load()
    {
        Request request = new Request.Builder().url(BID_ENDPOINT).build();

        progressDialog.show();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Toast.makeText(MainActivity.this, "Erro durante o carregamento: " + e.getMessage(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                final String BODY = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();

                        parseBpiResponse(BODY);
                    }
                });

            }
        });
    }

    public void verificar(View view)
    {
        //Pesquisando CEP
        if(edtCep.getText().toString() != null && edtCep.getText().toString().length() == 8 )
        {
            String novoCep = BID_ENDPOINT + edtCep.getText().toString();

            Request request = new Request.Builder().url(novoCep).build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    Toast.makeText(MainActivity.this, "Erro durante o carregamento: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                    final String BODY = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();

                            parseBpiResponse(BODY);
                        }
                    });

                }
            });
        }
    }

    private void parseBpiResponse(final String BODY)
    {
        try
        {

            JSONObject jsonObject = new JSONObject(BODY);

            tvEndereco.setText("Endereço: " + jsonObject.getString("address"));

            tvBairro.setText("Bairro: " + jsonObject.getString("district"));
            tvCidade.setText("Cidade: " + jsonObject.getString("city"));
            tvEstado.setText("Estado: " + jsonObject.getString("state"));
            tvLat.setText("Latitude: " + jsonObject.getString("lat"));
            tvLon.setText("Longitude: " + jsonObject.getString("lng"));

            //Maps - Apontado pela lat e lng
            WebView wv = findViewById(R.id.wv);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl("https://www.google.com/maps/search/?api=&query=" + jsonObject.getString("lat") + ", " + jsonObject.getString("lng"));


        }catch (Exception e)
        {

        }
    }
}
