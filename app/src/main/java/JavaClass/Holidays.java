package JavaClass;

import java.util.Date;

public class Holidays {

    String description;
    String Population;
    Date dateDebut;
    Date dateFin;
    String academie;
    String zone;
    String anneeScolaire;

    public Holidays() {
    }

    public Holidays(String description, String population, Date dateDebut, Date dateFin, String academie, String zone, String anneeScolaire) {
        this.description = description;
        Population = population;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.academie = academie;
        this.zone = zone;
        this.anneeScolaire = anneeScolaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getAcademie() {
        return academie;
    }

    public void setAcademie(String academie) {
        this.academie = academie;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    @Override
    public String toString() {
        return "Holidays{" +
                "description='" + description + '\'' +
                ", Population='" + Population + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", academie='" + academie + '\'' +
                ", zone='" + zone + '\'' +
                ", anneeScolaire='" + anneeScolaire + '\'' +
                '}';
    }
}
