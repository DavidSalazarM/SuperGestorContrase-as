package view;

import javax.swing.*;

import controller.Controller;
import model.PasswordEntry;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;

public class EditPassword extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JTextField sitioField;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JRadioButton noSerializadaCheckbox;
    private JRadioButton caracterEspecialCheckbox;
    private JRadioButton letrasMayusculasCheckbox;
    private JRadioButton letrasMinusculasCheckbox;
    private JRadioButton caracterNumericoCheckbox;
    private JComboBox<Integer> longitudComboBox;
    private JButton guardarButton;
    private JButton sugerenciaButton;
    private JButton regresarButton;
    private JToggleButton verContraseñaButton;
    private Controller controller;
    private PasswordEntry currentEntry; 


    public EditPassword(JFrame frame, Controller controller, PasswordEntry passwordEntry) {
        this.frame = frame;
    	currentEntry = passwordEntry;    	
        this.controller = controller;
        initialize();
    }

    private void initialize() {
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
        logoLabel.setBounds(590, 30, 50, 50); // Posición y tamaño manual

        // Título
        JLabel titleLabel = new JLabel("Nueva Contraseña");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(650, 10, 535, 100); // Posición y tamaño manual

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

        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Panel izquierdo
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(450, 200, 350, 300);
        leftPanel.setBackground(Color.WHITE);

     // Etiqueta SITIO
        JLabel lblSitio = new JLabel("SITIO");
        lblSitio.setBounds(0, 0, 80, 25);
        lblSitio.setForeground(Color.decode("#808080"));
        lblSitio.setFont(new Font("Arial", Font.PLAIN, 16)); // Aumentar tamaño de la fuente
        leftPanel.add(lblSitio);

        sitioField = new JTextField();
        sitioField.setBounds(0, 30, 300, 35);
        sitioField.setFont(new Font("Arial", Font.PLAIN, 16));
        sitioField.setBackground(Color.decode("#ECECEC"));
        sitioField.setForeground(Color.decode("#4D4C4C"));
        leftPanel.add(sitioField);
        sitioField.setColumns(10);
        if (currentEntry != null) {
        	sitioField.setText(currentEntry.getSite());
        }

        // Etiqueta USUARIO
        JLabel lblUsuario = new JLabel("USUARIO");
        lblUsuario.setBounds(0, 70, 80, 25);
        lblUsuario.setForeground(Color.decode("#808080"));
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16)); // Aumentar tamaño de la fuente
        leftPanel.add(lblUsuario);

        usuarioField = new JTextField();
        usuarioField.setBounds(0, 100, 300, 35);
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 16));
        usuarioField.setBackground(Color.decode("#ECECEC"));        
        usuarioField.setForeground(Color.decode("#4D4C4C"));
        leftPanel.add(usuarioField);
        usuarioField.setColumns(10);
        if (currentEntry != null) {
        	usuarioField.setText(currentEntry.getUsername());
        }

        // Etiqueta CONTRASEÑA
        JLabel lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setBounds(0, 140, 110, 25);
        lblContraseña.setForeground(Color.decode("#808080"));
        lblContraseña.setFont(new Font("Arial", Font.PLAIN, 16)); // Aumentar tamaño de la fuente
        leftPanel.add(lblContraseña);

        contraseñaField = new JPasswordField();
        contraseñaField.setBounds(0, 170, 300, 35);
        contraseñaField.setFont(new Font("Arial", Font.PLAIN, 16));
        contraseñaField.setBackground(Color.decode("#ECECEC"));
        contraseñaField.setForeground(Color.decode("#4D4C4C"));
        leftPanel.add(contraseñaField);
        if (currentEntry != null) {
        	contraseñaField.setText(currentEntry.getPassword());
        }

        verContraseñaButton = new JToggleButton("...");
        verContraseñaButton.setBounds(300, 170, 30, 34);
        verContraseñaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (verContraseñaButton.isSelected()) {
                    contraseñaField.setEchoChar((char) 0); // Mostrar contraseña
                } else {
                    contraseñaField.setEchoChar('\u2022'); // Ocultar contraseña
                }
            }
        });
        leftPanel.add(verContraseñaButton);

        frame.getContentPane().add(leftPanel);

        // Panel derecho
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBounds(800, 200, 250, 200);
        rightPanel.setBackground(Color.WHITE);

        // Etiqueta para los requerimientos de contraseña
        JLabel lblRequerimientos = new JLabel("<html>Requerimientos contraseña:<br><br></html>");
        lblRequerimientos.setHorizontalAlignment(SwingConstants.LEFT);
        Font font1 = new Font("Arial", Font.PLAIN, 16); // Arial, negrita, tamaño 20
        lblRequerimientos.setFont(font1);

        // Panel para checkboxes
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(0, 1, 0, 1)); // Grid para checkboxes con más espacio vertical
        checkboxPanel.setBackground(Color.WHITE);

        noSerializadaCheckbox = new JRadioButton("No puede ser serializada");
        caracterEspecialCheckbox = new JRadioButton("Carácter especial");
        caracterEspecialCheckbox.setBackground(Color.WHITE); // Establecer fondo blanco

        letrasMayusculasCheckbox = new JRadioButton("Minúscula");
        letrasMayusculasCheckbox.setBackground(Color.WHITE);

        letrasMinusculasCheckbox = new JRadioButton("Mayúscula");
        letrasMinusculasCheckbox.setBackground(Color.WHITE);

        caracterNumericoCheckbox = new JRadioButton("Carácter numérico");
        caracterNumericoCheckbox.setBackground(Color.WHITE);

        Font font = new Font("Arial", Font.PLAIN, 16); // Fuente Arial, tamaño 16
        noSerializadaCheckbox.setFont(font);
        caracterEspecialCheckbox.setFont(font);
        letrasMayusculasCheckbox.setFont(font);
        letrasMinusculasCheckbox.setFont(font);
        caracterNumericoCheckbox.setFont(font);

        checkboxPanel.add(caracterEspecialCheckbox);
        checkboxPanel.add(letrasMayusculasCheckbox);
        checkboxPanel.add(letrasMinusculasCheckbox);
        checkboxPanel.add(caracterNumericoCheckbox);

        rightPanel.add(lblRequerimientos, BorderLayout.NORTH); // Agregar la etiqueta al panel derecho
        rightPanel.add(checkboxPanel, BorderLayout.CENTER); // Agregar el panel de checkboxes al centro del panel derecho

        // ComboBox para longitud de contraseña (6 a 12 caracteres)
        Integer[] longitudes = { 6, 7, 8, 9, 10, 11, 12 };
        longitudComboBox = new JComboBox<>(longitudes);
        longitudComboBox.setSelectedIndex(0); // Seleccionar el primer ítem por defecto

        // Crear la etiqueta para la longitud de contraseña
        JLabel lblLongitud = new JLabel("Longitud de contraseña:");
        lblLongitud.setFont(new Font("Arial", Font.PLAIN, 16));

        // Crear el panel que contendrá la etiqueta y el ComboBox
        JPanel longitudPanel = new JPanel();
        longitudPanel.setBackground(Color.WHITE);
        longitudPanel.add(lblLongitud); // Agregar la etiqueta al panel
        longitudPanel.add(longitudComboBox); // Agregar el ComboBox al panel

        // Agregar longitudPanel al panel derecho (rightPanel)
        rightPanel.add(longitudPanel, BorderLayout.SOUTH); // Agregar el panel de longitud al panel derecho

        frame.getContentPane().add(rightPanel); // Agregar el panel derecho al frame

        // Panel para los botones principales (Guardar, Generar, Regresar)
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(0, 3, 10, 5));
        buttonsPanel.setBounds(500, 500, 500, 50);
        buttonsPanel.setBackground(Color.WHITE);

        guardarButton = new JButton("Guardar");
        guardarButton.setBackground(Color.decode("#FF4F63"));
        guardarButton.setForeground(Color.WHITE);
        guardarButton.setFont(new Font("Arial", Font.BOLD, 20));
        guardarButton.setBounds(570, 500, 100, 40); // Posición manual
        buttonsPanel.add(guardarButton); // Agregar el botón al panel de botones
        guardarButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	PasswordEntry entry = new PasswordEntry();
            	entry.setSite(sitioField.getText());
            	entry.setUsername(usuarioField.getText());
            	entry.setPassword(contraseñaField.getText());
            	controller.addPasswordEntry(entry);
            	try {
					controller.save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	new CheckPassword(frame, controller); 
            } 
        }); 

        sugerenciaButton = new JButton("Generar");
        sugerenciaButton.setBackground(Color.decode("#FF4F63"));
        sugerenciaButton.setFont(new Font("Arial", Font.BOLD, 20));
        sugerenciaButton.setBounds(680, 500, 100, 40); // Posición manual
        sugerenciaButton.setForeground(Color.WHITE);
        buttonsPanel.add(sugerenciaButton);

        regresarButton = new JButton("Regresar");
        regresarButton.setBackground(Color.decode("#FF4F63"));
        regresarButton.setFont(new Font("Arial", Font.BOLD, 20));
        regresarButton.setBounds(790, 500, 100, 40); // Posición manual
        regresarButton.setForeground(Color.WHITE);
        regresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Welcome(frame, controller);
            }
        });
        buttonsPanel.add(regresarButton);

        frame.getContentPane().add(buttonsPanel);

        // Panel para el botón "Help"
        JPanel helpPanel = new JPanel(new BorderLayout());
        helpPanel.setBackground(Color.WHITE);
        helpPanel.setBounds(1380, 54, 100, 40); // Ajustar los límites para colocarlo en la parte superior derecha

        frame.getContentPane().add(helpPanel);

        frame.getContentPane().setBackground(Color.WHITE);

        frame.setVisible(true);
    }
}
