// File: hotel/controller/AccommodationController.java
package hotel.controller;

import hotel.model.entity.Accommodation;
import hotel.model.entity.SearchCriteria;
import hotel.repositories.AccommodationRepository;

import java.util.ArrayList;
import java.util.List;

public class AccommodationController {
    private static AccommodationController instance;
    private AccommodationRepository accommodationRepository;

    private AccommodationController() {
        this.accommodationRepository = AccommodationRepository.getInstance();
    }

    public static synchronized AccommodationController getInstance() {
        if (instance == null) {
            instance = new AccommodationController();
        }
        return instance;
    }

    public List<Accommodation> buscarAlojamientos(SearchCriteria criteria) {
        List<Accommodation> alojamientos = accommodationRepository.getAllAccommodations();
        List<Accommodation> resultados = new ArrayList<>();

        for (Accommodation alojamiento : alojamientos) {
            if (alojamiento.getUbicacion().equalsIgnoreCase(criteria.getCiudad()) &&
                    alojamiento.getTipo().equalsIgnoreCase(criteria.getTipo())) {

                int capacidad = calcularCapacidad(alojamiento, criteria.getAdultos(), criteria.getNinos());
                int habitacionesDisponibles = contarHabitacionesDisponibles(alojamiento, criteria.getHabitacionesReq());

                if (capacidad >= (criteria.getAdultos() + criteria.getNinos()) &&
                        habitacionesDisponibles >= criteria.getHabitacionesReq()) {
                    resultados.add(alojamiento);
                }
            }
        }

        return resultados;
    }

    public Accommodation buscarPorNombre(String nombreAlojamiento) throws Exception {
        Accommodation alojamiento = accommodationRepository.buscarPorNombre(nombreAlojamiento);
        if (alojamiento == null) {
            throw new Exception("Alojamiento no encontrado.");
        }
        return alojamiento;
    }

    private int calcularCapacidad(Accommodation alojamiento, int adultos, int ninos) {
        // Asumiendo una lógica simplificada para la capacidad
        // Puedes ajustar según las necesidades
        return adultos + ninos;
    }

    private int contarHabitacionesDisponibles(Accommodation alojamiento, int habitacionesReq) {
        if (alojamiento instanceof hotel.model.entity.Hotel) {
            hotel.model.entity.Hotel hotel = (hotel.model.entity.Hotel) alojamiento;
            int disponibles = 0;
            for (hotel.model.entity.Room habitacion : hotel.getHabitaciones()) {
                if (habitacion.isDisponible()) {
                    disponibles++;
                }
            }
            return disponibles;
        }
        // Para otros tipos de alojamiento, asumimos que tienen una sola "habitacion"
        return 1;
    }
}
