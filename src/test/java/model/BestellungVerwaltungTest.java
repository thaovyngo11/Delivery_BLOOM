package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestellungVerwaltungTest {

    @BeforeEach
    void setup() {

        BestellungVerwaltung.initObjekte();
    }
    @Test
    void addBestellung() {
        int vorher = BestellungVerwaltung.getAlleBestellungen().size();

        Bestellung neueBestellung = new Bestellung(
                "Vy",
                "017612345678",
                "Wiley Straße 1, 89233 Neu-Ulm",
                List.of(), // leere Dienstliste
                java.time.LocalDateTime.of(2025, 12, 30, 10, 0)
        );

        BestellungVerwaltung.addBestellung(neueBestellung);

        List<Bestellung> bestellungen = BestellungVerwaltung.getAlleBestellungen();
        assertEquals(vorher + 1, bestellungen.size());
        assertEquals("Vy", bestellungen.get(bestellungen.size() - 1).getEmpfaengerName());
    }

    @Test
    void getAlleBestellungen() {
        List<Bestellung> bestellungen = BestellungVerwaltung.getAlleBestellungen();
        assertEquals(3, bestellungen.size()); // initObjekte() erstellt 3 Einträge
        assertEquals("Büsra", bestellungen.get(0).getEmpfaengerName());
        assertEquals("Vy", bestellungen.get(1).getEmpfaengerName());
        assertEquals("Sarah", bestellungen.get(2).getEmpfaengerName());
    }

    @Test
    void initObjekte() {
        List<Bestellung> bestellungen = BestellungVerwaltung.getAlleBestellungen();
        assertEquals(3, bestellungen.size());

        assertTrue(bestellungen.stream().anyMatch(t -> t.getEmpfaengerName().equals("Büsra")));
        assertTrue(bestellungen.stream().anyMatch(t -> t.getEmpfaengerName().equals("Vy")));
        assertTrue(bestellungen.stream().anyMatch(t -> t.getEmpfaengerName().equals("Sarah")));
    }

}