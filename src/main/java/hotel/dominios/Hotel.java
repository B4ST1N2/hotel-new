package hotel.dominios;

import hotel.utilidades.CalculadorPrecioTotal;

import java.time.LocalDate;
import java.util.List;

public class Hotel extends Alojamiento {

    private double precioNoche;
    private List<Habitacion> habitaciones;

    public Hotel(String tipo, String nombre, int calificacion, String ciudad, List<Habitacion> habitaciones, double precioNoche) {
        super(tipo, nombre, calificacion, ciudad);
        this.habitaciones = habitaciones;
        this.precioNoche = precioHabitacionBarata();
    }

    public double precioHabitacionBarata() {
        /* !Deuda tecnica */
        return 0;
    };

    @Override
    public double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin) {
        return CalculadorPrecioTotal.calcularPrecioTotal(diaInicio,diaFin,getPrecioNoche());
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
