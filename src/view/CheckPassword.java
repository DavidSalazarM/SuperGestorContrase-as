package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

import controller.Controller;
import model.PasswordEntry;

public class CheckPassword extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller controller;

    public CheckPassword(final JFrame frame, Controller controller) {
        this.controller = controller;
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);
        frame.setLayout(null); 
        frame.setResizable(false);

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, frame.getWidth(), 100);
        headerPanel.setBackground(Color.decode("#FF4F63"));
        headerPanel.setLayout(null); 

        ImageIcon originalIcon = new ImageIcon("logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(540, 25, 50, 50);

        JLabel titleLabel = new JLabel("Revisar Contraseña");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(620, 30, 300, 40); 

        JButton helpButton = new JButton("Help");
        helpButton.setBounds(1380, 54, 100, 40); 
        helpButton.setBackground(Color.decode("#FF4F63"));
        helpButton.setForeground(Color.WHITE);
        helpButton.setFocusPainted(false);
        helpButton.setFont(new Font("Arial", Font.BOLD, 20));
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutManual.showHelpOptions(frame);
            }
        });

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        headerPanel.add(helpButton);
        frame.add(headerPanel);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBounds(0, 99, 1485, 500); // Posición y tamaño del panel de la tabla
        tablePanel.setBackground(Color.WHITE);

        String[] columnNames = {"Sitios", "Usuarios", "Contraseña", "Actualizar", "Copiar"};

        Object[][] data = new Object[controller.getPasswordEntries().size()][5];
        for (int i = 0; i < controller.getPasswordEntries().size(); i++) {
            data[i][0] = controller.getPasswordEntries().get(i).getSite();
            data[i][1] = controller.getPasswordEntries().get(i).getUsername();
            data[i][2] = "****"; 
            data[i][3] = "Actualizar";
            data[i][4] = "Copiar";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4;
            }
        };

        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setGridColor(Color.decode("#F68783")); 
        table.setShowVerticalLines(false);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.decode("#FF4F63"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(255, 128, 171));
        table.setSelectionForeground(Color.WHITE);

        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer("Actualizar"));
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), frame, controller, "Actualizar"));

        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer("Copiar"));
        table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox(), frame, controller, "Copiar"));

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(tablePanel);

        JButton backButton = new JButton("Regresar");
        backButton.setBounds(500, 600, 500, 50);
        backButton.setBackground(Color.decode("#FF4F63"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Welcome(frame, controller);
            }
        });

        frame.add(backButton);

        frame.setVisible(true);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    private String buttonType;

    public ButtonRenderer(String buttonType) {
        this.buttonType = buttonType;
        setOpaque(true);
        setBackground(Color.decode("#FF4F63"));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 16));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? buttonType : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private String site;
    private String username;
    private String password;
    private String buttonType;
    private Controller controller;

    public ButtonEditor(JCheckBox checkBox, JFrame frame, Controller controller, String buttonType) {
        super(checkBox);
        this.buttonType = buttonType;
        this.controller = controller;
        button = new JButton(buttonType);
        button.setOpaque(true);
        button.setBackground(Color.decode("#FF4F63"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(e -> {
            if ("Actualizar".equals(buttonType)) {
                fireEditingStopped();
                PasswordEntry entry = controller.getPasswordEntries().stream().filter(passwordEntry -> site.equals(passwordEntry.getSite()))
                    .findAny()
                    .orElse(null);
                EditPassword editPasswordScreem = new EditPassword(frame, controller, entry);
                controller.configureEditPasswordListeners(editPasswordScreem);
            } else if ("Copiar".equals(buttonType)) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection selection = new StringSelection(password);
                clipboard.setContents(selection, null);
                JOptionPane.showMessageDialog(button, "Contraseña copiada al portapapeles.");
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        site = (String) model.getValueAt(row, 0);
        username = (String) model.getValueAt(row, 1);
        password = controller.getPasswordEntries().stream().filter(passwordEntry -> site.equals(passwordEntry.getSite()))
            .findAny()
            .map(PasswordEntry::getPassword)
            .orElse("");
        label = (value == null) ? buttonType : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}