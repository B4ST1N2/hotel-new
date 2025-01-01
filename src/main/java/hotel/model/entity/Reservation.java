package hotel.model.entity;

import java.time.LocalDate;

public class Reservation {
    private Accommodation alojamiento;
    private Room habitacion;
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

    public Reservation(Accommodation alojamiento, Room habitacion, LocalDate diaInicio, LocalDate diaFin,
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

    public Accommodation getAlojamiento() {
        return alojamiento;
    }

        public void setAlojamiento(Accommodation alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Room getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Room habitacion) {
        this.habitacion = habitacion;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }
}
