package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;


public class Welcome extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller controller;

    public Welcome(final JFrame frame, Controller controller) {
    	this.controller = controller;
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setTitle("Super Password Max Plus Turbo (v.3.2.3)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setResizable(false);

        // Panel for the header
        JPanel headerPanel = new JPanel(null); // Layout nulo para posicionar manualmente
        headerPanel.setBackground(Color.decode("#FF4F63"));
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 100));

        // Logo
        ImageIcon originalIcon = new ImageIcon("logo.png");
        Image originalImage = originalIcon.getImage();
        // Resize the image
        Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(440, 30, 50, 50); // Posición y tamaño manual

        // Título
        JLabel titleLabel = new JLabel("Super Password Max Plus Turbo™ (v.3.2.3)");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(500, 10, 535, 100); // Posición y tamaño manual

        // Botón de ayuda
        JButton helpButton = new JButton("Help");
        helpButton.setBackground(Color.decode("#FF4F63"));
        helpButton.setForeground(Color.WHITE);
        helpButton.setFocusPainted(false);
        helpButton.setFont(new Font("Arial", Font.BOLD, 20));
        helpButton.setBounds(frame.getWidth() - 120, 54, 100, 40); // Posición y tamaño manual
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutManual.showHelpOptions(frame);
            }
        });

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        headerPanel.add(helpButton);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(null); // Layout nulo para posicionar manualmente
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() - 100));

        // Buttons
        JButton newPasswordButton = new JButton("Nueva contraseña");
        newPasswordButton.setBackground(Color.decode("#FF4F63"));
        newPasswordButton.setForeground(Color.WHITE);
        newPasswordButton.setFocusPainted(false);
        newPasswordButton.setFont(new Font("Arial", Font.BOLD, 20));
        newPasswordButton.setBounds(550, 80, 400, 70); // Posición y tamaño manual
        newPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditPassword(frame, controller, null);
            }
        });

        JButton reviewPasswordsButton = new JButton("Revisar Contraseñas");
        reviewPasswordsButton.setBackground(Color.decode("#FF4F63"));
        reviewPasswordsButton.setForeground(Color.WHITE);
        reviewPasswordsButton.setFocusPainted(false);
        reviewPasswordsButton.setFont(new Font("Arial", Font.BOLD, 20));
        reviewPasswordsButton.setBounds(550, 180, 400, 70); // Posición y tamaño manual
        reviewPasswordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CheckPassword(frame, controller);
            }
        });

        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.setBackground(Color.decode("#FF4F63"));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 20));
        logoutButton.setBounds(550, 280, 400, 70); // Posición y tamaño manual
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Hasta pronto :)");
                frame.getContentPane().removeAll();
                frame.repaint();
                new Login(frame, controller);
            }
        });

        buttonPanel.add(newPasswordButton);
        buttonPanel.add(reviewPasswordsButton);
        buttonPanel.add(logoutButton);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
