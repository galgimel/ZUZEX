package com.test_task.zuzex.response;

import com.test_task.zuzex.entity.Apartment;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApartmentResponse {
    private final int id;
    private final String address;
    private final int ownerId;
    public static ApartmentResponse of(Apartment apartment) {
        return ApartmentResponse.builder()
            .id(apartment.getId())
            .address(apartment.getAddress())
            .ownerId(apartment.getOwner() == null ? 0 : apartment.getOwner().getId())
            .build();
    }
}
