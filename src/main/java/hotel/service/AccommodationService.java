// File: hotel/service/AccommodationService.java
package hotel.service;

import hotel.controller.AccommodationController;
import hotel.model.entity.Accommodation;
import hotel.model.entity.SearchCriteria;

import java.util.List;

public class AccommodationService {
    private AccommodationController accommodationController;

    public AccommodationService() {
        this.accommodationController = AccommodationController.getInstance();
    }

    public List<Accommodation> buscarAlojamientos(SearchCriteria criteria) {
        return accommodationController.buscarAlojamientos(criteria);
    }

    public AccommodationController getAccommodationController() {
        return this.accommodationController;
    }
}
