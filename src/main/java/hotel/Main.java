package hotel;


import hotel.controller.AccommodationController;
import hotel.controller.ReservationController;
import hotel.view.AccommodationView;
import hotel.view.ReservationView;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccommodationController alojamientoController = new AccommodationController();
        ReservationController reservaController = new ReservationController(alojamientoController);
        AccommodationView accommodationView = new AccommodationView(alojamientoController);
        ReservationView reservaView = new ReservationView(reservaController, alojamientoController);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---- Menú ----");
            System.out.println("1. Buscar Alojamiento");
            System.out.println("2. Confirmar Reserva");
            System.out.println("3. Actualizar Reserva");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    accommodationView.buscarAlojamientos(scanner);
                    break;
                case "2":
                    reservaView.confirmarReserva(scanner);
                    break;
                case "3":
                    reservaView.actualizarReserva(scanner);
                    break;
                case "4":
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
