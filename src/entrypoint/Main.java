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
                    JFrame frame = new JFrame();
                    Login loginFrame = new Login(frame, controller);
                    loginFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
