package com.test_task.zuzex.service;

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
    public UserResponse saveUser(UserRequest userRequest) throws UserWithoutApartmentException {
        User user = of(userRequest);
        userRepository.save(user);
        return UserResponse.of(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    private User of(UserRequest request) throws UserWithoutApartmentException {
        User user = request.getId() == 0
            ? new User()
            : userRepository.findById(request.getId()).orElseGet(User::new);

        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());

        if (request.getPropertyId() == 0) {
            user.setProperty(null);
        } else {
            user.setProperty(apartmentRepository.findById(request.getPropertyId()).orElse(null));
        }
        return user;
    }
}