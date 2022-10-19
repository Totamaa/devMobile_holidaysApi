package JavaClass;

import android.util.Log;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date dateStart = formatDate.parse(dateStartString.substring(0,10));
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(dateStart);


            Date dateEnd = formatDate.parse(sateEndString);
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(dateEnd);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        this.startDate = startDate;
        this.endDate = endDate;


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
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", zone='" + zone + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
