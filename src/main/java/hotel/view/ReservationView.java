package hotel.view;

import hotel.controller.AccommodationController;
import hotel.controller.ReservationController;
import hotel.model.entity.Accommodation;
import hotel.model.entity.Reservation;
import hotel.model.entity.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationView {
    private ReservationController reservaController;
    private AccommodationController alojamientoController;

    public ReservationView(ReservationController reservaController, AccommodationController alojamientoController) {
        this.reservaController = reservaController;
        this.alojamientoController = alojamientoController;
    }

    public void buscarAlojamiento(Scanner scanner) {
        System.out.print("Ingrese el nombre del alojamiento a buscar: ");
        String nombreAlojamiento = scanner.nextLine();
        Accommodation alojamiento = alojamientoController.buscarPorNombre(nombreAlojamiento);

        if (alojamiento == null) {
            System.out.println("Alojamiento no encontrado.");
            return;
        }

        mostrarDetallesAlojamiento(alojamiento);
    }

    private void mostrarDetallesAlojamiento(Accommodation alojamiento) {
        System.out.println("Tipo: " + alojamiento.getTipo());
        System.out.println("Nombre: " + alojamiento.getNombre());
        System.out.println("Estrellas: " + alojamiento.getEstrellas());
        System.out.println("Ubicación: " + alojamiento.getUbicacion());
        System.out.println("Precio por noche: " + alojamiento.getPrecioPorNoche());

        if (alojamiento instanceof hotel.model.entity.Hotel) {
            hotel.model.entity.Hotel hotel = (hotel.model.entity.Hotel) alojamiento;
            System.out.println("Habitaciones:");
            for (Room habitacion : hotel.getHabitaciones()) {
                System.out.println(habitacion);
            }
        }
    }

    public void confirmarReserva(Scanner scanner) {
        try {
            System.out.print("Ingrese el nombre del alojamiento para confirmar reserva: ");
            String nombreAlojamiento = scanner.nextLine();
            Accommodation alojamientoEncontrado = alojamientoController.buscarPorNombre(nombreAlojamiento);

            if (alojamientoEncontrado == null) {
                System.out.println("Alojamiento no encontrado. Intente nuevamente.");
                return;
            }

            Room habitacionSeleccionada = null;
            double precioTotal = 0.0;
            if (alojamientoEncontrado instanceof hotel.model.entity.Hotel) {
                hotel.model.entity.Hotel hotel = (hotel.model.entity.Hotel) alojamientoEncontrado;
                List<Room> disponibles = obtenerHabitacionesDisponibles(hotel);
                if (disponibles.isEmpty()) {
                    System.out.println("No hay habitaciones disponibles en este alojamiento.");
                    return;
                }

                mostrarHabitacionesDisponibles(disponibles);
                System.out.print("Seleccione el número de la habitación que desea reservar: ");
                int seleccion = Integer.parseInt(scanner.nextLine());

                if (seleccion < 1 || seleccion > disponibles.size()) {
                    System.out.println("Selección inválida.");
                    return;
                }

                habitacionSeleccionada = disponibles.get(seleccion - 1);
                habitacionSeleccionada.setDisponible(false);
                precioTotal += alojamientoEncontrado.getPrecioPorNoche();
            }

            System.out.print("Ingrese la fecha de inicio (yyyy-mm-dd): ");
            LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());

            System.out.print("Ingrese la fecha de fin (yyyy-mm-dd): ");
            LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

            if (fechaInicio.isAfter(fechaFin)) {
                System.out.println("La fecha de inicio debe ser anterior a la fecha de fin.");
                if (habitacionSeleccionada != null) {
                    habitacionSeleccionada.setDisponible(true);
                }
                return;
            }

            long dias = fechaFin.toEpochDay() - fechaInicio.toEpochDay();
            precioTotal *= dias;

            System.out.print("Ingrese la cantidad de adultos: ");
            int cantidadAdultos = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese la cantidad de niños: ");
            int cantidadNinos = Integer.parseInt(scanner.nextLine());

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

            System.out.println("Precio total de la reserva: " + precioTotal);
            System.out.print("¿Desea confirmar la reserva? (s/n): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                Reservation reserva = new Reservation(alojamientoEncontrado, habitacionSeleccionada, fechaInicio, fechaFin, cantidadAdultos,
                        cantidadNinos, nombre, apellido, email, nacionalidad, telefono, horaLlegada);

                reservaController.confirmarReserva(alojamientoEncontrado, habitacionSeleccionada,
                        fechaInicio, fechaFin, cantidadAdultos, cantidadNinos, nombre, apellido, email, nacionalidad, telefono, horaLlegada);

                System.out.println("Reserva confirmada exitosamente.");
            } else {
                // Liberar habitación seleccionada
                if (habitacionSeleccionada != null) {
                    habitacionSeleccionada.setDisponible(true);
                }
                System.out.println("Reserva cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Error al confirmar la reserva: " + e.getMessage());
        }
    }

    public void actualizarReserva(Scanner scanner) {
        try {
            System.out.print("Ingrese su email para actualizar la reserva: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese su apellido para autenticación: ");
            String apellido = scanner.nextLine();

            Reservation reserva = reservaController.buscarReservaPorEmailYApellido(email, apellido);

            if (reserva == null) {
                System.out.println("Autenticación fallida. No se encontró la reserva.");
                return;
            }

            mostrarDetallesReserva(reserva);

            System.out.println("¿Qué desea actualizar?");
            System.out.println("1. Cambiar habitación");
            System.out.println("2. Cambiar alojamiento");
            System.out.print("Elija una opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                cambiarHabitacion(reserva, scanner);
            } else if (opcion.equals("2")) {
                cambiarAlojamiento(reserva, scanner);
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    private List<Room> obtenerHabitacionesDisponibles(hotel.model.entity.Hotel hotel) {
        List<Room> disponibles = new ArrayList<>();
        for (Room habitacion : hotel.getHabitaciones()) {
            if (habitacion.isDisponible()) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }

    private void mostrarHabitacionesDisponibles(List<Room> disponibles) {
        System.out.println("Habitaciones disponibles:");
        for (int i = 0; i < disponibles.size(); i++) {
            Room habitacion = disponibles.get(i);
            System.out.println((i + 1) + ". " + habitacion);
        }
    }

    private void mostrarDetallesReserva(Reservation reserva) {
        System.out.println("Reserva encontrada:");
        System.out.println("Alojamiento: " + reserva.getAlojamiento().getNombre());
        System.out.println("Habitación: " + (reserva.getHabitacion() != null ? reserva.getHabitacion().getTipo() : "N/A"));
        System.out.println("Fecha de inicio: " + reserva.getDiaInicio());
        System.out.println("Fecha de fin: " + reserva.getDiaFin());
        System.out.println("Adultos: " + reserva.getCantidadAdultos());
        System.out.println("Niños: " + reserva.getCantidadNinos());
        System.out.println("Nombre del cliente: " + reserva.getNombre() + " " + reserva.getApellido());
        System.out.println("Email: " + reserva.getEmail());
        System.out.println("Nacionalidad: " + reserva.getNacionalidad());
        System.out.println("Teléfono: " + reserva.getTelefono());
        System.out.println("Hora de llegada: " + reserva.getHoraLlegada());
    }

    private void cambiarHabitacion(Reservation reserva, Scanner scanner) {
        try {
            Accommodation alojamiento = reserva.getAlojamiento();
            if (!(alojamiento instanceof hotel.model.entity.Hotel)) {
                System.out.println("El alojamiento seleccionado no tiene habitaciones para cambiar.");
                return;
            }

            hotel.model.entity.Hotel hotel = (hotel.model.entity.Hotel) alojamiento;
            List<Room> disponibles = obtenerHabitacionesDisponibles(hotel);

            if (disponibles.isEmpty()) {
                System.out.println("No hay habitaciones disponibles para cambiar.");
                return;
            }

            mostrarHabitacionesDisponibles(disponibles);
            System.out.print("Seleccione el número de la nueva habitación: ");
            int seleccion = Integer.parseInt(scanner.nextLine());

            if (seleccion < 1 || seleccion > disponibles.size()) {
                System.out.println("Selección inválida.");
                return;
            }

            Room nuevaHabitacion = disponibles.get(seleccion - 1);
            nuevaHabitacion.setDisponible(false);
            reserva.setHabitacion(nuevaHabitacion);
            reservaController.actualizarReserva(reserva);

            System.out.println("Habitación actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cambiar la habitación: " + e.getMessage());
        }
    }

    private void cambiarAlojamiento(Reservation reserva, Scanner scanner) {
        try {
            System.out.print("Ingrese el nuevo nombre del alojamiento: ");
            String nuevoNombreAlojamiento = scanner.nextLine();
            Accommodation nuevoAlojamiento = alojamientoController.buscarPorNombre(nuevoNombreAlojamiento);

            if (nuevoAlojamiento == null) {
                System.out.println("Alojamiento no encontrado.");
                return;
            }

            Room nuevaHabitacion = null;
            if (nuevoAlojamiento instanceof hotel.model.entity.Hotel) {
                hotel.model.entity.Hotel hotel = (hotel.model.entity.Hotel) nuevoAlojamiento;
                List<Room> disponibles = obtenerHabitacionesDisponibles(hotel);
                if (disponibles.isEmpty()) {
                    System.out.println("No hay habitaciones disponibles en el nuevo alojamiento.");
                    return;
                }

                mostrarHabitacionesDisponibles(disponibles);
                System.out.print("Seleccione el número de la habitación en el nuevo alojamiento: ");
                int seleccion = Integer.parseInt(scanner.nextLine());

                if (seleccion < 1 || seleccion > disponibles.size()) {
                    System.out.println("Selección inválida.");
                    return;
                }

                nuevaHabitacion = disponibles.get(seleccion - 1);
                nuevaHabitacion.setDisponible(false);
            }

            reserva.setAlojamiento(nuevoAlojamiento);
            reserva.setHabitacion(nuevaHabitacion);
            reservaController.actualizarReserva(reserva);

            System.out.println("Alojamiento y habitación actualizados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cambiar el alojamiento: " + e.getMessage());
        }
    }
}
