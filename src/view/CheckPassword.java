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

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 64, 129)); // Color bloque
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 100)); // Size header
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon originalIcon = new ImageIcon("logo.png");
        Image originalImage = originalIcon.getImage();
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

        headerPanel.add(Box.createVerticalStrut(100));
        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(10)); 
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(20)); 

        String[] columnNames = {"Sitios", "Usuarios", "Contraseña", "Acciones"};
        Object[][] data = {
            {"https://www.google.com", "chopenawer@gmail.com", "M0RZaT_nbdsHGS", "Del/Edit"},
            {"https://www.Anschluss.com", "Kurt_Meyer01", "K@1s3r_1938", "Del/Edit"},
            {"https://blog.hubspot.com", "FakeName", "22xx/M@gyver/xx44", "Del/Edit"},
            {"https://www.youtube.com", "Daxos@hotmail.com", "28527500-M0l0t0x$", "Del/Edit"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; 
            }
        };


        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setGridColor(new Color(224, 224, 224));
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(255, 64, 129));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(255, 128, 171));
        table.setSelectionForeground(Color.WHITE);

        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);


        JButton backButton = new JButton("Regresar");
        backButton.setBackground(new Color(255, 64, 129));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	new Welcome(frame); 
            } 
        }); 


        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(backButton, BorderLayout.SOUTH);


        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);


        frame.setVisible(true);
    }
}


class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
        setBackground(new Color(255, 64, 129));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 16));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Del/Edit" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.setBackground(new Color(255, 64, 129));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Del/Edit" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Opción seleccionada.");
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
