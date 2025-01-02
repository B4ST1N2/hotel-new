// File: hotel/model/entity/Accommodation.java
package hotel.model.entity;

public abstract class Accommodation {
    private String nombre;
    private int calificacion; // Del 1 al 5
    private String ubicacion;
    private double precioPorNoche;
    private String tipo;

    public Accommodation(String tipo, String nombre, int calificacion, String ubicacion, double precioPorNoche) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.ubicacion = ubicacion;
        this.precioPorNoche = precioPorNoche;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nTipo: " + tipo +
                "\nCalificación: " + calificacion +
                "\nUbicación: " + ubicacion +
                "\nPrecio por noche: $" + precioPorNoche;
    }
}
