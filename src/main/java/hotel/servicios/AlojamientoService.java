package hotel.servicios;

import hotel.dominios.*;

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
    public HabitacionService habitacionService;
    private ReservaService reservaService;

    public AlojamientoService() {
        this.habitacionService = new HabitacionService();
        this.reservaService = new ReservaService();
        cargarDatos();
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
                if (datos.length < 5) continue;

                String tipo = datos[0];
                String nombre = datos[1];
                int calificacion = Integer.parseInt(datos[2]);
                String ciudad = datos[3];
                double precioNoche = Double.parseDouble(datos[4]);

                Alojamiento alojamiento = null;

                switch (tipo) {
                    case "Hotel":
                        alojamiento = new Hotel(tipo, nombre, calificacion, ciudad, new ArrayList<>(), precioNoche);
                        break;
                    case "Finca":
                        alojamiento = new Finca(tipo, nombre, calificacion, ciudad, new ArrayList<>(), precioNoche);
                        break;
                    case "Apartamento":
                        alojamiento = new Apartamento(tipo, nombre, calificacion, ciudad, new ArrayList<>(), precioNoche);
                        break;
                    case "DiaDeSol":
                        if (datos.length < 7) {
                            System.out.println("Datos insuficientes para DiaDeSol: " + nombre);
                            continue;
                        }
                        String actividades = datos[5];
                        boolean refrigerio = Boolean.parseBoolean(datos[6]);
                        double precioActividad = Double.parseDouble(datos[7]);
                        alojamiento = new DiaDeSol(tipo, nombre, calificacion, ciudad, new ArrayList<>(),
                                actividades, refrigerio, precioActividad);
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                }

                if (alojamiento != null) {
                    // Asignar habitaciones al alojamiento
                    for (Habitacion habitacion : habitaciones) {
                        if (habitacion.getNombreDelAlojamiento().equalsIgnoreCase(nombre)) {
                            alojamiento.getHabitaciones().add(habitacion);
                        }
                    }
                    alojamientos.add(alojamiento);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }


    public List<String> buscarAlojamientos(String ciudad, String tipoAlojamiento, LocalDate diaInicio, LocalDate diaFin,
                                           int cantidadAdultos, int cantidadNinos, int cantidadHabitaciones) {
        List<String> resultados = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCiudad().equalsIgnoreCase(ciudad) &&
                    alojamiento.getTipo().equalsIgnoreCase(tipoAlojamiento)) {

                long habitacionesDisponibles = alojamiento.getHabitaciones().stream()
                        .filter(Habitacion::isDisponible)
                        .count();

                if (habitacionesDisponibles >= cantidadHabitaciones) {
                    double precioTotal = alojamiento.calcularPrecioTotal(diaInicio, diaFin, cantidadHabitaciones);
                    double porcentaje = alojamiento.tarifaPorTemporada(diaInicio, diaFin);
                    StringBuilder info = new StringBuilder();
                    info.append("Nombre: ").append(alojamiento.getNombre()).append("\n");
                    info.append("Calificación: ").append(alojamiento.getCalificacion()).append("\n");
                    info.append("Precio por noche: ").append(alojamiento.getPrecioPorNoche()).append("\n");
                    info.append("Precio total: ").append(precioTotal).append("\n");
                    info.append("Porcentaje aplicado: ").append(porcentaje).append("%\n");
                    if (alojamiento instanceof DiaDeSol) {
                        DiaDeSol diaDeSol = (DiaDeSol) alojamiento;
                        info.append("Actividades: ").append(diaDeSol.getActividades()).append("\n");
                        info.append("Incluye refrigerio: ").append(diaDeSol.isRefrigerio() ? "Sí" : "No").append("\n");
                        info.append("Precio de actividades: ").append(diaDeSol.getPrecio()).append("\n");
                    }
                    resultados.add(info.toString());
                }
            }
        }
        return resultados;
    }


    public List<Habitacion> confirmarDisponibilidad(String nombreAlojamiento, LocalDate diaInicio, LocalDate diaFin,
                                                    int cantidadAdultos, int cantidadNinos, int cantidadHabitaciones) {
        List<Habitacion> disponibles = new ArrayList<>();
        Alojamiento alojamientoEncontrado = buscarPorNombre(nombreAlojamiento);

        if (alojamientoEncontrado != null) {
            for (Habitacion habitacion : alojamientoEncontrado.getHabitaciones()) {
                if (habitacion.isDisponible()) {
                    disponibles.add(habitacion);
                }
            }
        }

        return disponibles;
    }

    public Alojamiento buscarPorNombre(String nombre) {
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getNombre().equalsIgnoreCase(nombre)) {
                return alojamiento;
            }
        }
        return null;
    }


    public void confirmarReserva(Alojamiento alojamiento, Habitacion habitacion, LocalDate diaInicio, LocalDate diaFin,
                                 int cantidadAdultos, int cantidadNinos, String nombre, String apellido,
                                 String email, String nacionalidad, String telefono, String horaLlegada) {
        Reserva nuevaReserva = new Reserva(alojamiento, habitacion, diaInicio, diaFin, cantidadAdultos, cantidadNinos,
                nombre, apellido, email, nacionalidad, telefono, horaLlegada);
        reservaService.guardarReserva(nuevaReserva);
        habitacion.setDisponible(false);
        habitacionService.actualizarDisponibilidad(alojamiento.getNombre(), habitacion.getTipo(), false);
        System.out.println("Reserva confirmada: " + nuevaReserva);
    }
}
