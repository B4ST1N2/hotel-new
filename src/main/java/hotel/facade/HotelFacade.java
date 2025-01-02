// File: hotel/facade/HotelFacade.java
package hotel.facade;

import hotel.model.entity.Accommodation;
import hotel.model.entity.SearchCriteria;
import hotel.model.entity.Reservation;
import hotel.model.entity.ReservationDetails;
import hotel.model.entity.ReservationUpdate;
import hotel.service.AccommodationService;
import hotel.service.ReservationService;
import hotel.view.AccommodationView;
import hotel.view.ReservationView;

import java.util.List;

public class HotelFacade {
    private AccommodationService accommodationService;
    private ReservationService reservationService;
    private AccommodationView accommodationView;
    private ReservationView reservationView;

    public HotelFacade() {
        this.accommodationService = new AccommodationService();
        this.reservationService = new ReservationService(accommodationService);
        this.accommodationView = new AccommodationView();
        this.reservationView = new ReservationView();
    }

    public void buscarAlojamientos() {
        try {
            // Obtener criterios de búsqueda desde la vista (AccommodationView)
            SearchCriteria criteria = accommodationView.obtenerCriteriosBusqueda();

            // Buscar alojamientos usando el servicio
            List<Accommodation> resultados = accommodationService.buscarAlojamientos(criteria);

            // Mostrar resultados en la vista
            accommodationView.mostrarResultados(resultados);
        } catch (Exception e) {
            accommodationView.mostrarError("Error al buscar alojamientos: " + e.getMessage());
        }
    }

    public void confirmarReserva() {
        try {
            // Obtener detalles de la reserva desde la vista
            ReservationDetails detalles = reservationView.obtenerDetallesReserva();

            // Confirmar reserva usando el servicio
            String mensaje = reservationService.confirmarReserva(detalles);

            // Mostrar confirmación en la vista
            reservationView.mostrarMensaje(mensaje);
        } catch (Exception e) {
            reservationView.mostrarError("Error al confirmar la reserva: " + e.getMessage());
        }
    }

    public void actualizarReserva() {
        try {
            // Obtener detalles de la actualización desde la vista
            ReservationUpdate actualizacion = reservationView.obtenerActualizacionReserva();

            // Actualizar reserva usando el servicio
            String mensaje = reservationService.actualizarReserva(actualizacion);

            // Mostrar confirmación en la vista
            reservationView.mostrarMensaje(mensaje);
        } catch (Exception e) {
            reservationView.mostrarError("Error al actualizar la reserva: " + e.getMessage());
        }
    }
}
