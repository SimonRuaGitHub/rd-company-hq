package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AvailabilitySaveRequest;
import com.rapid.stock.dto.AvailabilitySaveResponse;
import com.rapid.stock.model.v2.Availability;
import org.springframework.data.domain.Page;

public interface ProductAvailabilityService {
    AvailabilitySaveResponse save(AvailabilitySaveRequest availabilitySaveRequest);
    Page<Availability> getAll(int page, int size);
    void delete(Long id);
}
