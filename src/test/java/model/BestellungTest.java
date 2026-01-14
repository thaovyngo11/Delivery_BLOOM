package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeschenkTest {
    @Test
    void testgetGeschenk() {
        Geschenk g = new Geschenk("Blumen", "Rose", 2.0);
        assertEquals("Blumen", g.getGeschenk());
    }

    @Test
    void testgetAngebot() {
        Geschenk g = new Geschenk("Blumen", "Rose", 2.0);
        assertEquals("Rose", g.getAngebot());
    }

    @Test
    void testgetPreis() {
        Geschenk d = new Geschenk("Blumen", "Rose", 2.0);
        assertEquals(2.0, d.getPreis());
    }

    @Test
    void testgetAlleAngebot() {
        List<Geschenk> angebote = Geschenk.getAlleAngebot();

        // Prüfe ob 6 Angeboten enthalten sind
        assertEquals(6, angebote.size());

        // Prüfe ob ein konkretes Beispiel vorhanden ist
        boolean gefunden = false;
        for (Geschenk g : angebote) {
            if (g.getGeschenk().equals("Blumen") &&
                    g.getAngebot().equals("Rose") &&
                    g.getPreis() == 0.80) {
                gefunden = true;
                break;
            }
        }

        assertTrue(gefunden);
    }

}