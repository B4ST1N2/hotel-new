package hotel.dominios;

import hotel.utilidades.CalculadorPrecioTotal;
import hotel.utilidades.CalculadorTarifaTemporada;

import java.time.LocalDate;

public class Apartamento extends Alojamiento {

    private double precioNoche;

    public Apartamento(String tipo, String nombre, int calificacion, String ciudad, double precioNoche) {
        super(tipo, nombre, calificacion, ciudad);
        this.precioNoche = precioNoche;
    }

    @Override
    public double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin) {
        return CalculadorPrecioTotal.calcularPrecioTotal(diaInicio, diaFin, this.getPrecioNoche());
    }

    @Override
    public String toString() {
        return super.toString() + ", Precio por noche: " + precioNoche;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }
}
