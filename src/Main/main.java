package Main;

import gui.ControladorReserva;
import gui.ModeloReservas;
import gui.Ventana;

public class main {
	public static void main(String[] args) {
        Ventana vista = new Ventana();
        ModeloReservas modelo = new ModeloReservas();

        new ControladorReserva(vista, modelo);

        vista.setVisible(true);
    }
}
