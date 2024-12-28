package hotel.controller;

import hotel.model.entity.Reserva;
import hotel.service.alojamiento.AlojamientoService;
import hotel.service.ReservaService;

public class ReservaController {
    private ReservaService reservaService;
    private AlojamientoService alojamientoService;

    public ReservaController(ReservaService reservaService, AlojamientoService alojamientoService) {
        this.reservaService = reservaService;
        this.alojamientoService = alojamientoService;
    }

    public void guardarReserva(Reserva reserva) {
        reservaService.guardarReserva(reserva);
    }

    public Reserva buscarReservaPorEmailYApellido(String email, String apellido) {
        return reservaService.buscarReservaPorEmailYApellido(email, apellido, alojamientoService);
    }

    public void actualizarReserva(Reserva reservaActualizada) {
        reservaService.actualizarReserva(reservaActualizada, alojamientoService);
    }

    public void eliminarReserva(Reserva reserva) {
        reservaService.eliminarReserva(reserva, alojamientoService);
    }

    public AlojamientoService getAlojamientoService() {
        return this.alojamientoService;
    }
}
