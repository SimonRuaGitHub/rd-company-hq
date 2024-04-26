package com.rapid.stock.mapper.v2.response;

import com.rapid.stock.dto.AvailabilitySaveResponse;
import com.rapid.stock.model.v2.Availability;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductAvailabilityMapperSaveResponse {
    public AvailabilitySaveResponse map(Availability availability) {
        return AvailabilitySaveResponse
                .builder()
                .availabilityId(UUID.fromString(availability.getId()))
                .build();
    }
}
