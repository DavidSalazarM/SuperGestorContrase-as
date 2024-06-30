package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckPassword extends JPanel {
    private static final long serialVersionUID = 1L;

    public CheckPassword(final JFrame frame) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#FF4F63"));
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 100));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon originalIcon = new ImageIcon("logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Título
        JLabel titleLabel = new JLabel("Revisar Contraseña");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalStrut(100));
        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(20));

        String[] columnNames = {"Sitios", "Usuarios", "Contraseña", "Acciones"};
        Object[][] data = {
                {"https://www.google.com", "chopenawer@gmail.com", "M0RZaT_nbdsHGS", "Actualizar"},
                {"https://www.Anschluss.com", "Kurt_Meyer01", "K@1s3r_1938", "Actualizar"},
                {"https://blog.hubspot.com", "FakeName", "22xx/M@gyver/xx44", "Actualizar"},
                {"https://www.youtube.com", "Daxos@hotmail.com", "28527500-M0l0t0x$", "Actualizar "}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setGridColor(Color.decode("#F68783")); // Color de la línea de cuadrícula
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.decode("#FF4F63"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(255, 128, 171));
        table.setSelectionForeground(Color.WHITE);

        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), frame)); // Pasar frame

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JButton backButton = new JButton("Regresar");
        backButton.setBackground(Color.decode("#FF4F63"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Welcome(frame);
            }
        });

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(backButton, BorderLayout.SOUTH);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Add Help button
        addHelpButton(frame);

        frame.setVisible(true);
    }

    private void addHelpButton(JFrame frame) {
    	JButton helpButton = new JButton("Help");
    	helpButton.setBackground(Color.decode("#FF4F63"));
    	helpButton.setForeground(Color.WHITE);
    	helpButton.setFocusPainted(false);
    	helpButton.setFont(new Font("Arial", Font.BOLD, 20));
    	helpButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        AboutManual.showHelpOptions(frame);
    	    }
    	});

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(helpButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
        setBackground(Color.decode("#FF4F63"));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 16));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Actualizar" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox, JFrame frame) {
        super(checkBox);
        button = new JButton("Actualizar");
        button.setOpaque(true);
        button.setBackground(Color.decode("#FF4F63"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(e -> {
            fireEditingStopped();
            // Abrir la ventana UpdatePassword
            new UpdatePassword(frame); // Pasar el JFrame al constructor de UpdatePassword
        });
        
        JButton helpButton = new JButton("Help");
        helpButton.setBackground(Color.decode("#FF4F63"));
        helpButton.setForeground(Color.WHITE);
        helpButton.setFocusPainted(false);
        helpButton.setFont(new Font("Arial", Font.BOLD, 20));
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutManual.showHelpOptions(frame);
            }
        });
    }
    
    

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Actualizar" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
