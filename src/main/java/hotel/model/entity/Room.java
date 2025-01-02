// File: hotel/model/entity/Room.java
package hotel.model.entity;

public class Room {
    private String tipo;
    private String contenido;
    private boolean disponible;
    private double costPerNight;

    public Room(String tipo, String contenido, boolean disponible, double costPerNight) {
        this.tipo = tipo;
        this.contenido = contenido;
        this.disponible = disponible;
        this.costPerNight = costPerNight;
    }

    public String getTipo() {
        return tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public double getCostPerNight() {
        return costPerNight;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setCostPerNight(double costPerNight) {
        this.costPerNight = costPerNight;
    }

    @Override
    public String toString() {
        return "Tipo de Habitación: " + tipo +
                "\nContenido: " + contenido +
                "\nDisponible: " + (disponible ? "Sí" : "No") +
                "\nCosto por noche: $" + costPerNight;
    }
}
