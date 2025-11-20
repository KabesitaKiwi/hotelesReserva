package gui;

import base.Reservas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModeloReservas {
	private ArrayList<Reservas> listaReservas;
	
	public ModeloReservas() {listaReservas = new ArrayList<Reservas>();}
	
	public ArrayList<Reservas> obtenerReservas(){
		return listaReservas;
	}
	public void altaReservas( String nombre,String dni,String telefono,String fechaEntrada,String fechaSalida, 
			String tipoHabitacion,String regimen,double precioNoche,List<String> extrasSuite,
			boolean desayuno,boolean parking,boolean spa,int descuento,double importeFinal){
		// Creamos la reserva con los datos recibidos
	    Reservas nueva = new Reservas(nombre, dni, telefono, fechaEntrada, fechaSalida,
	    		tipoHabitacion, regimen,precioNoche, extrasSuite,desayuno, parking, spa, descuento,importeFinal);
	    listaReservas.add(nueva);
	}
	public boolean borrarReservaPorDni(String dni) {
	    for (Reservas r : listaReservas) {
	        if (r.getDni().equalsIgnoreCase(dni)) {
	            listaReservas.remove(r);
	            return true;
	        }
	    }
	    return false;
	}
}
