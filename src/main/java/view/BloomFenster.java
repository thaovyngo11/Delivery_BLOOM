package view;

import com.toedter.calendar.JDateChooser;
import model.Bestellung;
import model.BestellungVerwaltung;
import model.Geschenk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class BloomFenster extends JFrame {

    // ----------------------------- //
    // 1. ATTRIBUTE: GUI-KOMPONENTEN //
    // ----------------------------- //

    private JPanel myPanel;
    private JLabel lbl_Slogan;

    private JTextField tf_Empfaenger;
    private JTextField tf_EmpfaengerTele;
    private JTextField tf_EmpfaengerAdresse;
    private JLabel lbl_Empfaenger;
    private JLabel lbl_EmpfaengerTele;
    private JLabel lbl_EmpfaengerAdresse;

    private JLabel lbl_Kategorie;
    private JLabel lbl_Blumen;
    private JCheckBox chb_Blumen1;
    private JCheckBox chb_Blumen2;
    private JCheckBox chb_Blumen3;
    private JLabel lbl_Verpackung;
    private JComboBox cb_Verpackung;
    private JLabel lbl_Accessorie;
    private JCheckBox chb_Accessorie1;
    private JCheckBox chb_Accessorie2;
    private JCheckBox chb_Accessorie3;

    private JButton btn_Berechnen;
    private JPanel gesamtpreisPanel;
    private JTextField tf_Gesamtpreis;

    private JLabel lbl_Datum;
    private JPanel datumPanel;
    private JLabel lbl_Uhrzeit;
    private JPanel uhrzeitPanel;
    private JSpinner spn_Uhrzeit;

    private JButton btn_Speichern_und_Anzeigen;

    private JScrollPane scp_Bestellung_Uebersicht;
    private JTextArea ta_Bestellung_Uebersicht;

    private JDateChooser dateChooser;
    private BestellungVerwaltung verwaltung = new BestellungVerwaltung();  // Speichert und verwaltet alle Bestellungen
    private boolean initialisiert = false;

    // ------------------------------------- //
    // 2. KONSTRUKTOR: GUI INITIALISIEREN    //
    // ------------------------------------- //

    public BloomFenster() {

        setTitle("Bloom");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(myPanel);
        setVisible(true);

        setupDatum_Uhrzeit();
        setup_cbx_Verpackung();
        setupActionListener_Berechnen();
        setupActionListener_Speichern();

        verwaltung.initObjekte();
        for (Bestellung b: verwaltung.getAlleBestellungen()) {
            zeigeBestellung(b);
        }

    }

    /* Alternative Konstruktor mit Steuerung, ob Beispieldaten geladen werden sollen.
     * Wird beim Unit-Test verwendet, um das Fenster ohne initiale Daten zu öffnen.
     * true = Beispieldaten werden geladen, false = leeres Fenster */

    public BloomFenster(boolean ladeInitial) {
        setTitle("iBeauty Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(myPanel);
        setVisible(true);

        setupDatum_Uhrzeit();
        setup_cbx_Verpackung();
        setupActionListener_Berechnen();
        setupActionListener_Speichern();

        if (ladeInitial) {
            ladeInitialBestellungen();
        }
    }

    // ---------------------------------------------------//
    //  3. KONSTRUKTOR: DATUM - UHRZEIT - ANZEIGEN PANEL  //
    // ---------------------------------------------------//

    private void setupDatum_Uhrzeit() {

        Locale.setDefault(Locale.GERMANY);                                  // Sprache und Format auf Deutsch festlegen
        dateChooser = new JDateChooser();                                   // JDateChooser-Objekt initialisieren (Kalenderfeld)
        dateChooser.setPreferredSize(new Dimension(150, 25));  // Größe des Datumswählers festlegen
        datumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));              // Layout des Panels auf FlowLayout setzen
        datumPanel.add(dateChooser);

        spn_Uhrzeit.setModel(new SpinnerDateModel());                                           // SpinnerDateModel: Uhrzeit initialisieren
        spn_Uhrzeit.setEditor(new JSpinner.DateEditor(spn_Uhrzeit, "HH:mm"));   // setEditor(...): Anzeigeformat für Uhrzeit festlegen (z.B.: 10:30)

    }

    // ---------------------------- //
    // 4. ACTIONLISTENER: Berechnen //
    // ---------------------------- //

    public void setup_cbx_Verpackung() {
        cb_Verpackung.addItem("Rundstrauß");
        cb_Verpackung.addItem("Blumenkorb");
    }

    private void setupActionListener_Berechnen() {

        btn_Berechnen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double gesamtpreis = berechneGesamtpreis();
                tf_Gesamtpreis.setText(gesamtpreis + " Euro");

            }
        });

    }

    // Hilfsmethode zur Preisberechnung
    public double berechneGesamtpreis() {
        double preis = 0.0;

        if (chb_Blumen1.isSelected()) {
            preis += getPreis("Blumen", "Rose");
        }
        if (chb_Blumen2.isSelected()) {
            preis += getPreis("Blumen", "Sonnenblumen");
        }
        if (chb_Blumen3.isSelected()) {
            preis += getPreis("Blumen", "Hortensie");
        }
        if (chb_Accessorie1.isSelected()) {
            preis += getPreis("Accessories", "Grußkarte");
        }
        if (chb_Accessorie2.isSelected()) {
            preis += getPreis("Accessories", "Schokolade");
        }
        if (chb_Accessorie3.isSelected()) {
            preis += getPreis("Accessories", "Teddybär");
        }
        return preis; // Rückgabe des Gesamtpreises
    }

    // Methode zum Ermitteln des Preises anhand Kategorie + Angebot
    public double getPreis(String geschenk, String angebot) {
        for (Geschenk g : Geschenk.getAlleAngebot()) {
            if (g.getGeschenk().equals(geschenk) && g.getAngebot().equals(angebot)) {
                return g.getPreis();
            }
        }
        return 0.0;
    }

    // ----------------------------------------  //
    // 5. ACTIONLISTENER: Speichern und Anzeigen //
    // ----------------------------------------  //

    private void setupActionListener_Speichern() {

        btn_Speichern_und_Anzeigen.addActionListener(e -> {

            try {
                Bestellung bestellung = erzeugeBestellungAusEingaben();

                BestellungVerwaltung.addBestellung(bestellung);

                zeigeBestellung(bestellung);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    public Bestellung erzeugeBestellungAusEingaben() {

        String name = tf_Empfaenger.getText().trim();
        if (name.isEmpty() || !name.matches("[a-zA-ZäöüÄÖÜß ]+")){
            throw new IllegalArgumentException("Bitte geben Sie einen gültigen Namen ein!");
        }
        String telefon = tf_EmpfaengerTele.getText().trim();
        if (telefon.isEmpty() || !telefon.matches("\\d+"))
            throw new IllegalArgumentException("Bitte geben Sie eine gültige Telefonnummer ein!");
        String adresse = tf_EmpfaengerAdresse.getText().trim();

        if (adresse.isEmpty())
            throw new IllegalArgumentException("Adresse darf nicht leer sein.");

        List<Geschenk> liste = new ArrayList<>();

        if (chb_Blumen1.isSelected())
            liste.add(new Geschenk("Blumen", "Rose", getPreis("Blumen", "Rose")));

        if (chb_Blumen2.isSelected())
            liste.add(new Geschenk("Blumen", "Sonnenblumen", getPreis("Blumen", "Sonnenblumen")));

        if (chb_Blumen3.isSelected())
            liste.add(new Geschenk("Blumen", "Hortensie", getPreis("Blumen", "Hortensie")));

        if (chb_Accessorie1.isSelected())
            liste.add(new Geschenk("Accessories", "Grußkarte", getPreis("Accessories", "Grußkarte")));

        if (chb_Accessorie2.isSelected())
            liste.add(new Geschenk("Accessories", "Schokolade", getPreis("Accessories", "Schokolade")));

        if (chb_Accessorie3.isSelected())
            liste.add(new Geschenk("Accessories", "Teddybär", getPreis("Accessories", "Teddybär")));

        if (liste.isEmpty())
            throw new IllegalArgumentException("Bitte wählen Sie mindestens ein Geschenk.");

        String preisText = tf_Gesamtpreis.getText().trim().replaceAll("[^\\d.]", "");    // Gesamtpreis-Text holen und Leerzeichen entfernen, nur Zahlen und Dezimalpunkt behalten (z.B. " 45.0 Euro " = "45.0")
        if (preisText.isEmpty())                                                                           // Wenn kein Preis berechnet wurde, Fehler auslösen
            throw new IllegalArgumentException("Bitte berechnen Sie zuerst den Gesamtpreis.");
        double preis = Double.parseDouble(preisText);

        Date datum = dateChooser.getDate();
        if (datum == null)
            throw new IllegalArgumentException("Bitte wählen Sie ein Datum.");

        LocalDate date = datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();                   // Umwandlung von Date zu LocalDate (Zum Beispiel: 2026-01-20)
        Date zeit = (Date) spn_Uhrzeit.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(zeit);
        LocalTime time = LocalTime.of(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));            // Umwandlung von Date zu LocalTime (Zum Beispiel: 10:30)
        LocalDateTime bestellungDatum = LocalDateTime.of(date, time);                                      // Datum und Uhrzeit zu einem LocalDateTime-Objekt kombinieren (z.B. 2026-01-20T10:30)
        LocalDateTime jetzt = LocalDateTime.now()
                .plusDays(1)
                .withSecond(0)
                .withNano(0);

        if (bestellungDatum.isBefore(jetzt))
            throw new IllegalArgumentException("Lieferzeit mindestens 24 Stunden nach Bestellung");         // Keine Bestellung in der Vergangenheit erlaubt


        return new Bestellung(name, telefon, adresse, liste, bestellungDatum);
    }

    private void zeigeBestellung(Bestellung b) {

        StringBuilder sb = new StringBuilder();

        sb.append("========== BESTELLUNG ==========\n\n");
        sb.append("Empfänger: ").append(b.getEmpfaengerName()).append("\n");
        sb.append("Telefon: ").append(b.getEmpfaengerTelefonnummer()).append("\n");
        sb.append("Adresse: ").append(b.getEmpfaengerAdresse()).append("\n\n");

        sb.append("Geschenke:\n");
        for (Geschenk g : b.getKatergorie()) {
            sb.append("- ")
                    .append(g.getGeschenk())
                    .append(" : ")
                    .append(g.getAngebot())
                    .append(" (")
                    .append(g.getPreis())
                    .append(" €)\n");
        }

        sb.append("\nGesamtpreis: ")
                .append(b.getGesamtpreis())
                .append(" €\n");

        sb.append("Datum & Uhrzeit: ")
                .append(b.getDatum());

        ta_Bestellung_Uebersicht.append(sb.toString());
        ta_Bestellung_Uebersicht.append("\n\n");
        ta_Bestellung_Uebersicht.setCaretPosition(
                ta_Bestellung_Uebersicht.getDocument().getLength()
        );
    }

    // ---------------------------------------------------//
    // 7. HILFSMETHODEN: Daten laden und Preis ermitteln  //
    // ---------------------------------------------------//

    private void ladeInitialBestellungen() {

        if (!initialisiert) {            // Wenn die Bestellung noch nicht geladen wurden
            verwaltung.initObjekte();    // Beispielbestellung in die Verwaltung laden
            initialisiert = true;        // Nur einmal laden
        }

        for (Bestellung b : verwaltung.getAlleBestellungen()) {
            String geschenkNamen = "";
            for (Geschenk g : b.getKatergorie()) {
                if (!geschenkNamen.isEmpty()) geschenkNamen += "\n";
                geschenkNamen += g.getGeschenk() + ": " + g.getAngebot();
            }

        }
    }

    // --------------------------------------------------------------------//
    // 8. HILFSMETHODEN: Getter-Methoden zur Unterstützung von Unit-Tests  //
    // --------------------------------------------------------------------//

    // Getter für Textfelder

    public JTextField getTf_Empfaenger() {

        return tf_Empfaenger;
    }

    public JTextField getTf_EmpfaengerTele() {

        return tf_EmpfaengerTele;
    }

    public JTextField getTf_EmpfaengerAdresse() {

        return tf_EmpfaengerAdresse;
    }

    public JTextField getTf_Gesamtpreis() {

        return tf_Gesamtpreis;
    }

    // Getter für Checkboxen
    public JCheckBox getchb_Blumen1() {

        return chb_Blumen1;
    }

    public JCheckBox getchb_Blumen2() {

        return chb_Blumen2;
    }

    public JCheckBox getchb_Blumen3() {

        return chb_Blumen3;
    }

    public JCheckBox getchb_Accessorie1() {

        return chb_Accessorie1;
    }

    public JCheckBox getchb_Accessorie2() {

        return chb_Accessorie2;
    }

    public JCheckBox getchb_Accessorie3() {

        return chb_Accessorie3;
    }

    // Getter für ComboBoxen
    public JComboBox getcb_Verpackung() {

        return cb_Verpackung;
    }

    // Getter für Kalender und Uhrzeit
    public JDateChooser getDateChooser() {

        return dateChooser;
    }

    public JSpinner getSpn_Uhrzeit() {

        return spn_Uhrzeit;
    }

}
