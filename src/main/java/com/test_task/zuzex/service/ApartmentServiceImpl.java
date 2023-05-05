package com.test_task.zuzex.service;

import com.test_task.zuzex.entity.Apartment;
import com.test_task.zuzex.entity.User;
import com.test_task.zuzex.exception.ApartmentWithoutOwnerException;
import com.test_task.zuzex.repository.ApartmentRepository;
import com.test_task.zuzex.repository.UserRepository;
import com.test_task.zuzex.request.ApartmentRequest;
import com.test_task.zuzex.response.ApartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    public ApartmentResponse getApartment(int id) {
        return ApartmentResponse.of(apartmentRepository.getReferenceById(id));
    }

    @Override
    @Transactional
    public ApartmentResponse saveApartment(ApartmentRequest apartmentRequest) {
        Apartment apartment = null;
        try {
            apartment = getApartmentOf(apartmentRequest);
        } catch (ApartmentWithoutOwnerException e) {
            System.err.println(e.getMessage());
        }
        assert apartment != null;
        apartmentRepository.save(apartment);
        return ApartmentResponse.of(apartment);
    }

    @Override
    @Transactional
    public void deleteApartment(int id) {
        apartmentRepository.deleteById(id);
    }

    private Apartment getApartmentOf(ApartmentRequest request) throws ApartmentWithoutOwnerException {
        Apartment apartment;
        if (request.getId() == 0) {
            apartment = new Apartment();
        } else {
            Optional<Apartment> optional = apartmentRepository.findById(request.getId());
            apartment = optional.orElseGet(Apartment::new);
        }
        apartment.setAddress(request.getAddress());
        if (request.getOwnerId() == 0) {
            throw new ApartmentWithoutOwnerException("Apartment Without Owner");
        } else {
            User owner = null;
            Optional<User> optionalUser = userRepository.findById(request.getOwnerId());
            if (optionalUser.isPresent()) {
                owner = optionalUser.get();
            }
            apartment.setOwner(owner);
        }
        return apartment;
    }
}
