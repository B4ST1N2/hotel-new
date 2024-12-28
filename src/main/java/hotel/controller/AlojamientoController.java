package hotel.controller;

import hotel.model.entity.Alojamiento;
import hotel.model.entity.Habitacion;
import hotel.service.alojamiento.AlojamientoService;

import java.time.LocalDate;
import java.util.List;

public class AlojamientoController {
    private AlojamientoService alojamientoService;

    public AlojamientoController(AlojamientoService alojamientoService) {
        this.alojamientoService = alojamientoService;
    }

    public List<String> buscarAlojamientos(String ciudad, String tipoAlojamiento, LocalDate diaInicio, LocalDate diaFin,
                                           int cantidadAdultos, int cantidadNinos, int cantidadHabitaciones) {

        return alojamientoService.buscarAlojamientos(ciudad, tipoAlojamiento, diaInicio, diaFin, cantidadAdultos, cantidadNinos, cantidadHabitaciones);
    }

    public Alojamiento buscarPorNombre(String nombre) {
        return alojamientoService.buscarPorNombre(nombre);
    }

    public void confirmarReserva(Alojamiento alojamiento, Habitacion habitacion, LocalDate diaInicio, LocalDate diaFin,
                                 int cantidadAdultos, int cantidadNinos, String nombre, String apellido,
                                 String email, String nacionalidad, String telefono, String horaLlegada) {
        alojamientoService.confirmarReserva(alojamiento, habitacion, diaInicio, diaFin,
                cantidadAdultos, cantidadNinos, nombre, apellido,
                email, nacionalidad, telefono, horaLlegada);
    }

    public AlojamientoService getAlojamientoService() {
        return alojamientoService;
    }

    public void setAlojamientoService(AlojamientoService alojamientoService) {
        this.alojamientoService = alojamientoService;
    }
}
