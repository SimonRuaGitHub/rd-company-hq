package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.model.v2.Availability;

public interface ProductAvailabilityService {
    Availability save(AvailabilityDTO availabilityDTO);
}
