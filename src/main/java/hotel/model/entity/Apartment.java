// File: hotel/model/entity/Apartment.java
package hotel.model.entity;

public class Apartment extends Accommodation {
    public Apartment(String nombre, int calificacion, String ubicacion, double precioPorNoche) {
        super("Apartamento", nombre, calificacion, ubicacion, precioPorNoche);
    }
}
