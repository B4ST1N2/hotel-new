package hotel.dominios;

import java.util.List;

public class Hotel extends Alojamiento{

    private double precioNoche;

    public Hotel(String tipo, List<Habitacion> habitaciones, int calificacion, String nombre, double precioNoche) {
        super(tipo, habitaciones, calificacion, nombre);
        this.precioNoche = precioNoche;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }
}
