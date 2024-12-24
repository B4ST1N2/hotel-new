package hotel.dominios;

import java.time.LocalDate;

public abstract class Alojamiento {
    private String tipo;
    private String nombre;
    private int calificacion;
    private double precioNoche;

    public Alojamiento(String tipo, String nombre, int calificacion, double precioNoche) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.precioNoche = precioNoche;
    }

    public double tarifaPorTemporada(LocalDate diaInicio, LocalDate diaFin) {
        double porcentaje = 0.0;

        int ultimoDiaMes = diaInicio.lengthOfMonth();
        int inicio = diaInicio.getDayOfMonth();
        int fin = diaFin.getDayOfMonth();

        if((inicio > (ultimoDiaMes-5) & (fin < ultimoDiaMes))){
            porcentaje = 15.0;
        }else if((10<=inicio)&(fin<=15)){
            porcentaje = 10.0;
        }else if((inicio>=5)&(fin<=10)){
            porcentaje = -8.0;
        }
        return porcentaje;
    };

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }
}
