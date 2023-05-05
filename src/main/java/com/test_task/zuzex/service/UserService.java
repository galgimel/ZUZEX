package com.test_task.zuzex.service;

import com.test_task.zuzex.exception.UserWithoutApartmentException;
import com.test_task.zuzex.request.UserRequest;
import com.test_task.zuzex.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse getUser(int id);

    UserResponse saveUser(UserRequest userRequest) throws UserWithoutApartmentException;

    void deleteUser(int id);
}
