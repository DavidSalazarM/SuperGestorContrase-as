package model.unbosque.edu.co;
import java.util.concurrent.ThreadLocalRandom;

public class RamdonPasword {
	public StringBuilder contraseñaAleatoria(){
		StringBuilder clave = new StringBuilder();
        int[][] ranges = {
            {33, 47}, 
            {48, 57}, 
            {65, 90},  
            {97, 122}  
        };

        for (int i = 0; i < 16; i++) {
            int[] range = ranges[i % ranges.length];
            clave.append((char) ThreadLocalRandom.current().nextInt(range[0], range[1] + 1));
        }

        return clave;
	}
}
