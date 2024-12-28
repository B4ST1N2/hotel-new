package hotel.model.entity;

import java.time.LocalDate;

public class Reserva {
    private Alojamiento alojamiento;
    private Habitacion habitacion; // Puede ser null si no es un hotel
    private LocalDate diaInicio;
    private LocalDate diaFin;
    private int cantidadAdultos;
    private int cantidadNinos;
    private String nombre;
    private String apellido;
    private String email;
    private String nacionalidad;
    private String telefono;
    private String horaLlegada;

    public Reserva(Alojamiento alojamiento, Habitacion habitacion, LocalDate diaInicio, LocalDate diaFin,
                   int cantidadAdultos, int cantidadNinos, String nombre, String apellido, String email,
                   String nacionalidad, String telefono, String horaLlegada) {
        this.alojamiento = alojamiento;
        this.habitacion = habitacion;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.cantidadAdultos = cantidadAdultos;
        this.cantidadNinos = cantidadNinos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.horaLlegada = horaLlegada;
    }

    @Override
    public String toString() {
        return alojamiento.getNombre() + "," +
                (habitacion != null ? habitacion.getTipo() : "N/A") + "," +
                diaInicio + "," +
                diaFin + "," +
                cantidadAdultos + "," +
                cantidadNinos + "," +
                nombre + "," +
                apellido + "," +
                email + "," +
                nacionalidad + "," +
                telefono + "," +
                horaLlegada;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCantidadAdultos() {
        return cantidadAdultos;
    }

    public void setCantidadAdultos(int cantidadAdultos) {
        this.cantidadAdultos = cantidadAdultos;
    }

    public int getCantidadNinos() {
        return cantidadNinos;
    }

    public void setCantidadNinos(int cantidadNinos) {
        this.cantidadNinos = cantidadNinos;
    }

    public LocalDate getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(LocalDate diaFin) {
        this.diaFin = diaFin;
    }

    public LocalDate getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(LocalDate diaInicio) {
        this.diaInicio = diaInicio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}