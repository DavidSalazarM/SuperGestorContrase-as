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
        //frame.setBounds(100, 100, 1000, 500);
        frame.setSize(1500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
        frame.setResizable(false);

        JLabel lblTitulo = new JLabel("Guardar Contraseña");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 0, 1500, 100);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(Color.decode("#FF4F63"));
        lblTitulo.setForeground(Color.WHITE);
        frame.getContentPane().add(lblTitulo);

        JPanel leftPanel = new JPanel(); /////////////////////////
        leftPanel.setLayout(null);
        leftPanel.setBounds(450, 200, 350, 300);
        leftPanel.setBackground(Color.WHITE);

        JLabel lblSitio = new JLabel("SITIO");
        lblSitio.setBounds(0, 0, 80, 25);
        lblSitio.setForeground(Color.decode("#808080"));
        leftPanel.add(lblSitio);

        sitioField = new JTextField();
        sitioField.setBounds(0, 30, 300, 35);
        sitioField.setBackground(Color.decode("#ECECEC"));
        sitioField.setForeground(Color.decode("#4D4C4C"));
        leftPanel.add(sitioField);
        sitioField.setColumns(10);
        if (currentEntry != null) {
        	sitioField.setText(currentEntry.getSite());
        }

        JLabel lblUsuario = new JLabel("USUARIO");
        lblUsuario.setBounds(0, 70, 80, 25);
        lblUsuario.setForeground(Color.decode("#808080"));
        leftPanel.add(lblUsuario);

        usuarioField = new JTextField();
        usuarioField.setBounds(0,100, 300, 35);
        usuarioField.setBackground(Color.decode("#ECECEC"));
        usuarioField.setForeground(Color.decode("#4D4C4C"));
        leftPanel.add(usuarioField);
        usuarioField.setColumns(10);
        if (currentEntry != null) {
        	usuarioField.setText(currentEntry.getUsername());
        }

        JLabel lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setBounds(0, 140, 100, 25);
        lblContraseña.setForeground(Color.decode("#808080"));
        leftPanel.add(lblContraseña);

        contraseñaField = new JPasswordField();
        contraseñaField.setBounds(0, 170, 300, 35);
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

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBounds(800, 200, 250, 200);
        
        rightPanel.setBackground(Color.WHITE);

        JLabel lblRequerimientos = new JLabel("<html>Requerimientos contraseña:<br><br></html>");
        lblRequerimientos.setHorizontalAlignment(SwingConstants.LEFT);
        rightPanel.add(lblRequerimientos, BorderLayout.NORTH);
        Font font1 = new Font("Arial", Font.PLAIN, 16); // Arial, negrita, tamaño 20
        lblRequerimientos.setFont(font1);

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(0,1,0, 1)); // Grid para checkboxes con más espacio vertical
        checkboxPanel.setBackground(Color.WHITE);
        checkboxPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));

        noSerializadaCheckbox = new JRadioButton("No puede ser serializada");
        caracterEspecialCheckbox = new JRadioButton("Carácter especial");
        caracterEspecialCheckbox.setBackground(Color.WHITE);  // Establecer fondo blanco
        
        

        letrasMayusculasCheckbox = new JRadioButton("Minúscula");
        letrasMayusculasCheckbox.setBackground(Color.WHITE);
        

        letrasMinusculasCheckbox = new JRadioButton("Mayúscula");
        letrasMinusculasCheckbox.setBackground(Color.WHITE);
       

        caracterNumericoCheckbox = new JRadioButton("Carácter numérico");
        caracterNumericoCheckbox.setBackground(Color.WHITE);
      

        
        Font font = new Font("Arial", Font.PLAIN, 14); // Fuente Arial, tamaño 16
        noSerializadaCheckbox.setFont(font);
        caracterEspecialCheckbox.setFont(font);
        letrasMayusculasCheckbox.setFont(font);
        letrasMinusculasCheckbox.setFont(font);
        caracterNumericoCheckbox.setFont(font);
        
        
        //checkboxPanel.add(noSerializadaCheckbox);
        checkboxPanel.add(caracterEspecialCheckbox);
        checkboxPanel.add(letrasMayusculasCheckbox);
        checkboxPanel.add(letrasMinusculasCheckbox);
        checkboxPanel.add(caracterNumericoCheckbox);

        rightPanel.add(checkboxPanel, BorderLayout.CENTER);

        // ComboBox para longitud de contraseña (6 a 12 caracteres)
        Integer[] longitudes = {6, 7, 8, 9, 10, 11, 12};
        longitudComboBox = new JComboBox<>(longitudes);
        longitudComboBox.setSelectedIndex(0); // Seleccionar el primer ítem por defecto   
     
     // Crear la etiqueta para la longitud de contraseña
        JLabel lblLongitud = new JLabel("Longitud de contraseña:");
        lblLongitud.setFont(new Font("Arial", Font.PLAIN, 14));

        // Crear el panel que contendrá la etiqueta y el ComboBox
        JPanel longitudPanel = new JPanel();
        longitudPanel.setBackground(Color.WHITE);
        longitudPanel.add(lblLongitud);  // Agregar la etiqueta al panel
        longitudPanel.add(longitudComboBox);  // Agregar el ComboBox al panel

        // Agregar longitudPanel al panel derecho (rightPanel)
        rightPanel.add(longitudPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(rightPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 3, 10, 5));
        bottomPanel.setBounds(570, 500, 360, 30);
        bottomPanel.setBackground(Color.WHITE);
        

        guardarButton = new JButton("Guardar");
        guardarButton.setBackground(Color.decode("#FF4F63"));
        guardarButton.setForeground(Color.WHITE);
        guardarButton.setPreferredSize(new Dimension(200, 200));
        bottomPanel.add(guardarButton);
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
        sugerenciaButton.setForeground(Color.WHITE);
        bottomPanel.add(sugerenciaButton);
        
        regresarButton = new JButton("Regresar");
        regresarButton.setBackground(Color.decode("#FF4F63"));
        regresarButton.setForeground(Color.WHITE);
        bottomPanel.add(regresarButton);
        regresarButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	new Welcome(frame, controller); 
            } 
        }); 
        
        frame.getContentPane().add(bottomPanel);

        frame.getContentPane().setBackground(Color.WHITE);

        frame.setVisible(true);
    }
}
