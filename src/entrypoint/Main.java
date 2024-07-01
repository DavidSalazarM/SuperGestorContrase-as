package entrypoint;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.io.IOException;
import controller.Controller;
import view.Login;
import view.MainContainer;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
				Controller controller = new Controller();
                try {
					controller.load();
					MainContainer frame = new MainContainer(controller);
					frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
