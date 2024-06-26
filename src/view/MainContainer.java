package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainContainer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MainContainer() {
		new Welcome(this);
	}

}
