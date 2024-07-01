package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AboutManual {

    public static void showHelpOptions(JFrame frame) {
        String[] options = {"About", "Manual"};
        int choice = JOptionPane.showOptionDialog(frame,
                "Seleccione una opción:",
                "Ayuda",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        // Manejar la selección del usuario
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(frame, "Super Password Max Plus Turbo (v.3.2.3)", "About", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                // Implementar la lógica para abrir el manual PDF
                String pathToManual = "C:\\Users\\Manuel\\Desktop\\SuperGestorContrase-as-master\\Manual de Usuario.pdf";
                openPDFManual(pathToManual);
                break;
            default:
                break;
        }
    }

    // Método para abrir el manual PDF
    private static void openPDFManual(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "El archivo no existe: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Desktop.getDesktop().open(file);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
