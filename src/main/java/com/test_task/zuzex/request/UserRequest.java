package com.test_task.zuzex.request;
import com.test_task.zuzex.response.ApartmentResponse;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class UserRequest {
    private final int id;
    private final String name;
    private final int age;
    private final String password;
    private final int propertyId;
    private final int apartmentId;
}
