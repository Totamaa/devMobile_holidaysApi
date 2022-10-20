package fr.iut.holidays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
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
import java.text.Collator;
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
    TextView textToussaint;
    TextView textNoel;
    TextView textHiver;
    TextView textPaques;
    TextView textEte;
    ProgressBar progressBar;


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
        textToussaint = findViewById(R.id.textToussaint);
        textNoel = findViewById(R.id.textNoel);
        textHiver = findViewById(R.id.textHiver);
        textPaques = findViewById(R.id.textPaques);
        textEte = findViewById(R.id.textEte);
        progressBar = findViewById(R.id.progressBar);
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
                            if (holidays.getScolarYear().equals("2022-2023")
                                    && holidays.getPopulation().equals("-")
                                    && place.equals(holidays.getLocation().toLowerCase())
                            )
                            {
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
                int upperInterv = 0;

                final Calendar today = Calendar.getInstance();
                for (Holidays h : holidaysArrayList) {
                    temp = daysBetween(today, h.getStartDate());
                    System.out.println(h.toString());
                    if (temp > 0 && temp < max){
                        j = i;
                        max = temp;
                        delay = daysBetween(today, h.getStartDate())+1;
                        libelle = h.getTypeHolidays();
                        upperInterv = h.getStartDate().get(Calendar.DAY_OF_YEAR);
                    }
                    i++;
                }
                final long finaldelay = delay;
                final String finaltype = libelle;
                final int finalUpperInterv = upperInterv;

                runOnUiThread(new Runnable() {
                    int currentProgress;
                    @Override
                    public void run() {

                        currentProgress = Math.round(((70f - (float)finaldelay) / 70f) * 100f);
                        progressBar.setProgress(currentProgress);
                        progressBar.setMax(100);

                        timeBeforeNextHolidays.setText(finaltype + " dans " + Long.toString(finaldelay) + "jour(s)");

                        Collator collator  = Collator.getInstance();
                        collator.setStrength(Collator.NO_DECOMPOSITION);

                        int i = 0;
                        for(Holidays h : holidaysArrayList)
                        {
                            if(collator.compare(h.getTypeHolidays().toLowerCase(), "Vacances de la Toussaint".toLowerCase()) == 0) {
                                int month = holidaysArrayList.get(i).getStartDate().get(Calendar.MONTH)+1;
                                textToussaint.setText(
                                        holidaysArrayList.get(i).getTypeHolidays() + " : " +
                                        holidaysArrayList.get(i).getStartDate().get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + holidaysArrayList.get(i).getStartDate().get(Calendar.YEAR));
                            }
                            else if (collator.compare(h.getTypeHolidays().toLowerCase(), "Vacances de Noël".toLowerCase()) == 0) {
                                int month = holidaysArrayList.get(i).getStartDate().get(Calendar.MONTH) + 1;
                                textNoel.setText(
                                        holidaysArrayList.get(i).getTypeHolidays() + " : " +
                                                holidaysArrayList.get(i).getStartDate().get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + holidaysArrayList.get(i).getStartDate().get(Calendar.YEAR));
                            }
                            else if(collator.compare(h.getTypeHolidays().toLowerCase(), "Vacances d'Hiver".toLowerCase()) == 0)
                            {
                                int month = holidaysArrayList.get(i).getStartDate().get(Calendar.MONTH)+1;
                                textHiver.setText(
                                        holidaysArrayList.get(i).getTypeHolidays() + " : " +
                                                holidaysArrayList.get(i).getStartDate().get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + holidaysArrayList.get(i).getStartDate().get(Calendar.YEAR));
                            }
                            else if(collator.compare(h.getTypeHolidays().toLowerCase(), "Vacances de Printemps".toLowerCase()) == 0)
                            {
                                int month = holidaysArrayList.get(i).getStartDate().get(Calendar.MONTH)+1;
                                textPaques.setText(
                                        holidaysArrayList.get(i).getTypeHolidays() + " : " +
                                                holidaysArrayList.get(i).getStartDate().get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + holidaysArrayList.get(i).getStartDate().get(Calendar.YEAR));
                            }
                            else if(collator.compare(h.getTypeHolidays().toLowerCase(), "Début des Vacances d'Été".toLowerCase()) == 0)
                            {
                                int month = holidaysArrayList.get(i).getStartDate().get(Calendar.MONTH)+1;
                                textEte.setText(
                                        holidaysArrayList.get(i).getTypeHolidays() + " : " +
                                                holidaysArrayList.get(i).getStartDate().get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + holidaysArrayList.get(i).getStartDate().get(Calendar.YEAR));
                            }
                            i++;
                        }
                    }
                });
            }
        });

        thread.start();
    }

    public static long daysBetween(Calendar startDate, Calendar endDate)
    {
        long start = startDate.getTimeInMillis();
        long end = endDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(end - start);
    }
}