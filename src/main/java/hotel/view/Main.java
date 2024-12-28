package hotel.view; // Asegúrate de que la clase Main esté en el paquete 'hotel.view'

import hotel.controller.AlojamientoController;
import hotel.controller.ReservaController;
import hotel.model.entity.Alojamiento;
import hotel.model.entity.Habitacion;
import hotel.model.entity.Hotel; // Importación añadida
import hotel.model.entity.Reserva;
import hotel.service.alojamiento.AlojamientoService;
import hotel.service.HabitacionService;
import hotel.service.ReservaService;
import hotel.utilidades.CalculadorTarifaTemporada; // Asegúrate de importar las utilidades si es necesario

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar servicios
        HabitacionService habitacionService = new HabitacionService();
        ReservaService reservaService = new ReservaService();
        AlojamientoService alojamientoService = new AlojamientoService(habitacionService, reservaService);

        // Inicializar controladores
        AlojamientoController alojamientoController = new AlojamientoController(alojamientoService);
        ReservaController reservaController = new ReservaController(reservaService, alojamientoService);

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
                    buscarAlojamientos(alojamientoController, scanner);
                    break;
                case 2:
                    confirmarReserva(alojamientoController, reservaController, scanner);
                    break;
                case 3:
                    actualizarReserva(reservaController, scanner);
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

    private static void buscarAlojamientos(AlojamientoController alojamientoController, Scanner scanner) {
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

            List<String> resultados = alojamientoController.buscarAlojamientos(ciudad, tipo, diaInicio, diaFin, adultos, ninos, habitacionesReq);
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

    private static void confirmarReserva(AlojamientoController alojamientoController, ReservaController reservaController, Scanner scanner) {
        try {
            System.out.print("Ingrese el nombre del alojamiento para confirmar reserva: ");
            String nombreAlojamiento = scanner.nextLine();

            Alojamiento alojamientoEncontrado = alojamientoController.buscarPorNombre(nombreAlojamiento);

            if (alojamientoEncontrado == null) {
                System.out.println("Alojamiento no encontrado. Intente nuevamente.");
                return;
            }

            List<Habitacion> habitacionesSeleccionadas = new ArrayList<>();
            if (alojamientoEncontrado instanceof Hotel) {
                Hotel hotel = (Hotel) alojamientoEncontrado;
                List<Habitacion> disponibles = hotel.getHabitaciones().stream()
                        .filter(Habitacion::isDisponible)
                        .toList();

                if (disponibles.isEmpty()) {
                    System.out.println("No hay habitaciones disponibles en este alojamiento.");
                    return;
                }

                System.out.println("Habitaciones disponibles:");
                for (int i = 0; i < disponibles.size(); i++) {
                    Habitacion habitacion = disponibles.get(i);
                    System.out.println((i + 1) + ". " + habitacion);
                }

                System.out.print("Ingrese la cantidad de habitaciones que desea reservar: ");
                int cantidadHabitaciones = Integer.parseInt(scanner.nextLine());

                if (cantidadHabitaciones > disponibles.size()) {
                    System.out.println("No hay suficientes habitaciones disponibles para la cantidad solicitada.");
                    return;
                }

                for (int i = 0; i < cantidadHabitaciones; i++) {
                    System.out.print("Seleccione el número de la habitación " + (i + 1) + ": ");
                    int seleccion = Integer.parseInt(scanner.nextLine());

                    if (seleccion < 1 || seleccion > disponibles.size()) {
                        System.out.println("Selección inválida. Intente nuevamente.");
                        i--;
                        continue;
                    }

                    Habitacion habitacion = disponibles.get(seleccion - 1);
                    if (habitacionesSeleccionadas.contains(habitacion)) {
                        System.out.println("Habitación ya seleccionada. Elija otra.");
                        i--;
                        continue;
                    }

                    habitacionesSeleccionadas.add(habitacion);
                }
            }

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
            double precioTotal = 0.0;
            if (alojamientoEncontrado instanceof Hotel) {
                long dias = fechaFin.toEpochDay() - fechaInicio.toEpochDay();
                for (Habitacion habitacion : habitacionesSeleccionadas) {
                    precioTotal += alojamientoEncontrado.getPrecioPorNoche() * dias;
                }
            } else {
                precioTotal = alojamientoEncontrado.calcularPrecioTotal(fechaInicio, fechaFin);
            }

            double porcentaje = alojamientoEncontrado.tarifaPorTemporada(fechaInicio, fechaFin);
            double precioFinal = CalculadorTarifaTemporada.aplicarPorcentaje(precioTotal, porcentaje);
            System.out.println("Precio total de la reserva: " + precioFinal + " (Porcentaje aplicado: " + porcentaje + "%)");

            System.out.print("¿Desea confirmar la reserva? (s/n): ");
            String confirmacion = scanner.nextLine();
            if (!confirmacion.equalsIgnoreCase("s")) {
                System.out.println("Reserva cancelada.");
                return;
            }

            // Confirmar la reserva
            if (alojamientoEncontrado instanceof Hotel) {
                for (Habitacion habitacion : habitacionesSeleccionadas) {
                    alojamientoController.confirmarReserva(alojamientoEncontrado, habitacion, fechaInicio, fechaFin,
                            cantidadAdultos, cantidadNinos, nombre, apellido, email, nacionalidad, telefono, horaLlegada);
                }
                System.out.println("Se ha realizado la reserva con éxito para " + habitacionesSeleccionadas.size() + " habitación(es).");
            } else {
                reservaController.guardarReserva(new Reserva(alojamientoEncontrado, null, fechaInicio, fechaFin,
                        cantidadAdultos, cantidadNinos, nombre, apellido, email, nacionalidad, telefono, horaLlegada));
                System.out.println("Se ha realizado la reserva con éxito.");
            }
        } catch (Exception e) {
        System.out.println("Error al actualizar la reserva: " + e.getMessage());
    }

        // Aquí estaba el método actualizarReserva, pero estaba anidado dentro de confirmarReserva
        // Lo movemos fuera para que esté al mismo nivel que buscarAlojamientos y confirmarReserva
    }

    private static void actualizarReserva(ReservaController reservaController, Scanner scanner) {
        try {
            System.out.print("Ingrese su email para actualizar la reserva: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese su apellido para autenticación: ");
            String apellido = scanner.nextLine();

            Reserva reserva = reservaController.buscarReservaPorEmailYApellido(email, apellido);

            if (reserva == null) {
                System.out.println("Autenticación fallida. No se encontró la reserva.");
                return;
            }

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

            System.out.println("¿Qué desea actualizar?");
            System.out.println("1. Cambiar habitación");
            System.out.println("2. Cambiar alojamiento");
            System.out.print("Elija una opción: ");
            int opcionActualizar = Integer.parseInt(scanner.nextLine());

            if (opcionActualizar == 1) {
                // Cambiar habitación
                if (!(reserva.getAlojamiento() instanceof Hotel)) {
                    System.out.println("El alojamiento seleccionado no tiene habitaciones para cambiar.");
                    return;
                }

                Hotel hotel = (Hotel) reserva.getAlojamiento();
                List<Habitacion> disponibles = hotel.getHabitaciones().stream()
                        .filter(Habitacion::isDisponible)
                        .toList();

                if (disponibles.isEmpty()) {
                    System.out.println("No hay habitaciones disponibles para cambiar.");
                    return;
                }

                System.out.println("Habitaciones disponibles:");
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
                if (reserva.getHabitacion() != null) {
                    reserva.getHabitacion().setDisponible(true);
                    reservaController.getAlojamientoService().getHabitacionService().actualizarDisponibilidad(
                            reserva.getAlojamiento().getNombre(),
                            reserva.getHabitacion().getTipo(),
                            true
                    );
                }

                // Asignar la nueva habitación
                nuevaHabitacion.setDisponible(false);
                reservaController.getAlojamientoService().getHabitacionService().actualizarDisponibilidad(
                        reserva.getAlojamiento().getNombre(),
                        nuevaHabitacion.getTipo(),
                        false
                );
                reserva.setHabitacion(nuevaHabitacion);

                // Actualizar la reserva en el archivo
                reservaController.actualizarReserva(reserva);
                System.out.println("Habitación actualizada correctamente.");

            } else if (opcionActualizar == 2) {
                // Cambiar alojamiento
                System.out.print("Ingrese el nuevo nombre del alojamiento: ");
                String nuevoNombreAlojamiento = scanner.nextLine();

                Alojamiento nuevoAlojamiento = reservaController.getAlojamientoService().buscarPorNombre(nuevoNombreAlojamiento);
                if (nuevoAlojamiento == null) {
                    System.out.println("Alojamiento no encontrado.");
                    return;
                }

                // Verificar si el nuevo alojamiento es un hotel y manejar habitaciones
                Habitacion nuevaHabitacion = null;
                if (nuevoAlojamiento instanceof Hotel) {
                    Hotel nuevoHotel = (Hotel) nuevoAlojamiento;
                    List<Habitacion> disponibles = nuevoHotel.getHabitaciones().stream()
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

                    nuevaHabitacion = disponibles.get(seleccion - 1);
                }

                // Liberar la habitación anterior si existía
                if (reserva.getHabitacion() != null) {
                    reserva.getHabitacion().setDisponible(true);
                    reservaController.getAlojamientoService().getHabitacionService().actualizarDisponibilidad(
                            reserva.getAlojamiento().getNombre(),
                            reserva.getHabitacion().getTipo(),
                            true
                    );
                }

                // Asignar el nuevo alojamiento y habitación
                reserva.setAlojamiento(nuevoAlojamiento);
                if (nuevaHabitacion != null) {
                    nuevaHabitacion.setDisponible(false);
                    reservaController.getAlojamientoService().getHabitacionService().actualizarDisponibilidad(
                            nuevoAlojamiento.getNombre(),
                            nuevaHabitacion.getTipo(),
                            false
                    );
                    reserva.setHabitacion(nuevaHabitacion);
                } else {
                    reserva.setHabitacion(null);
                }

                // Actualizar la reserva en el archivo
                reservaController.actualizarReserva(reserva);
                System.out.println("Alojamiento y habitación actualizados correctamente.");
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }
}

