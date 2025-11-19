package modelo;

import java.time.LocalTime;
import java.util.List;

public class ModeloReserva {
	public String nombre;
    public String dni;
    public String telefono;

    public String fechaEntrada;
    public String fechaSalida;

    public String tipoHabitacion;
    public String regimen;
    public double precioNoche;

    public List<String> extrasSuite;
    public boolean desayuno;
    public boolean parking;
    public boolean spa;
    public LocalTime horaSpa;

    public int descuento;
    public double importeFinal;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}
	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public double getPrecioNoche() {
		return precioNoche;
	}
	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}
	public List<String> getExtrasSuite() {
		return extrasSuite;
	}
	public void setExtrasSuite(List<String> extrasSuite) {
		this.extrasSuite = extrasSuite;
	}
	public boolean isDesayuno() {
		return desayuno;
	}
	public void setDesayuno(boolean desayuno) {
		this.desayuno = desayuno;
	}
	public boolean isParking() {
		return parking;
	}
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	public boolean isSpa() {
		return spa;
	}
	public void setSpa(boolean spa) {
		this.spa = spa;
	}
	public LocalTime getHoraSpa() {
		return horaSpa;
	}
	public void setHoraSpa(LocalTime horaSpa) {
		this.horaSpa = horaSpa;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public double getImporteFinal() {
		return importeFinal;
	}
	public void setImporteFinal(double importeFinal) {
		this.importeFinal = importeFinal;
	}
	public ModeloReserva(String nombre, String dni, String telefono, String fechaEntrada, String fechaSalida,
			String tipoHabitacion, String regimen, double precioNoche, List<String> extrasSuite, boolean desayuno,
			boolean parking, boolean spa, LocalTime horaSpa, int descuento, double importeFinal) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.tipoHabitacion = tipoHabitacion;
		this.regimen = regimen;
		this.precioNoche = precioNoche;
		this.extrasSuite = extrasSuite;
		this.desayuno = desayuno;
		this.parking = parking;
		this.spa = spa;
		this.horaSpa = horaSpa;
		this.descuento = descuento;
		this.importeFinal = importeFinal;
	}
    
    
    
}
