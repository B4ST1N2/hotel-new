package hotel.service.reservas;

import hotel.model.entity.Alojamiento;


public class AlojamientoReservado {

    public Boolean datosReserva( Alojamiento alojamiento,String[] datos){

        Boolean respuesta = true;
        String nombreAlojamiento = datos[0];

        if (alojamiento == null) {
            System.out.println("Alojamiento no encontrado para la reserva: " + nombreAlojamiento);
            respuesta = false;
        }

        return respuesta;
    }
}
