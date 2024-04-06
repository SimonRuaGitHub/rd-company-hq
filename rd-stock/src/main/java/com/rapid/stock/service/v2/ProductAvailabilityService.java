package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.model.v2.Availability;
import org.springframework.data.domain.Page;

public interface ProductAvailabilityService {
    Availability save(AvailabilityDTO availabilityDTO);
    Page<Availability> getAll(int page, int size);
}
