package fr.iut.holidays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.json.*;

import java.io.Console;
import java.io.FileWriter;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.ProtocolException;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;

import java.lang.Thread;
import java.lang.Runnable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import JavaClass.Holidays;

public class HomeActivity extends AppCompatActivity {

    TextView place_textView;
    TextView timeBeforeNextHolidays;
    String data;
    ArrayList<Holidays> holidaysArrayList = new ArrayList<>();
    ArrayList<String> holidaysType = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponent();

        Intent intent = getIntent();
        String place = intent.getStringExtra("place");
        place_textView.setText(place.substring(0, 1).toUpperCase() + place.substring(1).toLowerCase());
        // Log.i("place", place);
        getJson(place);

        holidaysType.add("Vacances d'Été");
        holidaysType.add("Vacances de Printemps");
        holidaysType.add("Pont de l'Ascension");
        holidaysType.add("Vacances de Pâques");
        holidaysType.add("Vacances d'Hiver");
        holidaysType.add("Vacances de Noël");
        holidaysType.add("Vacances de la Toussaint");

    }

    private void initComponent() {
        place_textView = findViewById(R.id.place_textView);
        timeBeforeNextHolidays = findViewById(R.id.timeBeforeNextHolidays);
    }

    private void getJson(String place) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                data = "";
                try {
                    URL holidays_url = new URL(
                            "https://data.education.gouv.fr/explore/dataset/fr-en-calendrier-scolaire/download?format=json&timezone=Europe/Berlin&use_labels_for_header=false");
                    HttpURLConnection conn = (HttpURLConnection) holidays_url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.addRequestProperty("content-type", "application/json");
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    Scanner sc = new Scanner(inputStream);
                    while (sc.hasNextLine()) {
                        data += sc.nextLine();
                    }
                    sc.close();
                    System.out.println(conn.getResponseCode());
                    // System.out.println(data);
                    conn.disconnect();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray tabJson = new JSONArray(data);
                    if (tabJson != null) {
                        for (int i = 0; i < tabJson.length(); i++) {
                            JSONObject vacation = tabJson.getJSONObject(i).getJSONObject("fields");
                            // System.out.println(vacation.getString("location"));
                            Holidays holidays = new Holidays(
                                    vacation.getString("location"),
                                    vacation.getString("annee_scolaire"),
                                    vacation.getString("description"),
                                    vacation.getString("start_date"),
                                    vacation.getString("end_date"),
                                    vacation.getString("zones"),
                                    vacation.getString("population"));
                            // System.out.println(holidays.getScolarYear());
                            if (holidays.getScolarYear().equals("2022-2023") && holidays.getPopulation().equals("-")
                                    && place.equals(holidays.getLocation().toLowerCase())) {
                                holidaysArrayList.add(holidays);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                long max = 365;
                long temp;
                long delay = 0;
                int i = 0;
                int j = 0;
                String libelle = "";


                Calendar today = Calendar.getInstance();
                for (Holidays h : holidaysArrayList) {
                    Log.i("holidays", h.toString());
                    System.out.println(daysBetween(today, h.getStartDate()));

                    temp = daysBetween(today, h.getStartDate());

                    if (temp > 0 && temp < max) {
                        j = i;
                        max = temp;
                        delay = daysBetween(today, h.getStartDate());
                        libelle = h.getTypeHolidays();
                    }
                    i++;
                }
                final long finaldelay = delay;
                final String finaltype = libelle;

                Log.i("TYPEVACANCES", holidaysArrayList.get(j).getTypeHolidays());
                Log.i("tostring", Long.toString(delay));

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        timeBeforeNextHolidays.setText(finaltype + "dans " + Long.toString(finaldelay) + "jour(s)");

                    }
                });
            }
        });

        thread.start();
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        long start = startDate.getTimeInMillis();
        long end = endDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(end - start);
    }
}