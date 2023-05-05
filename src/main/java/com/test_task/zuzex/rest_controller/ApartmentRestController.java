package com.test_task.zuzex.rest_controller;

import com.test_task.zuzex.request.ApartmentRequest;
import com.test_task.zuzex.response.ApartmentResponse;
import com.test_task.zuzex.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentRestController {
    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentRestController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public List<ApartmentResponse> showAllApartments() {
        return apartmentService.getAllApartments();
    }

    @GetMapping("/{id}")
    public ApartmentResponse getApartment(
        @PathVariable final int id
    ) {
        return apartmentService.getApartment(id);
    }

    @PostMapping
    public ApartmentResponse addNewApartment(@RequestBody final ApartmentRequest apartmentRequest) {
        return apartmentService.saveApartment(apartmentRequest);
    }

    @PutMapping
    public ApartmentResponse updateApartment(@RequestBody final ApartmentRequest apartmentRequest) {
        return apartmentService.saveApartment(apartmentRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteApartment(
        @PathVariable final int id
    ) {
        apartmentService.deleteApartment(id);
        return "Квартира с ID: " + id + ", была удалена";
    }
}
