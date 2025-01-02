// File: hotel/model/entity/SearchCriteria.java
package hotel.model.entity;

import java.time.LocalDate;

public class SearchCriteria {
    private String ciudad;
    private String tipo;
    private LocalDate diaInicio;
    private LocalDate diaFin;
    private int adultos;
    private int ninos;
    private int habitacionesReq;

    public SearchCriteria(String ciudad, String tipo, LocalDate diaInicio, LocalDate diaFin, int adultos, int ninos, int habitacionesReq) {
        this.ciudad = ciudad;
        this.tipo = tipo;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.adultos = adultos;
        this.ninos = ninos;
        this.habitacionesReq = habitacionesReq;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getDiaInicio() {
        return diaInicio;
    }

    public LocalDate getDiaFin() {
        return diaFin;
    }

    public int getAdultos() {
        return adultos;
    }

    public int getNinos() {
        return ninos;
    }

    public int getHabitacionesReq() {
        return habitacionesReq;
    }
}
