// File: hotel/repositories/AccommodationRepository.java
package hotel.repositories;

import hotel.model.entity.Accommodation;
import hotel.model.entity.Apartment;
import hotel.model.entity.DiaDeSol;
import hotel.model.entity.Farm;
import hotel.model.entity.Hotel;
import hotel.model.entity.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccommodationRepository {
    private static AccommodationRepository instance;
    private List<Accommodation> accommodations;

    private AccommodationRepository() {
        accommodations = new ArrayList<>();
        inicializarDatos();
    }

    public static synchronized AccommodationRepository getInstance() {
        if (instance == null) {
            instance = new AccommodationRepository();
        }
        return instance;
    }

    private void inicializarDatos() {
        // Agregar hoteles de ejemplo
        Hotel hotel1 = new Hotel("Hotel Lux", 5, "Medellín");
        hotel1.addHabitacion(new Room("Suite", "Suite Deluxe con vista al mar, aire acondicionado, cafetera, TV de pantalla plana, ducha y escritorio.", true, 250.0));
        hotel1.addHabitacion(new Room("Standard", "Habitación estándar con 2 camas dobles, aire acondicionado, cafetera, TV, baño privado.", true, 150.0));
        hotel1.addHabitacion(new Room("Deluxe", "Habitación deluxe con 1 cama king size, vista panorámica, aire acondicionado, minibar, TV de pantalla plana.", true, 200.0));
        hotel1.addHabitacion(new Room("Junior Suite", "Junior Suite con sala de estar, 2 camas individuales, aire acondicionado, cafetera, TV y escritorio.", true, 180.0));
        hotel1.addHabitacion(new Room("Presidential Suite", "Presidential Suite con múltiples habitaciones, jacuzzi, aire acondicionado, cafeteras, TV de pantalla grande.", true, 300.0));
        accommodations.add(hotel1);

        // Agregar apartamentos de ejemplo
        Apartment apartment1 = new Apartment("Apart Central", 4, "Bogotá", 150.0);
        accommodations.add(apartment1);

        // Agregar fincas de ejemplo
        String[] actividadesFinca = {"Paseo a caballo", "Pesca deportiva", "Excursión en bicicleta"};
        Farm finca1 = new Farm("Finca Verde", 3, "Valencia", actividadesFinca, true, 180.0);
        accommodations.add(finca1);

        // Agregar Día de Sol de ejemplo
        String[] actividadesDiaDeSol = {"Natación", "Tiro con arco", "Yoga al aire libre"};
        DiaDeSol diaDeSol1 = new DiaDeSol("Sol y Mar", 4, "Cartagena", actividadesDiaDeSol, true, 120.0);
        accommodations.add(diaDeSol1);
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
