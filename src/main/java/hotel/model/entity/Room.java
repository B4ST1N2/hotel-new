package hotel.model.entity;

import java.util.Objects;

public class Room {
    private String tipo;
    private String contenido;
    private boolean disponible;

    public Room(String tipo, String contenido, boolean disponible) {
        this.tipo = tipo;
        this.contenido = contenido;
        this.disponible = disponible;
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

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room that = (Room) o;

        return Objects.equals(contenido, that.contenido);
    }

    @Override
    public int hashCode() {
        return contenido != null ? contenido.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Contenido: " + contenido + ", Disponible: " + disponible;
    }
}
