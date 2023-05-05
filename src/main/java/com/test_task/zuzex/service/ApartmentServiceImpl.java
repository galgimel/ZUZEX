package com.test_task.zuzex.service;

import com.test_task.zuzex.entity.Apartment;
import com.test_task.zuzex.exception.ApartmentWithoutOwnerException;
import com.test_task.zuzex.repository.ApartmentRepository;
import com.test_task.zuzex.repository.UserRepository;
import com.test_task.zuzex.request.ApartmentRequest;
import com.test_task.zuzex.response.ApartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, UserRepository userRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<ApartmentResponse> getAllApartments() {
        return apartmentRepository.findAll().stream()
            .map(ApartmentResponse::of)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApartmentResponse getApartment(final int id) {
        return ApartmentResponse.of(apartmentRepository.getReferenceById(id));
    }

    @Override
    @Transactional
    public ApartmentResponse saveApartment(final ApartmentRequest apartmentRequest) throws ApartmentWithoutOwnerException {
        var apartment = of(apartmentRequest);
        apartmentRepository.save(apartment);
        return ApartmentResponse.of(apartment);
    }

    @Override
    @Transactional
    public void deleteApartment(final int id) {
        apartmentRepository.deleteById(id);
    }

    private Apartment of(final ApartmentRequest request) throws ApartmentWithoutOwnerException {
        var apartment = request.getId() == 0
            ? new Apartment()
            : apartmentRepository.findById(request.getId()).orElseGet(Apartment::new);

        apartment.setAddress(request.getAddress());
        if (request.getOwnerId() == 0) {
            throw new ApartmentWithoutOwnerException("Apartment Without Owner");
        } else {
            apartment.setOwner(userRepository.findById(request.getOwnerId()).orElse(null));
        }
        return apartment;
    }
}