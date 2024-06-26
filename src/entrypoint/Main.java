package entrypoint;

import java.awt.EventQueue;
import java.io.IOException;

import view.MainContainer;

public class Main {
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainContainer frame = new MainContainer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
