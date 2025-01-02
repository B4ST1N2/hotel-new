// File: hotel/view/AccommodationView.java
package hotel.view;

import hotel.model.entity.Accommodation;
import hotel.model.entity.SearchCriteria;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AccommodationView {
    private Scanner scanner;

    public AccommodationView() {
        this.scanner = new Scanner(System.in);
    }

    public SearchCriteria obtenerCriteriosBusqueda() {
        System.out.print("Ingrese la ciudad: ");
        String ciudad = scanner.nextLine();

        System.out.print("Ingrese el tipo de alojamiento (Hotel, Apartamento, Finca, Día de Sol): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese el día de inicio del hospedaje (yyyy-mm-dd): ");
        LocalDate diaInicio = parsearFecha(scanner.nextLine());

        System.out.print("Ingrese el día de finalización del hospedaje (yyyy-mm-dd): ");
        LocalDate diaFin = parsearFecha(scanner.nextLine());

        System.out.print("Ingrese la cantidad de adultos: ");
        int adultos = parsearEntero(scanner.nextLine());

        System.out.print("Ingrese la cantidad de niños: ");
        int ninos = parsearEntero(scanner.nextLine());

        System.out.print("Ingrese la cantidad de habitaciones: ");
        int habitacionesReq = parsearEntero(scanner.nextLine());

        return new SearchCriteria(ciudad, tipo, diaInicio, diaFin, adultos, ninos, habitacionesReq);
    }

    public void mostrarResultados(List<Accommodation> alojamientos) {
        if (alojamientos.isEmpty()) {
            System.out.println("No se encontraron alojamientos disponibles con los parámetros ingresados.");
        } else {
            System.out.println("\nAlojamientos encontrados:");
            for (Accommodation alojamiento : alojamientos) {
                System.out.println("\n----------------------------------------");
                System.out.println(alojamiento.toString());
                // Si es Dia de Sol, mostrar actividades
                if (alojamiento instanceof hotel.model.entity.DiaDeSol) {
                    hotel.model.entity.DiaDeSol diaDeSol = (hotel.model.entity.DiaDeSol) alojamiento;
                    System.out.println("Actividades:");
                    for (String actividad : diaDeSol.getActividades()) {
                        System.out.println("- " + actividad);
                    }
                    System.out.println("Incluye Refrigerio: " + (diaDeSol.isIncluyeRefrigerio() ? "Sí" : "No"));
                    System.out.println("Precio por Actividad: $" + diaDeSol.getPrecioActividad());
                }
                // Si es Finca, mostrar actividades
                if (alojamiento instanceof hotel.model.entity.Farm) {
                    hotel.model.entity.Farm finca = (hotel.model.entity.Farm) alojamiento;
                    System.out.println("Actividades:");
                    for (String actividad : finca.getActividades()) {
                        System.out.println("- " + actividad);
                    }
                    System.out.println("Incluye Almuerzo: " + (finca.isIncluyeAlmuerzo() ? "Sí" : "No"));
                    System.out.println("Precio por Actividad: $" + finca.getPrecioActividad());
                }
            }
            System.out.println("\n----------------------------------------");
        }
    }

    public void mostrarError(String mensaje) {
        System.out.println("\nError: " + mensaje);
    }

    private LocalDate parsearFecha(String fechaStr) {
        try {
            return LocalDate.parse(fechaStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Por favor, use yyyy-mm-dd.");
            return parsearFecha(scanner.nextLine());
        }
    }

    private int parsearEntero(String numeroStr) {
        try {
            return Integer.parseInt(numeroStr);
        } catch (Exception e) {
            System.out.println("Número inválido. Por favor, ingrese un número entero.");
            return parsearEntero(scanner.nextLine());
        }
    }
}
