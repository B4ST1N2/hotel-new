package hotel;

import hotel.dominios.Alojamiento;
import hotel.servicios.AlojamientoService;
import hotel.servicios.IAlojamientoService;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        IAlojamientoService alojamientos = new AlojamientoService();
        List<Alojamiento> todosLosAlojamientos;
        todosLosAlojamientos = alojamientos.obtenerAlojamientos();
        for(Alojamiento e:todosLosAlojamientos){
            System.out.println(e);
        }

    }
}