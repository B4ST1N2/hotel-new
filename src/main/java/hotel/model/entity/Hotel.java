// File: hotel/model/entity/Hotel.java
package hotel.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends Accommodation {
    private List<Room> habitaciones;

    public Hotel(String nombre, int calificacion, String ubicacion) {
        super("Hotel", nombre, calificacion, ubicacion, 0.0); // Precio inicial 0.0
        this.habitaciones = new ArrayList<>();
    }

    public List<Room> getHabitaciones() {
        return habitaciones;
    }

    public void addHabitacion(Room habitacion) {
        this.habitaciones.add(habitacion);
        actualizarPrecioPorNoche();
    }

    public void removeHabitacion(Room habitacion) {
        this.habitaciones.remove(habitacion);
        actualizarPrecioPorNoche();
    }

    private void actualizarPrecioPorNoche() {
        double minimo = Double.MAX_VALUE;
        for (Room habitacion : habitaciones) {
            if (habitacion.getCostPerNight() < minimo) {
                minimo = habitacion.getCostPerNight();
            }
        }
        if (minimo != Double.MAX_VALUE) {
            super.setPrecioPorNoche(minimo);
        } else {
            super.setPrecioPorNoche(0.0);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nHabitaciones:");
        for (Room habitacion : habitaciones) {
            sb.append("\n-----------------------");
            sb.append("\n").append(habitacion.toString());
        }
        sb.append("\n-----------------------");
        sb.append("\nPrecio por noche del hotel (basado en la habitación más barata): $").append(getPrecioPorNoche());
        return sb.toString();
    }
}
