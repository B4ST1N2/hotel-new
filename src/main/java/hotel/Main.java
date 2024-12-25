package hotel;

import hotel.dominios.Alojamiento;
import hotel.servicios.AlojamientoService;
import hotel.servicios.IGestionService;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        IGestionService alojamientos = new AlojamientoService();
        List<Alojamiento> todosLosAlojamientos;
        todosLosAlojamientos = alojamientos.obtenerDatos();
        for(Alojamiento e:todosLosAlojamientos){
            System.out.println(e);
        }

    }
}