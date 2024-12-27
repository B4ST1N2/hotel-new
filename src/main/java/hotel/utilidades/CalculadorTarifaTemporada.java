package hotel.utilidades;

import java.time.LocalDate;

public class CalculadorTarifaTemporada {
    public static double calcularPorcentaje(LocalDate diaInicio, LocalDate diaFin) {
        double porcentaje = 0.0;

        int inicio = diaInicio.getDayOfMonth();
        int fin = diaFin.getDayOfMonth();
        int ultimoDiaMes = diaInicio.lengthOfMonth();


        if (inicio >= (ultimoDiaMes - 4) || fin >= (ultimoDiaMes - 4)) {
            porcentaje += 15.0;
        }

        // Verificar si la estadía incluye días del 10 al 15
        if (inicio <= 15 && fin >= 10) {
            porcentaje += 10.0;
        }

        // Verificar si la estadía incluye días del 5 al 10
        if (inicio <= 10 && fin >= 5) {
            porcentaje -= 8.0;
        }

        return porcentaje;
    }

    public static double aplicarPorcentaje(double precioBase, double porcentaje) {
        return precioBase + (precioBase * (porcentaje / 100));
    }




}
