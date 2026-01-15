
package model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* Die Klasse BestellungVerwaltung dient zur zentralen Verwaltung von Bestellungen.
   Sie speichert alle Bestellungen, bietet Zugriff auf die Bestellungsliste */

public class BestellungVerwaltung {

    /* Die Klasse funktioniert wie eine Verwaltungszentrale für alle gebuchten Bestellung:
       Sie ist für das Speichern, Abrufen zuständig. */

    // ATTRIBUTE: Statische Liste zur Speicherung aller Bestellungen
    private static List<Bestellung> bestellungen = new ArrayList<>();

    public BestellungVerwaltung() {

    }

    /*Fügt eine neue Bestellung zur Bestellungsliste hinzu.*/
    public static void addBestellung(Bestellung bestellung) {

        bestellungen.add(bestellung);
    }

    public static List<Bestellung> getAlleBestellungen() {

        return new ArrayList<>(bestellungen);
    }

    public static void initObjekte() {
        bestellungen.clear();
        List<Geschenk> angebot1 = new ArrayList<>();

        angebot1.add(new Geschenk("Blumen", "Rose", 10.0));

        Bestellung b1 = new Bestellung( "Büsra",
                "015263816491", "Wiley Straße 1, 89231 Neu-Ulm", angebot1,
                LocalDateTime.of(2025, 12, 20, 18, 0)
        );

        bestellungen.add(b1);

        List<Geschenk> angebot2 = new ArrayList<>();

        angebot2.add(new Geschenk("Accessories", "Schokolade", 5.0));

        Bestellung b2 = new Bestellung( "Vy", "015263816491", "Wiley Straße 2, 89231 Neu-Ulm", angebot2,
                LocalDateTime.of(2025, 12, 22, 15, 0)
        );

        bestellungen.add(b2);

        List<Geschenk> angebot3 = new ArrayList<>();

        angebot3.add(new Geschenk("Accessories", "Teddybär", 10.0));

        Bestellung b3 = new Bestellung( "Sarah", "015263817842", "Wiley Straße 3, 89231 Neu-Ulm", angebot3,
                LocalDateTime.of(2025, 12, 20, 18, 0)
        );

        bestellungen.add(b3);

    }

}
