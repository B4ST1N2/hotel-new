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

        porcentaje = CalculadorPorcentaje.calcularPorcentaje(inicio,fin);

        return porcentaje;
    }

    public static double aplicarPorcentaje(double precioBase, double porcentaje) {
        return precioBase + (precioBase * (porcentaje / 100));
    }




}
