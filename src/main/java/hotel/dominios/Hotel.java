package hotel.dominios;

import java.util.List;

public class Hotel extends Alojamiento {

    private double precioNoche;

    public Hotel(String tipo, String nombre, int calificacion, String ciudad, List<Habitacion> habitaciones, double precioNoche) {
        super(tipo, nombre, calificacion, ciudad, habitaciones);
        this.precioNoche = precioNoche;
    }

    @Override
    public String toString() {
        return super.toString() + ", Precio por noche: " + precioNoche;
    }

    @Override
    public double getPrecioPorNoche() {
        return this.precioNoche;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }
}
