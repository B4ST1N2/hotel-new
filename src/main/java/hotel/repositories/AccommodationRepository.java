package hotel.repositories;

import hotel.model.entity.*;
import hotel.model.entity.Accommodation;
import hotel.model.entity.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccommodationRepository {
    private static AccommodationRepository instance;
    private List<Accommodation> accommodations;

    private AccommodationRepository() {
        accommodations = new ArrayList<>();
        Hotel hotel = new Hotel("Hotel", "Hotel Lux", 5, "Medellin", 200.0);
        hotel.addHabitacion(new Room("Suite", "Suite Deluxe", true));
        hotel.addHabitacion(new Room("Standard", "Standard Room", true));
        accommodations.add(hotel);
        accommodations.add(new Apartment("Apartment", "Apart Central", 5, "Bogota", 180.0));
        accommodations.add(new Farm("Farm", "Finca Verde", 3, "Valencia", 6, "Disponible", 220.0));
    }

    public static synchronized AccommodationRepository getInstance() {
        if (instance == null) {
            instance = new AccommodationRepository();
        }
        return instance;
    }

    public List<Accommodation> getAllAccommodations() {
        return Collections.unmodifiableList(accommodations);
    }

    public Accommodation buscarPorNombre(String nombreAlojamiento) {
        for (Accommodation alojamiento : accommodations) {
            if (alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento)) {
                return alojamiento;
            }
        }
        return null;
    }

        public boolean agregarAlojamiento(Accommodation alojamiento) {
        if (buscarPorNombre(alojamiento.getNombre()) != null) {
            return false;
        }
        accommodations.add(alojamiento);
        return true;
    }

    public boolean actualizarAlojamiento(Accommodation alojamiento) {
        for (int i = 0; i < accommodations.size(); i++) {
            if (accommodations.get(i).getNombre().equalsIgnoreCase(alojamiento.getNombre())) {
                accommodations.set(i, alojamiento);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarAlojamiento(String nombreAlojamiento) {
        Accommodation alojamiento = buscarPorNombre(nombreAlojamiento);
        if (alojamiento != null) {
            accommodations.remove(alojamiento);
            return true;
        }
        return false;
    }
}
