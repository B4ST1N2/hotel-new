package hotel.service.alojamiento;

import hotel.model.entity.Alojamiento;
import hotel.model.entity.Habitacion;
import hotel.model.entity.Hotel;
import hotel.utilidades.CalculadorTarifaTemporada;

import java.time.LocalDate;

public class BuscarHotel {

    public String hotelDisponible(Alojamiento alojamiento, int cantidadHabitaciones, LocalDate diaInicio, LocalDate diaFin){
        String resultado;
        Hotel hotel = (Hotel) alojamiento;
        long habitacionesDisponibles = hotel.getHabitaciones().stream()
                .filter(Habitacion::isDisponible)
                .count();
        if (habitacionesDisponibles >= cantidadHabitaciones) {
            long dias = diaFin.toEpochDay() - diaInicio.toEpochDay();
            double precioBase = alojamiento.getPrecioPorNoche() * cantidadHabitaciones * dias;
            double porcentaje = alojamiento.tarifaPorTemporada(diaInicio, diaFin);
            double precioFinal = CalculadorTarifaTemporada.aplicarPorcentaje(precioBase, porcentaje);

            StringBuilder info = new StringBuilder();
            info.append("Nombre: ").append(alojamiento.getNombre()).append("\n");
            info.append("Calificación: ").append(alojamiento.getCalificacion()).append("\n");
            info.append("Precio por noche (Habitación más barata): ").append(alojamiento.getPrecioPorNoche()).append("\n");
            info.append("Precio total: ").append(precioFinal).append("\n");
            info.append("Porcentaje aplicado: ").append(porcentaje).append("%\n");
            info.append("Habitaciones Disponibles: ").append(habitacionesDisponibles).append("\n");
            return resultado= info.toString();
        }
        return null;
    }
}
