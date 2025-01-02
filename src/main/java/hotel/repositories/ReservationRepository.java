// File: hotel/repositories/ReservationRepository.java
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

    public Reservation buscarReservaPorEmail(String email) {
        for (Reservation reserva : reservas) {
            if (reserva.getEmail().equalsIgnoreCase(email)) {
                return reserva;
            }
        }
        return null;
    }

    // Nuevo método agregado
    public Reservation buscarReservaPorEmailYFechaNacimiento(String email, String fechaNacimiento) {
        for (Reservation reserva : reservas) {
            if (reserva.getEmail().equalsIgnoreCase(email) && reserva.getFechaNacimiento().equals(fechaNacimiento)) {
                return reserva;
            }
        }
        return null;
    }

    public void actualizarReserva(Reservation reserva) {
        // Las reservas son objetos mutables y ya están actualizadas en la lista.
    }

    public boolean eliminarReserva(Reservation reserva) {
        return reservas.remove(reserva);
    }
}
