import controller.ViewController;
import view.BloomFenster;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BloomFenster view = new BloomFenster();
                ViewController controller = new ViewController(view);
                controller.BloomFenster_anzeigen();
            }
        });
    }
}
