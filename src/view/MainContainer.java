package view;

import javax.swing.JFrame;
import controller.Controller;

public class MainContainer extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainContainer(Controller controller) {
		new Welcome(this, controller);
	}
}
