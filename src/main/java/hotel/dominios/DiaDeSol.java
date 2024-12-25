package hotel.dominios;

import java.util.List;

public class DiaDeSol extends Alojamiento{

    private String actividades;
    private boolean refrigerio;
    private double precio;

    public DiaDeSol(String tipo,  String nombre, int calificacion,String ciudad, List<Habitacion> habitaciones,String actividades, boolean refrigerio, double precio) {
        super(tipo, nombre, calificacion, ciudad, habitaciones);
        this.actividades = actividades;
        this.refrigerio = refrigerio;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", actividades='" + actividades + '\'' +
                ", refrigerio=" + refrigerio +
                ", precio=" + precio ;

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
