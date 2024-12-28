package hotel.service;

import hotel.model.entity.Habitacion;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HabitacionService implements IGestionService<Habitacion> {
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
                if (datos.length < 5) continue; // Verificar que la línea tenga todos los campos

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

    // Método para actualizar la disponibilidad de una habitación
    public void actualizarDisponibilidad(String nombreAlojamiento, String tipoHabitacion, boolean disponible) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO_HABITACIONES));
            List<String> nuevasLineas = new ArrayList<>();

            for (String linea : lineas) {
                String[] datos = linea.split(",");
                if (datos.length < 5) {
                    nuevasLineas.add(linea);
                    continue;
                }

                String nombre = datos[0];
                String tipo = datos[1];
                String contenido = datos[2];
                String precio = datos[3];
                String disp = datos[4];

                if (nombre.equalsIgnoreCase(nombreAlojamiento) && tipo.equalsIgnoreCase(tipoHabitacion)) {
                    disp = String.valueOf(disponible);
                }

                nuevasLineas.add(String.join(",", nombre, tipo, contenido, precio, disp));
            }

            // Escribir las nuevas líneas en el archivo
            PrintWriter salida = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO_HABITACIONES, false));
            for (String nuevaLinea : nuevasLineas) {
                salida.println(nuevaLinea);
            }
            salida.close();
            System.out.println("Disponibilidad actualizada para " + tipoHabitacion + " en " + nombreAlojamiento);
        } catch (Exception e) {
            System.out.println("Error al actualizar la disponibilidad: " + e.getMessage());
        }
    }
}
