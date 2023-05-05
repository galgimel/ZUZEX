package com.test_task.zuzex.service;

import com.test_task.zuzex.request.ApartmentRequest;
import com.test_task.zuzex.response.ApartmentResponse;

import java.util.List;

public interface ApartmentService {
    List<ApartmentResponse> getAllApartments();
    ApartmentResponse getApartment(int id);
    ApartmentResponse saveApartment(ApartmentRequest apartmentRequest);
    void deleteApartment(int id);
}
