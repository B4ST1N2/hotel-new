package hotel.utilidades;

import java.time.LocalDate;

public class CalculadorPrecioTotal {

    public  static double calcularPrecioTotal(LocalDate dayStart, LocalDate dayEnd,double precioNoche) {
        int dias = diasTotales(dayStart, dayEnd);
        double precioBase = precioNoche * dias;
        double porcentaje = CalculadorTarifaTemporada.calcularPorcentaje(dayStart, dayEnd);
        double precioFinal = CalculadorTarifaTemporada.aplicarPorcentaje(precioBase, porcentaje);
        return precioFinal;
    }

    public static int diasTotales(LocalDate dayStart, LocalDate dayEnd) {
        return (int) (dayEnd.toEpochDay() - dayStart.toEpochDay());
    }
}
