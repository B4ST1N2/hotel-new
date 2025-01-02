// File: hotel/model/entity/ReservationDetails.java
package hotel.model.entity;

import java.time.LocalDate;
import java.util.List;

public class ReservationDetails {
    private String nombreAlojamiento;
    private LocalDate diaInicio;
    private LocalDate diaFin;
    private int cantidadAdultos;
    private int cantidadNinos;
    private String nombre;
    private String apellido;
    private String email;
    private String fechaNacimiento; // Nuevo campo agregado
    private String nacionalidad;
    private String telefono;
    private String horaLlegada;
    private List<String> tiposHabitacion; // Tipos de habitación seleccionados

    public ReservationDetails(String nombreAlojamiento, LocalDate diaInicio, LocalDate diaFin,
                              int cantidadAdultos, int cantidadNinos, String nombre, String apellido,
                              String email, String fechaNacimiento, String nacionalidad, String telefono,
                              String horaLlegada, List<String> tiposHabitacion) {
        this.nombreAlojamiento = nombreAlojamiento;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.cantidadAdultos = cantidadAdultos;
        this.cantidadNinos = cantidadNinos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.horaLlegada = horaLlegada;
        this.tiposHabitacion = tiposHabitacion;
    }

    public String getNombreAlojamiento() {
        return nombreAlojamiento;
    }

    public LocalDate getDiaInicio() {
        return diaInicio;
    }

    public LocalDate getDiaFin() {
        return diaFin;
    }

    public int getCantidadAdultos() {
        return cantidadAdultos;
    }

    public int getCantidadNinos() {
        return cantidadNinos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public List<String> getTiposHabitacion() {
        return tiposHabitacion;
    }

    public void setTiposHabitacion(List<String> tiposHabitacion) {
        this.tiposHabitacion = tiposHabitacion;
    }
}
