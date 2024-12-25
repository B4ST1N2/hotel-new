package hotel.servicios;

import hotel.dominios.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AlojamientoService implements IGestionService<Alojamiento> {
    private final String NOMBRE_ARCHIVO_ALOJAMIENTOS = "src/main/java/hotel/data/alojamientos.txt";
    private List<Alojamiento> alojamientos = new ArrayList<>();
    private HabitacionService habitacionService;

    public AlojamientoService() {
        this.habitacionService = new HabitacionService();
        cargarDatos();
    }


    public void cargarDatos() {
        try {
            crearArchivo();
            List<String> lineasAlojamiento = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO_ALOJAMIENTOS));
            List<Habitacion> habitaciones = habitacionService.obtenerDatos();

            for (String linea : lineasAlojamiento) {
                String[] datos = linea.split(",");
                String tipo = datos[0];
                String nombre = datos[1];
                int calificacion = Integer.parseInt(datos[2]);
                String ciudad = datos[3];

                Alojamiento alojamiento = null;

                switch (tipo) {
                    case "Hotel":
                        alojamiento = new Hotel(tipo, nombre, calificacion, ciudad, new ArrayList<>(), Double.parseDouble(datos[4]));
                        break;
                    case "Finca":
                        alojamiento = new Finca(tipo, nombre, calificacion, ciudad, new ArrayList<>(), Double.parseDouble(datos[4]));
                        break;
                    case "Apartamento":
                        alojamiento = new Apartamento(tipo, nombre, calificacion, ciudad, new ArrayList<>(), Double.parseDouble(datos[4]));
                        break;
                    case "DiaDeSol":
                        alojamiento = new DiaDeSol(tipo, nombre, calificacion, ciudad, new ArrayList<>(), datos[4], Boolean.parseBoolean(datos[5]), Double.parseDouble(datos[6]));
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                }

                if (alojamiento != null) {
                    // Asignar habitaciones al alojamiento
                    for (Habitacion habitacion : habitaciones) {
                        if (habitacion.getNombreDelAlojamiento().equals(nombre)) {
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
}
