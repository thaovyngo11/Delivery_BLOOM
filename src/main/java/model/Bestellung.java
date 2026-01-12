package model;

import java.time.LocalDateTime;
import java.util.List;

public class Bestellung {

     /* Die Klasse "Bestellung" beschreibt einen Bestellung im System.
       Ein Bestellung besteht aus: Absender Name, Absender Telefonnummer, Empfänger Name, Empfänger Telefonnummer, Empfänger Andresse, Geschenk Katergorie, Datum und Uhrzeit. */

    // ATTRIBUTE

    private String empfaengerName;
    private String empfaengerTelefonnummer;
    private String empfaengerAdresse;
    private List<Geschenk> katergorie;
    private LocalDateTime datum;

    // KONSTRUKTOR

    public Bestellung(String empfaengerName,
                      String empfaengerTelefonnummer,
                      String empfaengerAdresse,
                      List<Geschenk> angebot, LocalDateTime datum) {

        this.empfaengerName = empfaengerName;
        this.empfaengerTelefonnummer = empfaengerTelefonnummer;
        this.empfaengerAdresse = empfaengerAdresse;
        this.katergorie = angebot;
        this.datum = datum;

    }

    // GETTER-METHODEN

    public String getEmpfaengerName() {

        return empfaengerName;
    }

    public String getEmpfaengerTelefonnummer() {

        return empfaengerTelefonnummer;
    }

    public String getEmpfaengerAdresse() {

        return empfaengerAdresse;
    }

    public List<Geschenk> getKatergorie() {

        return katergorie;
    }

    public LocalDateTime getDatum() {

        return datum;
    }

    // SETTER-METHODEN

    public void setEmpfaengerName(String empfaengerName) {
        this.empfaengerName = empfaengerName;
    }

    public void setEmpfaengerTelefonnummer(String empfaengerTelefonnummer) {
        this.empfaengerTelefonnummer = empfaengerTelefonnummer;
    }

    public void setEmpfaengerAdresse(String empfaengerAdresse) {
        this.empfaengerAdresse = empfaengerAdresse;
    }

    public void setKatergorie(List<Geschenk> katergorie) {
        this.katergorie = katergorie;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    // METHODEN ZUR VERARBEITUNG

    /* Berechnet den Gesamtpreis des Bestellungs (die Summe aller gewählten Geschenkangeboten)

       Wir gehen die Katergorie mit einer for-each-Schleife durch und zählen die Preise zusammen.

       Zum Beispiel:
       Wenn 3 Geschenkangeboten gebucht wurden mit Preisen 25.0, 15.0, 40.0 → ergibt sich Gesamtpreis = 80.0 */

    public double getGesamtpreis() {
        double summe = 0.0;
        for (Geschenk d : katergorie) {
            summe = summe + d.getPreis();
        }
        return summe;
    }

}