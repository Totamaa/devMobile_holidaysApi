package fr.iut.holidays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.json.*;

import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;

import java.lang.Thread;
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Scanner;

import JavaClass.Holidays;


public class HomeActivity extends AppCompatActivity {

    TextView place_textView;
    String data;
    ArrayList<Holidays> holidaysArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponent();

        Intent intent = getIntent();
        String place = intent.getStringExtra("place");
        place_textView.setText(place.substring(0, 1).toUpperCase() + place.substring(1).toLowerCase());
        // Log.i("place", place);
        getJson();
        try
        {
            JSONArray tabJson = new JSONArray(data);
            if (tabJson != null)
            {
                for (int i = 0; i < tabJson.length(); i++)
                {
                    JSONObject vacation = tabJson.getJSONObject(i).getJSONObject("fields");
                    Log.i("obj", vacation.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initComponent()
    {
        place_textView = findViewById(R.id.place_textView);
    }

    private void getJson()
    {
        Thread thread = new Thread(new Runnable()
        {

            @Override
            public void run() {
                data = "";
                try {
                    URL holidays_url = new URL("https://www.data.gouv.fr/fr/datasets/r/000ae493-9fa8-4088-9f53-76d375204036");
                    HttpURLConnection conn = (HttpURLConnection) holidays_url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.addRequestProperty("content-type", "application/json");
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    Scanner sc = new Scanner(inputStream);
                    while (sc.hasNextLine())
                    {
                        data += sc.nextLine();
                    }
                    sc.close();
                    System.out.println(conn.getResponseCode());
                    Log.i("test", data);
                    conn.disconnect();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        thread.start();

    }
}