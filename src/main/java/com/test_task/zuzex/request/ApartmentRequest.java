package com.test_task.zuzex.request;
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
