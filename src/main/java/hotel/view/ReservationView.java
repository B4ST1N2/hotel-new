// File: hotel/view/ReservationView.java
package hotel.view;

import hotel.model.entity.ReservationDetails;
import hotel.model.entity.ReservationUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationView {
    private Scanner scanner;

    public ReservationView() {
        this.scanner = new Scanner(System.in);
    }

    public ReservationDetails obtenerDetallesReserva() {
        System.out.print("Ingrese el nombre del alojamiento para confirmar reserva: ");
        String nombreAlojamiento = scanner.nextLine();

        System.out.print("Ingrese el día de inicio del hospedaje (yyyy-mm-dd): ");
        LocalDate diaInicio = parsearFecha(scanner.nextLine());

        System.out.print("Ingrese el día de finalización del hospedaje (yyyy-mm-dd): ");
        LocalDate diaFin = parsearFecha(scanner.nextLine());

        System.out.print("Ingrese la cantidad de adultos: ");
        int cantidadAdultos = parsearEntero(scanner.nextLine());

        System.out.print("Ingrese la cantidad de niños: ");
        int cantidadNinos = parsearEntero(scanner.nextLine());

        System.out.print("Ingrese la cantidad de habitaciones que desea reservar: ");
        int cantidadHabitaciones = parsearEntero(scanner.nextLine());

        List<String> tiposHabitacion = new ArrayList<>();
        for (int i = 1; i <= cantidadHabitaciones; i++) {
            System.out.print("Ingrese el tipo de habitación " + i + " (e.g., Suite, Standard): ");
            String tipo = scanner.nextLine();
            tiposHabitacion.add(tipo);
        }

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese su fecha de nacimiento (yyyy-mm-dd): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Ingrese su nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        System.out.print("Ingrese su número de teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese su hora aproximada de llegada (HH:mm): ");
        String horaLlegada = scanner.nextLine();

        return new ReservationDetails(nombreAlojamiento, diaInicio, diaFin,
                cantidadAdultos, cantidadNinos, nombre, apellido,
                email, fechaNacimiento, nacionalidad, telefono, horaLlegada, tiposHabitacion);
    }

    public ReservationUpdate obtenerActualizacionReserva() {
        System.out.print("Ingrese su email para actualizar la reserva: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese su fecha de nacimiento (yyyy-mm-dd) para autenticación: ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("¿Desea hacer un cambio de habitación o de alojamiento? (habitación/alojamiento): ");
        String tipoCambio = scanner.nextLine();

        String tipoHabitacionActual = null;
        String nuevoTipoHabitacion = null;

        if (tipoCambio.equalsIgnoreCase("habitación")) {
            System.out.print("Ingrese el tipo de habitación que desea cambiar: ");
            tipoHabitacionActual = scanner.nextLine();

            System.out.print("Ingrese el nuevo tipo de habitación: ");
            nuevoTipoHabitacion = scanner.nextLine();
        }

        return new ReservationUpdate(email, fechaNacimiento, tipoCambio, tipoHabitacionActual, nuevoTipoHabitacion);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("\nError: " + mensaje);
    }

    private LocalDate parsearFecha(String fechaStr) {
        try {
            return LocalDate.parse(fechaStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Por favor, use yyyy-mm-dd.");
            return parsearFecha(scanner.nextLine());
        }
    }

    private int parsearEntero(String numeroStr) {
        try {
            return Integer.parseInt(numeroStr);
        } catch (Exception e) {
            System.out.println("Número inválido. Por favor, ingrese un número entero.");
            return parsearEntero(scanner.nextLine());
        }
    }
}
