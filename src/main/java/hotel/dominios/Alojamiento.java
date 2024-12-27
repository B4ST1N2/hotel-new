package hotel.dominios;

import hotel.utilidades.CalculadorPrecioTotal;
import hotel.utilidades.CalculadorTarifaTemporada;

import java.time.LocalDate;

public abstract class Alojamiento {
    private String tipo;
    private String nombre;
    private int calificacion;
    private String ciudad;

    public Alojamiento(String tipo, String nombre, int calificacion, String ciudad) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.ciudad = ciudad;
    }

    public abstract double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin);

    public double tarifaPorTemporada(LocalDate diaInicio, LocalDate diaFin) {
        return CalculadorTarifaTemporada.calcularPorcentaje(diaInicio, diaFin);
    }

    @Override
    public String toString() {
        return
                "Tipo: '" + tipo + '\'' +
                        ", Nombre: '" + nombre + '\'' +
                        ", Calificación: " + calificacion +
                        ", Ciudad: '" + ciudad + '\'';
    }

    // Getters y Setters
    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
