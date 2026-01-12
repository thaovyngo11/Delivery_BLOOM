package view;

import model.Bestellung;

import javax.swing.*;

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

    public BloomFenster() {

        setTitle("Bloom");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(myPanel);
        setVisible(true);

    }
}
