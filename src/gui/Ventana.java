package gui;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Ventana extends JFrame {

    public JLabel titulo;
    public JLabel logo;
    //lo que usare para el panel de datos cliente
    public JLabel nombre;
    public JLabel DNI;
    public JLabel telefono;
    public JLabel fechaEntrada;
    public JLabel fechaSalida;
    public JLabel imagenCliente;
    public JTextField campoNombre;
    public JTextField campoDNI;
    public JTextField campoTelefono;
    public JTextField campoFechaEntrada;
    public JTextField campoFechaSalida;
    //lo que usare para el panel de Habitaciones/opciones
    //primer panel de Habitaciones
    public JLabel tipoHabitacion;
    public JComboBox comboHabitacion;
    public JLabel regimen;
    public ButtonGroup grupoRegimen;
    public JRadioButton alojamiento;
    public JRadioButton mediaPen;
    public JRadioButton pensionEntera;
    public JLabel precioNocheCambiante;
    //segundo panel de habitaciones y opciones (Extras y servicios)
    public JLabel tituloServicios;
    public JList listaServicios;
    public JCheckBox chekDesayuno;
    public JCheckBox chekParking;
    public JCheckBox chekSpa;
    public JSpinner campoHorario;
    //tercer panel de habitaciones y opciones (descuento y notas)
    public JLabel descuento;
    public JSlider sliderDescuento;
    public JTextArea areaNotas;
    public JLabel notasRecepcionista;
	//ultimo Panel de la tabla reservas
	public JTable tablaReserva;
	public JButton botonNuevaReserva;
	public JButton botonGuardarReserva;
	public JButton botonCancelarReserva;
	public Object frame;

    public Ventana() {
        super("Reservica de hoteles");
        initUI();
    }

    private void initUI() {
        Container content = this.getContentPane();           // Obtiene el panel de contenido del frame
        content.setLayout(null);                             // Establece layout null (posición absoluta)
        //panel de Datos Cliente
        JPanel panelDatosCliente = new JPanel();
        panelDatosCliente.setLayout(null);
        panelDatosCliente.setBounds(50, 75, 900, 100);
        panelDatosCliente.setBorder(new TitledBorder("DATOS DEL CLIENTE"));
        //etiquetas
        nombre = new JLabel("Nombre");
        DNI = new JLabel("DNI");
        telefono = new JLabel("Teléfono");
        fechaEntrada = new JLabel("Fecha de Entrada");
        fechaSalida = new JLabel("Fecha de Salida");
        //campos
        campoNombre = new JTextField();
        campoDNI = new JTextField();
        campoTelefono = new JTextField();
        campoFechaEntrada = new JTextField();
        campoFechaSalida = new JTextField();

        //tamaño etiquetas
        nombre.setBounds(25, 25, 100, 20);
        DNI.setBounds(25, 45, 100, 20);
        telefono.setBounds(25, 65, 100, 20);
        fechaEntrada.setBounds(425, 35, 100, 20);
        fechaSalida.setBounds(425, 55, 100, 20);

        //tamaño campos
        campoNombre.setBounds(125, 25, 250, 20);
        campoDNI.setBounds(125, 45, 250, 20);
        campoTelefono.setBounds(125, 65, 250, 20);
        campoFechaEntrada.setBounds(535, 35, 200, 20);
        campoFechaSalida.setBounds(535, 55, 200, 20);

        panelDatosCliente.add(nombre);
        panelDatosCliente.add(DNI);
        panelDatosCliente.add(telefono);
        panelDatosCliente.add(fechaEntrada);
        panelDatosCliente.add(fechaSalida);
        panelDatosCliente.add(campoNombre);
        panelDatosCliente.add(campoDNI);
        panelDatosCliente.add(campoTelefono);
        panelDatosCliente.add(campoFechaEntrada);
        panelDatosCliente.add(campoFechaSalida);

        //comienzo del panel de Habitaciones y opciones
        JPanel panelHabitaciones = new JPanel();
        panelHabitaciones.setLayout(null);
        panelHabitaciones.setBounds(50, 200, 900, 240);
        panelHabitaciones.setBorder(new TitledBorder("HABITACIONES Y OPCIONES"));

        JPanel panelHabitacion = new JPanel();
        panelHabitacion.setLayout(null);
        panelHabitacion.setBounds(20, 20, 275, 200);
        panelHabitacion.setBorder(new TitledBorder("Habitación"));
        panelHabitaciones.add(panelHabitacion);
        tipoHabitacion = new JLabel("Tipo habitación:");
        tipoHabitacion.setBounds(15, 20, 120, 20);
        comboHabitacion = new JComboBox();
        comboHabitacion.addItem("Individual");
        comboHabitacion.addItem("Doble");
        comboHabitacion.addItem("Suite");
        comboHabitacion.addItem("Deluxe");
        comboHabitacion.setBounds(15, 45, 230, 25);

        regimen = new JLabel("Régimen");
        regimen.setBounds(15, 80, 200, 20);

        alojamiento = new JRadioButton("Solo alojamiento");
        mediaPen = new JRadioButton("Media pensión");
        pensionEntera = new JRadioButton("Pensión completa");

        alojamiento.setBounds(25, 100, 200, 20);
        mediaPen.setBounds(25, 120, 200, 20);
        pensionEntera.setBounds(25, 140, 200, 20);

        grupoRegimen = new ButtonGroup();
        grupoRegimen.add(alojamiento);
        grupoRegimen.add(mediaPen);
        grupoRegimen.add(pensionEntera);
        
        alojamiento.setSelected(true);
        

        precioNocheCambiante = new JLabel("Precio por noche: - €");
        precioNocheCambiante.setBounds(15, 165, 200, 20);

        panelHabitacion.add(tipoHabitacion);
        panelHabitacion.add(comboHabitacion);
        panelHabitacion.add(regimen);
        panelHabitacion.add(alojamiento);
        panelHabitacion.add(mediaPen);
        panelHabitacion.add(pensionEntera);
        panelHabitacion.add(precioNocheCambiante);

        //panel de extras y servicions
        JPanel panelExtras = new JPanel();
        panelExtras.setLayout(null);
        panelExtras.setBounds(330, 20, 275, 200);
        panelExtras.setBorder(new TitledBorder("Extras y Servicios"));
        panelHabitaciones.add(panelExtras);

        tituloServicios = new JLabel("Servicios Suite:");
        tituloServicios.setBounds(15, 20, 200, 20);
        listaServicios = new JList(
                new String[]{
                    "Decoración romántica",
                    "Pack Aniversario",
                    "Botella de vino",
                    "Bombones"
                }
        );
        listaServicios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollServicios = new JScrollPane(listaServicios);
        scrollServicios.setBounds(15, 45, 240, 70);

        chekDesayuno = new JCheckBox("Desayuno");
        chekDesayuno.setBounds(15, 120, 200, 20);
        chekParking = new JCheckBox("Parking");
        chekParking.setBounds(15, 140, 200, 20);
        chekSpa = new JCheckBox("SPA");
        chekSpa.setBounds(15, 160, 125, 20);

        campoHorario = new JSpinner();
        
        campoHorario= new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorHora = new JSpinner.DateEditor(campoHorario, "HH:mm");
        campoHorario.setEditor(editorHora);
        JLabel horario = new JLabel("Horario SPA");
        horario.setBounds(160, 140,120,25);
        campoHorario.setBounds(150, 160, 120 , 25);
        
        
        panelExtras.add(horario);
        panelExtras.add(campoHorario);
        panelExtras.add(tituloServicios);
        panelExtras.add(scrollServicios);
        panelExtras.add(chekDesayuno);
        panelExtras.add(chekParking);
        panelExtras.add(chekSpa);

        //ahora empiezo con el 3º panel dentro de habitacion y opcionesç
		JPanel panelDescuento = new JPanel();
		panelDescuento.setLayout(null);
		panelDescuento.setBounds(640, 20, 235, 200);
		panelDescuento.setBorder(new TitledBorder("Descuento y Notas"));
		panelHabitaciones.add(panelDescuento);

		descuento = new JLabel("Descuento (%)  --");
		descuento.setBounds(15, 20, 200, 20);
		
		sliderDescuento = new JSlider(0, 30, 0);
		sliderDescuento.setMajorTickSpacing(10);
		sliderDescuento.setMinorTickSpacing(10);
		sliderDescuento.setPaintTicks(true);
		sliderDescuento.setPaintLabels(true);
		sliderDescuento.setSnapToTicks(true);
		sliderDescuento.setBounds(15, 45, 200, 50);


		notasRecepcionista = new JLabel("Notas:");
		notasRecepcionista.setBounds(15, 95, 200, 20);
		panelDescuento.add(notasRecepcionista);

		areaNotas = new JTextArea();
		areaNotas.setLineWrap(true);
		areaNotas.setWrapStyleWord(true);
					
		JScrollPane scrollNotas = new JScrollPane(areaNotas);
		scrollNotas.setBounds(7, 115, 220, 75);

		panelDescuento.add(notasRecepcionista);
		panelDescuento.add(sliderDescuento);
		panelDescuento.add(descuento);
		panelDescuento.add(scrollNotas);

		//ultimo panel de reservas
		
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBounds(50, 460, 900, 200);
		panelTabla.setBorder(new TitledBorder("Reservas"));
		

		String[] columnas = {
		    "Nombre", "Fecha Entrada", "Fecha Salida", "Tipo", 
		    "Huéspedes", "Régimen", "Dto (%)", "Importe (€)"
		};

		tablaReserva = new JTable(new Object[0][columnas.length], columnas);
		JScrollPane scrollTabla = new JScrollPane(tablaReserva);
		scrollTabla.setBounds(15, 25, 870, 140);
		

		botonNuevaReserva = new JButton("Nueva Reserva");
		botonNuevaReserva.setBounds(180, 165, 150, 30);  // centrado debajo de la tabla

		botonGuardarReserva = new JButton("Guardar");
		botonGuardarReserva.setBounds(380, 165, 150, 30);  // debajo del anterior

		botonCancelarReserva = new JButton("Cancelar");
		botonCancelarReserva.setBounds(580, 165, 150, 30); // último

		panelTabla.add(scrollTabla);
		panelTabla.add(botonNuevaReserva);
		panelTabla.add(botonGuardarReserva);
		panelTabla.add(botonCancelarReserva);


        content.add(panelDatosCliente);                        // Añadir panel superior al contenedor principal
        content.add(panelHabitaciones);
		content.add(panelTabla);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Hacer que cerrar la ventana termine la aplicación
        this.setSize(1000, 700);                                // Tamaño de la ventana principal: ancho=600, alto=700
        this.setLocationRelativeTo(null);                      // Centrar la ventana en la pantalla
        
        //action command
        botonNuevaReserva.setActionCommand("NuevaReserva");
        botonGuardarReserva.setActionCommand("GuardarReserva");
        botonCancelarReserva.setActionCommand("CancelarReserva");

        comboHabitacion.setActionCommand("HabitacionElegida");

        alojamiento.setActionCommand("Alojamiento");
        
        mediaPen.setActionCommand("MediaPension");
        pensionEntera.setActionCommand("PensionCompleta");

        chekDesayuno.setActionCommand("CheckServicios");
        chekParking.setActionCommand("CheckServicios");
        chekSpa.setActionCommand("CheckServicios");
             
        listaServicios.setEnabled(false);

    }
    
}
