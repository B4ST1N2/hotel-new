package hotel.service;

import java.util.List;

public interface IGestionService<T> {
    void crearArchivo();
    List<T> obtenerDatos();
}
