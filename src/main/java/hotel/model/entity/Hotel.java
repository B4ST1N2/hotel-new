package hotel.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Hotel extends Alojamiento {
    private List<Habitacion> habitaciones;

    public Hotel(String tipo, String nombre, int calificacion, String ciudad, List<Habitacion> habitaciones) {
        super(tipo, nombre, calificacion, ciudad);
        this.habitaciones = habitaciones;
    }

    @Override
    public double getPrecioPorNoche() {
        // Encuentra el precio de la habitación más barata
        Optional<Habitacion> cheapest = habitaciones.stream()
                .min((h1, h2) -> Double.compare(h1.getPrecio(), h2.getPrecio()));
        return cheapest.map(Habitacion::getPrecio).orElse(0.0);
    }

    @Override
    public double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin) {
        // La lógica de cálculo se manejará en el servicio
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Precio por noche (Habitación más barata): " + getPrecioPorNoche();
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}
