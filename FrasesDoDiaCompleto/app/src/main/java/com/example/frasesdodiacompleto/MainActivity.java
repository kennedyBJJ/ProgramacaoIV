package com.example.frasesdodiacompleto;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import androidx.core.app.ActivityCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String fraseAtual;

    String[] frases = {"Não ganhe o mundo e perca sua alma; sabedoria é melhor que prata e ouro.",
            "Não viva para que a sua presença seja notada, mas para que a sua falta seja sentida.",
            "É preciso que as diferenças não diminuam a amizade e que a amizade não diminua as diferenças.",
            "Nunca deixe ninguém te dizer que não pode fazer alguma coisa. Se você tem um sonho tem que correr atrás dele. As pessoas não conseguem vencer e dizem que você também não vai vencer. Se você quer uma coisa corre atrás.",
            "Para ter sabedoria, é preciso primeiro pagar o seu preço. Use tudo o que você tem para adquirir o entendimento.",
            "Nós somos apenas simples pessoas movidas pela vingança em nome da justiça. Mas se a vingança é chamada de justiça, então dessa justiça irá nascer ainda mais vingança... E então se torna uma corrente de ódio. Viver com isso, ciente do passado, predizando o futuro, isso que significa conhecer a história. Não podemos evitar, mas sim compreender, que as pessoas nunca entenderão uma as outras.",
            "Acima de tudo, guarde o seu coração, pois dele depende toda a sua vida.",
            "A resposta calma desvia a fúria, mas a palavra ríspida desperta a ira.",
            "A bênção do Senhor é que enriquece; e não traz consigo dores.",
            "Não repreenda o zombador, caso contrário ele o odiará; repreenda o sábio, e ele o amará.",
            "Clame por inteligência e peça entendimento. Pois o Senhor concede sabedoria; de sua boca vêm conhecimento e entendimento.",
            "Eu faço da dificuldade a minha motivação. A volta por cima vem na continuação.",
            "Pra quem tem pensamento forte, o impossível é só questão de opinião.",
            "Quem ousou conquistar e saiu pra lutar, chega mais longe!",
            "A arte maior é o jeito de cada um... vivo pra ser feliz não vivo pra ser comum.",
            "A vida me ensinou a nunca desistir. Nem ganhar, nem perder mas procurar evoluir.",
            "O tempo às vezes é alheio à nossa vontade, mas só o que é bom dura tempo o bastante pra se tornar inesquecível.",
            "Hoje estou feliz porque sonhei com você, e amanhã posso chorar por não poder te ver ."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationChannel();
        // removendo o título
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //apresentar uma frase
        TextView textViewResult = findViewById(R.id.tvResult);
        int n = new Random().nextInt(frases.length);
        textViewResult.setText(frases[n]);
        fraseAtual = textViewResult.getText().toString();

        //programa uma notificação diária

        //checa se o aplicativo tem as permissões necessárias, se não tiver ele pede
        if( ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SCHEDULE_EXACT_ALARM) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.POST_NOTIFICATIONS},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.SCHEDULE_EXACT_ALARM},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.INTERNET},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.SCHEDULE_EXACT_ALARM},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.USE_EXACT_ALARM},1);

        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 20);
        calendar.set(Calendar.SECOND, 40);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(MainActivity.this, MemoBroadcast.class);

        // ao instanciar o pendingIntent ele deve estar dentro de um bloco try catch pois ele pode lançar uma exceção de segurança. Essa exceção o android obriga tratar ela
        PendingIntent pendingIntent = null;
        try {

            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_MUTABLE);
            System.out.println();
        }catch (Exception e){
            textViewResult.setText(e.toString());
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                pendingIntent);



    }


    private void NotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Frases";
            String description = "FRASES DO DIA";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    //método que chama novas frases
    public void mudarFrase(View view){

        TextView textViewResult = findViewById(R.id.tvResult);
        int n = new Random().nextInt(frases.length);
        textViewResult.setText(frases[n]);
        fraseAtual = textViewResult.getText().toString();
        //System.out.println(fraseAtual);

    }

    //método que compartilha as frases
    public void compartilhar(View view){

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, fraseAtual);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);


    }

    //se clicar em voltar na tela inicial será aberta uma caixa de diálogo
    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);

        // set title
        alertDialogBuilder.setTitle("Sair");

        // set dialog message
        alertDialogBuilder
                .setMessage("Você realmente quer sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}