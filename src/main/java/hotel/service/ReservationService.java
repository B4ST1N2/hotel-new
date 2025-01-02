// File: hotel/service/ReservationService.java
package hotel.service;

import hotel.controller.ReservationController;
import hotel.model.entity.Reservation;
import hotel.model.entity.ReservationDetails;
import hotel.model.entity.ReservationUpdate;

public class ReservationService {
    private ReservationController reservationController;

    public ReservationService(AccommodationService accommodationService) {
        this.reservationController = ReservationController.getInstance(accommodationService.getAccommodationController());
    }

    public String confirmarReserva(ReservationDetails detalles) throws Exception {
        return reservationController.confirmarReserva(detalles);
    }

    public String actualizarReserva(ReservationUpdate actualizacion) throws Exception {
        return reservationController.actualizarReserva(actualizacion);
    }

}
