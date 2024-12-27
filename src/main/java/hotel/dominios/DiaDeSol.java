package hotel.dominios;

import hotel.utilidades.CalculadorPrecioTotal;
import java.time.LocalDate;

public class DiaDeSol extends Alojamiento {

    private String actividades;
    private boolean refrigerio;
    private double precio;

    public DiaDeSol(String tipo, String nombre, int calificacion, String ciudad, String actividades, boolean refrigerio, double precio) {
        super(tipo, nombre, calificacion, ciudad);
        this.actividades = actividades;
        this.refrigerio = refrigerio;
        this.precio = precio;
    }

    @Override
    public double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin) {
        return CalculadorPrecioTotal.calcularPrecioTotal(diaInicio, diaFin, this.getPrecio());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Actividades: '" + actividades + '\'' +
                ", Incluye refrigerio: " + (refrigerio ? "Sí" : "No") +
                ", Precio por actividad: " + precio;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public boolean isRefrigerio() {
        return refrigerio;
    }

    public void setRefrigerio(boolean refrigerio) {
        this.refrigerio = refrigerio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
