package hotel.repositories;
import hotel.model.entity.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomRepository {
    private static RoomRepository instance;
    private List<Room> habitaciones;

    private RoomRepository() {
        habitaciones = new ArrayList<>();
    }

    public static synchronized RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

    public List<Room> getAllHabitaciones() {
        return Collections.unmodifiableList(habitaciones);
    }

    public void agregarHabitacion(Room habitacion) {
        habitaciones.add(habitacion);
    }

    public Room buscarPorContenido(String contenido) {
        for (Room habitacion : habitaciones) {
            if (habitacion.getContenido().equalsIgnoreCase(contenido)) {
                return habitacion;
            }
        }
        return null;
    }

    public boolean eliminarHabitacion(Room habitacion) {
        return habitaciones.remove(habitacion);
    }
}
