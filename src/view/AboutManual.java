package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
                // Implementar la lógica para abrir el manual PDF (ejemplo simulado)
                String pathToManual = "manual.pdf"; // Ruta al manual PDF
                openPDFManual(pathToManual);
                break;
            default:
                break;
        }
    }

    // Método para abrir el manual PDF (ejemplo simulado)
    private static void openPDFManual(String filePath) {
        // Aquí se debería implementar la lógica para abrir el PDF
        JOptionPane.showMessageDialog(null, "Abriendo el manual...", "Manual", JOptionPane.INFORMATION_MESSAGE);
    }
}
