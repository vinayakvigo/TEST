package com.example.test.main.Task13;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class InternetTimeGetter extends AsyncTask<Void, Void, String> {
    private TextView textView;

    public InternetTimeGetter(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = "";
        try {
            URL url = new URL("https://worldtimeapi.org/api/timezone/Asia/Kolkata");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TimeError", "run: "+e);
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String dateTime = jsonObject.getString("datetime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            Date date = sdf.parse(dateTime);
            SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String displayDateTime = displayFormat.format(date);
            Log.i("TimeError2", "run: "+displayDateTime);
            textView.setText(displayDateTime);
        } catch (JSONException | java.text.ParseException e) {
            e.printStackTrace();
            Log.i("TimeError", "run: "+e);
        }
    }
}
