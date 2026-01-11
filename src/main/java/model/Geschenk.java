package model;

import java.util.ArrayList;
import java.util.List;

public class Geschenk {

    /* Diese Klasse beschreibt eine Geschenk
       Zum Beispiel: Kategorie = "Blumen", Angebot = "Rose" */

    // ATTRIBUTE
    private String geschenk;
    private String angebot;
    private double preis;

    /* KONSTRUKTUR:

       Wenn wir ein neue Bestellung erstellen wollen,
       brauchen wir diese 3 Infos: Geschenk, Angebot, Preis. */

    public Geschenk (String geschenk, String angebot, double preis) {
        this.geschenk = geschenk;
        this.angebot = angebot;
        this.preis = preis;
    }

    /* GETTER- UND SETTER METHODE:

       Diese Methoden helfen uns, auf die privaten Daten (Geschenk, Angebot, Preis) zugreifen oder sie zu ändern.

       * Getter = Was steht drin?
       * Setter = Setze einen neuen Wert */

    public String getGeschenk (){

        return geschenk;
    }

    public String getAngebot (){

        return angebot;
    }

    public double getPreis(){

        return preis;
    }

    /* METHODE:

       *Diese Methode ist "static",
        weil wir sie direkt über die Klasse aufrufen wollen,
        ohne zuerst ein Objekt von "Geschennk" zu erstellen.

       *Diese Methode gibt eine Liste mit Beispiel-Angeboten zurück (ArrayList)

     */

    public static List<Geschenk> getAlleAngebot() {
        List<Geschenk> angebot = new ArrayList<>(); // Neue Liste erstellen

        //Blumen-Angebote hinzufügt
        angebot.add(new Geschenk("Blumen", "Rose", 0.80));
        angebot.add(new Geschenk("Blumen","Sonnenblumen", 2.0));
        angebot.add(new Geschenk("Blumen", "Hortensie", 2.0));

        //Accessories-Angebote hinzufügt
        angebot.add(new Geschenk("Accessories","Grußkarte", 3.0));
        angebot.add(new Geschenk("Accessories", "Schokolade", 5.0));
        angebot.add(new Geschenk("Accessories","Teddybär", 10.0));

        // Rückgabe der vollständigen Liste aller Angebote
        return angebot;
    }
}
