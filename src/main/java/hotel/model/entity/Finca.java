package hotel.model.entity;

import hotel.utilidades.CalculadorPrecioTotal;
import java.time.LocalDate;

public class Finca extends Alojamiento {
    private double precioNoche;
    private int cantidadPersonas;
    private Boolean disponibilidad;

    public Finca(String tipo, String nombre, int calificacion, String ciudad, double precioNoche, int cantidadPersonas, Boolean disponibilidad) {
        super(tipo, nombre, calificacion, ciudad);
        this.precioNoche = precioNoche;
        this.cantidadPersonas = cantidadPersonas;
        this.disponibilidad = disponibilidad;
    }

    @Override
    public double getPrecioPorNoche() {
        return this.precioNoche;
    }

    @Override
    public double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin) {
        return CalculadorPrecioTotal.calcularPrecioTotal(diaInicio, diaFin, this.precioNoche);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Precio por noche: " + precioNoche +
                ", Cantidad de personas: " + cantidadPersonas;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }
}
