// File: hotel/Main.java
package hotel;

import hotel.facade.HotelFacade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciar la fachada
        HotelFacade hotelFacade = new HotelFacade();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            mostrarMenu();
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    hotelFacade.buscarAlojamientos();
                    break;
                case "2":
                    hotelFacade.confirmarReserva();
                    break;
                case "3":
                    hotelFacade.actualizarReserva();
                    break;
                case "4":
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 4.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n---- Menú ----");
        System.out.println("1. Buscar Alojamiento");
        System.out.println("2. Confirmar Reserva");
        System.out.println("3. Actualizar Reserva");
        System.out.println("4. Salir");
        System.out.print("Elija una opción: ");
    }
}
