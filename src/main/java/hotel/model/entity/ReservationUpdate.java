// File: hotel/model/entity/ReservationUpdate.java
package hotel.model.entity;

public class ReservationUpdate {
    private String email;
    private String fechaNacimiento; // Fecha de nacimiento para autenticación
    private String tipoCambio; // "Habitación" o "Alojamiento"
    private String tipoHabitacionActual; // Tipo de habitación a cambiar
    private String nuevoTipoHabitacion; // Nuevo tipo de habitación

    public ReservationUpdate(String email, String fechaNacimiento, String tipoCambio,
                             String tipoHabitacionActual, String nuevoTipoHabitacion) {
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoCambio = tipoCambio;
        this.tipoHabitacionActual = tipoHabitacionActual;
        this.nuevoTipoHabitacion = nuevoTipoHabitacion;
    }

    public String getEmail() {
        return email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public String getTipoHabitacionActual() {
        return tipoHabitacionActual;
    }

    public String getNuevoTipoHabitacion() {
        return nuevoTipoHabitacion;
    }
}
