package model.unbosque.edu.co;
import java.util.ArrayList;
import java.util.HashMap;

public class Password {
	private StringBuilder cadena = new StringBuilder(" ");
	private StringBuilder SERIE_NUMERICA = new StringBuilder("0123456789");
	private StringBuilder SERIE_LETRAS_TECLADO = new StringBuilder("qwertyuiopasdfghjklzxcvbnm");
	private StringBuilder SERIE_LETRAS_TECLADO_MAYUSCULAS = new StringBuilder("QWERTYUIOPASDFGHJKLZXCVBNM");
	private StringBuilder ABECEDARIO_MAYUSCULAS = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	private StringBuilder ABECEDARIO = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
	private ArrayList<String> letrasMinusculas = new ArrayList<>();
	private ArrayList<String> letrasMayusculas = new ArrayList<>();
	private ArrayList<String> caracteresEspeciales = new ArrayList<>();
	private ArrayList<String> numeros = new ArrayList<>();
	private int caracteresContadosTotales=0;
	private byte minNumeroCaracteresNumericos=2;
	private byte minNumeroCaracteresMinusculos = 2;
	private byte minNumeroCaracteresMayusculas = 2;
	private byte minNumeroCaracteresEspeciales=2;
	private byte minNumeroCaracteres=8;
	
	public void contraseña(StringBuilder password){
		if (procesarPasswordCaracteres(password)){
			System.out.println("su contraseña es valida :)");
		}else {System.out.println("no es tu culpa pero fallaste en algo :'c");}
	}
	
    public boolean PasswordValido() {
        if ((CaracteresContadosValido()&&caracteresContadosTotales>=minNumeroCaracteres) && seriesValido()) {
            limpiador();
            return true;
        }
        limpiador();
        return false;
    }

    private boolean CaracteresContadosValido() {
        return contadorCaracteres(minNumeroCaracteresNumericos, new ArrayList<>(numeros), 0, new HashMap<>()) &&
               contadorCaracteres(minNumeroCaracteresMinusculos, new ArrayList<>(letrasMinusculas), 0, new HashMap<>()) &&
               contadorCaracteres(minNumeroCaracteresMayusculas, new ArrayList<>(letrasMayusculas), 0, new HashMap<>()) &&
               contadorCaracteres(minNumeroCaracteresEspeciales, new ArrayList<>(caracteresEspeciales), 0, new HashMap<>());
    }

    private boolean seriesValido() {
        return validarPasswordSeries(new ArrayList<>(letrasMinusculas), new ArrayList<>(), ABECEDARIO) &&
               validarPasswordSeries(new ArrayList<>(letrasMinusculas), new ArrayList<>(), SERIE_LETRAS_TECLADO) &&
               validarPasswordSeries(new ArrayList<>(letrasMayusculas), new ArrayList<>(), ABECEDARIO_MAYUSCULAS) &&
               validarPasswordSeries(new ArrayList<>(letrasMayusculas), new ArrayList<>(), SERIE_LETRAS_TECLADO_MAYUSCULAS) &&
               validarPasswordSeries(new ArrayList<>(numeros), new ArrayList<>(), SERIE_NUMERICA);
    }
	
	public void limpiador(){
		this.letrasMinusculas.clear();
		this.letrasMayusculas.clear();
		this.caracteresEspeciales.clear();
		this.numeros.clear();
		this.caracteresContadosTotales=0;
	}
	
	public boolean procesarPasswordCaracteres(StringBuilder password){
	    if (password.isEmpty()) {
	    	guradarString(cadena);
	    	caracteresEspeciales.remove(0);
	        return PasswordValido();
	    }
	    char caracter = password.charAt(0);  
	    char ultimoCaracterGuardado = cadena.charAt(cadena.length()-1); 
		if (Character.isLowerCase(caracter)){
			if(!Character.isLowerCase(ultimoCaracterGuardado)){
				guradarString(cadena);
				cadena.setLength(0);
			}
			cadena.append(caracter);
		}else if (Character.isUpperCase(caracter)){
			if(!Character.isUpperCase(ultimoCaracterGuardado)){
				guradarString(cadena);
				cadena.setLength(0);
			}
			cadena.append(caracter);
		}else if(Character.isDigit(caracter)){
			if(!Character.isDigit(ultimoCaracterGuardado)){
				guradarString(cadena);
				cadena.setLength(0);
			}
			cadena.append(caracter);
		}else if (!Character.isLetterOrDigit(caracter)){
			if(Character.isLetterOrDigit(ultimoCaracterGuardado)){
				guradarString(cadena);
				cadena.setLength(0);
			}
			cadena.append(caracter);
		}
		return procesarPasswordCaracteres(new StringBuilder(password.substring(1)));
	}

