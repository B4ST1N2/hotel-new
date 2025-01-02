// File: hotel/controller/ReservationController.java
package hotel.controller;

import hotel.model.entity.Accommodation;
import hotel.model.entity.Hotel;
import hotel.model.entity.Reservation;
import hotel.model.entity.ReservationDetails;
import hotel.model.entity.ReservationUpdate;
import hotel.model.entity.Room;
import hotel.repositories.ReservationRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private static ReservationController instance;
    private ReservationRepository reservationRepository;
    private AccommodationController accommodationController;

    private ReservationController(AccommodationController accommodationController) {
        this.reservationRepository = ReservationRepository.getInstance();
        this.accommodationController = accommodationController;
    }

    public static synchronized ReservationController getInstance(AccommodationController accommodationController) {
        if (instance == null) {
            instance = new ReservationController(accommodationController);
        }
        return instance;
    }

    public String confirmarReserva(ReservationDetails detalles) throws Exception {
        // Validar fechas
        if (detalles.getDiaInicio().isAfter(detalles.getDiaFin())) {
            throw new Exception("La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        // Buscar alojamiento por nombre
        Accommodation alojamiento = accommodationController.buscarPorNombre(detalles.getNombreAlojamiento());

        if (alojamiento == null) {
            throw new Exception("Alojamiento no encontrado o no disponible.");
        }

        List<Room> habitacionesSeleccionadas = new ArrayList<>();
        double precioTotalHabitaciones = 0.0;

        if (alojamiento instanceof Hotel) {
            Hotel hotel = (Hotel) alojamiento;
            for (String tipo : detalles.getTiposHabitacion()) {
                Room habitacion = seleccionarHabitacionDisponible(hotel, tipo);
                habitacionesSeleccionadas.add(habitacion);
                precioTotalHabitaciones += habitacion.getCostPerNight();
            }
        } else {
            // Solo se permiten reservas en hoteles
            throw new Exception("Solo se pueden reservar habitaciones en alojamientos tipo Hotel.");
        }

        // Calcular los días de estancia
        long dias = ChronoUnit.DAYS.between(detalles.getDiaInicio(), detalles.getDiaFin());
        if (dias <= 0) {
            throw new Exception("La cantidad de días debe ser mayor a cero.");
        }

        double costoSinAjustes = precioTotalHabitaciones * dias;
        double ajuste = calcularAjuste(detalles.getDiaInicio(), detalles.getDiaFin());
        double costoFinal = costoSinAjustes + ajuste;

        // Crear la reserva
        Reservation reserva = new Reservation(detalles.getNombre(), detalles.getApellido(),
                detalles.getEmail(), detalles.getFechaNacimiento(), detalles.getNacionalidad(),
                detalles.getTelefono(), detalles.getHoraLlegada(),
                alojamiento, habitacionesSeleccionadas,
                detalles.getDiaInicio(), detalles.getDiaFin(),
                detalles.getCantidadAdultos(), detalles.getCantidadNinos());

        reservationRepository.agregarReserva(reserva);

        // Retornar el mensaje de confirmación
        return "Se ha realizado la reserva con éxito. Costo total: $" + costoFinal +
                (ajuste > 0 ? " (Ajuste aplicado: $" + ajuste + ")" : ajuste < 0 ? " (Descuento aplicado: $" + (-ajuste) + ")" : "");
    }

    public String actualizarReserva(ReservationUpdate actualizacion) throws Exception {
        // Buscar reserva usando email y fecha de nacimiento
        Reservation reserva = reservationRepository.buscarReservaPorEmailYFechaNacimiento(actualizacion.getEmail(), actualizacion.getFechaNacimiento());
        if (reserva == null) {
            throw new Exception("Reserva no encontrada.");
        }

        // Mostrar datos de la reserva
        System.out.println("Datos actuales de la reserva:");
        System.out.println(reserva.toString());

        // Preguntar qué quiere cambiar
        if (actualizacion.getTipoCambio().equalsIgnoreCase("habitación")) {
            // Cambiar habitación
            Accommodation alojamiento = reserva.getAlojamiento();
            if (!(alojamiento instanceof Hotel)) {
                throw new Exception("El alojamiento no es un Hotel.");
            }
            Hotel hotel = (Hotel) alojamiento;

            String tipoActual = actualizacion.getTipoHabitacionActual();
            Room habitacionActual = null;
            for (Room hab : reserva.getHabitaciones()) {
                if (hab.getTipo().equalsIgnoreCase(tipoActual)) {
                    habitacionActual = hab;
                    break;
                }
            }
            if (habitacionActual == null) {
                throw new Exception("Habitación a cambiar no encontrada en la reserva.");
            }

            // Seleccionar nueva habitación
            Room nuevaHabitacion = seleccionarHabitacionDisponible(hotel, actualizacion.getNuevoTipoHabitacion());
            reserva.getHabitaciones().remove(habitacionActual);
            reserva.getHabitaciones().add(nuevaHabitacion);

            return "Reserva actualizada con éxito.";
        } else if (actualizacion.getTipoCambio().equalsIgnoreCase("alojamiento")) {
            // Cambiar alojamiento: eliminar la reserva y crear una nueva
            reservationRepository.eliminarReserva(reserva);
            return "Reserva eliminada. Por favor, realice una nueva reserva.";
        } else {
            throw new Exception("Tipo de cambio inválido.");
        }
    }

    private Room seleccionarHabitacionDisponible(Hotel hotel, String tipo) throws Exception {
        for (Room habitacion : hotel.getHabitaciones()) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && habitacion.isDisponible()) {
                habitacion.setDisponible(false);
                return habitacion;
            }
        }
        throw new Exception("No hay habitaciones disponibles del tipo '" + tipo + "' en el hotel seleccionado.");
    }

    private double calcularAjuste(LocalDate inicio, LocalDate fin) {
        boolean ultimoCincoDias = false;
        boolean diasDiezQuince = false;
        boolean diasCincoDiez = false;

        // Verificar si incluye los últimos 5 días del mes
        int diasDelMes = inicio.lengthOfMonth();
        for (int dia = inicio.getDayOfMonth(); dia <= fin.getDayOfMonth(); dia++) {
            if (dia > diasDelMes - 5) {
                ultimoCincoDias = true;
                break;
            }
        }

        // Verificar si incluye días del 10 al 15
        for (int dia = inicio.getDayOfMonth(); dia <= fin.getDayOfMonth(); dia++) {
            if (dia >= 10 && dia <= 15) {
                diasDiezQuince = true;
                break;
            }
        }

        // Verificar si incluye días del 5 al 10
        for (int dia = inicio.getDayOfMonth(); dia <= fin.getDayOfMonth(); dia++) {
            if (dia >= 5 && dia <= 10) {
                diasCincoDiez = true;
                break;
            }
        }

        // Calcular ajuste
        double ajuste = 0.0;
        if (ultimoCincoDias) {
            ajuste += 0.15; // Aumento del 15%
        }
        if (diasDiezQuince) {
            ajuste += 0.10; // Aumento del 10%
        }
        if (diasCincoDiez) {
            ajuste -= 0.08; // Descuento del 8%
        }
        return ajuste;
    }
}
