package hotel.controller;

import hotel.model.entity.Accommodation;
import hotel.model.entity.Reservation;
import hotel.model.entity.Room;
import hotel.repositories.ReservationRepository;

import java.time.LocalDate;

public class ReservationController {
    private ReservationRepository reservationRepository;
    private AccommodationController alojamientoController;

    public ReservationController(AccommodationController alojamientoController) {
        this.reservationRepository = ReservationRepository.getInstance();
        this.alojamientoController = alojamientoController;
    }

    public void guardarReserva(Reservation reserva) {
        reservationRepository.agregarReserva(reserva);
    }

    public Reservation buscarReservaPorEmailYApellido(String email, String apellido) {
        return reservationRepository.buscarReservaPorEmailYApellido(email, apellido);
    }

    public void confirmarReserva(Accommodation alojamiento, Room habitacion, LocalDate diaInicio, LocalDate diaFin,
                                 int cantidadAdultos, int cantidadNinos, String nombre, String apellido, String email,
                                 String nacionalidad, String telefono, String horaLlegada) {
        Reservation reserva = new Reservation(alojamiento, habitacion, diaInicio, diaFin, cantidadAdultos, cantidadNinos,
                nombre, apellido, email, nacionalidad, telefono, horaLlegada);
        reservationRepository.agregarReserva(reserva);
    }

    public void actualizarReserva(Reservation reserva) {
        reservationRepository.actualizarReserva(reserva);
    }

    public AccommodationController getAlojamientoController() {
        return alojamientoController;
    }
}
