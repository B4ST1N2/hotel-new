package hotel.service.alojamiento;

import hotel.model.entity.*;
import hotel.service.habitacion.HabitacionService;
import hotel.service.IGestionService;
import hotel.service.reservas.ReservaService;
import hotel.utilidades.CalculadorTarifaTemporada;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlojamientoService implements IGestionService<Alojamiento> {
    private final String NOMBRE_ARCHIVO_ALOJAMIENTOS = "src/main/java/hotel/data/alojamientos.txt";
    private List<Alojamiento> alojamientos = new ArrayList<>();
    private HabitacionService habitacionService;
    private ReservaService reservaService;
    private CargarAlojamientos cargarAlojamientos;
    private BuscarHotel buscarHotel;

    public AlojamientoService(HabitacionService habitacionService, ReservaService reservaService) {
        this.habitacionService = habitacionService;
        this.reservaService = reservaService;
        this.cargarAlojamientos = new CargarAlojamientos();
        this.buscarHotel = new BuscarHotel();
        cargarDatos();
    }

    // Getter para HabitacionService
    public HabitacionService getHabitacionService() {
        return this.habitacionService;
    }

    @Override
    public void crearArchivo() {
        var archivo = new File(NOMBRE_ARCHIVO_ALOJAMIENTOS);
        try {
            if (!archivo.exists()) {
                var salida = new PrintWriter(new FileWriter(archivo, false));
                salida.close();
                System.out.println("Archivo de alojamientos creado: " + NOMBRE_ARCHIVO_ALOJAMIENTOS);
            }
        } catch (Exception e) {
            System.out.println("Error al crear el archivo de alojamientos: " + e.getMessage());
        }
    }

    @Override
    public List<Alojamiento> obtenerDatos() {
        return alojamientos;
    }

    public void cargarDatos() {
        try {
            crearArchivo();
            List<String> lineasAlojamiento = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO_ALOJAMIENTOS));
            List<Habitacion> habitaciones = habitacionService.obtenerDatos();

            for (String linea : lineasAlojamiento) {
                String[] datos = linea.split(",");


                Alojamiento alojamiento = cargarAlojamientos.cargarAlojamiento(datos);

                if (alojamiento != null) {
                    if (alojamiento instanceof Hotel) {
                        // Asignar habitaciones al hotel
                        Hotel hotel = (Hotel) alojamiento;
                        for (Habitacion habitacion : habitaciones) {
                            if (habitacion.getNombreDelAlojamiento().equalsIgnoreCase(datos[1])) {
                                hotel.getHabitaciones().add(habitacion);
                            }
                        }
                    }
                    alojamientos.add(alojamiento);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al cargar datos aqui: " + e.getMessage());
        }
    }


    public List<String> buscarAlojamientos(String ciudad, String tipoAlojamiento, LocalDate diaInicio, LocalDate diaFin,
                                           int cantidadAdultos, int cantidadNinos, int cantidadHabitaciones) {

        List<String> resultados = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {

            if (alojamiento.getCiudad().equalsIgnoreCase(ciudad) &&
                    alojamiento.getTipo().equalsIgnoreCase(tipoAlojamiento)) {

                if (alojamiento instanceof Hotel) {
                    String informacionHotel = buscarHotel.hotelDisponible(alojamiento,cantidadHabitaciones,diaInicio,diaFin);

                    if(informacionHotel != null){
                        resultados.add(informacionHotel);
                    }
                } else {
                    // Para otros tipos de alojamientos
                    long dias = diaFin.toEpochDay() - diaInicio.toEpochDay();
                    double precioBase = alojamiento.getPrecioPorNoche() * dias;
                    double porcentaje = alojamiento.tarifaPorTemporada(diaInicio, diaFin);
                    double precioFinal = CalculadorTarifaTemporada.aplicarPorcentaje(precioBase, porcentaje);

                    StringBuilder info = new StringBuilder();
                    info.append("Nombre: ").append(alojamiento.getNombre()).append("\n");
                    info.append("Calificación: ").append(alojamiento.getCalificacion()).append("\n");
                    info.append("Precio por noche: ").append(alojamiento.getPrecioPorNoche()).append("\n");
                    info.append("Precio total: ").append(precioFinal).append("\n");
                    info.append("Porcentaje aplicado: ").append(porcentaje).append("%\n");
                    resultados.add(info.toString());
                }
            }
        }
        return resultados;
    }

    // Método para buscar alojamiento por nombre
    public Alojamiento buscarPorNombre(String nombre) {
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getNombre().equalsIgnoreCase(nombre)) {
                return alojamiento;
            }
        }
        return null;
    }

    // Método para confirmar una reserva
    public void confirmarReserva(Alojamiento alojamiento, Habitacion habitacion, LocalDate diaInicio, LocalDate diaFin,
                                 int cantidadAdultos, int cantidadNinos, String nombre, String apellido,
                                 String email, String nacionalidad, String telefono, String horaLlegada) {
        Reserva nuevaReserva = new Reserva(alojamiento, habitacion, diaInicio, diaFin, cantidadAdultos, cantidadNinos,
                nombre, apellido, email, nacionalidad, telefono, horaLlegada);
        reservaService.guardarReserva(nuevaReserva);

        if (habitacion != null) { // Solo los hoteles tienen habitaciones
            habitacion.setDisponible(false);
            habitacionService.actualizarDisponibilidad(alojamiento.getNombre(), habitacion.getTipo(), false);
        }
        System.out.println("Reserva confirmada: " + nuevaReserva);
    }
}
