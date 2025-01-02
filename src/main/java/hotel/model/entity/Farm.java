// File: hotel/model/entity/Farm.java
package hotel.model.entity;

public class Farm extends Accommodation {
    private String[] actividades; // Lista de actividades disponibles
    private boolean incluyeAlmuerzo;
    private double precioActividad;

    public Farm(String nombre, int calificacion, String ubicacion, String[] actividades, boolean incluyeAlmuerzo, double precioActividad) {
        super("Finca", nombre, calificacion, ubicacion, 0.0); // Precio inicial 0.0
        this.actividades = actividades;
        this.incluyeAlmuerzo = incluyeAlmuerzo;
        this.precioActividad = precioActividad;
    }

    public String[] getActividades() {
        return actividades;
    }

    public boolean isIncluyeAlmuerzo() {
        return incluyeAlmuerzo;
    }

    public double getPrecioActividad() {
        return precioActividad;
    }

    public void setActividades(String[] actividades) {
        this.actividades = actividades;
    }

    public void setIncluyeAlmuerzo(boolean incluyeAlmuerzo) {
        this.incluyeAlmuerzo = incluyeAlmuerzo;
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
        sb.append("\nIncluye Almuerzo: ").append(incluyeAlmuerzo ? "Sí" : "No");
        sb.append("\nPrecio por Actividad: $").append(precioActividad);
        return sb.toString();
    }
}
