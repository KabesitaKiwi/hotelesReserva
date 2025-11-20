package Util;

public class utilidades {
	
	public static boolean dniValido(String dni) {
	    return dni.matches("^[0-9]{8}[A-Z]$");
	}
	
	public static boolean telefonoValido(String tel) {
	    return tel.matches("^[0-9]{9}$");
	}

	public static boolean fechaValida(String fecha) {
	    return fecha.matches("^\\d{2}/\\d{2}/\\d{4}$");
	}

	
}
