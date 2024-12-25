package hotel.dominios;

import java.time.LocalDate;
import java.util.List;

public abstract class Alojamiento {
    private String tipo;
    private String nombre;
    private int calificacion;
    private List<Habitacion> habitaciones;


    public Alojamiento(String tipo, List<Habitacion> habitaciones, int calificacion, String nombre) {
        this.tipo = tipo;
        this.habitaciones = habitaciones;
        this.calificacion = calificacion;
        this.nombre = nombre;
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

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
