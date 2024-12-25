package hotel.servicios;

import hotel.dominios.Habitacion;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HabitacionService implements IGestionService<Habitacion>{
    private final String NOMBRE_ARCHIVO_HABITACIONES = "src/main/java/hotel/data/habitaciones.txt";

    public HabitacionService() {
        crearArchivo();
    }

    @Override
    public void crearArchivo() {
        var archivo = new File(NOMBRE_ARCHIVO_HABITACIONES);
        try {
            if (!archivo.exists()) {
                var salida = new PrintWriter(new FileWriter(archivo, false));
                salida.close();
                System.out.println("Archivo de habitaciones creado: " + NOMBRE_ARCHIVO_HABITACIONES);
            }
        } catch (Exception e) {
            System.out.println("Error al crear el archivo de habitaciones: " + e.getMessage());
        }
    }

    @Override
    public List<Habitacion> obtenerDatos() {
        var listaHabitaciones = new ArrayList<Habitacion>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO_HABITACIONES));
            for (String linea : lineas) {
                String[] datos = linea.split(",");
                String nombreAlojamiento = datos[0];
                String tipoHabitacion = datos[1];
                String contenido = datos[2];
                double precio = Double.parseDouble(datos[3]);
                boolean disponible = Boolean.parseBoolean(datos[4]);

                listaHabitaciones.add(new Habitacion(nombreAlojamiento, tipoHabitacion, contenido, precio, disponible));
            }
        } catch (Exception e) {
            System.out.println("Error al leer las habitaciones: " + e.getMessage());
        }
        return listaHabitaciones;
    }
}
