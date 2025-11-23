package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Util.utilidades;
import base.Reservas;

public class ControladorReserva implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private ModeloReservas modelo;
    private double precioBaseHabitacion = 0;
    private double precioRegimen = 0;
    private double precioServiciosExtras = 0;
    private double precioChecks = 0;
    private double descuento = 0;
    private double precioFinal = 0;
    private DefaultTableModel tablaModel;
    private int filaSeleccionada = -1;

    public ControladorReserva(Ventana vista, ModeloReservas modelo) {
        this.vista = vista;
        this.modelo = modelo;

        tablaModel = new DefaultTableModel(
                new Object[]{"Nombre", "DNI", "Teléfono", "Entrada", "Salida", "Hab.", "Regimen","Descuento", "Precio Final"},
                0
        );
        vista.tablaReserva.setModel(tablaModel);

        addActionListener(this);
        addListSelectionListener(this);
        vista.listaServicios.addListSelectionListener(this);
    }

    private void addActionListener(ActionListener listener) {
        vista.botonNuevaReserva.addActionListener(listener);
        vista.botonGuardarReserva.addActionListener(listener);
        vista.botonCancelarReserva.addActionListener(listener);

        vista.comboHabitacion.addActionListener(listener);

        vista.alojamiento.addActionListener(listener);
        vista.mediaPen.addActionListener(listener);
        vista.pensionEntera.addActionListener(listener);

        vista.chekDesayuno.addActionListener(listener);
        vista.chekParking.addActionListener(listener);
        vista.chekSpa.addActionListener(listener);
        
        vista.tablaReserva.getSelectionModel().addListSelectionListener(this);
        

        vista.campoHorario.addChangeListener(e -> listener.actionPerformed(
                new ActionEvent(vista.campoHorario, ActionEvent.ACTION_PERFORMED, "horario")
        ));

        vista.sliderDescuento.addChangeListener(e ->
        listener.actionPerformed(
            new ActionEvent(vista.sliderDescuento, ActionEvent.ACTION_PERFORMED, "Descuento")
        )
    );

        vista.listaServicios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listener.actionPerformed(
                    new ActionEvent(vista.listaServicios, ActionEvent.ACTION_PERFORMED, "ListaSuite")
                );
            }
        });
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listaServicios.addListSelectionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {

            case "NuevaReserva":
            	if (hayCamposVacios()) {
                    JOptionPane.showMessageDialog(null,
                            "Debes rellenar todos los campos antes de guardar.",
                            "Campos incompletos",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
            	

            	
            	if (!utilidades.dniValido(vista.campoDNI.getText().trim())) {
            	    JOptionPane.showMessageDialog(null, "DNI incorrecto (Ej: 12345678A)");
            	    break;
            	}

            	if (!utilidades.telefonoValido(vista.campoTelefono.getText().trim())) {
            	    JOptionPane.showMessageDialog(null, "Teléfono incorrecto (9 dígitos)");
            	    break;
            	}

            	if (!utilidades.fechaValida(vista.campoFechaEntrada.getText().trim()) ||
            	    !utilidades.fechaValida(vista.campoFechaSalida.getText().trim())) {
            	    JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy");
            	    break;
            	}
            	
            	int dias = calcularDias(
                        vista.campoFechaEntrada.getText().trim(),
                        vista.campoFechaSalida.getText().trim()
                );
            	
                if (dias <= 0) dias = 1;
                
                // precio por noche (con extras y descuento)
                double precioPorNoche = calcularPrecioPorNoche();

                // precio final para tabla
                double precioTotal = precioPorNoche * dias;
            	
            	modelo.altaReservas(
                        vista.campoNombre.getText().trim(),
                        vista.campoDNI.getText().trim(),
                        vista.campoTelefono.getText().trim(),
                        vista.campoFechaEntrada.getText().trim(),
                        vista.campoFechaSalida.getText().trim(),
                        vista.comboHabitacion.getSelectedItem().toString(),
                        obtenerRegimenSeleccionado(),
                        precioBaseHabitacion,
                        vista.listaServicios.getSelectedValuesList(),
                        vista.chekDesayuno.isSelected(),
                        vista.chekParking.isSelected(),
                        vista.chekSpa.isSelected(),
                        vista.sliderDescuento.getValue(),
                        precioTotal
                );
            	
            	 refrescarTabla();
            	 JOptionPane.showMessageDialog(null, "Reserva guardada correctamente.");
            	 limpiarCampos();
            	 break;

            case "GuardarReserva":
                
                if (hayCamposVacios()) {
                    JOptionPane.showMessageDialog(null,
                            "Debes rellenar todos los campos antes de guardar.",
                            "Campos incompletos",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
                
                if (!utilidades.dniValido(vista.campoDNI.getText().trim())) {
            	    JOptionPane.showMessageDialog(null, "DNI incorrecto (Ej: 12345678A)");
            	    break;
            	}

            	if (!utilidades.telefonoValido(vista.campoTelefono.getText().trim())) {
            	    JOptionPane.showMessageDialog(null, "Teléfono incorrecto (9 dígitos)");
            	    break;
            	}

            	if (!utilidades.fechaValida(vista.campoFechaEntrada.getText().trim()) ||
            	    !utilidades.fechaValida(vista.campoFechaSalida.getText().trim())) {
            	    JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy");
            	    break;
            	}

                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(null, "Selecciona una reserva para actualizar.");
                    break;
                }

                Reservas rerserva = modelo.obtenerReservas().get(filaSeleccionada);

                rerserva.setNombre(vista.campoNombre.getText());
                rerserva.setDni(vista.campoDNI.getText());
                rerserva.setTelefono(vista.campoTelefono.getText());
                rerserva.setFechaEntrada(vista.campoFechaEntrada.getText());
                rerserva.setFechaSalida(vista.campoFechaSalida.getText());
                rerserva.setTipoHabitacion(vista.comboHabitacion.getSelectedItem().toString());
                rerserva.setRegimen(obtenerRegimenSeleccionado());
                rerserva.setDesayuno(vista.chekDesayuno.isSelected());
                rerserva.setParking(vista.chekParking.isSelected());
                rerserva.setSpa(vista.chekSpa.isSelected());
                rerserva.setExtrasSuite(vista.listaServicios.getSelectedValuesList());
                rerserva.setDescuento(vista.sliderDescuento.getValue());

                
                int diasActualizar = calcularDias(rerserva.getFechaEntrada().trim(), rerserva.getFechaSalida().trim());
                if (diasActualizar <= 0) diasActualizar = 1;

                double precioNoche = calcularPrecioPorNoche();
                rerserva.setImporteFinal(precioNoche * diasActualizar);

                refrescarTabla();
                JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
                break;
                
            case "CancelarReserva":
            	 int fila = vista.tablaReserva.getSelectedRow();

            	    if (fila == -1) {
            	        JOptionPane.showMessageDialog(null, "Selecciona una reserva para cancelar.");
            	        break;
            	    }

            	    String dniSeleccionado = tablaModel.getValueAt(fila, 1).toString();

            	    if (modelo.borrarReservaPorDni(dniSeleccionado)) {
            	        JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
            	    } else {
            	        JOptionPane.showMessageDialog(null, "Error al eliminar la reserva.");
            	    }

            	    refrescarTabla();
            	    limpiarCampos();
            	    break;

            case "HabitacionElegida":
            	String hab = vista.comboHabitacion.getSelectedItem().toString();

                switch (hab) {
                    case "Individual":
                    case "Doble":
                        precioBaseHabitacion = (hab.equals("Individual")) ? 50 : 80;

                        // Desactivar servicios suite
                        vista.listaServicios.clearSelection();
                        vista.listaServicios.setEnabled(false);
                        break;

                    case "Suite":
                        precioBaseHabitacion = 120;

                        // Activar servicios suite
                        vista.listaServicios.setEnabled(true);
                        break;
                }

                actualizarPrecioFinal();
                break;

            case "Alojamiento":
                precioRegimen = 0;
                actualizarPrecioFinal();
                break;

            case "MediaPension":
                precioRegimen = 35;
                actualizarPrecioFinal();
                break;

            case "PensionCompleta":
                precioRegimen = 65;
                actualizarPrecioFinal();
                break;

            case "CheckServicios":
                precioChecks = 0;

                if (vista.chekDesayuno.isSelected()) precioChecks += 20;
                if (vista.chekParking.isSelected()) precioChecks += 30;
                if (vista.chekSpa.isSelected()) precioChecks += 50;

                actualizarPrecioFinal();
                break;

            case "ListaSuite":
                precioServiciosExtras = 0;

                List<String> seleccion = vista.listaServicios.getSelectedValuesList();

                for (String s : seleccion) {
                    if (s.equals("Jacuzzi")) precioServiciosExtras += 40;
                    if (s.equals("Cama King")) precioServiciosExtras += 60;
                    if (s.equals("Sala VIP")) precioServiciosExtras += 100;
                }

                actualizarPrecioFinal();
                break;

            case "Descuento":
            	descuento = vista.sliderDescuento.getValue();
                vista.descuento.setText("Descuento (%) " + descuento + "%");
                actualizarPrecioFinal();
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub
    	if (!e.getValueIsAdjusting()) {

            filaSeleccionada = vista.tablaReserva.getSelectedRow();

            if (filaSeleccionada < 0) return;

            // Cargar la reserva seleccionada
            Reservas r = modelo.obtenerReservas().get(filaSeleccionada);

            vista.campoNombre.setText(r.getNombre());
            vista.campoDNI.setText(r.getDni());
            vista.campoTelefono.setText(r.getTelefono());
            vista.campoFechaEntrada.setText(r.getFechaEntrada().trim());
            vista.campoFechaSalida.setText(r.getFechaSalida().trim());

            vista.comboHabitacion.setSelectedItem(r.getTipoHabitacion());

            // Seleccionar régimen
            switch (r.getRegimen()) {
                case "Alojamiento": vista.alojamiento.setSelected(true); break;
                case "Media Pensión": vista.mediaPen.setSelected(true); break;
                case "Pensión Completa": vista.pensionEntera.setSelected(true); break;
            }

            // Servicios Suite
            vista.listaServicios.clearSelection();
            for (String s : r.getExtrasSuite()) {
                vista.listaServicios.setSelectedValue(s, true);
            }

            // Extras
            vista.chekDesayuno.setSelected(r.isDesayuno());
            vista.chekParking.setSelected(r.isParking());
            vista.chekSpa.setSelected(r.isSpa());

            vista.sliderDescuento.setValue(r.getDescuento());
        }
    }

    private void actualizarPrecioFinal() {
    	double precioNoche = calcularPrecioPorNoche();
        vista.precioNocheCambiante.setText(precioNoche + " € / noche");
    }

    private String obtenerRegimenSeleccionado() {
        if (vista.alojamiento.isSelected()) {
            return "Alojamiento";
        } else if (vista.mediaPen.isSelected()) {
            return "Media Pensión";
        } else if (vista.pensionEntera.isSelected()) {
            return "Pensión Completa";
        }
        return "";
    }

    private void limpiarCampos() {
        vista.campoNombre.setText("");
        vista.campoDNI.setText("");
        vista.campoTelefono.setText("");
        vista.campoFechaEntrada.setText("");
        vista.campoFechaSalida.setText("");

        vista.comboHabitacion.setSelectedIndex(0);

        vista.grupoRegimen.clearSelection();

        vista.listaServicios.clearSelection();

        vista.chekDesayuno.setSelected(false);
        vista.chekParking.setSelected(false);
        vista.chekSpa.setSelected(false);

        vista.campoHorario.setValue(new java.util.Date());

        vista.sliderDescuento.setValue(0);
        vista.descuento.setText("Descuento (%) --");

        vista.areaNotas.setText("");
    }

    private void refrescarTabla() {
        tablaModel.setRowCount(0);
        for (Reservas r : modelo.obtenerReservas()) {
            tablaModel.addRow(new Object[]{
                    r.getNombre(),
                    r.getDni(),
                    r.getTelefono(),
                    r.getFechaEntrada(),
                    r.getFechaSalida(),
                    r.getTipoHabitacion(),
                    r.getRegimen(),
                    r.getDescuento(),
                    r.getImporteFinal()
            });
        }
    }
    private boolean hayCamposVacios() {
        return vista.campoNombre.getText().isEmpty() ||
               vista.campoDNI.getText().isEmpty() ||
               vista.campoTelefono.getText().isEmpty() ||
               vista.campoFechaEntrada.getText().isEmpty() ||
               vista.campoFechaSalida.getText().isEmpty() ||
               vista.comboHabitacion.getSelectedItem() == null ||
               obtenerRegimenSeleccionado().isEmpty();
    }
    
    private int calcularDias(String entrada, String salida) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate e = LocalDate.parse(entrada, f); // pasa de texto a fecha real
        LocalDate s = LocalDate.parse(salida, f);

        return (int) ChronoUnit.DAYS.between(e, s);
    }
    
    private double calcularPrecioPorNoche() {

        double suma = precioBaseHabitacion      // individual/doble/suite
                    + precioRegimen             // alojamiento/media/pensión
                    + precioServiciosExtras     // lista suite
                    + precioChecks;             // desayuno/parking/spa

        double desc = (descuento / 100.0);

        return suma - (suma * desc);
    }
    
}
