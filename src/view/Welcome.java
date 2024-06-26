package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Welcome extends JPanel {
	private static final long serialVersionUID = 1L;

	public Welcome(final JFrame frame) {
		frame.getContentPane().removeAll();
		frame.repaint();
        // Frame
		frame.setTitle("Super Password Max Plus Turbo (v.3.2.3)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);
        frame.setLayout(new BorderLayout());

        // Panel for the header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 64, 129)); // Color bloque
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 100)); // Size header
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        // logo
        ImageIcon originalIcon = new ImageIcon("logo.png");
        Image originalImage = originalIcon.getImage();
        // Resize the image
        Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Titulo
        JLabel titleLabel = new JLabel("Super Password Max Plus Turbo™ (v.3.2.3)");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Componentes head
        headerPanel.add(Box.createVerticalStrut(100)); // espacio arriba
        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(10)); // espacio entre logo y titulo
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(20)); // espacio bottom

        // Panel -> buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(200, 450, 200, 450));

        // Buttons
        JButton newPasswordButton = new JButton("Nueva contraseña");
        newPasswordButton.setBackground(new Color(255, 64, 129));
        newPasswordButton.setForeground(Color.WHITE);
        newPasswordButton.setFocusPainted(false);
        newPasswordButton.setFont(new Font("Arial", Font.BOLD, 20));
        newPasswordButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	new EditPassword(frame); 
            } 
        }); 
        
        
        JButton reviewPasswordsButton = new JButton("Revisar Contraseñas");
        reviewPasswordsButton.setBackground(new Color(255, 64, 129));
        reviewPasswordsButton.setForeground(Color.WHITE);
        reviewPasswordsButton.setFocusPainted(false);
        reviewPasswordsButton.setFont(new Font("Arial", Font.BOLD, 20));
        reviewPasswordsButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	new CheckPassword(frame); 
            } 
        }); 


        buttonPanel.add(newPasswordButton);
        buttonPanel.add(reviewPasswordsButton);

        // Panels -> Frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Display
        frame.setVisible(true);
	}
}
