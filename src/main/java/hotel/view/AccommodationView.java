package hotel.view;

import hotel.controller.AccommodationController;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AccommodationView {
    private AccommodationController alojamientoController;

    public AccommodationView(AccommodationController alojamientoController) {
        this.alojamientoController = alojamientoController;
    }

    public void buscarAlojamientos(Scanner scanner) {
        try {
            System.out.print("Ingrese la ciudad: ");
            String ciudad = scanner.nextLine();
            System.out.print("Ingrese el tipo de alojamiento (Hotel, Apartamento, Finca): ");
            String tipo = scanner.nextLine();
            System.out.print("Ingrese el día de inicio (yyyy-mm-dd): ");
            LocalDate diaInicio = LocalDate.parse(scanner.nextLine());
            System.out.print("Ingrese el día de fin (yyyy-mm-dd): ");
            LocalDate diaFin = LocalDate.parse(scanner.nextLine());

            if (diaInicio.isAfter(diaFin)) {
                System.out.println("La fecha de inicio debe ser anterior a la fecha de fin.");
                return;
            }

            System.out.print("Ingrese la cantidad de adultos: ");
            int adultos = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la cantidad de niños: ");
            int ninos = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la cantidad de habitaciones: ");
            int habitacionesReq = Integer.parseInt(scanner.nextLine());

            if (adultos <= 0 || habitacionesReq <= 0) {
                System.out.println("Las cantidades de adultos y habitaciones deben ser positivas.");
                return;
            }

            List<String> resultados = alojamientoController.buscarAlojamientos(ciudad, tipo, diaInicio, diaFin, adultos, ninos, habitacionesReq);
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron alojamientos disponibles.");
            } else {
                System.out.println("Alojamientos encontrados:");
                for (String info : resultados) {
                    System.out.println(info);
                    System.out.println("----------------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar alojamientos: " + e.getMessage());
        }
    }
}
