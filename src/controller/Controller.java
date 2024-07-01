package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.PasswordEntry;


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
}
