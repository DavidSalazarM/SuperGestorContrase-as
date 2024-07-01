package model;

import java.io.Serializable;
import java.util.List;

import javax.swing.JFrame;

import controller.Controller;

public class PasswordEntry implements Serializable {
	private String site;
	private String username;
	private String password;
	private List<String> oldPasswords;
	
	public PasswordEntry(){}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getOldPasswords() {
		return oldPasswords;
	}

	public void setOldPasswords(List<String> oldPasswords) {
		this.oldPasswords = oldPasswords;
	}
}
