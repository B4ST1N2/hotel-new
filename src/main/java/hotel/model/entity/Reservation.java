// File: hotel/model/entity/Reservation.java
package hotel.model.entity;

import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private String nombre;
    private String apellido;
    private String email;
    private String fechaNacimiento; // Nuevo campo agregado
    private String nacionalidad;
    private String telefono;
    private String horaLlegada;
    private Accommodation alojamiento;
    private List<Room> habitaciones;
    private LocalDate diaInicio;
    private LocalDate diaFin;
    private int cantidadAdultos;
    private int cantidadNinos;

    public Reservation(String nombre, String apellido, String email, String fechaNacimiento, String nacionalidad,
                       String telefono, String horaLlegada, Accommodation alojamiento,
                       List<Room> habitaciones, LocalDate diaInicio, LocalDate diaFin,
                       int cantidadAdultos, int cantidadNinos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.horaLlegada = horaLlegada;
        this.alojamiento = alojamiento;
        this.habitaciones = habitaciones;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.cantidadAdultos = cantidadAdultos;
        this.cantidadNinos = cantidadNinos;
    }

    // Getters y Setters para fechaNacimiento
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Otros Getters y Setters existentes...

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
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

    public Accommodation getAlojamiento() {
        return alojamiento;
    }

    public List<Room> getHabitaciones() {
        return habitaciones;
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

    public void setAlojamiento(Accommodation alojamiento) {
        this.alojamiento = alojamiento;
    }

    public void setHabitaciones(List<Room> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setDiaInicio(LocalDate diaInicio) {
        this.diaInicio = diaInicio;
    }

    public void setDiaFin(LocalDate diaFin) {
        this.diaFin = diaFin;
    }

    public void setCantidadAdultos(int cantidadAdultos) {
        this.cantidadAdultos = cantidadAdultos;
    }

    public void setCantidadNinos(int cantidadNinos) {
        this.cantidadNinos = cantidadNinos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Reserva de " + nombre + " " + apellido);
        sb.append("\nEmail: ").append(email);
        sb.append("\nFecha de Nacimiento: ").append(fechaNacimiento); // Mostrar fecha de nacimiento
        sb.append("\nNacionalidad: ").append(nacionalidad);
        sb.append("\nTeléfono: ").append(telefono);
        sb.append("\nHora de Llegada: ").append(horaLlegada);
        sb.append("\nAlojamiento: ").append(alojamiento.getNombre());
        sb.append("\nHabitaciones Reservadas:");
        for (Room habitacion : habitaciones) {
            sb.append("\n- ").append(habitacion.getTipo()).append(": $").append(habitacion.getCostPerNight());
        }
        sb.append("\nFechas: ").append(diaInicio).append(" a ").append(diaFin);
        sb.append("\nAdultos: ").append(cantidadAdultos);
        sb.append("\nNiños: ").append(cantidadNinos);
        return sb.toString();
    }
}
