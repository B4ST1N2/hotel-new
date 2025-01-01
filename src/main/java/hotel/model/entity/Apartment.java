package hotel.model.entity;

import java.time.LocalDate;

public class Apartment extends Accommodation {

    public Apartment(String tipo, String nombre, int estrellas, String ubicacion, double precioPorNoche) {
        super(tipo, nombre, estrellas, ubicacion, precioPorNoche);
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
