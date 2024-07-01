package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JTextField nombreField;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private Controller controller;


    public Register(JFrame frame, Controller controller) {
        this.frame = frame;
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setTitle("Registro de Usuario");
        frame.setSize(410, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setResizable(false);

        JLabel lblTitulo = new JLabel("Registro de Usuario");
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

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(70, 20, 80, 25);
        panel.add(lblNombre);

        nombreField = new JTextField();
        nombreField.setBounds(150, 20, 180, 25);
        panel.add(nombreField);
        nombreField.setColumns(10);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(70, 60, 80, 25);
        panel.add(lblUsuario);

        usuarioField = new JTextField();
        usuarioField.setBounds(150, 60, 180, 25);
        panel.add(usuarioField);
        usuarioField.setColumns(10);

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(70, 100, 80, 25);
        panel.add(lblContraseña);

        contraseñaField = new JPasswordField();
        contraseñaField.setBounds(150, 100, 180, 25);
        panel.add(contraseñaField);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(145, 140, 120, 30);
        btnRegistrarse.setBackground(Color.decode("#FF4F63"));
        btnRegistrarse.setForeground(Color.WHITE);
        btnRegistrarse.setFocusPainted(false);
        panel.add(btnRegistrarse);

        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto
                String nombre = nombreField.getText();
                String usuario = usuarioField.getText();
                String contraseña = String.valueOf(contraseñaField.getPassword());

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Guardar los datos en un archivo de texto
                guardarRegistro(nombre, usuario, contraseña);

                // Mostrar mensaje de registro exitoso
                JOptionPane.showMessageDialog(frame, "Registro exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);

                // Limpiar los campos después del registro
                nombreField.setText("");
                usuarioField.setText("");
                contraseñaField.setText("");

                // Volver a la ventana de inicio de sesión
                new Login(frame, controller);
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(145, 180, 120, 30);
        btnCancelar.setBackground(Color.decode("#FF4F63"));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        panel.add(btnCancelar);

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Volver a la ventana de inicio de sesión al cancelar
                new Login(frame, controller);
            }
        });

        frame.setVisible(true);
    }

    private void guardarRegistro(String nombre, String usuario, String contraseña) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("usuarios.txt", true))) {
            // Escribir los datos en el archivo
            writer.println(nombre + "," + usuario + "," + contraseña);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar el registro", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
