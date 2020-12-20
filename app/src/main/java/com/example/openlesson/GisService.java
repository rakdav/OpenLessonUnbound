package com.example.openlesson;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Binder;
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
    private static String LOG_TAG = "WeatherService";
    public static final String CHANNEL = "GIS_SERVICE";
    public static final String INFO = "INFO";
    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder {
        GisService getService(){
            return GisService.this;
        }
    }

    public GisService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG,"onBind");
        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(LOG_TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(LOG_TAG, "onUnbind");
        return true;
    }

    public void getWheather(int tid)
    {
        GisAsyncTask t = new GisAsyncTask();
        t.execute(tid);
    }

    private class GisAsyncTask extends AsyncTask<Integer,Void,String>
    {
        @Override
        protected String doInBackground(Integer... voids) {
            String result="";
            try {
                //загружаем данные URL

                URL url = new URL("http://icomms.ru/inf/meteo.php?tid="+voids[0]);
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