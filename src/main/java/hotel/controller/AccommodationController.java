package hotel.controller;

import hotel.model.entity.Accommodation;

import hotel.model.entity.Hotel;
import hotel.model.entity.Room;
import hotel.repositories.AccommodationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccommodationController {
    private AccommodationRepository accommodationRepository;

    public AccommodationController() {
        this.accommodationRepository = AccommodationRepository.getInstance();
    }

    public Accommodation buscarPorNombre(String nombreAlojamiento) {
        return accommodationRepository.buscarPorNombre(nombreAlojamiento);
    }

    public List<Accommodation> obtenerTodosLosAlojamientos() {
        return accommodationRepository.getAllAccommodations();
    }

    public boolean agregarAlojamiento(Accommodation alojamiento) {
        return accommodationRepository.agregarAlojamiento(alojamiento);
    }

    public boolean actualizarAlojamiento(Accommodation alojamiento) {
        return accommodationRepository.actualizarAlojamiento(alojamiento);
    }


    public List<String> buscarAlojamientos(String ciudad, String tipo, LocalDate diaInicio, LocalDate diaFin,
                                           int adultos, int ninos, int habitacionesReq) {
        List<String> resultados = new ArrayList<>();
        List<Accommodation> alojamientos = accommodationRepository.getAllAccommodations();

        for (Accommodation alojamiento : alojamientos) {
            if (alojamiento.getUbicacion().equalsIgnoreCase(ciudad) &&
                    alojamiento.getTipo().equalsIgnoreCase(tipo)) {

                int capacidad = calcularCapacidad(alojamiento, adultos, ninos);
                int habitacionesDisponibles = contarHabitacionesDisponibles(alojamiento, habitacionesReq);

                if (capacidad >= (adultos + ninos) && habitacionesDisponibles >= habitacionesReq) {
                    String info = formatAlojamientoInfo(alojamiento, diaInicio, diaFin);
                    resultados.add(info);
                }
            }
        }

        return resultados;
    }

    /**
     * Calcula la capacidad de un alojamiento para acomodar a la cantidad de adultos y niños.
     *
     * @param alojamiento El alojamiento a evaluar.
     * @param adultos      La cantidad de adultos.
     * @param ninos        La cantidad de niños.
     * @return La capacidad total del alojamiento.
     */
    private int calcularCapacidad(Accommodation alojamiento, int adultos, int ninos) {
        // Aquí puedes implementar lógica más compleja basada en el tipo de alojamiento.
        // Para simplificar, asumimos una capacidad fija por tipo.
        switch (alojamiento.getTipo().toLowerCase()) {
            case "hotel":
                // Cada habitación puede acomodar 2 adultos y 1 niño
                Hotel hotel = (Hotel) alojamiento;
                int capacidadHotel = 0;
                for (Room habitacion : hotel.getHabitaciones()) {
                    if (habitacion.isDisponible()) {
                        capacidadHotel += 3; // 2 adultos + 1 niño por habitación
                    }
                }
                return capacidadHotel;
            case "apartamento":
                return adultos + ninos + 1; // Un adulto extra por apartamento
            case "finca":
                return adultos + ninos + 2; // Dos adultos extra por finca
            default:
                return adultos + ninos;
        }
    }

    /**
     * Cuenta cuántas habitaciones disponibles tiene un alojamiento.
     *
     * @param alojamiento    El alojamiento a evaluar.
     * @param habitacionesReq La cantidad de habitaciones requeridas.
     * @return La cantidad de habitaciones disponibles.
     */
    private int contarHabitacionesDisponibles(Accommodation alojamiento, int habitacionesReq) {
        if (alojamiento instanceof Hotel) {
            Hotel hotel = (Hotel) alojamiento;
            int disponibles = 0;
            for (Room habitacion : hotel.getHabitaciones()) {
                if (habitacion.isDisponible()) {
                    disponibles++;
                }
            }
            return disponibles;
        }
        // Para otros tipos de alojamiento, asumimos que tienen una sola "habitacion"
        return 1; // Si no es hotel, consideramos una sola disponibilidad
    }

    /**
     * Formatea la información de un alojamiento para mostrar al usuario.
     *
     * @param alojamiento El alojamiento cuya información se va a formatear.
     * @param diaInicio    La fecha de inicio de la reserva.
     * @param diaFin       La fecha de fin de la reserva.
     * @return Una cadena con la información formateada.
     */
    private String formatAlojamientoInfo(Accommodation alojamiento, LocalDate diaInicio, LocalDate diaFin) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(alojamiento.getNombre()).append("\n");
        sb.append("Tipo: ").append(alojamiento.getTipo()).append("\n");
        sb.append("Estrellas: ").append(alojamiento.getEstrellas()).append("\n");
        sb.append("Ubicación: ").append(alojamiento.getUbicacion()).append("\n");
        sb.append("Precio por noche: ").append(alojamiento.getPrecioPorNoche()).append("\n");
        sb.append("Disponibilidad: ").append("Disponible").append("\n"); // Simplificación
        sb.append("Fechas: ").append(diaInicio).append(" a ").append(diaFin);
        return sb.toString();
    }
}
