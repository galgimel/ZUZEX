package com.test_task.zuzex.rest_controller;

import com.test_task.zuzex.exception.UserWithoutApartmentException;
import com.test_task.zuzex.request.UserRequest;
import com.test_task.zuzex.response.UserResponse;
import com.test_task.zuzex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<UserResponse> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(
        @PathVariable final int id
    ) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserResponse addNewUser(@RequestBody final UserRequest userRequest) throws UserWithoutApartmentException {
        return userService.saveUser(userRequest);
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody final UserRequest userRequest) throws UserWithoutApartmentException {
        return userService.saveUser(userRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(
        @PathVariable final int id
    ) {
        userService.deleteUser(id);
        return "Пользователь с ID: " + id + ", был удален";
    }
}
