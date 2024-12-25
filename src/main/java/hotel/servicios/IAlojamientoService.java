package hotel.servicios;

import hotel.dominios.Alojamiento;

import java.util.List;

public interface IAlojamientoService {
    List<Alojamiento> obtenerAlojamientos();
}
