package hotel.utilidades;

import java.time.LocalDate;

public class CalculadorPorcentaje {

    public static double calcularPorcentaje(int inicio, int fin){

        double porcentaje = 0;

        if (inicio <= 15 && fin >= 10) {
            porcentaje += 10.0;
        }

        if (inicio <= 10 && fin >= 5) {
            porcentaje -= 8.0;
        }

        return porcentaje;
    }
}
