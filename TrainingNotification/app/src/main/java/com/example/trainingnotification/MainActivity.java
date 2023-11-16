package com.example.trainingnotification;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.trainingnotification.notificationScheduler.NotificationScheduller;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Calendar calendar;
    EditText edTime;
    EditText edMedicamento;
    AlarmManager alarmManager;
    Button btSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationChannel();

        if( ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SCHEDULE_EXACT_ALARM) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.POST_NOTIFICATIONS},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.SCHEDULE_EXACT_ALARM},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.INTERNET},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.USE_EXACT_ALARM},1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE},1);

        }

        edTime = findViewById(R.id.etTime);

        edTime.setOnClickListener((View v) ->{
                selectTime();
            }
        );


        ChipGroup weekDays = findViewById(R.id.weekDayGroup);
        weekDays.setSelectionRequired(true);


        setInitialTime();


        btSalvar = findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener((View v) -> {

                notificar();
            }
        );
    }

    public void notificar(){
        edTime = findViewById(R.id.etTime);
        edMedicamento= findViewById(R.id.edMedicamento);

        String horario = edTime.getText().toString();
        String medicamento = edMedicamento.getText().toString();

        int hora = Integer.parseInt(horario.substring(0,2));
        int minuto = Integer.parseInt(horario.substring(3));


        alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minuto);
        calendar.set(Calendar.SECOND, 0);

        if (Calendar.getInstance().after(calendar)) {calendar.add(Calendar.DAY_OF_MONTH, 1);}

        Intent intent = new Intent(this, NotificationScheduller.class);
        intent.putExtra("medicamento", medicamento);
        PendingIntent pendingIntent = null;
        try {
            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
        }catch (Exception e){System.out.println();}

        assert pendingIntent != null;
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

        
        Toast.makeText(this, "hora:" + hora + "\n minuto:" + minuto, Toast.LENGTH_LONG).show();
    }

    private void selectTime(){
        TimePickerDialog.OnTimeSetListener listener = (TimePicker view, int hourOfDay, int minute) -> {

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            String texto = format.format(calendar.getTime());
            edTime.setText(texto);
        };

        TimePickerDialog timePicker = new TimePickerDialog(MainActivity.this,
                listener,
                calendar.HOUR_OF_DAY,
                calendar.MINUTE,
                true);

        timePicker.show();
    }
    private void setInitialTime(){
        edTime = findViewById(R.id.etTime);

        calendar = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date time = new Date(calendar.getTimeInMillis());

        String texto = format.format(time);

        edTime.setText(texto);
    }
    private void NotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION )
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION).build() ;

            CharSequence name = "medicamento";
            String description = "Não se esqueça de tomar o seu remédio";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("lembrete", name, importance);
            channel.setDescription(description);
            channel.setSound(alarmSound, audioAttributes);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

}