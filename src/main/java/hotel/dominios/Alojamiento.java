package hotel.dominios;

import java.time.LocalDate;
import java.util.List;

public abstract class Alojamiento {
    private String tipo;
    private String nombre;
    private int calificacion;
    private String ciudad;
    private List<Habitacion> habitaciones;


    public Alojamiento(String tipo,String nombre,int calificacion,String ciudad, List<Habitacion> habitaciones) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.ciudad = ciudad;
        this.habitaciones = habitaciones;
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

    @Override
    public String toString() {
        return
                "tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", calificacion=" + calificacion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", habitaciones=" + habitaciones ;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
