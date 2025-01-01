//package hotel.model.entity;
//
//import hotel.utilities.CalculateTotalPrice;
//import java.time.LocalDate;
//
//public class SunnyDay extends Accommodation {
//    private String actividades;
//    private boolean refrigerio;
//    private double precioActividad;
//    private int cantidadPersonas;
//    private Boolean disponibilidad;
//
//    public SunnyDay(String tipo, String nombre, int calificacion, String ciudad, String actividades, boolean refrigerio, double precioActividad, int cantidadPersonas, Boolean disponibilidad) {
//        super(tipo, nombre, calificacion, ciudad);
//        this.actividades = actividades;
//        this.refrigerio = refrigerio;
//        this.precioActividad = precioActividad;
//        this.cantidadPersonas = cantidadPersonas;
//        this.disponibilidad = disponibilidad;
//    }
//
//    @Override
//    public double getPrecioPorNoche() {
//        return this.precioActividad;
//    }
//
//    @Override
//    public double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin) {
//        return CalculateTotalPrice.calcularPrecioTotal(diaInicio, diaFin, this.precioActividad);
//    }
//
//    @Override
//    public String toString() {
//        return super.toString() +
//                ", Actividades: '" + actividades + '\'' +
//                ", Incluye refrigerio: " + (refrigerio ? "Sí" : "No") +
//                ", Precio por actividad: " + precioActividad +
//                ", Cantidad de personas: " + cantidadPersonas;
//    }
//
//    public String getActividades() {
//        return actividades;
//    }
//
//    public void setActividades(String actividades) {
//        this.actividades = actividades;
//    }
//
//    public int getCantidadPersonas() {
//        return cantidadPersonas;
//    }
//
//    public void setCantidadPersonas(int cantidadPersonas) {
//        this.cantidadPersonas = cantidadPersonas;
//    }
//
//    public Boolean getDisponibilidad() {
//        return disponibilidad;
//    }
//
//    public void setDisponibilidad(Boolean disponibilidad) {
//        this.disponibilidad = disponibilidad;
//    }
//
//    public double getPrecioActividad() {
//        return precioActividad;
//    }
//
//    public void setPrecioActividad(double precioActividad) {
//        this.precioActividad = precioActividad;
//    }
//
//    public boolean isRefrigerio() {
//        return refrigerio;
//    }
//
//    public void setRefrigerio(boolean refrigerio) {
//        this.refrigerio = refrigerio;
//    }
//}