package hotel.repositories;

import hotel.model.entity.Reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservationRepository {
    private static ReservationRepository instance;
    private List<Reservation> reservas;

    private ReservationRepository() {
        reservas = new ArrayList<>();
    }

    public static synchronized ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepository();
        }
        return instance;
    }

    public List<Reservation> getAllReservas() {
        return Collections.unmodifiableList(reservas);
    }

    public void agregarReserva(Reservation reserva) {
        reservas.add(reserva);
    }

    public Reservation buscarReservaPorEmailYApellido(String email, String apellido) {
        for (Reservation reserva : reservas) {
            if (reserva.getEmail().equalsIgnoreCase(email) && reserva.getApellido().equalsIgnoreCase(apellido)) {
                return reserva;
            }
        }
        return null;
    }

    public void actualizarReserva(Reservation reserva) {
    }

    public boolean eliminarReserva(Reservation reserva) {
        return reservas.remove(reserva);
    }
}
