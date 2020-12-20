package com.example.openlesson;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GisService extends Service {
    public static final String CHANNEL = "GIS_SERVICE";
    public static final String INFO = "INFO";

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Служба создана", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Служба запущена", Toast.LENGTH_SHORT).show();
        // создаем объект нашего AsyncTask (необходимо для работы с сетью)
        GisAsyncTask t = new GisAsyncTask();
        t.execute(); // запускаем AsyncTask
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Служба остановлена", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class GisAsyncTask extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids) {
            String result="";
            try {
                //загружаем данные URL

                URL url = new URL("http://icomms.ru/inf/meteo.php?tid=24");
                // ”оборачиваем” для удобства четния
                URLConnection urlc = url.openConnection();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(urlc.getInputStream(), "UTF8"));

                StringBuilder builder = new StringBuilder();
                int byteRead;
                while ((byteRead = buffer.read()) != -1) {
                    builder.append((char) byteRead);
                }
                buffer.close();
                result = builder.toString();
                // читаем и добавляем имя json массива

            }
            catch (Exception e)
            {
                Log.d("Message",e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Intent i = new Intent(CHANNEL); // интент для отправки ответа
            i.putExtra(INFO, s); // добавляем в интент данные
            sendBroadcast(i); //
        }
    }
}