	private void guradarString(StringBuilder cadena) {
		char caracter = cadena.charAt(0); 
		if (Character.isLowerCase(caracter)){
			this.letrasMinusculas.add(cadena.toString());
		}else if (Character.isUpperCase(caracter)){
			letrasMayusculas.add(cadena.toString());
		}else if(Character.isDigit(caracter)){
			numeros.add(cadena.toString());
		}else{caracteresEspeciales.add(cadena.toString());}
	}
	
	public boolean validarPasswordSeries(ArrayList<String> cadena, ArrayList<String> resultadosConocidosNumeros, StringBuilder tipoCaracter) {
		if (cadena.isEmpty()) {
	        return true;
	    }
	    String serieNumerosContraseña = cadena.get(0);
	    cadena.remove(0);
	    if (resultadosConocidosNumeros.contains(serieNumerosContraseña)) {
	        return validarPasswordSeries(cadena, resultadosConocidosNumeros,tipoCaracter);
	    }
	    else if (serieNumerosContraseña.length() < 3) {
	        resultadosConocidosNumeros.add(serieNumerosContraseña);
	        return validarPasswordSeries(cadena, resultadosConocidosNumeros,tipoCaracter);
	    }
	    else if (serieNumerosContraseña.length() > 3 && (validarCadena(serieNumerosContraseña, resultadosConocidosNumeros,tipoCaracter))) {
	    	 return false;
	    }
	    else if (tipoCaracter.toString().contains(serieNumerosContraseña) || tipoCaracter.reverse().toString().contains(serieNumerosContraseña)) {
	        return false;
	    }else {
	    resultadosConocidosNumeros.add(serieNumerosContraseña);
	    return validarPasswordSeries(cadena, resultadosConocidosNumeros,tipoCaracter);
	    }
	}
	private boolean validarCadena(String serieNumeros,ArrayList<String> resultadosConocidosNumeros,StringBuilder tipoCaracter) {
		if(serieNumeros.length()<= 2) {
			resultadosConocidosNumeros.add(serieNumeros);
			return false;
		}
		String posibleSerie = serieNumeros.substring(0, Math.min(serieNumeros.length(), 3));
		if (resultadosConocidosNumeros.contains(posibleSerie)){
			return validarCadena(serieNumeros.substring(1),resultadosConocidosNumeros,tipoCaracter);
			
		}else if(tipoCaracter.toString().contains(posibleSerie)|| tipoCaracter.reverse().toString().contains(posibleSerie)){
			return true;
			
		}else{
			resultadosConocidosNumeros.add(posibleSerie);
			return validarCadena(serieNumeros.substring(1),resultadosConocidosNumeros,tipoCaracter);
		}
	}
	
	public boolean contadorCaracteres(byte minimoCaracteres,
			ArrayList<String> caracteresContraseña,
			int caracteresContados,
			HashMap<String, Integer> resultadosConocidosNumeros){
		
		if(caracteresContraseña.isEmpty()){
			if(caracteresContados>= minimoCaracteres){
				caracteresContadosTotales+=caracteresContados;
				return true;
			}else {
				return false;
			}
		}
	    String lineaCaracteres = caracteresContraseña.get(0);
	    caracteresContraseña.remove(0);
	    if(resultadosConocidosNumeros.containsKey(lineaCaracteres)){
	    	caracteresContados+=resultadosConocidosNumeros.get(lineaCaracteres);
	    	return contadorCaracteres(minimoCaracteres,caracteresContraseña,caracteresContados,resultadosConocidosNumeros);
	    }else {
	    	caracteresContados+=lineaCaracteres.length();
	    	resultadosConocidosNumeros.put(lineaCaracteres, lineaCaracteres.length());
	    	return contadorCaracteres(minimoCaracteres,caracteresContraseña,caracteresContados,resultadosConocidosNumeros);
	    }
	}
}