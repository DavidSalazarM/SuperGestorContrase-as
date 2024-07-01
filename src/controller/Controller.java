package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.PasswordEntry;
import model.unbosque.edu.co.PasswordGenerator;
import view.EditPassword;


public class Controller {
	private List<PasswordEntry> passwordEntries;
	
	public Controller() {
		passwordEntries = new ArrayList<PasswordEntry>();
	}

	public List<PasswordEntry> getPasswordEntries() {
		return passwordEntries;
	}

	public void setPasswordEntries(List<PasswordEntry> passwordEntries) {
		this.passwordEntries = passwordEntries;
	}
	

	public void addPasswordEntry(PasswordEntry passwordEntry) {
		this.passwordEntries.add(passwordEntry);
	}
	
	public void updatePasswordEntry(PasswordEntry passwordEntry) {
		// this.passwordEntries.add(passwordEntry);
	}
	
	 public void save() throws IOException {
	      try (FileOutputStream fos = new FileOutputStream("passwords_data");
	           ObjectOutputStream oos = new ObjectOutputStream(fos)) {
	          oos.writeObject(passwordEntries);
	          oos.flush();
	      }
	 }
	 
	 public void load() throws IOException, ClassNotFoundException {
		 File f = new File("passwords_data");
		 if(f.exists() && !f.isDirectory()) { 
	      try (FileInputStream fis = new FileInputStream("passwords_data");
	    		  ObjectInputStream ois = new ObjectInputStream(fis)) {
	    	passwordEntries = (List<PasswordEntry>) ois.readObject();
	      }
		 } else {
		    	passwordEntries = new ArrayList<PasswordEntry>();
		 }
	  }


	public void generatePasswordSuggestion(EditPassword editPasswordScreen) {
		int length = (Integer) editPasswordScreen.getLongitudComboBox().getSelectedItem();
		boolean includeUppercase = editPasswordScreen.getLetrasMayusculasCheckbox().isSelected();
		boolean includeLowercase = editPasswordScreen.getLetrasMinusculasCheckbox().isSelected();
		boolean includeDigits = editPasswordScreen.getCaracterNumericoCheckbox().isSelected();
		boolean includeSpecial = editPasswordScreen.getCaracterEspecialCheckbox().isSelected();

		if (!includeUppercase && !includeLowercase && !includeDigits && !includeSpecial) {
			JOptionPane.showMessageDialog(editPasswordScreen,
					"Por favor seleccione al menos una opción de caracteres para generar la contraseña.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String newPassword = PasswordGenerator.generatePassword(length, includeUppercase, includeLowercase,
				includeDigits, includeSpecial);
		editPasswordScreen.getContraseñaField().setText(newPassword);
	}



	public void configureEditPasswordListeners(EditPassword editPasswordScreen) {
		editPasswordScreen.getSugerenciaButton().addActionListener(e -> {
			generatePasswordSuggestion(editPasswordScreen);
		});

		editPasswordScreen.getGuardarButton().addActionListener(e -> {
			JOptionPane.showMessageDialog(editPasswordScreen.getFrame(), "Funcionalidad de guardar contraseña no implementada.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		});

		// editPasswordScreen.getRegresarButton().addActionListener(e -> {
		// 	showWelcomeScreen();
		// });

		editPasswordScreen.getVerContraseñaButton().addActionListener(e -> {
			if (editPasswordScreen.getVerContraseñaButton().isSelected()) {
				editPasswordScreen.getContraseñaField().setEchoChar((char) 0); // Mostrar contraseña
			} else {
				editPasswordScreen.getContraseñaField().setEchoChar('\u2022'); // Ocultar contraseña
			}
		});
	}

}
