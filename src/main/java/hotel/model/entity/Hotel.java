package hotel.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends Accommodation {
    private List<Room> habitaciones;

    public Hotel(String tipo, String nombre, int estrellas, String ubicacion, double precioPorNoche) {
        super(tipo, nombre, estrellas, ubicacion, precioPorNoche);
        this.habitaciones = new ArrayList<>();
    }

    public List<Room> getHabitaciones() {
        return habitaciones;
    }

    public void addHabitacion(Room habitacion) {
        habitaciones.add(habitacion);
    }

    @Override
    public double calcularPrecioTotal(LocalDate fechaInicio, LocalDate fechaFin) {
        long dias = fechaFin.toEpochDay() - fechaInicio.toEpochDay();
        return getPrecioPorNoche() * dias;
    }

    @Override
    public double tarifaPorTemporada(LocalDate fechaInicio, LocalDate fechaFin) {
        return 0.0;
    }
}
