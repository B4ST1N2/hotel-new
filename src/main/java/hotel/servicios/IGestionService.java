package hotel.servicios;

import hotel.dominios.Alojamiento;

import java.util.List;

public interface IGestionService<T> {

    void crearArchivo();
    List<T> obtenerDatos();
}
