package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPassword extends JPanel {
	private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JTextField sitioField;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JTextArea requerimientosArea;
    private JButton guardarButton;
    private JButton sugerenciaButton;
    private JToggleButton verContraseñaButton; // Botón para alternar la visibilidad de la contraseña

    public EditPassword(JFrame frame) {
    	this.frame = frame;
        initialize();
    }

    private void initialize() {
		frame.getContentPane().removeAll();
		frame.repaint();
        frame.setTitle("Guardar nueva contraseña");
        frame.setBounds(600, 200, 600, 400); // Reducido la altura para acomodar mejor los componentes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("<html><div style='text-align: center;'>Guardar nueva<br>contraseña<br></div></html>");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 10, 564, 80); // Ajustado el ancho para centrarlo mejor
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(Color.decode("#FF4F63")); // Color de fondo #FF0065
        lblTitulo.setForeground(Color.WHITE); // Letras blancas
        
        frame.getContentPane().add(lblTitulo);

        // Panel izquierdo para Sitio, Usuario y Contraseña
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(10, 100, 280, 150); // Ajustado el tamaño y posición del panel izquierdo
        leftPanel.setBackground(Color.WHITE); // Fondo blanco

        JLabel lblSitio = new JLabel("SITIO");
        lblSitio.setBounds(80, 0, 80, 25);
        lblSitio.setForeground(Color.decode("#808080")); // Letras gris oscuro
        leftPanel.add(lblSitio);

        sitioField = new JTextField();
        sitioField.setBounds(80, 20, 170, 25);
        sitioField.setBackground(Color.decode("#ECECEC")); // Fondo gris claro
        sitioField.setForeground(Color.decode("#4D4C4C")); // Letras gris oscuro
        leftPanel.add(sitioField);
        sitioField.setColumns(10);

        JLabel lblUsuario = new JLabel("USUARIO");
        lblUsuario.setBounds(80, 50, 80, 25);
        lblUsuario.setForeground(Color.decode("#808080")); // Letras gris oscuro
        leftPanel.add(lblUsuario);

        usuarioField = new JTextField();
        usuarioField.setBounds(80, 70, 170, 25);
        usuarioField.setBackground(Color.decode("#ECECEC")); // Fondo gris claro
        usuarioField.setForeground(Color.decode("#4D4C4C")); // Letras gris oscuro
        leftPanel.add(usuarioField);
        usuarioField.setColumns(10);

        JLabel lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setBounds(80, 100, 100, 25);
        lblContraseña.setForeground(Color.decode("#808080")); // Letras gris oscuro
        leftPanel.add(lblContraseña);

        contraseñaField = new JPasswordField();
        contraseñaField.setBounds(80, 120, 170, 25);
        contraseñaField.setBackground(Color.decode("#ECECEC")); // Fondo gris claro
        contraseñaField.setForeground(Color.decode("#4D4C4C")); // Letras gris oscuro
        leftPanel.add(contraseñaField);

        // Botón para alternar visibilidad de la contraseña
        verContraseñaButton = new JToggleButton("...");
        verContraseñaButton.setBounds(252, 120, 20, 24);
        verContraseñaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (verContraseñaButton.isSelected()) {
                    // Mostrar la contraseña como texto plano
                    contraseñaField.setEchoChar((char) 0); // Configura el echo char como 0 (sin ocultar)
                } else {
                    // Ocultar la contraseña
                    contraseñaField.setEchoChar('\u2022'); // Vuelve a establecer el echo char (oculta la contraseña)
                }
            }
        });
        leftPanel.add(verContraseñaButton);

        frame.getContentPane().add(leftPanel);

        // Panel derecho para Requerimientos contraseña y otro requerimiento
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBounds(300, 120, 264, 100); // Ajustado el tamaño y posición del panel derecho
        rightPanel.setBackground(Color.WHITE); // Fondo blanco

        JLabel lblRequerimientos = new JLabel("Requerimientos contraseña:");
        lblRequerimientos.setHorizontalAlignment(SwingConstants.LEFT);
        rightPanel.add(lblRequerimientos, BorderLayout.NORTH);

        requerimientosArea = new JTextArea();
        requerimientosArea.setEditable(false);
        requerimientosArea.setText("*No puede ser serializada (ej:\"123\",\"abc\",\"qwe\")\n"
                + "*Al menos un carácter especial (ej: \"@\",\"$\",\"<\")\n"
                + "*Al menos una letra mayúscula y minúscula\n"
                + "*Al menos un carácter numérico\n"
                + "*Mínimo 6 caracteres");
        requerimientosArea.setOpaque(false); // Hace que el área de texto sea transparente
        requerimientosArea.setForeground(Color.decode("#000000")); // Letras negro
        rightPanel.add(requerimientosArea, BorderLayout.CENTER);
        frame.getContentPane().add(rightPanel);

        // Botones en la parte sur (abajo)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 10, 0)); // GridLayout para dos botones con espacio entre ellos
        bottomPanel.setBounds(95, 270, 400, 30); // Ajustado el tamaño y posición del panel de botones
        bottomPanel.setBackground(Color.WHITE); // Fondo blanco

        guardarButton = new JButton("Guardar");
        guardarButton.setBackground(Color.decode("#FF4F63")); // Color de fondo #FF0065
        guardarButton.setForeground(Color.WHITE); // Letras blancas
        bottomPanel.add(guardarButton);

        sugerenciaButton = new JButton("Sugerencia Contraseña");
        sugerenciaButton.setBackground(Color.decode("#FF4F63")); // Color de fondo #FF0065
        sugerenciaButton.setForeground(Color.WHITE); // Letras blancas
        bottomPanel.add(sugerenciaButton);

        frame.getContentPane().add(bottomPanel);

        // Establecer el color de fondo para todo el contenido del frame
        frame.getContentPane().setBackground(Color.WHITE);

        frame.setVisible(true);
    }
}