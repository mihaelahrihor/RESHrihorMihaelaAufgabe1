package model;

import java.time.LocalDate;

public class Ereignis {

    private int id;
    private String held;
    private String antagonist;
    private Kategorie konfrontationstyp;
    private String ort;
    private LocalDate datum;
    private double globalerEinfluss;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeld() {
        return held;
    }

    public void setHeld(String held) {
        this.held = held;
    }

    public String getAntagonist() {
        return antagonist;
    }

    public void setAntagonist(String antagonist) {
        this.antagonist = antagonist;
    }

    public String getKonfrontationstyp() {
        return konfrontationstyp.toString();
    }

    public void setKonfrontationstyp(Kategorie konfrontationstyp) {
        this.konfrontationstyp = konfrontationstyp;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getGlobalerEinfluss() {
        return globalerEinfluss;
    }

    public void setGlobalerEinfluss(double globalerEinfluss) {
        this.globalerEinfluss = globalerEinfluss;
    }

    @Override
    public String toString() {
        return "Ereignis{" +
                "id=" + id +
                ", held='" + held + '\'' +
                ", antagonist='" + antagonist + '\'' +
                ", konfrontationstyp=" + konfrontationstyp +
                ", ort='" + ort + '\'' +
                ", datum=" + datum +
                ", globalerEinfluss=" + globalerEinfluss +
                '}';
    }
}
