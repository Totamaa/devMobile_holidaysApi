package fr.iut.holidays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.io.IOException;

import com.google.gson.*;

public class HomeActivity extends AppCompatActivity {

    TextView place_textView;

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
    }

    private void initComponent()
    {
        place_textView = findViewById(R.id.place_textView);
    }

    private void getJson()
    {
        try
        {
            URL holidays_url = new URL("https://www.data.gouv.fr/fr/datasets/r/000ae493-9fa8-4088-9f53-76d375204036");
            HttpURLConnection conn = (HttpURLConnection) holidays_url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("content-type", "application/json");
            conn.connect();
            System.out.println(conn.getResponseCode());
            conn.disconnect();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}