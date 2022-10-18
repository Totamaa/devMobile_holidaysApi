package JavaClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("annee_scolaire")
    @Expose
    private String anneeScolaire;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("zones")
    @Expose
    private String zones;
    @SerializedName("population")
    @Expose
    private String population;

    /**
     * No args constructor for use in serialization
     *
     */
    public Fields() {
    }

    /**
     *
     * @param endDate
     * @param anneeScolaire
     * @param description
     * @param location
     * @param zones
     * @param startDate
     * @param population
     */
    public Fields(String endDate, String startDate, String location, String anneeScolaire, String description, String zones, String population) {
        super();
        this.endDate = endDate;
        this.startDate = startDate;
        this.location = location;
        this.anneeScolaire = anneeScolaire;
        this.description = description;
        this.zones = zones;
        this.population = population;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZones() {
        return zones;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Fields.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("endDate");
        sb.append('=');
        sb.append(((this.endDate == null)?"<null>":this.endDate));
        sb.append(',');
        sb.append("startDate");
        sb.append('=');
        sb.append(((this.startDate == null)?"<null>":this.startDate));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null)?"<null>":this.location));
        sb.append(',');
        sb.append("anneeScolaire");
        sb.append('=');
        sb.append(((this.anneeScolaire == null)?"<null>":this.anneeScolaire));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("zones");
        sb.append('=');
        sb.append(((this.zones == null)?"<null>":this.zones));
        sb.append(',');
        sb.append("population");
        sb.append('=');
        sb.append(((this.population == null)?"<null>":this.population));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}