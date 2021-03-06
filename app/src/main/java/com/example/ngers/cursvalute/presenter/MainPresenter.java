package com.example.ngers.cursvalute.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ngers.cursvalute.model.ValCurs;
import com.example.ngers.cursvalute.model.dataBase.DataBaseHandler;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ngers on 17.10.16.
 */

public class MainPresenter implements Presenter{
    String serverURL = "http://www.cbr.ru/scripts/XML_daily.asp";
    DataBaseHandler db;
    Context context;
    @Override
    public void create(Context context) {
        this.context = context;
        db = new DataBaseHandler(context);
        new DownloadDataTask().execute(serverURL);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    private class DownloadDataTask extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return false;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return false;
                }
                String forecastJsonStr = buffer.toString();
                Serializer serializer = new Persister();
                ValCurs valCurs = serializer.read(ValCurs.class, forecastJsonStr);
                db.deleteValCurs();
                db.addValcurs(valCurs);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(!aBoolean)
                Toast.makeText(context,"Error load data", Toast.LENGTH_SHORT).show();
        }
    }
}
