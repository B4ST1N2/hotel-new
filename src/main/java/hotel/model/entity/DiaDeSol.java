// File: hotel/model/entity/DiaDeSol.java
package hotel.model.entity;

public class DiaDeSol extends Accommodation {
    private String[] actividades;
    private boolean incluyeRefrigerio;
    private double precioActividad;

    public DiaDeSol(String nombre, int calificacion, String ubicacion, String[] actividades, boolean incluyeRefrigerio, double precioActividad) {
        super("Día de Sol", nombre, calificacion, ubicacion, 0.0); // Precio inicial 0.0
        this.actividades = actividades;
        this.incluyeRefrigerio = incluyeRefrigerio;
        this.precioActividad = precioActividad;
    }

    public String[] getActividades() {
        return actividades;
    }

    public boolean isIncluyeRefrigerio() {
        return incluyeRefrigerio;
    }

    public double getPrecioActividad() {
        return precioActividad;
    }

    public void setActividades(String[] actividades) {
        this.actividades = actividades;
    }

    public void setIncluyeRefrigerio(boolean incluyeRefrigerio) {
        this.incluyeRefrigerio = incluyeRefrigerio;
    }

    public void setPrecioActividad(double precioActividad) {
        this.precioActividad = precioActividad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nActividades Disponibles:");
        for (String actividad : actividades) {
            sb.append("\n- ").append(actividad);
        }
        sb.append("\nIncluye Refrigerio: ").append(incluyeRefrigerio ? "Sí" : "No");
        sb.append("\nPrecio por Actividad: $").append(precioActividad);
        return sb.toString();
    }
}
