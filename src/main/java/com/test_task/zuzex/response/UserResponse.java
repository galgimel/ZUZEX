package com.test_task.zuzex.response;

import com.test_task.zuzex.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponse {
    private final int id;
    private final String name;
    private final int age;
    private final String password;
    private final ApartmentResponse property;
//    private final ApartmentResponse apartment;

    public static UserResponse of(final User user) {
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .age(user.getAge())
            .password(user.getPassword())
            .property(ApartmentResponse.of(user.getProperty()))
//            .apartment(ApartmentResponse.of(user.getApartment()))
            .build();
    }
}
