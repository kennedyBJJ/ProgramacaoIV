package com.example.geolocalizacaov1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buscarInformacoes(View view){

        //verificando se o app tem permissão de acesso aos sistemas de GPS do Android
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.INTERNET},1);

            return;
        }

        LocationManager nLocManager = (LocationManager) getSystemService(MainActivity.this.LOCATION_SERVICE);
        LocationListener mLocListener = new MinhaLocalizacaoListener();

        nLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);

        if(nLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            String texto = "Latitude: " + MinhaLocalizacaoListener.latitude + "\n"
                            + "Longitude: " + MinhaLocalizacaoListener.longitude + '\n';

            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this, "GPS desabilitado", Toast.LENGTH_SHORT).show();
        }

        mostrarGoogleMaps(MinhaLocalizacaoListener.latitude, MinhaLocalizacaoListener.longitude);
    }

    public void mostrarGoogleMaps(double latitude, double longitude){
        WebView wv = findViewById(R.id.webv);
        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadUrl("https://www.google.com/maps/search/?api=&query=" + latitude + ", " + longitude );


    }
}