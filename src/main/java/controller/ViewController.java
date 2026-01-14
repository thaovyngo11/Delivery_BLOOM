package controller;

import view.BloomFenster;

public class ViewController {
    public BloomFenster bloomFenster;

    public ViewController(BloomFenster view) {
        this.bloomFenster = view;

    }
    public void BloomFenster_anzeigen() {
        bloomFenster.setVisible(true);
    }
}
