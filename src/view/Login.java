package view;

import javax.swing.*;
import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField usuarioField;
	private JPasswordField contrasenaField;
    private JButton helpButton; // Declarar el botón como miembro de la clase
    private Controller controller;

	public Login(JFrame frame, Controller controller) {
		this.frame = frame;
        this.controller = controller;
		initialize();
	}

	private void initialize() {
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setTitle("Super Password Max Plus Turbo™ (v.3.2.3)");
		frame.setSize(410, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
		frame.setResizable(false);

		JLabel lblTitulo = new JLabel("Iniciar Sesión");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 410, 90);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(Color.decode("#FF4F63"));
		lblTitulo.setForeground(Color.WHITE);
		frame.getContentPane().add(lblTitulo);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 80, 400, 500);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(70, 20, 80, 25);
		panel.add(lblUsuario);

		usuarioField = new JTextField();
		usuarioField.setBounds(150, 20, 180, 25);
		panel.add(usuarioField);
		usuarioField.setColumns(10);

		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setBounds(70, 60, 80, 25);
		panel.add(lblContraseña);

		contrasenaField = new JPasswordField();
		contrasenaField.setBounds(150, 60, 180, 25);
		panel.add(contrasenaField);

		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setBounds(145, 100, 120, 30);
		btnIniciarSesion.setBackground(Color.decode("#FF4F63"));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFocusPainted(false);
		panel.add(btnIniciarSesion);

		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Validar el usuario y contraseña
				String usuario = usuarioField.getText();
				String contraseña = String.valueOf(contrasenaField.getPassword());

				if (validarCredenciales(usuario, contraseña)) {
					// JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso");
					new Welcome(frame, controller); // Aquí puedes abrir la siguiente ventana o realizar alguna acción
				} else {
					JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(145, 140, 120, 30);
		btnRegistrarse.setBackground(Color.decode("#FF4F63"));
		btnRegistrarse.setForeground(Color.WHITE);
		btnRegistrarse.setFocusPainted(false);
		panel.add(btnRegistrarse);

		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrir la ventana de registro
				frame.dispose(); // Cerrar la ventana actual de inicio de sesión
				JFrame registerFrame = new JFrame();
				Register registerWindow = new Register(registerFrame, controller);
			}
		});
                // Configuración del botón "Help"
                helpButton = new JButton("Help");
                helpButton.setBounds(310, 55, 80, 30);
                helpButton.setBackground(Color.decode("#FF4F63"));
                helpButton.setForeground(Color.WHITE);
                helpButton.setFocusPainted(false);
                frame.getContentPane().add(helpButton);
        
                helpButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        AboutManual.showHelpOptions(frame);
                    }
                });
        
                // Asegurar que el botón Help sea visible desde el inicio
                helpButton.setVisible(true);
        
                // Ajustar el orden z para asegurar que el botón Help esté delante
                frame.getContentPane().setComponentZOrder(helpButton, 0);

		frame.setVisible(true);
	}

	private boolean validarCredenciales(String usuario, String contrasena) {
		try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] partes = linea.split(",");
				if (partes.length == 3) {
					String usuarioRegistrado = partes[1];
					String contrasenaRegistrada = partes[2];
					if (usuario.equals(usuarioRegistrado) && contrasena.equals(contrasenaRegistrada)) {
						return true;
					}
				}
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(frame, "Error al leer el archivo de usuarios", "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		return false;
	}
}