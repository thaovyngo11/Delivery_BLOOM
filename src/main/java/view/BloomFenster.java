package view;

import com.toedter.calendar.JDateChooser;
import model.Bestellung;
import model.Geschenk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class BloomFenster extends JFrame {

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

    }

    private void setupDatum_Uhrzeit() {

        Locale.setDefault(Locale.GERMANY);                                  // Sprache und Format auf Deutsch festlegen
        dateChooser = new JDateChooser();                                   // JDateChooser-Objekt initialisieren (Kalenderfeld)
        dateChooser.setPreferredSize(new Dimension(150, 25));  // Größe des Datumswählers festlegen
        datumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));              // Layout des Panels auf FlowLayout setzen
        datumPanel.add(dateChooser);

        spn_Uhrzeit.setModel(new SpinnerDateModel());                                           // SpinnerDateModel: Uhrzeit initialisieren
        spn_Uhrzeit.setEditor(new JSpinner.DateEditor(spn_Uhrzeit, "HH:mm"));   // setEditor(...): Anzeigeformat für Uhrzeit festlegen (z.B.: 10:30)

    }

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

    private double berechneGesamtpreis() {

        double preis = 0.0;

        if (chb_Blumen1.isSelected()) {
            preis += 0.8;
        }
        if (chb_Blumen2.isSelected()) {
            preis += 2.0;
        }
        if (chb_Blumen3.isSelected()) {
            preis += 2.0;
        }

        if (chb_Accessorie1.isSelected()) {
            preis += 3.0;
        }
        if (chb_Accessorie2.isSelected()) {
            preis += 5.0;
        }
        if (chb_Accessorie3.isSelected()) {
            preis += 10.0;
        }

        return preis;
    }

    private void setupActionListener_Speichern() {

        btn_Speichern_und_Anzeigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ta_Bestellung_Uebersicht.append(
                        "Bestellung gespeichert!\n"
                );
            }
        });
    }
    private String leseName() {
        String name = tf_Empfaenger.getText().trim();
        if (name.isEmpty())
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        return name;
    }

    private String leseTelefon() {
        String telefon = tf_EmpfaengerTele.getText().trim();
        if (telefon.isEmpty())
            throw new IllegalArgumentException("Telefonnummer darf nicht leer sein.");
        return telefon;
    }

    private String leseAdresse() {
        String adresse = tf_EmpfaengerAdresse.getText().trim();
        if (adresse.isEmpty())
            throw new IllegalArgumentException("Adresse darf nicht leer sein.");
        return adresse;
    }
    private java.util.List<model.Geschenk> erzeugeGeschenkListe() {

        java.util.List<model.Geschenk> liste = new java.util.ArrayList<>();

        if (chb_Blumen1.isSelected())
            liste.add(new model.Geschenk("Blumen", "Rose", 0.8));
        if (chb_Blumen2.isSelected())
            liste.add(new model.Geschenk("Blumen", "Sonnenblumen", 2.0));
        if (chb_Blumen3.isSelected())
            liste.add(new model.Geschenk("Blumen", "Hortensie", 2.0));

        if (chb_Accessorie1.isSelected())
            liste.add(new model.Geschenk("Accessories", "Grußkarte", 3.0));
        if (chb_Accessorie2.isSelected())
            liste.add(new model.Geschenk("Accessories", "Schokolade", 5.0));
        if (chb_Accessorie3.isSelected())
            liste.add(new model.Geschenk("Accessories", "Teddybär", 10.0));

        if (liste.isEmpty())
            throw new IllegalArgumentException("Bitte wählen Sie mindestens ein Geschenk.");

        return liste;
    }
    private java.time.LocalDateTime leseDatumUndUhrzeit() {

        java.util.Date datum = dateChooser.getDate();
        if (datum == null)
            throw new IllegalArgumentException("Bitte wählen Sie ein Datum.");

        java.time.LocalDate date =
                datum.toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime((java.util.Date) spn_Uhrzeit.getValue());

        java.time.LocalTime time =
                java.time.LocalTime.of(
                        cal.get(java.util.Calendar.HOUR_OF_DAY),
                        cal.get(java.util.Calendar.MINUTE)
                );

        return java.time.LocalDateTime.of(date, time);
    }
    private model.Bestellung erzeugeBestellungAusEingaben() {

        String name = leseName();
        String telefon = leseTelefon();
        String adresse = leseAdresse();

        java.util.List<model.Geschenk> liste = erzeugeGeschenkListe();
        java.time.LocalDateTime zeitpunkt = leseDatumUndUhrzeit();

        return new model.Bestellung(name, telefon, adresse, liste, zeitpunkt);
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
}
