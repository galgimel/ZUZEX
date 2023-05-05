package com.test_task.zuzex.request;
import com.test_task.zuzex.response.UserResponse;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ApartmentRequest {
    private final int id;
    private final String address;
    private final int ownerId;
}
