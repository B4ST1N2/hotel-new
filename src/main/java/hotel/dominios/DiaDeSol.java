package hotel.dominios;

import java.util.List;

public class DiaDeSol extends Alojamiento{

    private String actividades;
    private boolean refrigerio;
    private double precio;

    public DiaDeSol(String tipo, List<Habitacion> habitaciones, int calificacion, String nombre, String actividades, boolean refrigerio, double precio) {
        super(tipo, habitaciones, calificacion, nombre);
        this.actividades = actividades;
        this.refrigerio = refrigerio;
        this.precio = precio;
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
