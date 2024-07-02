package model.unbosque.edu.co;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
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
	
	public String getMinNumeroCaracteresNumericos() {
		return Byte.toString(minNumeroCaracteresNumericos);
	}

	public void setMinNumeroCaracteresNumericos(byte minNumeroCaracteresNumericos) {
		this.minNumeroCaracteresNumericos = minNumeroCaracteresNumericos;
	}

	public String getMinNumeroCaracteresMinusculos() {
		return Byte.toString(minNumeroCaracteresMinusculos);
		
	}

	public void setMinNumeroCaracteresMinusculos(byte minNumeroCaracteresMinusculos) {
		this.minNumeroCaracteresMinusculos = minNumeroCaracteresMinusculos;
	}

	public String getMinNumeroCaracteresMayusculas() {
		return Byte.toString(minNumeroCaracteresMayusculas);
		
	}

	public void setMinNumeroCaracteresMayusculas(byte minNumeroCaracteresMayusculas) {
		this.minNumeroCaracteresMayusculas = minNumeroCaracteresMayusculas;
	}

	public String getMinNumeroCaracteresEspeciales() {
		return  Byte.toString(minNumeroCaracteresEspeciales);
	
	}

	public void setMinNumeroCaracteresEspeciales(byte minNumeroCaracteresEspeciales) {
		this.minNumeroCaracteresEspeciales = minNumeroCaracteresEspeciales;
	}

	public String getMinNumeroCaracteres() {
		return Byte.toString(minNumeroCaracteres);
	}

	public void setMinNumeroCaracteres(byte minNumeroCaracteres) {
		this.minNumeroCaracteres = minNumeroCaracteres;
	}

	private byte minNumeroCaracteresNumericos=1;
	private byte minNumeroCaracteresMinusculos = 1;
	private byte minNumeroCaracteresMayusculas = 1;
	private byte minNumeroCaracteresEspeciales=1;
	private byte minNumeroCaracteres=8;
	
	public String contraseña(StringBuilder password){
		return caracteresMinimos(password);
		
	}
	
    public String PasswordValido() {
        if (CaracteresContadosValido()){
        	if(caracteresContadosTotales>=minNumeroCaracteres){
        		if(seriesValido()){
        			 	limpiador();
        	            return "Correcto";
        		}else {
        			 limpiador();
        			return "Fallo-Se encontro una serie";
        		}
        	}else{
        		 limpiador();
        		  return "Fallo-No cumple numero de caracteres totales";
        	}
           
        }else {
        limpiador();
        return "Fallo-No cumple numero de caracteres";
    }}

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

	public String procesarPasswordCaracteres(StringBuilder password){
		  String passwordStr = password.toString();
		 
		 guardarMinusculas(passwordStr);
		 guardarMayusculas(passwordStr);
		 guardarNumeros(passwordStr);
		 guardarEspeciales(passwordStr);
		return PasswordValido();
	}
	
	private void guardarEspeciales(String passwordStr) {
		 Pattern patterEspeciales = Pattern.compile("[^\\d|\\p{Lower}|\\p{Upper}]+");
		 Matcher matcherEspeciales = patterEspeciales.matcher(passwordStr);
		 while (matcherEspeciales.find()) {
			  String group = matcherEspeciales.group();
			 caracteresEspeciales.add(group);
		 }
		
	}

	private void guardarNumeros(String passwordStr) {
		 Pattern patterNumeros = Pattern.compile("\\d+");
		 Matcher matcherNumeros = patterNumeros.matcher(passwordStr);
		 while (matcherNumeros.find()) {
			 String group = matcherNumeros.group();
			 numeros.add(group);
		
		 }
		
	}

	
	public String caracteresMinimos(StringBuilder password){
		Pattern compiledPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).*$");
        Matcher matcher = compiledPattern.matcher(password.toString());
        if( matcher.matches()){
        	return procesarPasswordCaracteres(password);
        }else{return "FALLO-Faltan Caracteres";}
	}
	
	private void guardarMayusculas(String passwordStr) {
		 Pattern patterMayusculas = Pattern.compile("\\p{Upper}+");
		 Matcher matcherMayusculas = patterMayusculas.matcher(passwordStr);
		 while (matcherMayusculas.find()) {
			 String group = matcherMayusculas.group();
			 letrasMayusculas.add(group);
	
		 }
		 
		
	}

	private void guardarMinusculas(String passwordStr) {
		 Pattern patterMinusculas= Pattern.compile("\\p{Lower}+");
		 Matcher matcherMinusculas = patterMinusculas.matcher(passwordStr);
		 while (matcherMinusculas.find()) {
			 String group = matcherMinusculas.group();
			 letrasMinusculas.add(group);
		 }
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