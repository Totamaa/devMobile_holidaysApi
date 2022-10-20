package JavaClass;

import android.util.Log;

import java.io.Console;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class Holidays {
    String location;
    String scolarYear;
    String typeHolidays;
    Calendar startDate;
    Calendar endDate;
    String zone;
    String population;

    public Holidays() {
    }

    public Holidays(String location, String scolarYear, String typeHolidays, String dateStartString, String sateEndString, String zone, String population) {
        this.location = location;
        this.scolarYear = scolarYear;
        this.typeHolidays = typeHolidays;

        // date
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Calendar dateStart = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        try
        {
            dateStart.setTime(Objects.requireNonNull(formatDate.parse(dateStartString)));
            dateEnd.setTime(Objects.requireNonNull(formatDate.parse(sateEndString)));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        this.startDate = dateStart;
        this.endDate = dateEnd;


        this.zone = zone.substring(5);
        this.population = population;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getScolarYear() {
        return scolarYear;
    }

    public void setScolarYear(String scolarYear) {
        this.scolarYear = scolarYear;
    }

    public String getTypeHolidays() {
        return typeHolidays;
    }

    public void setTypeHolidays(String typeHolidays) {
        this.typeHolidays = typeHolidays;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Holidays{" +
                "location='" + location + '\'' +
                ", scolarYear='" + scolarYear + '\'' +
                ", typeHolidays='" + typeHolidays + '\'' +
                ", startDate='" + startDate.toString() + '\'' +
                ", endDate='" + endDate.toString() + '\'' +
                ", zone='" + zone + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
