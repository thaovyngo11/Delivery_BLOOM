package view;

import model.Bestellung;
import model.Geschenk;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BloomFensterTest {

    BloomFenster fenster = new BloomFenster(false);

    @Test
    void getPreisBlumen_Test() {
        //BloomFenster fenster = new BloomFenster();
        Geschenk.getAlleAngebot().clear();
        Geschenk.getAlleAngebot().add(new Geschenk("Blumen", "Rose", 10.0));

        double result = fenster.getPreis("Blumen", "Rose");
        assertEquals(10.0, result);
    }

    @Test
    void getPreisAccessories_Test() {
        //iBeautyFenster fenster = new iBeautyFenster();
        Geschenk.getAlleAngebot().clear();
        Geschenk.getAlleAngebot().add(new Geschenk("Accessories", "Grußkarte", 3.0));

        double result = fenster.getPreis("Accessories", "Grußkarte");
        assertEquals(3.0, result);
    }

    @Test
    void berechneGesamtpreis() {
        //iBeautyFenster fenster = new iBeautyFenster();

        Geschenk.getAlleAngebot().clear();
        Geschenk.getAlleAngebot().add(new Geschenk("Blumen", "Rose", 10.0));
        Geschenk.getAlleAngebot().add(new Geschenk("Accessories", "Grußkarte", 3.0));

        fenster.getchb_Blumen1().setSelected(true);
        fenster.getchb_Blumen2().setSelected(false);
        fenster.getchb_Blumen3().setSelected(false);
        fenster.getcb_Verpackung().setSelectedItem("Blumenkorb");
        fenster.getchb_Accessorie1().setSelected(true);
        fenster.getchb_Accessorie2().setSelected(false);
        fenster.getchb_Accessorie3().setSelected(false);

        double preis = fenster.berechneGesamtpreis();

        assertEquals(13.0, preis);
    }

    @Test
    void erzeugeBestellungAusEingaben() {
        //iBeautyFenster fenster = new iBeautyFenster();

        Geschenk.getAlleAngebot().clear();
        Geschenk.getAlleAngebot().add(new Geschenk("Accessories", "Schokolade ", 5.0));

        fenster.getTf_Empfaenger().setText("Vy");
        fenster.getTf_EmpfaengerTele().setText("015263816491");
        fenster.getTf_EmpfaengerAdresse().setText("Wiley Straße 2, 89231 Neu-Ulm");

        fenster.getchb_Accessorie2().setSelected(true);

        LocalDateTime dateTime = LocalDateTime.of(2026, 01, 30, 15, 0);
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        fenster.getDateChooser().setDate(date);
        fenster.getSpn_Uhrzeit().setValue(date);

        double preis = fenster.berechneGesamtpreis();
        fenster.getTf_Gesamtpreis().setText(preis + " Euro");

        Bestellung bestellung = fenster.erzeugeBestellungAusEingaben();

        assertEquals("Vy", bestellung.getEmpfaengerName());
        assertEquals("015263816491", bestellung.getEmpfaengerTelefonnummer());
        assertEquals("Wiley Straße 2, 89231 Neu-Ulm", bestellung.getEmpfaengerAdresse());
        assertEquals(1, bestellung.getKatergorie().size());
        assertEquals(5.0, bestellung.getGesamtpreis());
    }

}