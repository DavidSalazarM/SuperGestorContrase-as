package controller.unbosque.edu.co;
import model.unbosque.edu.co.Password;

public class Main {
	
	public static void main(String[] args) {
		
		 StringBuilder string = new StringBuilder("adhk135@%()QPWO");
		
		 Password password = new Password();
		 password.contraseña(string);
	}

}
