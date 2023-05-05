package com.test_task.zuzex.service;

import com.test_task.zuzex.entity.Apartment;
import com.test_task.zuzex.entity.User;
import com.test_task.zuzex.exception.UserWithoutApartmentException;
import com.test_task.zuzex.repository.ApartmentRepository;
import com.test_task.zuzex.repository.UserRepository;
import com.test_task.zuzex.request.UserRequest;
import com.test_task.zuzex.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, ApartmentRepository apartmentRepository) {
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    @Transactional
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserResponse::of)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse getUser(int id) {
        return UserResponse.of(userRepository.getReferenceById(id));
    }

    @Override
    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User user = null;
        try {
            user = getUserOf(userRequest);
        } catch (UserWithoutApartmentException e) {
            System.err.println(e.getMessage());
        }
        assert user != null;
        userRepository.save(user);
        return UserResponse.of(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    private User getUserOf(UserRequest request) throws UserWithoutApartmentException {
        User user;
        if (request.getId() == 0) {
            user = new User();
        } else {
            Optional<User> optional = userRepository.findById(request.getId());
            user = optional.orElseGet(User::new);
        }
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());

        if (request.getPropertyId() == 0) {
            user.setProperty(null);
        } else {
            Apartment property = null;
            Optional<Apartment> optional =
                apartmentRepository.findById(request.getPropertyId());
            if (optional.isPresent()) {
                property = optional.get();
            }
            user.setProperty(property);
        }

        if (request.getApartmentId() == 0) {
            throw new UserWithoutApartmentException("User Without Apartment");
        } else {
            Apartment apartment = null;
            Optional<Apartment> optional =
                apartmentRepository.findById(request.getApartmentId());
            if (optional.isPresent()) {
                apartment = optional.get();
            }
            user.setProperty(apartment);
        }
        return user;
    }
}
