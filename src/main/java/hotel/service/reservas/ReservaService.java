package hotel.service.reservas;

import hotel.model.entity.Alojamiento;
import hotel.model.entity.Habitacion;
import hotel.model.entity.Hotel;
import hotel.model.entity.Reserva;
import hotel.service.IGestionService;
import hotel.service.alojamiento.AlojamientoService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaService implements IGestionService<Reserva> {
    private final String NOMBRE_ARCHIVO_RESERVAS = "src/main/java/hotel/data/reservas.txt";
    private AlojamientoReservado alojamientoReservado;

    public ReservaService() {
        this.alojamientoReservado = new AlojamientoReservado();
        crearArchivo();
    }

    @Override
    public void crearArchivo() {
        var archivo = new File(NOMBRE_ARCHIVO_RESERVAS);
        try {
            if (!archivo.exists()) {
                if (archivo.getParentFile() != null) {
                    archivo.getParentFile().mkdirs(); // Crea directorios si no existen
                }
                if (archivo.createNewFile()) {
                    System.out.println("Archivo de reservas creado: " + NOMBRE_ARCHIVO_RESERVAS);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de reservas: " + e.getMessage());
        }
    }

    @Override
    public List<Reserva> obtenerDatos() {
        // Este método podría llamar a obtenerReservas(AlojamientoService) si se pasa AlojamientoService como parámetro
        // Por simplicidad, se mantiene vacío y se utiliza el método obtenerReservas(AlojamientoService)
        return new ArrayList<>();
    }

    public void guardarReserva(Reserva reserva) {
        try (var salida = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO_RESERVAS, true))) {
            salida.println(reserva.toString());
            System.out.println("Reserva guardada: " + reserva);
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva: " + e.getMessage());
        }
    }

    public Reserva buscarReservaPorEmailYApellido(String email, String apellido, AlojamientoService alojamientoService) {
        List<Reserva> reservas = obtenerReservas(alojamientoService);
        for (Reserva reserva : reservas) {
            if (reserva.getEmail().equalsIgnoreCase(email) &&
                    reserva.getApellido().equalsIgnoreCase(apellido)) {
                return reserva;
            }
        }

        return null;
    }

    public List<Reserva> obtenerReservas(AlojamientoService alojamientoService) {
        var listaReservas = new ArrayList<Reserva>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO_RESERVAS));
            for (String linea : lineas) {
                String[] datos = linea.split(",");

                String nombreAlojamiento = datos[0];
                String tipoHabitacion = datos[1];
                LocalDate diaInicio = LocalDate.parse(datos[2]);
                LocalDate diaFin = LocalDate.parse(datos[3]);
                int cantidadAdultos = Integer.parseInt(datos[4]);
                int cantidadNinos = Integer.parseInt(datos[5]);
                String nombre = datos[6];
                String apellido = datos[7];
                String email = datos[8];
                String nacionalidad = datos[9];
                String telefono = datos[10];
                String horaLlegada = datos[11];


                // Obtener el objeto Alojamiento
                Alojamiento alojamiento = alojamientoService.buscarPorNombre(nombreAlojamiento);
                if (!alojamientoReservado.datosReserva(alojamiento,datos)){
                    continue;
                }

                // Obtener el objeto Habitacion (solo si es un hotel)
                Habitacion habitacion = null;
                if (alojamiento instanceof Hotel) {
                    Hotel hotel = (Hotel) alojamiento;
                    habitacion = hotel.getHabitaciones().stream()
                            .filter(h -> h.getTipo().equalsIgnoreCase(tipoHabitacion))
                            .findFirst()
                            .orElse(null);
//                    if (habitacion == null) {
//                        System.out.println("Habitación no encontrada para la reserva: " + tipoHabitacion);
//                        continue;
//                    }
                }

                Reserva reserva = new Reserva(alojamiento, habitacion, diaInicio, diaFin,
                        cantidadAdultos, cantidadNinos, nombre, apellido, email, nacionalidad, telefono, horaLlegada);
                listaReservas.add(reserva);
            }
        } catch (IOException e) {
            System.out.println("Error al leer las reservas: " + e.getMessage());
        }
        return listaReservas;
    }

    public void actualizarReserva(Reserva reservaActualizada, AlojamientoService alojamientoService) {
        List<Reserva> reservas = obtenerReservas(alojamientoService);
        List<String> lineas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (!reserva.getEmail().equalsIgnoreCase(reservaActualizada.getEmail()) ||
                    !reserva.getApellido().equalsIgnoreCase(reservaActualizada.getApellido())) {
                lineas.add(reserva.toString());
            } else {
                // Añadir la reserva actualizada
                lineas.add(reservaActualizada.toString());
            }
        }

        try (PrintWriter salida = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO_RESERVAS, false))) {
            for (String linea : lineas) {
                salida.println(linea);
            }
            System.out.println("Reserva actualizada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    public void eliminarReserva(Reserva reserva, AlojamientoService alojamientoService) {
        List<Reserva> reservas = obtenerReservas(alojamientoService);
        List<String> lineas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (!(r.getEmail().equalsIgnoreCase(reserva.getEmail()) &&
                    r.getApellido().equalsIgnoreCase(reserva.getApellido()))) {
                lineas.add(r.toString());
            }
        }

        try (PrintWriter salida = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO_RESERVAS, false))) {
            for (String linea : lineas) {
                salida.println(linea);
            }
            System.out.println("Reserva eliminada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }
}
