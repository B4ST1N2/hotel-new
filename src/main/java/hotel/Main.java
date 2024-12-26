package hotel;

import hotel.dominios.Alojamiento;
import hotel.dominios.Habitacion;
import hotel.dominios.Reserva;
import hotel.servicios.AlojamientoService;
import hotel.servicios.ReservaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlojamientoService alojamientos = new AlojamientoService();
        ReservaService reservaService = new ReservaService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Sistema de Gestión de Alojamiento ===");
            System.out.println("1. Buscar alojamientos");
            System.out.println("2. Confirmar reserva");
            System.out.println("3. Actualizar reserva");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            int opcion = 0;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarAlojamientos(alojamientos, scanner);
                    break;
                case 2:
                    confirmarReserva(alojamientos, reservaService, scanner);
                    break;
                case 3:
                    actualizarReserva(alojamientos, reservaService, scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void buscarAlojamientos(AlojamientoService alojamientos, Scanner scanner) {
        try {
            System.out.print("Ingrese la ciudad: ");
            String ciudad = scanner.nextLine();
            System.out.print("Ingrese el tipo de alojamiento (Hotel, Apartamento, Finca, DiaDeSol): ");
            String tipo = scanner.nextLine();
            System.out.print("Ingrese el día de inicio (yyyy-mm-dd): ");
            LocalDate diaInicio = LocalDate.parse(scanner.nextLine());
            System.out.print("Ingrese el día de fin (yyyy-mm-dd): ");
            LocalDate diaFin = LocalDate.parse(scanner.nextLine());

            if (diaInicio.isAfter(diaFin)) {
                System.out.println("La fecha de inicio debe ser anterior a la fecha de fin.");
                return;
            }

            System.out.print("Ingrese la cantidad de adultos: ");
            int adultos = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la cantidad de niños: ");
            int ninos = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la cantidad de habitaciones: ");
            int habitacionesReq = Integer.parseInt(scanner.nextLine());

            if (adultos <= 0 || habitacionesReq <= 0) {
                System.out.println("Las cantidades de adultos y habitaciones deben ser positivas.");
                return;
            }

            List<String> resultados = alojamientos.buscarAlojamientos(ciudad, tipo, diaInicio, diaFin, adultos, ninos, habitacionesReq);
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron alojamientos disponibles.");
            } else {
                System.out.println("Alojamientos encontrados:");
                for (String info : resultados) {
                    System.out.println(info);
                    System.out.println("----------------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar alojamientos: " + e.getMessage());
        }
    }

    private static void confirmarReserva(AlojamientoService alojamientos, ReservaService reservaService, Scanner scanner) {
        try {
            System.out.print("Ingrese el nombre del alojamiento para confirmar reserva: ");
            String nombreAlojamiento = scanner.nextLine();

            Alojamiento alojamientoEncontrado = alojamientos.buscarPorNombre(nombreAlojamiento);

            if (alojamientoEncontrado == null) {
                System.out.println("Alojamiento no encontrado. Intente nuevamente.");
                return;
            }

            // Mostrar habitaciones disponibles
            List<Habitacion> disponibles = alojamientos.confirmarDisponibilidad(nombreAlojamiento, null, null, 0, 0, 0);
            disponibles = alojamientoEncontrado.getHabitaciones().stream().filter(Habitacion::isDisponible).toList();

            if (disponibles.isEmpty()) {
                System.out.println("No hay habitaciones disponibles en este alojamiento.");
                return;
            }

            System.out.println("Habitaciones disponibles:");
            for (int i = 0; i < disponibles.size(); i++) {
                Habitacion habitacion = disponibles.get(i);
                System.out.println((i + 1) + ". " + habitacion);
            }

            System.out.print("Seleccione el número de la habitación que desea reservar: ");
            int seleccion = Integer.parseInt(scanner.nextLine());

            if (seleccion < 1 || seleccion > disponibles.size()) {
                System.out.println("Selección inválida.");
                return;
            }

            Habitacion habitacionSeleccionada = disponibles.get(seleccion - 1);

            System.out.print("Ingrese la fecha de inicio (yyyy-mm-dd): ");
            LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());

            System.out.print("Ingrese la fecha de fin (yyyy-mm-dd): ");
            LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

            if (fechaInicio.isAfter(fechaFin)) {
                System.out.println("La fecha de inicio debe ser anterior a la fecha de fin.");
                return;
            }

            System.out.print("Ingrese la cantidad de adultos: ");
            int cantidadAdultos = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la cantidad de niños: ");
            int cantidadNinos = Integer.parseInt(scanner.nextLine());

            if (cantidadAdultos <= 0 || cantidadNinos < 0) {
                System.out.println("Las cantidades de adultos y niños deben ser válidas.");
                return;
            }

            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese su apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Ingrese su email: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese su nacionalidad: ");
            String nacionalidad = scanner.nextLine();

            System.out.print("Ingrese su número de teléfono: ");
            String telefono = scanner.nextLine();

            System.out.print("Ingrese su hora aproximada de llegada (HH:mm): ");
            String horaLlegada = scanner.nextLine();

            // Calcular precio total
            double precioTotal = alojamientoEncontrado.calcularPrecioTotal(fechaInicio, fechaFin, 1);
            double porcentaje = alojamientoEncontrado.tarifaPorTemporada(fechaInicio, fechaFin);
            System.out.println("Precio total de la reserva: " + precioTotal + " (Porcentaje aplicado: " + porcentaje + "%)");

            System.out.print("¿Desea confirmar la reserva? (s/n): ");
            String confirmacion = scanner.nextLine();
            if (!confirmacion.equalsIgnoreCase("s")) {
                System.out.println("Reserva cancelada.");
                return;
            }

            // Confirmar la reserva
            alojamientos.confirmarReserva(alojamientoEncontrado, habitacionSeleccionada, fechaInicio, fechaFin,
                    cantidadAdultos, cantidadNinos, nombre, apellido, email, nacionalidad, telefono, horaLlegada);

            System.out.println("Se ha realizado la reserva con éxito.");
        } catch (Exception e) {
            System.out.println("Error al confirmar la reserva: " + e.getMessage());
        }
    }

    private static void actualizarReserva(AlojamientoService alojamientos, ReservaService reservaService, Scanner scanner) {
        try {
            System.out.print("Ingrese su email para actualizar la reserva: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese su apellido para autenticación: ");
            String apellido = scanner.nextLine();

            Reserva reserva = reservaService.buscarReservaPorEmailYApellido(email, apellido, alojamientos);

            if (reserva == null) {
                System.out.println("Autenticación fallida. No se encontró la reserva.");
                return;
            }

            System.out.println("Reserva encontrada:");
            System.out.println("Alojamiento: " + reserva.getAlojamiento().getNombre());
            System.out.println("Habitación: " + reserva.getHabitacion().getTipo());
            System.out.println("Fecha de inicio: " + reserva.getDiaInicio());
            System.out.println("Fecha de fin: " + reserva.getDiaFin());
            System.out.println("Adultos: " + reserva.getCantidadAdultos());
            System.out.println("Niños: " + reserva.getCantidadNinos());
            System.out.println("Nombre del cliente: " + reserva.getNombre() + " " + reserva.getApellido());
            System.out.println("Email: " + reserva.getEmail());
            System.out.println("Nacionalidad: " + reserva.getNacionalidad());
            System.out.println("Teléfono: " + reserva.getTelefono());
            System.out.println("Hora de llegada: " + reserva.getHoraLlegada());

            System.out.println("¿Qué desea actualizar?");
            System.out.println("1. Cambiar habitación");
            System.out.println("2. Cambiar alojamiento");
            System.out.print("Elija una opción: ");
            int opcionActualizar = Integer.parseInt(scanner.nextLine());

            if (opcionActualizar == 1) {
                // Cambiar habitación
                System.out.print("Ingrese el nuevo tipo de habitación: ");
                String nuevoTipoHabitacion = scanner.nextLine();

                Alojamiento alojamiento = reserva.getAlojamiento();
                List<Habitacion> disponibles = alojamiento.getHabitaciones().stream()
                        .filter(h -> h.getTipo().equalsIgnoreCase(nuevoTipoHabitacion) && h.isDisponible())
                        .toList();

                if (disponibles.isEmpty()) {
                    System.out.println("No hay habitaciones disponibles del tipo seleccionado.");
                    return;
                }

                System.out.println("Habitaciones disponibles del tipo '" + nuevoTipoHabitacion + "':");
                for (int i = 0; i < disponibles.size(); i++) {
                    Habitacion habitacion = disponibles.get(i);
                    System.out.println((i + 1) + ". " + habitacion);
                }

                System.out.print("Seleccione el número de la nueva habitación: ");
                int seleccion = Integer.parseInt(scanner.nextLine());

                if (seleccion < 1 || seleccion > disponibles.size()) {
                    System.out.println("Selección inválida.");
                    return;
                }

                Habitacion nuevaHabitacion = disponibles.get(seleccion - 1);

                // Liberar la antigua habitación
                reserva.getHabitacion().setDisponible(true);
                alojamientos.habitacionService.actualizarDisponibilidad(reserva.getAlojamiento().getNombre(),
                        reserva.getHabitacion().getTipo(), true);

                // Asignar la nueva habitación
                reserva.setHabitacion(nuevaHabitacion);
                nuevaHabitacion.setDisponible(false);
                alojamientos.habitacionService.actualizarDisponibilidad(reserva.getAlojamiento().getNombre(),
                        nuevaHabitacion.getTipo(), false);

                // Actualizar la reserva en el archivo
                reservaService.actualizarReserva(reserva, alojamientos);
                System.out.println("Habitación actualizada correctamente.");

            } else if (opcionActualizar == 2) {
                // Cambiar alojamiento
                System.out.print("Ingrese el nuevo nombre del alojamiento: ");
                String nuevoNombreAlojamiento = scanner.nextLine();

                Alojamiento nuevoAlojamiento = alojamientos.buscarPorNombre(nuevoNombreAlojamiento);
                if (nuevoAlojamiento == null) {
                    System.out.println("Alojamiento no encontrado.");
                    return;
                }

                // Mostrar habitaciones disponibles en el nuevo alojamiento
                List<Habitacion> disponibles = nuevoAlojamiento.getHabitaciones().stream()
                        .filter(Habitacion::isDisponible)
                        .toList();

                if (disponibles.isEmpty()) {
                    System.out.println("No hay habitaciones disponibles en el nuevo alojamiento.");
                    return;
                }

                System.out.println("Habitaciones disponibles en '" + nuevoNombreAlojamiento + "':");
                for (int i = 0; i < disponibles.size(); i++) {
                    Habitacion habitacion = disponibles.get(i);
                    System.out.println((i + 1) + ". " + habitacion);
                }

                System.out.print("Seleccione el número de la habitación en el nuevo alojamiento: ");
                int seleccion = Integer.parseInt(scanner.nextLine());

                if (seleccion < 1 || seleccion > disponibles.size()) {
                    System.out.println("Selección inválida.");
                    return;
                }

                Habitacion nuevaHabitacion = disponibles.get(seleccion - 1);

                // Liberar la antigua habitación
                reserva.getHabitacion().setDisponible(true);
                alojamientos.habitacionService.actualizarDisponibilidad(reserva.getAlojamiento().getNombre(),
                        reserva.getHabitacion().getTipo(), true);

                // Asignar el nuevo alojamiento y habitación
                reserva.setAlojamiento(nuevoAlojamiento);
                reserva.setHabitacion(nuevaHabitacion);
                nuevaHabitacion.setDisponible(false);
                alojamientos.habitacionService.actualizarDisponibilidad(nuevoAlojamiento.getNombre(),
                        nuevaHabitacion.getTipo(), false);

                // Actualizar la reserva en el archivo
                reservaService.actualizarReserva(reserva, alojamientos);
                System.out.println("Alojamiento y habitación actualizados correctamente.");
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }
}
