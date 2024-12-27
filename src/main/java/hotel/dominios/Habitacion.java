package hotel.dominios;

public class Habitacion {
    private String nombreDelAlojamiento;
    private String tipo;
    private String contenido;
    private double precio;
    private Boolean disponible;

    public Habitacion(String nombreDelAlojamiento, String tipo, String contenido, double precio, Boolean disponible) {
        this.nombreDelAlojamiento = nombreDelAlojamiento;
        this.tipo = tipo;
        this.contenido = contenido;
        this.precio = precio;
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Tipo: '" + tipo + '\'' +
                ", Contenido: '" + contenido + '\'' +
                ", Precio: " + precio +
                ", Disponible: " + (disponible ? "Sí" : "No");
    }

    // Getters y Setters
    public String getNombreDelAlojamiento() {
        return nombreDelAlojamiento;
    }

    public void setNombreDelAlojamiento(String nombreDelAlojamiento) {
        this.nombreDelAlojamiento = nombreDelAlojamiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
