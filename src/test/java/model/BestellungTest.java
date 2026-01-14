package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestellungTest {

    @Test
    void testgetEmpfaengerName() {
        Bestellung b = new Bestellung("Moh", "015302793261", "Wiley Straße 4, 89233 Neu-Ulm",
                List.of(new Geschenk("Accessories", "Grußkarte", 3.0)),
                LocalDateTime.of(2025, 12, 30, 10, 0));
        assertEquals("Moh", b.getEmpfaengerName());
    }

    @Test
    void testgetEmpfaengerTelefonnummer() {
        Bestellung b = new Bestellung("Moh", "015302793261", "Wiley Straße 5, 89233 Neu-Ulm",
                List.of(new Geschenk("Accessories", "Grußkarte", 3.0)),
                LocalDateTime.of(2025, 12, 30, 10, 0));
        assertEquals("015302793261", b.getEmpfaengerTelefonnummer());
    }

    @Test
    void testgetEmpfaengerAdresse() {
        Bestellung b = new Bestellung("Moh", "015302793261", "Wiley Straße 5, 89233 Neu-Ulm",
                List.of(new Geschenk("Accessories", "Grußkarte", 3.0)),
                LocalDateTime.of(2025, 12, 30, 10, 0));
        assertEquals("Wiley Straße 5, 89233 Neu-Ulm", b.getEmpfaengerAdresse());
    }

    @Test
    void testgetKategorie() {
        List<Geschenk> angebot = new ArrayList<>();
        angebot.add(new Geschenk("Blumen", "Rose", 10.0));
        angebot.add(new Geschenk("Accessories", "Grußkarte", 3.0));

        Bestellung b = new Bestellung("Moh", "015302793261", "Wiley Straße 5, 89233 Neu-Ulm", angebot,
                LocalDateTime.of(2025, 12, 30, 10, 0));

        assertEquals(2, b.getKatergorie().size());
        assertEquals("Rose", b.getKatergorie().get(0).getAngebot());
        assertEquals("Grußkarte", b.getKatergorie().get(1).getAngebot());
    }

    @Test
    void testgetDatum() {
        LocalDateTime datum = LocalDateTime.of(2025, 12, 30, 10, 0);
        Bestellung b = new Bestellung("Moh", "015302793261", "Wiley Straße 5, 89233 Neu-Ulm",
                List.of(
                        new Geschenk("Blumen", "Rose", 10.0),
                        new Geschenk("Accessories", "Grußkarte", 3.0)
                ),
                datum);
        assertEquals(datum, b.getDatum());
    }
}