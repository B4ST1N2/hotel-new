package hotel.dominios;

import hotel.utilidades.CalculadorPrecios;

import java.time.LocalDate;
import java.util.List;

public abstract class Alojamiento {
    private String tipo;
    private String nombre;
    private int calificacion;
    private String ciudad;
    private List<Habitacion> habitaciones;

    public Alojamiento(String tipo, String nombre, int calificacion, String ciudad, List<Habitacion> habitaciones) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.ciudad = ciudad;
        this.habitaciones = habitaciones;
    }


    public double tarifaPorTemporada(LocalDate diaInicio, LocalDate diaFin) {
        return CalculadorPrecios.calcularPorcentaje(diaInicio, diaFin);
    }

    public double calcularPrecioTotal(LocalDate diaInicio, LocalDate diaFin, int cantidadHabitaciones) {
        int dias = calcularDias(diaInicio, diaFin);
        double precioBase = getPrecioPorNoche() * cantidadHabitaciones * dias;
        double porcentaje = tarifaPorTemporada(diaInicio, diaFin);
        double precioFinal = CalculadorPrecios.aplicarPorcentaje(precioBase, porcentaje);
        return precioFinal;
    }

    private int calcularDias(LocalDate inicio, LocalDate fin) {
        return (int) (fin.toEpochDay() - inicio.toEpochDay());
    }

    public abstract double getPrecioPorNoche();

    @Override
    public String toString() {
        return
                "Tipo: '" + tipo + '\'' +
                        ", Nombre: '" + nombre + '\'' +
                        ", Calificación: " + calificacion +
                        ", Ciudad: '" + ciudad + '\'' +
                        ", \n\t Habitaciones: " + habitaciones;
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

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
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
