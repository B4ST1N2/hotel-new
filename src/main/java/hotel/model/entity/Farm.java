package hotel.model.entity;

import java.time.LocalDate;

public class Farm extends Accommodation {
    private int cantidadAnimales;
    private String estado;

    public Farm(String tipo, String nombre, int estrellas, String ubicacion, int cantidadAnimales, String estado, double precioPorNoche) {
        super(tipo, nombre, estrellas, ubicacion, precioPorNoche);
        this.cantidadAnimales = cantidadAnimales;
        this.estado = estado;
    }

    public int getCantidadAnimales() {
        return cantidadAnimales;
    }

    public String getEstado() {
        return estado;
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
