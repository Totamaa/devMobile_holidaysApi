package fr.iut.holidays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText place;
    Button search;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();



    }

    private void initComponent()
    {
        place = findViewById(R.id.place_searchBar);
        search = findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String placeString = place.getText().toString().toLowerCase();
                if (!placeString.equals(""))
                {
                    getApi();
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Vous devez rentrer une ville", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void getApi()
    {
        Thread thread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                data = "";
                String placeString = place.getText().toString().toLowerCase();
                try {
                    URL url = new URL("https://data.education.gouv.fr/api/records/1.0/search/?dataset=fr-en-annuaire-education&q=nom_commune=" + placeString + "&rows=1");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.addRequestProperty("content-type", "application/json");
                    conn.connect();
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = conn.getInputStream();
                        Scanner sc = new Scanner(inputStream);
                        while (sc.hasNext())
                        {
                            data += sc.next();
                        }
                        sc.close();
                        inputStream.close();
                    }
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String academie = "";
                try
                {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray recordsJson = new JSONArray(jsonObject.getJSONArray("records").toString());
                    JSONObject objectTabJson = new JSONObject(recordsJson.getJSONObject(0).toString());
                    JSONObject fieldsJson = new JSONObject(objectTabJson.getJSONObject("fields").toString());
                    System.out.println(fieldsJson.toString());
                    academie = fieldsJson.getString("libelle_academie");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                if (!academie.equals(""))
                {
                    intent.putExtra("place", academie.toLowerCase());
                    startActivity(intent);
                }
            }

        });
        thread.start();
    }
}