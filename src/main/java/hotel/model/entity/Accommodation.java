package hotel.model.entity;

import java.time.LocalDate;

public abstract class Accommodation {
    private String tipo;
    private String nombre;
    private int estrellas;
    private String ubicacion;
    private double precioPorNoche;

    public Accommodation(String tipo, String nombre, int estrellas, String ubicacion, double precioPorNoche) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.estrellas = estrellas;
        this.ubicacion = ubicacion;
        this.precioPorNoche = precioPorNoche;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public abstract double calcularPrecioTotal(LocalDate fechaInicio, LocalDate fechaFin);

    public abstract double tarifaPorTemporada(LocalDate fechaInicio, LocalDate fechaFin);
}
